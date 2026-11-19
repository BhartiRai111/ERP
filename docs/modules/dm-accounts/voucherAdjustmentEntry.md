# dm-accounts / voucherAdjustmentEntry

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/accounts/src/voucherAdjustmentEntry/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHLedger, getTxtHVoucher
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `SearchFilterPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Vr. No. | DmTextBox | `txtVoucherNo` | `16, false` |
| 2 | Vr. Date | DmTextBox | `txtVoucherDate` | `11, false` |
| 3 | Ref. No. | DmTextBox | `txtRefNo` | `16, false` |
| 4 | Vr. Type | DmTextHelpMulti | `txtHVoucherType` | `KeyField_PrpAcs, 7` |
| 5 | Ledger | DmTextHelpMulti | `txtHAccount` | `TxtHLedger_PrpAcs, 7` |

**Grid / popup columns** (header, width, db field):
- `txtHVoucherType`: Name [Text, w150, name]
- `txtHAccount`: Name [Text, w150, name]; Manual Code [Text, w50, manualCode]; City [Text, w100, cityName]

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHAccount.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHLedger(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHLed
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHAccount).show();
public void onSuccess(DmResultPageing<List<TxtHLedger_Mdl>> result) {
if (result.isStatus()) {
txtHAccount.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAccount).show();
```

`txtHAccount.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAccount.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAccount).show();
```

`txtHVoucherType.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHVoucher(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFie
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHVoucherType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHVoucherType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHVoucherType).show();
```

`txtHVoucherType.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHVoucherType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHVoucherType).show();
```

### Screen `SearchPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmTextBox | `txtSearchValue` | `` |
| 2 | - | DmGridPaging | `grdSearch` | `GrdVoucher_PrpAcs, 15, false` |

**Grid / popup columns** (header, width, db field):
- `grdSearch`: Vr. No. [Text, w100, recId]; Type [Text, w50, v_TypeName]; Date [Text, w80, v_Date]; Ref. No. [Text, w100, refNo]; Ledger [Text, w218, acName]; Narration [Text, w130, narration]; Debit [Text, w100, amtDr]; Credit [Text, w100, amtCr]

**Lookup / suggestion / find sources (server RPC called):**
- `grdSearch` → `getLedger`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`filterPopup.addCallBack` → `select()`
```java
SearchPopup.this.filter = filter;
reQuery();
txtSearchValue.setFocus(true);
```

`filterPopup.addCallBack` → `close()`
```java
txtSearchValue.setFocus(true);
```

`grdSearch.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnToEndOfFile).getLedger(param, helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<GrdLe
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdLedger_Mdl>> result) {
if (result.isStatus()) {
grdSearch.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`grdSearch.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
grdSearch.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`grdSearch.addCallBack` → `getParam()`
```java
return filter;
```

### Screen `VoucherAdjustmentEntryGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | A/c Code | DmTextBox | `txtAcManualCode` | `` |
| 2 | A/c Name | DmTextBox | `txtAcName` | `` |
| 3 | City | DmTextBox | `txtCity` | `` |
| 4 | Narration | DmTextBox | `txtNarration` | `` |
| 5 | Credit | DmTextBox | `txtDebit` | `` |
| 6 | - | DmTextBox | `txtCredit` | `` |
| 7 | - | DmTextBoxNumeric | `txtAdjustment` | `2, true, 12, false` |
| 8 | - | DmTextBoxNumeric | `txtItemAdjustment` | `2, true, 12, false` |

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtAcManualCode.setEnabled(false)`
- `txtAcName.setEnabled(false)`
- `txtCity.setEnabled(false)`
- `txtNarration.setEnabled(false)`
- `txtDebit.setEnabled(false)`
- `txtCredit.setEnabled(false)`
- `txtAdjustment.setReadOnly(true)`
- `txtItemAdjustment.setReadOnly(true)`

**`manageDisplay()` logic (condensed code):**
```java
txtAcManualCode.setEnabled(false);
txtAcName.setEnabled(false);
txtCity.setEnabled(false);
txtNarration.setEnabled(false);
txtDebit.setEnabled(false);
txtCredit.setEnabled(false);
txtAdjustment.setReadOnly(true);
txtItemAdjustment.setReadOnly(true);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`adjustmentPopup.addCallBack` → `onOk()`
```java
VoucherAdjustmentEntryGridEdit.this.lstAdjustments.clear();
if (lstAdjustments != null) {
VoucherAdjustmentEntryGridEdit.this.lstAdjustments.addAll(lstAdjustments);
calculate();
txtAdjustment.setFocus(true);
```

