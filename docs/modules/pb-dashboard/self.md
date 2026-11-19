# pb-dashboard / self

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/dashBoard/src/self/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHAccountGroup, getTxtHAccountName
- CSV export: no

## Files
| file | lines |
|---|---|
| client/DashBoard.java | 1100 |
| client/DashBoard_Client.java | 23 |
| server/DashBoard_Cntx.java | 237 |
| server/DashBoard_Server.java | 219 |
| shared/AccountGroupBalance_Mdl.java | 58 |
| shared/DashBoard_Model.java | 146 |
| shared/Ledger_Model.java | 44 |

## Models (shared DTOs)
### AccountGroupBalance_Mdl
| type | field |
|---|---|
| String | acGroupCode |
| String | acGroupName |
| String | acCode |
| String | siteCode |
| String | asOnDate |

## Server `DashBoard_Server.java` (219 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getParamList`, `DmServer.getSsnSiteCode`, `DmServer.getSsnVoucherPrefix`, `DmShared.dNull`, `DmShared.xNull`

### `getAccountGroupBalance`
`public static DmPreparedStatement getAccountGroupBalance(AccountGroupBalance_Mdl groupBalanaceMdl, Connection cnTemp, DmRSSPrivate rssPrivate) throws Exception`

- SQL 1: `SELECT SUM(ISNULL(vLg.amtDr,0)-ISNULL(vLg.amtCr,0)) AS balanceAmt FROM ( SELECT ag.groupCode, ag.groupCode AS childGroupCode, ag.groupName FROM acGroup ag WHERE ag.groupCode = :groupCode UNION SELECT ag.groupCode, agc.groupCode AS childGroupCode, ag.groupName FROM acGroup ag LEFT JOIN acGroup agc ON CHARINDEX(ISNULL(ag.mainStreamCode, ''), ISNULL(agc.mainStreamCode,''),1) > 0 WHERE ag.groupUnder = :groupCode ) vAg LEFT JOIN ( SELECT lg.subcode, MAX(sg.name) AS acName, SUM(lg.amtDr) AS amtDr, SUM(lg.amtCr) AS amtCr , CASE WHEN MAX(ag.groupNature) IN (1,2,3) AND (ISNULL(SUM(lg.amtDr),0) - ISNULL(SUM(lg.amtCr), 0)) > 0 THEN MAX(sg.groupCode) WHEN MAX(ag.groupNature) IN (0,4,5) AND (ISNULL(SUM(lg.amtDr),0) - ISNULL(SUM(lg.amtCr), 0)) < 0 THEN MAX(sg.groupCode) WHEN MAX(ag.groupNature) IN (1,2,3) AND (ISNULL(SUM(lg.amtDr),0) - ISNULL(SUM(lg.amtCr), 0)) < 0 THEN CASE WHEN MAX(sg.acGroupCr) IS NOT NULL THEN MAX(sg.acGroupCr) ELSE MAX(sg.groupCode) END WHEN MAX(ag.groupNature) IN (0,4,5) AND (ISNULL(SUM(lg.amtDr),0) - ISNULL(SUM(lg.amtCr), 0)) > 0 THEN CASE WHEN MAX(sg.acGroupDr) IS NOT NULL THEN MAX(sg.acGroupDr) ELSE MAX(sg.groupCode) END ELSE MAX(sg.groupCode) END groupCode FROM ledger lg LEFT JOIN subGroup sg ON sg.subcode = lg.subCode LEFT JOIN acGroup ag ON ag.groupCode = sg.groupCode WHERE lg.isPosted = 1 {…} GROUP BY lg.subcode ) vLg ON vLg.groupCode = vAg.childGroupCode HAVING MAX(CASE WHEN vAg.groupCode = :groupCode AND vLg.subCode IS NULL THEN 0 ELSE 1 END) = 1 {…} ORDER BY MAX(CASE WHEN vAg.groupCode = :groupCode THEN CONCAT('1',vLg.acName) ELSE CONCAT('2',vAg.groupName) END )`
- Params: `:asOnDate` ← `DmServer.convertDate(DmShared.dNull(groupBalanaceMdl.getAsOnDate()))`; `:groupCode` ← `DmShared.xNull(groupBalanaceMdl.getAcGroupCode())`; `:groupCode` ← `DmShared.xNull(DmServer.getCodeViaManualCode(DmShared.xNull(groupBalanaceMdl.getAcGroupNam`
- Errors: "groupCode or groupName is mandatory!"

