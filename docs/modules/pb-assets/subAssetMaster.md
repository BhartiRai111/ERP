# pb-assets / subAssetMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/assets/src/subAssetMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: assetMast, assetStockHead, assetStock, assetLedger
- Update tables: assetMast
- Delete cascade (in order): assetStock, assetStockHead, assetLedger, assetPropertiesDetail, assetMast
- Duplicate checks on save: none found
- "In use" checks before delete:
  - `select count(docId) as cnt from assetStock where docId !=:docId and assetCode = :docId`
  - `select count(docId) as cnt from assetLedger where docId !=:docId and assetCode = :docId`
- Validations: Asset Item (Amount); Asset Name (Text); Voucher Date (Text); Recieved In Department (Amount); rule: Purchase Date should be less than or equal Voucher Date.; rule: Installation Date should be greater than or equal Purchase Date.; rule: Asset Nature is mandatory.; rule: Vehicle No. is Mandatory.
- Lookups/suggestions: getTxtHAssetItem, getTxtHSupplier, getTxtHRecivedBy, getSuggestionAssetName, getTxtHSubAssetName
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `SubAssetMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Purchase Value | DmTextHelp | `txtHAssetItem` | `AssetItem_PrpAcs, 7, true` |
| 2 | Asset Name | DmSuggestBox | `txtManualCode` | `20, 10, true, "manualCode", DmDataType.STRING` |
| 3 | Voucher Date | DmTextBox | `txtVrDate` | `11, false` |
| 4 | Recieved In Department | DmTextHelp | `txtHReceivedBy` | `KeyField_PrpAcs, 7, true` |
| 5 | Voucher No. | DmTextBox | `txtVrNo` | `20, false` |
| 6 | Purchase Date | DmTextBox | `txtPurchaseDate` | `11, false` |
| 7 | Supplier | DmTextHelp | `txtHSupplier` | `TxtHSupplier_PrpAcs, 7, false` |
| 8 | - | DmTextBoxNumeric | `txtPurchaseValue` | `2, false, 10, false` |
| 9 | Installation Date | DmTextBox | `txtInstallDate` | `11, false` |
| 10 | Asset Nature | DmTextHelp | `txtHAssetNature` | `KeyField_PrpAcs, 5, true` |
| 11 | Service Provider | DmTextHelp | `txtHServiceProvider` | `TxtHSupplier_PrpAcs, 7, false` |
| 12 | Assset Make | DmRemarkTextBox | `txtAssetMake` | `250, false` |
| 13 | Accessories | DmRemarkTextBox | `txtAccessories` | `250, false` |
| 14 | Specification | DmRemarkTextBox | `txtSpecification` | `250, false` |
| 15 | Asset Remark | DmRemarkTextBox | `txtRemark` | `250, false` |
| 16 | Vehicle No. | DmTextBox | `txtVehicleNo` | `15, true` |

Other widgets (not in tab list): `gui` (DmGUI), `dmFind` (DmFind), `grdAssetDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHAssetNature`: Name [Text, w0, Name]
- `txtHReceivedBy`: Name [Text, w0, Name]
- `txtHAssetItem`: Name [Text, w0, name]; Manual Code [Text, w100, manualCode]
- `txtHServiceProvider`: Name [Text, w0, Name]; Short Name [Text, w100, Name]
- `txtHSupplier`: Name [Text, w0, Name]; Short Name [Text, w100, Name]
- `dmFind`: Asset Item [Text, w150, assetItem]; Asset Name [Text, w180, manualCode]; Voucher Date [Text, w90, v_Date]; Purchase Date [Text, w130, dateOfPurchase]; Department Name [Text, w180, department]; Purchase Value [Text, w120, amtDr]; Supplier [Text, w200, supplier]; Vehicle No. [Text, w80, vehicleNumber]
- `grdAssetDetail`: Properties [Text, w330]; Date [Text, w80]; Description [Text, w140]; Due Date [Text, w80]; Remark [Text, w274]; Date [Text, w80]; Service No. [Text, w150]; Amount [Text, w92]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHAssetItem` → `getTxtHAssetItem`
- `txtHSupplier` → `getTxtHSupplier`
- `txtHServiceProvider` → `getTxtHSupplier`
- `txtHReceivedBy` → `getTxtHRecivedBy`
- `txtManualCode` → `getSuggestionAssetName`
- `txtHAssetNature` → `getAssetNatureType`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtManualCode.getText())
- `VoucherDate` ← DmShared.dNull(txtVrDate.getText())
- `AssetItemCode` ← DmShared.iNull(txtHAssetItem.getTag()).toString()
- `AssetItemName` ← DmShared.xNull(txtHAssetItem.getText())
- `ItemManualCode` ← DmShared.xNull(txtHAssetItem.getTag2nd())
- `AssetGroupManualCode` ← DmShared.xNull(txtHReceivedBy.getTag2nd())
- `PurchaseDate` ← DmShared.dNull(txtPurchaseDate.getText())
- `SupplierCode` ← DmShared.iNull(txtHSupplier.getTag()).toString()
- `ServiceProviderCode` ← DmShared.xNull(txtHServiceProvider.getTag())
- `InstallationDate` ← DmShared.dNull(txtInstallDate.getText())
- `AssetMake` ← DmShared.xNull(txtAssetMake.getText())
- `Specification` ← DmShared.xNull(txtSpecification.getText())
- `Accessories` ← DmShared.xNull(txtAccessories.getText())
- `Remark` ← DmShared.xNull(txtRemark.getText())
- `DepartmentCode` ← DmShared.iNull(txtHReceivedBy.getTag()).toString()
- `DepartmentName` ← DmShared.xNull(txtHReceivedBy.getText())
- `PurchaseValue` ← DmShared.vNull(txtPurchaseValue.getText()).toString()
- `AssetVehicleNature` ← AssetVehicleNature.getViaKey(DmShared.iNull(txtHAssetNature.getTag()).intValue()
- `VehicleNo` ← DmShared.xNull(txtVehicleNo.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtVrNo.setText` ← data.getRecIdAssetMaster().getRecIdValue()
- `txtHAssetItem.setTag` ← data.getAssetItemCode()
- `txtHAssetItem.setText` ← data.getAssetItemName()
- `txtVrDate.setText` ← data.getDefaultMdl().getVoucherDate()
- `txtManualCode.setText` ← data.getSharedKeyFieldName()
- `txtPurchaseDate.setText` ← data.getPurchaseDate()
- `txtInstallDate.setText` ← data.getInstallationDate()
- `txtHSupplier.setTag` ← data.getSupplierCode()
- `txtHSupplier.setText` ← data.getSupplierName()
- `txtHServiceProvider.setTag` ← data.getServiceProviderCode()
- `txtHServiceProvider.setText` ← data.getServiceProviderName()
- `txtAssetMake.setText` ← data.getAssetMake()
- `txtAccessories.setText` ← data.getAccessories()
- `txtSpecification.setText` ← data.getSpecification()
- `txtHReceivedBy.setTag` ← data.getDepartmentCode()
- `txtHReceivedBy.setText` ← data.getDepartmentName()
- `txtPurchaseValue.setText` ← data.getPurchaseValue()
- `txtRemark.setText` ← data.getRemark()
- `txtHAssetNature.setTag` ← DmShared.iNull(data.getAssetVehicleNature().getId()).toString()
- `txtHAssetNature.setText` ← DmShared.xNull(data.getAssetVehicleNature().getValue())
- `txtVehicleNo.setText` ← DmShared.xNull(data.getVehicleNo())
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtHAssetItem.setEnabled(flag)`
- `txtManualCode.setEnabled(!flag)`
- `txtHReceivedBy.setEnabled(flag)`
- `txtPurchaseValue.setEnabled(flag)`
- `txtVrNo.setEnabled(false)`
- `txtVrDate.setEnabled(false)`
- `txtHAssetNature.setEnabled(false)`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
if (!topControl.getMode().equalsIgnoreCase("Browse")) {
txtHAssetItem.setEnabled(flag);
txtManualCode.setEnabled(!flag);
txtHReceivedBy.setEnabled(flag);
txtPurchaseValue.setEnabled(flag);
txtVrNo.setEnabled(false);
txtVrDate.setEnabled(false);
txtHAssetNature.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
onAdd();
```

`topControl.addEditHandeller` → `editClicked()`
```java
if (isNotTransferable) {
new DmAlertMessageBox("This asset is not transferable. You can not modify this record.", txtManualCode).show();
topControl.btnClick("");
return;
txtManualCode.setFocus(true);
manageDisplay(false);
grdAssetDetail.getGrid().getStore().add(new PropertiesPoup_Mdl());
```

`topControl.addCardHandeller` → `cardClicked()`
```java
topControlCard();
```

`topControl.addFindHandeller` → `findClicked()`
```java
flwForm.remove(vrtInnerForm);
flwForm.add(dmFind);
```

`topControl.addMoveRecHandeller` → `moveRec()`
```java
topControlMoveRec(strFetchCode);
```

`topControl.addSaveHandeller` → `saveClicked()`
```java
topControlSave(false, -1);
```

`topControl.addDeleteHandeller` → `deleteClicked()`
```java
if (isNotTransferable) {
new DmAlertMessageBox("This asset is not transferable. You can not modify this record.", txtManualCode).show();
topControl.btnClick("");
return;
new DmConfirmMessageBox("Are you sure? You want to delete this record.", new DialogHideEvent.DialogHideHandler() {
public void onDialogHide(DialogHideEvent event) {
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
topControlDelete(topControl.getDocId());
}).show();
```

`topControl.addDeleteHandeller` → `onDialogHide()`
```java
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
topControlDelete(topControl.getDocId());
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(SubAssetMaster.this);
```

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
```

`dmFind.addHelpGridCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getFind(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Find_Mdl>>>()
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

`txtHAssetItem.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHAssetItem(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Asse
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHAssetItem).show();
public void onSuccess(DmResultPageing<List<AssetItem_Mdl>> result) {
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
clearBottomArea();
AssetVehicleNature asset;
if (blnStatus && tRow != null) {
txtHAssetItem.setTag2nd(tRow.getManualCode());
txtHReceivedBy.setTag2nd(tRow.getAssetGroupManualCode());
txtHAssetNature.setTag(DmShared.iNull(AssetVehicleNature.SUB_ASSET.getId()).toString());
asset = AssetVehicleNature.getViaKey(DmShared.iNull(tRow.getAssetVechileNature()).intValue());
if (asset == AssetVehicleNature.VEHICLE) {
flwBottomAreaContainer.add(flwBottomArea);
fillGrid(tRow.getSharedKeyFieldCode());
...
```

`txtHSupplier.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHSupplier(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSupplier).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHSupplier.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSupplier).show();
```

`txtHSupplier.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSupplier.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSupplier).show();
```

`txtHServiceProvider.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHSupplier(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHServiceProvider).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHServiceProvider.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHServiceProvider).show();
```

`txtHServiceProvider.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHServiceProvider.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHServiceProvider).show();
```

`txtHReceivedBy.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHRecivedBy(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyF
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHReceivedBy).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHReceivedBy.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHReceivedBy).show();
```

`txtHReceivedBy.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHReceivedBy.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHReceivedBy).show();
```

`txtManualCode.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getSuggestionAssetName(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<Lis
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtManualCode).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtManualCode.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtManualCode).show();
```

`txtManualCode.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtManualCode.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtManualCode).show();
```

`txtHAssetNature.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getAssetNatureType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHAssetNature).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHAssetNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAssetNature).show();
```

`txtHAssetNature.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAssetNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAssetNature).show();
```

`txtHAssetNature.addTextHelpCallBack` → `getSelectedRow()`
```java
clearBottomArea();
if (blnStatus && tRow != null) {
if (DmShared.iNull(txtHAssetNature.getTag()).intValue() == AssetVehicleNature.VEHICLE.getId()) {
flwBottomAreaContainer.add(flwBottomArea);
```

`grdAssetProp.addCallBack` → `onOk()`
```java
grdAssetDetail.commitChanges();
```

`grdAssetProp.addCallBack` → `onDelete()`
```java
grdAssetDetail.commitChanges();
```

**Top control options:** setRefreshRequired(false), setDisplay(true)

**Menu items (Other button):** Manage File

_Print permission "P" is removed for this screen (replace "P" with "*")._
- Confirm: "Are you sure? You want to delete this record."
- Confirm: "Are you sure? You want to save this record."
- Alert: "This asset is not transferable. You can not modify this record."
- Alert: "This asset is not transferable. You can not modify this record."

## Files
| file | lines |
|---|---|
| client/SubAssetMaster.java | 896 |
| client/SubAssetMaster_Dsg.java | 638 |
| server/SubAssetMaster_SrvImpl.java | 1103 |
| shared/AssetItem_Mdl.java | 55 |
| shared/DefaultValue_Mdl.java | 29 |
| shared/Find_Mdl.java | 73 |
| shared/GrdSubAsset_Mdl.java | 74 |
| shared/SubAssetMaster_Mdl.java | 233 |
| shared/SubAssetMaster_Srv.java | 37 |
| shared/SubAssetMaster_SrvAsync.java | 36 |
| shared/SubAssetMaster_Vld.java | 45 |
| shared/SubAssetSupplier_Mdl.java | 11 |
| shared/TxtHSubAsset_Mdl.java | 46 |

## UI labels / columns (client)
`Asset Item`, `Purchase Value`, `Asset Name`, `Voucher Date`, `Recieved In Department`, `Voucher No.`, `Purchase Date`, `Supplier`, `Installation Date`, `Asset Nature`, `Service Provider`, `Assset Make`, `Accessories`, `Specification`, `Asset Remark`, `Vehicle No.`

## Models (shared DTOs)
### SubAssetMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String assetItemCode, assetItemName, | itemManualCode |
| String assetGroupCode, | assetGroupManualCode |
| String purchaseDate, | installationDate |
| String supplierCode, | supplierName |
| String serviceProviderCode, | serviceProviderName |
| String departmentCode, | departmentName |
| String assetMake, accessories, specification, remark, | purchaseValue |
| RecId | recIdAssetMaster |
| String | tempKeyCode |
| DefaultValue_Mdl | defaultMdl |
| AssetVehicleNature | assetVehicleNature |
| String | vehicleNo |
| List<PropertiesPoup_Mdl> | lstProperty |
| boolean | notTransferable |

### TxtHSubAsset_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String supplierName, departmentName, purchaseDate, | currentSite |

### GrdSubAsset_Mdl
| type | field |
|---|---|
| String  assetItemCode, assetItemName,  supplierCode,supplierName, departmentCode, departmentName, | puchaseDate |

### DefaultValue_Mdl
| type | field |
|---|---|
| String voucherDate, | voucherTypeCode |

### SubAssetSupplier_Mdl (extends `KeyField implements Serializable, IsSerializable`)
### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String assetItem, purchaseDate, supplier, department, | purchaseValue |
| String voucherDate, | vehicleNo |

### AssetItem_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String assetGroupManualCode, assetGroupName, manualCode, | assetVechileNature |
| List<PropertiesPoup_Mdl> | grdPropertiesPopup |

## RPC interface
| method | params | returns |
|---|---|---|
| saveRecord | SubAssetMaster_Mdl mrTemp, String voucherPrefix | DmResult<String> |
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<SubAssetMaster_Mdl> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| deleteRecord | String strKey | DmResult<String> |
| getSuggestionAssetName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getTxtHAssetItem | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<AssetItem_Mdl>> |
| getTxtHSupplier | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHRecivedBy | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| getAssetNatureType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getGrid | String assetItemCode | DmResult<List<PropertiesPoup_Mdl>> |
| getTxtHSubAssetName | String assetItem, String assetCode, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHSubAsset_Mdl>> |

## Validator `SubAssetMaster_Vld`
```java
public class SubAssetMaster_Vld {
    public static void chkSave(SubAssetMaster_Mdl mrTemp) {
        DmShared.isValidAmount(mrTemp.getAssetItemCode(), "Asset Item", 100);
        if (DmShared.iNull(mrTemp.getSharedKeyFieldCode()).longValue() > 0) {
            DmShared.isValidText(mrTemp.getSharedKeyFieldName(), "Asset Name", 200);
        }
        DmShared.isValidText(mrTemp.getDefaultMdl().getVoucherDate(), "Voucher Date", 300);
        DmShared.isValidAmount(mrTemp.getDepartmentCode(), "Recieved In Department", 400);
        if (!DmShared.dNull(mrTemp.getPurchaseDate()).equalsIgnoreCase("")) {
            if (DmShared.convertDate(mrTemp.getPurchaseDate()).getTime() > DmShared.convertDate(mrTemp.getDefaultMdl().getVoucherDate()).getTime()) {
                throw new DmException(500, "Purchase Date should be less than or equal Voucher Date.");
            }
            if (!DmShared.xNull(mrTemp.getInstallationDate()).equalsIgnoreCase("")) {
                if (DmShared.convertDate(mrTemp.getPurchaseDate()).getTime() > DmShared.convertDate(mrTemp.getInstallationDate()).getTime()) {
                    throw new DmException(600, "Installation Date should be greater than or equal Purchase Date.");
                }
            }
        }
        if (mrTemp.getAssetVehicleNature() == null) {
            throw new DmException(800, "Asset Nature is mandatory.");
        }
        mrTemp.setVehicleNo(DmShared.removeSpace(mrTemp.getVehicleNo()));
        if (DmShared.iNull(mrTemp.getAssetVehicleNature().getId()).intValue() == DmShared.iNull(AssetVehicleNature.VEHICLE.getId()).intValue()) {
            if (DmShared.xNull(mrTemp.getVehicleNo()).equals("")) {
                throw new DmException(900, "Vehicle No. is Mandatory.");
            }
        }
    }
}
```

## Server `SubAssetMaster_SrvImpl.java` (1103 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getFieldUDT`, `DmServer.getRecId`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getVType`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.vNull`, `DmShared.xNull`

### `saveRecord`
`public DmResult<String> saveRecord(SubAssetMaster_Mdl mrTemp, String voucherPrefix)`

- SQL 1: `select manualCode from assetMast where manualCode =:manualCode and code <> :code`
- SQL 2: `select isTransferable from assetMast where code = :code`
- SQL 3: `insert Into assetMast(code, manualCode, assetItemCode, assetSupplierCode, assetMake, assetAccessories, assetSpecification, assetRemark, dateOfPurchase, dateOfInstallation, assetNature, vehicleNumber, preparedBy, site_code, u_EntDt, currentDepartmentCode, currentSite_Code, serviceProviderCode) values (:code, :manualCode, :assetItemCode, :assetSupplierCode, :assetMake, :assetAccessories, :assetSpecification, :assetRemark, :dateOfPurchase, :dateOfInstallation, :assetNature, :vehicleNumber, :preparedBy, :site_code, :u_EntDt, :department, :site_Code, :serviceProviderCode)`
- SQL 4: `Delete From assetStock Where docId= :docId`
- SQL 5: `Delete From assetLedger Where docId= :docId`
- SQL 6: `Delete From assetStockHead Where docId= :docId`
- SQL 7: `Insert Into assetStockHead(docId, v_Type,v_No, recId, v_Date, recIdPrefix, v_Prefix, u_EntDt, site_Code, preparedBy) Values (:docId, :v_Type, :v_No, :recId, :v_Date, :recIdPrefix, :v_Prefix, :u_EntDt,:site_Code, :userName)`
- SQL 8: `Insert Into assetStock(docId,v_SNo,v_Type,v_No,recIdPrefix,recId,v_Date,departmentCode, assetCode,recQty,isSystemGenerated,site_Code,u_Name,u_EntDt) Values (:docId, 1,:v_Type,:v_No,:recIdPrefix,:recId,:v_Date,:departmentCode, :assetCode,1,0,:site_Code,:userName,:u_EntDt)`
- SQL 9: `Insert Into assetLedger(docId,v_SNo,v_No,recIdPrefix,recId,v_Type, v_Prefix,v_Date,assetCode,amtDr,isNature,typeOfExpense) Values (:docId,:v_SNo,:v_No,:recIdPrefix,:recId,:v_Type,:v_Prefix,:v_Date, :assetCode,:amtDr,:isNature,:typeOfExpense)`
- SQL 10: `Update assetMast Set manualCode = :manualCode, assetSupplierCode =:assetSupplierCode, serviceProviderCode =:serviceProviderCode, assetMake =:assetMake, assetAccessories =:assetAccessories, assetSpecification =:assetSpecification, assetRemark =:assetRemark, dateOfPurchase =:dateOfPurchase, dateOfInstallation =:dateOfInstallation, vehicleNumber= :vehicleNumber, u_Name = :uName, u_EntDt = :u_EntDt Where code = :code`
- SQL 11: `update assetMast set parentAssetCode = null where parentAssetCode = :assetCode`
- SQL 12: `select child.manualCode, child.currentDepartmentCode as childDepartment, parent.currentDepartmentCode as parentDepartment, child.currentSite_Code as childCurrentSite_Code, parent.currentSite_Code as parentCurrentSite_Code from assetMast parent left join assetMast child on child.parentAssetCode = parent.code where child.parentAssetCode = :assetCode`
- SQL 13: `select am.code, max(am.manualCode) as assetName, count(ca.code) as cnt from assetMast am left join assetMast ca on am.code = ca.parentAssetCode where am.parentAssetCode = :aasetCode group by am.code having count(ca.code) > 0`
- SQL 14: `select code from assetMast where code = :aasetCode and parentAssetCode is not null`
- Params: `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:manualCode` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:vehicleNumber` ← `DmShared.xNull(mrTemp.getVehicleNo())`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:manualCode` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:assetItemCode` ← `DmShared.foriegnKey(mrTemp.getAssetItemCode())`; `:assetSupplierCode` ← `DmShared.foriegnKey(mrTemp.getSupplierCode())`; `:serviceProviderCode` ← `DmShared.foriegnKey(mrTemp.getServiceProviderCode())`; `:assetMake` ← `DmShared.xNull(mrTemp.getAssetMake())`; `:assetAccessories` ← `DmShared.xNull(mrTemp.getAccessories())`; `:assetSpecification` ← `DmShared.xNull(mrTemp.getSpecification())`; `:assetRemark` ← `DmShared.xNull(mrTemp.getRemark())`; `:dateOfPurchase` ← `DmServer.convertDate(mrTemp.getPurchaseDate())`; `:dateOfInstallation` ← `DmServer.convertDate(mrTemp.getInstallationDate())`; `:department` ← `DmShared.foriegnKey(mrTemp.getDepartmentCode())`; `:assetNature` ← `DmShared.iNull(AssetVehicleNature.SUB_ASSET.getId()).intValue()`; `:vehicleNumber` ← `DmShared.xNull(mrTemp.getVehicleNo())`; `:u_EntDt` ← `DmServer.getServerTimestamp()`; `:preparedBy` ← `DmShared.xNull(DmServer.getSsnUserName(this))`; `:site_code` ← `DmShared.foriegnKey(DmServer.getSsnSiteCode(this))`; `:assetCode` ← `DmShared.foriegnKey(mrTemp.getSharedKeyFieldCode())`; `:propertyCode` ← `DmShared.xNull(item_Mdl.getSharedKeyFieldCode())`; `:associatedDate` ← `DmServer.convertDate(item_Mdl.getAssociatedDate())`; `:associatedValue` ← `DmShared.xNull(item_Mdl.getAssociatedValue())`; `:dueDate` ← `DmServer.convertDate(item_Mdl.getDueOn())`; `:lastServiceNo` ← `DmShared.xNull(item_Mdl.getServiceNo())`; `:lastServiceAmount` ← `DmServer.vNull(item_Mdl.getLastServiceAmount(), "0.00")`; `:lastServiceDate` ← `DmServer.convertDate(item_Mdl.getLastServiceDueOn())`; `:remark` ← `DmShared.xNull(item_Mdl.getRemarks())`; `:SNo` ← `DmShared.iNull(item_Mdl.getSNo()).toString()`; `:nature` ← `DmShared.iNull(item_Mdl.getNatureCode()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_Type` ← `DmShared.foriegnKey(dvm.getVoucherTypeCode())`; `:v_No` ← `DmShared.iNull(mrTemp.getRecIdAssetMaster().getCounter()).toString()`; `:recId` ← `DmShared.xNull(DmShared.xNull(mrTemp.getRecIdAssetMaster().getRecIdValue()))`; `:v_Date` ← `DmServer.convertDate(dvm.getVoucherDate())`; `:recIdPrefix` ← `DmShared.xNull(DmShared.xNull(mrTemp.getRecIdAssetMaster().getPrefix()))`; `:v_Prefix` ← `voucherPrefix`; `:u_EntDt` ← `DmServer.getServerTimestamp()`; `:site_code` ← `DmShared.foriegnKey(DmServer.getSsnSiteCode(this))`; `:userName` ← `DmShared.xNull(DmServer.getSsnUserName(this))`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_Type` ← `DmShared.foriegnKey(dvm.getVoucherTypeCode())`; `:v_No` ← `DmShared.iNull(mrTemp.getRecIdAssetMaster().getCounter()).toString()`; `:recIdPrefix` ← `DmShared.xNull(DmShared.xNull(mrTemp.getRecIdAssetMaster().getPrefix()))`; `:recId` ← `DmShared.xNull(DmShared.xNull(mrTemp.getRecIdAssetMaster().getRecIdValue()))`; `:v_Date` ← `DmServer.convertDate(dvm.getVoucherDate())`; `:departmentCode` ← `DmShared.foriegnKey(mrTemp.getDepartmentCode())`; `:assetCode` ← `DmShared.foriegnKey(mrTemp.getSharedKeyFieldCode())`; `:site_code` ← `DmShared.foriegnKey(DmServer.getSsnSiteCode(this))`; `:userName` ← `DmShared.xNull(DmServer.getSsnUserName(this))`; `:u_EntDt` ← `DmServer.getServerTimestamp()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_SNo` ← `"1"`; `:v_No` ← `DmShared.iNull(mrTemp.getRecIdAssetMaster().getCounter()).toString()`; `:recIdPrefix` ← `DmShared.xNull(DmShared.xNull(mrTemp.getRecIdAssetMaster().getPrefix()))`; `:recId` ← `DmShared.xNull(DmShared.xNull(mrTemp.getRecIdAssetMaster().getRecIdValue()))`; `:v_Type` ← `DmShared.foriegnKey(dvm.getVoucherTypeCode())`; `:v_Prefix` ← `voucherPrefix`; `:v_Date` ← `DmServer.convertDate(dvm.getVoucherDate())`; `:assetCode` ← `DmShared.foriegnKey(mrTemp.getSharedKeyFieldCode())`; `:amtDr` ← `DmShared.vNull(mrTemp.getPurchaseValue()).toString()`; `:isNature` ← `DmShared.iNull(AssetNature.PURCHASE.getId()).toString()`; `:typeOfExpense` ← `DmShared.iNull(ExpenceType.CAPITAL.getId()).toString()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:manualCode` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:assetSupplierCode` ← `DmShared.foriegnKey(mrTemp.getSupplierCode())`; `:serviceProviderCode` ← `DmShared.foriegnKey(mrTemp.getServiceProviderCode())`; `:assetMake` ← `DmShared.xNull(mrTemp.getAssetMake())`; `:assetAccessories` ← `DmShared.xNull(mrTemp.getAccessories())`; `:assetSpecification` ← `DmShared.xNull(mrTemp.getSpecification())`; `:assetRemark` ← `DmShared.xNull(mrTemp.getRemark())`; `:dateOfPurchase` ← `DmServer.convertDate(mrTemp.getPurchaseDate())`; `:dateOfInstallation` ← `DmServer.convertDate(mrTemp.getInstallationDate())`; `:vehicleNumber` ← `DmShared.xNull(mrTemp.getVehicleNo())`; `:uName` ← `DmShared.xNull(DmServer.getSsnUserName(this))`; `:u_EntDt` ← `DmServer.getServerTimestamp()`; `:assetCode` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:assetCode` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:aasetCode` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:aasetCode` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`
- Errors: "This asset is not transferable. You can not modify this record." | "Vehicle No. Already Exist." | "Invalid key." | "You can not assign child to this asset it is already a child. "
- Other calls: `CAPITAL.getId`, `PURCHASE.getId`, `SUB_ASSET.getId`, `SubAssetMaster_Vld.chkSave`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `select code from assetMast where assetNature = {…} order by manualCode`
- Other calls: `SUB_ASSET.getId`

### `getMoveRec`
`public DmResult<SubAssetMaster_Mdl> getMoveRec(String strKey)`

- SQL 1: `select am.code, am.manualCode, astk.recId, astk.v_No, astk.recIdPrefix, am.assetItemCode, aim.name as assetItem, am.assetSupplierCode, sup.name as supplier, am.assetMake, am.assetAccessories, am.assetSpecification, am.assetRemark, am.dateOfPurchase, am.dateOfInstallation, am.assetNature, am.vehicleNumber, am.preparedBy, am.u_Name, al.v_Date, al.amtDr, astk.departmentCode, dep.name as department, am.isTransferable, am.serviceProviderCode, ser.name as serviceProvider from assetMast am left join subGroup sup on sup.subCode = am.assetSupplierCode left join subGroup ser on ser.subCode = am.serviceProviderCode left join assetLedger al on al.docID = am.code left join assetStock astk on astk.docId = am.code left join subGroup dep on dep.subCode = astk.departmentCode left join assetItemMast aim on aim.code = am.assetItemCode where am.code = :code`
- SQL 2: `select apd.propertyCode, apd.assetCode, apm.name, apd.associatedValue, apd.associatedDate, apd.lastServiceNo, apd.dueDate, apd.lastServiceAmount, apd.lastServiceDate, remark, apd.SNo, apd.nature from assetPropertiesDetail apd left join assetPropertyMast apm on apm.code = apd.propertyCode where apd.assetCode = :docId order by apd.SNo, apm.name`
- Params: `:code` ← `strKey`; `:docId` ← `strKey`
- Other calls: `AssetVehicleNature.getViaKey`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select am.code, am.manualCode, aim.name AS assetItem, sup.name AS supplier, am.dateOfPurchase, al.v_Date, al.amtDr, dep.name AS department, am.vehicleNumber from assetMast am left join subGroup sup on sup.subCode = am.assetSupplierCode left join assetLedger al on al.docID = am.code left join assetStock astk on astk.docId = am.code left join subGroup dep on dep.subCode = astk.departmentCode left join assetItemMast aim on aim.code = am.assetItemCode where assetNature = {…}`
- Other calls: `SUB_ASSET.getId`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey)`

- SQL 1: `select isTransferable from assetMast where code = :code`
- SQL 2: `select count(docId) as cnt from assetStock where docId !=:docId and assetCode = :docId`
- SQL 3: `select count(docId) as cnt from assetLedger where docId !=:docId and assetCode = :docId`
- SQL 4: `Delete From assetStock Where docId= :docId`
- SQL 5: `Delete From assetStockHead Where docId= :docId`
- SQL 6: `Delete From assetLedger Where docId= :docId`
- SQL 7: `Delete From assetPropertiesDetail Where assetCode= :assetCode`
- SQL 8: `Delete From assetMast Where code= :code`
- SQL 9: `update assetMast Set parentAssetCode = null where parentAssetCode = :code`
- Params: `:code` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `strKey`; `:docId` ← `strKey`; `:docId` ← `strKey`; `:docId` ← `strKey`; `:docId` ← `strKey`; `:assetCode` ← `strKey`; `:code` ← `strKey`; `:code` ← `strKey`
- Errors: "This asset is not transferable. You can not modify this record." | "Asset in use."

### `getTxtHAssetItem`
`public DmResultPageing<List<AssetItem_Mdl>> getTxtHAssetItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select aim.code, aim.name, aim.manualCode, agm.manualCode as groupManualCode, agm.name as assetGroup, agm.assetNature from assetItemMast aim left join assetGroupMast agm on agm.code = aim.assetGroupCode left join assetSiteList asl on asl.code = aim.code where site_Code = :siteCode`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHSupplier`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSupplier(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select sg.subCode, sg.name, sg.manualCode from subGroup sg left join subGroupNature sgn on sgn.code = sg.filterNature left join subGroupSiteList sgsl on sgsl.subCode = sg.subCode where sgsl.site_Code = :siteCode and sgn.manualCode in ('SU') and sg.isActive = 1`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`


### `getTxtHRecivedBy`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHRecivedBy(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select sg.subCode, sg.name from departmentMast dep left join subgroup sg on sg.subCode = dep.subCode left join subGroupSiteList sgsl on sgsl.subCode = sg.subCode where sg.isActive = 1 and sgsl.site_Code = :siteCode`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`

### `getSuggestionAssetName`
`public DmResultPageing<List<String>> getSuggestionAssetName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select manualCode From assetMast`

### `getAssetNatureType`
`public DmResultPageing<List<KeyFieldExtended>> getAssetNatureType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `AssetVehicleNature.values`

### `getGrid`
`public DmResult<List<PropertiesPoup_Mdl>> getGrid(String assetItemCode)`

- SQL 1: `select aip.propertyCode, apm.name,aip.SNo, aip.nature from assetItemProperties aip left join assetPropertyMast apm on apm.code = aip.propertyCode where aip.code = :assetItemCode order by aip.SNo`
- Params: `:assetItemCode` ← `DmShared.xNull(assetItemCode)`

### `getTxtHSubAssetName`
`public DmResultPageing<List<TxtHSubAsset_Mdl>> getTxtHSubAssetName(String assetItem, String assetCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select am.code, am.manualCode, am.assetSupplierCode, sup.name as supplier, am.currentDepartmentCode, dep.name as department, am.currentSite_code, sm.name as siteName, am.dateofPurchase from assetMast am left join subGroup sup on sup.subCode = am.assetSupplierCode left join subGroup dep on dep.subCode = am.currentDepartmentCode left join siteMast sm on sm.code = am.currentSite_code left join assetMast child on child.parentAssetCode = am.code where am.assetItemCode = :assetItem and am.code <> :assetCode and isnull(am.parentAssetCode, :assetCode) = :assetCode and child.parentAssetCode is null`
- Params: `:assetItem` ← `DmShared.xNull(assetItem)`; `:assetCode` ← `DmShared.xNull(assetCode)`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFileManager.client`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGUI.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.self.client`, `paperbiz.assets.propertiesPopup.client`, `paperbiz.assets.propertiesPopup.shared`, `paperbiz.assets.self.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (rsTemp.next())` | " Asset Name Already Exist." |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("isTransferable")).intValue() == 0)` | "This asset is not transferable. You can not modify this record." |
| saveRecord | `if (rsTemp.next())` | "Vehicle No. Already Exist." |
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("childDepartment")).longValue() != DmShared.iNull(rsTemp.getString("parentDepartment")).longValue())` | "manualCode" |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("childCurrentSite_Code")).longValue() != DmShared.iNull(rsTemp.getString("parentCurrentSite_Code")).longValue())` | "manualCode" |
| saveRecord | `if (rsTemp.next())` | " + DmShared.xNull(rsTemp.getString(" |
| saveRecord | `if (rsTemp.next())` | "You can not assign child to this asset it is already a child. " |
| deleteRecord | `if (DmShared.iNull(rsTemp.getString("isTransferable")).intValue() == 0)` | "This asset is not transferable. You can not modify this record." |
| deleteRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).longValue() > 0)` | "Asset in use." |
| deleteRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Asset in use." |

## Notes (hand-written)
_No hand-written notes yet._
