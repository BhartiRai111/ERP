# dm-accounts / addressBook

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/accounts/src/addressBook/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: AddressBook
- Update tables: AddressBook
- Delete cascade (in order): Addressbook
- Duplicate checks on save:
  - `select count(*) as cnt from addressBook where subCode = :subCode and isDefault=1 and Code <> :Code`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Account/Group Name (Text); Nature (Text); Contact Person (Text); Default (Text)
- Lookups/suggestions: getTxtHCity, getTxtHCompanyGroupName, getTxtHDesignation, getTxtHLinkWithAccount, getTxtHNature, getTxtHZone, getTxtHContactPrefix
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `AddressBook`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Entry No. | DmTextBox | `txtEntryNo` | `` |
| 2 | Company Group Name | DmTextHelp | `txtHGroup` | `KeyField_PrpAcs, 7, true` |
| 3 | Nature | DmTextHelp | `txtHNature` | `KeyField_PrpAcs, 7, true` |
| 4 | Designation | DmTextHelp | `txtHDesignation` | `KeyField_PrpAcs, 7` |
| 5 | Contact Person | DmTextHelp | `txtHContactPrefix` | `com.google.gwt.core.shared.KeyField_PrpAcs, 7, false` |
| 6 | - | DmTextBox | `txtContactPerson` | `35, true` |
| 7 | Link With A/c | DmTextHelp | `txtHAccount` | `txtHLinkWithAccount_PrpAcs, 7` |
| 8 | Address | DmTextBox | `txtAddress` | `100, false` |
| 9 | - | DmTextBox | `txtAddress1` | `100, false` |
| 10 | City | DmTextHelp | `txtHCity` | `txtHCity_PrpAcs, 7` |
| 11 | State | DmTextBox | `txtState` | `100, false` |
| 12 | Zone | DmTextHelp | `txtHZone` | `KeyField_PrpAcs, 7` |
| 13 | PIN | DmTextBoxNumeric | `txtPinCode` | `DmTextBoxNumeric.DecimalNature.ROUNDOFF, false, 6, false` |
| 14 | Phone No. | DmTextBox | `txtPhone` | `35, false` |
| 15 | Mobile No. | DmTextBox | `txtMobile` | `35, false` |
| 16 | Fax No. | DmTextBox | `txtFaxNo` | `35, false` |
| 17 | EMail | DmTextBox | `txtEmail` | `40, false` |
| 18 | Remark | DmTextArea | `txtRemark` | `250, false` |

Other widgets (not in tab list): `dmFind` (DmFind), `txtHDefault` (DmTextHelp)

