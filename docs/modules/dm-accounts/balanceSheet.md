# dm-accounts / balanceSheet

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/accounts/src/balanceSheet/`). Hand-written notes are in the "Notes" section.

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
### Screen `BalanceSheet`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `grdDisplay` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `grdDisplay`: Liabilities [Text, w340];  [Text, w150]; Amount [Text, w150]; Assets [Text, w340];  [Text, w140]; Amount [Text, w140]

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
| client/BalanceSheet.java | 513 |
| client/BalanceSheet_Dsg.java | 360 |
| server/BalanceSheet_SrvImpl.java | 384 |
| shared/BalanceSheetReport_Mdl.java | 61 |
| shared/BalanceSheet_Mdl.java | 159 |
| shared/BalanceSheet_Srv.java | 11 |
| shared/BalanceSheet_SrvAsync.java | 11 |
| shared/DefaultValue_Mdl.java | 84 |
| shared/GrdBalanceSheetReport_Mdl.java | 123 |

## Models (shared DTOs)
### BalanceSheetReport_Mdl
| type | field |
|---|---|
| String | totalLiabilitiesAmount |
| String | totalAssetsAmount |
| String | totalDifference |
| AcDisplaySetting_Mdl | acDisplaySettingMdl |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | dtFrom |
| String | dtTo |
| String | amountLiabilities |
| String | amountAssests |
| String | amountDifference |
| List<BalanceSheet_Mdl> | lstBalanceSheet |
| boolean | inventoryAccountingActive |
| String | defaultLedgerPeriodicity |

### GrdBalanceSheetReport_Mdl
| type | field |
|---|---|
| String | liabiltyCode |
| String | liabiltyName |
| String | isLiabiltyChild |
| String | isLiabiltyBold |
| String | liabiltyChildBalance |
| String | liabiltyAmount |
| String | assetCode |
| String | assetName |
| String | isAssetChild |
| String | isAssetBold |
| String | assetChildBalance |
| String | assetAmount |

### BalanceSheet_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String liabilityCode, liabilityName, | liabilityAmount |
| String liabilityChildGroupCode, | liabilityChildGroupName |
| String liabilityChildSubCode, | liabilityChildSubName |
| String | liabilityChildBalance |
| String assetCode, assetName, | assetAmount |
| String assetChildGroupCode, | assetChildGroupName |
| String assetChildSubCode, | assetChildSubName |
| String | assetChildBalance |
| int | selectionType |

## RPC interface
| method | params | returns |
|---|---|---|
| getDefault | AcDisplaySetting_Mdl acDisplaySetting | DmResult<DefaultValue_Mdl> |

## Server `BalanceSheet_SrvImpl.java` (384 lines)
Helpers used: `DmServer.convertDate`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getDefault`
`public DmResult<DefaultValue_Mdl> getDefault(AcDisplaySetting_Mdl acDisplaySetting)`

