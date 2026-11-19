# pb-purchase / purchaseDetailDisplay

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/purchase/src/purchaseDetailDisplay/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: -
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `PurchaseDetailDisplay`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmTextBox | `txtSearchValue` | `100, false` |
| 2 | - | DmGridPaging | `grdDisplay` | `GrdSalesDetail_PrpAcs, 20` |

**Grid / popup columns** (header, width, db field):
- `grdDisplay`: Voucher No. [Text, w120, recId]; Type [Text, w80, short_name]; Date [Text, w80, replace(convert(varchar, v_Date, 106), ' ','-')]; Bill No. [Text, w120, partyBillNo]; Bill Date [Text, w80, replace(convert(varchar, partyBillDate, 106), ' ','-')]; Supplier [Text, w440, supplier]; State [Text, w120, state]; Vehicle No. [Text, w120, vehicleNo]; GSTIN [Text, w120, gstin]; Purchase Account [Text, w120, purchaserName]; Qty [Text, w80, skuQtyTotal]; Taxable Amt. [Text, w100, taxableAmt]; SGST/UGST [Text, w80, sgstUgstAmt]; CGST [Text, w80, cgstAmt]; IGST [Text, w80, igstAmt]; TDS [Text, w80, tds]; Cess [Text, w80, cessAmt]; other Amount [Text, w120, otherAmount]; Net Amount [Text, w150, purchaseAmt]; Total [Text, w150, cumulativeTotal]

**Lookup / suggestion / find sources (server RPC called):**
- `displaySetting` → `getDefault`
- `grdDisplay` → `getPurchaseDetail`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`displaySetting.addCallBack` → `onOk()`
```java
displaySettingMdl = acDisplaySetting;
refreshGrid();
```

`grdDisplay.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnToEndOfFile).getPurchaseDetail(displaySettingMdl, helper, blnToEndOfFile, param, new AsyncCallback
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<PurchaseDetailDisplayGrid_Mdl>> result) {
if (result.isStatus()) {
grdDisplay.setData(result.getData(), result.getPaging());
Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
public void execute() {
int rowIndex = DmShared.iNull(displayRouteList.get((displayRouteList.size() - 1)).getClickRowIndex()).intValue();
int colIndex = DmShared.iNull(displayRouteList.get((displayRouteList.size() - 1)).getClickColIndex()).intValue();
...
```

`grdDisplay.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
grdDisplay.setData(result.getData(), result.getPaging());
Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
public void execute() {
int rowIndex = DmShared.iNull(displayRouteList.get((displayRouteList.size() - 1)).getClickRowIndex()).intValue();
int colIndex = DmShared.iNull(displayRouteList.get((displayRouteList.size() - 1)).getClickColIndex()).intValue();
renderSelectionFill(rowIndex, colIndex);
if (flagOnLoadRefresh) {
flagOnLoadRefresh = false;
if ((displayRouteList.size() > 0)) {
...
```

`grdDisplay.addCallBack` → `execute()`
```java
int rowIndex = DmShared.iNull(displayRouteList.get((displayRouteList.size() - 1)).getClickRowIndex()).intValue();
int colIndex = DmShared.iNull(displayRouteList.get((displayRouteList.size() - 1)).getClickColIndex()).intValue();
renderSelectionFill(rowIndex, colIndex);
if (flagOnLoadRefresh) {
flagOnLoadRefresh = false;
if ((displayRouteList.size() > 0)) {
((CellSelectionModel) grdDisplay.getDmGrid().getGrid().getSelectionModel()).selectCell(rowIndex, colIndex);
} else {
txtSearchValue.setFocus(true);
```

`grdDisplay.addCallBack` → `getParam()`
```java
return null;
```

## Files
| file | lines |
|---|---|
| client/PurchaseDetailDisplay.java | 581 |
| client/PurchaseDetailDisplay_Dsg.java | 503 |
| server/PurchaseDetailDisplay_SrvImpl.java | 520 |
| shared/DefaultValue_Mdl.java | 162 |
| shared/PurchaseDetailDisplayGrid_Mdl.java | 330 |
| shared/PurchaseDetailDisplay_Srv.java | 15 |
| shared/PurchaseDetailDisplay_SrvAsync.java | 15 |
| shared/RptPurchaseDetailDisplay_Mdl.java | 77 |

