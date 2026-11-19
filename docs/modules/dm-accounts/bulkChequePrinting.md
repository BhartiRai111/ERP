# dm-accounts / bulkChequePrinting

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/accounts/src/bulkChequePrinting/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: ledger
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Print Name (Text); Cheque Number (Text); Cheque Date (Text); Voucher No. (Text); Type (Text); rule: Grid Details are mandatory
- Lookups/suggestions: getTxtHVoucherType, getTxtHBankName, getTxtHAccountName, getTxtHVoucherNo, getTxtHRecordType
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `BulkChequePrinting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `ckMergeCheques` (CheckBox), `grdAccountDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `grdAccountDetail`:  [Text, w30]; Voucher No. [Text, w100, name]; Type [Text, w40, short_Name]; Date [Text, w80, v_Date]; Account [Text, w150, accountName]; Chq. No. [Text, w60, chq_No]; Chq. Date [Text, w80, chq_Date]; Amount [Text, w150, displayAmount]; Favour Of [Text, w250, printName];  [Text, w25]

**Event handlers (condensed code, CRUD flow and field callbacks):**

`chequeDetailsEdit.addCallBack` → `onOk()`
```java
grdAccountDetail.commitChanges();
changePrintName(grdDetails, isMerged);
if (DmShared.iNull(grdDetails.getChqNo()).longValue() > 0) {
chkNoLength = String.valueOf(grdDetails.getChqNo().length());
} else {
chkNoLength = String.valueOf(originalList.get(0).getChqNo().length());
```

`chequeDetailsEdit.addCallBack` → `onDelete()`
```java
grdAccountDetail.commitChanges();
```

`settings.addCallBack` → `onOk()`
```java
ckMergeCheques.setValue(false);
isMerged = false;
flagMergeChkBoxValue = false;
mrTemp = new BulkChequePrinting_Mdl();
mrTemp.setContraSub(settingValues.getContraSub());
mrTemp.setFromDate(settingValues.getFromDate());
mrTemp.setToDate(settingValues.getToDate());
mrTemp.setVoucherNo(settingValues.getVoucherNo());
mrTemp.setVoucherNoDocid(settingValues.getVoucherNoDocid());
mrTemp.setAccountCode(settingValues.getAccountCode());
...
```

`changeChqNo.addCallBack` → `onOk()`
```java
changeChqNo_Mdl = new ChangeChqNo_Mdl();
changeChqNo_Mdl.setChqDate(DmShared.dNull(values.getChqDate()));
changeChqNo_Mdl.setChqNo(DmShared.xNull(values.getChqNo()));
changeChqNo_Mdl.setIsCrossChqCode(DmShared.xNull(values.getIsCrossChqCode()));
changeChqNo_Mdl.setIsCrossChqText(DmShared.xNull(values.getIsCrossChqText()));
if (DmShared.iNull(values.getChqNo()).longValue() > 0) {
chkNoLength = String.valueOf(values.getChqNo().length());
} else {
chkNoLength = String.valueOf(originalList.get(0).getChqNo().length());
setChqNo_ChqDt();
...
```

`ckMergeCheques.addClickHandler` → `onClick()`
```java
mergeCheques();
```
- Alert: "Cannot Update if merge cheques is checked"
- Alert: "Cannot Update if merge cheques is checked"
- Alert: "new cheque no or date is mandatory"
- Alert: "Grid Details are mandatory"
- Alert: "Cheque No or Cheque Date is mandatory"
- Alert: "Details are mandatory"
- Alert: "From Date is mandatory"
- Alert: "To Date is mandatory"
- Alert: "Voucher Type is mandatory"
- Alert: "Bank Name is mandatory"
- Alert: "No record found"
- Alert: "Details in settings are mandatory"
- Alert: "From Date is mandatory"
- Alert: "To Date is mandatory"
- Alert: "Voucher Type is mandatory"
- Alert: "Bank Name is mandatory"
- Alert: "Grid Details Are Mandatory"

