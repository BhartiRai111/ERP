# pb-self-toolkit / finishedItemActivation

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/finishedItemActivation/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHStatus, getTxtHCategory, getTxtHItemGroup, getTxtHItemType
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `FinishedItemActivation`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmGridPaging | `dmGrd` | `Grd_PrpAcs, gridRows, false` |

**Grid / popup columns** (header, width, db field):
- `dmGrd`: Active [Text, w40]; View [Text, w35]; Item Name [Text, w230, name]; Manual Code [Text, w150, ManualCode]; Item Group [Text, w170, groupName]; Item Category [Text, w170, categoryName]; HSN [Text, w130, hsName]; SKU [Text, w80, skuName]; Sale Unit [Text, w100, saleUnitName]; Status By [Text, w160, preparedBY]

**Lookup / suggestion / find sources (server RPC called):**
- `dmGrd` → `getFillRecords`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`walkThrough.addCallBack` → `onClose()`
```java
dmGrd.reQuery();
```

`settingPopup.addCallBack` → `onOk()`
```java
setting_Mdl = new Setting_Mdl();
setting_Mdl = activation_Mdl;
dmGrd.reQuery();
```

`dmGrd.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(true).getFillRecords(param, helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<GrdFinishe
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdFinishedItemActivation_Mdl>> result) {
if (result.isStatus()) {
dmGrd.setData(result.getData(), result.getPaging());
```

`dmGrd.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
dmGrd.setData(result.getData(), result.getPaging());
```

`dmGrd.addCallBack` → `getParam()`
```java
return setting_Mdl;
```
- Confirm: "Are you sure? You want to close."

### Screen `Setting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | From Date | DmTextBox | `txtFromDate` | `15, false` |
| 2 | To Date | DmTextBox | `txtToDate` | `15, false` |
| 3 | Item Type | DmTextHelpMulti | `txtHItemType` | `TxtHItemType_PrpAcs, 5` |
| 4 | Item Group | DmTextHelpMulti | `txtHItemGroup` | `KeyField_PrpAcs, 5` |
| 5 | Item Category | DmTextHelpMulti | `txtHItemCategory` | `KeyField_PrpAcs, 5` |
| 6 | Status | DmTextHelp | `txtHStatus` | `KeyField_PrpAcs, 4, false` |

