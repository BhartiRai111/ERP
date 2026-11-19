# pb-sales / salesSummaryDisplay

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/sales/src/salesSummaryDisplay/`). Hand-written notes are in the "Notes" section.

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
### Screen `SalesSummaryDisplay`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmTextBox | `txtSearchValue` | `100, false` |
| 2 | - | DmGridPaging | `grdDisplay` | `GrdDisplay_PrpAcs, 20` |

**Grid / popup columns** (header, width, db field):
- `grdDisplay`: Particulars [Text, w740, particular]; Amount [Text, w240, netSales]; Total [Text, w240, cumulativeTotal]

**Lookup / suggestion / find sources (server RPC called):**
- `displaySetting` → `getDefault`
- `grdDisplay` → `getSalesSummary`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`displaySetting.addCallBack` → `onOk()`
```java
displaySettingMdl = acDisplaySetting;
refreshGrid();
```

`grdDisplay.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnToEndOfFile).getSalesSummary(displaySettingMdl, helper, blnToEndOfFile, param, new AsyncCallback<D
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<SalesSummaryDisplayGrid_Mdl>> result) {
if (result.isStatus()) {
grdDisplay.setData(result.getData(), result.getPaging());
Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
public void execute() {
try {
int rowIndex = DmShared.iNull(displayRouteList.get((displayRouteList.size() - 1)).getClickRowIndex()).intValue();
...
```

`grdDisplay.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
grdDisplay.setData(result.getData(), result.getPaging());
Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
public void execute() {
try {
int rowIndex = DmShared.iNull(displayRouteList.get((displayRouteList.size() - 1)).getClickRowIndex()).intValue();
int colIndex = DmShared.iNull(displayRouteList.get((displayRouteList.size() - 1)).getClickColIndex()).intValue();
if (flagOnLoadRefresh) {
flagOnLoadRefresh = false;
renderSelectionFill(rowIndex, colIndex);
...
```

`grdDisplay.addCallBack` → `execute()`
```java
try {
int rowIndex = DmShared.iNull(displayRouteList.get((displayRouteList.size() - 1)).getClickRowIndex()).intValue();
int colIndex = DmShared.iNull(displayRouteList.get((displayRouteList.size() - 1)).getClickColIndex()).intValue();
if (flagOnLoadRefresh) {
flagOnLoadRefresh = false;
renderSelectionFill(rowIndex, colIndex);
if ((displayRouteList.size() > 0)) {
((CellSelectionModel) grdDisplay.getDmGrid().getGrid().getSelectionModel()).selectCell(rowIndex, colIndex);
} else {
txtSearchValue.setFocus(true);
...
```

`grdDisplay.addCallBack` → `getParam()`
```java
return null;
```

## Files
| file | lines |
|---|---|
| client/SalesSummaryDisplay.java | 509 |
| client/SalesSummaryDisplay_Dsg.java | 225 |
| server/SalesSummaryDisplay_SrvImpl.java | 378 |
| shared/DefaultValue_Mdl.java | 28 |
| shared/RptSalesSummaryDisplay_Mdl.java | 31 |
| shared/SalesSummaryDisplayGrid_Mdl.java | 75 |
| shared/SalesSummaryDisplay_Srv.java | 15 |
| shared/SalesSummaryDisplay_SrvAsync.java | 15 |

## Models (shared DTOs)
### DefaultValue_Mdl
| type | field |
|---|---|
| String | totalSalesAmt |
| String | totalSaleslDispAmt |

### RptSalesSummaryDisplay_Mdl
| type | field |
|---|---|
| String | totalNetSales |
| DisplaySetting_Mdl | displaySettingMdl |

### SalesSummaryDisplayGrid_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | netSalesAmt |
| String | cumulativeTotalAmt |
| String | netSalesDispAmt |
| String | cumulativeTotalDispAmt |
| String fromDate, | toDate |
| boolean | selected |

## RPC interface
| method | params | returns |
|---|---|---|
| getDefault | DisplaySetting_Mdl acDisplaySetting, List<DisplayRoute_Mdl> displayRouteList, String loginDate | DmResult<DefaultValue_Mdl> |
| getSalesSummary | DisplaySetting_Mdl displaySetting, DmHelper helper, boolean blnToEndOfFile, KeyFieldExtended param | DmResultPageing<List<SalesSummaryDisplayGrid_Mdl>> |

## Server `SalesSummaryDisplay_SrvImpl.java` (378 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.vNull`, `DmShared.xNull`

### `getDefault`
`public DmResult<DefaultValue_Mdl> getDefault(DisplaySetting_Mdl displaySetting, List<DisplayRoute_Mdl> displayRouteList, String loginDate)`

- SQL 1: `IF OBJECT_ID('tempdb.. {…} ') IS NOT NULL DROP TABLE {…} ; CREATE TABLE {…} (orderIndex bigint, fromDate date, toDate date , periodicityType varchar(20), particular varchar(20), netSales decimal(18, 2), cumulativeTotal decimal(18, 2))`
- SQL 2: `DECLARE @startDate DATE = :fromDate; DECLARE @endDate DATE = :toDate; DECLARE @currentDate DATE = :currentDate; WITH ctePeriodicity AS ( SELECT CAST(@startDate AS DATE) AS cDate UNION ALL SELECT DATEADD(DAY, 1, cDate) FROM ctePeriodicity WHERE cDate < @endDate ), cteSales AS ( SELECT sb.docId, sb.partyCode, ptip.name AS partyName, sb.v_Date , CASE WHEN vt.category IN (:SI, :SIO, :SIU) THEN sb.oh_Amt_Net WHEN vt.category IN (:SR, :SRO) THEN -ISNULL(sb.oh_Amt_Net,0) END AS oh_Amt_Net FROM sBill sb LEFT JOIN voucher_Type vt ON vt.v_Type = sb.v_Type LEFT JOIN partyTransInfo ptip ON ptip.docId = sb.docId AND ptip.recordType = :partyRecordType WHERE ISNULL(sb.cancelledBy, '') = '' AND ISNULL(sb.approvedBy, '') <> '' AND vt.category IN (:SI, :SIO, :SR, :SRO, :SIU) AND sb.v_Date >= @startDate AND sb.v_Date <= @endDate {…} ), ctePeriodicitySales AS ( {…} ) SELECT cs.periodicityType, cs.year, cs.periodicityValue , CASE WHEN @startDate BETWEEN cs.fromDate AND cs.toDate THEN @startDate ELSE cs.fromDate END AS fromDate , CASE WHEN @endDate BETWEEN cs.fromDate AND cs.toDate THEN @endDate ELSE cs.toDate END AS toDate {…} FROM ctePeriodicitySales cs OPTION (MAXRECURSION 366)`
- SQL 3: `INSERT INTO {…} (orderIndex, fromDate, toDate , periodicityType, particular, netSales, cumulativeTotal) VALUES( :orderIndex, :fromDate, :toDate, :periodicityType , :particular, :netSales, :cumulativeTotal)`
- Params: `:SI` ← `pvc.SALES_INVOICE`; `:SIO` ← `pvc.GENERAL_SALES_INVOICE`; `:SR` ← `pvc.SALES_RETURN`; `:SRO` ← `pvc.GENERAL_SALES_RETURN`; `:SIU` ← `pvc.GENERAL_SALES_INVOICE_UNSTOCKABLE`; `:fromDate` ← `DmServer.convertDate(displaySetting.getFromDate())`; `:toDate` ← `DmServer.convertDate(displaySetting.getToDate())`; `:currentDate` ← `DmServer.convertDate(loginDate)`; `:partyRecordType` ← `DmEnum.PartyTransRecordType.PARTY.getCode()`; `:orderIndex` ← `orderIndex`; `:fromDate` ← `DmServer.convertDate(DmShared.dNull(rsTemp.getDate("fromDate")))`; `:toDate` ← `DmServer.convertDate(DmShared.dNull(rsTemp.getDate("toDate")))`; `:periodicityType` ← `DmShared.xNull(rsTemp.getString("periodicityType"))`; `:particular` ← `DmShared.xNull(rsTemp.getString("particular"))`; `:netSales` ← `DmServer.vNull(rsTemp.getString("netSales"), "0.00")`; `:cumulativeTotal` ← `DmServer.vNull(rsTemp.getString("cumulativeTotal"), "0.00")`
- Errors: "Period difference cannot be greater than 366 days!" | "To Date should be greater than from date!"
- Other calls: `DmJasper.formatAmount`, `PARTY.getCode`, `STServer.getDateDifference`

### `getSalesSummary`
`public DmResultPageing<List<SalesSummaryDisplayGrid_Mdl>> getSalesSummary(DisplaySetting_Mdl displaySetting, DmHelper helper, boolean blnToEndOfFile, KeyFieldExtended param)`

- SQL 1: `SELECT sb.orderIndex, sb.fromDate, sb.toDate, sb.particular, sb.netSales, sb.cumulativeTotal FROM {…} AS sb`
- Other calls: `DmJasper.formatAmount`

### `getTable`
`private String getTable(DmRSSPrivate rssPrivate)`


### `getParameter`
`private void getParameter(DisplaySetting_Mdl displaySetting, DmPreparedStatement psTemp)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmJasper.server`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.self.client`, `paperbiz.projectMenu.self.shared`, `paperbiz.sales.displaySetting.client`, `paperbiz.sales.displaySetting.shared`, `paperbiz.sales.reports.client`, `paperbiz.sales.salesSummaryDisplay.shared`, `paperbiz.selfToolkit.stStatic.server`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| getDefault | `if (dateDiff > 366)` | "Period difference cannot be greater than 366 days!" |
| getDefault | `} else if (dateDiff < 0)` | "To Date should be greater than from date!" |

## Notes (hand-written)
_No hand-written notes yet._