**Grid / popup columns** (header, width, db field):
- `txtHContactPrefix`: Name [Text, w0, NAME]
- `txtHNature`: Nature [Text, w0, NAME]
- `txtHGroup`: Name [Text, w0, NAME]
- `txtHDesignation`: Name [Text, w0, NAME]
- `txtHAccount`: Name [Text, w0, NAME]; Manual Code [Text, w150, ManualCode]; City [Text, w150, City]
- `txtHCity`: City [Text, w0, CityName]; State [Text, w125, stateName]; Country [Text, w100, countryName]
- `txtHZone`: Name [Text, w0, Name]
- `dmFind`: Entry No. [Text, w100, SearchCode]; Company/Group Name [Text, w200, GroupName]; Link With A/c [Text, w200, AcName]; Designation [Text, w150, Designation]; Contact Person [Text, w150, ContactPerson]; Nature [Text, w150, Nature]; City [Text, w100, CityName]; Country [Text, w100, CountryName]; Zone [Text, w100, ZoneName]; PIN [Text, w100, Pin]; Phone No. [Text, w100, Phone]; Mobile No. [Text, w100, Mobile]; E-Mail [Text, w150, EMail]; FAX No. [Text, w150, Fax]
- `txtHDefault`: Type [Text, w0]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHGroup` → `getTxtHCompanyGroupName`
- `txtHDesignation` → `getTxtHDesignation`
- `txtHAccount` → `getTxtHLinkWithAccount`
- `txtHNature` → `getTxtHNature`
- `txtHCity` → `getTxtHCity`
- `txtHZone` → `getTxtHZone`
- `txtHDefault` → `getYesNo`
- `txtHContactPrefix` → `getTxtHContactPrefix`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtEntryNo.getText())
- `Address` ← DmShared.xNull(txtAddress.getText())
- `Address1` ← DmShared.xNull(txtAddress1.getText())
- `CityCode` ← DmShared.xNull(txtHCity.getTag())
- `CityName` ← DmShared.xNull(txtHCity.getText())
- `PinCode` ← DmShared.xNull(txtPinCode.getText())
- `StateName` ← DmShared.xNull(txtState.getText())
- `CompanyGroup` ← DmShared.xNull(txtHGroup.getTag())
- `ContactPerson` ← DmShared.xNull(txtContactPerson.getText())
- `Designation` ← DmShared.xNull(txtHDesignation.getTag())
- `Email` ← DmShared.xNull(txtEmail.getText())
- `FaxNo` ← DmShared.xNull(txtFaxNo.getText())
- `LinkAccountTag` ← DmShared.xNull(txtHAccount.getTag())
- `LinkAccountText` ← DmShared.xNull(txtHAccount.getText())
- `MobileNo` ← DmShared.xNull(txtMobile.getText())
- `Nature` ← DmShared.xNull(txtHNature.getTag())
- `PhoneNo` ← DmShared.xNull(txtPhone.getText())
- `Remark` ← DmShared.xNull(txtRemark.getText())
- `ZoneCode` ← DmShared.xNull(txtHZone.getTag())
- `ZoneName` ← DmShared.xNull(txtHZone.getText())
- `IsDefaultTag` ← DmShared.xNull(txtHDefault.getTag())
- `IsDefault` ← DmShared.xNull(txtHDefault.getText())
- `InitialText` ← DmShared.xNull(txtHContactPrefix.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtEntryNo.setText` ← data.getSharedKeyFieldName()
- `txtContactPerson.setText` ← data.getContactPerson()
- `txtAddress.setText` ← data.getAddress()
- `txtAddress1.setText` ← data.getAddress1()
- `txtPinCode.setText` ← data.getPinCode()
- `txtPhone.setText` ← data.getPhoneNo()
- `txtMobile.setText` ← data.getMobileNo()
- `txtFaxNo.setText` ← data.getFaxNo()
- `txtEmail.setText` ← data.getEmail()
- `txtRemark.setText` ← data.getRemark()
- `txtHCity.setTag` ← data.getCityCode()
- `txtHCity.setText` ← data.getCityName()
- `txtHGroup.setTag` ← data.getCompanyGroup()
- `txtHGroup.setText` ← data.getCompanyGroup()
- `txtState.setText` ← data.getStateName()
- `txtHDesignation.setTag` ← data.getDesignation()
- `txtHDesignation.setText` ← data.getDesignation()
- `txtHAccount.setTag` ← data.getLinkAccountTag()
- `txtHAccount.setText` ← data.getLinkAccountText()
- `txtHNature.setTag` ← data.getNature()
- `txtHNature.setText` ← data.getNature()
- `txtHZone.setTag` ← data.getZoneCode()
- `txtHZone.setText` ← data.getZoneName()
- `txtHDefault.setTag` ← data.getIsDefaultTag()
- `txtHDefault.setText` ← data.getIsDefault()
- `txtHContactPrefix.setText` ← data.getInitialText()
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtEntryNo.setEnabled(false)`
- `txtState.setEnabled(false)`
- `txtHAccount.setEnabled(blnFlag)`
- `txtHDefault.setEnabled(blnFlag)`
- `txtHAccount.setEnabled(false)`
- `txtHDefault.setEnabled(false)`

**On New click:**
- `clear()`
- `setDefaultValues()`

**`manageDisplay()` logic (condensed code):**
```java
txtEntryNo.setEnabled(false);
txtState.setEnabled(false);
txtHAccount.setEnabled(blnFlag);
txtHDefault.setEnabled(blnFlag);
if (isSeperateMaster) {
txtHAccount.setEnabled(false);
txtHDefault.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
clear();
manageDisplay(true);
setDefaultValues();
```

`topControl.addEditHandeller` → `editClicked()`
```java
if (!DmShared.xNull(topControl.getDocId()).equalsIgnoreCase("")) {
manageDisplay(true);
txtHGroup.setFocus(true);
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(AddressBook.this);
```

`modifyReason.addCallBack` → `onOk()`
```java
try {
modifyReasonMdl = modifyReason;
if (topControl.getMode().equalsIgnoreCase("Browse") && DmShared.iNull(topControl.getDocId()).longValue() > 0) {
topControlDelete(topControl.getDocId());
} else {
topControlSave();
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), (DmTextBox) null).show();
```

`txtHGroup.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHCompanyGroupName(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<Li
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGroup).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHGroup.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGroup).show();
```

`txtHGroup.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHGroup.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGroup).show();
```

`txtHDesignation.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHDesignation(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDesignation).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHDesignation.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDesignation).show();
```

`txtHDesignation.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHDesignation.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDesignation).show();
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

