# dm-accounts / bankAccountDetail

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/accounts/src/bankAccountDetail/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: bankDetail
- Update tables: bankDetail
- Delete cascade (in order): bankDetail
- Duplicate checks on save:
  - `select count(*) as cnt from bankDetail where subCode =:subCode and sNo <> :sNo and isDefault=1`
  - `select count(*) as cnt from bankDetail where accountNo = :accountNo and subCode = :subCode and bankName= :bank and sNo<>:sNo`
  - `SELECT COUNT(*) as cnt FROM bankDetail bd LEFT JOIN subGroup sg ON sg.subCode = bd.subCode WHERE bd.subCode = :subCode AND bd.sNo<>:sNo AND sg.nature = :bank`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Link With A/c (Text); Account No. (Text); Bank (Text); Branch (Text); Default (Text); Account Holder Name (Text)
- Lookups/suggestions: getTxtHLinkWithAccount
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `BankAccountDetail`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Link With A/c | DmTextHelp | `txtHAccount` | `txtHLinkWithAccount_PrpAcs, 7, true` |
| 2 | Account Holder Name | DmTextBox | `txtAccountHolderName` | `100, true` |
| 3 | Account No. | DmTextBox | `txtAccountNo` | `20, true` |
| 4 | A/C Type | DmTextHelp | `txtHAcType` | `KeyField_PrpAcs, 5, true` |
| 5 | Bank | DmTextBox | `txtBankName` | `35, true` |
| 6 | I Bank | DmTextBox | `txtIBankName` | `100, true` |
| 7 | Branch | DmTextBox | `txtBankBranch` | `35, true` |
| 8 | Branch Address | DmRemarkTextBox | `txtBankBranchAddress` | `250, false` |
| 9 | UPI | DmTextBox | `txtUPI` | `250, false` |
| 10 | Swift No. | DmTextBox | `txtSwiftCode` | `15, false` |
| 11 | IFSC Code | DmTextBox | `txtIFSCCode` | `11, false` |

