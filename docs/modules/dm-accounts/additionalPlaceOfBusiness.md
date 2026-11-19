# dm-accounts / additionalPlaceOfBusiness

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/accounts/src/additionalPlaceOfBusiness/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: additionalPlaceOfBusiness
- Update tables: additionalPlaceOfBusiness
- Delete cascade (in order): additionalPlaceOfBusiness
- Duplicate checks on save:
  - `SELECT count(*) AS CNT FROM additionalPlaceOfBusiness apb WHERE apb.place = :place AND CONCAT(CAST(apb.subcode AS VARCHAR(25)),'-',CAST(apb.sNo AS varchar(15))) <> :code`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Link With A/c (Text); Place (Text); Address (Text); Pin (Text); rule: Default is mandatory!
- Lookups/suggestions: getTxtHYesNo, getTxtHCity, getTxtHLinkWithAccount, getSuggestion
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `AdditionalPlaceOfBusiness`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Link With A/c | DmTextHelp | `txtHAccount` | `txtHLinkWithAccount_PrpAcs, 7, true` |
| 2 | Place | DmSuggestBox | `txtPlace` | `50, 10, true, "place", DmEnum.DmDataType.STRING` |
| 3 | Address | DmTextBox | `txtAddress1` | `50, false` |
| 4 | - | DmTextBox | `txtAddress2` | `50, false` |
| 5 | City | DmTextHelp | `txtHCity` | `TxtHCity_Mdl_PrpAcs, 4, false` |
| 6 | State | DmTextBox | `txtState` | `100, false` |
| 7 | Country | DmTextBox | `txtCountry` | `100, false` |
| 8 | Mobile No. | DmTextBoxMobile | `txtMobile` | `false` |
| 9 | Email | DmTextBox | `txtEmail` | `250, false` |
| 10 | PIN | DmTextBoxNumeric | `txtPinNo` | `0, false, 6, false` |
| 11 | Default | DmTextHelp | `txtHDefault` | `KeyField_PrpAcs, 4, true, true` |

Other widgets (not in tab list): `txtPanNo` (DmTextBox), `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `txtHDefault`: Type [Text, w0]
- `txtHAccount`: Name [Text, w0, NAME]; Manual Code [Text, w150, manualCode]; City [Text, w150, City]
- `txtHCity`: City [Text, w0, CityName]; State [Text, w100, stateName]; Country [Text, w100, countryName]
- `dmFind`: Place [Text, w130, place]; Link With A/c [Text, w200, accountName]; Address 1 [Text, w175, add1]; Address 2 [Text, w175, add2]; City [Text, w150, cityCode]; Mobile [Text, w100, mobile]; Pin [Text, w80, pin]; Email [Text, w150, eMail]; Default [Text, w80, isDefault]

**Lookup / suggestion / find sources (server RPC called):**
- `txtPlace` → `getSuggestion`
- `txtHDefault` → `getTxtHYesNo`
- `txtHAccount` → `getTxtHLinkWithAccount`
- `txtHCity` → `getTxtHCity`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `sNo` ← txtHAccount.getTag2nd()
- `AccountTag` ← DmShared.xNull(txtHAccount.getTag())
- `AccountText` ← DmShared.xNull(txtHAccount.getText())
- `SharedKeyFieldName` ← DmShared.xNull(txtPlace.getText())
- `DefaultTag` ← DmShared.xNull(txtHDefault.getTag())
- `DefaultText` ← DmShared.xNull(txtHDefault.getText())
- `Address1` ← DmShared.xNull(txtAddress1.getText())
- `Address2` ← DmShared.xNull(txtAddress2.getText())
- `CityCode` ← DmShared.xNull(txtHCity.getTag())
- `CityName` ← DmShared.xNull(txtHCity.getText())
- `StateName` ← DmShared.xNull(txtState.getText())
- `CountryName` ← DmShared.xNull(txtCountry.getText())
- `Pin` ← DmShared.xNull(txtPinNo.getText())
- `Email` ← DmShared.xNull(txtEmail.getText())
- `Mobile` ← DmShared.xNull(txtMobile.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtPlace.setText` ← data.getSharedKeyFieldName()
- `txtHAccount.setTag` ← data.getAccountTag()
- `txtHAccount.setText` ← data.getAccountText()
- `txtHAccount.setTag2nd` ← data.getsNo()
- `txtAddress1.setText` ← data.getAddress1()
- `txtAddress2.setText` ← data.getAddress2()
- `txtHCity.setTag` ← data.getCityCode()
- `txtHCity.setTag2nd` ← data.getCityCode()
- `txtHCity.setText` ← data.getCityName()
- `txtState.setText` ← data.getStateName()
- `txtCountry.setText` ← data.getCountryName()
- `txtPinNo.setText` ← data.getPin()
- `txtMobile.setText` ← data.getMobile()
- `txtEmail.setText` ← data.getEmail()
- `txtHDefault.setTag` ← data.getDefaultTag()
- `txtHDefault.setText` ← data.getDefaultText()

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtState.setEnabled(false)`
- `txtCountry.setEnabled(false)`

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
txtState.setEnabled(false);
txtCountry.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
onAdd();
```