`adjustmentPopup.addCallBack` → `onClose()`
```java
txtAdjustment.setFocus(true);
```

`itemAdjustmentPopup.addCallBack` → `onOk()`
```java
VoucherAdjustmentEntryGridEdit.this.lstItemAdjustments.clear();
if (lstItemAdjustments != null) {
VoucherAdjustmentEntryGridEdit.this.lstItemAdjustments.addAll(lstItemAdjustments);
calculate();
txtItemAdjustment.setFocus(true);
```

`itemAdjustmentPopup.addCallBack` → `onClose()`
```java
txtItemAdjustment.setFocus(true);
```
- Alert: "Empty record is not allowed"

### Screen `VoucherAdjustmentEntry`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `grdAdjustment` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `grdAdjustment`: A/c Code [Text, w113]; A/c Name [Text, w270]; City [Text, w174]; Narration [Text, w290]; Debit [Text, w100]; Credit [Text, w100]; Account Adj. [Text, w100]; Item Adj. [Text, w100]

**Lookup / suggestion / find sources (server RPC called):**
- `voucherSearch` → `getLedgerByDocId`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`voucherSearch.addCallBack` → `select()`
```java
fillGrid(voucherDocId);
```

`voucherSearch.addCallBack` → `close()`
```java
focusGrid(0);
```

`adjustmentGridEdit.addCallBack` → `onOk()`
```java
grdAdjustment.commitChanges();
```

`adjustmentGridEdit.addCallBack` → `onDelete()`
```java
grdAdjustment.commitChanges();
```
- Confirm: "Are you sure? You want to close."
- Alert: "Please adjust a line before save."
- Alert: "Record saved successfully!"

## Files
| file | lines |
|---|---|
| client/SearchFilterPopup.java | 204 |
| client/SearchFilterPopup_Dsg.java | 201 |
| client/SearchPopup.java | 267 |
| client/SearchPopup_Dsg.java | 132 |
| client/VoucherAdjustmentEntry.java | 232 |
| client/VoucherAdjustmentEntryGridEdit.java | 294 |
| client/VoucherAdjustmentEntryGridEdit_Dsg.java | 207 |
| client/VoucherAdjustmentEntry_Dsg.java | 176 |
| server/VoucherAdjustmentEntry_SrvImpl.java | 609 |
| shared/Filter_Mdl.java | 76 |
| shared/GrdLedger_Mdl.java | 216 |
| shared/TxtHLedger_Mdl.java | 32 |
| shared/VoucherAdjustmentEntry_Srv.java | 23 |
| shared/VoucherAdjustmentEntry_SrvAsync.java | 23 |

## UI labels / columns (client)
`Vr. No.`, `Vr. Date`, `Ref. No.`, `Vr. Type`, `Ledger`, `A/c Code`, `A/c Name`, `City`, `Narration`, `Debit`, `Credit`

## Models (shared DTOs)
### GrdLedger_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | companyCode |
| String | v_TypeCode |
| String | v_TypeName |
| RecId | recId |
| String | v_SNo |
| String | v_Date |
| String | v_Prefix |
| String | site_Code |
| String | refNo |
| String | refDate |
| String | acCode |
| String | acName |
| String | acManualCode |
| String | cityCode |
| String | cityName |
| String | narration |
| String | debit |
| String | credit |
| String | adjustment |
| String | itemAdjustment |
| List<GrdAdjustment_Mdl> | lstAdjustment |
| List<GrdItemAdjustment_Mdl> | lstItemAdjustment |

### Filter_Mdl
| type | field |
|---|---|
| String | voucherNo |
| String | voucherDate |
| String | refNo |
| String | voucherTypeCode |
| String | voucherTypeName |
| String | acCode |
| String | acName |

### TxtHLedger_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | city |

## RPC interface
| method | params | returns |
|---|---|---|
| getLedger | Filter_Mdl param, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdLedger_Mdl>> |
| getLedgerByDocId | String voucherDocId | DmResult<List<GrdLedger_Mdl>> |
| getTxtHLedger | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHLedger_Mdl>> |
| getTxtHVoucher | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| saveRecord | List<GrdLedger_Mdl> lstRecords, String voucherDocId | DmResult<String> |

