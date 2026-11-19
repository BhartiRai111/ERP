# pb-sales / self

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/sales/src/self/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: transactionNatureMast, Voucher_Type
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
| client/Export.java | 543 |
| client/Sales.java | 652 |
| server/SalesDataStructure.java | 2535 |
| server/Sales_Cntx.java | 203 |
| shared/Export_Model.java | 156 |
| shared/SalesEnum.java | 69 |
| shared/Sales_Model.java | 169 |

## Server `Sales_Cntx.java` (203 lines)
### `contextInitialized`
`public void contextInitialized(ServletContextEvent sce)`


## Server `SalesDataStructure.java` (2535 lines)
Helpers used: `DmServer.executeSQL`, `DmServer.executeScalar`, `DmServer.getConnection`, `DmServer.saveVoucherType`, `DmServer.vNull`, `DmShared.vNull`

### `schemeGroupMast`
`private void schemeGroupMast(String tableName) throws Exception`


### `schemeGroupDetail`
`private void schemeGroupDetail(String tableName) throws Exception`


### `schemeMast`
`private void schemeMast(String tableName) throws Exception`


### `scheme`
`private void scheme(String tableName) throws Exception`


### `proformaInvoice`
`private void proformaInvoice(String tableName) throws Exception`


### `proformaInvoiceDetail`
`private void proformaInvoiceDetail(String tableName) throws Exception`


### `proformaInvoiceSchemeDetail`
`private void proformaInvoiceSchemeDetail(String tableName) throws Exception`


### `sOrder`
`private void sOrder(String tableName, String tableSuffix) throws Exception`


### `sOrderDetail`
`private void sOrderDetail(String tableName, String tableSuffix) throws Exception`


### `sOrderSchemeDetail`
`private void sOrderSchemeDetail(String tableName, String tableSuffix) throws Exception`


### `sODeliveryDateChangeLog`
`private void sODeliveryDateChangeLog(String tableName) throws Exception`


### `sBill`
`private void sBill(String tableName) throws Exception`


### `sBillDetail`
`private void sBillDetail(String tableName) throws Exception`


### `sBillBatchDetail`
`private void sBillBatchDetail(String tableName) throws Exception`


### `sBillSchemeDetail`
`private void sBillSchemeDetail(String tableName) throws Exception`


### `sBillTcsDetail`
`private void sBillTcsDetail(String tableName) throws Exception`


### `sChallan`
`private void sChallan(String tableName) throws Exception`


### `sChallanDetail`
`private void sChallanDetail(String tableName) throws Exception`


### `sChallanBatchDetail`
`private void sChallanBatchDetail(String tableName) throws Exception`


### `sChallanSchemeDetail`
`private void sChallanSchemeDetail(String tableName) throws Exception`


### `sChallanTcsDetail`
`private void sChallanTcsDetail(String tableName) throws Exception`


### `dmsOrder`
`private void dmsOrder(String tableName) throws Exception`


### `dmsOrderDetail`
`private void dmsOrderDetail(String tableName) throws Exception`


### `contractualSaleOrder`
`private void contractualSaleOrder(String tableName) throws Exception`


### `contractualGroupDetail`
`private void contractualGroupDetail(String tableName) throws Exception`


### `contractualItemDetail`
`private void contractualItemDetail(String tableName) throws Exception`


### `customerlist`
`private void customerlist(String tableName) throws Exception`


### `salesEnquiry`
`private void salesEnquiry(String tableName) throws Exception`


### `amendmentSOrder`
`private void amendmentSOrder(String tableName) throws Exception`


### `amendmentSOrderDetail`
`private void amendmentSOrderDetail(String tableName) throws Exception`


### `amendmentSOrderSchemeDetail`
`private void amendmentSOrderSchemeDetail(String tableName) throws Exception`


### `initilizer`
`public void initilizer() throws Exception`


### `iniTransactionNature`
`private void iniTransactionNature(Connection cnTemp) throws Exception`

- SQL 1: `Select Count(*) As Cnt From transactionNatureMast tn Where tn.shortName = ' {…} ' {…} Select isNull(max(code),0) + 1 As Cnt From transactionNatureMast tn {…} 0`
- SQL 2: `INSERT INTO transactionNatureMast (code, name, shortName, isSystemGenerated, preparedBy, preparedDt) VALUES ( {…} , 'Goods Sales', ' {…} ', 1, 'SA', getdate())`

### `iniVoucherType`
`private void iniVoucherType(Connection cnTemp) throws Exception`

