# dm-utility / numberSystemOld

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/utility/src/numberSystemOld/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: numberSystem, numberSystemCounter
- Update tables: -
- Delete cascade (in order): numbersystemformat, numberSystem, related
- Duplicate checks on save: none found
- "In use" checks before delete:
  - `SELECT count(*) AS count FROM numberSystemCounter WHERE nsGroup=:nsGroup`
- Validations: Voucher Type (Text); rule: Counter Format is mandatory
- Lookups/suggestions: getTxtHSite, getTxtHNSVoucherType, getTxtHIsYesNo, getTxtHVoucherType
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `CounterGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Number Format | DmTextBox | `txtNumberFormat` | `10, false` |
| 2 | Voucher Prefix | DmTextBoxNumeric | `txtvoucherPrefix` | `0, false, 2, false` |
| 3 | Site Name | DmTextHelp | `txtHSiteCode` | `KeyField_PrpAcs, 10` |
| 4 | Counter  | DmTextBoxNumeric | `txtCounter` | `0, false, 10, false` |

**Grid / popup columns** (header, width, db field):
- `txtHSiteCode`: Name [Text, w0, Name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHSiteCode` → `getTxtHSite`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHSiteCode.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHSite(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSiteCode).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHSiteCode.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHSiteCode).show();
```

`txtHSiteCode.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSiteCode.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHSiteCode).show();
```

`txtHSiteCode.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus) {
```
- Confirm: " Do you want to save this record ?"
- Confirm: "Are you sure? You want to permanent delete this record. "
- Alert: "This  Number Format already exists."

### Screen `Counter`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `CounterGrd` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `CounterGrd`: Number Format [Text, w210]; Voucher Prifix [Text, w125]; Site Name [Text, w120]; Counter [Text, w200]

**Lookup / suggestion / find sources (server RPC called):**
- `grdpopup` → `deleteRecordPopUp`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`grdpopup.addCallBack` → `onDelete()`
```java
CounterGrd.commitChanges();
SrvAsync.invokeRPC().deleteRecordPopUp(linkedUserGrid, new AsyncCallback<DmResult<String>>() {
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
return;
public void onSuccess(DmResult<String> result) {
if (result.isStatus()) {
filldata(nsGroup);
```

`grdpopup.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
filldata(nsGroup);
```

`grdpopup.addCallBack` → `onOk()`
```java
CounterGrd.commitChanges();
SrvAsync.invokeRPC().InsertRecordPopUp(linkedUserGrid, new AsyncCallback<DmResult<String>>() {
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResult<String> result) {
if (result.isStatus()) {
filldata(nsGroup);
```

`grdpopup.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
filldata(nsGroup);
```

### Screen `EntryGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Number Format | DmTextBox | `txtNumberFormat` | `150, true` |
| 2 | Counter Format | DmTextBox | `txtCounterFormat` | `10, true` |
| 3 | Site Name | DmTextHelp | `txtHSiteCode` | `KeyField_PrpAcs, 10` |
| 4 | Reset Site Wise | DmTextHelp | `txtHIsSiteWise` | `KeyField_PrpAcs, 4, true` |
| 5 | Reset Year Wise | DmTextHelp | `txtHIsYearWise` | `KeyField_PrpAcs, 4, true` |

Other widgets (not in tab list): `dmNuberFormatGrd` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHSiteCode`: Name [Text, w0, Name]
- `txtHIsSiteWise`: Name [Text, w0, Name]
- `txtHIsYearWise`: Name [Text, w0, Name]
- `dmNuberFormatGrd`: Number Format [Text, w150]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHSiteCode` → `getTxtHSite`
- `txtHIsSiteWise` → `getTxtHIsYesNo`
- `txtHIsYearWise` → `getTxtHIsYesNo`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHSiteCode.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHSite(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSiteCode).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHSiteCode.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHSiteCode).show();
```

`txtHSiteCode.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSiteCode.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHSiteCode).show();
```

`txtHSiteCode.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus) {
```

`txtHIsSiteWise.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHIsYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFie
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHIsSiteWise).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHIsSiteWise.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHIsSiteWise).show();
```

`txtHIsSiteWise.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHIsSiteWise.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHIsSiteWise).show();
```

`txtHIsSiteWise.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus) {
```

`txtHIsYearWise.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHIsYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFie
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHIsYearWise).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHIsYearWise.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHIsYearWise).show();
```

`txtHIsYearWise.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHIsYearWise.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHIsYearWise).show();
```

`txtHIsYearWise.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus) {
```
- Alert: "Only Zero value allow"
- Alert: "Only Number value allow"
- Alert: "Number Format is mandatory."
- Alert: "Counter Format is mandatory."
- Alert: "Is site wise is mandatory."
- Alert: "Is year wise is mandatory."
- Alert: "{counter} is mandatory."
- Alert: "Only Zero value allow"
- Alert: "Only Number value allow"
- Alert: "Number Format for this Site is already exists."
- Alert: "This number format has been used in number format"
- Alert: "This number format has been used in number format"

### Screen `NumberSystemOld`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Voucher Type | DmTextHelp | `txtHNSVtype` | `DmTxtHVoucherType_PrpAcs, 10, true` |

Other widgets (not in tab list): `dmFind` (DmFind), `dmVoucherGrd` (DmGrid), `dmEntryGrd` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHNSVtype`: Name [Text, w0, Name]; Category [Text, w100, Category]
- `dmFind`: Number System [Text, w200, Name]; Category [Text, w100, Category]
- `dmVoucherGrd`: Include More Type [Text, w1]
- `dmEntryGrd`: Number Format [Text, w1]; Counter Format [Text, w300]; Site Name [Text, w120]; Reset Site Wise [Text, w95]; Reset Year Wise [Text, w95]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHNSVtype` → `getTxtHNSVoucherType`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldCode` ← DmShared.xNull(txtHNSVtype.getTag())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- `txtHNSVtype.setText` ← data.getSharedKeyFieldName()
- `txtHNSVtype.setTag` ← data.getSharedKeyFieldCode()
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**On New click:**
- `clear()`
- `txtHNSVtype.setFocus(true)`
- `counterPopUpButton.setVisible(false)`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
clear();
txtHNSVtype.setFocus(true);
dmVoucherGrd.getGrid().getStore().clear();
dmVoucherGrd.getGrid().getStore().add(new KeyFieldExtended());
dmEntryGrd.getGrid().getStore().clear();
dmEntryGrd.getGrid().getStore().add(new GrdNumberFormat_Mdl());
counterPopUpButton.setVisible(false);
```

`topControl.addEditHandeller` → `editClicked()`
```java
txtHNSVtype.setFocus(true);
txtHNSVtype.setEnabled(false);
dmVoucherGrd.getGrid().getStore().add(new KeyFieldExtended());
dmEntryGrd.getGrid().getStore().add(new GrdNumberFormat_Mdl());
```

`topControl.addDeleteHandeller` → `deleteClicked()`
```java
new DmConfirmMessageBox("Are you sure? You want to delete this record.", new DialogHideEvent.DialogHideHandler() {
public void onDialogHide(DialogHideEvent event) {
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
topControlDelete(topControl.getDocId());
}).show();
```

`topControl.addDeleteHandeller` → `onDialogHide()`
```java
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
topControlDelete(topControl.getDocId());
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
topControlSave();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(NumberSystemOld.this);
```

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
```

`txtHNSVtype.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHNSVoucherType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHNSVtype).show();
public void onSuccess(DmResultPageing<List<TxtHVoucherType_Mdl>> result) {
if (result.isStatus()) {
txtHNSVtype.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHNSVtype).show();
```

`txtHNSVtype.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHNSVtype.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHNSVtype).show();
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
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), dmFind.getTextBox()).show();
```

`dmFind.addHelpGridCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
dmFind.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), dmFind.getTextBox()).show();
```

`dmFind.addHelpGridCallBack` → `getSelectedRow()`
```java
flwForm.remove(dmFind);
flwForm.add(vrtInnerForm);
if (blnStatus) {
topControlMoveRec(tRow.getNsGroup());
```

`grdEntry.addCallBack` → `onOk()`
```java
dmEntryGrd.commitChanges();
```

`grdEntry.addCallBack` → `onDelete()`
```java
dmEntryGrd.commitChanges();
```

`grdVoucherEntry.addCallBack` → `onOk()`
```java
dmVoucherGrd.commitChanges();
```

`grdVoucherEntry.addCallBack` → `onDelete()`
```java
dmVoucherGrd.commitChanges();
```

**Top control options:** setRefreshRequired(false), setDisplay(true)

_Print permission "P" is removed for this screen (replace "P" with "*")._
- Confirm: "Are you sure? You want to delete this record."
- Confirm: "Are you sure? You want to export records."
- Alert: "Please Select Voucher Type."

### Screen `VoucherGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Voucehr Type | DmTextHelp | `txtHVoucherType` | `DmTxtHVoucherType_PrpAcs, 10, true` |

**Grid / popup columns** (header, width, db field):
- `txtHVoucherType`: Name [Text, w0, Name]; Category [Text, w100, Category]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHVoucherType` → `getTxtHVoucherType`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHVoucherType.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHVoucherType(vTypeCode, helper, blnToEndOfFile, new AsyncCallback<DmResultPage
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHVoucherType).show();
public void onSuccess(DmResultPageing<List<TxtHVoucherType_Mdl>> result) {
if (result.isStatus()) {
txtHVoucherType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHVoucherType).show();
```

`txtHVoucherType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHVoucherType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHVoucherType).show();
```

`txtHVoucherType.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus) {
```
- Alert: "Voucher Type is already exists."

## Files
| file | lines |
|---|---|
| client/Counter.java | 198 |
| client/CounterGridEdit.java | 213 |
| client/CounterGridEdit_Dsg.java | 137 |
| client/Counter_Dsg.java | 111 |
| client/EntryGridEdit.java | 419 |
| client/EntryGridEdit_Dsg.java | 179 |
| client/NumberSystemOld.java | 555 |
| client/NumberSystemOld_Dsg.java | 274 |
| client/VoucherGridEdit.java | 153 |
| client/VoucherGridEdit_Dsg.java | 81 |
| server/NumberSystemOld_SrvImpl.java | 1186 |
| shared/Find_Mdl.java | 54 |
| shared/GrdCounter_Mdl.java | 72 |
| shared/GrdNumberFormat_Mdl.java | 98 |
| shared/NumberSystemOld.java | 38 |
| shared/NumberSystemOld_Srv.java | 46 |
| shared/NumberSystemOld_SrvAsync.java | 47 |
| shared/NumberSystemOld_Vld.java | 24 |
| shared/TxtHVoucherType_Mdl.java | 43 |

## UI labels / columns (client)
`Number Format`, `Counter Format`, `Site Name`, `Reset Site Wise`, `Reset Year Wise`, `Voucher Type`, `Voucher Prefix`, `Counter `, `Voucehr Type`

## Models (shared DTOs)
### TxtHVoucherType_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | category |
| String | description |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | category |
| String | Description |
| String | nsGroup |

### GrdCounter_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | numberFormat |
| String | counterFormat |
| String | siteCode |
| String | siteName |
| String | voucherPre |

### GrdNumberFormat_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | numberFormat |
| String | counterFormat |
| String | siteCode |
| String | siteName |
| String | isSiteWiseCode |
| String | isSiteWiseName |
| String | isYearWiseCode |
| String | isYearWiseName |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<NumberSystemOld> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| fillGrid | String vTypeCode | DmResult<List<GrdNumberFormat_Mdl>> |
| fillType | String voucherType | DmResult<List<KeyFieldExtended>> |
| saveRecord | NumberSystemOld mrTemp | DmResult<String> |
| deleteRecord | String strKey | DmResult<String> |
| getTxtHSite | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHIsYesNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHNSVoucherType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucherType_Mdl>> |
| getTxtHVoucherType | String vTypeCode, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucherType_Mdl>> |
| fillNumberFormat |  | DmResult<List<KeyFieldExtended>> |
| getCountList | String strKey | DmResult<List<GrdCounter_Mdl>> |
| deleteRecordPopUp | GrdCounter_Mdl grdCounter_Mdl | DmResult<String> |
| chkNumberFormat | String numberFormat, List<KeyFieldExtended> numberFormatLst | DmResult<String> |
| InsertRecordPopUp | GrdCounter_Mdl grdCounter_Mdl | DmResult<String> |
| exportDataToCSV | String session | DmResult<KeyFieldExtended> |

## Validator `NumberSystemOld_Vld`
```java
public class NumberSystemOld_Vld {
    public static void chkSave(NumberSystemOld mrTemp) {
        boolean flag = false;
        DmShared.isValidText(mrTemp.getSharedKeyFieldCode().trim(), "Voucher Type", 100);
        for (int i = 0; i < mrTemp.getGrdNumberFormat().size(); i++) {
            GrdNumberFormat_Mdl numberFormat = mrTemp.getGrdNumberFormat().get(i);
            if ((!DmShared.xNull(numberFormat.getNumberFormat()).equalsIgnoreCase("")) && (!DmShared.xNull(numberFormat.getCounterFormat()).equalsIgnoreCase(""))) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            throw new DmException(200, "Counter Format is mandatory");
        }
    }
}
```

## Server `NumberSystemOld_SrvImpl.java` (1186 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmServer.getSsnPLTCode`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `SELECT MAX(nsGroup) AS SearchCode FROM numbersystemformat GROUP BY nsGroup ORDER BY nsGroup`

### `getMoveRec`
`public DmResult<NumberSystemOld> getMoveRec(String strKey)`

- SQL 1: `SELECT nsf.counterFormat,nsf.nsFormat,nsf.nsGroup,CONCAT((VT.Description),' (', (VT.short_Name),')' )AS NAME ,nsf.site_Code,nsf.isSiteWise,nsf.isYearWise,sm.name as siteName FROM numbersystemformat nsf LEFT JOIN sitemast sm ON nsf.site_Code = sm.code LEFT JOIN voucher_type vt ON nsf.nsGroup = vt.v_Type WHERE nsf.nsGroup= :nsGroup`
- SQL 2: `SELECT ns.v_type,CONCAT((VT.Description),' (', (VT.short_Name),')' )AS NAME ,FORMAT(ns.u_EntDt,'HH.mm') as U_time , ns.u_EntDt, ns.u_Name FROM numberSystem ns LEFT JOIN voucher_type vt ON ns.v_Type=vt.v_Type WHERE ns.nsGroup= :nsGroup and ns.v_Type <> :nsGroup ORDER BY vt.short_Name`
- Params: `:nsGroup` ← `strKey`; `:nsGroup` ← `strKey`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT (ns.v_type) AS CODE,ns.nsGroup,CONCAT((VT.Description),' (', (VT.short_Name),')' )AS NAME ,(VT.category) AS category,(VT.Description) AS Description FROM numberSystem ns LEFT JOIN voucher_type vt ON ns.v_type=vt.v_Type`

### `fillGrid`
`public DmResult<List<GrdNumberFormat_Mdl>> fillGrid(String vTypeCode)`

- SQL 1: `SELECT nsf.counterFormat,nsf.nsFormat,nsf.nsGroup,vt.short_Name,nsf.site_Code,nsf.isSiteWise,nsf.isYearWise,sm.name as siteName FROM numbersystemformat nsf LEFT JOIN sitemast sm ON nsf.site_Code=sm.code LEFT JOIN voucher_type vt ON nsf.nsGroup=vt.v_Type WHERE nsf.nsGroup= :nsGroup`
- Params: `:nsGroup` ← `vTypeCode`

### `fillType`
`public DmResult<List<KeyFieldExtended>> fillType(String voucherType)`

- SQL 1: `SELECT V_Type,Description,short_Name FROM Voucher_Type WHERE V_Type<> :voucherType`

### `saveRecord`
`public DmResult<String> saveRecord(NumberSystemOld mrTemp)`

- SQL 1: `Delete From numbersystemformat Where nsGroup = :nsGroup`
- SQL 2: `Delete From numberSystem Where nsGroup = :nsGroup`
- SQL 3: `Insert Into numberSystem(nsGroup,v_type,u_Name,u_EntDt, includeV_Type) Values (:nsGroup,:v_type,:u_Name,GETDATE(), :includeV_Type)`
- Params: `:nsGroup` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:nsGroup` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:nsGroup` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:site_Code` ← `DmShared.foriegnKey(numberFormat.getSiteCode())`; `:nsFormat` ← `DmShared.xNull(numberFormat.getNumberFormat())`; `:counterFormat` ← `DmShared.xNull(numberFormat.getCounterFormat())`; `:isYearWise` ← `DmServer.vNull(numberFormat.getIsYearWiseCode(), "0")`; `:isSiteWise` ← `DmServer.vNull(numberFormat.getIsSiteWiseCode(), "0")`; `:nsGroup` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:u_Name` ← `DmShared.xNull(DmServer.getSsnUserName(this))`; `:v_type` ← `DmShared.foriegnKey(mrTemp.getSharedKeyFieldCode())`; `:includeV_Type` ← `DmShared.xNull(includedVType)`; `:nsGroup` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:u_Name` ← `DmShared.xNull(DmServer.getSsnUserName(this))`; `:v_type` ← `DmShared.foriegnKey(vType.getSharedKeyFieldCode())`; `:includeV_Type` ← `DmShared.xNull(includedVType)`
- Other calls: `NumberSystemOld_Vld.chkSave`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey)`

- SQL 1: `SELECT count(*) AS count FROM numberSystemCounter WHERE nsGroup=:nsGroup`
- SQL 2: `Delete From numbersystemformat Where nsGroup=:nsGroup`
- SQL 3: `Delete From numberSystem Where nsGroup =:nsGroup`
- SQL 4: `Delete related counter.`
- Params: `:nsGroup` ← `strKey`; `:nsGroup` ← `strKey`; `:nsGroup` ← `strKey`
- Errors: "Delete related counter."

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT SM.Code,SM.Name FROM SiteMast SM WHERE SM.pltCode= :pltCode`
- Params: `:pltCode` ← `DmServer.getSsnPLTCode(this)`

### `getTxtHNSVoucherType`
`public DmResultPageing<List<TxtHVoucherType_Mdl>> getTxtHNSVoucherType(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.V_Type AS code , CONCAT(vt.description,' (', vt.short_Name,')' ) AS name , vt.category, vt.Description FROM Voucher_Type vt WHERE v_type NOT IN ( SELECT v_type FROM( SELECT ns.v_type FROM numberSystem ns UNION ALL SELECT nsGroup AS v_Type FROM numbersystemformat ) AS tab )`

### `fillNumberFormat`
`public DmResult<List<KeyFieldExtended>> fillNumberFormat()`


### `getTxtHIsYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHIsYesNo(DmHelper pageing, boolean blnToEndOfFile)`


### `getTxtHVoucherType`
`public DmResultPageing<List<TxtHVoucherType_Mdl>> getTxtHVoucherType(String vTypeCode, DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.V_Type AS code , CONCAT(vt.Description,' (', vt.short_Name,')' ) AS name , vt.category,vt.Description FROM Voucher_Type vt WHERE vt.V_Type <>' {…} ' AND v_type NOT IN ( SELECT v_type FROM( SELECT v_type FROM numberSystem UNION ALL SELECT nsGroup AS v_Type FROM numbersystemformat ) AS tab )`

### `getCountList`
`public DmResult<List<GrdCounter_Mdl>> getCountList(String strKey)`

- SQL 1: `SELECT nsc.nsFormatValue AS Value, nsc.v_prefix AS v_type, nsc.site_Code AS scode, sm.name AS snanm, nsc.counter AS counter FROM numberSystemCounter AS nsc LEFT JOIN numberSystem AS ns ON nsc.nsGroup=ns.nsGroup LEFT JOIN siteMast AS sm ON sm.code = nsc.site_Code LEFT JOIN voucher_Type AS vt ON vt.v_Type = nsc.nsGroup WHERE nsc.nsGroup=:nsGroup`
- Params: `:nsGroup` ← `strKey`

### `deleteRecordPopUp`
`public DmResult<String> deleteRecordPopUp(GrdCounter_Mdl linkedUserGrid)`

- SQL 1: `Delete From numberSystemCounter Where nsGroup =:nsGroup AND nsFormatValue =:nsFormatValue AND v_prefix=:v_prefix AND isnull( site_Code,'')=:site_Code`
- Params: `:nsFormatValue` ← `DmShared.xNull(linkedUserGrid.getNumberFormat())`; `:v_prefix` ← `DmShared.xNull(linkedUserGrid.getVoucherPre())`; `:site_Code` ← `DmShared.xNull(linkedUserGrid.getSiteCode())`; `:nsGroup` ← `DmShared.xNull(linkedUserGrid.getSharedKeyFieldCode())`

### `chkNumberFormat`
`public DmResult<String> chkNumberFormat(String numberFormat, List<KeyFieldExtended> numberFormatLst)`


### `InsertRecordPopUp`
`public DmResult<String> InsertRecordPopUp(GrdCounter_Mdl objGrdCounter_Mdl)`

- SQL 1: `SELECT count(*) as cnt FROM numberSystemCounter WHERE nsFormatValue =:nsFormatValue AND v_prefix=:v_prefix AND isnull(site_Code,'')=:site_Code AND nsGroup =:nsGroup`
- SQL 2: `SELECT nsGroup,nsFormatValue,v_prefix,site_code,counter FROM numberSystemCounter WHERE nsGroup =:nsGroup`
- SQL 3: `Delete From numberSystemCounter Where nsGroup =:nsGroup`
- SQL 4: `INSERT INTO numberSystemCounter (nsGroup,nsFormatValue,v_prefix,site_Code,counter) VALUES (:nsGroup,:nsFormatValue,:v_prefix,:site_Code,:counter)`
- Params: `:nsFormatValue` ← `DmShared.xNull(objGrdCounter_Mdl.getNumberFormat())`; `:v_prefix` ← `DmShared.xNull(objGrdCounter_Mdl.getVoucherPre())`; `:site_Code` ← `DmShared.xNull(objGrdCounter_Mdl.getSiteCode())`; `:nsGroup` ← `DmShared.xNull(objGrdCounter_Mdl.getSharedKeyFieldCode())`; `:nsFormatValue` ← `DmShared.xNull(objGrdCounter_Mdl.getNumberFormat())`; `:v_prefix` ← `DmShared.xNull(objGrdCounter_Mdl.getVoucherPre())`; `:site_Code` ← `DmShared.xNull(objGrdCounter_Mdl.getSiteCode())`; `:nsGroup` ← `DmShared.xNull(objGrdCounter_Mdl.getSharedKeyFieldCode())`; `:counter` ← `DmShared.xNull(objGrdCounter_Mdl.getCounterFormat())`; `:nsGroup` ← `DmShared.xNull(objGrdCounter_Mdl.getSharedKeyFieldCode())`; `:nsGroup` ← `DmShared.xNull(objGrdCounter_Mdl.getSharedKeyFieldCode())`; `:nsGroup` ← `DmShared.foriegnKey(lstCounterEntry.get(i).getSharedKeyFieldCode())`; `:nsFormatValue` ← `DmShared.xNull(lstCounterEntry.get(i).getNumberFormat())`; `:v_prefix` ← `DmShared.xNull(lstCounterEntry.get(i).getVoucherPre())`; `:site_Code` ← `DmShared.foriegnKey(lstCounterEntry.get(i).getSiteCode())`; `:counter` ← `DmServer.vNull(lstCounterEntry.get(i).getCounterFormat(), "0")`
- Errors: "This Number Format already exists."

### `exportDataToCSV`
`public DmResult<KeyFieldExtended> exportDataToCSV(String session)`

- SQL 1: `SELECT nsf.counterFormat,nsf.nsFormat,ns.nsGroup AS nsgVType,ns.v_Type AS nsVType,vtns.short_Name AS nsGroup,vtin.short_Name AS includeV_Type,vtin.short_Name AS v_Type,nsf.isSiteWise,nsf.isYearWise FROM numbersystemformat nsf LEft JOIN numberSystem ns ON ns.nsGroup=nsf.nsGroup LEFT JOIN sitemast sm ON nsf.site_Code = sm.code LEFT JOIN voucher_type vtin ON ns.v_Type= vtin.v_Type LEFT JOIN voucher_type vtns ON ns.nsGroup= vtns.v_Type WHERE nsf.site_Code is null and ns.v_Type is not null order by ns.nsGroup`
- Errors: "Invalid request." | "File Name not given."
- Other calls: `FILE_HEADER.toString`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.client`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.libraryMenu.self.shared`, `dataman.utility.numberSystemOld.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| deleteRecord | (unconditional) | "Delete related counter." |
| chkNumberFormat | `if (strArray.length > 2)` | " Number Format is repeating, You can use only one Number Format ." |
| InsertRecordPopUp | `} else if (DmShared.vNull(rsTemp.getString("cnt")).doubleValue() > 1)` | "This Number Format already exists." |
| exportDataToCSV | `if (!DmSession.getSessions().containsKey(sessionId))` | "Invalid request." |
| exportDataToCSV | `if (DmShared.xNull(fileName).equalsIgnoreCase(""))` | "File Name not given." |

## Notes (hand-written)
_No hand-written notes yet._
