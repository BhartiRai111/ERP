# pb-dashboard / salesDashBoard

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/dashBoard/src/salesDashBoard/`). Hand-written notes are in the "Notes" section.

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
### Screen `OutstandingSetting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Account Group | DmTextHelp | `txtHAccountGroup` | `TxtHAccountGroup_PrpAcs, 6, false` |
| 2 | Account Name | DmTextHelpMulti | `txtHAccountName` | `TxtHAccountName_PrpAcs, 10` |

**Grid / popup columns** (header, width, db field):
- `txtHAccountGroup`: Name [Text, w220, GroupName]; Nature [Text, w150, nature]
- `txtHAccountName`: Name [Text, w250, Name]; Manual Code [Text, w100, ManualCode]; City [Text, w150, City]; Group Name [Text, w80, GroupName]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHAccountGroup` → `getTxtHAccountGroup`

**Event handlers (condensed code, CRUD flow and field callbacks):**

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

### Screen `RealizationSetting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Account Group | DmTextHelpMulti | `txtHAccountGroup` | `TxtHAccountGroup_PrpAcs, 10` |
| 2 | Account Name | DmTextHelpMulti | `txtHAccountName` | `TxtHAccountName_PrpAcs, 10` |

**Grid / popup columns** (header, width, db field):
- `txtHAccountGroup`: Name [Text, w220, GroupName]; Nature [Text, w150, nature]
- `txtHAccountName`: Name [Text, w250, Name]; Manual Code [Text, w100, ManualCode]; City [Text, w150, City]; Group Name [Text, w80, GroupName]

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHAccountGroup.addTextHelpMultiCallBack` → `populateData()`
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

`txtHAccountGroup.addTextHelpMultiCallBack` → `onSuccess()`
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
- Alert: "A/c group is mandatory!"

### Screen `SalesDashBoard`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `txtSearchValue` (DmTextBox), `txtDate` (DmTextBox), `txtHSite` (DmTextHelpMulti)

**Grid / popup columns** (header, width, db field):
- `txtHSite`: Site [Text, w200, name]

**Lookup / suggestion / find sources (server RPC called):**
- `salesInvoiceDisplay` → `getTxtHSite`
- `realizationSetting` → `getDefaultValues`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`paymentRealizationDisplay.addCallBack` → `onClose()`
```java
flwtemp.remove(paymentRealizationDisplay);
flwtemp.add(vrtInnerForm);
```

`salesInvoiceDisplay.addCallBack` → `onClose()`
```java
flwtemp.remove(salesInvoiceDisplay);
flwtemp.add(vrtInnerForm);
```

`txtHSite.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHSite(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
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

`outstandingSetting.addCallBack` → `onOk()`
```java
outstandingPopupCallBack(osm);
```

`realizationSetting.addCallBack` → `onOk()`
```java
realizationPopupCallBack(realizationSetting_Mdl);
```
- Confirm: "Are you sure? You want to close."

## Files
| file | lines |
|---|---|
| client/OutstandingSetting.java | 209 |
| client/OutstandingSetting_Dsg.java | 213 |
| client/RealizationSetting.java | 211 |
| client/RealizationSetting_Dsg.java | 210 |
| client/SalesDashBoard.java | 864 |
| client/SalesDashBoard_Dsg.java | 376 |
| server/SalesDashBoard_Server.java | 159 |
| server/SalesDashBoard_SrvImpl.java | 843 |
| shared/CardDetail_Mdl.java | 57 |
| shared/DefaultValue_Mdl.java | 78 |
| shared/OutstandingSetting_Mdl.java | 46 |
| shared/RealizationSetting_Mdl.java | 50 |
| shared/SalesDashBoard_Srv.java | 36 |
| shared/SalesDashBoard_SrvAsync.java | 36 |
| shared/SalesDashboardFilter_Mdl.java | 88 |
| shared/Setting_Mdl.java | 31 |

## UI labels / columns (client)
`Account Group`, `Account Name`, `0`, `-`, `Date`, `Site`, `Day Orders`, `Day Sales`, `Day Realization`, `Debtors Outstanding`, `Monthly Comparision Sales`, `Pending Sales Order`, `Quick Links`

## Models (shared DTOs)
### SalesDashboardFilter_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | fromDate |
| String | toDate |
| String | siteCode |
| String | siteName |
| String | acGroupCode |
| String | acGroupName |
| String | accountCode |
| String | accountName |

### OutstandingSetting_Mdl
| type | field |
|---|---|
| String | acGroupCode |
| String | acGroupName |
| String | acCode |
| String | acName |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | currentDate |
| String | currentSiteCode |
| String | currentSiteName |
| String | debtorsAcGroupCode |
| String | debtorsAcGroupName |
| String | realizationAcGroupCode |
| String | realizationAcGroupName |

### Setting_Mdl
| type | field |
|---|---|
| int | slab1 |
| int | slab2 |
| int | slab3 |

### CardDetail_Mdl
| type | field |
|---|---|
| String | count |
| List<DmChart_Mdl> | listChardData |
| List<DmChart_Mdl> | listChardDataCurrFY |
| List<DmChart_Mdl> | listChardDataPrvFY |
| List<DmChart_Mdl> | listChardDataSales |

### RealizationSetting_Mdl
| type | field |
|---|---|
| String | acGroupCode |
| String | acGroupName |
| String | acCode |
| String | acName |

## RPC interface
| method | params | returns |
|---|---|---|
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| getServerDateTime |  | DmResult<String[]> |
| getSalesOrder | String date, String siteList | DmResult<String> |
| getSales | String date, String siteList | DmResult<String> |
| getRealization | String mainAcGroupCode,String date, String siteList, RealizationSetting_Mdl rsm | DmResult<String> |
| getDebtorOutstanding | String date, String siteList, OutstandingSetting_Mdl osm | DmResult<String> |
| getPendingSoCardDetail | String date, String siteList | DmResult<CardDetail_Mdl> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getMonthlyCmpSalesChart | int CurrentFY, int PreviousFY, String siteList | DmResult<CardDetail_Mdl> |
| getTxtHAccountGroup | String mainAcGroupCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAccountName | String mainAcGroupCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<Ledger_Model.TxtHAccountName>> |

## Server `SalesDashBoard_SrvImpl.java` (843 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerDateTime`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.vNull`, `DmShared.convertDate`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`

- SQL 1: `select sm.code,sm.name AS site from siteMast sm where code = :site_Code`
- SQL 2: `select groupCode,groupName from acGroup where groupName = :groupName`
- SQL 3: `SELECT STRING_AGG (CONVERT(NVARCHAR(max),groupCode), ',') AS groupCode , STRING_AGG (CONVERT(NVARCHAR(max),groupName), ',') AS groupName from acGroup WHERE groupName IN (:loan_liability,:current_liabilities,:current_assets,:investments)`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:groupName` ← `SUNDRY_DEBTORS`; `:loan_liability` ← `LOAN_LIABILITY`; `:current_liabilities` ← `CURRENT_LIABILITIES`; `:current_assets` ← `CURRENT_ASSETS`; `:investments` ← `INVESTMENTS`

### `getSalesOrder`
`public DmResult<String> getSalesOrder(String date, String siteList)`

- Other calls: `SalesDashBoard_Server.getDaySalesOrder`

### `getSales`
`public DmResult<String> getSales(String date, String siteList)`

- SQL 1: `select sum(sb.oh_amt_net) totalTodayInvoiceAmt from sbill sb left join voucher_type as vt on sb.v_Type = vt.v_Type where IsNull(sb.approvedBy,'') <> '' and sb.v_Date = :v_date and vt.category IN (' {…} ',' {…} ') {…}`
- Params: `:v_date` ← `DmServer.convertDate(date)`
- Other calls: `DmJasper.formatAmount`

### `getRealization`
`public DmResult<String> getRealization(String mainAcGroupCode, String date, String siteList, RealizationSetting_Mdl rsm)`

- SQL 1: `SELECT SUM(lg.amtCr) AS realizedamt FROM ledger lg LEFT JOIN subGroup sgs ON sgs.subCode = lg.subcode LEFT JOIN subGroup sgc ON sgc.subCode = lg.contraSub LEFT JOIN subGroupNature sgn ON sgn.code = sgc.nature LEFT JOIN subGroupNature sgns ON sgns.code = sgs.nature INNER JOIN ( SELECT DISTINCT agc.groupCode, agc.groupName FROM acGroup ag INNER JOIN acGroup agc ON CHARINDEX(ISNULL(ag.mainStreamCode, ''), ISNULL(agc.mainStreamCode,''),1) > 0 WHERE 1=1 {…} ) ag ON ag.groupCode = sgs.groupCode WHERE lg.isPosted = 1 AND sgn.code IN (:CS,:BK) AND sgns.code NOT IN (:CS,:BK) AND ISNULL(lg.amtCr, 0) > 0 AND lg.v_Date = :asOnDate {…}`
- Params: `:CS` ← `DmServer.getCodeViaManualCode("Cash", "name", "subGroupNature", "code", cnTemp)`; `:BK` ← `DmServer.getCodeViaManualCode("Bank", "name", "subGroupNature", "code", cnTemp)`; `:asOnDate` ← `DmServer.convertDate(date)`
- Other calls: `DmJasper.formatAmount`

### `getDebtorOutstanding`
`public DmResult<String> getDebtorOutstanding(String date, String siteList, OutstandingSetting_Mdl osm)`

- Other calls: `DashBoard_Server.getAccountGroupBalance`, `DmJasper.formatAmount`

### `getPendingSoCardDetail`
`public DmResult<CardDetail_Mdl> getPendingSoCardDetail(String date, String siteList)`

- SQL 1: `SELECT COUNT(so.docId) as cnt , SUM(case when so.v_Date > ' {…} ' AND so.v_Date <= ' {…} ' THEN 1 ELSE 0 END) as slab1 , SUM(case when so.v_Date > ' {…} ' AND so.v_Date <= ' {…} ' THEN 1 ELSE 0 END) as slab2 , SUM(case when so.v_Date > ' {…} ' AND so.v_Date <= ' {…} ' THEN 1 ELSE 0 END) as slab3 , SUM(case when so.v_Date <= ' {…} ' THEN 1 ELSE 0 END) as slab4 FROM sOrder as so INNER JOIN ( SELECT sod.docId FROM sOrderDetail sod WHERE sod.isStatus = 0 AND sod.isFooter = 0 AND ISNULL(sod.skuQty,0) - isnull(sod.dispatchedSkuQty,0) > 0 group by sod.docId ) as vSod on vSod.docId = so.docId LEFT JOIN voucher_type as vt on so.v_Type = vt.v_Type WHERE vt.category IN (:SO,:SOO) AND isNull(so.approvedBy,'') <> '' AND so.v_date <= :asOnDate {…}`
- Params: `:SO` ← `pvc.SALES_ORDER`; `:SOO` ← `pvc.GENERAL_SALES_ORDER`; `:asOnDate` ← `DmServer.convertDate(date)`
- Other calls: `CalendarUtil.addDaysToDate`

### `getServerDateTime`
`public DmResult<String[]> getServerDateTime()`


### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getMonthlyCmpSalesChart`
`public DmResult<CardDetail_Mdl> getMonthlyCmpSalesChart(int currentFY, int previousFY, String siteList)`

- SQL 1: `SELECT SUM(CASE WHEN MONTH(sb.v_Date) = 4 AND DATEPART(YY,sb.v_Date) = {…} THEN sb.oh_Amt_Net ELSE 0 END) AS aprPrvFY, SUM(CASE WHEN MONTH(sb.v_Date) = 4 AND DATEPART(YY,sb.v_Date) = {…} THEN sb.oh_Amt_Net ELSE 0 END) AS aprCurrFY, SUM(CASE WHEN MONTH(sb.v_Date) = 5 AND DATEPART(YY,sb.v_Date) = {…} THEN sb.oh_Amt_Net ELSE 0 END) AS mayPrvFY, SUM(CASE WHEN MONTH(sb.v_Date) = 5 AND DATEPART(YY,sb.v_Date) = {…} THEN sb.oh_Amt_Net ELSE 0 END) AS mayCurrFY, SUM(CASE WHEN MONTH(sb.v_Date) = 6 AND DATEPART(YY,sb.v_Date) = {…} THEN sb.oh_Amt_Net ELSE 0 END) AS junPrvFY, SUM(CASE WHEN MONTH(sb.v_Date) = 6 AND DATEPART(YY,sb.v_Date) = {…} THEN sb.oh_Amt_Net ELSE 0 END) AS junCurrFY, SUM(CASE WHEN MONTH(sb.v_Date) = 7 AND DATEPART(YY,sb.v_Date) = {…} THEN sb.oh_Amt_Net ELSE 0 END) AS julPrvFY, SUM(CASE WHEN MONTH(sb.v_Date) = 7 AND DATEPART(YY,sb.v_Date) = {…} THEN sb.oh_Amt_Net ELSE 0 END) AS julCurrFY, SUM(CASE WHEN MONTH(sb.v_Date) = 8 AND DATEPART(YY,sb.v_Date) = {…} THEN sb.oh_Amt_Net ELSE 0 END) AS augPrvFY, SUM(CASE WHEN MONTH(sb.v_Date) = 8 AND DATEPART(YY,sb.v_Date) = {…} THEN sb.oh_Amt_Net ELSE 0 END) AS augCurrFY, SUM(CASE WHEN MONTH(sb.v_Date) = 9 AND DATEPART(YY,sb.v_Date) = {…} THEN sb.oh_Amt_Net ELSE 0 END) AS sepPrvFY, SUM(CASE WHEN MONTH(sb.v_Date) = 9 AND DATEPART(YY,sb.v_Date) = {…} THEN sb.oh_Amt_Net ELSE 0 END) AS sepCurrFY, SUM(CASE WHEN MONTH(sb.v_Date) = 10 AND DATEPART(YY,sb.v_Date) = {…} THEN sb.oh_Amt_Net ELSE 0 END) AS octPrvFY, SUM(CASE WHEN MONTH(sb.v_Date) = 10 AND DATEPART(YY,sb.v_Date) = {…} THEN sb.oh_Amt_Net ELSE 0 END) AS octCurrFY, SUM(CASE WHEN MONTH(sb.v_Date) = 11 AND DATEPART(YY,sb.v_Date) = {…} THEN sb.oh_Amt_Net ELSE 0 END) AS novPrvFY, SUM(CASE WHEN MONTH(sb.v_Date) = 11 AND DATEPART(YY,sb.v_Date) = {…} THEN sb.oh_Amt_Net ELSE 0 END) AS novCurrFY, SUM(CASE WHEN MONTH(sb.v_Date) = 12 AND DATEPART(YY,sb.v_Date) = {…} THEN sb.oh_Amt_Net ELSE 0 END) AS decPrvFY, SUM(CASE WHEN MONTH(sb.v_Date) = 12 AND DATEPART(YY,sb.v_Date) = {…} THEN sb.oh_Amt_Net ELSE 0 END) AS decCurrFY, SUM(CASE WHEN MONTH(sb.v_Date) = 1 AND DATEPART(YY,sb.v_Date) = ISNULL( {…} ,0) + 1 THEN sb.oh_Amt_Net ELSE 0 END) AS janPrvFY, SUM(CASE WHEN MONTH(sb.v_Date) = 1 AND DATEPART(YY,sb.v_Date) = ISNULL( {…} ,0) + 1 THEN sb.oh_Amt_Net ELSE 0 END) AS janCurrFY, SUM(CASE WHEN MONTH(sb.v_Date) = 2 AND DATEPART(YY,sb.v_Date) = ISNULL( {…} ,0) + 1 THEN sb.oh_Amt_Net ELSE 0 END) AS febPrvFY, SUM(CASE WHEN MONTH(sb.v_Date) = 2 AND DATEPART(YY,sb.v_Date) = ISNULL( {…} ,0) + 1 THEN sb.oh_Amt_Net ELSE 0 END) AS febCurrFY, SUM(CASE WHEN MONTH(sb.v_Date) = 3 AND DATEPART(YY,sb.v_Date) = ISNULL( {…} ,0) + 1 THEN sb.oh_Amt_Net ELSE 0 END) AS marPrvFY, SUM(CASE WHEN MONTH(sb.v_Date) = 3 AND DATEPART(YY,sb.v_Date) = ISNULL( {…} ,0) + 1 THEN sb.oh_Amt_Net ELSE 0 END) AS marCurrFY FROM sBill sb LEFT JOIN voucher_Type vt on vt.v_Type = sb.v_Type WHERE ISNULL(sb.approvedBy, '') <> '' and vt.category IN (' {…} ' ,' {…} ' ) {…}`

### `getTxtHAccountGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAccountGroup(String mainAcGroupCode, DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `DashBoard_Server.getTxtHAccountGroup`

### `getTxtHAccountName`
`public DmResultPageing<List<Ledger_Model.TxtHAccountName>> getTxtHAccountName(String mainAcGroupCode, DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `DashBoard_Server.getTxtHAccountName`

## Server `SalesDashBoard_Server.java` (159 lines)
Helpers used: `DmServer.convertDate`, `DmServer.getCodeViaManualCode`, `DmServer.getParamList`, `DmShared.dNull`, `DmShared.vNull`, `DmShared.xNull`

### `getDaySalesOrder`
`public static DmResult<String> getDaySalesOrder(String date, String siteList, String ssnSiteList, Connection cnTemp)`

- SQL 1: `SELECT COUNT(sod.docid) as cnt, SUM (sod.oh_Amt_Net) netAmt FROM sOrderDetail sod LEFT JOIN sOrder so on so.docid = sod.docid LEFT JOIN voucher_type as vt on so.v_Type = vt.v_Type WHERE ISNULL(so.approvedBy,:blank) <> :blank AND sod.isStatus IN (:zero,:two) AND CASE WHEN sod.isStatus = :zero THEN IIF(ISNULL(sod.skuQty,:zero) - ISNULL(sod.dispatchedSkuQty,:zero) > :zero,:one,:zero) ELSE :one END = :one AND sod.isFooter IN (:zero) AND vt.category IN (:SO,:SOO) AND so.v_date = :v_date {…}`
- Params: `:SO` ← `pvc.SALES_ORDER`; `:SOO` ← `pvc.GENERAL_SALES_ORDER`; `:blank` ← `""`; `:zero` ← `0`; `:two` ← `2`; `:one` ← `1`; `:v_date` ← `DmServer.convertDate(DmShared.dNull(date))`
- Other calls: `DmJasper.formatAmount`

### `getRealizationValue`
`public static DmResult<String> getRealizationValue(String date, String siteList, String ssnSiteList, RealizationSetting_Mdl rsm, Connection cnTemp)`

- SQL 1: `SELECT SUM(lg.amtCr) AS realizedamt FROM ledger lg LEFT JOIN subGroup sgs ON sgs.subCode = lg.subcode LEFT JOIN subGroup sgc ON sgc.subCode = lg.contraSub LEFT JOIN subGroupNature sgn ON sgn.code = sgc.nature INNER JOIN ( SELECT DISTINCT agc.groupCode, agc.groupName FROM acGroup ag INNER JOIN acGroup agc ON CHARINDEX(ISNULL(ag.mainStreamCode, ''), ISNULL(agc.mainStreamCode,''),1) > 0 WHERE ag.groupName IN ('Sundry Debtors','Sundry Creditors') ) ag ON ag.groupCode = sgs.groupCode WHERE lg.isPosted = 1 AND sgn.code IN (:CS,:BK) AND ISNULL(lg.amtCr, 0) > 0 AND lg.v_Date = :asOnDate {…}`
- Params: `:CS` ← `DmServer.getCodeViaManualCode("Cash", "name", "subGroupNature", "code", cnTemp)`; `:BK` ← `DmServer.getCodeViaManualCode("Bank", "name", "subGroupNature", "code", cnTemp)`; `:asOnDate` ← `DmServer.convertDate(date)`
- Other calls: `DmJasper.formatAmount`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmChart.shared`, `dataman.dmToolkit.dmJasper.server`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.self.client`, `dataman.libraryMenu.self.shared`, `paperbiz.dashBoard.paymentRealizationDisplay.client`, `paperbiz.dashBoard.purchaseDashBoard.shared`, `paperbiz.dashBoard.salesDashBoard.shared`, `paperbiz.dashBoard.salesInvoiceDisplay.client`, `paperbiz.dashBoard.self.server`, `paperbiz.dashBoard.self.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stStatic.client`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
