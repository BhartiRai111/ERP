# dm-accounts / trialBalance

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/accounts/src/trialBalance/`). Hand-written notes are in the "Notes" section.

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
### Screen `TrialBalance`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `grdDisplay` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `grdDisplay`: Particulars [Text, w575];  [Text, w200]; Debit [Text, w200]; Credit [Text, w200]

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
| client/TrialBalance.java | 432 |
| client/TrialBalance_Dsg.java | 296 |
| server/TrialBalance_SrvImpl.java | 304 |
| shared/DefaultValue_Mdl.java | 100 |
| shared/GrdTrialBalanceReport_Mdl.java | 76 |
| shared/TrialBalanceReport_Mdl.java | 60 |
| shared/TrialBalance_Mdl.java | 86 |
| shared/TrialBalance_Srv.java | 24 |
| shared/TrialBalance_SrvAsync.java | 25 |

## Models (shared DTOs)
### TrialBalance_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | childGroupCode |
| String | childGroupName |
| String | childSubCode |
| String | childSubName |
| String | childBalance |
| String | debit |
| String | credit |
| boolean | selected |

### TrialBalanceReport_Mdl
| type | field |
|---|---|
| String | fromToDate |
| String | totalCredit |
| String | totalDebit |
| String | totalDifference |
| List<GrdTrialBalanceReport_Mdl> | listTrialBalance |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | dtTo |
| String | dtFrom |
| String | isDifference |
| String | amtDr |
| String | isDrCr |
| String | amtCr |
| String | amtDiff |
| List<TrialBalance_Mdl> | lstTrialBalane |
| boolean | inventoryAccountingActive |
| String | defaultLedgerPeriodicity |

### GrdTrialBalanceReport_Mdl
| type | field |
|---|---|
| String | particularsTag |
| String | particularsText |
| String | isChild |
| String | isBold |
| String | childBalance |
| String | amtDr |
| String | amtCr |

## RPC interface
| method | params | returns |
|---|---|---|
| getDefault | AcDisplaySetting_Mdl acDisplaySetting | DmResult<DefaultValue_Mdl> |

## Server `TrialBalance_SrvImpl.java` (304 lines)
Helpers used: `DmServer.convertDate`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnVoucherPrefix`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getDefault`
`public DmResult<DefaultValue_Mdl> getDefault(AcDisplaySetting_Mdl acDisplaySetting)`

- SQL 1: `SELECT l.subcode, MAX(sg.groupCode) AS groupCode, MAX(AG.groupNature) as groupNature , MAX(ag.groupName) AS acGroupName, MAX(sg.name) AS acName, MAX(sg.nature) AS nature , MAX(sg.acGroupDr) AS acGroupDr, MAX(sg.acGroupCr) AS acGroupCr , ISNULL(SUM(l.amtDr),0) - ISNULL(SUM(l.amtCr), 0) AS opening , 0 AS amtDr , 0 AS amtCr FROM ledger l LEFT JOIN subGroup sg ON sg.subCode = l.subcode LEFT JOIN acGroup ag ON ag.groupCode = sg.groupCode WHERE ( CASE WHEN ISNULL(AG.GroupNature,'') IN ('2','3','4','5') THEN l.v_Prefix ELSE :v_Prefix END) = :v_Prefix AND l.v_Date < :dateFrom AND l.isPosted = 1 AND l.site_Code IN ( {…} :siteCode {…} ) GROUP BY l.subcode UNION ALL`
- SQL 2: `SELECT ec.isInventoryAccountingActive, ec.ac_LedgerPeriodicity FROM enviroCompany ec`
- SQL 3: `SELECT vAg.groupCode , MAX(vAg.groupName) AS groupName , MAX(vAg.sNo) AS sNo , MAX(vAg.GroupNature) AS groupNature , SUM(vLg.openingDr) AS openingDr , SUM(vLg.openingCr) AS openingCr , SUM(vLg.amtDr) AS amtDr , SUM(vLg.amtCr) AS amtCr , CASE WHEN (ISNULL(SUM(vLg.opening),0) +ISNULL(SUM(vLg.amtDr),0) - ISNULL(SUM(vLg.amtCr), 0)) > 0 THEN (ISNULL(SUM(vLg.opening),0) +ISNULL(SUM(vLg.amtDr),0) - ISNULL(SUM(vLg.amtCr), 0)) ELSE 0 END AS balanceDr , CASE WHEN (ISNULL(SUM(vLg.opening),0) +ISNULL(SUM(vLg.amtDr),0) - ISNULL(SUM(vLg.amtCr), 0)) < 0 THEN ABS(ISNULL(SUM(vLg.opening),0) +ISNULL(SUM(vLg.amtDr),0) - ISNULL(SUM(vLg.amtCr), 0)) ELSE 0 END AS balanceCr FROM ( SELECT ag.groupCode, ag.groupName, agc.groupCode AS childGroupCode, ag.sNo, ag.groupNature FROM ( SELECT ag.groupCode, ag.groupName, ag.sNo, ag.groupNature, ag.mainStreamCode FROM acGroup ag WHERE ag.groupUnder IS NULL ) ag LEFT JOIN acGroup agc ON CHARINDEX(ISNULL(ag.mainStreamCode, ''), ISNULL(agc.mainStreamCode,''),1) > 0 WHERE :one = :one {…} ) vAg LEFT JOIN ( SELECT vL.subcode,MAX(vL.acGroupName) AS acGroupName, MAX(vL.acName) AS acName, MAX(vL.nature) AS nature , MAX(vl.groupNature) as groupNature, MAX(vl.acGroupDr) AS acGroupDr, MAX(vl.acGroupCr) AS acGroupCr , ISNULL(SUM(vL.opening),0) AS opening , CASE WHEN ISNULL(SUM(vL.opening),0) > 0 THEN ISNULL(SUM(vL.opening),0) ELSE 0 END AS openingDr , CASE WHEN ISNULL(SUM(vL.opening),0) < 0 THEN ABS(ISNULL(SUM(vL.opening),0)) ELSE 0 END AS openingCr , ISNULL(SUM(vL.amtDr),0) AS amtDr , ISNULL(SUM(vL.amtCr),0) AS amtCr , CASE WHEN MAX(vL.groupNature) IN (1,2,3) AND (ISNULL(SUM(vL.amtDr),0) - ISNULL(SUM(vL.amtCr), 0)) > 0 THEN MAX(vL.groupCode) WHEN MAX(vL.groupNature) IN (0,4,5) AND (ISNULL(SUM(vL.amtDr),0) - ISNULL(SUM(vL.amtCr), 0)) < 0 THEN MAX(vL.groupCode) WHEN MAX(vL.groupNature) IN (1,2,3) AND (ISNULL(SUM(vL.amtDr),0) - ISNULL(SUM(vL.amtCr), 0)) < 0 THEN CASE WHEN MAX(vL.acGroupCr) IS NOT NULL THEN MAX(vL.acGroupCr) ELSE MAX(vL.groupCode) END WHEN MAX(vL.groupNature) IN (0,4,5) AND (ISNULL(SUM(vL.amtDr),0) - ISNULL(SUM(vL.amtCr), 0)) > 0 THEN CASE WHEN MAX(vL.acGroupDr) IS NOT NULL THEN MAX(vL.acGroupDr) ELSE MAX(vL.groupCode) END ELSE MAX(vL.groupCode) END groupCode FROM ( {…} SELECT l.subcode, MAX(sg.groupCode) AS groupCode, MAX(AG.groupNature) as groupNature , MAX(ag.groupName) AS acGroupName, MAX(sg.name) AS acName, MAX(sg.nature) AS nature , MAX(sg.acGroupDr) AS acGroupDr, MAX(sg.acGroupCr) AS acGroupCr , 0 AS opening , SUM(l.amtDr) AS amtDr , SUM(l.amtCr) AS amtCr FROM ledger l LEFT JOIN subGroup sg ON sg.subCode = l.subcode LEFT JOIN acGroup ag ON ag.groupCode = sg.groupCode WHERE ( CASE WHEN ISNULL(AG.GroupNature,'') IN ('2','3','4','5') THEN l.v_Prefix ELSE :v_Prefix END) = :v_Prefix AND l.v_Date BETWEEN :dateFrom AND :dateTo AND l.isPosted = 1 AND l.site_Code IN ( {…} :siteCode {…} ) GROUP BY l.subcode ) vL GROUP BY vL.subcode ) vLg ON vLg.groupCode = vAg.childGroupCode WHERE :one = :one {…} GROUP BY vAg.groupCode {…} ORDER BY GroupNature {…}`
- Params: `:one` ← `1`; `:dateFrom` ← `DmServer.convertDate(DmShared.dNull(acDisplaySetting.getDateFrom()))`; `:dateTo` ← `DmServer.convertDate(DmShared.dNull(acDisplaySetting.getDateTo()))`; `:v_Prefix` ← `DmServer.getSsnVoucherPrefix(this)`
- Other calls: `DmJasper.formatAmount`

### `addDetails`
`public void addDetails(AcDisplaySetting_Mdl acDisplaySetting, String acForCode, String acForName, List<TrialBalance_Mdl> trialBalanceList, boolean isCredit, Connection cnTemp) throws Exception`

- Other calls: `AcGroupDetail_Server.getAcGroupDetailPreparedStatement`, `DmJasper.formatAmount`

## Depends on (imports from other packages)
`dataman.accounts.acDisplaySetting.client`, `dataman.accounts.acDisplaySetting.shared`, `dataman.accounts.acGroupDetail.server`, `dataman.accounts.reports.client`, `dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmJasper.server`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.self.client`, `dataman.libraryMenu.self.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
