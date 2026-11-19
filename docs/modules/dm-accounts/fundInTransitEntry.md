# dm-accounts / fundInTransitEntry

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/accounts/src/fundInTransitEntry/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: LedgerM, Ledger
- Update tables: LedgerM
- Delete cascade (in order): LedgerCostCenter, Ledger, LedgerM
- Duplicate checks on save:
  - `SELECT COUNT(LM.DocId) AS Cnt FROM LedgerM LM WHERE LM.DocId = :docId AND isNull(LM.PostedBy,'') <> ''`
  - `Select Count(LM.DocId) As Cnt From LedgerM LM Where LM.DocId <> :docId And LM.LGDocId = :transferTag`
- "In use" checks before delete:
  - `SELECT COUNT(DocId) Cnt FROM LedgerM WHERE DocId = :docId AND isNull(PostedBy, '') <> ''`
  - `SELECT COUNT(*) Cnt FROM DataAudit WHERE DocId = :docId`
  - `SELECT COUNT(Adj_DocId) Cnt FROM LedgerAdj WHERE Adj_DocId= :docId`
  - `SELECT COUNT(*) Cnt FROM dataAudit WHERE DocId = :docId`
- Validations: Entry Date (Text); Current Site (Text); Transfer No. (Text); Debit A/c (Text); Credit A/c (Text); Amount (Amount); rule: Clearing date can't be less than cheque data.
- Lookups/suggestions: getTxtHCostCenter, getTxtHAccount, getTxtHTransferNo
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `FundInTransitEntry`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Entry No. | DmTextBox | `txtEntryNo` | `` |
| 2 | Entry Date | DmTextBox | `txtDtEntry` | `` |
| 3 | Transfer No. | DmTextHelp | `txtHTransferNo` | `TxtHTransferNo_PrpAcs, 7` |
| 4 | Current Site Name | DmTextBox | `txtCurrentSite` | `` |
| 5 | Cost Center | DmTextHelp | `txtHCostCenter` | `TxtHCostCenter_PrpAcs, 7` |
| 6 | Debit Account | DmTextHelp | `txtHDrAccount` | `TxtHAccount_PrpAcs, 7` |
| 7 | Credit Account | DmTextHelp | `txtHCrAccount` | `TxtHAccount_PrpAcs, 7` |
| 8 | Transfer Amount | DmTextBoxNumeric | `txtAmount` | `DmTextBoxNumeric.DecimalNature.DEFAULT, true, 13, true` |
| 9 | Cheque No. | DmTextBox | `txtChequeNo` | `` |
| 10 | Cheque Date | DmTextBox | `txtDtCheque` | `` |
| 11 | Clearing Date | DmTextBox | `txtDtClearing` | `` |
| 12 | - | DmRemarkTextBox | `txtRemark` | `250, false` |
| 13 | Transfer From Site Name | DmTextBox | `txtTrnSite` | `` |
| 14 | Transfer From Cost Center | DmTextBox | `txtTrnCostCenter` | `` |
| 15 | Debit Account | DmTextBox | `txtTrnDrAccount` | `` |
| 16 | Credit Account | DmTextBox | `txtTrnCrAccount` | `` |
| 17 | Remark | DmTextBox | `txtTrnRemark` | `` |

