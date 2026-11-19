# pb-dashboard / paymentRealizationDisplay

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/dashBoard/src/paymentRealizationDisplay/`). Hand-written notes are in the "Notes" section.

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
### Screen `PaymentRealizationDisplay`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmGridPaging | `gridSaleInvoice` | `com.google.gwt.core.shared.GrdPaging_PrpAcs, 23, false` |

Other widgets (not in tab list): `txtSearchValue` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `gridSaleInvoice`: Detail [Text, w50]; Voucher No. [Text, w120, recId]; Vr Date [Text, w80, replace(convert(varchar, v_Date, 106), ' ','-')]; Type [Text, w70, type]; Party Account [Text, w250, partyAc]; Contra Account [Text, w300, contraAc]; Amount [Text, w150, amount]

**Lookup / suggestion / find sources (server RPC called):**
- `gridSaleInvoice` → `getFillRecords`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`settingPopup.addSaleOrderCallBack` → `onOk()`
```java
searchValue_Mdl = svm;
gridSaleInvoice.getDmGrid().commitChanges();
gridSaleInvoice.reQuery();
```

`gridSaleInvoice.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnToEndOfFile).getFillRecords(recordType, dvm.getMainAcGroupCode(), searchValue_Mdl, helper, blnToEn
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdPaymentRealizationDisplay_Mdl>> result) {
if (result.isStatus()) {
gridSaleInvoice.setData(result.getData(), result.getPaging());
if (result.getValue1() != null) {
lblTotalAmt.setText(result.getValue1());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
...
```

`gridSaleInvoice.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
gridSaleInvoice.setData(result.getData(), result.getPaging());
if (result.getValue1() != null) {
lblTotalAmt.setText(result.getValue1());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`gridSaleInvoice.addCallBack` → `getParam()`
```java
return searchValue_Mdl;
```
- Confirm: "Are you sure? You want to close."

### Screen `Setting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | To Date | DmTextBox | `txtFromDate` | `15, false` |
| 2 | - | DmTextBox | `txtToDate` | `15, false` |
| 3 | Account Name | DmTextHelp | `txtHAccountGroup` | `TxtHAccountGroup_PrpAcs, 6, false` |
| 4 | - | DmTextHelpMulti | `txtHAccountName` | `TxtHAccountName_PrpAcs, 10` |
| 5 | Site | DmTextHelpMulti | `txtHSite` | `KeyField_PrpAcs, 5, false` |

**Grid / popup columns** (header, width, db field):
- `txtHAccountGroup`: Name [Text, w220, GroupName]; Nature [Text, w150, nature]
- `txtHAccountName`: Name [Text, w250, Name]; Manual Code [Text, w100, ManualCode]; City [Text, w150, City]; Group Name [Text, w80, GroupName]
- `txtHSite`: Site [Text, w200, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHAccountGroup` → `getTxtHAccountGroup`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtFromDate.addValueChangeHandler` → `onValueChange()`
```java
txtFromDate.setText(DmClient.returnDate(txtFromDate.getText()));
```

`txtToDate.addValueChangeHandler` → `onValueChange()`
```java
txtToDate.setText(DmClient.returnDate(txtToDate.getText()));
```

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

## Files
| file | lines |
|---|---|
| client/PaymentRealizationDisplay.java | 431 |
| client/PaymentRealizationDisplay_Dsg.java | 243 |
| client/Setting.java | 237 |
| client/Setting_Dsg.java | 266 |
| server/PaymentRealizationDisplay_SrvImpl.java | 409 |
| shared/DefaultValue_Mdl.java | 60 |
| shared/GrdPaymentRealizationDisplay_Mdl.java | 106 |
| shared/PaymentRealizationDisplay_Srv.java | 24 |
| shared/PaymentRealizationDisplay_SrvAsync.java | 24 |
| shared/SearchValue_Mdl.java | 88 |
| shared/TxtHPBill_Mdl.java | 32 |

## UI labels / columns (client)
`-`, `Total Amount:`, `From Date`, `To Date`, `Account Group`, `Account Name`, `Site`

## Models (shared DTOs)
### TxtHPBill_Mdl (extends `KeyField implements Serializable,IsSerializable`)
| type | field |
|---|---|
| String | pBillType |
| String | pBillDate |

### GrdPaymentRealizationDisplay_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | amount |
| String | creditAc |
| String | debitAc |
| String | voucherNo |
| String | v_Date |
| String | v_Type |
| String | mnuText |
| String | mnuModuleName |
| String | isView |
| String | particulars |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | currentDate |
| String | currentSiteCode |
| String | currentSiteName |
| String | mainAcGroupCode |
| String | mainAcGroupName |

### SearchValue_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | fromDate |
| String | toDate |
| String | accountName |
| String | accountCode |
| String | acGroupCode |
| String | acGroupName |
| String | siteCode |
| String | siteName |

## RPC interface
| method | params | returns |
|---|---|---|
| getFillRecords | String recordType,String mainAcGroupCode, SearchValue_Mdl searchValueMdl, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdPaymentRealizationDisplay_Mdl>> |
| getDefaultValues | String mainAcGroupCode | DmResult<DefaultValue_Mdl> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAccountGroup | String mainAcGroupCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAccountName | String mainAcGroupCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<Ledger_Model.TxtHAccountName>> |

## Server `PaymentRealizationDisplay_SrvImpl.java` (409 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerDateTime`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.xNull`

### `getFillRecords`
`public DmResultPageing<List<GrdPaymentRealizationDisplay_Mdl>> getFillRecords(String recordType, String mainAcGroupCode, SearchValue_Mdl param, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT {…} FROM ledger lg LEFT JOIN subGroup sgs ON sgs.subCode = lg.subcode LEFT JOIN subGroup sgc ON sgc.subCode = lg.contraSub LEFT JOIN subGroupNature sgn ON sgn.code = sgc.nature INNER JOIN ( SELECT DISTINCT agc.groupCode, agc.groupName FROM acGroup ag INNER JOIN acGroup agc ON CHARINDEX(ISNULL(ag.mainStreamCode, ''), ISNULL(agc.mainStreamCode,''),1) > 0 WHERE 1=1 {…} ) ag ON ag.groupCode = sgs.groupCode WHERE lg.isPosted = 1 AND sgn.code IN (:CS,:BK) {…}`
- SQL 2: `SELECT {…} , sgs.name as partyAc , sgc.name as contraAc , lg.recId , vt.short_Name as type,lg.v_Date, lg.docId , lg.refDate, lg.refNo, lg.narration, sgc.name as SGCName , vt.mnuText , vt.mnuAttachedInModule FROM ledger lg LEFT JOIN subGroup sgs ON sgs.subCode = lg.subcode LEFT JOIN subGroup sgc ON sgc.subCode = lg.contraSub LEFT JOIN subGroupNature sgn ON sgn.code = sgc.nature LEFT JOIN voucher_Type vt ON vt.v_Type = lg.v_Type INNER JOIN ( SELECT DISTINCT agc.groupCode, agc.groupName FROM acGroup ag INNER JOIN acGroup agc ON CHARINDEX(ISNULL(ag.mainStreamCode, ''), ISNULL(agc.mainStreamCode,''),1) > 0 WHERE 1 = 1 {…} ) ag ON ag.groupCode = sgs.groupCode WHERE lg.isPosted = 1 AND sgn.code IN (:CS,:BK) {…}`
- Params: `:CS` ← `DmServer.getCodeViaManualCode("Cash", "name", "subGroupNature", "code", cnTemp)`; `:BK` ← `DmServer.getCodeViaManualCode("Bank", "name", "subGroupNature", "code", cnTemp)`; `:fromv_Date` ← `DmServer.convertDate(param.getFromDate())`; `:tov_Date` ← `DmServer.convertDate(param.getToDate())`; `:CS` ← `DmServer.getCodeViaManualCode("Cash", "name", "subGroupNature", "code", cnTemp)`; `:BK` ← `DmServer.getCodeViaManualCode("Bank", "name", "subGroupNature", "code", cnTemp)`; `:fromv_Date` ← `DmServer.convertDate(param.getFromDate())`; `:tov_Date` ← `DmServer.convertDate(param.getToDate())`
- Errors: "record type is invalid"

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues(String mainAcGroupCode)`

- SQL 1: `select sm.code,sm.name AS site from siteMast sm where code = :site_Code`
- SQL 2: `SELECT STRING_AGG (CONVERT(NVARCHAR(max),groupCode), ',') AS groupCode , STRING_AGG (CONVERT(NVARCHAR(max),groupName), ',') AS groupName FROM acGroup {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

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
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.self.client`, `paperbiz.dashBoard.paymentRealizationDisplay.shared`, `paperbiz.dashBoard.purchaseDashBoard.shared`, `paperbiz.dashBoard.salesDashBoard.shared`, `paperbiz.dashBoard.self.server`, `paperbiz.dashBoard.self.shared`, `paperbiz.projectMenu.self.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| getFillRecords | `if (DmShared.xNull(recordType).trim().equalsIgnoreCase(""))` | "record type is invalid" |

## Notes (hand-written)
_No hand-written notes yet._