Other widgets (not in tab list): `txtHDefault` (DmTextHelp), `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `txtHDefault`: Type [Text, w0]
- `txtHAcType`: AC Type Name [Text, w0, name]
- `txtHAccount`: Name [Text, w0, NAME]; Manual Code [Text, w150, manualCode]; City [Text, w150, City]
- `dmFind`: Link With A/c [Text, w100, accountName]; Account No. [Text, w130, accountNo]; A/C Type [Text, w100, acType]; Bank [Text, w150, bankName]; Branch [Text, w150, branch]; Bank Address [Text, w300, bankAddress]; UPI [Text, w150, UPI]; Swift No. [Text, w100, swiftCode]; IFSC Code [Text, w90, ifscCode]; Default [Text, w30, isDefault]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHAccount` → `getTxtHLinkWithAccount`
- `txtHDefault` → `getYesNo`
- `txtHAcType` → `getAcType`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `sNo` ← txtHAccount.getTag2nd()
- `AccountCode` ← DmShared.xNull(txtHAccount.getTag())
- `SharedKeyFieldName` ← DmShared.xNull(txtHAccount.getText())
- `AccountNo` ← DmShared.xNull(txtAccountNo.getText())
- `AcTypeCode` ← DmShared.xNull(txtHAcType.getTag())
- `AcType` ← DmShared.xNull(txtHAcType.getText())
- `UPI` ← DmShared.xNull(txtUPI.getText())
- `BankName` ← DmShared.xNull(txtBankName.getText())
- `InBankName` ← DmShared.xNull(txtIBankName.getText())
- `AccountHolderName` ← DmShared.xNull(txtAccountHolderName.getText())
- `BankBranch` ← DmShared.xNull(txtBankBranch.getText())
- `BankBranchAddress` ← DmShared.xNull(txtBankBranchAddress.getText())
- `SwiftCode` ← DmShared.xNull(txtSwiftCode.getText())
- `IFSCCode` ← DmShared.xNull(txtIFSCCode.getText())
- `DefaultTag` ← DmShared.xNull(txtHDefault.getTag())
- `DefaultText` ← DmShared.xNull(txtHDefault.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtHAccount.setTag` ← data.getAccountCode()
- `txtHAccount.setText` ← data.getSharedKeyFieldName()
- `txtHAccount.setTag2nd` ← data.getsNo()
- `txtAccountHolderName.setText` ← DmShared.xNull(data.getAccountHolderName())
- `txtAccountNo.setText` ← data.getAccountNo()
- `txtHAcType.setTag` ← data.getAcTypeCode()
- `txtHAcType.setText` ← data.getAcType()
- `txtUPI.setText` ← data.getUPI()
- `txtBankName.setText` ← data.getBankName()
- `txtIBankName.setText` ← data.getInBankName()
- `txtBankBranch.setText` ← data.getBankBranch()
- `txtBankBranchAddress.setText` ← data.getBankBranchAddress()
- `txtSwiftCode.setText` ← data.getSwiftCode()
- `txtIFSCCode.setText` ← data.getIFSCCode()
- `txtHDefault.setTag` ← data.getDefaultTag()
- `txtHDefault.setText` ← data.getDefaultText()

**On New click:**
- `clear()`
- `getTempKeyCode()`
- `txtHAccount.setFocus(true)`
- `txtHDefault.setEnabled(true)`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
clear();
getTempKeyCode();
txtHAccount.setFocus(true);
txtHDefault.setEnabled(true);
```

`topControl.addEditHandeller` → `editClicked()`
```java
if (!DmShared.xNull(topControl.getDocId()).equalsIgnoreCase("")) {
txtAccountNo.setFocus(true);
txtHDefault.setEnabled(true);
txtHAccount.setEnabled(false);
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

`topControl.addSaveHandeller` → `saveClicked()`
```java
if (topControl.getMode().equalsIgnoreCase("Edit")) {
showModifyReason();
} else {
topControlSave();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(BankAccountDetail.this);
```

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
```

`modifyReason.addCallBack` → `onOk()`
```java
try {
modifyReasonMdl = modifyReason;
if (topControl.getMode().equalsIgnoreCase("Browse") && !DmShared.xNull(topControl.getDocId()).trim().equalsIgnoreCase(""
topControlDelete(topControl.getDocId());
} else {
topControlSave();
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), (DmTextBox) null).show();
```

`txtHAccount.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHLinkWithAccount(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<Lis
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHAccount).show();
public void onSuccess(DmResultPageing<List<TxtHLinkWithAccount_Mdl>> result) {
if (result.isStatus()) {
txtHAccount.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAccount).show();
```

`txtHAccount.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAccount.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAccount).show();
```

`txtHAccount.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHAccount.getTag()).equalsIgnoreCase(txtHAccount.getTag2nd())) {
resetAccountHolder();
txtHAccount.setTag2nd(DmShared.xNull(txtHAccount.getTag()));
txtAccountHolderName.setText(tRow.getSharedModifiedBy());
```

`txtHDefault.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExte
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDefault).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHDefault.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDefault).show();
```

`txtHDefault.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHDefault.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDefault).show();
```

`txtHAcType.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getAcType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExt
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHAcType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHAcType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAcType).show();
```

`txtHAcType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAcType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAcType).show();
```

`dmFind.addHelpGridCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getFind(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Find_Mdl>>>()
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

**Top control options:** setOtherRequired(true), setRefreshRequired(false), setDisplay(true)

**Menu items (Other button):** Mange Files

_Print permission "P" is removed for this screen (replace "P" with "*")._

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."

## Files
| file | lines |
|---|---|
| client/BankAccountDetail.java | 665 |
| client/BankAccountDetail_Dsg.java | 424 |
| server/BankAccountDetail_SrvImpl.java | 840 |
| shared/BankAccountDetail_Mdl.java | 178 |
| shared/BankAccountDetail_Srv.java | 32 |
| shared/BankAccountDetail_SrvAsync.java | 32 |
| shared/BankAccountDetail_Vld.java | 17 |
| shared/DefaultValue_Mdl.java | 25 |
| shared/Find_Mdl.java | 122 |
| shared/GrdContactPerson_Mdl.java | 50 |
| shared/TxtHCity_Mdl.java | 50 |
| shared/TxtHLinkWithAccount_Mdl.java | 32 |

## UI labels / columns (client)
`Link With A/c`, `Account Holder Name`, `Account No.`, `A/C Type`, `Bank`, `I Bank`, `Branch`, `Branch Address`, `UPI`, `Swift No.`, `IFSC Code`, `Default`

## Models (shared DTOs)
### GrdContactPerson_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | mobileNo |
| String | landlineNo |
| String | designation |
| String | isChecked |

### DefaultValue_Mdl
| type | field |
|---|---|
| DmFileRuleType | fileRuleTypeObj |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | sNo |
| String | accountNo |
| String | acType |
| String | acTypeCode |
| String | UPI |
| String | bankName |
| String | InBankName |
| String | bankBranch |
| String | bankBranchAddress |
| String | swiftCode |
| String | IFSCCode |
| String | defaultText |

### BankAccountDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | accountCode |
| String | sNo |
| String | accountNo |
| String | acType |
| String | acTypeCode |
| String | UPI |
| String | bankName |
| String | inBankName |
| String | bankBranch |
| String | bankBranchAddress |
| String | swiftCode |
| String | IFSCCode |
| String | defaultText |
| String | defaultTag |
| String | tempKeyCode |
| String | accountHolderName |
| ModifyReason_Mdl | modifyReasonMdl |
| DmSnapshot_Mdl | snapshot_Mdl |

### TxtHLinkWithAccount_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | ManualCode |
| String | City |

### TxtHCity_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | StateName |
| String | StateCode |
| String | countryCode |
| String | countryName |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<BankAccountDetail_Mdl> |
| saveRecord | BankAccountDetail_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl modifyReasonMdl | DmResult<String> |
| getTxtHLinkWithAccount | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHLinkWithAccount_Mdl>> |
| getFind | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getYesNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTempKeyCode |  | String |
| getDefaultValue |  | DmResult<DefaultValue_Mdl> |
| getAcType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `BankAccountDetail_Vld`
```java
public class BankAccountDetail_Vld {
    public static void chkSave(BankAccountDetail_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getAccountCode(), "Link With A/c", 100);
        DmShared.isValidText(mrTemp.getAccountNo(), "Account No.", 200);
        DmShared.isValidText(mrTemp.getBankName(), "Bank", 300);
        DmShared.isValidText(mrTemp.getBankBranch(), "Branch", 400);
        DmShared.isValidText(mrTemp.getDefaultTag(), "Default", 500);
        DmShared.isValidText(mrTemp.getAccountHolderName(), "Account Holder Name", 600);
    }
}
```

## Server `BankAccountDetail_SrvImpl.java` (840 lines)
Helpers used: `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getConnectionMongo`, `DmServer.getLocalIPAddresses`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnFileDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnUserName`, `DmServer.setSsnOldData`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `select concat(cast(bd.subcode as varchar(25)),'-',cast(bd.sNo as varchar(15))) as subcode from bankdetail bd left join subgroup as sg on bd.subcode=sg.subcode order by sg.name,bd.sNo`

### `getMoveRec`
`public DmResult<BankAccountDetail_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT bd.subCode,bd.acHolderName,sg.name as accountName,bd.sNo,bd.bankName, bd.iBankName,bd.accountNo,bd.branch,bd.acTypeCode, hm.name as acType,bd.UPI, bd.swiftCode,bd.isDefault,bd.ifscCode,bd.branchAddress,bd.preparedby,bd.preparedDt,bd.preparedByIP,bd.u_EntDt,bd.u_name FROM bankDetail as bd left join subgroup as sg on bd.subCode=sg.subcode left join hl7mast as hm on bd.acTypeCode=hm.code WHERE concat(cast(bd.subcode as varchar(25)),'-',cast(bd.sNo as varchar(15)))=:code`
- Params: `:code` ← `DmShared.xNull(strKey)`

### `saveRecord`
`public DmResult<String> saveRecord(BankAccountDetail_Mdl mrTemp)`

- SQL 1: `SELECT ISNULL(MAX(sNo),0) + 1 AS Mx FROM bankDetail WHERE subCode=:subCode`
- SQL 2: `select count(*) as cnt from bankDetail where subCode =:subCode and sNo <> :sNo and isDefault=1`
- SQL 3: `select count(*) as cnt from bankDetail where accountNo = :accountNo and subCode = :subCode and bankName= :bank and sNo<>:sNo`
- SQL 4: `SELECT COUNT(*) as cnt FROM bankDetail bd LEFT JOIN subGroup sg ON sg.subCode = bd.subCode WHERE bd.subCode = :subCode AND bd.sNo<>:sNo AND sg.nature = :bank`
- SQL 5: `Select Count(*) As Cnt From bankDetail where sNo = :sNo and SubCode=:subCode`
- SQL 6: `INSERT INTO bankDetail (subCode, sNo, bankName, iBankName, accountNo, acTypeCode, UPI, branch,swiftCode,isDefault,branchAddress,ifscCode,preparedBY,U_EntDt,preparedByIP,preparedDt,acHolderName) VALUES (:subCode, :sNo, :bankName, :iBankName, :accountNo, :acTypeCode, :UPI, :branch,:swiftCode,:isDefault,:branchAddress,:ifscCode,:preparedBY,:preparedDt,:preparedByIP,:preparedDt,:acHolderName)`
- SQL 7: `UPDATE bankDetail SET accountNo = :accountNo ,bankName = :bankName ,iBankName = :InBankName ,acTypeCode = :acTypeCode ,UPI = :UPI ,branch = :branch ,swiftCode = :swiftCode ,isDefault = :isDefault ,branchAddress = :branchAddress ,acHolderName = :acHolderName ,ifscCode = :ifscCode ,U_Name = :u_name ,U_EntDt = :U_EntDt WHERE concat(cast(subcode as varchar(25)),'-',cast(sNo as varchar(15))) =:code`
- Params: `:subCode` ← `DmShared.iNull(mrTemp.getAccountCode()).toString()`; `:subCode` ← `DmShared.iNull(mrTemp.getAccountCode()).toString()`; `:sNo` ← `DmShared.iNull(mrTemp.getsNo()).toString()`; `:accountNo` ← `DmShared.xNull(mrTemp.getAccountNo().trim())`; `:bank` ← `DmShared.xNull(mrTemp.getBankName().trim())`; `:subCode` ← `DmShared.iNull(mrTemp.getAccountCode()).toString()`; `:sNo` ← `DmShared.iNull(mrTemp.getsNo()).toString()`; `:subCode` ← `DmShared.iNull(mrTemp.getAccountCode()).toString()`; `:sNo` ← `DmShared.iNull(mrTemp.getsNo()).toString()`; `:bank` ← `DmServer.getCodeViaManualCode("BK", "manualCode", "subgroupNature", "Code", cnTemp)`; `:sNo` ← `mrTemp.getsNo()`; `:subCode` ← `DmShared.iNull(mrTemp.getAccountCode()).toString()`; `:subCode` ← `DmShared.iNull(mrTemp.getAccountCode()).toString()`; `:sNo` ← `mrTemp.getsNo()`; `:accountNo` ← `DmShared.xNull(mrTemp.getAccountNo())`; `:acTypeCode` ← `DmShared.foriegnKey(mrTemp.getAcTypeCode())`; `:UPI` ← `DmShared.xNull(mrTemp.getUPI())`; `:bankName` ← `DmShared.xNull(mrTemp.getBankName())`; `:iBankName` ← `DmShared.xNull(mrTemp.getInBankName())`; `:branch` ← `DmShared.xNull(mrTemp.getBankBranch())`; `:swiftCode` ← `DmShared.xNull(mrTemp.getSwiftCode())`; `:isDefault` ← `DmShared.xNull(mrTemp.getDefaultTag())`; `:branchAddress` ← `DmShared.xNull(mrTemp.getBankBranchAddress())`; `:ifscCode` ← `DmShared.xNull(mrTemp.getIFSCCode())`; `:acHolderName` ← `DmShared.xNull(mrTemp.getAccountHolderName())`; `:preparedBY` ← `DmServer.getSsnUserName(this)`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:preparedDt` ← `timestamp`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:accountNo` ← `DmShared.xNull(mrTemp.getAccountNo())`; `:acTypeCode` ← `DmShared.foriegnKey(mrTemp.getAcTypeCode())`; `:UPI` ← `DmShared.xNull(mrTemp.getUPI())`; `:bankName` ← `DmShared.xNull(mrTemp.getBankName())`; `:iBankName` ← `DmShared.xNull(mrTemp.getInBankName())`; `:branch` ← `DmShared.xNull(mrTemp.getBankBranch())`; `:swiftCode` ← `DmShared.xNull(mrTemp.getSwiftCode())`; `:isDefault` ← `DmShared.xNull(mrTemp.getDefaultTag())`; `:branchAddress` ← `DmShared.xNull(mrTemp.getBankBranchAddress())`; `:acHolderName` ← `DmShared.xNull(mrTemp.getAccountHolderName())`; `:ifscCode` ← `DmShared.xNull(mrTemp.getIFSCCode())`; `:u_name` ← `DmServer.getSsnUserName(this)`; `:U_EntDt` ← `timestamp`
- Errors: "Invalid key." | "Default 'Yes' for " | "Duplicate Record Not Allowed !!!"
- Other calls: `BankAccountDetail_Vld.chkSave`, `DmFileManager_Server.manageLinkFileCollection`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl modifyReasonMdl)`

- SQL 1: `DELETE FROM bankDetail WHERE concat(cast(subcode as varchar(25)),'-',cast(sNo as varchar(15))) = :code`
- SQL 2: `SELECT lfc.docId, lfc.sNo, lfc.ruleCode FROM linkFileCollection lfc WHERE concat(cast(docid as varchar(25)),'-',cast(sNo as varchar(15))) = :code`
- Params: `:code` ← `DmShared.xNull(strKey)`; `:code` ← `DmShared.xNull(strKey)`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT bd.subCode,sg.name as accountName,bd.sNo,bd.bankName,bd.acTypeCode, hl.name as acType bd.iBankName, bd.UPI, bd.accountNo,bd.branch, bd.swiftCode,bd.isDefault,bd.ifscCode,bd.branchAddress FROM bankDetail as bd left join subgroup as sg on bd.subCode=sg.subcode left join hl7Mast as hl on bd.acTypeCode=hl.code`

