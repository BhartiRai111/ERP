# dm-toolkit / gst

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/gst/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: gstTrans, gstTransHead, eGst, eGstDetail, eGstContractDetail, eGstDocDetail, eGstToken
- Update tables: gstTrans, gstTransHead
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: -
- CSV export: no

## Files
| file | lines |
|---|---|
| server/EGstApiCall.java | 477 |
| server/EinvoiceMongo_Server.java | 149 |
| server/Gst_Server.java | 3114 |
| shared/EGstDetail_Mdl.java | 393 |
| shared/EGstParam_Mdl.java | 586 |
| shared/EGstToken_Mdl.java | 76 |
| shared/EGst_Mdl.java | 1074 |
| shared/EInvCancelData_Mdl.java | 32 |
| shared/EInvCancelResponse_Mdl.java | 59 |
| shared/EInvData_Mdl.java | 147 |
| shared/EInvDocDtls_Mdl.java | 47 |
| shared/EInvEwbDtlsResponse_Mdl.java | 96 |
| shared/EInvItemDtls_Mdl.java | 327 |
| shared/EInvTranDtls_Mdl.java | 67 |
| shared/EInvValDtls_Mdl.java | 127 |
| shared/EInvoice_Mdl.java | 58 |
| shared/EWayViaIrnResponse_Mdl.java | 58 |
| shared/EinvAdditionalDocDtls_Mdl.java | 62 |
| shared/EinvCancelInfoDtls_Mdl.java | 37 |
| shared/EinvExportDtls_Mdl.java | 87 |
| shared/EinvItmAttributeDtls_Mdl.java | 39 |
| shared/EinvItmBatchDtls_Mdl.java | 48 |
| shared/EinvPayeeDtls_Mdl.java | 128 |
| shared/EinvRefContractDtls_Mdl.java | 115 |
| shared/EinvRefDocPeriodDetail_Mdl.java | 38 |
| shared/EinvRefDtls_Mdl.java | 59 |
| shared/EinvRefPrecedingDocDtls_Mdl.java | 47 |
| shared/EinvoiceMongo_Mdl.java | 132 |
| shared/ErrorDetails_Mdl.java | 36 |
| shared/Error_Mdl.java | 33 |
| shared/EwayCancelRequest_Mdl.java | 44 |
| shared/EwayCancelResponse_Mdl.java | 33 |
| shared/Eway_Mdl.java | 57 |
| shared/ExtractedSignedInvoiceData_Mdl.java | 187 |
| shared/ExtractedSignedQrCode_Mdl.java | 107 |
| shared/GSTS_Mdl.java | 277 |
| shared/GstDocument_Mdl.java | 93 |
| shared/GstTrans_Mdl.java | 706 |
| shared/Gst_Shared.java | 31 |
| shared/MasterEGst_Mdl.java | 41 |
| shared/Party_Mdl.java | 231 |

## Models (shared DTOs)
### EinvItmAttributeDtls_Mdl
| type | field |
|---|---|
| String | nm |
| String | val |

### EInvCancelResponse_Mdl
| type | field |
|---|---|
| String | status |
| String | data |
| ErrorDetails_Mdl | errorDetails |
| List<EinvCancelInfoDtls_Mdl> | infoDtls |

### EInvTranDtls_Mdl
| type | field |
|---|---|
| String | taxSch |
| String | supTyp |
| String | regRev |
| String | ecmGstin |
| String | igstOnIntra |

### Error_Mdl
| type | field |
|---|---|
| String | status_cd |
| ErrorDetails_Mdl | error |

### EinvAdditionalDocDtls_Mdl
| type | field |
|---|---|
| String | documentUrl |
| String | document |
| String | additionalInfoOfDocument |
| String | docid |
| String | vSno |

### GstTrans_Mdl
| type | field |
|---|---|
| String | docId |
| String | v_Sno |
| String | site_Code |
| String | v_Type |
| String | recID |
| String | recIdPrefix |
| String | recIdSerial |
| String | v_Date |
| String | partyBillNo |
| String | partyBillDt |
| String | shippingBillNo |
| String | shippingBillDt |
| String | shippingPortCode |
| String | partyCode |
| String | consignee |
| String | partyType |
| String | gstin |
| String | gstinDate |
| String | placeOfSupply |
| String | refDocId |
| String | refRecId |
| String | refV_Date |
| String | refV_Type |
| String | refDocValue |
| String | refPartyBillNo |
| String | refPartyBillDt |
| String | gstReasonCode |
| String | rcmApplicable |
| String | itemCode |
| String | hsnCode |
| String | departmentCode |
| String | qty |
| String | gstUnitCode |
| String | itemGrossAmount |
| String | itemTaxableAmount |
| String | itemCgstRate |
| String | itemCgstAmount |
| String | itemSgstRate |
| String | itemSgstAmount |
| String | itemIgstRate |
| String | itemIgstAmount |
| String | itemUgstRate |
| String | itemUgstAmount |
| String | itemCessRate |
| String | itemCessAmount |
| String | itemNetAmount |
| String | invoiceSGST |
| String | invoiceCGST |
| String | invoiceIGST |
| String | invoiceUGST |
| String | invoiceValue |
| String | uploadYN |
| String | gstType |
| String | gstTransNature |
| String | isTaxInclusive |
| String | itemCessNaRate |
| String | itemCessNaAmount |
| String | itemCessEvalTypeCode |
| String | itemCessNaGstUnitCode |
| String | invoiceCess |
| String | invoiceCessNonAdvol |
| String | gstDocTypeCode |
| String | gstDocTypeName |
| String | gstDocNatureCode |
| String | gstDocNatureName |
| String | itemGSTNature |
| String | itemGstTypeName |
| String | itemGstTypeCode |
| String | refGstDocType |
| String | refGstDocNature |
| String | rateDiff |
| String | rateDiffVal |
| String | qtyDiff |
| String | otherDiff |
| String | overAllDiff |
| String | values |
| String | rate |

### EInvEwbDtlsResponse_Mdl
| type | field |
|---|---|
| Object | transId |
| String | transName |
| String | transMode |
| int | distance |
| String | transDocNo |
| String | transDocDt |
| String | vehNo |
| String | vehType |

### Party_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | gstin |
| String | legalName |
| String | tradeName |
| String | address1 |
| String | address2 |
| String | pinCode |
| String | stateNumericCode |
| String | cityName |
| String | placeOfSupplyCode |
| String | phone |
| String | email |
| String | name |
| String | cityCode |
| String | siteCode |
| String | stateName |
| String | gstinDate |
| String | transporterId |
| String | gstCountryCode |
| String | countryName |

### EinvRefContractDtls_Mdl
| type | field |
|---|---|
| String | receiptAdviceDate |
| String | receiptAdviceNo |
| String | tenderRefNo |
| String | contractNo |
| String | extraRefNo |
| String | projectRefNo |
| String | poRefNo |
| String | poRefDate |
| String | docid |
| String | vSNo |

### EinvPayeeDtls_Mdl
| type | field |
|---|---|
| String | nm |
| Object | accDet |
| String | mode |
| String | finInsBr |
| String | crTrn |
| String | payInstr |
| String | payTerm |
| String | dirDr |
| int | crDay |
| int | paidAmt |
| int | paymtDue |

### EinvRefPrecedingDocDtls_Mdl
| type | field |
|---|---|
| String | refRecId |
| String | refV_Date |
| String | refDocOtherRef |

### EinvCancelInfoDtls_Mdl
| type | field |
|---|---|
| String | InfCd |
| Object | Desc |

### ExtractedSignedInvoiceData_Mdl
| type | field |
|---|---|
| String | ackNo |
| String | ackDt |
| String | version |
| String | irn |
| EInvTranDtls_Mdl | tranDtls |
| EInvDocDtls_Mdl | docDtls |
| Party_Mdl | sellerDtls |
| Party_Mdl | buyerDtls |
| Party_Mdl | dispDtls |
| Party_Mdl | shipDtls |
| EInvEwbDtlsResponse_Mdl | ewbDtls |
| EInvValDtls_Mdl | valDtls |
| EinvPayeeDtls_Mdl | payDtls |
| EinvExportDtls_Mdl | expDtls |
| EinvRefDtls_Mdl | refDtls |
| List<EinvAdditionalDocDtls_Mdl> | addlDocDtls |
| List<EInvItemDtls_Mdl> | itemList |

### EGst_Mdl
| type | field |
|---|---|
| String | docid |
| String | mongoLinkId |
| String | v_Type |
| String | v_Prefix |
| String | v_Date |
| String | site_Code |
| RecId | recId |
| String | gstType |
| String | partyType |
| String | locationCode |
| String | posStateNumericCode |
| String | posStateName |
| String | ewaySupplyTypeCode |
| String | ewaySupplyTypeName |
| String | ewaySubSupplyTypeCode |
| String | ewaySubSupplyTypeName |
| String | ewaySubSupplyDesc |
| String | ewayDocTypeCode |
| String | ewayDocTypeName |
| String | ewayTransTypeCode |
| String | ewayTransTypeName |
| String | actFromStateNumericCode |
| String | actToStateNumericCode |
| String | einv_version |
| String | taxSchemeCode |
| String | taxSchemeName |
| String | eComGstin |
| String | einvSupplyTypeCode |
| String | einvSupplyTypeName |
| String | einvDocTypeCode |
| String | einvDocTypeName |
| String | reverseCharge |
| String | igstOnIntra |
| String | u_EntDt_LatestLine |
| Party_Mdl | consigner_Mdl |
| Party_Mdl | billTo_Mdl |
| Party_Mdl | consignee_Mdl |
| Party_Mdl | dispatchFrom_Mdl |
| Party_Mdl | shipTo_Mdl |
| Party_Mdl | transporter_Mdl |
| String | otherAmount |
| String | taxableValue |
| String | cgstValue |
| String | sgstValue |
| String | igstValue |
| String | cessValue |
| String | cessNonAdvolValue |
| String | stateCessAmount |
| String | roundOfAmount |
| String | netInvAmount |
| String | discountAmount |
| String | totalInvAmtInFc |
| String | tt_u_EntDt_LatestLine |
| boolean | tpBillGenrated |
| String | transportLrNo |
| String | totInvValue |
| String | transportModeCode |
| String | transportModeName |
| String | transDistance |
| String | transportLrDate |
| String | vehicleNo |
| String | vehicleTypeCode |
| String | vehicleTypeName |
| String | gstVehicleType |
| String | eway_BillNo |
| String | eway_BillDate |
| String | eway_validUpto |
| String | eway_alert |
| String | eway_cancelRsnCode |
| String | eway_cancelRmrk |
| String | eway_cancelDate |
| String | eway_Status |
| String | eway_GenerationMode |
| String | einv_cancelRsnCode |
| String | einv_cancelRmrk |
| String | einv_cancelDate |
| String | einv_Status |
| String | einv_IrnNo |
| String | einv_ackNo |
| String | einv_ackDt |
| String | einv_JwtIssuer |
| String | einv_SignedQrCode |
| String | einv_SignedInvoice |
| String | einv_SignedQrCodeImage |
| String | einv_ExtractedSignedInvoiceDataJson |
| String | einv_ExtractedSignedQrCodeJson |
| boolean | ewayBillRequired |
| boolean | einvoiceRequired |
| boolean | siteTransaction |
| String | refRemark |
| String | refDocid |
| String | refRecId |
| String | refV_Date |
| String | refDocOtherRef |
| String | refInvDocStartDate |
| String | refInvDocEndDate |
| String | shippingBillNo |
| String | shippingBillDate |
| String | currencyCode |
| String | portCode |
| String | refundClaim |
| String | payee_Name |
| String | payee_AccountNo |
| String | payee_ModeOfPayment |
| String | payee_BranchCode |
| String | payee_CrTrn |
| String | payee_PaymentInstruction |
| String | payee_PaymentTerms |
| String | payee_DirectDebit |
| String | payee_CreditDays |
| String | payee_PaidAmount |
| String | payee_PaymentDue |
| ArrayList<EinvAdditionalDocDtls_Mdl> | addDocList |
| ArrayList<EinvRefContractDtls_Mdl> | ContractDetails |
| ArrayList<EGstDetail_Mdl> | itemList |

