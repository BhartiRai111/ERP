# dm-accounts / fundTransferEntry

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/accounts/src/fundTransferEntry/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: ledgerM, Ledger
- Update tables: ledgerM
- Delete cascade (in order): LedgerCostCenter, Ledger, LedgerM
- Duplicate checks on save: none found
- "In use" checks before delete:
  - `SELECT COUNT(DocId) Cnt FROM LedgerM WHERE DocId = :docId AND isNull(PostedBy,'')<>''`
  - `SELECT COUNT(*) Cnt FROM DataAudit WHERE DocId = :docId`
  - `SELECT COUNT(Adj_DocId) Cnt FROM LedgerAdj WHERE Adj_DocId= :docId`
  - `SELECT COUNT(*) Cnt FROM dataAudit WHERE DocId = :docId`
  - `SELECT COUNT(DocId) Cnt FROM LedgerM WHERE LGDocId = :docId`
- Validations: Entry Date (Text); Current Site (Text); Transfer site (Text); Debit A/c (Text); Credit A/c (Text); Transfer Amount (Amount); rule: Clearing date can't be less than cheque data.
- Lookups/suggestions: getTxtHFromCostCenter, getTxtHAccount, getTxtHTransferSite
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `FundTransferEntry`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Entry No. | DmTextBox | `txtEntryNo` | `` |
| 2 | Entry Date | DmTextBox | `txtDtEntry` | `` |
| 3 | Current Site Name | DmTextBox | `txtCurrentSite` | `` |
| 4 | Transfer To Site Name | DmTextHelp | `txtHTransferSite` | `TxtHTransferSite_PrpAcs, 7` |
| 5 | From Cost Center | DmTextHelp | `txtHFromCostCenter` | `TxtHFromCostCenter_PrpAcs, 7` |
| 6 | Debit Account | DmTextHelp | `txtHDrAccount` | `TxtHAccount_PrpAcs, 7` |
| 7 | Credit Account | DmTextHelp | `txtHCrAccount` | `TxtHAccount_PrpAcs, 7` |
| 8 | Transfer Amount | DmTextBoxNumeric | `txtAmount` | `DmTextBoxNumeric.DecimalNature.DEFAULT, false, 13, true` |
| 9 | Cheque No. | DmTextBox | `txtChequeNo` | `` |
| 10 | Cheque Date | DmTextBox | `txtDtCheque` | `` |
| 11 | Clearing Date | DmTextBox | `txtDtClearing` | `` |
| 12 | - | DmRemarkTextBox | `txtRemark` | `250, false` |
| 13 | Transfer To Cost Center | DmTextBox | `txtToCostCenter` | `` |
| 14 | Debit Account | DmTextBox | `txtTrnDrAccount` | `` |
| 15 | Credit Account | DmTextBox | `txtTrnCrAccount` | `` |
| 16 | Remark | DmTextBox | `txtTrnRemark` | `` |

