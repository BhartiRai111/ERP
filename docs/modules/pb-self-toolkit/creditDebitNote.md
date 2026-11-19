# pb-self-toolkit / creditDebitNote

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/creditDebitNote/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: creditDebitNote, creditDebitNoteDetail, LedgerM
- Update tables: creditDebitNote, LedgerM, pBillDetail, eGst
- Delete cascade (in order): LedgerM, creditDebitNoteDetail, creditDebitNote
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Voucher Type (Text); Transaction Type (Text); Ref. Voucher Type (Text); Party (Text); Ref. Bill No. (Text); Item Nature (Text); GST Nature (Text); Item type (Text); Party Bill No. (Text); party Bill Date (Text); rule: GridRow; rule: Items are mandatory.
- Lookups/suggestions: getTxtHVoucher, getTxtHRefVoucherType, getTxtHPartyName, getTxtHGstType, getTxtHLocation, getTxtHAdjBillNo, getTxtHRefBillNo, getTxtHDIfferenceType, getTxtHHSN, getTxtHItemGSTNature, getTxtHDueEntryNo, getTxtHTransactionType, getTxtHItem
- CSV export: no

## Files
| file | lines |
|---|---|
| client/CreditDebitNote.java | 2310 |
| client/CreditDebitNotePurchase.java | 24 |
| client/CreditDebitNoteSales.java | 23 |
| client/CreditDebitNote_Dsg.java | 910 |
| client/GridEditPopup.java | 425 |
| client/GridEditPopup_Dsg.java | 231 |
| server/CreditDebitNote_SrvImpl.java | 4348 |
| shared/BillLinking_Mdl.java | 185 |
| shared/CreditDebitNote_Mdl.java | 709 |
| shared/CreditDebitNote_Srv.java | 90 |
| shared/CreditDebitNote_SrvAsync.java | 89 |
| shared/CreditDebitNote_Vld.java | 65 |
| shared/DefaultValue_Mdl.java | 275 |
| shared/Find_Mdl.java | 105 |
| shared/GridEditPopup_Mdl.java | 50 |
| shared/GridItemDetail_Mdl.java | 524 |
| shared/TxtHHSN_Mdl.java | 40 |
| shared/TxtHItemName_Mdl.java | 167 |
| shared/TxtHItemType_Mdl.java | 23 |
| shared/TxtHPartyName_Mdl.java | 150 |
| shared/TxtHVoucherNo_Mdl.java | 95 |

## UI labels / columns (client)
`Item Name`, `Quantity`, `Rate`, `Amount`, `Narration`, `TDS`, `Party`, `Voucher Type`, `Date`, `Ref. Voucher Type`, `Trans Type`, `State`, `Against From`, `Diff. Type`, `Adj Bill No.`, `Ref Bill No.`, `Voucher No.`, `HSN Filter`, `Due Entry No.`, `Party Bill No.`, `Party Bill Date`, `Remark`, `TDS On Amount`

## Models (shared DTOs)
### TxtHItemType_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | shortname |

### TxtHVoucherNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | v_SNo |
| String | voucherType |
| String | shortName |
| String | v_Date |
| String | refRecId |
| String | refNo |
| String | refDate |
| String | dueDate |
| String | docValue |

### TxtHItemName_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | itemDesc |
| String | manualCode |
| String | purchaseUnitCode |
| String | purchaseUnitName |
| String | purchaseUnitDisplayName |
| String | purchaseUnitConversionRate |
| String | purchaseUnitDecimalPlaces |
| String | skuCode |
| String | skuName |
| String | skuDecimalPlaces |
| String | itsBasedOn |
| String | hsnCode |
| String | itemGSTNature |
| String | hsn |
| String | itemType |
| String | itemTypeName |
| String | propertyList |

### TxtHHSN_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | hsn |
| String | natureCode |
| String | natureName |

### DefaultValue_Mdl
| type | field |
|---|---|
| TxtHVoucherType_Mdl | voucherType |
| TxtHVoucherType_Mdl | newVoucherType |
| String | currentDate |
| String | siteCode |
| String | siteStateCode |
| String | isSiteStateUT |
| String | siteGstin |
| String | siteGstinDate |
| String | newVoucherDate |
| String | itemTypeCode |
| String | itemTypeName |
| String | itemGSTNatureCode |
| String | itemGSTNatureName |
| String | purchaseSaleAcPostingBasedOn |
| boolean | isContinuousNewMode |
| boolean | gstApplicable |
| boolean | inventoryAccountingActive |
| String | itcCode |
| String | itcName |
| String | roundOffAcCode |
| String | finalDicountAcCode |
| String | tcsActiveFrom |
| String | tcsLimitValue |
| boolean | tcsAutoCalculate |
| boolean | autoTcsBasedOnSales |
| TreeMap<String, GstLocation_Mdl> | gstLocationTreeMap |
| List<OHHeadAccount_Mdl> | ohHeadAccount_Mdls |
| TxtHTransactionType_Mdl | transactionType_Mdl |

### GridEditPopup_Mdl
| type | field |
|---|---|
| String | mode |
| String | qty |
| String | rate |
| String | amount |

### BillLinking_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | partyCode |
| String | partyName |
| String | refVoucherTypeCode |
| String | refVoucherTypeName |
| String | refBillDocId |
| String | refBillRecId |
| String | partyBillNo |
| String | partyBillDate |
| String | diffTypeCode |
| String | diffTypeName |
| String | gstNatureCode |
| String | gstNatureName |
| List<GridItemDetail_Mdl> | listItemDetail |
| TDSPopup_Mdl | tdsTcsPopupMdl |
| boolean | tdsTcsApplicable |
| boolean | tcsActive |
| String | locationCode |
| String | locationName |

### CreditDebitNote_Mdl (extends `KeyField implements OHBaseAPI, Serializable, IsSerializable`)
| type | field |
|---|---|
| RecId | recIdObj |
| ModifyReason_Mdl | modifyReasonMdl |
| String | wtaCode |
| String | voucherTypeCode |
| String | voucherTypeName |
| String | voucherCategory |
| String | VoucherPrefix |
| String | voucherDate |
| String | menuText |
| UserPermission | userPermission |
| String | partyCode |
| String | partyName |
| String | supplierGstin |
| String | supplierGstinDate |
| String | partyStateCode |
| String | partyStateName |
| String | partyType |
| String | partyGstin |
| String | partyGstinDate |
| String | partyTypeForEntry |
| String | placeOfSupply |
| String | locationCode |
| String | locationName |
| String | refVoucherTypeCode |
| String | refVoucherTypeName |
| String | refBillDocId |
| String | refBillRecId |
| String | adjBillDocId |
| String | adjBillRecId |
| String | adjBillV_SNo |
| String | adjBillDate |
| String | adjBillVType |
| String | adjBillDocValue |
| String | partyBillNo |
| String | partyBillDate |
| String | diffTypeCode |
| String | diffTypeName |
| String | diffTypeManualCode |
| String | dueEntryCode |
| String | dueEntryName |
| String | dueEntryDate |
| String | hsnCode |
| String | hsnName |
| String | gstNatureCode |
| String | gstNatureName |
| String | mode |
| String | dueDate |
| String | remark |
| String | itcCode |
| String | itcName |
| String | rcmApplicable |
| String | calculateOnCode |
| String | calculateOnName |
| String | balancingAc |
| String | u_EntDt_LatestLine |
| String | approvedBy |
| String | approvalDateTime |
| String | calculateOnText |
| String | guiConfigCode |
| String | grossAmount |
| String | transTypeCode |
| String | transTypeName |
| String | itemCatCodeCSV |
| DefaultValue_Mdl | defaultValue_Mdl |
| List<GridItemDetail_Mdl> | grdItemDetail |
| HashMap<Integer, PartyDetailPopup_Mdl> | hmPartyInfo |
| DmGUI_Mdl | guiMdl |
| GstDocument_Mdl | gstDoc_Mdl |
| List<OverHead_Mdl> | overHeadList |
| TDSPopup_Mdl | tdsTcsPopupMdl |
| boolean | tdsTcsApplicable |
| boolean | tcsActive |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | voucherDate |
| String | voucherType |
| String | partyName |
| String | refVoucherType |
| String | refBillNo |
| String | adjBillNo |
| String | diffType |
| String | hsnFilter |
| String | dueEntryNo |
| String | dueEntryDate |

### TxtHPartyName_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | stateCode |
| String | stateName |
| String | cityCode |
| String | cityName |
| String | manualCode |
| String | partyType |
| String | gstin |
| String | gstinDate |
| String | dueDays |
| String | tan |
| String | pan |
| String | partyNature |
| String | tcsTdsCategoryCode |
| String | tcsTdsCategoryName |
| boolean | tcsActive |

