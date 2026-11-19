# pb-dashboard / purchaseDashBoard

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/dashBoard/src/purchaseDashBoard/`). Hand-written notes are in the "Notes" section.

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
srvAsync.invokeRPC(blnCallBackWait).getTxtHAccountGroup(mainAcGroupCode,helper, blnToEndOfFile, new AsyncCallback<DmResu
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
srvAsync.invokeRPC(blnCallBackWait).getTxtHAccountName(mainAcGroupCode,helper, blnToEndOfFile, new AsyncCallback<DmResul
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

### Screen `PaymentSetting`

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

### Screen `PurchaseDashBoard`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `txtSearchValue` (DmTextBox), `txtDate` (DmTextBox), `txtHSite` (DmTextHelpMulti)

**Grid / popup columns** (header, width, db field):
- `txtHSite`: Site [Text, w200, name]

**Lookup / suggestion / find sources (server RPC called):**
- `pBillDisplay` → `getTxtHSite`
- `paymentSetting` → `getDefaultValues`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`paymentRealizationDisplay.addCallBack` → `onClose()`
```java
flwtemp.remove(paymentRealizationDisplay);
flwtemp.add(vrtInnerForm);
```

`pBillDisplay.addCallBack` → `onClose()`
```java
flwtemp.remove(pBillDisplay);
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

`paymentSetting.addCallBack` → `onOk()`
```java
paymentPopupCallBack(paymentSetting_Mdl);
```
- Confirm: "Are you sure? You want to close."

## Files
| file | lines |
|---|---|
| client/OutstandingSetting.java | 208 |
| client/OutstandingSetting_Dsg.java | 212 |
| client/PaymentSetting.java | 210 |
| client/PaymentSetting_Dsg.java | 212 |
| client/PurchaseDashBoard.java | 833 |
| client/PurchaseDashBoard_Dsg.java | 386 |
| server/PurchaseDashBoard_Server.java | 157 |
| server/PurchaseDashBoard_SrvImpl.java | 846 |
| shared/CardDetail_Mdl.java | 49 |
| shared/DefaultValue_Mdl.java | 79 |
| shared/OutstandingSetting_Mdl.java | 49 |
| shared/PaymentSetting_Mdl.java | 50 |
| shared/Popup_Mdl.java | 47 |
| shared/PurchaseDashBoard_Srv.java | 36 |
| shared/PurchaseDashBoard_SrvAsync.java | 37 |
| shared/PurchaseDashboardFilter_Mdl.java | 89 |
| shared/Setting_Mdl.java | 31 |

## UI labels / columns (client)
`Account Group`, `Account Name`, `0`, `-`, `Date`, `Site`, `Day Orders`, `Day Purchase`, `Day Payments`, `Creditor Outstanding`, `Monthly Comparision Purchase`, `Pending Purchase Order`, `Quick Links`

## Models (shared DTOs)
### PurchaseDashboardFilter_Mdl (extends `KeyField implements Serializable, IsSerializable`)
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

### Popup_Mdl
| type | field |
|---|---|
| String | acGroupCode |
| String | acGroupName |
| String | acCode |
| String | acName |

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
| String | creditorsAcGroupCode |
| String | creditorsAcGroupName |
| String | paymentAcGroupCode |
| String | paymentAcGroupName |

### Setting_Mdl
| type | field |
|---|---|
| int | slab1 |
| int | slab2 |
| int | slab3 |

### PaymentSetting_Mdl
| type | field |
|---|---|
| String | acGroupCode |
| String | acGroupName |
| String | acCode |
| String | acName |

### CardDetail_Mdl
| type | field |
|---|---|
| String | count |
| List<DmChart_Mdl> | listChardData |
| List<DmChart_Mdl> | listChardDataCurrFY |
| List<DmChart_Mdl> | listChardDataPrvFY |

