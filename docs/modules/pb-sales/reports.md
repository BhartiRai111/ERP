# pb-sales / reports

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/sales/src/reports/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Site (Text); rule: From Date is mandatory.; rule: To Date is mandatory.; rule: From Date should be less than or equal to To Date.; From Date (Text); To Date (Text); 'Summary On' (Text); 'Report For' (Text); Summary Type (Text); rule: Show Services is mandatory.; rule: Report For is mandatory.; rule: Report for is mandatory.; 'Size Unit ' (Text); 'Periodicity' (Text); rule: Inactive Days is mandatory.; rule: Inactive From Date should be between From Date and To Date.; rule: Periodicity is mandatory.; 'Group By' (Text); rule: As On Date is mandatory.; rule: Credit Limit is mandatory.; Report For (Text)
- Lookups/suggestions: getTxtHSite, getTxtHInvoiceNo, getTxtHReturnNo, getTxtHParty, getTxtHGateNo, getTxtHTransporter, getTxtHTransportationMode, getTxtHItemCat, getTxtHItemGroup, getTxtHShowServices, getTxtHEwayBillReq, getTxtHHSN, getTxtHReason, getTxtHRecordType, getTxtHYesNo, getTxtHAccountGroup, getTxtHCustomer, getTxtHVoucherType, getTxtHSaleOrderNo, getTxtHDistributor, getTxtHDeliveryCity, getTxtHSalesman, getTxtHItemType, getTxtHStatus, getTxtHDispatchDetails, getTxtHReportFor, getTxtHItemNature, getTxtHTransactionType, getTxtHFreightType, getTxtHReportType, getTxtHPartyName, getTxtHOrderType, getTxtHBF, getTxtHGSM, getTxtHSize, getTxtHSOrd, getTxtHItem, getTxtHSalesReturnNo, getTxtHItemCategory, getTxtHZone, getTxtHProformaInvoiceNo, getTxtHVendor, getTxtHBf, getTxtHGsm, getTxtHWidth, getTxtHLength, getTxtHGroupOn, getTxtHCustomerName, getTxtHUnit, getTxtHConsignee, getTxtHVoucherNo, getTxtHArea, getHBF, getHGSM, getHPeriodicity, getTxtHPrintProperties, getHSizeUnit, getTxtHApprovalStatus, getTxtHAmendmentOrder, getTxtHPartyType, getTxtHVType, getTxtHPolicyNo
- CSV export: no

## Files
| file | lines |
|---|---|
| client/OrderWiseWIPDisplayReport.java | 678 |
| client/RptCustomerCreditLimitBalanceRegister.java | 277 |
| client/RptDeliveryManageRegister.java | 465 |
| client/RptGSTSaleRegister.java | 383 |
| client/RptInactiveCustomerRegister.java | 568 |
| client/RptMonthlySalesOrderReport.java | 1040 |
| client/RptMonthlySalesReport.java | 1033 |
| client/RptNetSalesSummaryRegister.java | 668 |
| client/RptOrderSummaryRegister.java | 993 |
| client/RptPIRegister.java | 952 |
| client/RptPendingSaleOrder.java | 1186 |
| client/RptSaleOrderStatusRegister.java | 1191 |
| client/RptSalesDetailDisplay.java | 230 |
| client/RptSalesOrderRegister.java | 999 |
| client/RptSalesRegister.java | 1072 |
| client/RptSalesReturnRegister.java | 1011 |
| client/RptSalesSummaryDisplay.java | 230 |
| client/RptSalesSummaryRegister.java | 833 |
| server/OrderWiseWIPDisplayReport_SrvImpl.java | 873 |
| server/RptCustomerCreditLimitBalanceRegister_SrvImpl.java | 835 |
| server/RptDeliveryManageRegister_SrvImp.java | 904 |
| server/RptGSTSaleRegister_SrvImpl.java | 956 |
| server/RptInactiveCustomerRegister_SrvImpl.java | 1452 |
| server/RptMonthlySalesOrderReport_SrvImpl.java | 2383 |
| server/RptMonthlySalesReport_SrvImpl.java | 2392 |
| server/RptNetSalesSummaryRegister_SrvImpl.java | 1519 |
| server/RptOrderSummaryRegister_SrvImpl.java | 1081 |
| server/RptPIRegister_SrvImpl.java | 2197 |
| server/RptPendingSaleOrder_SrvImpl.java | 2498 |
| server/RptSaleOrderStatusRegister_SrvImpl.java | 2387 |
| server/RptSalesDetailDisplay_SrvImpl.java | 90 |
| server/RptSalesOrderRegister_SrvImpl.java | 1136 |
| server/RptSalesRegister_SrvImpl.java | 1322 |
| server/RptSalesReturnRegister_SrvImpl.java | 1305 |
| server/RptSalesSummaryDisplay_SrvImpl.java | 83 |
| server/RptSalesSummaryRegister_SrvImpl.java | 1941 |
| shared/Filter_Mdl.java | 187 |
| shared/OrderWIseDisplayFilter.java | 49 |
| shared/OrderWiseWIPDisplayOrderhelp_Mdl.java | 121 |
| shared/OrderWiseWIPDisplayParameter.java | 139 |
| shared/OrderWiseWIPDisplayReport_Mdl.java | 32 |
| shared/OrderWiseWIPDisplayReport_Srv.java | 39 |
| shared/OrderWiseWIPDisplayReport_SrvAsync.java | 39 |
| shared/OrderWiseWIPDisplay_Mdl.java | 543 |
| shared/RptBarcode_Srv.java | 54 |
| shared/RptBarcode_SrvAsync.java | 54 |
| shared/RptBarcode_Vld.java | 14 |
| shared/RptCustomerCreditLimitBalanceRegister_Srv.java | 26 |
| shared/RptCustomerCreditLimitBalanceRegister_SrvAsync.java | 26 |
| shared/RptCustomerCreditLimitBalanceRegister_Vld.java | 21 |
| shared/RptDeliveryManageRegister_Srv.java | 41 |
| shared/RptDeliveryManageRegister_SrvAsync.java | 41 |
| shared/RptDeliveryManageRegister_Vld.java | 28 |
| shared/RptGSTSaleRegister_Srv.java | 25 |
| shared/RptGSTSaleRegister_SrvAsync.java | 25 |
| shared/RptGSTSaleRegister_Vld.java | 30 |
| shared/RptInactiveCustomerRegister_Srv.java | 39 |
| shared/RptInactiveCustomerRegister_SrvAsync.java | 38 |
| shared/RptInactiveCustomerRegister_Vld.java | 39 |
| shared/RptMonthlySalesOrderReport_Srv.java | 57 |
| shared/RptMonthlySalesOrderReport_SrvAsync.java | 56 |
| shared/RptMonthlySalesOrderReport_Vld.java | 30 |
| shared/RptMonthlySalesReport_Srv.java | 54 |
| shared/RptMonthlySalesReport_SrvAsync.java | 55 |
| shared/RptMonthlySalesReport_Vld.java | 29 |
| shared/RptNetSalesSummaryRegister_Srv.java | 46 |
| shared/RptNetSalesSummaryRegister_SrvAsync.java | 46 |
| shared/RptNetSalesSummaryRegister_Vld.java | 18 |
| shared/RptOrderSummaryRegister_Srv.java | 50 |
| shared/RptOrderSummaryRegister_SrvAsync.java | 50 |
| shared/RptOrderSummaryRegister_Vld.java | 31 |
| shared/RptPIRegister_Srv.java | 52 |
| shared/RptPIRegister_SrvAsync.java | 49 |
| shared/RptPIRegister_Vld.java | 31 |
| shared/RptPendingSaleOrder_Srv.java | 60 |
| shared/RptPendingSaleOrder_SrvAsync.java | 61 |
| shared/RptPendingSaleOrder_Vld.java | 31 |
| shared/RptSaleOrderStatusRegister_Srv.java | 60 |
| shared/RptSaleOrderStatusRegister_SrvAsync.java | 61 |
| shared/RptSaleOrderStatusRegister_Vld.java | 31 |
| shared/RptSalesDetailDisplay_Srv.java | 15 |
| shared/RptSalesDetailDisplay_SrvAsync.java | 15 |
| shared/RptSalesOrderRegister_Srv.java | 46 |
| shared/RptSalesOrderRegister_SrvAsync.java | 46 |
| shared/RptSalesOrderRegister_Vld.java | 30 |
| shared/RptSalesRegister_Srv.java | 54 |
| shared/RptSalesRegister_SrvAsync.java | 54 |
| shared/RptSalesRegister_Vld.java | 29 |
| shared/RptSalesReturnRegister_Srv.java | 52 |
| shared/RptSalesReturnRegister_SrvAsync.java | 52 |
| shared/RptSalesReturnRegister_Vld.java | 27 |
| shared/RptSalesSummaryDisplay_Srv.java | 15 |
| shared/RptSalesSummaryDisplay_SrvAsync.java | 15 |
| shared/RptSalesSummaryRegister_Srv.java | 50 |
| shared/RptSalesSummaryRegister_SrvAsync.java | 50 |
| shared/RptSalesSummaryRegister_Vld.java | 29 |
| shared/TxtHAccountName.java | 40 |
| shared/TxtHAmendmentOrderNo_Mdl.java | 59 |
| shared/TxtHAmendmentOrder_Mdl.java | 41 |
| shared/TxtHCommissionNo_Mdl.java | 41 |
| shared/TxtHDealerName_Mdl.java | 58 |
| shared/TxtHGateNo_Mdl.java | 32 |
| shared/TxtHHSN_Mdl.java | 22 |
| shared/TxtHInvoiceNo_Mdl.java | 68 |
| shared/TxtHItem_Mdl.java | 69 |
| shared/TxtHParty_Mdl.java | 31 |
| shared/TxtHPolicyNo_Mdl.java | 50 |
| shared/TxtHRecId_Mdl.java | 60 |
| shared/TxtHReturnNo_Mdl.java | 87 |
| shared/TxtHSalesOrderNo_Mdl.java | 42 |
| shared/TxtHSalesOrder_Mdl.java | 59 |
| shared/TxtHShift_Mdl.java | 39 |
| shared/TxtHVoucherNo_Mdl.java | 169 |
| shared/TxtHVoucher_Mdl.java | 23 |
| shared/TxtHWeighmentNo_Mdl.java | 41 |
| shared/TxtHWidth_Mdl.java | 33 |
| shared/TxtTransporters_Mdl.java | 60 |
| shared/proformaInvoiceNo_Mdl.java | 41 |

## Models (shared DTOs)
### TxtHVoucherNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | voucherType |
| String | voucherDate |
| String | partyName |
| String | partyNameWithCityName |
| String | shortName |
| String | partyCity |
| String | distributorName |
| String | consigneeName |
| String | partyOrderNo |
| String | partyOrderDate |

### TxtHVoucher_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | shortName |

### TxtHReturnNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | refDate |
| String | refNo |
| String | date |
| String | party |
| String | city |
| String | voucherType |
| String | voucherDate |
| String | shortName |

### TxtHAmendmentOrderNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | aoType |
| String | aoDate |
| String | partyName |
| String | distributorName |
| String | consigneeName |

### TxtHAmendmentOrder_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | v_No |
| String | v_Date |
| String | partyName |

### TxtHHSN_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | hsn |

### OrderWiseWIPDisplay_Mdl
| type | field |
|---|---|
| String | bfcode |
| String | bfname |
| String | bundlebalanceweight |
| String | bundleinputweight |
| String | bundleoutputweight |
| String | consigneecode |
| String | consineename |
| String | delverydate |
| String | dispatchweight |
| String | grain |
| String | graincode |
| String | gsmcode |
| String | gsmname |
| String | itemcode |
| String | itemname |
| String | jumborollinputweight |
| String | jumborolloutputbalanceweight |
| String | jumborolloutputweight |
| String | orderdate |
| String | orderdocID |
| String | orderno |
| Integer | orderv_sno |
| String | orderv_type |
| String | orderweight |
| String | partycode |
| String | partyname |
| String | partyorderdate |
| String | partyorderno |
| String | plandate |
| String | plandocid |
| String | planno |
| String | reelrewinderbalanceweight |
| String | reelrewinderinputweight |
| String | reelrewinderoutputweight |
| String | rimbalanceweight |
| String | riminputweight |
| String | rimmergingbalanceweight |
| String | rimmerginginputweight |
| String | rimmergingoutputweight |
| String | rimoutputweight |
| String | rimreprocessbalanceweight |
| String | rimreprocessinputweight |
| String | rimreprocessoutputweight |
| String | rollplanweight |
| String | saletype |
| String | saletypecode |
| String | sheeterbalanceweight |
| String | sheeterinputweight |
| String | sheeteroutputweight |
| String | sheetrewinderbalanceweight |
| String | sheetrewinderinputweight |
| String | sheetrewinderoutputweight |
| String | size |
| String | sizeheight |
| String | sizereel |
| String | sizeunit |
| String | stockweight |
| String | transferweight |
| String | variety |

### TxtHGateNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | gateDate |
| String | partyName |

### TxtHItem_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | itemGroup |
| String | itemCategory |
| String | unit |
| String | unitCode |
| String | manualCode |
| String | itemType |

### TxtHCommissionNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | commissionType |
| String | fromMonth |
| String | toMonth |

### TxtHShift_Mdl (extends `KeyField implements IsSerializable`)
| type | field |
|---|---|
| String | fromTime |
| String | toTime |
| String | manualCode |

### OrderWiseWIPDisplayReport_Mdl
| type | field |
|---|---|
| String | reportType |
| List<OrderWiseWIPDisplay_Mdl> | orderWiseWIPDisplay_Mdls |

### TxtHDealerName_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | dealerCode |
| String | dealerName |
| String | dealerManualCode |
| String | dealerCityName |
| String | dealerGSTINo |

### TxtHWidth_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | widthNo |
| String | lengthNo |

### Filter_Mdl
| type | field |
|---|---|
| String | reportType |
| String | fromDate |
| String | toDate |
| String | partyCode |
| String | consigneeCode |
| String | asOnDate |
| String | orderDocId |
| String | itemCode |
| String | bf |
| String | gsm |
| String | orderType |
| String | size |
| String | status |
| String | day1 |
| String | day2 |
| String | day3 |
| String | day4 |
| String | day5 |
| String | day6 |

### TxtTransporters_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | stateCode |
| String | stateName |
| String | manualCode |
| String | cityName |
| String | partyType |

### TxtHRecId_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | invoiceType |
| String | invoiceDate |
| String | partyName |
| String | distributorName |
| String | consigneeName |

### TxtHSalesOrderNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | v_Date |
| String | v_Time |
| String | shortName |

### TxtHParty_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityName |

### TxtHInvoiceNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | invoiceType |
| String | invoiceDate |
| String | partyName |
| String | city |
| String | distributorName |
| String | consigneeName |

### TxtHSalesOrder_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | soType |
| String | soDate |
| String | partyName |
| String | distributorName |
| String | consigneeName |

### OrderWiseWIPDisplayOrderhelp_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | tick |
| String | orderdocid |
| String | orderno |
| String | orderdate |
| String | partycode |
| String | partyname |
| String | consigneecode |
| String | consigneename |
| String | distributorcode |
| String | distributorname |
| String | partyOrdNo |
| String | partyOrdDate |

### TxtHWeighmentNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | v_Date |
| String | v_Time |
| String | short_Name |

### TxtHPolicyNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | insuranceComp |
| String | policyNo |
| String | dateFrom |
| String | dateTo |

### proformaInvoiceNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | proformaInvoiceNo |
| String | proformaDate |
| String | partyName |

## RPC interface
| method | params | returns |
|---|---|---|
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVoucherType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucher_Mdl>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getTxtHSaleOrderNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHSalesOrder_Mdl>> |
| getTxtHParty | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getTxtHDeliveryCity | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHApprovalStatus | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHReportFor | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getItemName | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getTxtHStatus | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getGroupName | String excludeGroup, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHZone | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDistributor | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |

## RPC interface
| method | params | returns |
|---|---|---|
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVoucherType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucherNo_Mdl>> |
| getTxtHSaleOrderNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucherNo_Mdl>> |
| getTxtHParty | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getTxtHItemNature | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSalesman | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getTxtHDeliveryCity | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHProformaInvoiceNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemCat | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getItemName | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getTxtHStatus | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getGroupName | String excludeGroup, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<KeyFieldExtended> |
| getReportFor | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHInvoiceNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHInvoiceNo_Mdl>> |
| getTxtHParty | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getTxtHDeliveryCity | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGateNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTransporter | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtTransporters_Mdl>> |
| getTxtHTransportationMode | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getItemName | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getTxtHItemCat | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPolicyNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHShowServices | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHEwayBillReq | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getGroupName | String excludeGroup, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getTxtHHSN | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHHSN_Mdl>> |
| getTxtHRecordType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVoucherType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucherNo_Mdl>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getTxtHSaleOrderNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucherNo_Mdl>> |
| getTxtHParty | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getTxtHDistributor | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getTxtHDeliveryCity | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSalesman | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getxtHProformaInvoiceNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getItemName | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getTxtHItemCat | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHStatus | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getGroupName | String excludeGroup, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHYesNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHReportFor | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemNature | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDispatchDetails | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTransactionType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHFreightType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, RptSalesSummaryDisplay_Mdl salesSummaryPrint_Mdl | DmResult<String> |

## RPC interface
| method | params | returns |
|---|---|---|
| execute | String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<KeyFieldExtended> |
| getTxtHAccountGroup | DmHelper helper, boolean blnToEndOfFile, String strReportFor | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHYesNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHCustomer | String creditLimitExist, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHInvoiceNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHInvoiceNo_Mdl>> |
| getTxtHParty | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getTxtHGateNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTransporter | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtTransporters_Mdl>> |
| getTxtHTransportationMode | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getItemName | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getTxtHItemCat | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHShowServices | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHEwayBillReq | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getGroupName | String excludeGroup, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getTxtHHSN | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHHSN_Mdl>> |
| getTxtHReturnNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHReturnNo_Mdl>> |
| getTxtHReason | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHRecordType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| execute | String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<KeyFieldExtended> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVoucherType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucher_Mdl>> |
| getTxtHVoucherNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucherNo_Mdl>> |
| getTxtHParty | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getTxtHConsignee | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getItemName | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getTxtHItemGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemCategory | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getReportFor | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getGroupBy | String tag1, String tag2, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSalesman | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getTxtHArea | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getHBF | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getHGSM | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getHPeriodicity | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPrintProperties | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getHSizeUnit | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| execute | String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<KeyFieldExtended> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVoucherType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucher_Mdl>> |
| getTxtHVoucherNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucherNo_Mdl>> |
| getTxtHParty | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getTxtHConsignee | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getItemName | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getTxtHItemGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemCategory | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getReportFor | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getGroupBy | String tag1, String tag2, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSalesman | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getTxtHArea | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getHBF | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getHGSM | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getHPeriodicity | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPrintProperties | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getHSizeUnit | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVoucherType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucherNo_Mdl>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getTxtHSaleOrderNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucherNo_Mdl>> |
| getTxtHParty | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getTxtHDistributor | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getTxtHDeliveryCity | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSalesman | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getxtHProformaInvoiceNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getItemName | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getTxtHItemCat | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHStatus | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getGroupName | String excludeGroup, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHYesNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHReportType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHReportFor | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemNature | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTransactionType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHFreightType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

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
| getTxtHHavingWeight | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDetailSummary | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| execute | String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<KeyFieldExtended> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHParty | String partyTypeCode, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPartyType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getReportFor | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |

## RPC interface
| method | params | returns |
|---|---|---|
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| execute | String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<KeyFieldExtended> |
| getTxtHInvoiceNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHInvoiceNo_Mdl>> |
| getTxtHSalesReturnNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHReturnNo_Mdl>> |
| getTxtHParty | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getTxtHDistributor | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getItemName | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getTxtHItemGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemCategory | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHZone | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHArea | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVoucherType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucher_Mdl>> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHYesNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getGroupBy | String tag1, String tag2, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getReportFor | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getSummaryType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| execute | String startDate, String endDate, String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<KeyFieldExtended> |
| getTxtHInvoiceNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHInvoiceNo_Mdl>> |
| getTxtHSalesReturnNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHReturnNo_Mdl>> |
| getTxtHParty | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getTxtHDistributor | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getItemName | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getTxtHItemGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemCategory | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHZone | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVoucherType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucher_Mdl>> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getGroupBy | String summaryOnTag, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getReportFor | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getSummaryType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| execute | String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<KeyFieldExtended> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHParty | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItem | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getTxtHItemCat | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDeliveryCity | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemNature | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getReportFor | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getPerioditicity | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |

## RPC interface
| method | params | returns |
|---|---|---|
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVoucherType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucher_Mdl>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getTxtHSaleOrderNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHSalesOrder_Mdl>> |
| getTxtHParty | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getTxtHDeliveryCity | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAmendmentOrder | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHAmendmentOrderNo_Mdl>> |
| getItemName | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getTxtHItemCat | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHStatus | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getGroupName | String excludeGroup, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHYesNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHReportFor | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| getReport | LinkedHashMap<String, FieldUDT_Mdl> filter | DmResult<OrderWiseWIPDisplayReport_Mdl> |
| getTxtHReportType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPartyName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| exportToPdf | String fileName, String html, String width | DmResult<String> |
| exportToExcel | String fileName, String html | DmResult<String> |
| getTxtHStatus | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHOrderType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHBF | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGSM | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSize | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSOrd | String partyCode, String consigneeCOde, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<OrderWiseWIPDisplayOrderhelp_Mdl>> |
| getTxtHItem | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |

## RPC interface
| method | params | returns |
|---|---|---|
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, RptSalesDetailDisplay_Mdl salesDetailPrint_Mdl | DmResult<String> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVendor | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
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
| getTxtHConsignee | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getTxtHSaleOrderNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucherNo_Mdl>> |

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

## Validator `RptSalesSummaryRegister_Vld`
```java
public class RptSalesSummaryRegister_Vld {
    public static void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter) {
        DmShared.isValidText(hashMapFilter.get("fromDate").getText(), "From Date", 200);
        DmShared.isValidText(hashMapFilter.get("toDate").getText(), "To Date", 200);
        if ((!DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) && (!DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase(""))) {
            if (DmShared.convertDate(hashMapFilter.get("fromDate").getText()).getTime() > DmShared.convertDate(hashMapFilter.get("toDate").getText()).getTime()) {
                throw new DmException(100, "From Date should be less than or equal to To Date.");
            }
        }
        DmShared.isValidText(hashMapFilter.get("site").getTag(), "Site", 200);
        if (DmShared.xNull(hashMapFilter.get("showServices").getText()).equalsIgnoreCase("")) {
            throw new DmException(300, "Show Services is mandatory.");
        }
        DmShared.isValidText(hashMapFilter.get("summaryOn").getTag(), "'Summary On'", 400);
        DmShared.isValidText(hashMapFilter.get("reportFor").getTag(), "'Report For'", 500);
        DmShared.isValidText(hashMapFilter.get("summaryType").getTag(), "Summary Type", 600);
    }
}
```

## Validator `RptPendingSaleOrder_Vld`
```java
public class RptPendingSaleOrder_Vld {
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
        if ((!DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) && (!DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase(""))) {
            if (DmShared.convertDate(hashMapFilter.get("fromDate").getText()).getTime() > DmShared.convertDate(hashMapFilter.get("toDate").getText()).getTime()) {
                throw new DmException(100, "From Date should be less than or equal to To Date.");
            }
        }
    }
}
```

## Validator `RptPIRegister_Vld`
```java
public class RptPIRegister_Vld {
    public static void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter) {
        if (DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(20, "From Date is mandatory.");
        }
        if (DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(40, "To Date is mandatory.");
        }
        if (DmShared.xNull(hashMapFilter.get("reportFor").getText()).equalsIgnoreCase("")) {
            throw new DmException(60, "Report for is mandatory.");
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

## Validator `RptSalesReturnRegister_Vld`
```java
public class RptSalesReturnRegister_Vld {
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

## Validator `RptMonthlySalesOrderReport_Vld`
```java
public class RptMonthlySalesOrderReport_Vld {
    public static void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter) {
        DmShared.isValidText(hashMapFilter.get("fromDate").getText(), "From Date", 200);
        DmShared.isValidText(hashMapFilter.get("toDate").getText(), "To Date", 200);
        if ((!DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) && (!DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase(""))) {
            if (DmShared.convertDate(hashMapFilter.get("fromDate").getText()).getTime() > DmShared.convertDate(hashMapFilter.get("toDate").getText()).getTime()) {
                throw new DmException(100, "From Date should be less than or equal to To Date.");
            }
        }
        DmShared.isValidText(hashMapFilter.get("site").getTag(), "Site", 200);
        DmShared.isValidText(hashMapFilter.get("sizeUnit").getTag(), "'Size Unit '", 300);
        DmShared.isValidText(hashMapFilter.get("summaryOn").getTag(), "'Summary On'", 400);
        DmShared.isValidText(hashMapFilter.get("reportFor").getTag(), "'Report For'", 500);
        DmShared.isValidText(hashMapFilter.get("periodicity").getTag(), "'Periodicity'", 600);
    }
}
```

## Validator `RptSalesRegister_Vld`
```java
public class RptSalesRegister_Vld {
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

## Validator `RptMonthlySalesReport_Vld`
```java
public class RptMonthlySalesReport_Vld {
    public static void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter) {
        DmShared.isValidText(hashMapFilter.get("fromDate").getText(), "From Date", 200);
        DmShared.isValidText(hashMapFilter.get("toDate").getText(), "To Date", 200);
        if ((!DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) && (!DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase(""))) {
            if (DmShared.convertDate(hashMapFilter.get("fromDate").getText()).getTime() > DmShared.convertDate(hashMapFilter.get("toDate").getText()).getTime()) {
                throw new DmException(100, "From Date should be less than or equal to To Date.");
            }
        }
        DmShared.isValidText(hashMapFilter.get("site").getTag(), "Site", 200);
          DmShared.isValidText(hashMapFilter.get("sizeUnit").getTag(), "'Size Unit '", 300);
        DmShared.isValidText(hashMapFilter.get("summaryOn").getTag(), "'Summary On'", 400);
        DmShared.isValidText(hashMapFilter.get("reportFor").getTag(), "'Report For'", 500);
        DmShared.isValidText(hashMapFilter.get("periodicity").getTag(), "'Periodicity'", 600);
    }
}
```

## Validator `RptInactiveCustomerRegister_Vld`
```java
public class RptInactiveCustomerRegister_Vld {
    public static void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter) {
        if (DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(100, "From Date is mandatory.");
        }
        if (DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(200, "To Date is mandatory.");
        }
        if (DmShared.xNull(hashMapFilter.get("inactiveDays").getText()).equalsIgnoreCase("")) {
            throw new DmException(300, "Inactive Days is mandatory.");
        }
        if ((!DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) && (!DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase(""))) {
            if (DmShared.convertDate(hashMapFilter.get("fromDate").getText()).getTime() > DmShared.convertDate(hashMapFilter.get("toDate").getText()).getTime()) {
                throw new DmException(400, "From Date should be less than or equal to To Date.");
            }
        }
        if ((!DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) && (!DmShared.dNull(hashMapFilter.get("inactiveFromDate").getText()).equalsIgnoreCase(""))) {
            if (DmShared.convertDate(hashMapFilter.get("fromDate").getText()).getTime() > DmShared.convertDate(hashMapFilter.get("inactiveFromDate").getText()).getTime()) {
                throw new DmException(500, "Inactive From Date should be between From Date and To Date.");
            }
        }
        DmShared.isValidText(hashMapFilter.get("site").getTag(), "Site", 600);
        if (DmShared.xNull(hashMapFilter.get("periodicity").getText()).equalsIgnoreCase("")) {
            throw new DmException(700, "Periodicity is mandatory.");
        }
    }
}
```

## Validator `RptSalesOrderRegister_Vld`
```java
public class RptSalesOrderRegister_Vld {
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

## Validator `RptNetSalesSummaryRegister_Vld`
```java
public class RptNetSalesSummaryRegister_Vld {
    public static void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter) {
        DmShared.isValidText(hashMapFilter.get("site").getTag(), "Site", 200);
        DmShared.isValidText(hashMapFilter.get("summaryType").getTag(), "Summary Type", 300);
        DmShared.isValidText(hashMapFilter.get("group2").getTag(), "'Group By'", 400);
    }
}
```

## Validator `RptSaleOrderStatusRegister_Vld`
```java
public class RptSaleOrderStatusRegister_Vld {
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
        if ((!DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) && (!DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase(""))) {
            if (DmShared.convertDate(hashMapFilter.get("fromDate").getText()).getTime() > DmShared.convertDate(hashMapFilter.get("toDate").getText()).getTime()) {
                throw new DmException(100, "From Date should be less than or equal to To Date.");
            }
        }
    }
}
```

## Validator `RptCustomerCreditLimitBalanceRegister_Vld`
```java
public class RptCustomerCreditLimitBalanceRegister_Vld {
     public static void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter) {
        if (DmShared.dNull(hashMapFilter.get("asOnDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(20, "As On Date is mandatory.");
        }
        if (DmShared.xNull(hashMapFilter.get("creditLimitExist").getText()).equalsIgnoreCase("")) {
            throw new DmException(40, "Credit Limit is mandatory.");
        }
    }
}
```

## Validator `RptOrderSummaryRegister_Vld`
```java
public class RptOrderSummaryRegister_Vld {
    public static void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter) {
        if (DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(20, "From Date is mandatory.");
        }
        if (DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(40, "To Date is mandatory.");
        }
        if (DmShared.xNull(hashMapFilter.get("reportFor").getText()).equalsIgnoreCase("")) {
            throw new DmException(40, "Report For is mandatory.");
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

## Validator `RptGSTSaleRegister_Vld`
```java
public class RptGSTSaleRegister_Vld {
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
        DmShared.isValidText(hashMapFilter.get("reportFor").getTag(), "Report For", 400);
    }
}
```

## Validator `RptBarcode_Vld`
```java
public class RptBarcode_Vld {
    public static void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter) {
    }
}
```

## Server `RptSalesSummaryDisplay_SrvImpl.java` (83 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmServer.saveLog`, `DmServer.showJasper`, `DmShared.xNull`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, RptSalesSummaryDisplay_Mdl salesSummaryPrint_Mdl)`

- SQL 1: `SELECT sb.orderIndex, sb.fromDate, sb.toDate, sb.particular, sb.netSales, sb.cumulativeTotal FROM {…} AS sb`
- Other calls: `PRINT.getValue`, `SYSTEM_ACTIVITY.getName`

### `getTable`
`private String getTable(DmRSSPrivate rssPrivate)`


## Server `RptSalesDetailDisplay_SrvImpl.java` (90 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmServer.saveLog`, `DmServer.showJasper`, `DmShared.xNull`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, RptSalesDetailDisplay_Mdl salesDetailPrint_Mdl)`

- SQL 1: `SELECT sb.site_Code, sb.orderIndex, sb.docId, sb.recId , sb.v_No, sb.v_Prefix, sb.v_Type, sb.v_date, sb.short_Name, sb.party , sb.refNo, sb.refDate, vt.mnuAttachedInModule, vt.mnuText , oh_Amt_Net AS salesAmt, sb.cumulativeTotal FROM {…} sb LEFT JOIN voucher_Type vt ON vt.v_Type = sb.v_Type`
- Other calls: `PRINT.getValue`, `SYSTEM_ACTIVITY.getName`

### `getTable`
`private String getTable(DmRSSPrivate rssPrivate)`


## Server `RptSalesReturnRegister_SrvImpl.java` (1305 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.saveLog`, `DmServer.showJasper`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.xNull`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHInvoiceNo`
`public DmResultPageing<List<TxtHInvoiceNo_Mdl>> getTxtHInvoiceNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select sb.docId as code, sb.recId as name ,sb.v_Date as invoiceDate, sb.v_No ,sgp.name as partyName , vt.short_Name as invoiceType , c.cityName as city , CASE WHEN sb.site_Code = {…} THEN 1 ELSE 2 END as tempSr from sBill sb LEFT JOIN voucher_Type vt ON vt.v_Type = sb.v_Type LEFT JOIN subGroup sgp ON sgp.subCode = sb.partyCode left join city as c on c.cityCode = sgp.cityCode LEFT JOIN siteMast sm ON sm.code = sb.site_Code WHERE vt.category IN (' {…} ',' {…} ', ' {…} ',' {…} ', ' {…} ') and sb.site_Code in( {…} :siteList {…} )`

### `getTxtHReturnNo`
`public DmResultPageing<List<TxtHReturnNo_Mdl>> getTxtHReturnNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select sb.docId as code , sb.recId as name ,sb.v_Date as date, sb.v_No , sb.refNo ,sb.refDate ,sgp.name as partyName , vt.short_Name as voucherType , c.cityName as city , CASE WHEN sb.site_Code = {…} THEN 1 ELSE 2 END as tempSr from sBill sb LEFT JOIN voucher_Type vt ON vt.v_Type = sb.v_Type LEFT JOIN subGroup sgp ON sgp.subCode = sb.partyCode LEFT JOIN siteMast sm ON sm.code = sb.site_Code left join city as c on c.cityCode = sgp.cityCode WHERE vt.category IN (' {…} ',' {…} ', ' {…} ',' {…} ') and sb.site_Code in( {…} :siteList {…} )`

### `getTxtHParty`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHParty(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as supplierName , sg.manualCode , c.cityName FROM subGroup sg LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( SELECT DISTINCT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_Type = acf.v_Type WHERE vt.category IN (' {…} ',' {…} '' {…} ',' {…} ') )`

### `getTxtHGateNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGateNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select gio.docId as gateDocId ,gio.recId ,gio.v_Date as gateInDate ,gio.v_Time as gateInTime ,vt.short_Name ,gio.v_No , CASE WHEN gio.site_Code = {…} THEN 1 ELSE 2 END as tempSr from gateInOut gio left join voucher_type vt on vt.v_Type= gio.v_Type LEFT JOIN siteMast sm ON sm.code = gio.site_Code where gio.site_Code in( {…} :siteList {…} )`

### `getTxtHTransporter`
`public DmResultPageing<List<TxtTransporters_Mdl>> getTxtHTransporter(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code, sg.name, sg.manualCode, c.cityName FROM subGroup sg LEFT JOIN city c ON c.cityCode = sg.cityCode WHERE sg.nature = :nature`
- Params: `:nature` ← `DmServer.getCodeViaManualCode("TP", "manualCode", "subGroupNature", "code", cnTemp)`

### `getTxtHTransportationMode`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTransportationMode(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `TransportMode.values`

### `getItemName`
`public DmResultPageing<List<TxtHItem_Mdl>> getItemName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select im.code as itemCode, im.name as itemName ,im.manualCode ,it.name as itemTypeName from itemMast im LEFT JOIN unitmast um ON im.saleUnit = um.code Left join itemType it on it.code=im.itemType where (im.filterItemType IN (:FM,:SM) OR im.itemType = :SV)`
- Params: `:FM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:SM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`; `:SV` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SV", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemCat`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemCat(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ic.code , ic.name FROM itemCat ic WHERE (ic.filterItemType = :filterItemType or ic.isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ig.code , ig.name FROM itemGroup ig WHERE (ig.filterItemType = :filterItemType or ig.isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHShowServices`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHShowServices(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHEwayBillReq`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHEwayBillReq(DmHelper helper, boolean blnToEndOfFile)`


### `getGroupName`
`public DmResultPageing<List<KeyFieldExtended>> getGroupName(String excludeGroup, DmHelper helper, boolean blnToEndOfFile)`


### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT {…} as group1Code , {…} as group1Name , {…} as group2Code , {…} as group2Name ,sb.docId, sb.v_Type, vt.description AS voucherDescriptionName, vt.short_Name AS v_shortName, sb.recId AS salesReturnNo, sb.v_Date , ptip.name AS partyName, ptid.name AS distributorName, sb.remark, sb.refDate, sb.refNo, sb.vehicleNo, ptitc.name AS transporterName, sb.lrNo, sb.lrDate, sb.isEWayBillRequired, sb.eWayBillDt, sb.eWayBillNo, gtio.recId AS gateNo, im.name AS itemName, sbd.itemCode, sbd.quantity saleQty, sbd.skuQty, um1.unit AS skuUnitName, im.itsBasedOn itsBasedOn, um2.unit AS saleUnitName, sgdc.name AS departmentName, sbd.rate, sbd.oh_Amt_Gross, sbd.landedSkuValue, Case When sb.transportMode = {…} then ' {…} ' When sb.transportMode = {…} then ' {…} ' When sb.transportMode = {…} then ' {…} ' When sb.transportMode = {…} then ' {…} ' ELSE '' END AS transportModeName, ptip.cityName AS partyCityName, ptid.cityName AS distributorCityName, ptip.stateName AS partyStateName, ptid.stateName AS distributorStateName, ISNULL( sbd.oh_Amt_CGST ,0) as oh_Amt_CGST , ISNULL( sbd.oh_Amt_IGST ,0) as oh_Amt_IGST, ISNULL(sbd.oh_Amt_SGST ,0) as oh_Amt_SGST, ISNULL(sbd.oh_Amt_UGST ,0) as oh_Amt_UGST, ISNULL(sbd.oh_Amt_Discount, 0) as oh_Amt_Discount , ISNULL(sbd.oh_Amt_Net ,0) as oh_Amt_Net , sbd.oh_Amt_Taxable , sbl.recId as invoiceNo, sbl.v_date as invoiceDate, im.itemDesc, hm.name as hsnName, hm.hsn , ib.propertyDispCsv , umc.unit as cessNaUnitName , CASE WHEN ISNULL(sbd.cessEvalTypeCode,0)= {…} THEN ' {…} ' WHEN ISNULL(sbd.cessEvalTypeCode,0)= {…} THEN ' {…} ' WHEN ISNULL(sbd.cessEvalTypeCode,0)= {…} THEN ' {…} ' end as cessEvalTypeName , ISNULL(sbd.oh_Amt_CessNa, 0) AS oh_Amt_CessNa , ISNULL(sbd.oh_At_CessNa, 0) AS oh_At_CessNa , ISNULL(sbd.oh_Amt_Cess, 0) AS oh_Amt_Cess , ISNULL(sbd.oh_At_Cess, 0) AS oh_At_Cess , lm.name as locationName , sb.isItc , sbd.packCount , sbd.packSizeCode , ISNULL(sbd.oh_Amt_SchemeDiscount ,0) as oh_Amt_SchemeDiscount , sbd.freeQty , psm.name as packSizeName,umpsm.unit as packUnitName , ISNULL(sb.oh_Amt_Deduction,0) as deductuionAmtHead , sbd.ded1_Head ,sbd.ded2_Head,sbd.ded3_Head,sbd.ded4_Head,sbd.ded5_Head , sbd.ded1_Per ,sbd.ded2_Per,sbd.ded3_Per,sbd.ded4_Per,sbd.ded5_Per , sbd.ded1_Amt ,sbd.ded2_Amt,sbd.ded3_Amt,sbd.ded4_Amt,sbd.ded5_Amt , ISNULL(sbd.oh_Amt_Deduction,0) as deductionAmtLine , sb.oh_Amt_FinalDiscount FROM sbill sb {…} LEFT JOIN voucher_Type vt ON vt.v_Type = sb.v_Type LEFT JOIN sBillDetail sbd on sbd.docId = sb.docId LEFT JOIN partyTransInfo ptip ON ptip.docId = sb.docId AND ptip.recordType = :partyRecordType LEFT JOIN partyTransInfo ptid ON ptid.docId = sb.docId AND ptid.recordType = :distributorRecordType LEFT JOIN partyTransInfo ptitc ON ptitc.docId = sb.docId AND ptitc.recordType = :transporterRecordType LEFT JOIN gateInOut gtIo ON gtIo.docId = sb.gateDocId LEFT JOIN itemBatch ib ON ib.code = sbd.ibCode LEFT JOIN itemMast im ON im.code = sbd.itemCode LEFT JOIN itemCat ic ON ic.code = im.itemCategoryCode LEFT JOIN itemGroup ig ON ig.code = im.itemGroup LEFT JOIN unitMast um1 ON um1.code = im.sku LEFT JOIN unitMast um2 ON um2.code = im.saleUnit LEFT JOIN subGroup sgdc ON sgdc.subCode = sbd.departmentCode left join hsnMast as hm on hm.code=sbd.hsnCode left Join unitMast umc on umc.code = sbd.cessNaUnitCode left Join locationMast lm on lm.code = sb.locationCode left join packSizeMast psm on psm.code=sbd.packSizeCode left join unitMast umpsm on umpsm.code=psm.packUnitCode LEFT JOIN (SELECT max(sbd.sbDocId) as sbDocId, sbd.docId from sBillDetail sbd group by docId ) vsbd on vsbd.docId = sb.docId LEFT join sBill sbl on sbl.docId = vsbd.sbDocId WHERE vt.category IN (' {…} ',' {…} '' {…} ',' {…} ') {…} ORDER BY {…} {…} , {…} {…} {…} , {…} {…} {…} sb.v_Date, vt.short_Name, sb.v_No, sb.recId, sbd.v_SNo`
- Params: `:partyRecordType` ← `DmEnum.PartyTransRecordType.PARTY.getCode()`; `:distributorRecordType` ← `DmEnum.PartyTransRecordType.DISTRIBUTOR.getCode()`; `:transporterRecordType` ← `DmEnum.PartyTransRecordType.TRANSPORTER.getCode()`
- Other calls: `BY_AIR.getId`, `BY_AIR.getValue`, `BY_RAIL.getId`, `BY_RAIL.getValue`, `BY_ROAD.getId`, `BY_ROAD.getValue`, `BY_SHIP.getId`, `BY_SHIP.getValue`, `DISTRIBUTOR.getCode`, `PARTY.getCode`, `PRINT.getValue`, `QUANTITY_BASED.getCode`, `QUANTITY_BASED.getName`, `QUANTITY_VALUE_BASED.getCode`, `QUANTITY_VALUE_BASED.getName`, `RptSalesReturnRegister_Vld.validateInput`, `SYSTEM_ACTIVITY.getName`, `TRANSPORTER.getCode`, `VALUE_BASED.getCode`, `VALUE_BASED.getName`

### `getParameter`
`private void getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`

### `getTxtHHSN`
`public DmResultPageing<List<TxtHHSN_Mdl>> getTxtHHSN(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select hm.code, hm.name, hm.hsn from hsnMast hm`

### `getTxtHReason`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHReason(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT code, name FROM gstReasonMast`

### `getTxtHRecordType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHRecordType(DmHelper helper, boolean blnToEndOfFile)`


## Server `RptCustomerCreditLimitBalanceRegister_SrvImpl.java` (835 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnId`, `DmServer.getSsnSiteCode`, `DmServer.removeAllCharacterExceptAlphaNumeric`, `DmServer.setReportHeaderExcel`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `execute`
`public DmResult<KeyFieldExtended> execute(String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT sg.subCode, sg.name, sg.manualCode, sg.add1, sg.add2, sg.pin ,CONCAT(sg.add1,' ',sg.add2,' ',sg.pin,' ',c.cityName) AS address, c.cityName, sm.name AS stateName, ct.name AS countryName , sg.mobile, sg.eMail , IIF(ISNULL(sg.creditLimit, 0) = 0, 'No', 'Yes') AS isCreditLimitDefined , sg.creditLimit, ABS(vLg.balanceAmt) AS balanceAmt , CASE WHEN ISNULL(vLg.balanceAmt, 0) > 0 THEN 'Dr' WHEN ISNULL(vLg.balanceAmt, 0) < 0 THEN 'Cr' ELSE '' END AS balanceType , CASE WHEN ISNULL(sg.creditLimit, 0) = 0 THEN 0 ELSE CASE WHEN ISNULL(vLg.balanceAmt, 0) > 0 THEN ISNULL(sg.creditLimit, 0) - ISNULL(vLg.balanceAmt, 0) ELSE ISNULL(sg.creditLimit, 0) END END AS balanceCreditLimitAmt , CASE WHEN ISNULL(vLg.balanceAmt, 0) < 0 THEN ABS(ISNULL(vLg.balanceAmt, 0)) ELSE 0 END AS advanceAmt , CAST(CASE WHEN ISNULL(sg.creditLimit, 0) = 0 THEN 0 ELSE (CASE WHEN ISNULL(vLg.balanceAmt, 0) > 0 THEN ISNULL(sg.creditLimit, 0) - ISNULL(vLg.balanceAmt, 0) ELSE ISNULL(sg.creditLimit, 0) END * 100) / sg.creditLimit END AS DECIMAL(18,2)) AS balanceCreditLimitPer FROM subGroup sg INNER JOIN ( SELECT DISTINCT acf.nature FROM acFilteration acf LEFT JOIN voucher_Type vt ON vt.v_Type = acf.v_Type WHERE vt.category IN ( {…} ) AND acf.nature IS NOT NULL AND acf.nature <> :CS ) vAcf ON vAcf.nature = sg.nature LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN stateMast sm ON sm.code = c.stateCode LEFT JOIN country ct ON ct.code = c.countryCode LEFT JOIN ( SELECT lg.subcode, (ISNULL(SUM(lg.amtDr), 0) - ISNULL(SUM(lg.amtCr), 0)) AS balanceAmt FROM ledger lg WHERE lg.isPosted = 1 AND lg.v_Date <= :asOnDate GROUP BY lg.subcode ) vLg ON sg.subCode = vLg.subCode WHERE 1=1 {…} ORDER BY isCreditLimitDefined, balanceCreditLimitPer, sg.name`
- Params: `:CS` ← `DmServer.getCodeViaManualCode("Cash", "name", "subGroupNature", "code", cnTemp)`
- Errors: "Invalid request." | "File Name not given."
- Other calls: `RptCustomerCreditLimitBalanceRegister_Vld.validateInput`

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

- Params: `:asOnDate` ← `DmServer.convertDate(filter.get("asOnDate").getText()), true`

### `getTxtHYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHAccountGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAccountGroup(DmHelper helper, boolean blnToEndOfFile, String strReportFor)`

- SQL 1: `SELECT agc.groupCode, agc.groupName FROM acGroup ag INNER JOIN acGroup agc ON CHARINDEX(ISNULL(ag.mainStreamCode, ''), ISNULL(agc.mainStreamCode,''),1) > 0 WHERE ag.groupName = :groupName`
- Params: `:groupName` ← `DEBTORS_GROUP_NAME`; `:groupName` ← `CREDITORS_GROUP_NAME`

### `getTxtHCustomer`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHCustomer(String creditLimitExist, DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code, sg.name as customerName, sg.manualCode, c.cityName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sg.isActive = 1 AND sgsl.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

## Server `RptSaleOrderStatusRegister_SrvImpl.java` (2387 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.removeAllCharacterExceptAlphaNumeric`, `DmServer.saveLog`, `DmServer.setReportHeaderExcel`, `DmServer.showJasper`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT ' {…} ' As DispatchDetails, ' {…} ' As Status, so.docId,sod.v_SNo , so.site_Code, sm.name AS SiteName, so.recId AS orderNo, so.v_Date AS orderDate, so.partyOrdNo, so.partyOrdDt, pro.recId AS proformaInvoiceNo, pro.v_Date AS proformaInvoiceDate, ptip.name AS partyName, ptip.add1 AS partyAddress, ptip.cityName AS partyCity, ptip.gstin AS partyGSTIN, ptip.pan, isnull(ptic.name,'') AS consignee, ptic.cityName AS conCityName, ptidt.name AS deliveryTo, ptidt.cityName AS deliveryCity, ct.cityName AS placeOfSupply, so1.recId AS amdOrdNo, so1.v_Date AS amdOrdDate, sgsm.displayName AS salesman, so.remark AS soRemark, itn.name AS itemNature, im.name AS itemName, ib.bfCode, ib.gsmCode, ib.sizeWidth AS width, ib.sizeLength AS length, um2.unit AS sizeUnitName, um.decimalPlaces, CASE WHEN ISNULL(ib.grain, 0) = 1 THEN 'L' WHEN ISNULL(ib.grain, 0) = 2 THEN 'S' END AS grainName, sod.packCount, sod.quantity AS orderQty, sod.rate, sod.oh_Amt_Gross, um1.unit AS itemUnitName, (ISNULL(sod.oh_At_SGST, 0) + ISNULL(sod.oh_At_UGST, 0) + ISNULL(sod.oh_At_CGST, 0) + ISNULL(sod.oh_At_IGST, 0)) AS taxRate, sod.unitCode AS itemUnitCode, sod.itemDesc, sod.rate AS itemValue, sod.oh_Amt_SchemeDiscount AS schemeDiscount, sod.oh_Amt_Taxable, sod.oh_Amt_CGST, sod.oh_Amt_SGST, sod.oh_Amt_IGST, sod.oh_Amt_UGST, sod.oh_Amt_Net, sod.oh_Amt_Cess, sod.oh_Amt_CessNa, sod.CMN_Addition, sod.CMN_Deduction, sod.skuQty, ISNULL(sod.dispatchedSkuQty, 0) AS dispatchedSkuQty, CASE WHEN sod.tl_ExcessQty > 0 OR sod.tl_ShortageQty > 0 THEN CONCAT('+ ', sod.tl_ExcessQty, ' ; - ', sod.tl_ShortageQty, ' ;') ELSE CONCAT('+ ', FORMAT(sod.tl_ExcessQty, '0'), ' ; - ', FORMAT(sod.tl_ShortageQty, '0'), ' ;') END AS tolerance, (ISNULL(sod.skuQty, 0) - ISNULL(sod.dispatchedSkuQty, 0)) AS pendingQty, CASE WHEN sod.isStatus = 1 THEN 'Cancelled' WHEN sod.isStatus = 2 THEN 'Completed' ELSE 'Open' END AS qtyStatus , cso.recId AS csoRecId , cso.v_Date AS csoDate, cso.validUpTo AS csoValidUpTo , InvoiceNo,InvoiceDate,DispatchQty FROM sOrder so LEFT JOIN sOrderDetail AS sod ON sod.docId = so.docId LEFT JOIN siteMast sm ON sm.code = so.site_Code LEFT JOIN partyTransInfo ptip ON ptip.docId = so.docId AND ptip.recordType =:PartyRecordType LEFT JOIN partyTransInfo ptid ON ptid.docId = so.docId AND ptid.recordType = :DistributorRecordType LEFT JOIN partyTransInfo ptic ON ptic.docId = so.docId AND ptic.recordType = :consigneeRecordType LEFT JOIN partyTransInfo ptidt ON ptidt.docId = so.docId AND ptidt.recordType = :delivieryToRecordType LEFT JOIN city AS ct ON ct.cityCode = so.deliveryCity LEFT JOIN sOrder AS so1 ON so1.docId = so.amdOrdDocId LEFT JOIN proformaInvoice AS pro ON pro.docId = sod.piDocId LEFT JOIN itemMast im ON im.code = sod.itemCode LEFT JOIN itemType it ON it.code = im.itemType LEFT JOIN itemBatch ib ON ib.code = sod.ibCode LEFT JOIN unitMast um ON um.code = ib.sizeCode LEFT JOIN unitMast um1 ON um1.code = sod.unitCode LEFT JOIN voucher_Type vt ON vt.v_Type = so.v_Type LEFT JOIN subGroup sgsm ON sgsm.subCode = so.salesmanCode LEFT JOIN itemNature itn ON itn.code = ib.itemNature LEFT JOIN unitMast um2 ON um2.code = ib.sizeUnitCode LEFT JOIN contractualSaleOrder cso ON cso.docId = sod.csoDocId LEFT JOIN contractualItemDetail cid ON cid.docId = sod.csoDocId AND cid.group_v_SNo = sod.csoV_SNo AND cid.v_SNo = sod.csoV_SNo LEFT JOIN contractualGroupDetail cgd ON cid.docId = cgd.docId AND cgd.v_SNo = cid.group_v_SNo LEFT JOIN (SELECT S2.recid AS InvoiceNo,S2.v_Date AS InvoiceDate,s1.soDocId,s1.soV_SNo,s1.quantity AS DispatchQty FROM sBillDetail S1 LEFT JOIN sBill S2 ON S1.docId = S2.docId) SBD ON sbd.soDocId = sod.docId AND sbd.soV_SNo = sod.v_SNo WHERE 1=1 {…} ORDER BY so.v_Date, vt.short_Name, so.v_No, so.recId, sod.v_SNo`
- Params: `:PartyRecordType` ← `DmEnum.PartyTransRecordType.PARTY.getCode()`; `:DistributorRecordType` ← `DmEnum.PartyTransRecordType.DISTRIBUTOR.getCode()`; `:consigneeRecordType` ← `DmEnum.PartyTransRecordType.CONSIGNEE.getCode()`; `:delivieryToRecordType` ← `DmEnum.PartyTransRecordType.DELIVERY_TO_PARTY.getCode()`
- Other calls: `CONSIGNEE.getCode`, `DELIVERY_TO_PARTY.getCode`, `DISTRIBUTOR.getCode`, `PARTY.getCode`, `PRINT.getValue`, `RptSaleOrderStatusRegister_Vld.validateInput`, `SYSTEM_ACTIVITY.getName`, `Status.equalsIgnoreCase`

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

### `getTxtHStatus`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHStatus(DmHelper helper, boolean blnToEndOfFile)`


### `getGroupName`
`public DmResultPageing<List<KeyFieldExtended>> getGroupName(String excludeGroup, DmHelper helper, boolean blnToEndOfFile)`


### `getGroupOn`
`private String[] getGroupOn(String groupOn)`


### `getTxtHDispatchDetails`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDispatchDetails(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHReportFor`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHReportFor(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHItemNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemNature(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT itn.code, itn.name, itn.manualCode FROM itemNature AS itn WHERE itn.isActive = 1 AND itn.manualCode IN (:R, :S)`
- Params: `:R` ← `DmShared.xNull(STEnum.ItemNature.REEL.getManualCode())`; `:S` ← `DmShared.xNull(STEnum.ItemNature.SHEET.getManualCode())`
- Other calls: `REEL.getManualCode`, `SHEET.getManualCode`

### `getTxtHTransactionType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTransactionType(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `Select Code,name from transtype where manualCode='SO'`

### `getTxtHFreightType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHFreightType(DmHelper helper, boolean blnToEndOfFile)`


## Server `OrderWiseWIPDisplayReport_SrvImpl.java` (873 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.xNull`

### `getTxtHReportType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHReportType(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHPartyName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHPartyName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name + ', ' + c.cityName AS partyName FROM pb_SubGroup sg LEFT JOIN pb_city c on c.cityCode = sg.cityCode LEFT JOIN pb_SubGroup sg1 ON sg1.subCode = (CASE WHEN ISNULL(sg.distributor, '') <> '' THEN sg.distributor ELSE sg.subcode END) WHERE ISNULL(sg.isActive, 0) = 1 AND lower(sg.nature) = 'customer' AND sg1.subCode = :linkedAccount`

### `getTxtHStatus`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHStatus(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHOrderType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHOrderType(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHBF`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHBF(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT gm.code, gm.name FROM pb_BFMast gm WHERE ISNULL(gm.isActive, 0) = 1`

### `getTxtHGSM`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGSM(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT gm.code, gm.name FROM pb_GsmMast gm WHERE ISNULL(gm.isActive, 0) = 1`

### `getTxtHSize`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSize(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name + ', ' + c.cityName AS partyName FROM pb_SubGroup sg LEFT JOIN pb_city c on c.cityCode = sg.cityCode LEFT JOIN pb_SubGroup sg1 ON sg1.subCode = (CASE WHEN ISNULL(sg.distributor, '') <> '' THEN sg.distributor ELSE sg.subcode END) WHERE ISNULL(sg.isActive, 0) = 1 AND lower(sg.nature) = 'customer' AND sg1.subCode = :linkedAccount`

### `getTxtHSOrd`
`public DmResultPageing<List<OrderWiseWIPDisplayOrderhelp_Mdl>> getTxtHSOrd(String partyCode, String consigneeCOde, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `IF OBJECT_ID('tempdb.. {…} ') IS NULL BEGIN CREATE TABLE {…} ( orderIndex bigint, Tick varchar(20), ConsigneeCode varchar(50), ConsigneeName varchar(200), DistributorCode varchar(50), DistributorName varchar(200), OrderDate varchar(30), OrderDocId varchar(100), OrderNo varchar(50), PartyCode varchar(50), PartyName varchar(200), PartyOrdNo varchar(100), partyOrdDate varchar(30) ) END`
- SQL 2: `SELECT CASE WHEN OBJECT_ID('tempdb.. {…} ') IS NOT NULL AND EXISTS (SELECT 1 FROM {…} ) THEN 1 ELSE 0 END AS DataExists`
- SQL 3: `SELECT CASE WHEN OBJECT_ID('tempdb.. {…} ') IS NOT NULL AND EXISTS (SELECT 1 FROM {…} ) THEN 1 ELSE 0 END AS DataExists`
- SQL 4: `INSERT INTO {…} (orderIndex, Tick, ConsigneeCode, ConsigneeName, DistributorCode, DistributorName, OrderDate, OrderDocId, OrderNo, PartyCode, PartyName, PartyOrdNo, PartyOrdDate) VALUES (:orderIndex, :Tick, :ConsigneeCode, :ConsigneeName, :DistributorCode, :DistributorName, :OrderDate, :OrderDocId, :OrderNo, :PartyCode, :PartyName, :PartyOrdNo, :PartyOrdDate)`
- SQL 5: `SELECT orderIndex, Tick, ConsigneeCode, ConsigneeName , DistributorCode, DistributorName, OrderDate , OrderDocId, OrderNo, PartyCode, PartyName , PartyOrdNo, partyOrdDate FROM {…}`
- Params: `:Tick` ← `mdl.getTick()`; `:orderIndex` ← `orderIndex`; `:ConsigneeCode` ← `mdl.getConsigneecode()`; `:ConsigneeName` ← `mdl.getConsigneename()`; `:DistributorCode` ← `mdl.getDistributorcode()`; `:DistributorName` ← `mdl.getDistributorname()`; `:OrderDate` ← `mdl.getOrderdate()`; `:OrderDocId` ← `mdl.getOrderdocid()`; `:OrderNo` ← `mdl.getOrderno()`; `:PartyCode` ← `mdl.getPartycode()`; `:PartyName` ← `mdl.getPartyname()`; `:PartyOrdNo` ← `mdl.getPartyOrdNo()`; `:partyOrdDate` ← `mdl.getPartyOrdDate()`

### `getTxtHItem`
`public DmResultPageing<List<TxtHItem_Mdl>> getTxtHItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code, im.name, im.dispName, im.manualCode, im.sku, im.hsn_Code, im.hsn_Name, im.tolerance , eicw.corePipeCode, cpm.name AS corePipeName, cpm.CorePipeValue, eicw.dia, cpm.Unit AS diaUnit , im.categoryName, im.itemGroupName FROM pb_itemMast im LEFT JOIN pb_enviroItemCategoryWise eicw ON eicw.itemCategoryCode = im.categoryCode AND eicw.site_code = :siteCode LEFT JOIN pb_CorePipeMast cpm ON cpm.Code = eicw.corePipeCode WHERE ISNULL(im.isActive, 0) = 1`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`

