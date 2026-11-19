# pb-dashboard / todaysExpenses

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/dashBoard/src/todaysExpenses/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHSupplierPT, getTxtHSite
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `Setting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | From Date | DmTextBox | `txtFromDate` | `15, false` |
| 2 | To Date | DmTextBox | `txtToDate` | `15, false` |
| 3 | Account Name | DmTextHelpMulti | `txtHParty` | `TxtHSupplierName_PrpAcs, 7` |
| 4 | Site | DmTextHelpMulti | `txtHSite` | `KeyField_PrpAcs, 5, true` |

**Grid / popup columns** (header, width, db field):
- `txtHParty`: Name [Text, w250, acName]; Manual Code [Text, w100, manualCode]
- `txtHSite`: Site [Text, w200, name]

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtFromDate.addValueChangeHandler` → `onValueChange()`
```java
txtFromDate.setText(DmClient.returnDate(txtFromDate.getText()));
```

`txtToDate.addValueChangeHandler` → `onValueChange()`
```java
txtToDate.setText(DmClient.returnDate(txtToDate.getText()));
```

`txtHParty.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHSupplierPT(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Txt
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHParty).show();
public void onSuccess(DmResultPageing<List<TxtHSupplierPT_Mdl>> result) {
if (result.isStatus()) {
txtHParty.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHParty).show();
```

`txtHParty.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHParty.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHParty).show();
```

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
- Alert: "From Date should be less than or equal to To Date."

### Screen `TodaysExpenses`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmGridPaging | `gridTodaysExpenses` | `com.google.gwt.core.shared.GrdPaging_PrpAcs, 23, false` |

Other widgets (not in tab list): `txtSearchValue` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `gridTodaysExpenses`: View [Text, w50]; Voucher No. [Text, w150, recId]; Date [Text, w100, replace(convert(varchar, v_Date, 106), ' ','-')]; Type [Text, w80, type]; A/c Name [Text, w200, acName]; Particulars [Text, w300, narration]; Amount [Text, w200, amtDr]

**Lookup / suggestion / find sources (server RPC called):**
- `gridTodaysExpenses` → `getFillRecords`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`settingPopup.addCallBack` → `onOk()`
```java
searchValue_Mdl = svm;
gridTodaysExpenses.getDmGrid().commitChanges();
gridTodaysExpenses.reQuery();
```

`gridTodaysExpenses.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnToEndOfFile).getFillRecords(searchValue_Mdl, helper, blnToEndOfFile, new AsyncCallback<DmResultPag
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdTodaysExpenses_Mdl>> result) {
if (result.isStatus()) {
gridTodaysExpenses.setData(result.getData(), result.getPaging());
if (todaysPBCallBackl != null) {
DmResult<Object> dmrTemp = new DmResult<>();
dmrTemp.setStatus(true);
todaysPBCallBackl.result(dmrTemp);
...
```

`gridTodaysExpenses.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
gridTodaysExpenses.setData(result.getData(), result.getPaging());
if (todaysPBCallBackl != null) {
DmResult<Object> dmrTemp = new DmResult<>();
dmrTemp.setStatus(true);
todaysPBCallBackl.result(dmrTemp);
todaysPBCallBackl = null;
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`gridTodaysExpenses.addCallBack` → `getParam()`
```java
return searchValue_Mdl;
```
- Confirm: "Are you sure? You want to close."

## Files
| file | lines |
|---|---|
| client/Setting.java | 222 |
| client/Setting_Dsg.java | 179 |
| client/TodaysExpenses.java | 408 |
| client/TodaysExpenses_Dsg.java | 213 |
| server/TodaysExpenses_SrvImpl.java | 332 |
| shared/DefaultValue_Mdl.java | 42 |
| shared/GrdTodaysExpenses_Mdl.java | 86 |
| shared/SearchValue_Mdl.java | 68 |
| shared/TodaysExpenses_Srv.java | 20 |
| shared/TodaysExpenses_SrvAsync.java | 21 |
| shared/TxtHSupplierPT_Mdl.java | 41 |

## UI labels / columns (client)
`From Date`, `To Date`, `Account Name`, `Site`

## Models (shared DTOs)
### DefaultValue_Mdl
| type | field |
|---|---|
| String | currentDate |
| String | currentSiteCode |
| String | currentSiteName |

### SearchValue_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | FromDate |
| String | ToDate |
| String | accountName |
| String | accountCode |
| String | siteCode |
| String | siteName |

### TxtHSupplierPT_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityName |
| String | groupName |

### GrdTodaysExpenses_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | v_Type |
| String | v_Date |
| String | particulars |
| String | amtDr |
| String | mnuText |
| String | mnuModuleName |
| String | isView |
| String | acName |

## RPC interface
| method | params | returns |
|---|---|---|
| getFillRecords | SearchValue_Mdl searchValueMdl, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdTodaysExpenses_Mdl>> |
| getTxtHSupplierPT | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHSupplierPT_Mdl>> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |

## Server `TodaysExpenses_SrvImpl.java` (332 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerDateTime`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.getSsnSiteName`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.xNull`

