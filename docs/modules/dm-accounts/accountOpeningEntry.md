# dm-accounts / accountOpeningEntry

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/accounts/src/accountOpeningEntry/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: LedgerM, Ledger
- Update tables: LedgerM
- Delete cascade (in order): LedgerCostCenter, Ledger, LedgerM
- Duplicate checks on save: none found
- "In use" checks before delete:
  - `Select Count(*) as Cnt From DataAudit Where DocId= :docId`
  - `Select Count(*) as cnt From LedgerAdj Where Adj_DocID= :docId`
- Validations: Voucher Type (Text); Date (Text); A/c Name (Text); rule: Please Fill Debit / Credit Amount.; rule: You Can Only Fill One Debit / Credit Amount.; rule: Reference date cannot be greater than or equal to voucher date.; rule: Clear Date Should be Greater Then Or Equal Chq Date.; rule: Clear Date Should be Greater Then Or Equal Voucher Date.
- Lookups/suggestions: getTxtHAccountName, getTxtHCostCenter, getTxtHVoucherType
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `AccountOpeningEntry`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Voucher Type | DmTextHelp | `txtHVoucherType` | `KeyField_PrpAcs, 7, true` |
| 2 | Vr No. | DmTextBox | `txtVoucherNo` | `10, false` |
| 3 | Date | DmTextBox | `txtVoucherDate` | `11, true` |
| 4 | Account Name | DmTextHelp | `txtHAccountName` | `TxtHAccountName_PrpAcs, 7, true` |
| 5 | <Cost Center> | DmTextHelp | `txtHCostCenter` | `TxtHCostCenter_PrpAcs, 7` |
| 6 | - | DmRemarkTextBox | `txtNarration` | `250, false` |
| 7 | Ref No. | DmTextBox | `txtRefNo` | `10, false` |
| 8 | Ref Date | DmTextBox | `txtRefDate` | `11, false` |
| 9 | Debit | DmTextBoxNumeric | `txtDebit` | `DmTextBoxNumeric.DecimalNature.DEFAULT, true, 15, false` |
| 10 | Credit | DmTextBoxNumeric | `txtCredit` | `DmTextBoxNumeric.DecimalNature.DEFAULT, true, 15, false` |
| 11 | Cheque No. | DmTextBox | `txtChequeNo` | `20, false` |
| 12 | Cheque Date | DmTextBox | `txtChequeDate` | `11, false` |
| 13 | Favour Of | DmRemarkTextBox | `txtFavourOf` | `100, false` |
| 14 | Clear Date | DmTextBox | `txtClearDate` | `11, false` |
| 15 | Remark | DmTextBox | `txtReconcilationRemark` | `100, false` |