- SQL 1: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 2: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule, parentVoucher) VALUES (' {…} ', ' {…} ', ' {…} ', 'Sales Order DMS', ' {…} ', 1, 'SA', 'SA', getdate(), 'Sales Order DMS',' {…} ', null)`

### `iniProcedure`
`private void iniProcedure(Connection cnTemp) throws Exception`


### `iniDmsProcedure`
`private void iniDmsProcedure(Connection cnTemp) throws Exception`

- SQL 1: `select count(*) as cnt from sysObjects where Name = 'osrDms_Order' and xType = 'P' {…} CREATE PROCEDURE [dbo].[osrDms_Order] @fieldUDT dbo.fieldUDT READONLY AS BEGIN SET NOCOUNT ON DECLARE @sqlQuery VARCHAR(max) DECLARE @docId VARCHAR(30) SELECT @docId = fieldTag FROM @fieldUDT WHERE fieldCode = 'docId' if @docId is null set @docId = 0 SET @sqlQuery = 'SELECT so.docId , so.v_Type , vt.description as voucherName, vt.category AS voucherCategory , so.v_No , so.recId , so.recIdPrefix , so.v_Prefix , so.v_Date , so.site_Code, so.wtaCode , so.partyCode , so.distributor , so.oh_Amt_Gross , so.consignee , so.deliveryTo , sod.deliveryDt , dc.cityName As deliveryCity , so.itemNature , so.remark , so.preparedBy , so.preparedDt , so.u_Name , so.u_EntDt , so.approvedBy , so.approvalDateTime , sod.oh_Amt_Discount , sod.v_SNo , sod.itemCode , im.name as itemName , sod.itemDesc , (ISNULL(sod.oh_Amt_CGST,0)+ISNULL(sod.oh_Amt_SGST,0)+ISNULL(sod.oh_Amt_IGST,0)+ISNULL(sod.oh_Amt_UGST,0)) AS taxAmountLine , im.itsBasedOn , sod.isStatus , ib.bfCode , sod.unitCode as itemUnitCode , um1.unit as itemUnitName , um.unit as sizeUnitName , um.decimalPlaces , ib.grain , sod.quantity , sod.rate , sod.skuQty , sod.tl_ExcessPer , sod.tl_ExcessQty , sod.tl_ShortagePer , sod.serviceEvalRate , tl_ShortageQty , sod.oh_Amt_Taxable , sod.oh_Amt_CGST , sod.oh_Amt_SGST , sod.oh_Amt_IGST , sod.oh_Amt_UGST , sod.oh_Amt_Net , ISNULL( so.oh_Amt_Net ,0) as headerNetAmt , sod.remark , so.oh_Amt_SubTotal , concat(sgp.add1, sgp.add2) as partyAddress , sgp.gsTin as partyGSTin , sgp.dueDays as paymentDays , sgp.pan as partyPan , sgp.name as partyName , sgp.mobile AS partyMobile , sgp.email AS partyEmail , sgp.pin AS partyPinCode , cParty.cityName as partyCityName , sm1.name as partyStateName , FORMAT(sm1.numericCode, ''00'') as partyNumericCode , sgd.name as distributorName , concat(sgd.add1, sgd.add2) as distributorAddress , sgd.pan as distributorPan , sgd.gsTin as distributorGSTin , sgd.mobile AS distributorMobile , sgd.email AS distributorEmail , sgd.pin AS distributorPinCode , c2.cityName as distributorCityName , sm2.name as distributorStateName , FORMAT(sm2.numericCode, ''00'') as distributorNumericCode , sgc.name as consigneeName , concat(sgc.add1, sgc.add2) as consigneeAddress , sgc.gsTin as consigneeGSTin , sgc.pan as consigneePan , sgc.mobile AS consigneeMobile , sgc.email AS consigneeEmail , c3.cityName as consigneeCityName , sm3.name as consigneeStateName , FORMAT(sm3.numericCode, ''00'') as consigneeNumericCode , concat(sgdt.add1, sgdt.add2) as deliveryToAddress , sgdt.gsTin as deliveryTogsTin , sgdt.pan as deliveryToPan , sgdt.name as deliveryToName , sgdt.mobile AS deliveryToMobile , sgdt.email AS deliveryToEmail , sgdt.pin AS deliveryToPin , c4.cityName as deliveryToCityName , sm4.name as deliveryToStateName , FORMAT(sm4.numericCode, ''00'') as deliveryToNumericCode , sim.name as siteName , concat(sim.address1, sim.address2) as siteAddress , sim.city as siteCity , sim.pin as sitePin , sim.stateName as siteStateName , FORMAT(sim.stateNumericCode, ''00'') as siteStateNumericCode , sim.gstin as siteGstin , sim.panNo as sitePan , sim.eMail as siteEmail , sim.phone as siteMobile , hm.hsn as hsnName , ib.propertyDispCsv , um2.unit as cessNaUnitName , ISNULL(sod.oh_Amt_CessNa, 0) AS oh_Amt_CessNa , ISNULL(sod.oh_At_CessNa, 0) AS oh_At_CessNa , ISNULL(sod.oh_Amt_Cess, 0) AS oh_Amt_Cess , ISNULL(sod.oh_At_Cess, 0) AS oh_At_Cess , ISNULL(sod.oh_Amt_Gross ,0) as line_Oh_Amt_Gross , lm.name as locationName, so.partyPLCode, so.isItc , sod.packCount , psm.name as packSizeName , umpsm.unit as packUnitName , sod.isFooter , so.site_Code as siteCode , so.verifiedBy, so.verifiedDt from dmsOrder so LEFT JOIN voucher_Type vt ON vt.v_Type = so.v_Type LEFT JOIN dmsOrderDetail sod ON sod.docId = so.docId LEFT JOIN subGroup sgp ON sgp.subCode = so.partyCode LEFT JOIN subGroup sgd ON sgd.subCode = so.distributor LEFT JOIN subGroup sgc ON sgc.subCode = so.consignee LEFT JOIN subGroup sgdt`
- SQL 2: `select count(*) as cnt from sysObjects where Name = 'osrDms_OrderOH' and xType = 'P' {…} CREATE PROCEDURE [dbo].[osrDms_OrderOH] @fieldUDT dbo.fieldUDT READONLY as begin set nocount on declare @sqlQuery varchar(max) declare @docId varchar(30) select @docId = fieldTag from @fieldUDT where fieldCode = 'docId' if @docId is null set @docId = 0 set @sqlQuery = 'select so.docId , so.oh_Amt_GrossGoods , so.oh_Amt_GrossService , so.oh_Amt_Discount , so.oh_Amt_TaxableService , so.oh_Amt_TaxableGoods , so.oh_Amt_Taxable , so.oh_Amt_SGST , so.oh_Amt_UGST , so.oh_Amt_CGST , so.oh_Amt_IGST , so.oh_Amt_Cess , so.oh_Amt_CessNa , so.oh_Amt_SubTotal , so.oh_Amt_Total , so.oh_Amt_RoundOff , so.oh_Amt_Net ,vSc.service1_Caption ,vSc.service2_Caption ,vSc.service3_Caption ,vSc.service4_Caption ,vSc.service5_Caption ,vSa.service1_Amt ,vSa.service2_Amt ,vSa.service3_Amt ,vSa.service4_Amt ,vSa.service5_Amt FROM dmsOrder so LEFT JOIN ( SELECT t.docId , MAX(t.service1_Amt) AS service1_Amt , MAX(t.service2_Amt) AS service2_Amt , MAX(t.service3_Amt) AS service3_Amt , MAX(t.service4_Amt) AS service4_Amt , MAX(t.service5_Amt) AS service5_Amt FROM ( SELECT line.docId , CASE WHEN smf.name = ''service1'' THEN line.oh_Amt_Gross END AS service1_Amt , CASE WHEN smf.name = ''Service2'' THEN line.oh_Amt_Gross END AS service2_Amt , CASE WHEN smf.name = ''Service3'' THEN line.oh_Amt_Gross END AS service3_Amt , CASE WHEN smf.name = ''Service4'' THEN line.oh_Amt_Gross END AS service4_Amt , CASE WHEN smf.name = ''Service5'' THEN line.oh_Amt_Gross END AS service5_Amt FROM dmsOrderDetail line LEFT JOIN itemMast im ON im.code = line.itemCode LEFT JOIN serviceMappingField smf ON im.smfCode = smf.code LEFT JOIN itemType it ON it.code = im.itemType WHERE line.isFooter = 2 AND it.manualCode = ''SV'' AND line.docId = '+ @docId +' ) t GROUP BY t.docId ) vSa ON vSa.docId = so.docId LEFT JOIN ( SELECT MAX(t.service1_Caption) AS service1_Caption , MAX(t.service2_Caption) AS service2_Caption , MAX(t.service3_Caption) AS service3_Caption , MAX(t.service4_Caption) AS service4_Caption , MAX(t.service5_Caption) AS service5_Caption FROM ( SELECT CASE WHEN smf.name = ''Service1'' THEN im.name END AS service1_Caption , CASE WHEN smf.name = ''Service2'' THEN im.name END AS service2_Caption , CASE WHEN smf.name = ''Service3'' THEN im.name END AS service3_Caption , CASE WHEN smf.name = ''Service4'' THEN im.name END AS service4_Caption , CASE WHEN smf.name = ''Service5'' THEN im.name END AS service5_Caption FROM serviceMappingField smf LEFT JOIN itemMast im ON im.smfCode = smf.code LEFT JOIN itemType it ON it.code = im.itemType WHERE it.manualCode = ''SV'' AND im.usedIn IN (2,3) ) t ) vSc ON 1 = 1 WHERE so.docId= ' + @docId exec (@sqlQuery) RETURN end`
- SQL 3: `select count(*) as cnt from sysObjects where Name = 'osrDms_OrderTax' and xType = 'P' {…} CREATE PROCEDURE [dbo].[osrDms_OrderTax] @fieldUDT dbo.fieldUDT READONLY as begin set nocount on declare @sqlQuery varchar(max) declare @docId varchar(30) select @docId = fieldTag from @fieldUDT where fieldCode = 'docId' if @docId is null set @docId = 0 set @sqlQuery = 'SELECT Max( sod.docId ) as docId , (ISNULL(sod.oh_At_SGST,0) + ISNULL(sod.oh_At_UGST,0) + ISNULL(sod.oh_At_CGST,0) + ISNULL(sod.oh_At_IGST,0)) AS taxRate , SUM(ISNULL(sod.oh_Amt_Taxable,0)) AS taxableAmount , SUM(ISNULL(sod.oh_Amt_SGST,0)) AS SGST , SUM(ISNULL(sod.oh_Amt_UGST,0)) AS UGST , SUM( ISNULL(sod.oh_Amt_CGST,0)) AS CGST , SUM(ISNULL(sod.oh_Amt_IGST,0)) AS IGST , SUM(ISNULL(sod.oh_Amt_Cess,0)) AS Cess , SUM(ISNULL(sod.oh_Amt_CessNa,0)) AS CessNa , SUM(isNull(sod.oh_Amt_SGST,0)+isNull(sod.oh_Amt_CGST,0) + isNull(sod.oh_Amt_UGST,0) +isNull(sod.oh_Amt_IGST,0)+ ISNULL(sod.oh_Amt_Cess,0) + ISNULL(sod.oh_Amt_CessNa,0) ) as totalTaxAmount FROM dmsOrderDetail AS sod WHERE sod.docid= '+@docId +' GROUP BY (ISNULL(sod.oh_At_SGST,0) + ISNULL(sod.oh_At_UGST,0) + ISNULL(sod.oh_At_CGST,0) + ISNULL(sod.oh_At_IGST,0)) ' exec (@sqlQuery) RETURN end`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmRefreshDataStructure.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmMenuBar.client`, `dataman.dmToolkit.dmMenuItem.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.self.server`, `dataman.dmToolkit.self.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.sales.changeSODeliveryDate.client`, `paperbiz.sales.changeSODeliveryDate.server`, `paperbiz.sales.displaySetting.client`, `paperbiz.sales.displaySetting.server`, `paperbiz.sales.pendingSaleOrderDisplay.client`, `paperbiz.sales.pendingSaleOrderDisplay.server`, `paperbiz.sales.proformaInvoice.client`, `paperbiz.sales.proformaInvoice.server`, `paperbiz.sales.reports.client`, `paperbiz.sales.reports.server`, `paperbiz.sales.salesChallan.client`, `paperbiz.sales.salesChallan.server`, `paperbiz.sales.salesDetailDisplay.client`, `paperbiz.sales.salesDetailDisplay.server`, `paperbiz.sales.salesEnquiry.client`, `paperbiz.sales.salesEnquiry.server`, `paperbiz.sales.salesInvoice.client`, `paperbiz.sales.salesInvoice.server`, `paperbiz.sales.salesInvoiceUnstockable.client`, `paperbiz.sales.salesInvoiceUnstockable.server`, `paperbiz.sales.salesOrder.client`, `paperbiz.sales.salesOrder.server`, `paperbiz.sales.salesReturn.client`, `paperbiz.sales.salesReturn.server`, `paperbiz.sales.salesSummaryDisplay.client`, `paperbiz.sales.salesSummaryDisplay.server`, `paperbiz.selfToolkit.generalSalesInvoiceApproval.client`, `paperbiz.selfToolkit.generalSalesOrderApproval.client`, `paperbiz.selfToolkit.generalSalesReturnApproval.client`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
