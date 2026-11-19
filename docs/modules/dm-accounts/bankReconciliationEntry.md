# dm-accounts / bankReconciliationEntry

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/accounts/src/bankReconciliationEntry/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: ledger
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: rule: GridRow
- Lookups/suggestions: getTxtHBankName, getTxtHStatus, getTxtHHideEntry
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `BankReconciliationEntryGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Voucher No. | DmTextBox | `txtVoucherName` | `` |
| 2 | Date | DmTextBox | `txtDate` | `` |
| 3 | A/C Name | DmTextBox | `txtACName` | `` |
| 4 | Cheque No. | DmTextBox | `txtChequeNo` | `` |
| 5 | Cheque Date | DmTextBox | `txtChequeDate` | `15, false` |
| 6 | Debit | DmTextBoxNumeric | `txtDebit` | `2, false, 10, false` |
| 7 | Credit | DmTextBoxNumeric | `txtCredit` | `2, false, 10, false` |
| 8 | Favour Of | DmTextBox | `txtFavourOf` | `` |
| 9 | Clear Date | DmTextBox | `txtClearDate` | `15, true` |
- Alert: "Clear Date is mandatory"
- Alert: "Clear Date should be greater than or equal to Cheque Date."
- Alert: "Clear Date should be greater than or equal to Date."
- Alert: "Clear Date should be less than or equal to Current Date."

### Screen `BankReconciliationEntry`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | 0 | DmTextBox | `txtSearchText` | `50, false` |
| 2 | Clear Date | DmTextBox | `txtClearDate` | `15, false` |

Other widgets (not in tab list): `grdAcountDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `grdAcountDetail`: Voucher No. [Text, w147]; Date [Text, w75]; A/c Name [Text, w384]; Favour Of [Text, w175]; Chq. No [Text, w75]; Chq. Date [Text, w75]; Clear Date [Text, w75]; Debit [Text, w100]; Credit [Text, w100];  [Text, w30]

**Save: model field ← widget** (`topControlSaveInitialize`):
- `ClearDate` ← DmShared.dNull(txtClearDate.getText())

**Event handlers (condensed code, CRUD flow and field callbacks):**

`setting.addCallBack` → `onOk()`
```java
BankReconciliationEntry.this.setting_Mdl = setting_Mdl;
fillRecords();
if (DmShared.xNull(setting_Mdl.getStatusCode()).equalsIgnoreCase("V")) {
flwTableDisplaySection.setVisible(false);
} else {
flwTableDisplaySection.setVisible(true);
toDate = setting_Mdl.getToDate();
txtClearDate.setFocus(true);
```

`setting.addCallBack` → `onClose()`
```java
txtClearDate.setFocus(true);
```

`txtSearchText.addKeyUpHandler` → `onKeyUp()`
```java
if (event.getNativeKeyCode() == KeyCodes.KEY_DOWN) {
((CellSelectionModel) grdAcountDetail.getGrid().getSelectionModel()).selectCell(0, 1);
} else {
fillFilteredData();
```

`bankReconciliationEntryGridEdit.addCallBack` → `onOk()`
```java
grdAcountDetail.commitChanges();
calculate();
```

`bankReconciliationEntryGridEdit.addCallBack` → `onDelete()`
```java
grdAcountDetail.commitChanges();
calculate();
```
- Confirm: "Are you sure? You want to close."
- Alert: "Please fill clear Date."
- Alert: "Clear Date should be less than or equal to Current Date."
- Alert: "Please select Bank Name first"

### Screen `Setting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Bank Name | DmTextHelp | `txtHBankName` | `TxtHBank_PrpAcs, 5, true` |
| 2 | Status | DmTextHelp | `txtHStatus` | `KeyField_PrpAcs, 4` |
| 3 | Hide Entries Cleared On Date‎ | DmTextHelp | `txtHHideEntry` | `KeyField_PrpAcs, 3` |
| 4 | From Date | DmTextBox | `txtFromDate` | `15, true` |
| 5 | To Date | DmTextBox | `txtToDate` | `15, true` |

**Grid / popup columns** (header, width, db field):
- `txtHBankName`: Name [Text, w0, Name]; Manual Code [Text, w150, bankManualCode]; City [Text, w150, City]
- `txtHStatus`: Name [Text, w0, Name]
- `txtHHideEntry`: Type [Text, w0, Type]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHBankName` → `getTxtHBankName`
- `txtHStatus` → `getTxtHStatus`
- `txtHHideEntry` → `getTxtHHideEntry`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHBankName.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHBankName(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHB
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHBankName).show();
public void onSuccess(DmResultPageing<List<TxtHBank_Mdl>> result) {
if (result.isStatus()) {
txtHBankName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBankName).show();
```