`txtHCity.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHCity(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHCity_
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHCity).show();
public void onSuccess(DmResultPageing<List<TxtHCity_Mdl>> result) {
if (result.isStatus()) {
txtHCity.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCity).show();
```

`txtHCity.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHCity.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCity).show();
```

`txtHCity.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && tRow != null) {
String St_Coun = DmShared.xNull(tRow.getStateName()) + " " + "-" + " " + DmShared.xNull(tRow.getCountryName());
txtState.setText(St_Coun);
txtState.setEnabled(false);
} else {
txtState.setText(DmShared.xNull(""));
```

`txtHZone.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHZone(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHZone).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
try {
if (result.isStatus()) {
txtHZone.setData(result.getData(), result.getPaging());
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), txtHZone).show();
```

`txtHZone.addTextHelpCallBack` → `onSuccess()`
```java
try {
if (result.isStatus()) {
txtHZone.setData(result.getData(), result.getPaging());
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), txtHZone).show();
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

`inputBox.addCallBack` → `onOk()`
```java
if (parentName.equalsIgnoreCase("NatureBox")) {
txtHNature.setTag(inputValue);
txtHNature.setText(inputValue);
txtHNature.setFocus(true);
} else if (parentName.equalsIgnoreCase("GroupBox")) {
txtHGroup.setTag(inputValue);
txtHGroup.setText(inputValue);
txtHGroup.setFocus(true);
} else if (parentName.equalsIgnoreCase("DesignationBox")) {
txtHDesignation.setTag(inputValue);
...
```

`txtHContactPrefix.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHContactPrefix(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHContactPrefix).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHContactPrefix.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHContactPrefix).show();
```

`txtHContactPrefix.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHContactPrefix.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHContactPrefix).show();
```

`dmFind.addHelpGridCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getFind(filterAcCode, helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List
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

**Top control options:** setOtherRequired(false), setRefreshRequired(false), setDisplay(true)

_Print permission "P" is removed for this screen (replace "P" with "*")._

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."

## Files
| file | lines |
|---|---|
| client/AddressBook.java | 903 |
| client/AddressBook_Dsg.java | 579 |
| server/AddressBook_SrvImpl.java | 981 |
| shared/AddressBook_Mdl.java | 231 |
| shared/AddressBook_Srv.java | 36 |
| shared/AddressBook_SrvAsync.java | 38 |
| shared/AddressBook_Vld.java | 16 |
| shared/DefaultValue_Mdl.java | 65 |
| shared/Find_Mdl.java | 161 |
| shared/GrdContactPerson_Mdl.java | 50 |
| shared/TxtHCity_Mdl.java | 50 |
| shared/TxtHLinkWithAccount_Mdl.java | 41 |

## UI labels / columns (client)
`Entry No.`, `Company Group Name`, `Nature`, `Designation`, `Contact Person`, `Link With A/c`, `Address`, `City`, `State`, `Zone`, `PIN`, `Phone No.`, `Mobile No.`, `Fax No.`, `EMail`, `Remark`, `Default Contact`

## Models (shared DTOs)
### GrdContactPerson_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | mobileNo |
| String | landlineNo |
| String | designation |
| String | isChecked |

### AddressBook_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | CompanyGroup |
| String | Nature |
| String | Designation |
| String | ContactPerson |
| String | LinkAccountText |
| String | LinkAccountTag |
| String | Address |
| String | Address1 |
| String | CityName |
| String | CityCode |
| String | StateName |
| String | ZoneName |
| String | ZoneCode |
| String | PinCode |
| String | PhoneNo |
| String | MobileNo |
| String | FaxNo |
| String | Email |
| String | Remark |
| String | isDefault |
| String | isDefaultTag |
| String | initialTag |
| String | initialText |
| ModifyReason_Mdl | modifyReasonMdl |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | acCode |
| String | acName |
| String | companyGroup |
| String | nature |
| String | designation |
| boolean | continuousNewMode |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | CompanyGroup |
| String | Nature |
| String | Designation |
| String | ContactPerson |
| String | LinkAccount |
| String | Address |
| String | City |
| String | State |
| String | Country |
| String | Zone |
| String | PinCode |
| String | PhoneNo |
| String | MobileNo |
| String | FaxNo |
| String | Email |

### TxtHLinkWithAccount_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | cityName |
| String | manualCode |
| String | cityCode |

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
| getNavigator | String farmerMastAc | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<AddressBook_Mdl> |
| saveRecord | AddressBook_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl modifyReasonMdl | DmResult<String> |
| getTxtHCity | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHCity_Mdl>> |
| getTxtHCompanyGroupName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDesignation | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHLinkWithAccount | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHLinkWithAccount_Mdl>> |
| getTxtHNature | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHZone | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getFind | String farmerMastAc ,DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getYesNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHContactPrefix | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `AddressBook_Vld`
```java
public class AddressBook_Vld {
    public static void chkSave(AddressBook_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getCompanyGroup(), "Account/Group Name", 100);
        DmShared.isValidText(mrTemp.getNature(), "Nature", 200);
        DmShared.isValidText(mrTemp.getContactPerson(), "Contact Person", 300);
        DmShared.isValidEMail(mrTemp.getEmail(), 400);
        DmShared.isValidText(mrTemp.getIsDefaultTag(), "Default", 350);
    }
}
```

## Server `AddressBook_SrvImpl.java` (981 lines)
Helpers used: `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.setSsnOldData`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.vNull`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(String filterAcCode)`

- SQL 1: `SELECT AB.Code AS SearchCode,AB.Code,AB.SubCode,SG.Name FROM AddressBook AB LEFT JOIN SubGroup SG ON SG.SubCode=AB.SubCode {…} ORDER BY NAME,CODE`
- Params: `:one` ← `1`; `:filterAcCode` ← `filterAcCode`

### `getMoveRec`
`public DmResult<AddressBook_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT ab.Subcode AS ACCode, sg.Name AS AcName, ct.CityName, ct.CityCode, zm.name AS ZoneName, ab.Zone, ab.Fax, ab.Phone, ab.Add1, ab.Add2, ab.Mobile, ab.EMail, ab.ContactPerson, ab.Pin, ab.PreparedBy, ab.preparedByIP, ab.preparedDt, ab.U_Name AS ModifiedBy, ab.u_EntDt, ab.Code AS SearchCode, ab.Remarks, ab.GroupName, ab.Nature, ab.Designation,ab.isDefault,ab.initial,ct.stateCode,sm.name as stateName, ct.CountryCode,Ctry.Name AS CountryName FROM Addressbook ab LEFT JOIN City ct ON ct.CityCode = ab.CityCode LEFT JOIN ZoneMast zm ON zm.Code = ab.Zone LEFT JOIN Country Ctry ON ct.countryCode=Ctry.Code LEFT JOIN stateMast sm ON ct.stateCode=sm.Code LEFT JOIN Subgroup sg ON sg.subcode = ab.subcode WHERE ab.code = :code`
- Params: `:code` ← `DmShared.iNull(strKey).toString()`

### `saveRecord`
`public DmResult<String> saveRecord(AddressBook_Mdl mrTemp)`

- SQL 1: `select count(*) as cnt from addressBook where subCode = :subCode and isDefault=1 and Code <> :Code`
- SQL 2: `INSERT INTO AddressBook ( CODE, SubCode, ADD1, ADD2, CityCode, PIN, Phone, Mobile, FAX, EMail, ContactPerson, U_EntDt, Site_Code, PreparedBy, preparedDt, preparedByIP, Zone, GroupName, Designation, Nature, Remarks,isDefault,initial) VALUES ( :code, :subCode, :address1, :address2, :cityCode, :pinCode, :phone, :mobile, :fax, :email, :contactPerson, :preparedDt, :siteCode, :userName, :preparedDt, :preparedByIP, :zone, :groupName, :designation, :nature, :remarks,:isDefault,:initial);`
- SQL 3: `UPDATE AddressBook SET SubCode = :subCode ,Add1 = :address1 ,Add2 = :address2 ,CityCode = :cityCode ,Pin = :pinCode ,Phone = :phone ,Mobile = :mobile ,Fax = :fax ,Email = :email ,ContactPerson = :contactPerson ,U_Name = :userName ,U_EntDt = :U_EntDt ,Zone = :zone ,GroupName = :groupName ,Designation = :designation ,Nature = :nature ,Remarks = :remark ,isDefault = :isDefault ,initial = :initial WHERE Code = :code`
- Params: `:subCode` ← `DmShared.foriegnKey(mrTemp.getLinkAccountTag())`; `:Code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getLinkAccountTag())`; `:address1` ← `DmShared.xNull(mrTemp.getAddress())`; `:address1` ← `DmShared.xNull(mrTemp.getAddress1())`; `:cityCode` ← `DmShared.foriegnKey(mrTemp.getCityCode())`; `:address1` ← `DmShared.xNull(mrTemp.getAddress())`; `:address2` ← `DmShared.xNull(mrTemp.getAddress1())`; `:cityCode` ← `DmShared.foriegnKey(mrTemp.getCityCode())`; `:pinCode` ← `DmShared.xNull(mrTemp.getPinCode())`; `:phone` ← `DmShared.xNull(mrTemp.getPhoneNo())`; `:mobile` ← `DmShared.xNull(mrTemp.getMobileNo())`; `:fax` ← `DmShared.xNull(mrTemp.getFaxNo())`; `:zone` ← `DmShared.foriegnKey(mrTemp.getZoneCode())`; `:email` ← `DmShared.xNull(mrTemp.getEmail())`; `:contactPerson` ← `DmShared.xNull(mrTemp.getContactPerson())`; `:siteCode` ← `siteCode`; `:userName` ← `userName`; `:groupName` ← `DmShared.xNull(mrTemp.getCompanyGroup())`; `:designation` ← `DmShared.foriegnKey(mrTemp.getDesignation())`; `:nature` ← `DmShared.xNull(mrTemp.getNature())`; `:remarks` ← `DmShared.xNull(mrTemp.getRemark())`; `:isDefault` ← `DmShared.xNull(mrTemp.getIsDefaultTag())`; `:initial` ← `DmShared.xNull(mrTemp.getInitialText())`; `:preparedDt` ← `timestamp`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getLinkAccountTag())`; `:address1` ← `DmShared.xNull(mrTemp.getAddress())`; `:address2` ← `DmShared.xNull(mrTemp.getAddress1())`; `:cityCode` ← `DmShared.foriegnKey(mrTemp.getCityCode())`; `:pinCode` ← `DmShared.xNull(mrTemp.getPinCode())`; `:phone` ← `DmShared.xNull(mrTemp.getPhoneNo())`; `:mobile` ← `DmShared.xNull(mrTemp.getMobileNo())`; `:fax` ← `DmShared.xNull(mrTemp.getFaxNo())`; `:email` ← `DmShared.xNull(mrTemp.getEmail())`; `:contactPerson` ← `DmShared.xNull(mrTemp.getContactPerson())`; `:userName` ← `userName`; `:zone` ← `DmShared.foriegnKey(mrTemp.getZoneCode())`; `:groupName` ← `DmShared.xNull(mrTemp.getCompanyGroup())`; `:designation` ← `DmShared.foriegnKey(mrTemp.getDesignation())`; `:nature` ← `DmShared.xNull(mrTemp.getNature())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:isDefault` ← `DmShared.xNull(mrTemp.getIsDefaultTag())`; `:initial` ← `DmShared.xNull(mrTemp.getInitialText())`; `:U_EntDt` ← `timestamp`
- Errors: "Invalid key." | "Yes for "
- Other calls: `AddressBook_Vld.chkSave`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl modifyReasonMdl)`