### `getTxtHAccountGroup`
`public static DmResultPageing<List<KeyFieldExtended>> getTxtHAccountGroup(String mainAcGroupCode, DmHelper helper, boolean blnToEndOfFile, Connection cnTemp, DmRSSPrivate rss)`

- SQL 1: `SELECT agc.groupCode, agc.groupName , sgn.name AS nature FROM acGroup ag INNER JOIN acGroup agc ON CHARINDEX(ISNULL(ag.mainStreamCode, ''), ISNULL(agc.mainStreamCode,''),1) > 0 LEFT JOIN subGroupNature sgn ON sgn.code = ag.nature WHERE ag.groupCode IN ( {…} :groupCode {…} )`

### `getTxtHAccountName`
`public static DmResultPageing<List<Ledger_Model.TxtHAccountName>> getTxtHAccountName(String mainAcGroupCode, DmHelper helper, boolean blnToEndOfFile, Connection cnTemp, DmRSSPrivate rss)`

- SQL 1: `Select SG.SubCode,SG.Name,Sg.ManualCode,isNull(CT.CityName,'') as City,AG.GroupName, ISNULL(zm.name,'') as Zone From SubGroup SG INNER JOIN ( SELECT agc.groupCode, agc.groupName FROM acGroup ag INNER JOIN acGroup agc ON CHARINDEX(ISNULL(ag.mainStreamCode, ''), ISNULL(agc.mainStreamCode,''),1) > 0 WHERE ag.groupCode IN ( {…} :groupCode {…} ) ) ag ON sg.groupCode = ag.groupCode Left Join City CT On SG.CityCode=CT.CityCode Left Join ZoneMast ZM On ZM.Code=SG.Zone`
- Other calls: `Ledger_Model.TxtHAccountName`

## Server `DashBoard_Cntx.java` (237 lines)
### `contextInitialized`
`public void contextInitialized(ServletContextEvent sce)`