`txtHBankName.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHBankName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBankName).show();
```

`txtHStatus.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHStatus(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFiel
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHStatus).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHStatus.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHStatus).show();
```

`txtHStatus.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHStatus.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHStatus).show();
```

`txtHStatus.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHStatus.getTag()).equalsIgnoreCase("U")) {
txtFromDate.setEnabled(true);
txtHHideEntry.setEnabled(false);
} else {
txtFromDate.setEnabled(false);
txtFromDate.setText("");
txtHHideEntry.setEnabled(true);
```

`txtHHideEntry.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHHideEntry(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyF
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHHideEntry).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHHideEntry.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHHideEntry).show();
```

`txtHHideEntry.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHHideEntry.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHHideEntry).show();
```
- Alert: "Bank Name is mandatory."
- Alert: "From Date is mandatory."
- Alert: "To Date is mandatory."
- Alert: "From Date should be less than or equal to To Date."

## Files
| file | lines |
|---|---|
| client/BankReconciliationEntry.java | 587 |
| client/BankReconciliationEntryGridEdit.java | 127 |
| client/BankReconciliationEntryGridEdit_Dsg.java | 184 |
| client/BankReconciliationEntry_Dsg.java | 440 |
| client/Setting.java | 270 |
| client/Setting_Dsg.java | 216 |
| server/BankReconciliationEntry_SrvImpl.java | 508 |
| shared/BankReconciliationEntry_Mdl.java | 48 |
| shared/BankReconciliationEntry_Srv.java | 21 |
| shared/BankReconciliationEntry_SrvAsync.java | 21 |
| shared/BankReconciliationEntry_Vld.java | 52 |
| shared/GrdBankReconciliationEntry_Mdl.java | 167 |
| shared/Setting_Mdl.java | 85 |
| shared/TxtHBank_Mdl.java | 32 |

## UI labels / columns (client)
`0`, `Clear Date`, `Total Amount`, `Opening As Per Bank`, `Amount not reflected`, `Amount reflected`, `Balance As Per Company Book`, `Balance As Per Bank`, `Voucher No.`, `Date`, `A/C Name`, `Cheque No.`, `Cheque Date`, `Debit`, `Credit`, `Favour Of`, `Bank Name`, `Status`, `Hide Entries Cleared On Date‎`, `From Date`, `To Date`

## Models (shared DTOs)
### GrdBankReconciliationEntry_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | vNo |
| String | vDate |
| String | accountName |
| String | accountCode |
| String | favourOf |
| String | chq |
| String | chqDate |
| String | clearDate |
| Double | debit |
| Double | credit |
| String | debitTag |
| String | creditTag |
| String | tag |
| String | bank_V_SNo |
| String | remark |
| String | image |
| String | isCleared |

### Setting_Mdl
| type | field |
|---|---|
| String | statusCode |
| String | statusName |
| String | chkentriesCode |
| String | chkentriesName |
| String | bankName |
| String | bankCode |
| String | fromDate |
| String | toDate |

### TxtHBank_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | cityName |
| String | manualCode |

### BankReconciliationEntry_Mdl
| type | field |
|---|---|
| String | opnAsPerBank |
| String | companyBal |
| String | clearDate |

## RPC interface
| method | params | returns |
|---|---|---|
| saveRecord | BankReconciliationEntry_Mdl mrTemp, Setting_Mdl setting_Mdl | DmResult<BankReconciliationEntry_Mdl> |
| getFillRecords | Setting_Mdl setting_Mdl | DmResult<BankReconciliationEntry_Mdl> |
| getTxtHBankName | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHBank_Mdl>> |
| getTxtHStatus | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHHideEntry | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `BankReconciliationEntry_Vld`
```java
public class BankReconciliationEntry_Vld {
    public static void chkSave(BankReconciliationEntry_Mdl mrTemp) {
        List<GrdBankReconciliationEntry_Mdl> listBr = mrTemp.getListBr();
        for (GrdBankReconciliationEntry_Mdl grdEntry_Mdl : mrTemp.getListBr()) {
            if (!DmShared.dNull(grdEntry_Mdl.getClearDate()).trim().equalsIgnoreCase("")) {
                if (!DmShared.dNull(grdEntry_Mdl.getChqDate()).trim().equalsIgnoreCase("")) {
                    if ((DmShared.convertDate(DmShared.dNull(grdEntry_Mdl.getClearDate())).getTime() < (DmShared.convertDate(DmShared.dNull(grdEntry_Mdl.getChqDate()))).getTime())) {
                        throw new DmException(100, "GridRow", DmShared.iNull(listBr.indexOf(grdEntry_Mdl)).toString(), "Clear Date should be greater than or equal to Cheque Date in line no. " + DmShared.iNull(listBr.indexOf(grdEntry_Mdl) + 1).toString() + ".");
                    }
                }
                if (!DmShared.dNull(grdEntry_Mdl.getVDate()).trim().equalsIgnoreCase("")) {
                    if ((DmShared.convertDate(DmShared.dNull(grdEntry_Mdl.getClearDate())).getTime() < (DmShared.convertDate(DmShared.dNull(grdEntry_Mdl.getVDate()))).getTime())) {
                        throw new DmException(200, "GridRow", DmShared.iNull(listBr.indexOf(grdEntry_Mdl)).toString(), "Clear Date should be greater than or equal to Voucher Date in line no. " + DmShared.iNull(listBr.indexOf(grdEntry_Mdl) + 1).toString() + ".");
                    }
                }
            }
        }
    }
}
```

