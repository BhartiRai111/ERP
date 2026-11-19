# pb-dashboard / financeDashBoard

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/dashBoard/src/financeDashBoard/`). Hand-written notes are in the "Notes" section.

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
### Screen `DayBankSetting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Account Group | DmTextHelp | `txtHAccountGroup` | `KeyField_PrpAcs, 6, true` |
| 2 | Account Name | DmTextHelpMulti | `txtHAccountName` | `TxtHAccountName_PrpAcs, 10` |

**Grid / popup columns** (header, width, db field):
- `txtHAccountGroup`: Name [Text, w220, GroupName]; Nature [Text, w150, nature]
- `txtHAccountName`: Account Name [Text, w250, Name]; Manual Code [Text, w100, ManualCode]; Group Name [Text, w80, GroupName]

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
- Alert: "A/c group is mandatory!"

### Screen `DayCashSetting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Account Group | DmTextHelp | `txtHAccountGroup` | `KeyField_PrpAcs, 6, false` |
| 2 | Account Name | DmTextHelpMulti | `txtHAccountName` | `TxtHAccountName_PrpAcs, 10` |

**Grid / popup columns** (header, width, db field):
- `txtHAccountGroup`: Name [Text, w220, GroupName]; Nature [Text, w150, nature]
- `txtHAccountName`: Account Name [Text, w250, Name]; Manual Code [Text, w100, ManualCode]; Group Name [Text, w80, GroupName]

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
- Alert: "A/c group is mandatory!"

### Screen `FinanceDashBoard`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `txtSearchValue` (DmTextBox), `txtDate` (DmTextBox), `txtHSite` (DmTextHelpMulti)

**Grid / popup columns** (header, width, db field):
- `txtHSite`: Site [Text, w200, name]

**Lookup / suggestion / find sources (server RPC called):**
- `todaysExpenses` → `getTxtHSite`
- `recievableSetting` → `getDefaultValues`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`partyFutureOutstandingDisplay.addCallBack` → `onClose()`
```java
flwtemp.remove(partyFutureOutstandingDisplay);
flwtemp.add(vrtInnerForm);
```

`todaysExpenses.addCallBack` → `onClose()`
```java
flwtemp.remove(todaysExpenses);
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

`dayCashSetting.addCallBack` → `onOk()`
```java
popup_Mdl.copy(dayCashSetting_Mdl);
getDayCash(txtDate.getText(), DmShared.xNull(txtHSite.getTag()));
```

`dayBankSetting.addCallBack` → `onOk()`
```java
popup_Mdl.copy(dayBankSetting_Mdl);
getDayBank(txtDate.getText(), DmShared.xNull(txtHSite.getTag()));
```

`creditorsSetting.addCallBack` → `onOk()`
```java
popup_Mdl.copy(creditorsSetting_Mdl);
getCreditorsForNDays();
```

`debtorsSetting.addCallBack` → `onOk()`
```java
popup_Mdl.copy(debtorsSetting_Mdl);
getDebtorsForNDays();
```

`payableSetting.addCallBack` → `onOk()`
```java
popup_Mdl.copy(payableSetting_Mdl);
getAgeingCreditorChart("Supplier", txtDate.getText(), DmShared.xNull(txtHSite.getTag()));
```

`recievableSetting.addCallBack` → `onOk()`
```java
popup_Mdl.copy(recievableSetting_Mdl);
getAgeingDebitorChart("Customer", txtDate.getText(), DmShared.xNull(txtHSite.getTag()));
```
- Confirm: "Are you sure? You want to close."

### Screen `PartyFutureOutstandingSetting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Account Group | DmTextHelp | `txtHAccountGroup` | `TxtHAccountGroup_PrpAcs, 6, false` |
| 2 | Account Name | DmTextHelpMulti | `txtHAccountName` | `TxtHAccountName_PrpAcs, 10` |
| 3 | - | DmTextBoxNumeric | `txtNextDays` | `0, false, 2, true` |

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
- Alert: "'A/c group' is mandatory!"
- Alert: "'No. of days' is mandatory!"
- Alert: "'No. of days' can not be less than or equal to zero!"
- Alert: "'No. of days' can not be greater than 30 days!"

