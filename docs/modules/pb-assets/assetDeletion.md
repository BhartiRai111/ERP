# pb-assets / assetDeletion

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/assets/src/assetDeletion/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: assetStockHead, assetLedger
- Update tables: assetStockHead
- Delete cascade (in order): assetLedger, assetStockHead
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Asset Item (Text); Asset Name (Text); Voucher Date (Text); Nature (Text); Type of Expense (Text); Amount (Amount)
- Lookups/suggestions: getTxtHAssetItem
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `AssetDeletion`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Asset Item | DmTextHelp | `txtHAssetItem` | `KeyField_PrpAcs, 7, true` |
| 2 | Asset Name | DmTextHelp | `txtHManualCode` | `AssetCode_PrpAcs, 7, true` |
| 3 | Voucher No. | DmTextBox | `txtVrNo` | `20, false` |
| 4 | Voucher Date | DmTextBox | `txtVrDate` | `20, true` |
| 5 | Assset Make | DmRemarkTextBox | `txtAssetMake` | `250, false` |
| 6 | Accessories | DmRemarkTextBox | `txtAccessories` | `250, false` |
| 7 | Specification | DmRemarkTextBox | `txtSpecification` | `250, false` |
| 8 | Asset Remark | DmRemarkTextBox | `txtAssetRemark` | `250, false` |
| 9 | Nature | DmTextHelp | `txtHNature` | `KeyField_PrpAcs, 7, true, true` |
| 10 | Type of Expense | DmTextHelp | `txtHExpense` | `KeyField_PrpAcs, 7, true, true` |
| 11 | Ref. No. | DmTextBox | `txtRefNo` | `20, false` |
| 12 | Ref. Date | DmTextBox | `txtRefDate` | `20, false` |
| 13 | Remark | DmRemarkTextBox | `txtRemark` | `250, false` |
| 14 | Amount | DmTextBoxNumeric | `txtAmount` | `2, false, 10, true` |

