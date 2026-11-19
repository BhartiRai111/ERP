# dm-accounts / acLedgerDetail

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/accounts/src/acLedgerDetail/`). Hand-written notes are in the "Notes" section.

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
### Screen `AcLedgerDetail`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmTextBox | `txtSearchValue` | `100, false` |
| 2 | - | DmGridPaging | `grdDisplay` | `GrdLedger_PrpAcs, 20` |

**Grid / popup columns** (header, width, db field):
- `grdDisplay`: Vr.No. [Text, w180, recId]; Type [Text, w100, v_Desc]; Date [Text, w100, replace(convert(varchar, v_Date, 106), ' ','-')]; Particulars [Text, w250, contraName]; Debit [Text, w130, amtDr]; Credit [Text, w130, amtCr]; Balance [Text, w130, runningBalance];  [Text, w30]

**Lookup / suggestion / find sources (server RPC called):**
- `acDisplaySetting` → `getDefault`
- `grdDisplay` → `getAcLedgerDetial`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`acDisplaySetting.addCallBack` → `onOk()`
```java
acDisplaySettingMdl = acDisplaySetting;
if (acDisplaySetting != null) {
DisplayRoute_Mdl currentDisplayRoute_Mdl = displayRouteList.get(displayRouteList.size() - 1).clone();
displayRouteList.remove(displayRouteList.size() - 1);
currentDisplayRoute_Mdl.setCode(acDisplaySettingMdl.getLedgerAcCode());
currentDisplayRoute_Mdl.setName(acDisplaySettingMdl.getLedgerAcName());
displayRouteList.add(currentDisplayRoute_Mdl);
refreshGrid();
```

`grdDisplay.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnToEndOfFile).getAcLedgerDetial(helper, blnToEndOfFile, param, new AsyncCallback<DmResultPageing<Li
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<AcLedgerDetail_Mdl>> result) {
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
| client/AcLedgerDetail.java | 625 |
| client/AcLedgerDetail_Dsg.java | 287 |
| server/AcLedgerDetail_SrvImpl.java | 405 |
| shared/AcLedgerDetailReport_Mdl.java | 59 |
| shared/AcLedgerDetail_Mdl.java | 122 |
| shared/AcLedgerDetail_Srv.java | 15 |
| shared/AcLedgerDetail_SrvAsync.java | 15 |
| shared/DefaultValue_Mdl.java | 140 |
| shared/GrdAcLedgerDetail_Mdl.java | 114 |

## Models (shared DTOs)
### AcLedgerDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | particulars |
| String | dtVoucher |
| String | voucherType |
| String | drCr |
| String | amtDr |
| String | amtCr |
| String | amtBalance |
| String | moduleName |
| String | menuText |
| String | v_Prefix |
| String | site_Code |
| boolean | selected |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | sessionTable |
| String | dtFrom |
| String | dtTo |
| String | acForCode |
| String | acName |
| String | openingBalance |
| String | openingDrCr |
| String | totalDr |
| String | totalCr |
| String | closingBalance |
| String | closingDrCr |
| String | siteCode |
| boolean | inventoryAccountingActive |
| String | defaultLedgerPeriodicity |

### AcLedgerDetailReport_Mdl
| type | field |
|---|---|
| String | openingBalance |
| String | openingDrCr |
| String | closingBalance |
| String | closingDrCr |
| AcDisplaySetting_Mdl | acDisplaySettingMdl |

### GrdAcLedgerDetail_Mdl
| type | field |
|---|---|
| String | amtDr |
| String | amtCr |
| String | particularsTag |
| String | particularsText |
| String | drCr |
| String | balance |
| String | type |
| String | date |
| String | vrNo |
| String | vrNoTag |
| String | totalBalance |

## RPC interface
| method | params | returns |
|---|---|---|
| getDefault | AcDisplaySetting_Mdl acDisplaySetting, List<DisplayRoute_Mdl> displayRouteList | DmResult<DefaultValue_Mdl> |
| getAcLedgerDetial | DmHelper helper, boolean blnToEndOfFile, KeyFieldExtended keyField | DmResultPageing<List<AcLedgerDetail_Mdl>> |

## Server `AcLedgerDetail_SrvImpl.java` (405 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnVoucherPrefix`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getDefault`
`public DmResult<DefaultValue_Mdl> getDefault(AcDisplaySetting_Mdl acDisplaySetting, List<DisplayRoute_Mdl> displayRouteList)`

- SQL 1: `Select Count(ag.GroupNature) As Cnt From SubGroup SG left join acGroup ag on ag.groupCode = sg.GroupCode Where SG.SubCode = :subCode and ag.GroupNature in (2,3,4,5)`
- SQL 2: `IF OBJECT_ID('tempdb.. {…} ') IS NOT NULL DROP TABLE {…} ; create table {…} (orderIndex bigint, docId bigInt, recId varchar(50), narration varchar(max), amtDr float, amtCr float, runningBalance float, v_Type smallInt, v_Desc varchar(50), v_date date, sno smallInt, contraText varchar(Max), serialNo smallInt, contraName varchar(max), refNo varchar(30), refDate date,mnuAttachedInModule varchar(100),mnuText varchar(100),v_Prefix tinyInt,site_Code smallint)`
- SQL 3: `SELECT '-1' as DocId, '' as Narration, (CASE WHEN (ISNULL(SUM(round(AmtDr,2)), 0) - ISNULL(SUM(round(LG.AmtCr,2)), 0)) > 0 THEN (ISNULL(SUM(round(AmtDr,2)), 0) - ISNULL(SUM(round(LG.AmtCr,2)), 0)) ELSE 0 END) AS AmtDr, (CASE WHEN (ISNULL(SUM(round(AmtCr,2)), 0) - ISNULL(SUM(round(LG.AmtDr,2)), 0)) > 0 THEN (ISNULL(SUM(round(AmtCr,2)), 0) - ISNULL(SUM(round(LG.AmtDr,2)), 0)) ELSE 0 END) AS AmtCr, NULL AS V_No, NULL AS V_Type, NULL as v_Desc, NULL AS V_Date, 0 AS SNo, 'Opening' AS ContraText, 0 AS SerialNo, 'Opening' AS ContraName, NULL AS RefNo, NULL AS RefDate, null as mnuAttachedInModule,null as mnuText,null as v_Prefix,null as site_Code FROM Ledger LG WHERE LG.isPosted = 1 {…} HAVING (ISNULL(SUM(round(AmtDr,2)), 0) - ISNULL(SUM(round(LG.AmtCr,2)), 0)) <> 0 UNION ALL`
- SQL 4: `SELECT LG.DocId, (case when isNull(refNo,'')<>'' then 'Ref No. '+refNo+' ' else '' end) +(case when refDate is not Null then 'Ref Dt. '+FORMAT (refDate, 'dd-MMM-yyyy ')+' ' else '' end) +isNull(LG.Narration,'') as Narration, round(LG.AmtDr,2) as amtDr, round(LG.AmtCr,2) as amtCr, LG.RecId AS V_No, LG.V_Type, VT.short_Name as v_Desc, LG.V_Date, 1 AS SNo, LG.ContraText, VT.SerialNo, SG.Name AS ContraName, LG.RefNo, LG.RefDate,vt.mnuAttachedInModule,vt.mnuText,lg.v_Prefix,lg.site_Code FROM Ledger LG LEFT JOIN Voucher_Type VT ON LG.V_Type = VT.V_Type LEFT JOIN SubGroup SG ON LG.ContraSub = SG.SubCode WHERE LG.isPosted = 1 {…} ORDER BY SNo, V_Date, SerialNo, V_No`
- SQL 5: `insert into {…} (orderIndex, docId, recId, narration, amtDr, amtCr, runningBalance, v_Type, v_Desc, v_date, sno, contraText, serialNo, contraName, refNo, refDate,mnuAttachedInModule,mnuText,v_Prefix,site_Code) values (:orderIndex, :docId, :recId, :narration, :amtDr, :amtCr, :runningBalance, :v_Type, :v_Desc, :v_date, :sno, :contraText, :serialNo, :contraName, :refNo, :refDate,:mnuAttachedInModule,:mnuText,:v_Prefix,:site_Code)`
- SQL 6: `insert into {…} (orderIndex, docId, contraName, mnuAttachedInModule, mnuText, v_Prefix, site_Code) values (:orderIndex, :docId, :contraName, :mnuAttachedInModule,:mnuText,:v_Prefix,:site_Code)`
- SQL 7: `insert into {…} (orderIndex, docId, contraName, mnuAttachedInModule, mnuText, v_Prefix, site_Code) values (:orderIndex, :docId, :contraName, :mnuAttachedInModule,:mnuText,:v_Prefix,:site_Code)`
- SQL 8: `SELECT ec.isInventoryAccountingActive, ec.ac_LedgerPeriodicity FROM enviroCompany ec`
- Params: `:subCode` ← `DmShared.iNull(dvm.getAcForCode()).toString()`; `:dateFrom` ← `DmServer.convertDate(DmShared.dNull(acDisplaySetting.getDateFrom()))`; `:dateTo` ← `DmServer.convertDate(DmShared.dNull(acDisplaySetting.getDateTo()))`; `:subCode` ← `DmShared.xNull(DmShared.xNull(dvm.getAcForCode()))`; `:orderIndex` ← `String.valueOf(orderIndex)`; `:docId` ← `DmShared.iNull(rsTemp.getString("docId")).toString()`; `:recId` ← `DmShared.xNull(rsTemp.getString("v_No"))`; `:narration` ← `DmShared.xNull(rsTemp.getString("narration"))`; `:v_Type` ← `DmShared.iNull(rsTemp.getString("v_Type")).toString()`; `:v_desc` ← `DmShared.xNull(rsTemp.getString("v_desc"))`; `:v_date` ← `DmServer.convertDate(DmShared.dNull(rsTemp.getDate("v_date")))`; `:sno` ← `DmShared.iNull(rsTemp.getString("sno")).toString()`; `:contraText` ← `DmShared.xNull(rsTemp.getString("contraText"))`; `:serialNo` ← `DmShared.iNull(rsTemp.getString("serialNo")).toString()`; `:contraName` ← `DmShared.xNull(rsTemp.getString("narration"))`; `:contraName` ← `DmShared.xNull(contraTextLines[0])`; `:contraName` ← `DmShared.xNull(rsTemp.getString("contraName"))`; `:refNo` ← `DmShared.xNull(rsTemp.getString("refNo"))`; `:refDate` ← `DmServer.convertDate(DmShared.dNull(rsTemp.getDate("refDate")))`; `:amtDr` ← `DmServer.vNull(amtDr, "0.00")`; `:amtCr` ← `DmServer.vNull(amtCr, "0.00")`; `:runningBalance` ← `DmServer.vNull(runningBalance, "0.00")`; `:mnuAttachedInModule` ← `DmShared.xNull(rsTemp.getString("mnuAttachedInModule"))`; `:mnuText` ← `DmShared.xNull(rsTemp.getString("mnuText"))`; `:v_Prefix` ← `DmShared.iNull(rsTemp.getString("v_Prefix")).toString()`; `:site_Code` ← `DmShared.iNull(rsTemp.getString("site_Code")).toString()`; `:orderIndex` ← `String.valueOf(orderIndex)`; `:docId` ← `DmShared.iNull(rsTemp.getString("docId")).toString()`; `:contraName` ← `DmShared.xNull(DmShared.xNull(rsTemp.getString("narration")))`; `:mnuAttachedInModule` ← `DmShared.xNull(rsTemp.getString("mnuAttachedInModule"))`; `:mnuText` ← `DmShared.xNull(rsTemp.getString("mnuText"))`; `:v_Prefix` ← `DmShared.iNull(rsTemp.getString("v_Prefix")).toString()`; `:site_Code` ← `DmShared.iNull(rsTemp.getString("site_Code")).toString()`; `:orderIndex` ← `String.valueOf(orderIndex)`; `:docId` ← `DmShared.iNull(rsTemp.getString("docId")).toString()`; `:contraName` ← `DmShared.xNull(contraTextLines[i])`; `:mnuAttachedInModule` ← `DmShared.xNull(rsTemp.getString("mnuAttachedInModule"))`; `:mnuText` ← `DmShared.xNull(rsTemp.getString("mnuText"))`; `:v_Prefix` ← `DmShared.iNull(rsTemp.getString("v_Prefix")).toString()`; `:site_Code` ← `DmShared.iNull(rsTemp.getString("site_Code")).toString()`
- Errors: "Multiple account selection not allowed!"
- Other calls: `DmJasper.formatAmount`

### `getAcLedgerDetial`
`public DmResultPageing<List<AcLedgerDetail_Mdl>> getAcLedgerDetial(DmHelper helper, boolean blnToEndOfFile, KeyFieldExtended keyField)`

- SQL 1: `select orderIndex, docId, ISNULL(recId, '') AS recId, narration, amtDr, amtCr, runningBalance, v_Type, v_Desc, v_date, sno, contraText,serialNo, contraName, refNo, refDate,mnuAttachedInModule,mnuText,v_Prefix,site_Code from {…}`
- Other calls: `DmJasper.formatAmount`

### `getTable`
`private String getTable(DmRSSPrivate rssPrivate)`


## Depends on (imports from other packages)
`dataman.accounts.acDisplaySetting.client`, `dataman.accounts.acDisplaySetting.shared`, `dataman.accounts.reports.client`, `dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmJasper.server`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.self.client`, `dataman.libraryMenu.self.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| getDefault | `if (DmShared.xNull(dvm.getAcForCode()).indexOf(",") > 0)` | "Multiple account selection not allowed!" |

## Notes (hand-written)
_No hand-written notes yet._
