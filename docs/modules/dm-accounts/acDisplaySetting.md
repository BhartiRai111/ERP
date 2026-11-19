# dm-accounts / acDisplaySetting

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/accounts/src/acDisplaySetting/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHAccountGroup
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `AcDisplaySetting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | From Date | DmTextBox | `txtDateFrom` | `11, true` |
| 2 | To Date | DmTextBox | `txtDateTo` | `11, true` |
| 3 | Site Name | DmTextHelpMulti | `txtHSite` | `KeyField_PrpAcs, 7, true` |
| 4 | Show Zero Value A/c | DmTextHelp | `txtHZeroValue` | `KeyField_PrpAcs, 4, true, false` |
| 5 | Show Detail | DmTextHelp | `txtHDetail` | `KeyField_PrpAcs, 4, true, false` |
| 6 | Show Narration | DmTextHelp | `txtHNarration` | `KeyField_PrpAcs, 4, true, false` |
| 7 | Include Opening | DmTextHelp | `txtHOpening` | `KeyField_PrpAcs, 4, true, false` |
| 8 | A/c Group | DmTextHelpMulti | `txtHAccountGroup` | `TxtHAcGroup_PrpAcs, 10` |
| 9 | A/c Ledger | DmTextHelpMulti | `txtHLedger` | `TxtHLedger_PrpAcs, 7` |
| 10 | Order By | DmTextHelp | `txtHOrderBy` | `KeyField_PrpAcs, 4, true, true` |
| 11 | Periodicity | DmTextHelp | `txtHPeriodicity` | `KeyField_PrpAcs, 4, true, false` |
| 12 | Closing Stock | DmTextBoxNumeric | `txtClosing` | `2, false, 15, false` |

**Grid / popup columns** (header, width, db field):
- `txtHZeroValue`: Name [Text, w100]
- `txtHDetail`: Name [Text, w100]
- `txtHNarration`: Name [Text, w100]
- `txtHOpening`: Name [Text, w100]
- `txtHOrderBy`: Order By [Text, w100]
- `txtHLedger`: Name [Text, w250, name]; Manual Code [Text, w120, manualCode]; City [Text, w150, cityName]
- `txtHSite`: Name [Text, w150, name]
- `txtHAccountGroup`: Name [Text, w220, GroupName]; Nature [Text, w100, nature]
- `txtHPeriodicity`: Priodicity [Text, w100]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHDetail` → `getYesNo`
- `txtHNarration` → `getYesNo`
- `txtHOpening` → `getYesNo`
- `txtHZeroValue` → `getYesNo`
- `txtHOrderBy` → `getOrderBy`
- `txtHPeriodicity` → `getPerioditicity`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtHOrderBy.setEnabled(!isDetailTrialBalance)`
- `txtHDetail.setEnabled(!isDetailTrialBalance)`
- `txtHAccountGroup.setEnabled(isEnableAcGroup)`
- `txtHLedger.setEnabled(isEnableLedger)`
- `txtHPeriodicity.setEnabled(isEnablePeriodicity)`

**`manageDisplay()` logic (condensed code):**
```java
flwClosingStockContainer.clear();
if (!acDisplaySetting.isInventoryAccountingActive()) {
flwClosingStockContainer.add(flwClosingStock);
txtHOrderBy.setEnabled(!isDetailTrialBalance);
txtHDetail.setEnabled(!isDetailTrialBalance);
txtHAccountGroup.setEnabled(isEnableAcGroup);
txtHAccountGroup.setMandatory(isAcGroupMandatory);
txtHLedger.setEnabled(isEnableLedger);
txtHLedger.setMandatory(isLedgerMandatory);
txtHPeriodicity.setEnabled(isEnablePeriodicity);
txtHPeriodicity.setMandatory(isEnablePeriodicity);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHDetail.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExte
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDetail).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHDetail.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDetail).show();
```

`txtHDetail.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHDetail.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDetail).show();
```

`txtHLedger.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getLedgerAcount(helper, blnToEndOfFile, DmShared.xNull(txtHSite.getTag()), new Async
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHLedger).show();
public void onSuccess(DmResultPageing<List<AcntLedger_Mdl>> result) {
if (result.isStatus()) {
txtHLedger.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHLedger).show();
```

`txtHLedger.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHLedger.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHLedger).show();
```

`txtHNarration.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExte
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHNarration).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHNarration.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHNarration).show();
```

`txtHNarration.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHNarration.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHNarration).show();
```

`txtHOpening.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExte
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHOpening).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHOpening.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHOpening).show();
```

`txtHOpening.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHOpening.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHOpening).show();
```

`txtHZeroValue.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExte
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHZeroValue).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHZeroValue.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHZeroValue).show();
```

`txtHZeroValue.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHZeroValue.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHZeroValue).show();
```

`txtHSite.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getSite(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExten
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

`txtHOrderBy.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getOrderBy(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldEx
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHOrderBy).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHOrderBy.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHOrderBy).show();
```

`txtHOrderBy.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHOrderBy.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHOrderBy).show();
```

`txtHAccountGroup.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHAccountGroup(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<K
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

`txtHPeriodicity.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getPerioditicity(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyF
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHPeriodicity).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHPeriodicity.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPeriodicity).show();
```

`txtHPeriodicity.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHPeriodicity.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPeriodicity).show();
```
- Alert: "Site Name is mandatory."
- Alert: "From date is mandatory."
- Alert: "To date is mandatory."
- Alert: "From date should be less than To Date"
- Alert: "Order By is mandatory."
- Alert: "Periodicity is mandatory."
- Alert: "Account group is mandatory."
- Alert: "Account is mandatory."

## Files
| file | lines |
|---|---|
| client/AcDisplaySetting.java | 589 |
| client/AcDisplaySetting_Dsg.java | 390 |
| server/AcDisplaySetting_SrvImpl.java | 434 |
| shared/AcDisplaySetting_Mdl.java | 198 |
| shared/AcDisplaySetting_Srv.java | 26 |
| shared/AcDisplaySetting_SrvAsync.java | 27 |
| shared/AcntLedger_Mdl.java | 31 |
| shared/DisplayRoute_Mdl.java | 116 |

## UI labels / columns (client)
`From Date`, `To Date`, `Site Name`, `Show Zero Value A/c`, `Show Detail`, `Show Narration`, `Include Opening`, `A/c Group`, `A/c Ledger`, `Order By`, `Periodicity`, `Closing Stock`

## Models (shared DTOs)
### DisplayRoute_Mdl
| type | field |
|---|---|
| PageName | pageName |
| CodeType | codeType |
| String code, | name |
| AcDisplaySetting_Mdl | acDisplaySetting_Mdl |
| String | clickRowIndex |
| String | clickColIndex |
| boolean | ledgerPeriodicSummary |
| String | pipePeriodStr |

### AcDisplaySetting_Mdl
| type | field |
|---|---|
| String siteName, | siteCode |
| String dateFrom, | dateTo |
| String showZeroValueAc, showNarration, | showDetail |
| String | includeOpening |
| String ledgerAcName, | ledgerAcCode |
| boolean | inventoryAccountingActive |
| String | closingStock |
| String | printOrderByCode |
| String | printOrderByName |
| String acGroupCode, | acGroupName |
| String periodicityCode, | periodicityName |

### AcntLedger_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | city |

## RPC interface
| method | params | returns |
|---|---|---|
| getYesNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getOrderBy | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getLedgerAcount | DmHelper helper, boolean blnToEndOfFile, String siteList | DmResultPageing<List<AcntLedger_Mdl>> |
| getTxtHAccountGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getPerioditicity | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| fillData |  | DmResult<AcDisplaySetting_Mdl> |
| refreshClosingStock | StockValue_Mdl svm | DmResult<Double> |

## Server `AcDisplaySetting_SrvImpl.java` (434 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.xNull`