Other widgets (not in tab list): `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `txtHManualCode`: Name [Text, w0, Name]; Department [Text, w100, Department]
- `txtHNature`: Name [Text, w0, Name]
- `txtHExpense`: Name [Text, w0, Name]
- `txtHAssetItem`: Name [Text, w0, Name]
- `dmFind`: Voucher No. [Text, w160, recId]; Voucher Date [Text, w100, v_Date]; Asset Name [Text, w330, manualCodeName]; Asset Item [Text, w380, assetItemName]; Ref. No [Text, w130, refNO]; Ref. Date [Text, w100, refDate]; Amount [Text, w110, amount]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHAssetItem` → `getTxtHAssetItem`
- `txtHExpense` → `getExpenseType`
- `txtHNature` → `getNatureType`
- `txtHManualCode` → `getManualCode`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `AssetItemCode` ← DmShared.iNull(txtHAssetItem.getTag()).toString()
- `AssetItemName` ← DmShared.xNull(txtHAssetItem.getText())
- `ManualCode` ← DmShared.xNull(txtHManualCode.getTag()).toString()
- `ManualCodeName` ← DmShared.xNull(txtHManualCode.getText())
- `AssetMake` ← DmShared.xNull(txtAssetMake.getText())
- `Specification` ← DmShared.xNull(txtSpecification.getText())
- `Accessories` ← DmShared.xNull(txtAccessories.getText())
- `NatureCode` ← DmShared.xNull(txtHNature.getTag())
- `NatureValue` ← DmShared.xNull(txtHNature.getText())
- `ExpenseCode` ← DmShared.xNull(txtHExpense.getTag())
- `ExpenseValue` ← DmShared.xNull(txtHExpense.getText())
- `RefNo` ← DmShared.xNull(txtRefNo.getText())
- `RefDate` ← DmShared.dNull(txtRefDate.getText())
- `Remark` ← DmShared.xNull(txtRemark.getText())
- `VoucherDate` ← txtVrDate.getText()
- `Amount` ← txtAmount.getText()

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtVrNo.setText` ← data.getSharedKeyFieldName()
- `txtVrDate.setText` ← data.getVoucherDate()
- `txtHAssetItem.setTag` ← data.getAssetItemCode()
- `txtHAssetItem.setText` ← data.getAssetItemName()
- `txtHManualCode.setTag` ← data.getManualCode()
- `txtHManualCode.setText` ← data.getManualCodeName()
- `txtAssetMake.setText` ← data.getAssetMake()
- `txtAccessories.setText` ← data.getAccessories()
- `txtSpecification.setText` ← data.getSpecification()
- `txtHNature.setTag` ← data.getNatureCode()
- `txtHNature.setText` ← data.getNatureValue()
- `txtHExpense.setTag` ← data.getExpenseCode()
- `txtHExpense.setText` ← data.getExpenseValue()
- `txtRemark.setText` ← data.getRemark()
- `txtRefNo.setText` ← data.getRefNo()
- `txtRefDate.setText` ← data.getRefDate()
- `txtAmount.setText` ← data.getAmount()
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtVrDate.setEnabled(flag)`
- `txtVrNo.setEnabled(!flag)`
- `txtAssetMake.setEnabled(!flag)`
- `txtSpecification.setEnabled(!flag)`
- `txtAccessories.setEnabled(!flag)`
- `txtAssetRemark.setEnabled(!flag)`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
if (!topControl.getMode().equalsIgnoreCase("Browse")) {
txtVrDate.setEnabled(flag);
txtVrNo.setEnabled(!flag);
txtAssetMake.setEnabled(!flag);
txtSpecification.setEnabled(!flag);
txtAccessories.setEnabled(!flag);
txtAssetRemark.setEnabled(!flag);
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
txtHAssetItem.setFocus(true);
manageDisplay(true);
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(AssetDeletion.this);
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
getAutoFillSuc(txtHManualCode.getTag());
```

**Top control options:** setOtherRequired(false), setRefreshRequired(false), setOtherRequired(true), setDisplay(true)

**Menu items (Other button):** Process Message

_Print permission "P" is removed for this screen (replace "P" with "*")._
- Confirm: "Are you sure? You want to delete this record."
- Confirm: "Are you sure? You want to Process Message."
- Alert: "Can be use in Browse Mode only and select a record."
- Alert: "Message is processed successfully!"
- Alert: "Message can be send only in Browse Mode!"

## Files
| file | lines |
|---|---|
| client/AssetDeletion.java | 625 |
| client/AssetDeletion_Dsg.java | 430 |
| server/AssetDeletion_SrvImpl.java | 741 |
| shared/AssetDeletion_Mdl.java | 198 |
| shared/AssetDeletion_Srv.java | 38 |
| shared/AssetDeletion_SrvAsync.java | 38 |
| shared/AssetDeletion_Vld.java | 19 |
| shared/DefaultValue_Mdl.java | 22 |
| shared/Find_Mdl.java | 111 |

## UI labels / columns (client)
`Asset Item`, `Asset Name`, `Voucher No.`, `Voucher Date`, `Assset Make`, `Accessories`, `Specification`, `Asset Remark`, `Nature`, `Type of Expense`, `Ref. No.`, `Ref. Date`, `Remark`, `Amount`

## Models (shared DTOs)
### DefaultValue_Mdl
| type | field |
|---|---|
| String | currentDate |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String assetItemCode, assetItemName, manualCode, manualCodeName, voucherDate, natureCode, nature, expenseCode, expense, refNo, refDate, | amount |

### AssetDeletion_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | voucherTypeCode |
| String | voucherTypeName |
| String | voucherCategory |
| String | voucherDate |
| RecId | recIdAssetAdd |

## RPC interface
| method | params | returns |
|---|---|---|
| saveRecord | AssetDeletion_Mdl mrTemp, String voucherPrefix | DmResult<String> |
| getNavigator | UserPermission userPermission | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<AssetDeletion_Mdl> |
| sendMessage | String strKey | DmResult<String> |
| getFind | UserPermission userPermission, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| deleteRecord | String strKey | DmResult<String> |
| getTxtHAssetItem | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getNatureType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getExpenseType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getManualCode | String assetItemCode, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getAutoFill | String assetCode | DmResult<KeyFieldExtended> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |

## Validator `AssetDeletion_Vld`
```java
public class AssetDeletion_Vld {
    public static void chkSave(AssetDeletion_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getAssetItemCode(), "Asset Item", 100);
        DmShared.isValidText(mrTemp.getManualCode(), "Asset Name", 200);
        DmShared.isValidText(DmShared.dNull(mrTemp.getVoucherDate()), "Voucher Date", 300);
        DmShared.isValidText(mrTemp.getNatureCode(), "Nature", 400);
        DmShared.isValidText(mrTemp.getExpenseCode(), "Type of Expense ", 500);
        DmShared.isValidAmount(mrTemp.getAmount(), "Amount", 600);
    }
}
```

## Server `AssetDeletion_SrvImpl.java` (741 lines)
Helpers used: `DmServer.checkForwardVoucherDateTime`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getRecId`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserViewCondition`, `DmServer.getVType`, `DmServer.sendMail`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `saveRecord`
`public DmResult<String> saveRecord(AssetDeletion_Mdl mrTemp, String voucherPrefix)`

- SQL 1: `select v_Type from Voucher_Type where v_Type=:v_Type`
- SQL 2: `select v_Date from assetStockHead where docId=:asseCode`
- SQL 3: `Insert Into assetStockHead(docId, v_Type,v_No, recId, v_Date, recIdPrefix, v_Prefix, u_EntDt, refNo, refDate, remark, site_Code, preparedBy, approvedBy) Values (:docId, :v_Type, :v_No, :recId, :v_Date, :recIdPrefix, :v_Prefix, :u_EntDt, :refNo, :refDate, :remark, :site_Code, :userName, :userName)`
- SQL 4: `Insert Into assetLedger(docId,v_SNo,v_No,recIdPrefix,recId,v_Type, v_Prefix,v_Date, refNo, refDate, assetCode, amtCr, remark,isNature,typeOfExpense) Values (:docId,:v_SNo,:v_No,:recIdPrefix,:recId,:v_Type,:v_Prefix,:v_Date, :refNo, :refDate, :assetCode,:amtCr,:remark, :isNature,:typeOfExpense)`
- SQL 5: `Update assetStockHead Set v_Date = :v_Date, refNo = :refNo, refDate = :refDate, u_Name = :uName, u_EntDt = :u_EntDt Where docId = :docId`
- SQL 6: `delete from assetLedger where docId = :docId`
- SQL 7: `Insert Into assetLedger(docId,v_SNo,v_No,recIdPrefix,recId,v_Type, v_Prefix,v_Date, refNo, refDate, assetCode, amtCr, remark,isNature,typeOfExpense) Values (:docId,:v_SNo,:v_No,:recIdPrefix,:recId,:v_Type,:v_Prefix,:v_Date, :refNo, :refDate, :assetCode,:amtCr,:remark, :isNature,:typeOfExpense)`
- Params: `:v_Type` ← `DmShared.iNull(v_Type).toString()`; `:asseCode` ← `DmShared.iNull(mrTemp.getManualCode()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_Type` ← `v_Type`; `:v_No` ← `DmShared.iNull(mrTemp.getRecIdAssetAdd().getCounter()).toString()`; `:recId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:recIdPrefix` ← `DmShared.xNull(DmShared.xNull(mrTemp.getRecIdAssetAdd().getPrefix()))`; `:v_Prefix` ← `voucherPrefix`; `:refNo` ← `DmShared.xNull(mrTemp.getRefNo())`; `:refDate` ← `DmServer.convertDate(mrTemp.getRefDate())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:u_EntDt` ← `DmServer.getServerTimestamp()`; `:site_code` ← `DmShared.foriegnKey(DmServer.getSsnSiteCode(this))`; `:userName` ← `DmShared.xNull(DmServer.getSsnUserName(this))`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_SNo` ← `"1"`; `:v_No` ← `DmShared.iNull(mrTemp.getRecIdAssetAdd().getCounter()).toString()`; `:recIdPrefix` ← `DmShared.xNull(DmShared.xNull(mrTemp.getRecIdAssetAdd().getPrefix()))`; `:recId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:v_Type` ← `DmShared.foriegnKey(v_Type)`; `:v_Prefix` ← `voucherPrefix`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:refNo` ← `DmShared.xNull(mrTemp.getRefNo())`; `:refDate` ← `DmServer.convertDate(mrTemp.getRefDate())`; `:assetCode` ← `DmShared.foriegnKey(mrTemp.getManualCode())`; `:amtCr` ← `DmShared.vNull(mrTemp.getAmount()).toString()`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:isNature` ← `DmShared.iNull(mrTemp.getNatureCode()).toString()`; `:typeOfExpense` ← `DmShared.iNull(mrTemp.getExpenseCode()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:refNo` ← `DmShared.xNull(mrTemp.getRefNo())`; `:refDate` ← `DmServer.convertDate(mrTemp.getRefDate())`; `:uName` ← `DmShared.xNull(DmServer.getSsnUserName(this))`; `:u_EntDt` ← `DmServer.getServerTimestamp()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_SNo` ← `"1"`; `:v_No` ← `DmShared.iNull(mrTemp.getRecIdAssetAdd().getCounter()).toString()`; `:recIdPrefix` ← `DmShared.xNull(DmShared.xNull(mrTemp.getRecIdAssetAdd().getPrefix()))`; `:recId` ← `DmShared.xNull(mrTemp.getRecIdAssetAdd().getRecIdValue())`; `:v_Type` ← `DmShared.foriegnKey(v_Type)`; `:v_Prefix` ← `voucherPrefix`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:refNo` ← `DmShared.xNull(mrTemp.getRefNo())`; `:refDate` ← `DmServer.convertDate(mrTemp.getRefDate())`; `:assetCode` ← `DmShared.foriegnKey(mrTemp.getManualCode())`; `:amtCr` ← `DmShared.vNull(mrTemp.getAmount()).toString()`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:isNature` ← `DmShared.iNull(mrTemp.getNatureCode()).toString()`; `:typeOfExpense` ← `DmShared.iNull(mrTemp.getExpenseCode()).toString()`
- Errors: "Voucher Category not found." | "Voucher Date should greater Asset Master Voucher Date."
- Other calls: `AssetDeletion_Vld.chkSave`, `STServer.checkAssetCurrentDepartment`, `STServer.updateAssetCurrentDepartment`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(UserPermission userPermission)`

- SQL 1: `select astkh.docId from assetStockHead as astkh left join voucher_Type as vt on astkh.v_Type = vt.v_Type where vt.category IN (' {…} ') and astkh.site_Code = :siteCode and astkh.v_prefix = :vPrefix`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getMoveRec`
`public DmResult<AssetDeletion_Mdl> getMoveRec(String strKey)`

- SQL 1: `select astkh.docId,aim.code as assetCode,aim.name as assetItemName,am.code as manualCode,am.manualCode as manualCodeName, alg.recId,alg.v_Date, am.assetAccessories,am.assetMake,am.assetSpecification,alg.remark,alg.isNature,alg.typeOfExpense,alg.refNo, alg.refDate,alg.amtCr as amount,astkh.preparedBy, astkh.u_Name, astkh.v_Type,astkh.v_No,astkh.recIdPrefix from assetStockHead astkh left join assetLedger alg on alg.docId=astkh.docId left join assetMast am on am.code= alg.assetCode left join assetItemMast aim on aim.code=am.assetItemCode where astkh.docId=:docId`
- Params: `:docId` ← `strKey`
- Other calls: `AssetNature.getViaKey`, `ExpenceType.getViaKey`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select astkh.docId,aim.code as assetCode,aim.name as assetItemName,am.code as manualCode,am.manualCode as manualCodeName, alg.recId,alg.v_Date, am.assetAccessories,am.assetMake,am.assetSpecification,alg.remark,alg.isNature,alg.typeOfExpense,alg.refNo, alg.refDate,alg.amtCr as amount,astkh.preparedBy, astkh.u_Name , alg.v_No,vt.short_Name from assetStockHead astkh left join assetLedger alg on alg.docId=astkh.docId left join assetMast am on am.code= alg.assetCode left join assetItemMast aim on aim.code=am.assetItemCode left join voucher_Type as vt on astkh.v_Type = vt.v_Type where vt.category IN (' {…} ') and astkh.site_Code = :siteCode and astkh.v_prefix = :vPrefix`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey)`

- SQL 1: `Delete From assetLedger Where docId= :docId`
- SQL 2: `Delete From assetStockHead Where docId= :docId`
- Params: `:docId` ← `strKey`; `:docId` ← `strKey`

### `getTxtHAssetItem`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAssetItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select aim.code, aim.name from assetItemMast aim left join assetSiteList asl on asl.code = aim.code where asl.site_Code = :siteCode`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`


### `getExpenseType`
`public DmResultPageing<List<KeyFieldExtended>> getExpenseType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `ExpenceType.values`

### `getNatureType`
`public DmResultPageing<List<KeyFieldExtended>> getNatureType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `DELETION.getId`, `DELETION.getValue`, `DEPRICIATION.getId`, `DEPRICIATION.getValue`, `IMPAIRMENT.getId`, `IMPAIRMENT.getValue`

### `getManualCode`
`public DmResultPageing<List<KeyFieldExtended>> getManualCode(String assetItemCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select am.code, am.manualCode as name, astk.department from assetMast am left join assetItemMast aim on aim.code = am.assetItemCode left join (select astk.assetCode, max(dep.name) as department from assetStock astk left join subGroup dep on dep.subCode = astk.departmentCode where astk.site_Code = :siteCode group by astk.assetCode, astk.departmentCode having (isNull(sum(astk.recQty),0) - isNull(sum(astk.isuQty),0)) > 0 ) astk on astk.assetCode = am.code where am.assetItemCode=:assetItemCode`
- Params: `:assetItemCode` ← `DmShared.iNull(assetItemCode).toString()`; `:siteCode` ← `DmServer.getSsnSiteCode(this)`

### `getAutoFill`
`public DmResult<KeyFieldExtended> getAutoFill(String assetCode)`

- SQL 1: `select am.assetRemark,am.assetAccessories,am.assetMake,am.assetSpecification from assetStock astk left join subGroup sg on sg.subCode=astk.departmentCode left join assetMast am on am.code=astk.docId where astk.docId=:assetCode`
- Params: `:assetCode` ← `assetCode`

### `sendMessage`
`public DmResult<String> sendMessage(String strKey)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `paperbiz.assets.assetDeletion.shared`, `paperbiz.assets.self.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stStatic.server`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (!rsTemp.next())` | "Voucher Category not found." |
| saveRecord | `if (DmServer.convertDate(DmShared.dNull(rsTemp.getDate("v_Date"))).getTime() > DmServer.convertDate(DmShared.dNull(mrTemp.getVoucherDate())).getTime())` | "Voucher Date should greater Asset Master Voucher Date." |

## Notes (hand-written)
_No hand-written notes yet._