### `getFillRecords`
`public DmResultPageing<List<GrdTodaysExpenses_Mdl>> getFillRecords(SearchValue_Mdl param, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT l.docId, l.recId, vt.short_Name as type, l.v_Date, sg.name as acName , l.refDate, l.refNo, sgc.name as SGCName, l.amtDr, l.v_No , vt.mnuText , vt.mnuAttachedInModule ,concat(sgc.name ,', ',l.refNo,', ',l.refDate) as narration FROM ledger l LEFT JOIN subGroup sg ON sg.subCode = l.subcode LEFT JOIN acGroup ag ON ag.groupCode = sg.groupCode LEFT JOIN voucher_Type vt ON vt.v_Type = l.v_Type LEFT JOIN subGroup sgc ON sgc.subCode = l.contraSub LEFT JOIN ( SELECT agc.groupCode FROM acGroup ag INNER JOIN acGroup agc ON CHARINDEX(ISNULL(ag.mainStreamCode, ''), ISNULL(agc.mainStreamCode,''),1) > 0 WHERE 1=1 AND ag.groupName IN (:purchaseAc, :salesAc, :openingStock, :closingStock) ) agps ON agps.groupCode = sg.groupCode WHERE ag.GroupNature In (:two,:three,:four,:five) AND agps.groupCode IS NULL AND ISNULL(l.amtDr,0) > 0 AND l.isPosted = :one {…}`
- Params: `:purchaseAc` ← `PURCHASE_ACCOUNTS_GROUP_NAME`; `:salesAc` ← `SALES_ACCOUNTS_GROUP_NAME`; `:openingStock` ← `OPENING_STOCK_GROUP_NAME`; `:closingStock` ← `CLOSING_STOCK_GROUP_NAME`; `:fromv_Date` ← `DmServer.convertDate(param.getFromDate())`; `:tov_Date` ← `DmServer.convertDate(param.getToDate())`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`


### `getTxtHSupplierPT`
`public DmResultPageing<List<TxtHSupplierPT_Mdl>> getTxtHSupplierPT(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT SG.SubCode as code, SG.Name as acName, Sg.ManualCode as manualCode, AG.GroupName FROM SubGroup SG LEFT JOIN AcGroup AG ON AG.GroupCode = SG.GroupCode LEFT JOIN ( SELECT agc.groupCode FROM acGroup ag INNER JOIN acGroup agc ON CHARINDEX(ISNULL(ag.mainStreamCode, ''), ISNULL(agc.mainStreamCode,''),1) > 0 WHERE 1=1 AND ag.groupName IN (:purchaseAc, :salesAc, :openingStock, :closingStock) ) agps ON agps.groupCode = sg.groupCode LEFT JOIN City CT ON SG.CityCode = CT.CityCode WHERE AG.GroupNature In (:two, :three, :four, :five) AND agps.groupCode IS NULL`
- Params: `:purchaseAc` ← `PURCHASE_ACCOUNTS_GROUP_NAME`; `:salesAc` ← `SALES_ACCOUNTS_GROUP_NAME`; `:openingStock` ← `OPENING_STOCK_GROUP_NAME`; `:closingStock` ← `CLOSING_STOCK_GROUP_NAME`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.self.client`, `paperbiz.dashBoard.todaysExpenses.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stStatic.client`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