### Screen `ChangeChqNo`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Cheque No. | DmTextBox | `txtChequeNo` | `40, false` |
| 2 | Cheque Date | DmTextBox | `txtChequeDate` | `50, false` |
| 3 | Cross Cheque | DmTextHelp | `txtHYesNo` | `KeyField_PrpAcs, 3, true` |

**Grid / popup columns** (header, width, db field):
- `txtHYesNo`: Name [Text, w200]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHYesNo` → `getYesNo`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHYesNo.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExte
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHYesNo).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHYesNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHYesNo).show();
```

`txtHYesNo.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHYesNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHYesNo).show();
```
- Confirm: "Are you sure? You want to update this in all the records."
- Alert: "Cheque No. or Cheque Date is mandatory"
- Alert: "Cross Cheque"

### Screen `ChequeDetailsGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Favour Of | DmTextBox | `txtPrintName` | `100, false` |
| 2 | Cheque No. | DmTextBox | `txtChequeNo` | `40, false` |
| 3 | Cheque Date | DmTextBox | `txtChequeDate` | `100, false` |
| 4 | Cross Cheque | DmTextHelp | `txtHYesNo` | `KeyField_PrpAcs, 3, true` |

**Grid / popup columns** (header, width, db field):
- `txtHYesNo`: Name [Text, w200]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHYesNo` → `getYesNo`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHYesNo.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExte
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHYesNo).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHYesNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHYesNo).show();
```

`txtHYesNo.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHYesNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHYesNo).show();
```
- Confirm: "Are you sure? You want to update this in all the records."
- Alert: "Favor Of is mandatory"
- Alert: "Cross Cheque is mandatory"

### Screen `Settings`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | From Date | DmTextBox | `txtFromDate` | `20, false` |
| 2 | To Date | DmTextBox | `txtToDate` | `11, true` |
| 3 | Voucher Type | DmTextHelp | `txtHVoucherType` | `TxtHVoucherType, 7, true` |
| 4 | Bank Name | DmTextHelp | `txtHBankName` | `TxtHAccountName, 7, true` |
| 5 | Voucher No | DmTextHelpMulti | `txtHVoucherNo` | `TxtHVoucherNo, 7, false` |
| 6 | Exclude Acounts | DmTextHelpMulti | `txtHAccountName` | `TxtHAccount_PrpAcs, 7, false` |
| 7 | Record Type | DmTextHelp | `txtHRecordsType` | `KeyField_PrpAcs, 4, true` |

**Grid / popup columns** (header, width, db field):
- `txtHVoucherType`: Name [Text, w1, Description]
- `txtHBankName`: Name [Text, w1, Name]; City [Text, w100, CityName]; Code [Text, w50, ManualCode]
- `txtHAccountName`: Name [Text, w1, name]; City [Text, w100, CityName]; Code [Text, w50, ManualCode]
- `txtHVoucherNo`: Name [Text, w1, name]
- `txtHRecordsType`: Name [Text, w0, NAME]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHVoucherType` → `getTxtHVoucherType`
- `txtHBankName` → `getTxtHBankName`
- `txtHRecordsType` → `getTxtHRecordType`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHVoucherType.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHVoucherType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Tx
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<TxtHVoucherType_Mdl>> result) {
if (result.isStatus()) {
txtHVoucherType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`txtHVoucherType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHVoucherType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`txtHVoucherType.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus) {
```

`txtHBankName.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHBankName(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHB
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<TxtHBankName_Mdl>> result) {
if (result.isStatus()) {
txtHBankName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`txtHBankName.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHBankName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`txtHBankName.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus) {
if (tRow != null) {
```

`txtHAccountName.addTextHelpMultiCallBack` → `populateData()`
```java
if (DmShared.dNull(txtFromDate.getText()).equalsIgnoreCase("")) {
new DmAlertMessageBox("From Date is mandatory", txtFromDate).show();
return;
if (DmShared.dNull(txtToDate.getText()).equalsIgnoreCase("")) {
new DmAlertMessageBox("To Date is mandatory", txtToDate).show();
return;
if (DmShared.iNull(txtHVoucherType.getTag()).longValue() == 0) {
new DmAlertMessageBox("Voucher Type is mandatory", txtHVoucherType).show();
return;
if (DmShared.iNull(txtHBankName.getTag()).longValue() == 0) {
...
```