## Depends on (imports from other packages)
`dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmMenuBar.client`, `dataman.dmToolkit.dmMenuItem.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.gstVoucherEntryApproval.client`, `dataman.dmToolkit.voucherEntryApproval.client`, `paperbiz.dashBoard.dataModificationLog.client`, `paperbiz.dashBoard.dataModificationLog.server`, `paperbiz.dashBoard.dispatchInstruction.client`, `paperbiz.dashBoard.dispatchInstruction.server`, `paperbiz.dashBoard.enquiry.client`, `paperbiz.dashBoard.enquiry.server`, `paperbiz.dashBoard.favorite.client`, `paperbiz.dashBoard.favorite.server`, `paperbiz.dashBoard.finalWeighment.client`, `paperbiz.dashBoard.finalWeighment.server`, `paperbiz.dashBoard.financeDashBoard.client`, `paperbiz.dashBoard.financeDashBoard.server`, `paperbiz.dashBoard.firstWeighment.client`, `paperbiz.dashBoard.firstWeighment.server`, `paperbiz.dashBoard.gateIn.client`, `paperbiz.dashBoard.gateIn.server`, `paperbiz.dashBoard.generalGrn.client`, `paperbiz.dashBoard.generalGrn.server`, `paperbiz.dashBoard.generalPurchaseBill.client`, `paperbiz.dashBoard.generalPurchaseBill.server`, `paperbiz.dashBoard.generalPurchaseOrder.client`, `paperbiz.dashBoard.generalPurchaseOrder.server`, `paperbiz.dashBoard.generalSalesOrder.client`, `paperbiz.dashBoard.generalSalesOrder.server`, `paperbiz.dashBoard.grn.client`, `paperbiz.dashBoard.grn.server`, `paperbiz.dashBoard.indent.client`, `paperbiz.dashBoard.indent.server`, `paperbiz.dashBoard.inventoryDashBoard.client`, `paperbiz.dashBoard.inventoryDashBoard.server`, `paperbiz.dashBoard.masterWorkOrder.client`, `paperbiz.dashBoard.masterWorkOrder.server`, `paperbiz.dashBoard.pBillDisplay.client`, `paperbiz.dashBoard.pBillDisplay.server`, `paperbiz.dashBoard.packingList.client`, `paperbiz.dashBoard.packingList.server`, `paperbiz.dashBoard.partyFutureOutstandingDisplay.client`, `paperbiz.dashBoard.partyFutureOutstandingDisplay.server`, `paperbiz.dashBoard.paymentRealizationDisplay.client`, `paperbiz.dashBoard.paymentRealizationDisplay.server`, `paperbiz.dashBoard.productionDispatchDisplay.client`, `paperbiz.dashBoard.productionDispatchDisplay.server`, `paperbiz.dashBoard.productionOrder.client`, `paperbiz.dashBoard.productionOrder.server`, `paperbiz.dashBoard.proformaInvoice.client`, `paperbiz.dashBoard.proformaInvoice.server`, `paperbiz.dashBoard.purchaseBill.client`, `paperbiz.dashBoard.purchaseBill.server`, `paperbiz.dashBoard.purchaseDashBoard.client`, `paperbiz.dashBoard.purchaseDashBoard.server`, `paperbiz.dashBoard.purchaseOrder.client`, `paperbiz.dashBoard.purchaseOrder.server`, `paperbiz.dashBoard.quotation.client`, `paperbiz.dashBoard.quotation.server`, `paperbiz.dashBoard.requisition.client`, `paperbiz.dashBoard.requisition.server`, `paperbiz.dashBoard.salesDashBoard.client`, `paperbiz.dashBoard.salesDashBoard.server`, `paperbiz.dashBoard.salesInvoiceDisplay.client`, `paperbiz.dashBoard.salesInvoiceDisplay.server`, `paperbiz.dashBoard.salesOrder.client`, `paperbiz.dashBoard.salesOrder.server`, `paperbiz.dashBoard.self.shared`, `paperbiz.dashBoard.storeSample.client`, `paperbiz.dashBoard.storeSample.server`, `paperbiz.dashBoard.todaysExpenses.client`, `paperbiz.dashBoard.todaysExpenses.server`, `paperbiz.dashBoard.unloading.client`, `paperbiz.dashBoard.unloading.server`, `paperbiz.dashBoard.visitorGatePass.client`, `paperbiz.dashBoard.visitorGatePass.server`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.dispatchInstructionApproval.client`, `paperbiz.selfToolkit.enquiryApproval.client`, `paperbiz.selfToolkit.finishedItemActivation.client`, `paperbiz.selfToolkit.generalGrnApproval.client`, `paperbiz.selfToolkit.generalPurchaseBillApproval.client`, `paperbiz.selfToolkit.generalPurchaseOrderApproval.client`, `paperbiz.selfToolkit.generalPurchaseReturnApproval.client`, `paperbiz.selfToolkit.generalSalesInvoiceApproval.client`, `paperbiz.selfToolkit.generalSalesOrderApproval.client`, `paperbiz.selfToolkit.generalSalesReturnApproval.client`, `paperbiz.selfToolkit.grnApproval.client`, `paperbiz.selfToolkit.indentApproval.client`, `paperbiz.selfToolkit.itemActivation.client`, `paperbiz.selfToolkit.labApproval.client`, `paperbiz.selfToolkit.orderApproval.client`, `paperbiz.selfToolkit.packingListApproval.client`, `paperbiz.selfToolkit.proformaInvoiceApproval.client`, `paperbiz.selfToolkit.purchaseBillApproval.client`, `paperbiz.selfToolkit.purchaseOrderApproval.client`, `paperbiz.selfToolkit.purchaseReturnApproval.client`, `paperbiz.selfToolkit.requisitionApproval.client`, `paperbiz.selfToolkit.salesInvoiceApproval.client`, `paperbiz.selfToolkit.salesReturnApproval.client`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
