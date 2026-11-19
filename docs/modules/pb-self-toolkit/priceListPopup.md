# pb-self-toolkit / priceListPopup

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/priceListPopup/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: priceListMast, guiPriceListHead
- Update tables: priceListMast, sg, pricelist, guiPriceListHead
- Delete cascade (in order): -
- Duplicate checks on save: none found
- "In use" checks before delete:
  - `SELECT COUNT(*) cnt FROM priceList pl WHERE entryFrom = :entryFrom AND site_Code = :site_Code AND applicableFrom = :applicableFrom AND priceListCode = :priceListCode`
- Validations: Rate List (Text); Price List Type (Text); Applicable On (Text); Name (Text); Party (Text); Site Specific (Text); rule: BF from is mandatory!; rule: BF to is mandatory!; rule: BF to should be greater than or equal to BF from.; rule: GSM from is mandatory!; rule: GSM to is mandatory!; rule: GSM to should be greater than or equal to GSM from.; rule: BF to should be greater than or equal to BF from; rule: Invalid price list type!; rule: Rate is mandatory!; rule: Deduction value cannot be greater than rate.; rule: Estimated landed rate cannot be negative.
- Lookups/suggestions: getTxtHPriceList, getTxtHApplicableOn, getTxtHParty, getTxtHRefCustomer, getTxtHPriceListType, getTxtHBf, getTxtHGsm, getTxtHOverhead, getTxtHRateType, getTxtHNature, getTxtHRoundOff, getTxtHAccount, getTxtHFormula, getTxtHItemCategory, getTxtHItemGroup, getTxtHItemName, getTxtHItemType, getSuggestionName
- CSV export: no

## Files
| file | lines |
|---|---|
| client/ClosingDatePopup.java | 169 |
| client/ClosingDatePopup_Dsg.java | 114 |
| client/CreatePriceListPopup.java | 548 |
| client/CreatePriceListPopup_Dsg.java | 341 |
| client/DeductionFormula.java | 268 |
| client/DeductionFormula_Dsg.java | 190 |
| client/DeductionGridEdit.java | 713 |
| client/DeductionGridEdit_Dsg.java | 467 |
| client/ItemGridEdit.java | 778 |
| client/ItemGridEdit_Dsg.java | 487 |
| client/PriceListGui.java | 221 |
| client/PriceListGui_Dsg.java | 128 |
| client/PriceListPopup.java | 945 |
| client/PriceListPopup_Dsg.java | 514 |
| client/Setting.java | 231 |
| client/Setting_Dsg.java | 217 |
| server/PriceListPopup_SrvImpl.java | 2929 |
| shared/ApplicableDates_Mdl.java | 40 |
| shared/CloseOpenLog_Mdl.java | 32 |
| shared/DefaultValue_Mdl.java | 67 |
| shared/Find_Mdl.java | 114 |
| shared/GrdItemDetail_Mdl.java | 241 |
| shared/OhDefaultValue_Mdl.java | 53 |
| shared/PriceListGui_Mdl.java | 183 |
| shared/PriceListPopup_Mdl.java | 150 |
| shared/PriceListPopup_Srv.java | 73 |
| shared/PriceListPopup_SrvAsync.java | 74 |
| shared/PriceListPopup_Vld.java | 101 |
| shared/SearchValue_Mdl.java | 130 |
| shared/Setting_Mdl.java | 89 |
| shared/TxtHFormula_Mdl.java | 23 |
| shared/TxtHParty_Mdl.java | 42 |
| shared/txtHOverHead_Mdl.java | 27 |

## UI labels / columns (client)
`Formula`, ` * Nature Add(+) will not be used in any transaction.`, ` * Nature Add(+) is only used for calculate Estimated Landed rate.`, `SNo.`, `Head`, `Manual Code`, `Nature`, `Show Formula Value (F.V.)`, `Round Off (R.O.)`, `Rate Type`, `Inventory`, `At Rate`, `Amount`, `Head A/c`, `Head Due A/c`, `Item Name`, `Item Category`, `Print S.No.`, `BF From`, `BF To`, `GSM From`, `GSM To`, `Rate`, `Deduction`, `Addition`, `Estimated Landed Rate`, `Deduction Detail :`, `Date`, `Party`, `Price List`, `Type`, `Applicable On`, `Applicable From`, `Applicable To`, `Ref. Customer`, `Site Specific`, `New`, `Item Type`, `Item Group`

## Models (shared DTOs)
### OhDefaultValue_Mdl
| type | field |
|---|---|
| String | roundOffTag |
| String | roundOffText |
| String | inventoryTag |
| String | inventoryText |

### CloseOpenLog_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | actionType |
| String | actionDate |

### GrdItemDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | v_SNo |
| String | unitName |
| String | unitCode |
| String | itemCatName |
| String | itemCatCode |
| String | bfFromTag |
| String | bfFromText |
| String | bfToTag |
| String | bfToText |
| String | gsmFromTag |
| String | gsmFromText |
| String | gsmToTag |
| String | gsmToText |
| String | rate |
| String | printSNo |
| String | deductionAmt |
| String | additionAmt |
| String | estimatedLandedRate |
| String | recordType |
| List<GrdDeduction_Mdl> | grdDeductionLst |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | siteTag |
| String | siteText |
| String | salesDefaultItemTypeTag |
| String | salesDefaultItemTypeText |
| String | guiTemplateCode |
| String | voucherTypeCode |

### Setting_Mdl
| type | field |
|---|---|
| String | itemTypeCode |
| String | itemTypeName |
| String | itemCategoryCode |
| String | itemCategoryName |
| String | itemGroupCode |
| String | itemGroupName |
| String | itemCode |
| String | itemName |

### txtHOverHead_Mdl (extends `KeyField implements Serializable,IsSerializable`)
| type | field |
|---|---|
| String | manualCode |

### SearchValue_Mdl
| type | field |
|---|---|
| String | fieldDataType |
| String | fieldData |
| String | applicableDate |
| String | rateListCode |
| String | priceTypeCode |
| String | itemcode |
| String | fromBfCode |
| String | toBfCode |
| String | fromGsmCode |
| String | toGsmCode |
| String | applicableOnCode |
| String | partyCode |
| String | refCustomerCode |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | priceListTypeTag |
| String | priceListType |
| String | applicableOnTag |
| String | applicableOn |
| String | partyCode |
| String | partyName |
| String | refCustomerCode |
| String | refCustomerName |
| String | applicableFrom |
| String | siteSpecificCode |
| String | siteSpecificName |

### PriceListPopup_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | priceListTypeTag |
| String | priceListTypeText |
| String | applicableOnTag |
| String | applicableOnText |
| String | partyCode |
| String | partyName |
| String | refCustomerCode |
| String | refCustomerName |
| String | siteSpecificCode |
| String | siteSpecificName |
| String | applicableFrom |
| String | applicableTo |
| String | isActive |