- SQL 1: `SELECT l.subcode, MAX(sg.groupCode) AS groupCode, MAX(AG.groupNature) as groupNature , MAX(ag.groupName) AS acGroupName, MAX(sg.name) AS acName , MAX(sg.acGroupDr) AS acGroupDr, MAX(sg.acGroupCr) AS acGroupCr , ISNULL(SUM(l.amtDr),0) - ISNULL(SUM(l.amtCr), 0) AS opening , 0 AS amtDr , 0 AS amtCr FROM ledger l LEFT JOIN subGroup sg ON sg.subCode = l.subcode LEFT JOIN acGroup ag ON ag.groupCode = sg.groupCode WHERE ag.GroupNature IN (0,1) AND l.v_Date < :dateFrom AND l.isPosted = 1 AND l.site_Code IN ( {…} :siteCode {…} ) GROUP BY l.subcode UNION ALL`
- SQL 2: `SELECT vAg.groupCode AS groupCode , MAX(vAg.groupName) AS gName , CASE WHEN (ISNULL(SUM(vLg.opening),0) +ISNULL(SUM(vLg.amtDr),0) - ISNULL(SUM(vLg.amtCr), 0)) > 0 THEN (ISNULL(SUM(vLg.opening),0) +ISNULL(SUM(vLg.amtDr),0) - ISNULL(SUM(vLg.amtCr), 0)) ELSE 0 END AS amtDr , CASE WHEN (ISNULL(SUM(vLg.opening),0) +ISNULL(SUM(vLg.amtDr),0) - ISNULL(SUM(vLg.amtCr), 0)) < 0 THEN ABS(ISNULL(SUM(vLg.opening),0) +ISNULL(SUM(vLg.amtDr),0) - ISNULL(SUM(vLg.amtCr), 0)) ELSE 0 END AS amtCr , MAX(vAg.GroupNature) AS groupNature , MAX(vAg.sNo) AS sNo FROM ( SELECT ag.groupCode, ag.groupName, agc.groupCode AS childGroupCode, ag.sNo, ag.groupNature FROM (SELECT ag.groupCode, ag.groupName, ag.sNo, ag.groupNature, ag.mainStreamCode FROM acGroup ag WHERE ag.groupUnder IS NULL AND AG.GroupNature IN (0,1) ) ag LEFT JOIN acGroup agc ON CHARINDEX(ISNULL(ag.mainStreamCode, ''), ISNULL(agc.mainStreamCode,''),1) > 0 WHERE :one = :one {…} ) vAg LEFT JOIN ( SELECT vL.subcode,MAX(vL.acGroupName) AS acGroupName, MAX(vL.acName) AS acName , MAX(vl.groupNature) as groupNature, MAX(vl.acGroupDr) AS acGroupDr, MAX(vl.acGroupCr) AS acGroupCr , ISNULL(SUM(vL.opening),0) AS opening , CASE WHEN ISNULL(SUM(vL.opening),0) > 0 THEN ISNULL(SUM(vL.opening),0) ELSE 0 END AS openingDr , CASE WHEN ISNULL(SUM(vL.opening),0) < 0 THEN ABS(ISNULL(SUM(vL.opening),0)) ELSE 0 END AS openingCr , ISNULL(SUM(vL.amtDr),0) AS amtDr , ISNULL(SUM(vL.amtCr),0) AS amtCr , CASE WHEN MAX(vL.groupNature) = 1 AND (ISNULL(SUM(vL.amtDr),0) - ISNULL(SUM(vL.amtCr), 0)) > 0 THEN MAX(vL.groupCode) WHEN MAX(vL.groupNature) = 0 AND (ISNULL(SUM(vL.amtDr),0) - ISNULL(SUM(vL.amtCr), 0)) < 0 THEN MAX(vL.groupCode) WHEN MAX(vL.groupNature) = 1 AND (ISNULL(SUM(vL.amtDr),0) - ISNULL(SUM(vL.amtCr), 0)) < 0 THEN CASE WHEN MAX(vL.acGroupCr) IS NOT NULL THEN MAX(vL.acGroupCr) ELSE MAX(vL.groupCode) END WHEN MAX(vL.groupNature) = 0 AND (ISNULL(SUM(vL.amtDr),0) - ISNULL(SUM(vL.amtCr), 0)) > 0 THEN CASE WHEN MAX(vL.acGroupDr) IS NOT NULL THEN MAX(vL.acGroupDr) ELSE MAX(vL.groupCode) END ELSE MAX(vL.groupCode) END groupCode FROM ( {…} SELECT l.subcode, MAX(sg.groupCode) AS groupCode, MAX(AG.groupNature) as groupNature , MAX(ag.groupName) AS acGroupName, MAX(sg.name) AS acName , MAX(sg.acGroupDr) AS acGroupDr, MAX(sg.acGroupCr) AS acGroupCr , 0 AS opening , SUM(l.amtDr) AS amtDr , SUM(l.amtCr) AS amtCr FROM ledger l LEFT JOIN subGroup sg ON sg.subCode = l.subcode LEFT JOIN acGroup ag ON ag.groupCode = sg.groupCode WHERE ag.GroupNature IN (0,1) AND l.v_Date BETWEEN :dateFrom AND :dateTo AND l.isPosted = 1 AND l.site_Code IN ( {…} :siteCode {…} ) GROUP BY l.subcode ) vL GROUP BY vL.subcode ) vLg ON vLg.groupCode = vAg.childGroupCode WHERE :one = :one {…} GROUP BY vAg.groupCode {…} ORDER BY groupNature {…}`
- SQL 3: `SELECT ec.isInventoryAccountingActive, ec.ac_LedgerPeriodicity FROM enviroCompany ec`
- Params: `:one` ← `1`; `:dateFrom` ← `DmServer.convertDate(DmShared.dNull(acDisplaySetting.getDateFrom()))`; `:dateTo` ← `DmServer.convertDate(DmShared.dNull(acDisplaySetting.getDateTo()))`
- Other calls: `DmJasper.formatAmount`

### `addDetails`
`private void addDetails(AcDisplaySetting_Mdl acDisplaySetting, String acForCode, String acForName, List<BalanceSheet_Mdl> balanceSheetList, double amtDr, double amtCr, int groupNature, Connection cnTemp) throws Exception`

- Other calls: `AcGroupDetail_Server.getAcGroupDetailPreparedStatement`, `DmJasper.formatAmount`

### `getEmptyRowIndex`
`private BalanceSheet_Mdl getEmptyRowIndex(List<BalanceSheet_Mdl> balanceSheetList, double amtDr, double amtCr, int groupNature)`


## Depends on (imports from other packages)
`dataman.accounts.acDisplaySetting.client`, `dataman.accounts.acDisplaySetting.shared`, `dataman.accounts.acGroupDetail.server`, `dataman.accounts.profitLossDisplay.server`, `dataman.accounts.reports.client`, `dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmJasper.server`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.self.client`, `dataman.libraryMenu.self.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
