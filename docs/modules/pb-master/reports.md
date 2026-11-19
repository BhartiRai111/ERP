# pb-master / reports

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/master/src/reports/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: rule: From Date is mandatory.; rule: To Date is mandatory.; rule: From Date should be less than or equal to To Date.
- Lookups/suggestions: getTxtHPriceList, getTxtHParty, getTxtHRateType, getTxtHShowDeduction, getTxtHSite, getTxtHPolicyNo, getTxtHReportType
- CSV export: no

## Files
| file | lines |
|---|---|
| client/RptInsurancePolicyRegister.java | 201 |
| client/RptPriceListRegister.java | 643 |
| server/RptInsurancePolicyRegister_SrvImpl.java | 288 |
| server/RptPriceListRegister_SrvImpl.java | 943 |
| shared/RptInsurancePolicyRegister_Srv.java | 24 |
| shared/RptInsurancePolicyRegister_SrvAsync.java | 23 |
| shared/RptInsurancePolicyRegister_Vld.java | 25 |
| shared/RptPriceListRegister_Srv.java | 32 |
| shared/RptPriceListRegister_SrvAsync.java | 34 |
| shared/RptPriceListRegister_Vid.java | 45 |
| shared/TxtHItem_Mdl.java | 69 |
| shared/TxtHParty_Mdl.java | 32 |
| shared/TxtHPolicyNo_Mdl.java | 50 |
| shared/TxtHPriceList_Mdl.java | 86 |

## Models (shared DTOs)
### TxtHItem_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | itemGroup |
| String | itemCategory |
| String | unit |
| String | unitCode |
| String | manualCode |
| String | itemType |

### TxtHPriceList_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | applicableOnCode |
| String | applicableOn |
| String | partyCode |
| String | partyName |
| String | priceListTypeTag |
| String | priceTypeName |
| String | isSiteSpecificName |

### TxtHParty_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityName |

### TxtHPolicyNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | insuranceComp |
| String | policyNo |
| String | dateFrom |
| String | dateTo |

## RPC interface
| method | params | returns |
|---|---|---|
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getTxtHPriceList | DmHelper helper, String Site, boolean blnToEndOfFile | DmResultPageing<List<TxtHPriceList_Mdl>> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHRateType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHParty | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getItemName | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getTxtHShowDeduction | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getTxtHPolicyNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHPolicyNo_Mdl>> |
| getTxtHReportType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `RptInsurancePolicyRegister_Vld`
```java
public class RptInsurancePolicyRegister_Vld {
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

## Server `RptPriceListRegister_SrvImpl.java` (943 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.processWjMessageQueue`, `DmServer.saveLog`, `DmServer.showJasper`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.xNull`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT plm.applicableOn FROM priceListMast plm WHERE plm.code = :priceListCode`
- Params: `:priceListCode` ← `DmShared.xNull(filter.get("priceListCode").getTag())`
- Errors: "Price List is not valid!" | "Report Name is not valid!"
- Other calls: `PRINT.getValue`, `PURCHASE.getId`, `RptPriceListRegister_Vid.validateInput`, `SALES.getId`, `SYSTEM_ACTIVITY.getName`

### `execute`
`private String execute(LinkedHashMap<String, FieldUDT_Mdl> filter, String partyCode, String applicableOn, String reportName, String reportTitle, Connection cnTemp) throws Exception`