### TxtHParty_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityCode |
| String | cityName |

### PriceListGui_Mdl
| type | field |
|---|---|
| RecId | recIdPlGui |
| String | voucherDate |
| String | voucherTypeCode |
| String | priceListCode |
| String | priceListName |
| String | applicableDate |
| String | siteSpecificCode |
| String | siteSpecificName |
| String | siteCode |
| String | siteName |
| String | guiConfigTag |
| String | guiConfigText |
| String | partyCode |
| String | partyName |
| DmGUI_Mdl | guiMdl |

### ApplicableDates_Mdl
| type | field |
|---|---|
| String | applicableFrom |
| String | applicableTo |
| String | otherApplicableTo |

### TxtHFormula_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | OverheadCode |

## RPC interface
| method | params | returns |
|---|---|---|
| getDefaultValue |  | DmResult<DefaultValue_Mdl> |
| savePriceList | String applicableOnShortName, PriceListPopup_Mdl prevRecord, PriceListPopup_Mdl newRecord | DmResult<String> |
| deletePriceListMaster | String applicableOnShortName, String code | DmResult<String> |
| saveRecord | String applicableOnShortName, GrdItemDetail_Mdl prevMrTemp, GrdItemDetail_Mdl mrTemp, SearchValue_Mdl param | DmResult<String> |
| deleteRecord | String applicableOnShortName, GrdItemDetail_Mdl prevMrTemp, SearchValue_Mdl param | DmResult<String> |
| getMoveRec | String priceListCode | DmResult<PriceListPopup_Mdl> |
| getFind | String applicableOnShortName,DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getItemDetailGrid | Setting_Mdl setting_Mdl, SearchValue_Mdl param, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdItemDetail_Mdl>> |
| getYesNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPriceListType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getPriceListGuiDetail | String priceListCode, String applicableDate | DmResult<PriceListGui_Mdl> |
| savePriceListGui | String applicableOnShortName, PriceListGui_Mdl prevMrTemp, PriceListGui_Mdl priceListGui_Mdl,String voucherPrefix | DmResult<String> |
| closeOpenPriceList | String applicableOnShortName, PriceListPopup_Mdl plMdl, String closingDate, String loginDate | DmResult<String> |
| getTxtHFormula | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHFormula_Mdl>> |
| getTxtHPriceList | String applicableOnShortName,DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<PriceListPopup_Mdl>> |
| getTxtHApplicableOn | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHParty | String applicableOnCode, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getTxtHRefCustomer | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getTxtHBf | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGsm | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHOverhead | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<txtHOverHead_Mdl>> |
| getTxtHRateType | String applicableOnCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHNature | String applicableOnCode,DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHRoundOff | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAccount | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemCategory | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemGroup | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemName | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getApplicableDates | String startDt, PriceListPopup_Mdl param | DmResult<ApplicableDates_Mdl> |
| getSuggestionName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| printRecords | String priceListCode | DmResult<String> |

## Validator `PriceListPopup_Vld`
```java
public class PriceListPopup_Vld {
    public static void chkSave(SearchValue_Mdl searchValue_Mdl, GrdItemDetail_Mdl mrTemp) {
        DmShared.isValidText(searchValue_Mdl.getRateListCode(), "Rate List", 100);
        DmShared.isValidText(searchValue_Mdl.getPriceTypeCode(), "Price List Type", 200);
        DmShared.isValidText(searchValue_Mdl.getApplicableOnCode(), "Applicable On", 200);
        if (STEnum.PriceListType.getViaKey(DmShared.iNull(searchValue_Mdl.getPriceTypeCode()).intValue()) != null) {
            switch (STEnum.PriceListType.getViaKey(DmShared.iNull(searchValue_Mdl.getPriceTypeCode()).intValue())) {
                case BF:
                    if (DmShared.xNull(mrTemp.getBfFromTag()).trim().equalsIgnoreCase("")) {
                        throw new DmException(300, "BF from is mandatory!");
                    }
                    if (DmShared.xNull(mrTemp.getBfToTag()).trim().equalsIgnoreCase("")) {
                        throw new DmException(400, "BF to is mandatory!");
                    }
                    if (DmShared.vNull(mrTemp.getBfFromText()).doubleValue() > DmShared.vNull(mrTemp.getBfFromTag()).doubleValue()) {
                        throw new DmException(400, "BF to should be greater than or equal to BF from.");
                    }
                    break;
                case GSM:
                    if (DmShared.xNull(mrTemp.getGsmFromTag()).trim().equalsIgnoreCase("")) {
                        throw new DmException(500, "GSM from is mandatory!");
                    }
                    if (DmShared.xNull(mrTemp.getGsmToTag()).trim().equalsIgnoreCase("")) {
                        throw new DmException(600, "GSM to is mandatory!");
                    }
                    if (DmShared.vNull(mrTemp.getGsmFromText()).doubleValue() > DmShared.vNull(mrTemp.getGsmFromTag()).doubleValue()) {
                        throw new DmException(600, "GSM to should be greater than or equal to GSM from.");
                    }
                    break;
                case BOTH:
                    if (DmShared.xNull(mrTemp.getBfFromTag()).trim().equalsIgnoreCase("")) {
                        throw new DmException(300, "BF from is mandatory!");
                    }
                    if (DmShared.xNull(mrTemp.getBfToTag()).trim().equalsIgnoreCase("")) {
                        throw new DmException(400, "BF to is mandatory!");
                    }
                    if (DmShared.vNull(mrTemp.getBfFromText()).doubleValue() > DmShared.vNull(mrTemp.getBfFromTag()).doubleValue()) {
                        throw new DmException(400, "BF to should be greater than or equal to BF from");
                    }
                    if (DmShared.xNull(mrTemp.getGsmFromTag()).trim().equalsIgnoreCase("")) {
                        throw new DmException(500, "GSM from is mandatory!");
                    }
                    if (DmShared.xNull(mrTemp.getGsmToTag()).trim().equalsIgnoreCase("")) {
                        throw new DmException(600, "GSM to is mandatory!");
                    }
                    if (DmShared.vNull(mrTemp.getGsmFromText()).doubleValue() > DmShared.vNull(mrTemp.getGsmFromTag()).doubleValue()) {
                        throw new DmException(600, "GSM to should be greater than or equal to GSM from.");
                    }
                    break;
                default:
                    break;
            }
        } else {
            throw new DmException(200, "Invalid price list type!");
        }
        if (DmShared.vNull(mrTemp.getRate()).doubleValue() <= 0) {
            throw new DmException(700, "Rate is mandatory!");
        }
        boolean flagApplibaleOnPurchase = false;
        if (!DmShared.xNull(searchValue_Mdl.getApplicableOnCode()).trim().equalsIgnoreCase("")
                && STEnum.PriceListApplicableOn.getViaKey(DmShared.iNull(searchValue_Mdl.getApplicableOnCode()).intValue()) != null
                && STEnum.PriceListApplicableOn.getViaKey(DmShared.iNull(searchValue_Mdl.getApplicableOnCode()).intValue()).equals(STEnum.PriceListApplicableOn.PURCHASE)) {
            flagApplibaleOnPurchase = true;
        }
        PriceListShared.chkPriceListSchemeGrid(800, mrTemp.getGrdDeductionLst(), flagApplibaleOnPurchase);
        if (flagApplibaleOnPurchase) {
            if (DmShared.vNull(mrTemp.getDeductionAmt()).doubleValue() > DmShared.vNull(mrTemp.getRate()).doubleValue()) {
                throw new DmException(700, "Deduction value cannot be greater than rate.");
            }
        }
        if (DmShared.vNull(mrTemp.getEstimatedLandedRate()).doubleValue() < 0) {
            throw new DmException(700, "Estimated landed rate cannot be negative.");
        }
    }
    public static void chkSavePriceList(PriceListPopup_Mdl mrTemp) {
        DmShared.isValidText(DmShared.xNull(mrTemp.getSharedKeyFieldName()), "Name", 100);
        DmShared.isValidText(DmShared.xNull(mrTemp.getPriceListTypeTag()), "Price List Type", 200);
        DmShared.isValidText(DmShared.xNull(mrTemp.getApplicableOnTag()), "Applicable On", 300);
        if (STEnum.PriceListApplicableOn.getViaKey(DmShared.iNull(mrTemp.getApplicableOnTag()).intValue()).equals(STEnum.PriceListApplicableOn.PURCHASE)) {
            DmShared.isValidText(DmShared.xNull(mrTemp.getPartyCode()), "Party", 400);
        }
        DmShared.isValidText(DmShared.xNull(mrTemp.getSiteSpecificCode()), "Site Specific", 500);
    }
}
```