## UI labels / columns (client)
`Taxable Amt :`, `SGST/UGST :`, `CGST :`, `Qty Total :`, `IGST :`, `Cess :`, `Net Amt :`, `Total Tcs :`

## Models (shared DTOs)
### PurchaseDetailDisplayGrid_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | docId |
| String | voucherNo |
| String | siteCode |
| String | v_Prefix |
| String | voucherType |
| String | voucherDate |
| String | partyBillNo |
| String | partyBillDate |
| String | supplier |
| String purchaseAmt, | purchaseDispAmt |
| String cumTotalAmt, | cumTotalDispAmt |
| String | state |
| String | vehicleNo |
| String | gstin |
| String | purchaseAcCode |
| String | purchaseAcName |
| String taxableAmt, | taxableDispAmt |
| String sgstUgstAmt, | sgstUgstDispAmt |
| String cgstAmt, | cgstDispAmt |
| String igstAmt, | igstDispAmt |
| String cessAmt, | cessDispAmt |
| String tcsAmount, | tcsDispAmount |
| String skuQty, | skuDispQty |
| String | otherAmount |
| String | moduleName |
| String | menuText |
| boolean | selected |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | totalPurchaseAmt |
| String | totalPurchaseDispAmt |
| String | totalTaxableAmt |
| String | totalTaxableDispAmt |
| String | totalCgstAmt |
| String | totalCgstDispAmt |
| String | totalIgstAmt |
| String | totalIgstDispAmt |
| String | totalSgstUgstAmt |
| String | totalSgstUgstDispAmt |
| String | totalCessAmt |
| String | totalCessDispAmt |
| String | totalTcs |
| String | totalTcsDisp |
| String | totalSkuQty |
| String | totalSkuQtyDisp |

### RptPurchaseDetailDisplay_Mdl
| type | field |
|---|---|
| String | totalPurchaseAmt |
| DisplaySetting_Mdl | displaySettingMdl |
| String | totalTaxableAmt |
| String | totalCgstAmt |
| String | totalIgstAmt |
| String | totalSgstUgstAmt |
| String | totalCessAmt |

## RPC interface
| method | params | returns |
|---|---|---|
| getDefault | DisplaySetting_Mdl displaySetting, List<DisplayRoute_Mdl> displayRouteList, String loginDate | DmResult<DefaultValue_Mdl> |
| getPurchaseDetail | DisplaySetting_Mdl displaySetting, DmHelper helper, boolean blnToEndOfFile, KeyFieldExtended keyField | DmResultPageing<List<PurchaseDetailDisplayGrid_Mdl>> |

## Server `PurchaseDetailDisplay_SrvImpl.java` (520 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.vNull`, `DmShared.xNull`

### `getDefault`
`public DmResult<DefaultValue_Mdl> getDefault(DisplaySetting_Mdl displaySetting, List<DisplayRoute_Mdl> displayRouteList, String loginDate)`

