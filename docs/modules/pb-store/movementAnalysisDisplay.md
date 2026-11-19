# pb-store / movementAnalysisDisplay

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/store/src/movementAnalysisDisplay/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: 'From Date' (Text); 'To Date' (Text); 'Site Name' (Text); 'Summary On' (Text); 'Report For' (Text); rule: From Date cannot be greater than To Date
- Lookups/suggestions: getTxtHSite, getTxtHDepartment, getTxtHAcGroup, getTxtHParty, getTxtHItemType, getTxtHItemGroup, getTxtHItemCategory, getTxtHItemName, getTxtHPackSize, getTxtHVoucherType, getTxtHSummaryOn, getTxtHReportFor, getTxtHBf, getTxtHGsm, getTxtHItemNature
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `MASetting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | To Date | DmTextBox | `txtFromDate` | `11, true` |
| 2 | - | DmTextBox | `txtToDate` | `11, true` |
| 3 | Site Name | DmTextHelpMulti | `txtHSite` | `KeyField_PrpAcs, 10, true` |
| 4 | Warehouse | DmTextHelpMulti | `txtHDepartment` | `KeyField_PrpAcs, 5` |
| 5 | Account Group | DmTextHelpMulti | `txtHAcGroup` | `TxtHAcGroup_PrpAcs, 10` |
| 6 | Party | DmTextHelpMulti | `txtHParty` | `TxtHPartyName_PrpAcs, 10` |
| 7 | Item Type | DmTextHelpMulti | `txtHVoucherType` | `TxtHVoucherType_PrpAcs, 10` |
| 8 | - | DmTextHelpMulti | `txtHItemType` | `KeyField_PrpAcs, 10` |
| 9 | Item Category | DmTextHelpMulti | `txtHItemGroup` | `KeyField_PrpAcs, 10` |
| 10 | - | DmTextHelpMulti | `txtHItemCategory` | `KeyField_PrpAcs, 10` |
| 11 | Item Name | DmTextHelpMulti | `txtHItemName` | `TxtHItemNamePrpAcs, 10` |
| 12 | GSM | DmTextHelp | `txtHItemNature` | `com.google.gwt.core.client.KeyField_PrpAcs, 5, false` |
| 13 | - | DmTextHelp | `txtHBf` | `com.google.gwt.core.client.KeyField_PrpAcs, 5, false` |
| 14 | - | DmTextHelp | `txtHGsm` | `com.google.gwt.core.client.KeyField_PrpAcs, 5, false` |
| 15 | Report For | DmTextHelpMulti | `txtHPackSize` | `TxtHPackSize_PrpAcs, 10` |
| 16 | - | DmTextHelp | `txtHReportFor` | `TxtHSummaryType_PrpAcs, 3, true` |
| 17 | Group By | DmTextHelp | `txtHSummaryOn` | `TxtHSummaryOn_PrpAcs, 9, true` |
| 18 | - | DmTextHelp | `txtHGroupBy` | `KeyField_PrpAcs, 7` |

Other widgets (not in tab list): `txtClosing` (DmTextBoxNumeric)

**Grid / popup columns** (header, width, db field):
- `txtHSite`: Name [Text, w150, name]
- `txtHDepartment`: Name [Text, w125, Name]
- `txtHAcGroup`: Group Name [Text, w200, name]; Parent [Text, w200, parentGroupName]; Nature [Text, w150, nature]
- `txtHParty`: Name [Text, w300, name]; Manual Code [Text, w100, manualCode]; City Name [Text, w200, cityName]; Ac Group [Text, w180, acGroupName]; Nature [Text, w120, nature]
- `txtHItemType`: Name [Text, w150, name]
- `txtHItemGroup`: Name [Text, w150, name]
- `txtHItemCategory`: Name [Text, w250, name]
- `txtHItemName`: Item Name [Text, w400, itemName]; Manual Code [Text, w80, manualCode]; Item Type [Text, w100, itemType]; Item Category [Text, w100, itemCategory]; Item Group [Text, w150, itemGroup]
- `txtHPackSize`: Pack Size [Text, w150, name]; Unit [Text, w80, unit]
- `txtHVoucherType`: Type [Text, w180, name]; Short Name [Text, w80, short_Name]
- `txtHSummaryOn`: Name [Text, w100, name]
- `txtHGroupBy`: Name [Text, w100]
- `txtHReportFor`: Name [Text, w100, name]
- `txtHBf`: Name [Text, w50, name]
- `txtHGsm`: Name [Text, w50, name]
- `txtHItemNature`: Name [Text, w75, name]

**Lookup / suggestion / find sources (server RPC called):**
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

`txtHItemType.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItemType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHItemType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemType).show();
```

`txtHItemType.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemType).show();
```

`txtHItemGroup.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItemGroup(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyF
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemGroup).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHItemGroup.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemGroup).show();
```

`txtHItemGroup.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemGroup.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemGroup).show();
```

`txtHItemCategory.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItemCategory(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<K
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemCategory).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHItemCategory.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemCategory).show();
```

`txtHItemCategory.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemCategory.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemCategory).show();
```

`txtHItemName.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItemName(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHI
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemName).show();
public void onSuccess(DmResultPageing<List<TxtHItem_Mdl>> result) {
if (result.isStatus()) {
txtHItemName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemName).show();
```

`txtHItemName.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemName).show();
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