### Screen `PayableRecievableSetting`

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
- Alert: "A/c group is mandatory!"

## Files
| file | lines |
|---|---|
| client/DayBankSetting.java | 212 |
| client/DayBankSetting_Dsg.java | 206 |
| client/DayCashSetting.java | 212 |
| client/DayCashSetting_Dsg.java | 208 |
| client/FinanceDashBoard.java | 1022 |
| client/FinanceDashBoard_Dsg.java | 552 |
| client/PartyFutureOutstandingSetting.java | 233 |
| client/PartyFutureOutstandingSetting_Dsg.java | 232 |
| client/PayableRecievableSetting.java | 212 |
| client/PayableRecievableSetting_Dsg.java | 212 |
| server/FinanceDashBoard_SrvImpl.java | 1019 |
| shared/AgeingChartSetting_Mdl.java | 49 |
| shared/CardDetail_Mdl.java | 48 |
| shared/CredDebt_Mdl.java | 30 |
| shared/CreditorsSetting_Mdl.java | 83 |
| shared/DayBankSetting_Mdl.java | 63 |
| shared/DayCashSetting_Mdl.java | 63 |
| shared/DefaultValue_Mdl.java | 115 |
| shared/FinanceDashBoard_Srv.java | 35 |
| shared/FinanceDashBoard_SrvAsync.java | 36 |
| shared/PartyFutureOutstandingDisplaySetting_Mdl.java | 104 |
| shared/PayableRecievableSetting_Mdl.java | 64 |
| shared/Setting_Mdl.java | 31 |
| shared/TxtHSupplierPT_Mdl.java | 32 |

## UI labels / columns (client)
`0`, `99`, `-`, `Date`, `Site`, `Cash Balance`, `Bank Balance`, `Day Expenses`, `Month Expenses`, `Quick Links`, `Creditors Outstanding`, `Debtors Outstanding`, `Receivable`, `Payable`, `Account Group`, `Account Name`, `Next Days`

## Models (shared DTOs)
### CredDebt_Mdl
| type | field |
|---|---|
| String | crAmt |
| int | drAmt |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | currentDate |
| String | currentSiteCode |
| String | currentSiteName |
| String | payableAcGroupCode |
| String | payableAcGroupName |
| String | recievableAcGroupCode |
| String | recievableAcGroupName |
| String | dayCashGroupCode |
| String | dayCashGroupName |
| String | dayBankGroupCode |
| String | dayBankGroupName |

### Setting_Mdl
| type | field |
|---|---|
| int | slab1 |
| int | slab2 |
| int | slab3 |

### DayBankSetting_Mdl
| type | field |
|---|---|
| String | acGroupCode |
| String | acGroupName |
| String | acCode |
| String | acName |

### PayableRecievableSetting_Mdl
| type | field |
|---|---|
| String | acGroupCode |
| String | acGroupName |
| String | acCode |
| String | acName |

### PartyFutureOutstandingDisplaySetting_Mdl
| type | field |
|---|---|
| String | asOnDate |
| String | siteCode |
| String | acGroupCode |
| String | acGroupName |
| String | acCode |
| String | acName |
| String | nextDays |
| String | recordType |

### CreditorsSetting_Mdl
| type | field |
|---|---|
| int | slab1 |
| int | slab2 |
| int | slab3 |
| int | slab4 |
| int | slab5 |
| int | slab6 |
| int | slab7 |

### AgeingChartSetting_Mdl
| type | field |
|---|---|
| int | slab1 |
| int | slab2 |
| int | slab3 |
| int | slab4 |
| int | slab5 |
| int | slab6 |

