# pb-store / itemMovementDisplay

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/store/src/itemMovementDisplay/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Site (Text); Summary On (Text); Report For (Text)
- Lookups/suggestions: getTxtHSite, getTxtHDepartment, getTxtHItemName, getTxtHParty, getTxtHAcGroup, getTxtHVoucherType, getTxtHPackSize, getTxtHSummaryOn, getTxtHReportFor, getTxtHBf, getTxtHGsm, getTxtHItemNature
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `IMSetting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | To Date | DmTextBox | `txtFromDate` | `11, false` |
| 2 | - | DmTextBox | `txtToDate` | `11, false` |
| 3 | Site Name | DmTextHelpMulti | `txtHSite` | `KeyField_PrpAcs, 10, true` |
| 4 | Warehouse | DmTextHelpMulti | `txtHDepartment` | `KeyField_PrpAcs, 5` |
| 5 | Item | DmTextHelp | `txtHItem` | `TxtHItemNamePrpAcs, 10, true` |
| 6 | Party | DmTextHelpMulti | `txtHParty` | `TxtHPartyName_PrpAcs, 10` |
| 7 | Account Group | DmTextHelpMulti | `txtHAcGroup` | `TxtHAcGroup_PrpAcs, 10` |
| 8 | Voucher Type | DmTextHelpMulti | `txtHVoucherType` | `TxtHVoucherType_PrpAcs, 10` |
| 9 | Item Nature | DmTextHelpMulti | `txtHPackSize` | `TxtHPackSize_PrpAcs, 10` |
| 10 | - | DmTextHelp | `txtHItemNature` | `com.google.gwt.core.client.KeyField_PrpAcs, 5, false` |
| 11 | GSM | DmTextHelp | `txtHBf` | `com.google.gwt.core.client.KeyField_PrpAcs, 5, false` |
| 12 | - | DmTextHelp | `txtHGsm` | `com.google.gwt.core.client.KeyField_PrpAcs, 5, false` |
| 13 | Summary On | DmTextHelp | `txtHGroupBy` | `KeyField_PrpAcs, 5` |
| 14 | - | DmTextHelp | `txtHSummaryOn` | `KeyField_PrpAcs, 5, true` |
| 15 | Report For | DmTextHelp | `txtHReportFor` | `TxtHSummaryType_PrpAcs, 3, true` |

Other widgets (not in tab list): `txtClosing` (DmTextBoxNumeric)

**Grid / popup columns** (header, width, db field):
- `txtHSite`: Name [Text, w150, name]
- `txtHDepartment`: Name [Text, w125, Name]
- `txtHItem`: Item Name [Text, w400, itemName]; Manual Code [Text, w80, manualCode]; Item Type [Text, w100, itemType]; Item Category [Text, w100, itemCategory]; Item Group [Text, w150, itemGroup]
- `txtHParty`: Name [Text, w300, name]; Manual Code [Text, w100, manualCode]; City Name [Text, w200, cityName]; Ac Group [Text, w180, acGroupName]; Nature [Text, w120, nature]
- `txtHAcGroup`: Group Name [Text, w200, name]; Parent [Text, w200, parentGroupName]; Nature [Text, w150, nature]
- `txtHVoucherType`: Type [Text, w180, name]; Short Name [Text, w80, short_Name]
- `txtHPackSize`: Pack Size [Text, w150, name]; Unit [Text, w80, unit]
- `txtHSummaryOn`: Name [Text, w100]
- `txtHGroupBy`: Name [Text, w100]
- `txtHReportFor`: Name [Text, w100, name]
- `txtHBf`: Name [Text, w50, name]
- `txtHGsm`: Name [Text, w50, name]
- `txtHItemNature`: Name [Text, w75, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHItem` → `getTxtHItemName`
- `txtHSummaryOn` → `getTxtHSummaryOn`
- `txtHGroupBy` → `getTxtHSummaryOn`
- `txtHReportFor` → `getTxtHReportFor`
- `txtHBf` → `getTxtHBf`
- `txtHGsm` → `getTxtHGsm`
- `txtHItemNature` → `getTxtHItemNature`

**Event handlers (condensed code, CRUD flow and field callbacks):**

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

`txtHDepartment.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHDepartment(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Key
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDepartment).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHDepartment.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDepartment).show();
```

`txtHDepartment.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHDepartment.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDepartment).show();
```

`txtHItem.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItemName(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHI
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItem).show();
public void onSuccess(DmResultPageing<List<TxtHItem_Mdl>> result) {
if (result.isStatus()) {
txtHItem.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItem).show();
```