### `getTxtHLinkWithAccount`
`public DmResultPageing<List<TxtHLinkWithAccount_Mdl>> getTxtHLinkWithAccount(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT SG.SubCode, SG.Name, isNull(CT.CityName, '') AS City, SG.manualCode,SG.displayName FROM SubGroup SG LEFT JOIN City CT ON CT.CityCode = SG.CityCode WHERE SG.isActive = 1`

### `getYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getDefaultValue`
`public DmResult<DefaultValue_Mdl> getDefaultValue()`

- SQL 1: `select ftm.ruleCode , ftm.fileType , ftm.maxSize , ftm.extFilters , ftm.description from {…} .dbo.fileTypeMast ftm WHERE ruleCode = :ruleCode and isDefault =1`
- Params: `:ruleCode` ← `LibraryRuleCode.BANK_ACCOUNT_UPIQR.getRuleCode()`
- Other calls: `BANK_ACCOUNT_UPIQR.getRuleCode`

### `getMongoDBFileLink`
`private String getMongoDBFileLink(String ruleCode, String Docid, String Sno, Connection cnTemp) throws Exception`

- SQL 1: `Select fileLink from linkFileCollection where docId=' {…} ' and sNo=' {…} ' and ruleCode=' {…} '`

### `getAcType`
`public DmResultPageing<List<KeyFieldExtended>> getAcType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select hm.code, hm.name from hl7mast hm where tableName=' {…} '`
- Other calls: `BANK_ACCOUNT_TYPE.getCode`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmFileManager.shared`, `dataman.dmBase.dmFileManagerMongo.server`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFileManager.client`, `dataman.dmToolkit.dmFileManager.server`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmSnapshot.client`, `dataman.dmToolkit.dmSnapshot.shared`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `dataman.libraryMenu.self.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (DmShared.xNull(mrTemp.getsNo()).equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (DmShared.xNull(mrTemp.getSharedKeyFieldCode()).equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Default 'Yes' for " |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | " Account No. is already exists for " |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Duplicate Record Not Allowed !!!" |

## Notes (hand-written)
_No hand-written notes yet._
