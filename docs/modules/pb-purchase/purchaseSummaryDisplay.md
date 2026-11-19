# pb-purchase / purchaseSummaryDisplay

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/purchase/src/purchaseSummaryDisplay/`). Hand-written notes are in the "Notes" section.

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
### Screen `PurchaseSummaryDisplay`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmTextBox | `txtSearchValue` | `100, false` |
| 2 | - | DmGridPaging | `grdDisplay` | `GrdDisplay_PrpAcs, 20` |

**Grid / popup columns** (header, width, db field):
- `grdDisplay`: Particulars [Text, w740, particular]; Amount [Text, w240, netPurchase]; Total [Text, w240, cumulativeTotal]

**Lookup / suggestion / find sources (server RPC called):**
- `displaySetting` → `getDefault`
- `grdDisplay` → `getPurchaseSummary`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`displaySetting.addCallBack` → `onOk()`
```java
displaySettingMdl = acDisplaySetting;
refreshGrid();
```

`grdDisplay.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnToEndOfFile).getPurchaseSummary(displaySettingMdl, helper, blnToEndOfFile, param, new AsyncCallbac
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<PurchaseSummaryDisplayGrid_Mdl>> result) {
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
| client/PurchaseSummaryDisplay.java | 508 |
| client/PurchaseSummaryDisplay_Dsg.java | 225 |
| server/PurchaseSummaryDisplay_SrvImpl.java | 382 |
| shared/DefaultValue_Mdl.java | 28 |
| shared/PurchaseSummaryDisplayGrid_Mdl.java | 75 |
| shared/PurchaseSummaryDisplay_Srv.java | 15 |
| shared/PurchaseSummaryDisplay_SrvAsync.java | 15 |
| shared/RptPurchaseSummaryDisplay_Mdl.java | 31 |

## Models (shared DTOs)
### PurchaseSummaryDisplayGrid_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | netPurchaseAmt |
| String | cumulativeTotalAmt |
| String | netPurchaseDispAmt |
| String | cumulativeTotalDispAmt |
| String fromDate, | toDate |
| boolean | selected |

### RptPurchaseSummaryDisplay_Mdl
| type | field |
|---|---|
| String | totalNetPurchase |
| DisplaySetting_Mdl | displaySettingMdl |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | totalPurchaseAmt |
| String | totalPurchaseDispAmt |

## RPC interface
| method | params | returns |
|---|---|---|
| getDefault | DisplaySetting_Mdl displaySetting, List<DisplayRoute_Mdl> displayRouteList, String loginDate | DmResult<DefaultValue_Mdl> |
| getPurchaseSummary | DisplaySetting_Mdl displaySetting, DmHelper helper, boolean blnToEndOfFile, KeyFieldExtended param | DmResultPageing<List<PurchaseSummaryDisplayGrid_Mdl>> |

## Server `PurchaseSummaryDisplay_SrvImpl.java` (382 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.vNull`, `DmShared.xNull`

### `getDefault`
`public DmResult<DefaultValue_Mdl> getDefault(DisplaySetting_Mdl displaySetting, List<DisplayRoute_Mdl> displayRouteList, String loginDate)`

- SQL 1: `IF OBJECT_ID('tempdb.. {…} ') IS NOT NULL DROP TABLE {…} ; CREATE TABLE {…} (orderIndex bigint, fromDate date, toDate date , periodicityType varchar(20), particular varchar(20), netPurchase decimal(18, 2), cumulativeTotal decimal(18, 2))`
- SQL 2: `DECLARE @startDate DATE = :fromDate; DECLARE @endDate DATE = :toDate; DECLARE @currentDate DATE = :currentDate; WITH ctePeriodicity AS ( SELECT CAST(@startDate AS DATE) AS cDate UNION ALL SELECT DATEADD(DAY, 1, cDate) FROM ctePeriodicity WHERE cDate < @endDate ), ctePurchase AS ( SELECT pb.docId, pb.subCode, ptip.name AS partyName, pb.v_Date , CASE WHEN vt.category IN (:PBIL, :GPBUS, :PBILU,:GPB,:GPBU,:GPBD,:GPBDU, :SPB, :SPBU, :SPBD, :SPBDU) THEN pb.oh_Amt_Net WHEN vt.category IN (:PRTN, :PRTNU,:GPR,:GPRU, :SPRTN, :SPRNU) THEN -ISNULL(pb.oh_Amt_Net,0) END AS oh_Amt_Net FROM pBill pb LEFT JOIN voucher_Type vt ON vt.v_Type = pb.v_Type LEFT JOIN partyTransInfo ptip ON ptip.docId = pb.docId AND ptip.recordType = :partyRecordType WHERE ISNULL(pb.cancelledBy, '') = '' AND ISNULL(pb.approvedBy, '') <> '' AND vt.category IN (:PBIL, :GPBUS, :PBILU,:GPB,:GPBU,:GPBD,:GPBDU, :SPB, :SPBU, :SPBD, :SPBDU, :PRTN, :PRTNU,:GPR,:GPRU, :SPRTN, :SPRNU) AND pb.v_Date >= @startDate AND pb.v_Date <= @endDate {…} ), ctePeriodicityPurchase AS ( {…} ) SELECT cp.periodicityType, cp.year, cp.periodicityValue , CASE WHEN @startDate BETWEEN cp.fromDate AND cp.toDate THEN @startDate ELSE cp.fromDate END AS fromDate , CASE WHEN @endDate BETWEEN cp.fromDate AND cp.toDate THEN @endDate ELSE cp.toDate END AS toDate {…} FROM ctePeriodicityPurchase cp OPTION (MAXRECURSION 366)`
- SQL 3: `INSERT INTO {…} (orderIndex, fromDate, toDate , periodicityType, particular, netPurchase, cumulativeTotal) VALUES( :orderIndex, :fromDate, :toDate, :periodicityType , :particular, :netPurchase, :cumulativeTotal)`
- Params: `:PBIL` ← `pvc.PURCHASE_BILL`; `:GPBUS` ← `pvc.GENERAL_PURCHASE_BILL_UNSTOCKABLE`; `:PBILU` ← `pvc.PURCHASE_BILL_UNREGISTERED`; `:GPB` ← `pvc.GENERAL_PURCHASE_BILL`; `:GPBU` ← `pvc.GENERAL_PURCHASE_BILL_UNREGISTERED`; `:GPBD` ← `pvc.GENERAL_PURCHASE_BILL_DIRECT`; `:GPBDU` ← `pvc.GENERAL_PURCHASE_BILL_DIRECT_UNREGISTERED`; `:SPB` ← `pvc.SERVICE_PURCHASE_BILL`; `:SPBU` ← `pvc.SERVICE_PURCHASE_BILL_UNREGISTERED`; `:SPBD` ← `pvc.SERVICE_PURCHASE_BILL_DIRECT`; `:SPBDU` ← `pvc.SERVICE_PURCHASE_BILL_DIRECT_UNREGISTERED`; `:PRTN` ← `pvc.PURCHASE_RETURN`; `:PRTNU` ← `pvc.PURCHASE_RETURN_UNREGISTERED`; `:GPR` ← `pvc.GENERAL_PURCHASE_RETURN`; `:GPRU` ← `pvc.GENERAL_PURCHASE_RETURN_UNREGISTERED`; `:SPRTN` ← `pvc.SERVICE_PURCHASE_RETURN`; `:SPRNU` ← `pvc.SERVICE_PURCHASE_RETURN_UNREGISTERED`; `:fromDate` ← `DmServer.convertDate(displaySetting.getFromDate())`; `:toDate` ← `DmServer.convertDate(displaySetting.getToDate())`; `:currentDate` ← `DmServer.convertDate(loginDate)`; `:partyRecordType` ← `DmEnum.PartyTransRecordType.PARTY.getCode()`; `:orderIndex` ← `orderIndex`; `:fromDate` ← `DmServer.convertDate(DmShared.dNull(rsTemp.getDate("fromDate")))`; `:toDate` ← `DmServer.convertDate(DmShared.dNull(rsTemp.getDate("toDate")))`; `:periodicityType` ← `DmShared.xNull(rsTemp.getString("periodicityType"))`; `:particular` ← `DmShared.xNull(rsTemp.getString("particular"))`; `:netPurchase` ← `DmServer.vNull(rsTemp.getString("netPurchase"), "0.00")`; `:cumulativeTotal` ← `DmServer.vNull(rsTemp.getString("cumulativeTotal"), "0.00")`
- Errors: "Period difference cannot be greater than 366 days!" | "To Date should be greater than from date!"
- Other calls: `DmJasper.formatAmount`, `PARTY.getCode`, `STServer.getDateDifference`

### `getPurchaseSummary`
`public DmResultPageing<List<PurchaseSummaryDisplayGrid_Mdl>> getPurchaseSummary(DisplaySetting_Mdl displaySetting, DmHelper helper, boolean blnToEndOfFile, KeyFieldExtended param)`

- SQL 1: `SELECT pb.orderIndex, pb.fromDate, pb.toDate, pb.particular, pb.netPurchase, pb.cumulativeTotal FROM {…} AS pb`
- Other calls: `DmJasper.formatAmount`

### `getTable`
`private String getTable(DmRSSPrivate rssPrivate)`


### `getParameter`
`private void getParameter(DisplaySetting_Mdl displaySetting, DmPreparedStatement psTemp)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmJasper.server`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.self.client`, `paperbiz.projectMenu.self.shared`, `paperbiz.purchase.displaySetting.client`, `paperbiz.purchase.displaySetting.shared`, `paperbiz.purchase.purchaseSummaryDisplay.shared`, `paperbiz.purchase.reports.client`, `paperbiz.selfToolkit.stStatic.server`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| getDefault | `if (dateDiff > 366)` | "Period difference cannot be greater than 366 days!" |
| getDefault | `} else if (dateDiff < 0)` | "To Date should be greater than from date!" |

## Notes (hand-written)
_No hand-written notes yet._
