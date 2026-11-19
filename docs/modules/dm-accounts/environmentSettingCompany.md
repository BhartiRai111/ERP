# dm-accounts / environmentSettingCompany

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/accounts/src/environmentSettingCompany/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: enviroCompany
- Update tables: enviroCompany
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: TDS Round Off (Text); GST Round Off (Text); TCS Date (Text); TCS Auto Calculate (Text); Auto TCS Based On (Text); Auto Instrument Clear Date (Text)
- Lookups/suggestions: getTxtHYesNo, getTxtHRoundOff, getTxtHAutoTCSBasedOn, getTxtHVoucherType
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `EnvironmentSettingCompany`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | TDS/TCS Round Off | DmTextHelp | `txtHRoundOffTDS` | `KeyField_PrpAcs, 6, true, true` |
| 2 | GST Round Off | DmTextHelp | `txtHRoundOffGST` | `KeyField_PrpAcs, 6, true, true` |
| 3 | TCS Date | DmTextBox | `txtTcsActiveFrom` | `11, true` |
| 4 | TCS Auto Calculate | DmTextHelp | `txtHTCSAutoCalculate` | `KeyField_PrpAcs, 6, true, true` |
| 5 | Auto TCS Based On | DmTextHelp | `txtHAutoTCSBasedOn` | `KeyField_PrpAcs, 6, false, false` |
| 6 | Sales Category For Turnover | DmTextHelpMulti | `txtHVoucherTypeDr` | `TxtHVoucherTypeDr_PrpAcs, 7, false` |
| 7 | Credit Notes Category For Turnover | DmTextHelpMulti | `txtHVoucherTypeCr` | `TxtHVoucherTypeCr_PrpAcs, 7, false` |
| 8 | TCS Limit | DmTextBoxNumeric | `txtTCSLimit` | `2, false, 11, false` |
| 9 | Auto Instrument Clear Date | DmTextHelp | `txtHAutoInstrumentClearDate` | `KeyField_PrpAcs, 6, true, true` |

