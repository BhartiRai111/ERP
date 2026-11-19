# dm-accounts / acInfoAlteration

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/accounts/src/acInfoAlteration/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: subGroup
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Account Name (Text)
- Lookups/suggestions: getTxtHAccountName, getTxtHCity, getTxtHNature
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `AcInfoAlteration`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Nature | DmTextHelp | `txtHNature` | `KeyField_PrpAcs, 4, false` |
| 2 | City | DmTextHelp | `txtHCity` | `TxtHCity_Mdl_PrpAcs, 4, false` |
| 3 | Account Name | DmTextHelp | `txtHAccountName` | `TxtHManfacturer, 4, true` |
| 4 | Display Name | DmTextBox | `txtDisplayName` | `100, false` |
| 5 | Phone No. | DmTextBoxNumeric | `txtPhoneNo` | `2, false, 10, false` |
| 6 | Mobile No. | DmTextBoxNumeric | `txtMobileNo` | `2, false, 10, false` |
| 7 | Fax No. | DmTextBox | `txtFaxNo` | `30, false` |
| 8 | Email | DmTextBox | `txtEmail` | `200, false` |
| 9 | Limit | DmTextBoxNumeric | `txtLimit` | `2, false, 10, false` |
| 10 | Date | DmTextBox | `txtLimitDate` | `11, false` |

**Grid / popup columns** (header, width, db field):
- `txtHNature`: Nature [Text, w100, name]
- `txtHCity`: City [Text, w0, CityName]; State [Text, w100, stateName]; Country [Text, w100, countryName]
- `txtHAccountName`: Account Name [Text, w0, accountName]; Code [Text, w150, ManualCode]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHCity` → `getTxtHCity`
- `txtHAccountName` → `getTxtHAccountName`
- `txtHNature` → `getTxtHNature`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `DisplayName` ← DmShared.xNull(txtDisplayName.getText())
- `PhoneNo` ← DmShared.xNull(txtPhoneNo.getText())
- `MobileNo` ← DmShared.xNull(txtMobileNo.getText())
- `FaxNo` ← DmShared.xNull(txtFaxNo.getText())
- `Email` ← DmShared.xNull(txtEmail.getText())
- `Limit` ← DmShared.xNull(txtLimit.getText())
- `LimitDate` ← DmShared.dNull(txtLimitDate.getText())
- `NatureCode` ← DmShared.xNull(txtHNature.getTag())
- `CityCode` ← DmShared.xNull(txtHCity.getTag())
- `AccountNameCode` ← DmShared.xNull(txtHAccountName.getTag())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtHAccountName.setEnabled(flag)`
- `txtDisplayName.setEnabled(flag)`
- `txtPhoneNo.setEnabled(flag)`
- `txtMobileNo.setEnabled(flag)`
- `txtFaxNo.setEnabled(flag)`
- `txtEmail.setEnabled(flag)`
- `txtLimit.setEnabled(flag)`
- `txtLimitDate.setEnabled(flag)`
- `txtDisplayName.setEnabled(true)`
- `txtPhoneNo.setEnabled(true)`
- `txtMobileNo.setEnabled(true)`
- `txtFaxNo.setEnabled(true)`
- `txtEmail.setEnabled(true)`
- `txtLimit.setEnabled(true)`
- `txtLimitDate.setEnabled(true)`

**`manageDisplay()` logic (condensed code):**
```java
txtHAccountName.setEnabled(flag);
txtDisplayName.setEnabled(flag);
txtPhoneNo.setEnabled(flag);
txtMobileNo.setEnabled(flag);
txtFaxNo.setEnabled(flag);
txtEmail.setEnabled(flag);
txtLimit.setEnabled(flag);
txtLimitDate.setEnabled(flag);
if (isDisplayNameEditable) {
txtDisplayName.setEnabled(true);
if (isContactEditable) {
txtPhoneNo.setEnabled(true);
txtMobileNo.setEnabled(true);
txtFaxNo.setEnabled(true);
if (isEmailEditable) {
txtEmail.setEnabled(true);
if (isCreditLimitEditable) {
txtLimit.setEnabled(true);
txtLimitDate.setEnabled(true);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHCity.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHCity(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHCity_
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHCity).show();
public void onSuccess(DmResultPageing<List<TxtHCity_Mdl>> result) {
try {
if (result.isStatus()) {
txtHCity.setData(result.getData(), result.getPaging());
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), txtHCity).show();
```

`txtHCity.addTextHelpCallBack` → `onSuccess()`
```java
try {
if (result.isStatus()) {
txtHCity.setData(result.getData(), result.getPaging());
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), txtHCity).show();
```

`txtHAccountName.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHAccountName(txtHNature.getTag(), txtHCity.getTag(), helper, blnToEndOfFile, n
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHAccountName).show();
public void onSuccess(DmResultPageing<List<TxtHAccountName_Mdl>> result) {
if (result.isStatus()) {
txtHAccountName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAccountName).show();
```

`txtHAccountName.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAccountName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAccountName).show();
```

`txtHAccountName.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHAccountName.getTag2nd()).equalsIgnoreCase(DmShared.xNull(txtHAccountName.getTag()))) {
resetAccountInfo();
txtHAccountName.setTag2nd(DmShared.xNull(txtHAccountName.getTag()));
if (blnStatus && t1Row != null) {
txtDisplayName.setText(t1Row.getDisplayName());
txtPhoneNo.setText(t1Row.getPhoneNo());
txtMobileNo.setText(t1Row.getMobileNo());
txtFaxNo.setText(t1Row.getFaxNo());
txtEmail.setText(t1Row.getEmail());
txtLimit.setText(t1Row.getLimit());
...
```