## Server `PriceListPopup_SrvImpl.java` (2929 lines)
Helpers used: `DmServer.chkIfDocumentUsed`, `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getLocalIPAddresses`, `DmServer.getParamList`, `DmServer.getRecId`, `DmServer.getSerialNoInteger`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnEndDate`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteName`, `DmServer.getSsnUserName`, `DmServer.getUserWithDateTime`, `DmServer.saveLog`, `DmServer.showJasper`, `DmServer.vNull`, `DmShared.convertDate`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.isValidText`, `DmShared.vNull`, `DmShared.xNull`

### `getTxtHPriceList`
`public DmResultPageing<List<PriceListPopup_Mdl>> getTxtHPriceList(String applicableOnShortName, DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT plm.code, plm.name as priceListName , plm.priceListType as priceListTypeTag , CASE WHEN plm.priceListType = :BFCODE THEN :BFNAME WHEN plm.priceListType = :GSMCODE THEN :GSMNAME WHEN plm.priceListType = :BOTHCODE THEN :BOTHNAME WHEN plm.priceListType = :NONECODE THEN :NONENAME ELSE '' END AS priceTypeName , plm.applicableOn as applicableOnTag , CASE WHEN plm.applicableOn = :PURCHASECODE THEN :PURCHASENAME WHEN plm.applicableOn = :SALECODE THEN :SALENAME ELSE '' END AS applicableOn , plm.subCode, sg.name as partyName, plm.refSubCode, sgrc.name AS refCustomerName , plm.isSiteSpecific FROM priceListMast plm LEFT JOIN subGroup sg on sg.subCode = plm.subCode LEFT JOIN subGroup sgrc on sgrc.subCode = plm.refSubCode WHERE 1 = 1 {…}`
- Params: `:BFNAME` ← `STEnum.PriceListType.BF.getValue()`; `:GSMNAME` ← `STEnum.PriceListType.GSM.getValue()`; `:BOTHNAME` ← `STEnum.PriceListType.BOTH.getValue()`; `:NONENAME` ← `STEnum.PriceListType.NONE.getValue()`; `:PURCHASENAME` ← `STEnum.PriceListApplicableOn.PURCHASE.getValue()`; `:SALENAME` ← `STEnum.PriceListApplicableOn.SALES.getValue()`
- Other calls: `BF.getId`, `BF.getValue`, `BOTH.getId`, `BOTH.getValue`, `GSM.getId`, `GSM.getValue`, `NONE.getId`, `NONE.getValue`, `PURCHASE.getId`, `PURCHASE.getShortName`, `PURCHASE.getValue`, `PriceListApplicableOn.getViaShortName`, `SALES.getId`, `SALES.getShortName`, `SALES.getValue`

### `getDefaultValue`
`public DmResult<DefaultValue_Mdl> getDefaultValue()`

- SQL 1: `SELECT TOP 1 vt.v_Type AS code , vt.guiTemplateCode FROM voucher_Type vt WHERE vt.category = :PRLST AND vt.isActive = 1`

### `getApplicableDates`
`public DmResult<ApplicableDates_Mdl> getApplicableDates(String startDt, PriceListPopup_Mdl param)`

- SQL 1: `SELECT max(pl.applicableFrom) AS applicableFrom, max(pl.applicableTo) as applicableTo FROM pricelist pl WHERE :startDt BETWEEN pl.applicableFrom AND pl.applicableTo AND pl.entryFrom = :entryFrom AND pl.site_Code= :site_Code AND pl.priceListCode = :plCode`
- Params: `:startDt` ← `DmServer.convertDate(startDt)`; `:entryFrom` ← `PriceListEntryFrom.PRICELIST_MASTER.getCode()`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:plCode` ← `DmShared.xNull(param.getSharedKeyFieldCode())`; `:entryFrom` ← `PriceListEntryFrom.PRICELIST_MASTER.getCode()`; `:subCode` ← `DmShared.xNull(param.getPartyCode())`; `:refSubCode` ← `DmShared.xNull(param.getRefCustomerCode())`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:plCode` ← `DmShared.xNull(param.getSharedKeyFieldCode())`
- Other calls: `PRICELIST_MASTER.getCode`, `PURCHASE.getId`

### `savePriceList`
`public DmResult<String> savePriceList(String applicableOnShortName, PriceListPopup_Mdl prevMrTemp, PriceListPopup_Mdl mrTemp)`

- SQL 1: `SELECT COUNT(*) AS CNT FROM priceListMast WHERE Name = :name`
- SQL 2: `INSERT INTO priceListMast( code, name, priceListType, applicableOn, subCode, refSubCode, isSiteSpecific , preparedBy, preparedDt ) VALUES ( :code,:name,:priceListType ,:applicableOn, :subCode, :refSubCode, :isSiteSpecific , :preparedBy, :preparedDt)`
- SQL 3: `UPDATE priceListMast SET name = :name , subCode = :subCode , refSubCode = :refSubCode , u_EntDt = :uEntDt , u_name = :u_name WHERE code = :code`
- Params: `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:subCode` ← `DmShared.xNull(mrTemp.getPartyCode())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:applicableOn` ← `DmShared.xNull(mrTemp.getApplicableOnTag())`; `:entryFrom` ← `PriceListEntryFrom.PRICELIST_MASTER.getCode()`; `:subCode` ← `DmShared.xNull(mrTemp.getPartyCode())`; `:refSubCode` ← `DmShared.xNull(mrTemp.getRefCustomerCode())`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:priceListType` ← `DmShared.foriegnKey(mrTemp.getPriceListTypeTag())`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getPartyCode())`; `:refSubCode` ← `DmShared.foriegnKey(mrTemp.getRefCustomerCode())`; `:applicableOn` ← `DmShared.foriegnKey(mrTemp.getApplicableOnTag())`; `:isSiteSpecific` ← `DmShared.xNull(mrTemp.getSiteSpecificCode())`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getPartyCode())`; `:refSubCode` ← `DmShared.foriegnKey(mrTemp.getRefCustomerCode())`; `:u_name` ← `DmServer.getSsnUserName(this)`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:pplCode` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:partyCode` ← `DmShared.xNull(mrTemp.getPartyCode())`; `:u_name` ← `DmServer.getSsnUserName(this)`; `:uEntDt` ← `DmServer.getServerTimestamp()`
- Errors: "PriceList name already exist." | "party is mandatory." | "'PriceList party' already exists!" | "'Reference customer' already exists!" | "Invalid key."
- Other calls: `PRICELIST_MASTER.getCode`, `PURCHASE.getId`, `PriceListApplicableOn.getViaKey`, `PriceListApplicableOn.getViaShortName`, `PriceListPopup_Vld.chkSavePriceList`, `SALES.getId`