### GridItemDetail_Mdl (extends `FullPaperProperty_Mdl implements OHBaseAPI, Serializable, IsSerializable, OHLExtended`)
| type | field |
|---|---|
| String | dueDocId |
| String | dueRecId |
| String | dueDate |
| String | dueV_SNo |
| String | adjDocId |
| String | adjRecId |
| String | adjV_SNo |
| String | refDocId |
| String | refRecId |
| String | refV_SNo |
| String | hsnCode |
| String | hsn |
| String | unitCode |
| String | unitName |
| String | unitDecimalPlaces |
| String | skuCode |
| String | skuName |
| String | skuDecimalPlaces |
| String | gstUnitCode |
| String | itemType |
| String | skuQty |
| String | qty |
| String | billQty |
| String | billRate |
| String | rate |
| String | amount |
| String | narration |
| String | qtyDiff |
| String | rateDiff |
| String | otherDiff |
| String | overAllDIff |
| String | itcCode |
| String | itcName |
| String | rcmApplicable |
| boolean | manualGstType |
| String | itemGSTNature |
| String | calculateOnCode |
| String | calculateOnName |
| String | gstTypeCode |
| String | gstTypeName |
| String | landedSkuRate |
| String | landedSkuValue |
| String | vSNo |
| String | isStatus |
| String | balancingAc |
| String | itemUnitCode |
| String | values |
| String | locationCode |
| String | itsBasedOn |
| boolean | isManualGstType |
| List<OverHeadLine_Mdl> | OHLList |
| List<ITS_Mdl> | ITS_Mdls |

## RPC interface
| method | params | returns |
|---|---|---|
| getVoucherType | String partyType | DmResult<TxtHVoucherType_Mdl> |
| getTxtHVoucher | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucherType_Mdl>> |
| getTxtHPartyName | String voucherCategory, String v_Type, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHPartyName_Mdl>> |
| getTxtHRefBillNo | String subCode, String refVoucherType, String v_Date, String differenceType, String billDocId, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucherNo_Mdl>> |
| getTxtHAdjBillNo | String voucherCategory, String subCode, String refVoucherType, String v_Date, String differenceType, String billDocId, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucherNo_Mdl>> |
| getTxtHGstType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHRefVoucherType | final String vCat, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDIfferenceType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHHSN | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHHSN_Mdl>> |
| getTxtHItemGSTNature | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDueEntryNo | String partyCode, String v_Date, String refType, String diffType, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucherNo_Mdl>> |
| getTxtHLocation | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getLocation | String locationCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getPartyDetails | String supplierCode, String voucherCategory, String v_Type, DmHelper pageing, boolean blnToEndOfFile | DmResult<TxtHPartyName_Mdl> |
| getAdjBillNo | String voucherCategory, String subCode, String refVoucherType, String v_Date, String differenceType, String billDocId, DmHelper pageing, boolean blnToEndOfFile | DmResult<TxtHVoucherNo_Mdl> |
| getTxtHTransactionType | String v_Type, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHTransactionType_Mdl>> |
| getTxtHItem | String v_Type, String supplierCode, String itemCatCodeCSV, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemName_Mdl>> |
| fillGridDetails | CreditDebitNote_Mdl noteDue_Mdl | DmResult<List<GridItemDetail_Mdl>> |
| setApproval | String approvedBy, boolean isApprove, String docId | DmResult<String> |
| getServerDateTime |  | DmResult<String[]> |
| getDefaultValues | String menuText | DmResult<DefaultValue_Mdl> |
| saveRecord | CreditDebitNote_Mdl mrTemp, String voucherPrefix, boolean isApprove | DmResult<String> |
| getMoveRec | String strKey | DmResult<CreditDebitNote_Mdl> |
| deleteRecord | String strKey, String guiConfigCode, ModifyReason_Mdl modifyReasonMdl | DmResult<String> |
| getFind | UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getNavigator | UserPermission userPermission | DmResult<List<KeyFieldExtended>> |
| printRecords | String docId, String v_Type, String reportTitle | DmResult<String> |
| getAdditionalPartyDetail | String subCode, String docid, int recordTypeCode | DmResult<PartyDetailPopup_Mdl> |
| getTDSData | String tdsCategoryCode, String date | DmResult<List<GrdTdsDetail_Mdl>> |
| getITS | String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst | DmResult<List<ITS_Mdl>> |
| sendMessage | String docid, String vTypeCode, String reportTitle | DmResult<String> |
| getEgstDetail | String strKey, String vTypeCode | DmResult<MasterEGst_Mdl> |

## Validator `CreditDebitNote_Vld`
```java
public class CreditDebitNote_Vld {
    private static final DmVoucherCategory lvc = new DmVoucherCategory();
    public static void chkSave(CreditDebitNote_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getVoucherTypeCode(), "Voucher Type", 100);
        DmShared.isValidText(mrTemp.getTransTypeCode().trim(), "Transaction Type", 120);
        DmShared.isValidText(mrTemp.getPartyCode(), "Ref. Voucher Type", 200);
        DmShared.isValidText(mrTemp.getPartyCode(), "Party", 300);
        DmShared.isValidText(mrTemp.getPartyCode(), "Ref. Bill No.", 500);
        DmShared.isValidText(mrTemp.getPartyCode(), "Item Nature", 600);
        DmShared.isValidText(mrTemp.getPartyCode(), "GST Nature", 700);
        DmShared.isValidText(mrTemp.getPartyCode(), "Item type", 800);
        DmShared.isValidText(mrTemp.getPartyCode(), "Party Bill No.", 900);
        DmShared.isValidText(mrTemp.getPartyCode(), "party Bill Date", 1000);
        GSTDocumentInfo_Vld.validate(mrTemp.getGstDoc_Mdl());
        boolean flag = false;
        List<GridItemDetail_Mdl> listItemDetail = mrTemp.getGrdItemDetail();
        for (GridItemDetail_Mdl detail_Mdl : listItemDetail) {
            if (DmShared.iNull(detail_Mdl.getSharedKeyFieldCode()).longValue() != 0) {
                flag = true;
                if (mrTemp.getDefaultValue_Mdl().isInventoryAccountingActive()) {
                    if (DmShared.xNull(detail_Mdl.getBalancingAc()).equalsIgnoreCase("")) {
                        throw new DmException(700, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please define GR/IR A/c in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (DmShared.vNull(detail_Mdl.getLandedSkuValue()).doubleValue() < 0) {
                    throw new DmException(700, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Landed SKU Value is negative in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
            }
        }
        if (!flag) {
            throw new DmException(1200, "Items are mandatory.");
        }
        OverHead_Vld.validateOverHead(mrTemp.getOverHeadList(), true);
    }
}
```

## Server `CreditDebitNote_SrvImpl.java` (4348 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.checkFinancialYearDate`, `DmServer.checkForwardVoucherDateTime`, `DmServer.checkTransactionLockDate`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getLocalIPAddresses`, `DmServer.getParamList`, `DmServer.getRecId`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnPLTCode`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteGSTIN`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserViewCondition`, `DmServer.getUserWithDateTime`, `DmServer.isDocumentEMailRequired`, `DmServer.sendMail`, `DmServer.setSsnOldData`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.haveControlPermission`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getTxtHVoucher`
`public DmResultPageing<List<TxtHVoucherType_Mdl>> getTxtHVoucher(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type as code , vt.description as name , vt.category , vt.guiTemplateCode , vt.balancingAc FROM voucher_Type vt WHERE vt.category IN ( {…} ) AND vt.isActive = 1`

### `getVoucherType`
`public DmResult<TxtHVoucherType_Mdl> getVoucherType(String partyType)`


### `getVoucherType`
`private TxtHVoucherType_Mdl getVoucherType(String partyType, Connection cnTemp) throws Exception`

- SQL 1: `SELECT TOP 1 vt.v_Type AS code , vt.description AS name , vt.category , vt.guiTemplateCode , vt.balancingAc FROM voucher_Type vt WHERE vt.category IN ( {…} ) AND isActive = 1`

### `getTxtHRefVoucherType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHRefVoucherType(final String vCat, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type AS code, vt.description AS name FROM voucher_Type vt WHERE 1 = 1 {…} AND vt.isActive = 1`

### `getTxtHPartyName`
`public DmResultPageing<List<TxtHPartyName_Mdl>> getTxtHPartyName(String voucherCategory, String v_Type, DmHelper helper, boolean blnToEndOfFile)`


### `getPartyDetails`
`public DmResult<TxtHPartyName_Mdl> getPartyDetails(String partyCode, String voucherCategory, String v_Type, DmHelper helper, boolean blnToEndOfFile)`


### `getParty`
`private List<TxtHPartyName_Mdl> getParty(String partyCode, String voucherCategory, String v_Type, DmHelper helper, boolean blnToEndOfFile, Connection cnTemp) throws Exception`

- SQL 1: `SELECT sg.subCode AS code, sg.name,sg.DueDays, sg.manualCode , c.cityCode,c.cityName, c.stateCode, sg.partyType, sg.gstin , sg.gstinDate, sm.name as stateName , sgn.manualCode AS partyNature , sg.isTcsActive, tc.code as tcsTdsCategoryCode, tc.name as tcsTdsCategoryName , sg.tan , sg.pan FROM subGroup sg LEFT JOIN tdsCat tc ON {…} LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature LEFT JOIN stateMast sm ON sm.code = c.stateCode WHERE sgn.code IN (SELECT acf.Nature FROM AcFilteration acf WHERE acf.v_Type = :v_Type) AND sg.isActive = 1 AND sgsl.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Type` ← `DmShared.iNull(v_Type).toString()`; `:partyCode` ← `DmShared.xNull(partyCode)`