- SQL 1: `SELECT cpl.* FROM ctePriceList cpl WHERE cpl.sno = 1`
- SQL 2: `SELECT MAX(vGt.responsibilityTerms) AS responsibilityTerms, MAX(vGt.priceTerms) AS priceTerms , MAX(vGt.cartageTerms) AS cartageTerms, MAX(vGt.remark) AS remark , MAX(vGt.interestTerms) AS interestTerms, MAX(vGt.paymentTerms) AS paymentTerms FROM ( SELECT gt.responsibilityTerms, gt.priceTerms, gt.cartageTerms, gt.remark , NULL AS interestTerms, NULL AS paymentTerms FROM ( SELECT pl.site_code, pl.priceListCode, pl.entryFrom, pl.applicableFrom FROM priceList pl LEFT JOIN priceListMast plm ON plm.code = pl.priceListCode Where pl.priceListCode = $P{priceListCode} AND pl.entryFrom = '1' AND pl.applicableFrom = $P{fromDate} AND CASE WHEN pl.site_Code = $P{site_code} THEN 1 ELSE CASE WHEN plm.isSiteSpecific = $P{isSiteSpecificCode} THEN 0 ELSE 1 END END = 1 GROUP BY pl.site_code, pl.priceListCode, pl.entryFrom, pl.applicableFrom ) vPl INNER JOIN guipriceListHead gplh ON gplh.priceListCode = vPl.priceListCode AND gplh.applicableDate = vPl.applicableFrom AND gplh.entryFrom = vPl.entryFrom AND gplh.site_code = vPl.site_Code INNER JOIN guiTrans gt ON gt.docId = gplh.docId AND vPl.applicableFrom = gplh.applicableDate AND vPl.entryFrom = gplh.entryFrom AND vPl.site_code = gplh.site_Code UNION ALL SELECT NULL AS responsibilityTerms, NULL AS priceTerms, NULL AS cartageTerms, NULL AS remark , gt.interestTerms, gt.paymentTerms FROM guiTrans gt WHERE gt.docId = $P{tempPartyCode} ) vGt`
- Other calls: `ADD.getCode`, `DED.getCode`, `PERCENTAGE.getCode`, `PERCENTAGE.getName`, `PER_UNIT.getCode`, `PER_UNIT.getName`, `STServer.setJasperVariables`

### `getPriceListReportQuery`
`private String getPriceListReportQuery(LinkedHashMap<String, FieldUDT_Mdl> filter, String partyCode, String applicableOn) throws Exception`

- SQL 1: `DECLARE @subCodeCsv VARCHAR(MAX); SET @subCodeCsv=' {…} partyCode {…} ';`
- SQL 2: `DECLARE @tempTable TABLE(subCode BIGINT PRIMARY KEY); {…} INSERT INTO @tempTable (subCode ) {…}`
- SQL 3: `WITH {…} ctePriceList AS ( SELECT {…} AS tempPartyCode, :asOnDate AS asOnDate , pl.priceListCode, plm.name as priceListName , plm.priceListType as priceListTypeTag ,pl.v_SNo , plt.name AS priceTypeName , CASE WHEN plm.applicableOn = :PURCHASE_CODE THEN :PURCHASE_NAME WHEN plm.applicableOn = :SALES_CODE THEN :SALES_NAME ELSE '' END AS applicableOn , IIF(plm.isSiteSpecific = 1, 'Yes', 'No') AS isSiteSpecific , plm.isSiteSpecific AS isSiteSpecificCode , plm.description , ' {…} showDeduction {…} ' AS showDeduction , refCust.displayName AS refCustomer , plm.consignee, sgc.name AS consigneeName , sgd.gsTin AS consigneeGsTin, sgd.gstinDate AS consigneeGstinDate, sgd.pan AS consigneePan , sgd.add1 AS consigneeAdd1, sgd.add2 AS consigneeAdd2, sgd.cityCode AS consigneeCityCode, cmd.cityName AS consigneeCityName , cmd.stateCode AS consigneeStateCode, smd.name AS consigneeStateName, smd.numericCode AS consigneeNumericCode , sgd.pin AS consigneePin, sgd.eMail AS consigneeEmail, sgd.mobile AS consigneeMobile , sgd.phone AS consigneePhone, sgd.areaCode AS consigneeAreaCode , plm.distributor, sgd.name AS distributerName , sgd.gsTin AS distributorGsTin, sgd.gstinDate AS distributorGstinDate, sgd.pan AS distributorPan , sgd.add1 AS distributorAdd1, sgd.add2 AS distributorAdd2, sgd.cityCode AS distributorCityCode, cmd.cityName AS distributorCityName , cmd.stateCode AS distributorStateCode, smd.name AS distributorStateName, smd.numericCode AS distributorNumericCode , sgd.pin AS distributorPin, sgd.eMail AS distributorEmail, sgd.mobile AS distributorMobile , sgd.phone AS distributorPhone, sgd.areaCode AS distributorAreaCode , sm.name AS site_Name , sgrsm.name AS rsmName , ic.name AS itemCategory , sm.siteDisplayName,CONCAT(address1,' , ', address2) AS siteAddress , sm.city AS siteCity,sm.pin AS sitePin ,sm.stateName AS siteStateName , FORMAT(sm.stateNumericCode, '00') siteStateCode , sm.country AS siteCountry ,sm.eMail AS siteEmail ,sm.mobile AS siteMobile , sm.gstin AS siteGSTIN , sm.panNo AS sitePanNo , pl.subCode as partyCode, sg.name as partyName, sg.gsTin AS partyGSTin, sg.gstinDate, sg.pan AS partyPan , sg.add1, sg.add2, sg.cityCode, cm.cityName, cm.stateCode, smp.name AS stateName, smp.numericCode, sg.pin , sg.eMail, sg.mobile AS partyMobile, sg.phone, sg.areaCode , sg.creditLimit, sg.dueDays, sg.interestRate , pl.applicableFrom as fromDate, pl.applicableTo as toDate , pl.itemCode , im.name as itemName, pl.fromBfCode, pl.toBfCode,pl.fromGsmCode, pl.toGsmCode , pl.rate, um.unit, pl.cmn_Addition, pl.cmn_Deduction, pl.estdLandedRate , pl.entryFrom , pl.site_code , CASE WHEN ISNULL(pl.freightTypeCode,0) = {…} THEN ' {…} ' WHEN ISNULL(pl.freightTypeCode,0) = {…} THEN ' {…} ' ELSE '' END AS freightTypeName, imn.name AS itemNatureName , DENSE_RANK() OVER(PARTITION BY {…} plm.name, ic.name, im.name, pl.fromBfCode, pl.toBfCode, pl.fromGsmCode, pl.toGsmCode ORDER BY {…} plm.name, ic.name, im.name, pl.fromBfCode, pl.toBfCode, pl.fromGsmCode, pl.toGsmCode {…} ) sno , pl.packagingType, pt.name as packagingTypeName FROM priceListMast plm LEFT JOIN subGroup sgc ON sgc.subCode = plm.consignee LEFT JOIN city cmc ON cmc.cityCode = sgc.cityCode LEFT JOIN stateMast smc ON smc.code = cmc.stateCode LEFT JOIN subGroup sgd ON sgd.subCode = plm.distributor LEFT JOIN city cmd ON cmd.cityCode = sgd.cityCode LEFT JOIN stateMast smd ON smd.code = cmd.stateCode LEFT JOIN priceListType plt ON plt.code = plm.priceListType {…} LEFT JOIN itemMast im ON im.code = pl.itemCode LEFT JOIN itemCat ic ON ic.code = im.itemCategoryCode LEFT JOIN unitMast um ON um.code = pl.unitCode LEFT JOIN siteMast sm ON sm.code = pl.site_code LEFT JOIN subGroup refCust ON refCust.subCode = plm.refSubCode LEFT JOIN city cm ON cm.cityCode = sg.cityCode LEFT JOIN stateMast smp ON smp.code = cm.stateCode LEFT JOIN itemNature imn ON imn.code = pl.itemNature LEFT JOIN packagingType pt ON pt.code = pl.packagingType {…} )`
- Other calls: `EX_MILL.getCode`, `EX_MILL.getValue`, `FOR.getCode`, `FOR.getValue`, `PURCHASE.getId`, `SALES.getId`