### DayCashSetting_Mdl
| type | field |
|---|---|
| String | acGroupCode |
| String | acGroupName |
| String | acCode |
| String | acName |

### TxtHSupplierPT_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityName |

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
| getDayCash | String date, String siteList, DayCashSetting_Mdl dayCashSetting_Mdl | DmResult<String> |
| getDayBank | String date, String siteList, DayBankSetting_Mdl dayBankSetting_Mdl | DmResult<String> |
| getDayExpenses | String date, String siteList | DmResult<String> |
| getMonthExpenses | String date, String siteList | DmResult<String> |
| getPartyOutstanidngForNDays | PartyFutureOutstandingDisplaySetting_Mdl crDrSetting_Mdl | DmResult<String> |
| getAgeingChart | String recordType, String date, String siteList, PayableRecievableSetting_Mdl recievableSetting_Mdl | DmResult<CardDetail_Mdl> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAccountGroup | String mainAcGroupCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAccountName | String mainAcGroupCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<Ledger_Model.TxtHAccountName>> |

## Server `FinanceDashBoard_SrvImpl.java` (1019 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerDateTime`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.getSsnSiteName`, `DmServer.vNull`, `DmShared.convertDate`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`

- SQL 1: `select groupCode,groupName from acGroup where groupName = :groupName`
- SQL 2: `select groupCode,groupName from acGroup where groupName = :groupName`
- SQL 3: `select groupCode,groupName from acGroup where groupName = :groupName`
- SQL 4: `select groupCode,groupName from acGroup where groupName = :groupName`
- Params: `:groupName` ← `CREDITORS_GROUP_NAME`; `:groupName` ← `DEBTORS_GROUP_NAME`; `:groupName` ← `CASH_IN_HAND_GROUP_NAME`; `:groupName` ← `BANK_ACCOUNT_GROUP_NAME`

### `getDefaultSettings`
`public DmResult<PartyFutureOutstandingDisplaySetting_Mdl> getDefaultSettings(String recordType)`


### `getDayCash`
`public DmResult<String> getDayCash(String date, String siteList, DayCashSetting_Mdl dayCashSetting_Mdl)`

- Other calls: `DashBoard_Server.getAccountGroupBalance`, `DmJasper.formatAmount`

### `getDayBank`
`public DmResult<String> getDayBank(String date, String siteList, DayBankSetting_Mdl dayBankSetting_Mdl)`

- Other calls: `DashBoard_Server.getAccountGroupBalance`, `DmJasper.formatAmount`

### `getDayExpenses`
`public DmResult<String> getDayExpenses(String date, String siteList)`

- SQL 1: `SELECT SUM(l.amtDr) as dayExpense FROM ledger l LEFT JOIN subGroup sg ON sg.subCode = l.subcode LEFT JOIN acGroup ag ON ag.groupCode = sg.groupCode LEFT JOIN ( SELECT DISTINCT agc.groupCode FROM acGroup ag INNER JOIN acGroup agc ON CHARINDEX(ISNULL(ag.mainStreamCode, ''), ISNULL(agc.mainStreamCode,''),1) > 0 WHERE 1=1 AND ag.groupName IN (:purchaseAc, :salesAc, :openingStock, :closingStock) ) agps ON agps.groupCode = sg.groupCode WHERE ag.GroupNature In (:two,:three,:four,:five) AND agps.groupCode IS NULL AND l.v_Date = :v_Date AND l.isPosted = :one {…}`
- Params: `:v_Date` ← `DmServer.convertDate(date)`; `:purchaseAc` ← `PURCHASE_ACCOUNTS_GROUP_NAME`; `:salesAc` ← `SALES_ACCOUNTS_GROUP_NAME`; `:openingStock` ← `OPENING_STOCK_GROUP_NAME`; `:closingStock` ← `CLOSING_STOCK_GROUP_NAME`; `:one` ← `1`; `:two` ← `2`; `:three` ← `3`; `:four` ← `4`; `:five` ← `5`
- Other calls: `DmJasper.formatAmount`