### `getTxtHGstType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGstType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT it.code, it.name, it.printSrNo FROM gstType it WHERE it.isActive = 1`

### `getTxtHLocation`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHLocation(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHAdjBillNo`
`public DmResultPageing<List<TxtHVoucherNo_Mdl>> getTxtHAdjBillNo(String voucherCategory, String subCode, String refVoucherType, String v_Date, String differenceType, String billDocId, DmHelper helper, boolean blnToEndOfFile)`


### `getAdjBillNo`
`public DmResult<TxtHVoucherNo_Mdl> getAdjBillNo(String voucherCategory, String subCode, String refVoucherType, String v_Date, String differenceType, String billDocId, DmHelper helper, boolean blnToEndOfFile)`


### `getAdjBill`
`private List<TxtHVoucherNo_Mdl> getAdjBill(String voucherCategory, String subCode, String refVoucherType, String v_Date, String differenceType, String billDocId, DmHelper helper, boolean blnToEndOfFile, Connection cnTemp) throws Exception`

- SQL 1: `SELECT l.docId AS code, l.recid AS name, vt.short_Name as type , l.v_Date, l.v_type, l.v_SNo, l.v_No, l.refNo, l.refDate , IIF(ISNULL(l.amtDr,0) > 0, l.amtDr, l.amtCr) AS docValue FROM ledger l LEFT JOIN voucher_Type vt ON vt.v_Type = l.v_type LEFT JOIN ( SELECT gt.docId FROM gstTrans gt WHERE gt.partyCode = :subCode AND gt.site_Code = :site_Code AND gt.v_Type = :v_Type GROUP BY gt.docId -- HAVING SUM(ISNULL(gt.qtyDiff, 0)) > 0 OR SUM(ISNULL(gt.rateDiff, 0)) > 0 ) Vgt ON Vgt.docId = l.docId WHERE 1 = 1 AND ISNULL(Vgt.docId, '') <> '' AND l.subCode = :subCode AND l.site_Code = :site_Code AND l.v_Type = :v_Type AND l.isPosted = 1 AND (CASE WHEN :CrDrVCat IN ( {…} ) THEN l.amtCr WHEN :CrDrVCat IN ( {…} ) THEN l.amtDr ELSE '' END) > 0 {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:subCode` ← `DmShared.xNull(subCode)`; `:v_Type` ← `DmShared.xNull(refVoucherType)`; `:billDocId` ← `DmShared.xNull(billDocId)`; `:CrDrVCat` ← `DmShared.xNull(voucherCategory)`

### `getTxtHRefBillNo`
`public DmResultPageing<List<TxtHVoucherNo_Mdl>> getTxtHRefBillNo(String subCode, String refVoucherType, String v_Date, String differenceType, String billDocId, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT DISTINCT gt.docid as code , gt.recid as name , vt.description AS description , sg.NAME AS consignee , gt.v_Date , case when isnull(partybillno, '') = '' THEN recid ELSE partybillno END AS partybillno , ISNULL(partybilldt, v_date) AS partyBillDate FROM gstTrans gt LEFT JOIN subgroup sg ON sg.subcode = gt.consignee LEFT JOIN voucher_type vt ON vt.v_type = gt.v_type WHERE gt.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:subCode` ← `DmShared.xNull(subCode)`; `:v_Date` ← `DmServer.convertDate(v_Date)`; `:v_Type` ← `DmShared.xNull(refVoucherType)`

### `getTxtHDIfferenceType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDIfferenceType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT dt.code, dt.manualCode, dt.name FROM differenceType dt`

### `getTxtHHSN`
`public DmResultPageing<List<TxtHHSN_Mdl>> getTxtHHSN(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select hm.code, hm.name, hm.hsn from hsnMast hm where hm.nature = :goods`

### `getTxtHItemGSTNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemGSTNature(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `GSTNature.values`

### `getTxtHDueEntryNo`
`public DmResultPageing<List<TxtHVoucherNo_Mdl>> getTxtHDueEntryNo(String partyCode, String v_Date, String refTypeCode, String diffTypeCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT cdn.docId AS code, cdn.recId, cdn.v_Date, cdn.v_Type, vt.short_Name FROM creditDebitNoteDue cdn LEFT JOIN voucher_Type vt ON vt.v_Type = cdn.v_Type WHERE cdn.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Date` ← `DmServer.convertDate(v_Date)`; `:refV_Type` ← `DmShared.iNull(refTypeCode).toString()`; `:diffType` ← `DmShared.iNull(diffTypeCode).toString()`

### `getLocation`
`public DmResultPageing<List<KeyFieldExtended>> getLocation(String locationCode, DmHelper helper, boolean blnToEndOfFile)`


### `getLocation`
`public List<KeyFieldExtended> getLocation(String locationCode, DmHelper helper, boolean blnToEndOfFile, Connection cnTemp) throws Exception`