## Server `BankReconciliationEntry_SrvImpl.java` (508 lines)
Helpers used: `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.vNull`, `DmShared.convertDate`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `saveRecord`
`public DmResult<BankReconciliationEntry_Mdl> saveRecord(BankReconciliationEntry_Mdl mrTemp, Setting_Mdl setting_Mdl)`

- SQL 1: `Update ledger set clg_Date = :clg_Date where docId = :docId and v_SNo = :v_SNo`
- Params: `:docId` ← `DmShared.iNull(grdEntry_Mdl.getSharedKeyFieldCode()).toString()`; `:v_SNo` ← `DmShared.iNull(grdEntry_Mdl.getVNo()).toString()`; `:clg_Date` ← `DmServer.convertDate(grdEntry_Mdl.getClearDate())`; `:clg_Date` ← `DmServer.convertDate(mrTemp.getClearDate())`; `:clg_Date` ← `DmServer.convertDate("")`

### `getFillRecords`
`public DmResult<BankReconciliationEntry_Mdl> getFillRecords(Setting_Mdl setting_Mdl)`


### `getRecords`
`private BankReconciliationEntry_Mdl getRecords(Setting_Mdl setting_Mdl, Connection cnTemp) throws Exception`

- SQL 1: `declare @tempTable table (docId bigint,recId varchar(20),v_date date,v_type_short varchar(5),isBankFound tinyInt); insert into @tempTable (docId,recId,v_date,v_type_short,isBankFound) select lg.docId,max(lg.recId) as recId,max(lg.v_date) as v_date,max(vt.short_Name) as short_Name, min(case when sn.manualCode in ('BK','CS') and lg.subCode <> :subCode then 0 else 1 end) as isBankFound from ledger lg left join subGroup sg on sg.subCode = lg.subCode left join subgroupNature sn on sn.code = sg.nature left join voucher_type vt on vt.v_Type = lg.v_Type where vt.isBankCashBook = 1 {…} and docId in (select lg1.docId from ledger lg1 where lg1.subCode = :subCode {…} ) group by docId order by isBankFound; select * from @tempTable; select max(case when isNull(temp.clg_Date,:clgIsNullValue) < {…} then null else docId end) as docId, max(case when isNull(temp.clg_Date,:clgIsNullValue) < {…} then null else v_sno end) as v_sno, max(case when isNull(temp.clg_Date,:clgIsNullValue) < {…} then 0 else 1 end) as sno, max(case when isNull(temp.clg_Date,:clgIsNullValue) < {…} then null else v_no end) as v_no, max(case when isNull(temp.clg_Date,:clgIsNullValue) < {…} then null else recId end) as recId, max(case when isNull(temp.clg_Date,:clgIsNullValue) < {…} then null else recIdPrefix end) as recIdPrefix, max(case when isNull(temp.clg_Date,:clgIsNullValue) < {…} then null else v_date end) as v_date, max(case when isNull(temp.clg_Date,:clgIsNullValue) < {…} then null else vTypeSName end) as vTypeSName, max(case when isNull(temp.clg_Date,:clgIsNullValue) < {…} then 'OPENING BALANCE' else contraName end) as contraName, isNull(sum(case when isNull(temp.clg_Date,:clgIsNullValue) < {…} then null else amtDr end),0) as amtDr, isNull(sum(case when isNull(temp.clg_Date,:clgIsNullValue) < {…} then null else amtCr end),0) as amtCr, isNull(sum(case when isNull(temp.clg_Date,:clgIsNullValue) < {…} then amtDr else null end),0) as amtDr_OP, isNull(sum(case when isNull(temp.clg_Date,:clgIsNullValue) < {…} then amtCr else null end),0) as amtCr_OP, max(case when isNull(temp.clg_Date,:clgIsNullValue) < {…} then null else chq_no end) as chq_no, max(case when isNull(temp.clg_Date,:clgIsNullValue) < {…} then null else chq_Date end) as chq_Date, max(case when isNull(temp.clg_Date,:clgIsNullValue) < {…} then null else clg_Date end) as clg_Date, max(case when isNull(temp.clg_Date,:clgIsNullValue) < {…} then null else refNo end) as refNo, max(case when isNull(temp.clg_Date,:clgIsNullValue) < {…} then null else refDate end) as refDate, max(case when isNull(temp.clg_Date,:clgIsNullValue) < {…} then null else narration end) as narration, max(case when isNull(temp.clg_Date,:clgIsNullValue) < {…} then null else favourOf end) as favourOf from ( select lg.docId,lg.v_sno,lg.v_no,lg.recId,lg.recIdPrefix,lg.v_date,vt.short_name as vTypeSName, sg.name as contraName,lg.amtCr as amtDr,lg.amtDr as amtCr,lg.chq_no, lg.chq_Date,lg.clg_Date,lg.refNo,lg.refDate,lg.narration,lg.favourOf from ledger lg left join subGroup sg on sg.subCode = lg.subCode left join voucher_type vt on vt.v_Type = lg.v_Type where vt.isBankCashBook = 1 and lg.isBankCashBook = 1 {…} and lg.docId in (select t.docId from @tempTable t) union all select lg.docId,lg.v_sno,lg.v_no,lg.recId,lg.recIdPrefix,lg.v_date,vt.short_name as vTypeSName, sg.name as contraName,lg.amtDr,lg.amtCr,lg.chq_no, lg.chq_Date,lg.clg_Date,lg.refNo,lg.refDate,lg.narration,lg.favourOf from ledger lg left join subGroup sg on sg.subCode = lg.contraSub left join voucher_type vt on vt.v_Type = lg.v_Type where vt.isBankCashBook = 0 and lg.subCode = :subCode {…} ) as temp group by (case when isNull(temp.clg_Date,:clgIsNullValue) < {…} then null else docId end),(case when isNull(temp.clg_Date,:clgIsNullValue) < {…} then null else v_sno end) order by sno,v_date,recIdPrefix,v_no,recId,docId,v_sno;`
- SQL 2: `select (isNull(sum(lg.amtDr),0) - isNull(sum(amtCr),0)) as amount from ledger lg where lg.isPosted = 1 and lg.v_Date <= :toDate and lg.subCode = :subCode group by lg.subCode`
- Params: `:fromDate` ← `DmServer.convertDate(setting_Mdl.getFromDate())`; `:toDate` ← `DmServer.convertDate(setting_Mdl.getToDate())`; `:toDate` ← `DmServer.convertDate(setting_Mdl.getToDate())`; `:fromDate` ← `DmServer.convertDate(setting_Mdl.getFromDate())`; `:toDate` ← `DmServer.convertDate(setting_Mdl.getToDate())`; `:clgIsNullValue` ← `DmServer.convertDate(clgIsNullValue)`; `:subCode` ← `setting_Mdl.getBankCode()`; `:toDate` ← `DmServer.convertDate(setting_Mdl.getToDate())`; `:subCode` ← `setting_Mdl.getBankCode()`
- Errors: "Invalid entry please check Vr. No.: "

### `getTxtHBankName`
`public DmResultPageing<List<TxtHBank_Mdl>> getTxtHBankName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select sg.subCode as code, sg.name, sgn.name as nature, sgn.manualCode,isNull(CT.CityName, '') AS City, sg.manualCode as bankManualCode from subGroup as sg left join subGroupSiteList as sgsl on sg.subCode = sgsl.subCode left join subgroupNature as sgn on sg.nature = sgn.code LEFT JOIN City CT ON CT.CityCode = sg.CityCode where sg.isActive = 1 and sgsl.site_Code = :site_Code and sgn.manualCode in ('BK')`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHStatus`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHStatus(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHHideEntry`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHHideEntry(DmHelper helper, boolean blnToEndOfFile)`


## Depends on (imports from other packages)
`dataman.accounts.bankReconciliationEntry.shared`, `dataman.accounts.reports.client`, `dataman.accounts.reports.shared`, `dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.shared`, `dataman.libraryMenu.self.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| getRecords | `if (DmShared.iNull(rsTemp.getString("isBankFound")).intValue() == 0)` | "Invalid entry please check Vr. No.: " |

## Notes (hand-written)
_No hand-written notes yet._
