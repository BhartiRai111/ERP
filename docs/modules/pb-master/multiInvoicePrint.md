# pb-master / multiInvoicePrint

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/master/src/multiInvoicePrint/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: From Date (Text); To Date (Text); Direct Print Required (Text); Voucher Type (Text)
- Lookups/suggestions: getTxtHVoucherType, getTxtHVoucherNo, getTxtHParty
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `MultiInvoicePrint`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | From Date | DmTextBox | `txtFromDate` | `11, true` |
| 2 | To Date | DmTextBox | `txtToDate` | `11, true` |
| 3 | Direct Print Required | DmTextHelp | `txtHIsDirectPrintRequired` | `com.google.gwt.core.shared.KeyField_PrpAcs, 4, true, true` |
| 4 | Voucher Type | DmTextHelp | `txtHVoucherType` | `KeyField_PrpAcs, 8, true` |
| 5 | Party | DmTextHelpMulti | `txtHParty` | `TxtHSupplierName_PrpAcs, 7` |
| 6 | Entry No. | DmTextHelpMulti | `txtHVoucherNo` | `Voucher_PrpAcs, 8` |

Other widgets (not in tab list): `txtDecimalPlace` (DmTextBoxNumeric), `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `dmFind`: Unit Name [Text, w450, name]; Decimal Places [Text, w250, decimalPlaces]; GST Unit [Text, w300, gstUnitName]; Unit Nature [Text, w200, nature]
- `txtHVoucherType`: Name [Text, w0, name]
- `txtHVoucherNo`: Entry No. [Text, w190, name]; Type [Text, w80, voucherType]; Date [Text, w110, replace(convert(varchar, v_Date, 106), ' ','-')]
- `txtHParty`: Name [Text, w220, supplierName]; Manual Code [Text, w80, manualCode]; City Name [Text, w80, cityName]
- `txtHIsDirectPrintRequired`: Type [Text, w0, Name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHVoucherType` → `getTxtHVoucherType`
- `txtHIsDirectPrintRequired` → `getTxtYesNo`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `iniDefaultValues`
- `txtFromDate.setText` ← DmClient.getStartDate()
- `txtToDate.setText` ← DmClient.getLoginDate()
- `txtHIsDirectPrintRequired.setTag` ← "1"
- `txtHIsDirectPrintRequired.setText` ← "Yes"

**`manageDisplay()` logic (condensed code):**
```java
flwPrintRecord.clear();
flwPrintRecord.add(flwBtnPrintRecord);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHVoucherNo.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHVoucherNo(category, partyCode, helper, blnToEndOfFile, new AsyncCallback<DmRe
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHVoucherNo).show();
public void onSuccess(DmResultPageing<List<TxtHVoucherNo_Mdl>> result) {
if (result.isStatus()) {
txtHVoucherNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHVoucherNo).show();
```

`txtHVoucherNo.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHVoucherNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHVoucherNo).show();
```

`txtHVoucherNo.addTextHelpMultiCallBack` → `getSelection()`
```java
if (blnStatus && !DmShared.xNull(keyCode).isEmpty()) {
docIdCSV = DmShared.xNull(keyCode);
} else {
new DmAlertMessageBox("Please select at least one record", txtHVoucherNo).show();
```