- SQL 1: `SELECT s.isUt FROM siteMast sm LEFT JOIN stateMast s on s.numericCode = sm.stateNumericCode WHERE sm.code = :siteCode`
- SQL 2: `SELECT lm.code, lm.name from locationMast lm WHERE lm.isActive = 1 AND lm.manualCode <> :manualCode {…}`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:manualCode` ← `DmEnum.DmGstLocation_SystemDefined.INTRA_STATE.getManualCode()`; `:manualCode` ← `DmEnum.DmGstLocation_SystemDefined.UNION_TERRITORY.getManualCode()`; `:locationCode` ← `locationCode`
- Other calls: `INTRA_STATE.getManualCode`, `UNION_TERRITORY.getManualCode`

### `getTxtHTransactionType`
`public DmResultPageing<List<TxtHTransactionType_Mdl>> getTxtHTransactionType(String v_Type, DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `STUtilityServer.getTransactionTypes`

### `getTxtHItem`
`public DmResultPageing<List<TxtHItemName_Mdl>> getTxtHItem(String v_Type, String supplierCode, String itemCatCodeCSV, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , im.manualCode , im.hsnCode , im.itemGSTNature , im.itsBasedOn , im.itemDesc , im.propertyList , im.purchaseUnit AS purchaseUnitCode , um.unit AS purchaseUnitName , ISNULL(im.purchaseUnitConversionRate, 0) AS purchaseUnitConversionRate , ISNULL(um.decimalPlaces,0) AS purchaseUnitDecimalPlaces , ums.code AS skuCode , ums.unit AS skuName , ISNULL(ums.decimalPlaces,0) AS skuDecimalPlaces , im.tl_ExcessPer , im.tl_ShortagePer , hsn.hsn, itm.manualCode as itemType, itm.name AS itemTypeName , im.packSizeCode , psm.name as packSizeName , psm.sku as packSizeSku , psm.packUnitCode , psm.packUnitConversionRate , umPsm.unit as packUnitName,im.itemGroup AS itemGroupCode , umPsmSku.unit as packSizeSkuName , umPsm.decimalPlaces as packUnitDecimalPlaces FROM itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN unitmast um ON im.purchaseUnit = um.code LEFT JOIN unitmast ums ON im.sku = ums.code LEFT JOIN hsnMast hsn ON hsn.code = im.hsnCode LEFT JOIN itemType itm on itm.code = im.itemType LEFT JOIN itemGroup ig ON ig.code = im.itemGroup LEFT JOIN itemNature imn ON imn.code = ig.itemNature LEFT JOIN packSizeMast psm on psm.code = im.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku LEFT JOIN ( SELECT ibs.itemCode, SUM(ibs.balanceQty) AS currentStock FROM itemBatchStock ibs WHERE ibs.site_Code = :site_Code GROUP BY ibs.itemCode ) AS vis ON im.code = vis.itemCode WHERE im.IsActive = 1 AND sl.site_Code = :site_Code AND im.isQuotationMandatory = 0 {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `fillGridDetails`
`public DmResult<List<GridItemDetail_Mdl>> fillGridDetails(CreditDebitNote_Mdl mrTmp)`

- SQL 1: `SELECT gt.docId AS invoiceDocId, gt.recId AS invoiceRecId , gt.v_Date AS invoiceV_Date , gt.v_SNo AS invoiceV_SNo , gt.v_Type AS invoiceV_Type, vt.description AS invoiceVoucherName , gt.itemCode, im.name AS itemName , um.code AS unitCode, um.unit AS unitName, um.decimalPlaces , im.sku AS skuCode, sku.unit AS skuName, sku.decimalPlaces AS skuDecimalPlaces , {…} AS calcOn , {…} AS rcmApplicable , im.itsBasedOn , gt.hsnCode , hsn.hsn as hsnNo , gt.qty AS billQty, gt.rate AS billRate , gt.qtyDiff AS cdnQty , gt.rateDiff AS cdnRate , gt.otherDiff, gt.OverAllDiff, gt.itemGrossAmount , enod.cessEvalTypeCode , enod.cessNaUnitCode , umc.unit as cessNaUnitName , enod.isManualGstType, enod.itemGSTNature , eno.isItc , eno.locationCode, im.itsBasedOn, enod.gstTypeCode, enod.isManualGstType , im.purchaseAc , im.salesAc , im.purchaseContraAc , enod.oh_Amt_Net , ISNULL(enod.oh_Ac_Item, im.subCode) AS oh_Ac_Item , enod.oh_Ac_Balancing FROM gstTrans gt LEFT JOIN voucher_Type vt ON vt.v_Type = gt.v_Type {…} LEFT JOIN subGroup sg ON sg.subCode = gt.partyCode LEFT JOIN unitMast umc ON umc.code = enod.cessNaUnitCode LEFT JOIN itemMast im ON im.code = gt.itemCode LEFT JOIN unitMast um ON um.code = enod.unitCode LEFT JOIN unitMast sku ON sku.code = im.sku LEFT JOIN hsnMast hsn on hsn.code = gt.hsnCode WHERE gt.docId = :docId AND gt.v_Type = :v_Type AND gt.partyCode = :partyCode`
- SQL 2: `SELECT cdn.docId AS drCrDue , cdn.recId AS drCrDueNo , cdn.v_Date AS drCrDueEntryDate, cdnd.v_SNo AS drCrDueV_SNo , cdnd.invoiceDocId, pb.recId AS invoiceRecId, cdnd.invoiceV_SNo , cdnd.itemCode, im.name AS itemName , im.hsnCode , hsn.hsn as hsnNo , cdnd.qty, cdnd.rate, cdnd.amount , um.code AS unitCode, um.unit AS unitName, um.decimalPlaces , um.sku AS skuCode, sku.unit AS skuName, sku.decimalPlaces AS skuDecimalPlaces , {…} AS calcOn , {…} AS rcmApplicable , enod.isManualGstType, enod.itemGSTNature , eno.isItc , enod.gstTypeCode , enod.cessEvalTypeCode , enod.cessNaUnitCode , im.purchaseAc , im.salesAc , im.purchaseContraAc , enod.oh_Amt_Net , ISNULL(enod.oh_Ac_Item, im.subCode) AS oh_Ac_Item , enod.oh_Ac_Balancing , im.itsBasedOn FROM creditDebitNoteDue cdn LEFT JOIN creditDebitNoteDueDetail cdnd ON cdnd.docId = cdn.docId LEFT JOIN pBill pb ON pb.docId = cdnd.invoiceDocId LEFT JOIN subGroup sg ON sg.subCode = cdnd.partyCode LEFT JOIN itemMast im ON im.code = cdnd.itemCode LEFT JOIN hsnMast hsn on hsn.code = im.hsnCode {…} LEFT JOIN unitMast um ON um.code = enod.unitCode LEFT JOIN unitMast sku ON sku.code = im.sku WHERE cdn.docId = :docId AND cdnd.partyCode = :partyCode`
- Params: `:docId` ← `DmShared.xNull(mrTmp.getRefBillDocId())`; `:v_Type` ← `DmShared.xNull(mrTmp.getRefVoucherTypeCode())`; `:partyCode` ← `DmShared.xNull(mrTmp.getPartyCode())`; `:docId` ← `DmShared.xNull(mrTmp.getDueEntryCode())`; `:partyCode` ← `DmShared.xNull(mrTmp.getPartyCode())`
- Other calls: `OverHeadLine_Server.copyOverHead`, `OverHeadLine_Server.getOverHeadConfig`, `OverHeadLine_Server.getOverHeadTrans`, `OverHead_Server.getOverHeadTrans`, `STServer.getITS`

### `setApproval`
`public DmResult<String> setApproval(String approvedBy, boolean isApprove, String docId)`

- Other calls: `STApprovals.creditDebitNoteApproval`

### `getWalkThroughFor`
`private WalkThroughDefault_Mdl getWalkThroughFor(CreditDebitNote_Mdl mrTemp)`


### `walkThrough`
`public DmResult<String> walkThrough(WalkThrough_Mdl walkThrough)`

- Other calls: `STApprovals.creditDebitNoteApproval`, `WalkThrough_Server.walkThrough`

### `getServerDateTime`
`public DmResult<String[]> getServerDateTime()`


### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues(String menuText)`

- SQL 1: `SELECT TOP 1 vt.v_Type AS code , vt.description AS name , vt.category , vt.guiTemplateCode , vt.balancingAc FROM voucher_Type vt WHERE vt.category IN ( {…} ) AND isActive = 1`
- SQL 2: `SELECT git.code , git.name , git.manualCode FROM gstItemType git WHERE git.code = 2`
- SQL 3: `SELECT si.gstin AS siteGstin , si.gstinDate AS siteGstinDate , si.registrationType AS siteRegistrationType , st.code AS siteStateCode , st.isUT AS isSiteStateUT FROM siteMast si LEFT JOIN stateMast st ON si.stateNumericCode = st.numericCode WHERE si.code = :siteCode`
- SQL 4: `SELECT e.isItc, e.purchaseSalesAcPostingBasedOn FROM enviro e WHERE e.site_Code = :site_Code`
- SQL 5: `SELECT ec.tcsLimit, ec.isTcsAutoCalculate, ec.autoTcsBasedOn, ec.ac_tcsActiveFrom, ec.isInventoryAccountingActive FROM enviroCompany ec`
- SQL 6: `SELECT im.uniqueId, im.subCode FROM itemMast im WHERE im.uniqueId IN ('RO', 'FD')`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Errors: "Input Tax Credit is not defined in Environment Setting." | "Purchase/Sales A/c Posting Based On is not defined in Environment Setting." | "Inventory Accounting Active is not defined in Environment Setting (Company)." | "'TCS Auto Calculate' is not defined in Environment Setting (Company)." | "'Auto TCS Based On' is not defined in Environment Setting (Company)." | "'TCS Limit' is not defined in Environment Setting (Company)."
- Other calls: `GST_ITEM.getId`, `GST_ITEM.getValue`, `OverHead_Server.getOHHeadAccounts`, `SALES.getCode`, `STServer.getSystemDefinedGstLocationMap`, `STUtilityServer.getTransactionType`

### `saveRecord`
`public DmResult<String> saveRecord(CreditDebitNote_Mdl mrTemp, String voucherPrefix, boolean isApprove)`

- SQL 1: `SELECT postedBy,cancelledBy FROM ledgerM where docId = :docId`
- SQL 2: `SELECT u_EntDt_LatestLine FROM creditDebitNote WHERE docId = :docId`
- SQL 3: `SELECT Count(*) AS Cnt From DataAudit WHERE DocId=:DocId`
- SQL 4: `SELECT COUNT(l.docid) AS cnt FROM ledger l INNER JOIN voucher_type vt ON vt.v_Type = l.v_Type WHERE l.refNo = :refNo AND l.subCode=:subCode AND l.docId != :docId AND vt.category IN(' {…} ', ' {…} ')`
- SQL 5: `Delete From Ledger WHERE DocId =:DocId`
- SQL 6: `Delete From creditDebitNoteDetail WHERE DocId =:DocId`
- SQL 7: `INSERT INTO creditDebitNote ( docId, v_Type, v_No, recId, v_Prefix, recIdPrefix, v_Date, subCode, partyCode, partyBillNo, partyBillDate, differenceType, refV_Type, locationCode, adjBillDocId, adjBillV_Sno, refBillDocId, hsnFilter, dueEntryNo, dueEntrydate, remark, rcmApplicable, calcOn, isItc, site_Code, u_EntDt, preparedBy, preparedDt, wtaCode, transferDt, guiConfigCode, preparedByIP, balancingAc, tdsDeductFrom, tdsCategory, tan, pan, tdsTcsType, tdsFor, oh_Amt_TdsOn, oh_Amt_Tds, isTDSApply, transType ) VALUES ( :docId, :v_Type, :v_No, :recId, :v_Prefix, :recIdPrefix, :v_Date, :subCode, :partyCode, :partyBillNo, :partyBillDate, :differenceType, :refV_Type, :locationCode, :adjBillDocId, :adjBillV_Sno, :refBillDocId, :hsnFilter, :dueEntryNo, :dueEntrydate, :remark, :rcmApplicable, :calcOn, :isItc, :site_Code, :u_EntDt, :preparedBy, :preparedDt, :wtaCode, :transferDt, :guiConfigCode, :preparedByIP, :balancingAc, :tdsDeductFrom, :tdsCategory, :tan, :pan, :tdsTcsType, :tdsFor, :oh_Amt_TdsOn, :oh_Amt_Tds, :isTDSApply, :transType )`
- SQL 8: `UPDATE creditDebitNote SET v_Date = :v_Date , refV_Type = :refV_Type , subCode = :subCode , partyCode = :partyCode , partyBillNo = :partyBillNo , partyBillDate = :partyBillDate , locationCode = :locationCode , differenceType = :differenceType , adjBillDocId = :adjBillDocId , adjBillV_Sno = :adjBillV_Sno , refBillDocId = :refBillDocId , hsnFilter = :hsnFilter , dueEntryNo = :dueEntryNo , dueEntrydate = :dueEntrydate , calcOn = :calcOn , rcmApplicable = :rcmApplicable , isItc = :isItc , remark = :remark , u_EntDt = :uEntDt , u_EntDt_LatestLine = :uEntDt , u_Name = :u_Name , transferDt = :transferDt , balancingAc = :balancingAc , oh_Amt_Tds = :oh_Amt_Tds , oh_Amt_TdsOn = :oh_Amt_TdsOn , tdsFor = :tdsFor , tdsTcsType = :tdsTcsType , tdsCategory = :tdsCategory , tdsDeductFrom = :tdsDeductFrom , isTdsApply = :isTdsApply , tan = :tan , pan = :pan , transType = :transType WHERE DocId = :DocId;`
- SQL 9: `INSERT INTO creditDebitNoteDetail ( DocId, V_SNo, adjBillDocId, adjBillV_SNo, refBillDocId, refBillV_SNo, dueEntryDocId, dueEntryV_SNo, itemCode, hsnCode, qty, rate, amount, narration, itemGSTNature, isManualGstType, gstTypeCode, unitCode ) VALUES ( :DocId, :V_SNo, :adjBillDocId, :adjBillV_SNo, :refBillDocId, :refBillV_SNo, :dueEntryDocId, :dueEntryV_SNo, :itemCode, :hsnCode, :qty, :rate, :amount, :narration, :itemGSTNature, :isManualGstType, :gstTypeCode, :unitCode )`
- SQL 10: `Insert Into LedgerM ( DocId , V_Type , V_Prefix , Site_Code , V_No , V_Date , SubCode , Narration , RecId , recIdPrefix , preparedDt , PreparedBy , preparedByIP , wtaCode , remark , consignee , partyType , gsTin , gstinDate , placeOfSupply , refDocId , refV_SNo , u_EntDt_LatestLine , isTdsApply , tdsTcsType , tdsFor , tdsCategory , tdsDeductFrom , tdsOnAmt , tdsAmt, pan, tan ) Values ( :DocId , :V_Type , :V_Prefix , :Site_Code , :V_No , :V_Date , :SubCode , :Narration , :RecId , :recIdPrefix , :uEntDt , :preparedBy , :preparedByIP , :wtaCode , :remark , :consignee , :partyType , :gsTin , :gstinDate , :placeOfSupply , :refDocId , :refV_SNo , :uEntDt , :isTdsApply , :tdsTcsType , :tdsFor , :tdsCategory , :tdsDeductFrom , :tdsOnAmt , :tdsAmt, :pan, :tan )`
- SQL 11: `update LedgerM Set V_Date=:V_Date,SubCode=:SubCode,consignee=:consignee,partyType=:partyType,gsTin=:gsTin,gstinDate=:gstinDate,placeOfSupply=:placeOfSupply,refDocId = :refDocId, refV_SNo = :refV_SNo, Narration=:Narration,transferDt=:transferDt,U_Name=:uName,u_EntDt_LatestLine = :uEntDt, remark=:remark, U_EntDt=:uEntDt , isTdsApply = :isTdsApply , tdsTcsType = :tdsTcsType , tdsFor = :tdsFor , tdsCategory = :tdsCategory , tdsDeductFrom = :tdsDeductFrom , tdsOnAmt = :tdsOnAmt , tdsAmt = :tdsAmt , pan = :pan , tan = :tan WHERE DocId=:DocId`
- SQL 12: `SELECT TOP 1 gst.gstDocType as refDocType , gst.gstDocNature as refDocNature FROM gstTrans as gst WHERE gst.docId = :docId`
- Params: `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:DocId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:refNo` ← `DmShared.xNull(mrTemp.getRefVoucherTypeCode())`; `:subCode` ← `DmShared.xNull(mrTemp.getPartyCode())`; `:DocId` ← `mrTemp.getSharedKeyFieldCode()`; `:DocId` ← `mrTemp.getSharedKeyFieldCode()`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:recId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:recIdPrefix` ← `DmShared.xNull(mrTemp.getRecIdObj().getPrefix())`; `:v_Prefix` ← `voucherPrefix`; `:v_Type` ← `DmShared.foriegnKey(mrTemp.getVoucherTypeCode())`; `:v_No` ← `mrTemp.getRecIdObj().getCounter().toString()`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getPartyCode())`; `:partyCode` ← `DmShared.foriegnKey(mrTemp.getPartyCode())`; `:partyBillNo` ← `DmShared.xNull(mrTemp.getPartyBillNo())`; `:partyBillDate` ← `DmServer.convertDate(mrTemp.getPartyBillDate())`; `:differenceType` ← `DmShared.xNull(mrTemp.getDiffTypeCode())`; `:locationCode` ← `DmShared.foriegnKey(mrTemp.getLocationCode())`; `:refV_Type` ← `DmShared.foriegnKey(mrTemp.getRefVoucherTypeCode())`; `:adjBillDocId` ← `DmShared.foriegnKey(mrTemp.getAdjBillDocId())`; `:adjBillV_Sno` ← `DmShared.xNull(mrTemp.getAdjBillV_SNo())`; `:refBillDocId` ← `DmShared.foriegnKey(mrTemp.getRefBillDocId())`; `:hsnFilter` ← `DmShared.foriegnKey(mrTemp.getHsnCode())`; `:dueEntryNo` ← `DmShared.foriegnKey(mrTemp.getDueEntryCode())`; `:dueEntrydate` ← `DmServer.convertDate(DmShared.dNull(mrTemp.getDueEntryDate()))`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:rcmApplicable` ← `DmShared.iNull(mrTemp.getRcmApplicable()).toString()`; `:calcOn` ← `DmShared.iNull(mrTemp.getCalculateOnCode()).toString()`; `:isItc` ← `DmShared.xNull(mrTemp.getItcCode())`; `:site_Code` ← `DmShared.foriegnKey(siteCode)`; `:u_EntDt` ← `timestamp`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `timestamp`; `:wtaCode` ← `DmShared.foriegnKey(mrTemp.getWtaCode())`; `:transferDt` ← `null`; `:guiConfigCode` ← `DmShared.foriegnKey(mrTemp.getGuiConfigCode())`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:balancingAc` ← `DmShared.foriegnKey(mrTemp.getBalancingAc())`; `:locationCode` ← `DmShared.foriegnKey(mrTemp.getLocationCode())`; `:oh_Amt_TdsOn` ← `DmServer.vNull(mrTemp.getTdsTcsPopupMdl().getTdsOnAmount(), "0.00")`; `:oh_Amt_Tds` ← `DmServer.vNull(mrTemp.getTdsTcsPopupMdl().getTotalAmount(), "0.00")`; `:tdsFor` ← `DmShared.foriegnKey(mrTemp.getTdsTcsPopupMdl().getTdsForCode())`; `:tdsTcsType` ← `DmShared.foriegnKey(mrTemp.getTdsTcsPopupMdl().getTdsTcsTypeCode())`; `:tdsCategory` ← `DmShared.foriegnKey(mrTemp.getTdsTcsPopupMdl().getSharedKeyFieldCode())`; `:tdsDeductFrom` ← `DmShared.foriegnKey(mrTemp.getTdsTcsPopupMdl().getTdsDeductFromCode())`; `:isTdsApply` ← `mrTemp.isTdsTcsApplicable() ? 1 : 0`; `:tan` ← `DmShared.xNull(mrTemp.getTdsTcsPopupMdl().getTan())`; `:pan` ← `DmShared.xNull(mrTemp.getTdsTcsPopupMdl().getPan())`; `:transType` ← `DmShared.foriegnKey(mrTemp.getTransTypeCode())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:partyBillNo` ← `DmShared.xNull(mrTemp.getPartyBillNo())`; `:partyBillDate` ← `DmServer.convertDate(mrTemp.getPartyBillDate())`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getPartyCode())`; `:partyCode` ← `DmShared.foriegnKey(mrTemp.getPartyCode())`; `:differenceType` ← `DmShared.xNull(mrTemp.getDiffTypeCode())`; `:refV_Type` ← `DmShared.foriegnKey(mrTemp.getRefVoucherTypeCode())`; `:locationCode` ← `DmShared.foriegnKey(mrTemp.getLocationCode())`; `:adjBillDocId` ← `DmShared.foriegnKey(mrTemp.getAdjBillDocId())`; `:adjBillV_Sno` ← `DmShared.xNull(mrTemp.getAdjBillV_SNo())`; `:refBillDocId` ← `DmShared.foriegnKey(mrTemp.getRefBillDocId())`; `:hsnFilter` ← `DmShared.foriegnKey(mrTemp.getHsnCode())`; `:dueEntryNo` ← `DmShared.foriegnKey(mrTemp.getDueEntryCode())`; `:dueEntrydate` ← `DmServer.convertDate(DmShared.dNull(mrTemp.getDueEntryDate()))`; `:calcOn` ← `DmShared.iNull(mrTemp.getCalculateOnCode()).toString()`; `:rcmApplicable` ← `DmShared.iNull(mrTemp.getRcmApplicable()).toString()`; `:isItc` ← `DmShared.xNull(mrTemp.getItcCode())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:uEntDt` ← `timestamp`; `:transferDt` ← `null`; `:balancingAc` ← `DmShared.foriegnKey(mrTemp.getBalancingAc())`; `:oh_Amt_TdsOn` ← `DmServer.vNull(mrTemp.getTdsTcsPopupMdl().getTdsOnAmount(), "0.00")`; `:oh_Amt_Tds` ← `DmServer.vNull(mrTemp.getTdsTcsPopupMdl().getTotalAmount(), "0.00")`; `:tdsFor` ← `DmShared.foriegnKey(mrTemp.getTdsTcsPopupMdl().getTdsForCode())`; `:tdsTcsType` ← `DmShared.foriegnKey(mrTemp.getTdsTcsPopupMdl().getTdsTcsTypeCode())`; `:tdsCategory` ← `DmShared.foriegnKey(mrTemp.getTdsTcsPopupMdl().getSharedKeyFieldCode())`; `:tdsDeductFrom` ← `DmShared.foriegnKey(mrTemp.getTdsTcsPopupMdl().getTdsDeductFromCode())`; `:isTdsApply` ← `mrTemp.isTdsTcsApplicable() ? 1 : 0`; `:tan` ← `DmShared.xNull(mrTemp.getTdsTcsPopupMdl().getTan())`; `:pan` ← `DmShared.xNull(mrTemp.getTdsTcsPopupMdl().getPan())`; `:transType` ← `DmShared.foriegnKey(mrTemp.getTransTypeCode())`; `:DocId` ← `mrTemp.getSharedKeyFieldCode()`; `:V_SNo` ← `DmServer.vNull(vSNo, "0")`; `:adjBillDocId` ← `DmShared.foriegnKey(detail_Mdl.getAdjDocId())`; `:adjBillV_SNo` ← `DmShared.foriegnKey(detail_Mdl.getAdjV_SNo())`; `:refBillDocId` ← `DmShared.foriegnKey(detail_Mdl.getRefDocId())`
- Errors: "Record is posted. You can't Edit this record." | "Record is discarded. You can't Edit this record." | "Record has been changed, can't update record, refresh record then update" | "Record Has Been Audited. You Can Not Edit/ Delete This Record." | "Invalid key." | "Entry already exists with this Reference Number."
- Other calls: `AccountAdjustment_Server.deleteRecord`, `CreditDebitNote_Vld.chkSave`, `Gst_Server.deleteGstTrans`, `Gst_Server.saveGstTrans`, `INPUT.toInt`, `ItemAdjustment_Server.deleteRecord`, `OverHeadLine_Server.save`, `OverHead_Server.save`, `PartyDetailPopup_Server.deletePartyTransRecord`, `PartyDetailPopup_Server.getSiteDetailPopup_Mdl`, `PartyDetailPopup_Server.savePartyTransRecord`, `STApprovals.creditDebitNoteApproval`, `TRANSACTION_SITE.getCode`, `WalkThrough_Server.applyWalkThrough`

### `getMoveRec`
`public DmResult<CreditDebitNote_Mdl> getMoveRec(String strKey)`


### `getMoveRec`
`public CreditDebitNote_Mdl getMoveRec(String strKey, boolean flagEGST, Connection cnTemp) throws Exception`

- SQL 1: `SELECT TOP 1 cdn.docId , cdn.recId , cdn.v_No , cdn.recIdPrefix , cdn.v_Type , cdn.v_Date , vt.description AS voucherTypeName , vt.category , cdn.subCode , sgs.name AS partyName , c.stateCode AS supplierStateCode , sm.name AS supplierStateName , cdn.locationCode , lm.name AS locationName , cdn.partyBillNo , cdn.partyBillDate , cdn.refV_Type , vt.category AS referenceCatagory , vtr.description AS referenceName , cdn.refDate , cdn.differenceType , dt.manualCode AS differenceTypeManualCode , cdn.adjBillDocId , cdn.adjBillV_Sno , lg.recId AS adjBillRecId , cdn.refBillDocId , gt.recId AS refBillRecId , cdn.hsnFilter , hm.name AS hsn , cdn.dueEntryNo , cnd.recId AS dueEntryRecId , cdn.dueEntrydate , cdn.remark , cdn.balancingAc , cdn.isItc , cdn.calcOn , cdn.guiConfigCode , cdn.u_Name , cdn.approvedBy , cdn.wtaCode , cdn.u_EntDt_LatestLine , cdn.preparedBy , cdn.preparedDt , cdn.preparedByIP , cdn.approvalDateTime , cdn.u_EntDt , cdn.oh_Amt_TdsOn , cdn.oh_Amt_Tds , cdn.tdsFor , cdn.tdsTcsType , cdn.tdsCategory AS tdsCategoryCode , tc.name AS tdsCategoryName , cdn.tdsDeductFrom , sgt.name AS tdsDeductFromName , cdn.isTdsApply , pti.tan , pti.pan , cdn.transType, tt.name AS transTypeName, tt.itemCatCode FROM creditDebitNote cdn LEFT JOIN voucher_Type vt ON cdn.v_Type = vt.v_Type LEFT JOIN voucher_Type vtr ON cdn.refV_Type = vtr.v_Type LEFT JOIN subGroup sgs ON cdn.subCode = sgs.subCode LEFT JOIN city c ON c.cityCode = sgs.cityCode LEFT JOIN stateMast sm ON sm.code = c.stateCode LEFT JOIN locationMast lm on lm.code = cdn.locationCode LEFT JOIN ledger lg ON lg.docId = cdn.adjBillDocId AND lg.v_SNo = cdn.adjBillV_SNo LEFT JOIN gstTrans gt ON gt.docId = cdn.refBillDocId LEFT JOIN partyTransInfo pti ON pti.docId = IIF(ISNULL(cdn.adjBillDocId, '') <> '', cdn.adjBillDocId, refBillDocId) AND pti.recordType = ' {…} ' LEFT JOIN hsnMast hm ON hm.code = cdn.hsnFilter LEFT JOIN creditDebitNoteDue cnd ON cnd.docId = cdn.dueEntryNo LEFT JOIN tdsCat tc ON tc.code = cdn.tdsCategory LEFT JOIN subGroup sgt ON sgt.subCode = cdn.tdsDeductFrom LEFT JOIN transType tt ON tt.code = cdn.transType LEFT JOIN differenceType dt ON dt.code = cdn.differenceType WHERE cdn.docId = :docId`
- SQL 2: `SELECT cnd.docId, cnd.v_SNo , cnd.adjBillDocId, adjGT.recId AS adjBillRecId, cnd.adjBillV_SNo , cnd.refBillDocId, refGT.recId AS refBillRecId, cnd.refBillV_SNo , cnd.dueEntryDocId, cdn.recId AS dueEntryRecId, cnd.dueEntryV_SNo , cdn.v_Date AS dueEntryDate , cnd.itemCode, im.name AS itemName, cnd.hsnCode,hsn.hsn , cnd.qty, cnd.rate, cnd.amount, cnd.narration , it.name AS gstTypeName, cnd.gstTypeCode , cnd.itemGSTNature , cnd.unitCode, um.unit AS unitName, um.decimalPlaces AS unitDecimalPlaces, um.gstUnitCode , im.sku as skuUnitCode, umSku.unit as skuUnitName, ISNULL(umSku.decimalPlaces,0) AS skuUnitDecimalPlaces FROM creditDebitNoteDetail cnd LEFT JOIN creditDebitNoteDueDetail cndd ON cndd.docId = cnd.dueEntryDocId AND cndd.v_SNo = cnd.dueEntryV_SNo LEFT JOIN creditDebitNoteDue cdn ON cdn.docId = cndd.docId LEFT JOIN gstTrans adjGT ON adjGT.docId = cnd.adjBillDocId AND adjGT.v_SNo=cnd.adjBillV_SNo LEFT JOIN gstTrans refGT ON refGT.docId = cnd.refBillDocId AND refGT.v_SNo=cnd.refBillV_SNo LEFT JOIN itemMast im ON im.code = cnd.itemCode LEFT JOIN unitMast umc ON umc.code = cnd.cessNaUnitCode LEFT JOIN gstType it ON it.code = cnd.gstTypeCode LEFT JOIN hsnMast hsn on hsn.code = cnd.hsnCode LEFT JOIN unitMast um ON um.code = cnd.unitCode LEFT JOIN unitMast umSku ON umSku.code = im.sku WHERE cnd.docId = :docId ORDER BY cnd.v_SNo`
- Params: `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`
- Other calls: `DifferenceType.getViaCode`, `Gst_Server.getGstDocumentDetail`, `OverHeadLine_Server.getOverHeadTrans`, `OverHead_Server.getOverHeadTrans`, `PARTY.getCode`, `PartyDetailPopup_Server.getPartyTransRecord`

### `deleteRecord`
`public DmResult<String> deleteRecord(String keyCode, String guiConfigCode, ModifyReason_Mdl modifyReasonMdl)`

- SQL 1: `SELECT v_date from creditDebitNote where docid = :docid`
- SQL 2: `SELECT approvedBy,cancelledBy, wtaCode FROM creditDebitNote WHERE docId = :docId`
- SQL 3: `Delete From LedgerM Where docId= :docId`
- SQL 4: `Delete From creditDebitNoteDetail Where docId= :docId`
- SQL 5: `Delete From creditDebitNote Where docId= :docId`
- Params: `:docid` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`
- Errors: "Record is approved. You can't Discarded this record." | "Record is Already Discarded."
- Other calls: `AccountAdjustment_Server.deleteRecord`, `AccountPosting_Server.deleteAccountPosting`, `Gst_Server.deleteGstTrans`, `ItemAdjustment_Server.deleteRecord`, `OverHeadLine_Server.delete`, `OverHead_Server.delete`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(UserPermission userPermission)`

- SQL 1: `SELECT pb.docId FROM creditDebitNote pb LEFT JOIN voucher_Type vt ON pb.v_Type = vt.v_Type WHERE vt.category IN ( {…} ) AND pb.site_Code = :site_Code AND pb.v_Prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT cdn.docId, cdn.recId, cdn.v_No, cdn.v_Date, vt.short_Name , vtr.short_Name AS referencType, sg.name AS partyName , pb.recId AS refBillDocId, dt.name AS differenceType , hm.hsn, cnd.recId AS dueEntryNo, cnd.v_Date As dueEntryDate FROM creditDebitNote cdn LEFT JOIN voucher_Type vt ON vt.v_Type = cdn.v_Type LEFT JOIN voucher_Type vtr ON vtr.v_Type = cdn.refV_Type LEFT JOIN subGroup sg ON sg.subCode = cdn.subCode LEFT JOIN pBill pb ON pb.docId = cdn.refBillDocId LEFT JOIN differenceType dt ON dt.code = cdn.differenceType LEFT JOIN hsnMast hm ON hm.code = cdn.hsnFilter LEFT JOIN creditDebitNoteDue cnd ON cnd.docId = cdn.dueEntryNo WHERE vt.category IN ( {…} ) AND cdn.site_Code = :site_Code AND cdn.v_Prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getGSTS`
`public DmResult<List<GSTS_Mdl>> getGSTS(String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst)`


### `getITS`
`public DmResult<List<ITS_Mdl>> getITS(String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst)`

- Other calls: `STServer.getITS`

### `getGSTS`
`private DmResult<List<GSTS_Mdl>> getGSTS(String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst, Connection cnTemp) throws Exception`

- Other calls: `STServer.getGSTS`

### `manageBilledQty`
`private void manageBilledQty(String docId, boolean isDelete, Connection cnTemp) throws Exception`

- SQL 1: `SELECT pb.docId AS pbDocId , cnd.refBillV_SNo , pbd.isStatus , pb.approvedBy , pb.recId AS pcNo , vt.short_name AS pcType FROM pBillDetail pbd LEFT JOIN pBill pb ON pbd.docId = pb.docId LEFT JOIN voucher_type vt ON pb.v_Type = vt.v_type INNER JOIN ( SELECT cnd.refBillDocId, cnd.refBillV_SNo, MAX(cnd.docId) AS docId FROM creditDebitNoteDetail cnd WHERE cnd.docId = :docId GROUP BY cnd.refBillDocId, cnd.refBillV_SNo ) cnd ON pbd.docId = cnd.refBillDocId AND pbd.v_SNo = cnd.refBillV_SNo`
- SQL 2: `UPDATE pBillDetail SET isStatus = :isStatus WHERE docId = :pbDocId AND v_SNo = :refBillV_SNo`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:pbDocId` ← `DmShared.xNull(rsTemp.getString("pbDocId"))`; `:refBillV_SNo` ← `DmShared.xNull(rsTemp.getString("refBillV_SNo"))`; `:isStatus` ← `DmShared.iNull(isStatus).toString()`; `:uEntDt` ← `DmServer.getServerTimestamp()`