`txtHAccountName.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAccountName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`txtHVoucherNo.addTextHelpMultiCallBack` → `populateData()`
```java
if (DmShared.dNull(txtFromDate.getText()).equalsIgnoreCase("")) {
new DmAlertMessageBox("From Date is mandatory", txtFromDate).show();
return;
if (DmShared.dNull(txtToDate.getText()).equalsIgnoreCase("")) {
new DmAlertMessageBox("To Date is mandatory", txtToDate).show();
return;
if (DmShared.iNull(txtHVoucherType.getTag()).longValue() == 0) {
new DmAlertMessageBox("Voucher Type is mandatory", txtHVoucherType).show();
return;
if (DmShared.iNull(txtHBankName.getTag()).longValue() == 0) {
...
```

`txtHVoucherNo.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHVoucherNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`txtHRecordsType.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHRecordType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Key
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHRecordsType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`txtHRecordsType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHRecordsType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```
- Alert: "From Date is mandatory"
- Alert: "To Date is mandatory"
- Alert: "From Date should be less than or equal to To Date."
- Alert: "Voucher Type is mandatory"
- Alert: "Bank Name is mandatory"
- Alert: "From Date is mandatory"
- Alert: "To Date is mandatory"
- Alert: "Voucher Type is mandatory"
- Alert: "Bank Name is mandatory"
- Alert: "From Date is mandatory"
- Alert: "To Date is mandatory"
- Alert: "Voucher Type is mandatory"
- Alert: "Bank Name is mandatory"

## Files
| file | lines |
|---|---|
| client/BulkChequePrinting.java | 646 |
| client/BulkChequePrinting_Dsg.java | 331 |
| client/ChangeChqNo.java | 225 |
| client/ChangeChqNo_Dsg.java | 178 |
| client/ChequeDetailsGridEdit.java | 219 |
| client/ChequeDetailsGridEdit_Dsg.java | 115 |
| client/ReportPrint.java | 86 |
| client/Settings.java | 348 |
| client/Settings_Dsg.java | 332 |
| server/BulkChequePrinting_SrvImpl.java | 1133 |
| shared/BulkChequePrinting_Mdl.java | 114 |
| shared/BulkChequePrinting_Srv.java | 30 |
| shared/BulkChequePrinting_SrvAsync.java | 30 |
| shared/BulkChequePrinting_Vld.java | 33 |
| shared/ChangeChqNo_Mdl.java | 77 |
| shared/GrdBulkChequePrinting_Mdl.java | 178 |
| shared/Print_Mdl.java | 58 |
| shared/Settings_Mdl.java | 97 |
| shared/TxtHAccountName_Mdl.java | 34 |
| shared/TxtHBankName_Mdl.java | 32 |
| shared/TxtHVoucherNo_Mdl.java | 14 |
| shared/TxtHVoucherType_Mdl.java | 50 |

## UI labels / columns (client)
`Favour Of`, `Cheque No.`, `Cheque Date`, `Cross Cheque`, `From Date`, `To Date`, `Voucher Type`, `Bank Name`, `Voucher No`, `Exclude Acounts`, `Record Type`, `Merge Cheques`, `Total : `

## Models (shared DTOs)
### TxtHVoucherNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
### GrdBulkChequePrinting_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | v_TypeShortName |
| String | date |
| String | accountName |
| String | prvChqNo |
| String | chqNo |
| String | chqDate |
| String | debit |
| String | credit |
| String | v_SNo |
| String | printName |
| String | displayAmount |
| String | isChecked |
| String | isCrossChqCode |
| String | IsCrossChqText |
| String | isView |

### TxtHAccountName_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | city |
| String | manualCode |

### TxtHBankName_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | City |
| String | SCode |

### Print_Mdl
| type | field |
|---|---|
| String | docId |
| String | chqNo |
| String | amount |
| String | favourOf |
| String | v_Sno |

### TxtHVoucherType_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | category |
| String | DefaultAc |
| String | DAName |
| String | Nature |

### BulkChequePrinting_Mdl
| type | field |
|---|---|
| List<GrdBulkChequePrinting_Mdl> | grdChequeDetail_Mdl |
| String | docId_V_SNoCSV |
| String | fromDate |
| String | toDate |
| String | contraSub |
| String | voucherNoDocid |
| String | voucherNo |
| String | accountCode |
| String | v_type |
| String | recordTypeCode |
| String | recordTypeName |

### Settings_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | fromDate |
| String | toDate |
| String | contraSub |
| String | voucherNoDocid |
| String | voucherNo |
| String | accountCode |
| String | v_type |
| String | recordTypeCode |
| String | recordTypeName |

### ChangeChqNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | chqNo |
| String | chqDate |
| String | isCrossChqCode |
| String | isCrossChqText |
| String | docId |
| String | v_SNo |
| String | prvChqNo |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHVoucherType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucherType_Mdl>> |
| executePrint | BulkChequePrinting_Mdl mrTemp, List<Print_Mdl> listPrint, boolean isMerge | DmResult<String> |
| updateVoucher | GrdBulkChequePrinting_Mdl mrTemp, List<ChangeChqNo_Mdl> mdl, String chkNoLength, boolean isMerged | DmResult<String> |
| getTxtHBankName | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHBankName_Mdl>> |
| getTxtHAccountName | DmHelper pageing, boolean blnToEndOfFile, String contraSub, String fromDate, String toDate, String voucherNoDocid | DmResultPageing<List<TxtHAccountName_Mdl>> |
| getTxtHVoucherNo | DmHelper pageing, boolean blnToEndOfFile, String contraSub, String fromDate, String toDate | DmResultPageing<List<TxtHVoucherNo_Mdl>> |
| showRecords | BulkChequePrinting_Mdl mrTemp | DmResult<List<GrdBulkChequePrinting_Mdl>> |
| getTxtHRecordType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getYesNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `BulkChequePrinting_Vld`
```java
public class BulkChequePrinting_Vld {
    static boolean isGridDetailsExists = false;
    public static void chkSave(BulkChequePrinting_Mdl mrTemp) {
        for (GrdBulkChequePrinting_Mdl mdl : mrTemp.getGrdChequeDetail_Mdl()) {
            if (DmShared.iNull(mdl.getSharedKeyFieldCode()).longValue() > 0
                    && DmShared.xNull(mdl.getIsChecked()).equalsIgnoreCase("1")) {
                isGridDetailsExists = true;
                DmShared.isValidText(DmShared.xNull(mdl.getPrintName()), "Print Name", 100);
                DmShared.isValidText(DmShared.xNull(mdl.getChqNo()), "Cheque Number", 100);
                DmShared.isValidText(DmShared.xNull(mdl.getChqDate()), "Cheque Date", 100);
                DmShared.isValidText(DmShared.xNull(mdl.getSharedKeyFieldName()), "Voucher No.", 100);
                DmShared.isValidText(DmShared.xNull(mdl.getV_TypeShortName()), "Type", 100);
            }
        }
        if (!isGridDetailsExists) {
            throw new DmException(100, "Grid Details are mandatory");
        }
    }
}
```

## Server `BulkChequePrinting_SrvImpl.java` (1133 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.showJasper`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getTxtHVoucherType`
`public DmResultPageing<List<TxtHVoucherType_Mdl>> getTxtHVoucherType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT VT.V_Type,VT.Description,VT.DefaultAc,SG.Name AS DAName,SG.Nature,VT.Category FROM Voucher_Type VT LEFT JOIN SubGroup SG ON VT.DefaultAc=SG.SubCode WHERE VT.NCat = 'FA' AND VT.category NOT IN (' {…} ',' {…} ')`

### `getTxtHBankName`
`public DmResultPageing<List<TxtHBankName_Mdl>> getTxtHBankName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT SG.SubCode, SG.Name, ISNULL(c.CityName, '') AS CityName, SG.ManualCode FROM SubGroup SG LEFT JOIN city c ON c.CityCode = SG.CityCode LEFT JOIN subGroupSiteList SGL ON SG.subCode = SGL.subCode WHERE SGL.site_Code = :site_Code AND sg.nature = :bankCode AND SG.isActive = 1`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:bankCode` ← `DmServer.getCodeViaManualCode("BK", "manualCode", "subGroupNature", "code", cnTemp)`

### `getTxtHAccountName`
`public DmResultPageing<List<TxtHAccountName_Mdl>> getTxtHAccountName(DmHelper helper, boolean blnToEndOfFile, String contraSub, String fromDate, String toDate, String voucherNoDocid)`

- SQL 1: `SELECT lm.subCode as code , max(sg.displayName) as name , max(ISNULL(c.CityName, '')) AS CityName , max(sg.manualCode) AS manualCode FROM ledger lm LEFT JOIN subGroup sg ON lm.subCode=sg.subCode LEFT JOIN city c ON c.CityCode = sg.CityCode LEFT JOIN subGroupSiteList SGL ON SG.subCode = SGL.subCode WHERE SGL.site_Code = :site_Code AND lm.isPosted=1 AND sg.isActive = 1 {…} GROUP BY lm.subCode`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:contraSub` ← `DmShared.iNull(contraSub).toString()`; `:fromDate` ← `DmServer.convertDate(DmShared.dNull(fromDate))`; `:toDate` ← `DmServer.convertDate(DmShared.dNull(toDate))`

### `getTxtHVoucherNo`
`public DmResultPageing<List<TxtHVoucherNo_Mdl>> getTxtHVoucherNo(DmHelper helper, boolean blnToEndOfFile, String contraSub, String fromDate, String toDate)`

- SQL 1: `SELECT max(lm.recId) as name , lm.docid as code FROM ledger lm LEFT JOIN subGroup sg ON lm.subCode=sg.subCode LEFT JOIN subGroupSiteList SGL ON SG.subCode = SGL.subCode WHERE SGL.site_Code = :site_Code AND lm.isPosted=1 AND sg.isActive = 1 {…} GROUP BY lm.docId`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:contraSub` ← `DmShared.iNull(contraSub).toString()`; `:fromDate` ← `DmServer.convertDate(DmShared.dNull(fromDate))`; `:toDate` ← `DmServer.convertDate(DmShared.dNull(toDate))`

### `showRecords`
`public DmResult<List<GrdBulkChequePrinting_Mdl>> showRecords(BulkChequePrinting_Mdl mrTemp)`

- SQL 1: `SELECT lm.docid as code , lm.recid as name , lm.isCrossCheque , lm.v_SNo , lm.v_Date , vt.short_Name , sgc.displayName as accountName , lm.chq_No , lm.chq_Date , lm.favourOf as printName , ISNULL(lm.amtDr,0) as debit , ISNULL(lm.amtCr,0) as credit , CASE WHEN ISNULL(lm.amtCr,0)>0 THEN lm.amtCr ELSE ISNULL(lm.amtDr,0) END as displayAmount FROM ledger lm LEFT JOIN subGroup sg ON lm.subCode=sg.subCode LEFT JOIN subGroup sgc ON lm.contraSub=sgc.subCode INNER JOIN voucher_type vt ON vt.v_Type=lm.v_Type WHERE lm.site_Code = :site_Code AND lm.isPosted=1 AND sg.isActive = 1 {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:contraSub` ← `DmShared.iNull(mrTemp.getContraSub()).toString()`; `:v_type` ← `DmShared.iNull(mrTemp.getV_type()).toString()`; `:fromDate` ← `DmServer.convertDate(DmShared.dNull(mrTemp.getFromDate()))`; `:toDate` ← `DmServer.convertDate(DmShared.dNull(mrTemp.getToDate()))`

### `executePrint`
`public DmResult<String> executePrint(BulkChequePrinting_Mdl mrTemp, List<Print_Mdl> listPrint, boolean isMerge)`

- SQL 1: `SELECT lm.docid as code , MAX(lm.recid) as name , MAX(lm.isCrossCheque) as isCrossCheque`
- SQL 2: `SELECT cps.bankCode , cps.chequeDateTop , cps.chequeDateLeft , cps.crossChequeTop , cps.crossChequeLeft , cps.favourOfTop , cps.favourOfLeft , cps.AmtInWordsTop , cps.AmtInWordsLeft , cps.AmtTop , cps.AmtLeft , cps.authSignatory , cps.authSignatoryTop , cps.authSignatoryLeft , cps.companyName , cps.companyNameTop , cps.companyNameLeft , cps.paperWidth , cps.paperHeight FROM chequePrintingSetup as cps where cps.bankCode= :bankCode`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:contraSub` ← `DmShared.iNull(mrTemp.getContraSub()).toString()`; `:v_type` ← `DmShared.iNull(mrTemp.getV_type()).toString()`; `:fromDate` ← `DmServer.convertDate(DmShared.dNull(mrTemp.getFromDate()))`; `:toDate` ← `DmServer.convertDate(DmShared.dNull(mrTemp.getToDate()))`; `:bankCode` ← `DmShared.iNull(mrTemp.getContraSub()).toString()`
- Other calls: `BulkChequePrinting_Vld.chkSave`, `DmBaseShared.xNull`, `JRElementsVisitor.visitReport`, `JRXmlLoader.load`, `JasperCompileManager.compileReport`, `PRINT.getValue`, `SYSTEM_ACTIVITY.getName`

### `getTxtHRecordType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHRecordType(DmHelper helper, boolean blnToEndOfFile)`


### `getYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `updateVoucher`
`public DmResult<String> updateVoucher(GrdBulkChequePrinting_Mdl bulkChequePrinting_Mdl, List<ChangeChqNo_Mdl> mdlLst, String chkNoLength, boolean isMerged)`

- SQL 1: `UPDATE ledger SET favourOf = :favourOf ,chq_No = :chq_No ,chq_Date = :chq_Date ,U_Name = :u_Name ,U_EntDt = :uEntDt ,isCrossCheque = :isCrossCheque Where docId = :docId`
- SQL 2: `UPDATE ledger SET chq_No = :chq_No , chq_Date = :chq_Date , U_Name = :u_Name , U_EntDt = :uEntDt , isCrossCheque = :isCrossCheque Where docId = :docId`
- Params: `:favourOf` ← `bulkChequePrinting_Mdl.getPrintName()`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:docId` ← `DmShared.iNull(bulkChequePrinting_Mdl.getSharedKeyFieldCode()).toString()`; `:chq_No` ← `DmShared.xNull(bulkChequePrinting_Mdl.getChqNo())`; `:isCrossCheque` ← `DmShared.iNull(bulkChequePrinting_Mdl.getIsCrossChqCode()).toString()`; `:chq_Date` ← `DmServer.convertDate(bulkChequePrinting_Mdl.getChqDate())`; `:v_SNo` ← `bulkChequePrinting_Mdl.getV_SNo()`; `:prvChqNo` ← `DmShared.iNull(bulkChequePrinting_Mdl.getPrvChqNo()).toString()`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:docId` ← `DmShared.iNull(mdl.getDocId()).toString()`; `:chq_No` ← `DmShared.xNull(String.format("%0" + (chkNoLength) + "d", DmShared.iNull(mdl.getChqNo()).in`; `:chq_Date` ← `DmServer.convertDate(mdl.getChqDate())`; `:isCrossCheque` ← `DmShared.iNull(mdl.getIsCrossChqCode()).toString()`; `:v_SNo` ← `mdl.getV_SNo()`; `:prvChqNo` ← `DmShared.iNull(mdl.getPrvChqNo()).toString()`

## Depends on (imports from other packages)
`dataman.accounts.bulkChequePrinting.shared`, `dataman.accounts.chequePrintSetup.shared`, `dataman.accounts.voucherEntry.client`, `dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.server`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmJasper.server`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmReportLayout.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.libraryMenu.self.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