### ExtractedSignedQrCode_Mdl
| type | field |
|---|---|
| String | sellerGstin |
| String | buyerGstin |
| String | docNo |
| String | docTyp |
| String | docDt |
| String | totInvVal |
| String | itemCnt |
| String | mainHsnCode |
| String | irn |

### GSTS_Mdl
| type | field |
|---|---|
| String | gstsHeadCode |
| String | gstsHeadShortName |
| String | gstsHeadName |
| String | atRate |
| String | purchaseSalesAcName |
| String | purchaseSalesAcCode |
| String | purchaseSalesAcManualCode |
| String | inputTaxAcName |
| String | inputTaxAcCode |
| String | inputTaxAcManualCode |
| String | outputTaxAcName |
| String | outputTaxAcCode |
| String | outputTaxAcManualCode |
| String | rcmAcName |
| String | rcmAcCode |
| String | rcmAcManualCode |
| String | rcmContraAcName |
| String | rcmContraAcCode |
| String | rcmContraAcManualCode |
| String | cessEvalTypeCode |
| String | cessEvalTypeName |
| String | ratePerUnit |
| String | unitName |
| String | unitCode |

### EGstParam_Mdl
| type | field |
|---|---|
| String | userGstin |
| String | eway_UserName |
| String | eway_UserPassword |
| String | eway_GSPName |
| String | eway_AspUserId |
| String | eway_AspPassword |
| String | eway_IrpCode |
| String | eway_IrpName |
| String | eway_BaseUrl |
| String | eway_ClientId |
| String | eway_ClientSecret |
| String | eway_GSPUserID |
| String | eway_UrlFormat |
| String | eway_PrintFormat |
| String | eway_AuthFormat |
| String | eway_AuthFolderName |
| String | eway_BillFolderName |
| String | eway_AuthApiName |
| String | eway_BillApiName |
| String | eway_PrintApiName |
| String | eway_AuthAction |
| String | eway_GenerateAction |
| String | eway_CancelAction |
| String | eway_PrintAction |
| String | eway_DetailePrintAction |
| String | eway_getDetailAction |
| String | eway_AuthApiVersion |
| String | eway_BillApiVersion |
| String | eway_PrintApiVersion |
| String | einv_UserName |
| String | einv_UserPassword |
| String | einv_IrpCode |
| String | einv_IrpName |
| String | einv_BaseUrl |
| String | einv_GSPName |
| String | einv_ASPID |
| String | einv_ASPID_Password |
| String | einv_UrlFormat |
| String | einv_AuthFormat |
| String | einv_PrintFormat |
| String | einv_CancelFormat |
| String | einv_AuthFolderName |
| String | einv_ApiFolderName |
| String | einv_AuthApiName |
| String | einv_BillApiName |
| String | einv_PrintApiName |
| String | einv_AuthApiVersion |
| String | einv_BillApiVersion |
| String | einv_PrintApiVersion |
| String | einv_AuthAction |
| String | einv_GenerateAction |
| String | einv_CancelAction |
| String | einv_PrintAction |
| String | einv_DetailedPrintAction |
| String | einv_getDetailAction |
| String | einv_qrCodeSize |
| String | einv_parseIrnResp |
| String | einv_eWayViaIrnUrlFormat |
| String | einv_eWayViaIrnApiName |
| String | einv_eWayViaIrnApiFolderName |
| String | einv_eWayViaIrnApiVersion |
| String | userDateTime |

### EGstDetail_Mdl
| type | field |
|---|---|
| String | docid |
| String | itemCode |
| String | ibCode |
| String | itemName |
| String | itemDesc |
| String | hsn |
| String | itemGstNature |
| String | quantity |
| String | unitCode |
| String | unitName |
| String | gstUnit |
| String | vSno |
| String | isService |
| String | batchNo |
| String | warrantyDate |
| String | expiryDate |
| String | rate |
| String | freeQty |
| String | discountAmount |
| String | gstRate |
| String | grossAmount |
| String | taxableAmount |
| String | sgstRate |
| String | cgstRate |
| String | igstRate |
| String | cessRate |
| String | cessNonAdvolRate |
| String | sgstAmount |
| String | igstAmount |
| String | cgstAmount |
| String | cessAmount |
| String | cessNaAmount |
| String | stateCessRate |
| String | stateCessAmount |
| String | stateCessNaAmount |
| String | netAmount |
| String | otherCharges |
| String | propertieDispCsv |
| String | barCode |
| String | orderRefNo |
| String | originCountry |
| String | itemUSno |

### GstDocument_Mdl
| type | field |
|---|---|
| String | docid |
| String | gstDocTypeCode |
| String | gstDocTypeName |
| String | gstDocNatureCode |
| String | gstDocNatureName |
| String | itemGSTNatureCode |
| String | itemGSTNatureName |
| String | itemGstTypeCode |
| String | itemGstTypeName |

### EInvValDtls_Mdl
| type | field |
|---|---|
| String | assVal |
| String | cgstVal |
| String | sgstVal |
| String | igstVal |
| String | cesVal |
| String | stCesVal |
| String | discount |
| String | othChrg |
| String | rndOffAmt |
| String | totInvVal |
| String | totInvValFc |

### ErrorDetails_Mdl
| type | field |
|---|---|
| String | error_cd |
| String | message |

### EGstToken_Mdl
| type | field |
|---|---|
| String | userGstin |
| String | authtoken |
| String | tokenDt |
| String | sek |
| String | clientId |
| String | userName |
| String | tokenExpiry |

### EInvCancelData_Mdl
| type | field |
|---|---|
| String | irn |
| String | cancelDate |

### EInvData_Mdl
| type | field |
|---|---|
| String | ackNo |
| String | ackDt |
| String | irn |
| String | signedInvoice |
| String | signedQRCode |
| String | status |
| String | ewbNo |
| String | ewbDt |
| String | ewbValidTill |
| ExtractedSignedInvoiceData_Mdl | extractedSignedInvoiceData |
| ExtractedSignedQrCode_Mdl | extractedSignedQrCode |
| String | qrCodeImage |
| String | jwtIssuer |

### MasterEGst_Mdl
| type | field |
|---|---|
| EGst_Mdl | eGst_Mdl |
| GstDocument_Mdl | gst_Mdl |
| EGstToken_Mdl | eGstToken_Mdl |

### Eway_Mdl
| type | field |
|---|---|
| String | ewayBillNo |
| String | ewayBillDate |
| String | validUpto |
| String | alert |

### EInvoice_Mdl
| type | field |
|---|---|
| String | status |
| String | data |
| List<ErrorDetails_Mdl> | errorDetails |
| List<EinvCancelInfoDtls_Mdl> | infoDtls |

### EinvRefDocPeriodDetail_Mdl
| type | field |
|---|---|
| String | refInvDocStartDate |
| String | refInvDocEndDate |

### EInvDocDtls_Mdl
| type | field |
|---|---|
| String | typ |
| String | no |
| String | dt |

### EinvExportDtls_Mdl
| type | field |
|---|---|
| String | shippingBillNo |
| String | shippingBillDate |
| Object | gstCountryCode |
| String | currencyCode |
| Object | invForCur |
| String | portCode |
| String | refundClaim |

### EinvItmBatchDtls_Mdl
| type | field |
|---|---|
| String | batchNo |
| String | ExpiryDate |
| String | warrantyDate |

### EinvoiceMongo_Mdl
| type | field |
|---|---|
| String | mongoLinkId |
| String | docid |
| String | recid |
| String | modulename |
| String | menuname |
| String | ack_no |
| String | ack_no_date |
| String | irn |
| String | extracted_Signed_Invoice_DataJson |
| String | extracted_Signed_QrCodeJson |
| String | signed_e_invoice |
| String | signed_Qr_Code |
| String | signed_Qr_Code_Img |

### EInvItemDtls_Mdl
| type | field |
|---|---|
| String | itemNo |
| String | slNo |
| String | prdDesc |
| String | isServc |
| String | hsnCd |
| String | barcde |
| String | qty |
| String | freeQty |
| String | unit |
| String | unitPrice |
| String | totAmt |
| String | discount |
| String | preTaxVal |
| String | assAmt |
| String | gstRt |
| String | igstAmt |
| String | cgstAmt |
| String | sgstAmt |
| String | cesRt |
| String | cesAmt |
| String | cesNonAdvlAmt |
| String | stateCesRt |
| String | stateCesAmt |
| String | stateCesNonAdvlAmt |
| String | othChrg |
| String | totItemVal |
| String | ordLineRef |
| String | orgCntry |
| String | prdSlNo |
| EinvItmBatchDtls_Mdl | bchDtls |
| List<EinvItmAttributeDtls_Mdl> | attribDtls |

### EwayCancelRequest_Mdl
| type | field |
|---|---|
| String | ewbNo |
| String | cancelRsnCode |
| String | cancelRmrk |

### EWayViaIrnResponse_Mdl
| type | field |
|---|---|
| String | status |
| String | data |
| List<ErrorDetails_Mdl> | errorDetails |
| List<EinvCancelInfoDtls_Mdl> | infoDtls |

### EinvRefDtls_Mdl
| type | field |
|---|---|
| String | invRm |
| EinvRefDocPeriodDetail_Mdl | docPerdDtls |
| List<EinvRefPrecedingDocDtls_Mdl> | precDocDtls |
| List<EinvRefContractDtls_Mdl> | contrDtls |

### EwayCancelResponse_Mdl
| type | field |
|---|---|
| String | ewayBillNo |
| String | cancelDate |

## Server `EGstApiCall.java` (477 lines)
Helpers used: `DmShared.iNull`, `DmShared.xNull`