### `printRecords`
`public DmResult<String> printRecords(String docId, String v_Type, String reportTitle)`


### `setAccountAdjustments`
`private void setAccountAdjustments(String vrDocId, String vrPartyCode, String adjDocId, String adjPartyCode, String netAmount, Connection cnTemp)`

- SQL 1: `SELECT TOP 1 lv.docId, lv.v_SNo, lv.v_Date, lv.subCode, lv.amtDr, lv.amtCr FROM ledger lv WHERE lv.docId = :docId AND lv.subCode = :subCode {…} ORDER BY lv.v_SNo`
- SQL 2: `SELECT TOP 1 lb.docId, lb.v_SNo, lb.v_Date, lb.subCode FROM ledger lb WHERE lb.docId = :docId AND lb.subCode = :subCode AND ISNULL(lb.amtCr, 0) > 0 ORDER BY lb.v_SNo`
- Params: `:docId` ← `DmShared.xNull(vrDocId)`; `:subCode` ← `DmShared.xNull(vrPartyCode)`; `:docId` ← `DmShared.xNull(adjDocId)`; `:subCode` ← `DmShared.xNull(adjPartyCode)`
- Other calls: `AccountAdjustment_Server.saveRecord`

### `setItemAdjustments`
`private void setItemAdjustments(String docId, String companyCode, Connection cnTemp)`

