# pb-self-toolkit / bankDetailInfo

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/bankDetailInfo/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHBankAC
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `BankDetailInfo`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Bank Name | DmTextHelp | `txtHBankAC` | `TxtHBankAc_PrpAcs, 8` |
| 2 | A/C Holder Name | DmTextBox | `txtAcHolderName` | `20, false` |
| 3 | A/C No | DmTextBox | `txtAcNo` | `20, false` |
| 4 | IFSC Code | DmTextBox | `txtIFSCCode` | `20, false` |
| 5 | Branch | DmTextBox | `txtBranch` | `20, false` |
| 6 | Address | DmTextBox | `txtBranchAddress` | `20, false` |

Other widgets (not in tab list): `txtBankName` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `txtHBankAC`: Bank [Text, w100, bankName]; Account Name [Text, w170, acHolderName]; Account No. [Text, w130, accountNo]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHBankAC` → `getTxtHBankAC`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtHBankAC.setEnabled(flag)`
- `txtAcNo.setReadOnly(true)`
- `txtBranch.setReadOnly(true)`
- `txtIFSCCode.setReadOnly(true)`
- `txtBranchAddress.setReadOnly(true)`
- `txtBankName.setReadOnly(true)`
- `txtAcHolderName.setReadOnly(true)`

**`manageDisplay()` logic (condensed code):**
```java
boolean flag = false;
if (topControl != null) {
flag = topControl.getMode().equalsIgnoreCase("New") || topControl.getMode().equalsIgnoreCase("Edit");
txtHBankAC.setEnabled(flag);
txtAcNo.setReadOnly(true);
txtBranch.setReadOnly(true);
txtIFSCCode.setReadOnly(true);
txtBranchAddress.setReadOnly(true);
txtBankName.setReadOnly(true);
txtAcHolderName.setReadOnly(true);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHBankAC.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHBankAC(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHBan
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHBankAC).show();
public void onSuccess(DmResultPageing<List<TxtHBankAc_Mdl>> result) {
if (result.isStatus()) {
resetTxtHBankAc();
txtHBankAC.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBankAC).show();
```

`txtHBankAC.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
resetTxtHBankAc();
txtHBankAC.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBankAC).show();
```

`txtHBankAC.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
txtAcNo.setText(DmShared.xNull(t1Row.getAccountNo()));
txtBranch.setText(DmShared.xNull(t1Row.getBranch()));
txtIFSCCode.setText(DmShared.xNull(t1Row.getIfscCode()));
txtBranchAddress.setText(DmShared.xNull(t1Row.getBranchAddress()));
txtBankName.setText(DmShared.xNull(t1Row.getSharedKeyFieldName()));
txtAcHolderName.setText(DmShared.xNull(t1Row.getAcAccountHolder()));
callBack.onChange(fillData());
```

## Files
| file | lines |
|---|---|
| client/BankDetailInfo.java | 219 |
| client/BankDetailInfo_Dsg.java | 193 |
| server/BankDetailInfo_Server.java | 91 |
| server/BankDetailInfo_SrvImpl.java | 111 |
| shared/BankDetailInfo_Mdl.java | 58 |
| shared/BankDetailInfo_Srv.java | 14 |
| shared/BankDetailInfo_SrvAsync.java | 14 |
| shared/TxtHBankAc_Mdl.java | 58 |

## UI labels / columns (client)
`Bank Name`, `A/C Holder Name`, `A/C No`, `IFSC Code`, `Branch`, `Address`

## Models (shared DTOs)
### TxtHBankAc_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | accountNo |
| String | acAccountHolder |
| String | branch |
| String | branchAddress |
| String | ifscCode |

### BankDetailInfo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | accountNo |
| String | acAccountHolder |
| String | branch |
| String | branchAddress |
| String | ifscCode |

## RPC interface
| method | params | returns |
|---|---|---|
| getBankDetail | String voucherCategory, String bankAcCode | DmResult<BankDetailInfo_Mdl> |
| getTxtHBankAC | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHBankAc_Mdl>> |

## Server `BankDetailInfo_SrvImpl.java` (111 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmShared.xNull`

### `getTxtHBankAC`
`public DmResultPageing<List<TxtHBankAc_Mdl>> getTxtHBankAC(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT CONCAT(bd.subCode, '#', bd.sno) AS bankAcCode , bd.acHolderName, bd.accountNo, bd.bankName , bd.ifscCode, bd.branch, bd.branchAddress FROM bankDetail bd LEFT JOIN subGroup sg ON sg.subCode = bd.subCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.manualCode = 'BK'`

### `getBankDetail`
`public DmResult<BankDetailInfo_Mdl> getBankDetail(String voucherCategory, String bankAcCode)`

- Other calls: `BankDetailInfo_Server.getBankAcDetail`, `BankDetailInfo_Server.getDefaultBankAcCode`

## Server `BankDetailInfo_Server.java` (91 lines)
Helpers used: `DmShared.xNull`

### `getDefaultBankAcCode`
`public static String getDefaultBankAcCode(String voucherCategory, Connection cnTemp) throws Exception`

- SQL 1: `SELECT vt.bankAcCode FROM voucher_Type vt WHERE vt.category = :voucherCategory`
- Params: `:voucherCategory` ← `DmShared.xNull(voucherCategory)`

### `getBankAcDetail`
`public static BankDetailInfo_Mdl getBankAcDetail(String bankAcCode, Connection cnTemp) throws Exception`

- SQL 1: `SELECT CONCAT(bd.subCode, '#', bd.sno) AS bankAcCode , bd.acHolderName, bd.accountNo, bd.bankName, bd.ifscCode, bd.branch , bd.branchAddress FROM bankDetail bd WHERE CONCAT(bd.subCode, '#', bd.sno) = :code`
- Params: `:code` ← `DmShared.xNull(bankAcCode)`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `paperbiz.selfToolkit.bankDetailInfo.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
Bank detail popup (`getBankDetail`, `getTxtHBankAC`).