### `getYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getSite`
`public DmResultPageing<List<KeyFieldExtended>> getSite(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in ( {…} :code {…} )`

### `getPosition`
`public DmResultPageing<List<KeyFieldExtended>> getPosition(DmHelper helper, boolean blnToEndOfFile)`


### `getCostCenter`
`public DmResultPageing<List<KeyFieldExtended>> getCostCenter(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT code, name FROM costCenterMast WHERE (ISNULL(underCostCenter, 0) = 0)`

### `getLedgerAcount`
`public DmResultPageing<List<AcntLedger_Mdl>> getLedgerAcount(DmHelper helper, boolean blnToEndOfFile, String siteList)`

- SQL 1: `SELECT SG.subCode, SG.name, SG.manualCode, CT.cityName FROM subGroup AS SG LEFT JOIN city AS CT ON SG.cityCode = CT.cityCode`

### `fillData`
`public DmResult<AcDisplaySetting_Mdl> fillData()`

- SQL 1: `select name from siteMast where code = :code`
- SQL 2: `SELECT ec.isInventoryAccountingActive FROM enviroCompany ec`
- Params: `:code` ← `DmServer.getSsnSiteCode(this)`

### `getOrderBy`
`public DmResultPageing<List<KeyFieldExtended>> getOrderBy(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHAccountGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAccountGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT AG.GroupCode,AG.GroupName,sgn.name as nature FROM AcGroup AG LEFT JOIN subgroupNature sgn on AG.nature = sgn.code`

### `getPerioditicity`
`public DmResultPageing<List<KeyFieldExtended>> getPerioditicity(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `LedgerPeriodicity.values`

### `refreshClosingStock`
`public DmResult<Double> refreshClosingStock(StockValue_Mdl svm)`

- Other calls: `Stock_Server.getStockValue`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.stock.server`, `dataman.dmToolkit.stock.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