- SQL 1: `IF OBJECT_ID('tempdb.. {…} ') IS NOT NULL DROP TABLE {…} ; CREATE TABLE {…} (orderIndex bigint, docId bigInt, site_Code smallint , recId varchar(50), v_no smallInt, v_Prefix tinyInt, v_Type smallInt, v_date date , short_Name varchar(5), category varchar(10), partyBillNo varchar(30), partyBillDate date, supplier varchar(250) , purchaseAc varchar(250), state varchar(200) ,oh_Amt_Net decimal(18, 2), cumulativeTotal decimal(18, 2) , oh_Amt_Taxable decimal(18, 2), oh_Amt_SGST_UGST decimal(18, 2), oh_Amt_CGST decimal(18, 2), oh_Amt_IGST decimal(18, 2) , gsTin varchar(20), skuQtyTotal decimal(18, 7), tds decimal(18, 7), otherAmount decimal(18, 7), oh_Amt_Cess decimal(18, 2) ,vehicleNo varchar(250))`
- SQL 2: `SELECT pb.docId, MAX(pb.site_Code) as site_Code, MAX(pb.recId) AS recId, MAX(pb.v_No) AS v_No, MAX(pb.v_Prefix) AS v_Prefix, MAX(pb.v_Date) AS v_Date, MAX(vt.category) AS category , MAX(pb.v_Type) AS v_Type, MAX(vt.short_Name) AS short_Name, MAX(pb.partyBillNo) AS partyBillNo, MAX(pb.partyBillDate) AS partyBillDate, MAX(sm.manualCode) AS siteShortName , MAX(CONCAT(ptip.name, IIF(ISNULL(ptip.cityName,'')='', '', ', '), ptip.cityName)) AS supplier , CASE WHEN MAX(pbd.oh_Ac_Purchase) IS NULL THEN MAX(pbd.oh_Ac_PurchaseContra) ELSE MAX(pbd.oh_Ac_Purchase) END AS purchaseAc , MAX(pboh.subCode) AS subCode, MAX(sgoh.name) AS purchaseAcName , MAX(sga.name) AS purchaseAcName ,MAX(pb.gsTin) as gsTin , MAX(pb.oh_Amt_Tds) AS tds , MAX(pb.vehicleNo) AS vehicleNo , MAX(CASE WHEN vt.category IN (:PBIL, :GPBUS, :PBILU, :PBILD, :PBLDU, :SPB, :SPBU, :SPBD, :SPBDU, :GPB, :GPBU, :GPBD, :GPBDU) THEN pb.oh_Amt_Net END) AS oh_Amt_Net , MAX(CASE WHEN vt.category IN (:PBIL, :GPBUS, :PBILU, :PBILD, :PBLDU, :SPB, :SPBU, :SPBD, :SPBDU, :GPB, :GPBU, :GPBD, :GPBDU) THEN pb.oh_Amt_Taxable END) AS oh_Amt_Taxable , MAX(CASE WHEN vt.category IN (:PBIL, :GPBUS, :PBILU, :PBILD, :PBLDU, :SPB, :SPBU, :SPBD, :SPBDU, :GPB, :GPBU, :GPBD, :GPBDU) THEN (ISNULL(pb.oh_Amt_SGST, 0)+ISNULL(pb.oh_Amt_UGST, 0)) END) AS oh_Amt_SGST_UGST , MAX(CASE WHEN vt.category IN (:PBIL,:GPBUS, :PBILU, :PBILD, :PBLDU, :SPB, :SPBU, :SPBD, :SPBDU, :GPB, :GPBU, :GPBD, :GPBDU) THEN pb.oh_Amt_CGST END) AS oh_Amt_CGST , MAX(CASE WHEN vt.category IN (:PBIL, :GPBUS, :PBILU, :PBILD, :PBLDU, :SPB, :SPBU, :SPBD, :SPBDU, :GPB, :GPBU, :GPBD, :GPBDU) THEN pb.oh_Amt_IGST END) AS oh_Amt_IGST , MAX(CASE WHEN vt.category IN (:PBIL,:GPBUS, :PBILU, :PBILD, :PBLDU, :SPB, :SPBU, :SPBD, :SPBDU, :GPB, :GPBU, :GPBD, :GPBDU) THEN isnull(pb.oh_Amt_Cess,0)+isnull(pb.oh_Amt_CessNA,0) END) AS oh_Amt_Cess , SUM(CASE WHEN vt.category IN (:PBIL, :GPBUS,:GPBUS, :PBILU, :PBILD, :PBLDU, :SPB, :SPBU, :SPBD, :SPBDU, :GPB, :GPBU, :GPBD, :GPBDU) THEN pbd.skuQty END) AS skuQtyTotal , SUM(pb.oh_Amt_Net - (pb.oh_Amt_Taxable + pb.oh_Amt_SGST + pb.oh_Amt_CGST + pb.oh_Amt_IGST )) AS otherAmount , MAX(CONCAT(ptip.name, IIF(ISNULL(ptip.cityName,'')='', '', ', '), ptip.cityName)) AS party ,MAX(ptip.stateCode) as stateCode, MAX(ptip.stateName) as state FROM pBill pb LEFT JOIN pBillDetail pbd ON pbd.docId = pb.docId LEFT JOIN pBillOH pboh ON pboh.docId = pb.docId LEFT JOIN overHeadMast ohm ON ohm.code = pboh.overHeadCode LEFT JOIN subGroup sgoh ON sgoh.subCode = pboh.subCode LEFT JOIN voucher_Type vt ON vt.v_Type = pb.v_Type LEFT JOIN subGroup sg on sg.subCode = pb.subCode LEFT JOIN subGroup sga ON sga.subCode = pbd.oh_Ac_Purchase LEFT JOIN partyTransInfo ptip ON ptip.docId = pb.docId AND ptip.recordType = :partyRecordType LEFT JOIN siteMast sm ON sm.code = pb.site_Code WHERE 1=1 AND ISNULL(pb.cancelledBy, '') = '' AND ISNULL(pb.approvedBy, '') <> '' AND vt.category IN (:PBIL, :GPBUS,:PBILU,:PBILD,:PBLDU,:SPB,:SPBU, :SPBD, :SPBDU, :GPB, :GPBU, :GPBD, :GPBDU) AND ohm.manualCode IN ('G', 'TV') AND pboh.subCode IS NOT NULL {…} GROUP BY pb.docId ORDER BY V_Date, short_Name, V_No, recId, siteShortName`
- SQL 3: `INSERT INTO {…} (orderIndex, site_Code, docId, recId, v_no, v_Prefix, category, v_Type, v_date, short_Name, supplier, state, partyBillNo, purchaseAc , partyBillDate, oh_Amt_Net, cumulativeTotal,oh_Amt_Taxable, oh_Amt_SGST_UGST, oh_Amt_CGST , oh_Amt_IGST, gsTin, skuQtyTotal, tds, oh_Amt_Cess, vehicleNo, otherAmount) VALUES(:orderIndex, :site_Code, :docId, :recId, :v_no, :v_Prefix, :category , :v_Type, :v_date, :short_Name, :supplier, :state, :partyBillNo, :purchaseAc , :partyBillDate, :oh_Amt_Net, :cumulativeTotal, :oh_Amt_Taxable, :oh_Amt_SGST_UGST, :oh_Amt_CGST ,:oh_Amt_IGST, :gsTin, :skuQtyTotal, :tds, :oh_Amt_Cess, :vehicleNo, :otherAmount )`
- Params: `:PBIL` ← `pvc.PURCHASE_BILL`; `:GPBUS` ← `pvc.GENERAL_PURCHASE_BILL_UNSTOCKABLE`; `:PBILU` ← `pvc.PURCHASE_BILL_UNREGISTERED`; `:PBILD` ← `pvc.PURCHASE_BILL_DIRECT`; `:PBLDU` ← `pvc.PURCHASE_BILL_DIRECT_UNREGISTERED`; `:SPB` ← `pvc.SERVICE_PURCHASE_BILL`; `:SPBU` ← `pvc.SERVICE_PURCHASE_BILL_UNREGISTERED`; `:SPBD` ← `pvc.SERVICE_PURCHASE_BILL_DIRECT`; `:SPBDU` ← `pvc.SERVICE_PURCHASE_BILL_DIRECT_UNREGISTERED`; `:GPB` ← `pvc.GENERAL_PURCHASE_BILL`; `:GPBU` ← `pvc.GENERAL_PURCHASE_BILL_UNREGISTERED`; `:GPBD` ← `pvc.GENERAL_PURCHASE_BILL_DIRECT`; `:GPBDU` ← `pvc.GENERAL_PURCHASE_BILL_DIRECT_UNREGISTERED`; `:partyRecordType` ← `DmEnum.PartyTransRecordType.PARTY.getCode()`; `:orderIndex` ← `orderIndex`; `:site_Code` ← `DmShared.xNull(rsTemp.getString("site_Code"))`; `:docId` ← `DmShared.xNull(rsTemp.getString("docId"))`; `:recId` ← `DmShared.xNull(rsTemp.getString("recId"))`; `:v_no` ← `DmShared.xNull(rsTemp.getString("v_no"))`; `:v_Prefix` ← `DmShared.xNull(rsTemp.getString("v_Prefix"))`; `:category` ← `DmShared.xNull(rsTemp.getString("category"))`; `:v_Type` ← `DmShared.xNull(rsTemp.getString("v_Type"))`; `:v_date` ← `DmServer.convertDate(DmShared.dNull(rsTemp.getDate("v_date")))`; `:short_Name` ← `DmShared.xNull(rsTemp.getString("short_Name"))`; `:supplier` ← `DmShared.xNull(rsTemp.getString("supplier"))`; `:state` ← `DmShared.xNull(rsTemp.getString("state"))`; `:gsTin` ← `DmShared.xNull(rsTemp.getString("gsTin"))`; `:partyBillNo` ← `DmShared.xNull(rsTemp.getString("partyBillNo"))`; `:partyBillDate` ← `DmServer.convertDate(DmShared.dNull(rsTemp.getDate("partyBillDate")))`; `:purchaseAc` ← `DmShared.xNull(rsTemp.getString("purchaseAcName"))`; `:vehicleNo` ← `DmShared.xNull(rsTemp.getString("vehicleNo"))`; `:oh_Amt_Net` ← `DmServer.vNull(purchaseAmt, "0.00")`; `:cumulativeTotal` ← `DmServer.vNull(cumulativeTotal, "0.00")`; `:oh_Amt_Taxable` ← `DmServer.vNull(taxableAmt, "0.00")`; `:tds` ← `DmServer.vNull(tds, "0.00")`; `:skuQtyTotal` ← `DmServer.vNull(totalSkuQuantity)`; `:oh_Amt_SGST_UGST` ← `DmServer.vNull(DmShared.vNull(rsTemp.getString("oh_Amt_SGST_UGST")).doubleValue(), "0.00")`; `:oh_Amt_CGST` ← `DmServer.vNull(DmShared.vNull(rsTemp.getString("oh_Amt_CGST")).doubleValue(), "0.00")`; `:oh_Amt_IGST` ← `DmServer.vNull(DmShared.vNull(rsTemp.getString("oh_Amt_IGST")).doubleValue(), "0.00")`; `:oh_Amt_Cess` ← `DmServer.vNull(DmShared.vNull(rsTemp.getString("oh_Amt_Cess")).doubleValue(), "0.00")`; `:otherAmount` ← `DmServer.vNull(DmShared.vNull(rsTemp.getString("otherAmount")).doubleValue(), "0.00")`
- Errors: "Period difference cannot be greater than 366 days!" | "To Date should be greater than from date!"
- Other calls: `DmJasper.formatAmount`, `PARTY.getCode`, `STServer.getDateDifference`