### `getAuthToken`
`public EGstToken_Mdl getAuthToken(EGstParam_Mdl eGSTParam_Mdl, EGstToken_Mdl eGstToken_Mdl, int actionCode) throws Exception`


### `genrateEWayBill`
`public Eway_Mdl genrateEWayBill(EGstParam_Mdl eGSTParam_Mdl, EGstToken_Mdl eGstToken_Mdl, String jsonData, int actionCode) throws Exception`


### `genrateEInvoice`
`public EInvoice_Mdl genrateEInvoice(EGstParam_Mdl eGSTParam_Mdl, EGstToken_Mdl eGstToken_Mdl, String jsonData, int actionCode) throws Exception`


### `genrateEWayBillViaIRN`
`public EWayViaIrnResponse_Mdl genrateEWayBillViaIRN(EGstParam_Mdl eGSTParam_Mdl, EGstToken_Mdl eGstToken_Mdl, String jsonData, int actionCode, String irn) throws Exception`


### `getEWayBillDetail`
`public String getEWayBillDetail(EGstParam_Mdl eGSTParam_Mdl, EGstToken_Mdl eGstToken_Mdl, String ewayNo) throws Exception`

- Other calls: `EWAY_NO.getValue`, `E_WAY_GET_BILL.getCode`

### `cancelEWayBill`
`public EwayCancelResponse_Mdl cancelEWayBill(EGstParam_Mdl eGSTParam_Mdl, EGstToken_Mdl eGstToken_Mdl, int actionCode, String jsonData) throws Exception`


### `cancelEinvIRN`
`public EInvCancelResponse_Mdl cancelEinvIRN(EGstParam_Mdl eGSTParam_Mdl, EGstToken_Mdl eGstToken_Mdl, int actionCode, String jsonData) throws Exception`


### `printEWayBill`
`public byte[] printEWayBill(EGstParam_Mdl eGSTParam_Mdl, EGstToken_Mdl eGstToken_Mdl, String jsonData, int actionCode) throws Exception`


### `getURL`
`private String getURL(EGstParam_Mdl eGSTParam_Mdl, EGstToken_Mdl eGstToken_Mdl, int actionCode) throws Exception`

- Other calls: `ACTION.getValue`, `API_NAME.getValue`, `ASP_ID.getValue`, `ASP_PASSWORD.getValue`, `AUTH_TOKEN.getValue`, `BASE_URL.getValue`, `E_INV_AUTHENTICATION.getCode`, `E_INV_AUTHENTICATION.getName`, `E_INV_CANCEL_BILL.getCode`, `E_INV_CANCEL_BILL.getName`, `E_INV_GENRATE_BILL.getCode`, `E_INV_GENRATE_BILL.getName`, `E_INV_GET_BILL.getCode`, `E_INV_GET_BILL.getName`, `E_INV_PRINT_BILL.getCode`, `E_INV_PRINT_BILL.getName`, `E_INV_PRINT_DETAILED_BILL.getCode`, `E_INV_PRINT_DETAILED_BILL.getName`, `E_WAY_AUTHENTICATION.getCode`, `E_WAY_AUTHENTICATION.getName`, `E_WAY_CANCEL_BILL.getCode`, `E_WAY_CANCEL_BILL.getName`, `E_WAY_GENERATE_VIA_IRN.getCode`, `E_WAY_GENERATE_VIA_IRN.getName`, `E_WAY_GENRATE_BILL.getCode`, `E_WAY_GENRATE_BILL.getName`, `E_WAY_GET_BILL.getCode`, `E_WAY_GET_BILL.getName`, `E_WAY_PRINT_BILL.getCode`, `E_WAY_PRINT_BILL.getName`, `E_WAY_PRINT_DETAILED_BILL.getCode`, `E_WAY_PRINT_DETAILED_BILL.getName`, `FOLDER.getValue`, `GSP_PASSWORD.getValue`, `GSP_USER_NAME.getValue`, `GSTIN.getValue`, `PARSE_IRN_RESP.getValue`, `QR_CODE_SIZE.getValue`, `VERSION.getValue`

## Server `Gst_Server.java` (3114 lines)
Helpers used: `DmBaseServer.getSsnData`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.getCodeViaManualCode`, `DmServer.getServerTimestamp`, `DmServer.getSsnSiteGSTIN`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getGSTS`
`public static List<GSTS_Mdl> getGSTS(String locationCode, String gstsTypeCode, Connection cnTemp) throws Exception`

- SQL 1: `SELECT gsts.gstHeadCode , ih.shortName AS gstHeadShortName , ih.name AS gstHeadName , gsts.atRate , sgps.name AS purchaseSalesAcName , sgps.manualCode AS purchaseSalesAcManualCode , gsts.purchaseSalesAc , sgi.name AS inputTaxAcName , sgi.manualCode AS inputTaxAcManualCode , gsts.inputTaxAc , sgo.name AS outputTaxAcName , sgo.manualCode AS outputTaxAcManualCode , gsts.outputTaxAc , sgr.name AS rcmAcName , sgr.manualCode AS rcmAcManualCode, gsts.rcmAc , sgrc.name AS rcmContraAcName , sgrc.manualCode AS rcmContraAcManualCode, gsts.rcmContraAc , gsts.cessEValTypeCode, gsts.ratePerUnit, gsts.unitCode, um.unit as unitName FROM gsts LEFT JOIN gstHead ih ON ih.code = gsts.gstHeadCode LEFT JOIN subGroup sgps ON sgps.subCode = gsts.purchaseSalesAc LEFT JOIN subGroup sgi ON sgi.subCode = gsts.inputTaxAc LEFT JOIN subGroup sgo ON sgo.subCode = gsts.outputTaxAc LEFT JOIN subGroup sgr ON sgr.subCode = gsts.rcmAc LEFT JOIN subGroup sgrc ON sgrc.subCode = gsts.rcmContraAc LEFT JOIN unitMast um ON um.code = gsts.unitCode WHERE gsts.locationCode = :locationCode AND gsts.gstTypeCode = :gstTypeCode`
- Params: `:locationCode` ← `DmShared.iNull(locationCode).toString()`; `:gstTypeCode` ← `DmShared.iNull(gstsTypeCode).toString()`
- Other calls: `CessEvalType.getViaCode`

### `saveGstTrans`
`public static void saveGstTrans(GstTrans_Mdl gstTrans_Mdl, Connection cnTemp, DmRSSPrivate rssTemp) throws Exception`

