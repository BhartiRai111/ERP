# pb-assets / assetDepreciationPosting

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/assets/src/assetDepreciationPosting/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: assetStockHead, assetLedger
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHDepartment, getTxtHAssetGroup, getTxtHAssetItem, getTxtHAssetCode
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `AssetDepreciationPosting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmGridPaging | `grdSample` | `GrdPaging_PrpAcs, 23, false` |

Other widgets (not in tab list): `txtSearchValue` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `grdSample`: Asset Item [Text, w160, itemName]; Asset Name [Text, w280, name]; Opening [Text, w100, Opening]; Less 180 [Text, w100, addUpTo180]; Greater 180 [Text, w100, addGreater180]; Deletion [Text, w110, deletion]; Sales / Scrap [Text, w110, salesScrap]; Depreciation [Text, w120, Depreciation]; Balance [Text, w120, balance]

**Lookup / suggestion / find sources (server RPC called):**
- `grdSample` → `getAssets`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`setting.addCallBack` → `onOk()`
```java
searchMdl = searchValue;
reQuery();
Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
public void execute() {
txtSearchValue.setFocus(true);
```

`setting.addCallBack` → `execute()`
```java
txtSearchValue.setFocus(true);
```

`grdSample.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(false).getAssets(searchValue_Mdl, helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<GrdA
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdAssetDepreciationPostingDetails_Mdl>> result) {
if (result.isStatus()) {
grdSample.setData(result.getData(), result.getPaging());
grdSample.getDmGrid().commitChanges();
} else {
new DmAlertMessageBox(result.getErrorMessage(), btnPost).show();
```

`grdSample.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
grdSample.setData(result.getData(), result.getPaging());
grdSample.getDmGrid().commitChanges();
} else {
new DmAlertMessageBox(result.getErrorMessage(), btnPost).show();
```

`grdSample.addCallBack` → `getParam()`
```java
return valueIntialize();
```

**Menu items (Other button):** Process Message
- Confirm: "Are you sure? You want to close."
- Alert: "Posted Successfully."

### Screen `Setting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Asset Group | DmTextHelpMulti | `txtHAssetGroup` | `KeyField_PrpAcs, 5` |
| 2 | Asset Item | DmTextHelpMulti | `txtHAssetItem` | `KeyField_PrpAcs, 5` |
| 3 | Asset Code | DmTextHelpMulti | `txtHAssetCode` | `KeyField_PrpAcs, 5` |
| 4 | Department | DmTextHelpMulti | `txtHDepartment` | `KeyField_PrpAcs, 5` |

**Grid / popup columns** (header, width, db field):
- `txtHAssetGroup`: Name [Text, w125, Name]
- `txtHAssetItem`: Name [Text, w125, Name]
- `txtHAssetCode`: Name [Text, w125, Name]
- `txtHDepartment`: Name [Text, w125, Name]

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHAssetGroup.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHAssetGroup(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Key
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
SrvAsync.invokeRPC(blnCallBackWait).getTxtHAssetItem(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyF
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

`txtHAssetCode.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHAssetCode(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyF
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHAssetCode).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHAssetCode.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAssetCode).show();
```

`txtHAssetCode.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAssetCode.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAssetCode).show();
```

`txtHDepartment.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHDepartment(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Key
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

## Files
| file | lines |
|---|---|
| client/AssetDepreciationPosting.java | 284 |
| client/AssetDepreciationPosting_Dsg.java | 210 |
| client/Setting.java | 226 |
| client/Setting_Dsg.java | 200 |
| server/AssetDepreciationPosting_SrvImpl.java | 550 |
| shared/AssetDepreciationPosting_Srv.java | 27 |
| shared/AssetDepreciationPosting_SrvAsync.java | 27 |
| shared/GrdAssetDepreciationPostingDetails_Mdl.java | 132 |
| shared/SearchValue_Mdl.java | 120 |

## UI labels / columns (client)
`Asset Group`, `Asset Item`, `Asset Code`, `Department`

## Models (shared DTOs)
### GrdAssetDepreciationPostingDetails_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | serialNo |
| String itemCode, itemName, assetCode, assetName, opening, addUpTo180, addGreater180, deletion, salesScrap, depreciation, | balance |
| String | voucherDate |
| RecId | recIdAssetDel |

### SearchValue_Mdl
| type | field |
|---|---|
| String | assetGroupTag |
| String | assetGroupText |
| String | assetItemTag |
| String | assetItemText |
| String | assetCodeTag |
| String | assetCodeText |
| String | departmentTag |
| String | departmentText |
| String | fromDate |
| String | toDate |
| String | fieldName |
| String | fieldNameValue |

## RPC interface
| method | params | returns |
|---|---|---|
| getAssets | SearchValue_Mdl searchValue_Mdl, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdAssetDepreciationPostingDetails_Mdl>> |
| getTxtHDepartment | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| sendMessage | String strKey, String vTypeCode | DmResult<String> |
| getTxtHAssetGroup | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAssetItem | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAssetCode | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getPost | List<GrdAssetDepreciationPostingDetails_Mdl> grdDep_Mdl, String voucherPrefix, String fromDate | DmResult<String> |

## Server `AssetDepreciationPosting_SrvImpl.java` (550 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getParamList`, `DmServer.getRecId`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getVType`, `DmServer.sendMail`, `DmServer.vNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getAssets`
`public DmResultPageing<List<GrdAssetDepreciationPostingDetails_Mdl>> getAssets(SearchValue_Mdl searchValueMdl, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select aim.code as itemCode,aim.name as itemName,am.code,am.manualCode as name,dm.subCode as departmentCode, dm.name as department,isNull(opening,0) as opening,isNull(addUpTo180,0) as addUpTo180, isNull(addGreater180,0) as addGreater180, isNull(deletion,0) as deletion,isNull(salesScrap,0) as salesScrap, aim.assetDepRate ,short_Name, aLG.v_Date, v_No, recId from ( select astk.assetCode, astk.departmentCode from assetStock astk group By astk.assetCode, astk.departmentCode having (isNull(sum(astk.recQty),0) - isNull(sum(astk.isuQty),0)) > 0 ) as aST left join ( select aLG.assetCode, sum(case when aLG.v_date < :fromDate then isNull(aLG.amtDr,0) - isNull(aLG.amtCr,0) else 0 end) as opening, sum(case when aLG.v_date between :fromDate and :toDate and dateDiff(d, :fromDate, aLG.v_Date) <= 180 then isNull(aLG.amtDr,0) else 0 end) as addUpTo180, sum(case when aLG.v_date between :fromDate and :toDate and dateDiff(d, :fromDate, aLG.v_Date) > 180 then isNull(aLG.amtDr,0) else 0 end) as addGreater180, sum(case when aLG.v_date between :fromDate and :toDate and aLG.isNature not in (:sales,:scrap) then isNull(aLG.amtCr,0) else 0 end) as deletion, sum(case when aLG.v_date between :fromDate and :toDate and aLG.isNature in (:sales,:scrap) then isNull(aLG.amtCr,0) else 0 end) as salesScrap, max(case when aLG.v_date >= :fromDate and alg.isNature = :nature then 1 else 0 end) as isDepreciationDone, max(vt.short_Name) as short_Name,max(aLG.v_Date)as v_Date, max(aLG.v_NO) as v_No, max(aLG.recId) as recId from assetLedger aLG left join voucher_Type vt on vt.v_Type = alg.v_Type where aLG.v_date <= :toDate group By aLG.assetCode ) as aLG on aLG.assetCode = aST.assetCode left join assetMast am on am.code = ast.assetCode left join assetItemMast aim on aim.code = am.assetItemCode left join assetGroupMast agm on aim.assetGroupCode=agm.code left join subgroup dm on dm.subCode = aST.departmentCode where isNull(aLG.isDepreciationDone,0) = 0 and aim.depMethod <> 0 {…}`
- Params: `:fromDate` ← `DmServer.convertDate(searchValueMdl.getFromDate())`; `:toDate` ← `DmServer.convertDate(searchValueMdl.getToDate())`
- Errors: "Please use help."
- Other calls: `DEPRICIATION.getId`, `SALES.getId`, `SCRAP.getId`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select sg.subCode as code, sg.name from departmentMast dep left join subgroup sg on sg.subCode = dep.subCode left join subGroupSiteList sgsl on sgsl.subCode = sg.subCode where sg.isActive = 1 and sgsl.site_Code = :siteCode`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHAssetGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAssetGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code, name from assetGroupMast`

### `getTxtHAssetItem`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAssetItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select aim.code, aim.name from assetItemMast aim left join assetSiteList asl on asl.code = aim.code where asl.site_Code = :siteCode and aim.depMethod <> 0`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHAssetCode`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAssetCode(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select am.code, am.manualCode as name from assetMast am left join assetItemMast aim on aim.code = am.assetItemCode where am.currentSite_code = :siteCode and aim.depMethod <> 0`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`

### `getPost`
`public DmResult<String> getPost(List<GrdAssetDepreciationPostingDetails_Mdl> grdDep_Mdl, String voucherPrefix, String fromDate)`

- SQL 1: `select v_Type from Voucher_Type where v_Type=:v_Type`
- SQL 2: `select depMethod from assetItemMast where code = :itemCode`
- Params: `:v_Type` ← `DmShared.iNull(v_Type).toString()`; `:itemCode` ← `DmShared.xNull(item_Mdl.getItemCode())`
- Errors: "Voucher Category not found."

### `save`
`public DmResult<String> save(GrdAssetDepreciationPostingDetails_Mdl mrTemp, String voucherPrefix, String fromDate, String v_Type, Connection cnTemp) throws Exception`

- SQL 1: `select assetCode from assetLedger where assetCode=:assetCode and v_Date>:fromDate and isNature=:nature`
- SQL 2: `Insert Into assetStockHead(docId, v_Type,v_No, recId, v_Date, recIdPrefix, v_Prefix, u_EntDt, site_Code, preparedBy, approvedBy) Values (:docId, :v_Type, :v_No, :recId, :v_Date, :recIdPrefix, :v_Prefix, :u_EntDt, :site_Code, :userName, :userName)`
- SQL 3: `Insert Into assetLedger(docId,v_SNo,v_No,recIdPrefix,recId,v_Type, v_Prefix,v_Date, assetCode, amtCr, isNature,typeOfExpense) Values (:docId,:v_SNo,:v_No,:recIdPrefix,:recId,:v_Type,:v_Prefix,:v_Date, :assetCode,:amtCr, :isNature,:typeOfExpense)`
- Params: `:assetCode` ← `DmShared.foriegnKey(mrTemp.getAssetCode())`; `:fromDate` ← `DmServer.convertDate(fromDate)`; `:nature` ← `DmShared.iNull(AssetNature.DEPRICIATION.getId()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_Type` ← `v_Type`; `:v_No` ← `DmShared.iNull(mrTemp.getRecIdAssetDel().getCounter()).toString()`; `:recId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:v_Date` ← `DmServer.convertDate(DmServer.getServerDateTime()[0])`; `:recIdPrefix` ← `DmShared.xNull(DmShared.xNull(mrTemp.getRecIdAssetDel().getPrefix()))`; `:v_Prefix` ← `voucherPrefix`; `:u_EntDt` ← `DmServer.getServerTimestamp()`; `:site_code` ← `DmShared.foriegnKey(DmServer.getSsnSiteCode(this))`; `:userName` ← `DmShared.xNull(DmServer.getSsnUserName(this))`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_SNo` ← `"1"`; `:v_No` ← `DmShared.iNull(mrTemp.getRecIdAssetDel().getCounter()).toString()`; `:recIdPrefix` ← `DmShared.xNull(DmShared.xNull(mrTemp.getRecIdAssetDel().getPrefix()))`; `:recId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:v_Type` ← `DmShared.foriegnKey(v_Type)`; `:v_Prefix` ← `voucherPrefix`; `:v_Date` ← `DmServer.convertDate(DmServer.getServerDateTime()[0])`; `:assetCode` ← `DmShared.foriegnKey(mrTemp.getAssetCode())`; `:amtCr` ← `DmShared.vNull(mrTemp.getDepreciation()).toString()`; `:isNature` ← `DmShared.iNull(AssetNature.DEPRICIATION.getId()).toString()`; `:typeOfExpense` ← `DmShared.iNull(ExpenceType.CAPITAL.getId()).toString()`
- Errors: "Asset already Posted."
- Other calls: `CAPITAL.getId`, `DEPRICIATION.getId`, `STServer.checkAssetCurrentDepartment`, `STServer.updateAssetCurrentDepartment`

### `sendMessage`
`public DmResult<String> sendMessage(String strKey, String vTypeCode)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stStatic.server`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| getAssets | `if (DmShared.xNull(searchValueMdl.getAssetGroupTag()).equals("") && DmShared.xNull(searchValueMdl.getAssetItemTag()).equals("") && DmShared.xNull(searchValueMdl.getAssetCodeTag()).equals("") && DmShared.xNull(searchValueMdl.getDepartmentTag()).equals(""))` | "Please use help." |
| getPost | `if (!rsTemp.next())` | "Voucher Category not found." |
| save | `if (rsTemp.next())` | "Asset already Posted." |

## Notes (hand-written)
_No hand-written notes yet._
