# pb-self-toolkit / salesScheme

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/salesScheme/`). Hand-written notes are in the "Notes" section.

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
| server/SalesScheme_Server.java | 444 |
| shared/SchemeGroupItem_Mdl.java | 76 |
| shared/SchemeParamBase_Mdl.java | 104 |
| shared/SchemeParam_Mdl.java | 58 |
| shared/Scheme_Mdl.java | 335 |

## Models (shared DTOs)
### Scheme_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | schemeCategoryCode |
| String | schemeCategoryName |
| String | itemCategoryCode |
| String | itemCategoryName |
| String | itemCode |
| String | itemName |
| String | schemeGroupCode |
| String | schemeGroupName |
| String | unitCode |
| String | unitName |
| String | rateFrom |
| String | rateTo |
| String | amountFrom |
| String | amountTo |
| String | qtyFrom |
| String | qtyTo |
| String | interval |
| String | discountTypeCode |
| String | discountTypeName |
| String | atRate |
| String | amount |
| String | tempMixDiscountPer |
| String | tempMixDiscountAmt |
| String | categoryUnitMixQty |
| String | categoryUnitMixAmount |
| String | categoryMixAmount |
| String | itemUnitMixQty |
| String | itemUnitMixAmount |
| String | itemMixAmount |
| String | schemeGroupUnitMixQty |
| String | schemeGroupUnitMixAmount |
| String | schemeGroupMixAmount |
| String | itemCodeCsv |
| String | itemNameCsv |
| boolean | processed |

### SchemeGroupItem_Mdl
| type | field |
|---|---|
| long | itemCode |
| long | schemeGroupCode |
| int | unitCode |
| String | itemCodeCsv |
| double | schemeGroupUnitMixQty |
| double | schemeGroupUnitMixAmount |
| double | schemeGroupMixAmount |

### SchemeParam_Mdl
| type | field |
|---|---|
| SchemeParamBase_Mdl | spbm |
| double | quantity |
| double | rate |
| double | amount |
| int | rowIndex |

### SchemeParamBase_Mdl
| type | field |
|---|---|
| int | itemCategoryCode |
| long | itemCode |
| int | unitCode |
| double | categoryUnitMixQty |
| double | categoryUnitMixAmount |
| double | categoryMixAmount |
| double | itemUnitMixQty |
| double | itemUnitMixAmount |
| double | itemMixAmount |
| List<SchemeGroupItem_Mdl> | sgims |

## Server `SalesScheme_Server.java` (444 lines)
Helpers used: `DmServer.convertDate`, `DmServer.getCodeViaManualCode`, `DmServer.getParamList`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getApplicableScheme`
`public static List<Scheme_Mdl> getApplicableScheme(String site_Code, String billDate, String partyCode, String schemeType, List<SchemeParam_Mdl> lstParam, Connection cnTemp) throws Exception`

- SQL 1: `SELECT s.schemeCode, sm.name AS schemeName, sm.category , s.itemCode, s.itemCategoryCode, s.unitCode , s.rateFrom, s.rateTo, s.amountFrom, s.amountTo, s.qtyFrom, s.qtyTo , s.discountType, s.atRate, s.amount , s.interval, s.schemeGroupCode, sgm.name AS schemeGroupName FROM scheme s LEFT JOIN schemeMast sm ON sm.code = s.schemeCode LEFT JOIN schemeGroupMast sgm ON sgm.code = s.schemeGroupCode LEFT JOIN schemeGroupDetail sgd ON sgd.code = sgm.code LEFT JOIN itemMast im ON CASE WHEN s.itemCode IS NULL THEN CASE WHEN s.schemeGroupCode IS NULL THEN CASE WHEN im.itemCategoryCode = s.itemCategoryCode THEN 1 ELSE 2 END ELSE CASE WHEN im.code = sgd.itemCode THEN 1 ELSE 2 END END ELSE CASE WHEN im.code = s.itemCode THEN 1 ELSE 2 END END = 1`
- SQL 2: `SELECT vS.*, im.name AS itemName, ic.name AS itemCategoryName, um.unit AS unitName FROM ( {…} ) vS LEFT JOIN itemMast im ON im.code = vs.itemCode LEFT JOIN itemCat ic ON ic.code = vs.itemCategoryCode LEFT JOIN unitMast um ON um.code = vS.unitCode`
- Params: `:site_Code` ← `DmShared.xNull(site_Code)`; `:billDate` ← `DmServer.convertDate(billDate)`; `:schemeType` ← `DmShared.xNull(schemeType)`; `:partyCode` ← `DmShared.xNull(partyCode)`; `:schemeMaster` ← `String.valueOf(STEnum.SchemeEntryFrom.SCHEME_MASTER.toInt())`; `:partyScheme` ← `String.valueOf(STEnum.SchemeEntryFrom.PARTY_SCHEME.toInt())`; `:itemScheme` ← `STEnum.SchemeCategory.ITEM_SCHEME.getCode()`
- Other calls: `ITEM_SCHEME.getCode`, `PARTY_SCHEME.toInt`, `SCHEME_MASTER.toInt`, `SchemeCategory.getViaKey`, `SchemeDiscountType.getViaKey`

### `processSchemeParam`
`public static void processSchemeParam(List<SchemeParam_Mdl> lstParam, Connection cnTemp) throws Exception`

- SQL 1: `Declare @Temp Table ( itemCategoryCode INT , itemCode BIGINT , unitCode INT , quantity DECIMAL(18,7) , rate float , amount float , rowIndex INT)`
- SQL 2: `Insert Into @Temp Values( {…} , {…} , {…} , {…} , {…} , {…} , {…} )`
- SQL 3: `Select t.itemCode, t.unitCode , SUM(t.amount) as totalAmount , SUM(t.quantity) as totalQty From @Temp AS t GROUP BY t.itemCode, t.unitCode`
- SQL 4: `Select t.itemCode , SUM(t.amount) as totalAmount From @Temp AS t GROUP BY t.itemCode`
- SQL 5: `Select t.itemCategoryCode, t.unitCode , SUM(t.amount) as totalAmount , SUM(t.quantity) as totalQty From @Temp AS t GROUP BY t.itemCategoryCode, t.unitCode`
- SQL 6: `Select t.itemCategoryCode , SUM(t.amount) as totalAmount From @Temp AS t GROUP BY t.itemCategoryCode`
- SQL 7: `SELECT DISTINCT t.itemCode, t.unitCode, sgd.code AS schemeGroupCode From @Temp AS t LEFT JOIN schemeGroupDetail AS sgd ON sgd.itemCode = t.itemCode WHERE sgd.code IS NOT NULL ORDER BY sgd.code`
- SQL 8: `SELECT STRING_AGG(CONVERT(NVARCHAR(max),sgd.itemCode), ',') AS csv FROM schemeGroupDetail sgd WHERE sgd.code = :code`
- SQL 9: `Select SUM(t.amount) as totalAmount , SUM(t.quantity) as totalQty From @Temp AS t WHERE t.itemCode IN( {…} :itemCode {…} ) AND t.unitCode = :unitCode`
- SQL 10: `Select SUM(t.amount) as totalAmount From @Temp AS t WHERE t.itemCode IN( {…} :itemCode {…} )`
- Params: `:code` ← `sgim.getSchemeGroupCode()`; `:unitCode` ← `sgim.getUnitCode()`
- Errors: "Item category is missing for some items."

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `paperbiz.selfToolkit.salesScheme.shared`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