- SQL 1: `INSERT INTO gstTrans (docId ,v_Sno ,site_Code ,v_Type ,recID ,recIdPrefix ,recIdSerial ,v_Date ,partyBillNo ,partyBillDt ,shippingBillNo ,shippingBillDt ,shippingPortCode ,partyCode ,consignee ,partyType ,gsTin ,gstinDate ,placeOfSupply ,refDocId ,refRecId ,refV_Date ,refV_Type ,refDocValue ,refPartyBillNo ,refPartyBillDt ,gstReasonCode ,rcmApplicable ,itemCode ,hsnCode ,departmentCode ,qty ,gstUnitCode ,itemGrossAmount ,itemTaxableAmount ,itemCgstRate ,itemCgstAmount ,itemSgstRate ,itemSgstAmount ,itemIgstRate ,itemIgstAmount ,itemUgstRate ,itemUgstAmount ,itemCessRate ,itemCessAmount ,itemNetAmount ,invoiceSGST ,invoiceCGST ,invoiceIGST ,invoiceUGST ,invoiceValue ,isTaxInclusive ,gstType ,gstTransNature ,itemCessNaAmount ,itemCessNaRate ,itemCessEvalTypeCode ,itemCessNaGstUnitCode ,invoiceCess ,invoiceCessNonAdvol ,gstDocType ,gstDocNature ,itemGSTNature ,itemGstType ,refGstDocType ,refGstDocNature ,isStatus ,rateDiff ,rateDiffVal ,qtyDiff ,otherDiff ,overAllDiff ,value ,rate ) VALUES ( :docId ,:v_Sno ,:site_Code ,:v_Type ,:recID ,:recIdPrefix ,:recIdSerial ,:v_Date ,:partyBillNo ,:partyBillDt ,:shippingBillNo ,:shippingBillDt ,:shippingPortCode ,:partyCode ,:consignee ,:partyType ,:gsTin ,:gstinDate ,:placeOfSupply ,:refDocId ,:refRecId ,:refV_Date ,:refV_Type ,:refDocValue ,:refPartyBillNo ,:refPartyBillDt ,:gstReasonCode ,:rcmApplicable ,:itemCode ,:hsnCode ,:departmentCode ,:qty ,:gstUnitCode ,:itemGrossAmount ,:itemTaxableAmount ,:itemCgstRate ,:itemCgstAmount ,:itemSgstRate ,:itemSgstAmount ,:itemIgstRate ,:itemIgstAmount ,:itemUgstRate ,:itemUgstAmount ,:itemCessRate ,:itemCessAmount ,:itemNetAmount ,:invoiceSGST ,:invoiceCGST ,:invoiceIGST ,:invoiceUGST ,:invoiceValue ,:isTaxInclusive ,:gstType ,:gstTransNature ,:itemCessNaAmount ,:itemCessNaRate ,:itemCessEvalTypeCode ,:itemCessNaGstUnitCode ,:invoiceCess ,:invoiceCessNonAdvol ,:gstDocType ,:gstDocNature ,:itemGSTNature ,:itemGstType ,:refGstDocType ,:refGstDocNature ,:isStatus ,:rateDiff ,:rateDiffVal ,:qtyDiff ,:otherDiff ,:overAllDiff ,:value ,:rate )`
- Params: `:docId` ← `DmShared.xNull(gstTrans_Mdl.getDocId())`; `:v_Sno` ← `DmShared.xNull(gstTrans_Mdl.getV_Sno())`; `:site_Code` ← `DmShared.foriegnKey(gstTrans_Mdl.getSite_Code())`; `:v_Type` ← `DmShared.foriegnKey(gstTrans_Mdl.getV_Type())`; `:recID` ← `DmShared.xNull(gstTrans_Mdl.getRecID())`; `:recIdPrefix` ← `DmShared.xNull(gstTrans_Mdl.getRecIdPrefix())`; `:recIdSerial` ← `DmShared.xNull(gstTrans_Mdl.getRecIdSerial())`; `:v_Date` ← `DmServer.convertDate(gstTrans_Mdl.getV_Date())`; `:partyBillNo` ← `DmShared.xNull(gstTrans_Mdl.getPartyBillNo())`; `:partyBillDt` ← `DmServer.convertDate(gstTrans_Mdl.getPartyBillDt())`; `:shippingBillNo` ← `DmShared.foriegnKey(gstTrans_Mdl.getShippingBillNo())`; `:shippingBillDt` ← `DmServer.convertDate(gstTrans_Mdl.getShippingBillDt())`; `:shippingPortCode` ← `DmShared.xNull(gstTrans_Mdl.getShippingPortCode())`; `:partyCode` ← `DmShared.foriegnKey(gstTrans_Mdl.getPartyCode())`; `:consignee` ← `DmShared.foriegnKey(gstTrans_Mdl.getConsignee())`; `:partyType` ← `DmShared.xNull(gstTrans_Mdl.getPartyType())`; `:gsTin` ← `DmShared.xNull(gstTrans_Mdl.getGstin())`; `:gstinDate` ← `DmServer.convertDate(gstTrans_Mdl.getGstinDate())`; `:placeOfSupply` ← `DmShared.foriegnKey(gstTrans_Mdl.getPlaceOfSupply())`; `:refDocId` ← `DmShared.foriegnKey(gstTrans_Mdl.getRefDocId())`; `:refPartyBillNo` ← `DmShared.xNull(gstTrans_Mdl.getRefPartyBillNo())`; `:refPartyBillDt` ← `DmServer.convertDate(gstTrans_Mdl.getRefPartyBillDt())`; `:refRecId` ← `DmShared.xNull(gstTrans_Mdl.getRefRecId())`; `:refV_Date` ← `DmServer.convertDate(gstTrans_Mdl.getRefV_Date())`; `:refV_Type` ← `DmShared.foriegnKey(gstTrans_Mdl.getRefV_Type())`; `:refDocValue` ← `DmServer.vNull(gstTrans_Mdl.getRefDocValue())`; `:gstReasonCode` ← `DmShared.foriegnKey(gstTrans_Mdl.getGstReasonCode())`; `:rcmApplicable` ← `DmShared.iNull(gstTrans_Mdl.getRcmApplicable()).toString()`; `:itemCode` ← `DmShared.foriegnKey(gstTrans_Mdl.getItemCode())`; `:hsnCode` ← `DmShared.foriegnKey(gstTrans_Mdl.getHsnCode())`; `:departmentCode` ← `DmShared.foriegnKey(gstTrans_Mdl.getDepartmentCode())`; `:qty` ← `DmServer.vNull(gstTrans_Mdl.getQty())`; `:gstUnitCode` ← `DmShared.foriegnKey(gstTrans_Mdl.getGstUnitCode())`; `:itemGrossAmount` ← `DmServer.vNull(gstTrans_Mdl.getItemGrossAmount())`; `:itemTaxableAmount` ← `DmServer.vNull(gstTrans_Mdl.getItemTaxableAmount())`; `:itemCgstRate` ← `DmServer.vNull(gstTrans_Mdl.getItemCgstRate())`; `:itemCgstAmount` ← `DmServer.vNull(gstTrans_Mdl.getItemCgstAmount())`; `:itemSgstRate` ← `DmServer.vNull(gstTrans_Mdl.getItemSgstRate())`; `:itemSgstAmount` ← `DmServer.vNull(gstTrans_Mdl.getItemSgstAmount())`; `:itemIgstRate` ← `DmServer.vNull(gstTrans_Mdl.getItemIgstRate())`; `:itemIgstAmount` ← `DmServer.vNull(gstTrans_Mdl.getItemIgstAmount())`; `:itemUgstRate` ← `DmServer.vNull(gstTrans_Mdl.getItemUgstRate())`; `:itemUgstAmount` ← `DmServer.vNull(gstTrans_Mdl.getItemUgstAmount())`; `:itemCessRate` ← `DmServer.vNull(gstTrans_Mdl.getItemCessRate())`; `:itemCessAmount` ← `DmServer.vNull(gstTrans_Mdl.getItemCessAmount())`; `:itemNetAmount` ← `DmServer.vNull(gstTrans_Mdl.getItemNetAmount())`; `:invoiceSGST` ← `DmServer.vNull(gstTrans_Mdl.getInvoiceSGST())`; `:invoiceCGST` ← `DmServer.vNull(gstTrans_Mdl.getInvoiceCGST())`; `:invoiceIGST` ← `DmServer.vNull(gstTrans_Mdl.getInvoiceIGST())`; `:invoiceUGST` ← `DmServer.vNull(gstTrans_Mdl.getInvoiceUGST())`; `:invoiceCess` ← `DmServer.vNull(gstTrans_Mdl.getInvoiceCess())`; `:invoiceCessNonAdvol` ← `DmServer.vNull(gstTrans_Mdl.getInvoiceCessNonAdvol())`; `:invoiceValue` ← `DmServer.vNull(gstTrans_Mdl.getInvoiceValue())`; `:isTaxInclusive` ← `DmShared.iNull(gstTrans_Mdl.getIsTaxInclusive()).toString()`; `:gstType` ← `DmShared.iNull(gstTrans_Mdl.getGstType()).toString()`; `:gstTransNature` ← `DmShared.foriegnKey(gstTrans_Mdl.getGstTransNature()).toString()`; `:itemCessNaRate` ← `DmServer.vNull(gstTrans_Mdl.getItemCessNaRate())`; `:itemCessNaAmount` ← `DmServer.vNull(gstTrans_Mdl.getItemCessNaAmount())`; `:itemCessEvalTypeCode` ← `DmShared.foriegnKey(gstTrans_Mdl.getItemCessEvalTypeCode())`; `:itemCessNaGstUnitCode` ← `DmShared.foriegnKey(gstTrans_Mdl.getItemCessNaGstUnitCode())`; `:gstDocType` ← `DmShared.foriegnKey(gstTrans_Mdl.getGstDocTypeCode())`; `:gstDocNature` ← `DmShared.foriegnKey(gstTrans_Mdl.getGstDocNatureCode())`; `:itemGSTNature` ← `DmShared.xNull(gstTrans_Mdl.getItemGSTNature())`; `:itemGstType` ← `DmShared.xNull(gstTrans_Mdl.getItemGstTypeCode())`; `:refGstDocType` ← `DmShared.foriegnKey(gstTrans_Mdl.getRefGstDocType())`; `:refGstDocNature` ← `DmShared.foriegnKey(gstTrans_Mdl.getRefGstDocNature())`; `:rateDiff` ← `DmServer.vNull(gstTrans_Mdl.getRateDiff())`; `:rateDiffVal` ← `DmServer.vNull(gstTrans_Mdl.getRateDiffVal())`; `:qtyDiff` ← `DmServer.vNull(gstTrans_Mdl.getQtyDiff())`; `:otherDiff` ← `DmServer.vNull(gstTrans_Mdl.getOtherDiff())`; `:overAllDiff` ← `DmServer.vNull(gstTrans_Mdl.getOverAllDiff())`; `:value` ← `DmServer.vNull(gstTrans_Mdl.getValues())`; `:rate` ← `DmServer.vNull(gstTrans_Mdl.getRate())`; `:isStatus` ← `1`

### `saveGstTransHead`
`public static void saveGstTransHead(GstTrans_Mdl gstTrans_Mdl, Connection cnTemp, DmRSSPrivate rssTemp) throws Exception`

- SQL 1: `DELETE FROM gstTransHead WHERE docId = :docId`
- SQL 2: `INSERT INTO gstTransHead(docId, grossAmount, taxableAmount, cgstAmount, sgstAmount , igstAmount, ugstAmount, cessAmount, cessNaAmount, netAmount, otherCharges , v_Type, recId, recIdPrefix, recIdSerial, v_Date, site_Code, partyBillNo, partyBillDt , shippingBillNo, shippingBillDt, shippingPortCode, partyCode, consignee, partyType , gsTin, gstinDate, placeOfSupply, refDocId, refRecId, refV_Date, refV_Type, refDocValue , refPartyBillNo, refPartyBillDt, gstReasonCode, rcmApplicable , gstDocType, gstDocNature, itemGSTNature, itemGstType ) SELECT cte.* , :v_Type AS v_Type , :recId AS recId , :recIdPrefix AS recIdPrefix , :recIdSerial AS recIdSerial , :v_Date AS v_Date , :site_Code AS site_Code , :partyBillNo AS partyBillNo , :partyBillDt AS partyBillDt , :shippingBillNo AS shippingBillNo , :shippingBillDt AS shippingBillDt , :shippingPortCode AS shippingPortCode , :partyCode AS partyCode , :consignee AS consignee , :partyType AS partyType , :gsTin AS gsTin , :gstinDate AS gstinDate , :placeOfSupply AS placeOfSupply , :refDocId AS refDocId , :refRecId AS refRecId , :refV_Date AS refV_Date , :refV_Type AS refV_Type , :refDocValue AS refDocValue , :refPartyBillNo AS refPartyBillNo , :refPartyBillDt AS refPartyBillDt , :gstReasonCode AS gstReasonCode , :rcmApplicable AS rcmApplicable , :gstDocType AS gstDocType , :gstDocNature AS gstDocNature , :itemGSTNature AS itemGSTNature , :itemGstType AS itemGstType FROM ( SELECT gt.docId , SUM(ISNULL(gt.itemGrossAmount, 0)) AS grossAmount, SUM(ISNULL(gt.itemTaxableAmount, 0)) AS taxableAmount , SUM(ISNULL(gt.itemCgstAmount, 0)) AS cgstAmount, SUM(ISNULL(gt.itemSgstAmount, 0)) AS sgstAmount , SUM(ISNULL(gt.itemIgstAmount, 0)) AS igstAmount, SUM(ISNULL(gt.itemUgstAmount, 0)) AS ugstAmount , SUM(ISNULL(gt.itemCessAmount, 0)) AS cessAmount, SUM(ISNULL(gt.itemCessNaAmount, 0)) AS cessNaAmount , SUM(ISNULL(gt.itemNetAmount, 0)) AS netAmount, SUM(ISNULL(gt.itemOtherCharges, 0)) AS otherCharges FROM gstTrans gt WHERE gt.docId = :docId GROUP BY gt.docId ) AS cte`
- Params: `:docId` ← `DmShared.xNull(gstTrans_Mdl.getDocId())`; `:docId` ← `DmShared.xNull(gstTrans_Mdl.getDocId())`; `:site_Code` ← `DmShared.foriegnKey(gstTrans_Mdl.getSite_Code())`; `:v_Type` ← `DmShared.foriegnKey(gstTrans_Mdl.getV_Type())`; `:recID` ← `DmShared.xNull(gstTrans_Mdl.getRecID())`; `:recIdPrefix` ← `DmShared.xNull(gstTrans_Mdl.getRecIdPrefix())`; `:recIdSerial` ← `DmShared.xNull(gstTrans_Mdl.getRecIdSerial())`; `:v_Date` ← `DmServer.convertDate(gstTrans_Mdl.getV_Date())`; `:partyBillNo` ← `DmShared.xNull(gstTrans_Mdl.getPartyBillNo())`; `:partyBillDt` ← `DmServer.convertDate(gstTrans_Mdl.getPartyBillDt())`; `:shippingBillNo` ← `DmShared.foriegnKey(gstTrans_Mdl.getShippingBillNo())`; `:shippingBillDt` ← `DmServer.convertDate(gstTrans_Mdl.getShippingBillDt())`; `:shippingPortCode` ← `DmShared.xNull(gstTrans_Mdl.getShippingPortCode())`; `:partyCode` ← `DmShared.foriegnKey(gstTrans_Mdl.getPartyCode())`; `:consignee` ← `DmShared.foriegnKey(gstTrans_Mdl.getConsignee())`; `:partyType` ← `DmShared.xNull(gstTrans_Mdl.getPartyType())`; `:gsTin` ← `DmShared.xNull(gstTrans_Mdl.getGstin())`; `:gstinDate` ← `DmServer.convertDate(gstTrans_Mdl.getGstinDate())`; `:placeOfSupply` ← `DmShared.foriegnKey(gstTrans_Mdl.getPlaceOfSupply())`; `:refDocId` ← `DmShared.foriegnKey(gstTrans_Mdl.getRefDocId())`; `:refPartyBillNo` ← `DmShared.xNull(gstTrans_Mdl.getRefPartyBillNo())`; `:refPartyBillDt` ← `DmServer.convertDate(gstTrans_Mdl.getRefPartyBillDt())`; `:refRecId` ← `DmShared.xNull(gstTrans_Mdl.getRefRecId())`; `:refV_Date` ← `DmServer.convertDate(gstTrans_Mdl.getRefV_Date())`; `:refV_Type` ← `DmShared.foriegnKey(gstTrans_Mdl.getRefV_Type())`; `:refDocValue` ← `DmServer.vNull(gstTrans_Mdl.getRefDocValue())`; `:gstReasonCode` ← `DmShared.foriegnKey(gstTrans_Mdl.getGstReasonCode())`; `:rcmApplicable` ← `DmShared.iNull(gstTrans_Mdl.getRcmApplicable()).toString()`; `:gstDocType` ← `DmShared.foriegnKey(gstTrans_Mdl.getGstDocTypeCode())`; `:gstDocNature` ← `DmShared.foriegnKey(gstTrans_Mdl.getGstDocNatureCode())`; `:itemGSTNature` ← `DmShared.xNull(gstTrans_Mdl.getItemGSTNature())`; `:itemGstType` ← `DmShared.xNull(gstTrans_Mdl.getItemGstTypeCode())`

