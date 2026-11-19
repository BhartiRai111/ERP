# dm-accounts / acGroupDetail

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/accounts/src/acGroupDetail/`). Hand-written notes are in the "Notes" section.

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
### Screen `AcGroupDetail`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmTextBox | `txtSearchValue` | `100, false` |
| 2 | - | DmGridPaging | `grdDisplay` | `GrdDisplay_PrpAcs, 20` |

**Grid / popup columns** (header, width, db field):
- `grdDisplay`: Particulars [Text, w500, subName]; Opening Balance [Text, w175, amtOpening];  [Text, w30, amtOpeningDrCr]; Debit [Text, w175, amtDr]; Credit [Text, w175, amtCr]; Closing Balance [Text, w175, amtClosing];  [Text, w30, amtClosingDrCr]

**Lookup / suggestion / find sources (server RPC called):**
- `acDisplaySetting` → `getDefault`
- `grdDisplay` → `getAcGroupDetial`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`acDisplaySetting.addCallBack` → `onOk()`
```java
if (acDisplaySetting != null) {
acDisplaySettingMdl = acDisplaySetting;
DisplayRoute_Mdl currentDisplayRoute_Mdl = displayRouteList.get(displayRouteList.size() - 1).clone();
displayRouteList.remove(displayRouteList.size() - 1);
if (currentDisplayRoute_Mdl != null) {
if (currentDisplayRoute_Mdl.isLedgerPeriodicSummary()
&& !DmShared.xNull(acDisplaySettingMdl.getPeriodicityCode()).trim().equalsIgnoreCase("")) {
currentDisplayRoute_Mdl.setCode(acDisplaySettingMdl.getLedgerAcCode());
currentDisplayRoute_Mdl.setName(acDisplaySettingMdl.getLedgerAcName());
} else {
...
```

`acDisplaySetting.addCallBack` → `onClose()`
```java
refreshGrid();
```

`grdDisplay.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnToEndOfFile).getAcGroupDetial(helper, blnToEndOfFile, param, new AsyncCallback<DmResultPageing<Lis
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<AcGroupDetail_Mdl>> result) {
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
| client/AcGroupDetail.java | 696 |
| client/AcGroupDetail_Dsg.java | 387 |
| server/AcGroupDetail_Server.java | 406 |
| server/AcGroupDetail_SrvImpl.java | 304 |
| shared/AcGroupDetail_Mdl.java | 122 |
| shared/AcGroupDetail_Srv.java | 15 |
| shared/AcGroupDetail_SrvAsync.java | 15 |
| shared/DefaultValue_Mdl.java | 130 |
| shared/GrdAcGroupDetail_Mdl.java | 68 |
| shared/RptAcGroupDetail_Mdl.java | 86 |

## Models (shared DTOs)
### DefaultValue_Mdl
| type | field |
|---|---|
| String | sessionTable |
| String | dateFrom |
| String | dateTo |
| String | acForCode |
| String | acName |
| String | totalDr |
| String | totalCr |
| String | totalOpening |
| String | totalOpeningDrCr |
| String | totalClosing |
| String | totalClosingDrCr |
| boolean | inventoryAccountingActive |
| String | defaultLedgerPeriodicity |

### RptAcGroupDetail_Mdl
| type | field |
|---|---|
| String | totalCredit |
| String | totalDebit |
| String | totalOpening |
| String | totalOpeningCrDr |
| String | totalClosing |
| String | totalClosingCrDr |
| String | heading |
| AcDisplaySetting_Mdl | acDisplaySettingMdl |

### GrdAcGroupDetail_Mdl
| type | field |
|---|---|
| String | amtDr |
| String | amtCr |
| String | particularsTag |
| String | particularsText |
| String | amtBalance |
| boolean | acgroup |

### AcGroupDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | subCode |
| String | subName |
| String | opening |
| String | openingDrCr |
| String | debit |
| String | credit |
| String | closing |
| String | closingDrCr |
| String | parentSrNo |
| String | childSrNo |
| String | showDetail |
| boolean | selected |

## RPC interface
| method | params | returns |
|---|---|---|
| getDefault | AcDisplaySetting_Mdl acDisplaySetting, List<DisplayRoute_Mdl> displayRouteList | DmResult<DefaultValue_Mdl> |
| getAcGroupDetial | DmHelper helper, boolean blnToEndOfFile, KeyFieldExtended keyField | DmResultPageing<List<AcGroupDetail_Mdl>> |

## Server `AcGroupDetail_Server.java` (406 lines)
Helpers used: `DmServer.convertDate`, `DmServer.getParamList`, `DmServer.getSsnSiteCode`, `DmServer.getSsnVoucherPrefix`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.xNull`

### `getAcGroupDetailPreparedStatement`
`public static DmPreparedStatement getAcGroupDetailPreparedStatement(AcDisplaySetting_Mdl acDisplaySetting, String acForCode, boolean isLedgerPeriodicSummary, Connection cnTemp, DmRSSPrivate rssPrivate) throws Exception`

- SQL 1: `SELECT vAg.groupCode, MAX(vAg.groupName) AS groupName , MAX(CASE WHEN vAg.groupCode = :groupCode THEN vLg.subCode ELSE NULL END) AS subCode , MAX(CASE WHEN vAg.groupCode = :groupCode THEN vLg.acName ELSE vAg.groupName END) AS subName , SUM(vLg.amtDr) AS amtDr , SUM(vLg.amtCr) AS amtCr , (ISNULL(SUM(vLg.opAmtDr), 0) - ISNULL(SUM(vLg.opAmtCr), 0)) AS opening , (ISNULL(SUM(vLg.opAmtDr), 0) - ISNULL(SUM(vLg.opAmtCr), 0)) + (ISNULL(SUM(vLg.amtDr), 0) - ISNULL(SUM(vLg.amtCr), 0)) AS balance FROM ( SELECT ag.groupCode, ag.groupCode AS childGroupCode, ag.groupName FROM acGroup ag WHERE ag.groupCode = :groupCode UNION SELECT ag.groupCode, agc.groupCode AS childGroupCode, ag.groupName FROM acGroup ag LEFT JOIN acGroup agc ON CHARINDEX(ISNULL(ag.mainStreamCode, ''), ISNULL(agc.mainStreamCode,''),1) > 0 WHERE ag.groupUnder = :groupCode ) vAg LEFT JOIN ( SELECT lg.subcode, MAX(sg.name) AS acName , SUM(CASE WHEN lg.v_Date < :dateFrom THEN lg.amtDr ELSE 0 END) AS opAmtDr , SUM(CASE WHEN lg.v_Date < :dateFrom THEN lg.amtCr ELSE 0 END) AS opAmtCr , SUM(CASE WHEN lg.v_Date >= :dateFrom THEN lg.amtDr ELSE 0 END) AS amtDr , SUM(CASE WHEN lg.v_Date >= :dateFrom THEN lg.amtCr ELSE 0 END) AS amtCr , CASE WHEN MAX(ag.groupNature) IN (1,2,3) AND (ISNULL(SUM(lg.amtDr),0) - ISNULL(SUM(lg.amtCr), 0)) > 0 THEN MAX(sg.groupCode) WHEN MAX(ag.groupNature) IN (0,4,5) AND (ISNULL(SUM(lg.amtDr),0) - ISNULL(SUM(lg.amtCr), 0)) < 0 THEN MAX(sg.groupCode) WHEN MAX(ag.groupNature) IN (1,2,3) AND (ISNULL(SUM(lg.amtDr),0) - ISNULL(SUM(lg.amtCr), 0)) < 0 THEN CASE WHEN MAX(sg.acGroupCr) IS NOT NULL THEN MAX(sg.acGroupCr) ELSE MAX(sg.groupCode) END WHEN MAX(ag.groupNature) IN (0,4,5) AND (ISNULL(SUM(lg.amtDr),0) - ISNULL(SUM(lg.amtCr), 0)) > 0 THEN CASE WHEN MAX(sg.acGroupDr) IS NOT NULL THEN MAX(sg.acGroupDr) ELSE MAX(sg.groupCode) END ELSE MAX(sg.groupCode) END groupCode FROM ledger lg LEFT JOIN subGroup sg ON sg.subcode = lg.subCode LEFT JOIN acGroup ag ON ag.groupCode = sg.groupCode WHERE lg.isPosted = 1 {…} GROUP BY lg.subcode ) vLg ON vLg.groupCode = vAg.childGroupCode GROUP BY vAg.groupCode, (CASE WHEN vAg.groupCode = :groupCode THEN vLg.subCode ELSE NULL END) HAVING MAX(CASE WHEN vAg.groupCode = :groupCode AND vLg.subCode IS NULL THEN 0 ELSE 1 END) = 1 {…} ORDER BY MAX(CASE WHEN vAg.groupCode = :groupCode THEN CONCAT('1',vLg.acName) ELSE CONCAT('2',vAg.groupName) END)`
- SQL 2: `SELECT CAST(' {…} ' AS DATE) as code, ' {…} ' AS particular,' {…} ' AS groupName, ' {…} ' AS subCode`
- SQL 3: `SELECT CAST(' {…} ' AS DATE) as code, ' {…} ' AS particular,' {…} ' AS groupName, ' {…} ' AS subCode`
- SQL 4: `SELECT CAST(' {…} ' AS DATE) AS code , SUM(lg.amtDr) AS amtDr, SUM(lg.amtCr) AS amtCr , CASE WHEN MAX(ag.groupNature) IN (1,2,3) AND (ISNULL(SUM(lg.amtDr),0) - ISNULL(SUM(lg.amtCr), 0)) > 0 THEN MAX(sg.groupCode) WHEN MAX(ag.groupNature) IN (0,4,5) AND (ISNULL(SUM(lg.amtDr),0) - ISNULL(SUM(lg.amtCr), 0)) < 0 THEN MAX(sg.groupCode) WHEN MAX(ag.groupNature) IN (1,2,3) AND (ISNULL(SUM(lg.amtDr),0) - ISNULL(SUM(lg.amtCr), 0)) < 0 THEN CASE WHEN MAX(sg.acGroupCr) IS NOT NULL THEN MAX(sg.acGroupCr) ELSE MAX(sg.groupCode) END WHEN MAX(ag.groupNature) IN (0,4,5) AND (ISNULL(SUM(lg.amtDr),0) - ISNULL(SUM(lg.amtCr), 0)) > 0 THEN CASE WHEN MAX(sg.acGroupDr) IS NOT NULL THEN MAX(sg.acGroupDr) ELSE MAX(sg.groupCode) END ELSE MAX(sg.groupCode) END groupCode FROM ledger lg LEFT JOIN subGroup sg ON sg.subcode = lg.subCode LEFT JOIN acGroup ag ON ag.groupCode = sg.groupCode WHERE lg.isPosted = 1 AND lg.subcode = :forSubcode {…} UNION ALL`
- SQL 5: `SELECT t.code AS groupCode , MAX(t.subCode) AS subCode , MAX(t.particular) AS subName , CONCAT(MAX(t.groupName),'|',MAX(sg.name)) AS groupName , MAX(sg.name) AS acName , SUM(vLg.amtDr) AS amtDr, SUM(vLg.amtCr) AS amtCr , ISNULL(SUM(vLg.amtDr), 0) - ISNULL(SUM(vLg.amtCr), 0) AS balance FROM ( {…} ) as t LEFT JOIN ( {…} SELECT {…} AS code , SUM(lg.amtDr) AS amtDr, SUM(lg.amtCr) AS amtCr , CASE WHEN MAX(ag.groupNature) IN (1,2,3) AND (ISNULL(SUM(lg.amtDr),0) - ISNULL(SUM(lg.amtCr), 0)) > 0 THEN MAX(sg.groupCode) WHEN MAX(ag.groupNature) IN (0,4,5) AND (ISNULL(SUM(lg.amtDr),0) - ISNULL(SUM(lg.amtCr), 0)) < 0 THEN MAX(sg.groupCode) WHEN MAX(ag.groupNature) IN (1,2,3) AND (ISNULL(SUM(lg.amtDr),0) - ISNULL(SUM(lg.amtCr), 0)) < 0 THEN CASE WHEN MAX(sg.acGroupCr) IS NOT NULL THEN MAX(sg.acGroupCr) ELSE MAX(sg.groupCode) END WHEN MAX(ag.groupNature) IN (0,4,5) AND (ISNULL(SUM(lg.amtDr),0) - ISNULL(SUM(lg.amtCr), 0)) > 0 THEN CASE WHEN MAX(sg.acGroupDr) IS NOT NULL THEN MAX(sg.acGroupDr) ELSE MAX(sg.groupCode) END ELSE MAX(sg.groupCode) END groupCode FROM ledger lg LEFT JOIN subGroup sg ON sg.subcode = lg.subCode LEFT JOIN acGroup ag ON ag.groupCode = sg.groupCode WHERE lg.isPosted = 1 AND lg.subcode = :forSubcode {…} GROUP BY {…} ) vLg ON vLg.code = t.code LEFT JOIN subGroup sg ON sg.subCode = t.subCode GROUP BY t.code ORDER BY t.code`
- Params: `:dateFrom` ← `DmServer.convertDate(DmShared.dNull(acDisplaySetting.getDateFrom()))`; `:dateTo` ← `DmServer.convertDate(DmShared.dNull(acDisplaySetting.getDateTo()))`; `:dateFrom` ← `DmServer.convertDate(DmShared.dNull(acDisplaySetting.getDateFrom()))`; `:groupCode` ← `DmShared.xNull(DmShared.xNull(tempAcGroupCode))`; `:forSubcode` ← `DmShared.xNull(tempAccountCode)`
- Errors: "Multiple group selection not allowed!" | "Multiple account selection not allowed!"

### `getTable`
`public static String getTable(DmRSSPrivate rssPrivate)`


## Server `AcGroupDetail_SrvImpl.java` (304 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getServerDateTime`, `DmServer.getSsnDBName`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getDefault`
`public DmResult<DefaultValue_Mdl> getDefault(AcDisplaySetting_Mdl acDisplaySetting, List<DisplayRoute_Mdl> displayRouteList)`

- SQL 1: `IF OBJECT_ID('tempdb.. {…} ') IS NOT NULL DROP TABLE {…} ; create table {…} (groupCode varchar(50), groupName varchar(250), subCode bigInt, subName varchar(250) , amtOpening float, amtDr float, amtCr float, amtClosing float, parentSrNo int, childSrNo int, showDetail int)`
- SQL 2: `INSERT INTO {…} (groupCode, groupName, subCode, subName, amtOpening, amtDr, amtCr,amtClosing,parentSrNo,childSrNo, showDetail) VALUES (:groupCode, :groupName, :subCode, :subName, :amtOpening, :amtDr, :amtCr,:amtClosing,:parentSrNo,:childSrNo, :showDetail)`
- SQL 3: `insert into {…} (groupCode, groupName, subCode, subName, amtOpening, amtDr, amtCr,amtClosing, parentSrNo,childSrNo, showDetail ) values (:groupCode, :groupName, :subCode, :subName, :amtOpening, :amtDr, :amtCr,:amtClosing , :parentSrNo, :childSrNo, :showDetail )`
- SQL 4: `SELECT ec.isInventoryAccountingActive, ec.ac_LedgerPeriodicity FROM enviroCompany ec`
- Params: `:groupCode` ← `childGroupCode`; `:groupName` ← `DmShared.xNull(rsTemp.getString("groupName"))`; `:subCode` ← `DmShared.xNull(rsTemp.getString("subCode"))`; `:subName` ← `DmShared.xNull(rsTemp.getString("subName"))`; `:amtDr` ← `DmServer.vNull(amtDr, "0.00")`; `:amtCr` ← `DmServer.vNull(amtCr, "0.00")`; `:amtClosing` ← `DmServer.vNull(closingAmt, "0.00")`; `:parentSrNo` ← `DmShared.iNull(++parentSrNo).intValue()`; `:childSrNo` ← `DmShared.iNull(childSrNo).intValue()`; `:showDetail` ← `DmShared.iNull(acDisplaySetting.getShowDetail()).intValue() == 1 ? 1 : 0`; `:amtOpening` ← `DmServer.vNull(openingAmt, "0.00")`; `:groupCode` ← `DmShared.xNull(rsTemp2.getString("GroupCode"))`; `:groupName` ← `DmShared.xNull(rsTemp2.getString("groupName"))`; `:subCode` ← `DmShared.xNull(rsTemp2.getString("subCode"))`; `:subName` ← `DmShared.xNull(rsTemp2.getString("subName"))`; `:amtDr` ← `DmServer.vNull(rsTemp2.getString("AmtDr"), "0.00")`; `:amtCr` ← `DmServer.vNull(rsTemp2.getString("AmtCr"), "0.00")`; `:amtClosing` ← `DmServer.vNull(closingAmt, "0.00")`; `:parentSrNo` ← `DmShared.iNull(parentSrNo).intValue()`; `:childSrNo` ← `DmShared.iNull(++childSrNo).intValue()`; `:showDetail` ← `DmShared.iNull(acDisplaySetting.getShowDetail()).intValue() == 1 ? 1 : 0`; `:amtOpening` ← `DmServer.vNull(openingAmt, "0.00")`
- Other calls: `AcGroupDetail_Server.getAcGroupDetailPreparedStatement`, `AcGroupDetail_Server.getTable`, `DmJasper.formatAmount`

### `getAcGroupDetial`
`public DmResultPageing<List<AcGroupDetail_Mdl>> getAcGroupDetial(DmHelper helper, boolean blnToEndOfFile, KeyFieldExtended keyField)`

- SQL 1: `SELECT t.groupCode, t.groupName, t.subCode, t.subName , t.amtDr, t.amtCr , ABS(ISNULL(t.amtOpening,0)) AS amtOpening , ABS(ISNULL(t.amtClosing,0)) AS amtClosing , (CASE WHEN ISNULL(t.amtOpening,0) < 0 THEN 'Cr' WHEN ISNULL(t.amtOpening,0) > 0 THEN 'Dr' ELSE '' END) AS amtOpeningDrCr , (CASE WHEN ISNULL(t.amtClosing,0) < 0 THEN 'Cr' WHEN ISNULL(t.amtClosing,0) > 0 THEN 'Dr' ELSE '' END) AS amtClosingDrCr , t.parentSrNo, t.childSrNo, t.showDetail FROM {…} AS t`
- Other calls: `AcGroupDetail_Server.getTable`, `DmJasper.formatAmount`

## Depends on (imports from other packages)
`dataman.accounts.acDisplaySetting.client`, `dataman.accounts.acDisplaySetting.shared`, `dataman.accounts.reports.client`, `dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmJasper.server`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.self.client`, `dataman.libraryMenu.self.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
