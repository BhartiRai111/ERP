# dm-accounts / tdsTcsEntry

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/accounts/src/tdsTcsEntry/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: tdsTrans, LedgerM, ledger, Ledger
- Update tables: LedgerM
- Delete cascade (in order): ledger, ledgerM
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: rule:  Type is mandatory.; rule: Transaction is mandatory.; rule: Transaction can not be '; rule: Party is mandatory.; rule: Category is mandatory.; rule: On Amount is mandatory.; rule: GridRow
- Lookups/suggestions: getTxtHType, getTxtHTransaction, getTxtHTdsCategory, getTxtHParty
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `TdsTcsEntry`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Type | DmTextHelp | `txtHType` | `KeyField_PrpAcs, 3, true` |
| 2 | Transaction | DmTextHelp | `txtHTransaction` | `KeyField_PrpAcs, 3, true` |
| 3 | Date | DmTextBox | `txtVoucherDate` | `` |
| 4 | Party | DmTextHelp | `txtHParty` | `TxtHTdsDeduct, 8, true` |
| 5 | Category | DmTextHelp | `txtHTdsCategory` | `KeyField_PrpAcs, 7, true` |
| 6 | PAN | DmTextBox | `txtPan` | `` |
| 7 | On Amount | DmTextBoxNumeric | `txtTdsOnAmount` | `2, false, 15, true` |
| 8 | Remark | DmTextBox | `txtRemark` | `250, false` |
| 9 | - | DmTextBox | `txtAdjustment` | `` |

Other widgets (not in tab list): `txtTan` (DmTextBox), `txtVoucherNo` (DmTextBox), `txtVoucherType` (DmTextBox), `txtWTACode` (DmTextBox), `tdsCategoryGrid` (DmGrid), `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `txtHTdsCategory`: Name [Text, w1, Name]
- `txtHTransaction`: Transaction [Text, w200, name]
- `txtHType`: Type [Text, w200, name]
- `txtHParty`: Name [Text, w1, Name]; City [Text, w100, CityName]; Manual Code [Text, w100, ManualCode]; Group Name [Text, w100, GroupName]
- `tdsCategoryGrid`: Description [Text, w100]; Posting A/c [Text, w300]; Percentage(%) [Text, w120]; Amount [Text, w120]
- `dmFind`: Voucher No. [Text, w125, Voucher_No]; Voucher Type [Text, w90, short_Name]; Voucher Date [Text, w80, replace(convert(varchar, V_Date, 106), ' ','-')]; Type [Text, w50, tdsTcsType]; Transaction [Text, w70, tdsFor]; Party [Text, w235, partyName]; Category [Text, w235, category]; On Amount [Text, w85, tdsOnAmt]; Total Amount [Text, w85, tdsAmt]; Posted By [Text, w190, statusBy]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHTdsCategory` → `getTxtHTdsCategory`
- `txtHParty` → `getTxtHParty`
- `txtHTransaction` → `getTxtHTransaction`
- `txtHType` → `getTxtHType`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `VoucherPrefix` ← DmShared.xNull(txtVoucherNo.getTag())
- `VoucherTypeCode` ← DmShared.xNull(txtVoucherType.getTag())
- `VoucherTypeName` ← DmShared.xNull(txtVoucherType.getText())
- `VoucherDate` ← DmShared.dNull(txtVoucherDate.getText())
- `TdsDeductFromName` ← DmShared.xNull(txtHParty.getText())
- `TdsDeductFromCode` ← DmShared.xNull(txtHParty.getTag())
- `TypeCode` ← DmShared.xNull(txtHType.getTag())
- `TypeName` ← DmShared.xNull(txtHType.getText())
- `TdsCategoryName` ← DmShared.xNull(txtHTdsCategory.getText())
- `TdsCategoryCode` ← DmShared.xNull(txtHTdsCategory.getTag())
- `TransactionCode` ← DmShared.xNull(txtHTransaction.getTag())
- `TransactionName` ← DmShared.xNull(txtHTransaction.getText())
- `Pan` ← DmShared.xNull(txtPan.getText())
- `Tan` ← DmShared.xNull(txtTan.getText())
- `Remark` ← DmShared.xNull(txtRemark.getText())
- `SharedKeyFieldName` ← DmShared.xNull(txtVoucherNo.getText())
- `WtaCode` ← txtWTACode.getText()
- `TdsOnAmount` ← DmClient.vNull(txtTdsOnAmount.getText(), "0.00")
- `TotalAmount` ← lblTotalTds.getText()

