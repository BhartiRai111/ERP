# pb-dashboard / partyFutureOutstandingDisplay

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/dashBoard/src/partyFutureOutstandingDisplay/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHSite, getTxtHAccountGroup, getTxtHAccountName
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `DisplaySetting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Next Days | DmTextBox | `txtAsOnDate` | `11, true` |
| 2 | - | DmTextBoxNumeric | `txtNextDays` | `0, false, 2, true` |
| 3 | Site | DmTextHelpMulti | `txtHSite` | `KeyField_PrpAcs, 10, true` |
| 4 | Account Group | DmTextHelp | `txtHAccountGroup` | `TxtHAccountGroup_PrpAcs, 10, false` |
| 5 | Account Name | DmTextHelpMulti | `txtHAccountName` | `TxtHAccountName_PrpAcs, 10` |

**Grid / popup columns** (header, width, db field):
- `txtHSite`: Site [Text, w300, name]
- `txtHAccountGroup`: Name [Text, w220, GroupName]; Nature [Text, w150, nature]
- `txtHAccountName`: Name [Text, w250, Name]; Manual Code [Text, w100, ManualCode]; City [Text, w150, City]; Group Name [Text, w80, GroupName]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHAccountGroup` → `getTxtHAccountGroup`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHSite.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHSite(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSite).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHSite.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSite).show();
```

`txtHSite.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSite.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSite).show();
```

`txtHAccountGroup.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHAccountGroup(mainAcGroupCode, helper, blnToEndOfFile, new AsyncCallback<DmRes
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHAccountGroup).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHAccountGroup.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAccountGroup).show();
```

`txtHAccountGroup.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAccountGroup.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAccountGroup).show();
```

`txtHAccountName.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHAccountName(mainAcGroupCode, helper, blnToEndOfFile, new AsyncCallback<DmResu
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHAccountName).show();
public void onSuccess(DmResultPageing<List<Ledger_Model.TxtHAccountName>> result) {
if (result.isStatus()) {
txtHAccountName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAccountName).show();
```

`txtHAccountName.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAccountName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAccountName).show();
```
- Alert: "'As On Date'!"
- Alert: "Site is mandatory!"
- Alert: "'A/c group' is mandatory!"
- Alert: "'No. of days' is mandatory!"
- Alert: "'No. of days' can not be less than or equal to zero!"
- Alert: "'No. of days' can not be greater than 30 days!"

### Screen `PartyFutureOutstandingDisplay`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmTextBox | `txtSearchValue` | `100, false` |

Other widgets (not in tab list): `grdDisplay` (DmGridPaging)

**Grid / popup columns** (header, width, db field):
- `grdDisplay`: Party [Text, w390, name]; Total Dues [Text, w95, totalDueAmt]; Opening [Text, w95, []

**Lookup / suggestion / find sources (server RPC called):**
- `displaySetting` → `getDefault`
- `grdDisplay` → `getPartyFutureOutstanding`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`displaySetting.addCallBack` → `onOk()`
```java
displaySetting_Mdl = popup_Mdl;
refreshGrid();
```

`grdDisplay.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnToEndOfFile).getPartyFutureOutstanding(displaySetting_Mdl, helper, blnToEndOfFile, param, new Asyn
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<PartyFutureOutstandingDisplayGrid_Mdl>> result) {
if (result.isStatus()) {
grdDisplay.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`grdDisplay.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
grdDisplay.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`grdDisplay.addCallBack` → `getParam()`
```java
return null;
```
- Confirm: "Are you sure? You want to close."

## Files
| file | lines |
|---|---|
| client/DisplaySetting.java | 288 |
| client/DisplaySetting_Dsg.java | 286 |
| client/PartyFutureOutstandingDisplay.java | 537 |
| client/PartyFutureOutstandingDisplay_Dsg.java | 274 |
| server/PartyFutureOutstandingDisplay_SrvImpl.java | 921 |
| shared/DefaultValue_Mdl.java | 57 |
| shared/DisplaySetting_Mdl.java | 104 |
| shared/PartyFutureOutstandingDisplayGrid_Mdl.java | 578 |
| shared/PartyFutureOutstandingDisplay_Srv.java | 25 |
| shared/PartyFutureOutstandingDisplay_SrvAsync.java | 25 |

## UI labels / columns (client)
`As On Date`, `Next Days`, `Site`, `Account Group`, `Account Name`

## Models (shared DTOs)
### DisplaySetting_Mdl
| type | field |
|---|---|
| String | asOnDate |
| String | siteCode |
| String | siteName |
| String | acGroupCode |
| String | acGroupName |
| String | acCode |
| String | acName |
| String | nextDays |

### DefaultValue_Mdl
| type | field |
|---|---|
| String fromDate, | toDate |
| String | totalOutstandingAmt |
| String | totalOutstandingAmtDisp |
| List<String> | amtOnDateFieldList |

### PartyFutureOutstandingDisplayGrid_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| boolean | selected |
| String | orderIndex |
| String totalDueAmt, | totalDueAmtDisp |
| String openingAmt, | openingAmtDisp |
| String amtOnDay1, | amtOnDay1Disp |
| String amtOnDay2, | amtOnDay2Disp |
| String amtOnDay3, | amtOnDay3Disp |
| String amtOnDay4, | amtOnDay4Disp |
| String amtOnDay5, | amtOnDay5Disp |
| String amtOnDay6, | amtOnDay6Disp |
| String amtOnDay7, | amtOnDay7Disp |
| String amtOnDay8, | amtOnDay8Disp |
| String amtOnDay9, | amtOnDay9Disp |
| String amtOnDay10, | amtOnDay10Disp |
| String amtOnDay11, | amtOnDay11Disp |
| String amtOnDay12, | amtOnDay12Disp |
| String amtOnDay13, | amtOnDay13Disp |
| String amtOnDay14, | amtOnDay14Disp |
| String amtOnDay15, | amtOnDay15Disp |
| String amtOnDay16, | amtOnDay16Disp |
| String amtOnDay17, | amtOnDay17Disp |
| String amtOnDay18, | amtOnDay18Disp |
| String amtOnDay19, | amtOnDay19Disp |
| String amtOnDay20, | amtOnDay20Disp |
| String amtOnDay21, | amtOnDay21Disp |
| String amtOnDay22, | amtOnDay22Disp |
| String amtOnDay23, | amtOnDay23Disp |
| String amtOnDay24, | amtOnDay24Disp |
| String amtOnDay25, | amtOnDay25Disp |
| String amtOnDay26, | amtOnDay26Disp |
| String amtOnDay27, | amtOnDay27Disp |
| String amtOnDay28, | amtOnDay28Disp |
| String amtOnDay29, | amtOnDay29Disp |
| String amtOnDay30, | amtOnDay30Disp |

## RPC interface
| method | params | returns |
|---|---|---|
| getDefault | String recordType, DisplaySetting_Mdl displaySetting, String loginDate | DmResult<DefaultValue_Mdl> |
| getPartyFutureOutstanding | DisplaySetting_Mdl displaySetting, DmHelper helper, boolean blnToEndOfFile, KeyFieldExtended keyField | DmResultPageing<List<PartyFutureOutstandingDisplayGrid_Mdl>> |
| fillDefaultSettings |  | DmResult<DisplaySetting_Mdl> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAccountGroup | String mainAcGroupCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAccountName | String mainAcGroupCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<Ledger_Model.TxtHAccountName>> |
| printReport | String widgetName, DisplaySetting_Mdl displaySetting | DmResult<KeyFieldExtended> |

## Server `PartyFutureOutstandingDisplay_SrvImpl.java` (921 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnId`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.removeAllCharacterExceptAlphaNumeric`, `DmServer.setReportHeaderExcel`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getDefault`
`public DmResult<DefaultValue_Mdl> getDefault(String recordType, DisplaySetting_Mdl displaySetting_Mdl, String loginDate)`

- SQL 1: `DECLARE @asOnDate DATE = :asOnDate; DECLARE @endDate DATE = :toDate; WITH ctePeriodicity AS ( SELECT CAST(@asOnDate AS DATE) AS cDate, FORMAT(CAST(@asOnDate AS DATE), 'dd-MMM-yyyy') AS fDate UNION ALL SELECT DATEADD(DAY, 1, cDate), FORMAT(DATEADD(DAY, 1, cDate), 'dd-MMM-yyyy') AS fDate FROM ctePeriodicity WHERE cDate < @endDate ) SELECT STRING_AGG(CONCAT('[',cp.fDate,']'),',') AS amtOnDateFieldCSVWithBreaket , STRING_AGG(cp.fDate,',') AS amtOnDateFieldCSV FROM ctePeriodicity cp`
- SQL 2: `IF OBJECT_ID('tempdb.. {…} ') IS NOT NULL DROP TABLE {…} ; CREATE TABLE {…} (orderIndex int , code bigInt, name varchar(250) , totalDueAmt decimal(18, 2) {…} )`
- SQL 3: `DECLARE @asOnDate DATE = :asOnDate; DECLARE @startDate DATE = :startDate; DECLARE @endDate DATE = :endDate; WITH ctePeriodicity AS ( SELECT CAST(@asOnDate AS DATE) AS cDate, FORMAT(CAST(@asOnDate AS DATE), 'dd-MMM-yyyy') AS fDate UNION ALL SELECT DATEADD(DAY, 1, cDate), FORMAT(DATEADD(DAY, 1, cDate), 'dd-MMM-yyyy') AS fDate FROM ctePeriodicity WHERE cDate < @endDate ) , cteLedger AS ( SELECT {…} AS dueAmt , {…} AS dueDate , MAX(lg.subcode) AS subcode , MAX(sg.name) AS partyName , MAX(sg.cityCode) AS cityCode , MAX(cm.cityName) AS cityName FROM ledger lg LEFT JOIN siteMast AS sm ON sm.code = lg.site_Code LEFT JOIN subGroup sg ON sg.subCode = lg.subcode LEFT JOIN city cm ON cm.cityCode = sg.cityCode LEFT JOIN ( SELECT DISTINCT agc.groupCode, agc.groupName FROM acGroup ag INNER JOIN acGroup agc ON CHARINDEX(ISNULL(ag.mainStreamCode, ''), ISNULL(agc.mainStreamCode,''),1) > 0 WHERE 1 = 1 {…} ) ag ON sg.groupCode = ag.groupCode LEFT JOIN ( SELECT ladj.adj_DocId, ladj.adj_V_SNo, SUM(ladj.adjustment) AS adjustmentAmt FROM ledgerAdj ladj GROUP BY ladj.adj_DocId, ladj.adj_V_SNo ) vLadj ON lg.docId = vLadj.adj_DocId AND lg.v_SNo = vLadj.adj_V_SNo WHERE 1 = 1 AND {…} >= @startDate AND {…} <= @endDate {…} GROUP BY lg.subcode, {…} HAVING {…} > 0 UNION ALL SELECT {…} AS dueAmt , @asOnDate AS dueDate , lg.subcode , MAX(sg.name) AS partyName , MAX(sg.cityCode) AS cityCode , MAX(cm.cityName) AS cityName FROM ledger lg LEFT JOIN siteMast AS sm ON sm.code = lg.site_Code LEFT JOIN subGroup sg ON sg.subCode = lg.subcode LEFT JOIN city cm ON cm.cityCode = sg.cityCode LEFT JOIN ( SELECT DISTINCT agc.groupCode, agc.groupName FROM acGroup ag INNER JOIN acGroup agc ON CHARINDEX(ISNULL(ag.mainStreamCode, ''), ISNULL(agc.mainStreamCode,''),1) > 0 WHERE 1 = 1 {…} ) ag ON sg.groupCode = ag.groupCode LEFT JOIN ( SELECT ladj.adj_DocId, ladj.adj_V_SNo, SUM(ladj.adjustment) AS adjustmentAmt FROM ledgerAdj ladj GROUP BY ladj.adj_DocId, ladj.adj_V_SNo ) vLadj ON lg.docId = vLadj.adj_DocId AND lg.v_SNo = vLadj.adj_V_SNo WHERE 1 = 1 AND {…} <= @asOnDate {…} GROUP BY lg.subcode HAVING {…} > 0 ), cteLedgerParty AS ( SELECT clg.subcode, MAX(clg.partyName) AS partyName, MAX(clg.cityCode) AS cityCode, MAX(clg.cityName) AS cityName FROM cteLedger clg GROUP BY clg.subcode ), ctePeriodicityParty AS ( SELECT cp.cDate, cp.fDate, clp.* FROM ctePeriodicity cp, cteLedgerParty clp ) SELECT * FROM ( SELECT cpp.fDate, cpp.subcode, CONCAT(cpp.partyName, ', ', cpp.cityName) AS partyName, clg.dueAmt FROM ctePeriodicityParty cpp LEFT JOIN cteLedger clg ON clg.dueDate = cpp.cDate AND clg.subcode = cpp.subcode -- ORDER BY cpp.partyName, cpp.cDate ) t PIVOT (SUM(dueAmt) FOR fDate IN ( {…} )) AS PivotTable ORDER BY partyName`
- SQL 4: `INSERT INTO {…} ( orderIndex, code, name, totalDueAmt , {…} ) VALUES(:orderIndex, :code, :name, :totalDueAmt {…} )`
- Params: `:asOnDate` ← `DmServer.convertDate(DmShared.dNull(asOnDate))`; `:toDate` ← `DmServer.convertDate(DmShared.dNull(toDate))`; `:asOnDate` ← `DmServer.convertDate(DmShared.dNull(asOnDate))`; `:startDate` ← `DmServer.convertDate(DmShared.dNull(fromDate))`; `:endDate` ← `DmServer.convertDate(DmShared.dNull(toDate))`; `:groupName` ← `CREDITORS_GROUP_NAME`; `:groupName` ← `DEBTORS_GROUP_NAME`; `:orderIndex` ← `++orderIndex`; `:code` ← `DmShared.xNull(rsTemp.getString("subCode"))`; `:name` ← `DmShared.xNull(rsTemp.getString("partyName"))`; `:totalDueAmt` ← `DmServer.vNull(totalOutstandingPartyWise, "0.00")`
- Errors: "'No. of days' can not be greater than 30 days!"
- Other calls: `CalendarUtil.addDaysToDate`, `DmJasper.formatAmount`

### `getPartyFutureOutstanding`
`public DmResultPageing<List<PartyFutureOutstandingDisplayGrid_Mdl>> getPartyFutureOutstanding(DisplaySetting_Mdl displaySetting, DmHelper helper, boolean blnToEndOfFile, KeyFieldExtended keyField)`

- SQL 1: `SELECT lg.* FROM {…} lg`
- Other calls: `DmJasper.formatAmount`

### `getTable`
`private String getTable(DmRSSPrivate rssPrivate)`


### `fillDefaultSettings`
`public DmResult<DisplaySetting_Mdl> fillDefaultSettings()`

- SQL 1: `SELECT name FROM siteMast WHERE code = :code`
- Params: `:code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHAccountGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAccountGroup(String mainAcGroupCode, DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `DashBoard_Server.getTxtHAccountGroup`

### `getTxtHAccountName`
`public DmResultPageing<List<Ledger_Model.TxtHAccountName>> getTxtHAccountName(String mainAcGroupCode, DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `DashBoard_Server.getTxtHAccountName`

### `printReport`
`public DmResult<KeyFieldExtended> printReport(String widgetName, DisplaySetting_Mdl displaySetting)`

- SQL 1: `SELECT lg.orderIndex, lg.code, lg.name, lg.totalDueAmt {…} FROM {…} lg`
- Errors: "Invalid request." | "File Name not given." | "No Record Found!"
- Other calls: `ExcelReport_Server.createAndMergeBlankRows`, `ExcelReport_Server.createAndMergeCells`, `ExcelReport_Server.getCellStyle`, `ExcelReport_Server.getCellStyleHeader`, `ExcelReport_Server.getDataFormat`, `ExcelReport_Server.newCell`, `ExcelReport_Server.printFilterData`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmBase.self.xls`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmJasper.server`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.excelReport.server`, `dataman.dmToolkit.excelReport.shared`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.self.client`, `dataman.libraryMenu.self.shared`, `paperbiz.dashBoard.partyFutureOutstandingDisplay.shared`, `paperbiz.dashBoard.self.server`, `paperbiz.dashBoard.self.shared`, `paperbiz.projectMenu.self.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| getDefault | `if (DmShared.iNull(displaySetting_Mdl.getNextDays()).intValue() > 30)` | "'No. of days' can not be greater than 30 days!" |
| printReport | `if (!DmSession.getSessions().containsKey(sessionId))` | "Invalid request." |
| printReport | `if (DmShared.xNull(fileName).equalsIgnoreCase(""))` | "File Name not given." |
| printReport | `if (!status)` | "No Record Found!" |

## Notes (hand-written)
_No hand-written notes yet._