### `getMonthExpenses`
`public DmResult<String> getMonthExpenses(String date, String siteList)`

- SQL 1: `SELECT SUM(l.amtDr) AS monthExpense FROM ledger l LEFT JOIN subGroup sg ON sg.subCode = l.subcode LEFT JOIN acGroup ag ON ag.groupCode = sg.groupCode LEFT JOIN ( SELECT DISTINCT agc.groupCode FROM acGroup ag INNER JOIN acGroup agc ON CHARINDEX(ISNULL(ag.mainStreamCode, ''), ISNULL(agc.mainStreamCode,''),1) > 0 WHERE 1=1 AND ag.groupName IN (:purchaseAc, :salesAc, :openingStock, :closingStock) ) agps ON agps.groupCode = sg.groupCode WHERE ag.GroupNature In (:two,:three,:four,:five) AND agps.groupCode IS NULL AND l.v_Date BETWEEN CAST('01-' + FORMAT(:v_Date,'MMM-yyyy') AS DATE) And :v_Date AND l.isPosted = :one {…}`
- Params: `:v_Date` ← `DmServer.convertDate(date)`; `:purchaseAc` ← `PURCHASE_ACCOUNTS_GROUP_NAME`; `:salesAc` ← `SALES_ACCOUNTS_GROUP_NAME`; `:openingStock` ← `OPENING_STOCK_GROUP_NAME`; `:closingStock` ← `CLOSING_STOCK_GROUP_NAME`; `:one` ← `1`; `:two` ← `2`; `:three` ← `3`; `:four` ← `4`; `:five` ← `5`
- Other calls: `DmJasper.formatAmount`

### `getAgeingChart`
`public DmResult<CardDetail_Mdl> getAgeingChart(String ageingCondition, String date, String siteList, PayableRecievableSetting_Mdl recievableSetting_Mdl)`