- SQL 1: `DELETE FROM Addressbook WHERE CODE = :code`
- Params: `:code` ← `DmShared.iNull(strKey).toString()`

### `getTxtHCity`
`public DmResultPageing<List<TxtHCity_Mdl>> getTxtHCity(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT c.cityCode,c.cityName,c.stateCode,sm.name AS stateName,c.countryCode,cn.name AS countryName FROM city AS c LEFT JOIN stateMast AS sm ON c.stateCode = sm.code LEFT JOIN country AS cn ON c.countryCode = cn.Code`

### `getTxtHCompanyGroupName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCompanyGroupName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT isNull(GroupName, '') CODE, isNull(MAX(GroupName), '') AS NAME FROM addressbook WHERE isNull(GroupName, '') <> '' GROUP BY GroupName`

### `getTxtHDesignation`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDesignation(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT isNull(Designation, '') CODE, isNull(Designation, '') AS NAME FROM addressbook WHERE isNull(designation, '') <> ''`

### `getTxtHLinkWithAccount`
`public DmResultPageing<List<TxtHLinkWithAccount_Mdl>> getTxtHLinkWithAccount(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT SG.SubCode, SG.Name, isNull(CT.CityName, '') AS City, SG.ManualCode,SG.CityCode FROM SubGroup SG LEFT JOIN City CT ON CT.CityCode = SG.CityCode WHERE SG.isActive = 1`

