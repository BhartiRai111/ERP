# pb-sales / salesDetailDisplay

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/sales/src/salesDetailDisplay/`). Hand-written notes are in the "Notes" section.

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
### Screen `SalesDetailDisplay`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmTextBox | `txtSearchValue` | `100, false` |
| 2 | - | DmGridPaging | `grdDisplay` | `GrdSalesDetail_PrpAcs, 20` |

**Grid / popup columns** (header, width, db field):
- `grdDisplay`: Voucher No. [Text, w120, recId]; Type [Text, w80, short_name]; Date [Text, w80, replace(convert(varchar, v_Date, 106), ' ','-')]; Party [Text, w540, party]; Amount [Text, w200, salesAmt]; Total [Text, w200, cumulativeTotal]

**Lookup / suggestion / find sources (server RPC called):**
- `displaySetting` → `getDefault`
- `grdDisplay` → `getSalesDetail`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`displaySetting.addCallBack` → `onOk()`
```java
displaySettingMdl = acDisplaySetting;
refreshGrid();
```

`grdDisplay.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnToEndOfFile).getSalesDetail(displaySettingMdl, helper, blnToEndOfFile, param, new AsyncCallback<Dm
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<SalesDetailDisplayGrid_Mdl>> result) {
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
| client/SalesDetailDisplay.java | 518 |
| client/SalesDetailDisplay_Dsg.java | 232 |
| server/SalesDetailDisplay_SrvImpl.java | 355 |
| shared/DefaultValue_Mdl.java | 29 |
| shared/RptSalesDetailDisplay_Mdl.java | 32 |
| shared/SalesDetailDisplayGrid_Mdl.java | 147 |
| shared/SalesDetailDisplay_Srv.java | 15 |
| shared/SalesDetailDisplay_SrvAsync.java | 15 |

## Models (shared DTOs)
### DefaultValue_Mdl
| type | field |
|---|---|
| String | totalSalesAmt |
| String | totalSaleslDispAmt |

### SalesDetailDisplayGrid_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | docId |
| String | siteCode |
| String | v_Prefix |
| String | voucherType |
| String | voucherDate |
| String | refNo |
| String | refDate |
| String | party |
| String salesAmt, | salesDispAmt |
| String cumTotalAmt, | cumTotalDispAmt |
| String | moduleName |
| String | menuText |
| boolean | selected |

### RptSalesDetailDisplay_Mdl
| type | field |
|---|---|
| String | totalSalesAmt |
| DisplaySetting_Mdl | displaySettingMdl |

## RPC interface
| method | params | returns |
|---|---|---|
| getDefault | DisplaySetting_Mdl displaySetting, List<DisplayRoute_Mdl> displayRouteList, String loginDate | DmResult<DefaultValue_Mdl> |
| getSalesDetail | DisplaySetting_Mdl displaySetting, DmHelper helper, boolean blnToEndOfFile, KeyFieldExtended keyField | DmResultPageing<List<SalesDetailDisplayGrid_Mdl>> |

## Server `SalesDetailDisplay_SrvImpl.java` (355 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.vNull`, `DmShared.xNull`

### `getDefault`
`public DmResult<DefaultValue_Mdl> getDefault(DisplaySetting_Mdl displaySetting, List<DisplayRoute_Mdl> displayRouteList, String loginDate)`

- SQL 1: `IF OBJECT_ID('tempdb.. {…} ') IS NOT NULL DROP TABLE {…} ; CREATE TABLE {…} (orderIndex bigint, docId bigInt, site_Code smallint , recId varchar(50), v_no smallInt, v_Prefix tinyInt, v_Type smallInt, v_date date , short_Name varchar(5), category varchar(10), refNo varchar(30), refDate date , party varchar(250), oh_Amt_Net decimal(18, 2), cumulativeTotal decimal(18, 2) )`
- SQL 2: `SELECT sb.docId, sb.site_Code, sb.recId, sb.v_No, v_Prefix, sb.v_Date, vt.category , sb.v_Type, vt.short_Name, sb.refNo, sb.refDate, sm.manualCode AS siteShortName , CASE WHEN vt.category IN (:SI, :SIO, :SIU) THEN sb.oh_Amt_Net WHEN vt.category IN (:SR, :SRO, :SIU) THEN - ISNULL(sb.oh_Amt_Net,0) END AS oh_Amt_Net , CONCAT(ptip.name, IIF(ISNULL(ptip.cityName,'')='', '', ', '), ptip.cityName) AS party , refNo, refDate FROM sBill sb LEFT JOIN voucher_Type vt ON vt.v_Type = sb.v_Type LEFT JOIN partyTransInfo ptip ON ptip.docId = sb.docId AND ptip.recordType = :partyRecordType LEFT JOIN siteMast sm ON sm.code = sb.site_Code WHERE 1=1 AND ISNULL(sb.cancelledBy, '') = '' AND ISNULL(sb.approvedBy, '') <> '' AND vt.category IN (:SI, :SIO, :SR, :SRO, :SIU) {…} ORDER BY V_Date, short_Name, V_No, recId, siteShortName`
- SQL 3: `INSERT INTO {…} (orderIndex, site_Code, docId, recId, v_no , v_Prefix, category, v_Type, v_date, short_Name, party, refNo, refDate, oh_Amt_Net, cumulativeTotal) VALUES(:orderIndex, :site_Code, :docId, :recId, :v_no, :v_Prefix, :category , :v_Type, :v_date, :short_Name, :party, :refNo, :refDate, :oh_Amt_Net, :cumulativeTotal)`
- Params: `:SI` ← `pvc.SALES_INVOICE`; `:SIU` ← `pvc.GENERAL_SALES_INVOICE_UNSTOCKABLE`; `:SIO` ← `pvc.GENERAL_SALES_INVOICE`; `:SR` ← `pvc.SALES_RETURN`; `:SRO` ← `pvc.GENERAL_SALES_RETURN`; `:partyRecordType` ← `DmEnum.PartyTransRecordType.PARTY.getCode()`; `:orderIndex` ← `orderIndex`; `:site_Code` ← `DmShared.xNull(rsTemp.getString("site_Code"))`; `:docId` ← `DmShared.xNull(rsTemp.getString("docId"))`; `:recId` ← `DmShared.xNull(rsTemp.getString("recId"))`; `:v_no` ← `DmShared.xNull(rsTemp.getString("v_no"))`; `:v_Prefix` ← `DmShared.xNull(rsTemp.getString("v_Prefix"))`; `:category` ← `DmShared.xNull(rsTemp.getString("category"))`; `:v_Type` ← `DmShared.xNull(rsTemp.getString("v_Type"))`; `:v_date` ← `DmServer.convertDate(DmShared.dNull(rsTemp.getDate("v_date")))`; `:short_Name` ← `DmShared.xNull(rsTemp.getString("short_Name"))`; `:party` ← `DmShared.xNull(rsTemp.getString("party"))`; `:refNo` ← `""`; `:refDate` ← `null`; `:oh_Amt_Net` ← `DmServer.vNull(salesAmt, "0.00")`; `:cumulativeTotal` ← `DmServer.vNull(cumulativeTotal, "0.00")`; `:orderIndex` ← `orderIndex`; `:site_Code` ← `DmShared.xNull(rsTemp.getString("site_Code"))`; `:docId` ← `DmShared.xNull(rsTemp.getString("docId"))`; `:recId` ← `""`; `:v_no` ← `""`; `:v_Prefix` ← `DmShared.xNull(rsTemp.getString("v_Prefix"))`; `:category` ← `""`; `:v_Type` ← `DmShared.xNull(rsTemp.getString("v_Type"))`; `:v_date` ← `null`; `:short_Name` ← `""`; `:party` ← `refNoDate`; `:oh_Amt_Net` ← `"0"`; `:cumulativeTotal` ← `"0"`; `:refNo` ← `DmShared.xNull(rsTemp.getString("refNo"))`; `:refDate` ← `DmServer.convertDate(DmShared.dNull(rsTemp.getDate("refDate")))`
- Errors: "Period difference cannot be greater than 366 days!" | "To Date should be greater than from date!"
- Other calls: `DmJasper.formatAmount`, `PARTY.getCode`, `STServer.getDateDifference`

### `getSalesDetail`
`public DmResultPageing<List<SalesDetailDisplayGrid_Mdl>> getSalesDetail(DisplaySetting_Mdl displaySetting, DmHelper helper, boolean blnToEndOfFile, KeyFieldExtended keyField)`

- SQL 1: `SELECT sb.site_Code, sb.orderIndex, sb.docId, sb.recId , sb.v_No, sb.v_Prefix, sb.v_Type, sb.v_date, sb.short_Name, sb.party , sb.refNo, sb.refDate, vt.mnuAttachedInModule, vt.mnuText , oh_Amt_Net AS salesAmt, sb.cumulativeTotal FROM {…} sb LEFT JOIN voucher_Type vt ON vt.v_Type = sb.v_Type`
- Other calls: `DmJasper.formatAmount`

### `getTable`
`private String getTable(DmRSSPrivate rssPrivate)`


### `getParameter`
`private void getParameter(DisplaySetting_Mdl filter, DmPreparedStatement psTemp)`

- Params: `:fromDate` ← `DmServer.convertDate(filter.getFromDate())`; `:toDate` ← `DmServer.convertDate(filter.getToDate())`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmJasper.server`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.self.client`, `paperbiz.projectMenu.self.shared`, `paperbiz.sales.displaySetting.client`, `paperbiz.sales.displaySetting.shared`, `paperbiz.sales.reports.client`, `paperbiz.sales.salesDetailDisplay.shared`, `paperbiz.selfToolkit.stStatic.server`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| getDefault | `if (dateDiff > 366)` | "Period difference cannot be greater than 366 days!" |
| getDefault | `} else if (dateDiff < 0)` | "To Date should be greater than from date!" |

## Notes (hand-written)
_No hand-written notes yet._
