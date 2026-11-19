# pb-assets / assetAddRepair

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/assets/src/assetAddRepair/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: assetStockHead, assetLedger
- Update tables: assetStockHead, assetMast
- Delete cascade (in order): assetLedger, assetStockHead
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Asset Item (Text); Asset Name (Text); Voucher Date (Text); Amount (Amount); rule: Nature is mandatory.; rule: Type of Expense is mandatory.
- Lookups/suggestions: getTxtHAssetItem, getTxtHProperties
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `AddRepairPopUp`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Properties | DmTextHelp | `txtHProperties` | `Property_PrpAcs, 7, true` |
| 2 | Description | DmTextBox | `txtAssociatedValue` | `20, false` |
| 3 | Date | DmTextBox | `txtAssociatedDate` | `11, false` |
| 4 | Remark | DmRemarkTextBox | `txtRemark` | `250, false` |
| 5 | Due Date | DmTextBox | `txtDueOn` | `11, false` |
| 6 | Service No. | DmTextBox | `txtServiceNo` | `11, false` |
| 7 | Date | DmTextBox | `txtLastServiceDueOn` | `11, false` |
| 8 | Amount | DmTextBoxNumeric | `txtLastServiceAmount` | `11, false, 10, false` |

Other widgets (not in tab list): `txtAssetCode` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `txtHProperties`: Name [Text, w0, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHProperties` → `getTxtHProperties`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← txtHProperties.getTag()
- `SharedKeyFieldName` ← txtHProperties.getText()
- `AssociatedDate` ← DmShared.dNull(txtAssociatedDate.getText())
- `AssociatedValue` ← DmShared.xNull(txtAssociatedValue.getText())
- `ServiceNo` ← DmShared.xNull(txtServiceNo.getText())
- `DueOn` ← DmShared.dNull(txtDueOn.getText())
- `LastServiceDueOn` ← DmShared.dNull(txtLastServiceDueOn.getText())
- `LastServiceAmount` ← DmShared.xNull(txtLastServiceAmount.getText())
- `Remarks` ← DmShared.xNull(txtRemark.getText())

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHProperties.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHProperties(assetItemCode, assetCode, helper, blnToEndOfFile, new AsyncCallbac
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHProperties).show();
public void onSuccess(DmResultPageing<List<PropertiesPoup_Mdl>> result) {
if (result.isStatus()) {
txtHProperties.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHProperties).show();
```

`txtHProperties.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHProperties.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHProperties).show();
```

`txtHProperties.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && tRow != null) {
clear();
setPropertyData(tRow);
```
- Alert: "Properties is mandatory"

### Screen `AssetAddRepair`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Asset Item | DmTextHelp | `txtHAssetItem` | `KeyField_PrpAcs, 7, true` |
| 2 | Asset Name | DmTextHelp | `txtHManualCode` | `AssetCode_PrpAcs, 7, true` |
| 3 | Voucher No. | DmTextBox | `txtVrNo` | `20, false` |
| 4 | Voucher Date | DmTextBox | `txtVrDate` | `11, true` |
| 5 | Assset Make | DmRemarkTextBox | `txtAssetMake` | `250, false` |
| 6 | Accessories | DmRemarkTextBox | `txtAccessories` | `250, false` |
| 7 | Specification | DmRemarkTextBox | `txtSpecification` | `250, false` |
| 8 | Asset Remark | DmRemarkTextBox | `txtAssetRemark` | `250, false` |
| 9 | Nature | DmTextHelp | `txtHNature` | `KeyField_PrpAcs, 7, true, true` |
| 10 | Type of Expense | DmTextHelp | `txtHExpense` | `KeyField_PrpAcs, 7, true, true` |
| 11 | Ref. No. | DmTextBox | `txtRefNo` | `20, false` |
| 12 | Ref. Date | DmTextBox | `txtRefDate` | `11, false` |
| 13 | Remark | DmRemarkTextBox | `txtRemark` | `250, false` |
| 14 | Amount | DmTextBoxNumeric | `txtAmount` | `2, false, 10, true` |

Other widgets (not in tab list): `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `txtHManualCode`: Name [Text, w0, manualCode]; Department [Text, w100, Department]
- `txtHNature`: Name [Text, w0, Name]
- `txtHExpense`: Name [Text, w0, Name]
- `txtHAssetItem`: Name [Text, w0, Name]
- `dmFind`: Voucher No. [Text, w130, recId]; Voucher Date [Text, w100, v_Date]; Asset Name [Text, w300, manualCodeName]; Asset Item [Text, w300, assetItemName]; Ref. No [Text, w130, refNO]; Ref. Date [Text, w100, refDate]; Amount [Text, w150, amount]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHAssetItem` → `getTxtHAssetItem`
- `txtHExpense` → `getExpenseType`
- `txtHNature` → `getNatureType`
- `txtHManualCode` → `getManualCode`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `VoucherTypeCode` ← DmShared.xNull(txtVrNo.getTag())
- `AssetItemCode` ← DmShared.xNull(txtHAssetItem.getTag())
- `AssetItemName` ← DmShared.xNull(txtHAssetItem.getText())
- `DepartmentCode` ← DmShared.xNull(txtHManualCode.getTag2nd())
- `AssetCode` ← DmShared.xNull(txtHManualCode.getTag())
- `AssetName` ← DmShared.xNull(txtHManualCode.getText())
- `AssetMake` ← DmShared.xNull(txtAssetMake.getText())
- `Specification` ← DmShared.xNull(txtSpecification.getText())
- `Accessories` ← DmShared.xNull(txtAccessories.getText())
- `AssetNature` ← AssetNature.getViaKey(DmShared.iNull(txtHNature.getTag()).intValue())
- `ExpenceType` ← ExpenceType.getViaKey(DmShared.iNull(txtHExpense.getTag()).intValue())
- `RefNo` ← DmShared.xNull(txtRefNo.getText())
- `RefDate` ← DmShared.dNull(txtRefDate.getText())
- `Remark` ← DmShared.xNull(txtRemark.getText())
- `VoucherDate` ← txtVrDate.getText()
- `Amount` ← txtAmount.getText()

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtVrNo.setText` ← data.getSharedKeyFieldName()
- `txtVrNo.setTag` ← data.getVoucherTypeCode()
- `txtVrDate.setText` ← data.getVoucherDate()
- `txtHAssetItem.setTag` ← data.getAssetItemCode()
- `txtHAssetItem.setText` ← data.getAssetItemName()
- `txtHManualCode.setTag2nd` ← data.getDepartmentCode()
- `txtHManualCode.setTag` ← data.getAssetCode()
- `txtHManualCode.setText` ← data.getAssetName()
- `txtAssetMake.setText` ← data.getAssetMake()
- `txtAccessories.setText` ← data.getAccessories()
- `txtSpecification.setText` ← data.getSpecification()
- `txtHNature.setTag` ← DmShared.iNull(data.getAssetNature().getId()).toString()
- `txtHNature.setText` ← data.getAssetNature().getValue()
- `txtHExpense.setTag` ← DmShared.iNull(data.getExpenceType().getId()).toString()
- `txtHExpense.setText` ← data.getExpenceType().getValue()
- `txtRemark.setText` ← data.getRemark()
- `txtRefNo.setText` ← data.getRefNo()
- `txtRefDate.setText` ← data.getRefDate()
- `txtAmount.setText` ← data.getAmount()
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtVrNo.setEnabled(false)`
- `txtAssetMake.setEnabled(false)`
- `txtSpecification.setEnabled(false)`
- `txtAccessories.setEnabled(false)`
- `txtAssetRemark.setEnabled(false)`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
txtVrNo.setEnabled(false);
txtAssetMake.setEnabled(false);
txtSpecification.setEnabled(false);
txtAccessories.setEnabled(false);
txtAssetRemark.setEnabled(false);
flwProperty.clear();
if (isPropertyEdit) {
flwProperty.add(btnProp);
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

`topControl.addEditHandeller` → `editClicked()`
```java
manageDisplay(false);
manageViaNature();
if ((DmShared.iNull(txtHNature.getTag()).intValue()) == AssetNature.PURCHASE.getId()) {
txtRefNo.setFocus(true);
} else {
txtHAssetItem.setFocus(true);
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
topControlSave();
```

`topControl.addDeleteHandeller` → `deleteClicked()`
```java
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(AssetAddRepair.this);
```

`dmFind.addHelpGridCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getFind(topControl.getPermissions(), helper, blnToEndOfFile, new AsyncCallback<DmRes
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
srvAsync.invokeRPC(blnCallBackWait).getTxtHAssetItem(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyF
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
if (blnStatus = true && tRow != null) {
txtHManualCode.setText("");
txtHManualCode.setTag("");
txtHManualCode.setTag2nd("");
txtAssetMake.setText("");
txtSpecification.setText("");
txtAccessories.setText("");
txtAssetRemark.setText("");
```

`txtHExpense.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getExpenseType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFie
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHExpense).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHExpense.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHExpense).show();
```

`txtHExpense.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHExpense.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHExpense).show();
```

`txtHNature.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getNatureType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFiel
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHNature).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHNature).show();
```

`txtHNature.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHNature).show();
```

`txtHManualCode.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getManualCode(txtHAssetItem.getTag(), helper, blnToEndOfFile, new AsyncCallback<DmRe
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHManualCode).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHManualCode.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHManualCode).show();
```

`txtHManualCode.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHManualCode.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHManualCode).show();
```

`txtHManualCode.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus = true && tRow != null) {
txtHManualCode.setTag2nd(DmShared.xNull(tRow.getSharedPreparedBy()));
getAutoFillSuc(txtHManualCode.getTag(), null);
```

**Top control options:** setOtherRequired(false), setRefreshRequired(false), setOtherRequired(true), setDisplay(true)

**Menu items (Other button):** Process Message

_Print permission "P" is removed for this screen (replace "P" with "*")._
- Confirm: "Are you sure? You want to delete this record."
- Confirm: "Do you want to update Property ?"
- Confirm: "Are you sure? You want to Process Message."
- Alert: "Please use it in Edit Mode only."
- Alert: "Can be use in Browse Mode only and select a record."
- Alert: "Message is processed successfully!"
- Alert: "Message can be send only in Browse Mode!"

### Screen `GrdAddRepairPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `grdAssetDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `grdAssetDetail`: Properties [Text, w150]; Date [Text, w75]; Description [Text, w75]; Due Date [Text, w75]; Remark [Text, w200]; Date [Text, w75]; Service No. [Text, w125]; Amount [Text, w75]

**Event handlers (condensed code, CRUD flow and field callbacks):**

`grdAssetProp.addCallBack` → `onOk()`
```java
grdAssetDetail.commitChanges();
```

`grdAssetProp.addCallBack` → `onDelete()`
```java
grdAssetDetail.commitChanges();
```

## Files
| file | lines |
|---|---|
| client/AddRepairPopUp.java | 288 |
| client/AddRepairPopUp_Dsg.java | 319 |
| client/AssetAddRepair.java | 754 |
| client/AssetAddRepair_Dsg.java | 460 |
| client/GrdAddRepairPopup.java | 178 |
| client/GrdAddRepairPopup_Dsg.java | 128 |
| server/AssetAddRepair_SrvImpl.java | 898 |
| shared/AssetAddRepair_Mdl.java | 179 |
| shared/AssetAddRepair_Srv.java | 41 |
| shared/AssetAddRepair_SrvAsync.java | 41 |
| shared/AssetAddRepair_Vld.java | 26 |
| shared/AssetSupplier_Mdl.java | 12 |
| shared/AutoFill_Mdl.java | 86 |
| shared/DefaultValue_Mdl.java | 30 |
| shared/Find_Mdl.java | 111 |
| shared/GrdProperty_Mdl.java | 36 |

## UI labels / columns (client)
`Asset Item`, `Asset Name`, `Voucher No.`, `Voucher Date`, `Assset Make`, `Accessories`, `Specification`, `Asset Remark`, `Nature`, `Type of Expense`, `Ref. No.`, `Ref. Date`, `Remark`, `Amount`, `Associated`, `Last Serive Detail`, `Properties`, `Description`, `Date`, `Due Date`, `Service No.`

## Models (shared DTOs)
### DefaultValue_Mdl
| type | field |
|---|---|
| String | currentDate |
| String | voucherTypeTag |

### AutoFill_Mdl
| type | field |
|---|---|
| String remark, accessories, assetMake, specification, | nextServiceDueOn |
| String itemCode, itemName, assetCode, | assetName |

### GrdProperty_Mdl (extends `KeyField implements Serializable, IsSerializable`)
### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String assetItemCode, assetItemName, manualCode, manualCodeName, voucherDate, natureCode, nature, expenseCode, expense, refNo, refDate, | amount |

### AssetSupplier_Mdl (extends `KeyField implements Serializable, IsSerializable`)
### AssetAddRepair_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | voucherTypeCode |
| String | voucherDate |
| String assetItemCode, | assetItemName |
| String assetCode, | assetName |
| String assetMake, specification, | accessories |
| String departmentCode, | department |
| String remark, refNo, refDate, | amount |
| AssetNature | assetNature |
| ExpenceType | expenceType |
| RecId | recIdAssetAdd |
| List<PropertiesPoup_Mdl> | grdProp |

## RPC interface
| method | params | returns |
|---|---|---|
| saveRecord | AssetAddRepair_Mdl mrTemp, String voucherPrefix | DmResult<String> |
| getNavigator | UserPermission userPermission | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<AssetAddRepair_Mdl> |
| sendMessage | String strKey, String vTypeCode | DmResult<String> |
| getFind | UserPermission userPermission, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| deleteRecord | String strKey | DmResult<String> |
| getTxtHAssetItem | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getNatureType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getExpenseType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getManualCode | String assetItemCode, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getAutoFill | String assetCode | DmResult<AutoFill_Mdl> |
| getGridProperties | String assetCode | DmResult<List<PropertiesPoup_Mdl>> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| getTxtHProperties | String assetItemCode, String assetCode, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<PropertiesPoup_Mdl>> |
| saveProperty | Boolean isDelete, PropertiesPoup_Mdl mdl | DmResult<String> |

## Validator `AssetAddRepair_Vld`
```java
public class AssetAddRepair_Vld {
    public static void chkSave(AssetAddRepair_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getAssetItemName(), "Asset Item", 100);
        DmShared.isValidText(mrTemp.getAssetCode(), "Asset Name", 200);
        DmShared.isValidText(DmShared.dNull(mrTemp.getVoucherDate()), "Voucher Date", 300);
        if (mrTemp.getAssetNature() == null) {
            throw new DmException(400, "Nature is mandatory.");
        }
        if (mrTemp.getExpenceType() == null) {
            throw new DmException(500, "Type of Expense is mandatory.");
        }
        if (!(mrTemp.getAssetNature() == AssetNature.REPAIR && mrTemp.getExpenceType() == ExpenceType.REVENUE)) {
            DmShared.isValidAmount(mrTemp.getAmount(), "Amount", 600);
        }
    }
}
```

## Server `AssetAddRepair_SrvImpl.java` (898 lines)
Helpers used: `DmServer.checkForwardVoucherDateTime`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getRecId`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserViewCondition`, `DmServer.isDocumentEMailRequired`, `DmServer.sendMail`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `saveRecord`
`public DmResult<String> saveRecord(AssetAddRepair_Mdl mrTemp, String voucherPrefix)`

- SQL 1: `select v_Type from Voucher_Type where v_Type=:v_Type`
- SQL 2: `select v_Date from assetStockHead where docId=:asseCode`
- SQL 3: `Insert Into assetStockHead(docId, v_Type,v_No, recId, v_Date, recIdPrefix, v_Prefix, u_EntDt, refNo, refDate, remark, site_Code, preparedBy, approvedBy) Values (:docId, :v_Type, :v_No, :recId, :v_Date, :recIdPrefix, :v_Prefix, :u_EntDt, :refNo, :refDate, :remark, :site_Code, :userName, :userName)`
- SQL 4: `Update assetStockHead Set v_Date = :v_Date, u_Name = :uName, u_EntDt = :u_EntDt Where docId = :docId`
- SQL 5: `delete from assetLedger where docId = :docId`
- SQL 6: `Insert Into assetLedger(docId,v_SNo,v_No,recIdPrefix,recId,v_Type, v_Prefix,v_Date, refNo, refDate, assetCode, amtDr, remark,isNature,typeOfExpense) Values (:docId,:v_SNo,:v_No,:recIdPrefix,:recId,:v_Type,:v_Prefix,:v_Date, :refNo, :refDate, :assetCode,:amtDr,:remark, :isNature,:typeOfExpense)`
- SQL 7: `Update assetMast Set u_Name = :uName, u_EntDt = :u_EntDt Where code = :assetCode`
- Params: `:v_Type` ← `DmShared.iNull(mrTemp.getVoucherTypeCode()).toString()`; `:asseCode` ← `DmShared.iNull(mrTemp.getAssetCode()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_Type` ← `DmShared.foriegnKey(mrTemp.getVoucherTypeCode())`; `:v_No` ← `DmShared.iNull(mrTemp.getRecIdAssetAdd().getCounter()).toString()`; `:recId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:recIdPrefix` ← `DmShared.xNull(DmShared.xNull(mrTemp.getRecIdAssetAdd().getPrefix()))`; `:v_Prefix` ← `voucherPrefix`; `:refNo` ← `DmShared.xNull(mrTemp.getRefNo())`; `:refDate` ← `DmServer.convertDate(mrTemp.getRefDate())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:u_EntDt` ← `DmServer.getServerTimestamp()`; `:site_code` ← `DmShared.foriegnKey(DmServer.getSsnSiteCode(this))`; `:userName` ← `DmShared.xNull(DmServer.getSsnUserName(this))`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:uName` ← `DmShared.xNull(DmServer.getSsnUserName(this))`; `:u_EntDt` ← `DmServer.getServerTimestamp()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_SNo` ← `"1"`; `:v_No` ← `DmShared.iNull(mrTemp.getRecIdAssetAdd().getCounter()).toString()`; `:recIdPrefix` ← `DmShared.xNull(DmShared.xNull(mrTemp.getRecIdAssetAdd().getPrefix()))`; `:recId` ← `DmShared.xNull(mrTemp.getRecIdAssetAdd().getRecIdValue())`; `:v_Type` ← `DmShared.foriegnKey(mrTemp.getVoucherTypeCode())`; `:v_Prefix` ← `voucherPrefix`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:refNo` ← `DmShared.xNull(mrTemp.getRefNo())`; `:refDate` ← `DmServer.convertDate(mrTemp.getRefDate())`; `:assetCode` ← `DmShared.foriegnKey(mrTemp.getAssetCode())`; `:amtDr` ← `DmShared.vNull(mrTemp.getAmount()).toString()`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:isNature` ← `DmShared.iNull(mrTemp.getAssetNature().getId()).toString()`; `:typeOfExpense` ← `DmShared.iNull(mrTemp.getExpenceType().getId()).toString()`; `:assetCode` ← `DmShared.iNull(mrTemp.getAssetCode()).toString()`; `:uName` ← `DmShared.xNull(DmServer.getSsnUserName(this))`; `:u_EntDt` ← `DmServer.getServerTimestamp()`
- Errors: "Voucher Category not found." | "Voucher Date should greater or equal Asset Master Voucher Date."
- Other calls: `AssetAddRepair_Vld.chkSave`, `STServer.checkAssetCurrentDepartment`, `STServer.updateAssetCurrentDepartment`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(UserPermission userPermission)`

- SQL 1: `select astkh.docId from assetStockHead as astkh left join voucher_Type as vt on astkh.v_Type = vt.v_Type where vt.category IN ( {…} ) and astkh.site_Code = :siteCode and astkh.v_prefix = :vPrefix`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getMoveRec`
`public DmResult<AssetAddRepair_Mdl> getMoveRec(String strKey)`

- SQL 1: `select astkh.docId,aim.code as assetCode,aim.name as assetItemName,am.code as manualCode,am.manualCode as manualCodeName, alg.recId,alg.v_Date, am.currentDepartmentCode, am.assetAccessories,am.assetMake,am.assetSpecification,alg.remark,alg.isNature,alg.typeOfExpense, alg.refNo, alg.refDate,alg.amtDr as amount,astkh.preparedBy, astkh.u_Name ,astkh.v_Type,astkh.v_No,astkh.recIdPrefix from assetStockHead astkh left join assetLedger alg on alg.docId=astkh.docId left join assetMast am on am.code= alg.assetCode left join assetItemMast aim on aim.code=am.assetItemCode where astkh.docId=:docId`
- Params: `:docId` ← `strKey`
- Other calls: `AssetNature.getViaKey`, `ExpenceType.getViaKey`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select astkh.docId,aim.code as assetCode,aim.name as assetItemName,am.code as manualCode,am.manualCode as manualCodeName, alg.recId,alg.v_Date, am.assetAccessories,am.assetMake,am.assetSpecification,alg.remark,alg.isNature,alg.typeOfExpense,alg.refNo, alg.refDate,alg.amtDr as amount,astkh.preparedBy, astkh.u_Name , alg.v_No, vt.short_Name from assetStockHead astkh left join assetLedger alg on alg.docId=astkh.docId left join assetMast am on am.code= alg.assetCode left join assetItemMast aim on aim.code=am.assetItemCode left join voucher_Type as vt on astkh.v_Type = vt.v_Type where vt.category IN ( {…} ) and astkh.site_Code = :siteCode and astkh.v_prefix = :vPrefix`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey)`

- SQL 1: `Delete From assetLedger Where docId= :docId`
- SQL 2: `Delete From assetStockHead Where docId= :docId`
- Params: `:docId` ← `strKey`; `:docId` ← `strKey`

### `getTxtHAssetItem`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAssetItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select aim.code, aim.name from assetItemMast aim left join assetSiteList asl on asl.code= aim.Code where asl.site_Code = :siteCode`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`


### `getExpenseType`
`public DmResultPageing<List<KeyFieldExtended>> getExpenseType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `ExpenceType.values`

### `getNatureType`
`public DmResultPageing<List<KeyFieldExtended>> getNatureType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `ADDITION.getId`, `ADDITION.getValue`, `APPRICIATION.getId`, `APPRICIATION.getValue`, `REPAIR.getId`, `REPAIR.getValue`

### `getManualCode`
`public DmResultPageing<List<KeyFieldExtended>> getManualCode(String assetItemCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select am.code, am.manualCode, am.currentDepartmentCode, dep.name as department from assetMast am left join subGroup dep on dep.subCode = am.currentDepartmentCode where am.currentSite_code = :siteCode and am.assetItemCode = :itemCode`
- Params: `:itemCode` ← `DmShared.iNull(assetItemCode).toString()`; `:siteCode` ← `DmServer.getSsnSiteCode(this)`

### `getAutoFill`
`public DmResult<AutoFill_Mdl> getAutoFill(String assetCode)`

- SQL 1: `select am.assetRemark, am.assetAccessories, am.assetMake, am.assetSpecification, am.manualCode, astk.assetCode, aim.name assetItem, am.assetItemCode from assetStock astk left join subGroup sg on sg.subCode = astk.departmentCode left join assetMast am on am.code = astk.docId left join assetItemMast aim on aim.code = am.assetItemCode where astk.docId = :assetCode`
- Params: `:assetCode` ← `assetCode`

### `getGridProperties`
`public DmResult<List<PropertiesPoup_Mdl>> getGridProperties(String assetCode)`

- SQL 1: `select apd.propertyCode, apd.assetCode, apm.name, apd.associatedValue, apd.associatedDate, apd.lastServiceNo, apd.dueDate, apd.lastServiceAmount, apd.lastServiceDate, remark, apd.SNo , apd.nature from assetPropertiesDetail apd left join assetPropertyMast apm on apm.code = apd.propertyCode where apd.assetCode = :assetCode and apd.nature <> 0 order by apd.SNo, apm.name`
- Params: `:assetCode` ← `DmShared.xNull(assetCode)`

### `getTxtHProperties`
`public DmResultPageing<List<PropertiesPoup_Mdl>> getTxtHProperties(String assetItemCode, String assetCode, DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `select aip.propertyCode as code, apm.name,aip.SNo,apd.associatedDate,apd.associatedValue from assetItemProperties aip left join assetPropertyMast apm on apm.code = aip.propertyCode left join assetPropertiesDetail apd on apd.propertyCode = aip.propertyCode where aip.code = :assetItemCode and isnull(apd.assetCode, :assetCode) = :assetCode and aip.nature <> 0`
- Params: `:assetItemCode` ← `DmShared.xNull(assetItemCode)`; `:assetCode` ← `DmShared.xNull(assetCode)`

### `saveProperty`
`public DmResult<String> saveProperty(Boolean isDelete, PropertiesPoup_Mdl mdl)`

- Other calls: `PropertiesPopup_Server.save`

### `sendMessage`
`public DmResult<String> sendMessage(String strKey, String vTypeCode)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `paperbiz.assets.assetAddRepair.shared`, `paperbiz.assets.propertiesPopup.client`, `paperbiz.assets.propertiesPopup.server`, `paperbiz.assets.propertiesPopup.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stStatic.server`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (!rsTemp.next())` | "Voucher Category not found." |
| saveRecord | `if (DmServer.convertDate(DmShared.dNull(rsTemp.getDate("v_Date"))).getTime() > DmServer.convertDate(DmShared.dNull(mrTemp.getVoucherDate())).getTime())` | "Voucher Date should greater or equal Asset Master Voucher Date." |

## Notes (hand-written)
_No hand-written notes yet._