- SQL 1: `SELECT cdnd.adjBillDocId, cdnd.adjBillV_SNo , cdn.subCode AS partyCode, cdn.v_Date , cdn.v_Type , cdn.recId , cdn.recIdPrefix , cdn.v_No , cdn.v_Prefix , cdn.site_Code , cdnd.v_SNo, cdnd.oh_Ac_Item, (IsNull(pbd.oh_Amt_Taxable, 0) - IsNull(cdnd.oh_Amt_Taxable, 0)) AS adjValue , cdnd.itemCode, pbd.ibCode, pbd.departmentCode FROM creditDebitNote cdn LEFT JOIN creditDebitNoteDetail cdnd ON cdnd.docId = cdn.docId LEFT JOIN pBillDetail pbd ON pbd.docId = cdnd.adjBillDocId AND pbd.v_SNo = cdnd.adjBillV_SNo WHERE cdn.docId = :docId AND pbd.isFooter = 0 AND (ISNULL(pbd.oh_Amt_Taxable, 0) - IsNull(cdnd.oh_Amt_Taxable, 0)) <> 0`
- Params: `:docId` ← `docId`
- Other calls: `ItemAdjustment_Server.saveRecord`

### `getCsvFromTable`
`public String getCsvFromTable(String resultFieldName, String tableName, String docidFiledName, String docidCsv, Connection cnTemp) throws Exception`