`txtHSummaryOn.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHSummaryOn(false, txtHGroupBy.getTag(), helper, blnToEndOfFile, new AsyncCallb
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
srvAsync.invokeRPC(blnCallBackWait).getTxtHSummaryOn(true, txtHSummaryOn.getTag(), helper, blnToEndOfFile, new AsyncCall
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
- Alert: "To Date should be greater than from date!"
- Alert: "'Site Name' is mandatory."
- Alert: "'Summary On' is mandatory."
- Alert: "'Report For' is mandatory."

### Screen `MovementAnalysisDisplay`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmTextBox | `txtSearchValue` | `100, false` |
| 2 | - | DmGridPaging | `grdDisplay` | `GrdDisplay_PrpAcs, 20` |

**Grid / popup columns** (header, width, db field):
- `grdDisplay`: Particulars [Text, w320, particulars]; Unit [Text, w100, unit]; Quantity [Text, w150, inQty]; Eff. Rate [Text, w100, inEffRate]; Value [Text, w150, inValue]; Quantity [Text, w150, outQty]; Eff. Rate [Text, w100, outEffRate]; Value [Text, w150, outValue]

**Lookup / suggestion / find sources (server RPC called):**
- `setting` → `getDefault`
- `grdDisplay` → `getMovementAnalysisSummary`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`setting.addCallBack` → `onOk()`
```java
setting_Mdl = acDisplaySetting;
refreshGrid();
```

`grdDisplay.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnToEndOfFile).getMovementAnalysisSummary(setting_Mdl, helper, blnToEndOfFile, param, new AsyncCallb
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdMovementAnalysisDisplay_Mdl>> result) {
if (result.isStatus()) {
grdDisplay.setData(result.getData(), result.getPaging());
Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
public void execute() {
try {
int rowIndex = DmShared.iNull(displayRouteList.get((displayRouteList.size() - 1)).getClickRowIndex()).intValue();
...
```

`grdDisplay.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
grdDisplay.setData(result.getData(), result.getPaging());
Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
public void execute() {
try {
int rowIndex = DmShared.iNull(displayRouteList.get((displayRouteList.size() - 1)).getClickRowIndex()).intValue();
int colIndex = DmShared.iNull(displayRouteList.get((displayRouteList.size() - 1)).getClickColIndex()).intValue();
if (flagOnLoadRefresh) {
flagOnLoadRefresh = false;
renderSelectionFill(rowIndex, colIndex);
...
```

`grdDisplay.addCallBack` → `execute()`
```java
try {
int rowIndex = DmShared.iNull(displayRouteList.get((displayRouteList.size() - 1)).getClickRowIndex()).intValue();
int colIndex = DmShared.iNull(displayRouteList.get((displayRouteList.size() - 1)).getClickColIndex()).intValue();
if (flagOnLoadRefresh) {
flagOnLoadRefresh = false;
renderSelectionFill(rowIndex, colIndex);
if ((displayRouteList.size() > 0)) {
((CellSelectionModel) grdDisplay.getDmGrid().getGrid().getSelectionModel()).selectCell(rowIndex, colIndex);
} else {
txtSearchValue.setFocus(true);
...
```

`grdDisplay.addCallBack` → `getParam()`
```java
return null;
```
- Confirm: "Are you sure? You want to close."

## Files
| file | lines |
|---|---|
| client/MASetting.java | 735 |
| client/MASetting_Dsg.java | 628 |
| client/MovementAnalysisDisplay.java | 553 |
| client/MovementAnalysisDisplay_Dsg.java | 385 |
| server/MovementAnalysisDisplay_SrvImpl.java | 1732 |
| shared/DefaultValue_Mdl.java | 122 |
| shared/DisplayRoute_Mdl.java | 113 |
| shared/GrdMovementAnalysisDisplay_Mdl.java | 168 |
| shared/MASetting_Mdl.java | 332 |
| shared/MASetting_Vld.java | 24 |
| shared/MovementAnalysisDisplay_Srv.java | 46 |
| shared/MovementAnalysisDisplay_SrvAsync.java | 47 |
| shared/RptMovementAnalysisDisplay_Mdl.java | 77 |
| shared/TxtHAcGroup_Mdl.java | 42 |
| shared/TxtHItem_Mdl.java | 52 |
| shared/TxtHParty_Mdl.java | 50 |
| shared/TxtHVoucherNo_Mdl.java | 60 |

## UI labels / columns (client)
`From Date`, `To Date`, `Site Name`, `Warehouse`, `Account Group`, `Party`, `Voucher Type`, `Item Type`, `Item Group`, `Item Category`, `Item Name`, `Item Nature`, `BF`, `GSM`, `Pack Size`, `Report For`, `Summary On`, `Group By`

## Models (shared DTOs)
### GrdMovementAnalysisDisplay_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | styleFor |
| String | forCode |
| String | particularsCode |
| String | summaryOn |
| String | unit |
| String inQty, | inDispQty |
| String inEffRate, | inDispEffRate |
| String inValue, | inDispValue |
| String outQty, | outDispQty |
| String outEffRate, | outDispEffRate |
| String outValue, | outDispValue |
| boolean | selected |

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
| String | particularsCode |
| String | prevSummaryOn |
| String forCode, | forName |
| String | clickRowIndex |
| String | clickColIndex |
| MASetting_Mdl | setting_mdl |

### MASetting_Mdl
| type | field |
|---|---|
| String | lblHeading |
| String fromDate, | toDate |
| String siteCode, | siteName |
| String departmentCode, | departmentName |
| String acGroupCode, | acGroupName |
| String partyCode, | partyName |
| String itemTypeCode, | itemTypeName |
| String itemGroupCode, | itemGroupName |
| String itemCatCode, | itemCatName |
| String itemCode, | itemName |
| String packSizeCode, | packSizeName |
| String v_TypeCode, | v_TypeName |
| String | summaryOn |
| String | groupBy |
| String | reportFor |
| String | itemNatureCode |
| String | itemNatureName |
| String | bfCode |
| String | bfName |
| String | gsmCode |
| String | gsmName |

### DefaultValue_Mdl
| type | field |
|---|---|
| String totalInQty, | totalInDispQty |
| String totalInEffRate, | totalInDispEffRate |
| String totalInValue, | totalInDispValue |
| String totalOutQty, | totalOutDispQty |
| String totalOutEffRate, | totalOutDispEffRate |
| String totalOutValue, | totalOutDispValue |
| String | unit |

### TxtHItem_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | itemType |
| String | itemCategory |
| String | itemGroup |

### RptMovementAnalysisDisplay_Mdl
| type | field |
|---|---|
| String | totalInQty |
| String | totalInEffRate |
| String | totalInValue |
| String | totalOutQty |
| String | totalOutEffRate |
| String | totalOutValue |
| MASetting_Mdl | displaySettingMdl |

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

## RPC interface
| method | params | returns |
|---|---|---|
| getDefault | MASetting_Mdl displaySetting, DisplayRoute_Mdl currentDisplayRoute_mdl | DmResult<DefaultValue_Mdl> |
| getMovementAnalysisSummary | MASetting_Mdl displaySetting, DmHelper helper, boolean blnToEndOfFile, KeyFieldExtended param | DmResultPageing<List<GrdMovementAnalysisDisplay_Mdl>> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDepartment | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAcGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHAcGroup_Mdl>> |
| getTxtHParty | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getTxtHItemType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemCategory | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getTxtHPackSize | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVoucherType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSummaryOn | boolean isGroupBy, String code, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHReportFor | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| fillSettings |  | DmResult<MASetting_Mdl> |
| getTxtHBf | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGsm | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemNature | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `MASetting_Vld`
```java
public class MASetting_Vld {
    public static void chkSave(MASetting_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getFromDate(), "'From Date'", 100);
        DmShared.isValidText(mrTemp.getToDate(), "'To Date'", 200);
        DmShared.isValidText(mrTemp.getSiteCode(), "'Site Name'", 300);
        DmShared.isValidText(mrTemp.getSummaryOn(), "'Summary On'", 400);
        DmShared.isValidText(mrTemp.getReportFor(), "'Report For'", 500);
        if (!DmShared.dNull(mrTemp.getFromDate()).equalsIgnoreCase("") && !DmShared.dNull(mrTemp.getToDate()).equalsIgnoreCase("")) {
            if (DmShared.convertDate(mrTemp.getFromDate()).getTime() > DmShared.convertDate(mrTemp.getToDate()).getTime()) {
                throw new DmException(100, "From Date cannot be greater than To Date");
            }
        }
    }
}
```

## Server `MovementAnalysisDisplay_SrvImpl.java` (1732 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.getNumberFormat`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getDefault`
`public DmResult<DefaultValue_Mdl> getDefault(MASetting_Mdl setting_Mdl, DisplayRoute_Mdl route_mdl)`

- SQL 1: `IF OBJECT_ID('tempdb.. {…} ') IS NOT NULL DROP TABLE {…} ; CREATE TABLE {…} (orderIndex bigint , styleFor varchar(50), summaryOn varchar(20), forCode varchar(25) , particularsCode varchar(25), particulars varchar(250), unit varchar(20) , inQty decimal(18, 7), inEffRate decimal(18, 2), inValue decimal(18, 2) , outQty decimal(18, 7), outEffRate decimal(18, 2), outValue decimal(18, 2) )`
- SQL 2: `WITH cteMovement AS ( SELECT MAX(stk.itemCode) AS forCode , MAX( {…} ) AS particularsCode , MAX( {…} ) AS particulars , {…} AS groupByCode , {…} AS groupByName , MAX( {…} ) AS unit , MAX( {…} ) AS decimalPlaces , MAX(sgd.name) AS departmentName , {…} AS inwardQty ,SUM(CASE WHEN vt.parentVoucher IN ('Purchase') THEN ISNULL( {…} , 0) WHEN vt.parentVoucher IN ('Sale Return') THEN (ABS(ISNULL(stk.landedSkuSaleValue, 0))+ABS(ISNULL(stk.otherAdjustment, 0))) ELSE CASE WHEN ISNULL(stk.isuQty,0) < 0 THEN ABS(ISNULL( {…} , 0)) WHEN ISNULL(stk.recQty,0) > 0 THEN ISNULL( {…} , 0) WHEN (ISNULL(stk.isuQty,0) + ISNULL(stk.recQty,0) ) = 0 THEN ISNULL( {…} ,0) ELSE 0 END END) AS inwardValue , {…} AS outwardQty ,SUM(CASE WHEN vt.parentVoucher IN ('Purchase Return') THEN ABS(ISNULL( {…} , 0)) WHEN vt.parentVoucher IN ('Sale') THEN (ABS(ISNULL(stk.landedSkuSaleValue, 0))+ABS(ISNULL(stk.otherAdjustment, 0))) ELSE CASE WHEN ISNULL(stk.recQty,0) < 0 THEN ABS(ISNULL( {…} , 0)) WHEN ISNULL(stk.isuQty,0) > 0 THEN ISNULL( {…} , 0) ELSE 0 END END) AS outwardValue FROM stock stk LEFT JOIN voucher_Type vt ON vt.v_Type = stk.v_Type LEFT JOIN itemMast im ON im.code = stk.itemCode LEFT JOIN packSizeMast psm ON psm.code = stk.packSizeCode LEFT JOIN unitMast um ON um.code = im.sku LEFT JOIN itemBatch ib ON ib.code = stk.ibCode LEFT JOIN unitMast ump ON ump.code = psm.packUnitCode LEFT JOIN itemType it ON it.code = im.itemType LEFT JOIN itemCat ic ON ic.code = im.itemCategoryCode LEFT JOIN itemGroup ig ON ig.code = im.itemGroup LEFT JOIN subGroup sg ON sg.subCode = stk.partyCode LEFT JOIN subGroup sgd ON sgd.subCode = stk.departmentCode LEFT JOIN acGroup acg ON acg.groupCode = sg.groupCode WHERE {…} AND (CASE WHEN ISNULL(stk.recQty,0) > 0 OR ISNULL(stk.isuQty,0) < 0 THEN 'MOVEMENT INWARD' WHEN ISNULL(stk.recQty,0) < 0 OR ISNULL(stk.isuQty,0) > 0 THEN 'MOVEMENT OUTWARD' WHEN ISNULL(stk.recQty,0) = 0 AND ISNULL(stk.isuQty,0) = 0 AND ISNULL((ISNULL(stk.landedSkuValue,0) + ISNULL(stk.otherAdjustment,0)),0) <> 0 THEN 'MOVEMENT INWARD' ELSE '' END) <> '' GROUP BY {…} ) SELECT cm.forCode , cm.particularsCode, cm.particulars , cm.groupByCode, IIF(ISNULL(cm.groupByName, '')<>'', cm.groupByName, '-') AS groupByName , cm.unit, cm.decimalPlaces, cm.inwardQty , CAST(CASE WHEN ISNULL(cm.inwardQty, 0) <> 0 THEN ABS(ISNULL(cm.inwardValue, 0) / cm.inwardQty) ELSE 0 END AS DECIMAL(18,2)) AS inwardEffRate , cm.inwardValue , cm.outwardQty , CAST(CASE WHEN ISNULL(cm.outwardQty, 0) <> 0 THEN ABS(ISNULL(cm.outwardValue, 0) / cm.outwardQty) ELSE 0 END AS DECIMAL(18,2)) AS outwardEffRate , cm.outwardValue FROM cteMovement cm ORDER BY {…}`
- SQL 3: `INSERT INTO {…} (orderIndex, styleFor , summaryOn, forCode, particularsCode, particulars, unit , inQty, inEffRate, inValue, outQty, outEffRate, outValue) VALUES( :orderIndex, :styleFor, :summaryOn, :forCode, :particularsCode, :particulars, :unit , :inQty, :inEffRate, :inValue, :outQty, :outEffRate, :outValue)`
- Params: `:fromDate` ← `DmServer.convertDate(setting_Mdl.getFromDate())`; `:toDate` ← `DmServer.convertDate(setting_Mdl.getToDate())`; `:particularsCode` ← `DmShared.xNull(route_mdl.getParticularsCode())`
- Other calls: `DmJasper.formatAmount`, `MASetting_Vld.chkSave`, `STServer.getSsnDepartmentCodeList`

### `addNewRecord`
`private void addNewRecord(Record_Mdl record_Mdl, DmPreparedStatement psTempIn) throws Exception`

- Params: `:orderIndex` ← `record_Mdl.getOrderIndex()`; `:styleFor` ← `DmShared.xNull(record_Mdl.getStyleFor())`; `:summaryOn` ← `DmShared.xNull(record_Mdl.getSummaryOn())`; `:forCode` ← `DmShared.xNull(record_Mdl.getForCode())`; `:particularsCode` ← `DmShared.xNull(record_Mdl.getParticularsCode())`; `:particulars` ← `DmShared.xNull(record_Mdl.getParticulars())`; `:unit` ← `DmShared.xNull(record_Mdl.getUnit())`; `:inQty` ← `DmServer.vNull(record_Mdl.getInQty())`; `:inEffRate` ← `DmServer.vNull(record_Mdl.getInEffRate(), "0.00")`; `:inValue` ← `DmServer.vNull(record_Mdl.getInValue(), "0.00")`; `:outQty` ← `DmServer.vNull(record_Mdl.getOutQty())`; `:outEffRate` ← `DmServer.vNull(record_Mdl.getOutEffRate(), "0.00")`; `:outValue` ← `DmServer.vNull(record_Mdl.getOutValue(), "0.00")`

### `getParameter`
`private void getParameter(MASetting_Mdl setting_Mdl, DmPreparedStatement psTemp)`

- Params: `:one` ← `1`; `:fromDate` ← `DmShared.dNull(setting_Mdl.getFromDate())`; `:toDate` ← `DmShared.dNull(setting_Mdl.getToDate())`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getMovementAnalysisSummary`
`public DmResultPageing<List<GrdMovementAnalysisDisplay_Mdl>> getMovementAnalysisSummary(MASetting_Mdl setting_Mdl, DmHelper helper, boolean blnToEndOfFile, KeyFieldExtended param)`

- SQL 1: `SELECT stk.orderIndex, stk.styleFor, stk.summaryOn, stk.forCode, stk.particularsCode, stk.particulars, stk.unit , stk.inQty, stk.inEffRate, stk.inValue, stk.outQty, stk.outEffRate, stk.outValue FROM {…} AS stk`
- Other calls: `DmJasper.formatAmount`

### `getTable`
`private String getTable(DmRSSPrivate rssPrivate)`


### `fillSettings`
`public DmResult<MASetting_Mdl> fillSettings()`

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

### `getTxtHAcGroup`
`public DmResultPageing<List<TxtHAcGroup_Mdl>> getTxtHAcGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT acg.groupCode AS code, acg.groupName AS name , acg.groupUnder AS parentGroupCode, acgp.groupName AS parentGroupName, sgn.name AS nature FROM acGroup acg LEFT JOIN acGroup acgp ON acgp.groupCode = acg.groupUnder LEFT JOIN subGroupNature sgn ON sgn.code = acg.nature`

### `getTxtHParty`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHParty(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code, sg.name, sg.manualCode, sgn.name AS nature, c.cityName, acg.groupName AS acGroupName FROM subGroup sg LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN acGroup acg ON acg.groupCode = sg.groupCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature`

### `getTxtHItemType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT it.code, it.name FROM itemType it`

### `getTxtHItemGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ig.code, ig.name FROM itemGroup ig`

### `getTxtHItemCategory`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemCategory(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ic.code, ic.name FROM itemCat ic`

### `getTxtHItemName`
`public DmResultPageing<List<TxtHItem_Mdl>> getTxtHItemName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name AS itemName, im.manualCode, it.name AS itemType, ic.name AS itemCategory, ig.name AS itemGroup FROM itemMast im LEFT JOIN itemType it ON it.code = im.itemType LEFT JOIN itemCat ic ON ic.code = im.itemCategoryCode LEFT JOIN itemGroup ig ON ig.code = im.itemGroup WHERE im.filterItemType IN (:FM, :SM)`
- Params: `:FM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:SM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHPackSize`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHPackSize(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT psm.code, psm.name, um.unit FROM packSizeMast psm LEFT JOIN unitMast um ON um.code = psm.packUnitCode`

### `getTxtHVoucherType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVoucherType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type AS code, vt.description AS name, vt.short_Name FROM voucher_type vt`

### `getTxtHSummaryOn`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSummaryOn(boolean isGroupBy, String code, DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHReportFor`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHReportFor(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHBf`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHBf(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT bf.code, bf.code as name FROM bfMast bf where bf.isActive = 1`

### `getTxtHGsm`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGsm(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT gm.code, gm.code as name FROM gsmMast gm`

### `getTxtHItemNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemNature(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT inm.code, inm.name FROM itemNature inm`

### `getPosition`
`public DmResultPageing<List<KeyFieldExtended>> getPosition(DmHelper helper, boolean blnToEndOfFile)`


### `getCostCenter`
`public DmResultPageing<List<KeyFieldExtended>> getCostCenter(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT code, name FROM costCenterMast WHERE (ISNULL(underCostCenter, 0) = 0)`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmJasper.server`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.self.client`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.store.itemMovementDisplay.shared`, `paperbiz.store.movementAnalysisDisplay.shared`, `paperbiz.store.report.client`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