### `exportToPdf`
`public DmResult<String> exportToPdf(String fileName, String html, String width)`

- Other calls: `CSS.getBytes`, `PdfWriter.getInstance`, `Tags.getHtmlTagProcessorFactory`, `XMLWorkerHelper.getCSS`

### `exportToExcel`
`public DmResult<String> exportToExcel(String fileName, String html)`

- Other calls: `Jsoup.parse`

### `getReport`
`public DmResult<OrderWiseWIPDisplayReport_Mdl> getReport(LinkedHashMap<String, FieldUDT_Mdl> filter)`


### `getOrderWiseWIPDisplayReport`
`private List<OrderWiseWIPDisplay_Mdl> getOrderWiseWIPDisplayReport(Connection cnTemp, LinkedHashMap<String, FieldUDT_Mdl> filter) throws Exception`

- SQL 1: `SELECT so.docId, so.v_No, so.v_Date , so.partyOrdNo, so.partyOrdDt , so.partyCode, ptip.name AS partyName , so.consignee, ptic.name AS consigneeName , sod.itemCode, im.name AS itemName , ib.bfCode, ib.gsmCode , CAST(ib.sizeWidth AS VARCHAR(20)) + ' X ' + CAST(ib.sizeLength AS VARCHAR(20)) AS size , um.unit AS unitName , psm.name AS packSizeName , sod.quantity AS orderQty , sod.dispatchedSkuQty FROM sOrder AS so LEFT JOIN sOrderDetail AS sod ON sod.docId = so.docId LEFT JOIN voucher_Type AS vt ON vt.v_Type = so.v_Type LEFT JOIN partyTransInfo ptip ON ptip.docId = so.docId AND ptip.recordType = :partyRecordType LEFT JOIN partyTransInfo ptic ON ptic.docId = so.docId AND ptic.recordType = :consigneeRecordType LEFT JOIN itemMast AS im ON im.code = sod.itemCode LEFT JOIN itemBatch AS ib ON ib.code = sod.ibCode LEFT JOIN unitMast AS um ON um.code = sod.unitCode LEFT JOIN packSizeMast AS psm ON psm.code = sod.packSizeCode WHERE vt.category IN (:SO, :GSO) AND ISNULL(so.approvedBy, '') <> '' AND sod.isFooter = 0 {…} ORDER BY so.v_Date, so.v_No, sod.v_SNo`
- Params: `:SO` ← `pvc.SALES_ORDER`; `:GSO` ← `pvc.GENERAL_SALES_ORDER`; `:partyRecordType` ← `DmEnum.PartyTransRecordType.PARTY.getCode()`; `:consigneeRecordType` ← `DmEnum.PartyTransRecordType.CONSIGNEE.getCode()`; `:fromDate` ← `DmServer.convertDate(fromDate), true`; `:toDate` ← `DmServer.convertDate(toDate), true`
- Other calls: `CONSIGNEE.getCode`, `PARTY.getCode`

### `getText`
`private String getText(LinkedHashMap<String, FieldUDT_Mdl> filter, String key)`


### `getTag`
`private String getTag(LinkedHashMap<String, FieldUDT_Mdl> filter, String key)`


### `getTable`
`private String getTable(DmRSSPrivate rssPrivate)`


## Server `RptNetSalesSummaryRegister_SrvImpl.java` (1519 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnId`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.removeAllCharacterExceptAlphaNumeric`, `DmServer.setReportHeaderExcel`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `execute`
`public DmResult<KeyFieldExtended> execute(String startDate, String endDate, String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`


### `createExcelReport`
`private KeyFieldExtended createExcelReport(String startDate, String endDate, String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, Connection cnTemp) throws Exception`

- SQL 1: `SELECT {…} {…} as group1Code {…} {…} {…} , ISNULL( {…} ,'Blank') as group1Name, {…} {…} as group2Code , {…} as group2Name {…} , SUM( {…} ) AS totalAmt from sBill sb LEFT JOIN siteMast sm ON sm.code = sb.site_Code LEFT JOIN subGroup sg ON sg.subCode = sb.partyCode LEFT JOIN city cpc ON cpc.cityCode = sg.cityCode LEFT JOIN subGroup sgdt ON sgdt.subCode = sb.deliveryTo LEFT JOIN city cdc ON cdc.cityCode = sb.deliveryCity LEFT JOIN stateMast stm ON stm.code = sb.placeOfSupply LEFT JOIN subGroup AS sgd ON sgd.subCode = sb.distributor LEFT JOIN subGroup AS sgc ON sgc.subCode = sb.consignee LEFT JOIN voucher_Type vt ON vt.v_Type = sb.v_Type LEFT JOIN zoneMast zm ON zm.code = sg.zone LEFT JOIN areaMast am ON am.code = sg.areaCode WHERE vt.category IN (' {…} ',' {…} ',' {…} ',' {…} ', ' {…} ',' {…} ',' {…} ',' {…} ', ' {…} ') AND ISNULL(sb.approvedBy, '') <> '' AND ISNULL(sb.cancelledBy, '') = '' {…} GROUP BY {…} {…} , {…} {…} ORDER BY {…} {…} , {…} {…}`
- Errors: "Invalid request." | "File Name not given." | "No record found!"
- Other calls: `CellReference.convertNumToColString`, `RptNetSalesSummaryRegister_Vld.validateInput`

### `getParameter`
`private void getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:SI` ← `pvc.SALES_INVOICE`; `:SIU` ← `pvc.GENERAL_SALES_INVOICE_UNSTOCKABLE`; `:SIO` ← `pvc.GENERAL_SALES_INVOICE`; `:SR` ← `pvc.SALES_RETURN`; `:SRO` ← `pvc.GENERAL_SALES_RETURN`

### `getTxtHInvoiceNo`
`public DmResultPageing<List<TxtHInvoiceNo_Mdl>> getTxtHInvoiceNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select sb.docId, sb.recId, sb.v_Date, sb.v_No, sb.v_Time, sb.v_Prefix, vt.short_Name, sg.name as partyName, sd.name as distributorName, sc.name as consigneeName , CASE WHEN sb.site_Code = {…} THEN 1 ELSE 2 END as tempSr from sBill sb LEFT JOIN voucher_Type vt On vt.v_Type = sb.v_Type LEFT JOIN subGroup sg On sg.subCode = sb.partyCode LEFT JOIN subGroup sd On sd.subCode = sb.distributor LEFT JOIN subGroup sc On sc.subCode = sb.consignee LEFT JOIN siteMast sm ON sm.code = sb.site_Code Where vt.category IN (' {…} ',' {…} ', ' {…} ') and ISNULL(sb.approvedBy,'')<>'' and sb.site_Code in( {…} :siteList {…} )`

### `getTxtHSalesReturnNo`
`public DmResultPageing<List<TxtHReturnNo_Mdl>> getTxtHSalesReturnNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select sb.docId, sb.recId, sb.v_Date, sb.v_No, sb.v_Time, sb.v_Prefix , vt.short_name, sg.name as partyName, sd.name as distributorName , sc.name as consigneeName, sm.manualCode AS siteManualCode , CASE WHEN sb.site_Code = {…} THEN 1 ELSE 2 END as tempSr FROM sBill sb LEFT JOIN voucher_Type vt On vt.v_Type = sb.v_Type LEFT JOIN subGroup sg On sg.subCode = sb.partyCode LEFT JOIN subGroup sd On sd.subCode = sb.distributor LEFT JOIN subGroup sc On sc.subCode = sb.consignee LEFT JOIN siteMast sm ON sm.code = sb.site_Code WHERE vt.category IN (:SR,:SRO) AND sb.site_Code IN( {…} :siteList {…} ) AND ISNULL(sb.approvedBy,'')<>'' AND ISNULL(sb.cancelledBy, '') = ''`
- Params: `:SR` ← `pvc.SALES_RETURN`; `:SRO` ← `pvc.GENERAL_SALES_RETURN`

### `getTxtHParty`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHParty(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as supplierName , sg.manualCode , c.cityName FROM subGroup sg LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( SELECT DISTINCT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_Type = acf.v_Type WHERE vt.category IN (' {…} ',' {…} ', ' {…} ') )`

### `getTxtHDistributor`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHDistributor(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as name , sg.manualCode , c.cityName FROM subGroup sg LEFT JOIN city c ON c.cityCode = sg.cityCode WHERE sg.partyCat= :partyCat`
- Params: `:partyCat` ← `DmEnum.PartyCategory.DISTRIBUTOR.getId()`
- Other calls: `DISTRIBUTOR.getId`

