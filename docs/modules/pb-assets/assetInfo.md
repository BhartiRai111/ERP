# pb-assets / assetInfo

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/assets/src/assetInfo/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHAssetItem, getTxtHAsset, getTxtHAssetGroup
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `AssetInfo`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Asset Item | DmTextHelp | `txtHItem` | `KeyField_PrpAcs, 7, true` |
| 2 | - | DmGridPaging | `grdAsset` | `GrdAsset_PrpAcs, 17` |
| 3 | - | DmGridPaging | `grdLedger` | `GrdLedger_PrpAcs, 17` |
| 4 | - | DmGridPaging | `grdDown` | `GrdMachineDown_PrpAcs, 17` |

Other widgets (not in tab list): `txtSearchValueAsset` (DmTextBox), `txtSearchValueLedger` (DmTextBox), `txtSearchValueDown` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `txtHItem`: Name [Text, w100, name]
- `grdAsset`: Asset [Text, w250, parentAsset]; Sub-Asset [Text, w250, subAsset]; Nature [Text, w100, nature]; Department [Text, w250, department]; Purchase Date [Text, w100, dateOfPurchase]; Supplier [Text, w270, supplier]
- `grdLedger`: Voucher No. [Text, w130, recId]; Voucher Date [Text, w100, v_date]; Type [Text, w80, short_Name]; Asset [Text, w200, parentAsset]; Sub-Asset [Text, w200, subAsset]; Current Department [Text, w150, currentDepartment]; Nature [Text, w100, assetNature]; Amount [Text, w120, amount]; Remark [Text, w280, remark]
- `grdDown`: Voucher No. [Text, w130, recId]; Voucher Date [Text, w100, v_date]; Type [Text, w80, short_Name]; Asset [Text, w200, parentAsset]; Sub-Asset [Text, w200, subAsset]; Down Date [Text, w100, machineDownDate]; Down Time [Text, w80, machineDownTime]; Up Date [Text, w100, machineUpDate]; Up Time [Text, w80, machineUpTime]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHItem` → `getTxtHAssetItem`
- `grdAsset` → `getAssetsDetail`
- `grdLedger` → `getLedgerDetail`
- `grdDown` → `getDownDetail`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`tabPanel.addSelectionHandler` → `onSelection()`
```java
flwSearchValue.clear();
if (event.getSelectedItem() == tabAsset) {
flwSearchValue.add(txtSearchValueAsset);
} else if (event.getSelectedItem() == tabLedger) {
flwSearchValue.add(txtSearchValueLedger);
} else if (event.getSelectedItem() == tabDown) {
flwSearchValue.add(txtSearchValueDown);
```

`txtHItem.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHAssetItem(helper, blnToEndOfFile, "", new AsyncCallback<DmResultPageing<List<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItem).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
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

`txtHItem.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
reQuery(grdAsset, searchDbFieldNameAsset, searchDbFieldDataTypeAsset, txtSearchValueAsset);
reQuery(grdLedger, searchDbFieldNameLedger, searchDbFieldDataTypeLedger, txtSearchValueLedger);
reQuery(grdDown, searchDbFieldNameDown, searchDbFieldDataTypeDown, txtSearchValueDown);
clear();
```

`grdAsset.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(false).getAssetsDetail(searchValue_Mdl, helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<Lis
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdAssets_Mdl>> result) {
if (result.isStatus()) {
grdAsset.setData(result.getData(), result.getPaging());
grdAsset.getDmGrid().commitChanges();
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`grdAsset.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
grdAsset.setData(result.getData(), result.getPaging());
grdAsset.getDmGrid().commitChanges();
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`grdAsset.addCallBack` → `getParam()`
```java
return valueIntialize(searchDbFieldNameAsset, txtSearchValueAsset);
```

`grdLedger.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(false).getLedgerDetail(searchValue_Mdl, helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<Lis
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdLedger_Mdl>> result) {
if (result.isStatus()) {
grdLedger.setData(result.getData(), result.getPaging());
grdLedger.getDmGrid().commitChanges();
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`grdLedger.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
grdLedger.setData(result.getData(), result.getPaging());
grdLedger.getDmGrid().commitChanges();
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`grdLedger.addCallBack` → `getParam()`
```java
return valueIntialize(searchDbFieldNameLedger, txtSearchValueLedger);
```

`grdDown.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(false).getDownDetail(searchValue_Mdl, helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdDown_Mdl>> result) {
if (result.isStatus()) {
grdDown.setData(result.getData(), result.getPaging());
grdDown.getDmGrid().commitChanges();
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`grdDown.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
grdDown.setData(result.getData(), result.getPaging());
grdDown.getDmGrid().commitChanges();
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`grdDown.addCallBack` → `getParam()`
```java
return valueIntialize(searchDbFieldNameDown, txtSearchValueDown);
```

`prnPopup.addCallBack` → `hide()`
```java
txtHItem.setFocus(true);
```
- Confirm: "Are you sure? You want to close."

### Screen `PrintPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Asset Group | DmTextHelpMulti | `txtHAssetGroup` | `KeyField_PrpAcs, 10` |
| 2 | Asset Item | DmTextHelpMulti | `txtHAssetItem` | `KeyField_PrpAcs, 10` |
| 3 | Asset Id | DmTextHelpMulti | `txtHAssets` | `KeyField_PrpAcs, 10` |
| 4 | - | CheckBox | `cbLabel` | `"", HasDirection.Direction.RTL` |

**Grid / popup columns** (header, width, db field):
- `txtHAssets`: Asset [Text, w0, manualCode]
- `txtHAssetGroup`: Name [Text, w0, Name]
- `txtHAssetItem`: Name [Text, w0, Name]

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHAssetGroup.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHAssetGroup(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Key
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHAssetGroup).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHAssetGroup.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAssetGroup).show();
```

`txtHAssetGroup.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAssetGroup.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAssetGroup).show();
```

`txtHAssetItem.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHAssetItem(helper, blnToEndOfFile, DmShared.xNull(txtHAssetGroup.getTag()), ne
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHAssetItem).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHAssetItem.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAssetItem).show();
```

`txtHAssetItem.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAssetItem.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAssetItem).show();
```

`txtHAssets.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHAsset(helper, blnToEndOfFile, itemCode, new AsyncCallback<DmResultPageing<Lis
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHAssets).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHAssets.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAssets).show();
```

`txtHAssets.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAssets.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAssets).show();
```

## Files
| file | lines |
|---|---|
| client/AssetInfo.java | 638 |
| client/AssetInfo_Dsg.java | 291 |
| client/PrintPopup.java | 213 |
| client/PrintPopup_Dsg.java | 202 |
| server/AssetInfo_SrvImpl.java | 461 |
| shared/AssetInfo_Srv.java | 23 |
| shared/AssetInfo_SrvAsync.java | 23 |
| shared/GrdAssets_Mdl.java | 67 |
| shared/GrdDown_Mdl.java | 100 |
| shared/GrdLedger_Mdl.java | 112 |
| shared/SearchValue_Mdl.java | 104 |
| shared/TaskScheduleHistory_Mdl.java | 150 |

## UI labels / columns (client)
`Asset Item`, `Asset Group`, `Asset Id`

## Models (shared DTOs)
### GrdAssets_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | parentAsset |
| String | subAsset |
| String | department |
| String | supplier |
| String | purchaseDate |
| String | nature |

### GrdLedger_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | voucherDate |
| String | voucherType |
| String | remark |
| String | amount |
| String | Nature |
| String | parentAsset |
| String | subAsset |
| String | ledgerDocId |
| String | ledgerRecId |
| String | category |
| String | currentDepartment |

### TaskScheduleHistory_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | taskId |
| String | taskRowId |
| String | scheduleDate |
| String | scheduleTime |
| String | scheduleDateTime |
| String | assignTo |
| String | taskTypeCode |
| String | taskTypeName |
| String | statusRemark |
| String | statusCode |
| String | statusName |
| String | preparedBy |
| Timestamp | preparedByDate |
| String | u_name |
| Timestamp | u_EntDt |

### GrdDown_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | downDocId |
| String | downRecId |
| String | voucherDate |
| String | voucherType |
| String downDate, | downTime |
| String upDate, | upTime |
| String parentAsset, | subAsset |

### SearchValue_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | schFromDate |
| String | schToDate |
| String | assetItemTag |
| String | assetItemText |
| String | assetTag |
| String | assetText |
| String | statusTag |
| String | statusText |
| String | fieldName |
| String | fieldNameValue |

## RPC interface
| method | params | returns |
|---|---|---|
| getAssetsDetail | SearchValue_Mdl searchValue_Mdl, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdAssets_Mdl>> |
| getLedgerDetail | SearchValue_Mdl searchValue_Mdl, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdLedger_Mdl>> |
| getDownDetail | SearchValue_Mdl searchValue_Mdl, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdDown_Mdl>> |
| getTxtHAssetItem | DmHelper helper, boolean blnEndofFile, String groupCode | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAsset | DmHelper helper, boolean blnEndofFile, String itemCode | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAssetGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValue |  | DmResult<String> |

## Server `AssetInfo_SrvImpl.java` (461 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getVType`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.xNull`

### `getAssetsDetail`
`public DmResultPageing<List<GrdAssets_Mdl>> getAssetsDetail(SearchValue_Mdl searchValueMdl, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select am.code as assetCode,am.manualcode parentAsset , case when sa.subAssetCode is not null then sa.subAsset else am.manualcode end as subAsset , case am.assetNature when {…} then ' {…} ' when {…} then ' {…} ' when {…} then ' {…} ' else '' end as assetNature, sgDept.name as departmentName,am.dateOfPurchase,sgSup.name as supplierName from assetMast as am left join subgroup as sgDept on am.currentDepartmentCode=sgDept.subCode left join subgroup as sgSup on am.assetSupplierCode=sgSup.subCode left join ( select max(manualcode) as subAsset,max(parentAssetCode) as parentAssetCode,code as subAssetCode from assetMast group by code )as sa on sa.parentAssetCode=am.code where 1=1 {…}`
- Params: `:assetItemCode` ← `DmShared.xNull(searchValueMdl.getAssetItemTag())`
- Other calls: `GENERAL.getId`, `GENERAL.getValue`, `MACHINE.getId`, `MACHINE.getValue`, `VEHICLE.getId`, `VEHICLE.getValue`

### `getLedgerDetail`
`public DmResultPageing<List<GrdLedger_Mdl>> getLedgerDetail(SearchValue_Mdl searchValueMdl, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select al.docId, al.recId, am.code, am.manualcode as parentAsset, al.v_date, vt.short_Name, vt.v_type, isNature, al.amtDr as amount, al.remark, vt.category, dep.name as currentDepartment, case when sa.subAssetCode is not null then sa.subAsset else am.manualcode end as subAsset, case al.isNature when {…} then ' {…} ' when {…} then ' {…} ' when {…} then ' {…} ' when {…} then ' {…} ' when {…} then ' {…} ' when {…} then ' {…} ' else '' end as assetNature from assetLedger as al left join voucher_Type as vt on al.v_type=vt.v_type left join assetMast as am on al.assetCode=am.code left join subGroup dep on dep.subCode = am.currentDepartmentCode left join ( select max(manualcode) as subAsset,max(parentAssetCode) as parentAssetCode, code as subAssetCode from assetMast am group by code )as sa on sa.parentAssetCode=am.code where vt.category in(' {…} ',' {…} ',' {…} ') {…}`
- Params: `:assetItemCode` ← `DmShared.xNull(searchValueMdl.getAssetItemTag())`
- Other calls: `ADDITION.getId`, `ADDITION.getValue`, `APPRICIATION.getId`, `APPRICIATION.getValue`, `DELETION.getId`, `DELETION.getValue`, `DEPRICIATION.getId`, `DEPRICIATION.getValue`, `REPAIR.getId`, `REPAIR.getValue`, `SCRAP.getId`, `SCRAP.getValue`

### `getDownDetail`
`public DmResultPageing<List<GrdDown_Mdl>> getDownDetail(SearchValue_Mdl searchValueMdl, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select mmd.docId,mmd.recId,am.code ,am.manualcode parentAsset, mmd.v_date ,vt.short_Name,mmd.machineDownDate,mmd.machineDownTime, mmd.machineUpDate,mmd.machineUpTime , case when sa.subAssetCode is not null then sa.subAsset else am.manualcode end as subAsset from machineMaintainanceDetail as mmd left join voucher_Type as vt on mmd.v_type=vt.v_type left join assetMast as am on mmd.assetCode=am.code left join ( select max(manualcode) as subAsset,max(parentAssetCode) as parentAssetCode,code as subAssetCode from assetMast group by code )as sa on sa.parentAssetCode=am.code where vt.category=' {…} ' {…}`
- Params: `:assetItemCode` ← `DmShared.xNull(searchValueMdl.getAssetItemTag())`

### `getTxtHAssetItem`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAssetItem(DmHelper helper, boolean blnToEndOfFile, String groupCode)`

- SQL 1: `select aim.code, aim.name from assetItemMast aim left join assetSiteList asl on asl.code= aim.Code where asl.site_Code = :siteCode {…}`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHAsset`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAsset(DmHelper helper, boolean blnEndofFile, String itemCode)`

- SQL 1: `select am.code, am.manualCode, am.currentDepartmentCode, dep.name as department from assetMast am left join subGroup dep on dep.subCode = am.currentDepartmentCode where am.currentSite_code = :siteCode and am.assetItemCode = :itemCode`
- Params: `:itemCode` ← `DmShared.iNull(itemCode).toString()`; `:siteCode` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHAssetGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAssetGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code, name from assetGroupMast`

### `getDefaultValue`
`public DmResult<String> getDefaultValue()`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.openSourceReport.client`, `dataman.dmToolkit.self.client`, `paperbiz.assets.assetInfo.shared`, `paperbiz.assets.self.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