### `deletePriceListMaster`
`public DmResult<String> deletePriceListMaster(String applicableOnShortName, String code)`

- SQL 1: `UPDATE sg SET sg.pplCode = :pplCode, sg.u_EntDt = :uEntDt, sg.u_name = :u_name FROM priceListMast plm LEFT JOIN subGroup sg ON sg.subCode = plm.subCode WHERE plm.subCode IS NOT NULL AND plm.code = :code`
- SQL 2: `DELETE FROM priceListMast where code = :code`
- Params: `:code` ← `code`; `:pplCode` ← `null`; `:u_name` ← `DmServer.getSsnUserName(this)`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:code` ← `code`
- Other calls: `PURCHASE.getId`, `PriceListApplicableOn.getViaShortName`, `SALES.getId`

### `saveRecord`
`public DmResult<String> saveRecord(String applicableOnShortName, GrdItemDetail_Mdl prevMrTemp, GrdItemDetail_Mdl mrTemp, SearchValue_Mdl param)`

- Other calls: `PURCHASE.getId`, `PriceListApplicableOn.getViaShortName`, `PriceListPopup_Vld.chkSave`, `SALES.getId`

### `deleteRecord`
`public DmResult<String> deleteRecord(String applicableOnShortName, GrdItemDetail_Mdl mrTemp, SearchValue_Mdl param)`

- SQL 1: `SELECT COUNT(*) cnt FROM priceList pl WHERE entryFrom = :entryFrom AND site_Code = :site_Code AND applicableFrom = :applicableFrom AND priceListCode = :priceListCode`
- Params: `:entryFrom` ← `PriceListEntryFrom.PRICELIST_MASTER.getCode()`; `:site_Code` ← `DmShared.xNull(site_Code)`; `:priceListCode` ← `DmShared.xNull(param.getRateListCode())`; `:applicableFrom` ← `DmServer.convertDate(DmShared.dNull(param.getApplicableDate()))`
- Errors: "Invalid key."
- Other calls: `PRICELIST_MASTER.getCode`, `PURCHASE.getId`, `PriceListApplicableOn.getViaShortName`, `PriceList_Server.deletePriceList`, `SALES.getId`

### `saveItem`
`private String saveItem(GrdItemDetail_Mdl prevMrTemp, GrdItemDetail_Mdl mrTemp, SearchValue_Mdl param, Connection cnTemp)`

- Other calls: `PriceList_Server.deletePriceList`, `PriceList_Server.savePriceList_ExistingFunction`

### `getPriceListMdl`
`private ItemPriceList_Mdl getPriceListMdl(GrdItemDetail_Mdl mrTemp, SearchValue_Mdl param)`

- Other calls: `NONE.getCode`

### `getTxtHApplicableOn`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHApplicableOn(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `PriceListApplicableOn.values`

### `getTxtHParty`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHParty(String applicableOnCode, DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as supplierName , sg.manualCode , c.cityName , sg.cityCode FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( SELECT acf.Nature FROM AcFilteration acf {…} ) AND sg.isActive = :one AND sgsl.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:PO` ← `DmShared.xNull(DmServer.getCodeViaManualCode(new DmVoucherCategory().PURCHASE_ORDER, "cate`; `:POU` ← `DmShared.xNull(DmServer.getCodeViaManualCode(new DmVoucherCategory().PURCHASE_ORDER_UNREGI`; `:PB` ← `DmShared.xNull(DmServer.getCodeViaManualCode(new DmVoucherCategory().PURCHASE_BILL, "categ`; `:PBU` ← `DmShared.xNull(DmServer.getCodeViaManualCode(new DmVoucherCategory().PURCHASE_BILL_UNREGIS`; `:PBD` ← `DmShared.xNull(DmServer.getCodeViaManualCode(new DmVoucherCategory().PURCHASE_BILL_DIRECT,`; `:PBDU` ← `DmShared.xNull(DmServer.getCodeViaManualCode(new DmVoucherCategory().PURCHASE_BILL_DIRECT_`; `:SOV` ← `DmShared.xNull(DmServer.getCodeViaManualCode(new DmVoucherCategory().SALES_ORDER, "categor`; `:SOOV` ← `DmShared.xNull(DmServer.getCodeViaManualCode(new DmVoucherCategory().GENERAL_SALES_ORDER, `; `:SBV` ← `DmShared.xNull(DmServer.getCodeViaManualCode(new DmVoucherCategory().SALES_INVOICE, "categ`; `:SBOV` ← `DmShared.xNull(DmServer.getCodeViaManualCode(new DmVoucherCategory().GENERAL_SALES_INVOICE`
- Other calls: `PriceListApplicableOn.getViaKey`

