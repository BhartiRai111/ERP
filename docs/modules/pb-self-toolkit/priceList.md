# pb-self-toolkit / priceList

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/priceList/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: priceList, guipriceListHead
- Update tables: priceList, guiPriceListHead
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: -
- CSV export: no

## Files
| file | lines |
|---|---|
| client/PriceListClient.java | 284 |
| client/STPriceListCallback.java | 11 |
| server/PriceList_Server.java | 1584 |
| shared/GrdDeduction_Mdl.java | 264 |
| shared/ItemPlOh_Mdl.java | 230 |
| shared/ItemPriceList_Mdl.java | 301 |
| shared/PriceListDelete_Mdl.java | 107 |
| shared/PriceListGUIHead_Mdl.java | 137 |
| shared/PriceListSave_Mdl.java | 82 |
| shared/PriceListShared.java | 94 |
| shared/ServicePriceList_Mdl.java | 174 |

## Models (shared DTOs)
### PriceListGUIHead_Mdl
| type | field |
|---|---|
| String | docId |
| String | guiConfigCode |
| String | priceListCode |
| String | v_Type |
| String | v_Prefix |
| String | v_Date |
| String | entryFrom |
| String | entryFromNature |
| String | applicableDate |
| String | site_Code |
| String | subCode |
| Timestamp | timestamp |
| List<DmGUIRow_Mdl> | guiRow_MdlList |

### ServicePriceList_Mdl
| type | field |
|---|---|
| STEnum.PriceListEntryFrom | priceListEntryFrom |
| String | entryFromNature |
| STEnum.PriceListNature | priceListNature |
| String date, serviceCode, organizationServiceCode, organizationServiceName, organizationCode, | siteCode |
| String roomCatCode, consultantCode, voucherType, | dayNameCode |
| String time, | timeTo |
| String rate, discAmt, | discPercentage |
| String | printSNo |

### PriceListSave_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | priceListCode |
| String | site_Code |
| String | priceTypeCode |
| String | applicableFrom |
| String | applicableTo |
| List<ItemPriceList_Mdl> | priceList |
| STEnum.PriceListEntryFrom | priceListEntryFrom |

### ItemPriceList_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| STEnum.PriceListEntryFrom | entryFrom |
| String | entryFromNature |
| STEnum.PriceListNature | priceListNature |
| String | priceListCode |
| String | v_SNo |
| String | date |
| String | partyCode |
| String | refCustomerCode |
| String | siteCode |
| String | itemName |
| String | itemCode |
| String | unitName |
| String | unitCode |
| String | rate |
| String | printSNo |
| String | deductionAmount |
| String | additionAmount |
| String | bfFromCode |
| String | bfToCode |
| String | gsmFromCode |
| String | gsmToCode |
| String | estdLandedRate |
| String | freightTypeCode |
| String | itemNatureCode |
| String | packagingTypeCode |

### ItemPlOh_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| STEnum.PriceListNature | priceListNature |
| String | priceListCode |
| String | date |
| String | partyCode |
| String | refSubCode |
| String | siteCode |
| String | itemName |
| String | itemCode |
| String | unitName |
| String | unitCode |
| String | bf |
| String | gsm |
| String | freightType |
| String | itemNature |
| String | packagingType |
| String | consignee |
| String | distributor |
| String | rate |
| List<GrdDeduction_Mdl> | grdDeduction_MdlLst |

### PriceListDelete_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | v_SNo |
| String | applicableFrom |
| STEnum.PriceListEntryFrom | priceListEntryFrom |
| String | priceListCode |
| String | site_Code |
| String | bfFrom |
| String | bfTo |
| String | gsmFrom |
| String | gsmTo |
| boolean | silentlyRetrun |

### GrdDeduction_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | srNo |
| String | overHeadManualCode |
| String | rateTypeCode |
| String | rateTypeName |
| String | natureCode |
| String | natureName |
| String | showFormulaValueCode |
| String | showFormulaValueName |
| String | roundOffCode |
| String | roundOffName |
| String | atRate |
| String | amount |
| String | dueAmt |
| String | adjAmt |
| String | headAcCode |
| String | headAcName |
| String | headDueAcCode |
| String | headDueAcName |
| String | isInventoryEffectCode |
| String | isInventoryEffectName |
| String | formula |
| String | dueStatus |
| boolean | dummyHead |