**Grid / popup columns** (header, width, db field):
- `txtHItemCategory`: Name [Text, w220, categoryName]
- `txtHItemType`: Name [Text, w100, name]; Short Name [Text, w70, manualcode]
- `txtHItemGroup`: Name [Text, w240, name]
- `txtHStatus`: Status [Text, w100, Name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHStatus` → `getTxtHStatus`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtFromDate.addValueChangeHandler` → `onValueChange()`
```java
txtFromDate.setText(DmClient.returnDate(txtFromDate.getText()));
```

`txtToDate.addValueChangeHandler` → `onValueChange()`
```java
txtToDate.setText(DmClient.returnDate(txtToDate.getText()));
```

`txtHItemType.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItemType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHI
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemType).show();
public void onSuccess(DmResultPageing<List<TxtHItemType_Mdl>> result) {
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

`txtHStatus.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHStatus(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFiel
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHStatus).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHStatus.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHStatus).show();
```

`txtHStatus.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHStatus.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHStatus).show();
```

`txtHItemCategory.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHCategory(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
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
- Alert: "From Date should be less than or equal to To Date."

## Files
| file | lines |
|---|---|
| client/FinishedItemActivation.java | 338 |
| client/FinishedItemActivationCard.java | 130 |
| client/FinishedItemActivation_Dsg.java | 234 |
| client/Setting.java | 280 |
| client/Setting_Dsg.java | 234 |
| server/FinishedItemActivation_SrvImpl.java | 523 |
| shared/DefaultValue_Mdl.java | 22 |
| shared/FinishedItemActivation_Srv.java | 27 |
| shared/FinishedItemActivation_SrvAsync.java | 27 |
| shared/GrdFinishedItemActivation_Mdl.java | 221 |
| shared/Setting_Mdl.java | 120 |
| shared/TxtHItemType_Mdl.java | 23 |

## UI labels / columns (client)
`From Date`, `To Date`, `Item Type`, `Item Group`, `Item Category`, `Status`

## Models (shared DTOs)
### TxtHItemType_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | shortname |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | currentDate |

### Setting_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | FromDate |
| String | ToDate |
| String | StatusCode |
| String | StatusName |
| String | wtaCode |
| String | itemCategoryCode |
| String | itemCategoryName |
| String | itemGroupCode |
| String | itemGroupName |
| String | itemTypeCode |
| String | itemTypeName |
| List<GrdFinishedItemActivation_Mdl> | grdItemActivation_Mdl |

### GrdFinishedItemActivation_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | wtaCode |
| String | statusChangedBy |
| String | isStatus |
| String | isView |
| String | isChecked |
| String | manualCode |
| String | itemTypeCode |
| String | itemTypeName |
| String | itemGroupCode |
| String | itemGroupName |
| String | itemCategoryCode |
| String | itemCategoryName |
| String | hsnCode |
| String | hsnName |
| String | skuCode |
| String | skuName |
| String | purchaseUnitCode |
| String | purchaseUnitName |
| String | saleUnitCode |
| String | saleUnitname |
| String | active |
| String | itemGSTNature |
| String | isActiveCode |

## RPC interface
| method | params | returns |
|---|---|---|
| getFillRecords | Setting_Mdl param, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdFinishedItemActivation_Mdl>> |
| getTxtHStatus | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHCategory | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemGroup | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemType_Mdl>> |
| setActive | String activeBy, boolean isActive, String docId | DmResult<String> |
| getCardDetail |  | DmResult<CardDetail_Mdl> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |

## Server `FinishedItemActivation_SrvImpl.java` (523 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerDateTime`, `DmServer.getSsnDBName`, `DmServer.getSsnUserName`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.xNull`

### `getFillRecords`
`public DmResultPageing<List<GrdFinishedItemActivation_Mdl>> getFillRecords(Setting_Mdl param, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code, im.name, im.isActive, im.ManualCode, im.itemType AS typeCode, it.name AS typeName, im.itemGroup AS groupCode, ig.name AS groupName, im.itemCategoryCode, ic.name AS categoryName, im.hsnCode as hsnCode, hs.name as hsName , im.itemGSTNature , im.sku AS skuCode, um.unit AS skuName,im.wtaCode, im.purchaseUnit as purchaseUnitCode, ump.unit as purchaseUnitName, im.saleUnit as saleUnitCode, ums.unit as saleUnitName, im.preparedBY, im.u_name , im.u_EntDt, format(im.preparedDt,'dd-MMM-yyyy HH:mm') AS preparedDt FROM itemMast AS im LEFT JOIN itemCat AS ic ON im.itemCategoryCode = ic.code LEFT JOIN itemgroup AS ig ON im.itemGroup = ig.code LEFT JOIN itemtype AS it ON im.itemType = it.code LEFT JOIN unitmast AS um ON im.sku = um.code LEFT JOIN unitmast AS ump ON im.purchaseUnit = ump.code LEFT JOIN unitmast AS ums ON im.saleUnit = ums.code LEFT JOIN hsnMast AS hs ON im.hsnCode = hs.code {…} WHERE im.filterItemType = :filterItemType {…}`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:fromPreparedDate` ← `DmServer.convertDate(param.getFromDate())`; `:toPreParedDate` ← `DmServer.convertDate(param.getToDate())`
- Other calls: `ACTIVE.getId`, `HOLD.getId`, `INACTIVE.getId`

### `getTxtHStatus`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHStatus(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `ItemStatus.values`

### `getTxtHCategory`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCategory(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select imc.code as categoryCode , imc.name as categoryName from itemcat imc where (imc.filterItemType = :filterItemType OR imc.isCommon = 1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ig.code, ig.name FROM itemgroup ig WHERE (ig.filterItemType = :filterItemType OR ig.isCommon = 1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemType`
`public DmResultPageing<List<TxtHItemType_Mdl>> getTxtHItemType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code,name,manualcode from itemType`

### `setActive`
`public DmResult<String> setActive(String activeBy, boolean isActive, String code)`

- Other calls: `STApprovals.itemActivation`

### `walkThrough`
`public DmResult<String> walkThrough(WalkThrough_Mdl walkThrough)`

- Other calls: `STApprovals.itemActivation`, `WalkThrough_Server.walkThrough`

### `getCardDetail`
`public DmResult<CardDetail_Mdl> getCardDetail()`

- SQL 1: `Select count(im.code) as cnt, sum(case when DATEDIFF(day, im.preparedDt, CAST(getdate() AS date)) <= {…} then 1 else 0 end) as low, sum(case when DATEDIFF(day, im.preparedDt, CAST(getdate() AS date)) > {…} and DATEDIFF(day, im.preparedDt, CAST(getdate() AS date)) <= {…} then 1 else 0 end) as medium, sum(case when DATEDIFF(day, im.preparedDt, CAST(getdate() AS date)) > {…} then 1 else 0 end) as high from itemMast as im LEFT JOIN walkThroughFootPrint wtfp on wtfp.wtaCode = im.wtaCode and wtfp.isStatus = 0 where im.isActive = ' {…} ' AND isNull(wtfp.linkedUser,' {…} ,') like '% {…} ,%' and im.filterItemType = :filterItemType`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`
- Other calls: `HOLD.getId`, `LOW_PRIORITYDAYS.getDays`, `MEDIUM_PRIORITYDAYS.getDays`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.dashboardCardStrip.client`, `paperbiz.selfToolkit.dashboardCardStrip.shared`, `paperbiz.selfToolkit.finishedItemActivation.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
