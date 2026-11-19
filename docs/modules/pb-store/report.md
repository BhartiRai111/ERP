# pb-store / report

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/store/src/report/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Site (Text); rule: From Date is mandatory.; rule: To Date is mandatory.; rule: From Date should be less than or equal to To Date.; rule: Report Type is mandatory.; rule: Posting Date is mandatory.; rule: Opening Date is mandatory.; Unit (Text); MOQ Ratio (Text); rule: 'As On Date' is mandatory.; rule: 'Value On' is mandatory.; rule: 'Report For' is mandatory.; rule: As Date is mandatory.; rule: item is mandatory.; rule: Report For is mandatory.; rule: Value On is mandatory.; rule: Report Choice For is mandatory.; rule: From site is mandatory.; rule: To site is mandatory.
- Lookups/suggestions: getTxtHSite, getTxtHGateNo, getTxtHTransporter, getTxtHTransportationMode, getTxtHShowServices, getTxtHRecordType, getTxtHInTransiteNo, getTxtHVoucherType, getTxtHDepartment, getTxtHItem, getTxtHItemCat, getTxtHItemGroup, getTxtHValueOn, getTxtHEvaluationStock, getTxtHReportFor, getTxtHBf, getTxtHGsm, getTxtHItemNature, getTxtHVoucherNo, getTxtHIssueTo, getTxtHItemName, getTxtHWidth, getTxtHLength, getTxtHSizeUnit, getTxtHStockType, getTxtHRequisitionNo, getTxtHPackSize, getTxtHReceiveTo, getTxtHWarehouse, getTxtHUnit, getTxtHHavingWeight, getTxtHSaleOrderNo, getTxtHParty, getTxtHDistributor, getTxtHDeliveryCity, getTxtHYesNo, getTxtHBF, getTxtHGSM, getTxtHContractualSaleOrder, getTxtHRecievedFrom, getTxtHTransferNo, getTxtHTransitStatus, getTxtHPolicyNo, getTxtHOrderTransferNo, getTxtHTransferType, getTxtHPreparedBy, getTxtHModifiedBy, getTxtHTransferTo, getTxtHReportChoice, getTxtHItemType, getTxtHDate, getTxtHItemCategory, getTxtHManufacturer, getTxtHItemCompany, getTxtHReportType, getTxtHSalesman, getTxtHStatus, getTxtHReelNo, getTxtHPartyReelNo, getTxtHItemStatus, getTxtHRequisition, getTxtHReqType, getTxtHShowUsage
- CSV export: no

## Files
| file | lines |
|---|---|
| client/RptBFGSMClosingStock.java | 1097 |
| client/RptFinishStockWithReelNo.java | 1273 |
| client/RptInventoryAgeingDisplay.java | 176 |
| client/RptItemIssueRegister.java | 742 |
| client/RptItemMovementDisplay.java | 350 |
| client/RptItemOpeningRegister.java | 603 |
| client/RptItemReceiveRegister.java | 681 |
| client/RptItemTransferRegister.java | 631 |
| client/RptMovementAnalysisDisplay.java | 370 |
| client/RptOrderTransferRegister.java | 717 |
| client/RptPhysicalStockBeforePosting.java | 451 |
| client/RptReelSheetIssueRegister.java | 883 |
| client/RptReelSheetReceiveRegister.java | 925 |
| client/RptRequistionRegister.java | 656 |
| client/RptSheetStockSummary.java | 1040 |
| client/RptSiteStockInTransitRegister.java | 676 |
| client/RptSiteStockTransferRegister.java | 726 |
| client/RptStockAsOnDateRegister.java | 556 |
| client/RptStockBatch.java | 166 |
| client/RptStockDayBookSummary.java | 376 |
| client/RptStockLedger.java | 548 |
| client/RptStockLedgerDisplay.java | 163 |
| client/RptStockSummary.java | 557 |
| client/RptStockSummaryDisplay.java | 172 |
| client/RptStockWithMoqReport.java | 539 |
| client/RptSupplierWiseStockDisplay.java | 185 |
| client/RptTradeItemStockAsOnDateRegister.java | 691 |
| server/RptBFGSMClosingStock_SrvImpl.java | 2228 |
| server/RptFinishStockWithReelNo_SrvImpl.java | 2476 |
| server/RptInventoryAgeingDisplay_SrvImpl.java | 708 |
| server/RptItemIssueRegister_SrvImpl.java | 1057 |
| server/RptItemMovementDisplay_SrvImpl.java | 97 |
| server/RptItemOpeningRegister_SrvImpl.java | 834 |
| server/RptItemReceiveRegister_SrvImpl.java | 988 |
| server/RptItemTransferRegister_SrvImpl.java | 858 |
| server/RptMovementAnalysisDisplay_SrvImpl.java | 105 |
| server/RptOrderTransferRegister_SrvImpl.java | 2054 |
| server/RptPhysicalStockBeforePosting_SrvImpl.java | 844 |
| server/RptReelSheetIssueRegister_SrvImpl.java | 1333 |
| server/RptReelSheetReceiveRegister_SrvImpl.java | 1357 |
| server/RptRequistionRegister_SrvImpl.java | 892 |
| server/RptSheetStockSummary_SrvImpl.java | 2116 |
| server/RptSiteStockInTransitRegister_SrvImpl.java | 873 |
| server/RptSiteStockTransferRegister_SrvImpl.java | 959 |
| server/RptStockAsOnDateRegister_SrvImpl.java | 1217 |
| server/RptStockBatch_SrvImpl.java | 100 |
| server/RptStockDayBookSummary_SrvImpl.java | 519 |
| server/RptStockLedgerDisplay_SrvImpl.java | 104 |
| server/RptStockLedger_SrvImpl.java | 938 |
| server/RptStockSummaryDisplay_SrvImpl.java | 103 |
| server/RptStockSummary_SrvImpl.java | 1692 |
| server/RptStockWithMoqReport_SrvImpl.java | 1493 |
| server/RptSupplierWiseStockDisplay_SrvImpl.java | 636 |
| server/RptTradeItemStockAsOnDateRegister_SrvImpl.java | 1666 |
| shared/RptBFGSMClosingStock_Srv.java | 58 |
| shared/RptBFGSMClosingStock_SrvAsync.java | 59 |
| shared/RptBFGSMClosingStock_Vld.java | 21 |
| shared/RptFinishStockWithReelNo_Srv.java | 70 |
| shared/RptFinishStockWithReelNo_SrvAsync.java | 70 |
| shared/RptFinishStockWithReelNo_Vld.java | 19 |
| shared/RptInventoryAgeingDisplay_Srv.java | 15 |
| shared/RptInventoryAgeingDisplay_SrvAsync.java | 15 |
| shared/RptItemIssueRegister_Srv.java | 43 |
| shared/RptItemIssueRegister_SrvAsync.java | 42 |
| shared/RptItemIssueRegister_Vld.java | 28 |
| shared/RptItemMovementDisplay_Srv.java | 15 |
| shared/RptItemMovementDisplay_SrvAsync.java | 15 |
| shared/RptItemOpeningRegister_Srv.java | 38 |
| shared/RptItemOpeningRegister_SrvAsync.java | 38 |
| shared/RptItemOpeningRegister_Vld.java | 21 |
| shared/RptItemReceiveRegister_Srv.java | 40 |
| shared/RptItemReceiveRegister_SrvAsync.java | 40 |
| shared/RptItemReceiveRegister_Vld.java | 27 |
| shared/RptItemTransferRegister_Srv.java | 37 |
| shared/RptItemTransferRegister_SrvAsync.java | 37 |
| shared/RptItemTransferRegister_Vld.java | 28 |
| shared/RptMovementAnalysisDisplay_Srv.java | 15 |
| shared/RptMovementAnalysisDisplay_SrvAsync.java | 15 |
| shared/RptOrderTransferRegister_Srv.java | 52 |
| shared/RptOrderTransferRegister_SrvAsync.java | 52 |
| shared/RptOrderTransferRegister_Vld.java | 27 |
| shared/RptPhysicalStockBeforePosting_Srv.java | 29 |
| shared/RptPhysicalStockBeforePosting_SrvAsync.java | 30 |
| shared/RptPhysicalStockBeforePosting_Vld.java | 20 |
| shared/RptReelSheetIssueRegister_Srv.java | 51 |
| shared/RptReelSheetIssueRegister_SrvAsync.java | 50 |
| shared/RptReelSheetIssueRegister_Vld.java | 28 |
| shared/RptReelSheetReceiveRegister_Srv.java | 53 |
| shared/RptReelSheetReceiveRegister_SrvAsync.java | 52 |
| shared/RptReelSheetReceiveRegister_Vld.java | 28 |
| shared/RptRequistionRegister_Srv.java | 37 |
| shared/RptRequistionRegister_SrvAsync.java | 38 |
| shared/RptSheetStockSummary_Srv.java | 58 |
| shared/RptSheetStockSummary_SrvAsync.java | 58 |
| shared/RptSheetStockSummary_Vld.java | 19 |
| shared/RptSiteStockInTransitRegister_Srv.java | 40 |
| shared/RptSiteStockInTransitRegister_SrvAsync.java | 42 |
| shared/RptSiteStockInTransitRegister_Vld.java | 32 |
| shared/RptSiteStockTransferRegister_Srv.java | 42 |
| shared/RptSiteStockTransferRegister_SrvAsync.java | 44 |
| shared/RptSiteStockTransferRegister_Vld.java | 32 |
| shared/RptStockAsOnDateRegister_Srv.java | 36 |
| shared/RptStockAsOnDateRegister_SrvAsync.java | 35 |
| shared/RptStockAsOnDateRegister_Vld.java | 27 |
| shared/RptStockBatch_Srv.java | 15 |
| shared/RptStockBatch_SrvAsync.java | 15 |
| shared/RptStockDayBookSummary_Srv.java | 34 |
| shared/RptStockDayBookSummary_SrvAsync.java | 34 |
| shared/RptStockDayBookSummary_Vld.java | 30 |
| shared/RptStockLedgerDisplay_Srv.java | 15 |
| shared/RptStockLedgerDisplay_SrvAsync.java | 15 |
| shared/RptStockLedger_Srv.java | 39 |
| shared/RptStockLedger_SrvAsync.java | 38 |
| shared/RptStockLedger_Vld.java | 29 |
| shared/RptStockSummaryDisplay_Srv.java | 15 |
| shared/RptStockSummaryDisplay_SrvAsync.java | 15 |
| shared/RptStockSummary_Srv.java | 39 |
| shared/RptStockSummary_SrvAsync.java | 38 |
| shared/RptStockSummary_Vld.java | 33 |
| shared/RptStockWithMoqReport_Srv.java | 31 |
| shared/RptStockWithMoqReport_SrvAsync.java | 31 |
| shared/RptStockWithMoqReport_Vld.java | 28 |
| shared/RptSupplierWiseStockDisplay_Srv.java | 16 |
| shared/RptSupplierWiseStockDisplay_SrvAsync.java | 16 |
| shared/RptTradeItemStockAsOnDateRegister_Srv.java | 39 |
| shared/RptTradeItemStockAsOnDateRegister_SrvAsync.java | 39 |
| shared/RptTradeItemStockAsOnDateRegister_Vld.java | 28 |
| shared/TxtHConsumptionNo_Mdl.java | 32 |
| shared/TxtHDate_Mdl.java | 66 |
| shared/TxtHItemCompany_Mdl.java | 23 |
| shared/TxtHItemName_Mdl.java | 40 |
| shared/TxtHItem_Mdl.java | 68 |
| shared/TxtHManufacturer_Mdl.java | 40 |
| shared/TxtHParty_Mdl.java | 50 |
| shared/TxtHRecieveNo_Mdl.java | 33 |
| shared/TxtHRequisitionNo_Mdl.java | 41 |
| shared/TxtHVoucherNo_Mdl.java | 161 |
| shared/TxtTransporters_Mdl.java | 60 |

## Models (shared DTOs)
### TxtHDate_Mdl (extends `KeyField`)
| type | field |
|---|---|
| String | departmentCode |
| String | departmentName |
| String | filterTypeCode |
| String | filterTypeName |
| String | filterValueCode |
| String | filterValueName |

### TxtHVoucherNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | shortName |
| String | voucherType |
| String | voucherDate |
| String | partyName |
| String | partyCity |
| String | distributorName |
| String | consigneeName |
| String | partyOrderNo |
| String | partyOrderDate |

### TxtHItemName_Mdl (extends `KeyField implements Serializable,IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | unitCode |
| String | unitName |

### TxtHRequisitionNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | requisitionDate |
| String | requisitionVType |
| String | requestedBy |

### TxtHManufacturer_Mdl (extends `KeyField implements Serializable,IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityName |
| String | address |

### TxtHItem_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | itemGroup |
| String | itemCategory |
| String | unit |
| String | unitCode |
| String | manualCode |
| String | itemType |

### TxtHItemCompany_Mdl (extends `KeyField implements Serializable,IsSerializable`)
| type | field |
|---|---|
| String | shortName |

### TxtTransporters_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | stateCode |
| String | stateName |
| String | manualCode |
| String | cityName |
| String | partyType |

### TxtHParty_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityName |
| String | acGroupName |
| String | nature |

### TxtHConsumptionNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | consumptionDate |
| String | consumptionType |

### TxtHRecieveNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | recieveDate |
| String | recieveType |

## RPC interface
| method | params | returns |
|---|---|---|
| execute | String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, DefaultValue_Mdl defaultValue_Mdl | DmResult<KeyFieldExtended> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHVoucherNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHConsumptionNo_Mdl>> |
| getTxtHDepartment | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHIssueTo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVoucherType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemName_Mdl>> |
| getTxtHItemCat | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHBf | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGsm | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemNature | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getGroupName | String excludeGroup, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHWidth | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHLength | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSizeUnit | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHReportFor | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHStockType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHSite | boolean isFromSite,DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGateNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTransporter | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtTransporters_Mdl>> |
| getTxtHTransportationMode | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHShowServices | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getGroupName | String excludeGroup, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getTxtHRecordType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTransferNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVoucherType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTransitStatus | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPolicyNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getTxtHSaleOrderNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucherNo_Mdl>> |
| getTxtHParty | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getTxtHDistributor | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getTxtHDeliveryCity | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getxtHProformaInvoiceNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHContractualSaleOrder | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getItemName | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getTxtHItemCat | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHYesNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHBF | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGSM | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHWidth | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHLength | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHHavingWeight | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHStockType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSizeUnit | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDepartment | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, DefaultValue_Mdl defaultValue_Mdl | DmResult<String> |

## RPC interface
| method | params | returns |
|---|---|---|
| execute | String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<KeyFieldExtended> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHWarehouse | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItem | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getTxtHItemCat | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHValueOn | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHReportFor | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getTxtHBf | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGsm | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemNature | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| execute | String widgetName,LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, DefaultValue_Mdl defaultValue_Mdl | DmResult<KeyFieldExtended> |

## RPC interface
| method | params | returns |
|---|---|---|
| execute | String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<KeyFieldExtended> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemNature | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHWarehouse | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemCat | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHUnit | String nature, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItem | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getGroupBy | String tag2, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDepartment | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItem | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getTxtHItemCat | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHValueOn | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<KeyFieldExtended> |
| getTxtHEvaluationStock | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getTxtHReportFor | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHBf | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGsm | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemNature | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getTxtHSaleOrderNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucherNo_Mdl>> |
| getTxtHParty | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getTxtHDistributor | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getTxtHDeliveryCity | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getxtHProformaInvoiceNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHContractualSaleOrder | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getItemName | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getTxtHItemCat | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHYesNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHBF | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGSM | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemNature | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHWidth | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHLength | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHReportChoice | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHHavingWeight | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, RptMovementAnalysisDisplay_Mdl salesSummaryPrint_Mdl | DmResult<String> |

## RPC interface
| method | params | returns |
|---|---|---|
| execute | String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<KeyFieldExtended> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHWarehouse | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItem | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getTxtHItemCat | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHValueOn | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHReportFor | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getTxtHBf | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGsm | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemNature | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHUnit | String nature, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getGroupBy | String tag2, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHVoucherNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHRecieveNo_Mdl>> |
| getTxtHDepartment | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHRecievedFrom | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVoucherType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemName_Mdl>> |
| getTxtHItemCat | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getGroupName | String excludeGroup, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHBf | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGsm | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemNature | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHSite | boolean isFromSite,DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGateNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTransporter | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtTransporters_Mdl>> |
| getTxtHTransportationMode | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHShowServices | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getGroupName | String excludeGroup, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getTxtHRecordType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHInTransiteNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVoucherType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHItemName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getTxtHItemGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemCategory | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDate | String reportType, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHDate_Mdl>> |
| getTxtHDepartment | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHReportType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHManufacturer | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHManufacturer_Mdl>> |
| getTxtHItemCompany | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemCompany_Mdl>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHVoucherNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHConsumptionNo_Mdl>> |
| getTxtHDepartment | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHIssueTo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVoucherType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemName_Mdl>> |
| getTxtHItemCat | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHRequisitionNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHRequisitionNo_Mdl>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHBf | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGsm | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemNature | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getGroupName | String excludeGroup, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHVoucherNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHConsumptionNo_Mdl>> |
| getTxtHDepartment | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHReceiveTo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVoucherType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemName_Mdl>> |
| getTxtHItemCat | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHBf | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGsm | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemNature | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getGroupName | String excludeGroup, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHWidth | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHLength | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSizeUnit | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHReportFor | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHStockType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| TxtHAgainst | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, DefaultValue_Mdl defaultValue_Mdl | DmResult<String> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItem | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getTxtHEvaluationStock | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |

## RPC interface
| method | params | returns |
|---|---|---|
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<KeyFieldExtended> |
| getTxtHSaleOrderNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucherNo_Mdl>> |
| getTxtHParty | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getTxtHDistributor | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getTxtHDeliveryCity | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSalesman | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getxtHProformaInvoiceNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHContractualSaleOrder | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getItemName | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getTxtHItemCat | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHStatus | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getGroupName | String excludeGroup, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHYesNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHReportType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHHavingWeight | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHReportFor | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHBF | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGSM | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHStockType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHWidth | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSizeUnit | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHReelNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPartyReelNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDepartment | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDepartment | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItem | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getTxtHItemCat | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHValueOn | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHEvaluationStock | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getTxtHPackSize | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHBf | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGsm | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemNature | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, DefaultValue_Mdl defaultValue_Mdl | DmResult<String> |