### `getAdditionalPartyDetail`
`public DmResult<PartyDetailPopup_Mdl> getAdditionalPartyDetail(String subCode, String docid, int recordTypeCode)`

- Other calls: `PartyDetailPopup_Server.getPartyDetailPopup_Mdl`, `PartyDetailPopup_Server.getPartyTransRecord`

### `getTDSData`
`public DmResult<List<GrdTdsDetail_Mdl>> getTDSData(String tdsCategoryCode, String date)`

- Other calls: `TdsPopup_Server.getTDSData`

### `sendMessage`
`public DmResult<String> sendMessage(String docid, String vTypeCode, String reportTitle)`


### `processEGst`
`public DmResult<MasterEGst_Mdl> processEGst(MasterEGst_Mdl masterEGst_Mdl, int actionCode)`

- SQL 1: `SELECT u_EntDt_LatestLine,isStatus , transporterCode,transportMode,vehicleTypeCode,vehicleNo,lrNo,lrDate FROM transporterTrans where docid = :docid`
- SQL 2: `SELECT u_EntDt_LatestLine, approvedBy, cancelledBy FROM creditDebitNote WHERE docId = :docId`
- Params: `:docId` ← `DmShared.xNull(masterEGst_Mdl.geteGst_Mdl().getDocid())`; `:docId` ← `DmShared.xNull(masterEGst_Mdl.geteGst_Mdl().getDocid())`
- Errors: "Record has been changed, can't process this action, refresh record and retake action." | "Transporter bill is genrated you can not change transport detail." | "Record is discarded, can't process this action." | "Record is not approved, can't process this action."
- Other calls: `EGstPopup_Server.processEGst`, `E_INV_CANCEL_BILL.getCode`

### `finalizeEGstProcess`
`private void finalizeEGstProcess(EGst_Mdl eGst_Mdl, int actionCode, Connection cnTemp) throws Exception`