## RPC interface
| method | params | returns |
|---|---|---|
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| getServerDateTime |  | DmResult<String[]> |
| getPurchaseOrder | String date, String siteList | DmResult<String> |
| getPurchase | String date, String siteList | DmResult<String> |
| getDayPayments | String mainAcGroupCode,String date, String siteList, PaymentSetting_Mdl rapm | DmResult<String> |
| getCreditorOutstanding | String date, String siteList, OutstandingSetting_Mdl osm | DmResult<String> |
| getPendingPoCardDetail | String date, String siteList | DmResult<CardDetail_Mdl> |
| getMonthlyCmpPurchaseChart | int currentFY, int previousFY, String siteList | DmResult<CardDetail_Mdl> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAccountGroup | String mainAcGroupCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAccountName | String mainAcGroupCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<Ledger_Model.TxtHAccountName>> |

## Server `PurchaseDashBoard_Server.java` (157 lines)
Helpers used: `DmServer.convertDate`, `DmServer.getCodeViaManualCode`, `DmServer.getParamList`, `DmShared.dNull`, `DmShared.vNull`, `DmShared.xNull`

### `getDayPurchaseOrder`
`public static DmResult<String> getDayPurchaseOrder(String date, String siteList, String ssnSiteList, Connection cnTemp)`

- SQL 1: `SELECT COUNT(pod.docId) as cnt, SUM (pod.oh_Amt_Net) netAmt FROM pOrderDetail pod LEFT JOIN pOrder po on po.docid = pod.docid LEFT JOIN voucher_type as vt on po.v_Type = vt.v_Type WHERE ISNULL(po.approvedBy,:blank) <> :blank AND pod.isStatus IN (:zero,:two) AND CASE WHEN pod.isStatus = :zero THEN IIF(ISNULL(pod.quantity,:zero) - ISNULL(pod.challanedQty,:zero) > :zero,:one,:zero) ELSE :one END = :one AND pod.isFooter IN (:zero) AND vt.category IN (:PO,:POU,:SPO,:SPOU) AND po.v_date = :v_date {…}`
- Params: `:PO` ← `pvc.PURCHASE_ORDER`; `:POU` ← `pvc.PURCHASE_ORDER_UNREGISTERED`; `:SPO` ← `pvc.SERVICE_PURCHASE_ORDER`; `:SPOU` ← `pvc.SERVICE_PURCHASE_ORDER_UNREGISTERED`; `:blank` ← `""`; `:zero` ← `0`; `:two` ← `2`; `:one` ← `1`; `:v_date` ← `DmServer.convertDate(DmShared.dNull(date))`
- Other calls: `DmJasper.formatAmount`

### `getPaymentValue`
`public static DmResult<String> getPaymentValue(String date, String siteList, String ssnSiteList, PaymentSetting_Mdl rapm, Connection cnTemp)`

- SQL 1: `SELECT SUM(lg.AmtDr) AS amts FROM ledger lg LEFT JOIN subGroup sgs ON sgs.subCode = lg.subcode LEFT JOIN subGroup sgc ON sgc.subCode = lg.contraSub LEFT JOIN subGroupNature sgn ON sgn.code = sgc.nature INNER JOIN ( SELECT DISTINCT agc.groupCode, agc.groupName FROM acGroup ag INNER JOIN acGroup agc ON CHARINDEX(ISNULL(ag.mainStreamCode, ''), ISNULL(agc.mainStreamCode,''),1) > 0 WHERE ag.groupName IN ('Sundry Debtors','Sundry Creditors') ) ag ON ag.groupCode = sgs.groupCode WHERE lg.isPosted = 1 AND sgn.code IN (:CS,:BK) AND ISNULL(lg.amtCr, 0) > 0 AND lg.v_Date = :asOnDate {…}`
- Params: `:CS` ← `DmServer.getCodeViaManualCode("Cash", "name", "subGroupNature", "code", cnTemp)`; `:BK` ← `DmServer.getCodeViaManualCode("Bank", "name", "subGroupNature", "code", cnTemp)`; `:asOnDate` ← `DmServer.convertDate(date)`
- Other calls: `DmJasper.formatAmount`