### `getApplicablePartyResultSet`
`private ResultSet getApplicablePartyResultSet(LinkedHashMap<String, FieldUDT_Mdl> filter, String partyCode, Connection cnTemp) throws Exception`

- SQL 1: `SELECT plm.priceListType, plm.applicableOn, plm.subCode AS plSubCode FROM priceListMast plm WHERE plm.code = :priceListCode`
- SQL 2: `SELECT DISTINCT cpl.tempPartyCode FROM ctePriceList cpl WHERE cpl.sno = 1`
- Params: `:priceListCode` ← `DmShared.xNull(filter.get("priceListCode").getTag())`
- Errors: "Price List is not valid!"

### `getParameter`
`private void getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp, String applicableOn) throws Exception`

- Params: `:asOnDate` ← `DmServer.convertDate(filter.get("asOnDate").getText()), true`; `:siteCode` ← `DmShared.xNull(filter.get("siteCode").getTag())`; `:priceListCode` ← `DmShared.xNull(filter.get("priceListCode").getTag())`; `:PURCHASE_CODE` ← `STEnum.PriceListApplicableOn.PURCHASE.getId()`; `:PURCHASE_NAME` ← `STEnum.PriceListApplicableOn.PURCHASE.getValue()`; `:SALES_CODE` ← `STEnum.PriceListApplicableOn.SALES.getId()`; `:SALES_NAME` ← `STEnum.PriceListApplicableOn.SALES.getValue()`; `:PBIL` ← `pvc.PURCHASE_BILL`; `:PBILU` ← `pvc.PURCHASE_BILL_UNREGISTERED`; `:PBILD` ← `pvc.PURCHASE_BILL_DIRECT`; `:PBLDU` ← `pvc.PURCHASE_BILL_DIRECT_UNREGISTERED`; `:SI` ← `pvc.SALES_INVOICE`; `:GSI` ← `pvc.GENERAL_SALES_INVOICE`; `:PRICELIST_MASTER` ← `STEnum.PriceListEntryFrom.PRICELIST_MASTER.getCode()`
- Other calls: `PRICELIST_MASTER.getCode`, `PURCHASE.getId`, `PURCHASE.getValue`, `SALES.getId`, `SALES.getValue`