- SQL 1: `SELECT SUM(vLg.Amt1) AS slab1, SUM(vLg.Amt2) AS slab2, SUM(vLg.Amt3) AS slab3, SUM(vLg.Amt4) AS slab4, SUM(vLg.Amt5) AS slab5, SUM(vLg.Amt6) AS slab6, SUM(vLg.Amt7) AS slab7, SUM(vLg.totalAmtDR) AS totalAmtDR, SUM(vLg.totalAmtCR) AS totalAmtCR FROM ( SELECT ISNULL(SUM( {…} ),0) AS Amt1,0 AS Amt2,0 AS Amt3,0 AS Amt4 ,0 AS Amt5,0 AS Amt6,0 AS Amt7,0 AS totalAmtDR,0 AS totalAmtCR From Ledger As Lg Left Join SubGroup As SG On Lg.SubCode=Sg.SubCode LEFT JOIN voucher_Type vt ON vt.v_Type = lg.v_Type LEFT JOIN ( SELECT DISTINCT agc.groupCode, agc.groupName FROM acGroup ag INNER JOIN acGroup agc ON CHARINDEX(ISNULL(ag.mainStreamCode, ''), ISNULL(agc.mainStreamCode,''),1) > 0 WHERE :one = :one {…} ) ag ON sg.groupCode = ag.groupCode LEFT JOIN Sitemast As SM On SM.Code=Lg.site_Code WHERE {…} > :slab1Date AND {…} <= :asOnDate AND {…} Union All Select 0 As Amt1,ISNULL(Sum( {…} ),0) As Amt2,0 As Amt3,0 As Amt4 ,0 As Amt5,0 As Amt6,0 AS Amt7,0 AS totalAmtDR,0 AS totalAmtCR From Ledger As Lg Left Join SubGroup As SG On Lg.SubCode=Sg.SubCode LEFT JOIN voucher_Type vt ON vt.v_Type = lg.v_Type LEFT JOIN ( SELECT DISTINCT agc.groupCode, agc.groupName FROM acGroup ag INNER JOIN acGroup agc ON CHARINDEX(ISNULL(ag.mainStreamCode, ''), ISNULL(agc.mainStreamCode,''),1) > 0 WHERE :one = :one {…} ) ag ON sg.groupCode = ag.groupCode Left Join Sitemast As SM On SM.Code=Lg.site_Code WHERE {…} > :slab2Date AND {…} <= :slab1Date AND {…} Union All Select 0 As Amt1,0 As Amt2,ISNULL(Sum( {…} ),0) As Amt3,0 As Amt4 ,0 As Amt5,0 As Amt6,0 AS Amt7,0 AS totalAmtDR,0 AS totalAmtCR From Ledger As Lg Left Join SubGroup As SG On Lg.SubCode=Sg.SubCode LEFT JOIN voucher_Type vt ON vt.v_Type = lg.v_Type LEFT JOIN ( SELECT DISTINCT agc.groupCode, agc.groupName FROM acGroup ag INNER JOIN acGroup agc ON CHARINDEX(ISNULL(ag.mainStreamCode, ''), ISNULL(agc.mainStreamCode,''),1) > 0 WHERE :one = :one {…} ) ag ON sg.groupCode = ag.groupCode Left Join Sitemast As SM On SM.Code=Lg.site_Code WHERE {…} > :slab3Date AND {…} <= :slab2Date AND {…} Union All Select 0 As Amt1,0 As Amt2,0 As Amt3,isNull(Sum( {…} ),0) As Amt4 ,0 As Amt5,0 As Amt6,0 AS Amt7,0 AS totalAmtDR,0 AS totalAmtCR From Ledger As Lg Left Join SubGroup As SG On Lg.SubCode=Sg.SubCode LEFT JOIN voucher_Type vt ON vt.v_Type = lg.v_Type LEFT JOIN ( SELECT DISTINCT agc.groupCode, agc.groupName FROM acGroup ag INNER JOIN acGroup agc ON CHARINDEX(ISNULL(ag.mainStreamCode, ''), ISNULL(agc.mainStreamCode,''),1) > 0 WHERE :one = :one {…} ) ag ON sg.groupCode = ag.groupCode Left Join Sitemast As SM On SM.Code=Lg.site_Code WHERE {…} > :slab4Date AND {…} <= :slab3Date AND {…} Union All Select 0 As Amt1,0 As Amt2,0 As Amt3,0 As Amt4 ,isNull(Sum( {…} ),0) As Amt5,0 As Amt6,0 AS Amt7,0 AS totalAmtDR,0 AS totalAmtCR FROM LEDGER AS Lg LEFT JOIN SubGroup As SG On Lg.SubCode=Sg.SubCode LEFT JOIN voucher_Type vt ON vt.v_Type = lg.v_Type LEFT JOIN ( SELECT DISTINCT agc.groupCode, agc.groupName FROM acGroup ag INNER JOIN acGroup agc ON CHARINDEX(ISNULL(ag.mainStreamCode, ''), ISNULL(agc.mainStreamCode,''),1) > 0 WHERE :one = :one {…} ) ag ON sg.groupCode = ag.groupCode LEFT JOIN Sitemast As SM On SM.Code=Lg.site_Code LEFT JOIN City CT ON CT.CityCode =SG.CityCode WHERE {…} > :slab5Date AND {…} <= :slab4Date AND {…} UNION ALL SELECT 0 As Amt1,0 As Amt2,0 As Amt3,0 As Amt4 , 0 As Amt5,isNull(Sum( {…} ),0) As Amt6,0 AS Amt7,0 AS totalAmtDR,0 AS totalAmtCR From Ledger As Lg LEFT JOIN SubGroup As SG On Lg.SubCode=Sg.SubCode LEFT JOIN voucher_Type vt ON vt.v_Type = lg.v_Type LEFT JOIN ( SELECT DISTINCT agc.groupCode, agc.groupName FROM acGroup ag INNER JOIN acGroup agc ON CHARINDEX(ISNULL(ag.mainStreamCode, ''), ISNULL(agc.mainStreamCode,''),1) > 0 WHERE :one = :one {…} ) ag ON sg.groupCode = ag.groupCode Left Join Sitemast As SM On SM.Code=Lg.site_Code LEFT JOIN City CT ON CT.CityCode =SG.CityCode WHERE {…} > :slab6Date AND {…} <= :slab5Date AND {…} Union All Select 0 As Amt1,0 As Amt2,0 As Amt3,0 As Amt4,0 As Amt5,0 As Amt6,isNull(Sum( {…} ),0)`
- Params: `:asOnDate` ← `DmServer.convertDate(DmShared.dNull(date)), true`; `:slab1Date` ← `DmServer.convertDate(DmShared.dNull(slab1Date))`; `:slab2Date` ← `DmServer.convertDate(DmShared.dNull(slab2Date))`; `:slab3Date` ← `DmServer.convertDate(DmShared.dNull(slab3Date))`; `:slab4Date` ← `DmServer.convertDate(DmShared.dNull(slab4Date))`; `:slab5Date` ← `DmServer.convertDate(DmShared.dNull(slab5Date))`; `:slab6Date` ← `DmServer.convertDate(DmShared.dNull(slab6Date))`; `:opening` ← `new DmVoucherCategory().OPENING_BALANCE`; `:one` ← `1`; `:groupName` ← `CREDITORS_GROUP_NAME`; `:groupName` ← `DEBTORS_GROUP_NAME`
- Other calls: `CalendarUtil.addDaysToDate`, `DmJasper.formatAmount`