### `getItemName`
`public DmResultPageing<List<TxtHItem_Mdl>> getItemName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select im.code as itemCode, im.name as itemName ,im.manualCode from itemMast im LEFT JOIN unitmast um ON im.saleUnit = um.code where (im.filterItemType = :filterItemType )`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemGroup(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `select ig.code, ig.name from itemGroup ig`

### `getTxtHItemCategory`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemCategory(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `select ic.code, ic.name from itemCat ic`

### `getTxtHZone`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHZone(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `select zm.code, zm.name from zoneMast zm`

### `getTxtHVoucherType`
`public DmResultPageing<List<TxtHVoucher_Mdl>> getTxtHVoucherType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type as code , vt.description as voucherType , vt.category , vt.short_Name FROM voucher_Type vt WHERE vt.category IN (' {…} ',' {…} ',' {…} ',' {…} ', ' {…} ')`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getReportFor`
`public DmResultPageing<List<KeyFieldExtended>> getReportFor(DmHelper helper, boolean blnToEndOfFile)`


### `getGroupBy`
`public DmResultPageing<List<KeyFieldExtended>> getGroupBy(String groupByTag, DmHelper helper, boolean blnToEndOfFile)`


### `getSummaryType`
`public DmResultPageing<List<KeyFieldExtended>> getSummaryType(DmHelper helper, boolean blnToEndOfFile)`


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

## Server `RptPIRegister_SrvImpl.java` (2197 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnEndDate`, `DmServer.getSsnId`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.getSsnStartDate`, `DmServer.processWjMessageQueue`, `DmServer.removeAllCharacterExceptAlphaNumeric`, `DmServer.saveLog`, `DmServer.setReportHeaderExcel`, `DmShared.convertDate`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `execute`
`public DmResult<KeyFieldExtended> execute(String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT STRING_AGG(CONVERT(NVARCHAR(max),CONCAT('[',ISNULL(vIm.name, smf.name),']')), ',') AS serviceCSV FROM serviceMappingField smf LEFT JOIN ( SELECT im.name, smfCode FROM itemMast im WHERE im.filterItemType = :filterItemType AND im.usedIn IN (:OT,:IOT) AND im.itemType = :itemType AND im.smfCode IS NOT NULL ) vIm ON vIm.smfCode = smf.code`
- SQL 2: `WITH cteService AS ( SELECT * FROM ( SELECT pi.docId AS serviceDocId, im.name AS serviceName,ISNULL(pid.oh_Amt_Taxable, 0) AS serviceValue FROM proformaInvoice pi LEFT JOIN voucher_Type vt ON vt.v_Type = pi.v_Type LEFT JOIN proformaInvoiceDetail pid ON pid.docId = pi.docId LEFT JOIN itemMast im ON im.code = pid.itemCode LEFT JOIN serviceMappingField smf ON smf.code = im.smfCode WHERE pid.isFooter = 2 AND vt.category IN ( {…} ) ) AS t PIVOT (MAX(serviceValue) FOR serviceName IN ( {…} )) AS PivotTable )`
- SQL 3: `SELECT {…} AS group1Name, {…} AS group2Name , pi.site_Code, pi.docId, pi.recId, pi.v_Date, pi.refNo , pi.refDate, pi.oh_Amt_GrossGoods as goodsValue , pi.oh_Amt_GrossService as serviceValue ,pi.oh_Amt_Cess, pi.oh_Amt_CessNA ,pi.remark as piRemark ,pid.rate , ptip.name as partyName, ptip.add1 as partyAddress ,ptip.cityName as partyCityName,ptip.gstin as partyGstin, ptip.pan, ctip.name as consignee , dtip.name as deliveryTo ,ct.cityName As deliveryCity , inl.name as itemNature ,IIF(pi.isBifurcateService = 1, 'Yes', 'No') AS isBifurcateService ,im.name as itemName , ib.bfCode , ib.gsmCode , ib.sizeWidth AS width , ib.sizeLength AS length --, CASE ISNULL(sUnt.unit,'') WHEN 'MM' THEN ib.mmWidth WHEN 'CM' THEN ib.cmWidth WHEN 'Inch' THEN ib.inchWidth END AS width --, CASE ISNULL(sUnt.unit,'') WHEN 'MM' THEN ib.mmLength WHEN 'CM' THEN ib.cmLength WHEN 'Inch' THEN ib.inchLength END AS length , sUnt.unit as sizeUnitName , um.decimalPlaces , CASE WHEN ISNULL(ib.grain,0) = 1 THEN 'L' WHEN ISNULL(ib.grain,0) = 2 THEN 'S' END AS grainName ,um1.unit as itemUnitName ,(ISNULL(pid.oh_At_SGST,0) + ISNULL(pid.oh_At_UGST,0) + ISNULL(pid.oh_At_CGST,0) + ISNULL(pid.oh_At_IGST,0)) AS taxRate ,pid.quantity, pid.packCount , pid.oh_Amt_Gross as oh_Amt_Gross, pid.unitCode as itemUnitCode, pid.itemDesc, pid.rate as itemValue , pid.oh_Amt_SchemeDiscount as schemeDiscount , pid.oh_Amt_Taxable, pid.oh_Amt_CGST, pid.oh_Amt_SGST, pid.oh_Amt_IGST , pid.oh_Amt_UGST, pid.oh_Amt_Net as finalitemValue, pid.CMN_Addition, pid.CMN_Deduction, case when pid.tl_ExcessQty>0 or pid.tl_ShortageQty>0 then concat('+ ',pid.tl_ExcessQty,' ; - ',pid.tl_ShortageQty,' ;') else concat('+ ',format(pid.tl_ExcessQty,'0'),' ; - ',format(pid.tl_ShortageQty,'0'),' ;') end as tolerance , pi.oh_Amt_RoundOff ,pid.oh_Amt_ServiceBifurcated ,pi.oh_Amt_Tcs {…} FROM proformaInvoice pi LEFT JOIN voucher_Type vt ON vt.v_Type = pi.v_Type LEFT JOIN siteMast sm ON sm.code = pi.site_Code LEFT JOIN proformaInvoiceDetail pid ON pid.docId = pi.docId LEFT JOIN partyTransInfo ptip ON ptip.docId = pi.docId AND ptip.recordType = :partyRecordType LEFT JOIN partyTransInfo ctip on ctip.docId = pi.docId AND ctip.recordType = :consigneeRecordType LEFT JOIN partyTransInfo dtip on dtip.docId = pi.docId AND dtip.recordType = :deliveryToRecordType LEFT JOIN city ct ON ct.cityCode = pi.deliveryCity LEFT JOIN itemMast im ON im.code = pid.itemCode LEFT JOIN itemType it ON it.code = im.itemType LEFT JOIN itemBatch ib on ib.code = pid.ibCode LEFT JOIN itemNature inl ON inl.code = ib.itemNature LEFT JOIN unitMast um ON um.code = ib.sizeCode LEFT JOIN unitMast um1 on um1.code = pid.unitCode LEFT JOIN subGroup sgsm ON sgsm.subCode = pi.salesmanCode LEFT JOIN unitMast sUnt ON sUnt.code = ib.sizeUnitCode {…} WHERE vt.category IN ( {…} ) {…} ORDER BY {…} pi.v_Date, vt.short_Name, pi.v_No, pi.recId, pid.v_SNo`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("OI", "manualCode", "itemType", "code", cnTem`; `:itemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SV", "manualCode", "itemType", "code", cnTem`; `:OT` ← `ServiceUsedIn.OUTWARD_TRANSACTION.getId()`; `:IOT` ← `ServiceUsedIn.BOTH_INWARD_AND_OUTWARD_TRANSACTION.getId()`
- Errors: "From Date should be greater than or equal to " | "To Date should be less than or equal to " | "Invalid request." | "File Name not given."
- Other calls: `BOTH_INWARD_AND_OUTWARD_TRANSACTION.getId`, `OUTWARD_TRANSACTION.getId`, `PRINT.getValue`, `RptPIRegister_Vld.validateInput`, `SYSTEM_ACTIVITY.getName`

### `createExcelReport`
`private KeyFieldExtended createExcelReport(String widgetName, String sessionId, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, ResultSet rsTemp, int totalColumns) throws Exception`

- Errors: "No record found!"
- Other calls: `ExcelReport_Server.createAndMergeBlankRows`, `ExcelReport_Server.createAndMergeCells`, `ExcelReport_Server.getCellStyle`, `ExcelReport_Server.getCellStyleHeader`, `ExcelReport_Server.getDataFormat`, `ExcelReport_Server.newCell`, `ExcelReport_Server.printFilterData`

### `getParameter`
`private void getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:partyRecordType` ← `DmEnum.PartyTransRecordType.PARTY.getCode()`; `:consigneeRecordType` ← `DmEnum.PartyTransRecordType.CONSIGNEE.getCode()`; `:deliveryToRecordType` ← `DmEnum.PartyTransRecordType.DELIVERY_TO_PARTY.getCode()`; `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`
- Other calls: `CONSIGNEE.getCode`, `DELIVERY_TO_PARTY.getCode`, `PARTY.getCode`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code = :code`
- Params: `:code` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHVoucherType`
`public DmResultPageing<List<TxtHVoucherNo_Mdl>> getTxtHVoucherType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type as code , vt.description as voucherType , vt.category , vt.short_Name FROM voucher_Type vt WHERE vt.category IN (' {…} ')`

### `getTxtHSaleOrderNo`
`public DmResultPageing<List<TxtHVoucherNo_Mdl>> getTxtHSaleOrderNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT so.docId, so.recId, so.v_Date, vt.category ,so.v_No, sg.name as partyName, sd.name as distributorName, sc.name as consigneeName FROM sOrder AS so left join voucher_Type as vt on vt.v_Type = so.v_Type LEFT JOIN subGroup sg On sg.subCode = so.partyCode LEFT JOIN subGroup sd On sd.subCode = so.distributor LEFT JOIN subGroup sc On sc.subCode = so.consignee where vt.Category IN (' {…} ') AND ISNULL(so.approvedBy, '') <> ''`

### `getTxtHParty`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHParty(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as supplierName , sg.manualCode , c.cityName FROM subGroup sg LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( SELECT DISTINCT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_Type = acf.v_Type WHERE vt.category = ' {…} ' )`

### `getTxtHItemNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemNature(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT itn.code, itn.name, itn.manualCode FROM itemNature AS itn WHERE itn.isActive = 1 AND itn.manualCode IN (:R, :S)`
- Params: `:R` ← `DmShared.xNull(STEnum.ItemNature.REEL.getManualCode())`; `:S` ← `DmShared.xNull(STEnum.ItemNature.SHEET.getManualCode())`
- Other calls: `REEL.getManualCode`, `SHEET.getManualCode`

### `getTxtHSalesman`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHSalesman(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ec.srDesignationCode FROM enviroCompany ec`
- SQL 2: `SELECT sg.subCode AS code , sg.name AS salesman , sg.manualCode , c.cityName , c.cityCode FROM subGroup sg LEFT JOIN subGroup2 sg2 ON sg2.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode WHERE sg2.designationCode = {…}`

### `getTxtHDeliveryCity`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDeliveryCity(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select ct.cityCode, ct.cityName, ct.stateCode, sm.name as stateName from city ct left join stateMast sm on ct.stateCode = sm.code`

### `getTxtHProformaInvoiceNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHProformaInvoiceNo(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `select so.docId, so.recId,so.v_No, so.v_Date from proformaInvoice so LEFT JOIN voucher_Type vt ON vt.v_Type = so.v_Type where so.approvedBy <> '' and vt.category = ' {…} '`

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

- SQL 1: `select im.code as itemCode, im.name as itemName ,im.manualCode from itemMast im LEFT JOIN unitmast um ON im.saleUnit = um.code where (im.filterItemType = :filterItemType )`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`

### `getTxtHStatus`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHStatus(DmHelper helper, boolean blnToEndOfFile)`


### `getGroupName`
`public DmResultPageing<List<KeyFieldExtended>> getGroupName(String excludeGroup, DmHelper helper, boolean blnToEndOfFile)`


### `getGroupOn`
`private String[] getGroupOn(String groupOn)`


### `getReportFor`
`public DmResultPageing<List<KeyFieldExtended>> getReportFor(DmHelper helper, boolean blnToEndOfFile)`


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

## Server `RptDeliveryManageRegister_SrvImp.java` (904 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.showJasper`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.xNull`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT {…} as group1Code , {…} as group1Name ,so.docId,so.site_Code, so.v_Date ,ptip.name ,so.recId, ib.bfCode,ib.gsmCode,ib.sizeWidth ,ib.sizeLength,ib.sizeUnitCode,sg. name AS consigneeName ,sod.deliveryDt, sod.packCount,um.unit,sod.quantity , DATEDIFF(DAY, sod.deliveryDt, :currentDate) AS delayedDays , IIF(sod.deliveryDt < (:currentDate), 'Delayed', 'On Time') AS delayedStatus FROM sOrder so LEFT JOIN voucher_Type vt ON vt.v_Type = so.v_Type LEFT JOIN sOrderDetail sod ON sod.docId = so.docId LEFT JOIN sBillDetail sbd ON sbd.soDocId = sod.docId AND sbd.poV_SNo = sod.v_SNo LEFT JOIN sBill sb ON sb.docId = sod.docId LEFT JOIN itemBatch ib ON ib.code = sod.ibCode LEFT JOIN subGroup sg ON sg.subCode = so.consignee LEFT JOIN unitMast um ON um.code = ib.sizeUnitCode LEFT JOIN partyTransInfo ptip ON ptip.docId = so.docId AND ptip.recordType = :partyRecordType where 1=1 AND vt.category IN ( {…} ) AND ISNULL(sod.deliveryDt, '') <> '' AND ISNULL(so.approvedBy, '') <> '' AND ISNULL(sb.approvedBy, '') = '' {…}`
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

### `getTxtHSaleOrderNo`
`public DmResultPageing<List<TxtHVoucherNo_Mdl>> getTxtHSaleOrderNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT so.docId, so.site_Code,so.recId, so.v_Date , vt.short_Name as recieveType , so.v_No, sg.name as partyName, sd.name as distributorName , sc.name as consigneeName , CASE WHEN so.site_Code = {…} THEN 1 ELSE 2 END as tempSr,so.partyOrdNo,so.partyOrdDt FROM sOrder AS so left join voucher_Type as vt on vt.v_Type = so.v_Type LEFT JOIN subGroup sg On sg.subCode = so.partyCode LEFT JOIN subGroup sd On sd.subCode = so.distributor LEFT JOIN subGroup sc On sc.subCode = so.consignee LEFT JOIN siteMast sm ON sm.code = so.site_Code where vt.Category IN ( {…} ) AND ISNULL(so.approvedBy, '') <> '' and so.site_Code in( {…} :siteList {…} )`

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

### `getTxtHConsignee`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHConsignee(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as supplierName , sg.manualCode , c.cityName FROM subGroup sg LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( SELECT DISTINCT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_Type = acf.v_Type WHERE vt.category = ' {…} ' )`

## Server `RptMonthlySalesReport_SrvImpl.java` (2392 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnEndDate`, `DmServer.getSsnId`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.getSsnStartDate`, `DmServer.removeAllCharacterExceptAlphaNumeric`, `DmServer.setReportHeaderExcel`, `DmShared.convertDate`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `execute`
`public DmResult<KeyFieldExtended> execute(String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `WITH cteSales AS ( SELECT {…} AS {…} , {…} AS group1Name, {…} AS group2Name {…} , MAX(sizeUM.unit) AS sizeUnitName FROM sBill sb LEFT JOIN sBillDetail sbd ON sbd.docId = sb.docId LEFT JOIN siteMast sm ON sm.code = sb.site_Code LEFT JOIN subGroup sg ON sg.subCode = sb.partyCode LEFT JOIN partyTransInfo AS pti ON pti.docId = sb.docId AND pti.recordType = 1 LEFT JOIN subGroup sgdt ON sgdt.subCode = sb.deliveryTo LEFT JOIN city cdc ON cdc.cityCode = sb.deliveryCity LEFT JOIN stateMast stm ON stm.code = sb.placeOfSupply LEFT JOIN subGroup AS sgd ON sgd.subCode = sb.distributor LEFT JOIN subGroup AS sgc ON sgc.subCode = sb.consignee LEFT JOIN subGroup sgs ON sgs.subCode = sb.salesmanCode LEFT JOIN voucher_Type vt ON vt.v_Type = sb.v_Type LEFT JOIN zoneMast zm ON zm.code = sg.zone LEFT JOIN areaMast am ON am.code = sg.areaCode LEFT JOIN itemMast im ON im.code = sbd.itemCode LEFT JOIN unitMast um ON um.code = sbd.unitCode LEFT JOIN unitMast sizeUM ON sizeUM.code = :sizeUnitCode LEFT JOIN packSizeMast psm ON psm.code = sbd.packSizeCode LEFT JOIN unitMast ump ON ump.code = psm.packUnitCode LEFT JOIN itemCat ic ON ic.code = im.itemCategoryCode LEFT JOIN itemGroup ig ON ig.code = im.itemGroup LEFT JOIN itemBatch as ib on ib.code = sbd.ibCode LEFT JOIN itemNature itn ON itn.code = ib.itemNature LEFT JOIN itemMakeMast imk ON imk.code = ib.itemMakeCode WHERE vt.category IN (:SI, :SIO, :SIU) AND ISNULL(sb.approvedBy, :approvedBy) <> '' AND ISNULL(sb.cancelledBy, :cancelledBy) = '' AND sbd.isFooter = :isFooter {…} GROUP BY {…} )`
- SQL 2: `SELECT {…} , cs.group1Name, cs.group2Name ,sizeUnitName`
- Params: `:sizeUnitCode` ← `DmShared.xNull(filter.get("sizeUnit").getTag())`
- Errors: "From Date should be greater than or equal to " | "To Date should be less than or equal to " | "Invalid request." | "File Name not given."
- Other calls: `CM.getName`, `INCH.getName`, `MM.getName`, `Q1Amt.getSqlFieldAliasName`, `Q1Qty.getSqlFieldAliasName`, `Q2Amt.getSqlFieldAliasName`, `Q2Qty.getSqlFieldAliasName`, `Q3Amt.getSqlFieldAliasName`, `Q3Qty.getSqlFieldAliasName`, `Q4Amt.getSqlFieldAliasName`, `Q4Qty.getSqlFieldAliasName`, `RptMonthlySalesReport_Vld.validateInput`

### `createExcelReport`
`private KeyFieldExtended createExcelReport(String widgetName, String sessionId, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, ResultSet rsTemp, int totalColumns) throws Exception`

- Errors: "No record found!"
- Other calls: `ExcelReport_Server.createAndMergeBlankRows`, `ExcelReport_Server.createAndMergeCells`, `ExcelReport_Server.getAllMonthsWithYear`, `ExcelReport_Server.getCellStyle`, `ExcelReport_Server.getCellStyleHeader`, `ExcelReport_Server.getDataFormat`, `ExcelReport_Server.newCell`, `ExcelReport_Server.printFilterData`

### `getParameter`
`private void getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:approvedBy` ← `""`; `:cancelledBy` ← `""`; `:isFooter` ← `0`; `:SI` ← `pvc.SALES_INVOICE`; `:SIU` ← `pvc.GENERAL_SALES_INVOICE_UNSTOCKABLE`; `:SIO` ← `pvc.GENERAL_SALES_INVOICE`; `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- SQL 2: `SELECT ec.prod_SizeUnitCode AS sizeUnitCode, um.unit AS sizeUnitName FROM enviroCompany ec LEFT JOIN unitMast um ON um.code = ec.prod_SizeUnitCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHVoucherType`
`public DmResultPageing<List<TxtHVoucher_Mdl>> getTxtHVoucherType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type as code , vt.description as voucherType , vt.category , vt.short_Name FROM voucher_Type vt WHERE vt.category IN (:SI, :GSIU, :GSI)`
- Params: `:SI` ← `pvc.SALES_INVOICE`; `:GSIU` ← `pvc.GENERAL_SALES_INVOICE_UNSTOCKABLE`; `:GSI` ← `pvc.GENERAL_SALES_INVOICE`

### `getTxtHVoucherNo`
`public DmResultPageing<List<TxtHVoucherNo_Mdl>> getTxtHVoucherNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sb.docId , sb.recId , sb.v_Date , sb.v_No , sb.v_Prefix , vt.short_Name , sg.name as partyName , CONCAT(sg.name, ' - ', ct.cityName) AS PartyNameWithCity , sm.manualCode AS siteManualCode , CASE WHEN sb.site_Code = {…} THEN 1 ELSE 2 END as tempSr FROM sBill sb LEFT JOIN voucher_Type vt On vt.v_Type = sb.v_Type LEFT JOIN siteMast sm ON sm.code = sb.site_Code LEFT JOIN subGroup sg ON sg.subCode = sb.partyCode LEFT JOIN city ct ON ct.cityCode = sb.deliveryCity WHERE vt.category IN (:SI, :SIO, :SIU) AND sb.site_Code IN( {…} :siteList {…} ) AND ISNULL(sb.approvedBy,'')<>'' AND ISNULL(sb.cancelledBy, '') = ''`
- Params: `:SI` ← `pvc.SALES_INVOICE`; `:SIU` ← `pvc.GENERAL_SALES_INVOICE_UNSTOCKABLE`; `:SIO` ← `pvc.GENERAL_SALES_INVOICE`

### `getTxtHParty`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHParty(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code, sg.name as partyName, c.cityName, sg.manualCode FROM subGroup sg LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( SELECT DISTINCT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_Type = acf.v_Type WHERE vt.category IN (:SI, :SIO, :SIU) )`
- Params: `:SI` ← `pvc.SALES_INVOICE`; `:SIU` ← `pvc.GENERAL_SALES_INVOICE_UNSTOCKABLE`; `:SIO` ← `pvc.GENERAL_SALES_INVOICE`

### `getTxtHConsignee`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHConsignee(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code, sg.name as consignee, c.cityName, sg.manualCode FROM subGroup sg LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( SELECT DISTINCT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_Type = acf.v_Type WHERE vt.category IN (:SI, :SIO, :SIU) )`
- Params: `:SI` ← `pvc.SALES_INVOICE`; `:SIU` ← `pvc.GENERAL_SALES_INVOICE_UNSTOCKABLE`; `:SIO` ← `pvc.GENERAL_SALES_INVOICE`

### `getItemName`
`public DmResultPageing<List<TxtHItem_Mdl>> getItemName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code as itemCode , it.name AS itemType , im.name as itemName , im.manualCode FROM itemMast im LEFT JOIN unitmast um ON im.saleUnit = um.code LEFT JOIN itemType it ON it.code= im.itemType WHERE im.filterItemType IN (:FM,:SM)`
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


### `getTxtHSalesman`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHSalesman(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ec.srDesignationCode FROM enviroCompany ec`
- SQL 2: `SELECT sg.subCode AS code , sg.name AS salesman , sg.manualCode , c.cityName , c.cityCode FROM subGroup sg LEFT JOIN subGroup2 sg2 ON sg2.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode WHERE sg2.designationCode = {…}`

### `getTxtHArea`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHArea(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT am.code, am.name FROM areaMast am`

### `getHBF`
`public DmResultPageing<List<KeyFieldExtended>> getHBF(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT bf.code, bf.code as name FROM bfMast bf WHERE bf.isActive = 1`

### `getHGSM`
`public DmResultPageing<List<KeyFieldExtended>> getHGSM(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT gm.code, gm.code as name FROM gsmMast gm WHERE gm.isActive = 1`

### `getHPeriodicity`
`public DmResultPageing<List<KeyFieldExtended>> getHPeriodicity(DmHelper helper, boolean blnToEndOfFile)`


### `getQuartersWithYears`
`public static String[] getQuartersWithYears(Date startDate, boolean financialMonthOrder, boolean shortYear)`


### `getTxtHPrintProperties`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHPrintProperties(DmHelper helper, boolean blnToEndOfFile)`


### `getHSizeUnit`
`public DmResultPageing<List<KeyFieldExtended>> getHSizeUnit(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT um.code, um.unit AS sizeUnitName FROM unitMast um WHERE um.nature = :nature AND um.unit IN (:inch, :cm, :mm)`
- Params: `:nature` ← `STEnum.UnitNature.LENGTH.getId()`; `:inch` ← `STEnum.UnitMaster.INCH.getName()`; `:cm` ← `STEnum.UnitMaster.CM.getName()`; `:mm` ← `STEnum.UnitMaster.MM.getName()`
- Other calls: `CM.getName`, `INCH.getName`, `LENGTH.getId`, `MM.getName`

## Server `RptInactiveCustomerRegister_SrvImpl.java` (1452 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnId`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.removeAllCharacterExceptAlphaNumeric`, `DmServer.setReportHeaderExcel`, `DmShared.convertDate`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `execute`
`public DmResult<KeyFieldExtended> execute(String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `WITH cteInactiveParty AS ( SELECT sg.subCode AS partyCode FROM subGroup sg INNER JOIN ( SELECT af.nature FROM voucher_Type vt LEFT JOIN acFilteration af ON af.v_Type = vt.v_Type WHERE vt.category IN (:SI, :SR, :GSI, :GSR, :SIU) AND af.nature IS NOT NULL AND af.nature <> :CS GROUP BY af.nature ) vAf ON vAf.nature = sg.nature LEFT JOIN ( SELECT sb.partyCode FROM sBill sb LEFT JOIN sBillDetail sbd ON sbd.docId = sb.docId LEFT JOIN siteMast sm ON sm.code = sb.site_Code LEFT JOIN voucher_Type vt ON vt.v_Type = sb.v_Type LEFT JOIN itemMast im ON im.code = sbd.itemCode LEFT JOIN unitMast um ON um.code = sbd.unitCode LEFT JOIN itemBatch as ib on ib.code = sbd.ibCode WHERE vt.category IN (:SI, :SR, :GSI, :GSR, :SIU) AND ISNULL(sb.approvedBy, '') <> '' AND ISNULL(sb.cancelledBy, '') = '' {…} AND sbd.isFooter IN (0,3) GROUP BY sb.partyCode ) vSb ON vSb.partyCode = sg.subCode WHERE vSb.partyCode IS NULL ), cteSales AS ( SELECT sb.partyCode, um.unit as unitName {…} FROM sBill sb LEFT JOIN sBillDetail sbd ON sbd.docId = sb.docId LEFT JOIN siteMast sm ON sm.code = sb.site_Code LEFT JOIN voucher_Type vt ON vt.v_Type = sb.v_Type LEFT JOIN itemMast im ON im.code = sbd.itemCode LEFT JOIN unitMast um ON um.code = sbd.unitCode LEFT JOIN itemBatch as ib on ib.code = sbd.ibCode WHERE vt.category IN (:SI, :SR, :GSI, :GSR, :SIU) AND ISNULL(sb.approvedBy, '') <> '' AND ISNULL(sb.cancelledBy, '') = '' AND sbd.isFooter IN (0,3) {…} GROUP BY sb.partyCode, um.unit )`
- SQL 2: `SELECT cip.partyCode, CONCAT(sg.name,', ',cm.cityName) as partyName, sg.manualCode AS manualCode, cs.unitName {…} FROM cteInactiveParty cip LEFT JOIN subGroup sg ON sg.subCode = cip.partyCode LEFT JOIN city cm ON cm.cityCode = sg.cityCode LEFT JOIN cteSales cs ON cs.partyCode = cip.partyCode WHERE 1=1 {…} ORDER BY sg.name, cs.unitName`
- Params: `:CS` ← `DmShared.xNull(DmServer.getCodeViaManualCode("CS", "manualCode", "subGroupNature", "code",`
- Errors: "Date difference should be less than or equal to 365 days" | "Invalid request." | "File Name not given."
- Other calls: `DAYS.between`, `ExcelReport_Server.getAllMonthsWithYear`, `ExcelReport_Server.getQuartersWithYear`, `RptInactiveCustomerRegister_Vld.validateInput`, `ZoneId.systemDefault`

### `createExcelReport`
`private KeyFieldExtended createExcelReport(String widgetName, String sessionId, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, ResultSet rsTemp, int totalColumns) throws Exception`

- Errors: "No record found!"
- Other calls: `ExcelReport_Server.createAndMergeBlankRows`, `ExcelReport_Server.createAndMergeCells`, `ExcelReport_Server.getAllMonthsWithYear`, `ExcelReport_Server.getCellStyle`, `ExcelReport_Server.getCellStyleHeader`, `ExcelReport_Server.getDataFormat`, `ExcelReport_Server.getQuartersWithYear`, `ExcelReport_Server.newCell`, `ExcelReport_Server.printFilterData`

### `getParameter`
`private void getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp) throws Exception`

- Params: `:SI` ← `pvc.SALES_INVOICE`; `:SIU` ← `pvc.GENERAL_SALES_INVOICE_UNSTOCKABLE`; `:SR` ← `pvc.SALES_RETURN`; `:GSI` ← `pvc.GENERAL_SALES_INVOICE`; `:GSR` ← `pvc.GENERAL_SALES_RETURN`; `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`; `:inactiveFromDate` ← `DmServer.convertDate(filter.get("inactiveFromDate").getText()), true`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHParty`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHParty(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name AS name,sg.manualCode,c.cityName as city FROM subgroup sg LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature left join city as c on c.cityCode = sg.cityCode WHERE sgn.manualCode in ('CT','PT')`

### `getTxtHItem`
`public DmResultPageing<List<TxtHItem_Mdl>> getTxtHItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , um.code AS unitCode , um.unit,im.manualCode FROM itemMast AS im LEFT JOIN itemType it ON it.code = im.itemType LEFT JOIN unitmast um ON im.sku = um.code WHERE (it.manualcode IN ('FM','SM') or im.isCommon=1)`

### `getTxtHItemGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ig.code, ig.name FROM itemgroup ig WHERE (ig.filterItemType = :filterItemType or ig.isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemCat`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemCat(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ic.code, ic.name FROM itemCat ic WHERE (ic.filterItemType = :filterItemType or ic.isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHDeliveryCity`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDeliveryCity(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select ct.cityCode, ct.cityName, ct.stateCode, sm.name as stateName from city ct left join stateMast sm on ct.stateCode = sm.code`

### `getTxtHItemNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemNature(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select itn.code, itn.name, itn.manualCode from itemNature as itn`

### `getReportFor`
`public DmResultPageing<List<KeyFieldExtended>> getReportFor(DmHelper helper, boolean blnToEndOfFile)`


### `getPerioditicity`
`public DmResultPageing<List<KeyFieldExtended>> getPerioditicity(DmHelper helper, boolean blnToEndOfFile)`


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

## Server `RptPendingSaleOrder_SrvImpl.java` (2498 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.removeAllCharacterExceptAlphaNumeric`, `DmServer.saveLog`, `DmServer.setReportHeaderExcel`, `DmServer.showJasper`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT {…} as group1Code, {…} as group2Code, {…} as group1Name, {…} as group2Name, (' {…} ') as group1For, (' {…} ') as group2For, ' {…} ' as ReportType , so.docid,so.v_Date As orderDate, (vt.short_Name) As VoucherType, so.v_No, so.recId As orderNo,(so.site_Code) AS siteCode,(sm.name) AS siteName, (so.partyOrdNo) AS partyOrdNo, (so.partyOrdDt) AS partyOrdDt, (ptip.name) AS partyName ,(ptip.add1) AS partyAddress, (ptip.cityName) AS partyCity, (ptip.gstin) AS partyGSTIN, (ptip.pan) AS partyPAN, (so.distributor) AS distributorCode, (ptid.name) AS distributorName, (ptic.name) AS consignee ,(ptic.add1) AS consigneeAddress, (ptic.cityName) AS conCityName, (ptidt.name) AS deliveryTo, (ptidt.cityName) AS deliveryCity, (ct.cityName) AS placeOfSupply, (sgsm.displayName) AS salesman, (so.remark) AS soRemark,im.itemDesc, (ib.bfCode) AS bfCode, (ib.gsmCode) AS gsmCode,(sod.packCount) AS packCount, (im.name) AS itemName,(sod.quantity) AS orderQty, (ib.sizeWidth) AS width, (ib.sizeLength) AS length, (um2.unit) AS sizeUnitName, (um.decimalPlaces) AS decimalPlace, (sod.skuQty) AS totalSkuQty, (ISNULL(sod.dispatchedSkuQty,0)) AS dispatchedSkuQty, (ISNULL(sod.skuQty,0) - ISNULL(sod.dispatchedSkuQty,0)) AS pendingQty, ( CASE WHEN ISNULL(ib.grain,0) = 1 THEN 'L' WHEN ISNULL(ib.grain,0) = 2 THEN 'S' END ) AS grainName, CASE WHEN (CASE WHEN sod.isStatus = 1 THEN 1 ELSE 0 END) > 0 THEN 'Cancelled' WHEN (CASE WHEN sod.isStatus = 2 THEN 1 ELSE 0 END) > 0 THEN 'Completed' ELSE 'Open' END AS qtyStatus, (cso.recId) AS csoRecId, (cso.v_Date) AS csoDate, (cso.validUpTo) AS csoValidUpTo FROM sOrder so LEFT JOIN sOrderDetail AS sod ON sod.docId = so.docId LEFT JOIN siteMast sm ON sm.code = so.site_Code LEFT JOIN partyTransInfo ptip ON ptip.docId = so.docId AND ptip.recordType =:PartyRecordType LEFT JOIN partyTransInfo ptid ON ptid.docId = so.docId AND ptid.recordType = :DistributorRecordType LEFT JOIN partyTransInfo ptic ON ptic.docId = so.docId AND ptic.recordType = :consigneeRecordType LEFT JOIN partyTransInfo ptidt ON ptidt.docId = so.docId AND ptidt.recordType = :delivieryToRecordType LEFT JOIN city AS ct ON ct.cityCode = so.deliveryCity LEFT JOIN sOrder AS so1 ON so1.docId = so.amdOrdDocId LEFT JOIN proformaInvoice AS pro ON pro.docId = sod.piDocId LEFT JOIN itemMast im ON im.code = sod.itemCode LEFT JOIN itemType it ON it.code = im.itemType LEFT JOIN itemBatch ib ON ib.code = sod.ibCode LEFT JOIN unitMast um ON um.code = ib.sizeCode LEFT JOIN unitMast um1 ON um1.code = sod.unitCode LEFT JOIN voucher_Type vt ON vt.v_Type = so.v_Type LEFT JOIN subGroup sgsm ON sgsm.subCode = so.salesmanCode LEFT JOIN itemNature itn ON itn.code = ib.itemNature LEFT JOIN unitMast um2 ON um2.code = ib.sizeUnitCode LEFT JOIN contractualSaleOrder cso ON cso.docId = sod.csoDocId LEFT JOIN contractualItemDetail cid ON cid.docId = sod.csoDocId AND cid.group_v_SNo = sod.csoV_SNo AND cid.v_SNo = sod.csoV_SNo LEFT JOIN contractualGroupDetail cgd ON cid.docId = cgd.docId AND cgd.v_SNo = cid.group_v_SNo WHERE 1=1 {…} ORDER BY {…} , {…} ,so.docid,so.v_Date, so.v_No, so.recId, sod.itemCode`
- Params: `:PartyRecordType` ← `DmEnum.PartyTransRecordType.PARTY.getCode()`; `:DistributorRecordType` ← `DmEnum.PartyTransRecordType.DISTRIBUTOR.getCode()`; `:consigneeRecordType` ← `DmEnum.PartyTransRecordType.CONSIGNEE.getCode()`; `:delivieryToRecordType` ← `DmEnum.PartyTransRecordType.DELIVERY_TO_PARTY.getCode()`
- Other calls: `CONSIGNEE.getCode`, `DELIVERY_TO_PARTY.getCode`, `DISTRIBUTOR.getCode`, `PARTY.getCode`, `PRINT.getValue`, `RptPendingSaleOrder_Vld.validateInput`, `SYSTEM_ACTIVITY.getName`

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


### `getTxtHItemNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemNature(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT itn.code, itn.name, itn.manualCode FROM itemNature AS itn WHERE itn.isActive = 1 AND itn.manualCode IN (:R, :S)`
- Params: `:R` ← `DmShared.xNull(STEnum.ItemNature.REEL.getManualCode())`; `:S` ← `DmShared.xNull(STEnum.ItemNature.SHEET.getManualCode())`
- Other calls: `REEL.getManualCode`, `SHEET.getManualCode`

### `getTxtHTransactionType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTransactionType(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `Select Code,name from transtype where manualCode='SO'`

### `getTxtHFreightType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHFreightType(DmHelper helper, boolean blnToEndOfFile)`


## Server `RptOrderSummaryRegister_SrvImpl.java` (1081 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.saveLog`, `DmServer.showJasper`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.xNull`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHVoucherType`
`public DmResultPageing<List<TxtHVoucher_Mdl>> getTxtHVoucherType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type as code , vt.description as voucherType , vt.category , vt.short_Name FROM voucher_Type vt WHERE vt.category IN (' {…} ',' {…} ', ' {…} ',' {…} ')`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT {…} as group1Code , {…} as group1Name , {…} as group2Code , {…} as group2Name , {…} as g1OrderDate , {…} as g1PartyOrderNo , {…} as g1PartyOrderDate , {…} as g2OrderDate , {…} as g2PartyOrderNo , {…} as g2PartyOrderDate ,MAX(im.name) AS itemName, MAX(ib.propertyDispCsv) AS propertyDispCsv , {…} as orderQty, {…} AS dispatchQty , {…} AS balanceQty, SUM(sod.oh_Amt_Taxable) AS taxableValue, {…} AS pendingValue, MAX(cp.cityName) as partyCity, {…} AS unit , max(sgp.name) As partyName, {…} AS packSizeName FROM sOrder so LEFT JOIN sOrderDetail sod on sod.docId = so.docId LEFT JOIN itemMast im on im.code = sod.itemCode LEFT JOIN itemBatch ib ON ib.code = sod.ibCode LEFT JOIN subGroup sgd on sgd.subCode = so.distributor LEFT JOIN subGroup sgp on sgp.subCode = so.partyCode LEFT JOIN subGroup sgc on sgc.subCode = so.consignee LEFT JOIN city cd on cd.cityCode = sgd.cityCode LEFT JOIN city cp on cp.cityCode = sgp.cityCode LEFT JOIN city cc on cc.cityCode = sgc.cityCode LEFT JOIN unitMast um on um.code = sod.unitCode LEFT JOIN siteMast sm on sm.code = so.site_Code LEFT JOIN voucher_Type vt on vt.v_Type = so.v_Type LEFT JOIN city C ON C.cityCode = so.deliveryCity LEFT JOIN subGroup sgdt on sgdt.subCode = so.deliveryTo LEFT JOIN ZoneMast ZM ON ZM.Code=sgp.Zone LEFT JOIN packSizeMast psm ON psm.code = sod.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode WHERE vt.category IN (' {…} ',' {…} ', ' {…} ',' {…} ') AND sod.isFooter = 0 {…} {…} ORDER BY {…}`
- Other calls: `PRINT.getValue`, `RptOrderSummaryRegister_Vld.validateInput`, `SYSTEM_ACTIVITY.getName`

### `getGroupArray`
`private String[] getGroupArray(String groupOn)`


### `getParameter`
`private void getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`

### `getTxtHSaleOrderNo`
`public DmResultPageing<List<TxtHSalesOrder_Mdl>> getTxtHSaleOrderNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT so.docId, so.recId, so.v_Date, vt.short_Name ,so.v_No, sg.name as partyName, sd.name as distributorName, sc.name as consigneeName , CASE WHEN so.site_Code = {…} THEN 1 ELSE 2 END as tempSr FROM sOrder AS so left join voucher_Type as vt on vt.v_Type = so.v_Type LEFT JOIN subGroup sg On sg.subCode = so.partyCode LEFT JOIN subGroup sd On sd.subCode = so.distributor LEFT JOIN subGroup sc On sc.subCode = so.consignee LEFT JOIN siteMast sm ON sm.code = so.site_Code where vt.Category IN (' {…} ',' {…} ', ' {…} ',' {…} ') AND ISNULL(so.approvedBy, '') <> '' and so.site_Code in( {…} :siteList {…} )`

### `getTxtHParty`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHParty(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as supplierName , sg.manualCode , c.cityName FROM subGroup sg LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( SELECT DISTINCT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_Type = acf.v_Type WHERE vt.category IN (' {…} ',' {…} ', ' {…} ',' {…} ') )`

### `getTxtHDeliveryCity`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDeliveryCity(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select ct.cityCode, ct.cityName, ct.stateCode, sm.name as stateName from city ct left join stateMast sm on ct.stateCode = sm.code`

### `getTxtHApprovalStatus`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHApprovalStatus(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHReportFor`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHReportFor(DmHelper helper, boolean blnToEndOfFile)`


### `getItemName`
`public DmResultPageing<List<TxtHItem_Mdl>> getItemName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select im.code as itemCode, im.name as itemName ,im.manualCode from itemMast im LEFT JOIN unitmast um ON im.saleUnit = um.code where (im.filterItemType = :filterItemType )`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`

### `getTxtHStatus`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHStatus(DmHelper helper, boolean blnToEndOfFile)`


### `getGroupName`
`public DmResultPageing<List<KeyFieldExtended>> getGroupName(String excludeGroup, DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHZone`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHZone(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ZM.Code, ZM.Name FROM zonemast ZM`

### `getTxtHDistributor`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHDistributor(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as distributorName , sg.manualCode , c.cityName FROM subGroup sg LEFT JOIN city c ON c.cityCode = sg.cityCode WHERE sg.partyCat = 'D'`

## Server `RptSalesOrderRegister_SrvImpl.java` (1136 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.saveLog`, `DmServer.showJasper`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.xNull`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHVoucherType`
`public DmResultPageing<List<TxtHVoucher_Mdl>> getTxtHVoucherType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type as code , vt.description as voucherType , vt.category , vt.short_Name FROM voucher_Type vt WHERE vt.category IN (' {…} ',' {…} ', ' {…} ',' {…} ')`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT {…} as group1Code , {…} as group1Name , {…} as group2Code , {…} as group2Name ,so.docId,so.recId,so.v_Date,sod.quantity AS orderQty ,um.unit as sizeUnitName ,sod.rate ,sod.itemCode ,im.name as itemName ,ptip.name AS partyName ,so.partyOrdNo as partyOrderNo ,ptid.name AS distributorName,vt.short_Name ,ptic.name AS consigneeName,so.v_No ,ptidt.name AS deliveryToName ,so.deliveryCity ,c.cityName ,sod.deliveryDt ,so.amdOrdDocId ,sod.quantity ,sod.itemDesc ,sod.unitCode ,sod.oh_Amt_Taxable ,um.unit,so.partyOrdDt,sod.oh_Amt_Gross, sod.tl_ExcessQty ,sod.tl_ShortageQty , sod.dispatchedSkuQty , CAST(CASE WHEN ISNULL(sod.skuQtyForRate, 0) = 0 THEN 0 ELSE ISNULL(sod.dispatchedSkuQty, 0) / sod.skuQtyForRate END AS DECIMAL(18,7)) AS dispatchedQty ,sod.dispatchedPackCount ,ISNULL(sod.packCount, 0) - ISNULL(sod.dispatchedPackCount, 0) AS pendingPackCount ,CASE WHEN sod.isStatus = 1 AND sod.isAmended = 0 THEN 'Cancelled' WHEN sod.isStatus = 2 THEN 'Completed' WHEN sod.isStatus = 1 AND sod.isAmended = 1 THEN 'Amended' ELSE 'Open' END AS status ,CASE WHEN sod.isStatus = 1 AND sod.isAmended = 0 THEN 'X' WHEN sod.isStatus = 2 THEN 'C' WHEN sod.isStatus = 1 AND sod.isAmended = 1 THEN 'A' ELSE 'O' END AS statusShortName , (ISNULL(sod.skuQty, 0) - ISNULL(sod.dispatchedSkuQty, 0)) AS pendingSkuQty , CAST(CASE WHEN ISNULL(sod.skuQtyForRate, 0) = 0 THEN 0 ELSE (ISNULL(sod.skuQty,0) - ISNULL(sod.dispatchedSkuQty,0)) / sod.skuQtyForRate END AS DECIMAL(18,7)) AS pendingQty , CAST(CASE WHEN (ISNULL(sod.skuQty,0) - ISNULL(sod.freeSkuQty,0)) = 0 THEN 0 ELSE (ISNULL(sod.oh_Amt_Taxable, 0) / (ISNULL(sod.skuQty,0) - ISNULL(sod.freeSkuQty,0))) * ((ISNULL(sod.skuQty,0) - ISNULL(sod.freeSkuQty,0)) - (ISNULL(sod.dispatchedSkuQty,0)-ISNULL(sod.dispatchedFreeSkuQty,0))) END AS DECIMAL(18,2)) AS pendingValue ,ptip.cityName as partyCity ,ptid.cityName as distributerCity ,ptic.cityName as consigneeCity ,um1.unit as sku ,soA.recId AS amendedRecID ,soA.v_Date AS amendedv_Date ,soA.partyOrdNo As amendedPartyOrdNo ,soA.partyOrdDt AS amendedPartyOrdDt ,sod.oh_Amt_Taxable AS taxableValue ,ISNULL(sod.oh_Amt_Discount, 0) AS discountAmt ,sod.oh_at_Discount AS discountAt ,ISNULL(sod.oh_Amt_Net ,0) AS netAmt , ib.propertyDispCsv , umc.unit as cessNaUnitName , CASE WHEN ISNULL(sod.cessEvalTypeCode,0)= {…} THEN ' {…} ' WHEN ISNULL(sod.cessEvalTypeCode,0)= {…} THEN ' {…} ' WHEN ISNULL(sod.cessEvalTypeCode,0)= {…} THEN ' {…} ' end as cessEvalTypeName , ISNULL(sod.oh_Amt_CessNa, 0) AS oh_Amt_CessNa , ISNULL(sod.oh_At_CessNa, 0) AS oh_At_CessNa , ISNULL(sod.oh_Amt_Cess, 0) AS oh_Amt_Cess , ISNULL(sod.oh_At_Cess, 0) AS oh_At_Cess , lm.name as locationName , so.isItc , sod.packCount , sod.packSizeCode , ISNULL(sod.oh_Amt_SchemeDiscount, 0) as oh_Amt_SchemeDiscount , sod.freeQty , psm.name as packSizeName , umpsm.unit as packUnitName FROM sOrder AS so {…} LEFT JOIN sOrderDetail AS sod ON sod.docId = so.docId LEFT JOIN itemMast as im on sod.itemCode = im.code LEFT JOIN itemCat ic ON ic.code = im.itemCategoryCode LEFT JOIN itemGroup ig ON ig.code = im.itemGroup LEFT JOIN unitMast as um on um.code = sod.UnitCode LEFT JOIN unitMast as um1 on um1.code = im.sku LEFT JOIN voucher_Type AS vt ON vt.v_Type = so.v_Type LEFT JOIN partyTransInfo ptip ON ptip.docId = so.docId AND ptip.recordType = :partyRecordType LEFT JOIN partyTransInfo ptidt ON ptidt.docId = so.docId AND ptidt.recordType = :delivieryToRecordType LEFT JOIN partyTransInfo ptid ON ptid.docId = so.docId AND ptid.recordType = :distributorRecordType LEFT JOIN partyTransInfo ptic ON ptic.docId = so.docId AND ptic.recordType = :consigneeRecordType LEFT JOIN city AS c ON c.cityCode = so.deliveryCity LEFT JOIN itemBatch ib on ib.code = sod.ibCode left Join unitMast umc on umc.code = sod.cessNaUnitCode left Join locationMast lm on lm.code = so.locationCode left join packSizeMast psm on psm.code=sod.packSizeCode left join unitMast umpsm on umpsm.code=psm.packUnitCode LEFT JOIN sOrder AS soA ON soA.amdOrdDocId = so.docId WHERE vt.category `
- Params: `:partyRecordType` ← `DmEnum.PartyTransRecordType.PARTY.getCode()`; `:delivieryToRecordType` ← `DmEnum.PartyTransRecordType.DELIVERY_TO_PARTY.getCode()`; `:distributorRecordType` ← `DmEnum.PartyTransRecordType.DISTRIBUTOR.getCode()`; `:consigneeRecordType` ← `DmEnum.PartyTransRecordType.CONSIGNEE.getCode()`
- Other calls: `CONSIGNEE.getCode`, `DELIVERY_TO_PARTY.getCode`, `DISTRIBUTOR.getCode`, `PARTY.getCode`, `PRINT.getValue`, `QUANTITY_BASED.getCode`, `QUANTITY_BASED.getName`, `QUANTITY_VALUE_BASED.getCode`, `QUANTITY_VALUE_BASED.getName`, `RptSalesOrderRegister_Vld.validateInput`, `SYSTEM_ACTIVITY.getName`, `VALUE_BASED.getCode`, `VALUE_BASED.getName`

### `getParameter`
`private void getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`

### `getTxtHSaleOrderNo`
`public DmResultPageing<List<TxtHSalesOrder_Mdl>> getTxtHSaleOrderNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT so.docId, so.recId, so.v_Date , vt.short_Name as recieveType , so.v_No, sg.name as partyName, sd.name as distributorName, sc.name as consigneeName , CASE WHEN so.site_Code = {…} THEN 1 ELSE 2 END as tempSr FROM sOrder AS so left join voucher_Type as vt on vt.v_Type = so.v_Type LEFT JOIN subGroup sg On sg.subCode = so.partyCode LEFT JOIN subGroup sd On sd.subCode = so.distributor LEFT JOIN subGroup sc On sc.subCode = so.consignee LEFT JOIN siteMast sm ON sm.code = so.site_Code where vt.Category IN (' {…} ',' {…} ', ' {…} ',' {…} ') AND ISNULL(so.approvedBy, '') <> '' and so.site_Code in( {…} :siteList {…} )`

### `getTxtHParty`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHParty(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as supplierName , sg.manualCode , c.cityName FROM subGroup sg LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( SELECT DISTINCT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_Type = acf.v_Type WHERE vt.category IN (' {…} ',' {…} '' {…} ',' {…} ') )`

### `getTxtHDeliveryCity`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDeliveryCity(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select ct.cityCode, ct.cityName, ct.stateCode, sm.name as stateName from city ct left join stateMast sm on ct.stateCode = sm.code`

### `getTxtHAmendmentOrder`
`public DmResultPageing<List<TxtHAmendmentOrderNo_Mdl>> getTxtHAmendmentOrder(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `select so.docId, so.recId, so.v_No , so.v_Date, vt.short_Name as vTypeShortName , sg.name as partyName , sd.name as distributorName , sc.name as consigneeName , CASE WHEN so.site_Code = {…} THEN 1 ELSE 2 END as tempSr from sOrder so LEFT JOIN voucher_Type vt ON vt.v_Type = so.v_Type INNER JOIN sOrder soAmd ON soAmd.amdOrdDocId = so.DocId LEFT JOIN subGroup sg On sg.subCode = so.partyCode LEFT JOIN subGroup sd On sd.subCode = so.distributor LEFT JOIN subGroup sc On sc.subCode = so.consignee LEFT JOIN siteMast sm ON sm.code = so.site_Code where so.approvedBy <> '' and vt.category IN (' {…} ',' {…} '' {…} ',' {…} ') and so.site_Code in( {…} :siteList {…} )`

### `getTxtHItemCat`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemCat(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ic.code , ic.name FROM itemCat ic WHERE (ic.filterItemType = :filterItemType or ic.isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ig.code , ig.name FROM itemGroup ig WHERE (ig.filterItemType = :filterItemType or ig.isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getItemName`
`public DmResultPageing<List<TxtHItem_Mdl>> getItemName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select im.code as itemCode, im.name as itemName ,im.manualCode ,it.name as itemTypeName from itemMast im LEFT JOIN unitmast um ON im.saleUnit = um.code Left join itemType it on it.code=im.itemType where (im.filterItemType IN (:FM,:SM) OR im.itemType = :SV)`
- Params: `:FM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:SM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`; `:SV` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SV", "manualCode", "itemType", "code", cnTem`

### `getTxtHStatus`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHStatus(DmHelper helper, boolean blnToEndOfFile)`


### `getGroupName`
`public DmResultPageing<List<KeyFieldExtended>> getGroupName(String excludeGroup, DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHReportFor`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHReportFor(DmHelper helper, boolean blnToEndOfFile)`


## Server `RptGSTSaleRegister_SrvImpl.java` (956 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnId`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.removeAllCharacterExceptAlphaNumeric`, `DmServer.setReportHeaderExcel`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `execute`
`public DmResult<KeyFieldExtended> execute(String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT sg.gsTin AS {…} , '' AS {…} , sg.name AS {…} , CONCAT(FORMAT(stm.numericCode, '00'), '-', stm.name) AS {…} , CONCAT(FORMAT(stmpos.numericCode, '00'), '-', stmpos.name) AS {…} , gt.refRecId AS {…} , gt.refV_Date AS {…} , gt.recId AS {…} , gt.v_Date AS {…} , hm.HSN AS {…} , gum.name AS {…} , IIF(ISNULL(hm.nature,0)=1, 'Goods', 'Service') AS {…} , {…} AS {…} , {…} AS {…} --DIFFERENTIAL VALUE FOR WHICH CR/DB NOTE ISSUED , gt.itemIgstRate AS {…} , {…} AS {…} , gt.itemCgstRate AS {…} , {…} AS {…} , gt.itemSgstRate AS {…} , {…} AS {…} , gt.itemUgstRate AS {…} , {…} AS {…} , gt.itemCessRate AS {…} , {…} AS {…} , {…} AS {…} , gt.gstDocNature AS {…} -- ENUM VALUE , IIF(ISNULL(gt.rcmApplicable,0) =1,'Yes', 'No') AS {…} , '' AS {…} , grm.name AS {…} , 'No' AS {…} , '' AS {…} , CAST(NULL AS DATE) AS {…} , 'No' AS {…} , eg.einv_SupplyType AS {…} , CASE WHEN ISNULL(gt.itemGSTNature, '') IN ('N','E','L') THEN gt.itemGSTNature END AS {…} , eg.einv_IrnNo AS {…} , eg.einv_ackNo AS {…} , eg.einv_ackDt AS {…} FROM gstTrans gt LEFT JOIN eGst eg ON eg.docid = gt.docId LEFT JOIN voucher_Type vt ON vt.v_Type = gt.v_type LEFT JOIN subGroup sg ON sg.subCode = gt.partyCode LEFT JOIN stateMast stmpos ON stmpos.code = gt.placeOfSupply LEFT JOIN city cpc ON cpc.cityCode = sg.cityCode LEFT JOIN stateMast stm ON stm.code = cpc.stateCode LEFT JOIN hsnMast hm ON hm.code = gt.hsnCode LEFT JOIN gstReasonMast grm ON grm.code = gt.gstReasonCode LEFT JOIN gstUnitMast gum ON gum.code = gt.gstUnitCode WHERE 1=1 {…} ORDER BY gt.v_Date, vt.short_Name, gt.recIdSerial, gt.recId, gt.v_Sno`
- Errors: "Invalid request." | "File Name not given."
- Other calls: `CREDIT_NOTE.getShortName`, `DEBIT_NOTE.getShortName`, `OUTPUT.toInt`, `RptGSTSaleRegister_Vld.validateInput`

### `createExcelReport`
`private KeyFieldExtended createExcelReport(String widgetName, String sessionId, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, ResultSet rsTemp, int totalColumns) throws Exception`

- Errors: "No record found!"
- Other calls: `ExcelReport_Server.createAndMergeBlankRows`, `ExcelReport_Server.createAndMergeCells`, `ExcelReport_Server.getCellStyle`, `ExcelReport_Server.getCellStyleHeader`, `ExcelReport_Server.getDataFormat`, `ExcelReport_Server.newCell`, `ExcelReport_Server.printFilterData`, `GstDocumentNature.getViaShortName`

### `getParameter`
`private String getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHParty`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHParty(String partyTypeCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vGt.partyCode AS code, sg.name, sg.manualCode, c.cityName FROM ( SELECT DISTINCT gt.partyCode FROM gstTrans gt WHERE gt.gstType= {…} ) vGt LEFT JOIN subGroup sg ON sg.subCode = vGt.partyCode LEFT JOIN city c ON c.cityCode = sg.cityCode`
- Other calls: `OUTPUT.toInt`

### `getTxtHPartyType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHPartyType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `DmPartyType.values`

### `getTxtHVType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVType(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT vGt.v_Type AS code, vt.description AS description, vt.short_Name as shortName FROM ( SELECT DISTINCT gt.v_Type FROM gstTrans gt WHERE gt.gstType= {…} ) vGt LEFT JOIN voucher_Type vt ON vt.v_Type = vGt.v_Type`
- Other calls: `OUTPUT.toInt`

### `getReportFor`
`public DmResultPageing<List<KeyFieldExtended>> getReportFor(DmHelper helper, boolean blnToEndOfFile)`


### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

## Server `RptSalesSummaryRegister_SrvImpl.java` (1941 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnEndDate`, `DmServer.getSsnId`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.getSsnStartDate`, `DmServer.removeAllCharacterExceptAlphaNumeric`, `DmServer.setReportHeaderExcel`, `DmShared.convertDate`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `execute`
`public DmResult<KeyFieldExtended> execute(String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT {…} AS {…} , {…} AS group1Name, {…} AS group2Name {…} FROM sBill sb LEFT JOIN sBillDetail sbd ON sbd.docId = sb.docId LEFT JOIN siteMast sm ON sm.code = sb.site_Code LEFT JOIN subGroup sg ON sg.subCode = sb.partyCode LEFT JOIN partyTransInfo AS pti ON pti.docId = sb.docId AND pti.recordType = 1 LEFT JOIN subGroup sgdt ON sgdt.subCode = sb.deliveryTo LEFT JOIN city cdc ON cdc.cityCode = sb.deliveryCity LEFT JOIN stateMast stm ON stm.code = sb.placeOfSupply LEFT JOIN subGroup AS sgd ON sgd.subCode = sb.distributor LEFT JOIN subGroup AS sgc ON sgc.subCode = sb.consignee LEFT JOIN voucher_Type vt ON vt.v_Type = sb.v_Type LEFT JOIN zoneMast zm ON zm.code = sg.zone LEFT JOIN areaMast am ON am.code = sg.areaCode LEFT JOIN itemMast im ON im.code = sbd.itemCode LEFT JOIN unitMast um ON um.code = sbd.unitCode LEFT JOIN packSizeMast psm ON psm.code = sbd.packSizeCode LEFT JOIN unitMast ump ON ump.code = psm.packUnitCode LEFT JOIN itemCat ic ON ic.code = im.itemCategoryCode LEFT JOIN itemGroup ig ON ig.code = im.itemGroup LEFT JOIN itemBatch as ib on ib.code = sbd.ibCode LEFT JOIN itemMakeMast imk ON imk.code = ib.itemMakeCode WHERE vt.category IN (:SI,:SIO,:SR, :SRO, :ESI, :EGSI, :ESR, :EGSR, :SIU) AND ISNULL(sb.approvedBy, '') <> '' AND ISNULL(sb.cancelledBy, '') = '' {…} GROUP BY {…} ORDER BY {…}`
- Errors: "From Date should be greater than or equal to " | "To Date should be less than or equal to " | "Invalid request." | "File Name not given."
- Other calls: `RptSalesSummaryRegister_Vld.validateInput`

### `createExcelReport`
`private KeyFieldExtended createExcelReport(String widgetName, String sessionId, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, ResultSet rsTemp, int totalColumns) throws Exception`

- Errors: "No record found!"
- Other calls: `ExcelReport_Server.createAndMergeBlankRows`, `ExcelReport_Server.createAndMergeCells`, `ExcelReport_Server.getAllMonthsWithYear`, `ExcelReport_Server.getCellStyle`, `ExcelReport_Server.getCellStyleHeader`, `ExcelReport_Server.getDataFormat`, `ExcelReport_Server.getQuartersWithYear`, `ExcelReport_Server.newCell`, `ExcelReport_Server.printFilterData`

### `getGroupOn`
`private String[] getGroupOn(String groupOn)`


### `getParameter`
`private void getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:SI` ← `pvc.SALES_INVOICE`; `:SIO` ← `pvc.GENERAL_SALES_INVOICE`; `:SR` ← `pvc.SALES_RETURN`; `:SRO` ← `pvc.GENERAL_SALES_RETURN`; `:SIU` ← `pvc.GENERAL_SALES_INVOICE_UNSTOCKABLE`; `:ESI` ← `pvc.EX_SALES_INVOICE`; `:EGSI` ← `pvc.EX_GENERAL_SALES_INVOICE`; `:ESR` ← `pvc.EX_SALES_RETURN`; `:EGSR` ← `pvc.EX_GENERAL_SALES_RETURN`; `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `getTxtHInvoiceNo`
`public DmResultPageing<List<TxtHInvoiceNo_Mdl>> getTxtHInvoiceNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select sb.docId, sb.recId, sb.v_Date, sb.v_No, sb.v_Time, sb.v_Prefix , vt.short_Name, sg.name as partyName, sd.name as distributorName , sc.name as consigneeName, sm.manualCode AS siteManualCode , CASE WHEN sb.site_Code = {…} THEN 1 ELSE 2 END as tempSr FROM sBill sb LEFT JOIN voucher_Type vt On vt.v_Type = sb.v_Type LEFT JOIN subGroup sg On sg.subCode = sb.partyCode LEFT JOIN subGroup sd On sd.subCode = sb.distributor LEFT JOIN subGroup sc On sc.subCode = sb.consignee LEFT JOIN siteMast sm ON sm.code = sb.site_Code WHERE vt.category IN (' {…} ',' {…} ',' {…} ',' {…} ', ' {…} ') AND sb.site_Code IN( {…} :siteList {…} ) AND ISNULL(sb.approvedBy,'')<>'' AND ISNULL(sb.cancelledBy, '') = ''`

### `getTxtHSalesReturnNo`
`public DmResultPageing<List<TxtHReturnNo_Mdl>> getTxtHSalesReturnNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select sb.docId, sb.recId, sb.v_Date, sb.v_No, sb.v_Time, sb.v_Prefix , vt.short_name, sg.name as partyName, sd.name as distributorName , sc.name as consigneeName, sm.manualCode AS siteManualCode , CASE WHEN sb.site_Code = {…} THEN 1 ELSE 2 END as tempSr FROM sBill sb LEFT JOIN voucher_Type vt On vt.v_Type = sb.v_Type LEFT JOIN subGroup sg On sg.subCode = sb.partyCode LEFT JOIN subGroup sd On sd.subCode = sb.distributor LEFT JOIN subGroup sc On sc.subCode = sb.consignee LEFT JOIN siteMast sm ON sm.code = sb.site_Code WHERE vt.category IN (:SR,:SRO, :ESR, :EGSR) AND sb.site_Code IN( {…} :siteList {…} ) AND ISNULL(sb.approvedBy,'')<>'' AND ISNULL(sb.cancelledBy, '') = ''`
- Params: `:SR` ← `pvc.SALES_RETURN`; `:SRO` ← `pvc.GENERAL_SALES_RETURN`; `:ESR` ← `pvc.EX_SALES_RETURN`; `:EGSR` ← `pvc.EX_GENERAL_SALES_RETURN`

### `getTxtHParty`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHParty(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as supplierName , sg.manualCode , c.cityName FROM subGroup sg LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( SELECT DISTINCT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_Type = acf.v_Type WHERE vt.category IN (' {…} ',' {…} ', ' {…} ',' {…} ', ' {…} ') )`

### `getTxtHDistributor`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHDistributor(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as name , sg.manualCode , c.cityName FROM subGroup sg LEFT JOIN city c ON c.cityCode = sg.cityCode WHERE sg.partyCat= :partyCat`
- Params: `:partyCat` ← `DmEnum.PartyCategory.DISTRIBUTOR.getId()`
- Other calls: `DISTRIBUTOR.getId`

### `getItemName`
`public DmResultPageing<List<TxtHItem_Mdl>> getItemName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select im.code as itemCode, im.name as itemName ,im.manualCode from itemMast im LEFT JOIN unitmast um ON im.saleUnit = um.code where (im.filterItemType = :filterItemType )`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemGroup(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `select ig.code, ig.name from itemGroup ig`

### `getTxtHItemCategory`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemCategory(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `select ic.code, ic.name from itemCat ic`

### `getTxtHZone`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHZone(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `select zm.code, zm.name from zoneMast zm`

### `getTxtHArea`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHArea(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT am.code, am.name FROM areaMast am`

### `getTxtHVoucherType`
`public DmResultPageing<List<TxtHVoucher_Mdl>> getTxtHVoucherType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type as code , vt.description as voucherType , vt.category , vt.short_Name FROM voucher_Type vt WHERE vt.category IN (' {…} ',' {…} ', ' {…} ',' {…} ', ' {…} ')`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getReportFor`
`public DmResultPageing<List<KeyFieldExtended>> getReportFor(DmHelper helper, boolean blnToEndOfFile)`


### `getGroupBy`
`public DmResultPageing<List<KeyFieldExtended>> getGroupBy(String tag1, String tag2, DmHelper helper, boolean blnToEndOfFile)`


### `getSummaryType`
`public DmResultPageing<List<KeyFieldExtended>> getSummaryType(DmHelper helper, boolean blnToEndOfFile)`


## Server `RptSalesRegister_SrvImpl.java` (1322 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.saveLog`, `DmServer.showJasper`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.xNull`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHInvoiceNo`
`public DmResultPageing<List<TxtHInvoiceNo_Mdl>> getTxtHInvoiceNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select sb.docId as code, sb.recId as name, sb.v_No ,sb.v_Date as invoiceDate ,sgp.name as partyName , vt.short_Name as invoiceType , c.cityName as city , CASE WHEN sb.site_Code = {…} THEN 1 ELSE 2 END as tempSr from sBill sb LEFT JOIN voucher_Type vt ON vt.v_Type = sb.v_Type LEFT JOIN subGroup sgp ON sgp.subCode = sb.partyCode left join city as c on c.cityCode = sgp.cityCode LEFT JOIN siteMast sm ON sm.code = sb.site_Code WHERE vt.category IN (' {…} ',' {…} ', ' {…} ',' {…} ', ' {…} ') and sb.site_Code in( {…} :siteList {…} )`

### `getTxtHParty`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHParty(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as supplierName , sg.manualCode , c.cityName FROM subGroup sg LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( SELECT DISTINCT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_Type = acf.v_Type WHERE vt.category IN (' {…} ',' {…} ', ' {…} ',' {…} ', ' {…} ') )`

### `getTxtHDeliveryCity`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDeliveryCity(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select ct.cityCode, ct.cityName, ct.stateCode, sm.name as stateName from city ct left join stateMast sm on ct.stateCode = sm.code`

### `getTxtHGateNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGateNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select gio.docId as gateDocId ,gio.v_Date as gateInDate ,gio.v_Time as gateInTime ,vt.short_Name ,gio.v_No ,gio.recId , CASE WHEN gio.site_Code = {…} THEN 1 ELSE 2 END as tempSr from gateInOut gio left join voucher_type vt on vt.v_Type= gio.v_Type LEFT JOIN siteMast sm ON sm.code = gio.site_Code where gio.site_Code in( {…} :siteList {…} )`

### `getTxtHTransporter`
`public DmResultPageing<List<TxtTransporters_Mdl>> getTxtHTransporter(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code, sg.name, sg.manualCode, c.cityName FROM subGroup sg LEFT JOIN city c ON c.cityCode = sg.cityCode WHERE sg.nature = :nature`
- Params: `:nature` ← `DmServer.getCodeViaManualCode("TP", "manualCode", "subGroupNature", "code", cnTemp)`

### `getTxtHTransportationMode`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTransportationMode(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `TransportMode.values`

### `getItemName`
`public DmResultPageing<List<TxtHItem_Mdl>> getItemName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select im.code as itemCode, im.name as itemName ,im.manualCode ,it.name as itemTypeName from itemMast im LEFT JOIN unitmast um ON im.saleUnit = um.code Left join itemType it on it.code=im.itemType where (im.filterItemType IN (:FM,:SM) OR im.itemType = :SV)`
- Params: `:FM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:SM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`; `:SV` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SV", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemCat`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemCat(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ic.code , ic.name FROM itemCat ic WHERE (ic.filterItemType = :filterItemType or ic.isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ig.code , ig.name FROM itemGroup ig WHERE (ig.filterItemType = :filterItemType or ig.isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHPolicyNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHPolicyNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select sb.policyCode, pm.name ,pm.insuranceComp from sBill sb left join policyMast pm on pm.code = policyCode`

### `getTxtHShowServices`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHShowServices(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHEwayBillReq`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHEwayBillReq(DmHelper helper, boolean blnToEndOfFile)`


### `getGroupName`
`public DmResultPageing<List<KeyFieldExtended>> getGroupName(String excludeGroup, DmHelper helper, boolean blnToEndOfFile)`


### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT {…} as group1Code , {…} as group1Name , {…} as group2Code , {…} as group2Name ,sb.docId, sb.v_Type, vt.description AS voucherDescriptionName, vt.short_Name AS v_shortName, sb.recId AS saleInvoiceNo, sb.v_Date, ptip.name AS partyName, ptid.name AS distributorName, ptic.name AS consigneeName, cpos.cityName AS toPlace, ptidt.name AS deliveryToName, sb.remark, sb.isSupplementary, sb.refDate, sb.refNo, sb.vehicleNo, ptitc.name AS transporterName, sb.lrNo, sb.lrDate, sb.isEWayBillRequired, sb.eWayBillDt, sb.eWayBillNo, gtio.recId AS gateNo, pm.name AS policyName, im.name AS itemName, sbd.itemCode, sbd.quantity saleQty, sbd.skuQty, um1.unit AS skuUnitName, im.itsBasedOn itsBasedOn, so.recID AS soRecID, so.v_Date As soDate, um2.unit AS saleUnitName, sgdc.name AS departmentName, sbd.rate, sbd.oh_Amt_Gross, sbd.landedSkuValue, Case When sb.transportMode = {…} then ' {…} ' When sb.transportMode = {…} then ' {…} ' When sb.transportMode = {…} then ' {…} ' When sb.transportMode = {…} then ' {…} ' ELSE '' END AS transportModeName, ptip.cityName AS partyCityName, ptid.cityName AS distributorCityName, ptic.cityName AS consigneeCityName, ptidt.cityName AS deliveryToCityName, ptip.stateName AS partyStateName, ptid.stateName AS distributorStateName, ptic.stateName AS consigneeStateName, ptidt.stateName AS deliveryToStateName, ISNULL(sbd.oh_Amt_CGST ,0) as oh_Amt_CGST , ISNULL(sbd.oh_Amt_IGST ,0) as oh_Amt_IGST , ISNULL(sbd.oh_Amt_SGST ,0) as oh_Amt_SGST , ISNULL(sbd.oh_Amt_UGST ,0) as oh_Amt_UGST , ISNULL(sbd.oh_Amt_Discount ,0) as oh_Amt_Discount , sbd.oh_Amt_Net , sbd.oh_Amt_Taxable , im.itemDesc, hm.name as hsnName, hm.hsn , ib.propertyDispCsv , umc.unit as cessNaUnitName , CASE WHEN ISNULL(sbd.cessEvalTypeCode,0)= {…} THEN ' {…} ' WHEN ISNULL(sbd.cessEvalTypeCode,0)= {…} THEN ' {…} ' WHEN ISNULL(sbd.cessEvalTypeCode,0)= {…} THEN ' {…} ' end as cessEvalTypeName , ISNULL(sbd.oh_Amt_CessNa, 0) AS oh_Amt_CessNa , ISNULL(sbd.oh_At_CessNa, 0) AS oh_At_CessNa , ISNULL(sbd.oh_Amt_Cess, 0) AS oh_Amt_Cess , ISNULL(sbd.oh_At_Cess, 0) AS oh_At_Cess , lm.name as locationName , sb.isItc , sbd.packCount , sbd.packSizeCode , ISNULL(sbd.oh_Amt_SchemeDiscount ,0) as oh_Amt_SchemeDiscount , sbd.freeQty , psm.name as packSizeName , umpsm.unit as packUnitName , ISNULL(sb.oh_Amt_Deduction,0) as deductuionAmtHead , sbd.ded1_Head ,sbd.ded2_Head,sbd.ded3_Head,sbd.ded4_Head,sbd.ded5_Head , sbd.ded1_Per ,sbd.ded2_Per,sbd.ded3_Per,sbd.ded4_Per,sbd.ded5_Per , sbd.ded1_Amt ,sbd.ded2_Amt,sbd.ded3_Amt,sbd.ded4_Amt,sbd.ded5_Amt , ISNULL(sbd.oh_Amt_Deduction,0) as deductionAmtLine , sb.oh_Amt_FinalDiscount FROM sbill sb {…} LEFT JOIN voucher_Type vt ON vt.v_Type = sb.v_Type LEFT JOIN sBillDetail sbd on sbd.docId = sb.docId LEFT JOIN subGroup sgp ON sgp.subCode = sb.partyCode LEFT JOIN partyTransInfo ptip ON ptip.docId = sb.docId AND ptip.recordType = :partyRecordType LEFT JOIN partyTransInfo ptidt ON ptidt.docId = sb.docId AND ptidt.recordType = :delivieryToRecordType LEFT JOIN partyTransInfo ptid ON ptid.docId = sb.docId AND ptid.recordType = :distributorRecordType LEFT JOIN partyTransInfo ptic ON ptic.docId = sb.docId AND ptic.recordType = :consigneeRecordType LEFT JOIN partyTransInfo ptitc ON ptitc.docId = sb.docId AND ptitc.recordType = :transporterRecordType LEFT JOIN city cpos ON cpos.cityCode = sb.placeOfSupply LEFT JOIN gateInOut gtIo ON gtIo.docId = sb.gateDocId LEFT JOIN policyMast pm ON pm.code = sb.policyCode LEFT JOIN itemBatch ib ON ib.code = sbd.ibCode LEFT JOIN sOrder so ON so.docId = sbd.soDocId LEFT JOIN itemMast im ON im.code = sbd.itemCode LEFT JOIN itemCat ic ON ic.code = im.itemCategoryCode LEFT JOIN itemGroup ig ON ig.code = im.itemGroup LEFT JOIN unitMast um1 ON um1.code = im.sku LEFT JOIN unitMast um2 ON um2.code = im.saleUnit LEFT JOIN subGroup sgdc ON sgdc.subCode = sbd.departmentCode LEFT JOIN zoneMast zm ON zm.code = sgp.zone left join hsnMast as hm on hm.code=sbd.hsnCode left Join unitMast umc on umc.code = sbd.cessNaUnitCode left Join loc`
- Params: `:partyRecordType` ← `DmEnum.PartyTransRecordType.PARTY.getCode()`; `:delivieryToRecordType` ← `DmEnum.PartyTransRecordType.DELIVERY_TO_PARTY.getCode()`; `:distributorRecordType` ← `DmEnum.PartyTransRecordType.DISTRIBUTOR.getCode()`; `:consigneeRecordType` ← `DmEnum.PartyTransRecordType.CONSIGNEE.getCode()`; `:transporterRecordType` ← `DmEnum.PartyTransRecordType.TRANSPORTER.getCode()`
- Other calls: `BY_AIR.getId`, `BY_AIR.getValue`, `BY_RAIL.getId`, `BY_RAIL.getValue`, `BY_ROAD.getId`, `BY_ROAD.getValue`, `BY_SHIP.getId`, `BY_SHIP.getValue`, `CONSIGNEE.getCode`, `DELIVERY_TO_PARTY.getCode`, `DISTRIBUTOR.getCode`, `PARTY.getCode`, `PRINT.getValue`, `QUANTITY_BASED.getCode`, `QUANTITY_BASED.getName`, `QUANTITY_VALUE_BASED.getCode`, `QUANTITY_VALUE_BASED.getName`, `RptSalesRegister_Vld.validateInput`, `SYSTEM_ACTIVITY.getName`, `TRANSPORTER.getCode`, `VALUE_BASED.getCode`, `VALUE_BASED.getName`

### `getParameter`
`private void getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`

### `getTxtHHSN`
`public DmResultPageing<List<TxtHHSN_Mdl>> getTxtHHSN(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select hm.code, hm.name, hm.hsn from hsnMast hm`

### `getTxtHRecordType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHRecordType(DmHelper helper, boolean blnToEndOfFile)`


## Server `RptMonthlySalesOrderReport_SrvImpl.java` (2383 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnEndDate`, `DmServer.getSsnId`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.getSsnStartDate`, `DmServer.removeAllCharacterExceptAlphaNumeric`, `DmServer.setReportHeaderExcel`, `DmShared.convertDate`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `execute`
`public DmResult<KeyFieldExtended> execute(String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `WITH cteSales AS ( SELECT {…} AS {…} , {…} AS group1Name, {…} AS group2Name {…} , MAX(sizeUM.unit) AS sizeUnitName FROM sOrder so LEFT JOIN sOrderDetail sod ON sod.docId = so.docId LEFT JOIN siteMast sm ON sm.code = so.site_Code LEFT JOIN subGroup sg ON sg.subCode = so.partyCode LEFT JOIN partyTransInfo AS pti ON pti.docId = so.docId AND pti.recordType = 1 LEFT JOIN subGroup sgdt ON sgdt.subCode = so.deliveryTo LEFT JOIN city cdc ON cdc.cityCode = so.deliveryCity LEFT JOIN stateMast stm ON stm.code = so.placeOfSupply LEFT JOIN subGroup AS sgd ON sgd.subCode = so.distributor LEFT JOIN subGroup AS sgc ON sgc.subCode = so.consignee LEFT JOIN subGroup sgs ON sgs.subCode = so.salesmanCode LEFT JOIN voucher_Type vt ON vt.v_Type = so.v_Type LEFT JOIN zoneMast zm ON zm.code = sg.zone LEFT JOIN areaMast am ON am.code = sg.areaCode LEFT JOIN itemMast im ON im.code = sod.itemCode LEFT JOIN unitMast um ON um.code = sod.unitCode LEFT JOIN unitMast sizeUM ON sizeUM.code = :sizeUnitCode LEFT JOIN packSizeMast psm ON psm.code = sod.packSizeCode LEFT JOIN unitMast ump ON ump.code = psm.packUnitCode LEFT JOIN itemCat ic ON ic.code = im.itemCategoryCode LEFT JOIN itemGroup ig ON ig.code = im.itemGroup LEFT JOIN itemBatch as ib on ib.code = sod.ibCode LEFT JOIN itemNature itn ON itn.code = ib.itemNature LEFT JOIN itemMakeMast imk ON imk.code = ib.itemMakeCode WHERE vt.category IN (:SO, :GSO) AND ISNULL(so.approvedBy, :approvedBy) <> '' AND sod.isFooter = :isFooter {…} GROUP BY {…} )`
- SQL 2: `SELECT {…} , cs.group1Name, cs.group2Name ,sizeUnitName`
- Params: `:sizeUnitCode` ← `DmShared.xNull(filter.get("sizeUnit").getTag())`
- Errors: "From Date should be greater than or equal to " | "To Date should be less than or equal to " | "Invalid request." | "File Name not given."
- Other calls: `CM.getName`, `INCH.getName`, `MM.getName`, `Q1Amt.getSqlFieldAliasName`, `Q1Qty.getSqlFieldAliasName`, `Q2Amt.getSqlFieldAliasName`, `Q2Qty.getSqlFieldAliasName`, `Q3Amt.getSqlFieldAliasName`, `Q3Qty.getSqlFieldAliasName`, `Q4Amt.getSqlFieldAliasName`, `Q4Qty.getSqlFieldAliasName`, `RptMonthlySalesOrderReport_Vld.validateInput`

### `createExcelReport`
`private KeyFieldExtended createExcelReport(String widgetName, String sessionId, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, ResultSet rsTemp, int totalColumns) throws Exception`

- Errors: "No record found!"
- Other calls: `ExcelReport_Server.createAndMergeBlankRows`, `ExcelReport_Server.createAndMergeCells`, `ExcelReport_Server.getAllMonthsWithYear`, `ExcelReport_Server.getCellStyle`, `ExcelReport_Server.getCellStyleHeader`, `ExcelReport_Server.getDataFormat`, `ExcelReport_Server.newCell`, `ExcelReport_Server.printFilterData`

### `getParameter`
`private void getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:approvedBy` ← `""`; `:isFooter` ← `0`; `:SO` ← `pvc.SALES_ORDER`; `:GSO` ← `pvc.GENERAL_SALES_ORDER`; `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- SQL 2: `SELECT ec.prod_SizeUnitCode AS sizeUnitCode, um.unit AS sizeUnitName FROM enviroCompany ec LEFT JOIN unitMast um ON um.code = ec.prod_SizeUnitCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHVoucherType`
`public DmResultPageing<List<TxtHVoucher_Mdl>> getTxtHVoucherType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type as code , vt.description as voucherType , vt.category , vt.short_Name FROM voucher_Type vt WHERE vt.category IN (:SO, :GSO)`
- Params: `:SO` ← `pvc.SALES_ORDER`; `:GSO` ← `pvc.GENERAL_SALES_ORDER`

### `getTxtHVoucherNo`
`public DmResultPageing<List<TxtHVoucherNo_Mdl>> getTxtHVoucherNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT so.docId , so.recId , so.v_Date , so.v_No , so.v_Prefix , vt.short_Name , sg.name as partyName , CONCAT(sg.name, ' - ', ct.cityName) AS PartyNameWithCity , sm.manualCode AS siteManualCode , CASE WHEN so.site_Code = {…} THEN 1 ELSE 2 END as tempSr FROM sOrder so LEFT JOIN voucher_Type vt On vt.v_Type = so.v_Type LEFT JOIN siteMast sm ON sm.code = so.site_Code LEFT JOIN subGroup sg ON sg.subCode = so.partyCode LEFT JOIN city ct ON ct.cityCode = so.deliveryCity WHERE vt.category IN (:SO, :GSO) AND so.site_Code IN( {…} :siteList {…} ) AND ISNULL(so.approvedBy,'')<>''`
- Params: `:SO` ← `pvc.SALES_ORDER`; `:GSO` ← `pvc.GENERAL_SALES_ORDER`

### `getTxtHParty`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHParty(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code, sg.name as partyName, c.cityName, sg.manualCode FROM subGroup sg LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( SELECT DISTINCT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_Type = acf.v_Type WHERE vt.category IN (:SO, :GSO) )`
- Params: `:SO` ← `pvc.SALES_ORDER`; `:GSO` ← `pvc.GENERAL_SALES_ORDER`

### `getTxtHConsignee`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHConsignee(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code, sg.name as consignee, c.cityName, sg.manualCode FROM subGroup sg LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( SELECT DISTINCT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_Type = acf.v_Type WHERE vt.category IN (:SO, :GSO) )`
- Params: `:SO` ← `pvc.SALES_ORDER`; `:GSO` ← `pvc.GENERAL_SALES_ORDER`

### `getItemName`
`public DmResultPageing<List<TxtHItem_Mdl>> getItemName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code as itemCode , it.name AS itemType , im.name as itemName , im.manualCode FROM itemMast im LEFT JOIN unitmast um ON im.saleUnit = um.code LEFT JOIN itemType it ON it.code= im.itemType WHERE im.filterItemType IN (:FM,:SM)`
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


### `getTxtHSalesman`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHSalesman(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ec.srDesignationCode FROM enviroCompany ec`
- SQL 2: `SELECT sg.subCode AS code , sg.name AS salesman , sg.manualCode , c.cityName , c.cityCode FROM subGroup sg LEFT JOIN subGroup2 sg2 ON sg2.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode WHERE sg2.designationCode = {…}`

### `getTxtHArea`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHArea(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT am.code, am.name FROM areaMast am`

### `getHBF`
`public DmResultPageing<List<KeyFieldExtended>> getHBF(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT bf.code, bf.code as name FROM bfMast bf WHERE bf.isActive = 1`

### `getHGSM`
`public DmResultPageing<List<KeyFieldExtended>> getHGSM(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT gm.code, gm.code as name FROM gsmMast gm WHERE gm.isActive = 1`

### `getHPeriodicity`
`public DmResultPageing<List<KeyFieldExtended>> getHPeriodicity(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHPrintProperties`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHPrintProperties(DmHelper helper, boolean blnToEndOfFile)`


### `getHSizeUnit`
`public DmResultPageing<List<KeyFieldExtended>> getHSizeUnit(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT um.code, um.unit AS sizeUnitName FROM unitMast um WHERE um.nature = :nature AND um.unit IN (:inch, :cm, :mm)`
- Params: `:nature` ← `STEnum.UnitNature.LENGTH.getId()`; `:inch` ← `STEnum.UnitMaster.INCH.getName()`; `:cm` ← `STEnum.UnitMaster.CM.getName()`; `:mm` ← `STEnum.UnitMaster.MM.getName()`
- Other calls: `CM.getName`, `INCH.getName`, `LENGTH.getId`, `MM.getName`

### `getQuartersWithYears`
`public static String[] getQuartersWithYears(Date startDate, boolean financialMonthOrder, boolean shortYear)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.client`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmBase.self.xls`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmReportLayout.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.excelReport.server`, `dataman.dmToolkit.excelReport.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.sales.reports.shared`, `paperbiz.sales.salesDetailDisplay.shared`, `paperbiz.sales.salesSummaryDisplay.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| execute | `if (!DmSession.getSessions().containsKey(sessionId))` | "Invalid request." |
| execute | `if (DmShared.xNull(fileName).equalsIgnoreCase(""))` | "File Name not given." |
| createExcelReport | `if (status == false)` | "No record found!" |
| createExcelReport | `if (!DmShared.xNull(group2Text).trim().equalsIgnoreCase(""))` | "No record found!" |
| createExcelReport | `if (!DmSession.getSessions().containsKey(sessionId))` | "Invalid request." |
| createExcelReport | `if (DmShared.xNull(fileName).equalsIgnoreCase(""))` | "File Name not given." |
| execute | `if (DmShared.convertDate(filter.get("fromDate").getText()).getTime() < DmShared.convertDate(DmServer.getSsnStartDate(this)).getTime())` | "From Date should be greater than or equal to " |
| execute | `if (DmShared.convertDate(filter.get("toDate").getText()).getTime() > DmShared.convertDate(DmServer.getSsnEndDate(this)).getTime())` | "To Date should be less than or equal to " |
| execute | `if (dateDiff > 365)` | "Date difference should be less than or equal to 365 days" |
| createExcelReport | `if (rsTemp.next())` | "No record found!" |

## Notes (hand-written)
_No hand-written notes yet._