**Grid / popup columns** (header, width, db field):
- `txtHRoundOffTDS`: Type [Text, w0, Name]
- `txtHRoundOffGST`: Type [Text, w0, Name]
- `txtHTCSAutoCalculate`: Type [Text, w0, Name]
- `txtHAutoTCSBasedOn`: Type [Text, w0, Name]
- `txtHVoucherTypeDr`: Category [Text, w60, code]; Description [Text, w150, description]
- `txtHVoucherTypeCr`: Category [Text, w60, code]; Description [Text, w150, description]
- `txtHAutoInstrumentClearDate`: Type [Text, w0]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHRoundOffTDS` → `getTxtHRoundOff`
- `txtHRoundOffGST` → `getTxtHRoundOff`
- `txtHTCSAutoCalculate` → `getTxtHYesNo`
- `txtHAutoInstrumentClearDate` → `getTxtHYesNo`
- `txtHAutoTCSBasedOn` → `getTxtHAutoTCSBasedOn`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `TdsRoundOffTag` ← DmShared.xNull(txtHRoundOffTDS.getTag())
- `TdsRoundOffText` ← DmShared.xNull(txtHRoundOffTDS.getText())
- `GstRoundOffTag` ← DmShared.xNull(txtHRoundOffGST.getTag())
- `GstRoundOffText` ← DmShared.xNull(txtHRoundOffGST.getText())
- `TcsActiveFrom` ← DmShared.dNull(txtTcsActiveFrom.getText())
- `TcsAutoCalculateTag` ← DmShared.xNull(txtHTCSAutoCalculate.getTag())
- `TcsAutoCalculateText` ← DmShared.xNull(txtHTCSAutoCalculate.getText())
- `AutoTCSBasedOnTag` ← DmShared.xNull(txtHAutoTCSBasedOn.getTag())
- `AutoTCSBasedOnText` ← DmShared.xNull(txtHAutoTCSBasedOn.getText())
- `TcsLimit` ← DmClient.vNull(txtTCSLimit.getText(), "0.00")
- `TcsTurnoverDrVCatTag` ← DmShared.xNull(txtHVoucherTypeDr.getTag())
- `TcsTurnoverDrVCatText` ← DmShared.xNull(txtHVoucherTypeDr.getText())
- `TcsTurnoverCrVCatTag` ← DmShared.xNull(txtHVoucherTypeCr.getTag())
- `TcsTurnoverCrVCatText` ← DmShared.xNull(txtHVoucherTypeCr.getText())
- `AutoInstrumentClearDateTag` ← DmShared.xNull(txtHAutoInstrumentClearDate.getTag())
- `AutoInstrumentClearDateText` ← DmShared.xNull(txtHAutoInstrumentClearDate.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtHRoundOffTDS.setTag` ← DmShared.xNull(mrTemp.getTdsRoundOffTag())
- `txtHRoundOffTDS.setText` ← DmShared.xNull(mrTemp.getTdsRoundOffText())
- `txtHRoundOffGST.setTag` ← DmShared.xNull(mrTemp.getGstRoundOffTag())
- `txtHRoundOffGST.setText` ← DmShared.xNull(mrTemp.getGstRoundOffText())
- `txtTcsActiveFrom.setText` ← DmShared.dNull(mrTemp.getTcsActiveFrom())
- `txtHTCSAutoCalculate.setTag` ← DmShared.xNull(mrTemp.getTcsAutoCalculateTag())
- `txtHTCSAutoCalculate.setText` ← DmShared.xNull(mrTemp.getTcsAutoCalculateText())
- `txtHAutoInstrumentClearDate.setTag` ← DmShared.xNull(mrTemp.getAutoInstrumentClearDateTag())
- `txtHAutoInstrumentClearDate.setText` ← DmShared.xNull(mrTemp.getAutoInstrumentClearDateText())
- `txtHAutoTCSBasedOn.setTag` ← DmShared.xNull(mrTemp.getAutoTCSBasedOnTag())
- `txtHAutoTCSBasedOn.setText` ← DmShared.xNull(mrTemp.getAutoTCSBasedOnText())
- `txtHVoucherTypeDr.setTag` ← DmShared.xNull(mrTemp.getTcsTurnoverDrVCatTag())
- `txtHVoucherTypeDr.setText` ← DmShared.xNull(mrTemp.getTcsTurnoverDrVCatText())
- `txtHVoucherTypeCr.setTag` ← DmShared.xNull(mrTemp.getTcsTurnoverCrVCatTag())
- `txtHVoucherTypeCr.setText` ← DmShared.xNull(mrTemp.getTcsTurnoverCrVCatText())
- `txtTCSLimit.setText` ← String.valueOf(mrTemp.getTcsLimit())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtHAutoTCSBasedOn.setEnabled(true)`
- `txtHAutoTCSBasedOn.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
if (DmShared.iNull(txtHTCSAutoCalculate.getTag()).intValue() == 1) {
txtHAutoTCSBasedOn.setEnabled(true);
} else {
txtHAutoTCSBasedOn.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`tabPanel.addSelectionHandler` → `onSelection()`
```java
if (event.getSelectedItem() == 1) {
```

`txtHRoundOffTDS.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHRoundOff(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHRoundOffTDS).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHRoundOffTDS.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHRoundOffTDS).show();
```

`txtHRoundOffTDS.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHRoundOffTDS.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHRoundOffTDS).show();
```

`txtHRoundOffTDS.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && tRow != null) {
```

`txtHRoundOffGST.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHRoundOff(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHRoundOffGST).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHRoundOffGST.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHRoundOffGST).show();
```

`txtHRoundOffGST.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHRoundOffGST.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHRoundOffGST).show();
```

`txtHRoundOffGST.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && tRow != null) {
```

`txtHTCSAutoCalculate.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyField
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHTCSAutoCalculate).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHTCSAutoCalculate.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTCSAutoCalculate).show();
```

`txtHTCSAutoCalculate.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHTCSAutoCalculate.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTCSAutoCalculate).show();
```

`txtHTCSAutoCalculate.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && tRow != null) {
if (DmShared.iNull(txtHTCSAutoCalculate.getTag()).intValue() == 1) {
txtHAutoTCSBasedOn.setEnabled(true);
} else {
txtHAutoTCSBasedOn.setTag("");
txtHAutoTCSBasedOn.setText("");
txtHAutoTCSBasedOn.setEnabled(false);
```

`txtHAutoInstrumentClearDate.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyField
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHAutoInstrumentClearDate).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHAutoInstrumentClearDate.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAutoInstrumentClearDate).show();
```

`txtHAutoInstrumentClearDate.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAutoInstrumentClearDate.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAutoInstrumentClearDate).show();
```

`txtHAutoInstrumentClearDate.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && tRow != null) {
```

`txtHAutoTCSBasedOn.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHAutoTCSBasedOn(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHAutoTCSBasedOn).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHAutoTCSBasedOn.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAutoTCSBasedOn).show();
```

`txtHAutoTCSBasedOn.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAutoTCSBasedOn.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAutoTCSBasedOn).show();
```

`txtHAutoTCSBasedOn.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && tRow != null) {
```

`txtHVoucherTypeDr.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHVoucherType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHVoucherTypeDr).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHVoucherTypeDr.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHVoucherTypeDr).show();
```

`txtHVoucherTypeDr.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHVoucherTypeDr.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHVoucherTypeDr).show();
```

`txtHVoucherTypeCr.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHVoucherType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHVoucherTypeCr).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHVoucherTypeCr.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHVoucherTypeCr).show();
```

`txtHVoucherTypeCr.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHVoucherTypeCr.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHVoucherTypeCr).show();
```
- Confirm: "Are you sure? You want to close."

## Files
| file | lines |
|---|---|
| client/EnvironmentSettingCompany.java | 498 |
| client/EnvironmentSettingCompany_Dsg.java | 403 |
| server/EnvironmentSettingCompany_SrvImpl.java | 469 |
| shared/DefaultValue_Mdl.java | 24 |
| shared/EnvironmentSettingCompany_Mdl.java | 159 |
| shared/EnvironmentSettingCompany_Srv.java | 24 |
| shared/EnvironmentSettingCompany_SrvAsync.java | 24 |
| shared/EnvironmentSettingCompany_Vld.java | 21 |

## UI labels / columns (client)
`TDS and TCS Details`, `TDS/TCS Round Off`, `GST Round Off`, `TCS Date`, `TCS Auto Calculate`, `Auto TCS Based On`, `Sales Category For Turnover`, `Credit Notes Category For Turnover`, `TCS Limit`, `Auto Instrument Clear Date`

## Models (shared DTOs)
### DefaultValue_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | isFinishedItemUsed |

### EnvironmentSettingCompany_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | tdsRoundOffTag |
| String | tdsRoundOffText |
| String | gstRoundOffTag |
| String | gstRoundOffText |
| String | tcsActiveFrom |
| String | tcsAutoCalculateTag |
| String | tcsAutoCalculateText |
| String | autoTCSBasedOnTag |
| String | autoTCSBasedOnText |
| String | tcsLimit |
| String | tcsTurnoverDrVCatTag |
| String | tcsTurnoverDrVCatText |
| String | tcsTurnoverCrVCatTag |
| String | tcsTurnoverCrVCatText |
| String | autoInstrumentClearDateTag |
| String | autoInstrumentClearDateText |

## RPC interface
| method | params | returns |
|---|---|---|
| getMoveRec | String strKey | DmResult<EnvironmentSettingCompany_Mdl> |
| saveRecord | EnvironmentSettingCompany_Mdl mrTemp | DmResult<String> |
| getTxtHYesNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValue |  | DmResult<DefaultValue_Mdl> |
| getTxtHRoundOff | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAutoTCSBasedOn | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVoucherType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `EnvironmentSettingCompany_Vld`
```java
public class EnvironmentSettingCompany_Vld {
    public static void chkSave(EnvironmentSettingCompany_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getTdsRoundOffTag(), "TDS Round Off ", 1200);
        DmShared.isValidText(mrTemp.getGstRoundOffTag(), "GST Round Off ", 1210);
        DmShared.isValidText(mrTemp.getTcsActiveFrom(), "TCS Date ", 1220);
        DmShared.isValidText(mrTemp.getTcsAutoCalculateTag(), "TCS Auto Calculate ", 1230);
        if (DmShared.iNull(mrTemp.getTcsAutoCalculateTag()).intValue() == 1) {
            DmShared.isValidText(mrTemp.getAutoTCSBasedOnTag(), "Auto TCS Based On ", 1240);
        }
        DmShared.isValidText(mrTemp.getAutoInstrumentClearDateTag(), "Auto Instrument Clear Date ", 1240);
    }
}
```

## Server `EnvironmentSettingCompany_SrvImpl.java` (469 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getMoveRec`
`public DmResult<EnvironmentSettingCompany_Mdl> getMoveRec(String strKey)`


### `moveRec`
`private EnvironmentSettingCompany_Mdl moveRec(Connection cnTemp)`

- SQL 1: `SELECT ec.ac_RoundOffTDS , ec.ac_RoundOffGST, ec.ac_tcsActiveFrom, ec.isTcsAutoCalculate, ec.isAutoInstrumentClearDate, ec.autoTCSBasedOn, ec.tcsLimit, ec.tcsTurnoverDrVCat, ec.tcsTurnoverCrVCat FROM enviroCompany ec`

### `saveRecord`
`public DmResult<String> saveRecord(EnvironmentSettingCompany_Mdl mrTemp)`

- SQL 1: `SELECT count(*) as cnt FROM enviroCompany`
- SQL 2: `INSERT INTO enviroCompany ( , ac_RoundOffTDS , ac_RoundOffGST , ac_tcsActiveFrom , isTcsAutoCalculate , autoTCSBasedOn , tcsLimit , tcsTurnoverDrVCat , tcsTurnoverCrVCat , isAutoInstrumentClearDate ) VALUES ( , :ac_RoundOffTDS , :ac_RoundOffGST , :ac_tcsActiveFrom , :isTcsAutoCalculate , :autoTCSBasedOn , :tcsLimit , :tcsTurnoverDrVCat , :tcsTurnoverCrVCat , :isAutoInstrumentClearDate )`
- SQL 3: `update enviroCompany set ac_RoundOffTDS = :ac_RoundOffTDS, ac_RoundOffGST = :ac_RoundOffGST, ac_tcsActiveFrom =:ac_tcsActiveFrom, isTcsAutoCalculate =:isTcsAutoCalculate, isAutoInstrumentClearDate =:isAutoInstrumentClearDate, autoTCSBasedOn =:autoTCSBasedOn, tcsTurnoverDrVCat = :tcsTurnoverDrVCat, tcsTurnoverCrVCat = :tcsTurnoverCrVCat, tcsLimit=:tcsLimit`
- Params: `:ac_RoundOffTDS` ← `DmShared.xNull(mrTemp.getTdsRoundOffTag())`; `:ac_RoundOffGST` ← `DmShared.xNull(mrTemp.getGstRoundOffTag())`; `:ac_tcsActiveFrom` ← `DmServer.convertDate(mrTemp.getTcsActiveFrom())`; `:isTcsAutoCalculate` ← `DmShared.xNull(mrTemp.getTcsAutoCalculateTag())`; `:isAutoInstrumentClearDate` ← `DmShared.xNull(mrTemp.getAutoInstrumentClearDateTag())`; `:autoTCSBasedOn` ← `DmShared.xNull(mrTemp.getAutoTCSBasedOnTag())`; `:tcsLimit` ← `DmServer.vNull(mrTemp.getTcsLimit(), "0.00")`; `:ac_RoundOffTDS` ← `DmShared.xNull(mrTemp.getTdsRoundOffTag())`; `:ac_RoundOffGST` ← `DmShared.xNull(mrTemp.getGstRoundOffTag())`; `:ac_tcsActiveFrom` ← `DmServer.convertDate(mrTemp.getTcsActiveFrom())`; `:isTcsAutoCalculate` ← `DmShared.xNull(mrTemp.getTcsAutoCalculateTag())`; `:isAutoInstrumentClearDate` ← `DmShared.xNull(mrTemp.getAutoInstrumentClearDateTag())`; `:tcsTurnoverDrVCat` ← `DmShared.xNull(mrTemp.getTcsTurnoverDrVCatTag())`; `:tcsTurnoverCrVCat` ← `DmShared.xNull(mrTemp.getTcsTurnoverCrVCatTag())`; `:autoTCSBasedOn` ← `DmShared.xNull(mrTemp.getAutoTCSBasedOnTag())`; `:tcsLimit` ← `DmServer.vNull(mrTemp.getTcsLimit(), "0.00")`

### `getTxtHYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getDefaultValue`
`public DmResult<DefaultValue_Mdl> getDefaultValue()`


### `getTxtHRoundOff`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHRoundOff(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHAutoTCSBasedOn`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAutoTCSBasedOn(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `AutoTcsBasedOn.values`, `COLLECTION.getCode`

### `getTxtHVoucherType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVoucherType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vc.category AS code, vt.description FROM ( SELECT vt.category FROM voucher_type vt WHERE vt.isActive = 1 GROUP BY vt.category ) vc LEFT JOIN voucher_Type vt ON vt.category = vc.category AND vt.short_Name = vc.category`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.client`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.libraryMenu.self.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
