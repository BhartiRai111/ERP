# dm-accounts / detailTrialBalance

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/accounts/src/detailTrialBalance/`). Hand-written notes are in the "Notes" section.

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
### Screen `DetailTrialBalance`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmTextBox | `txtSearchValue` | `100, false` |
| 2 | - | DmGridPaging | `grdDisplay` | `GrdDisplay_PrpAcs, 20` |

**Grid / popup columns** (header, width, db field):
- `grdDisplay`: Particulars [Text, w200, SName]; Opening [Text, w200, amtOP];  [Text, w30, amtOP]; Debit [Text, w200, amtDr]; Credit [Text, w200, amtCr]; Closing [Text, w200, amtClosing];  [Text, w30, amtClosing]

**Lookup / suggestion / find sources (server RPC called):**
- `acDisplaySetting` → `getDefault`
- `grdDisplay` → `getAcLedgerDetial`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`acDisplaySetting.addCallBack` → `onOk()`
```java
acDisplaySettingMdl = acDisplaySetting;
refreshGrid();
```

`grdDisplay.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnToEndOfFile).getAcLedgerDetial(helper, blnToEndOfFile, param, new AsyncCallback<DmResultPageing<Li
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<DetailTrialBalance_Mdl>> result) {
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
if ((displayRouteList.size() > 0)) {
((CellSelectionModel) grdDisplay.getDmGrid().getGrid().getSelectionModel()).selectCell(rowIndex, colIndex);
} else {
...
```

`grdDisplay.addCallBack` → `execute()`
```java
int rowIndex = DmShared.iNull(displayRouteList.get((displayRouteList.size() - 1)).getClickRowIndex()).intValue();
int colIndex = DmShared.iNull(displayRouteList.get((displayRouteList.size() - 1)).getClickColIndex()).intValue();
renderSelectionFill(rowIndex, colIndex);
if ((displayRouteList.size() > 0)) {
((CellSelectionModel) grdDisplay.getDmGrid().getGrid().getSelectionModel()).selectCell(rowIndex, colIndex);
} else {
txtSearchValue.setFocus(true);
```

`grdDisplay.addCallBack` → `getParam()`
```java
return setParam();
```

## Files
| file | lines |
|---|---|
| client/DetailTrialBalance.java | 538 |
| client/DetailTrialBalance_Dsg.java | 256 |
| server/DetailTrialBalance_SrvImpl.java | 313 |
| shared/DefaultValue_Mdl.java | 129 |
| shared/DetailTrialBalanceReport_Mdl.java | 87 |
| shared/DetailTrialBalance_Mdl.java | 78 |
| shared/DetailTrialBalance_Srv.java | 27 |
| shared/DetailTrialBalance_SrvAsync.java | 27 |
| shared/GrdDetailTrialBalanceReport_Mdl.java | 85 |
| shared/SearchValue_Mdl.java | 41 |

## Models (shared DTOs)
### DefaultValue_Mdl
| type | field |
|---|---|
| String | dtFrom |
| String | dtTo |
| String | totalDebit |
| String | totalCredit |
| String | amtOpening |
| String | openingDrCr |
| String | amtClosing |
| String | closingDrCr |
| String | amtDifference |
| String | differenceDrCr |
| String | sessionTable |
| boolean | inventoryAccountingActive |
| String | defaultLedgerPeriodicity |

### DetailTrialBalance_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | debit |
| String | credit |
| String | opening |
| String | openingDrCr |
| String | closing |
| String | closingDrCr |
| boolean | selected |

### DetailTrialBalanceReport_Mdl
| type | field |
|---|---|
| String | totalCredit |
| String | totalDebit |
| String | totalDifference |
| String | totalOpening |
| String | openingDrCr |
| String | totalClosing |
| String | closingDrCr |
| String | fromToDate |

### SearchValue_Mdl
| type | field |
|---|---|
| DefaultValue_Mdl | defaultValue |
| AcDisplaySetting_Mdl | acLedgerDisplay |
| String | searchValue |

### GrdDetailTrialBalanceReport_Mdl
| type | field |
|---|---|
| String | amtDr |
| String | amtCr |
| String | particularsTag |
| String | particularsText |
| String | amtOpening |
| String | amtOpDrCr |
| String | amtClosing |
| String | amtClDrCr |

## RPC interface
| method | params | returns |
|---|---|---|
| getDefault | AcDisplaySetting_Mdl acLedgerMdl | DmResult<DefaultValue_Mdl> |
| getAcLedgerDetial | DmHelper helper, boolean blnToEndOfFile, SearchValue_Mdl svm | DmResultPageing<List<DetailTrialBalance_Mdl>> |

## Server `DetailTrialBalance_SrvImpl.java` (313 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnVoucherPrefix`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getDefault`
`public DmResult<DefaultValue_Mdl> getDefault(AcDisplaySetting_Mdl acDisplaySetting)`

- SQL 1: `IF OBJECT_ID('tempdb.. {…} ') IS NOT NULL DROP TABLE {…} ; create table {…} (orderIndex bigint, subCode bigInt, SName varchar(160), amtDr float, amtCr float, amtOP float, amtClosing float)`
- SQL 2: `SELECT SubCode, MAX(SName) AS SName, ISNULL(SUM(OPBal), 0) AS OPBal, ISNULL(SUM(AmtDr), 0) AS AmtDr, ISNULL(SUM(AmtCr), 0) AS AmtCr FROM (`
- SQL 3: `SELECT ISNULL(SG.SubCode, 0) AS SubCode, (ISNULL(MAX(SG.Name), '') + ' - ' + ISNULL(MAX(CT.CityName), '')) AS SName, (ISNULL(SUM(LG.AmtDr), 0) - ISNULL(SUM(LG.AmtCr), 0)) AS OPBal, 0 AS AmtDr, 0 AS AmtCr FROM Ledger LG LEFT JOIN SubGroup SG ON LG.SubCode = SG.SubCode LEFT JOIN City CT ON CT.CityCode = SG.CityCode LEFT JOIN acGroup ag ON ag.groupCode = SG.groupCode {…} WHERE LG.isPosted = 1 {…} GROUP BY ISNULL(SG.SubCode, 0) HAVING (ISNULL(SUM(LG.AmtDr), 0) - ISNULL(SUM(LG.AmtCr), 0)) <> 0 UNION ALL`
- SQL 4: `SELECT ISNULL(SG.SubCode, 0) AS SubCode, (ISNULL(MAX(SG.Name), '') + ' - ' + ISNULL(MAX(CT.CityName), '')) AS SName, 0 AS OPBal,ISNULL(SUM(LG.AmtDr), 0) as AmtDr,ISNULL(SUM(LG.AmtCr), 0) as AmtCr FROM Ledger LG LEFT JOIN SubGroup SG ON LG.SubCode = SG.SubCode LEFT JOIN City CT ON CT.CityCode = SG.CityCode LEFT JOIN acGroup ag ON ag.groupCode = SG.groupCode {…} WHERE LG.isPosted = 1 {…} GROUP BY SG.SubCode`
- SQL 5: `insert into {…} (orderIndex, subCode, SName, amtDr, amtCr, amtOP, amtClosing) values (:orderIndex, :subCode, :name, :amtDr, :amtCr, :amtOP, :amtClosing)`
- SQL 6: `SELECT ec.isInventoryAccountingActive, ec.ac_LedgerPeriodicity FROM enviroCompany ec`
- Params: `:dateFrom` ← `DmServer.convertDate(DmShared.dNull(acDisplaySetting.getDateFrom()))`; `:dateTo` ← `DmServer.convertDate(DmShared.dNull(acDisplaySetting.getDateTo()))`; `:orderIndex` ← `String.valueOf(orderIndex)`; `:subCode` ← `DmShared.iNull(rsTemp.getString("subCode")).toString()`; `:name` ← `DmShared.xNull(rsTemp.getString("SName"))`; `:amtDr` ← `DmServer.vNull(rsTemp.getString("amtDr"), "0.00").toString()`; `:amtCr` ← `DmServer.vNull(rsTemp.getString("amtCr"), "0.00").toString()`; `:amtOP` ← `DmServer.vNull(rsTemp.getString("OPBal"), "0.00").toString()`; `:amtClosing` ← `DmServer.vNull(amtClosing, "0.00").toString()`
- Other calls: `DmJasper.formatAmount`

### `getAcLedgerDetial`
`public DmResultPageing<List<DetailTrialBalance_Mdl>> getAcLedgerDetial(DmHelper helper, boolean blnToEndOfFile, SearchValue_Mdl svm)`

- SQL 1: `SELECT orderIndex, subCode, SName, amtDr, amtCr, amtOP, amtClosing FROM {…}`
- Other calls: `DmJasper.formatAmount`

### `getTable`
`private String getTable(DmRSSPrivate rssPrivate)`


## Depends on (imports from other packages)
`dataman.accounts.acDisplaySetting.client`, `dataman.accounts.acDisplaySetting.shared`, `dataman.accounts.reports.client`, `dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmJasper.server`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.libraryMenu.self.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