`txtHVoucherType.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHVoucherType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
public void onFailure(Throwable caught) {
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

`txtHVoucherType.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus) {
category = DmShared.xNull(t1Row.getSharedCancelledBy());
```

`txtHParty.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHParty(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHPart
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHParty).show();
public void onSuccess(DmResultPageing<List<TxtHParty_Mdl>> result) {
if (result.isStatus()) {
txtHParty.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHParty).show();
```

`txtHParty.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHParty.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHParty).show();
```

`txtHParty.addTextHelpMultiCallBack` → `getSelection()`
```java
if (blnStatus) {
partyCode = DmShared.xNull(keyCode);
```

`txtHIsDirectPrintRequired.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHIsDirectPrintRequired.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHIsDirectPrintRequired).show();
```

`txtHIsDirectPrintRequired.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHIsDirectPrintRequired.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHIsDirectPrintRequired).show();
```

**Menu items (Other button):** Export Data

_Print permission "P" is removed for this screen (replace "P" with "*")._

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to export records."
- Alert: "Please select at least one record"

## Files
| file | lines |
|---|---|
| client/MultiInvoicePrint.java | 330 |
| client/MultiInvoicePrint_Dsg.java | 288 |
| server/MultiInvoicePrint_SrvImpl.java | 379 |
| shared/Find_Mdl.java | 50 |
| shared/MultiInvoicePrint_Mdl.java | 78 |
| shared/MultiInvoicePrint_Srv.java | 23 |
| shared/MultiInvoicePrint_SrvAsync.java | 24 |
| shared/MultiInvoicePrint_Vld.java | 14 |
| shared/TxtHParty_Mdl.java | 31 |
| shared/TxtHVoucherNo_Mdl.java | 32 |

## UI labels / columns (client)
`From Date`, `To Date`, `Direct Print Required`, `Voucher Type`, `Party`, `Entry No.`

## Models (shared DTOs)
### TxtHVoucherNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | voucherDate |
| String | voucherType |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | decimalPlaces |
| String | gstUnitName |
| String | unitNatureCode |
| String | unitNatureName |

### TxtHParty_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityName |

### MultiInvoicePrint_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | decimalPlaces |
| String | gstUnitCode |
| String | gstUnitName |
| String | unitNatureCode |
| String | unitNatureName |
| String | isSystemGenerated |
| ModifyReason_Mdl | modifyReasonMdl |

## RPC interface
| method | params | returns |
|---|---|---|
| iniDefaultValues |  | DmResult<KeyFieldExtended> |
| getTxtYesNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVoucherNo | String category, String partyCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucherNo_Mdl>> |
| getTxtHVoucherType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHParty | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| printRecords | String docId, String v_Type | DmResult<List<String>> |

## Validator `MultiInvoicePrint_Vld`
```java
public class MultiInvoicePrint_Vld {
    public static void chkPrintRecord(String fromDate, String toDate, String voucherType, String isDirectPrintRequired) {
        DmShared.isValidText(fromDate.trim(), "From Date", 100);
        DmShared.isValidText(toDate.trim(), "To Date", 200);
        DmShared.isValidText(isDirectPrintRequired.trim(), "Direct Print Required", 300);
        DmShared.isValidText(voucherType.trim(), "Voucher Type", 400);
    }
}
```

## Server `MultiInvoicePrint_SrvImpl.java` (379 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.xNull`

### `iniDefaultValues`
`public DmResult<KeyFieldExtended> iniDefaultValues()`

- SQL 1: `SELECT gu.code, gu.name FROM gstUnitMast gu WHERE gu.manualCode = :manualCode`
- Params: `:manualCode` ← `"OTH"`

### `getTxtHVoucherType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVoucherType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select vt.v_Type as code, vt.description as name, vt.balancingAc , vt.short_Name, vt.category, sg.name as balancingAcName from voucher_Type vt left join subGroup sg on sg.subCode = vt.balancingAc where vt.category IN( {…} ) and vt.isActive = 1`

### `getTxtHVoucherNo`
`public DmResultPageing<List<TxtHVoucherNo_Mdl>> getTxtHVoucherNo(String category, String partyCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select sh.docId AS code,sh.recId AS name , sh.v_Date , sh.v_No , vt.short_Name as voucherType , CASE WHEN sh.site_Code = {…} THEN 1 ELSE 2 END as tempSr from {…} sh LEFT JOIN voucher_Type AS vt ON vt.v_Type=sh.v_Type LEFT JOIN siteMast sm ON sm.code = sh.site_Code where 1=1 AND ( :partyCode = '' OR sh.partyCode = :partyCode ) AND sh.site_Code in( {…} :siteList {…} )`
- Params: `:partyCode` ← `DmShared.xNull(partyCode)`

### `getTxtYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHParty`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHParty(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as supplierName , sg.manualCode , c.cityName FROM subGroup sg LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( SELECT DISTINCT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_Type = acf.v_Type WHERE vt.category IN (' {…} ') )`

### `printRecords1`
`public DmResult<String> printRecords1(String docIdCSV, String v_Type)`


### `printRecords`
`public DmResult<List<String>> printRecords(String docIdCSV, String v_Type)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.documentSystemReport.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