### `getPartyOutstanidngForNDays`
`public DmResult<String> getPartyOutstanidngForNDays(PartyFutureOutstandingDisplaySetting_Mdl setting)`

- SQL 1: `SELECT {…} AS totalAmt FROM ledger lg LEFT JOIN subGroup sg ON sg.subCode = lg.subcode LEFT JOIN ( SELECT ladj.adj_DocId, ladj.adj_V_SNo, SUM(ladj.adjustment) AS adjustmentAmt FROM ledgerAdj ladj GROUP BY ladj.adj_DocId, ladj.adj_V_SNo ) vLadj ON lg.docId = vLadj.adj_DocId AND lg.v_SNo = vLadj.adj_V_SNo LEFT JOIN ( SELECT DISTINCT agc.groupCode, agc.groupName FROM acGroup ag INNER JOIN acGroup agc ON CHARINDEX(ISNULL(ag.mainStreamCode, ''), ISNULL(agc.mainStreamCode,''),1) > 0 WHERE :one = :one {…} ) ag ON sg.groupCode = ag.groupCode LEFT JOIN siteMast AS sm ON sm.code = lg.site_Code WHERE :one = :one {…} HAVING {…} > 0`
- Params: `:one` ← `1`; `:nThDate` ← `DmServer.convertDate(DmShared.dNull(nThDate))`; `:groupName` ← `CREDITORS_GROUP_NAME`; `:groupName` ← `DEBTORS_GROUP_NAME`
- Other calls: `CalendarUtil.addDaysToDate`

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
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmChart.shared`, `dataman.dmToolkit.dmJasper.server`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.self.client`, `dataman.libraryMenu.self.shared`, `paperbiz.dashBoard.financeDashBoard.shared`, `paperbiz.dashBoard.partyFutureOutstandingDisplay.client`, `paperbiz.dashBoard.partyFutureOutstandingDisplay.shared`, `paperbiz.dashBoard.self.server`, `paperbiz.dashBoard.self.shared`, `paperbiz.dashBoard.todaysExpenses.client`, `paperbiz.projectMenu.self.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
