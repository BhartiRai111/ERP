# pb-assets / assetTransfer

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/assets/src/assetTransfer/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: assetStockHead, assetStock
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Voucher Date (Text); Asset Item (Text); To Department (Amount); rule: Please use Add Asset.
- Lookups/suggestions: getTxtHDepartment, getTxtHAssetItem
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `AssetTransfer`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Voucher No. | DmTextBox | `txtVrNo` | `20, false` |
| 2 | Voucher Date | DmTextBox | `txtVrDate` | `11, false` |
| 3 | Asset Item | DmTextHelp | `txtHAssetItem` | `KeyField_PrpAcs, 7, true` |
| 4 | To Department | DmTextHelp | `txtHDepartment` | `KeyField_PrpAcs, 7, true` |
| 5 | Remark | DmRemarkTextBox | `txtRemark` | `50, false` |

Other widgets (not in tab list): `txtConsultant` (DmTextBox), `txtHGender` (DmTextHelp), `txtHAddAsset` (DmHelpGridMulti), `dmFind` (DmFind), `grdAssets` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHDepartment`: Department [Text, w0, Name]
- `txtHGender`: Name [Text, w0, Name]
- `txtHAssetItem`: Name [Text, w0, Name]
- `txtHAddAsset`:  Asset Name [Text, w120, manualCode]; Current Department [Text, w130, department]
- `dmFind`: Voucher No. [Text, w130, recId]; Voucher Date [Text, w120, v_Type]; Asset Item [Text, w300, assetItemName];  To Department [Text, w300, department]; Remark [Text, w360, remark]
- `grdAssets`: Asset Name [Text, w200]; Asset Make [Text, w190]; Accessories [Text, w190]; Specification [Text, w190]; Remark [Text, w240]; Current Department [Text, w180]; Delete [Text, w50]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHDepartment` → `getTxtHDepartment`
- `txtHAssetItem` → `getTxtHAssetItem`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `CurrentDate` ← DmShared.dNull(txtVrDate.getText())
- `DepartmentCode` ← DmShared.iNull(txtHDepartment.getTag()).toString()
- `Department` ← DmShared.xNull(txtHDepartment.getText())
- `AssetCode` ← DmShared.iNull(txtHAssetItem.getTag()).toString()
- `AssetName` ← DmShared.xNull(txtHAssetItem.getText())
- `Remark` ← DmShared.xNull(txtRemark.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtVrDate.setText` ← data.getDefaultMdl().getCurrentDate()
- `txtVrNo.setText` ← data.getSharedKeyFieldName()
- `txtHAssetItem.setTag` ← data.getAssetCode()
- `txtHAssetItem.setText` ← data.getAssetName()
- `txtHDepartment.setTag` ← data.getDepartmentCode()
- `txtHDepartment.setText` ← data.getDepartment()
- `txtRemark.setText` ← data.getRemark()
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtVrNo.setEnabled(false)`
- `txtVrDate.setEnabled(false)`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
txtVrNo.setEnabled(false);
txtVrDate.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
```

`topControl.addNewHandeller` → `newClicked()`
```java
onAdd();
```

`topControl.addSaveHandeller` → `saveClicked()`
```java
topControlSave();
```

`topControl.addCardHandeller` → `cardClicked()`
```java
topControlCard();
```

`topControl.addMoveRecHandeller` → `moveRec()`
```java
clear();
topControlMoveRec(strFetchCode);
```

`topControl.addEditHandeller` → `editClicked()`
```java
manageDisplay(true);
grdAssets.getGrid().getStore().add(new GrdAssetDetail_Mdl());
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(AssetTransfer.this);
```

`topControl.addFindHandeller` → `findClicked()`
```java
flwForm.remove(vrtInnerForm);
flwForm.add(dmFind);
```

`txtHDepartment.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHDepartment(helper, blnToEndOfFile, DmShared.xNull(txtHDepartment.getTag2nd())
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDepartment).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHDepartment.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDepartment).show();
```

`txtHDepartment.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHDepartment.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDepartment).show();
```

`txtHDepartment.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
if (DmShared.iNull(txtHDepartment.getTag2nd()).longValue() == 0) {
grdAssets.getGrid().getStore().clear();
```

`txtHAssetItem.addTextHelpCallBack` → `populateData()`
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

`txtHAssetItem.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAssetItem.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAssetItem).show();
```

`txtHAssetItem.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
grdAssets.getGrid().getStore().clear();
```

`txtHAddAsset.addHelpGridMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getAddAsset(helper, blnToEndOfFile, txtHAssetItem.getTag(), txtHDepartment.getTag(),
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHAssetItem).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHAddAsset.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAssetItem).show();
```

`txtHAddAsset.addHelpGridMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAddAsset.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAssetItem).show();
```

`txtHAddAsset.addHelpGridMultiCallBack` → `getSelection()`
```java
if (blnStatus = true && keyCode != null) {
fillRecord(keyCode, false);
```

`dmFind.addHelpGridCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getFind(topControl.getPermissions(), helper, blnToEndOfFile, new AsyncCallback<DmRes
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), dmFind.getTextBox()).show();
public void onSuccess(DmResultPageing<List<Find_Mdl>> result) {
if (result.isStatus()) {
dmFind.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), dmFind.getTextBox()).show();
```

`dmFind.addHelpGridCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
dmFind.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), dmFind.getTextBox()).show();
```

`dmFind.addHelpGridCallBack` → `getSelectedRow()`
```java
flwForm.remove(dmFind);
flwForm.add(vrtInnerForm);
if (blnStatus) {
topControlMoveRec(tRow.getSharedKeyFieldCode());
```

**Top control options:** setRefreshRequired(false), setOtherRequired(true), setDisplay(true)

**Menu items (Other button):** Process Message

_Print permission "P" is removed for this screen (replace "P" with "*")._
- Confirm: "Are you sure? You want to Process Message."
- Alert: "Can be use in Browse Mode only and select a record."
- Alert: "Message is processed successfully!"
- Alert: "Message can be send only in Browse Mode!"

## Files
| file | lines |
|---|---|
| client/AssetTransfer.java | 626 |
| client/AssetTransfer_Dsg.java | 376 |
| server/AssetTransfer_SrvImpl.java | 763 |
| shared/AssetDetail_Mdl.java | 61 |
| shared/AssetTransfer_Mdl.java | 82 |
| shared/AssetTransfer_Srv.java | 32 |
| shared/AssetTransfer_SrvAsync.java | 32 |
| shared/AssetTransfer_Vld.java | 26 |
| shared/DefaultValue_Mdl.java | 40 |
| shared/Find_Mdl.java | 54 |
| shared/GrdAssetDetail_Mdl.java | 89 |
| shared/GrdVoucherType_Mdl.java | 80 |

## UI labels / columns (client)
`Voucher No.`, `Voucher Date`, `Asset Item`, `To Department`, `Remark`

## Models (shared DTOs)
### AssetDetail_Mdl
### DefaultValue_Mdl
| type | field |
|---|---|
| String voucherTypeTag, | voucherTypeText |
| String | currentDate |

### GrdAssetDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String assetMake, specification, accessories, remark, departmentCode, | departmentName |

### GrdVoucherType_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String shortName, | category |
| boolean | checked |
| String manualCode, brand, specification, departmentCode, | departmentName |

### AssetTransfer_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String assetName, | assetCode |
| String department, | departmentCode |
| String | remark |
| DefaultValue_Mdl | defaultMdl |
| RecId | recIdTrans |
| List<GrdAssetDetail_Mdl> | lstTransferAsset |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String assetItem, assetName, department, remark, | voucherDate |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHDepartment | DmHelper helper, boolean blnToEndOfFile, String department | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAssetItem | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| saveRecord | AssetTransfer_Mdl mrTemp, String voucherPrefix | DmResult<String> |
| getNavigator | UserPermission userPermission | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<AssetTransfer_Mdl> |
| sendMessage | String strKey | DmResult<String> |
| getAddAsset | DmHelper helper, boolean blnToEndOfFile, String assetItemCode, String departmentCode | DmResultPageing<List<KeyFieldExtended>> |
| getFind | UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| getGridAddAsset | String assetCode, String departmentCode, String currentDepartment | DmResult<List<GrdAssetDetail_Mdl>> |
| getAutoFill | String assetCode | DmResult<AssetDetail_Mdl> |

## Validator `AssetTransfer_Vld`
```java
public class AssetTransfer_Vld {
    public static void chkSave(AssetTransfer_Mdl mrTemp) {
        boolean flag = false;
        DmShared.isValidText(mrTemp.getDefaultMdl().getCurrentDate(), "Voucher Date", 100);
        DmShared.isValidText(mrTemp.getAssetCode(), "Asset Item", 200);
        DmShared.isValidAmount(mrTemp.getDepartmentCode(), "To Department", 300);
        for (GrdAssetDetail_Mdl detail_Mdl : mrTemp.getLstTransferAsset()) {
            if (DmShared.iNull(detail_Mdl.getSharedKeyFieldCode()).longValue() > 0) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            throw new DmException(400, "Please use Add Asset.");
        }
    }
}
```

## Server `AssetTransfer_SrvImpl.java` (763 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getParamList`, `DmServer.getRecId`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserViewCondition`, `DmServer.getVType`, `DmServer.isDocumentEMailRequired`, `DmServer.sendMail`, `DmShared.dNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.xNull`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile, String departmentCode)`

- SQL 1: `select sg.subCode, sg.name from departmentMast dep left join subgroup sg on sg.subCode = dep.subCode left join subGroupSiteList sgsl on sgsl.subCode = sg.subCode where sg.isActive = 1 and sgsl.site_Code = :siteCode {…}`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:departmentCode` ← `DmShared.iNull(departmentCode).toString()`

### `getTxtHAssetItem`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAssetItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select aim.code, aim.name from assetItemMast aim left join assetSiteList asl on asl.code = aim.code where asl.site_Code = :siteCode`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`

### `saveRecord`
`public DmResult<String> saveRecord(AssetTransfer_Mdl mrTemp, String voucherPrefix)`

- SQL 1: `Insert Into assetStockHead(docId, v_Type,v_No, recId, v_Date, recIdPrefix, v_Prefix, u_EntDt, site_Code, preparedBy, approvedBy) Values (:docId, :v_Type, :v_No, :recId, :v_Date, :recIdPrefix, :v_Prefix, :u_EntDt,:site_Code, :userName, :userName)`
- SQL 2: `select am.code from assetMast am where am.parentAssetCode = :assetCode`
- Params: `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_Type` ← `dvm.getVoucherTypeTag()`; `:v_No` ← `DmShared.iNull(mrTemp.getRecIdTrans().getCounter()).toString()`; `:recId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:v_Date` ← `DmServer.convertDate(dvm.getCurrentDate())`; `:recIdPrefix` ← `DmShared.xNull(DmShared.xNull(mrTemp.getRecIdTrans().getPrefix()))`; `:v_Prefix` ← `voucherPrefix`; `:u_EntDt` ← `DmServer.getServerTimestamp()`; `:site_code` ← `DmShared.foriegnKey(DmServer.getSsnSiteCode(this))`; `:userName` ← `DmShared.xNull(DmServer.getSsnUserName(this))`; `:code` ← `DmShared.iNull(assetMdl.getSharedKeyFieldCode()).toString()`; `:assetCode` ← `assetMdl.getSharedKeyFieldCode()`
- Errors: "Invalid key." | "Voucher date should be greater or equal purchase date."
- Other calls: `AssetTransfer_Vld.chkSave`, `STServer.checkAssetCurrentDepartment`, `STServer.updateAssetCurrentDepartment`

### `transferStock`
`private void transferStock(AssetTransfer_Mdl mrTemp, DefaultValue_Mdl dvm, int vSno, String v_Type, String departmentCode, String assetCode, String colquantity, boolean isSystemDefine, Connection cnTemp)`

- SQL 1: `insert into assetStock (docId, v_SNo, v_Type, v_No, recIdPrefix, recId, v_Date, departmentCode, assetCode, isSystemGenerated, remark, site_Code,u_Name,u_EntDt, {…} ) values (:docId, :vSno, :vType, :vNo, :recIdPrefix, :recId, :vDate, :department, :assetCode, :isSystemGenerate, :remark, :siteCode,:userName,:u_EntDt,1)`
- Params: `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:vSno` ← `DmShared.iNull(vSno).toString()`; `:vType` ← `v_Type`; `:vNo` ← `DmShared.iNull(mrTemp.getRecIdTrans().getCounter()).toString()`; `:recIdPrefix` ← `DmShared.xNull(DmShared.xNull(mrTemp.getRecIdTrans().getPrefix()))`; `:recId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:vDate` ← `DmServer.convertDate(dvm.getCurrentDate())`; `:department` ← `DmShared.foriegnKey(departmentCode)`; `:assetCode` ← `DmShared.foriegnKey(assetCode)`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:isSystemGenerate` ← `isSystemDefine ? "1" : "0"`; `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:userName` ← `DmShared.xNull(DmServer.getSsnUserName(this))`; `:u_EntDt` ← `DmServer.getServerTimestamp()`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(UserPermission userPermission)`

- SQL 1: `select docId from assetStockHead ash left join voucher_Type vt on vt.v_Type = ash.v_Type where ash.site_Code = :siteCode and ash.v_Prefix = :vPrefix and vt.category in (' {…} ')`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getMoveRec`
`public DmResult<AssetTransfer_Mdl> getMoveRec(String strKey)`

- SQL 1: `select astk.docId, (am.assetItemCode) as assetItemCode, (aim.name) as assetItemName, (astk.departmentCode) as departmentCode, (dep.name) as department, (astk.remark) as remark,(astk.v_Date) as v_date,(astk.recId) as recId,(ash.preparedBy) as preparedBy from assetStockHead ash left join assetStock astk on astk.docId = ash.docId left join assetMast am on am.code = astk.assetCode left join assetItemMast aim on aim.code = am.assetItemCode left join subGroup dep on dep.subCode = astk.departmentCode where astk.docId = :docId and astk.v_sno = 1`
- SQL 2: `select am.code,am.manualCode as assetCodeName, am.assetAccessories, am.assetMake, am.assetRemark, am.assetSpecification, astk.departmentCode, dep.name as department from assetStock astk left join subGroup dep on dep.subCode = astk.departmentCode left join assetMast am on am.code = astk.assetCode where astk.docId = :docId and astk.isSystemGenerated = 0 order By astk.v_SNo`
- Params: `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`

### `getAddAsset`
`public DmResultPageing<List<KeyFieldExtended>> getAddAsset(DmHelper helper, boolean blnToEndOfFile, String assetItemCode, String departmentCode)`

- SQL 1: `select am.code, am.manualCode, dep.name as department from assetMast am left join subGroup dep on dep.subCode = am.currentDepartmentCode where am.currentSite_code = :siteCode and am.currentDepartmentCode <> :department and am.assetItemCode = :itemCode and am.isTransferable = 1 and am.parentAssetCode is null`
- Params: `:itemCode` ← `DmShared.iNull(assetItemCode).toString()`; `:department` ← `DmShared.iNull(departmentCode).toString()`; `:siteCode` ← `DmServer.getSsnSiteCode(this)`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(UserPermission userPermission, DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `select astk.docId , max(ash.recId) as recId, max(ash.v_Date) as v_Date, max(aim.name) as assetItemName, max(dep.name) as department, max(astk.remark) as remark, max(vt.short_Name) as short_Name, max(ash.v_No) as v_No from assetStockHead ash left join voucher_Type vt on vt.v_Type = ash.v_Type left join assetStock astk on astk.docId = ash.docId left join subGroup dep on dep.subCode = astk.departmentCode left join assetMast am on am.code = astk.assetCode left join assetItemMast aim on aim.code = am.assetItemCode where astk.isSystemGenerated = 0 and vt.category in (' {…} ') and ash.v_Prefix = :vPrefix and astk.site_Code = :siteCode`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`

- SQL 1: `select v_Type, description from voucher_Type where short_Name = ' {…} '`

### `getGridAddAsset`
`public DmResult<List<GrdAssetDetail_Mdl>> getGridAddAsset(String assetCodes, String department, String currentDepartment)`

- SQL 1: `select am.code, am.manualCode, am.assetMake, am.assetAccessories, am.assetSpecification, am.assetRemark, am.currentDepartmentCode, dep.name as department from assetMast am left join subGroup dep on dep.subCode = am.currentDepartmentCode left join assetItemMast aim on aim.code = am.assetItemCode where am.currentSite_code = :siteCode and am.isTransferable = 1 {…} and am.code in ( {…} :asset {…} ) order by am.manualCode, dep.name`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:department` ← `DmShared.iNull(currentDepartment).longValue() > 0 ? currentDepartment : department`

### `getAutoFill`
`public DmResult<AssetDetail_Mdl> getAutoFill(String assetCode)`

- SQL 1: `select am.assetItemCode, aim.name as item, am.code, am.currentDepartmentCode, dep.name as department from assetMast am left join assetItemMast aim on aim.code = am.assetItemCode left join subGroup dep on dep.subCode = am.currentDepartmentCode where am.code = :assetCode`
- Params: `:assetCode` ← `assetCode`

### `sendMessage`
`public DmResult<String> sendMessage(String strKey)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmHelpGridMulti.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stStatic.server`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (DmServer.convertDate(dvm.getCurrentDate()).getTime() < DmServer.convertDate(DmShared.dNull(rsTemp.getDate("dateOfPurchase"))).getTime())` | "Voucher date should be greater or equal purchase date." |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("isTransferable")).intValue() == 0)` | " Asset is not transferable asset." |

## Notes (hand-written)
_No hand-written notes yet._