## Server `PriceList_Server.java` (1584 lines)
Helpers used: `DmServer.addDate`, `DmServer.convertDate`, `DmServer.getDocId`, `DmServer.getLocalIPAddresses`, `DmServer.getRecId`, `DmServer.getServerTimestamp`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.convertDate`, `DmShared.dNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.xNull`

### `savePriceList`
`public static void savePriceList(PriceListSave_Mdl priceListSave_Mdl, Connection cnTemp) throws Exception`

- SQL 1: `select TOP 1 imn.name, pt.name AS packagingTypeName FROM priceList pl LEFT JOIN itemNature imn ON imn.code = pl.itemNature LEFT JOIN packagingType pt ON pt.code = pl.packagingType WHERE 1 = 1 AND priceListCode = :priceListCode AND itemCode = :itemCode AND freightTypeCode = :freightTypeCode AND itemNature = :itemNature AND packagingType = :packagingType AND ((:fromBfCode BETWEEN ISNULL(fromBfCode, 0) AND ISNULL(toBfCode, 0)) OR (:toBfCode BETWEEN ISNULL(fromBfCode, 0) AND ISNULL(toBfCode, 0))) AND ((:fromGsmCode BETWEEN ISNULL(fromGsmCode, 0) AND ISNULL(toGsmCode, 0)) OR (:toGsmCode BETWEEN ISNULL(fromGsmCode, 0) AND ISNULL(toGsmCode, 0))) {…}`
- SQL 2: `DELETE plohd FROM priceListOhDetail plohd LEFT JOIN priceList pl ON pl.priceListCode = plohd.priceListCode AND pl.v_SNo = plohd.v_SNo WHERE pl.priceListCode = :priceListCode AND pl.v_Sno = :v_Sno`
- SQL 3: `DELETE pl FROM priceList pl WHERE pl.priceListCode = :priceListCode AND pl.v_Sno = :v_Sno`
- SQL 4: `Select isNull(max(v_SNo),0) + 1 As v_SNo From priceList pl WHERE pl.priceListCode = :priceListCode`
- SQL 5: `INSERT INTO priceList(entryFrom, entryFromNature, site_code, priceListCode, v_SNo, subCode, refSubCode, itemCode, unitCode, applicableFrom, applicableTo, nature, rate, cmn_Deduction,cmn_Addition, printSNo ,fromBfCode,toBfCode,fromGsmCode,toGsmCode,estdLandedRate, freightTypeCode , itemNature, packagingType) VALUES (:entryFrom, :entryFromNature, :site_code, :priceListCode, :v_SNo, :subCode, :refSubCode, :itemCode, :unitCode, :applicableFrom, :applicableTo, :nature, :rate, :cmn_Deduction, :cmn_Addition, :printSNo ,:fromBfCode,:toBfCode,:fromGsmCode,:toGsmCode,:estdLandedRate, :freightTypeCode , :itemNature, :packagingType )`
- Params: `:itemCode` ← `DmShared.xNull(ipl.getItemCode())`; `:v_Sno` ← `DmShared.iNull(ipl.getV_SNo()).intValue()`; `:priceListCode` ← `priceListSave_Mdl.getPriceListCode()`; `:fromBfCode` ← `DmShared.iNull(ipl.getBfFromCode()).intValue()`; `:freightTypeCode` ← `DmShared.iNull(ipl.getFreightTypeCode()).intValue()`; `:itemNature` ← `DmShared.iNull(ipl.getItemNatureCode()).intValue()`; `:packagingType` ← `DmShared.iNull(ipl.getPackagingTypeCode()).intValue()`; `:toBfCode` ← `DmShared.iNull(ipl.getBfToCode()).intValue()`; `:fromGsmCode` ← `DmShared.iNull(ipl.getGsmFromCode()).intValue()`; `:toGsmCode` ← `DmShared.iNull(ipl.getGsmToCode()).intValue()`; `:priceListCode` ← `priceListSave_Mdl.getPriceListCode()`; `:v_Sno` ← `DmShared.iNull(ipl.getV_SNo()).intValue()`; `:priceListCode` ← `priceListSave_Mdl.getPriceListCode()`; `:v_Sno` ← `DmShared.iNull(ipl.getV_SNo()).intValue()`; `:priceListCode` ← `priceListSave_Mdl.getPriceListCode()`; `:entryFrom` ← `STEnum.PriceListEntryFrom.PRICELIST_MASTER.getCode()`; `:site_Code` ← `DmShared.xNull(priceListSave_Mdl.getSite_Code())`; `:priceListCode` ← `DmShared.foriegnKey(priceListSave_Mdl.getPriceListCode())`; `:v_SNo` ← `DmShared.foriegnKey(ipl.getV_SNo())`; `:applicableFrom` ← `DmServer.convertDate(DmShared.dNull(priceListSave_Mdl.getApplicableFrom()))`; `:applicableTo` ← `DmServer.convertDate(DmShared.dNull(priceListSave_Mdl.getApplicableTo()))`; `:subCode` ← `DmShared.foriegnKey(ipl.getPartyCode())`; `:refSubCode` ← `DmShared.foriegnKey(ipl.getRefCustomerCode())`; `:nature` ← `DmShared.iNull(STEnum.PriceListNature.None.getId()).toString()`; `:entryFromNature` ← `DmShared.iNull(ipl.getEntryFromNature()).toString()`; `:itemCode` ← `DmShared.foriegnKey(ipl.getItemCode())`; `:unitCode` ← `DmShared.foriegnKey(ipl.getUnitCode())`; `:rate` ← `DmServer.vNull(ipl.getRate(), "0.00")`; `:cmn_Deduction` ← `DmServer.vNull(ipl.getDeductionAmount(), "0.00")`; `:cmn_Addition` ← `DmServer.vNull(ipl.getAdditionAmount(), "0.00")`; `:printSNo` ← `DmShared.foriegnKey(ipl.getPrintSNo())`; `:fromBfCode` ← `DmShared.foriegnKey(ipl.getBfFromCode(), true)`; `:toBfCode` ← `DmShared.foriegnKey(ipl.getBfToCode(), true)`; `:fromGsmCode` ← `DmShared.foriegnKey(ipl.getGsmFromCode(), true)`; `:toGsmCode` ← `DmShared.foriegnKey(ipl.getGsmToCode(), true)`; `:estdLandedRate` ← `DmServer.vNull(ipl.getEstdLandedRate(), "0.00")`; `:freightTypeCode` ← `DmShared.xNull(ipl.getFreightTypeCode())`; `:itemNature` ← `DmShared.foriegnKey(ipl.getItemNatureCode())`; `:packagingType` ← `DmShared.foriegnKey(ipl.getPackagingTypeCode())`; `:priceListCode` ← `DmShared.foriegnKey(priceListSave_Mdl.getPriceListCode())`; `:v_SNo` ← `DmShared.foriegnKey(ipl.getV_SNo())`; `:sNo` ← `DmShared.foriegnKey(gdm.getSrNo()).toString()`; `:overHeadCode` ← `DmShared.foriegnKey(gdm.getSharedKeyFieldCode())`; `:overHeadMCode` ← `DmShared.foriegnKey(gdm.getOverHeadManualCode())`; `:atRate` ← `DmServer.vNull(gdm.getAtRate(), "0.00")`; `:amount` ← `DmServer.vNull(gdm.getAmount(), "0.00")`; `:evalTypeCode` ← `DmShared.foriegnKey(gdm.getRateTypeCode())`; `:nature` ← `DmShared.foriegnKey(gdm.getNatureCode())`; `:formula` ← `DmShared.xNull(gdm.getFormula())`; `:isShowFormulaValue` ← `DmShared.iNull(gdm.getShowFormulaValueCode()).intValue()`; `:roundOff` ← `DmShared.foriegnKey(gdm.getRoundOffCode())`; `:isInventoryAffected` ← `DmShared.iNull(gdm.getIsInventoryEffectCode()).intValue()`; `:acCode` ← `DmShared.foriegnKey(gdm.getHeadAcCode())`; `:dueAcCode` ← `DmShared.foriegnKey(gdm.getHeadDueAcCode())`; `:isDummyHead` ← `gdm.isDummyHead() ? 1 : 0`
- Errors: "Invalid key!"
- Other calls: `FreightType.getViaCode`, `None.getId`, `PRICELIST_MASTER.getCode`

### `savePriceList_ExistingFunction`
`public static void savePriceList_ExistingFunction(PriceListSave_Mdl priceListSave_Mdl, Connection cnTemp, boolean flagChangePriceList) throws Exception`

- SQL 1: `SELECT Max(applicableFrom) as applicableFrom,Min(applicableFrom) AS applicableFromMin from priceList where entryFrom = :entryFrom and site_Code = :site_Code and priceListCode = :priceListCode {…}`
- SQL 2: `update priceList set applicableTo = :applicableTo where applicableFrom = :applicableFrom and entryFrom = :entryFrom and site_Code = :site_Code and priceListCode = :priceListCode {…}`
- SQL 3: `select applicableFrom,applicableTo FROM priceList where applicableFrom > :applicableFrom and entryFrom = :entryFrom and site_Code = :site_Code and priceListCode = :priceListCode {…} group by applicableFrom,applicableTo order by applicableFrom asc offset 0 rows fetch next 1 rows only`
- SQL 4: `update priceList set applicableTo = :applicableTo where applicableFrom = :applicableFrom and entryFrom = :entryFrom and site_Code = :site_Code and priceListCode = :priceListCode {…}`
- SQL 5: `select COUNT(*) cnt FROM priceList WHERE applicableFrom = :applicableFrom AND entryFrom = :entryFrom AND site_Code = :site_Code AND priceListCode = :priceListCode AND itemCode = :itemCode AND freightTypeCode = :freightTypeCode AND itemNature = :itemNature AND ((:fromBfCode BETWEEN ISNULL(fromBfCode, 0) AND ISNULL(toBfCode, 0)) OR (:toBfCode BETWEEN ISNULL(fromBfCode, 0) AND ISNULL(toBfCode, 0))) AND ((:fromGsmCode BETWEEN ISNULL(fromGsmCode, 0) AND ISNULL(toGsmCode, 0)) OR (:toGsmCode BETWEEN ISNULL(fromGsmCode, 0) AND ISNULL(toGsmCode, 0))) {…}`
- SQL 6: `DELETE plohd FROM priceListOhDetail plohd LEFT JOIN priceList pl ON pl.priceListCode = plohd.priceListCode AND pl.v_SNo = plohd.v_SNo WHERE pl.priceListCode = :priceListCode AND pl.v_Sno = :v_Sno`
- SQL 7: `DELETE pl FROM priceList pl WHERE pl.priceListCode = :priceListCode AND pl.v_Sno = :v_Sno`
- SQL 8: `Select isNull(max(v_SNo),0) + 1 As v_SNo From priceList pl WHERE pl.priceListCode = :priceListCode`
- SQL 9: `INSERT INTO priceList(entryFrom, entryFromNature, site_code, priceListCode, v_SNo, subCode, refSubCode, itemCode, unitCode, applicableFrom, applicableTo, nature, rate, cmn_Deduction,cmn_Addition, printSNo ,fromBfCode,toBfCode,fromGsmCode,toGsmCode,estdLandedRate, freightTypeCode, itemNature) VALUES (:entryFrom, :entryFromNature, :site_code, :priceListCode, :v_SNo, :subCode, :refSubCode, :itemCode, :unitCode, :applicableFrom, :applicableTo, :nature, :rate, :cmn_Deduction, :cmn_Addition, :printSNo ,:fromBfCode,:toBfCode,:fromGsmCode,:toGsmCode,:estdLandedRate, :freightTypeCode, :itemNature )`
- Params: `:entryFrom` ← `priceListSave_Mdl.getPriceListEntryFrom().getCode()`; `:site_Code` ← `priceListSave_Mdl.getSite_Code()`; `:priceListCode` ← `priceListSave_Mdl.getPriceListCode()`; `:keyCode` ← `DmShared.xNull(partyCode)`; `:keyCode` ← `DmShared.xNull(priceListSave_Mdl.getSharedKeyFieldCode())`; `:applicableFrom` ← `DmServer.convertDate(priceListSave_Mdl.getApplicableFrom())`; `:entryFrom` ← `priceListSave_Mdl.getPriceListEntryFrom().getCode()`; `:site_Code` ← `priceListSave_Mdl.getSite_Code()`; `:priceListCode` ← `priceListSave_Mdl.getPriceListCode()`; `:keyCode` ← `DmShared.xNull(partyCode)`; `:keyCode` ← `DmShared.xNull(priceListSave_Mdl.getSharedKeyFieldCode())`; `:applicableTo` ← `DmServer.convertDate(DmServer.addDate(Calendar.DATE, -1, DmShared.dNull(priceListSave_Mdl.`; `:applicableFrom` ← `rsTemp.getDate("ApplicableFrom")`; `:entryFrom` ← `priceListSave_Mdl.getPriceListEntryFrom().getCode()`; `:site_Code` ← `priceListSave_Mdl.getSite_Code()`; `:priceListCode` ← `priceListSave_Mdl.getPriceListCode()`; `:keyCode` ← `DmShared.xNull(partyCode)`; `:keyCode` ← `DmShared.xNull(priceListSave_Mdl.getSharedKeyFieldCode())`; `:applicableFrom` ← `DmServer.convertDate(DmShared.dNull(priceListSave_Mdl.getApplicableFrom()))`; `:entryFrom` ← `priceListSave_Mdl.getPriceListEntryFrom().getCode()`; `:site_Code` ← `priceListSave_Mdl.getSite_Code()`; `:priceListCode` ← `priceListSave_Mdl.getPriceListCode()`; `:keyCode` ← `DmShared.xNull(partyCode)`; `:keyCode` ← `DmShared.xNull(priceListSave_Mdl.getSharedKeyFieldCode())`; `:applicableFrom` ← `DmServer.convertDate(maxApplicableDate)`; `:applicableTo` ← `DmServer.convertDate(DmServer.addDate(Calendar.DATE, -1, DmShared.dNull(priceListSave_Mdl.`; `:entryFrom` ← `priceListSave_Mdl.getPriceListEntryFrom().getCode()`; `:site_Code` ← `priceListSave_Mdl.getSite_Code()`; `:priceListCode` ← `priceListSave_Mdl.getPriceListCode()`; `:keyCode` ← `DmShared.xNull(partyCode)`; `:keyCode` ← `DmShared.xNull(priceListSave_Mdl.getSharedKeyFieldCode())`; `:entryFrom` ← `priceListSave_Mdl.getPriceListEntryFrom().getCode()`; `:subCode` ← `DmShared.xNull(ipl.getPartyCode())`; `:refSubCode` ← `DmShared.xNull(ipl.getRefCustomerCode())`; `:site_Code` ← `DmShared.xNull(priceListSave_Mdl.getSite_Code())`; `:plCode` ← `DmShared.xNull(priceListSave_Mdl.getPriceListCode())`; `:applicableFrom` ← `DmServer.convertDate(DmShared.dNull(priceListSave_Mdl.getApplicableFrom()))`; `:entryFrom` ← `priceListSave_Mdl.getPriceListEntryFrom().getCode()`; `:site_Code` ← `priceListSave_Mdl.getSite_Code()`; `:subCode` ← `DmShared.xNull(ipl.getPartyCode())`; `:subCode` ← `priceListSave_Mdl.getSharedKeyFieldCode()`; `:itemCode` ← `DmShared.xNull(ipl.getItemCode())`; `:v_Sno` ← `DmShared.iNull(ipl.getV_SNo()).intValue()`; `:priceListCode` ← `priceListSave_Mdl.getPriceListCode()`; `:fromBfCode` ← `DmShared.iNull(ipl.getBfFromCode()).intValue()`; `:freightTypeCode` ← `DmShared.iNull(ipl.getFreightTypeCode()).intValue()`; `:itemNature` ← `DmShared.iNull(ipl.getItemNatureCode()).intValue()`; `:toBfCode` ← `DmShared.iNull(ipl.getBfToCode()).intValue()`; `:fromGsmCode` ← `DmShared.iNull(ipl.getGsmFromCode()).intValue()`; `:toGsmCode` ← `DmShared.iNull(ipl.getGsmToCode()).intValue()`; `:applicableFrom` ← `DmServer.convertDate(priceListSave_Mdl.getApplicableFrom())`; `:entryFrom` ← `priceListSave_Mdl.getPriceListEntryFrom().getCode()`; `:site_Code` ← `priceListSave_Mdl.getSite_Code()`; `:priceListCode` ← `priceListSave_Mdl.getPriceListCode()`; `:applicableFrom` ← `DmServer.convertDate(priceListSave_Mdl.getApplicableFrom())`; `:entryFrom` ← `priceListSave_Mdl.getPriceListEntryFrom().getCode()`; `:site_Code` ← `priceListSave_Mdl.getSite_Code()`; `:priceListCode` ← `priceListSave_Mdl.getPriceListCode()`; `:priceListCode` ← `priceListSave_Mdl.getPriceListCode()`; `:v_Sno` ← `DmShared.iNull(ipl.getV_SNo()).intValue()`; `:priceListCode` ← `priceListSave_Mdl.getPriceListCode()`; `:v_Sno` ← `DmShared.iNull(ipl.getV_SNo()).intValue()`; `:priceListCode` ← `priceListSave_Mdl.getPriceListCode()`; `:entryFrom` ← `priceListSave_Mdl.getPriceListEntryFrom().getCode()`; `:site_Code` ← `DmShared.xNull(priceListSave_Mdl.getSite_Code())`; `:priceListCode` ← `DmShared.foriegnKey(priceListSave_Mdl.getPriceListCode())`; `:v_SNo` ← `DmShared.foriegnKey(ipl.getV_SNo())`; `:applicableFrom` ← `DmServer.convertDate(DmShared.dNull(priceListSave_Mdl.getApplicableFrom()))`; `:applicableTo` ← `DmServer.convertDate(DmShared.dNull(applicableTo))`; `:subCode` ← `DmShared.foriegnKey(ipl.getPartyCode())`; `:refSubCode` ← `DmShared.foriegnKey(ipl.getRefCustomerCode())`; `:nature` ← `DmShared.iNull(STEnum.PriceListNature.None.getId()).toString()`; `:entryFromNature` ← `DmShared.iNull(ipl.getEntryFromNature()).toString()`; `:itemCode` ← `DmShared.foriegnKey(ipl.getItemCode())`; `:unitCode` ← `DmShared.foriegnKey(ipl.getUnitCode())`; `:rate` ← `DmServer.vNull(ipl.getRate(), "0.00")`; `:cmn_Deduction` ← `DmServer.vNull(ipl.getDeductionAmount(), "0.00")`; `:cmn_Addition` ← `DmServer.vNull(ipl.getAdditionAmount(), "0.00")`; `:printSNo` ← `DmShared.foriegnKey(ipl.getPrintSNo())`; `:fromBfCode` ← `DmShared.foriegnKey(ipl.getBfFromCode(), true)`; `:toBfCode` ← `DmShared.foriegnKey(ipl.getBfToCode(), true)`; `:fromGsmCode` ← `DmShared.foriegnKey(ipl.getGsmFromCode(), true)`; `:toGsmCode` ← `DmShared.foriegnKey(ipl.getGsmToCode(), true)`; `:estdLandedRate` ← `DmServer.vNull(ipl.getEstdLandedRate(), "0.00")`; `:freightTypeCode` ← `DmShared.xNull(ipl.getFreightTypeCode())`; `:itemNature` ← `DmShared.foriegnKey(ipl.getItemNatureCode())`; `:priceListCode` ← `DmShared.foriegnKey(priceListSave_Mdl.getPriceListCode())`; `:v_SNo` ← `DmShared.foriegnKey(ipl.getV_SNo())`; `:sNo` ← `DmShared.foriegnKey(gdm.getSrNo()).toString()`; `:overHeadCode` ← `DmShared.foriegnKey(gdm.getSharedKeyFieldCode())`
- Errors: "Price List: Entry From not found." | "'Applicable from' date should be greater than previous closed date (" | "BF/GSM Range, Freight Type & Item Nature combination already exist!" | "Invalid key!"
- Other calls: `None.getId`

### `savePriceListGUI`
`public static void savePriceListGUI(PriceListGUIHead_Mdl plGUI_Mdl, Connection cnTemp, DmRSSPrivate rssTemp) throws Exception`

- SQL 1: `INSERT INTO guipriceListHead (docId, guiConfigCode, priceListCode, v_Type, v_No, v_Prefix, v_Date, recId, recIdPrefix, applicableDate, entryFrom, entryFromNature, site_Code, subCode , preparedBy, preparedDt, preparedByIP ) VALUES (:docId, :guiConfigCode, :priceListCode, :v_Type, :v_No,:v_Prefix, :v_Date, :recId, :recIdPrefix , :applicableDate, :entryFrom, :entryFromNature, :site_Code, :subCode , :preparedBy, :preparedDt, :preparedByIP )`
- SQL 2: `UPDATE guiPriceListHead SET guiConfigCode = :guiConfigCode , u_Name = :u_Name , u_EntDt = :u_EntDt WHERE docId = :docId`
- Params: `:docId` ← `DmShared.xNull(plGUI_Mdl.getDocId())`; `:guiConfigCode` ← `DmShared.foriegnKey(plGUI_Mdl.getGuiConfigCode())`; `:priceListCode` ← `DmShared.foriegnKey(plGUI_Mdl.getPriceListCode())`; `:v_Type` ← `DmShared.foriegnKey(plGUI_Mdl.getV_Type())`; `:v_No` ← `String.valueOf(recId.getCounter())`; `:v_Prefix` ← `DmShared.foriegnKey(plGUI_Mdl.getV_Prefix())`; `:v_Date` ← `DmServer.convertDate(DmShared.dNull(plGUI_Mdl.getV_Date()))`; `:recId` ← `DmShared.xNull(recId.getRecIdValue())`; `:recIdPrefix` ← `DmShared.xNull(recId.getPrefix())`; `:applicableDate` ← `DmServer.convertDate(DmShared.dNull(plGUI_Mdl.getApplicableDate()))`; `:entryFrom` ← `DmShared.xNull(plGUI_Mdl.getEntryFrom())`; `:entryFromNature` ← `DmShared.xNull(plGUI_Mdl.getEntryFromNature())`; `:site_Code` ← `DmShared.foriegnKey(plGUI_Mdl.getSite_Code())`; `:subCode` ← `DmShared.foriegnKey(plGUI_Mdl.getSubCode())`; `:preparedBy` ← `DmServer.getSsnUserName(rssTemp)`; `:preparedDt` ← `plGUI_Mdl.getTimestamp()`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(rssTemp)`; `:docId` ← `DmShared.xNull(plGUI_Mdl.getDocId())`; `:guiConfigCode` ← `DmShared.foriegnKey(plGUI_Mdl.getGuiConfigCode())`; `:u_Name` ← `DmServer.getSsnUserName(rssTemp)`; `:u_EntDt` ← `plGUI_Mdl.getTimestamp()`
- Errors: "Invalid 'GUI Config Code'." | "Invalid key."
- Other calls: `DmGUI_Server.deleteViaConfigCode`, `DmGUI_Server.saveViaConfigCode`

### `deletePriceList`
`public static void deletePriceList(PriceListDelete_Mdl priceListDelete_Mdl, Connection cnTemp, boolean flagChangePriceList, boolean isDeleteForComplete) throws Exception`

- SQL 1: `SELECT applicableFrom,applicableTo,refSubCode FROM priceList pl WHERE pl.applicableFrom = :applicableFrom AND pl.entryFrom = :entryFrom AND pl.site_Code = :site_Code AND pl.priceListCode = :priceListCode {…} order by applicableFrom desc offset 0 rows fetch next 1 rows only`
- SQL 2: `SELECT applicableFrom,applicableTo FROM priceList WHERE applicableFrom < :applicableFrom AND entryFrom = :entryFrom AND site_Code = :site_Code AND priceListCode = :priceListCode GROUP BY applicableFrom,applicableTo ORDER BY applicableFrom DESC OFFSET 0 ROWS FETCH NEXT 1 ROWS ONLY`
- SQL 3: `UPDATE priceList set applicableTo = :applicableTo WHERE applicableFrom = :applicableFrom AND entryFrom = :entryFrom AND site_Code = :site_Code AND priceListCode = :priceListCode`
- SQL 4: `DELETE ploh FROM priceListOhDetail ploh LEFT JOIN priceList pl ON pl.priceListCode = ploh.priceListCode AND pl.v_SNo = ploh.v_SNo WHERE pl.applicableFrom = :applicableFrom AND pl.entryFrom = :entryFrom AND pl.site_Code = :site_Code AND pl.priceListCode = :priceListCode {…}`
- SQL 5: `delete pl from priceList pl where pl.applicableFrom = :applicableFrom and pl.entryFrom = :entryFrom and pl.site_Code = :site_Code and pl.priceListCode = :priceListCode {…}`
- Params: `:applicableFrom` ← `DmServer.convertDate(priceListDelete_Mdl.getApplicableFrom())`; `:applicableFrom` ← `DmServer.convertDate(DmShared.dNull(priceListDelete_Mdl.getApplicableFrom()))`; `:entryFrom` ← `priceListDelete_Mdl.getPriceListEntryFrom().getCode()`; `:site_Code` ← `DmShared.xNull(priceListDelete_Mdl.getSite_Code())`; `:priceListCode` ← `DmShared.xNull(priceListDelete_Mdl.getPriceListCode())`; `:applicableTo` ← `DmServer.convertDate(applicableTo)`; `:applicableFrom` ← `DmServer.convertDate(DmShared.dNull(rsTemp.getDate("applicableFrom")))`; `:entryFrom` ← `priceListDelete_Mdl.getPriceListEntryFrom().getCode()`; `:site_Code` ← `DmShared.xNull(priceListDelete_Mdl.getSite_Code())`; `:priceListCode` ← `DmShared.xNull(priceListDelete_Mdl.getPriceListCode())`; `:applicableFrom` ← `DmServer.convertDate(DmShared.dNull(priceListDelete_Mdl.getApplicableFrom()))`; `:v_SNo` ← `DmShared.iNull(priceListDelete_Mdl.getV_SNo()).intValue()`; `:applicableFrom` ← `DmServer.convertDate(DmShared.dNull(priceListDelete_Mdl.getApplicableFrom()))`; `:v_SNo` ← `DmShared.iNull(priceListDelete_Mdl.getV_SNo()).intValue()`
- Errors: "Price List: Entry From not found." | "Deletion of closed price list is not allowed!" | "Applicable From Date is Invalid!"

### `getParameter`
`public static void getParameter(PriceListDelete_Mdl priceListDelete_Mdl, DmPreparedStatement psTemp, boolean flagChangePriceList)`

- Params: `:keyCode` ← `DmShared.xNull(priceListDelete_Mdl.getSharedKeyFieldCode())`; `:keyCode` ← `DmShared.xNull(priceListDelete_Mdl.getSharedKeyFieldCode())`; `:fromBfCode` ← `DmShared.iNull(priceListDelete_Mdl.getBfFrom()).intValue()`; `:toBfCode` ← `DmShared.iNull(priceListDelete_Mdl.getBfTo()).intValue()`; `:fromGsmCode` ← `DmShared.iNull(priceListDelete_Mdl.getGsmFrom()).intValue()`; `:toGsmCode` ← `DmShared.iNull(priceListDelete_Mdl.getGsmTo()).intValue()`; `:entryFrom` ← `priceListDelete_Mdl.getPriceListEntryFrom().getCode()`; `:site_Code` ← `DmShared.xNull(priceListDelete_Mdl.getSite_Code())`; `:priceListCode` ← `DmShared.xNull(priceListDelete_Mdl.getPriceListCode())`

### `getItemPriceBestFit`
`public static ItemPlOh_Mdl getItemPriceBestFit(ItemPlOh_Mdl itemPriceList, STEnum.PriceListApplicableOn applicableOn, Connection cnTemp) throws DmException`


### `getItemPriceBestFit`
`public static ItemPlOh_Mdl getItemPriceBestFit(ItemPlOh_Mdl itemPriceList, STEnum.PriceListApplicableOn applicableOn, String plOhSNo, String overHeadCode, String rateEvalTypeCode, boolean flagDummyHeadScheme, Connection cnTemp) throws DmException`

- SQL 1: `SELECT pl.priceListCode, pl.v_SNo , plm.isSiteSpecific, pl.site_code, pl.freightTypeCode , pl.subCode, pl.refSubCode, plm.consignee, plm.distributor , pl.itemCode, pl.itemNature, pl.applicableFrom , pl.fromBfCode, pl.toBfCode, pl.fromGsmCode, pl.toGsmCode , pl.estdLandedRate, :date AS date , pl.rate FROM priceList pl LEFT JOIN priceListMast plm ON plm.code = pl.priceListCode WHERE plm.isActive = 1 AND pl.applicableFrom <> '1900-01-01' AND (:date BETWEEN pl.applicableFrom AND pl.applicableTo) AND pl.itemCode = :itemCode AND CASE WHEN ISNULL(plm.isSiteSpecific,0) = 1 THEN CASE WHEN pl.site_Code = :site_Code THEN 1 ELSE 0 END ELSE 1 END = 1 {…}`
- SQL 2: `SELECT ord.* FROM ( {…} ) AS ord ORDER BY ((CASE WHEN ord.site_code = '1' THEN 2 ELSE CASE WHEN ISNULL(ord.isSiteSpecific,0) = 0 THEN 1 ELSE 0 END END ) + IIF(ISNULL(ord.subCode, '') <> '', 1, 0) + IIF(ISNULL(ord.refSubCode, '') <> '', 1, 0) + IIF(ISNULL(ord.consignee, '') <> '', 1, 0) + IIF(ISNULL(ord.distributor, '') <> '', 1, 0) + IIF(ord.itemCode <> '', 1, 0) + IIF(ISNULL(ord.itemNature, '') <> '', 1, 0) + IIF(ord.freightTypeCode <> '', 1, 0) + IIF(ISNULL(ord.fromBfCode, '') <> '', 1, 0) + IIF(ISNULL(ord.toBfCode, '') <> '', 1, 0) + IIF(ISNULL(ord.fromGsmCode, '') <> '', 1, 0) + IIF(ISNULL(ord.toGsmCode, '') <> '', 1, 0)) DESC , ord.priceListCode ASC {…} OFFSET 0 ROWS FETCH NEXT 1 ROWS ONLY`
- Params: `:date` ← `DmServer.convertDate(itemPriceList.getDate())`; `:itemCode` ← `itemPriceList.getItemCode()`; `:site_Code` ← `itemPriceList.getSiteCode()`; `:nature` ← `natureCode`; `:freightTypeCode` ← `itemPriceList.getFreightType()`; `:subCode` ← `itemPriceList.getPartyCode()`; `:refSubCode` ← `itemPriceList.getRefSubCode()`; `:consignee` ← `itemPriceList.getConsignee()`; `:distributor` ← `itemPriceList.getDistributor()`; `:itemNature` ← `itemPriceList.getItemNature()`; `:packagingType` ← `itemPriceList.getPackagingType()`; `:bf` ← `DmShared.iNull(itemPriceList.getBf()).intValue()`; `:gsm` ← `DmShared.iNull(itemPriceList.getGsm()).intValue()`; `:priceListCode` ← `itemPriceList.getPriceListCode()`
- Other calls: `ItemPlOh_Mdl.cloneGrdDeduction_Mdl`

### `getGridOhDeductionDetails`
`public static List<GrdDeduction_Mdl> getGridOhDeductionDetails(String priceListCode, String plV_SNo, boolean flagShowAllNature, Connection cnTemp) throws Exception`


### `getOhDeductionDetails`
`public static GrdDeduction_Mdl getOhDeductionDetails(String priceListCode, String plV_SNo, String plOhSNo, String overHeadCode, String rateEvalTypeCode, boolean flagShowAllNature, boolean flagDummyHeadScheme, Connection cnTemp) throws Exception`


### `getGridOhDeductionDetails`
`private static List<GrdDeduction_Mdl> getGridOhDeductionDetails(String priceListCode, String plV_SNo, String plOhSNo, String overHeadCode, String rateEvalTypeCode, boolean flagShowAllNature, boolean flagDummyHeadScheme, Connection cnTemp) throws Exception`

- SQL 1: `SELECT plod.sNo ,plod.overHeadCode ,ohm.name AS overHeadName ,plod.overHeadMCode ,plod.atRate ,plod.amount ,plod.evalTypeCode ,plod.nature ,plod.formula ,plod.isShowFormulaValue ,plod.roundOff ,plod.isInventoryAffected ,plod.acCode ,sgha.name as acName ,plod.dueAcCode ,sghda.name as dueAcName ,plod.isDummyHead FROM priceListOhDetail plod LEFT JOIN overheadmast ohm on ohm.code = plod.overHeadCode LEFT JOIN subGroup sgha on sgha.subCode = plod.acCode LEFT JOIN subGroup sghda on sghda.subCode = plod.dueAcCode WHERE plod.priceListCode = :priceListCode AND plod.v_SNo = :plV_SNo {…}`
- Params: `:priceListCode` ← `DmShared.xNull(priceListCode)`; `:plV_SNo` ← `DmShared.xNull(plV_SNo)`; `:sNo` ← `DmShared.xNull(plOhSNo)`; `:overHeadCode` ← `DmShared.xNull(overHeadCode)`; `:evalTypeCode` ← `DmShared.xNull(rateEvalTypeCode)`; `:ded` ← `DmEnum.OverHeadNature.DED.getCode()`
- Other calls: `DED.getCode`, `OverHeadNature.getViaCode`, `PriceList_OH_EvalType.getViaCode`, `RoundOffNature.getViaCode`

### `getPurchasePriceListCode`
`public static KeyFieldExtended getPurchasePriceListCode(String subCode, String refSubCode, String v_Date, String siteCode, Connection cnTemp) throws Exception`

- Params: `:subCode` ← `DmShared.xNull(subCode)`; `:applicableOn` ← `STEnum.PriceListApplicableOn.PURCHASE.getId()`; `:entryFrom` ← `STEnum.PriceListEntryFrom.PRICELIST_MASTER.getCode()`; `:subCode` ← `DmShared.xNull(subCode)`; `:refSubCode` ← `DmShared.xNull(refSubCode)`; `:site_code` ← `DmShared.xNull(siteCode)`; `:vDate` ← `DmServer.convertDate(DmShared.dNull(v_Date))`
- Other calls: `PRICELIST_MASTER.getCode`, `PURCHASE.getId`

## Depends on (imports from other packages)
`dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGUI.server`, `dataman.dmToolkit.dmGUI.shared`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.overHeadLine.shared`, `paperbiz.selfToolkit.priceList.shared`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
Price list ka **common server** (master ki `priceListMaster`/`partyPriceList` aur sales/purchase use karte hain): `savePriceList` (+`_ExistingFunction`, `savePriceListGUI` dynamic fields ke saath), `deletePriceList` (active rates wali delete nahi), **`getItemPriceBestFit`** (item ke liye party/site/date ke hisaab se sabse behtar rate), `getGridOhDeductionDetails`/`getOhDeductionDetails` (overhead/deduction lines), `getPurchasePriceListCode`, `getParameter`. Tables: `priceList`, `guiPriceListHead`. Rules `pb-master/priceListMaster` me.