`txtHNature.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHNature(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFiel
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHNature).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHNature).show();
```

`txtHNature.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHNature).show();
```

`txtHNature.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHNature.getTag2nd()).equalsIgnoreCase(DmShared.xNull(txtHNature.getTag()))) {
resetAccountInfo();
txtHNature.setTag2nd(DmShared.xNull(txtHNature.getTag()));
txtHAccountName.setEnabled(true);
```

_Print permission "P" is removed for this screen (replace "P" with "*")._
- Confirm: "Are you sure? You want to close."
- Alert: "Record saved successfully"

## Files
| file | lines |
|---|---|
| client/AcInfoAlteration.java | 398 |
| client/AcInfoAlteration_Dsg.java | 413 |
| server/AcInfoAlteration_SrvImpl.java | 276 |
| shared/AcInfoAlteration_Mdl.java | 141 |
| shared/AcInfoAlteration_Srv.java | 18 |
| shared/AcInfoAlteration_SrvAsync.java | 18 |
| shared/AcInfoAlteration_Vld.java | 13 |
| shared/Find_Mdl.java | 23 |
| shared/TxtHAccountName_Mdl.java | 86 |
| shared/TxtHCity_Mdl.java | 59 |

## UI labels / columns (client)
`Nature`, `City`, `Account Name`, `Account Info Alteration`, `Display Name`, `Phone No.`, `Mobile No.`, `Fax No.`, `Email`, `Limit`, `Date`

## Models (shared DTOs)
### TxtHAccountName_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | ManualCode |
| String | displayName |
| String | phoneNo |
| String | mobileNo |
| String | faxNo |
| String | email |
| String | limit |
| String | limitDate |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | shortName |

### AcInfoAlteration_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | natureCode |
| String | cityCode |
| String | accountNameCode |
| String | displayName |
| String | phoneNo |
| String | mobileNo |
| String | faxNo |
| String | email |
| String | limit |
| String | limitDate |
| boolean | isDisplayNameEditable |
| boolean | isContactEditable |
| boolean | isEmailEditable |
| boolean | isCreditLimitEditable |

### TxtHCity_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | stateCode |
| String | stateName |
| String | stateNumericCode |
| String | countryCode |
| String | countryName |

## RPC interface
| method | params | returns |
|---|---|---|
| saveRecord | AcInfoAlteration_Mdl mrTemp | DmResult<String> |
| getTxtHAccountName | String nature, String cityCode, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHAccountName_Mdl>> |
| getTxtHCity | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHCity_Mdl>> |
| getTxtHNature | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `AcInfoAlteration_Vld`
```java
public class AcInfoAlteration_Vld {
    public static void chkSave(AcInfoAlteration_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getAccountNameCode(), "Account Name", 100);
        DmShared.isValidMobile(mrTemp.getMobileNo(), 200);
        DmShared.isValidEMail(mrTemp.getEmail(), 300);
    }
}
```

## Server `AcInfoAlteration_SrvImpl.java` (276 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnUserName`, `DmShared.dNull`, `DmShared.xNull`

### `saveRecord`
`public DmResult<String> saveRecord(AcInfoAlteration_Mdl mrTemp)`

- SQL 1: `update subGroup set {…} u_EntDt = :u_EntDt , u_Name = :userName WHERE subCode = :subCode`
- Params: `:subCode` ← `DmShared.xNull(mrTemp.getAccountNameCode())`; `:displayName` ← `DmShared.xNull(mrTemp.getDisplayName())`; `:phone` ← `DmShared.xNull(mrTemp.getPhoneNo())`; `:mobile` ← `DmShared.xNull(mrTemp.getMobileNo())`; `:fax` ← `DmShared.xNull(mrTemp.getFaxNo())`; `:eMail` ← `DmShared.xNull(mrTemp.getEmail())`; `:creditLimit_Temp` ← `DmShared.xNull(mrTemp.getLimit())`; `:creditLimit_Temp_Date` ← `DmServer.convertDate(mrTemp.getLimitDate())`; `:u_EntDt` ← `DmServer.getServerTimestamp()`; `:userName` ← `DmServer.getSsnUserName(this)`
- Other calls: `AcInfoAlteration_Vld.chkSave`

### `getTxtHAccountName`
`public DmResultPageing<List<TxtHAccountName_Mdl>> getTxtHAccountName(String nature, String cityCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select sg.subCode, sg.name as accountName, sg.manualCode, sg.displayName, sg.phone, sg.mobile, sg.fax, sg.eMail, sg.creditLimit_Temp, sg.creditLimit_Temp_Date from subGroup sg where sg.nature = :nature AND sg.cityCode = :cityCode`
- Params: `:nature` ← `DmShared.xNull(nature)`; `:cityCode` ← `DmShared.xNull(cityCode)`

### `getTxtHCity`
`public DmResultPageing<List<TxtHCity_Mdl>> getTxtHCity(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select cityCode,cityName,stateCode,sm.name as stateName,sm.numericCode, countryCode,ctr.name as countryName from city ct left join stateMast sm on ct.stateCode = sm.code left join country ctr on ct.countryCode = ctr.code`

### `getTxtHNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHNature(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT code,name FROM subgroupNature`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.libraryMenu.self.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