- SQL 1: `update eGst set mongoLinkId=:mongoLinkId, u_EntDt_LatestLine = :uEntDt where docId=:docId`
- SQL 2: `update creditDebitNote set eInvIrnNo = :eInvIrnNo, eInvAckDt = :eInvAckDt, eInvAckNo = :eInvAckNo, eInvMongoLinkId = :eInvMongoLinkId, u_EntDt_LatestLine = :uEntDt where docId = :docId`
- Params: `:docId` ← `eGst_Mdl.getDocid()`; `:mongoLinkId` ← `DmShared.foriegnKey(eGst_Mdl.getMongoLinkId())`; `:uEntDt` ← `timestamp`; `:docId` ← `eGst_Mdl.getDocid()`; `:eInvIrnNo` ← `DmShared.foriegnKey(eGst_Mdl.getEinv_IrnNo())`; `:eInvAckDt` ← `Gst_Server.convertTimestamp(eGst_Mdl.getEinv_ackDt())`; `:eInvAckNo` ← `DmShared.foriegnKey(eGst_Mdl.getEinv_ackNo())`; `:eInvMongoLinkId` ← `DmShared.foriegnKey(eGst_Mdl.getMongoLinkId())`; `:uEntDt` ← `timestamp`
- Other calls: `E_INV_CANCEL_BILL.getCode`, `Gst_Server.convertTimestamp`, `PARTY.getCode`, `PartyDetailPopup_Server.updatePartyTransRecord`, `STApprovals.creditDebitNoteApproval`, `TRANSACTION_SITE.getCode`

### `finalizeGstDocumentProcess`
`private void finalizeGstDocumentProcess(GstDocument_Mdl gst_Mdl, int actionCode, Connection cnTemp) throws Exception`


### `getEgstDetail`
`public DmResult<MasterEGst_Mdl> getEgstDetail(String strKey, String vTypeCode)`

- Other calls: `Gst_Server.getEGstRecord`, `Gst_Server.getToken`

### `getEgstDetail`
`private EGst_Mdl getEgstDetail(CreditDebitNote_Mdl mrTemp, Connection cnTemp) throws Exception`

- SQL 1: `select vt.einvSupplyTypeCode from voucher_Type vt where vt.v_Type = :v_Type`
- Params: `:v_Type` ← `DmShared.xNull(mrTemp.getVoucherTypeCode())`; `:code` ← `DmShared.xNull(itemDetail_Mdl.getGstUnitCode())`; `:code` ← `DmShared.xNull(itemDetail_Mdl.getUnitCode())`; `:ibCode` ← `DmShared.xNull(itemDetail_Mdl.getIbCode())`
- Errors: "Record approval is required to process the GST Action!" | "Consigner is mandatory!" | "Bill To is mandatory!" | "gst unit code is mandatory for item: " | "Item GST nature is mandatory for item: "
- Other calls: `Business_to_Business.getCode`, `Business_to_Business.getValue`, `Business_to_Consumer.getCode`, `Business_to_Consumer.getValue`, `DmPartyType.getValueOf`, `Einv_SupplyTypeCode.getValue`, `GST.getCode`, `GST.toString`, `INVOICE.getCode`, `INVOICE.getValue`, `NO.getEnumShortName`, `OUTPUT.toInt`, `PARTY.getCode`, `PartyDetailPopup_Server.getPartyTransInfo`, `TRANSACTION_SITE.getCode`, `VOID.toInt`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.accountAdjustment.server`, `dataman.dmToolkit.accountAdjustment.shared`, `dataman.dmToolkit.accountPosting.server`, `dataman.dmToolkit.dmCheckBox.client`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGUI.client`, `dataman.dmToolkit.dmGUI.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.documentSystemReport.client`, `dataman.dmToolkit.eGstPopup.client`, `dataman.dmToolkit.eGstPopup.server`, `dataman.dmToolkit.eGstPopup.shared`, `dataman.dmToolkit.gst.server`, `dataman.dmToolkit.gst.shared`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.itemAdjustment.server`, `dataman.dmToolkit.itemAdjustment.shared`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.overHead.client`, `dataman.dmToolkit.overHead.server`, `dataman.dmToolkit.overHead.shared`, `dataman.dmToolkit.overHeadLine.client`, `dataman.dmToolkit.overHeadLine.server`, `dataman.dmToolkit.overHeadLine.shared`, `dataman.dmToolkit.partyDetailPopup.client`, `dataman.dmToolkit.partyDetailPopup.server`, `dataman.dmToolkit.partyDetailPopup.shared`, `dataman.dmToolkit.self.shared`, `dataman.dmToolkit.tdsPopup.shared`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `dataman.libraryMenu.self.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.creditDebitNote.client`, `paperbiz.selfToolkit.creditDebitNote.shared`, `paperbiz.selfToolkit.gstDocumentInfo.client`, `paperbiz.selfToolkit.gstDocumentInfo.shared`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`, `paperbiz.selfToolkit.tdsPopup.client`, `paperbiz.selfToolkit.tdsPopup.server`, `paperbiz.selfToolkit.tdsPopup.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| getDefaultValues | `if (rsTemp.getString("isItc") == null)` | "Input Tax Credit is not defined in Environment Setting." |
| getDefaultValues | `} else if (rsTemp.getString("purchaseSalesAcPostingBasedOn") == null)` | "Purchase/Sales A/c Posting Based On is not defined in Environment Setting." |
| getDefaultValues | (unconditional) | "Purchase/Sales A/c Posting Based On is not defined in Environment Setting." |
| getDefaultValues | `if (rsTemp.getString("isInventoryAccountingActive") == null)` | "Inventory Accounting Active is not defined in Environment Setting (Company)." |
| getDefaultValues | `if (rsTemp.getString("isTcsAutoCalculate") == null)` | "'TCS Auto Calculate' is not defined in Environment Setting (Company)." |
| getDefaultValues | `if (rsTemp.getString("autoTcsBasedOn") == null)` | "'Auto TCS Based On' is not defined in Environment Setting (Company)." |
| getDefaultValues | `if (rsTemp.getString("tcsLimit") == null)` | "'TCS Limit' is not defined in Environment Setting (Company)." |
| saveRecord | `if (!DmShared.xNull(rsTemp.getString("postedBy")).equalsIgnoreCase(""))` | "Record is posted. You can't Edit this record." |
| saveRecord | `} else if (!DmShared.xNull(rsTemp.getString("cancelledBy")).equalsIgnoreCase(""))` | "Record is discarded. You can't Edit this record." |
| saveRecord | `if (DmShared.iNull(mrTemp.getU_EntDt_LatestLine()).longValue() != rsTemp.getTimestamp("u_EntDt_LatestLine").getTime())` | "Record has been changed, can't update record, refresh record then update" |
| saveRecord | `if (rsTemp.getLong("Cnt") > 0)` | "Record Has Been Audited. You Can Not Edit/ Delete This Record." |
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Entry already exists with this Reference Number." |
| deleteRecord | `if (!DmShared.xNull(rsTemp.getString("approvedBy")).equalsIgnoreCase(""))` | "Record is approved. You can't Discarded this record." |
| deleteRecord | `} else if (!DmShared.xNull(rsTemp.getString("cancelledBy")).equalsIgnoreCase(""))` | "Record is Already Discarded." |
| manageBilledQty | `if (DmShared.xNull(rsTemp.getString("approvedBy")).trim().equalsIgnoreCase(""))` | "pbDocId" |
| manageBilledQty | `if (isStatus == 1)` | "pbDocId" |
| processEGst | `if (DmShared.iNull(masterEGst_Mdl.geteGst_Mdl().getTt_u_EntDt_LatestLine()).longValue() != rsTemp.getTimestamp("u_EntDt_LatestLine").getTime())` | "Record has been changed, can't process this action, refresh record and retake action." |
| processEGst | `if (isTransporterDetailChange)` | "Transporter bill is genrated you can not change transport detail." |
| processEGst | `if (DmShared.iNull(masterEGst_Mdl.geteGst_Mdl().getU_EntDt_LatestLine()).longValue() != rsTemp.getTimestamp("u_EntDt_LatestLine").getTime())` | "Record has been changed, can't process this action, refresh record and retake action." |
| processEGst | `if (!DmShared.xNull(rsTemp.getString("cancelledBy")).trim().equalsIgnoreCase(""))` | "Record is discarded, can't process this action." |
| processEGst | `if (DmShared.xNull(rsTemp.getString("approvedBy")).trim().equalsIgnoreCase(""))` | "Record is not approved, can't process this action." |
| getEgstDetail | `if (DmShared.xNull(mrTemp.getApprovedBy()).trim().equalsIgnoreCase(""))` | "Record approval is required to process the GST Action!" |
| getEgstDetail | `if (consigner_Mdl == null \|\| DmShared.xNull(consigner_Mdl.getSharedKeyFieldCode()).trim().equalsIgnoreCase(""))` | "Consigner is mandatory!" |
| getEgstDetail | `if (billTo_Mdl == null \|\| DmShared.xNull(billTo_Mdl.getSharedKeyFieldCode()).trim().equalsIgnoreCase(""))` | "Bill To is mandatory!" |
| getEgstDetail | `if (DmShared.xNull(gstUnitCode).trim().equalsIgnoreCase(""))` | "gst unit code is mandatory for item: " |
| getEgstDetail | `if (DmShared.xNull(itemDetail_Mdl.getItemGSTNature()).trim().equalsIgnoreCase(""))` | "Item GST nature is mandatory for item: " |

## Notes (hand-written)
_No hand-written notes yet._
