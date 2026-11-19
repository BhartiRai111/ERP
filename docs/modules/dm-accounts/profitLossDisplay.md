# dm-accounts / profitLossDisplay

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/accounts/src/profitLossDisplay/`). Hand-written notes are in the "Notes" section.

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
### Screen `ProfitLossDisplay`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `grdDisplay` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `grdDisplay`: Particulars [Text, w340];  [Text, w150]; Amount [Text, w150]; Particulars [Text, w340];  [Text, w150]; Amount [Text, w150]

**Lookup / suggestion / find sources (server RPC called):**
- `acDisplaySetting` → `getDefault`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`acDisplaySetting.addCallBack` → `onOk()`
```java
acDisplaySettingMdl = acDisplaySetting;
refreshGrid();
```

## Files
| file | lines |
|---|---|
| client/ProfitLossDisplay.java | 522 |
| client/ProfitLossDisplay_Dsg.java | 384 |
| server/ProfitLossDisplay_Server.java | 453 |
| server/ProfitLossDisplay_SrvImpl.java | 36 |
| shared/DefaultValue_Mdl.java | 47 |
| shared/GrdProfitLossReport_Mdl.java | 122 |
| shared/ProfitLossDisplay_Mdl.java | 159 |
| shared/ProfitLossDisplay_Srv.java | 13 |
| shared/ProfitLossDisplay_SrvAsync.java | 26 |
| shared/ProfitLossReport_Mdl.java | 43 |

## Models (shared DTOs)
### ProfitLossDisplay_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String expensesCode, expensesName, | expensesAmount |
| String expensesChildGroupCode, | expensesChildGroupName |
| String expensesChildSubCode, | expensesChildSubName |
| String | expensesChildBalance |
| String revenueCode, revenueName, | revenueAmount |
| String revenueChildGroupCode, | revenueChildGroupName |
| String revenueChildSubCode, | revenueChildSubName |
| String | revenueChildBalance |
| int | selectionType |

### GrdProfitLossReport_Mdl
| type | field |
|---|---|
| String | expensesCode |
| String | expensesName |
| String | isExpensesChild |
| String | isExpensesBold |
| String | expensesChildBalance |
| String | expensesAmount |
| String | revenueCode |
| String | revenueName |
| String | isRevenueChild |
| String | isRevenueBold |
| String | revenueChildBalance |
| String | revenueAmount |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | total |
| List<ProfitLossDisplay_Mdl> | profitLossDisplayList |
| boolean | inventoryAccountingActive |
| String | defaultLedgerPeriodicity |

### ProfitLossReport_Mdl
| type | field |
|---|---|
| String | totalRevenueAmount |
| String | totalExpenseAmount |

## RPC interface
| method | params | returns |
|---|---|---|
| getDefault | AcDisplaySetting_Mdl acDisplaySetting | DmResult<DefaultValue_Mdl> |

## Server `ProfitLossDisplay_SrvImpl.java` (36 lines)
Helpers used: `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmShared.xNull`

### `getDefault`
`public DmResult<DefaultValue_Mdl> getDefault(AcDisplaySetting_Mdl acDisplaySetting)`


## Server `ProfitLossDisplay_Server.java` (453 lines)
Helpers used: `DmServer.convertDate`, `DmServer.getParamList`, `DmServer.getSsnSiteCode`, `DmServer.getSsnVoucherPrefix`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `evaluate`
`protected DmResult<DefaultValue_Mdl> evaluate(Connection cnTemp, AcDisplaySetting_Mdl acDisplaySetting, DmRSSPrivate rss)`

- SQL 1: `SELECT ec.isInventoryAccountingActive, ec.ac_LedgerPeriodicity FROM enviroCompany ec`
- SQL 2: `SELECT l.subcode, MAX(sg.groupCode) AS groupCode, MAX(AG.groupNature) as groupNature , MAX(ag.groupName) AS acGroupName, MAX(sg.name) AS acName , MAX(sg.acGroupDr) AS acGroupDr, MAX(sg.acGroupCr) AS acGroupCr , ISNULL(SUM(l.amtDr),0) - ISNULL(SUM(l.amtCr), 0) AS opening , 0 AS amtDr , 0 AS amtCr FROM ledger l LEFT JOIN subGroup sg ON sg.subCode = l.subcode LEFT JOIN acGroup ag ON ag.groupCode = sg.groupCode WHERE ag.GroupNature IN (2,3,4,5) AND l.v_Prefix = :v_Prefix AND l.v_Date < :dateFrom AND l.isPosted = 1 AND l.site_Code IN ( {…} :siteCode {…} ) GROUP BY l.subcode UNION ALL`
- SQL 3: `SELECT vAg.groupCode AS groupCode , MAX(vAg.groupName) AS gName , CASE WHEN (ISNULL(SUM(vLg.opening),0) +ISNULL(SUM(vLg.amtDr),0) - ISNULL(SUM(vLg.amtCr), 0)) > 0 THEN (ISNULL(SUM(vLg.opening),0) +ISNULL(SUM(vLg.amtDr),0) - ISNULL(SUM(vLg.amtCr), 0)) ELSE 0 END AS amtDr , CASE WHEN (ISNULL(SUM(vLg.opening),0) +ISNULL(SUM(vLg.amtDr),0) - ISNULL(SUM(vLg.amtCr), 0)) < 0 THEN ABS(ISNULL(SUM(vLg.opening),0) +ISNULL(SUM(vLg.amtDr),0) - ISNULL(SUM(vLg.amtCr), 0)) ELSE 0 END AS amtCr , MAX(vAg.GroupNature) AS groupNature , MAX(vAg.sNo) AS sNo FROM ( SELECT ag.groupCode, ag.groupName, agc.groupCode AS childGroupCode, ag.sNo, ag.groupNature FROM ( SELECT ag.groupCode, ag.groupName, ag.sNo, ag.groupNature, ag.mainStreamCode FROM acGroup ag WHERE ag.groupUnder IS NULL {…} AND AG.GroupNature IN (2,3,4,5) ) ag LEFT JOIN acGroup agc ON CHARINDEX(ISNULL(ag.mainStreamCode, ''), ISNULL(agc.mainStreamCode,''),1) > 0 ) vAg LEFT JOIN ( SELECT vL.subcode,MAX(vL.acGroupName) AS acGroupName, MAX(vL.acName) AS acName , MAX(vl.groupNature) as groupNature, MAX(vl.acGroupDr) AS acGroupDr, MAX(vl.acGroupCr) AS acGroupCr , ISNULL(SUM(vL.opening),0) AS opening , CASE WHEN ISNULL(SUM(vL.opening),0) > 0 THEN ISNULL(SUM(vL.opening),0) ELSE 0 END AS openingDr , CASE WHEN ISNULL(SUM(vL.opening),0) < 0 THEN ABS(ISNULL(SUM(vL.opening),0)) ELSE 0 END AS openingCr , ISNULL(SUM(vL.amtDr),0) AS amtDr , ISNULL(SUM(vL.amtCr),0) AS amtCr , CASE WHEN MAX(vL.groupNature) IN (2,3) AND (ISNULL(SUM(vL.amtDr),0) - ISNULL(SUM(vL.amtCr), 0)) > 0 THEN MAX(vL.groupCode) WHEN MAX(vL.groupNature) IN (4,5) AND (ISNULL(SUM(vL.amtDr),0) - ISNULL(SUM(vL.amtCr), 0)) < 0 THEN MAX(vL.groupCode) WHEN MAX(vL.groupNature) IN (2,3) AND (ISNULL(SUM(vL.amtDr),0) - ISNULL(SUM(vL.amtCr), 0)) < 0 THEN CASE WHEN MAX(vL.acGroupCr) IS NOT NULL THEN MAX(vL.acGroupCr) ELSE MAX(vL.groupCode) END WHEN MAX(vL.groupNature) IN (4,5) AND (ISNULL(SUM(vL.amtDr),0) - ISNULL(SUM(vL.amtCr), 0)) > 0 THEN CASE WHEN MAX(vL.acGroupDr) IS NOT NULL THEN MAX(vL.acGroupDr) ELSE MAX(vL.groupCode) END ELSE MAX(vL.groupCode) END groupCode FROM ( {…} SELECT l.subcode, MAX(sg.groupCode) AS groupCode, MAX(AG.groupNature) as groupNature , MAX(ag.groupName) AS acGroupName, MAX(sg.name) AS acName , MAX(sg.acGroupDr) AS acGroupDr, MAX(sg.acGroupCr) AS acGroupCr , 0 AS opening , SUM(l.amtDr) AS amtDr , SUM(l.amtCr) AS amtCr FROM ledger l LEFT JOIN subGroup sg ON sg.subCode = l.subcode LEFT JOIN acGroup ag ON ag.groupCode = sg.groupCode WHERE ag.GroupNature IN (2,3,4,5) AND l.v_Prefix = :v_Prefix AND l.v_Date BETWEEN :dateFrom AND :dateTo AND l.isPosted = 1 AND l.site_Code IN ( {…} :siteCode {…} ) GROUP BY l.subcode ) vL GROUP BY vL.subcode ) vLg ON vLg.groupCode = vAg.childGroupCode WHERE :one = :one {…} GROUP BY vAg.groupCode {…} ORDER BY MAX(CASE when vAg.GroupNature IN (2,4) then 0 ELSE 1 END), groupNature {…}`
- Params: `:one` ← `1`; `:v_Prefix` ← `DmServer.getSsnVoucherPrefix(rss)`; `:dateFrom` ← `DmServer.convertDate(DmShared.dNull(acDisplaySetting.getDateFrom()))`; `:dateTo` ← `DmServer.convertDate(DmShared.dNull(acDisplaySetting.getDateTo()))`
- Other calls: `DmJasper.formatAmount`

### `addDetails`
`private void addDetails(AcDisplaySetting_Mdl acDisplaySetting, String acForCode, String acForName, List<ProfitLossDisplay_Mdl> profitLossList, double amount, Connection cnTemp, DmRSSPrivate dmRss) throws Exception`

- Other calls: `AcGroupDetail_Server.getAcGroupDetailPreparedStatement`, `DmJasper.formatAmount`

### `getEmptyRowIndex`
`private ProfitLossDisplay_Mdl getEmptyRowIndex(List<ProfitLossDisplay_Mdl> profitLossList, double amount)`


## Depends on (imports from other packages)
`dataman.accounts.acDisplaySetting.client`, `dataman.accounts.acDisplaySetting.shared`, `dataman.accounts.acGroupDetail.server`, `dataman.accounts.profitLossDisplay.shared`, `dataman.accounts.reports.client`, `dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmJasper.server`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.self.client`, `dataman.libraryMenu.self.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