Other widgets (not in tab list): `txtWTACode` (DmTextBox), `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `txtHDrAccount`: Name [Text, w0, Name]; Manual Code [Text, w150, ManualCode]; City [Text, w150, CityName]; Nature [Text, w75, Nature]
- `txtHCrAccount`: Name [Text, w0, Name]; Manual Code [Text, w150, ManualCode]; City [Text, w150, CityName]; Nature [Text, w75, Nature]
- `txtHCostCenter`: Name [Text, w0, Name]; Site Name [Text, w150, Site_Code]
- `txtHTransferNo`: Entry No. [Text, w0, RecId]; Type [Text, w75, V_Type]; Entry Date [Text, w75, V_Date]; Name [Text, w100, SName]; Amount [Text, w75, Amount]; Credit A/c [Text, w100, SubName]; Debit A/c [Text, w100, ContraName]
- `dmFind`: Entry No. [Text, w100, DocId]; Entry Date [Text, w75, Entry_Date]; Current Site [Text, w150, Trn_SiteName]; Cost Center [Text, w150, CostCenterName]; Credit A/c [Text, w150, Credit_Ac]; Debit A/c [Text, w150, Debit_Ac]; Amount [Text, w150, Amount]; Transfer Cost Center [Text, w150, Trn_Cost_Center_Name]; Transfer Credit A/c [Text, w150, Trn_Credit_Ac]; Transfer Debit A/c [Text, w150, Trn_Debit_Ac]; Prepared By [Text, w100, PreparedBy]; Approved By [Text, w100, PostedBy]; Modified By [Text, w100, U_Name]; Entry Date [Text, w125, entryDateTime]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHCostCenter` → `getTxtHCostCenter`
- `txtHCrAccount` → `getTxtHAccount`
- `txtHDrAccount` → `getTxtHAccount`
- `txtHTransferNo` → `getTxtHTransferNo`
- `narrationSearch` → `getFind`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `Amount` ← DmClient.vNull(txtAmount.getText(), "0.00")
- `ChqNo` ← DmShared.xNull(txtChequeNo.getText())
- `CostCenterTag` ← DmShared.xNull(txtHCostCenter.getTag())
- `CostCenterText` ← DmShared.xNull(txtHCostCenter.getText())
- `CrAccountDesc` ← DmShared.xNull(txtHCrAccount.getTag2nd())
- `CrAccountTag` ← DmShared.xNull(txtHCrAccount.getTag())
- `CrAccountText` ← DmShared.xNull(txtHCrAccount.getText())
- `CurrentSiteTag` ← DmShared.xNull(txtCurrentSite.getTag())
- `CurrentSiteText` ← DmShared.xNull(txtCurrentSite.getText())
- `DrAccountDesc` ← DmShared.xNull(txtHDrAccount.getTag2nd())
- `DrAccountTag` ← DmShared.xNull(txtHDrAccount.getTag())
- `DrAccountText` ← DmShared.xNull(txtHDrAccount.getText())
- `DtChq` ← DmShared.dNull(DmShared.convertDate(txtDtCheque.getText()))
- `DtClearing` ← DmShared.dNull(txtDtClearing.getText())
- `DtEntry` ← DmShared.dNull(DmShared.convertDate(txtDtEntry.getText()))
- `WtaCode` ← txtWTACode.getText()
- `VoucherTypeCode` ← DmShared.xNull(txtDtEntry.getTag2nd())
- `SharedKeyFieldName` ← DmShared.xNull(txtEntryNo.getText())
- `Remark` ← DmShared.xNull(txtRemark.getText())
- `TransCostCenterTag` ← DmShared.xNull(txtTrnCostCenter.getTag())
- `TransCostCenterText` ← DmShared.xNull(txtTrnCostCenter.getText())
- `TransCrAccountTag` ← DmShared.xNull(txtTrnCrAccount.getTag())
- `TransCrAccountText` ← DmShared.xNull(txtTrnCrAccount.getText())
- `TransDrAccountTag` ← DmShared.xNull(txtTrnDrAccount.getTag())
- `TransDrAccountText` ← DmShared.xNull(txtTrnDrAccount.getText())
- `TransRemark` ← DmShared.xNull(txtTrnRemark.getText())
- `TransferNoTag` ← DmShared.xNull(txtHTransferNo.getTag())
- `TransferNoText` ← DmShared.xNull(txtHTransferNo.getText())
- `TransSiteTag` ← DmShared.xNull(txtTrnSite.getTag())
- `TransSiteText` ← DmShared.xNull(txtTrnSite.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtAmount.setText` ← DmClient.vNull(data.getAmount(), "0.00")
- `txtChequeNo.setText` ← data.getChqNo()
- `txtCurrentSite.setTag` ← data.getCurrentSiteTag()
- `txtCurrentSite.setText` ← data.getCurrentSiteText()
- `txtDtCheque.setText` ← data.getDtChq()
- `txtDtClearing.setText` ← data.getDtClearing()
- `txtDtEntry.setText` ← data.getDtEntry()
- `txtDtEntry.setTag` ← data.getVoucherPrefix()
- `txtDtEntry.setTag2nd` ← DmShared.xNull(data.getVoucherTypeCode())
- `txtEntryNo.setText` ← data.getSharedKeyFieldName()
- `txtEntryNo.setTag` ← DmShared.iNull(data.getObjRecId().getCounter()).toString()
- `txtEntryNo.setTag2nd` ← DmShared.xNull(data.getObjRecId().getPrefix())
- `txtRemark.setText` ← data.getRemark()
- `txtTrnCostCenter.setTag` ← data.getTransCostCenterTag()
- `txtTrnCostCenter.setText` ← data.getTransCostCenterText()
- `txtTrnCrAccount.setTag` ← data.getTransCrAccountTag()
- `txtTrnDrAccount.setTag` ← data.getTransDrAccountTag()
- `txtTrnDrAccount.setText` ← data.getTransDrAccountText()
- `txtTrnRemark.setText` ← data.getTransRemark()
- `txtTrnSite.setTag` ← data.getTransSiteTag()
- `txtTrnSite.setText` ← data.getTransSiteText()
- `txtHCostCenter.setTag` ← data.getCostCenterTag()
- `txtHCostCenter.setText` ← data.getCostCenterText()
- `txtHCrAccount.setTag` ← data.getCrAccountTag()
- `txtHCrAccount.setText` ← data.getCrAccountText()
- `txtHDrAccount.setTag` ← data.getDrAccountTag()
- `txtHDrAccount.setText` ← data.getDrAccountText()
- `txtHTransferNo.setTag` ← data.getTransferNoTag()
- `txtHTransferNo.setText` ← data.getTransferNoText()
- `txtWTACode.setText` ← DmShared.xNull(data.getWtaCode())
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- `userControl.setTextBlog` ← approvedByBlog, data.getApprovedBy()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())
- userControl.setTextBlog(approvedByBlog, data.getApprovedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtCurrentSite.setEnabled(false)`
- `txtTrnCostCenter.setEnabled(false)`
- `txtTrnCrAccount.setEnabled(false)`
- `txtTrnDrAccount.setEnabled(false)`
- `txtTrnRemark.setEnabled(false)`
- `txtTrnSite.setEnabled(false)`
- `txtAmount.setEnabled(false)`
- `txtChequeNo.setEnabled(false)`
- `txtDtCheque.setEnabled(false)`
- `txtEntryNo.setEnabled(false)`

**On New click:**
- `clear()`
- `txtDtEntry.setTag2nd(defaultVoucherType)`
- `txtDtEntry.setFocus(true)`

**`manageDisplay()` logic (condensed code):**
```java
txtCurrentSite.setEnabled(false);
txtTrnCostCenter.setEnabled(false);
txtTrnCrAccount.setEnabled(false);
txtTrnDrAccount.setEnabled(false);
txtTrnRemark.setEnabled(false);
txtTrnSite.setEnabled(false);
txtAmount.setEnabled(false);
txtChequeNo.setEnabled(false);
txtDtCheque.setEnabled(false);
txtEntryNo.setEnabled(false);
DmClient.setStyleHyperlinkLabel(lblRemark, flag, "", false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
clear();
manageDisplay(true);
txtDtEntry.setTag2nd(defaultVoucherType);
txtDtEntry.setFocus(true);
```

`topControl.addEditHandeller` → `editClicked()`
```java
txtEntryNo.setEnabled(false);
SrvAsync.invokeRPC().getEditCheck(topControl.getDocId(), new AsyncCallback<DmResult<String>>() {
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtDtEntry).show();
public void onSuccess(DmResult<String> result) {
if (result.isStatus()) {
manageDisplay(true);
txtDtEntry.setFocus(true);
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtDtEntry).show();
...
```

`topControl.addEditHandeller` → `onSuccess()`
```java
if (result.isStatus()) {
manageDisplay(true);
txtDtEntry.setFocus(true);
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtDtEntry).show();
topControl.btnClick("");
```

`topControl.addDeleteHandeller` → `deleteClicked()`
```java
new DmConfirmMessageBox("Are you sure? You want to delete this record.", new DialogHideEvent.DialogHideHandler() {
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
if (topControl.getMode().equalsIgnoreCase("Edit")) {
showModifyReason();
} else {
topControlSave();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(FundInTransitEntry.this);
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

`txtHCostCenter.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHCostCenter(helper, blnToEndOfFile, txtCurrentSite.getTag().toString(), new As
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

`txtHCrAccount.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHAccount(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHAc
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHCrAccount).show();
public void onSuccess(DmResultPageing<List<TxtHAccounts_Mdl>> result) {
if (result.isStatus()) {
txtHCrAccount.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCrAccount).show();
```

`txtHCrAccount.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHCrAccount.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCrAccount).show();
```

`txtHDrAccount.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHAccount(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHAc
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDrAccount).show();
public void onSuccess(DmResultPageing<List<TxtHAccounts_Mdl>> result) {
if (result.isStatus()) {
txtHDrAccount.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDrAccount).show();
```

`txtHDrAccount.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHDrAccount.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDrAccount).show();
```

`txtHTransferNo.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHTransferNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Txt
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHTransferNo).show();
public void onSuccess(DmResultPageing<List<TxtHTransferNo_Mdl>> result) {
if (result.isStatus()) {
txtHTransferNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTransferNo).show();
```

`txtHTransferNo.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHTransferNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTransferNo).show();
```

`txtHTransferNo.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus) {
txtHTransferNo.setTag(t1Row.getSharedKeyFieldCode());
txtHTransferNo.setText(t1Row.getSharedKeyFieldName().trim() + "/" + t1Row.getVType().trim() + "/" + t1Row.getSubCode());
txtAmount.setText(DmShared.vNull(t1Row.getAmount()).toString());
txtHCrAccount.setTag(t1Row.getDefaultCode());
txtHCrAccount.setText(t1Row.getDefaultName());
txtTrnSite.setTag(t1Row.getSiteCode());
txtTrnSite.setText(t1Row.getSName());
txtTrnCrAccount.setTag(t1Row.getSubCode());
txtTrnCrAccount.setText(t1Row.getSubName());
...
```

`lblRemark.addClickHandler` → `onClick()`
```java
if (!DmShared.xNull(topControl.getMode()).equalsIgnoreCase("Browse")) {
showNarrationSearch();
```

`narrationSearch.addCallBack` → `ok()`
```java
txtRemark.setText(DmShared.xNull(narration));
txtRemark.setFocus(true);
```

`narrationSearch.addCallBack` → `close()`
```java
txtRemark.setFocus(true);
```

`dmFind.addHelpGridCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getFind(topControl.getPermissions(), helper, blnToEndOfFile, DmClient.getVoucherPref
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), dmFind.getTextBox()).show();
public void onSuccess(DmResultPageing<List<Find_Mdl>> result) {
if (result.isStatus()) {
if (result.isStatus()) {
dmFind.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), dmFind.getTextBox()).show();
```

`dmFind.addHelpGridCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
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

`txtDtEntry.addBlurHandler` → `onBlur()`
```java
txtDtEntry.setText(DmClient.returnDate(txtDtEntry.getText()));
```

`txtDtCheque.addBlurHandler` → `onBlur()`
```java
txtDtCheque.setText(DmClient.returnDate(txtDtCheque.getText()));
```

`txtDtClearing.addBlurHandler` → `onBlur()`
```java
txtDtClearing.setText(DmClient.returnDate(txtDtClearing.getText()));
```

**Top control options:** setOtherRequired(false), setRefreshRequired(false), setDisplay(true)

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."

## Files
| file | lines |
|---|---|
| client/FundInTransitEntry.java | 826 |
| client/FundInTransitEntry_Dsg.java | 579 |
| server/FundInTransitEntry_SrvImpl.java | 1285 |
| shared/Find_Mdl.java | 207 |
| shared/FundInTransitEntry_Mdl.java | 321 |
| shared/FundInTransitEntry_Srv.java | 42 |
| shared/FundInTransitEntry_SrvAsync.java | 42 |
| shared/FundInTransitEntry_Vld.java | 39 |
| shared/TxtHAccounts_Mdl.java | 41 |
| shared/TxtHCostCenter_Mdl.java | 35 |
| shared/TxtHTransferNo_Mdl.java | 171 |

## UI labels / columns (client)
`Remark`, `Entry No.`, `Entry Date`, `Transfer No.`, `Current Site Name`, `Cost Center`, `Debit Account`, `Credit Account`, `Transfer Amount`, `Cheque No.`, `Cheque Date`, `Clearing Date`, `Transfer Detail`, `Transfer From Site Name`, `Transfer From Cost Center`

## Models (shared DTOs)
### TxtHAccounts_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | cityName |
| String | nature |
| String | manualCode |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | VDate |
| String | VType |
| String | Narration |
| String | CostCenter |
| String | ChqNo |
| String | DtChq |
| String | DtClearing |
| String | AmtCr |
| String | CrParty |
| String | DrParty |
| String | TrnSite |
| String | TrnRecID |
| String | TrnType |
| String | TrnCrParty |
| String | TrnDrParty |
| String | TrnCostCenter |
| String | approvedBy |
| String | preparedBy |
| String | modifiedBy |
| String | entryDtTime |

### TxtHCostCenter_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | Site |

### TxtHTransferNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | VType |
| String | VDate |
| String | SiteCode |
| String | SName |
| String | Amount |
| String | SubCode |
| String | ContraSub |
| String | SubName |
| String | ContraName |
| String | CostCenter |
| String | CCName |
| String | ChqNo |
| String | DtChq |
| String | DefaultCode |
| String | DefaultName |
| String | Narration |

### FundInTransitEntry_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| RecId | objRecId |
| String | voucherPrefix |
| String | voucherTypeName |
| String | voucherTypeCode |
| String | DtEntry |
| String | TransferNoTag |
| String | TransferNoText |
| String | CurrentSiteTag |
| String | CurrentSiteText |
| String | CostCenterText |
| String | CostCenterTag |
| String | CrAccountText |
| String | CrAccountTag |
| String | CrAccountDesc |
| String | DrAccountText |
| String | DrAccountTag |
| String | DrAccountDesc |
| String | Amount |
| String | ChqNo |
| String | DtChq |
| String | DtClearing |
| String | Remark |
| String | TransSiteTag |
| String | TransSiteText |
| String | TransCostCenterTag |
| String | TransCostCenterText |
| String | TransDrAccountText |
| String | TransDrAccountTag |
| String | TransCrAccountText |
| String | TransCrAccountTag |
| String | TransRemark |
| String | approvedBy |
| ModifyReason_Mdl | modifyReasonMdl |
| String | wtaCode |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator | UserPermission userPermission, String vPrefix | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<FundInTransitEntry_Mdl> |
| saveRecord | FundInTransitEntry_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl modifyReasonMdl | DmResult<String> |
| getFind | UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile, String vPrefix | DmResultPageing<List<Find_Mdl>> |
| getTxtHCostCenter | DmHelper helper, boolean blnToEndOfFile, String strSiteCode | DmResultPageing<List<TxtHCostCenter_Mdl>> |
| getTxtHCostCenter | DmHelper pageing, boolean blnToEndOfFile, String strSiteCode, boolean blnInsert | DmResultPageing<List<TxtHCostCenter_Mdl>> |
| getTxtHAccount | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHAccounts_Mdl>> |
| setApproval | String approvedBy, boolean isApprove, String docId | DmResult<String> |
| getVoucherType |  | DmResult<String> |
| getTxtHTransferNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHTransferNo_Mdl>> |
| getCurrentSite |  | DmResult<KeyFieldExtended> |
| getEditCheck | String docId | DmResult<String> |

## Validator `FundInTransitEntry_Vld`
```java
public class FundInTransitEntry_Vld {
    public static void chkSave(FundInTransitEntry_Mdl mrTemp) {
        Date dtCheque = DmShared.convertDate(mrTemp.getDtChq());
        Date dtClearing = DmShared.convertDate(mrTemp.getDtClearing());
        DmShared.isValidText(mrTemp.getDtEntry(), "Entry Date", 100);
        DmShared.isValidText(mrTemp.getCurrentSiteTag(), "Current Site", 200);
        DmShared.isValidText(mrTemp.getTransferNoTag(), "Transfer No.", 300);
        DmShared.isValidText(mrTemp.getDrAccountTag(), "Debit A/c", 400);
        DmShared.isValidText(mrTemp.getCrAccountTag(), "Credit A/c", 500);
        DmShared.isValidAmount(mrTemp.getAmount(), "Amount", 600);
        if (!(DmShared.dNull(mrTemp.getDtChq())).equalsIgnoreCase("") && !DmShared.dNull(mrTemp.getDtClearing()).equals("")) {
            if (dtCheque.after(dtClearing)) {
                throw new DmException(700, "Clearing date can't be less than cheque data.");
            }
        }
    }
}
```

## Server `FundInTransitEntry_SrvImpl.java` (1285 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.checkFinancialYearDate`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getLocalIPAddresses`, `DmServer.getRecId`, `DmServer.getServerTimestamp`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserViewCondition`, `DmServer.getUserWithDateTime`, `DmServer.prepareContraText`, `DmServer.setSsnOldData`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(UserPermission userPermission, String vPrefix)`

- SQL 1: `SELECT LM.DocId AS SearchCode FROM LedgerM LM left join voucher_type vt on lm.v_type = vt.v_type WHERE vt.category = {…} AND LM.V_Prefix = :vPrefix AND LM.Site_Code = :siteCode`
- Params: `:vPrefix` ← `vPrefix`; `:siteCode` ← `siteCode`

### `getMoveRec`
`public DmResult<FundInTransitEntry_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT LM.V_Prefix, LM.PreparedBy,LM.preparedByIP, LM.PostedBy,LM.PostedByDateTime,LM.preparedDt,LM.u_EntDt, LM.U_Name, LG.DocID, LG.RecID, Lg.v_No, Lg.recIdPrefix , LG.V_DAte, LG.V_Type, LG.Narration, LG.AmtCr, LG.SubCode, LG.ContraSub, LG.Site_Code, isNull(LG.Chq_No, '') AS Chq_No, LG.Chq_Date, LG.Clg_Date,VT.Description, SGC.Name AS CreditPartyName, SGD.Name AS DebitPartyName, SM.Name AS SiteName, CCM.Name AS CostCenterName, LGC.CostCenter, LM.LGDocId, TLM.RecId AS TLMRecId, TLM.V_Type AS TLMV_Type, TLM.SubCode AS TLMSubCode, TLM.ContraSub AS TLMContraSub, TLM.Site_Code AS TLMSite_Code, TLM.CreditPartyName AS TLMCreditPartyName, TLM.DebitPartyName AS TLMDebitPartyName, TLM.SiteName AS TLMSiteName, TLM.CostCenterName AS TLMCostCenterName, TLM.CostCenter AS TLMCostCenter, TLM.Narration AS TLMNarration, LM.u_Name, lm.wtaCode FROM LedgerM LM LEFT JOIN Ledger LG ON LG.DocID = LM.DocID LEFT JOIN LedgerCostCenter LGC ON LGC.DocID = LG.DocId AND LGC.V_SNo = LG.V_SNo LEFT JOIN SubGroup SGC ON SGC.SubCode = LG.SubCode LEFT JOIN SubGroup SGD ON SGD.SubCode = LG.ContraSub LEFT JOIN Voucher_Type VT ON VT.V_Type = LG.V_Type LEFT JOIN SiteMast SM ON SM.Code = LG.Site_Code LEFT JOIN CostCenterMast CCM ON CCM.Code = LGC.CostCenter LEFT JOIN (SELECT LG.DocId, LG.RecId, LG.V_Type, LG.SubCode, LG.ContraSub, LG.Site_Code, SGC.Name AS CreditPartyName, SGD.Name AS DebitPartyName, SM.Name AS SiteName, CCM.Name AS CostCenterName, LGC.CostCenter, LG.Narration FROM Ledger LG LEFT JOIN LedgerCostCenter LGC ON LGC.DocID = LG.DocId AND LGC.V_SNo = LG.V_SNo LEFT JOIN SubGroup SGC ON SGC.SubCode = LG.SubCode LEFT JOIN SubGroup SGD ON SGD.SubCode = LG.ContraSub LEFT JOIN SiteMast SM ON SM.Code = LG.Site_Code LEFT JOIN CostCenterMast CCM ON CCM.Code = LGC.CostCenter WHERE isNull(LG.AmtCr, 0) > 0) TLM ON TLM.DocId = LM.LGDocId WHERE LG.DocID = :docId AND isNull(LG.AmtCr, 0) > 0`
- Params: `:docId` ← `DmShared.iNull(strKey).toString()`

### `saveRecord`
`public DmResult<String> saveRecord(FundInTransitEntry_Mdl mrTemp)`

- SQL 1: `SELECT COUNT(LM.DocId) AS Cnt FROM LedgerM LM WHERE LM.DocId = :docId AND isNull(LM.PostedBy,'') <> ''`
- SQL 2: `Select Count(LM.DocId) As Cnt From LedgerM LM Where LM.DocId <> :docId And LM.LGDocId = :transferTag`
- SQL 3: `DELETE FROM LedgerCostCenter WHERE DocID = :docID`
- SQL 4: `DELETE FROM Ledger WHERE DocID = :docID`
- SQL 5: `INSERT INTO LedgerM ( DocId, V_Type, V_Prefix, Site_Code, V_Date, SubCode, Narration, RecId, recIdPrefix, v_No, preparedDt, preparedByIP, LGDocId, PreparedBy, wtaCode) VALUES( :docId, :vType, :vPrefix, :siteCode, :vDate, :subCode, :narration, :recId, :recIdPrefix, :v_No, :preparedDt, :preparedByIP, :transferNo, :userName, :wtaCode) ;`
- SQL 6: `UPDATE LedgerM SET V_Date = :vDate ,SubCode = :subCode ,Narration = :narration ,LGDocId = :transferNo ,transferDt = null ,U_Name = :userName ,U_EntDt = :uEntDt WHERE DocId = :docId`
- SQL 7: `INSERT INTO Ledger ( DocId, RecId, V_SNo, V_Date, SubCode, ContraSub, AmtDr, AmtCr, Narration, V_Type, V_Prefix, Site_Code, Chq_No, Chq_Date, ContraText, Clg_Date,favourOf, recIdPrefix, v_No) VALUES ( :docId, :recId, :vSno, :vDate, :subCode, :ContraSub, NULL, :amount, :narration, :vType, :vPrefix, :siteCode, :chqNo, :chqDate, :contraText, :clgDate,:favourOf, :recIdPrefix, :v_No)`
- SQL 8: `INSERT INTO Ledger ( DocId, RecId, V_SNo, V_Date, SubCode, ContraSub, AmtDr, AmtCr, Narration, V_Type, V_Prefix, Site_Code, Chq_No, Chq_Date, ContraText, Clg_Date,favourOf, recIdPrefix, v_No ) VALUES ( :docId, :recId, :vSno, :vDate, :subCode, :contraSub, :amtDr, NULL, :narration, :vType, :vPrefix, :siteCode, :chqNo, :chqDate, :contraText, :clgDate,:favourOf, :recIdPrefix, :v_No)`
- Params: `:docId` ← `mrTemp.getTransferNoTag()`; `:docId` ← `mrTemp.getSharedKeyFieldCode()`; `:transferTag` ← `mrTemp.getTransferNoTag()`; `:docID` ← `mrTemp.getSharedKeyFieldCode()`; `:docID` ← `mrTemp.getSharedKeyFieldCode()`; `:docId` ← `mrTemp.getSharedKeyFieldCode()`; `:vType` ← `vType`; `:vPrefix` ← `DmShared.xNull(mrTemp.getVoucherPrefix())`; `:siteCode` ← `DmShared.foriegnKey(mrTemp.getCurrentSiteTag())`; `:vDate` ← `DmServer.convertDate(DmShared.dNull(mrTemp.getDtEntry()))`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getCrAccountTag())`; `:narration` ← `DmShared.xNull(mrTemp.getRemark())`; `:recId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:recIdPrefix` ← `DmShared.xNull(mrTemp.getObjRecId().getPrefix())`; `:v_No` ← `DmShared.iNull(mrTemp.getObjRecId().getCounter()).toString()`; `:userName` ← `userName`; `:transferNo` ← `DmShared.foriegnKey(mrTemp.getTransferNoTag())`; `:preparedDt` ← `timestamp`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:wtaCode` ← `DmShared.foriegnKey(mrTemp.getWtaCode())`; `:vDate` ← `DmServer.convertDate(DmShared.dNull(mrTemp.getDtEntry()))`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getCrAccountTag())`; `:narration` ← `DmShared.xNull(mrTemp.getRemark())`; `:transferNo` ← `DmShared.foriegnKey(mrTemp.getTransferNoTag())`; `:userName` ← `userName`; `:docId` ← `mrTemp.getSharedKeyFieldCode()`; `:uEntDt` ← `timestamp`; `:docId` ← `mrTemp.getSharedKeyFieldCode()`; `:recId` ← `mrTemp.getSharedKeyFieldName()`; `:recIdPrefix` ← `DmShared.xNull(mrTemp.getObjRecId().getPrefix())`; `:v_No` ← `DmShared.iNull(mrTemp.getObjRecId().getCounter()).toString()`; `:vSno` ← `intVSno`; `:vDate` ← `DmServer.convertDate(DmShared.dNull(mrTemp.getDtEntry()))`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getCrAccountTag())`; `:contraSub` ← `DmShared.foriegnKey(mrTemp.getDrAccountTag())`; `:amount` ← `DmServer.vNull(mrTemp.getAmount(), "0.00")`; `:narration` ← `DmShared.xNull(mrTemp.getRemark())`; `:vType` ← `vType`; `:vPrefix` ← `vPrefix`; `:siteCode` ← `DmShared.foriegnKey(mrTemp.getCurrentSiteTag())`; `:chqNo` ← `DmShared.xNull(mrTemp.getChqNo())`; `:chqDate` ← `DmServer.convertDate(DmShared.dNull(mrTemp.getDtChq()))`; `:contraText` ← `contraText`; `:clgDate` ← `DmServer.convertDate(DmShared.dNull(mrTemp.getDtClearing()))`; `:favourOf` ← `DmShared.xNull(mrTemp.getDrAccountText())`; `:docId` ← `mrTemp.getSharedKeyFieldCode()`; `:recId` ← `mrTemp.getSharedKeyFieldName()`; `:recIdPrefix` ← `DmShared.xNull(mrTemp.getObjRecId().getPrefix())`; `:v_No` ← `DmShared.iNull(mrTemp.getObjRecId().getCounter()).toString()`; `:vSno` ← `intVSno`; `:vDate` ← `DmServer.convertDate(DmShared.dNull(mrTemp.getDtEntry()))`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getDrAccountTag())`; `:contraSub` ← `DmShared.foriegnKey(mrTemp.getCrAccountTag())`; `:amtDr` ← `DmServer.vNull(mrTemp.getAmount(), "0.00")`; `:narration` ← `DmShared.xNull(mrTemp.getRemark())`; `:vType` ← `vType`; `:vPrefix` ← `vPrefix`; `:siteCode` ← `DmShared.foriegnKey(mrTemp.getCurrentSiteTag())`; `:chqNo` ← `DmShared.xNull(mrTemp.getChqNo())`; `:chqDate` ← `DmServer.convertDate(DmShared.dNull(mrTemp.getDtChq()))`; `:contraText` ← `contraText`; `:clgDate` ← `DmServer.convertDate(DmShared.dNull(mrTemp.getDtClearing()))`; `:favourOf` ← `DmShared.xNull(mrTemp.getDrAccountText())`; `:fromCostCenter` ← `DmShared.foriegnKey(mrTemp.getTransCostCenterTag())`; `:docId` ← `mrTemp.getSharedKeyFieldCode()`
- Errors: "Invalid key." | "Transfer No. Not Found." | "Transfer No. Already Is In Use."
- Other calls: `FundInTransitEntry_Vld.chkSave`, `WalkThrough_Server.applyWalkThrough`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl modifyReasonMdl)`

- SQL 1: `SELECT v_date from LedgerM where docid = :docid`
- SQL 2: `SELECT COUNT(DocId) Cnt FROM LedgerM WHERE DocId = :docId AND isNull(PostedBy, '') <> ''`
- SQL 3: `SELECT COUNT(*) Cnt FROM DataAudit WHERE DocId = :docId`
- SQL 4: `SELECT COUNT(Adj_DocId) Cnt FROM LedgerAdj WHERE Adj_DocId= :docId`
- SQL 5: `SELECT COUNT(*) Cnt FROM dataAudit WHERE DocId = :docId`
- SQL 6: `DELETE FROM LedgerCostCenter WHERE DocID = :docId`
- SQL 7: `DELETE FROM Ledger WHERE DocID = :docId`
- SQL 8: `DELETE FROM LedgerM WHERE DocID = :docId`
- Params: `:docid` ← `DmShared.xNull(strKey)`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`
- Errors: "Record Has Been Posted. You Can Not Edit/ Delete This Record." | "Record Has Been Audited. You Can Not Edit/ Delete This Record." | "Record Has Been Adjusted. You Can Not Edit/ Delete This Record." | "Record Has Been Transfered. You Can Not Edit/ Delete This Record."

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile, String vPrefix)`

- SQL 1: `SELECT LM.DocId, LG.RecId AS Entry_No, LG.V_Type AS Entry_Type, LG.V_Date AS Entry_Date, LG.Narration AS Remark, LG.AmtCr AS Amount, isNull(LG.Chq_No, '') AS Chq_No, LG.Chq_Date, LG.Clg_Date, SGC.Name AS Credit_Ac, SGD.Name AS Debit_Ac, CCM.Name AS Cost_Center_Name, LM.PreparedBy, LM.PostedBy, LM.U_Name, TLM.SiteName AS Transfer_From_Site, TLM.RecId AS Trn_RecId, TLM.V_Type AS Trn_Type, TLM.CreditPartyName AS Trn_Credit_Ac, TLM.DebitPartyName AS Trn_Debit_Ac,CCM.Name AS CostCenterName, TLM.CostCenterName AS Trn_Cost_Center_Name, FORMAT(lm.u_EntDt,'dd-MMM-yyyy HH.mm') AS entryDateTime, LM.V_Date, vt.short_Name,LM.V_No FROM LedgerM LM LEFT JOIN Ledger LG ON LG.DocID = LM.DocID LEFT JOIN voucher_type vt on LG.v_type = vt.v_Type LEFT JOIN LedgerCostCenter LGC ON LGC.DocID = LG.DocId AND LGC.V_SNo = LG.V_SNo LEFT JOIN SubGroup SGC ON SGC.SubCode = LG.SubCode LEFT JOIN SubGroup SGD ON SGD.SubCode = LG.ContraSub LEFT JOIN CostCenterMast CCM ON CCM.Code = LGC.CostCenter LEFT JOIN (SELECT LG.DocId, LG.RecId, LG.V_Type, LG.SubCode, LG.ContraSub, LG.Site_Code, SGC.Name AS CreditPartyName, SGD.Name AS DebitPartyName, SM.Name AS SiteName, CCM.Name AS CostCenterName, LGC.CostCenter FROM Ledger LG LEFT JOIN LedgerCostCenter LGC ON LGC.DocID = LG.DocId AND LGC.V_SNo = LG.V_SNo LEFT JOIN SubGroup SGC ON SGC.SubCode = LG.SubCode LEFT JOIN SubGroup SGD ON SGD.SubCode = LG.ContraSub LEFT JOIN SiteMast SM ON SM.Code = LG.Site_Code LEFT JOIN CostCenterMast CCM ON CCM.Code = LGC.CostCenter WHERE isNull(LG.AmtCr, 0) > 0 ) TLM ON TLM.DocId = LM.LGDocId WHERE LM.Site_Code = :siteCode AND LM.V_Prefix = :vPrefix AND vt.category = {…} AND isNull(LG.AmtCr, 0) > 0`
- Params: `:siteCode` ← `siteCode`; `:vPrefix` ← `vPrefix`

### `getTxtHCostCenter`
`public DmResultPageing<List<TxtHCostCenter_Mdl>> getTxtHCostCenter(DmHelper helper, boolean blnToEndOfFile, String strSiteCode, boolean blnInsert)`

- SQL 1: `SELECT CCM.Code, CCM.Name, CCM.ManualCode, CCM.Site_Code,sm.name as siteName FROM costcentermast CCM left join siteMast as sm on sm.code = ccm.site_Code WHERE isNull(CCM.UnderCostCenter, '') = '' AND CCM.isActive = 1`
- Params: `:siteCode` ← `strSite`

### `getTxtHAccount`
`public DmResultPageing<List<TxtHAccounts_Mdl>> getTxtHAccount(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT SG.SubCode, SG.Name, isNull(CT.CityName, '') AS CityName, SG.ManualCode, SG.Nature ,sgn.name as natureName FROM subgroup SG left join subgroupNature as sgn on sgn.code= sg.nature LEFT JOIN city CT ON CT.CityCode = SG.CityCode LEFT JOIN subgroupsitelist SBSL ON SG.subCode=SBSL.subCode WHERE SBSL.Site_Code =:siteCode AND SG.isActive = 1`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHTransferNo`
`public DmResultPageing<List<TxtHTransferNo_Mdl>> getTxtHTransferNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT LM.DocId, LM.RecId, LM.V_Type, LM.V_Date, LM.Site_Code, SM.Name AS SName, ISNULL(LG.AmtCr, 0) AS Amount, LG.SubCode, LG.ContraSub, SG.Name AS SubName, CSG.Name AS ContraName, LGC.CostCenter, CCM.Name AS CCMName, LG.Chq_No, LG.Chq_Date, SSG.SubCode AS DefaultCode, SSG.Name AS DefaultName, LG.Narration FROM LedgerM LM LEFT JOIN LedgerM TLM ON LM.DocId = TLM.LGDocId LEFT JOIN Ledger LG ON LM.DocId = LG.DocId LEFT JOIN LedgerCostCenter LGC ON LM.DocId = LGC.DocId AND LGC.V_SNo = LG.V_SNo LEFT JOIN Voucher_Type VT ON VT.V_Type = LM.V_Type LEFT JOIN SiteMast SM ON SM.Code = LM.Site_Code LEFT JOIN SubGroup SSG ON SSG.SubCode = SM.SubCode LEFT JOIN SubGroup SG ON SG.SubCode = LG.SubCode LEFT JOIN SubGroup CSG ON CSG.SubCode = LG.ContraSub LEFT JOIN CostCenterMast CCM ON CCM.Code = LGC.CostCenter WHERE VT.Category = 'FNDTR' AND ISNULL(TLM.DocId, '') = '' AND ISNULL(LG.AmtCr, 0) > 0 AND LM.To_Site_Code = :To_Site_Code AND ISNULL(LM.PostedBy, '') <> ''`
- Params: `:To_Site_Code` ← `siteCode`

### `getCurrentSite`
`public DmResult<KeyFieldExtended> getCurrentSite()`

- SQL 1: `SELECT SM.Code,SM.Name AS SiteName FROM sitemast SM LEFT JOIN subgroup SG ON SM.SubCode = SG.SubCode WHERE SM.Code = :code ORDER BY SM.Name`
- Params: `:code` ← `siteCode`

### `getEditCheck`
`public DmResult<String> getEditCheck(String docId)`

- SQL 1: `SELECT COUNT(DocId) Cnt FROM LedgerM WHERE DocId = :docId AND isNull(PostedBy, '') <> ''`
- SQL 2: `SELECT COUNT(*) Cnt FROM DataAudit WHERE DocId = :docId`
- SQL 3: `SELECT COUNT(Adj_DocId) Cnt FROM LedgerAdj WHERE Adj_DocId= :docId`
- SQL 4: `SELECT COUNT(*) Cnt FROM dataAudit WHERE DocId = :docId`
- Params: `:docId` ← `DmShared.iNull(docId).toString()`; `:docId` ← `DmShared.iNull(docId).toString()`; `:docId` ← `DmShared.iNull(docId).toString()`; `:docId` ← `DmShared.iNull(docId).toString()`
- Errors: "Record Has Been Posted. You Can Not Edit/ Delete This Record." | "Record Has Been Audited. You Can Not Edit/ Delete This Record." | "Record Has Been Adjusted. You Can Not Edit/ Delete This Record." | "Record Has Been Transfered. You Can Not Edit/ Delete This Record."

### `getWalkThroughFor`
`private WalkThroughDefault_Mdl getWalkThroughFor(FundInTransitEntry_Mdl mrTemp, DmRSSPrivate dmRSSPrivate)`


### `setApproval`
`public DmResult<String> setApproval(String approvedBy, boolean isApprove, String docId)`

- Other calls: `DmApprovals.voucherApproval`

### `walkThrough`
`public DmResult<String> walkThrough(WalkThrough_Mdl walkThrough)`

- Other calls: `DmApprovals.voucherApproval`, `WalkThrough_Server.walkThrough`

### `getVoucherType`
`public DmResult<String> getVoucherType()`

- SQL 1: `SELECT TOP 1 vt.v_Type AS code FROM voucher_Type vt WHERE vt.category = {…} AND vt.isActive = 1`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.narrationSearch.client`, `dataman.dmToolkit.openSourceReport.client`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `dataman.libraryMenu.self.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (rsTemp.getLong("Cnt") <= 0)` | "Transfer No. Not Found." |
| saveRecord | `if (rsTemp.getLong("Cnt") >= 1)` | "Transfer No. Already Is In Use." |
| deleteRecord | `if (rsTemp.getLong("Cnt") > 0)` | "Record Has Been Posted. You Can Not Edit/ Delete This Record." |
| deleteRecord | `if (rsTemp.getLong("Cnt") > 0)` | "Record Has Been Audited. You Can Not Edit/ Delete This Record." |
| deleteRecord | `if (rsTemp.getLong("Cnt") > 0)` | "Record Has Been Adjusted. You Can Not Edit/ Delete This Record." |
| deleteRecord | `if (rsTemp.getLong("Cnt") > 0)` | "Record Has Been Transfered. You Can Not Edit/ Delete This Record." |
| getEditCheck | `if (rsTemp.getLong("Cnt") > 0)` | "Record Has Been Posted. You Can Not Edit/ Delete This Record." |
| getEditCheck | `if (rsTemp.getLong("Cnt") > 0)` | "Record Has Been Audited. You Can Not Edit/ Delete This Record." |
| getEditCheck | `if (rsTemp.getLong("Cnt") > 0)` | "Record Has Been Adjusted. You Can Not Edit/ Delete This Record." |
| getEditCheck | `if (rsTemp.getLong("Cnt") > 0)` | "Record Has Been Transfered. You Can Not Edit/ Delete This Record." |

## Notes (hand-written)
_No hand-written notes yet._