### `getTxtHPriceList`
`public DmResultPageing<List<TxtHPriceList_Mdl>> getTxtHPriceList(DmHelper helper, String Site, boolean blnToEndOfFile)`

- SQL 1: `SELECT plm.code,plm.subCode as partyCode, sg.name as partyName,plm.name ,plm.applicableOn as applicableOnCode , CASE WHEN plm.applicableOn = '1' THEN 'Purchase' WHEN plm.applicableOn = '2' THEN 'Sales' ELSE '' END AS applicableOn , CASE WHEN plm.isSiteSpecific ='1' THEN 'Yes' WHEN plm.isSiteSpecific ='0' THEN 'No' ELSE '' END AS isSiteSpecific , plm.priceListType as priceListTypeTag , CASE WHEN plm.priceListType = '1' THEN 'BF' WHEN plm.priceListType = '2' THEN 'GSM' WHEN plm.priceListType = '3' THEN 'BOTH' WHEN plm.priceListType = '4' THEN 'NONE' ELSE '' END AS priceTypeName FROM priceListMast plm LEFT JOIN subGroup sg on sg.subCode = plm.subCode`

### `getTxtHParty`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHParty(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as supplierName , sg.manualCode , c.cityName FROM subGroup sg LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( SELECT DISTINCT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_Type = acf.v_Type WHERE vt.category IN (' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ') )`

### `getTxtHRateType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHRateType(DmHelper helper, boolean blnToEndOfFile)`


### `getItemName`
`public DmResultPageing<List<TxtHItem_Mdl>> getItemName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select im.code as itemCode, im.name as itemName ,im.manualCode ,it.name as itemTypeName from itemMast im LEFT JOIN unitmast um ON im.saleUnit = um.code Left join itemType it on it.code=im.itemType where im.filterItemType IN (:FM,:SM)`
- Params: `:FM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:SM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHShowDeduction`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHShowDeduction(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

## Server `RptInsurancePolicyRegister_SrvImpl.java` (288 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.saveLog`, `DmServer.showJasper`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.xNull`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT NULL AS docId , NULL AS recId , :fromDate AS v_Date, NULL AS vTypeShortName, 0 AS v_No, 0 AS v_SNo, ISNULL(SUM(pt.policyAmt),0) - ISNULL(SUM(pt.utilizedAmt) ,0) AS policyAmount, 0 AS utilizedAmt, CAST(NULL AS DATE) AS dateFrom, CAST(NULL AS DATE) AS dateTo, 'Opening Balance' partyName, NULL AS partyCityName, NULL AS distributorName, NULL AS distributorCityName, MAX(pm.name) AS policyNo , MAX(pm.insuranceComp) AS insuranceComp, pm.code AS policyCode, 1 AS isOpeningRecord FROM policyMast pm LEFT JOIN policyTrans AS pt ON pm.code = pt.policyCode LEFT JOIN voucher_Type vt ON vt.v_Type = pt.v_Type {…} AND pt.isStatus=0 GROUP BY pm.code`
- Other calls: `PRINT.getValue`, `RptInsurancePolicyRegister_Vld.validateInput`, `SYSTEM_ACTIVITY.getName`

### `getParameter`
`private void getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`

### `getTxtHPolicyNo`
`public DmResultPageing<List<TxtHPolicyNo_Mdl>> getTxtHPolicyNo(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `select code, name , insuranceComp , dateFrom, dateTo from policyMast`

### `getTxtHReportType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHReportType(DmHelper helper, boolean blnToEndOfFile)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmReportLayout.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| execute | `if (DmShared.xNull(applicableOn).trim().equalsIgnoreCase(""))` | "Price List is not valid!" |
| execute | `if (DmShared.xNull(reportName).trim().equalsIgnoreCase(""))` | "Report Name is not valid!" |
| getApplicablePartyResultSet | `if (rsTemp.next())` | "Price List is not valid!" |

## Notes (hand-written)
_No hand-written notes yet._