### `updateRefGstDocument`
`private static void updateRefGstDocument(String docId, String gstDocumentType, String gstDocumentNature, Connection cnTemp) throws Exception`

- Params: `:docId` ← `DmShared.xNull(docId)`; `:gstDocType` ← `DmShared.foriegnKey(gstDocumentType)`; `:gstDocNature` ← `DmShared.foriegnKey(gstDocumentNature)`

### `getGstDocumentDetail`
`public static GstDocument_Mdl getGstDocumentDetail(String docid, String vTypeCode, Connection cnTemp) throws Exception`

- Params: `:docId` ← `docid`; `:v_Type` ← `DmShared.xNull(vTypeCode)`
- Other calls: `GSTNature.getValueOf`, `GstDocumentNature.getViaShortName`, `GstDocumentType.getViaShortName`

### `deleteGstTrans`
`public static void deleteGstTrans(String docId, Connection cnTemp)`

- SQL 1: `DELETE FROM gstTrans WHERE docId = :docId`
- SQL 2: `DELETE FROM gstTransHead WHERE docId = :docId`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:docId` ← `DmShared.xNull(docId)`

### `getAuthToken`
`public static EGstToken_Mdl getAuthToken(int actionCode, Connection cnTemp, DmRSSPrivate dmRss) throws Exception`


### `processEGst`
`public static void processEGst(MasterEGst_Mdl currentMasterEGst_Mdl, int actionCode, Connection cnTemp, DmRSSPrivate dmRss, IEGstTransaction gstTransaction) throws Exception`

- Errors: "E-Way bill already active for this document!" | "E-Way Bill Response Error." | "E-invoice already active for this document!" | "E-invoice Response Error." | "E-Way Bill via IRN Response Error." | "E-Way bill already cancel for this document!" | "E-way Cancel Response Error." | "E-way bill not exist for this document!" | "E-invoice bill already cancel for this document!" | "E-invoice Cancel Response Error." | "E-invoice not exist for this document!"
- Other calls: `Active.getCode`, `Cancelled.getCode`, `E_INV_CANCEL_BILL.getCode`, `E_INV_GENRATE_BILL.getCode`, `E_WAY_CANCEL_BILL.getCode`, `E_WAY_GENERATED_DIRECTLY.getCode`, `E_WAY_GENERATED_VIA_IRN.getCode`, `E_WAY_GENERATED_WITH_E_INVOICE.getCode`, `E_WAY_GENERATE_VIA_IRN.getCode`, `E_WAY_GENRATE_BILL.getCode`, `GST_UPDATE_DOCUMENTS.getCode`

### `printEWayBill`
`public static KeyFieldExtended printEWayBill(String eWayBillNo, int actionCode, Connection cnTemp, DmRSSPrivate dmRss) throws Exception`

- Other calls: `DmRequestBuilder.getSessionId`, `Gst_Server.createFile`

### `rectifyEwayJson`
`private static String rectifyEwayJson(String eWayBillNo, String jsonEgstDetail, Connection cnTemp)`

- Params: `:ewbNo` ← `DmShared.xNull(eWayBillNo)`
- Other calls: `Bill_To_Ship_To.getCode`, `OTHERS.getCode`

### `getEGstRecord`
`public static MasterEGst_Mdl getEGstRecord(String docid, Connection cnTemp, DmRSSPrivate dmRss) throws Exception`

- SQL 1: `SELECT eg.docid, eg.v_type, eg.v_Prefix, eg.site_Code, eg.v_date, eg.taxScheme, eg.eComGstin , eg.recID, eg.recIdPrefix, eg.recIdSerial, eg.isIgstOnIntra , eg.gstType, eg.isRCM, eg.posStateNumericCode, eg.posStateName, eg.partyType, eg.locationCode , eg.seller_SiteCode, eg.seller_Name, eg.seller_Gstin, eg.seller_GstinDate , eg.seller_TradeName, eg.seller_LegalName, eg.seller_Add1, eg.seller_Add2 , eg.seller_Pin, eg.actFrom_StateNumericCode, eg.seller_StateName, eg.seller_StateNumericCode , eg.seller_CityCode , eg.seller_CityName, eg.seller_Mobile, eg.seller_Email , eg.buyer_AcCode,eg.buyer_SiteCode, eg.buyer_Name, eg.buyer_Gstin, eg.buyer_GstinDate , eg.buyer_LegalName, eg.buyer_TradeName, eg.buyer_Add1, eg.buyer_Add2 , eg.actTo_StateNumericCode, eg.buyer_StateNumericCode, eg.buyer_StateName,eg.buyer_CityCode, eg.buyer_CityName , eg.buyer_Pin, eg.buyer_Mobile, eg.buyer_Email , eg.dispFrom_SiteCode, eg.dispFrom_Name, eg.dispFrom_Gstin, eg.dispFrom_GstinDate , eg.dispFrom_LegalName, eg.dispFrom_TradeName , eg.dispFrom_Add1, eg.dispFrom_Add2 , eg.dispFrom_Pin, eg.dispFrom_StateNumericCode, eg.dispFrom_StateName ,eg.dispFrom_CityCode,eg.dispFrom_CityName , eg.dispFrom_Mobile, eg.dispFrom_Email , eg.shipTo_AcCode, eg.shipTo_SiteCode , eg.shipTo_Name , eg.shipTo_Gstin, eg.shipTo_GstinDate , eg.shipTo_LegalName, eg.shipTo_TradeName, eg.shipTo_Add1, eg.shipTo_Add2 , eg.shipTo_Pin, eg.shipTo_StateNumericCode ,shipTo_StateName , eg.shipTo_Mobile, eg.shipTo_Email, eg.shipTo_CityCode, eg.shipTo_CityName , eg.consignee_AcCode, eg.consignee_SiteCode, eg.consignee_Name, eg.consignee_Gstin, eg.consignee_GstinDate , eg.consignee_LegalName, eg.consignee_TradeName, eg.consignee_pin, eg.consignee_Mobile, eg.consignee_Email , eg.consignee_Add1, eg.consignee_Add2, eg.consignee_StateNumericCode , eg.consignee_StateName , eg.consignee_CityCode, eg.consignee_CityName , eg.transportMode, eg.transporter_AcCode, eg.transporter_Name, eg.transporter_Id , eg.transporter_Gstin, eg.transporter_GstinDate , eg.lrNo, eg.lrDate, eg.vehicleNo, eg.vehicleTypeCode, eg.vehicleTypeName, eg.gstVehicleType, eg.distance , eg.payee_Name, eg.payee_AcNo, eg.payee_ModeOfPayment, eg.payee_BranchCode, eg.payee_CreditTransfer , eg.payee_PaymentInstruction, eg.payee_PaymentTerms, eg.payee_DirectDebit, eg.payee_CreditDays , eg.payee_PaidAmt, eg.payee_DueAmt , eg.eway_TransactionType , eg.eway_BillNo, eg.eWay_BillDt , eg.eway_ValidUpTo , eg.eway_Alert , eg.eway_SupplyType, eg.eway_SubSupplyType, eg.eway_SubSupplyDesc, eg.eway_DocType , eg.eway_Status, eg.eway_CancelReason , eg.eway_CancelRemark, eg.eway_cancelDt , eg.einv_Version, eg.einv_SupplyType, eg.einv_DocType, eg.einv_IrnNo, eg.einv_ackNo, eg.einv_ackDt , eg.einv_JwtIssuer, eg.einv_Status , eg.discountAmount, eg.taxableAmount, eg.sgstAmount, eg.cgstAmount, eg.igstAmount, eg.cessAmount, eg.cessNaAmount , eg.scessAmount, eg.otherCharges, eg.totalInvAmount, eg.roundOffAmount, eg.netInvAmount, eg.netInvAmountInFC , eg.mongoLinkId , eg.einv_CancelReason, eg.einv_CancelRemark, eg.einv_cancelDt, eg.eWay_GenerationMode , eg.u_EntDt_LatestLine, eg.isEWayBillRequired, eg.isEInvoiceRequired, eg.isSiteTransaction , eg.seller_GstCountryCode, eg.buyer_GstCountryCode, eg.dispFrom_GstCountryCode, eg.shipTo_GstCountryCode, eg.consignee_GstCountryCode , eg.seller_CountryName, eg.buyer_CountryName, eg.dispFrom_CountryName, eg.shipTo_CountryName, eg.consignee_CountryName , eg.refDocId, eg.refRecId, eg.refV_Date, eg.refDocOtherRef, eg.refInvDocStartDate , eg.refInvDocEndDate, eg.refRemark, eg.shippingBillNo, eg.shippingBillDate , eg.gstPortCode, eg.refundClaim, eg.gstCurrencyCode FROM eGst eg WHERE eg.docId = :docId`
- SQL 2: `SELECT tt.isStatus,tt.u_EntDt_LatestLine FROM transporterTrans tt WHERE tt.docid = :docid`
- SQL 3: `SELECT egcd.docid, egcd.v_SNo, egcd.receiptAdviceNo, egcd.receiptAdviceDate , egcd.tenderRefNo, egcd.contractNo , egcd.extraRefNo, egcd.projectRefNo , egcd.poRefNo, egcd.poRefDate FROM eGstContractDetail egcd WHERE egcd.docId = :docId`
- SQL 4: `SELECT egdd.docid, egdd.v_SNo, egdd.docUrl, egdd.docEncoded , egdd.docInfo FROM eGstDocDetail egdd WHERE egdd.docId = :docId`
- SQL 5: `SELECT egd.docid, egd.v_SNo, egd.itemCode, egd.ibCode , egd.itemName, egd.itemDesc , egd.hsn, egd.itemGSTNature, egd.rate, egd.quantity, egd.freeQty , egd.unitCode, egd.unitName, egd.gstUnit, egd.batchNo , egd.warrentyDate, egd.expiryDate , egd.grossAmount, egd.discountAmount, egd.taxableAmount, egd.gstRate, egd.cgstRate, egd.sgstRate , egd.igstRate, egd.cessRate, egd.cessNonAdvolRate, egd.sCessRate , egd.sgstAmount, egd.cgstAmount, egd.igstAmount, egd.cessAmount, egd.cessNaAmount , egd.scessAmount, egd.otherCharges, egd.netAmount , egd.isService ,egd.propertyDispCsv,egd.barCode,egd.poRefNo,egd.gstOriginCountryCode,egd.itemUSNo FROM eGstDetail egd WHERE egd.docId = :docId`
- Params: `:docId` ← `docid`; `:docid` ← `DmShared.xNull(docid)`; `:docId` ← `docid`; `:docId` ← `docid`; `:docId` ← `docid`
- Other calls: `DmYesNo.valueOf`, `Egst_TaxScheme.getName`, `Einv_DocTypeCode.getValue`, `Einv_SupplyTypeCode.getValue`, `EinvoiceMongo_Server.get`, `Eway_DocType.getName`, `Eway_SubSupplyType.getName`, `Eway_SupplyType.getName`, `Eway_TransactionType.getViaCode`, `TransportMode.getViaKey`, `YES.toInt`

### `saveEGstRecord`
`public static void saveEGstRecord(EGst_Mdl previousEgst_Mdl, EGst_Mdl currentEgst_Mdl, int actionCode, Connection cnTemp, DmRSSPrivate dmRss) throws Exception`

- SQL 1: `SELECT eg.einv_IrnNo FROM eGst eg WHERE docid = :docid`
- SQL 2: `INSERT INTO eGst ( docid, v_type, v_Prefix, site_Code, v_date, taxScheme, eComGstin , recID, recIdPrefix, recIdSerial, isIgstOnIntra , gstType, isRCM, posStateNumericCode, posStateName, partyType, locationCode , seller_SiteCode, seller_Name, seller_Gstin, seller_GstinDate , seller_TradeName, seller_LegalName, seller_Add1, seller_Add2 , seller_Pin, actFrom_StateNumericCode, seller_StateName, seller_StateNumericCode , seller_CityCode, seller_CityName, seller_Mobile, seller_Email, seller_GstCountryCode, seller_CountryName , buyer_AcCode,buyer_SiteCode, buyer_Name, buyer_Gstin, buyer_GstinDate , buyer_LegalName, buyer_TradeName, buyer_Add1, buyer_Add2 , actTo_StateNumericCode, buyer_StateNumericCode, buyer_StateName, buyer_CityCode, buyer_CityName , buyer_Pin, buyer_Mobile, buyer_Email, buyer_GstCountryCode, buyer_CountryName , dispFrom_SiteCode, dispFrom_Name, dispFrom_Gstin, dispFrom_GstinDate , dispFrom_LegalName, dispFrom_TradeName , dispFrom_Add1, dispFrom_Add2 , dispFrom_Pin, dispFrom_StateNumericCode, dispFrom_StateName ,dispFrom_CityCode, dispFrom_CityName , dispFrom_Mobile, dispFrom_Email, dispFrom_GstCountryCode, dispFrom_CountryName , shipTo_AcCode, shipTo_SiteCode, shipTo_Name , shipTo_Gstin, shipTo_GstinDate , shipTo_LegalName, shipTo_TradeName, shipTo_Add1, shipTo_Add2 , shipTo_Pin, shipTo_StateNumericCode ,shipTo_StateName , shipTo_Mobile, shipTo_Email, shipTo_CityCode, shipTo_CityName, shipTo_GstCountryCode, shipTo_CountryName , consignee_AcCode,consignee_SiteCode, consignee_Name, consignee_Gstin, consignee_GstinDate , consignee_LegalName, consignee_TradeName, consignee_pin, consignee_Mobile, consignee_Email , consignee_Add1, consignee_Add2, consignee_StateNumericCode , consignee_StateName , consignee_CityCode, consignee_CityName, consignee_GstCountryCode, consignee_CountryName , transportMode, transporter_AcCode, transporter_Name, transporter_Id , transporter_Gstin, transporter_GstinDate , lrNo, lrDate, vehicleNo, vehicleTypeCode, vehicleTypeName, gstVehicleType, distance , payee_Name, payee_AcNo, payee_ModeOfPayment, payee_BranchCode, payee_CreditTransfer , payee_PaymentInstruction, payee_PaymentTerms, payee_DirectDebit, payee_CreditDays , payee_PaidAmt, payee_DueAmt , eway_TransactionType , eway_BillNo, eWay_BillDt , eway_ValidUpTo , eway_Alert , eway_SupplyType, eway_SubSupplyType, eway_SubSupplyDesc, eway_DocType , eway_Status, eway_CancelReason , eway_CancelRemark, eway_cancelDt , einv_Version, einv_SupplyType, einv_DocType, einv_IrnNo, einv_ackNo, einv_ackDt , einv_JwtIssuer, einv_Status , discountAmount, taxableAmount, sgstAmount, cgstAmount, igstAmount, cessAmount, cessNaAmount , scessAmount, otherCharges, totalInvAmount, roundOffAmount, netInvAmount, netInvAmountInFC , einv_CancelReason, einv_CancelRemark, einv_cancelDt, eWay_GenerationMode , isEWayBillRequired, isEInvoiceRequired, isSiteTransaction , refDocId,refRecId,refV_Date,refDocOtherRef,refInvDocStartDate , refInvDocEndDate,refRemark,shippingBillNo,shippingBillDate , gstPortCode, refundClaim, gstCurrencyCode ) VALUES ( :docid, :v_type, :v_Prefix, :site_Code, :v_date, :taxScheme, :eComGstin , :recID, :recIdPrefix, :recIdSerial, :isIgstOnIntra , :gstType, :isRCM, :posStateNumericCode, :posStateName, :partyType, :locationCode , :seller_SiteCode, :seller_Name, :seller_Gstin, :seller_GstinDate , :seller_TradeName, :seller_LegalName, :seller_Add1, :seller_Add2 , :seller_Pin, :actFrom_StateNumericCode, :seller_StateName, :seller_StateNumericCode , :seller_CityCode, :seller_CityName, :seller_Mobile, :seller_Email, :seller_GstCountryCode, :seller_CountryName , :buyer_AcCode, :buyer_SiteCode, :buyer_Name, :buyer_Gstin, :buyer_GstinDate , :buyer_LegalName, :buyer_TradeName, :buyer_Add1, :buyer_Add2 , :actTo_StateNumericCode, :buyer_StateNumericCode, :buyer_StateName, :buyer_CityCode, :buyer_CityName , :buyer_Pin, :buyer_Mobile, :buyer_Email, :buyer_GstCountryCode, :buyer_CountryName , :dispFrom_SiteCode, :dispFrom_Name, :dispFrom_Gstin, :dispFrom_GstinDate , `
- SQL 3: `INSERT INTO eGstDetail ( docid, v_SNo, itemCode, ibCode , itemName, itemDesc , hsn, itemGSTNature, rate, quantity, freeQty , unitCode, unitName, gstUnit, batchNo, warrentyDate, expiryDate , grossAmount, discountAmount, taxableAmount, gstRate, cgstRate, sgstRate , igstRate, cessRate, cessNonAdvolRate, sCessRate , sgstAmount, cgstAmount, igstAmount, cessAmount, cessNaAmount, scessAmount, otherCharges, netAmount , isService ,propertyDispCsv,barCode,poRefNo,gstOriginCountryCode,itemUSNo ) VALUES ( :docid, :v_SNo, :itemCode, :ibCode , :itemName, :itemDesc , :hsn, :itemGSTNature, :rate, :quantity, :freeQty , :unitCode, :unitName, :gstUnit, :batchNo, :warrentyDate, :expiryDate , :grossAmount, :discountAmount, :taxableAmount, :gstRate, :cgstRate, :sgstRate , :igstRate, :cessRate, :cessNonAdvolRate, :sCessRate , :sgstAmount, :cgstAmount, :igstAmount, :cessAmount, :cessNaAmount, :scessAmount, :otherCharges, :netAmount , :isService ,:propertyDispCsv,:barCode,:poRefNo,:gstOriginCountryCode,:itemUSNo )`
- SQL 4: `INSERT INTO eGstContractDetail ( docid, v_SNo , receiptAdviceNo,receiptAdviceDate,tenderRefNo,contractNo,extraRefNo,projectRefNo,poRefNo,poRefDate ) VALUES ( :docid, :v_SNo , :receiptAdviceNo,:receiptAdviceDate,:tenderRefNo,:contractNo,:extraRefNo,:projectRefNo,:poRefNo,:poRefDate )`
- SQL 5: `INSERT INTO eGstDocDetail ( docid, v_SNo , docUrl,docEncoded,docInfo ) VALUES ( :docid, :v_SNo , :docUrl,:docEncoded,:docInfo )`
- SQL 6: `SELECT TOP 1 vt.description, vt.short_Name , vt.mnuText as menuName, vt.mnuAttachedInModule as module from gstTrans gt LEFT JOIN voucher_Type vt on vt.v_type = gt.v_type WHERE gt.docid = :docid`
- Params: `:docid` ← `DmShared.xNull(currentEgst_Mdl.getDocid())`; `:docid` ← `DmShared.iNull(currentEgst_Mdl.getDocid()).toString()`; `:v_type` ← `DmShared.xNull(currentEgst_Mdl.getV_Type())`; `:v_Prefix` ← `DmShared.foriegnKey(currentEgst_Mdl.getV_Prefix())`; `:site_Code` ← `DmShared.foriegnKey(currentEgst_Mdl.getSite_Code())`; `:v_date` ← `DmShared.dNull(currentEgst_Mdl.getV_Date())`; `:taxScheme` ← `DmShared.iNull(currentEgst_Mdl.getTaxSchemeCode()).toString()`; `:eComGstin` ← `DmShared.xNull(currentEgst_Mdl.geteComGstin())`; `:recID` ← `DmShared.xNull(currentEgst_Mdl.getRecId().getRecIdValue())`; `:recIdSerial` ← `DmShared.xNull(String.valueOf(currentEgst_Mdl.getRecId().getCounter()))`; `:recIdPrefix` ← `DmShared.xNull(currentEgst_Mdl.getRecId().getPrefix())`; `:eWay_GenerationMode` ← `DmShared.foriegnKey(currentEgst_Mdl.getEway_GenerationMode())`; `:isIgstOnIntra` ← `(DmShared.xNull(currentEgst_Mdl.getIgstOnIntra()).trim().equalsIgnoreCase("") ? DmEnum.DmY`; `:gstType` ← `DmShared.iNull(currentEgst_Mdl.getGstType()).toString()`; `:isEWayBillRequired` ← `currentEgst_Mdl.isEwayBillRequired() ? 1 : 0`; `:isEInvoiceRequired` ← `currentEgst_Mdl.isEinvoiceRequired() ? 1 : 0`; `:isSiteTransaction` ← `currentEgst_Mdl.isSiteTransaction() ? 1 : 0`; `:isRCM` ← `(DmShared.xNull(currentEgst_Mdl.getReverseCharge()).trim().equalsIgnoreCase("") ? DmEnum.D`; `:posStateNumericCode` ← `DmShared.foriegnKey(currentEgst_Mdl.getPosStateNumericCode())`; `:posStateName` ← `DmShared.foriegnKey(currentEgst_Mdl.getPosStateName())`; `:partyType` ← `DmShared.xNull(currentEgst_Mdl.getPartyType())`; `:locationCode` ← `DmShared.foriegnKey(currentEgst_Mdl.getLocationCode())`; `:seller_SiteCode` ← `DmShared.foriegnKey(currentEgst_Mdl.getConsigner_Mdl().getSiteCode())`; `:seller_Name` ← `DmShared.xNull(currentEgst_Mdl.getConsigner_Mdl().getSharedKeyFieldName())`; `:seller_Gstin` ← `DmShared.xNull(currentEgst_Mdl.getConsigner_Mdl().getGstin())`; `:seller_GstinDate` ← `DmServer.convertDate(currentEgst_Mdl.getConsigner_Mdl().getGstinDate())`; `:seller_TradeName` ← `DmShared.xNull(currentEgst_Mdl.getConsigner_Mdl().getTradeName())`; `:seller_LegalName` ← `DmShared.xNull(currentEgst_Mdl.getConsigner_Mdl().getLegalName())`; `:seller_Add1` ← `DmShared.xNull(currentEgst_Mdl.getConsigner_Mdl().getAddress1())`; `:seller_Add2` ← `DmShared.xNull(currentEgst_Mdl.getConsigner_Mdl().getAddress2())`; `:seller_Pin` ← `DmShared.xNull(currentEgst_Mdl.getConsigner_Mdl().getPinCode())`; `:seller_StateNumericCode` ← `DmShared.foriegnKey(currentEgst_Mdl.getConsigner_Mdl().getStateNumericCode())`; `:seller_StateName` ← `DmShared.xNull(currentEgst_Mdl.getConsigner_Mdl().getStateName())`; `:seller_CityCode` ← `DmShared.foriegnKey(currentEgst_Mdl.getConsigner_Mdl().getCityCode())`; `:seller_CityName` ← `DmShared.xNull(currentEgst_Mdl.getConsigner_Mdl().getCityName())`; `:seller_Mobile` ← `DmShared.xNull(currentEgst_Mdl.getConsigner_Mdl().getPhone())`; `:seller_Email` ← `DmShared.xNull(currentEgst_Mdl.getConsigner_Mdl().getEmail())`; `:seller_GstCountryCode` ← `DmShared.foriegnKey(currentEgst_Mdl.getConsigner_Mdl().getGstCountryCode())`; `:seller_CountryName` ← `DmShared.foriegnKey(currentEgst_Mdl.getConsigner_Mdl().getCountryName())`; `:buyer_AcCode` ← `DmShared.foriegnKey(currentEgst_Mdl.getBillTo_Mdl().getSharedKeyFieldCode())`; `:buyer_SiteCode` ← `DmShared.foriegnKey(currentEgst_Mdl.getBillTo_Mdl().getSiteCode())`; `:buyer_Name` ← `DmShared.xNull(currentEgst_Mdl.getBillTo_Mdl().getSharedKeyFieldName())`; `:buyer_Gstin` ← `DmShared.xNull(currentEgst_Mdl.getBillTo_Mdl().getGstin())`; `:buyer_GstinDate` ← `DmServer.convertDate(currentEgst_Mdl.getBillTo_Mdl().getGstinDate())`; `:buyer_LegalName` ← `DmShared.xNull(currentEgst_Mdl.getBillTo_Mdl().getLegalName())`; `:buyer_TradeName` ← `DmShared.xNull(currentEgst_Mdl.getBillTo_Mdl().getTradeName())`; `:buyer_Add1` ← `DmShared.xNull(currentEgst_Mdl.getBillTo_Mdl().getAddress1())`; `:buyer_Add2` ← `DmShared.xNull(currentEgst_Mdl.getBillTo_Mdl().getAddress2())`; `:buyer_StateNumericCode` ← `DmShared.foriegnKey(currentEgst_Mdl.getBillTo_Mdl().getStateNumericCode())`; `:buyer_StateName` ← `DmShared.xNull(currentEgst_Mdl.getBillTo_Mdl().getStateName())`; `:buyer_CityCode` ← `DmShared.foriegnKey(currentEgst_Mdl.getBillTo_Mdl().getCityCode())`; `:buyer_CityName` ← `DmShared.xNull(currentEgst_Mdl.getBillTo_Mdl().getCityName())`; `:buyer_Pin` ← `DmShared.xNull(currentEgst_Mdl.getBillTo_Mdl().getPinCode())`; `:buyer_Mobile` ← `DmShared.xNull(currentEgst_Mdl.getBillTo_Mdl().getPhone())`; `:buyer_Email` ← `DmShared.xNull(currentEgst_Mdl.getBillTo_Mdl().getEmail())`; `:buyer_GstCountryCode` ← `DmShared.foriegnKey(currentEgst_Mdl.getBillTo_Mdl().getGstCountryCode())`; `:buyer_CountryName` ← `DmShared.foriegnKey(currentEgst_Mdl.getBillTo_Mdl().getCountryName())`; `:dispFrom_SiteCode` ← `DmShared.foriegnKey(currentEgst_Mdl.getDispatchFrom_Mdl().getSiteCode())`; `:dispFrom_Name` ← `DmShared.xNull(currentEgst_Mdl.getDispatchFrom_Mdl().getSharedKeyFieldName())`; `:dispFrom_Gstin` ← `DmShared.xNull(currentEgst_Mdl.getDispatchFrom_Mdl().getGstin())`; `:dispFrom_GstinDate` ← `DmServer.convertDate(currentEgst_Mdl.getDispatchFrom_Mdl().getGstinDate())`; `:dispFrom_TradeName` ← `DmShared.xNull(currentEgst_Mdl.getDispatchFrom_Mdl().getTradeName())`; `:dispFrom_LegalName` ← `DmShared.xNull(currentEgst_Mdl.getDispatchFrom_Mdl().getLegalName())`; `:dispFrom_Add1` ← `DmShared.xNull(currentEgst_Mdl.getDispatchFrom_Mdl().getAddress1())`; `:dispFrom_Add2` ← `DmShared.xNull(currentEgst_Mdl.getDispatchFrom_Mdl().getAddress2())`; `:dispFrom_CityCode` ← `DmShared.foriegnKey(currentEgst_Mdl.getDispatchFrom_Mdl().getCityCode())`; `:dispFrom_CityName` ← `DmShared.xNull(currentEgst_Mdl.getDispatchFrom_Mdl().getCityName())`; `:dispFrom_StateName` ← `DmShared.xNull(currentEgst_Mdl.getDispatchFrom_Mdl().getStateName())`; `:dispFrom_Pin` ← `DmShared.xNull(currentEgst_Mdl.getDispatchFrom_Mdl().getPinCode())`; `:dispFrom_StateNumericCode` ← `DmShared.foriegnKey(currentEgst_Mdl.getDispatchFrom_Mdl().getStateNumericCode())`; `:dispFrom_Mobile` ← `DmShared.xNull(currentEgst_Mdl.getDispatchFrom_Mdl().getPhone())`; `:dispFrom_Email` ← `DmShared.xNull(currentEgst_Mdl.getDispatchFrom_Mdl().getEmail())`; `:dispFrom_GstCountryCode` ← `DmShared.foriegnKey(currentEgst_Mdl.getDispatchFrom_Mdl().getGstCountryCode())`; `:dispFrom_CountryName` ← `DmShared.foriegnKey(currentEgst_Mdl.getDispatchFrom_Mdl().getCountryName())`; `:shipTo_AcCode` ← `DmShared.foriegnKey(currentEgst_Mdl.getShipTo_Mdl().getSharedKeyFieldCode())`; `:shipTo_SiteCode` ← `DmShared.foriegnKey(currentEgst_Mdl.getShipTo_Mdl().getSiteCode())`; `:shipTo_Name` ← `DmShared.xNull(currentEgst_Mdl.getShipTo_Mdl().getSharedKeyFieldName())`; `:shipTo_Gstin` ← `DmShared.xNull(currentEgst_Mdl.getShipTo_Mdl().getGstin())`; `:shipTo_GstinDate` ← `DmServer.convertDate(currentEgst_Mdl.getShipTo_Mdl().getGstinDate())`; `:shipTo_LegalName` ← `DmShared.xNull(currentEgst_Mdl.getShipTo_Mdl().getLegalName())`; `:shipTo_TradeName` ← `DmShared.xNull(currentEgst_Mdl.getShipTo_Mdl().getTradeName())`; `:shipTo_Add1` ← `DmShared.xNull(currentEgst_Mdl.getShipTo_Mdl().getAddress1())`; `:shipTo_Add2` ← `DmShared.xNull(currentEgst_Mdl.getShipTo_Mdl().getAddress2())`; `:shipTo_Pin` ← `DmShared.xNull(currentEgst_Mdl.getShipTo_Mdl().getPinCode())`; `:shipTo_CityCode` ← `DmShared.foriegnKey(currentEgst_Mdl.getShipTo_Mdl().getCityCode())`; `:shipTo_CityName` ← `DmShared.xNull(currentEgst_Mdl.getShipTo_Mdl().getCityName())`; `:shipTo_StateName` ← `DmShared.xNull(currentEgst_Mdl.getShipTo_Mdl().getStateName())`; `:shipTo_StateNumericCode` ← `DmShared.foriegnKey(currentEgst_Mdl.getShipTo_Mdl().getStateNumericCode())`; `:shipTo_Mobile` ← `DmShared.xNull(currentEgst_Mdl.getShipTo_Mdl().getPhone())`; `:shipTo_Email` ← `DmShared.xNull(currentEgst_Mdl.getShipTo_Mdl().getEmail())`
- Other calls: `DmYesNo.getViaShortName`, `EGST_ACTION.getViaCode`, `E_INV_GENRATE_BILL.getCode`, `E_WAY_CANCEL_BILL.getCode`, `EinvoiceMongo_Server.getUUID`, `EinvoiceMongo_Server.save`, `ModifyReason_Server.saveNew`, `NO.toInt`, `SYSTEM_ACTIVITY.getName`

### `updateGstDocument`
`public static void updateGstDocument(GstDocument_Mdl prevGstDoc_Mdl, GstDocument_Mdl currentGstDoc_Mdl, int actionCode, Connection cnTemp, DmRSSPrivate dmRss) throws Exception`

- SQL 1: `SELECT TOP 1 gt.recid, vt.description, vt.short_Name , vt.mnuText as menuName, vt.mnuAttachedInModule as module from gstTrans gt LEFT JOIN voucher_Type vt on vt.v_type = gt.v_type WHERE gt.docid =:docid`
- SQL 2: `update gstTrans set gstDocType=:gstDocType ,gstDocNature=:gstDocNature ,itemGstNature=:itemGstNature ,itemGstType=:itemGstType where docId=:docId`
- SQL 3: `update gstTransHead set gstDocType=:gstDocType ,gstDocNature=:gstDocNature ,itemGstNature=:itemGstNature ,itemGstType=:itemGstType where docId=:docId`
- Params: `:docId` ← `currentGstDoc_Mdl.getDocid()`; `:docId` ← `currentGstDoc_Mdl.getDocid()`; `:gstDocType` ← `DmShared.foriegnKey(currentGstDoc_Mdl.getGstDocTypeCode())`; `:gstDocNature` ← `DmShared.foriegnKey(currentGstDoc_Mdl.getGstDocNatureCode())`; `:itemGstNature` ← `DmShared.xNull(currentGstDoc_Mdl.getItemGSTNatureCode())`; `:itemGstType` ← `DmShared.xNull(currentGstDoc_Mdl.getItemGstTypeCode())`; `:docId` ← `currentGstDoc_Mdl.getDocid()`; `:gstDocType` ← `DmShared.foriegnKey(currentGstDoc_Mdl.getGstDocTypeCode())`; `:gstDocNature` ← `DmShared.foriegnKey(currentGstDoc_Mdl.getGstDocNatureCode())`; `:itemGstNature` ← `DmShared.xNull(currentGstDoc_Mdl.getItemGSTNatureCode())`; `:itemGstType` ← `DmShared.xNull(currentGstDoc_Mdl.getItemGstTypeCode())`
- Other calls: `EGST_ACTION.getViaCode`, `ModifyReason_Server.saveNew`, `SYSTEM_ACTIVITY.getName`

### `deleteEGstRecord`
`private static void deleteEGstRecord(String docId, Connection cnTemp)`

- SQL 1: `DELETE FROM eGstContractDetail WHERE docId = :docId`
- SQL 2: `DELETE FROM eGstDocDetail WHERE docId = :docId`
- SQL 3: `DELETE FROM eGstDetail WHERE docId = :docId`
- SQL 4: `DELETE FROM eGst WHERE docId = :docId`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:docId` ← `DmShared.xNull(docId)`; `:docId` ← `DmShared.xNull(docId)`; `:docId` ← `DmShared.xNull(docId)`

### `createFile`
`private static void createFile(byte[] byteArray, String directoryPath, String serverFileName) throws Exception`


### `getEWayJson`
`private static String getEWayJson(EGst_Mdl eGstMdl)`

- Other calls: `EWBJson.ReqGenEwbPl`, `Gst_Shared.concatPartyInfo`, `ReqGenEwbPl.ItemList`, `YES.getEnumShortName`

### `getEInvoiceJson`
`private static String getEInvoiceJson(EGst_Mdl eGstMdl)`

- Other calls: `AddlDocDtls.add`, `AttribDtls.add`, `AttribDtls.isEmpty`, `ContrDtls.add`, `EWAY_GenerationMode.getViaCode`, `E_InvoiceCommon.ReqPlGenIRN`, `E_WAY_GENERATED_WITH_E_INVOICE.getCode`, `Gst_Shared.concatPartyInfo`, `ItemList.add`, `ReqPlGenIRN.BuyerDetails`, `ReqPlGenIRN.DispatchedDetails`, `ReqPlGenIRN.DocDetails`, `ReqPlGenIRN.ExpDetails`, `ReqPlGenIRN.ItmList`, `ReqPlGenIRN.PayDetails`, `ReqPlGenIRN.SellerDetails`, `ReqPlGenIRN.ShippedDetails`, `ReqPlGenIRN.TranDetails`, `ReqPlGenIRN.ValDetails`

### `getEWayViaIrnJson`
`private static String getEWayViaIrnJson(EGst_Mdl eGstMdl)`

- Other calls: `E_InvoiceCommon.ReqPlGenEwbByIRN`, `Gst_Shared.concatPartyInfo`

### `loadEgstParamDetails`
`private static void loadEgstParamDetails(Connection cnTemp, DmRSSPrivate dmRss) throws Exception`


### `convertTimestamp`
`public static Timestamp convertTimestamp(String value) throws Exception`


### `convertTimestamp`
`private static Timestamp convertTimestamp(String pattern, String value) throws Exception`

- Errors: "Pattern should not be blank." | "Error in converting value in time stamp."

### `convertDate`
`private static String convertDate(String date)`


### `convertDate`
`private static String convertDate(String date, String pattern)`

- Other calls: `DateTimeFormatter.ofPattern`

### `getDateTime`
`public static String getDateTime(Timestamp entryDateTime) throws Exception`


### `getDateTime`
`public static String getDateTime(Timestamp entryDateTime, String format) throws Exception`


### `getEinvoice_Mongo_Mdl`
`private static EinvoiceMongo_Mdl getEinvoice_Mongo_Mdl(EGst_Mdl eGst_Mdl, Connection cnTemp) throws Exception`

- SQL 1: `SELECT TOP 1 vt.mnuText as menuName, vt.mnuAttachedInModule as module from gstTrans gt LEFT JOIN voucher_Type vt on vt.v_type = gt.v_type WHERE gt.docid =:docid`
- Params: `:docId` ← `eGst_Mdl.getDocid()`

### `getListFromCsv`
`private static HashMap<String, String> getListFromCsv(String strCsv)`


### `getCsvFromList`
`private static String getCsvFromList(HashMap<String, String> strList)`


### `getToken`
`public static EGstToken_Mdl getToken(Connection cnTemp, DmRSSPrivate dmRss) throws Exception`

- SQL 1: `select egt.tokenNo,egt.tokenDt, egt.clientId,egt.userName,tokenExpiryDt,egt.sek from eGstToken egt where egt.userGstin = :userGstin`
- Params: `:userGstin` ← `DmServer.getSsnSiteGSTIN(dmRss)`; `:userGstin` ← `"34AACCC1596Q002"`

### `saveToken`
`private static void saveToken(EGstToken_Mdl token_Mdl, Connection cnTemp) throws Exception`

- SQL 1: `INSERT INTO eGstToken ( userGstin, clientId, tokenNo, tokenDt , userName, tokenExpiryDt,sek) VALUES (:userGstin, :clientId, :tokenNo, :tokenDt , :userName, :tokenExpiryDt ,:sek )`
- Params: `:userGstin` ← `DmShared.xNull(token_Mdl.getUserGstin())`; `:tokenNo` ← `DmShared.xNull(token_Mdl.getAuthtoken())`; `:clientId` ← `DmShared.xNull(token_Mdl.getClientId())`; `:tokenDt` ← `convertTimestamp(token_Mdl.getTokenDt())`; `:sek` ← `DmShared.xNull(token_Mdl.getSek())`; `:userName` ← `DmShared.xNull(token_Mdl.getUserName())`; `:tokenExpiryDt` ← `expiryTimestamp`

### `deleteToken`
`private static void deleteToken(String userGstin, Connection cnTemp) throws Exception`

- SQL 1: `delete eGstToken where userGstin = :userGstin`
- Params: `:userGstin` ← `DmShared.xNull(userGstin)`

## Server `EinvoiceMongo_Server.java` (149 lines)
Helpers used: `DmServer.getLocalIPAddresses`, `DmServer.getMdbCollection`, `DmServer.getSsnCompanyCYear`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnId`, `DmServer.getSsnPLTCode`, `DmServer.getSsnPLTName`, `DmServer.getSsnSiteName`, `DmServer.getSsnSiteSName`, `DmServer.getSsnUserDescription`, `DmServer.getSsnUserName`, `DmShared.iNull`, `DmShared.xNull`

### `save`
`public static void save(EinvoiceMongo_Mdl einvoice_Mongo, DmRSS dmRSS) throws Exception`

- Errors: "Invalid docid" | "Invalid Data"

### `get`
`public static EinvoiceMongo_Mdl get(String filterFiled, String filterValue, DmRSS dmRSS) throws Exception`


### `genrateByte`
`private static byte[] genrateByte(String data) throws Exception`

- Other calls: `Base64.getDecoder`

### `getByteString`
`private static String getByteString(byte[] data) throws Exception`

- Other calls: `Base64.getEncoder`

### `getByteArrayInputStream`
`public static ByteArrayInputStream getByteArrayInputStream(String bytStr) throws Exception`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.server`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.client`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.eGstPopup.shared`, `dataman.dmToolkit.gst.shared`, `dataman.dmToolkit.modifyReason.server`, `dataman.dmToolkit.modifyReason.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
**Kya hai:** **GST e-way bill aur e-invoice** (GSP/IRP se API). Tables `gstTrans`, `gstTransHead` (document ka GST record), `eGst`, `eGstDetail`, `eGstContractDetail`, `eGstDocDetail`, `eGstToken` (API token).
**Server (`GST`/`EGst` classes):** `getAuthToken`, `genrateEWayBill`, `genrateEInvoice` (IRN), `genrateEWayBillViaIRN`, `getEWayBillDetail`, `cancelEWayBill`, `cancelEinvIRN` (reasons enum `EwayCancelReason`/`EinvCancelReason`), `printEWayBill`, `rectifyEwayJson`, `processEGst`, `saveEGstRecord/getEGstRecord/deleteEGstRecord`, `saveGstTrans/saveGstTransHead/updateRefGstDocument/getGstDocumentDetail/deleteGstTrans`, `getGSTS`, `getURL`. JSON files banate hain (`createFile`, `getEWayJson`).
**Dhyan:** vendor jars (`TaxProEInvoice`, `TaxProEwb`) naye project me Maven me nahi milte; GST API ka asli endpoint/credentials config se. Naya design: `EGstService` + HTTP client (OkHttp ya Spring `RestClient`), token cache.