### `getPurchaseDetail`
`public DmResultPageing<List<PurchaseDetailDisplayGrid_Mdl>> getPurchaseDetail(DisplaySetting_Mdl displaySetting, DmHelper helper, boolean blnToEndOfFile, KeyFieldExtended keyField)`

- SQL 1: `SELECT pb.site_Code, pb.orderIndex, pb.docId, pb.recId , pb.v_No, pb.v_Prefix, pb.v_Type, pb.v_date, pb.short_Name, pb.supplier , pb.partyBillNo, pb.partyBillDate, vt.mnuAttachedInModule, vt.mnuText , pb.state , pb.oh_Amt_Taxable AS taxableAmt , pb.oh_Amt_SGST_UGST AS sgstUgstAmt, pb.oh_Amt_CGST AS cgstAmt, pb.oh_Amt_IGST AS igstAmt , pb.gstin, pb.skuQtyTotal, pb.tds, pb.oh_Amt_Cess AS cessAmt, pb.vehicleNo , pb.oh_Amt_Net AS purchaseAmt, pb.cumulativeTotal, pb.purchaseAc, pb.otherAmount FROM {…} pb LEFT JOIN voucher_Type vt ON vt.v_Type = pb.v_Type`
- Other calls: `DmJasper.formatAmount`

### `getTable`
`private String getTable(DmRSSPrivate rssPrivate)`


### `getParameter`
`private void getParameter(DisplaySetting_Mdl filter, DmPreparedStatement psTemp)`

- Params: `:fromDate` ← `DmServer.convertDate(filter.getFromDate())`; `:toDate` ← `DmServer.convertDate(filter.getToDate())`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmJasper.server`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `paperbiz.projectMenu.self.shared`, `paperbiz.purchase.displaySetting.client`, `paperbiz.purchase.displaySetting.shared`, `paperbiz.purchase.purchaseDetailDisplay.shared`, `paperbiz.purchase.reports.client`, `paperbiz.selfToolkit.stStatic.server`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| getDefault | `if (dateDiff > 366)` | "Period difference cannot be greater than 366 days!" |
| getDefault | `} else if (dateDiff < 0)` | "To Date should be greater than from date!" |

## Notes (hand-written)
_No hand-written notes yet._