`txtHItem.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItem.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItem).show();
```

`txtHParty.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHParty(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHPart
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHParty).show();
public void onSuccess(DmResultPageing<List<TxtHParty_Mdl>> result) {
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

`txtHAcGroup.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHAcGroup(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHAc
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHAcGroup).show();
public void onSuccess(DmResultPageing<List<TxtHAcGroup_Mdl>> result) {
if (result.isStatus()) {
txtHAcGroup.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAcGroup).show();
```

`txtHAcGroup.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAcGroup.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAcGroup).show();
```

`txtHVoucherType.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHVoucherType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHVoucherType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHVoucherType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHVoucherType).show();
```

`txtHVoucherType.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHVoucherType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHVoucherType).show();
```

`txtHPackSize.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHPackSize(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHPackSize).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHPackSize.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPackSize).show();
```

`txtHPackSize.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHPackSize.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPackSize).show();
```

`txtHSummaryOn.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHSummaryOn(txtHGroupBy.getTag(), helper, blnToEndOfFile, new AsyncCallback<DmR
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSummaryOn).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHSummaryOn.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSummaryOn).show();
```

`txtHSummaryOn.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSummaryOn.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSummaryOn).show();
```

`txtHGroupBy.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHSummaryOn(txtHSummaryOn.getTag(), helper, blnToEndOfFile, new AsyncCallback<D
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSummaryOn).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHGroupBy.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGroupBy).show();
```

`txtHGroupBy.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHGroupBy.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGroupBy).show();
```

`txtHReportFor.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHReportFor(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyF
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHReportFor).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHReportFor.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHReportFor).show();
```

`txtHReportFor.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHReportFor.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHReportFor).show();
```

`txtHBf.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHBf(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExt
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHBf).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHBf.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBf).show();
```

`txtHBf.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHBf.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBf).show();
```

`txtHBf.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
```

`txtHGsm.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHGsm(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldEx
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGsm).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHGsm.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGsm).show();
```

`txtHGsm.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHGsm.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGsm).show();
```

`txtHGsm.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
```

`txtHItemNature.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItemNature(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Key
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemNature).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHItemNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemNature).show();
```

`txtHItemNature.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemNature).show();
```

`txtHItemNature.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
```
- Alert: "'Site Name' is mandatory."
- Alert: "Item is mandatory."
- Alert: "'Summary On' is mandatory."
- Alert: "'Report For' is mandatory."

### Screen `ItemMovementDisplay`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmTextBox | `txtSearchValue` | `100, false` |
| 2 | - | DmGridPaging | `grdDisplay` | `GrdDisplay_PrpAcs, 20` |

**Grid / popup columns** (header, width, db field):
- `grdDisplay`: Particulars [Text, w570, particulars]; Quantity [Text, w200, qty]; Unit [Text, w100, unit]; Eff. Rate [Text, w150, effectiveRate]; Value [Text, w200, value]

**Lookup / suggestion / find sources (server RPC called):**
- `imSetting` → `getDefault`
- `grdDisplay` → `getItemMovementAnalysis`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`imSetting.addCallBack` → `onOk()`
```java
setting_Mdl = imSetting;
refreshGrid();
```

`grdDisplay.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnToEndOfFile).getItemMovementAnalysis(setting_Mdl, helper, blnToEndOfFile, param, new AsyncCallback
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdItemMovementDisplay_Mdl>> result) {
if (result.isStatus()) {
grdDisplay.setData(result.getData(), result.getPaging());
Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
public void execute() {
try {
if (flagOnLoadRefresh) {
...
```

`grdDisplay.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
grdDisplay.setData(result.getData(), result.getPaging());
Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
public void execute() {
try {
if (flagOnLoadRefresh) {
flagOnLoadRefresh = false;
renderSelectionFill(0, 0);
((CellSelectionModel) grdDisplay.getDmGrid().getGrid().getSelectionModel()).selectCell(0, 0);
toolContainer.setTitle(widgetName);
...
```

`grdDisplay.addCallBack` → `execute()`
```java
try {
if (flagOnLoadRefresh) {
flagOnLoadRefresh = false;
renderSelectionFill(0, 0);
((CellSelectionModel) grdDisplay.getDmGrid().getGrid().getSelectionModel()).selectCell(0, 0);
toolContainer.setTitle(widgetName);
} catch (Exception e) {
txtSearchValue.setFocus(true);
```

`grdDisplay.addCallBack` → `getParam()`
```java
return null;
```
- Confirm: "Are you sure? You want to close."
- Alert: "Item is mandatory."

## Files
| file | lines |
|---|---|
| client/IMSetting.java | 651 |
| client/IMSetting_Dsg.java | 556 |
| client/ItemMovementDisplay.java | 483 |
| client/ItemMovementDisplay_Dsg.java | 282 |
| server/ItemMovementDisplay_SrvImpl.java | 1438 |
| shared/DefaultValue_Mdl.java | 19 |
| shared/DisplayRoute_Mdl.java | 105 |
| shared/GrdItemMovementDisplay_Mdl.java | 99 |
| shared/IMSetting_Mdl.java | 264 |
| shared/IMSetting_Vld.java | 17 |
| shared/ItemMovementDisplay_Srv.java | 40 |
| shared/ItemMovementDisplay_SrvAsync.java | 41 |
| shared/TxtHAcGroup_Mdl.java | 42 |
| shared/TxtHItem_Mdl.java | 52 |
| shared/TxtHParty_Mdl.java | 50 |
| shared/TxtHVoucherNo_Mdl.java | 60 |

## UI labels / columns (client)
`From Date`, `To Date`, `Site Name`, `Warehouse`, `Item`, `Party`, `Account Group`, `Voucher Type`, `Pack Size`, `Item Nature`, `BF`, `GSM`, `Group By`, `Summary On`, `Report For`

## Models (shared DTOs)
### TxtHVoucherNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | voucherType |
| String | voucherDate |
| String | voucherCategory |
| String | shortName |
| String | supplierName |

### DisplayRoute_Mdl
| type | field |
|---|---|
| PageName | pageName |
| CodeType | codeType |
| String | forCode |
| String | prevSummaryOn |
| String | lblHeading |
| String | clickRowIndex |
| String | clickColIndex |
| IMSetting_Mdl | displaySetting_mdl |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | forName |

### TxtHItem_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | itemType |
| String | itemCategory |
| String | itemGroup |

### TxtHAcGroup_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | parentGroupCode |
| String | parentGroupName |
| String | nature |

### TxtHParty_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityName |
| String | acGroupName |
| String | nature |

### GrdItemMovementDisplay_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | styleFor |
| String | particularsCode |
| String | unit |
| String quantity, | dispQuantity |
| String effectiveRate, | dispEffectiveRate |
| String value, | dispValue |
| boolean | selected |

### IMSetting_Mdl
| type | field |
|---|---|
| String fromDate, | toDate |
| String siteCode, | siteName |
| String departmentCode, | departmentName |
| String itemCode, | itemName |
| String partyCode, | partyName |
| String acGroupCode, | acGroupName |
| String v_TypeCode, | v_TypeName |
| String packSizeCode, | packSizeName |
| String | summaryOn |
| String | groupBy |
| String | reportFor |
| String | itemNatureCode |
| String | itemNatureName |
| String | bfCode |
| String | bfName |
| String | gsmCode |
| String | gsmName |

## RPC interface
| method | params | returns |
|---|---|---|
| getDefault | IMSetting_Mdl setting_Mdl | DmResult<DefaultValue_Mdl> |
| getItemMovementAnalysis | IMSetting_Mdl setting_Mdl, DmHelper helper, boolean blnToEndOfFile, KeyFieldExtended param | DmResultPageing<List<GrdItemMovementDisplay_Mdl>> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDepartment | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getTxtHParty | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getTxtHAcGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHAcGroup_Mdl>> |
| getTxtHVoucherType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPackSize | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSummaryOn | String code, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHReportFor | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| fillSettings |  | DmResult<IMSetting_Mdl> |
| getTxtHBf | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGsm | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemNature | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `IMSetting_Vld`
```java
public class IMSetting_Vld {
    public static void chkSave(IMSetting_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getSiteCode(), "Site", 300);
        DmShared.isValidText(mrTemp.getSummaryOn(), "Summary On", 500);
        DmShared.isValidText(mrTemp.getReportFor(), "Report For", 600);
    }
}
```

## Server `ItemMovementDisplay_SrvImpl.java` (1438 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.getNumberFormat`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getDefault`
`public DmResult<DefaultValue_Mdl> getDefault(IMSetting_Mdl setting_Mdl)`

- SQL 1: `SELECT um.unit as skuName FROM itemMast im LEFT JOIN unitMast um ON um.code = im.sku WHERE im.code = :itemCode`
- SQL 2: `IF OBJECT_ID('tempdb.. {…} ') IS NOT NULL DROP TABLE {…} ; CREATE TABLE {…} ( orderIndex bigint, styleFor varchar(50), particularsCode varchar(25) , particulars varchar(250), unit varchar(20) , quantity decimal(18, 7), effectiveRate decimal(18, 2), value decimal(18, 2) )`
- SQL 3: `WITH cteMovement AS (SELECT MAX( {…} ) AS particularsCode , MAX( {…} ) AS particulars , {…} AS groupByCode , {…} AS groupByName , MAX(vt.description) AS vTypeDesc , MAX(sgd.name) AS departmentName , MAX(im.name) AS itemName , MAX( {…} ) AS unit , MAX( {…} ) AS decimalPlaces , ABS(SUM(ISNULL( {…} , 0)-ISNULL( {…} , 0))) AS quantity , SUM(CASE WHEN vt.parentVoucher IN ('Purchase', 'Purchase Return') THEN ABS(ISNULL( {…} , 0)) WHEN vt.parentVoucher IN ('Sale', 'Sale Return') THEN (ABS(ISNULL(stk.landedSkuSaleValue, 0))+ABS(ISNULL(stk.otherAdjustment, 0))) ELSE CASE WHEN (ISNULL(stk.isuQty,0) + ISNULL(stk.recQty,0) ) = 0 THEN ISNULL( {…} ,0) ELSE ABS(ISNULL( {…} , 0)) END END) AS amount , {…} AS movementType FROM stock stk LEFT JOIN voucher_Type vt ON vt.v_Type = stk.v_Type LEFT JOIN itemMast im ON im.code = stk.itemCode LEFT JOIN unitMast um ON um.code = im.sku LEFT JOIN itemBatch ib ON ib.code = stk.ibCode LEFT JOIN packSizeMast psm ON psm.code = stk.packSizeCode LEFT JOIN unitMast ump ON ump.code = psm.packUnitCode LEFT JOIN subGroup sg ON sg.subCode = stk.partyCode LEFT JOIN acGroup acg ON acg.groupCode = sg.groupCode LEFT JOIN subGroup sgd ON sgd.subCode = stk.departmentCode WHERE {…} AND (CASE WHEN ISNULL(stk.recQty,0) > 0 OR ISNULL(stk.isuQty,0) < 0 THEN 'MOVEMENT INWARD' WHEN ISNULL(stk.recQty,0) < 0 OR ISNULL(stk.isuQty,0) > 0 THEN 'MOVEMENT OUTWARD' WHEN ISNULL(stk.recQty,0) = 0 AND ISNULL(stk.isuQty,0) = 0 AND ISNULL((ISNULL(stk.landedSkuValue,0) + ISNULL(stk.otherAdjustment,0)),0) <> 0 THEN 'MOVEMENT INWARD' ELSE '' END) <> '' GROUP BY {…} ) SELECT cm.movementType, cm.vTypeDesc, cm.particularsCode, cm.particulars , cm.groupByCode, IIF(ISNULL(cm.groupByName, '')<>'', cm.groupByName, '-') AS groupByName , cm.itemName, cm.departmentName, cm.unit, cm.decimalPlaces, cm.quantity , CAST(CASE WHEN ISNULL(cm.quantity, 0) <> 0 THEN ABS(ISNULL(cm.amount, 0) / cm.quantity) ELSE 0 END AS DECIMAL(18,2)) AS effectiveRate , cm.amount AS value FROM cteMovement cm ORDER BY cm.movementType, cm.vTypeDesc, cm.particulars`
- SQL 4: `INSERT INTO {…} (orderIndex, styleFor, particularsCode, particulars, unit , quantity, effectiveRate, value) VALUES( :orderIndex, :styleFor, :particularsCode, :particulars, :unit , :quantity, :effectiveRate, :value)`
- Params: `:itemCode` ← `setting_Mdl.getItemCode()`
- Other calls: `IMSetting_Vld.chkSave`, `STServer.getSsnDepartmentCodeList`

### `addNewRecord`
`private void addNewRecord(Record_Mdl record_Mdl, DmPreparedStatement psTempIn) throws Exception`

- Params: `:orderIndex` ← `record_Mdl.getOrderIndex()`; `:styleFor` ← `DmShared.xNull(record_Mdl.getStyleFor())`; `:particularsCode` ← `DmShared.xNull(record_Mdl.getParticularsCode())`; `:particulars` ← `DmShared.xNull(record_Mdl.getParticulars())`; `:unit` ← `DmShared.xNull(record_Mdl.getUnit())`; `:quantity` ← `DmServer.vNull(record_Mdl.getQuantity())`; `:effectiveRate` ← `DmServer.vNull(record_Mdl.getEffectiveRate(), "0.00")`; `:value` ← `DmServer.vNull(record_Mdl.getValue(), "0.00")`

### `getParameter`
`private void getParameter(IMSetting_Mdl setting_Mdl, DmPreparedStatement psTemp)`

- Params: `:one` ← `1`; `:fromDate` ← `DmShared.dNull(setting_Mdl.getFromDate())`; `:toDate` ← `DmShared.dNull(setting_Mdl.getToDate())`; `:itemCode` ← `DmShared.xNull(setting_Mdl.getItemCode())`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getItemMovementAnalysis`
`public DmResultPageing<List<GrdItemMovementDisplay_Mdl>> getItemMovementAnalysis(IMSetting_Mdl setting_Mdl, DmHelper helper, boolean blnToEndOfFile, KeyFieldExtended param)`

- SQL 1: `SELECT stk.orderIndex, stk.styleFor, stk.particularsCode, stk.particulars, stk.unit, stk.quantity, stk.effectiveRate, stk.value FROM {…} AS stk`
- Other calls: `DmJasper.formatAmount`

### `getTable`
`private String getTable(DmRSSPrivate rssPrivate)`


### `fillSettings`
`public DmResult<IMSetting_Mdl> fillSettings()`

- SQL 1: `SELECT name FROM siteMast WHERE code = :code`
- Params: `:code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in ( {…} :code {…} )`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT dm.subCode AS code, sg.name FROM departmentMast dm LEFT JOIN subGroup sg ON sg.subCode = dm.subCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature Left join subGroupSiteList as sgsl on sg.subCode=sgsl.subCode WHERE sgsl.site_Code = :siteCode {…}`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getTxtHItemName`
`public DmResultPageing<List<TxtHItem_Mdl>> getTxtHItemName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name AS itemName, im.manualCode, it.name AS itemType, ic.name AS itemCategory, ig.name AS itemGroup FROM itemMast im LEFT JOIN itemType it ON it.code = im.itemType LEFT JOIN itemCat ic ON ic.code = im.itemCategoryCode LEFT JOIN itemGroup ig ON ig.code = im.itemGroup WHERE im.filterItemType IN (:FM, :SM)`
- Params: `:FM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:SM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHParty`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHParty(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code, sg.name, sg.manualCode, sgn.name AS nature, c.cityName, acg.groupName AS acGroupName FROM subGroup sg LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN acGroup acg ON acg.groupCode = sg.groupCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature`

### `getTxtHAcGroup`
`public DmResultPageing<List<TxtHAcGroup_Mdl>> getTxtHAcGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT acg.groupCode AS code, acg.groupName AS name , acg.groupUnder AS parentGroupCode, acgp.groupName AS parentGroupName, sgn.name AS nature FROM acGroup acg LEFT JOIN acGroup acgp ON acgp.groupCode = acg.groupUnder LEFT JOIN subGroupNature sgn ON sgn.code = acg.nature`

### `getTxtHVoucherType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVoucherType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type AS code, vt.description AS name, vt.short_Name FROM voucher_type vt`

### `getTxtHPackSize`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHPackSize(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT psm.code, psm.name, um.unit FROM packSizeMast psm LEFT JOIN unitMast um ON um.code = psm.packUnitCode`

### `getTxtHSummaryOn`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSummaryOn(String code, DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHReportFor`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHReportFor(DmHelper helper, boolean blnToEndOfFile)`


### `getPosition`
`public DmResultPageing<List<KeyFieldExtended>> getPosition(DmHelper helper, boolean blnToEndOfFile)`


### `getCostCenter`
`public DmResultPageing<List<KeyFieldExtended>> getCostCenter(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT code, name FROM costCenterMast WHERE (ISNULL(underCostCenter, 0) = 0)`

### `getTxtHBf`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHBf(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT bf.code, bf.code as name FROM bfMast bf where bf.isActive = 1`

### `getTxtHGsm`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGsm(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT gm.code, gm.code as name FROM gsmMast gm`

### `getTxtHItemNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemNature(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT inm.code, inm.name FROM itemNature inm`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmJasper.server`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.self.client`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`, `paperbiz.store.itemMovementDisplay.shared`, `paperbiz.store.movementAnalysisDisplay.shared`, `paperbiz.store.report.client`, `paperbiz.store.stockBatch.shared`, `paperbiz.store.stockLedger.client`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