## Server `PurchaseDashBoard_SrvImpl.java` (846 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerDateTime`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.vNull`, `DmShared.convertDate`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`

- SQL 1: `select sm.code,sm.name AS site from siteMast sm where code = :site_Code`
- SQL 2: `select groupCode,groupName from acGroup where groupName = :groupName`
- SQL 3: `SELECT STRING_AGG (CONVERT(NVARCHAR(max),groupCode), ',') AS groupCode , STRING_AGG (CONVERT(NVARCHAR(max),groupName), ',') AS groupName from acGroup WHERE groupName IN (:loan_liability,:current_liabilities,:current_assets,:investments)`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:groupName` ← `SUNDRY_CREDITORS`; `:loan_liability` ← `LOAN_LIABILITY`; `:current_liabilities` ← `CURRENT_LIABILITIES`; `:current_assets` ← `CURRENT_ASSETS`; `:investments` ← `INVESTMENTS`

### `getPurchaseOrder`
`public DmResult<String> getPurchaseOrder(String date, String siteList)`

- Other calls: `PurchaseDashBoard_Server.getDayPurchaseOrder`

### `getPurchase`
`public DmResult<String> getPurchase(String date, String siteList)`

- SQL 1: `select sum(pb.oh_amt_net) totalTodayPurchaseAmt from pbill pb left join voucher_type as vt on pb.v_Type = vt.v_Type where IsNull(pb.approvedBy,'') <> '' and pb.v_Date = :v_date and vt.category in (' {…} ' ,' {…} ' ) {…}`
- Params: `:v_date` ← `DmServer.convertDate(DmShared.dNull(date))`
- Other calls: `DmJasper.formatAmount`

### `getDayPayments`
`public DmResult<String> getDayPayments(String mainAcGroupCode, String date, String siteList, PaymentSetting_Mdl rapm)`

- SQL 1: `SELECT SUM(lg.AmtDr) AS amt FROM ledger lg LEFT JOIN subGroup sgs ON sgs.subCode = lg.subcode LEFT JOIN subGroup sgc ON sgc.subCode = lg.contraSub LEFT JOIN subGroupNature sgn ON sgn.code = sgc.nature LEFT JOIN subGroupNature sgns ON sgns.code = sgs.nature INNER JOIN ( SELECT DISTINCT agc.groupCode, agc.groupName FROM acGroup ag INNER JOIN acGroup agc ON CHARINDEX(ISNULL(ag.mainStreamCode, ''), ISNULL(agc.mainStreamCode,''),1) > 0 WHERE 1=1 {…} ) ag ON ag.groupCode = sgs.groupCode WHERE lg.isPosted = 1 AND sgn.code IN (:CS,:BK) AND sgns.code NOT IN (:CS,:BK) AND ISNULL(lg.AmtDr, 0) > 0 AND lg.v_Date = :asOnDate {…}`
- Params: `:CS` ← `DmServer.getCodeViaManualCode("Cash", "name", "subGroupNature", "code", cnTemp)`; `:BK` ← `DmServer.getCodeViaManualCode("Bank", "name", "subGroupNature", "code", cnTemp)`; `:asOnDate` ← `DmServer.convertDate(date)`
- Other calls: `DmJasper.formatAmount`

### `getCreditorOutstanding`
`public DmResult<String> getCreditorOutstanding(String date, String siteList, OutstandingSetting_Mdl osm)`

- Other calls: `DashBoard_Server.getAccountGroupBalance`, `DmJasper.formatAmount`

### `getPendingPoCardDetail`
`public DmResult<CardDetail_Mdl> getPendingPoCardDetail(String date, String siteList)`

- SQL 1: `SELECT COUNT(po.docId) as cnt , SUM(case when {…} > ' {…} ' AND {…} <= ' {…} ' THEN 1 ELSE 0 END) as slab1 , SUM(case when {…} > ' {…} ' AND {…} <= ' {…} ' THEN 1 ELSE 0 END) as slab2 , SUM(case when {…} > ' {…} ' AND {…} <= ' {…} ' THEN 1 ELSE 0 END) as slab3 , SUM(case when {…} <= ' {…} ' THEN 1 ELSE 0 END) as slab4 FROM pOrder as po INNER JOIN( select pod.docId, MIN(pod.requiredDate) as requiredDate from pOrderDetail pod where pod.isStatus = 0 AND pod.isFooter = 0 and isnull(pod.quantity,0) - isnull(pod.challanedQty,0) > 0 group by pod.docId ) as vPod on vPod.docId = po.docId LEFT JOIN voucher_type as vt on po.v_Type = vt.v_Type WHERE vt.category IN (:PO,:POU) AND isNull(po.approvedBy,'') <> '' AND po.v_date <= :asOnDate {…}`
- Params: `:PO` ← `pvc.PURCHASE_ORDER`; `:POU` ← `pvc.PURCHASE_ORDER_UNREGISTERED`; `:asOnDate` ← `DmServer.convertDate(date)`
- Other calls: `CalendarUtil.addDaysToDate`

### `getMonthlyCmpPurchaseChart`
`public DmResult<CardDetail_Mdl> getMonthlyCmpPurchaseChart(int currentFY, int previousFY, String siteList)`

- SQL 1: `SELECT SUM(CASE WHEN MONTH(pb.v_Date) = 4 AND DATEPART(YY,pb.v_Date) = {…} THEN pb.oh_Amt_Net ELSE 0 END) AS aprPrvFY, SUM(CASE WHEN MONTH(pb.v_Date) = 4 AND DATEPART(YY,pb.v_Date) = {…} THEN pb.oh_Amt_Net ELSE 0 END) AS aprCurrFY, SUM(CASE WHEN MONTH(pb.v_Date) = 5 AND DATEPART(YY,pb.v_Date) = {…} THEN pb.oh_Amt_Net ELSE 0 END) AS mayPrvFY, SUM(CASE WHEN MONTH(pb.v_Date) = 5 AND DATEPART(YY,pb.v_Date) = {…} THEN pb.oh_Amt_Net ELSE 0 END) AS mayCurrFY, SUM(CASE WHEN MONTH(pb.v_Date) = 6 AND DATEPART(YY,pb.v_Date) = {…} THEN pb.oh_Amt_Net ELSE 0 END) AS junPrvFY, SUM(CASE WHEN MONTH(pb.v_Date) = 6 AND DATEPART(YY,pb.v_Date) = {…} THEN pb.oh_Amt_Net ELSE 0 END) AS junCurrFY, SUM(CASE WHEN MONTH(pb.v_Date) = 7 AND DATEPART(YY,pb.v_Date) = {…} THEN pb.oh_Amt_Net ELSE 0 END) AS julPrvFY, SUM(CASE WHEN MONTH(pb.v_Date) = 7 AND DATEPART(YY,pb.v_Date) = {…} THEN pb.oh_Amt_Net ELSE 0 END) AS julCurrFY, SUM(CASE WHEN MONTH(pb.v_Date) = 8 AND DATEPART(YY,pb.v_Date) = {…} THEN pb.oh_Amt_Net ELSE 0 END) AS augPrvFY, SUM(CASE WHEN MONTH(pb.v_Date) = 8 AND DATEPART(YY,pb.v_Date) = {…} THEN pb.oh_Amt_Net ELSE 0 END) AS augCurrFY, SUM(CASE WHEN MONTH(pb.v_Date) = 9 AND DATEPART(YY,pb.v_Date) = {…} THEN pb.oh_Amt_Net ELSE 0 END) AS sepPrvFY, SUM(CASE WHEN MONTH(pb.v_Date) = 9 AND DATEPART(YY,pb.v_Date) = {…} THEN pb.oh_Amt_Net ELSE 0 END) AS sepCurrFY, SUM(CASE WHEN MONTH(pb.v_Date) = 10 AND DATEPART(YY,pb.v_Date) = {…} THEN pb.oh_Amt_Net ELSE 0 END) AS octPrvFY, SUM(CASE WHEN MONTH(pb.v_Date) = 10 AND DATEPART(YY,pb.v_Date) = {…} THEN pb.oh_Amt_Net ELSE 0 END) AS octCurrFY, SUM(CASE WHEN MONTH(pb.v_Date) = 11 AND DATEPART(YY,pb.v_Date) = {…} THEN pb.oh_Amt_Net ELSE 0 END) AS novPrvFY, SUM(CASE WHEN MONTH(pb.v_Date) = 11 AND DATEPART(YY,pb.v_Date) = {…} THEN pb.oh_Amt_Net ELSE 0 END) AS novCurrFY, SUM(CASE WHEN MONTH(pb.v_Date) = 12 AND DATEPART(YY,pb.v_Date) = {…} THEN pb.oh_Amt_Net ELSE 0 END) AS decPrvFY, SUM(CASE WHEN MONTH(pb.v_Date) = 12 AND DATEPART(YY,pb.v_Date) = {…} THEN pb.oh_Amt_Net ELSE 0 END) AS decCurrFY, SUM(CASE WHEN MONTH(pb.v_Date) = 1 AND DATEPART(YY,pb.v_Date) = ISNULL( {…} ,0) + 1 THEN pb.oh_Amt_Net ELSE 0 END) AS janPrvFY, SUM(CASE WHEN MONTH(pb.v_Date) = 1 AND DATEPART(YY,pb.v_Date) = ISNULL( {…} ,0) + 1 THEN pb.oh_Amt_Net ELSE 0 END) AS janCurrFY, SUM(CASE WHEN MONTH(pb.v_Date) = 2 AND DATEPART(YY,pb.v_Date) = ISNULL( {…} ,0) + 1 THEN pb.oh_Amt_Net ELSE 0 END) AS febPrvFY, SUM(CASE WHEN MONTH(pb.v_Date) = 2 AND DATEPART(YY,pb.v_Date) = ISNULL( {…} ,0) + 1 THEN pb.oh_Amt_Net ELSE 0 END) AS febCurrFY, SUM(CASE WHEN MONTH(pb.v_Date) = 3 AND DATEPART(YY,pb.v_Date) = ISNULL( {…} ,0) + 1 THEN pb.oh_Amt_Net ELSE 0 END) AS marPrvFY, SUM(CASE WHEN MONTH(pb.v_Date) = 3 AND DATEPART(YY,pb.v_Date) = ISNULL( {…} ,0) + 1 THEN pb.oh_Amt_Net ELSE 0 END) AS marCurrFY FROM pBill pb LEFT JOIN voucher_Type vt on vt.v_Type = pb.v_Type WHERE ISNULL(pb.approvedBy, '') <> '' and vt.category IN (' {…} ' ,' {…} ' ) {…}`

### `getServerDateTime`
`public DmResult<String[]> getServerDateTime()`


### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHAccountGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAccountGroup(String mainAcGroupCode, DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `DashBoard_Server.getTxtHAccountGroup`

### `getTxtHAccountName`
`public DmResultPageing<List<Ledger_Model.TxtHAccountName>> getTxtHAccountName(String mainAcGroupCode, DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `DashBoard_Server.getTxtHAccountName`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmChart.shared`, `dataman.dmToolkit.dmJasper.server`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.self.client`, `dataman.libraryMenu.self.shared`, `paperbiz.dashBoard.pBillDisplay.client`, `paperbiz.dashBoard.paymentRealizationDisplay.client`, `paperbiz.dashBoard.purchaseDashBoard.shared`, `paperbiz.dashBoard.salesDashBoard.shared`, `paperbiz.dashBoard.self.server`, `paperbiz.dashBoard.self.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stStatic.client`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