## Server `VoucherAdjustmentEntry_SrvImpl.java` (609 lines)
Helpers used: `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnDBName`, `DmServer.getSsnPLTCode`, `DmServer.getSsnSiteCode`, `DmServer.vNull`, `DmShared.convertDate`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.xNull`

### `getLedger`
`public DmResultPageing<List<GrdLedger_Mdl>> getLedger(Filter_Mdl param, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT l.docId , l.recId , l.v_SNo , l.recIdPrefix , l.v_No , l.v_Date , l.V_Prefix, l.Site_Code , l.refNo , l.refDate , l.narration , l.amtDr , l.amtCr , l.v_Type AS v_TypeCode , vt.short_Name AS v_TypeName , l.subCode AS acCode , sg.name AS acName , sg.manualCode AS acManualCode , sg.cityCode , ct.cityName FROM ledger l LEFT JOIN voucher_Type vt ON vt.v_Type = l.v_Type LEFT JOIN subGroup sg ON sg.subcode = l.subCode LEFT JOIN city ct ON ct.cityCode = sg.cityCode WHERE l.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:recId` ← `param.getVoucherNo() + "%"`; `:v_Date` ← `DmShared.convertDate(param.getVoucherDate())`; `:refNo` ← `param.getRefNo() + "%"`

### `getLedgerByDocId`
`public DmResult<List<GrdLedger_Mdl>> getLedgerByDocId(String voucherDocId)`

- SQL 1: `SELECT l.docId , l.recId , l.v_SNo , l.recIdPrefix , l.v_No , l.v_Date , l.V_Prefix, l.Site_Code , l.refNo , l.refDate , l.narration , l.amtDr , l.amtCr , l.v_Type AS v_TypeCode , vt.short_Name AS v_TypeName , l.subCode AS acCode , sg.name AS acName , sg.manualCode AS acManualCode , sg.cityCode , ct.cityName FROM ledger l LEFT JOIN voucher_Type vt ON vt.v_Type = l.v_Type LEFT JOIN subGroup sg ON sg.subcode = l.subCode LEFT JOIN city ct ON ct.cityCode = sg.cityCode WHERE l.docId = :docId AND ISNULL(l.itemAdj_of_V_sno, :zero) = :zero`
- Params: `:docId` ← `DmShared.xNull(voucherDocId)`; `:zero` ← `0`
- Other calls: `AccountAdjustment_Server.getAdjustments`, `ItemAdjustment_Server.getItemAdjustments`

### `getTxtHLedger`
`public DmResultPageing<List<TxtHLedger_Mdl>> getTxtHLedger(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode , sg.name , sg.manualCode , ct.cityName FROM subGroup sg LEFT JOIN city ct ON ct.cityCode = sg.cityCode LEFT JOIN subGroupSiteList sgl ON sgl.subCode = sg.subCode WHERE sgl.site_Code = :site_Code AND sg.isActive = 1`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHVoucher`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVoucher(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type as code , vt.description as name FROM voucher_Type vt WHERE vt.isActive = 1`

### `saveRecord`
`public DmResult<String> saveRecord(List<GrdLedger_Mdl> lstRecords, String voucherDocId)`

- Other calls: `AccountAdjustment_Server.deleteRecord`, `AccountAdjustment_Server.saveRecord`, `AccountAdjustment_Server.validateAdjustment`, `ItemAdjustment_Server.deleteRecord`, `ItemAdjustment_Server.saveRecord`, `ItemAdjustment_Server.validateItemAdjustment`

### `getCompanyCode`
`private String getCompanyCode(String v_Prefix, Connection cnTemp) throws Exception`

- SQL 1: `SELECT c.comp_Code FROM {…} ..company c WHERE c.v_Prefix = :v_Prefix AND c.pltCode = :pltCode`
- Params: `:v_Prefix` ← `DmShared.xNull(v_Prefix)`; `:pltCode` ← `DmServer.getSsnPLTCode(this)`

## Depends on (imports from other packages)
`dataman.accounts.voucherAdjustmentEntry.shared`, `dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.accountAdjustment.client`, `dataman.dmToolkit.accountAdjustment.server`, `dataman.dmToolkit.accountAdjustment.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.itemAdjustment.client`, `dataman.dmToolkit.itemAdjustment.server`, `dataman.dmToolkit.itemAdjustment.shared`, `dataman.dmToolkit.modifyReason.shared`, `dataman.libraryMenu.self.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