**Save: model field ← widget** (`topControlSave`):
- `NewVoucherTypeCode` ← txtVoucherType.getTag()
- `NewVoucherTypeName` ← txtVoucherType.getText()
- `NewVoucherDate` ← txtVoucherDate.getText()
- `NewTypeCode` ← txtHType.getTag()
- `NewTypeName` ← txtHType.getText()
- `NewTransactionCode` ← txtHTransaction.getTag()
- `NewTransactionName` ← txtHTransaction.getText()

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `userControl.setTextBlog` ← preparedByBlog, mrTemp.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, mrTemp.getSharedModifiedBy()
- `userControl.setTextBlog` ← approvedByBlog, mrTemp.getApprovedBy()
- `txtVoucherType.setTag` ← mrTemp.getVoucherTypeCode()
- `txtVoucherType.setText` ← mrTemp.getVoucherTypeName()
- `txtWTACode.setText` ← DmShared.xNull(mrTemp.getWtaCode())
- `txtVoucherNo.setText` ← mrTemp.getSharedKeyFieldName()
- `txtVoucherNo.setTag` ← mrTemp.getVoucherPrefix()
- `txtVoucherNo.setTag2nd` ← DmShared.xNull(mrTemp.getObjRecId().getPrefix())
- `txtVoucherDate.setTag` ← mrTemp.getVoucherDate()
- `txtVoucherDate.setText` ← mrTemp.getVoucherDate()
- `txtVoucherDate.setTag` ← DmShared.iNull(mrTemp.getObjRecId().getCounter()).toString()
- `txtHTransaction.setTag` ← mrTemp.getTransactionCode()
- `txtHTransaction.setText` ← mrTemp.getTransactionName()
- `txtHType.setTag` ← mrTemp.getTypeCode()
- `txtHType.setTag2nd` ← mrTemp.getTypeCode()
- `txtHType.setText` ← mrTemp.getTypeName()
- `txtPan.setText` ← mrTemp.getPan()
- `txtTan.setText` ← mrTemp.getTan()
- `txtRemark.setText` ← mrTemp.getRemark()
- `txtHTdsCategory.setTag` ← mrTemp.getTdsCategoryCode()
- `txtHTdsCategory.setTag2nd` ← mrTemp.getTdsCategoryCode()
- `txtHTdsCategory.setText` ← mrTemp.getTdsCategoryName()
- `txtHParty.setTag` ← mrTemp.getTdsDeductFromCode()
- `txtHParty.setText` ← mrTemp.getTdsDeductFromName()
- `txtTdsOnAmount.setText` ← mrTemp.getTdsOnAmount()
- `lblTotalTds.setText` ← mrTemp.getTotalAmount()
- userControl.setTextBlog(preparedByBlog, mrTemp.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, mrTemp.getSharedModifiedBy())
- userControl.setTextBlog(approvedByBlog, mrTemp.getApprovedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtVoucherNo.setEnabled(false)`
- `txtVoucherDate.setEnabled(isDateEditable)`
- `txtPan.setEnabled(false)`
- `txtAdjustment.setEnabled(false)`

**On New click:**
- `clear()`
- `onAdd()`
- `txtHType.setFocus(true)`

**`manageDisplay()` logic (condensed code):**
```java
txtVoucherNo.setEnabled(false);
if (flag) {
txtVoucherDate.setEnabled(isDateEditable);
txtPan.setEnabled(false);
txtAdjustment.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
clear();
txtHType.setFocus(true);
onAdd();
```

`topControl.addEditHandeller` → `editClicked()`
```java
if (!DmShared.xNull(userControl.getTextBlog(approvedByBlog)).equalsIgnoreCase("")) {
topControl.btnClick("");
new DmAlertMessageBox("Record is approved. You can't modify this record.", (DmTextBox) null).show();
} else {
onEdit();
```

`topControl.addDeleteHandeller` → `deleteClicked()`
```java
if (!DmShared.xNull(userControl.getTextBlog(approvedByBlog)).equalsIgnoreCase("")) {
topControl.btnClick("");
new DmAlertMessageBox("Record is posted. You can't Delete this record.", (DmTextBox) null).show();
} else {
new DmConfirmMessageBox("Are you sure? You want to Delete this record.", new DialogHideEvent.DialogHideHandler() {
public void onDialogHide(DialogHideEvent event) {
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
showModifyReason();
}).show();
```

`topControl.addDeleteHandeller` → `onDialogHide()`
```java
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
showModifyReason();
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

`topControl.addPrintHandeller` → `printClicked()`
```java
prnRecord(false);
```

`topControl.addSaveHandeller` → `saveClicked()`
```java
if (DmShared.xNull(topControl.getMode()).equalsIgnoreCase("Edit")) {
showModifyReason();
} else {
topControlSave();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(TdsTcsEntry.this);
```

`txtHTdsCategory.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHTdsCategory(txtHType.getTag(), helper, blnToEndOfFile, new AsyncCallback<DmRe
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHTdsCategory.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`txtHTdsCategory.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHTdsCategory.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`txtHTdsCategory.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHTdsCategory.getTag()).equalsIgnoreCase(txtHTdsCategory.getTag2nd())) {
resetTdsTcsCategory();
txtHTdsCategory.setTag2nd(txtHTdsCategory.getTag());
calculate();
if (blnStatus && tRow != null) {
tdsCategory_TypeCode = DmShared.xNull(tRow.getSharedPreparedBy());
fillTdsGridData(txtHTdsCategory.getTag(), txtVoucherDate.getText());
```

`txtHParty.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHParty(txtVoucherType.getTag(), helper, blnToEndOfFile, new AsyncCallback<DmRe
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<TxtHTdsDeductFrom_Mdl>> result) {
if (result.isStatus()) {
txtHParty.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`txtHParty.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHParty.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`txtHParty.addTextHelpCallBack` → `getSelectedRow()`
```java
txtPan.setText(tRow.getPan());
if (blnStatus && tRow != null) {
getPartyTdsTcsAcBalance((topControl.getMode().equalsIgnoreCase("New") ? "" : topControl.getDocId()), DmShared.xNull(txtH
```

`txtHTransaction.addTextHelpCallBack` → `populateData()`
```java
String restrictForCode = "";
if (DmShared.xNull(txtHType.getTag()).equalsIgnoreCase(DmEnum.TDS_TCS_Type.TCS.getCode())) {
if (dvm.isTcsAutoCalculate()) {
restrictForCode = DmEnum.TDS_For.PAYABLE.getCode();
srvAsync.invokeRPC(blnCallBackWait).getTxtHTransaction(restrictForCode, !DmShared.xNull(txtHType.getTag()).trim().equals
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHTransaction).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHTransaction.setData(result.getData(), result.getPaging());
...
```

`txtHTransaction.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHTransaction.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTransaction).show();
```

`txtHTransaction.addTextHelpCallBack` → `getSelectedRow()`
```java
calculate();
```

`txtHType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHType).show();
```

`txtHType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHType).show();
```

`txtHType.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHType.getTag()).equalsIgnoreCase(DmShared.xNull(txtHType.getTag2nd()))) {
resetTdsTcsType();
txtHType.setTag2nd(txtHType.getTag());
calculate();
} else if (!DmShared.xNull(txtHType.getTag()).equalsIgnoreCase(DmShared.xNull(tdsCategory_TypeCode))) {
tdsCategoryGrid.getGrid().getStore().clear();
calculate();
if (blnStatus & t1Row != null) {
```

`dmFind.addHelpGridCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getFind(topControl.getPermissions(), helper, blnToEndOfFile, new AsyncCallback<DmRes
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<Find_Mdl>> result) {
if (result.isStatus()) {
dmFind.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`dmFind.addHelpGridCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
dmFind.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`dmFind.addHelpGridCallBack` → `getSelectedRow()`
```java
flwForm.remove(dmFind);
flwForm.add(vrtInnerForm);
if (blnStatus) {
topControlMoveRec(tRow.getSharedKeyFieldCode());
```

`modifyReason.addCallBack` → `onOk()`
```java
try {
TdsTcsEntry.this.modifyReasonMdl = modifyReason;
if (topControl.getMode().equalsIgnoreCase("Browse") && DmShared.iNull(topControl.getDocId()).longValue() > 0) {
topControlDelete(topControl.getDocId());
} else {
topControlSave();
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), (DmTextBox) null).show();
```

`walkThrough.addCallBack` → `onClose()`
```java
topControlMoveRec(topControl.getDocId());
topControlCard();
```

`adjustmentPopup.addCallBack` → `onOk()`
```java
TdsTcsEntry.this.lstAdjustments = lstAdjustments;
double totalAdjustment = 0;
if (lstAdjustments != null) {
for (GrdAdjustment_Mdl gaMdl : lstAdjustments) {
totalAdjustment += DmShared.vNull(gaMdl.getAdjustment());
txtAdjustment.setText(DmClient.vNull(totalAdjustment, "0.00"));
txtAdjustment.setFocus(true);
```

`adjustmentPopup.addCallBack` → `onClose()`
```java
txtAdjustment.setFocus(true);
```

**Top control options:** setOtherRequired(false), setRefreshRequired(false), setDisplay(true)

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to Delete this record."
- Alert: "Record is approved. You can't modify this record."
- Alert: "Record is posted. You can't Delete this record."

## Files
| file | lines |
|---|---|
| client/TdsTcsEntry.java | 1160 |
| client/TdsTcsEntry_Dsg.java | 580 |
| server/TdsTcsEntry_Server.java | 230 |
| server/TdsTcsEntry_SrvImpl.java | 1643 |
| shared/DefaultValue_Mdl.java | 129 |
| shared/Find_Mdl.java | 95 |
| shared/GrdTdsDetail_Mdl.java | 131 |
| shared/TdsTcsEntry_Mdl.java | 316 |
| shared/TdsTcsEntry_Srv.java | 44 |
| shared/TdsTcsEntry_SrvAsync.java | 43 |
| shared/TdsTcsEntry_Vld.java | 46 |
| shared/TxtHTdsDeductFrom_Mdl.java | 59 |

## UI labels / columns (client)
`Dr 0.00`, `F.Y Dr. 0.00`, `F.Y Cr. 0.00`, `TCS Amt. 0.00`, `TDS Amt. 0.00`, `Type`, `Transaction`, `Date`, `Voucher No.`, `Party`, `Category`, `PAN`, `On Amount`, `Remark`, `Total`

## Models (shared DTOs)
### DefaultValue_Mdl
| type | field |
|---|---|
| String | voucherDate |
| String | newVoucherCategory |
| String | newVoucherTypeCode |
| String | newVoucherTypeName |
| String | newVoucherDate |
| String | newTypeCode |
| String | newTypeName |
| String | newTransactionCode |
| String | newTransactionName |
| String | voucherTypeCode |
| String | voucherTypeName |
| boolean | continuousNewMode |
| boolean | tcsAutoCalculate |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | v_Date |
| String | voucherType |
| String | type |
| String | transaction |
| String | party |
| String | category |
| String | onAmount |
| String | totalAmount |
| String | statusBy |

### TdsTcsEntry_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| RecId | objRecId |
| String | VoucherPrefix |
| String | tdsCategoryCode |
| String | tdsCategoryName |
| String | tdsOnAmount |
| String | tdsDeductFromCode |
| String | tdsDeductFromName |
| String | totalAmount |
| String | tan |
| String | pan |
| String | remark |
| String | approvedBy |
| String | modifiedBy |
| String | preparedBy |
| String | u_EntDt_LatestLine |
| String | wtaCode |
| String | typeCode |
| String | typeName |
| String | transactionCode |
| String | transactionName |
| String | voucherTypeCode |
| String | voucherTypeName |
| String | voucherDate |
| ModifyReason_Mdl | modifyReasonMdl |
| List<GrdTdsDetail_Mdl> | tdsGridList |
| List<GrdAdjustment_Mdl> | lstAdjustments |
| DefaultValue_Mdl | dvm |

### TxtHTdsDeductFrom_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | city |
| String | sCode |
| String | tan |
| String | pan |
| String | groupName |

### GrdTdsDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | description |
| String | descriptionCode |
| String | descriptionMCode |
| String | postingAccount |
| String | postingAccountCode |
| String | percent |
| String | amount |
| String | formula |
| String | roundOffTDS |
| String | vSNo |
| String | calcOnValue |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHTdsCategory | String tdsFor, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTDSData | String tdsCategoryCode, String date | DmResult<List<GrdTdsDetail_Mdl>> |
| getTxtHParty | String vCat,DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHTdsDeductFrom_Mdl>> |
| getPartyTdsTcsAcBalance | String docid, String partyCode, String partyPan | DmResult<PartyTdsTcsAcBalance_Mdl> |
| getNavigator | UserPermission userPermission, String voucherCategory | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<TdsTcsEntry_Mdl> |
| deleteRecord | String strKey, ModifyReason_Mdl modifyReasonMdl | DmResult<String> |
| saveRecord | TdsTcsEntry_Mdl mrTemp,boolean isAutoApprove | DmResult<String> |
| getTxtHTransaction | String restrictForCode, boolean showHelp, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getFind | UserPermission userPermission,DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| getServerDateTime |  | DmResult<String[]> |
| setApproval | String approvedBy, boolean isApprove, String docId | DmResult<String> |
| printRecords | String docId | DmResult<String> |

## Validator `TdsTcsEntry_Vld`
```java
public class TdsTcsEntry_Vld {
    public static void chkSave(TdsTcsEntry_Mdl mrTemp) {
        if (DmShared.xNull(mrTemp.getTypeCode()).trim().equalsIgnoreCase("")) {
            throw new DmException(100, " Type is mandatory.");
        }
        if (DmShared.xNull(mrTemp.getTransactionCode()).trim().equalsIgnoreCase("")) {
            throw new DmException(200, "Transaction is mandatory.");
        } else {
            if (mrTemp.getDvm().isTcsAutoCalculate()) {
                if (DmShared.xNull(mrTemp.getTypeCode()).equalsIgnoreCase(DmEnum.TDS_TCS_Type.TCS.getCode())
                        && DmShared.xNull(mrTemp.getTransactionCode()).equalsIgnoreCase(DmEnum.TDS_For.PAYABLE.getCode())) {
                    throw new DmException(200, "Transaction can not be '" + DmEnum.TDS_For.PAYABLE.getName() + "'.");
                }
            }
        }
        if (DmShared.xNull(mrTemp.getTdsDeductFromCode()).trim().equalsIgnoreCase("")) {
            throw new DmException(300, "Party is mandatory.");
        }
        if (DmShared.xNull(mrTemp.getTdsCategoryCode()).trim().equalsIgnoreCase("")) {
            throw new DmException(400, "Category is mandatory.");
        }
        if (DmShared.vNull(mrTemp.getTdsOnAmount()).doubleValue() <= 0) {
            throw new DmException(500, "On Amount is mandatory.");
        }
        List<GrdTdsDetail_Mdl> listTdsTcsDetail = mrTemp.getTdsGridList();
        for (GrdTdsDetail_Mdl detail_Mdl : listTdsTcsDetail) {
            if (!DmShared.xNull(detail_Mdl.getDescriptionCode()).trim().equalsIgnoreCase("")) {
                if (DmShared.xNull(detail_Mdl.getPostingAccountCode()).equalsIgnoreCase("")) {
                    throw new DmException(600, "GridRow", DmShared.iNull(listTdsTcsDetail.indexOf(detail_Mdl)).toString(), "Posting account is required in line no. " + DmShared.iNull(listTdsTcsDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
            }
        }
    }
}
```

## Server `TdsTcsEntry_Server.java` (230 lines)
Helpers used: `DmServer.convertDate`, `DmServer.getServerTimestamp`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getTDSData`
`public static List<GrdTdsDetail_Mdl> getTDSData(String tdsCategoryCode, String date, Connection cnTemp) throws Exception`

- SQL 1: `SELECT ec.ac_RoundOffTDS FROM enviroCompany ec`
- SQL 2: `Select TC.TDSDesc,tcd.manualCode as tcdManualCode,TCD.Name As DName,TC.ACCode, SG.Name As AName,TC.Percentage ,TC.FormulaString From TDSCat_Det TC Left Join TDSCat_Description TCD On TC.TDSDesc=TCD.Code Left Join SubGroup SG On TC.AcCode=SG.SubCode Where TC.Code=:code {…} Order By TC.SrNo`
- Params: `:code` ← `DmShared.iNull(tdsCategoryCode).toString()`; `:one` ← `1`; `:two` ← `2`; `:date` ← `DmServer.convertDate(date)`

### `saveTdsTrans`
`public static void saveTdsTrans(TdsTcsEntry_Mdl tpm, Connection cnTemp, DmRSSPrivate rss) throws Exception`


### `saveTdsTrans`
`public static void saveTdsTrans(TdsTcsEntry_Mdl tpm, Connection cnTemp, DmRSSPrivate rss, boolean isTdsReturn) throws Exception`

- SQL 1: `Insert Into tdsTrans (docId, recId, recIdPrefix , v_No, v_SNo , v_Date, v_Prefix , v_Type, site_Code , pan, tan , vr_SubCode, subCode , tdsFor , type , tdsCategory, tdsOnAmt ,tdsDeductFrom , tdsDesc ,tdsPer , formulaString,roundOffTds , amount, calcOnValue ,u_Name,u_EntDt,isStatus) values ( :docId,:recId, :recIdPrefix , :v_No, :v_SNo,:v_Date, :v_Prefix,:v_Type,:site_Code , :pan , :tan , :vr_SubCode , :subCode , :tdsFor , :type , :tdsCategory ,:tdsOnAmt , :tdsDeductFrom,:tdsDesc, :tdsPer , :formulaString , :roundOffTds , :amount, :calcOnValue,:u_Name,:u_EntDt,:isStatus )`
- Params: `:docId` ← `DmShared.foriegnKey(tpm.getSharedKeyFieldCode())`; `:v_SNo` ← `DmShared.foriegnKey(DmShared.iNull(++vSno).toString())`; `:v_No` ← `DmShared.iNull(tpm.getObjRecId().getCounter()).toString()`; `:recIdPrefix` ← `DmShared.xNull(DmShared.xNull(tpm.getObjRecId().getPrefix()))`; `:recId` ← `DmShared.xNull(tpm.getObjRecId().getRecIdValue())`; `:v_Prefix` ← `DmShared.xNull(tpm.getVoucherPrefix())`; `:v_Type` ← `DmShared.foriegnKey(tpm.getVoucherTypeCode())`; `:v_Date` ← `DmServer.convertDate(DmShared.dNull(tpm.getVoucherDate()))`; `:site_Code` ← `DmShared.foriegnKey(DmServer.getSsnSiteCode(rss))`; `:vr_SubCode` ← `DmShared.foriegnKey(tpm.getTdsDeductFromCode())`; `:subCode` ← `DmShared.foriegnKey(tdsDet_Mdl.getPostingAccountCode())`; `:tan` ← `DmShared.xNull(tpm.getTan())`; `:pan` ← `DmShared.xNull(tpm.getPan())`; `:tdsFor` ← `DmShared.foriegnKey(tpm.getTransactionCode())`; `:type` ← `DmShared.foriegnKey(tpm.getTypeCode())`; `:tdsCategory` ← `DmShared.foriegnKey(tpm.getTdsCategoryCode())`; `:tdsDeductFrom` ← `DmShared.foriegnKey(tpm.getTdsDeductFromCode())`; `:tdsDesc` ← `DmShared.foriegnKey(tdsDet_Mdl.getDescriptionCode())`; `:tdsPer` ← `DmServer.vNull(tdsDet_Mdl.getPercent())`; `:formulaString` ← `DmShared.xNull(tdsDet_Mdl.getFormula())`; `:roundOffTds` ← `DmShared.xNull(tdsDet_Mdl.getRoundOffTDS())`; `:u_Name` ← `DmServer.getSsnUserName(rss)`; `:isStatus` ← `1`; `:u_EntDt` ← `DmServer.getServerTimestamp()`; `:tdsOnAmt` ← `DmServer.vNull(0 - DmShared.vNull(tpm.getTdsOnAmount()).doubleValue(), "0.00")`; `:calcOnValue` ← `DmServer.vNull(0 - DmShared.vNull(tdsDet_Mdl.getCalcOnValue()).doubleValue(), "0.00")`; `:amount` ← `DmServer.vNull(0 - DmShared.vNull(tdsDet_Mdl.getAmount()).doubleValue(), "0.00")`; `:tdsOnAmt` ← `DmServer.vNull(tpm.getTdsOnAmount(), "0.00")`; `:calcOnValue` ← `DmServer.vNull(tdsDet_Mdl.getCalcOnValue(), "0.00")`; `:amount` ← `DmServer.vNull(tdsDet_Mdl.getAmount(), "0.00")`
- Errors: "Error in TDS record saving."

### `deleteTdsTrans`
`public static void deleteTdsTrans(String docid, Connection cnTemp) throws Exception`

- SQL 1: `Delete From tdsTrans Where DocId= :DocId`
- Params: `:DocId` ← `DmShared.xNull(docid)`

## Server `TdsTcsEntry_SrvImpl.java` (1643 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.checkForwardVoucherDateTime`, `DmServer.checkTransactionLockDate`, `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getLocalIPAddresses`, `DmServer.getPartyTdsTcsAcBalance`, `DmServer.getRecId`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnEndDate`, `DmServer.getSsnOldData`, `DmServer.getSsnPLTCode`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSitePAN`, `DmServer.getSsnStartDate`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserViewCondition`, `DmServer.getUserWithDateTime`, `DmServer.prepareContraText`, `DmServer.setSsnOldData`, `DmServer.showJasper`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getTxtHType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `TDS_TCS_Type.values`

### `getTxtHTransaction`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTransaction(String restrictForCode, boolean showHelp, DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `TDS_For.values`

### `getTxtHTdsCategory`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTdsCategory(String tdsFor, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select tc.code, tc.name From tdsCat tc WHERE tc.type = :tdsFor`
- Params: `:tdsFor` ← `DmShared.xNull(tdsFor)`

### `getTDSData`
`public DmResult<List<GrdTdsDetail_Mdl>> getTDSData(String tdsCategoryCode, String date)`

- Other calls: `TdsTcsEntry_Server.getTDSData`

### `getTxtHParty`
`public DmResultPageing<List<TxtHTdsDeductFrom_Mdl>> getTxtHParty(String vType, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select SG.SubCode, SG.Name, isNull(CT.CityName,'') as CityName, SG.ManualCode , sg.tan , sg.pan , ag.groupName From SubGroup SG Left Join City CT On CT.CityCode=SG.CityCode LEFT JOIN subgroupsitelist SGL ON SG.subCode=SGL.subCode LEFT JOIN acGroup ag ON ag.groupCode = sg.groupCode Where SGL.site_Code=' {…} ' AND sg.nature IN ( SELECT acf.Nature FROM AcFilteration acf WHERE acf.v_Type = :v_Type )`
- Params: `:v_Type` ← `DmShared.iNull(vType).toString()`

### `getPartyTdsTcsAcBalance`
`public DmResult<PartyTdsTcsAcBalance_Mdl> getPartyTdsTcsAcBalance(String docid, String partyCode, String partyPan)`


### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(UserPermission userPermission, String voucherCategory)`

- SQL 1: `SELECT lm.DocId AS SearchCode From ledgerM lm LEFT JOIN Voucher_Type vt ON lm.V_Type = vt.V_Type WHERE lm.Site_Code = ' {…} ' AND lm.V_Prefix = ' {…} ' AND ISNULL(lm.cancelledBy, '') = '' {…}`

### `getMoveRec`
`public DmResult<TdsTcsEntry_Mdl> getMoveRec(String strKey)`


### `getMoveRec`
`private TdsTcsEntry_Mdl getMoveRec(String strKey, Connection cnTemp) throws Exception`

- SQL 1: `SELECT lm.DocId , lm.V_Type , lm.Site_Code , lm.V_Date , lm.SubCode , sg.Name AS AcName , lm.PostedBy , lm.RecId , lm.v_No , tc.name AS tdsCategoryName , lm.tdsCategory , lm.recIdPrefix , lm.V_Prefix , lm.U_Name , lm.remark , lm.Narration , vt.Description , vt.Category , lm.PreparedBy , lm.preparedByIP , lm.preparedDt , lm.u_EntDt , sgn.manualCode AS SGNature , lm.wtaCode , lm.u_EntDt_LatestLine , lm.cancelledBy , lm.cancelledDt , lm.tdsTcsType , lm.tdsFor ,lm.tdsDeductFrom, sg.name AS tdsDeductName , lm.tdsOnAmt ,lm.tdsAmt, lm.pan, lm.tan From ledgerM lm LEFT JOIN SubGroup sg ON sg.SubCode = lm.SubCode LEFT JOIN Voucher_Type vt ON vt.V_Type = lm.v_Type LEFT JOIN subGroupNature as sgn on sg.nature = sgn.code LEFT JOIN gstReasonMast grm ON grm.code = lm.gstReasonCode LEFT JOIN stateMast sm ON sm.code = lm.placeOfSupply LEFT JOIN ledger l ON l.docId = lm.refDocId AND l.v_SNo = lm.refV_SNo LEFT JOIN tdsCat tc ON tc.code = lm.tdsCategory WHERE lm.DocId = :docId`
- SQL 2: `SELECT l.docId , l.v_SNo, l.subcode AS acCode, sg.Name AS acName , l.tdsDesc AS tdsDescCode, tdsCD.name AS tdsDescName, tdsCD.manualCode AS tdsDescMCode , l.formulaString, l.roundOffTDS, l.tdsPer, l.amtCr,l.AmtDr, l.calcOnValue FROM ledger l LEFT JOIN SubGroup sg ON sg.SubCode = l.subcode LEFT JOIN tdsCat_Description tdsCD ON tdsCD.code = l.tdsDesc WHERE l.docId =:docId AND ISNULL(l.tdsDesc, '') <> '' order by l.v_SNo`
- SQL 3: `SELECT TOP 1 l.docId , l.v_SNo FROM ledger l WHERE l.docId =:docId AND l.subCode = :subCode {…} order by l.v_SNo DESC`
- Params: `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:subCode` ← `DmShared.xNull(mrTemp.getTdsDeductFromCode())`
- Other calls: `AccountAdjustment_Server.getAdjustments`, `PAYABLE.getCode`, `RECEIVABLE.getCode`, `TDS_For.getViaCode`, `TDS_TCS_Type.getViaCode`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl reasonInfo_Mdl)`


### `deleteRecord`
`private String deleteRecord(String keyCode, Connection cnTemp, Timestamp timestamp) throws Exception`

- SQL 1: `SELECT wtaCode FROM ledgerM WHERE docId = :docId`
- SQL 2: `Delete From ledger WHERE DocId= :DocId`
- SQL 3: `Delete From ledgerM WHERE DocId= :DocId`
- Params: `:docId` ← `DmShared.xNull(keyCode)`; `:DocId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`
- Other calls: `AccountAdjustment_Server.deleteRecord`, `TdsTcsEntry_Server.deleteTdsTrans`, `WalkThrough_Server.deleteWalkThrough`

### `saveRecord`
`public DmResult<String> saveRecord(TdsTcsEntry_Mdl mrTemp, boolean isAutoApprove)`

- SQL 1: `SELECT postedBy,cancelledBy FROM ledgerM where docId = :docId`
- SQL 2: `SELECT u_EntDt_LatestLine FROM ledgerM WHERE docId = :docId`
- SQL 3: `Delete From Ledger WHERE DocId =:DocId`
- SQL 4: `Insert Into LedgerM ( DocId , V_Type , V_Prefix , Site_Code , V_No , V_Date , SubCode , RecId , recIdPrefix , preparedDt , PreparedBy , preparedByIP , remark , Narration , wtaCode , u_EntDt_LatestLine , tdsFor , tdsCategory , tdsDeductFrom , tdsOnAmt , tdsAmt, pan, tan, tdsTcsType ) Values ( :DocId , :V_Type , :V_Prefix , :Site_Code , :V_No , :V_Date , :SubCode , :RecId , :recIdPrefix , :uEntDt , :preparedBy , :preparedByIP , :remark , :Narration , :wtaCode , :uEntDt , :tdsFor , :tdsCategory , :tdsDeductFrom , :tdsOnAmt , :tdsAmt, :pan, :tan ,:tdsTcsType )`
- SQL 5: `update LedgerM Set V_Date=:V_Date,SubCode=:SubCode,transferDt=:transferDt,U_Name=:uName,u_EntDt_LatestLine = :uEntDt, U_EntDt=:uEntDt, tdsFor = :tdsFor , tdsCategory = :tdsCategory , tdsDeductFrom = :tdsDeductFrom , tdsOnAmt = :tdsOnAmt , tdsAmt = :tdsAmt , remark=:remark , Narration=:Narration , pan = :pan , tan = :tan , tdsTcsType = :tdsTcsType WHERE DocId=:DocId`
- SQL 6: `INSERT INTO ledger ( DocId, RecId, recIdPrefix, V_No, V_SNo, V_Date , SubCode, ContraSub, AmtDr, AmtCr, remark, narration , V_Type, V_Prefix, Site_Code, ContraText , roundOffTDS, tdsCategory , tdsDesc, tdsOnAmt, tdsPer, calcOnValue, formulaString , U_Name, u_EntDt,tdsTcsType) Values ( :DocId, :RecId, :recIdPrefix, :V_No, :V_SNo, :V_Date, :SubCode, :ContraSub, :AmtDr, :AmtCr, :remark, :narration, :V_Type, :V_Prefix, :Site_Code, :ContraText, :roundOffTDS, :tdsCategory , :tdsDesc, :tdsOnAmt, :tdsPer, :calcOnValue, :formulaString , :uName, :uEntDt, :tdsTcsType)`
- SQL 7: `Insert Into Ledger ( DocId , RecId , recIdPrefix , V_No , V_SNo , V_Date , SubCode , ContraSub , AmtDr , AmtCr , remark , narration , V_Type , V_Prefix , Site_Code , ContraText , tdsCategory, tdsDeductFrom, tdsOnAmt , tdsFor, pan, tan , U_Name, u_EntDt, tdsTcsType) Values ( :DocId , :RecId , :recIdPrefix , :V_No , :V_SNo , :V_Date , :SubCode , :ContraSub , :AmtDr , :AmtCr , :remark , :narration , :V_Type , :V_Prefix , :Site_Code , :ContraText , :tdsCategory , :tdsDeductFrom , :tdsOnAmt , :tdsFor, :pan, :tan , :uName, :uEntDt, :tdsTcsType)`
- Params: `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:DocId` ← `mrTemp.getSharedKeyFieldCode()`; `:DocId` ← `mrTemp.getSharedKeyFieldCode()`; `:V_Type` ← `mrTemp.getVoucherTypeCode()`; `:V_Prefix` ← `mrTemp.getVoucherPrefix()`; `:Site_Code` ← `DmServer.getSsnSiteCode(this)`; `:recIdPrefix` ← `DmShared.xNull(mrTemp.getObjRecId().getPrefix())`; `:V_No` ← `mrTemp.getObjRecId().getCounter().toString()`; `:V_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:SubCode` ← `DmShared.foriegnKey(mrTemp.getTdsDeductFromCode())`; `:RecId` ← `mrTemp.getSharedKeyFieldName()`; `:wtaCode` ← `DmShared.foriegnKey(mrTemp.getWtaCode())`; `:preparedBy` ← `uName`; `:uEntDt` ← `timestamp`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:tdsFor` ← `DmShared.foriegnKey(mrTemp.getTransactionCode())`; `:tdsCategory` ← `DmShared.foriegnKey(mrTemp.getTdsCategoryCode())`; `:tdsDeductFrom` ← `DmShared.foriegnKey(mrTemp.getTdsDeductFromCode())`; `:tdsOnAmt` ← `DmServer.vNull(mrTemp.getTdsOnAmount(), "0.00")`; `:tdsAmt` ← `DmServer.vNull(mrTemp.getTotalAmount(), "0.00")`; `:pan` ← `DmShared.xNull(mrTemp.getPan())`; `:tan` ← `DmShared.xNull(mrTemp.getTan())`; `:tdsTcsType` ← `DmShared.foriegnKey(mrTemp.getTypeCode())`; `:Narration` ← `""`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:V_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:SubCode` ← `DmShared.foriegnKey(mrTemp.getTdsDeductFromCode())`; `:transferDt` ← `null`; `:uName` ← `uName`; `:uEntDt` ← `timestamp`; `:DocId` ← `mrTemp.getSharedKeyFieldCode()`; `:Narration` ← `""`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:tdsFor` ← `DmShared.foriegnKey(mrTemp.getTransactionCode())`; `:tdsCategory` ← `DmShared.foriegnKey(mrTemp.getTdsCategoryCode())`; `:tdsDeductFrom` ← `DmShared.foriegnKey(mrTemp.getTdsDeductFromCode())`; `:tdsOnAmt` ← `DmServer.vNull(mrTemp.getTdsOnAmount(), "0.00")`; `:tdsAmt` ← `DmServer.vNull(mrTemp.getTotalAmount(), "0.00")`; `:pan` ← `DmShared.xNull(mrTemp.getPan())`; `:tan` ← `DmShared.xNull(mrTemp.getTan())`; `:tdsTcsType` ← `DmShared.foriegnKey(mrTemp.getTypeCode())`; `:DocId` ← `mrTemp.getSharedKeyFieldCode()`; `:RecId` ← `mrTemp.getSharedKeyFieldName()`; `:recIdPrefix` ← `DmShared.xNull(mrTemp.getObjRecId().getPrefix())`; `:V_No` ← `mrTemp.getObjRecId().getCounter().toString()`; `:V_SNo` ← `DmServer.vNull(vSNo, "0")`; `:V_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:SubCode` ← `DmShared.foriegnKey(gtdm.getPostingAccountCode())`; `:ContraSub` ← `DmShared.foriegnKey(contra)`; `:AmtDr` ← `"0.00"`; `:AmtCr` ← `DmServer.vNull(gtdm.getAmount(), "0.00")`; `:AmtDr` ← `DmServer.vNull(gtdm.getAmount(), "0.00")`; `:AmtCr` ← `"0.00"`; `:remark` ← `""`; `:narration` ← `narrationTemp`; `:V_Type` ← `DmShared.foriegnKey(mrTemp.getVoucherTypeCode())`; `:V_Prefix` ← `mrTemp.getVoucherPrefix()`; `:Site_Code` ← `DmServer.getSsnSiteCode(this)`; `:ContraText` ← `contraTextJV`; `:roundOffTDS` ← `DmShared.foriegnKey(gtdm.getRoundOffTDS())`; `:tdsCategory` ← `DmShared.foriegnKey(mrTemp.getTdsCategoryCode())`; `:tdsDesc` ← `DmShared.foriegnKey(gtdm.getDescriptionCode())`; `:tdsOnAmt` ← `DmServer.vNull(mrTemp.getTdsOnAmount(), "0.00")`; `:tdsPer` ← `DmServer.vNull(gtdm.getPercent())`; `:calcOnValue` ← `DmServer.vNull(gtdm.getCalcOnValue(), "0.00")`; `:formulaString` ← `DmShared.foriegnKey(gtdm.getFormula())`; `:tdsTcsType` ← `DmShared.foriegnKey(mrTemp.getTypeCode())`; `:uName` ← `uName`; `:uEntDt` ← `timestamp`; `:DocId` ← `mrTemp.getSharedKeyFieldCode()`; `:RecId` ← `mrTemp.getSharedKeyFieldName()`; `:recIdPrefix` ← `DmShared.xNull(mrTemp.getObjRecId().getPrefix())`; `:V_No` ← `mrTemp.getObjRecId().getCounter().toString()`; `:V_SNo` ← `DmShared.iNull(vSNo).intValue()`; `:V_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:SubCode` ← `DmShared.foriegnKey(mrTemp.getTdsDeductFromCode())`; `:ContraSub` ← `DmShared.foriegnKey(contraTDS)`; `:AmtDr` ← `DmServer.vNull(mrTemp.getTotalAmount(), "0.00")`; `:AmtCr` ← `"0.00"`; `:AmtDr` ← `"0.00"`; `:AmtCr` ← `DmServer.vNull(mrTemp.getTotalAmount(), "0.00")`; `:remark` ← `""`; `:narration` ← `mrTemp.getRemark()`; `:V_Type` ← `DmShared.foriegnKey(mrTemp.getVoucherTypeCode())`; `:V_Prefix` ← `mrTemp.getVoucherPrefix()`; `:Site_Code` ← `DmServer.getSsnSiteCode(this)`; `:ContraText` ← `contraTextTDS`; `:tdsCategory` ← `DmShared.foriegnKey(mrTemp.getTdsCategoryCode())`; `:tdsDeductFrom` ← `DmShared.foriegnKey(mrTemp.getTdsDeductFromCode())`
- Errors: "Record is posted. You can't Edit this record." | "Record is discarded. You can't Edit this record." | "Record has been changed, can't update record, refresh record then update" | "Invalid key."
- Other calls: `AccountAdjustment_Server.deleteRecord`, `AccountAdjustment_Server.saveRecord`, `DmApprovals.gstVoucherApproval`, `PAYABLE.getCode`, `RECEIVABLE.getCode`, `TdsTcsEntry_Server.deleteTdsTrans`, `TdsTcsEntry_Server.saveTdsTrans`, `TdsTcsEntry_Vld.chkSave`, `WalkThrough_Server.applyWalkThrough`

### `getWalkThroughFor`
`private WalkThroughDefault_Mdl getWalkThroughFor(TdsTcsEntry_Mdl mrTemp)`


### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT lm.DocId, lm.V_Type, lm.V_Date , vt.short_Name , CASE WHEN lm.tdsTcsType=:tdsTypeCode THEN :tdsTypeName WHEN lm.tdsTcsType=:tcsTypeCode THEN :tcsTypeName ELSE '' END AS tdsTcsType , CASE WHEN lm.tdsFor =:payableCode THEN :payableName WHEN lm.tdsFor =:recievableCode THEN :recievableName ELSE '' END AS tdsFor , sg.name as partyName , tc.name as category , lm.tdsOnAmt as onAmount , lm.tdsAmt as totalAmount , lm.RecId AS Voucher_No,lm.V_NO , IIF(ISNULL(lm.PostedBy,'') = '','',CONCAT(lm.PostedBy,' ',format(lm.postedByDateTime,'dd-MMM-yyyy HH.mm'))) as statusBy From ledgerM lm LEFT JOIN Voucher_Type vt ON lm.V_Type=vt.V_Type LEFT JOIN subGroup sg ON sg.subCode = lm.subCode LEFT JOIN tdsCat tc ON tc.code = lm.tdsCategory WHERE lm.Site_Code = :siteCode And lm.V_Prefix = :voucherPrefix {…}`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:voucherPrefix` ← `DmServer.getSsnVoucherPrefix(this)`; `:tdsTypeCode` ← `DmEnum.TDS_TCS_Type.TDS.getCode()`; `:tdsTypeName` ← `DmEnum.TDS_TCS_Type.TDS.getName()`; `:tcsTypeCode` ← `DmEnum.TDS_TCS_Type.TCS.getCode()`; `:tcsTypeName` ← `DmEnum.TDS_TCS_Type.TCS.getName()`; `:payableCode` ← `DmEnum.TDS_For.PAYABLE.getCode()`; `:payableName` ← `DmEnum.TDS_For.PAYABLE.getName()`; `:recievableCode` ← `DmEnum.TDS_For.RECEIVABLE.getCode()`; `:recievableName` ← `DmEnum.TDS_For.RECEIVABLE.getName()`
- Other calls: `PAYABLE.getCode`, `PAYABLE.getName`, `RECEIVABLE.getCode`, `RECEIVABLE.getName`, `TCS.getCode`, `TCS.getName`, `TDS.getCode`, `TDS.getName`

### `getServerDateTime`
`public DmResult<String[]> getServerDateTime()`


### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`

- SQL 1: `select top 1 description,V_Type from voucher_Type where category IN (' {…} ')`
- SQL 2: `SELECT ec.isTcsAutoCalculate FROM enviroCompany ec`
- Errors: "'TCS Auto Calculate' is not defined in Environment Setting (Company)."

### `setApproval`
`public DmResult<String> setApproval(String approvedBy, boolean isApprove, String docId)`

- Other calls: `DmApprovals.gstVoucherApproval`

### `walkThrough`
`public DmResult<String> walkThrough(WalkThrough_Mdl walkThrough)`

- Other calls: `DmApprovals.gstVoucherApproval`, `WalkThrough_Server.walkThrough`

### `printRecords`
`public DmResult<String> printRecords(String docId)`

- SQL 1: `SELECT ROW_NUMBER() OVER (ORDER BY T.DocId ) AS srlno,T.* FROM ( SELECT lm.DocId , lm.V_Type , lm.V_Prefix , lm.Site_Code , lm.RecId AS V_No , lm.V_Date , lm.SubCode, lg.tdsPer , sg.Name AS AcName , sg.pan, lm.tdsAmt , lm.Narration , lm.U_Name , lm.PreparedBy , vt.Description , vt.Category , tc.name , lg.SubCode as SubCode1 , SG1.Name AS AcName1 , lg.Narration AS Narration1 , SG1.ManualCode AS accode , lg.V_SNo , lg.AmtDr-isNull(TDSADJ.ADJAmtCr,0) AS AmtDr , lg.AmtCr-isNull(TDSADJ.ADJAmtDr,0) AS AmtCr , isNull(lg.Chq_No,'') AS Chq_No , lg.Chq_Date , lg.TDSOnAmt , lg.TDSCategory , SM.Name AS SiteName , isNull(lm.PostedBy,'') AS PostedBy , vt.Category AS Categ , lm.RecID AS RecId , isNull(sg1.add1,'') AS add1 , isNull(sg1.add2,'') AS add2 , isNull(ct.cityname,'') AS Cityname , isNull(lg.isSystem_Generated,0) AS System_Generated , lg.RefNo , lg.RefDate , TDSADJ.ADJAmtDr , TDSADJ.ADJAmtCr ,CASE WHEN ISNULL(lm.tdsFor,0)=' {…} ' THEN ' {…} ' WHEN ISNULL(lm.tdsFor,0)=' {…} ' THEN ' {…} ' end as transactionType ,CASE WHEN ISNULL(lm.tdstcstype,0)=' {…} ' THEN ' {…} ' WHEN ISNULL(lm.tdstcstype,0)=' {…} ' THEN ' {…} ' end as tdstcsType , case when LC.Cnt_CCM=1 then LC.CCMName end as costCenterName , lm.remark FROM ledgerM lm LEFT JOIN ledger lg ON lg.DocID=lm.DocId LEFT JOIN SubGroup SG ON lm.SubCode=sg.SubCode LEFT JOIN SubGroup SG1 ON lg.SubCode=SG1.SubCode LEFT JOIN Voucher_Type VT ON vt.V_Type=lm.V_Type LEFT JOIN city CT ON ct.citycode=SG1.citycode LEFT JOIN SiteMast SM ON SM.Code=lm.Site_Code LEFT JOIN tdsCat tc ON tc.code = lm.tdsCategory LEFT JOIN ( SELECT lm.DocId,lg.V_SNo,lg.SubCode,lg.AmtDr AS ADJAmtDr,lg.AmtCr AS ADJAmtCr,lg.TDSFor, lg.TDSCategory,lg.TDSDeductFrom,lg.TDSDesc ,lg.TdsOnAmt,lg.TdsPer,lg.Tds_Of_V_Sno FROM ledgerM lm LEFT JOIN ledger lg ON lm.DocID=lg.DocID WHERE isNull(lg.TdsDesc,0) = 0 AND isNull(lg.Tds_Of_V_Sno,0)<> 0 AND lm.DocId = :docId ) AS TDSADJ ON TDSADJ.DocID=lg.DocID AND TDSADJ.Tds_Of_V_Sno=lg.V_SNo LEFT JOIN ( SELECT LGC.DocId,Count(Distinct LGC.CostCenter) AS Cnt_CCM,Max(LGC.CostCenter) AS CostCenter,Max(CCM.Name) AS CCMName From LedgerCostCenter LGC LEFT JOIN CostCenterMast CCM On CCM.Code = LGC.CostCenter Group By LGC.DocId )AS LC On LC.DocId = lm.DocId WHERE (CASE WHEN vt.Category ='PMT' THEN isNull(AmtDr,0) WHEN vt.Category ='RCT' THEN isNull(AmtCr,0) ELSE 1 END) <> 0 AND (CASE WHEN lg.isSystem_Generated = 1 THEN isNull(lg.TdsDesc,0) ELSE isNull(lg.TdsDesc,-1) END) <> 0 AND lm.DocId = :docId ) AS T ORDER BY T.V_date,T.Recid`
- Params: `:docId` ← `DmShared.xNull(docId)`
- Other calls: `PAYABLE.getCode`, `PAYABLE.getName`, `RECEIVABLE.getCode`, `RECEIVABLE.getName`, `TCS.getCode`, `TCS.getName`, `TDS.getCode`, `TDS.getName`

## Depends on (imports from other packages)
`dataman.accounts.tdsTcsEntry.shared`, `dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.accountAdjustment.client`, `dataman.dmToolkit.accountAdjustment.server`, `dataman.dmToolkit.accountAdjustment.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.documentSystemReport.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.overHeadLine.shared`, `dataman.dmToolkit.tdsPopup.shared`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `dataman.libraryMenu.self.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (!DmShared.xNull(rsTemp.getString("postedBy")).equalsIgnoreCase(""))` | "Record is posted. You can't Edit this record." |
| saveRecord | `} else if (!DmShared.xNull(rsTemp.getString("cancelledBy")).equalsIgnoreCase(""))` | "Record is discarded. You can't Edit this record." |
| saveRecord | `if (DmShared.iNull(mrTemp.getU_EntDt_LatestLine()).longValue() != rsTemp.getTimestamp("u_EntDt_LatestLine").getTime())` | "Record has been changed, can't update record, refresh record then update" |
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| getDefaultValues | `if (rsTemp.getString("isTcsAutoCalculate") == null)` | "'TCS Auto Calculate' is not defined in Environment Setting (Company)." |

## Notes (hand-written)
_No hand-written notes yet._