### `getTxtHRefCustomer`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHRefCustomer(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code, sg.name as refCustmerName, sg.manualCode, c.cityName , sg.cityCode FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode WHERE sg.isActive = :isActive AND sgsl.site_Code = :site_Code AND sg.nature IN ( SELECT DISTINCT acf.nature FROM acFilteration acf WHERE acf.v_Type IN (:SI, :SIO) AND acf.nature <> :CS AND acf.nature IS NOT NULL )`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:CS` ← `DmServer.getCodeViaManualCode("CS", "manualCode", "subGroupNature", "code", cnTemp)`; `:SI` ← `DmShared.xNull(DmServer.getCodeViaManualCode(new DmVoucherCategory().SALES_INVOICE, "categ`; `:SIO` ← `DmShared.xNull(DmServer.getCodeViaManualCode(new DmVoucherCategory().GENERAL_SALES_INVOICE`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(String applicableOnShortName, DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT plm.code, plm.name as priceListName , plm.priceListType as priceListTypeTag , CASE WHEN plm.priceListType = :BFCODE THEN :BFNAME WHEN plm.priceListType = :GSMCODE THEN :GSMNAME WHEN plm.priceListType = :BOTHCODE THEN :BOTHNAME WHEN plm.priceListType = :NONECODE THEN :NONENAME ELSE '' END AS priceTypeName , plm.applicableOn as applicableOnTag , CASE WHEN plm.applicableOn = :PURCHASECODE THEN :PURCHASENAME WHEN plm.applicableOn = :SALECODE THEN :SALENAME ELSE '' END AS applicableOn , plm.subCode, sg.name as partyName , plm.refSubCode, sgr.name as refSubName , vPl.applicableFrom , plm.isSiteSpecific as siteSpecificCode , CASE WHEN ISNULL(plm.isSiteSpecific,0) = 1 THEN 'Yes' ELSE 'NO' END AS siteSpecificName FROM priceListMast plm INNER JOIN ( SELECT pl.priceListCode , MAX(pl.applicableFrom) as applicableFrom FROM priceList pl Where pl.site_Code = :site_Code and entryFrom = :entryFrom group by pl.priceListCode ) vPl on vPl.priceListCode = plm.code LEFT JOIN subGroup sg on sg.subCode = plm.subCode LEFT JOIN subGroup sgr on sgr.subCode = plm.refSubCode WHERE 1=1 {…}`
- Params: `:entryFrom` ← `String.valueOf(PriceListEntryFrom.PRICELIST_MASTER.getCode())`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:BFNAME` ← `STEnum.PriceListType.BF.getValue()`; `:GSMNAME` ← `STEnum.PriceListType.GSM.getValue()`; `:BOTHNAME` ← `STEnum.PriceListType.BOTH.getValue()`; `:NONENAME` ← `STEnum.PriceListType.NONE.getValue()`; `:PURCHASENAME` ← `STEnum.PriceListApplicableOn.PURCHASE.getValue()`; `:SALENAME` ← `STEnum.PriceListApplicableOn.SALES.getValue()`
- Other calls: `BF.getId`, `BF.getValue`, `BOTH.getId`, `BOTH.getValue`, `GSM.getId`, `GSM.getValue`, `NONE.getId`, `NONE.getValue`, `PRICELIST_MASTER.getCode`, `PURCHASE.getId`, `PURCHASE.getShortName`, `PURCHASE.getValue`, `PriceListApplicableOn.getViaShortName`, `SALES.getId`, `SALES.getShortName`, `SALES.getValue`

### `getItemDetailGrid`
`public DmResultPageing<List<GrdItemDetail_Mdl>> getItemDetailGrid(Setting_Mdl setting_Mdl, SearchValue_Mdl param, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.name as itemName, pl.itemCode, pl.unitCode , um.unit AS unitName , im.itemCategoryCode as itemCatCode, ic.name as itemCatName , pl.rate, pl.cmn_Deduction, pl.cmn_Addition , pl.printSno, 2 as recordType , pl.fromBfCode, pl.toBfCode, pl.fromGsmCode, pl.toGsmCode, pl.estdLandedRate , pl.v_SNo FROM priceList pl LEFT JOIN itemMast im on im.code = pl.itemCode LEFT JOIN unitMast um ON um.code = pl.unitCode LEFT JOIN itemCat ic on ic.code = im.itemCategoryCode {…} UNION ALL SELECT im.name as itemName,im.code as itemCode {…} , um.unit AS unitName , ic.code as itemCatCode, ic.name as itemCatName , 0.00 as rate, 0.00 as cmn_Deduction, 0.00 as cmn_Addition , im.printSrNo as printSno , 1 as recordType , null as fromBfCode, null as toBfCode , null as fromGsmCode,null as toGsmCode, 0.00 as estdLandedRate , null as v_SNo FROM itemMast im LEFT JOIN itemCat ic ON ic.code = im.itemCategoryCode LEFT JOIN itemSiteList sl ON sl.code = im.code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:priceListCode` ← `DmShared.iNull(param.getRateListCode()).toString()`; `:applicableDate` ← `DmShared.dNull(DmServer.convertDate(param.getApplicableDate()))`; `:entryFrom` ← `DmShared.iNull(PriceListEntryFrom.PRICELIST_MASTER.getCode()).toString()`; `:FM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:SM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`
- Other calls: `NONE.getId`, `PRICELIST_MASTER.getCode`, `PURCHASE.getId`, `PriceList_Server.getGridOhDeductionDetails`, `SALES.getId`

### `getYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHPriceListType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHPriceListType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `PriceListType.values`

### `getTxtHBf`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHBf(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT bf.code, bf.code as name FROM bfMast bf where bf.isActive = 1`

### `getTxtHGsm`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGsm(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT gm.code, gm.code as name FROM gsmMast gm where gm.isActive = 1`

### `getTxtHOverhead`
`public DmResultPageing<List<txtHOverHead_Mdl>> getTxtHOverhead(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT CODE, NAME, manualCode FROM overheadmast`

### `getTxtHRateType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHRateType(String applicableOnCode, DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `PriceListApplicableOn.getViaKey`, `PriceList_OH_EvalType.values`

### `getTxtHNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHNature(String applicableOnCode, DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `OverHeadNature.values`, `PriceListApplicableOn.getViaKey`

### `getTxtHRoundOff`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHRoundOff(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `RoundOffNature.values`

### `getTxtHAccount`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAccount(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode,sg.name,sg.manualCode ,ag.groupName,sgn.name as nature FROM subGroup sg LEFT JOIN acGroup ag on ag.groupCode = sg.groupCode LEFT JOIN subGroupNature sgn on sgn.code = sg.nature`

### `getTxtHFormula`
`public DmResultPageing<List<TxtHFormula_Mdl>> getTxtHFormula(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT code, name, manualCode FROM overheadmast`

### `getTxtHItemCategory`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemCategory(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ic.code AS categoryCode , ic.name AS categoryName FROM itemcat ic`

### `getTxtHItemGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ig.code, ig.name FROM itemgroup ig`

### `getTxtHItemName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , um.unit AS sku , im.manualCode FROM itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN unitmast um ON im.sku = um.code WHERE im.IsActive = 1 AND (im.filterItemType IN (:FM,:SM) OR im.isCommon = 1) AND sl.site_Code = :site_Code AND im.isQuotationMandatory = 0`
- Params: `:FM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:SM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHItemType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemType(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `select code,name,manualcode from itemType {…}`

### `getPriceListGuiDetail`
`public DmResult<PriceListGui_Mdl> getPriceListGuiDetail(String priceListCode, String applicableDate)`

- SQL 1: `SELECT plm.code as priceListCode,plm.name as priceListName,plm.isSiteSpecific FROM priceListMast plm WHERE plm.code = :priceListCode`
- SQL 2: `SELECT gpl.docId,gpl.guiConfigCode,gpl.site_Code , gpl.v_Type,gpl.v_No,gpl.recId,gpl.recIdPrefix, gpl.v_Date ,gc.name AS guiConfigName,gpl.subCode AS partyCode ,sg.name AS partyName FROM guiPriceListHead gpl LEFT JOIN guiConfig gc ON gc.code = gpl.guiConfigCode LEFT JOIN subGroup sg ON sg.subCode = gpl.subCode WHERE gpl.priceListCode = :priceListCode AND gpl.applicableDate = :applicableDate AND gpl.entryFrom = :entryFrom {…}`
- Params: `:priceListCode` ← `DmShared.xNull(priceListCode)`; `:priceListCode` ← `DmShared.xNull(mrTemp.getPriceListCode())`; `:applicableDate` ← `DmServer.convertDate(DmShared.dNull(mrTemp.getApplicableDate()))`; `:entryFrom` ← `PriceListEntryFrom.PRICELIST_MASTER.getCode()`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `DmGUI_Server.getGUIViaConfigCode`, `PRICELIST_MASTER.getCode`

### `closeOpenPriceList`
`public DmResult<String> closeOpenPriceList(String applicableOnShortName, PriceListPopup_Mdl mrTmp, String closingDate, String loginDate)`

- SQL 1: `SELECT count(*) AS cnt FROM priceList pl LEFT JOIN priceListMast plm ON plm.code = pl.priceListCode WHERE 1=1 {…} AND pl.applicableFrom = :applicableFrom AND pl.applicableTo <> :applicableTo AND pl.priceListCode = :priceListCode`
- SQL 2: `SELECT count(*) AS cnt FROM priceList pl LEFT JOIN priceListMast plm ON plm.code = pl.priceListCode WHERE 1=1 {…} AND pl.applicableTo = :applicableTo AND pl.priceListCode <> :priceListCode`
- SQL 3: `SELECT COUNT(*) cnt FROM priceList pl LEFT JOIN priceListMast plm ON plm.code = pl.priceListCode WHERE 1=1 AND pl.entryFrom = :entryFrom AND pl.subCode = :subCode AND pl.refSubCode = :refSubCode AND pl.applicableTo > :applicableTo AND CASE WHEN pl.site_code = :site_Code THEN 1 ELSE CASE WHEN plm.isSiteSpecific = 1 THEN 0 ELSE 1 END END = 1`
- SQL 4: `UPDATE pricelist SET applicableTo = :updatedApplicableTo FROM pricelist pl, priceListMast plm WHERE plm.code = pl.priceListCode {…} AND pl.applicableFrom = :applicableFrom AND pl.applicableTo = :applicableTo AND priceListCode = :priceListCode`
- SQL 5: `updatedApplicableTo {…}`
- Params: `:entryFrom` ← `PriceListEntryFrom.PRICELIST_MASTER.getCode()`; `:subCode` ← `DmShared.xNull(mrTmp.getPartyCode())`; `:refSubCode` ← `DmShared.xNull(mrTmp.getRefCustomerCode())`; `:applicableFrom` ← `DmServer.convertDate(DmShared.dNull(mrTmp.getApplicableFrom()))`; `:applicableTo` ← `DmServer.convertDate(DmShared.dNull("31-Dec-9999"))`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:priceListCode` ← `DmShared.xNull(mrTmp.getSharedKeyFieldCode())`; `:entryFrom` ← `PriceListEntryFrom.PRICELIST_MASTER.getCode()`; `:subCode` ← `DmShared.xNull(mrTmp.getPartyCode())`; `:refSubCode` ← `DmShared.xNull(mrTmp.getRefCustomerCode())`; `:applicableTo` ← `DmServer.convertDate(DmShared.dNull("31-Dec-9999"))`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:priceListCode` ← `DmShared.xNull(mrTmp.getSharedKeyFieldCode())`; `:entryFrom` ← `PriceListEntryFrom.PRICELIST_MASTER.getCode()`; `:subCode` ← `DmShared.xNull(mrTmp.getPartyCode())`; `:refSubCode` ← `DmShared.xNull(mrTmp.getRefCustomerCode())`; `:applicableTo` ← `DmServer.convertDate(DmShared.dNull(mrTmp.getApplicableTo()))`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:updatedApplicableTo` ← `DmServer.convertDate(DmShared.dNull(updatedApplicableTo))`; `:entryFrom` ← `PriceListEntryFrom.PRICELIST_MASTER.getCode()`; `:subCode` ← `DmShared.xNull(mrTmp.getPartyCode())`; `:refSubCode` ← `DmShared.xNull(mrTmp.getRefCustomerCode())`; `:applicableFrom` ← `DmServer.convertDate(DmShared.dNull(mrTmp.getApplicableFrom()))`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:applicableTo` ← `DmServer.convertDate(DmShared.dNull(mrTmp.getApplicableTo()))`; `:priceListCode` ← `DmShared.xNull(mrTmp.getSharedKeyFieldCode())`
- Errors: "Closing date should not be greater than current date!" | "Closing date should be greater than or equal to applicable from date!" | "'Price List' already closed!" | "An open 'price list' exists for the same reference customer!" | "Only latest price list can be opened!"
- Other calls: `PRICELIST_MASTER.getCode`, `PURCHASE.getId`, `PriceListApplicableOn.getViaShortName`, `SALES.getId`

### `savePriceListGui`
`public DmResult<String> savePriceListGui(String applicableOnShortName, PriceListGui_Mdl prevMrTemp, PriceListGui_Mdl mrTemp, String voucherPrefix)`

- SQL 1: `INSERT INTO guiPriceListHead ( docId,v_Type,v_No,recId,recIdPrefix,v_Prefix,v_Date , guiConfigCode , priceListCode , applicableDate , entryFrom , entryFromNature , site_Code , subCode , preparedBy , preparedDt , transferDt , preparedByIP) VALUES( :docId,:v_Type,:v_No,:recId,:recIdPrefix,:v_Prefix,:v_Date , :guiConfigCode , :priceListCode , :applicableDate , :entryFrom , :entryFromNature , :site_Code , :subCode , :preparedBy , :preparedDt , :transferDt , :preparedByIP )`
- SQL 2: `UPDATE guiPriceListHead SET guiConfigCode = :guiConfigCode , u_Name = :u_Name , u_EntDt = :u_EntDt where docId = :docId`
- Params: `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:recId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:recIdPrefix` ← `DmShared.xNull(DmShared.xNull(mrTemp.getRecIdPlGui().getPrefix()))`; `:v_Type` ← `DmShared.foriegnKey(mrTemp.getVoucherTypeCode())`; `:v_No` ← `DmShared.iNull(mrTemp.getRecIdPlGui().getCounter()).toString()`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:v_Prefix` ← `DmShared.xNull(voucherPrefix)`; `:guiConfigCode` ← `DmShared.foriegnKey(mrTemp.getGuiConfigTag())`; `:priceListCode` ← `DmShared.foriegnKey(mrTemp.getPriceListCode())`; `:applicableDate` ← `DmServer.convertDate(DmShared.dNull(mrTemp.getApplicableDate()))`; `:site_Code` ← `DmShared.foriegnKey(mrTemp.getSiteCode())`; `:entryFrom` ← `PriceListEntryFrom.PRICELIST_MASTER.getCode()`; `:entryFromNature` ← `PriceListEntryFrom.NONE.getCode()`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getPartyCode())`; `:transferDt` ← `null`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:preparedby` ← `DmShared.xNull(DmServer.getSsnUserName(this))`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:guiConfigCode` ← `DmShared.foriegnKey(mrTemp.getGuiConfigTag())`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `DmServer.getServerTimestamp()`
- Errors: "Invalid key."
- Other calls: `DmGUI_Server.deleteViaConfigCode`, `DmGUI_Server.saveViaConfigCode`, `NONE.getCode`, `PRICELIST_MASTER.getCode`, `PURCHASE.getId`, `PriceListApplicableOn.getViaShortName`, `SALES.getId`

### `savePriceListLog`
`private void savePriceListLog(String keyCode, GrdItemDetail_Mdl prevRecord, GrdItemDetail_Mdl newRecord, DmEnum.DmTransactionMode transactionMode, String menuName)`

- Other calls: `ModifyReason_Server.saveNew`, `SYSTEM_ACTIVITY.getName`

### `saveLogCloseOpenPL`
`private void saveLogCloseOpenPL(CloseOpenLog_Mdl newRecord, String menuName)`

- Other calls: `ModifyReason_Server.saveNew`, `SYSTEM_ACTIVITY.getName`

### `savePriceListMasterLog`
`private void savePriceListMasterLog(String keyCode, PriceListPopup_Mdl prevRecord, PriceListPopup_Mdl newRecord, DmEnum.DmTransactionMode transactionMode, String menuName)`

- Other calls: `ModifyReason_Server.saveNew`, `SYSTEM_ACTIVITY.getName`

### `savePriceListGuiLog`
`private void savePriceListGuiLog(String keyCode, PriceListGui_Mdl prevRecord, PriceListGui_Mdl newRecord, DmEnum.DmTransactionMode transactionMode, String menuName)`

- Other calls: `ModifyReason_Server.saveNew`, `SYSTEM_ACTIVITY.getName`

### `getMoveRec`
`public DmResult<PriceListPopup_Mdl> getMoveRec(String priceListCode)`


### `getMoveRec`
`private PriceListPopup_Mdl getMoveRec(String priceListCode, Connection cnTemp) throws Exception`

- SQL 1: `SELECT plm.code, plm.name as priceListName , plm.priceListType as priceListTypeTag , CASE WHEN plm.priceListType = :BFCODE THEN :BFNAME WHEN plm.priceListType = :GSMCODE THEN :GSMNAME WHEN plm.priceListType = :BOTHCODE THEN :BOTHNAME WHEN plm.priceListType = :NONECODE THEN :NONENAME ELSE '' END AS priceTypeName , plm.applicableOn as applicableOnTag , CASE WHEN plm.applicableOn = :PURCHASECODE THEN :PURCHASENAME WHEN plm.applicableOn = :SALECODE THEN :SALENAME ELSE '' END AS applicableOn , plm.subCode, sg.name as partyName, plm.refSubCode, rcsg.name as refCustomer , plm.isSiteSpecific FROM priceListMast plm LEFT JOIN subGroup sg on sg.subCode = plm.subCode LEFT JOIN subGroup rcsg on rcsg.subCode = plm.refSubCode WHERE plm.code = :priceListCode`
- Params: `:priceListCode` ← `DmShared.xNull(priceListCode)`; `:BFCODE` ← `STEnum.PriceListType.BF.getId()`; `:BFNAME` ← `STEnum.PriceListType.BF.getValue()`; `:GSMCODE` ← `STEnum.PriceListType.GSM.getId()`; `:GSMNAME` ← `STEnum.PriceListType.GSM.getValue()`; `:BOTHCODE` ← `STEnum.PriceListType.BOTH.getId()`; `:BOTHNAME` ← `STEnum.PriceListType.BOTH.getValue()`; `:NONECODE` ← `STEnum.PriceListType.NONE.getId()`; `:NONENAME` ← `STEnum.PriceListType.NONE.getValue()`; `:PURCHASECODE` ← `STEnum.PriceListApplicableOn.PURCHASE.getId()`; `:PURCHASENAME` ← `STEnum.PriceListApplicableOn.PURCHASE.getValue()`; `:SALECODE` ← `STEnum.PriceListApplicableOn.SALES.getId()`; `:SALENAME` ← `STEnum.PriceListApplicableOn.SALES.getValue()`
- Other calls: `BF.getId`, `BF.getValue`, `BOTH.getId`, `BOTH.getValue`, `GSM.getId`, `GSM.getValue`, `NONE.getId`, `NONE.getValue`, `PURCHASE.getId`, `PURCHASE.getValue`, `SALES.getId`, `SALES.getValue`

### `getSuggestionName`
`public DmResultPageing<List<String>> getSuggestionName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT name, code, priceListType, applicableOn FROM priceListMast`

### `printRecords`
`public DmResult<String> printRecords(String priceListCode)`

- SQL 1: `WITH ctePriceList AS ( SELECT pl.priceListCode, plm.name as priceListName ,plm.priceListType as priceListTypeTag ,pl.v_SNo ,CASE WHEN plm.priceListType = :BFCODE THEN :BFNAME WHEN plm.priceListType = :GSMCODE THEN :GSMNAME WHEN plm.priceListType = :BOTHCODE THEN :BOTHNAME WHEN plm.priceListType = :NONECODE THEN :NONENAME ELSE '' END AS priceTypeName ,CASE WHEN plm.applicableOn = :PURCHASECODE THEN :PURCHASENAME WHEN plm.applicableOn = :SALECODE THEN :SALENAME ELSE '' END AS applicableOn ,IIF(plm.isSiteSpecific = 1, 'Yes', 'No') AS isSiteSpecific ,sm.name AS site_Name ,pl.subCode as partyCode ,sg.name as partyName ,pl.applicableFrom as fromDate, pl.applicableTo as toDate ,im.Code as itemCode, im.name as itemName, pl.fromBfCode, pl.toBfCode,pl.fromGsmCode, pl.toGsmCode ,pl.rate, um.unit, pl.cmn_Addition, pl.cmn_Deduction, pl.estdLandedRate ,pl.entryFrom ,1 AS sno --For Party Wise Print = No FROM priceListMast plm LEFT JOIN priceList pl ON pl.priceListCode = plm.code LEFT JOIN subGroup sg on sg.subCode = pl.subCode LEFT JOIN itemMast im ON im.code = pl.itemCode LEFT JOIN unitMast um ON um.code = pl.unitCode LEFT JOIN siteMast sm ON sm.code = pl.site_code WHERE plm.code = :priceListCode AND '2022-04-01' BETWEEN pl.applicableFrom AND pl.applicableTo AND CASE WHEN pl.site_code = 1 THEN 1 ELSE CASE WHEN plm.isSiteSpecific = 1 THEN 0 ELSE 1 END END = 1 AND pl.entryFrom = 1 --For Party Wise Print = No ) SELECT cpl.* FROM ctePriceList cpl WHERE cpl.sno = 1`
- SQL 2: `SELECT plohd.priceListCode,plohd.v_SNo, plohd.sNo , plohd.overHeadCode, ohm.name AS header ,ohm.manualCode ,plohd.atRate, plohd.amount,plohd.evalTypeCode ,plohd.formula,plohd.isShowFormulaValue ,plohd.roundOff,plohd.acCode,plohd.dueAcCode, plohd.nature ,CASE WHEN plohd.EvalTypeCode = {…} THEN ' {…} ' WHEN plohd.EvalTypeCode = {…} THEN ' {…} ' ELSE '' END AS EvalType ,CASE WHEN plohd.nature = ' {…} ' THEN ' {…} ' WHEN plohd.nature = ' {…} ' THEN ' {…} ' ELSE '' END AS natureType FROM priceListOhDetail plohd LEFT JOIN overheadmast as ohm ON ohm.code = plohd.overHeadCode WHERE plohd.priceListCode = $P{priceListCode} AND plohd.v_SNo = $P{v_SNo}`
- Params: `:priceListCode` ← `DmShared.xNull(priceListCode)`; `:BFCODE` ← `STEnum.PriceListType.BF.getId()`; `:BFNAME` ← `STEnum.PriceListType.BF.getValue()`; `:GSMCODE` ← `STEnum.PriceListType.GSM.getId()`; `:GSMNAME` ← `STEnum.PriceListType.GSM.getValue()`; `:BOTHCODE` ← `STEnum.PriceListType.BOTH.getId()`; `:BOTHNAME` ← `STEnum.PriceListType.BOTH.getValue()`; `:NONECODE` ← `STEnum.PriceListType.NONE.getId()`; `:NONENAME` ← `STEnum.PriceListType.NONE.getValue()`; `:PURCHASECODE` ← `STEnum.PriceListApplicableOn.PURCHASE.getId()`; `:PURCHASENAME` ← `STEnum.PriceListApplicableOn.PURCHASE.getValue()`; `:SALECODE` ← `STEnum.PriceListApplicableOn.SALES.getId()`; `:SALENAME` ← `STEnum.PriceListApplicableOn.SALES.getValue()`
- Other calls: `ADD.getCode`, `ADD.getName`, `BF.getId`, `BF.getValue`, `BOTH.getId`, `BOTH.getValue`, `DED.getCode`, `DED.getName`, `GSM.getId`, `GSM.getValue`, `NONE.getId`, `NONE.getValue`, `PERCENTAGE.getCode`, `PERCENTAGE.getName`, `PER_UNIT.getCode`, `PER_UNIT.getName`, `PRINT.getValue`, `PURCHASE.getId`, `PURCHASE.getValue`, `SALES.getId`, `SALES.getValue`, `STServer.setJasperVariables`, `SYSTEM_ACTIVITY.getName`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmCheckBox.client`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGUI.client`, `dataman.dmToolkit.dmGUI.server`, `dataman.dmToolkit.dmGUI.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextArea.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.iplApplicableWithOption.client`, `paperbiz.selfToolkit.iplApplicableWithOption.shared`, `paperbiz.selfToolkit.priceList.client`, `paperbiz.selfToolkit.priceList.server`, `paperbiz.selfToolkit.priceList.shared`, `paperbiz.selfToolkit.priceListPopup.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| savePriceList | `if (rsTemp.getLong("cnt") > 0)` | "PriceList name already exist." |
| savePriceList | `if (DmShared.xNull(mrTemp.getPartyCode()).trim().equalsIgnoreCase(""))` | "party is mandatory." |
| savePriceList | `if (rsTemp.getLong("cnt") > 0)` | "'PriceList party' already exists!" |
| savePriceList | `if (rsTemp.getInt("isActive") == 1)` | "'Reference customer' already exists!" |
| savePriceList | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| deleteRecord | `if (DmShared.xNull(mrTemp.getSharedKeyFieldCode()).equalsIgnoreCase(""))` | "Invalid key." |
| closeOpenPriceList | `if (DmShared.convertDate(DmShared.dNull(closingDate)).getTime() > DmShared.convertDate(DmShared.dNull(loginDate)).getTime())` | "Closing date should not be greater than current date!" |
| closeOpenPriceList | `if (DmShared.convertDate(DmShared.dNull(closingDate)).getTime() < DmShared.convertDate(DmShared.dNull(mrTmp.getApplicableFrom())).getTime())` | "Closing date should be greater than or equal to applicable from date!" |
| closeOpenPriceList | `if (rsTemp.getInt("cnt") > 0)` | "'Price List' already closed!" |
| closeOpenPriceList | `if (rsTemp.getInt("cnt") > 0)` | "An open 'price list' exists for the same reference customer!" |
| closeOpenPriceList | `if (rsTemp.getInt("cnt") > 0)` | "Only latest price list can be opened!" |
| savePriceListGui | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |

## Notes (hand-written)
_No hand-written notes yet._