## RPC interface
| method | params | returns |
|---|---|---|
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHOrderTransferNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTransferType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVoucherType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucherNo_Mdl>> |
| execute | String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<KeyFieldExtended> |
| getTxtHParty | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getItemName | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getTxtHItemNature | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHYesNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHReportFor | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHBF | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGSM | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHWidth | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHLength | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPreparedBy | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHModifiedBy | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSizeUnit | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getTxtHVoucherNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDepartment | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHRecievedFrom | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVoucherType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemName_Mdl>> |
| getTxtHItemCat | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHBf | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGsm | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemNature | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHItemStatus | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDepartment | boolean isFromDepartment, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHRequisition | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemName | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHReqType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHShowUsage | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHRequisitionNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHRequisitionNo_Mdl>> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getTxtHBf | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGsm | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemNature | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, IMSetting_Mdl displaySetting_Mdl | DmResult<String> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHDepartment | boolean isFromDepartment, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTransferNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVoucherType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemName_Mdl>> |
| getTxtHItemCat | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getTxtHTransferTo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHBf | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGsm | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemNature | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `RptReelSheetReceiveRegister_Vld`
```java
public class RptReelSheetReceiveRegister_Vld {
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

## Validator `RptItemTransferRegister_Vld`
```java
public class RptItemTransferRegister_Vld {
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

## Validator `RptPhysicalStockBeforePosting_Vld`
```java
public class RptPhysicalStockBeforePosting_Vld {
    public static void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter) {
        if (DmShared.xNull(hashMapFilter.get("reportType").getText()).equalsIgnoreCase("")) {
            throw new DmException(20, "Report Type is mandatory.");
        }
        if (DmShared.dNull(hashMapFilter.get("postingDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(40, "Posting Date is mandatory.");
        }
    }
}
```

## Validator `RptItemOpeningRegister_Vld`
```java
public class RptItemOpeningRegister_Vld {
    public static void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter) {
        if (DmShared.dNull(hashMapFilter.get("openingDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(20, "Opening Date is mandatory.");
        }
        DmShared.isValidText(hashMapFilter.get("site").getTag(), "Site", 200);
    }
}
```

## Validator `RptStockWithMoqReport_Vld`
```java
public class RptStockWithMoqReport_Vld {
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
        DmShared.isValidText(hashMapFilter.get("site").getTag(), "Site", 400);
        DmShared.isValidText(hashMapFilter.get("unitCode").getTag(), "Unit", 600);
        DmShared.isValidText(hashMapFilter.get("moqRatio").getText(), "MOQ Ratio", 700);
    }
}
```

## Validator `RptItemIssueRegister_Vld`
```java
public class RptItemIssueRegister_Vld {
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

## Validator `RptStockAsOnDateRegister_Vld`
```java
public class RptStockAsOnDateRegister_Vld {
    public static void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter) {
        if (DmShared.dNull(hashMapFilter.get("asOnDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(20, "'As On Date' is mandatory.");
        }
        if (DmShared.xNull(hashMapFilter.get("valueOn").getTag()).equalsIgnoreCase("")) {
            throw new DmException(200, "'Value On' is mandatory.");
        }
        if (DmShared.xNull(hashMapFilter.get("reportFor").getTag()).equalsIgnoreCase("")) {
            throw new DmException(200, "'Report For' is mandatory.");
        }
        DmShared.isValidText(hashMapFilter.get("site").getTag(), "Site", 300);
    }
}
```

## Validator `RptSheetStockSummary_Vld`
```java
public class RptSheetStockSummary_Vld {
    public static void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter) {
        if (DmShared.dNull(hashMapFilter.get("AsDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(20, "As Date is mandatory.");
        }
        DmShared.isValidText(hashMapFilter.get("site").getTag(), "Site", 200);
    }
}
```

## Validator `RptStockDayBookSummary_Vld`
```java
public class RptStockDayBookSummary_Vld {
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
        if (DmShared.xNull(hashMapFilter.get("item").getTag()).equalsIgnoreCase("")) {
            throw new DmException(200, "item is mandatory.");
        }
        DmShared.isValidText(hashMapFilter.get("site").getTag(), "Site", 300);
    }
}
```

## Validator `RptStockSummary_Vld`
```java
public class RptStockSummary_Vld {
    public static void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter) {
        if (DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(20, "From Date is mandatory.");
        }
        if (DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(40, "To Date is mandatory.");
        }
        if (DmShared.xNull(hashMapFilter.get("reportFor").getText()).equalsIgnoreCase("")) {
            throw new DmException(60, "Report For is mandatory.");
        }
        if ((!DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) && (!DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase(""))) {
            if (DmShared.convertDate(hashMapFilter.get("fromDate").getText()).getTime() > DmShared.convertDate(hashMapFilter.get("toDate").getText()).getTime()) {
                throw new DmException(100, "From Date should be less than or equal to To Date.");
            }
        }
        if (DmShared.xNull(hashMapFilter.get("valueOn").getTag()).equalsIgnoreCase("")) {
            throw new DmException(200, "Value On is mandatory.");
        }
        DmShared.isValidText(hashMapFilter.get("site").getTag(), "Site", 300);
    }
}
```

## Validator `RptBFGSMClosingStock_Vld`
```java
public class RptBFGSMClosingStock_Vld {
    public static void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter) {
        if (DmShared.dNull(hashMapFilter.get("AsDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(20, "As Date is mandatory.");
        }
        if (DmShared.xNull(hashMapFilter.get("ReportChoice").getText()).equalsIgnoreCase("")) {
            throw new DmException(60, "Report Choice For is mandatory.");
        }
        DmShared.isValidText(hashMapFilter.get("site").getTag(), "Site", 200);
    }
}
```

## Validator `RptOrderTransferRegister_Vld`
```java
public class RptOrderTransferRegister_Vld {
    public static void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter) {
        if (DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(20, "From Date is mandatory.");
        }
        if (DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(40, "To Date is mandatory.");
        }
        DmShared.isValidText(hashMapFilter.get("site_Code").getTag(), "Site", 200);
        if ((!DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) && (!DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase(""))) {
            if (DmShared.convertDate(hashMapFilter.get("fromDate").getText()).getTime() > DmShared.convertDate(hashMapFilter.get("toDate").getText()).getTime()) {
                throw new DmException(100, "From Date should be less than or equal to To Date.");
            }
        }
    }
}
```

## Validator `RptReelSheetIssueRegister_Vld`
```java
public class RptReelSheetIssueRegister_Vld {
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

## Validator `RptSiteStockInTransitRegister_Vld`
```java
public class RptSiteStockInTransitRegister_Vld {
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
         if (DmShared.xNull(hashMapFilter.get("fromSite").getTag()).equalsIgnoreCase("")) {
            throw new DmException(200, "From site is mandatory.");
        }
        DmShared.isValidText(hashMapFilter.get("site").getTag(), "Site", 200);
    }
}
```

## Validator `RptStockLedger_Vld`
```java
public class RptStockLedger_Vld {
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
        if (DmShared.xNull(hashMapFilter.get("valueOn").getTag()).equalsIgnoreCase("")) {
            throw new DmException(200, "Value On is mandatory.");
        }
        DmShared.isValidText(hashMapFilter.get("site").getTag(), "Site", 300);
    }
}
```

## Validator `RptFinishStockWithReelNo_Vld`
```java
public class RptFinishStockWithReelNo_Vld {
    public static void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter) {
        if (DmShared.dNull(hashMapFilter.get("AsDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(20, "As Date is mandatory.");
        }
        DmShared.isValidText(hashMapFilter.get("site").getTag(), "Site", 200);
    }
}
```

## Validator `RptTradeItemStockAsOnDateRegister_Vld`
```java
public class RptTradeItemStockAsOnDateRegister_Vld {
    public static void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter) {
        if (DmShared.dNull(hashMapFilter.get("asOnDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(20, "'As On Date' is mandatory.");
        }
        if (DmShared.xNull(hashMapFilter.get("valueOn").getTag()).equalsIgnoreCase("")) {
            throw new DmException(200, "'Value On' is mandatory.");
        }
        if (DmShared.xNull(hashMapFilter.get("reportFor").getTag()).equalsIgnoreCase("")) {
            throw new DmException(200, "'Report For' is mandatory.");
        }
        DmShared.isValidText(hashMapFilter.get("site").getTag(), "Site", 300);
        DmShared.isValidText(hashMapFilter.get("unitCode").getTag(), "Unit", 600);
    }
}
```

## Validator `RptItemReceiveRegister_Vld`
```java
public class RptItemReceiveRegister_Vld {
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

## Validator `RptSiteStockTransferRegister_Vld`
```java
public class RptSiteStockTransferRegister_Vld {
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
         if (DmShared.xNull(hashMapFilter.get("toSite").getTag()).equalsIgnoreCase("")) {
            throw new DmException(200, "To site is mandatory.");
        }
        DmShared.isValidText(hashMapFilter.get("site").getTag(), "Site", 200);
    }
}
```

## Server `RptSiteStockInTransitRegister_SrvImpl.java` (873 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.processWjMessageQueue`, `DmServer.saveLog`, `DmServer.showJasper`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.xNull`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(boolean isFromSite, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm {…}`

### `getTxtHGateNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGateNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select gio.docId as gateDocId ,gio.v_Date as gateInDate ,gio.v_Time as gateInTime ,vt.short_Name ,gio.v_No ,gio.recId from gateInOut gio left join voucher_type vt on vt.v_Type= gio.v_Type where gio.site_Code in( {…} :sitecode {…} )`

### `getTxtHTransporter`
`public DmResultPageing<List<TxtTransporters_Mdl>> getTxtHTransporter(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code, sg.name, sg.manualCode, c.cityName FROM subGroup sg LEFT JOIN city c ON c.cityCode = sg.cityCode WHERE sg.nature = :nature`
- Params: `:nature` ← `DmServer.getCodeViaManualCode("TP", "manualCode", "subGroupNature", "code", cnTemp)`

### `getTxtHTransportationMode`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTransportationMode(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `TransportMode.values`

### `getTxtHShowServices`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHShowServices(DmHelper helper, boolean blnToEndOfFile)`


### `getGroupName`
`public DmResultPageing<List<KeyFieldExtended>> getGroupName(String excludeGroup, DmHelper helper, boolean blnToEndOfFile)`


### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT {…} as group1Code , {…} as group1Name , {…} as group2Code , {…} as group2Name ,st.docId, st.v_Type , vt.description AS voucherDescriptionName, vt.short_Name AS v_shortName, st.recId AS inTransitNo, st.v_Date , stmTo.name AS siteToName,stmTo.city as sitetoCity ,stmTo.stateName as siteToStateName , cpos.cityName AS citytoPlace ,cfrom.cityName as deliveryCityName ,stmfrom.name as sfName ,stmfrom.city as sfCityName ,stmfrom.stateName as sfStateName , st.remark ,st.vehicleNo, sgt.name AS transporterName, st.lrNo, st.lrDate, st.isEWayBillRequired, st.eWayBillDt, st.eWayBillNo, gtio.recId AS gateNo, pm.name AS policyName, im.name AS itemName , std.quantity as transferQty, std.skuQty, um1.unit AS skuUnitName, im.itsBasedOn itsBasedOn, um2.unit AS saleUnitName, std.rate, std.oh_Amt_Gross, std.landedSkuValue, Case When st.transportMode = {…} then ' {…} ' When st.transportMode = {…} then ' {…} ' When st.transportMode = {…} then ' {…} ' When st.transportMode = {…} then ' {…} ' ELSE '' END AS transportModeName, smpos.name AS posStateName, ISNULL(std.oh_Amt_CGST ,0) as oh_Amt_CGST , ISNULL(std.oh_Amt_IGST ,0) as oh_Amt_IGST , ISNULL(std.oh_Amt_SGST ,0) as oh_Amt_SGST , ISNULL(std.oh_Amt_UGST ,0) as oh_Amt_UGST , ISNULL(std.oh_Amt_Discount ,0) as oh_Amt_Discount , std.oh_Amt_Net , std.oh_Amt_Taxable , st.oh_Amt_Net as headerNetAmount, std.oh_Amt_Taxable as headerTaxableAmount, im.itemDesc , hm.name as hsnName , hm.hsn , ib.propertyDispCsv , umc.unit as cessNaUnitName , CASE WHEN ISNULL(std.cessEvalTypeCode,0)= {…} THEN ' {…} ' WHEN ISNULL(std.cessEvalTypeCode,0)= {…} THEN ' {…} ' WHEN ISNULL(std.cessEvalTypeCode,0)= {…} THEN ' {…} ' end as cessEvalTypeName , ISNULL(std.oh_Amt_CessNa, 0) AS oh_Amt_CessNa , ISNULL(std.oh_At_CessNa, 0) AS oh_At_CessNa , ISNULL(std.oh_Amt_Cess, 0) AS oh_Amt_Cess , ISNULL(std.oh_At_Cess, 0) AS oh_At_Cess , lm.name as locationName , st.isItc , std.packCount , std.packSizeCode , psm.name as packSizeName ,umpsm.unit as packUnitName , stfNo.recId as transferNo , stfNo.v_Date as transferDate FROM stockTransfer st LEFT JOIN voucher_Type vt ON vt.v_Type = st.v_Type LEFT JOIN stockTransferDetail std on std.docId = st.docId LEFT JOIN siteMast stmfrom ON stmfrom.code = st.stSite_Code LEFT JOIN siteMast stmTo ON stmTo.code = st.Site_Code LEFT JOIN city cfrom ON cfrom.cityCode = st.deliveryCity LEFT JOIN stateMast smfrom ON smfrom.code=cfrom.stateCode LEFT JOIN city cpos ON cpos.cityCode = st.placeOfSupply LEFT JOIN stateMast smpos ON smpos.code=cpos.stateCode LEFT JOIN subGroup sgt ON sgt.subCode = st.transporterCode LEFT JOIN gateInOut gtIo ON gtIo.docId = st.gateDocId LEFT JOIN policyMast pm ON pm.code = st.policyCode LEFT JOIN itemBatch ib ON ib.code = std.ibCode LEFT JOIN itemMast im ON im.code = std.itemCode LEFT JOIN unitMast um1 ON um1.code = im.sku LEFT JOIN unitMast um2 ON um2.code = im.saleUnit left join hsnMast as hm on hm.code=std.hsnCode left Join unitMast umc on umc.code = std.cessNaUnitCode left Join locationMast lm on lm.code = st.locationCode left join packSizeMast psm on psm.code=std.packSizeCode left join unitMast umpsm on umpsm.code=psm.packUnitCode LEFT JOIN stockTransfer stfNo ON stfNo.docId = std.stDocId WHERE vt.category IN (' {…} ',' {…} ') {…} ORDER BY {…} {…} , {…} {…} {…} , {…} {…} {…} st.v_Date, vt.short_Name, st.v_No, st.recId, std.v_SNo`
- Other calls: `BY_AIR.getId`, `BY_AIR.getValue`, `BY_RAIL.getId`, `BY_RAIL.getValue`, `BY_ROAD.getId`, `BY_ROAD.getValue`, `BY_SHIP.getId`, `BY_SHIP.getValue`, `PRINT.getValue`, `QUANTITY_BASED.getCode`, `QUANTITY_BASED.getName`, `QUANTITY_VALUE_BASED.getCode`, `QUANTITY_VALUE_BASED.getName`, `RptSiteStockInTransitRegister_Vld.validateInput`, `SYSTEM_ACTIVITY.getName`, `VALUE_BASED.getCode`, `VALUE_BASED.getName`

### `getParameter`
`private void getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`

### `getTxtHRecordType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHRecordType(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHInTransiteNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHInTransiteNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select st.docId as code, st.recId as name ,st.v_Date as transferDate ,stm.name as siteName , st.v_No , vt.short_Name as intransitType , CASE WHEN st.site_Code = {…} THEN 1 ELSE 2 END as tempSr from stockTransfer st LEFT JOIN voucher_Type vt ON vt.v_Type = st.v_Type LEFT JOIN siteMAst stm ON stm.code = st.site_Code WHERE vt.category IN ( ' {…} ',' {…} ') and st.site_Code in( {…} :siteList {…} )`

### `getTxtHVoucherType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVoucherType(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type , vt.description AS name , vt.guiTemplateCode , vt.balancingAc , vt.category FROM voucher_Type vt WHERE vt.category IN (' {…} ',' {…} ') AND vt.isActive = 1`

## Server `RptStockSummary_SrvImpl.java` (1692 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnId`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.processWjMessageQueue`, `DmServer.removeAllCharacterExceptAlphaNumeric`, `DmServer.saveLog`, `DmServer.setReportHeaderExcel`, `DmServer.vNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `execute`
`public DmResult<KeyFieldExtended> execute(String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT vStk2.site_Code , sm.name as site_Name , vStk2.departmentCode , sgd.name as departmentName , vStk2.departmentCode, itemCode {…} , vStk2.packSizeCode {…} , sgd.name as departmentName , im.Name as itemName, purchaseUnitConversionRate , {…} as sku , {…} AS openingQty , ABS(CAST(CASE WHEN ISNULL(vStk2.openingQty,0) = 0 THEN 0 ELSE ISNULL(vStk2.openingValue,0) / vStk2.openingQty END AS decimal(18,7))) AS openingRate , vStk2.openingValue , {…} AS purchaseQty , {…} AS productionQty , {…} AS transferReceiveQty , {…} AS otherReceiveQty , {…} AS totalReceiveQty , vStk2.purchaseValue , vStk2.productionValue , vStk2.transferReceiveValue , vStk2.otherReceiveValue , ABS(CAST(CASE WHEN ISNULL(vStk2.totalReceiveQty,0) = 0 THEN 0 ELSE ISNULL(vStk2.totalReceiveValue,0) / vStk2.totalReceiveQty END AS decimal(18,7))) AS receiveRate , vStk2.totalReceiveValue , {…} AS transferIssueQty , {…} salesQty , {…} AS otherIssueQty, {…} AS totalIssueQty , vStk2.transferIssueValue , vStk2.salesValue , vStk2.otherIssueValue , ABS(CAST(CASE WHEN ISNULL(vStk2.totalIssueQty,0) = 0 THEN 0 ELSE ISNULL(vStk2.totalIssueValue,0) / vStk2.totalIssueQty END AS decimal(18,7))) AS issueRate , vStk2.totalIssueValue , {…} AS balanceQty , {…} AS balanceValue FROM ( SELECT vStk1.itemCode {…} , vStk1.packSizeCode {…} , vStk1.site_Code , vStk1.departmentCode , SUM(vStk1.openingQty) AS openingQty , SUM(vStk1.openingValue) AS openingValue , SUM(vStk1.openingPackCount) AS openingPackCount , SUM(vStk1.purchaseQty) AS purchaseQty , SUM(vStk1.purchasePackCount) AS purchasePackCount , SUM(vStk1.productionQty) AS productionQty , SUM(vStk1.productionPackCount) AS productionPackCount , SUM(vStk1.transferReceiveQty) AS transferReceiveQty , SUM(vStk1.transferReceivePackCount) AS transferReceivePackCount , SUM(vStk1.otherReceiveQty) AS otherReceiveQty , SUM(vStk1.otherReceivePackCount) AS otherReceivePackCount , SUM(vStk1.totalReceiveQty) AS totalReceiveQty , SUM(vStk1.purchaseValue) AS purchaseValue , SUM(vStk1.productionValue) AS productionValue , SUM(vStk1.transferReceiveValue) AS transferReceiveValue , SUM(vStk1.otherReceiveValue) AS otherReceiveValue , SUM(vStk1.totalReceiveValue) AS totalReceiveValue , SUM(vStk1.transferIssueQty) AS transferIssueQty , SUM(vStk1.transferIssuePackCount) AS transferIssuePackCount , SUM(vStk1.salesQty) AS salesQty , SUM(vStk1.salesPackCount) AS salesPackCount , SUM(vStk1.otherIssueQty) AS otherIssueQty , SUM(vStk1.otherIssuePackCount) AS otherIssuePackCount , SUM(vStk1.totalIssueQty) AS totalIssueQty , SUM(vStk1.transferIssueValue) AS transferIssueValue , SUM(vStk1.salesValue) AS salesValue , SUM(vStk1.otherIssueValue) AS otherIssueValue , SUM(vStk1.totalIssueValue) AS totalIssueValue , SUM(vStk1.packIssueCount) AS packIssueCount , SUM(vStk1.packRecCount) AS packRecCount FROM ( SELECT 0 AS docid, 0 AS v_SNo, null as v_Type, null recId, 0 as v_No , DATEADD(DAY, -1, :fromdate) AS v_Date , vStk.itemCode {…} , vStk.packSizeCode {…} , vStk.site_Code , vStk.departmentCode , null AS stockContraSubCode , vStk.openingQty , vStk.openingValue , vStk.openingPackCount , 0 AS purchaseQty , 0 AS purchasePackCount , 0 AS productionQty , 0 AS productionPackCount , 0 AS reprocessQty , 0 AS reprocessPackCount , 0 AS otherReceiveQty , 0 AS otherReceivePackCount , 0 AS transferReceiveQty , 0 AS transferReceivePackCount , 0 as totalReceiveQty , 0 AS purchaseValue , 0 AS productionValue , 0 AS transferReceiveValue , 0 AS otherReceiveValue , 0 AS totalReceiveValue , 0 AS transferIssueQty , 0 AS transferIssuePackCount , 0 AS salesQty , 0 AS salesPackCount , 0 AS otherIssueQty , 0 AS otherIssuePackCount , 0 AS totalIssueQty , 0 AS transferIssueValue , 0 AS salesValue , 0 AS otherIssueValue , 0 AS totalIssueValue , 0 AS packIssueCount , 0 AS packRecCount FROM ( SELECT stk.itemCode {…} , stk.packSizeCode {…} , stk.departmentCode , stk.site_Code , SUM(ISNULL(stk.recQty,0) - ISNULL(stk.isuQty,0)) AS openingQty , SUM(ISNULL(stk.packRecqty,0) - ISNULL(stk.packIsuqty,0)) AS opening`
- Params: `:filterStoreItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`; `:filterFinishedItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`
- Other calls: `PRINT.getValue`, `RptStockSummary_Vld.validateInput`, `STServer.getSsnDepartmentCodeList`, `SYSTEM_ACTIVITY.getName`

### `createExcelReport`
`public KeyFieldExtended createExcelReport(String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, ResultSet rsTemp) throws Exception`

- Errors: "Invalid request." | "File Name not given." | "No record found!"
- Other calls: `CellReference.convertNumToColString`, `ExcelReport_Server.createAndMergeCells`, `ExcelReport_Server.getCellStyle`, `ExcelReport_Server.getCellStyleHeader`, `ExcelReport_Server.getDataFormat`, `ExcelReport_Server.newCell`, `ExcelReport_Server.printFilterData`, `RptStockSummary_Vld.validateInput`

### `getParameter`
`private void getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select dm.subCode as code, sg.name from departmentMast as dm left join subGroup as sg on dm.subCode = sg.subCode left join subGroupNature sgn on sgn.code = sg.nature where 1=1 {…}`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getTxtHItem`
`public DmResultPageing<List<TxtHItem_Mdl>> getTxtHItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , um.code AS unitCode , um.unit,im.manualCode FROM itemMast im LEFT JOIN unitmast um ON im.sku = um.code WHERE (im.filterItemType IN ( :filterStoreItemType, :filterFinishedItemType ) or im.isCommon=1)`
- Params: `:filterStoreItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`; `:filterFinishedItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemCat`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemCat(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ic.code, ic.name FROM itemCat ic WHERE (ic.filterItemType = :filterItemType or isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ig.code, ig.name FROM itemGroup ig WHERE (ig.filterItemType = :filterItemType or ig.isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHValueOn`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHValueOn(DmHelper helper, boolean blnToEndOfFile)`


### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `getTxtHEvaluationStock`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHEvaluationStock(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHReportFor`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHReportFor(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHBf`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHBf(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT bf.code, bf.code as name FROM bfMast bf where bf.isActive = 1`

### `getTxtHGsm`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGsm(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT gm.code, gm.code as name FROM gsmMast gm`

### `getTxtHItemNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemNature(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT inm.code, inm.name FROM itemNature inm`

## Server `RptReelSheetIssueRegister_SrvImpl.java` (1333 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.processWjMessageQueue`, `DmServer.saveLog`, `DmServer.showJasper`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.xNull`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- Other calls: `PRINT.getValue`, `ReportFor.trim`, `RptReelSheetIssueRegister_Vld.validateInput`, `SYSTEM_ACTIVITY.getName`

### `getParameter`
`private void getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select dm.subCode as code, sg.name from departmentMast as dm left join subGroup as sg on dm.subCode = sg.subCode left join subGroupNature sgn on sgn.code = sg.nature where 1=1 {…}`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getTxtHVoucherNo`
`public DmResultPageing<List<TxtHConsumptionNo_Mdl>> getTxtHVoucherNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select sh.docId AS code,sh.recId AS name , sh.v_Date , sh.v_No , vt.short_Name as consumptionType , CASE WHEN sh.site_Code = {…} THEN 1 ELSE 2 END as tempSr from stockHead sh LEFT JOIN voucher_Type AS vt ON vt.v_Type=sh.v_Type LEFT JOIN siteMast sm ON sm.code = sh.site_Code where vt.category in ( {…} ) AND sh.site_Code in( {…} :siteList {…} )`

### `getTxtHIssueTo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHIssueTo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name AS name FROM subgroup sg LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( SELECT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_type = acf.v_Type WHERE vt.category IN ( {…} ) )`

### `getTxtHVoucherType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVoucherType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select v_Type as code, description as name from voucher_Type where category in ( {…} )`

### `getTxtHItemName`
`public DmResultPageing<List<TxtHItemName_Mdl>> getTxtHItemName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code, im.name, im.sku, um.unit,im.manualCode FROM itemMast im LEFT JOIN unitmast um ON im.sku = um.code WHERE (im.filterItemType = :filterItemType or isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemCat`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemCat(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select CODE, NAME from itemcat where (filterItemType = :filterItemType or isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT CODE, NAME, filteritemType FROM itemgroup where (filterItemType = :filterItemType or isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `getGroupName`
`public DmResultPageing<List<KeyFieldExtended>> getGroupName(String excludeGroup, DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHBf`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHBf(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT bf.code, bf.code as name FROM bfMast bf where bf.isActive = 1`

### `getTxtHGsm`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGsm(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT gm.code, gm.code as name FROM gsmMast gm`

### `getTxtHWidth`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHWidth(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sizeWidth AS Code ,sizeWidth AS Name FROM itemBatch GROUP BY sizeWidth`

### `getTxtHLength`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHLength(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sizeLength AS Code ,sizeLength AS Name FROM itemBatch GROUP BY sizeLength`

### `getTxtHSizeUnit`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSizeUnit(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT um.code, um.unit AS name FROM unitMast um WHERE um.nature = :nature AND um.unit IN (:inch, :cm, :mm)`
- Params: `:nature` ← `STEnum.UnitNature.LENGTH.getId()`; `:inch` ← `STEnum.UnitMaster.INCH.getName()`; `:cm` ← `STEnum.UnitMaster.CM.getName()`; `:mm` ← `STEnum.UnitMaster.MM.getName()`
- Other calls: `CM.getName`, `INCH.getName`, `LENGTH.getId`, `MM.getName`

### `getTxtHItemNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemNature(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT itn.code, itn.name, itn.manualCode FROM itemNature AS itn WHERE itn.isActive = 1 AND itn.manualCode IN (:R, :S)`
- Params: `:R` ← `DmShared.xNull(STEnum.ItemNature.REEL.getManualCode())`; `:S` ← `DmShared.xNull(STEnum.ItemNature.SHEET.getManualCode())`
- Other calls: `REEL.getManualCode`, `SHEET.getManualCode`

### `getTxtHReportFor`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHReportFor(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHStockType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHStockType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `With Order`
- SQL 2: `Without Order`

## Server `RptItemIssueRegister_SrvImpl.java` (1057 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.processWjMessageQueue`, `DmServer.saveLog`, `DmServer.showJasper`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.xNull`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `Select {…} as group1Code , {…} as group1Name , {…} as group2Code , {…} as group2Name , sh.docId, sh.recId, sh.v_Date, sh.v_Type, vt.short_Name, sgd.name as departmentName,sgrb.name receivedByName,im.name as itemName, um.unit, sh.refNo , sh.refDate, ms.quantity as isuQty , ms.remark,rq.recId as requisitionNo, ib.batchNo, ib.lotNo,ib.manufacturingDate,ib.expiryDate,imm.companyName as itemMakeName, ib.colourCode,ib.sizeCode,ib.mrp,icm.name as colourName,ism.name as sizeName,sh.site_Code,sm.name as siteName , ib.propertyDispCsv , psm.name as packSizeName , ms.packCount , umps.unit as packUnit from stockHead as sh left join stockHeadDetail as ms on sh.docId = ms.docId left join voucher_Type as vt on sh.v_Type = vt.v_Type left join subGroup as sgd on ms.departmentCode = sgd.subCode left join subGroup as sgrb on ms.subCode = sgrb.subCode left join subGroupNature as sgn on sgn.code=sgrb.nature left join requisition as rq on ms.requisitionDocId = rq.docId left join itemMast as im on ms.itemCode = im.code left join unitMast as um on im.sku = um.code left join siteMast sm ON sm.code=sh.site_Code LEFT JOIN itemBatch ib on ib.code = ms.ibCode left join itemColourMast as icm on icm.code = ib.colourCode left join itemSizeMast as ism on ism.code = ib.sizeCode Left join itemMakeMast as imm on imm.code= ib.itemMakeCode left join packSizeMast as psm on psm.code = ms.packSizeCode left join unitMast as umps on psm.packUnitCode = umps.code where vt.category in ( {…} ) {…} ORDER BY {…} {…} , {…} {…} {…} , {…} {…} {…} sh.v_Date, vt.short_Name, sh.v_No, sh.recId, ms.v_SNo`
- Other calls: `PRINT.getValue`, `RptItemIssueRegister_Vld.validateInput`, `SYSTEM_ACTIVITY.getName`

### `getParameter`
`private void getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select dm.subCode as code, sg.name from departmentMast as dm left join subGroup as sg on dm.subCode = sg.subCode left join subGroupNature sgn on sgn.code = sg.nature where 1=1 {…}`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getTxtHVoucherNo`
`public DmResultPageing<List<TxtHConsumptionNo_Mdl>> getTxtHVoucherNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select sh.docId AS code,sh.recId AS name , sh.v_Date , sh.v_No , vt.short_Name as consumptionType , CASE WHEN sh.site_Code = {…} THEN 1 ELSE 2 END as tempSr from stockHead sh LEFT JOIN voucher_Type AS vt ON vt.v_Type=sh.v_Type LEFT JOIN siteMast sm ON sm.code = sh.site_Code where vt.category in ( {…} ) AND sh.site_Code in( {…} :siteList {…} )`

### `getTxtHIssueTo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHIssueTo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name AS name FROM subgroup sg LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( SELECT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_type = acf.v_Type WHERE vt.category IN ( {…} ) )`

### `getTxtHVoucherType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVoucherType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select v_Type as code, description as name from voucher_Type where category in ( {…} )`

### `getTxtHItemName`
`public DmResultPageing<List<TxtHItemName_Mdl>> getTxtHItemName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code, im.name, im.sku, um.unit,im.manualCode FROM itemMast im LEFT JOIN unitmast um ON im.sku = um.code WHERE (im.filterItemType = :filterItemType or isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemCat`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemCat(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select CODE, NAME from itemcat where (filterItemType = :filterItemType or isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT CODE, NAME, filteritemType FROM itemgroup where (filterItemType = :filterItemType or isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHRequisitionNo`
`public DmResultPageing<List<TxtHRequisitionNo_Mdl>> getTxtHRequisitionNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select rq.docId, rq.recId as requisitionNo, rq.v_Date as requisitionDate , rq.v_No , sg.name as requestedBy , vt.short_Name as requisitionVType , CASE WHEN rq.site_Code = {…} THEN 1 ELSE 2 END as tempSr from requisition as rq LEFT JOIN voucher_Type vt ON rq.v_Type=vt.v_Type left join subGroup sg on sg.subCode = rq.requestedByCode LEFT JOIN siteMast sm ON sm.code = rq.site_Code where rq.site_Code in( {…} :siteList {…} ) and vt.category IN ( {…} ) and isNull(rq.approvedBy,'') <> ''`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `getGroupName`
`public DmResultPageing<List<KeyFieldExtended>> getGroupName(String excludeGroup, DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHBf`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHBf(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT bf.code, bf.code as name FROM bfMast bf where bf.isActive = 1`

### `getTxtHGsm`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGsm(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT gm.code, gm.code as name FROM gsmMast gm`

### `getTxtHItemNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemNature(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT inm.code, inm.name FROM itemNature inm`

## Server `RptStockLedger_SrvImpl.java` (938 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.processWjMessageQueue`, `DmServer.saveLog`, `DmServer.showJasper`, `DmShared.filterKeyFieldList`, `DmShared.xNull`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT vStk1.docid, vStk1.v_SNo, vStk1.v_type, vStk1.vTypeShortName, vStk1.recId, vStk1.v_No, vStk1.v_Date , sm.name as site_Name, vStk1.site_Code ,vStk1.departmentCode , vStk1.itemCode , vStk1.packSizeCode , sgd.name as departmentName , CASE WHEN vStk1.v_Type IS NULL THEN 'Opening Balance' ELSE sgc.name END as particulars , im.Name as itemName , um.unit as sku , vStk1.openingQty , ABS(CAST(CASE WHEN ISNULL(vStk1.openingQty,0) = 0 THEN 0 ELSE ISNULL(vStk1.openingValue,0) / vStk1.openingQty END AS decimal(18,7))) AS openingRate , vStk1.openingValue ,vStk1.openingPackCountQty , vStk1.receiveQty , ABS(CAST(CASE WHEN ISNULL(vStk1.receiveQty,0) = 0 THEN 0 ELSE ISNULL(vStk1.receiveValue,0) / vStk1.receiveQty END AS decimal(18,7))) AS receiveRate , vStk1.receiveValue , vStk1.issueQty , ABS(CAST(CASE WHEN ISNULL(vStk1.issueQty,0) = 0 THEN 0 ELSE ISNULL(vStk1.issueValue,0) / vStk1.issueQty END AS decimal(18,7))) AS issueRate , vStk1.issueValue {…} as packSizeName , vStk1.packIssueCount , vStk1.packRecCount , SUM(ISNULL(vStk1.openingQty,0) + ISNULL(vStk1.receiveQty,0) - ISNULL(vStk1.issueQty,0)) OVER (PARTITION BY sm.name, sgd.name, im.Name {…} ORDER BY sm.name, sgd.name, im.Name {…} , vStk1.v_Date, vStk1.serialNo, vStk1.vTypeShortName, vStk1.v_No, vStk1.recId, vStk1.v_SNo) AS balanceQty , SUM(ISNULL(vStk1.openingValue,0) + ISNULL(vStk1.receiveValue,0) - ISNULL(vStk1.issueValue,0)) OVER (PARTITION BY sm.name, sgd.name, im.Name {…} ORDER BY sm.name, sgd.name, im.Name {…} , vStk1.v_Date, vStk1.serialNo, vStk1.vTypeShortName, vStk1.v_No, vStk1.recId, vStk1.v_SNo) AS balanceValue , SUM(ISNULL(vStk1.openingPackCountQty,0) + ISNULL(vStk1.packRecCount,0) - ISNULL(vStk1.packIssueCount,0)) OVER (PARTITION BY sm.name, sgd.name, im.Name {…} ORDER BY sm.name, sgd.name {…} , vStk1.v_Date, vStk1.serialNo, vStk1.vTypeShortName, vStk1.v_No, vStk1.recId, vStk1.v_SNo) AS balancePack FROM ( SELECT 0 AS docid, 0 AS v_SNo, null as v_Type,'Opening' as vTypeShortName, null recId, 0 as v_No , null AS v_Date , vStk.itemCode , vStk.packSizeCode , vStk.site_Code , vStk.departmentCode , null AS partyCode , vStk.openingQty , vstk.openingPackCountQty , vStk.openingValue , 0 as packRecCount , 0 as packIssueCount , 0 as receiveQty , 0 AS receiveValue , 0 as issueQty , 0 AS issueValue , 0 AS serialNo FROM ( SELECT stk.itemCode {…} AS packSizeCode , stk.departmentCode , stk.site_Code , SUM(ISNULL(stk.recQty,0) - ISNULL(stk.isuQty,0)) AS openingQty , SUM(ISNULL(stk.packRecqty,0) - ISNULL(stk.packIsuqty,0)) AS openingPackCountQty {…} FROM stock stk INNER JOIN voucher_Type vt ON vt.v_Type = stk.v_Type LEFT JOIN itemBatch ib ON ib.code = stk.ibCode WHERE stk.v_Date < :fromDate {…} GROUP BY stk.site_Code, stk.departmentCode, stk.itemCode {…} ) AS vStk UNION ALL SELECT stk.docid, stk.v_SNo, stk.v_Type,vt.short_Name as vTypeShortName , stk.recId, stk.v_No, stk.v_Date , stk.itemCode {…} AS packSizeCode , stk.site_Code , stk.departmentCode , stk.partyCode , 0 as openingQty , 0 AS openingValue , 0 AS openingPackCountQty , stk.packRecQty as packRecCount , 0 as packIssueCount , CASE WHEN vt.parentVoucher IN ('Purchase', 'Purchase Return') THEN ISNULL(stk.recQty,0) ELSE CASE WHEN ISNULL(stk.isuQty,0) < 0 THEN ABS(ISNULL(stk.isuQty,0)) ELSE ISNULL(stk.recQty,0) END END AS receiveQty {…} , 0 as issueQty , 0 AS issueValue , vt.serialNo FROM stock stk INNER JOIN voucher_Type vt ON vt.v_Type = stk.v_Type LEFT JOIN itemBatch ib ON ib.code = stk.ibCode WHERE stk.v_Date >= :fromDate AND stk.v_Date <= :toDate AND CASE WHEN vt.parentVoucher IN ('Purchase', 'Purchase Return') THEN 1 WHEN vt.parentVoucher IN ('Sale', 'Sale Return') THEN 0 ELSE CASE WHEN (ISNULL(stk.isuQty,0) < 0 OR ISNULL(stk.recQty,0) > 0 OR (ISNULL(stk.isuQty,0) + ISNULL(stk.recQty,0)) = 0 ) THEN 1 ELSE 0 END END = 1 {…} UNION ALL SELECT stk.docid, stk.v_SNo, stk.v_Type,vt.short_Name as vTypeShortName, stk.recId , stk.v_No, stk.v_Date , stk.itemCode {…} AS packSizeCode , stk.site_Code , stk.departmentCode , stk.partyCode , 0 `
- Params: `:filterStoreItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`; `:filterFinishedItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`
- Other calls: `PRINT.getValue`, `RptStockLedger_Vld.validateInput`, `STServer.getSsnDepartmentCodeList`, `SYSTEM_ACTIVITY.getName`

### `getParameter`
`private void getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select dm.subCode as code, sg.name from departmentMast as dm left join subGroup as sg on dm.subCode = sg.subCode left join subGroupNature sgn on sgn.code = sg.nature where 1=1 {…}`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getTxtHItem`
`public DmResultPageing<List<TxtHItem_Mdl>> getTxtHItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , um.code AS unitCode , um.unit,im.manualCode FROM itemMast im LEFT JOIN unitmast um ON im.sku = um.code WHERE (im.filterItemType IN ( :filterStoreItemType, :filterFinishedItemType ) or im.isCommon=1)`
- Params: `:filterStoreItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`; `:filterFinishedItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemCat`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemCat(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ic.code, ic.name FROM itemCat ic WHERE (ic.filterItemType = :filterItemType or ic.isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ig.code, ig.name FROM itemGroup ig WHERE (ig.filterItemType = :filterItemType or ig.isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHValueOn`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHValueOn(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHEvaluationStock`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHEvaluationStock(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHBf`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHBf(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT bf.code, bf.code as name FROM bfMast bf where bf.isActive = 1`

### `getTxtHGsm`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGsm(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT gm.code, gm.code as name FROM gsmMast gm`

### `getTxtHItemNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemNature(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT inm.code, inm.name FROM itemNature inm`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `getTxtHPackSize`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHPackSize(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT pm.code , pm.name FROM packSizeMast pm where isActive= '1'`

## Server `RptReelSheetReceiveRegister_SrvImpl.java` (1357 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.processWjMessageQueue`, `DmServer.saveLog`, `DmServer.showJasper`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.xNull`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- Other calls: `PRINT.getValue`, `ReportFor.trim`, `RptReelSheetIssueRegister_Vld.validateInput`, `SYSTEM_ACTIVITY.getName`

### `getParameter`
`private void getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select dm.subCode as code, sg.name from departmentMast as dm left join subGroup as sg on dm.subCode = sg.subCode left join subGroupNature sgn on sgn.code = sg.nature where 1=1 {…}`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getTxtHVoucherNo`
`public DmResultPageing<List<TxtHConsumptionNo_Mdl>> getTxtHVoucherNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select sh.docId AS code,sh.recId AS name , sh.v_Date , sh.v_No , vt.short_Name as consumptionType , CASE WHEN sh.site_Code = {…} THEN 1 ELSE 2 END as tempSr from stockHead sh LEFT JOIN voucher_Type AS vt ON vt.v_Type=sh.v_Type LEFT JOIN siteMast sm ON sm.code = sh.site_Code where vt.category in ( {…} ) AND sh.site_Code in( {…} :siteList {…} )`

### `getTxtHReceiveTo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHReceiveTo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name AS name FROM subgroup sg LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( SELECT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_type = acf.v_Type WHERE vt.category IN ( {…} ) )`

### `getTxtHVoucherType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVoucherType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select v_Type as code, description as name from voucher_Type where category in ( {…} )`

### `getTxtHItemName`
`public DmResultPageing<List<TxtHItemName_Mdl>> getTxtHItemName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code, im.name, im.sku, um.unit,im.manualCode FROM itemMast im LEFT JOIN unitmast um ON im.sku = um.code WHERE (im.filterItemType = :filterItemType or isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemCat`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemCat(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select CODE, NAME from itemcat where (filterItemType = :filterItemType or isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT CODE, NAME, filteritemType FROM itemgroup where (filterItemType = :filterItemType or isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `getGroupName`
`public DmResultPageing<List<KeyFieldExtended>> getGroupName(String excludeGroup, DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHBf`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHBf(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT bf.code, bf.code as name FROM bfMast bf where bf.isActive = 1`

### `getTxtHGsm`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGsm(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT gm.code, gm.code as name FROM gsmMast gm`

### `getTxtHWidth`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHWidth(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sizeWidth AS Code ,sizeWidth AS Name FROM itemBatch GROUP BY sizeWidth`

### `getTxtHLength`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHLength(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sizeLength AS Code ,sizeLength AS Name FROM itemBatch GROUP BY sizeLength`

### `getTxtHSizeUnit`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSizeUnit(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT um.code, um.unit AS name FROM unitMast um WHERE um.nature = :nature AND um.unit IN (:inch, :cm, :mm)`
- Params: `:nature` ← `STEnum.UnitNature.LENGTH.getId()`; `:inch` ← `STEnum.UnitMaster.INCH.getName()`; `:cm` ← `STEnum.UnitMaster.CM.getName()`; `:mm` ← `STEnum.UnitMaster.MM.getName()`
- Other calls: `CM.getName`, `INCH.getName`, `LENGTH.getId`, `MM.getName`

### `getTxtHItemNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemNature(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT itn.code, itn.name, itn.manualCode FROM itemNature AS itn WHERE itn.isActive = 1 AND itn.manualCode IN (:R, :S)`
- Params: `:R` ← `DmShared.xNull(STEnum.ItemNature.REEL.getManualCode())`; `:S` ← `DmShared.xNull(STEnum.ItemNature.SHEET.getManualCode())`
- Other calls: `REEL.getManualCode`, `SHEET.getManualCode`

### `getTxtHReportFor`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHReportFor(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHStockType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHStockType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `With Order`
- SQL 2: `Without Order`

### `TxtHAgainst`
`public DmResultPageing<List<KeyFieldExtended>> TxtHAgainst(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `DmStockReceiveAgainst.values`

## Server `RptTradeItemStockAsOnDateRegister_SrvImpl.java` (1666 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnId`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.processWjMessageQueue`, `DmServer.removeAllCharacterExceptAlphaNumeric`, `DmServer.setReportHeaderExcel`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `execute`
`public DmResult<KeyFieldExtended> execute(String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT {…} AS group1Name, {…} AS group2Name , MAX(sgd.name) AS warehouse , MAX(stk.itemCode) AS itemCode , MAX(im.name) AS itemName , MAX(im.manualCode) AS manualCode , MAX(ina.name) AS itemNature , MAX(ib.bfCode) AS bfCode , MAX(ib.gsmCode) AS gsmCode , MAX(CASE ISNULL(sUnt.unit,'') WHEN 'MM' THEN ib.mmWidth WHEN 'CM' THEN ib.cmWidth WHEN 'Inch' THEN ib.inchWidth END) AS width , MAX(CASE ISNULL(sUnt.unit,'') WHEN 'MM' THEN ib.mmLength WHEN 'CM' THEN ib.cmLength WHEN 'Inch' THEN ib.inchLength END) AS length , MAX(CASE WHEN ISNULL(ib.grain,0) = 1 THEN 'Long' WHEN ISNULL(ib.grain,0) = 2 THEN 'Short' END) AS grainName , MAX(ib.sheetsPerReam) AS sheetsPerReam , MAX(ib.reamsPerBundle) AS reamsPerBundle , MAX(ib.reamWeight) AS reamWeight , MAX(sUnt.unit) AS sizeUnit , (ISNULL(SUM(stk.packRecQty), 0)-ISNULL(SUM(stk.packIsuQty), 0)) reelBundle , (ISNULL(SUM(stk.recQty), 0)-ISNULL(SUM(stk.isuQty), 0)) totalQuantity {…} FROM stock stk LEFT JOIN voucher_Type vt ON vt.v_Type = stk.v_Type LEFT JOIN itemMast im ON im.code = stk.itemCode LEFT JOIN itemBatch ib ON ib.code = stk.ibCode LEFT JOIN itemNature ina ON ina.code = ib.itemNature LEFT JOIN itemCat ic ON ic.code = im.itemCategoryCode LEFT JOIN itemGroup ig ON ig.code = im.itemGroup LEFT JOIN unitMast um ON um.code = im.sku LEFT JOIN packSizeMast psm ON psm.code = stk.packSizeCode LEFT JOIN unitMast ump ON ump.code = psm.packUnitCode LEFT JOIN siteMast sm ON sm.code = stk.site_Code LEFT JOIN subGroup sgd ON sgd.subcode = stk.departmentCode LEFT JOIN unitMast sUnt ON sUnt.code = :unitCode WHERE :one = :one {…} GROUP BY stk.itemCode, stk.ibCode {…} ORDER BY warehouse, itemName {…}`
- Errors: "Invalid request." | "File Name not given."
- Other calls: `RptTradeItemStockAsOnDateRegister_Vld.validateInput`, `STServer.getSsnDepartmentCodeList`

### `createExcelReport`
`private KeyFieldExtended createExcelReport(String widgetName, String sessionId, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, ResultSet rsTemp, int totalColumns) throws Exception`

- Errors: "No record found!"
- Other calls: `ExcelReport_Server.createAndMergeBlankRows`, `ExcelReport_Server.createAndMergeCells`, `ExcelReport_Server.getCellStyle`, `ExcelReport_Server.getCellStyleHeader`, `ExcelReport_Server.getDataFormat`, `ExcelReport_Server.newCell`, `ExcelReport_Server.printFilterData`

### `getParameter`
`private void getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:one` ← `"1"`; `:asOnDate` ← `DmServer.convertDate(filter.get("asOnDate").getText()), true`; `:unitCode` ← `DmShared.xNull(filter.get("unitCode").getTag())`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getTxtHWarehouse`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHWarehouse(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select dm.subCode as code, sg.name from departmentMast as dm left join subGroup as sg on dm.subCode = sg.subCode left join subGroupNature sgn on sgn.code = sg.nature where 1=1 {…}`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getTxtHItem`
`public DmResultPageing<List<TxtHItem_Mdl>> getTxtHItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , um.code AS unitCode , um.unit,im.manualCode FROM itemMast im LEFT JOIN unitmast um ON im.sku = um.code WHERE (im.filterItemType IN ( :filterStoreItemType, :filterFinishedItemType ) or im.isCommon=1)`
- Params: `:filterStoreItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`; `:filterFinishedItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemCat`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemCat(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ic.code, ic.name FROM itemCat ic WHERE (ic.filterItemType = :filterItemType or isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ig.code, ig.name FROM itemGroup ig WHERE (ig.filterItemType = :filterItemType or ig.isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHValueOn`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHValueOn(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHReportFor`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHReportFor(DmHelper pageing, boolean blnToEndOfFile)`


### `getTxtHBf`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHBf(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT bf.code, bf.code as name FROM bfMast bf where bf.isActive = 1`

### `getTxtHGsm`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGsm(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT gm.code, gm.code as name FROM gsmMast gm`

### `getTxtHItemNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemNature(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT inm.code, inm.name FROM itemNature inm`

### `getTxtHUnit`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHUnit(String nature, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select um.code, um.unit from unitMast um where um.nature = :nature`
- Params: `:nature` ← `DmShared.iNull(nature).toString()`

### `getGroupBy`
`public DmResultPageing<List<KeyFieldExtended>> getGroupBy(String tag2, DmHelper helper, boolean blnToEndOfFile)`


### `getGroupOn`
`private String[] getGroupOn(String groupOn)`


### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- SQL 2: `SELECT ec.prod_SizeUnitCode, um.unit FROM enviroCompany ec LEFT JOIN unitMast um ON um.code = ec.prod_SizeUnitCode`
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

## Server `RptStockDayBookSummary_SrvImpl.java` (519 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.saveLog`, `DmServer.showJasper`, `DmShared.filterKeyFieldList`, `DmShared.xNull`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `Select V_Date,isNull(Sum(ReceiveQty),0) As ReceiveQty,isNull(Sum(IssueQty),0) As IssueQty,`
- SQL 2: `Select LG.V_Date,`
- SQL 3: `Select :fromDate As V_Date,`
- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`
- Other calls: `PRINT.getValue`, `RptStockDayBookSummary_Vld.validateInput`, `SYSTEM_ACTIVITY.getName`

### `getParameter`
`private void getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`

### `getTxtHItem`
`public DmResultPageing<List<TxtHItem_Mdl>> getTxtHItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , um.code AS unitCode , um.unit,im.manualCode FROM itemMast im LEFT JOIN unitmast um ON im.sku = um.code WHERE (im.filterItemType IN ( :filterStoreItemType, :filterFinishedItemType ) or im.isCommon=1)`
- Params: `:filterStoreItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`; `:filterFinishedItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `getTxtHEvaluationStock`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHEvaluationStock(DmHelper helper, boolean blnToEndOfFile)`


## Server `RptSheetStockSummary_SrvImpl.java` (2116 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.removeAllCharacterExceptAlphaNumeric`, `DmServer.saveLog`, `DmServer.setReportHeaderExcel`, `DmServer.showJasper`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT im.Name AS itemName, SO.recId AS OrdNo,SO.v_Date AS OrdDate, SO.partyOrdNo AS partyOrdNo,SO.partyOrdDt AS partyOrdDt,ptiC.name AS Consignee,ib.gsmCode, ib.bfCode, ib.sizewidth AS size,ib.sizeLength AS Length, SUM(iis.balanceQty) AS weight , SUM(iis.balancePcs) AS Bundles , ptip.name AS PartyName,um.unit AS sizeUnit, SUM(ib.reamWeight) AS ReamWeight, SUM(ib.reamsPerBundle) AS Reams, SUM(ib.sheetsPerReam) AS sheetsPerReam, ib.grain,ib.propertyDispCsv, CASE WHEN ib.grain = 1 THEN 'Long' WHEN ib.grain = 2 THEN 'Short' END AS GrainValue, IIS.departmentCode, sgDep.name AS departmentName FROM itemIdStock IIS LEFT JOIN sOrderDetail sod ON sod.docId = IIS.soDocId AND sod.v_SNo = IIS.soV_SNo LEFT JOIN sOrder SO ON SO.docId=IIS.soDocId LEFT JOIN barCodeMast BCM ON IIS.itemId=BCM.barCodeId LEFT JOIN itemBatch ib ON ib.code = IIS.ibCode LEFT JOIN itemNature imn ON imn.code = ib.itemNature LEFT JOIN itemMast im ON im.code = ib.itemCode LEFT JOIN unitMast Um ON UM.code = ib.sizeUnitCode LEFT JOIN subGroup sgDep ON sgDep.subCode = IIS.departmentCode LEFT JOIN partyTransInfo ptip ON ptip.docId = so.docId AND ptip.recordType = :partyRecordType LEFT JOIN partyTransInfo ptic ON ptic.docId = so.docId AND ptic.recordType = :consigneeRecordType WHERE 1=1 AND ISNULL(IIS.balanceQty,0) > 0 AND imn.manualCode = :SHEET {…} GROUP BY im.Name,SO.Recid,SO.v_Date,SO.partyOrdDt,SO.partyOrdNo, ptiC.name, ib.gsmCode, ib.bfCode,ib.sizeLength, ib.sizewidth,ptip.name, um.unit, ib.grain, IIS.departmentCode, sgDep.name, ib.propertyDispCsv ORDER BY so.Recid, so.partyOrdNo, im.Name`
- Params: `:SHEET` ← `STEnum.ItemNature.SHEET.getManualCode()`; `:PartyRecordType` ← `DmEnum.PartyTransRecordType.PARTY.getCode()`; `:consigneeRecordType` ← `DmEnum.PartyTransRecordType.CONSIGNEE.getCode()`
- Other calls: `CONSIGNEE.getCode`, `PARTY.getCode`, `PRINT.getValue`, `RptSheetStockSummary_Vld.validateInput`, `SHEET.getManualCode`, `STServer.getSsnDepartmentCodeList`, `SYSTEM_ACTIVITY.getName`

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

- Params: `:AsDate` ← `DmServer.convertDate(filter.get("AsDate").getText()), true`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `getTxtHHavingWeight`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHHavingWeight(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHSaleOrderNo`
`public DmResultPageing<List<TxtHVoucherNo_Mdl>> getTxtHSaleOrderNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT so.docId, so.site_Code,so.recId, so.v_Date , vt.short_Name as recieveType , so.v_No, sg.name as partyName, sd.name as distributorName , sc.name as consigneeName , CASE WHEN so.site_Code = {…} THEN 1 ELSE 2 END as tempSr,so.partyOrdNo,so.partyOrdDt FROM sOrder AS so left join voucher_Type as vt on vt.v_Type = so.v_Type LEFT JOIN subGroup sg On sg.subCode = so.partyCode LEFT JOIN subGroup sd On sd.subCode = so.distributor LEFT JOIN subGroup sc On sc.subCode = so.consignee LEFT JOIN siteMast sm ON sm.code = so.site_Code where vt.Category IN (' {…} ') AND ISNULL(so.approvedBy, '') <> '' and so.site_Code in( {…} :siteList {…} )`

### `getTxtHParty`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHParty(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as supplierName , sg.manualCode , c.cityName FROM subGroup sg LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( SELECT DISTINCT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_Type = acf.v_Type WHERE vt.category IN (' {…} ') )`

### `getTxtHDistributor`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHDistributor(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as supplierName , sg.manualCode , c.cityName , sg.priceListCode , sg.partyCat FROM subGroup sg LEFT JOIN city c ON c.cityCode = sg.cityCode WHERE sg.partyCat= :partyCat`
- Params: `:partyCat` ← `DmEnum.PartyCategory.DISTRIBUTOR.getId()`
- Other calls: `DISTRIBUTOR.getId`

### `getTxtHDeliveryCity`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDeliveryCity(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select ct.cityCode, ct.cityName, ct.stateCode, sm.name as stateName from city ct left join stateMast sm on ct.stateCode = sm.code`

### `getxtHProformaInvoiceNo`
`public DmResultPageing<List<KeyFieldExtended>> getxtHProformaInvoiceNo(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `select pi.docId, pi.site_Code,pi.recId As ProformaInvoiceNo,pi.v_No, pi.v_Date from proformaInvoice pi LEFT JOIN voucher_Type vt ON vt.v_Type = pi.v_Type where pi.approvedBy <> '' and vt.category = ' {…} '`

### `getTxtHItemCat`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemCat(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ic.code, ic.name FROM itemCat ic WHERE ic.filterItemType IN (:FM, :SF)`
- Params: `:FM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:SF` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ig.code, ig.name FROM itemGroup ig LEFT JOIN itemNature imn ON imn.code = ig.itemNature WHERE imn.manualCode IN (:SHEET, :COPIER)`
- Params: `:SHEET` ← `STEnum.ItemNature.SHEET.getManualCode()`; `:COPIER` ← `STEnum.ItemNature.COPIER.getManualCode()`
- Other calls: `COPIER.getManualCode`, `SHEET.getManualCode`

### `getItemName`
`public DmResultPageing<List<TxtHItem_Mdl>> getItemName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select im.code as itemCode, im.name as itemName , im.manualCode, it.name as itemTypeName from itemMast im Left join itemType it on it.code=im.itemType LEFT JOIN itemGroup ig ON ig.code = im.itemGroup LEFT JOIN itemNature imn ON imn.code = ig.itemNature WHERE imn.manualCode IN (:SHEET, :COPIER)`
- Params: `:SHEET` ← `STEnum.ItemNature.SHEET.getManualCode()`; `:COPIER` ← `STEnum.ItemNature.COPIER.getManualCode()`
- Other calls: `COPIER.getManualCode`, `SHEET.getManualCode`

### `getTxtHYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHBF`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHBF(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT code ,code As name FROM bfMast`

### `getTxtHGSM`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGSM(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT code ,code As name FROM gsmMast`

### `getTxtHWidth`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHWidth(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sizeWidth AS Code ,sizeWidth AS Name FROM itemBatch GROUP BY sizeWidth`

### `getTxtHLength`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHLength(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sizeLength AS Code ,sizeLength AS Name FROM itemBatch GROUP BY sizeLength`

### `getTxtHSizeUnit`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSizeUnit(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT um.code, um.unit AS name FROM unitMast um WHERE um.nature = :nature AND um.unit IN (:inch, :cm, :mm)`
- Params: `:nature` ← `STEnum.UnitNature.LENGTH.getId()`; `:inch` ← `STEnum.UnitMaster.INCH.getName()`; `:cm` ← `STEnum.UnitMaster.CM.getName()`; `:mm` ← `STEnum.UnitMaster.MM.getName()`
- Other calls: `CM.getName`, `INCH.getName`, `LENGTH.getId`, `MM.getName`

### `getTxtHContractualSaleOrder`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHContractualSaleOrder(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `select pi.docId, pi.site_Code,pi.recId As ContractualSaleOrder,pi.v_No, pi.v_Date from ContractualSaleOrder pi LEFT JOIN voucher_Type vt ON vt.v_Type = pi.v_Type where pi.approvedBy <> '' and vt.category = ' {…} '`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select dm.subCode as code, sg.name from departmentMast as dm left join subGroup as sg on dm.subCode = sg.subCode left join subGroupNature sgn on sgn.code = sg.nature where 1=1 {…}`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getTxtHStockType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHStockType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `With Order`
- SQL 2: `Without Order`

## Server `RptInventoryAgeingDisplay_SrvImpl.java` (708 lines)
Helpers used: `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmServer.getSsnId`, `DmServer.getSsnSiteCode`, `DmServer.processWjMessageQueue`, `DmServer.removeAllCharacterExceptAlphaNumeric`, `DmServer.saveLog`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `execute`
`public DmResult<KeyFieldExtended> execute(String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, DefaultValue_Mdl defaultValue_Mdl)`

- SQL 1: `SELECT temp.skuName, temp.particulars, temp.positiveStock, temp.positiveValue , temp.negativeStock, temp.negativeValue , temp.slab1Qty, temp.slab1Value , temp.slab2Qty, temp.slab2Value , temp.slab3Qty, temp.slab3Value , temp.lastSlabQty, temp.lastSlabValue , temp.isItem FROM {…} AS temp order by temp.orderIndex`
- Other calls: `PRINT.getValue`, `SYSTEM_ACTIVITY.getName`

### `getTable`
`private String getTable(DmRSSPrivate rssPrivate)`


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

## Server `RptStockWithMoqReport_SrvImpl.java` (1493 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnEndDate`, `DmServer.getSsnId`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.getSsnStartDate`, `DmServer.processWjMessageQueue`, `DmServer.removeAllCharacterExceptAlphaNumeric`, `DmServer.setReportHeaderExcel`, `DmShared.convertDate`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `execute`
`public DmResult<KeyFieldExtended> execute(String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `WITH cteSOrder AS ( SELECT sod.ibCode , ISNULL(SUM(sod.skuQty), 0) - ISNULL(SUM(sod.dispatchedSkuQty), 0) pendingSoQty FROM sOrder so LEFT JOIN sOrderDetail sod ON sod.docId = so.docId LEFT JOIN voucher_Type vt ON vt.v_Type = so.v_Type WHERE ISNULL(so.approvedBy, '') <> '' AND vt.category IN ( {…} ) -- date, site, itemCode, itemCategory, itemGroup etc. GROUP BY sod.ibCode )`
- SQL 2: `SELECT {…} AS group1Name, {…} AS group2Name , MAX(sgd.name) AS warehouse , MAX(im.manualCode) AS manualCode , MAX(im.name) AS itemName , MAX(ina.name) AS itemNature , MAX(ib.bfCode) AS bfCode , MAX(ib.gsmCode) AS gsmCode , MAX(CASE ISNULL(sUnt.unit,'') WHEN 'MM' THEN ib.mmWidth WHEN 'CM' THEN ib.cmWidth WHEN 'Inch' THEN ib.inchWidth END) AS width , MAX(CASE ISNULL(sUnt.unit,'') WHEN 'MM' THEN ib.mmLength WHEN 'CM' THEN ib.cmLength WHEN 'Inch' THEN ib.inchLength END) AS length , MAX(CASE WHEN ISNULL(ib.grain,0) = 1 THEN 'Long' WHEN ISNULL(ib.grain,0) = 2 THEN 'Short' END) AS grainName , MAX(ib.sheetsPerReam) AS sheetsPerReam , MAX(ib.reamsPerBundle) AS reamsPerBundle , MAX(ib.reamWeight) AS reamWeight , MAX(sUnt.unit) AS sizeUnit , ISNULL(SUM(stk.recQty),0) - ISNULL(SUM(stk.isuQty),0) AS stockQuantity , MAX(um.unit) AS SKU , ISNULL(SUM(stk.packRecQty),0) - ISNULL(SUM(stk.packIsuQty),0) AS packStockQuantity , {…} AS netSalesQty , {…} AS averageSale , {…} AS MOQ , {…} AS difference , MAX(cso.pendingSoQty) AS pendingSoQty FROM stock stk LEFT JOIN voucher_Type vt ON vt.v_Type = stk.v_Type LEFT JOIN itemMast im ON im.code = stk.itemCode LEFT JOIN itemBatch ib ON ib.code = stk.ibCode LEFT JOIN itemNature ina ON ina.code = ib.itemNature LEFT JOIN itemCat ic ON ic.code = im.itemCategoryCode LEFT JOIN itemGroup ig ON ig.code = im.itemGroup LEFT JOIN unitMast um ON um.code = im.sku LEFT JOIN packSizeMast psm ON psm.code = stk.packSizeCode LEFT JOIN siteMast sm ON sm.code = stk.site_Code LEFT JOIN subGroup sgd ON sgd.subcode = stk.departmentCode LEFT JOIN unitMast sUnt ON sUnt.code = :unitCode {…} WHERE :one = :one {…} GROUP BY stk.itemCode, stk.ibCode ORDER BY group1Name, group2Name, itemNature, itemName`
- Errors: "From Date should be greater than or equal to " | "To Date should be less than or equal to " | "Invalid request." | "File Name not given."
- Other calls: `DAYS.between`, `RptStockWithMoqReport_Vld.validateInput`, `STServer.getSsnDepartmentCodeList`, `ZoneId.systemDefault`

### `createExcelReport`
`private KeyFieldExtended createExcelReport(String widgetName, String sessionId, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, ResultSet rsTemp, int totalColumns) throws Exception`

- Errors: "No record found!"
- Other calls: `ExcelReport_Server.createAndMergeBlankRows`, `ExcelReport_Server.createAndMergeCells`, `ExcelReport_Server.getCellStyle`, `ExcelReport_Server.getCellStyleHeader`, `ExcelReport_Server.getDataFormat`, `ExcelReport_Server.newCell`, `ExcelReport_Server.printFilterData`, `RED.getIndex`

### `getParameter`
`private void getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:one` ← `"1"`; `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`; `:unitCode` ← `DmShared.xNull(filter.get("unitCode").getTag())`; `:moqRatio` ← `DmShared.xNull(filter.get("moqRatio").getText()), true`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getTxtHWarehouse`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHWarehouse(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select dm.subCode as code, sg.name from departmentMast as dm left join subGroup as sg on dm.subCode = sg.subCode left join subGroupNature sgn on sgn.code = sg.nature where 1=1 {…}`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getTxtHItemCat`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemCat(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ic.code, ic.name FROM itemCat ic WHERE (ic.filterItemType = :filterItemType or isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ig.code, ig.name FROM itemGroup ig WHERE (ig.filterItemType = :filterItemType or ig.isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHItemNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemNature(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT inm.code, inm.name FROM itemNature inm`

### `getTxtHUnit`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHUnit(String nature, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select um.code, um.unit from unitMast um where um.nature = :nature`
- Params: `:nature` ← `DmShared.iNull(nature).toString()`

### `getTxtHItem`
`public DmResultPageing<List<TxtHItem_Mdl>> getTxtHItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , um.code AS unitCode , um.unit,im.manualCode FROM itemMast im LEFT JOIN unitmast um ON im.sku = um.code WHERE (im.filterItemType IN ( :filterStoreItemType, :filterFinishedItemType ) or im.isCommon=1)`
- Params: `:filterStoreItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`; `:filterFinishedItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`

### `getGroupBy`
`public DmResultPageing<List<KeyFieldExtended>> getGroupBy(String tag2, DmHelper helper, boolean blnToEndOfFile)`


### `getGroupOn`
`private String[] getGroupOn(String groupOn)`


### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- SQL 2: `SELECT ec.prod_SizeUnitCode, um.unit FROM enviroCompany ec LEFT JOIN unitMast um ON um.code = ec.prod_SizeUnitCode`
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

## Server `RptItemMovementDisplay_SrvImpl.java` (97 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.processWjMessageQueue`, `DmServer.saveLog`, `DmServer.showJasper`, `DmShared.xNull`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, IMSetting_Mdl displaySetting_Mdl)`

- SQL 1: `SELECT stk.orderIndex, stk.styleFor, stk.particularsCode, stk.particulars, stk.unit, stk.quantity, stk.effectiveRate, stk.value FROM {…} AS stk`
- Other calls: `PRINT.getValue`, `SYSTEM_ACTIVITY.getName`

### `getTable`
`private String getTable(DmRSSPrivate rssPrivate)`


## Server `RptItemReceiveRegister_SrvImpl.java` (988 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.processWjMessageQueue`, `DmServer.saveLog`, `DmServer.showJasper`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.xNull`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `Select {…} as group1Code , {…} as group1Name , {…} as group2Code , {…} as group2Name , sh.docId, sh.recId, sh.v_Date, sh.v_Type, vt.short_Name, sgd.name as departmentName,sgrb.name receivedByName ,im.name as itemName, um.unit as unitName, sh.refNo , sh.refDate, ms.quantity as qty , ms.remark ,sh.site_Code ,sm.name as siteName , ms.skuRate , ms.rate as itemReceiveRate , ms.skuValue as amount , ms.unitQuantity as totalPacks , ms.skuQtyForRate , ib.propertyDispCsv , ms.packCount , psm.name as packSizeName , umps.unit as packUnit from stockHead as sh left join stockHeadDetail as ms on sh.docId = ms.docId left join voucher_Type as vt on sh.v_Type = vt.v_Type left join subGroup as sgd on ms.departmentCode = sgd.subCode left join subGroup as sgrb on ms.subCode = sgrb.subCode left join itemMast as im on ms.itemCode = im.code left join unitMast as um on im.sku = um.code left join siteMast sm ON sm.code=sh.site_Code LEFT JOIN itemBatch ib on ib.code = ms.ibCode left join packSizeMast as psm on psm.code = ms.packSizeCode left join unitMast as umps on psm.packUnitCode = umps.code where vt.category in ( {…} ) {…} ORDER BY {…} {…} , {…} {…} {…} , {…} {…} {…} sh.v_Date, vt.short_Name, sh.v_No, sh.recId, ms.v_SNo`
- Other calls: `PRINT.getValue`, `RptItemReceiveRegister_Vld.validateInput`, `SYSTEM_ACTIVITY.getName`

### `getParameter`
`private void getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select dm.subCode as code, sg.name from departmentMast as dm left join subGroup as sg on dm.subCode = sg.subCode left join subGroupNature sgn on sgn.code = sg.nature where 1=1 {…}`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getTxtHVoucherNo`
`public DmResultPageing<List<TxtHRecieveNo_Mdl>> getTxtHVoucherNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select sh.docId AS code,sh.recId AS name , sh.v_Date , sh.v_No , vt.short_Name as recieveType , CASE WHEN sh.site_Code = {…} THEN 1 ELSE 2 END as tempSr from stockHead sh LEFT JOIN voucher_Type AS vt ON vt.v_Type = sh.v_Type LEFT JOIN siteMast sm ON sm.code = sh.site_Code where vt.category in ( {…} ) and sh.site_Code in( {…} :siteList {…} )`

### `getTxtHRecievedFrom`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHRecievedFrom(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name AS name FROM subgroup sg LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( SELECT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_type = acf.v_Type WHERE vt.category IN ( {…} ) )`

### `getTxtHVoucherType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVoucherType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select v_Type as code, description as name from voucher_Type where category = {…}`

### `getTxtHItemName`
`public DmResultPageing<List<TxtHItemName_Mdl>> getTxtHItemName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code, im.name, im.sku, um.unit ,im.manualCode FROM itemMast im LEFT JOIN unitmast um ON im.sku = um.code WHERE (im.filterItemType = :filterItemType or im.isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemCat`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemCat(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select CODE, NAME from itemcat where (filterItemType = :filterItemType or isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT CODE, NAME, filteritemType FROM itemgroup where (filterItemType = :filterItemType or isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHBf`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHBf(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT bf.code, bf.code as name FROM bfMast bf where bf.isActive = 1`

### `getTxtHGsm`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGsm(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT gm.code, gm.code as name FROM gsmMast gm`

### `getTxtHItemNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemNature(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT inm.code, inm.name FROM itemNature inm`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `getGroupName`
`public DmResultPageing<List<KeyFieldExtended>> getGroupName(String excludeGroup, DmHelper helper, boolean blnToEndOfFile)`


## Server `RptStockLedgerDisplay_SrvImpl.java` (104 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.processWjMessageQueue`, `DmServer.saveLog`, `DmServer.showJasper`, `DmShared.xNull`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, DefaultValue_Mdl defaultValue_Mdl)`

- SQL 1: `select temp.recId, temp.v_date, temp.v_Desc , temp.particulars,temp.propertyList, temp.receiveQty, temp.issueQty, temp.issueReceiveValue,temp.balanceQty, temp.balanceValue FROM {…} AS temp order by temp.orderIndex`
- Other calls: `PRINT.getValue`, `SYSTEM_ACTIVITY.getName`

### `getTable`
`private String getTable(DmRSSPrivate rssPrivate)`


## Server `RptSiteStockTransferRegister_SrvImpl.java` (959 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.processWjMessageQueue`, `DmServer.saveLog`, `DmServer.showJasper`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.xNull`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(boolean isFromSite, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm {…}`

### `getTxtHGateNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGateNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select gio.docId as gateDocId ,gio.v_Date as gateInDate ,gio.v_Time as gateInTime ,vt.short_Name ,gio.v_No ,gio.recId from gateInOut gio left join voucher_type vt on vt.v_Type= gio.v_Type where gio.site_Code in( {…} :sitecode {…} )`

### `getTxtHTransporter`
`public DmResultPageing<List<TxtTransporters_Mdl>> getTxtHTransporter(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code, sg.name, sg.manualCode, c.cityName FROM subGroup sg LEFT JOIN city c ON c.cityCode = sg.cityCode WHERE sg.nature = :nature`
- Params: `:nature` ← `DmServer.getCodeViaManualCode("TP", "manualCode", "subGroupNature", "code", cnTemp)`

### `getTxtHTransportationMode`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTransportationMode(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `TransportMode.values`

### `getTxtHShowServices`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHShowServices(DmHelper helper, boolean blnToEndOfFile)`


### `getGroupName`
`public DmResultPageing<List<KeyFieldExtended>> getGroupName(String excludeGroup, DmHelper helper, boolean blnToEndOfFile)`


### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT {…} as group1Code , {…} as group1Name , {…} as group2Code , {…} as group2Name ,st.docId, st.v_Type , vt.description AS voucherDescriptionName, vt.short_Name AS v_shortName, st.recId AS transferNo, st.v_Date, stmTo.name AS siteToName, cpos.cityName AS citytoPlace, c.cityName as deliveryCityName ,stmfrom.name as sfName ,stmfrom.city as sfCityName ,stmfrom.stateName as sfStateName , st.remark ,st.vehicleNo, sgt.name AS transporterName, st.lrNo, st.lrDate, st.isEWayBillRequired, st.eWayBillDt, st.eWayBillNo, gtio.recId AS gateNo, pm.name AS policyName, im.name AS itemName , std.quantity transferQty, std.skuQty, um1.unit AS skuUnitName, im.itsBasedOn itsBasedOn, um2.unit AS saleUnitName, std.rate, std.oh_Amt_Gross, std.landedSkuValue, Case When st.transportMode = {…} then ' {…} ' When st.transportMode = {…} then ' {…} ' When st.transportMode = {…} then ' {…} ' When st.transportMode = {…} then ' {…} ' ELSE '' END AS transportModeName, sm.name AS deliveryToStateName, smpos.name AS posStateName, ISNULL(std.oh_Amt_CGST ,0) as oh_Amt_CGST , ISNULL(std.oh_Amt_IGST ,0) as oh_Amt_IGST , ISNULL(std.oh_Amt_SGST ,0) as oh_Amt_SGST , ISNULL(std.oh_Amt_UGST ,0) as oh_Amt_UGST , ISNULL(std.oh_Amt_Discount ,0) as oh_Amt_Discount , std.oh_Amt_Net , std.oh_Amt_Taxable , st.oh_Amt_Net as headerNetAmount, std.oh_Amt_Taxable as headerTaxableAmount, im.itemDesc, hm.name as hsnName , hm.hsn , ib.propertyDispCsv , umc.unit as cessNaUnitName , CASE WHEN ISNULL(std.cessEvalTypeCode,0)= {…} THEN ' {…} ' WHEN ISNULL(std.cessEvalTypeCode,0)= {…} THEN ' {…} ' WHEN ISNULL(std.cessEvalTypeCode,0)= {…} THEN ' {…} ' end as cessEvalTypeName , ISNULL(std.oh_Amt_CessNa, 0) AS oh_Amt_CessNa , ISNULL(std.oh_At_CessNa, 0) AS oh_At_CessNa , ISNULL(std.oh_Amt_Cess, 0) AS oh_Amt_Cess , ISNULL(std.oh_At_Cess, 0) AS oh_At_Cess , lm.name as locationName , st.isItc , std.packCount , std.packSizeCode , psm.name as packSizeName ,umpsm.unit as packUnitName , viSt.transferDocId, CASE WHEN ISNULL(viSt.transferDocId,0)=0 THEN 0 ELSE 1 END as isTransitExist FROM stockTransfer st LEFT JOIN voucher_Type vt ON vt.v_Type = st.v_Type LEFT JOIN stockTransferDetail std on std.docId = st.docId LEFT JOIN siteMast stmfrom ON stmfrom.code = st.site_Code LEFT JOIN siteMast stmTo ON stmTo.code = st.stSite_Code LEFT JOIN city c ON c.cityCode = st.deliveryCity LEFT JOIN stateMast sm ON sm.code=c.cityCode LEFT JOIN city cpos ON cpos.cityCode = st.placeOfSupply LEFT JOIN stateMast smpos ON smpos.code=cpos.cityCode LEFT JOIN subGroup sgt ON sgt.subCode = st.transporterCode LEFT JOIN gateInOut gtIo ON gtIo.docId = st.gateDocId LEFT JOIN policyMast pm ON pm.code = st.policyCode LEFT JOIN itemBatch ib ON ib.code = std.ibCode LEFT JOIN itemMast im ON im.code = std.itemCode LEFT JOIN unitMast um1 ON um1.code = im.sku LEFT JOIN unitMast um2 ON um2.code = im.saleUnit left join hsnMast as hm on hm.code=std.hsnCode left Join unitMast umc on umc.code = std.cessNaUnitCode left Join locationMast lm on lm.code = st.locationCode left join packSizeMast psm on psm.code=std.packSizeCode left join unitMast umpsm on umpsm.code=psm.packUnitCode left join (select sitd.stDocId as transferDocId from stockTransfer sit left join stockTransferDetail sitd on sitd.docId=sit.docId left join voucher_Type vt on vt.v_Type=sit.v_Type where vt.category in (' {…} ',' {…} ') AND ISNULL(sit.approvedBy, '') <> '' AND ISNULL(sit.cancelledBy, '') = '' group by sitd.stDocId) as viSt on viSt.transferDocId =st.docId WHERE vt.category IN (' {…} ',' {…} ') {…} ORDER BY {…} {…} , {…} {…} {…} , {…} {…} {…} st.v_Date, vt.short_Name, st.v_No, st.recId, std.v_SNo`
- Other calls: `BY_AIR.getId`, `BY_AIR.getValue`, `BY_RAIL.getId`, `BY_RAIL.getValue`, `BY_ROAD.getId`, `BY_ROAD.getValue`, `BY_SHIP.getId`, `BY_SHIP.getValue`, `PRINT.getValue`, `QUANTITY_BASED.getCode`, `QUANTITY_BASED.getName`, `QUANTITY_VALUE_BASED.getCode`, `QUANTITY_VALUE_BASED.getName`, `RptSiteStockTransferRegister_Vld.validateInput`, `SYSTEM_ACTIVITY.getName`, `VALUE_BASED.getCode`, `VALUE_BASED.getName`

### `getParameter`
`private void getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`

### `getTxtHRecordType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHRecordType(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHTransferNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTransferNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select st.docId as code, st.recId as name ,st.v_Date as transferDate ,stm.name as siteName , st.v_No , vt.short_Name as transferType , CASE WHEN st.site_Code = {…} THEN 1 ELSE 2 END as tempSr from stockTransfer st LEFT JOIN voucher_Type vt ON vt.v_Type = st.v_Type LEFT JOIN siteMAst stm ON stm.code = st.site_Code WHERE vt.category IN ( ' {…} ',' {…} ') and st.site_Code in( {…} :siteList {…} )`

### `getTxtHVoucherType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVoucherType(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type , vt.description AS name , vt.guiTemplateCode , vt.balancingAc , vt.category FROM voucher_Type vt WHERE vt.category IN (' {…} ',' {…} ') AND vt.isActive = 1`

### `getTxtHTransitStatus`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTransitStatus(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHPolicyNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHPolicyNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT pm.code, pm.name, pm.insuranceComp FROM policyMast pm WHERE pm.isActive = 1`

## Server `RptOrderTransferRegister_SrvImpl.java` (2054 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnEndDate`, `DmServer.getSsnId`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.getSsnStartDate`, `DmServer.removeAllCharacterExceptAlphaNumeric`, `DmServer.setReportHeaderExcel`, `DmServer.showJasper`, `DmServer.vNull`, `DmShared.convertDate`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `execute`
`public DmResult<KeyFieldExtended> execute(String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT '' AS group1Name, '' AS group2Name, so.docId, so.site_Code, sm.name as SiteName, so.recId as orderNo, so.v_Date as orderDate ,so.partyOrdNo ,so.partyOrdDt , pro.recId as proformaInvoiceNo ,pro.v_Date as proformaInvoiceDate, ptip.name as partyName , ptip.add1 as partyAddress ,ptip.cityName as partyCity , ptip.gstin as partyGSTIN ,ptip.pan, ptic.name as consignee , ptid.name as deliveryTo ,ptid.cityName as deliveryCity , ct.cityName as placeOfSupply ,so1.recId as amdOrdNo ,so1.v_Date as amdOrdDate, sgsm.displayName as salesman , so.remark as soRemark, itn.name as itemNature, im.name as itemName, ib.bfCode , ib.gsmCode , ib.sizeWidth AS width , ib.sizeLength AS length -- CASE ISNULL(um2.unit,'') WHEN 'MM' THEN ib.mmWidth WHEN 'CM' THEN ib.cmWidth WHEN 'Inch' THEN ib.inchWidth END AS width, -- CASE ISNULL(um2.unit,'') WHEN 'MM' THEN ib.mmLength WHEN 'CM' THEN ib.cmLength WHEN 'Inch' THEN ib.inchLength END AS length, , um2.unit as sizeUnitName , um.decimalPlaces, CASE WHEN ISNULL(ib.grain,0) = 1 THEN 'L' WHEN ISNULL(ib.grain,0) = 2 THEN 'S' END AS grainName ,sod.packCount ,sod.quantity, sod.rate ,sod.oh_Amt_Gross as oh_Amt_Gross, um1.unit as itemUnitName, (ISNULL(sod.oh_At_SGST,0) + ISNULL(sod.oh_At_UGST,0) + ISNULL(sod.oh_At_CGST,0) + ISNULL(sod.oh_At_IGST,0)) AS taxRate , sod.skuQty, sod.unitCode as itemUnitCode, sod.itemDesc, sod.rate as itemValue, sod.oh_Amt_SchemeDiscount as schemeDiscount , sod.oh_Amt_Taxable, sod.oh_Amt_CGST, sod.oh_Amt_SGST, sod.oh_Amt_IGST, sod.oh_Amt_UGST, sod.oh_Amt_Net , sod.oh_Amt_Cess ,sod.oh_Amt_CessNa ,sod.CMN_Addition ,sod.CMN_Deduction, case when sod.tl_ExcessQty>0 or sod.tl_ShortageQty>0 then concat('+ ',sod.tl_ExcessQty,' ; - ',sod.tl_ShortageQty,' ;') else concat('+ ',format(sod.tl_ExcessQty,'0'),' ; - ',format(sod.tl_ShortageQty,'0'),' ;') end as tolerance , (ISNULL(sod.skuQty,0) - ISNULL(sod.dispatchedSkuQty,0)) AS pendingQty , CASE WHEN sod.isStatus = 1 THEN 'Cancelled' WHEN sod.isStatus = 2 THEN 'Completed' ELSE 'Open' END AS qtyStatus {…} FROM sOrder so LEFT JOIN sOrderDetail as sod on sod.docId= so.docId LEFT JOIN siteMast sm ON sm.code = so.site_Code LEFT JOIN partyTransInfo ptip ON ptip.docId = so.docId AND ptip.recordType = :partyRecordType LEFT JOIN partyTransInfo ptic on ptic.docId = so.docId AND ptic.recordType = :consigneeRecordType LEFT JOIN partyTransInfo ptid on ptid.docId = so.docId AND ptid.recordType = :deliveryToRecordType LEFT JOIN city as ct on ct.cityCode = so.deliveryCity LEFT JOIN sOrder as so1 on so1.docId = so.amdOrdDocId LEFT JOIN proformaInvoice as pro ON pro.docId = sod.piDocId LEFT JOIN itemMast im ON im.code = sod.itemCode LEFT JOIN itemType it ON it.code = im.itemType LEFT JOIN itemBatch ib on ib.code = sod.ibCode LEFT JOIN unitMast um ON um.code = ib.sizeCode LEFT JOIN unitMast um1 on um1.code = sod.unitCode LEFT JOIN voucher_Type vt ON vt.v_Type = so.v_Type LEFT JOIN subGroup sgsm ON sgsm.subCode = so.salesmanCode LEFT JOIN itemNature itn ON itn.code = ib.itemNature LEFT JOIN unitMast um2 ON um2.code = ib.sizeUnitCode {…} WHERE vt.category IN ( {…} ) {…} ORDER BY {…} so.v_Date, vt.short_Name, so.v_No, so.recId, sod.v_SNo`
- SQL 2: `SELECT ot.docId,ot.recId, ot.transferType,ot.v_Date, ot.godownCode , sg.name AS fromGodownName,ot.site_Code, ot.v_Type,ot.itemNatureCode , so.recId AS fromSoRecId, otd.fromSoDocId, otd.fromSoV_SNo, ot.preparedBy, ot.u_Name , im.name AS itemName, otd.itemCode , otd.quantity, otd.packCount , otd.unitCode, um.unit AS unitName , bcm.reelBundleNo, bcm.barcode, otd.barcodeId , otd.toGodownCode, sgtg.name AS togodownName , otd.toSoDocId, soTo.recId AS toSoRecId , otd.ibCode, ib.sizeUnitCode, umsu.unit AS sizeUnitName ,ib.bfCode, ib.gsmCode,ib.sizeLength,ib.sizeWidth ,CASE WHEN ot.transferType = 1 THEN 'Godown Transfer' ELSE 'Order Tansfer' END AS transferTypeName FROM orderTransferDetail otd LEFT JOIN orderTransfer ot ON ot.docId = otd.docId LEFT JOIN itemMast im ON im.code = otd.itemCode LEFT JOIN unitMast um ON um.code = otd.unitCode LEFT JOIN itemBatch ib ON ib.code = otd.ibCode LEFT JOIN unitMast umsu ON umsu.code = ib.sizeUnitCode LEFT JOIN subGroup sgtg ON sgtg.subCode = otd.toGodownCode LEFT JOIN sOrder so ON so.docId = otd.fromSoDocId LEFT JOIN sOrder soTo ON soTo.docId = otd.toSoDocId LEFT JOIN barCodeMast bcm ON bcm.barcodeId = otd.barcodeId LEFT JOIN subGroup sg ON sg.subCode = ot.godownCode WHERE 1=1 {…}`
- Errors: "From Date should be greater than or equal to " | "To Date should be less than or equal to " | "Invalid request." | "File Name not given."
- Other calls: `RptOrderTransferRegister_Vld.validateInput`

### `createPdfReport`
`private KeyFieldExtended createPdfReport(String widgetName, String sessionId, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, ResultSet rsTemp) throws Exception`


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
`private void getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHOrderTransferNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHOrderTransferNo(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT ot.docId AS code, ot.recId AS name, ot.v_Date FROM orderTransfer ot`

### `getTxtHTransferType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTransferType(DmHelper pageing, boolean blnToEndOfFile)`


### `getTxtHVoucherType`
`public DmResultPageing<List<TxtHVoucherNo_Mdl>> getTxtHVoucherType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type as code , vt.description as voucherType , vt.category , vt.short_Name FROM voucher_Type vt WHERE vt.category IN (' {…} ')`

### `getTxtHParty`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHParty(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as supplierName , sg.manualCode , c.cityName FROM subGroup sg LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( SELECT DISTINCT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_Type = acf.v_Type WHERE vt.category IN (' {…} ') )`

### `getTxtHItemNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemNature(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT itn.code, itn.name, itn.manualCode FROM itemNature AS itn WHERE itn.isActive = 1 AND itn.manualCode IN (:R, :S)`
- Params: `:R` ← `DmShared.xNull(STEnum.ItemNature.REEL.getManualCode())`; `:S` ← `DmShared.xNull(STEnum.ItemNature.SHEET.getManualCode())`
- Other calls: `REEL.getManualCode`, `SHEET.getManualCode`

### `getItemName`
`public DmResultPageing<List<TxtHItem_Mdl>> getItemName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select im.code as itemCode, im.name as itemName ,im.manualCode ,it.name as itemTypeName from itemMast im LEFT JOIN unitmast um ON im.saleUnit = um.code Left join itemType it on it.code=im.itemType where (im.filterItemType IN (:FM,:SM) OR im.itemType = :SV)`
- Params: `:FM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:SM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`; `:SV` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SV", "manualCode", "itemType", "code", cnTem`

### `getGroupOn`
`private String[] getGroupOn(String groupOn)`


### `getTxtHYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHReportFor`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHReportFor(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHBF`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHBF(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT code ,code As name FROM bfMast`

### `getTxtHGSM`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGSM(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT code ,code As name FROM gsmMast`

### `getTxtHWidth`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHWidth(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sizeWidth AS Code ,sizeWidth AS Name FROM itemBatch GROUP BY sizeWidth`

### `getTxtHLength`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHLength(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sizeLength AS Code ,sizeLength AS Name FROM itemBatch GROUP BY sizeLength`

### `getTxtHPreparedBy`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHPreparedBy(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT MAX(ot.docId) AS code, MAX(ot.recId) AS recId, ot.preparedBy AS name FROM orderTransfer ot GROUP BY ot.preparedBy`

### `getTxtHModifiedBy`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHModifiedBy(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT MAX(ot.docId) AS code, MAX(ot.recId) AS recId, ot.u_Name AS name FROM orderTransfer ot GROUP BY ot.u_Name`

### `getTxtHSizeUnit`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSizeUnit(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT um.code, um.unit AS name FROM unitMast um WHERE um.nature = :nature AND um.unit IN (:inch, :cm, :mm)`
- Params: `:nature` ← `STEnum.UnitNature.LENGTH.getId()`; `:inch` ← `STEnum.UnitMaster.INCH.getName()`; `:cm` ← `STEnum.UnitMaster.CM.getName()`; `:mm` ← `STEnum.UnitMaster.MM.getName()`
- Other calls: `CM.getName`, `INCH.getName`, `LENGTH.getId`, `MM.getName`

## Server `RptItemTransferRegister_SrvImpl.java` (858 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.processWjMessageQueue`, `DmServer.saveLog`, `DmServer.showJasper`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.xNull`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `Select sh.docId,sh.recId as transferNo, sh.v_Date as transferDate, vt.short_Name as vtype, depTo.name as departmentTo,depFrm.name as departmentFrom, tranTo.name as transferTo,im.name as itemName, imm.companyName as itemMakeName, um.unit, sh.refNo , sh.refDate, shd.quantity,shd.remark , ib.batchNo,ib.manufacturingDate,ib.expiryDate, ib.mrp,icm.name as colourName,ism.name as sizeName, sh.site_Code ,sm.name as siteName , ib.propertyDispCsv , psm.name as packSizeName , shd.packCount , umps.unit as packUnit from stockHead as sh left join stockHeadDetail as shd on sh.docId = shd.docId left join voucher_Type as vt on sh.v_Type = vt.v_Type left join subGroup as depTo on shd.departmentToCode = depTo.subCode left join subGroup as depFrm on shd.departmentCode = depFrm.subCode left join subGroup as tranTo on shd.subCode = tranTo.subCode left join itemMast as im on shd.itemCode = im.code left join unitMast as um on im.sku = um.code left join siteMast sm ON sm.code=sh.site_Code LEFT JOIN itemBatch ib on ib.code = shd.ibCode left join itemColourMast as icm on icm.code = ib.colourCode left join itemSizeMast as ism on ism.code = ib.sizeCode Left join itemMakeMast as imm on imm.code= ib.itemMakeCode left join packSizeMast as psm on psm.code = shd.packSizeCode left join unitMast as umps on psm.packUnitCode = umps.code where vt.category in ( {…} ) {…} ORDER BY sh.v_Date, vt.short_Name, sh.v_No, sh.recId, shd.v_SNo`
- Other calls: `PRINT.getValue`, `RptItemTransferRegister_Vld.validateInput`, `STServer.getSsnDepartmentCodeList`, `SYSTEM_ACTIVITY.getName`

### `getParameter`
`private void getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(boolean isFromDepartment, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select dm.subCode as code, sg.name from departmentMast as dm left join subGroup as sg on dm.subCode = sg.subCode left join subGroupNature sgn on sgn.code = sg.nature where 1=1 {…}`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getTxtHTransferNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTransferNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select sh.docId AS code,sh.recId AS name , sh.v_No , sh.v_Date , CASE WHEN sh.site_Code = {…} THEN 1 ELSE 2 END as tempSr from stockHead sh LEFT JOIN voucher_Type AS vt ON vt.v_Type=sh.v_Type LEFT JOIN siteMast sm ON sm.code = sh.site_Code where vt.category in ( {…} ) AND sh.site_Code in( {…} :siteList {…} )`

### `getTxtHVoucherType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVoucherType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select v_Type as code, description as name from voucher_Type where category in ( {…} )`

### `getTxtHItemName`
`public DmResultPageing<List<TxtHItemName_Mdl>> getTxtHItemName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code, im.name, im.sku, um.unit,im.manualCode FROM itemMast im LEFT JOIN unitmast um ON im.sku = um.code WHERE (im.filterItemType = :filterItemType or im.isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemCat`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemCat(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select CODE, NAME from itemcat where (filterItemType = :filterItemType or isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT CODE, NAME, filteritemType FROM itemgroup where (filterItemType = :filterItemType or isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm WHERE sm.code IN( {…} :code {…} )`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `getTxtHTransferTo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTransferTo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as name FROM subgroup sg LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( SELECT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_type = acf.v_Type WHERE vt.category IN ( {…} ) )`

### `getTxtHBf`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHBf(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT bf.code, bf.code as name FROM bfMast bf where bf.isActive = 1`

### `getTxtHGsm`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGsm(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT gm.code, gm.code as name FROM gsmMast gm`

### `getTxtHItemNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemNature(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT inm.code, inm.name FROM itemNature inm`

## Server `RptSupplierWiseStockDisplay_SrvImpl.java` (636 lines)
Helpers used: `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmServer.getSsnId`, `DmServer.getSsnSiteCode`, `DmServer.processWjMessageQueue`, `DmServer.removeAllCharacterExceptAlphaNumeric`, `DmServer.saveLog`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `execute`
`public DmResult<KeyFieldExtended> execute(String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, DefaultValue_Mdl defaultValue_Mdl)`

- SQL 1: `SELECT temp.skuName, temp.departmentName, temp.particulars, temp.bfCode, temp.gsmCode, temp.stockQty, temp.stockValue FROM {…} AS temp order by temp.orderIndex`
- Other calls: `PRINT.getValue`, `SYSTEM_ACTIVITY.getName`

### `getTable`
`private String getTable(DmRSSPrivate rssPrivate)`


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

## Server `RptStockBatch_SrvImpl.java` (100 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.processWjMessageQueue`, `DmServer.saveLog`, `DmServer.showJasper`, `DmShared.xNull`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, DefaultValue_Mdl defaultValue_Mdl)`

- SQL 1: `SELECT temp.orderIndex, temp.propertyList , temp.openingStock, temp.openingValue, temp.purchaseQty, temp.otherReceiveQty , temp.saleQty, temp.otherIssueQty, temp.closingStock, temp.closingValue FROM {…} AS temp order by temp.orderIndex`
- Other calls: `PRINT.getValue`, `SYSTEM_ACTIVITY.getName`

### `getTable`
`private String getTable(DmRSSPrivate rssPrivate)`


## Server `RptBFGSMClosingStock_SrvImpl.java` (2228 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.removeAllCharacterExceptAlphaNumeric`, `DmServer.saveLog`, `DmServer.setReportHeaderExcel`, `DmServer.showJasper`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT ' {…} ' as ReportType , ' {…} ' as GroupName1 , {…} as GroupValue1 , ' {…} ' as GroupName2 , {…} as GroupValue2 , ' {…} ' as GroupName3 , {…} as GroupValue3 , ' {…} ' as GroupName4 , {…} as GroupValue4, SUM(iis.balanceQty) AS TotalQty , SUM(iis.balancePcs) AS TotalReels FROM itemIdStock IIS LEFT JOIN sOrderDetail sod ON sod.docId = IIS.soDocId AND sod.v_SNo = IIS.soV_SNo LEFT JOIN sOrder SO ON SO.docId=IIS.soDocId LEFT JOIN barCodeMast BCM ON IIS.itemId=BCM.barCodeId LEFT JOIN itemBatch ib ON ib.code = IIS.ibCode LEFT JOIN itemMast im ON im.code = ib.itemCode LEFT JOIN unitMast Um ON UM.code = ib.sizeUnitCode LEFT JOIN subGroup sgDep ON sgDep.subCode = IIS.departmentCode LEFT JOIN partyTransInfo ptip ON ptip.docId = so.docId AND ptip.recordType = :partyRecordType LEFT JOIN partyTransInfo ptic ON ptic.docId = so.docId AND ptic.recordType = :consigneeRecordType WHERE 1=1 AND ISNULL(IIS.balanceQty,0) > 0 {…} GROUP BY {…} ORDER BY {…}`
- Params: `:PartyRecordType` ← `DmEnum.PartyTransRecordType.PARTY.getCode()`; `:consigneeRecordType` ← `DmEnum.PartyTransRecordType.CONSIGNEE.getCode()`
- Other calls: `CONSIGNEE.getCode`, `PARTY.getCode`, `PRINT.getValue`, `RptBFGSMClosingStock_Vld.validateInput`, `SYSTEM_ACTIVITY.getName`

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

- Params: `:AsDate` ← `DmServer.convertDate(filter.get("AsDate").getText()), true`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `getTxtHHavingWeight`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHHavingWeight(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHReportChoice`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHReportChoice(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHSaleOrderNo`
`public DmResultPageing<List<TxtHVoucherNo_Mdl>> getTxtHSaleOrderNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT so.docId, so.site_Code,so.recId, so.v_Date , vt.short_Name as recieveType , so.v_No, sg.name as partyName, sd.name as distributorName , sc.name as consigneeName , CASE WHEN so.site_Code = {…} THEN 1 ELSE 2 END as tempSr,so.partyOrdNo,so.partyOrdDt FROM sOrder AS so left join voucher_Type as vt on vt.v_Type = so.v_Type LEFT JOIN subGroup sg On sg.subCode = so.partyCode LEFT JOIN subGroup sd On sd.subCode = so.distributor LEFT JOIN subGroup sc On sc.subCode = so.consignee LEFT JOIN siteMast sm ON sm.code = so.site_Code where vt.Category IN (' {…} ') AND ISNULL(so.approvedBy, '') <> '' and so.site_Code in( {…} :siteList {…} )`

### `getTxtHParty`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHParty(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as supplierName , sg.manualCode , c.cityName FROM subGroup sg LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( SELECT DISTINCT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_Type = acf.v_Type WHERE vt.category IN (' {…} ') )`

### `getTxtHDistributor`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHDistributor(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as supplierName , sg.manualCode , c.cityName , sg.priceListCode , sg.partyCat FROM subGroup sg LEFT JOIN city c ON c.cityCode = sg.cityCode WHERE sg.partyCat= :partyCat`
- Params: `:partyCat` ← `DmEnum.PartyCategory.DISTRIBUTOR.getId()`
- Other calls: `DISTRIBUTOR.getId`

### `getTxtHDeliveryCity`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDeliveryCity(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select ct.cityCode, ct.cityName, ct.stateCode, sm.name as stateName from city ct left join stateMast sm on ct.stateCode = sm.code`

### `getxtHProformaInvoiceNo`
`public DmResultPageing<List<KeyFieldExtended>> getxtHProformaInvoiceNo(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `select pi.docId, pi.site_Code,pi.recId As ProformaInvoiceNo,pi.v_No, pi.v_Date from proformaInvoice pi LEFT JOIN voucher_Type vt ON vt.v_Type = pi.v_Type where pi.approvedBy <> '' and vt.category = ' {…} '`

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

### `getTxtHYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHBF`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHBF(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT code ,code As name FROM bfMast`

### `getTxtHGSM`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGSM(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT code ,code As name FROM gsmMast`

### `getTxtHItemNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemNature(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT itn.code, itn.name, itn.manualCode FROM itemNature AS itn WHERE itn.isActive = 1 AND itn.manualCode IN (:R,:S)`
- Params: `:R` ← `DmShared.xNull(STEnum.ItemNature.REEL.getManualCode())`; `:S` ← `DmShared.xNull(STEnum.ItemNature.SHEET.getManualCode())`
- Other calls: `REEL.getManualCode`, `SHEET.getManualCode`

### `getTxtHWidth`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHWidth(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sizeWidth AS Code ,sizeWidth AS Name FROM itemBatch GROUP BY sizeWidth`

### `getTxtHLength`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHLength(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sizeLength AS Code ,sizeLength AS Name FROM itemBatch GROUP BY sizeLength`

### `getTxtHContractualSaleOrder`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHContractualSaleOrder(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `select pi.docId, pi.site_Code,pi.recId As ContractualSaleOrder,pi.v_No, pi.v_Date from ContractualSaleOrder pi LEFT JOIN voucher_Type vt ON vt.v_Type = pi.v_Type where pi.approvedBy <> '' and vt.category = ' {…} '`

## Server `RptMovementAnalysisDisplay_SrvImpl.java` (105 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.processWjMessageQueue`, `DmServer.saveLog`, `DmServer.showJasper`, `DmShared.xNull`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, RptMovementAnalysisDisplay_Mdl maSummaryPrint_Mdl)`

- SQL 1: `SELECT stk.orderIndex, stk.styleFor, stk.summaryOn, stk.particulars, stk.unit , stk.inQty, stk.inEffRate, stk.inValue, stk.outQty, stk.outEffRate, stk.outValue FROM {…} AS stk`
- Other calls: `PRINT.getValue`, `SYSTEM_ACTIVITY.getName`

### `getTable`
`private String getTable(DmRSSPrivate rssPrivate)`


## Server `RptStockSummaryDisplay_SrvImpl.java` (103 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.processWjMessageQueue`, `DmServer.saveLog`, `DmServer.showJasper`, `DmShared.xNull`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, DefaultValue_Mdl defaultValue_Mdl)`

- SQL 1: `SELECT temp.skuName,temp.particulars, temp.openingStock, temp.openingValue , temp.purchaseQty, temp.otherReceiveQty, temp.saleQty, temp.otherIssueQty , temp.closingStock, temp.closingValue FROM {…} AS temp order by temp.orderIndex`
- Other calls: `PRINT.getValue`, `SYSTEM_ACTIVITY.getName`

### `getTable`
`private String getTable(DmRSSPrivate rssPrivate)`


## Server `RptItemOpeningRegister_SrvImpl.java` (834 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.processWjMessageQueue`, `DmServer.saveLog`, `DmServer.showJasper`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.xNull`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `Select sh.docId, sh.recId, sh.v_Date, vt.short_Name, sgd.name as departmentName,sgrb.name receivedByName,im.name as itemName , shd.quantity,um.unit as skuName ,shd.rate as itemOpeningRate , shd.skuValue as amount ,shd.refNo as refChallanNo , shd.refDate as refChallanDate, shd.remark , ib.propertyDispCsv , shd.packCount , psm.name as packSizeName , umps.unit as packUnit from stockHead as sh left join stockHeadDetail as shd on sh.docId = shd.docId left join voucher_Type as vt on sh.v_Type = vt.v_Type left join subGroup as sgd on shd.departmentCode = sgd.subCode left join subGroup as sgrb on shd.subCode = sgrb.subCode left join itemMast as im on shd.itemCode = im.code left join unitMast as um on im.sku = um.code left join unitMast as ump on shd.unitCode = ump.code left join packSizeMast as psm on psm.code = shd.packSizeCode left join unitMast as umps on psm.packUnitCode = umps.code LEFT JOIN itemBatch ib on ib.code = shd.ibCode where vt.category in ( {…} ) {…} ORDER BY sh.v_Date, vt.short_Name, sh.v_No, sh.recId, shd.v_SNo`
- Other calls: `PRINT.getValue`, `RptItemOpeningRegister_Vld.validateInput`, `SYSTEM_ACTIVITY.getName`

### `getParameter`
`private void getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:openingDate` ← `DmServer.convertDate(filter.get("openingDate").getText()), true`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select dm.subCode as code, sg.name from departmentMast as dm left join subGroup as sg on dm.subCode = sg.subCode left join subGroupNature sgn on sgn.code = sg.nature where 1=1 {…}`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getTxtHVoucherNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVoucherNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select sh.docId AS code,sh.recId AS name , sh.v_No , sh.v_Date , CASE WHEN sh.site_Code = {…} THEN 1 ELSE 2 END as tempSr from stockHead sh LEFT JOIN voucher_Type AS vt ON vt.v_Type=sh.v_Type LEFT JOIN siteMast sm ON sm.code = sh.site_Code where vt.category in ( {…} ) and sh.site_Code in( {…} :siteList {…} )`

### `getTxtHRecievedFrom`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHRecievedFrom(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name AS name , sg.manualCode , c.cityName as city FROM subgroup sg LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( SELECT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_type = acf.v_Type WHERE vt.category IN ( {…} ) )`

### `getTxtHVoucherType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVoucherType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select v_Type as code, description as name from voucher_Type where category = {…}`

### `getTxtHItemName`
`public DmResultPageing<List<TxtHItemName_Mdl>> getTxtHItemName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code, im.name, im.sku, um.unit ,im.manualCode FROM itemMast im LEFT JOIN unitmast um ON im.sku = um.code WHERE (im.filterItemType IN (:FM,:SM) or im.isCommon=1)`
- Params: `:FM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:SM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemCat`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemCat(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select CODE, NAME from itemcat where (filterItemType IN (:FM,:SM) or isCommon=1)`
- Params: `:FM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:SM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT CODE, NAME, filteritemType FROM itemgroup where (filterItemType IN (:FM,:SM) or isCommon=1)`
- Params: `:FM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:SM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHBf`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHBf(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT bf.code, bf.code as name FROM bfMast bf where bf.isActive = 1`

### `getTxtHGsm`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGsm(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT gm.code, gm.code as name FROM gsmMast gm`

### `getTxtHItemNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemNature(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT inm.code, inm.name FROM itemNature inm`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

## Server `RptPhysicalStockBeforePosting_SrvImpl.java` (844 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnVoucherPrefix`, `DmServer.saveLog`, `DmServer.showJasper`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.xNull`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT COUNT(*) AS totalClearanceEntry FROM stockHead sh LEFT JOIN voucher_Type vt ON vt.v_Type = sh.v_Type LEFT JOIN ( SELECT shd.docId, max(shd.departmentCode) AS departmentCode FROM stockHeadDetail shd GROUP BY shd.docId ) AS vShd ON vShd.docId = sh.docId WHERE vt.category = ' {…} ' AND sh.site_Code = :site_Code AND sh.v_Date = :v_Date AND vShd.departmentCode = :departmentCode`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Date` ← `DmServer.convertDate(filter.get("postingDate").getText())`; `:departmentCode` ← `DmShared.xNull(filter.get("departmentCode").getTag())`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Date` ← `DmServer.convertDate(filter.get("postingDate").getText())`; `:departmentCode` ← `DmShared.xNull(filter.get("departmentCode").getTag())`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Date` ← `DmServer.convertDate(filter.get("postingDate").getText())`; `:departmentCode` ← `DmShared.xNull(filter.get("departmentCode").getTag())`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:filterValueCode` ← `DmShared.iNull(filter.get("filterValue").getTag()).toString()`
- Errors: "Posting  already exist for selected date." | "Posting is not exist for selected date." | "Clearance already exist for selected date."
- Other calls: `PRINT.getValue`, `PhysicalStockFilterType.get`, `RptPhysicalStockBeforePosting_Vld.validateInput`, `SYSTEM_ACTIVITY.getName`

### `getParameter`
`private void getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:postingDate` ← `DmServer.convertDate(filter.get("postingDate").getText()), true`; `:departmentCode` ← `DmShared.xNull(filter.get("departmentCode").getTag())`

### `getTxtHDate`
`public DmResultPageing<List<TxtHDate_Mdl>> getTxtHDate(String reportType, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sh.v_Date AS date , shd.departmentCode , MAX(sg.name) AS departmentName , MAX(ISNULL(sh.filterType, 0)) AS filterTypeCode , MAX(CASE ISNULL(sh.filterType, 0) WHEN {…} THEN ' {…} ' WHEN {…} THEN ' {…} ' WHEN {…} THEN ' {…} ' WHEN {…} THEN ' {…} ' ELSE '' END) AS filterTypeName , MAX(CASE ISNULL(sh.filterType, 0) WHEN {…} THEN ISNULL(sh.itemCategoryCode, 0) WHEN {…} THEN ISNULL(sh.itemGroupCode, 0) WHEN {…} THEN ISNULL(sh.itemMakeCode, 0) WHEN {…} THEN ISNULL(sh.itemCode, 0) ELSE '' END) AS filterValueCode , MAX(CASE ISNULL(sh.filterType, 0) WHEN {…} THEN ISNULL(ic.name, '') WHEN {…} THEN ISNULL(ig.name, '') WHEN {…} THEN ISNULL(mm.companyname, '') WHEN {…} THEN ISNULL(im.name, '') ELSE '' END) AS filterValueName FROM StockHead as sh LEFT JOIN ( SELECT shd.docid, MAX(shd.departmentCode) AS departmentCode FROM stockHeadDetail shd GROUP BY shd.docid ) AS shd ON shd.docid = sh.docid LEFT JOIN voucher_Type vt ON sh.v_Type = vt.v_Type LEFT JOIN itemCat ic ON ic.code = sh.itemCategoryCode LEFT JOIN itemGroup ig ON ig.code = sh.itemGroupCode LEFT JOIN itemMakeMast mm ON mm.code = sh.itemMakeCode LEFT JOIN itemMast im ON im.code = sh.itemCode LEFT JOIN subGroup sg ON sg.subCode = shd.departmentCode WHERE vt.category = {…} AND sh.site_Code = :site_Code AND sh.v_prefix = :vPrefix {…} GROUP BY sh.v_Date, shd.departmentCode, sh.site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`
- Other calls: `ITEM.getId`, `ITEM.getValueStore`, `ITEM_CATEGORY.getId`, `ITEM_CATEGORY.getValueStore`, `ITEM_GROUP.getId`, `ITEM_GROUP.getValueStore`, `ITEM_MAKE.getId`, `ITEM_MAKE.getValueStore`, `STServer.getSsnDepartmentCodeList`

### `getTxtHItemCategory`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemCategory(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ic.code, ic.name FROM itemCat ic WHERE (ic.filterItemType = :filterItemType OR ic.isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ig.code, ig.name FROM itemgroup ig WHERE (ig.filterItemType = :filterItemType OR ig.isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHManufacturer`
`public DmResultPageing<List<TxtHManufacturer_Mdl>> getTxtHManufacturer(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `select sg.subCode,sg.name,ct.cityName,sg.manualCode,concat(add1,add2) as address,sg.filterNature from subGroup as sg left join city as ct on sg.cityCode = ct.cityCode where filterNature = :filterNature`
- Params: `:filterNature` ← `filterNature`

### `getTxtHItemCompany`
`public DmResultPageing<List<TxtHItemCompany_Mdl>> getTxtHItemCompany(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code,companyName,shortName from itemMakeMast WHERE filterItemType = :filterItemType`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemName`
`public DmResultPageing<List<TxtHItem_Mdl>> getTxtHItemName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code, im.name,im.manualCode FROM itemMast im`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT dm.subCode , sg.name as departmentName FROM departmentMast dm LEFT JOIN subGroup sg ON sg.subCode = dm.subCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE 1=1 {…}`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getTxtHReportType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHReportType(DmHelper helper, boolean blnToEndOfFile)`


## Server `RptStockAsOnDateRegister_SrvImpl.java` (1217 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnId`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.processWjMessageQueue`, `DmServer.removeAllCharacterExceptAlphaNumeric`, `DmServer.saveLog`, `DmServer.setReportHeaderExcel`, `DmServer.vNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `execute`
`public DmResult<KeyFieldExtended> execute(String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- Other calls: `PRINT.getValue`, `SYSTEM_ACTIVITY.getName`

### `createExcelReport`
`private KeyFieldExtended createExcelReport(String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, Connection cnTemp) throws Exception`

- SQL 1: `SELECT STRING_AGG(CONVERT(NVARCHAR(max),sm.manualCode), ',') AS siteCSV FROM siteMast sm WHERE sm.code IN ( {…} )`
- SQL 2: `SELECT * FROM ( SELECT MAX(sm.manualCode) AS siteManualCode , MAX(sgd.name) AS warehouse , MAX(im.name) AS itemName , MAX(ib.propertyDispCsv) AS propertyDispCsv {…} FROM stock stk LEFT JOIN voucher_Type vt ON vt.v_Type = stk.v_Type LEFT JOIN itemMast im ON im.code = stk.itemCode LEFT JOIN itemBatch ib ON ib.code = stk.ibCode LEFT JOIN unitMast um ON um.code = im.sku LEFT JOIN packSizeMast psm ON psm.code = stk.packSizeCode LEFT JOIN unitMast ump ON ump.code = psm.packUnitCode LEFT JOIN siteMast sm ON sm.code = stk.site_Code LEFT JOIN subGroup sgd ON sgd.subcode = stk.departmentCode WHERE :one = :one {…} GROUP BY stk.site_Code, stk.departmentCode, stk.itemCode, stk.ibCode {…} ) AS t PIVOT ( {…} FOR siteManualCode IN ( {…} )) AS PivotTable ORDER BY warehouse, itemName {…}`
- Errors: "Invalid request." | "File Name not given." | "No record found!"
- Other calls: `CellReference.convertNumToColString`, `RptStockAsOnDateRegister_Vld.validateInput`, `STServer.getSsnDepartmentCodeList`

### `getParameter`
`private void getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:one` ← `"1"`; `:asOnDate` ← `DmServer.convertDate(filter.get("asOnDate").getText()), true`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getTxtHWarehouse`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHWarehouse(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select dm.subCode as code, sg.name from departmentMast as dm left join subGroup as sg on dm.subCode = sg.subCode left join subGroupNature sgn on sgn.code = sg.nature where 1=1 {…}`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getTxtHItem`
`public DmResultPageing<List<TxtHItem_Mdl>> getTxtHItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , um.code AS unitCode , um.unit,im.manualCode FROM itemMast im LEFT JOIN unitmast um ON im.sku = um.code WHERE (im.filterItemType IN ( :filterStoreItemType, :filterFinishedItemType ) or im.isCommon=1)`
- Params: `:filterStoreItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`; `:filterFinishedItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemCat`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemCat(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ic.code, ic.name FROM itemCat ic WHERE (ic.filterItemType = :filterItemType or isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ig.code, ig.name FROM itemGroup ig WHERE (ig.filterItemType = :filterItemType or ig.isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHValueOn`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHValueOn(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHReportFor`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHReportFor(DmHelper pageing, boolean blnToEndOfFile)`


### `getTxtHBf`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHBf(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT bf.code, bf.code as name FROM bfMast bf where bf.isActive = 1`

### `getTxtHGsm`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGsm(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT gm.code, gm.code as name FROM gsmMast gm`

### `getTxtHItemNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemNature(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT inm.code, inm.name FROM itemNature inm`

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

### `printTotal`
`private int printTotal(DmXlsWriter xlsWriter, XSSFSheet workSheet, int[] columnIndices, int fromRowIndex, int toRowIndex, int printRowIndex) throws Exception`


## Server `RptFinishStockWithReelNo_SrvImpl.java` (2476 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnId`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.removeAllCharacterExceptAlphaNumeric`, `DmServer.saveLog`, `DmServer.setReportHeaderExcel`, `DmServer.showJasper`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `execute`
`public DmResult<KeyFieldExtended> execute(String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- Params: `:REEL` ← `STEnum.ItemNature.REEL.getManualCode()`; `:partyRecordType` ← `DmEnum.PartyTransRecordType.PARTY.getCode()`; `:consigneeRecordType` ← `DmEnum.PartyTransRecordType.CONSIGNEE.getCode()`
- Other calls: `CONSIGNEE.getCode`, `PARTY.getCode`, `PRINT.getValue`, `REEL.getManualCode`, `ReportType.trim`, `RptFinishStockWithReelNo_Vld.validateInput`, `STServer.getSsnDepartmentCodeList`, `SYSTEM_ACTIVITY.getName`

### `createPdfReport`
`private KeyFieldExtended createPdfReport(String RpTitle, String JasperReportName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, ResultSet rsTemp) throws Exception`


### `getGroupArray`
`private String[] getGroupArray(String groupOn)`


### `createExcelReport`
`private KeyFieldExtended createExcelReport(String widgetName, String sessionId, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, ResultSet rsTemp, int totalColumns) throws Exception`

- Errors: "No record found!"
- Other calls: `ExcelReport_Server.createAndMergeBlankRows`, `ExcelReport_Server.createAndMergeCells`, `ExcelReport_Server.getCellStyle`, `ExcelReport_Server.getCellStyleHeader`, `ExcelReport_Server.getDataFormat`, `ExcelReport_Server.newCell`, `ExcelReport_Server.printFilterData`

### `resetSummaryFunction`
`private void resetSummaryFunction(ExcelReportConfig.SummaryFunctionType functionType)`


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

- Params: `:AsDate` ← `DmServer.convertDate(filter.get("AsDate").getText()), true`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHSaleOrderNo`
`public DmResultPageing<List<TxtHVoucherNo_Mdl>> getTxtHSaleOrderNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT so.docId, so.site_Code,so.recId, so.v_Date , vt.short_Name as recieveType , so.v_No, sg.name as partyName, sd.name as distributorName , sc.name as consigneeName , CASE WHEN so.site_Code = {…} THEN 1 ELSE 2 END as tempSr,so.partyOrdNo,so.partyOrdDt FROM sOrder AS so left join voucher_Type as vt on vt.v_Type = so.v_Type LEFT JOIN subGroup sg On sg.subCode = so.partyCode LEFT JOIN subGroup sd On sd.subCode = so.distributor LEFT JOIN subGroup sc On sc.subCode = so.consignee LEFT JOIN siteMast sm ON sm.code = so.site_Code where vt.Category IN (' {…} ') AND ISNULL(so.approvedBy, '') <> '' and so.site_Code in( {…} :siteList {…} )`

### `getTxtHParty`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHParty(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as supplierName , sg.manualCode , c.cityName FROM subGroup sg LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( SELECT DISTINCT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_Type = acf.v_Type WHERE vt.category IN (' {…} ') )`

### `getTxtHDistributor`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHDistributor(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as supplierName , sg.manualCode , c.cityName , sg.priceListCode , sg.partyCat FROM subGroup sg LEFT JOIN city c ON c.cityCode = sg.cityCode WHERE sg.partyCat= :partyCat`
- Params: `:partyCat` ← `DmEnum.PartyCategory.DISTRIBUTOR.getId()`
- Other calls: `DISTRIBUTOR.getId`

### `getTxtHDeliveryCity`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDeliveryCity(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select ct.cityCode, ct.cityName, ct.stateCode, sm.name as stateName from city ct left join stateMast sm on ct.stateCode = sm.code`

### `getxtHProformaInvoiceNo`
`public DmResultPageing<List<KeyFieldExtended>> getxtHProformaInvoiceNo(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `select pi.docId, pi.site_Code,pi.recId As ProformaInvoiceNo,pi.v_No, pi.v_Date from proformaInvoice pi LEFT JOIN voucher_Type vt ON vt.v_Type = pi.v_Type where pi.approvedBy <> '' and vt.category = ' {…} '`

### `getTxtHSalesman`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHSalesman(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ec.srDesignationCode FROM enviroCompany ec`
- SQL 2: `SELECT sg.subCode AS code , sg.name AS salesMan , sg.manualCode , c.cityName , c.cityCode FROM subGroup sg LEFT JOIN subGroup2 sg2 ON sg2.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode WHERE sg2.designationCode = {…}`

### `getTxtHItemCat`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemCat(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ic.code, ic.name FROM itemCat ic WHERE ic.filterItemType IN (:FM, :SF)`
- Params: `:FM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:SF` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ig.code, ig.name FROM itemGroup ig LEFT JOIN itemNature imn ON imn.code = ig.itemNature WHERE imn.manualCode = :R`
- Params: `:R` ← `STEnum.ItemNature.REEL.getManualCode()`
- Other calls: `REEL.getManualCode`

### `getItemName`
`public DmResultPageing<List<TxtHItem_Mdl>> getItemName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select im.code as itemCode, im.name as itemName , im.manualCode, it.name as itemTypeName from itemMast im Left join itemType it on it.code=im.itemType LEFT JOIN itemGroup ig ON ig.code = im.itemGroup LEFT JOIN itemNature imn ON imn.code = ig.itemNature WHERE imn.manualCode = :R`
- Params: `:R` ← `STEnum.ItemNature.REEL.getManualCode()`
- Other calls: `REEL.getManualCode`

### `getTxtHStatus`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHStatus(DmHelper helper, boolean blnToEndOfFile)`


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

### `getTxtHStockType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHStockType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `With Order`
- SQL 2: `Without Order`

### `getTxtHWidth`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHWidth(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sizeWidth AS Code ,sizeWidth AS Name FROM itemBatch GROUP BY sizeWidth`

### `getTxtHSizeUnit`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSizeUnit(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT um.code, um.unit AS name FROM unitMast um WHERE um.nature = :nature AND um.unit IN (:inch, :cm, :mm)`
- Params: `:nature` ← `STEnum.UnitNature.LENGTH.getId()`; `:inch` ← `STEnum.UnitMaster.INCH.getName()`; `:cm` ← `STEnum.UnitMaster.CM.getName()`; `:mm` ← `STEnum.UnitMaster.MM.getName()`
- Other calls: `CM.getName`, `INCH.getName`, `LENGTH.getId`, `MM.getName`

### `getTxtHContractualSaleOrder`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHContractualSaleOrder(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `select pi.docId, pi.site_Code,pi.recId As ContractualSaleOrder,pi.v_No, pi.v_Date from ContractualSaleOrder pi LEFT JOIN voucher_Type vt ON vt.v_Type = pi.v_Type where pi.approvedBy <> '' and vt.category = ' {…} '`

### `getTxtHHavingWeight`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHHavingWeight(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHReelNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHReelNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT DISTINCT iis.itemId AS code, bcm.reelBundleNo AS name FROM itemIdStock iis LEFT JOIN barCodeMast bcm ON bcm.barCodeId = iis.itemId WHERE iis.balanceQty > 0 AND iis.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHPartyReelNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHPartyReelNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT DISTINCT iis.itemId AS code, urbd.customerReelNo AS name FROM itemIdStock iis LEFT JOIN unloadingRBDetail urbd ON urbd.barcodeId = iis.itemId WHERE iis.balanceQty > 0 AND ISNULL(urbd.customerReelNo, '') <> '' AND iis.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select dm.subCode as code, sg.name from departmentMast as dm left join subGroup as sg on dm.subCode = sg.subCode left join subGroupNature sgn on sgn.code = sg.nature where 1=1 {…}`
- Other calls: `STServer.getSsnDepartmentCodeList`

## Server `RptRequistionRegister_SrvImpl.java` (892 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.processWjMessageQueue`, `DmServer.saveLog`, `DmServer.showJasper`, `DmShared.convertDate`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.isValidText`, `DmShared.xNull`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT rq.recId,rq.docId,rqd.v_Sno as VSNo, rq.v_Date,sg.name AS subgroup,sdpto.name as departmentTo, vt.short_Name, sdp.name as departmentBy,rqd.reqQty,rqd.reqDate,rqd.reqTime, rqd.remark,CASE rqd.isStatus WHEN 0 THEN 'Open' WHEN 1 THEN 'Cancel' WHEN 2 THEN 'Complete' ELSE 'Open' END as isStatus, im.name as itemName ,um.unit AS sku,um.code skuCode,sm.name as siteCodeToName,rq.site_Code_To as siteCodeTo,rqd.issueQty, Cast(IsNull(rqd.reqQty,0)-(IsNull(rqd.issueQty,0)) as decimal(18,7)) as pendingQuantity,icm.name as colourName,imm.companyName as itemMakeName, ism.name as sizeName , ib.propertyDispCsv , rqd.packSizeCode, psm.name as packSizeName , rqd.packCount , umPsm.unit as packUnitName , rqd.issuePackCount , Cast(IsNull(rqd.packCount,0)-(IsNull(rqd.issuePackCount,0)) as decimal(18,7)) as pendingPackCount from requisition rq left join requisitiondetail as rqd on rqd.docId = rq.docId LEFT JOIN subGroup sg ON sg.subCode = rq.requestedByCode LEFT JOIN subgroup sdp ON sdp.subcode= rq.departmentCode LEFT JOIN subgroup sdpto ON sdpto.subcode = rq.departmentToCode left join voucher_Type vt on vt.v_Type = rq.v_Type LEFT JOIN itemMast im ON im.code = rqd.itemCode LEFT JOIN unitMast um ON um.code = im.purchaseUnit Left join siteMast as sm on sm.code =rq.site_Code_To LEFT JOIN itemBatch ib on ib.code = rqd.ibCode Left join itemColourMast as icm on icm.code =ib.colourCode Left join itemMakeMast as imm on imm.code=ib.itemMakeCode Left join itemSizeMast as ism on ism.code =ib.sizeCode LEFT JOIN packSizeMast psm on psm.code = rqd.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode where vt.category IN ( {…} ) {…} AND ISNULL(rq.approvedBy, '') <> '' ORDER BY rq.v_Date, vt.short_Name, rq.v_No, rq.recId, rqd.v_SNo`
- SQL 2: `SELECT CASE WHEN ISNULL(rqd.itemCode, 0) <> ISNULL(shd.itemCode, 0) THEN 1 ELSE 0 END AS isItemChanged ,CASE WHEN ISNULL(rqd.ibCode, 0) <> ISNULL(shd.ibCode, 0) THEN 1 ELSE 0 END AS isPropertiesChanged, sh.recId AS issueNo, sh.v_Date AS issueDate, isNull(im.name,'') AS issueItem, isNull(icm.name,'') AS issueColour, isNull(ism.name,'') AS issueSize, isNull(ib.propertyDispCsv,'') AS propertyDispCsv , shd.quantity FROM requisition rq LEFT JOIN requisitionDetail rqd ON rqd.docId = rq.docId LEFT JOIN ( SELECT shd.* FROM stockHeadDetail shd left join stockHead as sh on shd.docId=sh.docId left join voucher_type as vt on vt.v_Type=sh.v_type WHERE vt.category IN (' {…} ',' {…} ') AND shd.requisitionDocId = $P{docId} AND shd.requisitionV_SNo = $P{VSNo} ) AS shd ON shd.requisitionDocId = rqd.docId AND shd.requisitionV_SNo = rqd.v_SNo LEFT JOIN stockHead sh ON sh.docId = shd.docId LEFT JOIN itemMast im ON im.code = shd.itemCode LEFT JOIN itemBatch ib on ib.code = shd.ibCode LEFT JOIN itemColourMast icm ON icm.code = ib.colourCode LEFT JOIN itemSizeMast ism ON ism.code = ib.sizeCode WHERE rqd.docId = $P{docId} AND rqd.v_SNo = $P{VSNo}`
- Other calls: `PRINT.getValue`, `STServer.getSsnDepartmentCodeList`, `SYSTEM_ACTIVITY.getName`

### `validateInput`
`public void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter)`

- Errors: "From Date is mandatory." | "To Date is mandatory." | "From Date should be less than or equal to To Date." | "Department By and Department To can't be same."

### `getTxtHItemStatus`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemStatus(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(boolean isFromDepartment, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select dm.subCode as code, sg.name from departmentMast as dm left join subGroup as sg on dm.subCode = sg.subCode left join subGroupNature sgn on sgn.code = sg.nature where 1=1 {…}`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getParameter`
`private String getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`; `:itemStatus` ← `DmShared.xNull(filter.get("itemStatus").getTag())`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getTxtHRequisition`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHRequisition(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode as code,sg.name,sg.manualCode FROM subGroup sg where sg.filterNature=:filterNature`
- Params: `:filterNature` ← `DmServer.getCodeViaManualCode("EM", "manualCode", "subgroupNature", "code", cnTemp)`

### `getTxtHItemName`
`public DmResultPageing<List<TxtHItem_Mdl>> getTxtHItemName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code,im.name,um.unit AS sku,ig.name itemGroup,ic.name itemcategory,im.manualCode FROM itemMast im LEFT JOIN itemGroup ig ON ig.code = im.itemGroup LEFT JOIN itemCat ic ON ic.code = im.itemCategoryCode LEFT JOIN unitMast um ON um.code = im.purchaseUnit WHERE (im.filterItemType = :filterItemType or im.isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHReqType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHReqType(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type AS code , vt.description AS name FROM voucher_Type vt WHERE vt.category IN ( {…} )`

### `getTxtHShowUsage`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHShowUsage(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHRequisitionNo`
`public DmResultPageing<List<TxtHRequisitionNo_Mdl>> getTxtHRequisitionNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select rq.docId, rq.recId as requisitionNo, rq.v_Date as requisitionDate, rq.v_No , vt.short_Name as requisitionVType , CASE WHEN rq.site_Code = {…} THEN 1 ELSE 2 END as tempSr from requisition as rq LEFT JOIN voucher_Type vt ON rq.v_Type=vt.v_Type LEFT JOIN siteMast sm ON sm.code = rq.site_Code where rq.site_Code in( {…} :siteList {…} ) and vt.category IN ( {…} ) and isNull(rq.approvedBy,'') <> ''`

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

### `getTxtHItemNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemNature(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT inm.code, inm.name FROM itemNature inm`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmBase.self.xls`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmReportLayout.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.excelReport.server`, `dataman.dmToolkit.excelReport.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`, `paperbiz.store.inventoryAgeingDisplay.shared`, `paperbiz.store.itemMovementDisplay.shared`, `paperbiz.store.movementAnalysisDisplay.client`, `paperbiz.store.movementAnalysisDisplay.shared`, `paperbiz.store.report.shared`, `paperbiz.store.stockBatch.shared`, `paperbiz.store.stockLedger.shared`, `paperbiz.store.stockSummary.shared`, `paperbiz.store.supplierWiseStockDisplay.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| createExcelReport | `if (!DmSession.getSessions().containsKey(sessionId))` | "Invalid request." |
| createExcelReport | `if (DmShared.xNull(fileName).equalsIgnoreCase(""))` | "File Name not given." |
| createExcelReport | `if (status == false)` | "No record found!" |
| execute | `if (!DmSession.getSessions().containsKey(sessionId))` | "Invalid request." |
| execute | `if (DmShared.xNull(fileName).equalsIgnoreCase(""))` | "File Name not given." |
| createExcelReport | `if (!DmShared.xNull(group2Text).trim().equalsIgnoreCase(""))` | "No record found!" |
| execute | `if (DmShared.convertDate(filter.get("fromDate").getText()).getTime() < DmShared.convertDate(DmServer.getSsnStartDate(this)).getTime())` | "From Date should be greater than or equal to " |
| execute | `if (DmShared.convertDate(filter.get("toDate").getText()).getTime() > DmShared.convertDate(DmServer.getSsnEndDate(this)).getTime())` | "To Date should be less than or equal to " |
| execute | `if (DmShared.iNull(rsTemp.getString("totalPostingEntry")).intValue() > 0)` | "Posting  already exist for selected date." |
| execute | `if (DmShared.iNull(rsTemp.getString("totalPostingEntry")).intValue() == 0)` | "Posting is not exist for selected date." |
| execute | `if (DmShared.iNull(rsTemp.getString("totalClearanceEntry")).intValue() > 0)` | "Clearance already exist for selected date." |
| createExcelReport | (unconditional) | "No record found!" |
| createExcelReport | `if (!DmShared.xNull(group1Text).trim().equalsIgnoreCase(""))` | "No record found!" |
| validateInput | `if (DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase(""))` | "From Date is mandatory." |
| validateInput | `if (DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase(""))` | "To Date is mandatory." |
| validateInput | `if (DmShared.convertDate(hashMapFilter.get("fromDate").getText()).getTime() > DmShared.convertDate(hashMapFilter.get("toDate").getText()).getTime())` | "From Date should be less than or equal to To Date." |
| validateInput | `if (DmShared.xNull(hashMapFilter.get("departmentBy").getText()).equalsIgnoreCase(DmShared.xNull(hashMapFilter.get("departmentTo").getText())))` | "Department By and Department To can't be same." |

## Notes (hand-written)
_No hand-written notes yet._