### `getTxtHNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHNature(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT isNull(Nature, '') CODE, isNull(MAX(Nature), '') AS NAME FROM addressbook WHERE isNull(Nature, '') <> '' GROUP BY Nature`

### `getTxtHZone`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHZone(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ZM.Code, ZM.Name FROM zonemast ZM`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(String filterAcCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT AB.Subcode AS ACCode, SG.Name AS AcName, CT.CityName, CT.CityCode, sm.name AS StateName, C.name AS CountryName, ZM.name AS ZoneName, AB.Zone, CT.CountryCode, AB.Fax, AB.Phone, AB.Add1, AB.Add2, AB.Mobile, AB.EMail, AB.ContactPerson, AB.Pin, AB.U_Name AS PreparedBy, AB.U_Name AS ModifiedBy, AB.Code AS SearchCode, AB.Remarks, AB.GroupName, AB.Nature, AB.Designation FROM Addressbook AB LEFT JOIN City CT ON CT.CityCode = AB.CityCode LEFT JOIN stateMast as sm on ct.stateCode = sm.code LEFT JOIN ZoneMast ZM ON ZM.Code = AB.Zone LEFT JOIN Country C ON CT.countryCode = C.Code LEFT JOIN Subgroup SG ON SG.subcode = AB.subcode {…}`
- Params: `:filterAcCode` ← `filterAcCode`

### `getYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHContactPrefix`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHContactPrefix(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT CODE,NAME FROM initialmast`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmInputBox.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextArea.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `dataman.libraryMenu.self.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Yes for " |

## Notes (hand-written)
_No hand-written notes yet._