Other widgets (not in tab list): `txtWTACode` (DmTextBox), `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `txtHFromCostCenter`: Name [Text, w0, Name]; Site Name [Text, w150, Site_Code]
- `txtHCrAccount`: Name [Text, w0, Name]; Manual Code [Text, w150, ManualCode]; City [Text, w150, CityName]; Nature [Text, w75, Nature]
- `txtHDrAccount`: Name [Text, w0, Name]; Manual Code [Text, w150, ManualCode]; City [Text, w150, CityName]; Nature [Text, w100, natureName]
- `txtHTransferSite`: Name [Text, w0, SiteName]; City [Text, w75, City]; A/c Name [Text, w125, ACName]
- `dmFind`: Entry No. [Text, w100, Entry_No]; Entry Date [Text, w80, Entry_Date]; From Cost Center [Text, w150, CostCenterName]; Debit A/c [Text, w150, Debit_Ac]; Credit A/c [Text, w150, Credit_Ac]; Amount [Text, w150, Amount]; Cheque No. [Text, w150, Chq_No]; Cheque Date [Text, w80, Chq_Date]; Clearing Date [Text, w80, Clg_Date]; Transfer Cost Coenter [Text, w150, Trn_CostCenterName]; Prepared By [Text, w100, PreparedBy]; Approved By [Text, w100, PostedBy]; Modified By [Text, w100, ModifiedBy]; Entry Date [Text, w125, entryDateTime]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHCrAccount` → `getTxtHAccount`
- `txtHDrAccount` → `getTxtHAccount`
- `txtHFromCostCenter` → `getTxtHFromCostCenter`
- `txtHTransferSite` → `getTxtHTransferSite`
- `narrationSearch` → `getFind`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `Amount` ← DmClient.vNull(txtAmount.getText(), "0.00")
- `ChaqNo` ← DmShared.xNull(txtChequeNo.getText())
- `CostCenterTag` ← DmShared.xNull(txtHFromCostCenter.getTag())
- `CostCenterText` ← DmShared.xNull(txtHFromCostCenter.getText())
- `CrAccountDesc` ← DmShared.xNull(txtHCrAccount.getTag2nd())
- `CrAccountTag` ← DmShared.xNull(txtHCrAccount.getTag())
- `CrAccountText` ← DmShared.xNull(txtHCrAccount.getText())
- `CurrentSiteTag` ← DmShared.xNull(txtCurrentSite.getTag())
- `CurrentSiteText` ← DmShared.xNull(txtCurrentSite.getText())
- `DrAccountDesc` ← DmShared.xNull(txtHDrAccount.getTag2nd())
- `DrAccountTag` ← DmShared.xNull(txtHDrAccount.getTag())
- `DrAccountText` ← DmShared.xNull(txtHDrAccount.getText())
- `DtCheque` ← DmShared.dNull(DmShared.convertDate(txtDtCheque.getText()))
- `DtClearing` ← DmShared.dNull(DmShared.convertDate(txtDtClearing.getText()))
- `DtEntry` ← DmShared.dNull(DmShared.convertDate(txtDtEntry.getText()))
- `WtaCode` ← txtWTACode.getText()
- `VoucherTypeCode` ← DmShared.xNull(txtDtEntry.getTag2nd())
- `SharedKeyFieldName` ← DmShared.xNull(txtEntryNo.getText())
- `Remark` ← DmShared.xNull(txtRemark.getText())
- `TrnCostCenterTag` ← DmShared.xNull(txtToCostCenter.getTag())
- `TrnCostCenterText` ← DmShared.xNull(txtToCostCenter.getText())
- `TrnCrAccountDesc` ← DmShared.xNull(txtTrnCrAccount.getTag2nd())
- `TrnCrAccountTag` ← DmShared.xNull(txtTrnCrAccount.getTag())
- `TrnCrAccountText` ← DmShared.xNull(txtTrnCrAccount.getText())
- `TrnDrAccountDesc` ← DmShared.xNull(txtTrnDrAccount.getTag2nd())
- `TrnDrAccountTag` ← DmShared.xNull(txtTrnDrAccount.getTag())
- `TrnDrAccountText` ← DmShared.xNull(txtTrnDrAccount.getText())
- `TrnRemark` ← DmShared.xNull(txtTrnRemark.getText())
- `TrnToSiteTag` ← DmShared.xNull(txtHTransferSite.getTag())
- `TrnToSiteText` ← DmShared.xNull(txtHTransferSite.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtAmount.setText` ← DmClient.vNull(data.getAmount(), "0.00")
- `txtChequeNo.setText` ← data.getChaqNo()
- `txtTrnCrAccount.setText` ← data.getTrnCrAccountText()
- `txtTrnCrAccount.setTag` ← data.getTrnCrAccountTag()
- `txtTrnCrAccount.setTag2nd` ← data.getTrnCrAccountDesc()
- `txtCurrentSite.setTag` ← data.getCurrentSiteTag()
- `txtCurrentSite.setText` ← data.getCurrentSiteText()
- `txtTrnDrAccount.setTag` ← data.getTrnDrAccountTag()
- `txtTrnDrAccount.setText` ← data.getTrnDrAccountText()
- `txtDtCheque.setText` ← data.getDtCheque()
- `txtDtClearing.setText` ← data.getDtClearing()
- `txtDtEntry.setText` ← data.getDtEntry()
- `txtDtEntry.setTag` ← data.getVoucherPrefix()
- `txtDtEntry.setTag2nd` ← DmShared.xNull(data.getVoucherTypeCode())
- `txtEntryNo.setText` ← data.getSharedKeyFieldName()
- `txtEntryNo.setTag` ← DmShared.iNull(data.getObjRecId().getCounter()).toString()
- `txtEntryNo.setTag2nd` ← DmShared.xNull(data.getObjRecId().getPrefix())
- `txtHCrAccount.setTag` ← data.getCrAccountTag()
- `txtHCrAccount.setTag2nd` ← data.getCrAccountDesc()
- `txtHCrAccount.setText` ← data.getCrAccountText()
- `txtHDrAccount.setTag` ← data.getDrAccountTag()
- `txtHDrAccount.setTag2nd` ← data.getDrAccountDesc()
- `txtHDrAccount.setText` ← data.getDrAccountText()
- `txtHFromCostCenter.setTag` ← data.getCostCenterTag()
- `txtHFromCostCenter.setText` ← data.getCostCenterText()
- `txtHTransferSite.setTag` ← data.getTrnToSiteTag()
- `txtHTransferSite.setText` ← data.getTrnToSiteText()
- `txtRemark.setText` ← data.getRemark()
- `txtToCostCenter.setTag` ← data.getTrnCostCenterTag()
- `txtToCostCenter.setText` ← data.getTrnCostCenterText()
- `txtTrnRemark.setText` ← data.getTrnRemark()
- `txtWTACode.setText` ← DmShared.xNull(data.getWtaCode())
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- `userControl.setTextBlog` ← approvedByBlog, data.getApprovedBy()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())
- userControl.setTextBlog(approvedByBlog, data.getApprovedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtCurrentSite.setEnabled(false)`
- `txtToCostCenter.setEnabled(false)`
- `txtTrnCrAccount.setEnabled(false)`
- `txtTrnDrAccount.setEnabled(false)`
- `txtTrnRemark.setEnabled(false)`

**On New click:**
- `clear()`
- `txtDtEntry.setTag2nd(defaultVoucherType)`
- `txtDtEntry.setFocus(true)`

**`manageDisplay()` logic (condensed code):**
```java
txtCurrentSite.setEnabled(false);
txtToCostCenter.setEnabled(false);
txtTrnCrAccount.setEnabled(false);
txtTrnDrAccount.setEnabled(false);
txtTrnRemark.setEnabled(false);
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
SrvAsync.invokeRPC().getEditCheck(DmShared.xNull(topControl.getDocId()), new AsyncCallback<DmResult<String>>() {
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(FundTransferEntry.this);
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

`txtHCrAccount.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus) {
txtHCrAccount.setTag2nd(tRow.getNature());
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

`txtHDrAccount.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus) {
txtHDrAccount.setTag2nd(tRow.getNature());
```

`txtHFromCostCenter.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHFromCostCenter(helper, blnToEndOfFile, (String) txtHTransferSite.getTag(), ne
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHFromCostCenter).show();
public void onSuccess(DmResultPageing<List<TxtHFromCostCenter_Mdl>> result) {
if (result.isStatus()) {
txtHFromCostCenter.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHFromCostCenter).show();
```

`txtHFromCostCenter.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHFromCostCenter.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHFromCostCenter).show();
```

`txtHTransferSite.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHTransferSite(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<T
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHTransferSite).show();
public void onSuccess(DmResultPageing<List<TxtHTransferSite_Mdl>> result) {
if (result.isStatus()) {
txtHTransferSite.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTransferSite).show();
```

`txtHTransferSite.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHTransferSite.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTransferSite).show();
```

`txtHTransferSite.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus) {
txtHDrAccount.setTag(tRow.getSubCode());
txtHDrAccount.setText(tRow.getAccountName());
txtHCrAccount.setTag("");
txtHCrAccount.setText("");
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

**Top control options:** setOtherRequired(false), setRefreshRequired(false), setDisplay(true)

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."

## Files
| file | lines |
|---|---|
| client/FundTransferEntry.java | 813 |
| client/FundTransferEntry_Dsg.java | 556 |
| server/FundTransferEntry_SrvImpl.java | 1284 |
| shared/Find_Mdl.java | 207 |
| shared/FundTransferEntry_Mdl.java | 321 |
| shared/FundTransferEntry_Srv.java | 42 |
| shared/FundTransferEntry_SrvAsync.java | 42 |
| shared/FundTransferEntry_Vld.java | 39 |
| shared/TxtHAccounts_Mdl.java | 41 |
| shared/TxtHFromCostCenter_Mdl.java | 35 |
| shared/TxtHTransferSite_Mdl.java | 54 |

## UI labels / columns (client)
`Remark`, `Entry No.`, `Entry Date`, `Current Site Name`, `Transfer To Site Name`, `From Cost Center`, `Debit Account`, `Credit Account`, `Transfer Amount`, `Cheque No.`, `Cheque Date`, `Clearing Date`, `In Transit Detail`, `Transfer To Cost Center`

## Models (shared DTOs)
### TxtHFromCostCenter_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | Site |

### TxtHAccounts_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityName |
| String | nature |

### TxtHTransferSite_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | City |
| String | SubCode |
| String | AccountName |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | VDate |
| String | VType |
| String | CostCenter |
| String | CrAccount |
| String | DrAccount |
| String | Amount |
| String | ChaqNo |
| String | DtCheque |
| String | DtClearing |
| String | Narration |
| String | TransToSite |
| String | TrnRecId |
| String | TrnVType |
| String | TrnCostCenter |
| String | TrnCrAccount |
| String | TrnDrAccount |
| String | approvedBy |
| String | preparedBy |
| String | modifiedBy |
| String | entryDtTime |

### FundTransferEntry_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| RecId | objRecId |
| String | voucherPrefix |
| String | voucherTypeName |
| String | voucherTypeCode |
| String | DtEntry |
| String | CurrentSiteTag |
| String | CurrentSiteText |
| String | CostCenterTag |
| String | CostCenterText |
| String | CrAccountTag |
| String | CrAccountText |
| String | CrAccountDesc |
| String | DrAccountTag |
| String | DrAccountText |
| String | DrAccountDesc |
| String | Amount |
| String | ChaqNo |
| String | DtCheque |
| String | DtClearing |
| String | Remark |
| String | TrnCostCenterText |
| String | TrnCostCenterTag |
| String | TrnCrAccountTag |
| String | TrnCrAccountText |
| String | TrnCrAccountDesc |
| String | TrnDrAccountTag |
| String | TrnDrAccountText |
| String | TrnDrAccountDesc |
| String | TrnRemark |
| String | TrnToSiteText |
| String | TrnToSiteTag |
| String | approvedBy |
| ModifyReason_Mdl | modifyReasonMdl |
| String | wtaCode |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator | UserPermission userPermission, String vPrefix | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<FundTransferEntry_Mdl> |
| saveRecord | FundTransferEntry_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl modifyReasonMdl | DmResult<String> |
| getFind | UserPermission userPermission, DmHelper pageing, boolean blnToEndOfFile, String vPrefix | DmResultPageing<List<Find_Mdl>> |
| getTxtHFromCostCenter | DmHelper pageing, boolean blnToEndOfFile, String strSiteCode | DmResultPageing<List<TxtHFromCostCenter_Mdl>> |
| getTxtHFromCostCenter | DmHelper pageing, boolean blnToEndOfFile, String strSiteCode, boolean blnInsert | DmResultPageing<List<TxtHFromCostCenter_Mdl>> |
| getTxtHAccount | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHAccounts_Mdl>> |
| setApproval | String approvedBy, boolean isApprove, String docId | DmResult<String> |
| getTxtHTransferSite | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHTransferSite_Mdl>> |
| getVoucherType |  | DmResult<String> |
| getCurrentSite |  | DmResult<KeyFieldExtended> |
| getEditCheck | String docId | DmResult<String> |

## Validator `FundTransferEntry_Vld`
```java
public class FundTransferEntry_Vld {
    public static void chkSave(FundTransferEntry_Mdl mrTemp) {
        Date dtCheque = DmShared.convertDate(mrTemp.getDtCheque());
        Date dtClearing = DmShared.convertDate(mrTemp.getDtClearing());
        DmShared.isValidText(mrTemp.getDtEntry(), "Entry Date", 100);
        DmShared.isValidText(mrTemp.getCurrentSiteTag(), "Current Site", 200);
        DmShared.isValidText(mrTemp.getTrnToSiteTag(), "Transfer site", 300);
        DmShared.isValidText(mrTemp.getDrAccountTag(), "Debit A/c", 400);
        DmShared.isValidText(mrTemp.getCrAccountTag(), "Credit A/c", 500);
        DmShared.isValidAmount(mrTemp.getAmount(), "Transfer Amount", 600);
        if (!(DmShared.dNull(mrTemp.getDtCheque())).equalsIgnoreCase("") && !DmShared.dNull(mrTemp.getDtClearing()).equals("")) {
            if (dtCheque.after(dtClearing)) {
                throw new DmException(600, "Clearing date can't be less than cheque data.");
            }
        }
    }
}
```

## Server `FundTransferEntry_SrvImpl.java` (1284 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.checkFinancialYearDate`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getLocalIPAddresses`, `DmServer.getRecId`, `DmServer.getServerTimestamp`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserViewCondition`, `DmServer.getUserWithDateTime`, `DmServer.prepareContraText`, `DmServer.setSsnOldData`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(UserPermission userPermission, String vPrefix)`

- SQL 1: `SELECT lm.DocId AS SearchCode FROM LedgerM lm left join voucher_type vt on lm.v_type = vt.v_type WHERE vt.category = {…} AND lm.V_Prefix = :vPrefix AND lm.Site_Code = :siteCode`
- Params: `:vPrefix` ← `vPrefix`; `:siteCode` ← `siteCode`

### `getMoveRec`
`public DmResult<FundTransferEntry_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT LM.V_Prefix,LM.PreparedBy,LM.PostedBy,LM.PostedByDateTime,LM.U_Name,LG.DocID,LG.RecID, lg.recIdPrefix, lg.v_No, LG.V_Date, LG.V_Type,LG.Narration,LG.AmtCr,LG.SubCode,LG.ContraSub,LG.Site_Code,isNull(LG.Chq_No,'') AS Chq_No,LG.Chq_Date, LG.Clg_Date,VT.Description,SGC.Name AS CreditPartyName,SGC.Nature AS CreditPartyNature,SGD.Name AS DebitPartyName,SM.Name AS SiteName, SGD.Nature AS DebitPartyNature,CCM.Name AS CostCenterName,LGC.CostCenter,LM.LGDocId, TLM.RecId AS TLMRecId,TLM.V_Type AS TLMV_Type,TLM.SubCode AS TLMSubCode,TLM.ContraSub AS TLMContraSub, TLM.CreditPartyName AS TLMCreditPartyName,TLM.DebitPartyName AS TLMDebitPartyName,LM.To_Site_Code,TSM.Name AS TSName, TLM.CosetSenterName AS TLMCosetSenterName,TLM.CostCenter AS TLMCostCenter,TLM.Narration AS TLMNarration, LM.u_Name,LM.preparedDt,LM.preparedByIP,LM.u_EntDt, lm.wtaCode FROM LedgerM LM LEFT JOIN Ledger LG ON LG.DocID = LM.DocID LEFT JOIN LedgerCostCenter LGC ON LGC.DocID = LG.DocId AND LGC.V_SNo = LG.V_SNo LEFT JOIN SubGroup SGC ON SGC.SubCode = LG.SubCode LEFT JOIN SubGroup SGD ON SGD.SubCode = LG.ContraSub LEFT JOIN SiteMast SM ON SM.Code = LG.Site_Code LEFT JOIN CostCenterMast CCM ON CCM.Code = LGC.CostCenter LEFT JOIN LedgerM LMT ON LMT.LGDocId = LM.DocId LEFT JOIN SiteMast TSM ON TSM.Code = LM.To_Site_Code LEFT JOIN Voucher_Type VT ON VT.V_Type = LG.V_Type LEFT JOIN (SELECT LG.DocId,LG.RecId,LG.V_Type,LG.SubCode,LG.ContraSub,LG.Site_Code, SGC.Name AS CreditPartyName,SGD.Name AS DebitPartyName,SM.Name AS SiteName, CCM.Name AS CosetSenterName,LGC.CostCenter,LG.Narration FROM Ledger LG LEFT JOIN LedgerCostCenter LGC ON LGC.DocID = LG.DocId AND LGC.V_SNo = LG.V_SNo LEFT JOIN SubGroup SGC ON SGC.SubCode = LG.SubCode LEFT JOIN SubGroup SGD ON SGD.SubCode = LG.ContraSub LEFT JOIN SiteMast SM ON SM.Code = LG.Site_Code LEFT JOIN CostCenterMast CCM ON CCM.Code = LGC.CostCenter WHERE isNull(LG.AmtCr, 0) > 0 ) TLM ON TLM.DocId = LMT.DocId WHERE isNull(LG.AmtCr, 0) > 0 AND LG.DocID = :docId`
- Params: `:docId` ← `DmShared.iNull(strKey).toString()`

### `saveRecord`
`public DmResult<String> saveRecord(FundTransferEntry_Mdl mrTemp)`

- SQL 1: `DELETE FROM LedgerCostCenter WHERE DocID = :docID`
- SQL 2: `DELETE FROM Ledger WHERE DocID = :docID`
- SQL 3: `INSERT INTO ledgerM (DocId, RecId, recIdPrefix, v_No, V_Type, V_Prefix, V_Date, Narration, Site_Code, To_Site_Code, SubCode, PreparedBy, preparedDt, preparedByIP, wtaCode) VALUES ( :docID, :recId, :recIdPrefix, :v_No, :vType, :vPrefix, :vDate, :narration, :siteCode, :toSiteCode, :subCode, :userName, :preparedDt, :preparedByIP, :wtaCode) ;`
- SQL 4: `UPDATE ledgerM Set V_Date = :vDate , SubCode = :subCode , Narration = :narration , To_Site_Code = :toSiteCode , U_Name = :userName , U_EntDt = :uEntDt WHERE DocId = :docId`
- SQL 5: `INSERT INTO Ledger ( DocId, RecId, V_SNo, V_Date, SubCode, ContraSub, AmtDr, AmtCr, Narration, V_Type, V_Prefix, Site_Code, Chq_No, Chq_Date, ContraText, Clg_Date, favourOf, recIdPrefix, v_No ) VALUES ( :docId, :recId, :vSno, :vDate, :subCode, :contraSub, NULL, :amtCr, :narration, :vType, :vPrefix, :siteCode, :chqNo, :chqDate, :contraText, :clgDate, :favourOf, :recIdPrefix, :v_No)`
- SQL 6: `INSERT INTO Ledger ( DocId, RecId, V_SNo, V_Date, SubCode, ContraSub, AmtDr, AmtCr, Narration, V_Type, V_Prefix, Site_Code, Chq_No, Chq_Date, ContraText, Clg_Date,favourOf, recIdPrefix, v_No ) VALUES ( :docId, :recId, :vSno, :vDate, :subCode, :contraSub, :amtDr, NULL, :narration, :vType, :vPrefix, :siteCode, :chqNo, :chqDate, :contraText, :clgDate,:favourOf, :recIdPrefix, :v_No)`
- Params: `:docID` ← `mrTemp.getSharedKeyFieldCode()`; `:docID` ← `mrTemp.getSharedKeyFieldCode()`; `:docID` ← `mrTemp.getSharedKeyFieldCode()`; `:recId` ← `mrTemp.getSharedKeyFieldName()`; `:recIdPrefix` ← `DmShared.xNull(mrTemp.getObjRecId().getPrefix())`; `:v_No` ← `DmShared.iNull(mrTemp.getObjRecId().getCounter()).toString()`; `:vType` ← `vType`; `:vPrefix` ← `vPrefix`; `:vDate` ← `DmServer.convertDate(mrTemp.getDtEntry())`; `:narration` ← `DmShared.xNull(mrTemp.getRemark())`; `:siteCode` ← `DmShared.foriegnKey(mrTemp.getCurrentSiteTag())`; `:userName` ← `userName`; `:toSiteCode` ← `DmShared.xNull(mrTemp.getTrnToSiteTag())`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getCrAccountTag())`; `:preparedDt` ← `timestamp`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:wtaCode` ← `DmShared.foriegnKey(mrTemp.getWtaCode())`; `:vDate` ← `DmServer.convertDate(mrTemp.getDtEntry())`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getCrAccountTag())`; `:narration` ← `DmShared.xNull(mrTemp.getRemark())`; `:toSiteCode` ← `DmShared.foriegnKey(mrTemp.getTrnToSiteTag())`; `:userName` ← `userName`; `:docId` ← `mrTemp.getSharedKeyFieldCode()`; `:uEntDt` ← `timestamp`; `:docId` ← `mrTemp.getSharedKeyFieldCode()`; `:recId` ← `mrTemp.getSharedKeyFieldName()`; `:recIdPrefix` ← `DmShared.xNull(mrTemp.getObjRecId().getPrefix())`; `:v_No` ← `DmShared.iNull(mrTemp.getObjRecId().getCounter()).toString()`; `:vSno` ← `intVSno`; `:vDate` ← `DmServer.convertDate(mrTemp.getDtEntry())`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getCrAccountTag())`; `:contraSub` ← `DmShared.foriegnKey(mrTemp.getDrAccountTag())`; `:amtCr` ← `DmServer.vNull(mrTemp.getAmount(), "0.00")`; `:narration` ← `DmShared.xNull(mrTemp.getRemark())`; `:vType` ← `vType`; `:vPrefix` ← `vPrefix`; `:siteCode` ← `DmShared.foriegnKey(mrTemp.getCurrentSiteTag())`; `:chqNo` ← `DmShared.xNull(mrTemp.getChaqNo())`; `:chqDate` ← `DmServer.convertDate(mrTemp.getDtCheque())`; `:contraText` ← `contraText`; `:clgDate` ← `DmServer.convertDate(mrTemp.getDtClearing())`; `:favourOf` ← `DmShared.xNull(mrTemp.getDrAccountText())`; `:docId` ← `mrTemp.getSharedKeyFieldCode()`; `:recId` ← `mrTemp.getSharedKeyFieldName()`; `:recIdPrefix` ← `DmShared.xNull(mrTemp.getObjRecId().getPrefix())`; `:v_No` ← `DmShared.iNull(mrTemp.getObjRecId().getCounter()).toString()`; `:vSno` ← `intVSno`; `:vDate` ← `DmServer.convertDate(mrTemp.getDtEntry())`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getDrAccountTag())`; `:contraSub` ← `DmShared.foriegnKey(mrTemp.getCrAccountTag())`; `:amtDr` ← `DmServer.vNull(mrTemp.getAmount(), "0.00")`; `:narration` ← `mrTemp.getRemark()`; `:vType` ← `vType`; `:vPrefix` ← `vPrefix`; `:siteCode` ← `DmShared.foriegnKey(mrTemp.getCurrentSiteTag())`; `:chqNo` ← `mrTemp.getChaqNo()`; `:chqDate` ← `DmServer.convertDate(mrTemp.getDtCheque())`; `:contraText` ← `contraText`; `:clgDate` ← `DmServer.convertDate(mrTemp.getDtClearing())`; `:favourOf` ← `DmShared.xNull(mrTemp.getDrAccountText())`; `:fromCostCenter` ← `DmShared.foriegnKey(mrTemp.getCostCenterTag())`; `:docId` ← `mrTemp.getSharedKeyFieldCode()`
- Errors: "Invalid key."
- Other calls: `FundTransferEntry_Vld.chkSave`, `WalkThrough_Server.applyWalkThrough`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl modifyReasonMdl)`

- SQL 1: `SELECT v_date from LedgerM where docid = :docid`
- SQL 2: `SELECT COUNT(DocId) Cnt FROM LedgerM WHERE DocId = :docId AND isNull(PostedBy,'')<>''`
- SQL 3: `SELECT COUNT(*) Cnt FROM DataAudit WHERE DocId = :docId`
- SQL 4: `SELECT COUNT(Adj_DocId) Cnt FROM LedgerAdj WHERE Adj_DocId= :docId`
- SQL 5: `SELECT COUNT(*) Cnt FROM dataAudit WHERE DocId = :docId`
- SQL 6: `SELECT COUNT(DocId) Cnt FROM LedgerM WHERE LGDocId = :docId`
- SQL 7: `DELETE FROM LedgerCostCenter WHERE DocID = :docId`
- SQL 8: `DELETE FROM Ledger WHERE DocID = :docId`
- SQL 9: `DELETE FROM LedgerM WHERE DocID = :docId`
- Params: `:docid` ← `DmShared.xNull(strKey)`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`
- Errors: "Record Has Been Posted. You Can Not Edit/ Delete This Record." | "Record Has Been Audited. You Can Not Edit/ Delete This Record." | "Record Has Been Adjusted. You Can Not Edit/ Delete This Record." | "Record Has Been Transfered. You Can Not Edit/ Delete This Record." | "Record Used In Transit. You Can Not Edit / Delete This Record."

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile, String vPrefix)`

- SQL 1: `SELECT LM.DocId,LG.RecId AS Entry_No,LG.V_Type AS Entry_Type,LG.V_Date AS Entry_Date, LG.Narration AS Remark,LG.AmtCr AS Amount,isNull(LG.Chq_No,'') AS Chq_No,LG.Chq_Date, LG.Clg_Date,SGC.Name AS Credit_Ac,SGD.Name AS Debit_Ac, CCM.Name AS CostCenterName,TSM.Name AS Transfer_To_Site, TLM.RecId AS Trn_RecId,TLM.V_Type AS Trn_Type, LM.V_Date, vt.short_Name, LM.V_No, TLM.CreditPartyName AS Trn_Credit_Ac,TLM.DebitPartyName AS Trn_Debit_Ac, TLM.CosetSenterName AS Trn_CostCenterName,LM.PreparedBy,LM.U_Name As ModifiedBy,LM.PostedBy, FORMAT(lm.u_EntDt,'dd-MMM-yyyy HH.mm') AS entryDateTime FROM LedgerM LM LEFT JOIN Ledger LG ON LG.DocID = LM.DocID LEFT JOIN voucher_type vt on LG.v_type = vt.v_Type LEFT JOIN LedgerCostCenter LGC ON LGC.DocID = LG.DocId AND LGC.V_SNo = LG.V_SNo LEFT JOIN subgroup SGC ON SGC.SubCode = LG.SubCode LEFT JOIN subgroup SGD ON SGD.SubCode = LG.ContraSub LEFT JOIN costcentermast CCM ON CCM.Code = LGC.CostCenter LEFT JOIN LedgerM LMT ON LMT.LGDocId = LM.DocId LEFT JOIN sitemast TSM ON TSM.Code = LM.To_Site_Code LEFT JOIN ( SELECT LG.DocId,LG.RecId,LG.V_Type,LG.SubCode,LG.ContraSub,LG.Site_Code, SGC.Name AS CreditPartyName,SGD.Name AS DebitPartyName, CCM.Name AS CosetSenterName,LGC.CostCenter FROM Ledger LG LEFT JOIN LedgerCostCenter LGC ON LGC.DocID = LG.DocId AND LGC.V_SNo = LG.V_SNo LEFT JOIN subgroup SGC ON SGC.SubCode = LG.SubCode LEFT JOIN subgroup SGD ON SGD.SubCode = LG.ContraSub LEFT JOIN costcentermast CCM ON CCM.Code = LGC.CostCenter WHERE isNull(LG.AmtCr, 0) > 0 ) TLM ON TLM.DocId = LMT.DocId WHERE LM.Site_Code= :siteCode AND LM.V_Prefix = :vPrefix AND vt.category = {…} AND isNull(LG.AmtCr, 0) > 0`
- Params: `:siteCode` ← `siteCode`; `:vPrefix` ← `vPrefix`

### `getTxtHFromCostCenter`
`public DmResultPageing<List<TxtHFromCostCenter_Mdl>> getTxtHFromCostCenter(DmHelper helper, boolean blnToEndOfFile, String strSiteCode, boolean blnInsert)`

- SQL 1: `SELECT CCM.Code, CCM.Name, CCM.ManualCode, CCM.Site_Code,sm.name as siteName FROM costcentermast CCM left join siteMast as sm on sm.code = ccm.site_Code WHERE isNull(CCM.UnderCostCenter, '') = '' AND CCM.isActive = 1`
- Params: `:siteCode` ← `strSite`

### `getTxtHAccount`
`public DmResultPageing<List<TxtHAccounts_Mdl>> getTxtHAccount(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT SG.SubCode, SG.Name, isNull(CT.CityName, '') AS CityName, SG.ManualCode, SG.Nature ,sgn.name as natureName FROM subgroup SG left join subgroupNature as sgn on sgn.code= sg.nature LEFT JOIN city CT ON CT.CityCode = SG.CityCode LEFT JOIN subgroupsitelist SBSL ON SG.subCode=SBSL.subCode WHERE SBSL.Site_Code =:siteCode AND SG.isActive = 1`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`

### `getCurrentSite`
`public DmResult<KeyFieldExtended> getCurrentSite()`

- SQL 1: `SELECT SM.Code,SM.Name AS SiteName FROM sitemast SM LEFT JOIN subgroup SG ON SM.SubCode = SG.SubCode WHERE SM.Code = :code ORDER BY SM.Name`
- Params: `:code` ← `siteCode`

### `getTxtHTransferSite`
`public DmResultPageing<List<TxtHTransferSite_Mdl>> getTxtHTransferSite(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT SM.Code,SM.Name AS SiteName,SM.City,SM.SubCode,SG.Name AS ACName FROM sitemast SM LEFT JOIN subgroup SG ON SM.SubCode = SG.SubCode WHERE SM.Code <> :code`
- Params: `:code` ← `siteCode`

### `getEditCheck`
`public DmResult<String> getEditCheck(String docId)`

- SQL 1: `SELECT COUNT(DocId) Cnt FROM LedgerM WHERE DocId = :docId AND isNull(PostedBy,'')<>''`
- SQL 2: `SELECT COUNT(*) Cnt FROM DataAudit WHERE DocId = :docId`
- SQL 3: `SELECT COUNT(Adj_DocId) Cnt FROM LedgerAdj WHERE Adj_DocId= :docId`
- SQL 4: `SELECT COUNT(*) Cnt FROM dataAudit WHERE DocId = :docId`
- SQL 5: `SELECT COUNT(DocId) Cnt FROM LedgerM WHERE LGDocId = :docId`
- Params: `:docId` ← `DmShared.iNull(docId).toString()`; `:docId` ← `DmShared.iNull(docId).toString()`; `:docId` ← `DmShared.iNull(docId).toString()`; `:docId` ← `DmShared.iNull(docId).toString()`; `:docId` ← `DmShared.iNull(docId).toString()`
- Errors: "Record Has Been Posted. You Can Not Edit/ Delete This Record." | "Record Has Been Audited. You Can Not Edit/ Delete This Record." | "Record Has Been Adjusted. You Can Not Edit/ Delete This Record." | "Record Has Been Transfered. You Can Not Edit/ Delete This Record." | "Record Used In Transit. You Can Not Edit / Delete This Record."

### `getWalkThroughFor`
`private WalkThroughDefault_Mdl getWalkThroughFor(FundTransferEntry_Mdl mrTemp, DmRSSPrivate dmRSSPrivate)`


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
`dataman.accounts.fundTransferEntry.client`, `dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.narrationSearch.client`, `dataman.dmToolkit.openSourceReport.client`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `dataman.libraryMenu.self.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| deleteRecord | `if (rsTemp.getLong("Cnt") > 0)` | "Record Has Been Posted. You Can Not Edit/ Delete This Record." |
| deleteRecord | `if (rsTemp.getLong("Cnt") > 0)` | "Record Has Been Audited. You Can Not Edit/ Delete This Record." |
| deleteRecord | `if (rsTemp.getLong("Cnt") > 0)` | "Record Has Been Adjusted. You Can Not Edit/ Delete This Record." |
| deleteRecord | `if (rsTemp.getLong("Cnt") > 0)` | "Record Has Been Transfered. You Can Not Edit/ Delete This Record." |
| deleteRecord | `if (rsTemp.getLong("Cnt") > 0)` | "Record Used In Transit. You Can Not Edit / Delete This Record." |
| getEditCheck | `if (rsTemp.getLong("Cnt") > 0)` | "Record Has Been Posted. You Can Not Edit/ Delete This Record." |
| getEditCheck | `if (rsTemp.getLong("Cnt") > 0)` | "Record Has Been Audited. You Can Not Edit/ Delete This Record." |
| getEditCheck | `if (rsTemp.getLong("Cnt") > 0)` | "Record Has Been Adjusted. You Can Not Edit/ Delete This Record." |
| getEditCheck | `if (rsTemp.getLong("Cnt") > 0)` | "Record Has Been Transfered. You Can Not Edit/ Delete This Record." |
| getEditCheck | `if (rsTemp.getLong("Cnt") > 0)` | "Record Used In Transit. You Can Not Edit / Delete This Record." |

## Notes (hand-written)
_No hand-written notes yet._