`topControl.addEditHandeller` → `editClicked()`
```java
if (!DmShared.xNull(topControl.getDocId()).equalsIgnoreCase("")) {
txtAddress1.setFocus(true);
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
if (DmShared.xNull(topControl.getMode()).equalsIgnoreCase("Edit")) {
showModifyReason();
} else {
topControlSave();
```

`topControl.addOtherHandeller` → `otherClicked()`
```java
throw new UnsupportedOperationException("Not supported yet.");
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(AdditionalPlaceOfBusiness.this);
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

`txtPlace.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getSuggestion(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<String>
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtPlace).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtPlace.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtPlace).show();
```

`txtPlace.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtPlace.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtPlace).show();
```

`txtHDefault.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyField
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
if (!DmShared.xNull(txtHAccount.getTag()).equalsIgnoreCase(DmShared.xNull(txtHAccount.getTag2nd()))) {
resetAc();
txtHAccount.setTag2nd(txtHAccount.getTag());
if (blnStatus && tRow != null) {
stateNumericCode = DmShared.xNull(tRow.getNumericCode());
gstin = DmShared.xNull(tRow.getGstIn());
```

`txtHCity.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHCity(gstin, stateNumericCode, DmShared.xNull(txtHAccount.getTag()), "", helpe
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

`txtHCity.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHCity.getTag()).equalsIgnoreCase(DmShared.xNull(txtHCity.getTag2nd()))) {
resetCity();
txtHCity.setTag2nd(txtHCity.getTag());
if (blnStatus && tRow != null) {
txtState.setText(DmShared.xNull(tRow.getStateName()));
txtState.setTag(tRow.getStateNumericCode());
txtCountry.setText(DmShared.xNull(tRow.getCountryName()));
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

**Top control options:** setOtherRequired(false), setRefreshRequired(false), setDisplay(true)

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."

## Files
| file | lines |
|---|---|
| client/AdditionalPlaceOfBusiness.java | 612 |
| client/AdditionalPlaceOfBusiness_Dsg.java | 365 |
| server/AdditionalPlaceOfBusiness_SrvImpl.java | 762 |
| shared/AdditionalPlaceOfBusiness_Mdl.java | 169 |
| shared/AdditionalPlaceOfBusiness_Srv.java | 31 |
| shared/AdditionalPlaceOfBusiness_SrvAsync.java | 32 |
| shared/AdditionalPlaceOfBusiness_Vld.java | 22 |
| shared/Find_Mdl.java | 96 |
| shared/TxtHCity_Mdl.java | 51 |
| shared/TxtHLinkWithAccount_Mdl.java | 49 |

## UI labels / columns (client)
`Link With A/c`, `Place`, `Address`, `City`, `State`, `Country`, `Mobile No.`, `Email`, `PIN`, `Default`

## Models (shared DTOs)
### AdditionalPlaceOfBusiness_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | sNo |
| String | accountTag |
| String | accountText |
| String | address1 |
| String | address2 |
| String | defaultText |
| String | defaultTag |
| String | cityCode |
| String | cityName |
| String | stateName |
| String | countryName |
| String | pin |
| String | email |
| String | mobile |
| String | gstin |
| String | numericCode |
| ModifyReason_Mdl | modifyReasonMdl |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | sNo |
| String | place |
| String | address1 |
| String | address2 |
| String | city |
| String | mobile |
| String | pin |
| String | email |
| String | defaultText |

### TxtHLinkWithAccount_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | city |
| String | gstIn |
| String | numericCode |

### TxtHCity_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | stateCode |
| String | stateName |
| String | stateNumericCode |
| String | countryName |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| saveRecord | AdditionalPlaceOfBusiness_Mdl mrTemp | DmResult<String> |
| getSuggestion | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getMoveRec | String strKey | DmResult<AdditionalPlaceOfBusiness_Mdl> |
| getFind | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| deleteRecord | String strKey, ModifyReason_Mdl modifyReasonMdl | DmResult<String> |
| getTxtHYesNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHLinkWithAccount | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHLinkWithAccount_Mdl>> |
| getTxtHCity | String gstin,String stateNumericCode,String subCode,String siteCode,DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHCity_Mdl>> |

## Validator `AdditionalPlaceOfBusiness_Vld`
```java
public class AdditionalPlaceOfBusiness_Vld {
    public static void chkSave(AdditionalPlaceOfBusiness_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getAccountTag(), "Link With A/c", 200);
        DmShared.isValidText(mrTemp.getSharedKeyFieldName(), "Place", 100);
        DmShared.isValidText(mrTemp.getAddress1(), "Address", 700);
        DmShared.isValidText(mrTemp.getPin(), "Pin", 300);
        DmShared.isValidMobile(mrTemp.getMobile(), 400);
        DmShared.isValidEMail(mrTemp.getEmail(), 500);
        if (DmShared.xNull(mrTemp.getDefaultTag()).trim().equalsIgnoreCase("")) {
            throw new DmException(600, "Default is mandatory!");
        }
    }
}
```

## Server `AdditionalPlaceOfBusiness_SrvImpl.java` (762 lines)
Helpers used: `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnUserName`, `DmServer.setSsnOldData`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.vNull`, `DmShared.xNull`

### `getTxtHYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHCity`
`public DmResultPageing<List<TxtHCity_Mdl>> getTxtHCity(String gstin,String stateNumericCode,String subCode,String siteCode,DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ct.cityCode,ct.cityName,ct.stateCode,sm.name as stateName,sm.numericCode, ctr.name as countryName FROM city ct LEFT JOIN stateMast sm on ct.stateCode = sm.code LEFT JOIN country ctr on ct.countryCode = ctr.code WHERE 1=1 {…}`
- Params: `:stateNumericCode` ← `tempStateNumericCode`

### `saveRecord`
`public DmResult<String> saveRecord(AdditionalPlaceOfBusiness_Mdl mrTemp)`

- SQL 1: `SELECT count(*) AS CNT FROM additionalPlaceOfBusiness apb WHERE apb.place = :place AND CONCAT(CAST(apb.subcode AS VARCHAR(25)),'-',CAST(apb.sNo AS varchar(15))) <> :code`
- SQL 2: `SELECT ISNULL(MAX(sNo),0) + 1 AS Mx FROM additionalPlaceOfBusiness WHERE subCode=:subCode`
- SQL 3: `INSERT INTO additionalPlaceOfBusiness (place ,subCode, sNo, add1, add2, cityCode, pin,mobile,isDefault,eMail,preparedBy,preparedByIP,preparedDt) VALUES (:place ,:subCode, :sNo, :add1, :add2, :cityCode, :pin,:mobile,:isDefault,:eMail,:preparedBy,:preparedByIP,:preparedDt)`
- SQL 4: `Update additionalPlaceOfBusiness Set place = :place, add1 = :add1, add2 = :add2, cityCode = :cityCode, pin = :pin, eMail = :eMail, mobile = :mobile, isDefault = :isDefault, u_Name = :u_name, u_EntDt = :u_EntDt WHERE concat(cast(subcode as varchar(25)),'-',cast(sNo as varchar(15)))=:code`
- SQL 5: `UPDATE additionalPlaceOfBusiness SET isDefault = 0 WHERE CONCAT(CAST(subcode AS VARCHAR(25)),'-',CAST(sNo AS varchar(15))) <> :code`
- Params: `:place` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:subCode` ← `DmShared.iNull(mrTemp.getAccountTag()).toString()`; `:subCode` ← `DmShared.iNull(mrTemp.getAccountTag()).toString()`; `:sNo` ← `mrTemp.getsNo()`; `:place` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:add1` ← `DmShared.xNull(mrTemp.getAddress1())`; `:add2` ← `DmShared.xNull(mrTemp.getAddress2())`; `:cityCode` ← `DmShared.foriegnKey(mrTemp.getCityCode())`; `:pin` ← `DmShared.xNull(mrTemp.getPin())`; `:eMail` ← `DmShared.xNull(mrTemp.getEmail())`; `:mobile` ← `DmShared.xNull(mrTemp.getMobile())`; `:preparedBY` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `timestamp`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:isDefault` ← `DmShared.iNull(mrTemp.getDefaultTag()).intValue()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:place` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:add1` ← `DmShared.xNull(mrTemp.getAddress1())`; `:add2` ← `DmShared.xNull(mrTemp.getAddress2())`; `:cityCode` ← `DmShared.foriegnKey(mrTemp.getCityCode())`; `:pin` ← `DmShared.xNull(mrTemp.getPin())`; `:eMail` ← `DmShared.xNull(mrTemp.getEmail())`; `:isDefault` ← `DmShared.iNull(mrTemp.getDefaultTag()).intValue()`; `:mobile` ← `DmShared.xNull(mrTemp.getMobile())`; `:u_name` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `timestamp`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`
- Errors: "Record already exist!" | "Invalid key."
- Other calls: `AdditionalPlaceOfBusiness_Vld.chkSave`

### `getTxtHLinkWithAccount`
`public DmResultPageing<List<TxtHLinkWithAccount_Mdl>> getTxtHLinkWithAccount(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT SG.SubCode, SG.Name, isNull(CT.CityName, '') AS City, SG.manualCode,SG.gsTin, SM.numericCode FROM SubGroup SG LEFT JOIN City CT ON CT.CityCode = SG.CityCode LEFT JOIN stateMast SM ON SM.code = CT.stateCode WHERE SG.isActive = 1`

### `getMoveRec`
`public DmResult<AdditionalPlaceOfBusiness_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT apb.subCode,sg.name as accountName,apb.sNo,apb.place,apb.add1,apb.add2,apb.cityCode,apb.pin,apb.mobile , apb.eMail,apb.isDefault,apb.preparedby,apb.preparedDt,apb.preparedByIP,apb.u_EntDt,apb.u_name , ct.cityCode as cityCode,ct.cityName as cityName, sm.name as stateName, ctr.name as countryName , sg.gsTin, sm.numericCode FROM additionalPlaceOfBusiness as apb left join subgroup as sg on sg.subcode = apb.subCode left join city ct on apb.cityCode = ct.cityCode left join stateMast sm on ct.stateCode = sm.code left join country ctr on ct.countryCode = ctr.code WHERE concat(cast(apb.subcode as varchar(25)),'-',cast(apb.sNo as varchar(15)))=:code`
- Params: `:code` ← `DmShared.xNull(strKey)`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `select concat(cast(apb.subcode as varchar(25)),'-',cast(apb.sNo as varchar(15))) as subcode from additionalPlaceOfBusiness apb left join subgroup as sg on sg.subcode = apb.subcode order by sg.name,apb.sNo`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl modifyReasonMdl)`

- SQL 1: `DELETE FROM additionalPlaceOfBusiness WHERE concat(cast(subcode as varchar(25)),'-',cast(sNo as varchar(15))) = :code`
- Params: `:code` ← `DmShared.xNull(strKey)`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT apb.subCode,sg.name as accountName,apb.sNo,apb.place,apb.add1,apb.add2, ct.cityName, apb.pin,apb.mobile, apb.eMail,apb.isDefault FROM additionalPlaceOfBusiness as apb left join subgroup as sg on apb.subCode=sg.subcode left join city ct on ct.cityCode=apb.cityCode`

### `getSuggestion`
`public DmResultPageing<List<String>> getSuggestion(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT place FROM additionalPlaceOfBusiness`

## Depends on (imports from other packages)
`dataman.accounts.additionalPlaceOfBusiness.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxMobile.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `dataman.libraryMenu.self.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Record already exist!" |
| saveRecord | `if (DmShared.xNull(mrTemp.getsNo()).equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (DmShared.xNull(mrTemp.getSharedKeyFieldCode()).equalsIgnoreCase(""))` | "Invalid key." |

## Notes (hand-written)
_No hand-written notes yet._