Other widgets (not in tab list): `dmGrd` (DmGrid), `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `txtHAccountName`: Account Name [Text, w0, PartyName]; Code [Text, w100, Code]; Group Name [Text, w200, GroupName]; City Name [Text, w100, CityName]; Address-1 [Text, w100, Add1]; Address-2 [Text, w100, Add2]
- `txtHVoucherType`: Name [Text, w0, Description]
- `txtHCostCenter`: CostCenter Name [Text, w0, Name]; Code [Text, w100, ManualCode]; Site [Text, w150, Site_Code]
- `dmGrd`: V. Type [Text, w50]; Vr. No. [Text, w90]; Vr. Date [Text, w80]; Ref. No. [Text, w100]; Ref. Date [Text, w80]; Chq. [Text, w80]; Chq. Date [Text, w80]; Narration [Text, w100]; Debit [Text, w80]; Credit [Text, w80]; Cost Center [Text, w100]
- `dmFind`: Voucher No. [Text, w150, Voucher_No]; Account Name [Text, w200, Name]; Voucher Date [Text, w80, Voucher_Date]; Cost Center [Text, w100, Cost_Center]; Ref No. [Text, w150, Ref_No]; Ref Date [Text, w100, Ref_Date]; Narratioin [Text, w150, Narration]; Debit [Text, w100, Debit]; Credit [Text, w100, Credit]; Prepared By [Text, w100, PreparedBy]; Approved By [Text, w100, PostedBy]; Modified By [Text, w100, ModifiedBy]; Entry Date [Text, w125, entryDateTime]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHAccountName` → `getTxtHAccountName`
- `txtHVoucherType` → `getTxtHVoucherType`
- `txtHCostCenter` → `getTxtHCostCenter`
- `narrationSearch` → `getFind`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `AccountName` ← DmShared.xNull(txtHAccountName.getText())
- `AccountNameCode` ← DmShared.xNull(txtHAccountName.getTag())
- `AccountNameAccDesc` ← DmShared.xNull(txtHAccountName.getTag2nd())
- `ChqClearDate` ← DmShared.dNull(txtClearDate.getText())
- `ChqDate` ← DmShared.dNull(txtChequeDate.getText())
- `ChqNo` ← DmShared.xNull(txtChequeNo.getText())
- `CostCenterCode` ← DmShared.xNull(txtHCostCenter.getTag())
- `CostCenterName` ← DmShared.xNull(txtHCostCenter.getText())
- `Credit` ← DmClient.vNull(txtCredit.getText(), "0.00")
- `Date` ← DmShared.dNull(txtVoucherDate.getText())
- `Debit` ← DmClient.vNull(txtDebit.getText(), "0.00")
- `FavourOf` ← DmShared.xNull(txtFavourOf.getText())
- `Narration` ← DmShared.xNull(txtNarration.getText())
- `ReconcilationRemark` ← DmShared.xNull(txtReconcilationRemark.getText())
- `RefDate` ← DmShared.xNull(txtRefDate.getText())
- `RefNo` ← DmShared.xNull(txtRefNo.getText())
- `VoucherTypeCode` ← DmShared.xNull(txtHVoucherType.getTag())
- `VoucherTypeName` ← DmShared.xNull(txtHVoucherType.getText())
- `SharedKeyFieldName` ← DmShared.xNull(txtVoucherNo.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtChequeDate.setText` ← DmShared.xNull(data.getChqDate())
- `txtChequeNo.setText` ← DmShared.xNull(data.getChqNo())
- `txtCredit.setText` ← DmClient.vNull(data.getCredit(), "0.00")
- `txtDebit.setText` ← DmClient.vNull(data.getDebit(), "0.00")
- `txtFavourOf.setText` ← DmShared.xNull(data.getFavourOf())
- `txtHAccountName.setText` ← DmShared.xNull(data.getAccountName())
- `txtHAccountName.setTag` ← DmShared.xNull(data.getAccountNameCode())
- `txtHAccountName.setTag2nd` ← DmShared.xNull(data.getAccountNameAccDesc())
- `txtHCostCenter.setText` ← DmShared.xNull(data.getCostCenterName())
- `txtHCostCenter.setTag` ← DmShared.xNull(data.getCostCenterCode())
- `txtHVoucherType.setText` ← DmShared.xNull(data.getVoucherTypeName())
- `txtHVoucherType.setTag` ← DmShared.xNull(data.getVoucherTypeCode())
- `txtNarration.setText` ← DmShared.xNull(data.getNarration())
- `txtRefDate.setText` ← DmShared.xNull(data.getRefDate())
- `txtRefNo.setText` ← DmShared.xNull(data.getRefNo())
- `txtVoucherDate.setText` ← DmShared.xNull(data.getDate())
- `txtVoucherNo.setText` ← DmShared.xNull(data.getSharedKeyFieldName())
- `txtVoucherNo.setTag` ← DmShared.iNull(data.getObjRecId().getCounter()).toString()
- `txtVoucherNo.setTag2nd` ← DmShared.xNull(data.getObjRecId().getPrefix())
- `txtClearDate.setText` ← DmShared.xNull(data.getChqClearDate())
- `txtReconcilationRemark.setText` ← DmShared.xNull(data.getReconcilationRemark())
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- `userControl.setTextBlog` ← approvedByBlog, data.getApprovedBy()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())
- userControl.setTextBlog(approvedByBlog, data.getApprovedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtChequeNo.setEnabled(true)`
- `txtChequeDate.setEnabled(true)`
- `txtFavourOf.setEnabled(true)`
- `txtChequeNo.setEnabled(false)`
- `txtChequeDate.setEnabled(false)`
- `txtFavourOf.setEnabled(false)`
- `txtClearDate.setEnabled(false)`
- `txtVoucherNo.setEnabled(false)`
- `txtVoucherDate.setEnabled(false)`
- `txtHVoucherType.setEnabled(false)`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**On New click:**
- `clear()`
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
if (blnEnb && accountNameAccDesc.equalsIgnoreCase(DmShared.xNull(defaultValue.getBankNatureCode()))) {
txtChequeNo.setEnabled(true);
txtChequeDate.setEnabled(true);
txtFavourOf.setEnabled(true);
} else {
txtChequeNo.setEnabled(false);
txtChequeDate.setEnabled(false);
txtFavourOf.setEnabled(false);
txtClearDate.setEnabled(false);
txtVoucherNo.setEnabled(false);
txtVoucherDate.setEnabled(false);
if (topControl.getMode().equalsIgnoreCase("Edit")) {
txtHVoucherType.setEnabled(false);
DmClient.setStyleHyperlinkLabel(lblNarration, blnEnb, "", false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
clear();
onAdd();
```

`topControl.addEditHandeller` → `editClicked()`
```java
if (DmShared.xNull(userControl.getTextBlog(approvedByBlog)).equalsIgnoreCase("")) {
txtHVoucherType.setFocus(true);
manageDisplay(true, txtHAccountName.getTag2nd());
} else {
topControl.btnClick("");
new DmAlertMessageBox("Record is approved. You can't modify this record.", (DmTextBox) null).show();
```

`topControl.addDeleteHandeller` → `deleteClicked()`
```java
if (DmShared.xNull(userControl.getTextBlog(approvedByBlog)).equalsIgnoreCase("")) {
new DmConfirmMessageBox("Are you sure? You want to delete this record.", new DialogHideEvent.DialogHideHandler() {
public void onDialogHide(DialogHideEvent event) {
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
showModifyReason();
}).show();
} else {
topControl.btnClick("");
new DmAlertMessageBox("Record is approved. You can't delete this record.", (DmTextBox) null).show();
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

`topControl.addSaveHandeller` → `saveClicked()`
```java
if (topControl.getMode().equalsIgnoreCase("Edit")) {
showModifyReason();
} else {
topControlSave();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(AccountOpeningEntry.this);
```

`modifyReason.addCallBack` → `onOk()`
```java
try {
modifyReasonMdl = modifyReason;
if (topControl.getMode().equalsIgnoreCase("Browse") && DmShared.iNull(topControl.getDocId()).longValue() > 0) {
topControlDelete(topControl.getDocId());
} else {
topControlSave();
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), (DmTextBox) null).show();
```

`txtHAccountName.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHAccountName(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Tx
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHAccountName).show();
public void onSuccess(DmResultPageing<List<TxtHAccountName_Mdl>> result) {
if (result.isStatus()) {
txtHAccountName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAccountName).show();
```

`txtHAccountName.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAccountName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAccountName).show();
```

`txtHAccountName.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus) {
txtHAccountName.setTag2nd(tRow.getAccountNameAccDesc());
manageDisplay(true, DmShared.xNull(tRow.getAccountNameAccDesc()));
getGrdData(tRow.getSharedKeyFieldCode(), DmClient.getVoucherPrefix());
```

`txtHVoucherType.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHVoucherType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHVoucherType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHVoucherType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHVoucherType).show();
```

`txtHVoucherType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHVoucherType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHVoucherType).show();
```

`txtHCostCenter.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHCostCenter(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Txt
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHCostCenter).show();
public void onSuccess(DmResultPageing<List<TxtHCostCenter_Mdl>> result) {
if (result.isStatus()) {
txtHCostCenter.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCostCenter).show();
```

`txtHCostCenter.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHCostCenter.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCostCenter).show();
```

`lblNarration.addClickHandler` → `onClick()`
```java
if (!DmShared.xNull(topControl.getMode()).equalsIgnoreCase("Browse")) {
showNarrationSearch();
```

`narrationSearch.addCallBack` → `ok()`
```java
txtNarration.setText(DmShared.xNull(narration));
txtNarration.setFocus(true);
```

`narrationSearch.addCallBack` → `close()`
```java
txtNarration.setFocus(true);
```

`dmFind.addHelpGridCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getFind(topControl.getPermissions(), helper, blnToEndOfFile, DmClient.getVoucherPref
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

`btnApprovedBy.addClickHandler` → `onClick()`
```java
if (DmShared.iNull(topControl.getDocId()).longValue() != 0) {
if (DmShared.xNull(userControl.getTextBlog(approvedByBlog)).equalsIgnoreCase("")) {
setApproved(topControl.getDocId(), true);
} else {
setApproved(topControl.getDocId(), false);
```

**Top control options:** setOtherRequired(false), setRefreshRequired(false), setDisplay(true)

_Print permission "P" is removed for this screen (replace "P" with "*")._

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Alert: "Record is approved. You can't modify this record."
- Alert: "Record is approved. You can't delete this record."

## Files
| file | lines |
|---|---|
| client/AccountOpeningEntry.java | 756 |
| client/AccountOpeningEntry_Dsg.java | 585 |
| server/AccountOpeningEntry_SrvImpl.java | 1185 |
| shared/AccountOpeningEntry_Mdl.java | 237 |
| shared/AccountOpeningEntry_Srv.java | 36 |
| shared/AccountOpeningEntry_SrvAsync.java | 48 |
| shared/AccountOpeningEntry_Vld.java | 58 |
| shared/DefaultValue_Mdl.java | 31 |
| shared/Find_Mdl.java | 161 |
| shared/GrdAccountOpeningEntry_Mdl.java | 135 |
| shared/TxtHAccountName_Mdl.java | 81 |
| shared/TxtHCostCenter_Mdl.java | 45 |

## UI labels / columns (client)
`Narration`, `Voucher Type`, `Vr No.`, `Date`, `Account Name`, `<Cost Center>`, `Ref No.`, `Ref Date`, `Debit`, `Credit`, `Cheque No.`, `Cheque Date`, `Favour Of`, `Clear Date`, `Remark`

## Models (shared DTOs)
### DefaultValue_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | currentDate |
| String | bankNatureCode |

### GrdAccountOpeningEntry_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | vType |
| String | date |
| String | accountName |
| String | costCenterName |
| String | narration |
| String | refNo |
| String | refDate |
| String | debit |
| String | credit |
| String | chqNo |
| String | chqDate |
| String | favourOf |

### TxtHAccountName_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | groupName |
| String | cityName |
| String | address1 |
| String | address2 |
| String | accountNameAccDesc |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | vType |
| String | voucherTypeName |
| String | date |
| String | costCenterName |
| String | accountName |
| String | refNo |
| String | refDate |
| String | narration |
| String | debit |
| String | credit |
| String | approvedBy |
| String | preparedBy |
| String | modifiedBy |
| String | entryDtTime |

### AccountOpeningEntry_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| RecId | objRecId |
| String | voucherTypeName |
| String | voucherTypeCode |
| String | vPrefix |
| String | date |
| String | ledgerVoucherDate |
| String | accountName |
| String | accountNameCode |
| String | accountNameAccDesc |
| String | costCenterName |
| String | costCenterCode |
| String | narration |
| String | refNo |
| String | refDate |
| String | debit |
| String | credit |
| String | chqNo |
| String | chqDate |
| String | favourOf |
| String | chqClearDate |
| String | reconcilationRemark |
| String | contraSub |
| String | approvedBy |
| List<GrdAccountOpeningEntry_Mdl> | strGrdData |

### TxtHCostCenter_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | maualCode |
| String | site |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator | UserPermission userPermission, String vPrefix | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<AccountOpeningEntry_Mdl> |
| saveRecord | UserPermission userPermission, AccountOpeningEntry_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl modifyReasonMdl | DmResult<String> |
| getFind | UserPermission userPermission, DmHelper pageing, boolean blnToEndOfFile, String vPrfix | DmResultPageing<List<Find_Mdl>> |
| getTxtHAccountName | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHAccountName_Mdl>> |
| getTxtHCostCenter | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHCostCenter_Mdl>> |
| getTxtHCostCenter | DmHelper pageing, boolean blnToEndOfFile, boolean BlnInsert | DmResultPageing<List<TxtHCostCenter_Mdl>> |
| getTxtHVoucherType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getFrmGrdData | String accountNameCode, String VPrefix | DmResultPageing<List<GrdAccountOpeningEntry_Mdl>> |
| setApproval | String approvedBy, boolean isApprove, String docId | DmResult<String> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |

## Validator `AccountOpeningEntry_Vld`
```java
public class AccountOpeningEntry_Vld {
    public static void chkSave(AccountOpeningEntry_Mdl mrTemp) {
        Date vDate = DmShared.convertDate(mrTemp.getDate());
        Date chqDate = DmShared.convertDate(mrTemp.getChqDate());
        Date clearDate = DmShared.convertDate(mrTemp.getChqClearDate());
        DmShared.isValidText(mrTemp.getVoucherTypeCode(), "Voucher Type", 100);
        DmShared.isValidText(mrTemp.getDate(), "Date", 300);
        DmShared.isValidText(mrTemp.getAccountNameCode(), "A/c Name", 400);
        if (DmShared.vNull(mrTemp.getCredit()).equals(0.0) && DmShared.vNull(mrTemp.getDebit()).equals(0.0)) {
            throw new DmException(500, "Please Fill Debit / Credit Amount.");
        }
        if (!DmShared.vNull(mrTemp.getCredit()).equals(0.0) && !DmShared.vNull(mrTemp.getDebit()).equals(0.0)) {
            throw new DmException(600, "You Can Only Fill One Debit / Credit Amount.");
        }
        if (DmShared.dNull(mrTemp.getDate()).trim().equalsIgnoreCase("")
                && DmShared.dNull(mrTemp.getRefDate()).trim().equalsIgnoreCase("")) {
            if (DmShared.convertDate(DmShared.dNull(mrTemp.getRefDate())).getTime() >
                    DmShared.convertDate(DmShared.dNull(mrTemp.getDate())).getTime()) {
                throw new DmException(800, "Reference date cannot be greater than or equal to voucher date.");
            }
        }
        if (null != chqDate) {
            if (null != clearDate) {
                if (DmShared.xNull(mrTemp.getChqDate()).equals("") && DmShared.xNull(mrTemp.getChqClearDate()).equals("")) {
                    if (chqDate.after(clearDate)) {
                        throw new DmException(800, "Clear Date Should be Greater Then Or Equal Chq Date.");
                    } else if (!clearDate.equals(chqDate)) {
                        throw new DmException(800, "Clear Date Should be Greater Then Or Equal Chq Date.");
                    }
                }
            }
        }
        if (null != vDate) {
            if (null != clearDate) {
                if (DmShared.xNull(mrTemp.getChqClearDate()).equals("")) {
                    if (clearDate.after(vDate)) {
                        throw new DmException(900, "Clear Date Should be Greater Then Or Equal Voucher Date.");
                    } else if (!clearDate.equals(vDate)) {
                        throw new DmException(900, "Clear Date Should be Greater Then Or Equal Voucher Date.");
                    }
                }
            }
        }
    }
}
```

## Server `AccountOpeningEntry_SrvImpl.java` (1185 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.checkFinancialYearDate`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getLocalIPAddresses`, `DmServer.getParamList`, `DmServer.getRecId`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnCostCenterList`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserViewCondition`, `DmServer.setSsnOldData`, `DmServer.vNull`, `DmShared.convertDate`, `DmShared.dNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(UserPermission userPermission, String vPrefix)`

- SQL 1: `Select LM.DocId As SearchCode From LedgerM LM Left Join Voucher_Type VT ON VT.V_Type = LM.V_Type Where LM.Site_Code = :siteCode AND LM.V_Prefix = :vPrefix AND VT.Category In ( {…} )`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `vPrefix`

### `getMoveRec`
`public DmResult<AccountOpeningEntry_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT lg.docId, lg.v_SNo, lg.v_No, lg.recIdPrefix, lg.recId, lg.v_Type, lg.v_Prefix, lg.v_Date, lg.refNo, lg.refDate, lg.subcode, lg.contraSub, lg.amtDr, lg.amtCr, lg.chq_No, lg.chq_Date, lg.clg_Date, lg.favourOf, lg.narration, Lm.V_Date As lmVDate, LM.PostedBy,LM.PreparedBy,LM.preparedByIP,LM.U_Name AS ModifiedBy,LM.preparedDt,LM.u_EntDt,CCM.Code AS CCMCode,CCM.Name AS CCMName, SG.Name AS DrAcName,VT.Description, SG.Nature FROM LedgerM LM LEFT JOIN Ledger LG ON LM.DocID = LG.DocId LEFT JOIN LedgerCostCenter LGC ON LM.DocID = LGC.DocId LEFT JOIN CostCenterMast CCM ON CCM.Code = LGC.CostCenter LEFT JOIN SubGroup SG ON SG.SubCode = LG.SubCode LEFT JOIN Voucher_Type VT ON VT.V_Type = LM.V_Type WHERE LG.DocId = :docId`
- SQL 2: `Select LG.DocId, VT.short_Name as V_Type,LG.RecId,LG.V_Date,LG.RefNo,LG.RefDate,CCM.Name As CCMName, LG.Chq_No,LG.Chq_Date,LG.Narration,LG.AmtDr As AmtDr,LG.AmtCr as AmtCr From Ledger LG Left Join Voucher_Type VT On VT.V_Type = LG.V_Type Left Join LedgerCostCenter LGC On LG.DocID = LGC.DocId Left Join CostCenterMast CCM On CCM.Code = LGC.CostCenter Where LG.SubCode = :textAccountName And LG.V_Prefix = :compVPrefix And LG.Site_Code = :siteCode And VT.Category In ( {…} :vCategory {…} ) Order By LG.V_Date,LG.V_Type`
- Params: `:docId` ← `DmShared.iNull(strKey).toString()`; `:textAccountName` ← `DmShared.iNull(accountName).toString()`; `:CompVPrefix` ← `DmShared.iNull(pubCompVPrefix).toString()`; `:SiteCode` ← `siteCode`

### `haveControlPermission`
`public boolean haveControlPermission(UserPermission userPermission, String moduleName, String menuName, String controlCaption)`


### `saveRecord`
`public DmResult<String> saveRecord(UserPermission userPermission, AccountOpeningEntry_Mdl mrTemp)`

- SQL 1: `select postedBy FROM LedgerM WHERE docId = :docId`
- SQL 2: `Select Count(*) as Cnt From DataAudit Where DocId= :docId`
- SQL 3: `Select Count(*) as cnt From LedgerAdj Where Adj_DocID= :docId`
- SQL 4: `Delete From LedgerCostCenter Where DocId= :docId`
- SQL 5: `Delete From Ledger Where DocId= :docId`
- SQL 6: `Insert Into LedgerM(docId, V_Type, V_Prefix, Site_Code, RecId, recIdPrefix, v_No, V_Date, SubCode, U_EntDt, preparedDt, preparedByIP, PreparedBy) Values (:docId,:vType,:vPrefix,:siteCode,:recId, :recIdPrefix, :v_No,:vDate,:subCode,:preparedDt, :preparedDt, :preparedByIP,:preparedBy)`
- SQL 7: `Update LedgerM Set V_Date = :vDate, SubCode = :subCode, transferDt = null, U_Name = :uName , U_EntDt = :uEntDt Where DocId = :docId`
- SQL 8: `Insert Into Ledger(DocId,RecId, recIdPrefix, v_No,V_Date,SubCode,AmtDr,AmtCr,Chq_No,Chq_Date,Clg_Date,favourOf, Narration,V_Type,V_SNo,V_Prefix,Site_Code,RefNo,RefDate) Values (:docId,:recId, :recIdPrefix, :v_No,:vDate,:subCode,:amtDr,:amtCr,:chq_No,:chq_Date,:clg_Date,:favourOf,:narration,:vType,:vSNo,:vPrefix,:siteCode,:refNo,:refDate)`
- Params: `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:vType` ← `DmShared.xNull(mrTemp.getVoucherTypeCode())`; `:vPrefix` ← `DmShared.xNull(mrTemp.getvPrefix())`; `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`; `:recId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:recIdPrefix` ← `DmShared.xNull(mrTemp.getObjRecId().getPrefix())`; `:v_No` ← `DmShared.iNull(mrTemp.getObjRecId().getCounter()).toString()`; `:vDate` ← `DmServer.convertDate(mrTemp.getDate())`; `:subCode` ← `DmShared.xNull(mrTemp.getAccountNameCode())`; `:preparedBy` ← `DmShared.xNull(DmServer.getSsnUserName(this))`; `:preparedDt` ← `timestamp`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:vDate` ← `DmServer.convertDate(mrTemp.getDate())`; `:subCode` ← `DmShared.xNull(mrTemp.getAccountNameCode())`; `:uName` ← `DmShared.xNull(DmServer.getSsnUserName(this))`; `:uEntDt` ← `timestamp`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:recId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:recIdPrefix` ← `DmShared.xNull(mrTemp.getObjRecId().getPrefix())`; `:v_No` ← `DmShared.iNull(mrTemp.getObjRecId().getCounter()).toString()`; `:vDate` ← `DmServer.convertDate(mrTemp.getLedgerVoucherDate())`; `:subCode` ← `DmShared.xNull(mrTemp.getAccountNameCode())`; `:amtDr` ← `DmServer.vNull(mrTemp.getDebit(), "0.00")`; `:amtCr` ← `DmServer.vNull(mrTemp.getCredit(), "0.00")`; `:chq_No` ← `DmShared.xNull(mrTemp.getChqNo())`; `:chq_Date` ← `DmServer.convertDate(mrTemp.getChqDate())`; `:clg_Date` ← `DmServer.convertDate(mrTemp.getChqClearDate())`; `:favourOf` ← `DmShared.xNull(mrTemp.getFavourOf())`; `:narration` ← `DmShared.xNull(mrTemp.getNarration())`; `:vType` ← `DmShared.xNull(mrTemp.getVoucherTypeCode())`; `:vSNo` ← `"1"`; `:vPrefix` ← `DmShared.xNull(mrTemp.getvPrefix())`; `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`; `:refNo` ← `DmShared.xNull(mrTemp.getRefNo())`; `:refDate` ← `DmServer.convertDate(mrTemp.getRefDate())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:costCenter` ← `DmShared.foriegnKey(mrTemp.getCostCenterCode())`; `:amtDr` ← `DmServer.vNull(mrTemp.getDebit(), "0.00")`; `:amtCr` ← `DmServer.vNull(mrTemp.getCredit(), "0.00")`
- Errors: "Invalid key." | "Record is approved. You can't edit this record." | "This entry has been done from [Journal Voucher]. You can not edit/delete this entry." | "Record Has Been Audited. You Can Not Edit/ Delete This Record." | "Record Has Been Adjusted  You Can Not Edit/Delete This Bill." | "No Record Found."
- Other calls: `AccountOpeningEntry_Vld.chkSave`, `DmApprovals.voucherApproval`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl modifyReasonMdl)`

- SQL 1: `SELECT v_date from LedgerM where docid = :docid`
- SQL 2: `select postedBy FROM LedgerM WHERE docId = :docId`
- SQL 3: `Select Count(*) as Cnt From DataAudit Where DocId= :docId`
- SQL 4: `Select Count(*) as cnt From LedgerAdj Where Adj_DocID= :docId`
- SQL 5: `Delete From LedgerCostCenter Where DocId= :docId`
- SQL 6: `Delete From Ledger Where DocId= :docId`
- SQL 7: `Delete From LedgerM Where DocId= :docId`
- Params: `:docid` ← `DmShared.xNull(strKey)`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`
- Errors: "Record is approved. You can't delete this record." | "Record Has Been Audited. You Can Not Edit/ Delete This Record." | "Record Has Been Adjusted  You Can Not Edit/Delete This Bill."

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile, String vPrfix)`

- SQL 1: `Select LM.DocId As SearchCode,LM.V_Type As V_Type,VT.Description As Voucher_Type, LM.RecId As Voucher_No, VT.short_Name,LM.V_No, LM.recID, LM.V_Date As Voucher_Date,CCM.Name As Cost_Center, SG.Name,LG.RefNo As Ref_No,LG.RefDate As Ref_Date,LG.Narration, LG.AmtDr As Debit,LG.AmtCr As Credit,LM.PreparedBy,LM.U_Name As ModifiedBy,LM.PostedBy, FORMAT(lm.u_EntDt,'dd-MMM-yyyy HH.mm') AS entryDateTime From LedgerM LM Left Join Ledger LG On LG.DocId = LM.DocId Left Join SubGroup SG On LG.SubCode = SG.SubCode Left Join Voucher_Type VT On LG.V_Type = VT.V_Type Left Join LedgerCostCenter LGC On LG.DocID = LGC.DocId Left Join CostCenterMast CCM On CCM.Code = LGC.CostCenter Where LM.V_Prefix = :vPrefix And isNull(LG.isSystem_Generated,'0')='0' And LM.Site_Code = :siteCode And VT.Category In ( {…} :vCategory {…} )`
- Params: `:vPrefix` ← `vPrfix`; `:siteCode` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHAccountName`
`public DmResultPageing<List<TxtHAccountName_Mdl>> getTxtHAccountName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select SG.SubCode,SG.Name As PartyName,SG.ManualCode As Code,AG.GroupName, CT.CityName,SG.Add1,SG.Add2,SG.Nature From Subgroup SG Left Join AcGroup AG On AG.GroupCode = SG.GroupCode Left Join City CT On CT.CityCode = SG.CityCode LEFT JOIN subgroupsitelist SBSL ON SG.subCode=SBSL.subCode Where SBSL.Site_Code = :siteCode And SG.isActive = 1`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHCostCenter`
`public DmResultPageing<List<TxtHCostCenter_Mdl>> getTxtHCostCenter(DmHelper helper, boolean blnToEndOfFile, boolean BlnInsert)`

- SQL 1: `SELECT CCM.Code, CCM.Name, CCM.ManualCode, CCM.Site_Code,sm.name as siteName FROM costcentermast CCM left join siteMast as sm on sm.code = ccm.site_Code WHERE isNull(CCM.UnderCostCenter, '') = '' AND CCM.isActive = 1`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHVoucherType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVoucherType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select VT.V_Type, VT.Description From Voucher_Type VT Where VT.Category In ( {…} )`

### `getFrmGrdData`
`public DmResultPageing<List<GrdAccountOpeningEntry_Mdl>> getFrmGrdData(String accountNameCode, String VPrefix)`

- SQL 1: `Select LG.DocId, VT.short_Name as V_Type,LG.RecId,LG.V_Date,LG.RefNo,LG.RefDate,CCM.Name As CCMName, LG.Chq_No,LG.Chq_Date,LG.Narration,LG.AmtDr,LG.AmtCr From Ledger LG Left Join Voucher_Type VT On VT.V_Type = LG.V_Type Left Join LedgerCostCenter LGC On LG.DocID = LGC.DocId Left Join CostCenterMast CCM On CCM.Code = LGC.CostCenter Where LG.SubCode = :textAccountName And LG.V_Prefix = :compVPrefix And LG.Site_Code = :siteCode And VT.Category In ( {…} :vCategory {…} ) Order By LG.V_Date,LG.V_Type`
- Params: `:textAccountName` ← `strTextAccountCode`; `:compVPrefix` ← `strPubCompVPrefix`; `:siteCode` ← `strPubSiteCode`

### `setApproval`
`public DmResult<String> setApproval(String approvedBy, boolean isApprove, String docId)`

- Other calls: `DmApprovals.voucherApproval`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`

- SQL 1: `select v_Type as code, description as name from voucher_Type where category = {…} and isActive = 1`

## Depends on (imports from other packages)
`dataman.accounts.accountOpeningEntry.client`, `dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.narrationSearch.client`, `dataman.dmToolkit.walkThrough.shared`, `dataman.libraryMenu.self.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (DmShared.xNull(mrTemp.getSharedKeyFieldCode()).equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (!DmShared.xNull(rsTemp.getString("postedBy")).equalsIgnoreCase(""))` | "Record is approved. You can't edit this record." |
| saveRecord | `if (!"".equals(DmShared.xNull(mrTemp.getContraSub())))` | "This entry has been done from [Journal Voucher]. You can not edit/delete this entry." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Record Has Been Audited. You Can Not Edit/ Delete This Record." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Record Has Been Adjusted  You Can Not Edit/Delete This Bill." |
| saveRecord | (unconditional) | "No Record Found." |
| deleteRecord | `if (!DmShared.xNull(rsTemp.getString("postedBy")).equalsIgnoreCase(""))` | "Record is approved. You can't delete this record." |
| deleteRecord | `if (rsTemp.getLong("cnt") > 0)` | "Record Has Been Audited. You Can Not Edit/ Delete This Record." |
| deleteRecord | `if (rsTemp.getLong("cnt") > 0)` | "Record Has Been Adjusted  You Can Not Edit/Delete This Bill." |

## Notes (hand-written)
_No hand-written notes yet._
