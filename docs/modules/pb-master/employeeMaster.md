# pb-master / employeeMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/master/src/employeeMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: SubGroup, subgroupsitelist
- Update tables: SubGroup, SubGroup2
- Delete cascade (in order): Subgroup2, subGroupAreaList, subgroupsitelist, Subgroup
- Duplicate checks on save:
  - `Select Count(*) As Cnt From SubGroup Where Name = :name and SubCode<> :subCode`
  - `Select Count(*) As Cnt From SubGroup where ManualCode = :manualCode and SubCode<> :subCode`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Name (Text); Display name (Text); Gender (Text); A/c group (Text); Nature (Text); Passport Expiry Date (Text); Passport Issue Date (Text); rule: Spouse Name not required.; rule: AADHAAR should be 12 digit.; rule: PAN should be of 10(Ten) characters.; rule: DOB should be less than to Current Date.; rule: DOB should be less than to Anniv. Date.; rule: DOB should be less than to Joining Date.; rule: DOB should be less than to Passport Issue Date.; rule: Anniv. Date should be less than or equal to Current Date.; rule: Joining Date should be less than or equal to Current Date.; rule: Joining Date should be less than or equal to Resignation Date.; rule: Passport Issue Date should be less than  to Passport Expiry Date.
- Lookups/suggestions: getTxtHAccountNamePrefix, getTxtHAccountGroup, getTxtHSex, getTxtHMaritalStatus, getTxtHDesignation, getTxtHCity, getTxtHCountry, getTxtHZone, getTxtHTDSCategory, getTxtHNature, getTxtHDepartment, getTxtHArea, getTxtHBloodGroup, getTxtHReligion, getTxtHActive
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `EmployeeMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Name | DmTextHelp | `txtHAccountNamePrefix` | `KeyField_PrpAcs, 7, false` |
| 2 | - | DmSuggestBox | `txtName` | `100, 10, true, "NAME", DmDataType.STRING` |
| 3 | Display Name | DmTextBox | `txtDisplayName` | `100, true` |
| 4 | Gender | DmTextHelp | `txtHGender` | `com.google.gwt.core.client.KeyField_PrpAcs, 5, true` |
| 5 | Manual Code | DmTextBox | `txtManualCode` | `25, false` |
| 6 | Marital Status | DmTextHelp | `txtHMaritalStatus` | `com.google.gwt.core.client.KeyField_PrpAcs, 8` |
| 7 | Spouse | DmTextBox | `txtSpouse` | `50, false` |
| 8 | Religion | DmTextHelp | `txtHReligion` | `com.google.gwt.core.client.KeyField_PrpAcs, 8, false` |
| 9 | A/c Group | DmTextHelp | `txtHAccountGroup` | `TxtHAccountGroup, 6, true` |
| 10 | Nature | DmTextHelp | `txtHNature` | `com.google.gwt.core.client.KeyField_PrpAcs, 10, true` |
| 11 | Zone | DmTextHelp | `txtHZone` | `KeyField_PrpAcs, 4` |
| 12 | Designation | DmTextHelp | `txtHDesignation` | `KeyField_PrpAcs, 4` |
| 13 | Department | DmTextHelp | `txtHDepartment` | `com.google.gwt.core.client.KeyField_PrpAcs, 5, false` |
| 14 | Father Name | DmTextBox | `txtFatherName` | `50, false` |
| 15 | Phone No. | DmTextBox | `txtPhoneNo` | `35, false` |
| 16 | Mobile No. | DmTextBoxMobile | `txtMobileNo` | `false` |
| 17 | Aadhaar No. | DmTextBox | `txtAadhaarNo` | `12, false` |
| 18 | Address | DmTextBox | `txtAddress` | `100, false` |
| 19 | - | DmTextBox | `txtAddress1` | `100, false` |
| 20 | City | DmTextHelp | `txtHCity` | `TxtHCity_PrpAcs, 4` |
| 21 | Country | DmTextBox | `txtCountry` | `50, false` |
| 22 | PIN | DmTextBoxNumeric | `txtPinCode` | `0, false, 6, false` |
| 23 | Address | DmTextBox | `txtAddresstemp` | `100, false` |
| 24 | - | DmTextBox | `txtAddress1temp` | `100, false` |
| 25 | City | DmTextHelp | `txtHCityTemp` | `TxtHCityTemp_PrpAcs, 4` |
| 26 | Country | DmTextBox | `txtCountryTemp` | `50, false` |
| 27 | PIN | DmTextBoxNumeric | `txtPinCodetemp` | `0, false, 6, false` |
| 28 | Date of Birth | DmTextBox | `txtDob` | `11, false` |
| 29 | Anniv. Date | DmTextBox | `txtDOA` | `11, false` |
| 30 | Joining Date | DmTextBox | `txtDOJ` | `11, false` |
| 31 | Resignation Date | DmTextBox | `txtDOR` | `11, false` |
| 32 | Blood Group | DmTextHelp | `txtHBloodGroup` | `com.google.gwt.core.client.KeyField_PrpAcs, 8` |
| 33 | PAN | DmTextBox | `txtPAN` | `10, false` |
| 34 | IFSC Code | DmTextBox | `txtifscode` | `15, false` |
| 35 | Bank A/c No. | DmTextBox | `txtBankAccNo` | `20, false` |
| 36 | PF A/c No. | DmTextBox | `txtPfAccNo` | `25, false` |
| 37 | License No. | DmTextBox | `txtLicenseNo` | `25, false` |
| 38 | License Expiry | DmTextBox | `txtLicenseExpiry` | `11, false` |
| 39 | Passport No. | DmTextBox | `txtPassporNo` | `20, false` |
| 40 | Passport Issue Date | DmTextBox | `txtPassportIssueDate` | `35, false` |
| 41 | Expiry Date | DmTextBox | `txtPassportExpiryDate` | `35, false` |
| 42 | Passport Issue Place | DmTextBox | `txtPassportIssuePlace` | `20, false` |
| 43 | Email | DmTextBox | `txtEMail` | `250, false` |
| 44 | Qualification | DmTextBox | `txtQualification` | `50, false` |
| 45 | TDS Category | DmTextHelp | `txtHTDSCategory` | `KeyField_PrpAcs, 4` |
| 46 | Area List | DmTextHelpMulti | `txtHAreaList` | `KeyField_PrpAcs, 6` |
| 47 | Remark | DmTextBox | `txtRemark` | `250, false` |

Other widgets (not in tab list): `txtHActive` (DmTextHelp), `dmFind` (DmFind), `grdBankDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHAccountNamePrefix`: Name [Text, w0, NAME]
- `txtHAccountGroup`: Group [Text, w0, GroupName]; Nature [Text, w150, NatureName]
- `txtHDesignation`: Name [Text, w0, NAME]
- `txtHCity`: City [Text, w0, CityName]; State [Text, w100, stateName]; Country [Text, w100, countryName]
- `txtHCityTemp`: City [Text, w0, CityName]; State [Text, w100, stateName]; Country [Text, w100, countryName]
- `txtHZone`: Zone [Text, w0, NAME]
- `txtHNature`: Name [Text, w1, Nature]
- `txtHTDSCategory`: TDS Category [Text, w0, NAME]
- `txtHDepartment`: Name [Text, w0, NAME]
- `txtHAreaList`: Area [Text, w150, name]
- `txtHGender`: Name [Text, w0, Name]
- `txtHMaritalStatus`: Name [Text, w0, NAME]
- `txtHBloodGroup`: Name [Text, w0, Name]
- `txtHReligion`: Name [Text, w0, Name]
- `txtHActive`: Type [Text, w0, Name]
- `dmFind`: Account Name [Text, w100, Account_Name]; Display Name [Text, w100, displayName]; Manual Code [Text, w80, Manual_Code]; Spouse Name [Text, w100, spouseName]; Father Name [Text, w100, fatherName]; Account Group [Text, w100, Ac_Group]; Nature [Text, w80, Nature]; Address [Text, w200, Address]; City [Text, w100, City]; Country [Text, w100, Country]; Zone [Text, w80, Zone]; PIN [Text, w90, Pin]; Phone [Text, w100, Phone]; Mobile [Text, w100, Mobile]; Email [Text, w130, E_Mail]; Remark [Text, w100, Remark]
- `grdBankDetail`: Account No. [Text, w130]; Bank [Text, w200]; Branch [Text, w200]; Branch Address [Text, w364]; IFSC Code [Text, w130]; Swift No. [Text, w130]; Default [Text, w50]

**Lookup / suggestion / find sources (server RPC called):**
- `txtName` → `getAccountNameSuggestion`
- `txtHAccountNamePrefix` → `getTxtHAccountNamePrefix`
- `txtHAccountGroup` → `getTxtHAccountGroup`
- `txtHDesignation` → `getTxtHDesignation`
- `txtHCity` → `getTxtHCity`
- `txtHCityTemp` → `getTxtHCity`
- `txtHZone` → `getTxtHZone`
- `txtHTDSCategory` → `getTxtHTDSCategory`
- `txtHNature` → `getTxtHNature`
- `txtHDepartment` → `getTxtHDepartment`
- `txtHMaritalStatus` → `getTxtHMaritalStatus`
- `txtHGender` → `getTxtHSex`
- `txtHBloodGroup` → `getTxtHBloodGroup`
- `txtHReligion` → `getTxtHReligion`
- `txtHActive` → `getTxtHActive`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `AccountNamePrefix` ← DmShared.xNull(txtHAccountNamePrefix.getText())
- `SharedKeyFieldName` ← DmShared.removeSpace(DmShared.xNull(txtName.getText()))
- `DisplayName` ← DmShared.removeSpace(DmShared.xNull(txtDisplayName.getText()))
- `SpouseName` ← DmShared.removeSpace(DmShared.xNull(txtSpouse.getText()))
- `ManualCode` ← DmShared.removeSpace(DmShared.xNull(txtManualCode.getText()))
- `GenderCode` ← DmShared.xNull(txtHGender.getTag())
- `GenderName` ← DmShared.xNull(txtHGender.getText())
- `MaritalStatusName` ← DmShared.xNull(txtHMaritalStatus.getText())
- `MaritalStatusCode` ← DmShared.xNull(txtHMaritalStatus.getTag())
- `MaritalStatusShortName` ← DmShared.xNull(txtHMaritalStatus.getTag2nd())
- `ReligionCode` ← DmShared.xNull(txtHReligion.getTag())
- `Religion` ← DmShared.xNull(txtHReligion.getText())
- `Zone` ← DmShared.xNull(txtHZone.getTag())
- `ZoneName` ← DmShared.xNull(txtHZone.getText())
- `AccGroup` ← DmShared.xNull(txtHAccountGroup.getTag())
- `AccGroupName` ← DmShared.xNull(txtHAccountGroup.getText())
- `NatureCode` ← DmShared.xNull(txtHNature.getTag())
- `NatureName` ← DmShared.xNull(txtHNature.getText())
- `Designation` ← DmShared.xNull(txtHDesignation.getTag())
- `DesignationName` ← DmShared.xNull(txtHDesignation.getText())
- `DepartmentCode` ← DmShared.xNull(txtHDepartment.getTag())
- `DepartmentName` ← DmShared.xNull(txtHDepartment.getText())
- `FatherName` ← DmShared.xNull(txtFatherName.getText())
- `PhoneNo` ← DmShared.xNull(txtPhoneNo.getText())
- `MobileNo` ← DmShared.xNull(txtMobileNo.getText())
- `AadhaarNo` ← DmShared.xNull(txtAadhaarNo.getText())
- `Address` ← DmShared.xNull(txtAddress.getText())
- `Address1` ← DmShared.xNull(txtAddress1.getText())
- `City` ← DmShared.xNull(txtHCity.getTag())
- `CityName` ← DmShared.xNull(txtHCity.getText())
- `Country` ← DmShared.xNull(txtCountry.getTag())
- `CountryName` ← DmShared.xNull(txtCountry.getText())
- `Pin` ← DmShared.xNull(txtPinCode.getText())
- `AddressTemp` ← DmShared.xNull(txtAddresstemp.getText())
- `Address1Temp` ← DmShared.xNull(txtAddress1temp.getText())
- `CityTemp` ← DmShared.xNull(txtHCityTemp.getTag())
- `CityNameTemp` ← DmShared.xNull(txtHCityTemp.getText())
- `CountryTemp` ← DmShared.xNull(txtCountryTemp.getTag())
- `CountryNameTemp` ← DmShared.xNull(txtCountryTemp.getText())
- `PinTemp` ← DmShared.xNull(txtPinCodetemp.getText())
- `Dob` ← DmShared.dNull(txtDob.getText())
- `Doa` ← DmShared.dNull(txtDOA.getText())
- `Doj` ← DmShared.dNull(txtDOJ.getText())
- `Dor` ← DmShared.dNull(txtDOR.getText())
- `BloodGroupCode` ← DmShared.xNull(txtHBloodGroup.getTag())
- `BloodGroupName` ← DmShared.xNull(txtHBloodGroup.getText())
- `Pan` ← DmShared.xNull(txtPAN.getText())
- `Ifsccode` ← DmShared.xNull(txtifscode.getText())
- `PfAccNo` ← DmShared.xNull(txtPfAccNo.getText())
- `BankAccNo` ← DmShared.xNull(txtBankAccNo.getText())
- `LicenseNo` ← DmShared.xNull(txtLicenseNo.getText())
- `LicenseExpiry` ← DmShared.dNull(txtLicenseExpiry.getText())
- `PassportNo` ← DmShared.xNull(txtPassporNo.getText())
- `PassportIssueDate` ← DmShared.dNull(txtPassportIssueDate.getText())
- `PassportExpirydDate` ← DmShared.dNull(txtPassportExpiryDate.getText())
- `PassportIssuePlace` ← DmShared.xNull(txtPassportIssuePlace.getText())
- `Email` ← DmShared.xNull(txtEMail.getText()).trim()
- `Qualification` ← DmShared.xNull(txtQualification.getText())
- `TdsCategory` ← DmShared.xNull(txtHTDSCategory.getTag())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtHAccountNamePrefix.setText` ← data.getAccountNamePrefix()
- `txtHAccountNamePrefix.setTag` ← data.getAccountNamePrefix()
- `txtName.setText` ← data.getSharedKeyFieldName()
- `txtDisplayName.setText` ← data.getDisplayName()
- `txtHGender.setTag` ← data.getGenderCode()
- `txtHGender.setText` ← data.getGenderName()
- `txtManualCode.setText` ← data.getManualCode()
- `txtHMaritalStatus.setTag` ← data.getMaritalStatusCode()
- `txtHMaritalStatus.setText` ← data.getMaritalStatusName()
- `txtHMaritalStatus.setTag2nd` ← data.getMaritalStatusShortName()
- `txtSpouse.setText` ← data.getSpouseName()
- `txtHReligion.setTag` ← data.getReligionCode()
- `txtHReligion.setText` ← data.getReligion()
- `txtHAccountGroup.setText` ← data.getAccGroupName()
- `txtHAccountGroup.setTag` ← data.getAccGroup()
- `txtHNature.setText` ← data.getNatureName()
- `txtHNature.setTag` ← data.getNatureCode()
- `txtHZone.setText` ← data.getZoneName()
- `txtHZone.setTag` ← data.getZone()
- `txtHDesignation.setText` ← data.getDesignationName()
- `txtHDesignation.setTag` ← data.getDesignation()
- `txtHDepartment.setText` ← data.getDepartmentName()
- `txtHDepartment.setTag` ← data.getDepartmentCode()
- `txtFatherName.setText` ← data.getFatherName()
- `txtPhoneNo.setText` ← data.getPhoneNo()
- `txtMobileNo.setText` ← data.getMobileNo()
- `txtAadhaarNo.setText` ← data.getAadhaarNo()
- `txtAddress.setText` ← data.getAddress()
- `txtAddress1.setText` ← data.getAddress1()
- `txtHCity.setText` ← data.getCityName()
- `txtHCity.setTag` ← data.getCity()
- `txtCountry.setText` ← data.getCountryName()
- `txtCountry.setTag` ← data.getCountry()
- `txtPinCode.setText` ← data.getPin()
- `txtAddresstemp.setText` ← data.getAddressTemp()
- `txtAddress1temp.setText` ← data.getAddress1Temp()
- `txtHCityTemp.setText` ← data.getCityNameTemp()
- `txtHCityTemp.setTag` ← data.getCityTemp()
- `txtCountryTemp.setText` ← data.getCountryNameTemp()
- `txtCountryTemp.setTag` ← data.getCountryTemp()
- `txtPinCodetemp.setText` ← data.getPinTemp()
- `txtDob.setText` ← data.getDob()
- `txtDOA.setText` ← data.getDoa()
- `txtDOJ.setText` ← data.getDoj()
- `txtDOR.setText` ← data.getDor()
- `txtHBloodGroup.setTag` ← data.getBloodGroupCode()
- `txtHBloodGroup.setText` ← data.getBloodGroupName()
- `txtPAN.setText` ← data.getPan()
- `txtifscode.setText` ← data.getIfsccode()
- `txtPfAccNo.setText` ← data.getPfAccNo()
- `txtBankAccNo.setText` ← data.getBankAccNo()
- `txtLicenseNo.setText` ← data.getLicenseNo()
- `txtLicenseExpiry.setText` ← data.getLicenseExpiry()
- `txtPassporNo.setText` ← data.getPassportNo()
- `txtPassportIssueDate.setText` ← data.getPassportIssueDate()
- `txtPassportExpiryDate.setText` ← data.getPassportExpirydDate()
- `txtPassportIssuePlace.setText` ← data.getPassportIssuePlace()
- `txtEMail.setText` ← data.getEmail()
- `txtQualification.setText` ← data.getQualification()
- `txtHTDSCategory.setText` ← data.getTdsCategoryName()

**On New click:**
- `clear()`
- `onAdd()`
- `txtManualCode.setEnabled(false)`
- `txtName.setFocus(true)`
- `btnCopyAddress.setVisible(true)`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`modifyReason.addCallBack` → `onOk()`
```java
try {
EmployeeMaster.this.modifyReasonMdl = modifyReason;
if (topControl.getMode().equalsIgnoreCase("Browse") && DmShared.iNull(topControl.getDocId()).longValue() > 0) {
topControlDelete(topControl.getDocId());
} else {
topControlSave();
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), (DmTextBox) null).show();
```

`topControl.addNewHandeller` → `newClicked()`
```java
clear();
onAdd();
txtManualCode.setEnabled(false);
txtName.setFocus(true);
btnCopyAddress.setVisible(true);
```

`topControl.addEditHandeller` → `editClicked()`
```java
txtManualCode.setEnabled(true);
txtCountry.setEnabled(false);
txtCountryTemp.setEnabled(false);
txtHActive.setEnabled(true);
txtName.setFocus(true);
btnCopyAddress.setVisible(true);
grdBankDetail.getGrid().getStore().add(new GrdBankDetail_Mdl());
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
menu.show(topControl.getButton("Other"));
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(EmployeeMaster.this);
```

`userRegisterationPopup.addCallBack` → `onOk()`
```java
new DmAlertMessageBox("App registeration saved successfully!", (DmTextBox) null).show();
topControlMoveRec(keyCode);
```

`userRegisterationPopup.addCallBack` → `onDelete()`
```java
new DmAlertMessageBox("App registeration deleted successfully!", (DmTextBox) null).show();
topControlMoveRec(keyCode);
```

`userRegisterationPopup.addCallBack` → `getName()`
```java
return DmShared.xNull(txtName.getText());
```

`txtName.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getAccountNameSuggestion(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<L
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtName).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtName).show();
```

`txtName.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtName).show();
```

`txtHAccountNamePrefix.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHAccountNamePrefix(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<L
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHAccountNamePrefix).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHAccountNamePrefix.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAccountNamePrefix).show();
```

`txtHAccountNamePrefix.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAccountNamePrefix.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAccountNamePrefix).show();
```

`txtHAccountGroup.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHAccountGroup(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<T
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHAccountGroup).show();
public void onSuccess(DmResultPageing<List<TxtHAccountGroup_Mdl>> result) {
if (result.isStatus()) {
txtHAccountGroup.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAccountGroup).show();
```

`txtHAccountGroup.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAccountGroup.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAccountGroup).show();
```

`txtHAccountGroup.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && tRow != null) {
txtHNature.setTag(tRow.getNatureCode());
txtHNature.setText(tRow.getNatureName());
} else {
txtHNature.setTag("");
txtHNature.setText("");
```

`txtHDesignation.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHDesignation(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
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

`txtHAreaList.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHArea(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHAreaList).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHAreaList.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAreaList).show();
```

`txtHAreaList.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAreaList.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAreaList).show();
```

`txtHCity.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHCity(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHCity_
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
txtCountry.setTag(DmShared.xNull(tRow.getCountryCode()));
txtCountry.setText(DmShared.xNull(tRow.getCountryName()));
```

`txtHCityTemp.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHCity(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHCity_
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHCityTemp).show();
public void onSuccess(DmResultPageing<List<TxtHCity_Mdl>> result) {
if (result.isStatus()) {
txtHCityTemp.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCityTemp).show();
```

`txtHCityTemp.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHCityTemp.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCityTemp).show();
```

**Top control options:** setRefreshRequired(false), setDisplay(true)

_Print permission "P" is removed for this screen (replace "P" with "*")._

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Alert: "App registeration saved successfully!"
- Alert: "App registeration deleted successfully!"
- Alert: "Can be used in browse mode only."
- Alert: "Employee is not allowed for App registration!"
- Alert: "Can be used in browse mode only."

## Files
| file | lines |
|---|---|
| client/EmployeeMaster.java | 1300 |
| client/EmployeeMaster_Dsg.java | 1414 |
| server/EmployeeMaster_SrvImpl.java | 1856 |
| shared/DefaultValue_Mdl.java | 82 |
| shared/EmployeeMaster_Mdl.java | 635 |
| shared/EmployeeMaster_Srv.java | 59 |
| shared/EmployeeMaster_SrvAsync.java | 58 |
| shared/EmployeeMaster_Vld.java | 86 |
| shared/Find_Mdl.java | 436 |
| shared/GrdBankDetail_Mdl.java | 77 |
| shared/GrdTdsCategory_Mdl.java | 42 |
| shared/TxtHAccountGroup_Mdl.java | 33 |
| shared/TxtHCity_Mdl.java | 50 |
| shared/TxtHReferencedTo_Mdl.java | 32 |
| shared/TxtHVatClause_Mdl.java | 23 |

## UI labels / columns (client)
`Name`, `Display Name`, `Gender`, `Manual Code`, `Marital Status`, `Spouse`, `Religion`, `A/c Group`, `Nature`, `Zone`, `Designation`, `Department`, `Father Name`, `Phone No.`, `Mobile No.`, `Aadhaar No.`, `Permanent Address`, `Address`, `City`, `Country`, `PIN`, `Temporary Address`, `Date of Birth`, `Anniv. Date`, `Joining Date`, `Resignation Date`, `Blood Group`, `PAN`, `IFSC Code`, `Bank A/c No.`, `PF A/c No.`, `License No.`, `License Expiry`, `Passport No.`, `Passport Issue Date`, `Expiry Date`, `Passport Issue Place`, `Email`, `Qualification`, `TDS Category`, `Area List`, `Remark`, `Active`

## Models (shared DTOs)
### TxtHReferencedTo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | CityName |
| String | ManualCode |

### GrdBankDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | accountNo |
| String | bankName |
| String | branch |
| String | swiftNo |
| String | isDefault |
| String | ifscCode |
| String | branchAddress |

### DefaultValue_Mdl
| type | field |
|---|---|
| String groupCode, | groupName |
| String natureCode, | natureName |
| DmFileRuleType | fileRuleTypeObj |

### TxtHVatClause_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | VatNonVat |

### Find_Mdl
| type | field |
|---|---|
| String | code |
| String | V_Type |
| String | AccountNamePrefix |
| String | AccountName |
| String | DisplayName |
| String | ManualCode |
| String | AccGroup |
| String | GroupNature |
| String | Nature |
| String | CostCenter |
| String | Designation |
| String | FatherName |
| String | Address |
| String | Address1 |
| String | City |
| String | Country |
| String | Zone |
| String | Pin |
| String | PhoneNo |
| String | MobileNo |
| String | FaxNo |
| String | Email |
| String | Location |
| String | PartyCategory |
| String | FbtOn |
| String | FbtValue |
| String | TdsCategory |
| String | ReferencedTo |
| String | ContactPerson |
| String | Pan |
| String | Tin |
| String | Duplicate |
| String | CstNo |
| String | StNo |
| String | EccNo |
| String | ExciseNo |
| String | Range |
| String | Division |
| String | CreditLimit |
| String | DueDays |
| String | IecNo |
| String | LedgerGroup |
| String | VatClause |
| String | PolicyNo |
| String | PartyType |
| String | Remark |
| String | spouseName |

### EmployeeMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | AccountNamePrefix |
| String | DisplayName |
| String | ManualCode |
| String | AccGroup |
| String | AccGroupName |
| String | NatureCode |
| String | NatureName |
| String | Designation |
| String | DesignationName |
| String | FatherName |
| String | Address |
| String | Address1 |
| String | AddressTemp |
| String | Address1Temp |
| String | City |
| String | CityName |
| String | Country |
| String | CountryName |
| String | Zone |
| String | ZoneName |
| String | Pin |
| String | cityTemp |
| String | cityNameTemp |
| String | countryTemp |
| String | countryNameTemp |
| String | PinTemp |
| String | PhoneNo |
| String | MobileNo |
| String | FaxNo |
| String | Email |
| String | TdsCategory |
| String | TdsCategoryName |
| String | Pan |
| String | Remark |
| String | departmentCode |
| String | departmentName |
| String | areaCodeCsv |
| String | areaNameCsv |
| String | qualification |
| String | bloodGroupCode |
| String | bloodGroupName |
| String | maritalStatusCode |
| String | maritalStatusName |
| String | maritalStatusShortName |
| String | genderCode |
| String | genderName |
| String | dob |
| String | doa |
| String | doj |
| String | dor |
| String | religion |
| String | religionCode |
| String | ifsccode |
| String | pfAccNo |
| String | bankAccNo |
| String | licenseNo |
| String | licenseExpiry |
| String | passportNo |
| String | passportIssueDate |
| String | passportExpirydDate |
| String | passportIssuePlace |
| String | activeTag |
| String | activeText |
| String | aadhaarNo |
| String | tempKeyCode |
| String | spouseName |
| DmSnapshot_Mdl | snapshot_Mdl |
| List<GrdBankDetail_Mdl> | grdBankDetail |
| ModifyReason_Mdl | modifyReasonMdl |

### TxtHAccountGroup_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | natureCode |
| String | natureName |

### TxtHCity_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | countryCode |
| String | countryName |
| String | stateName |
| String | stateCode |

### GrdTdsCategory_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | FieldValue |
| String | FieldName |
| String | Sno |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getAccountNameSuggestion | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getMoveRec | String strKey | DmResult<EmployeeMaster_Mdl> |
| saveRecord | EmployeeMaster_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl modifyReasonMdl | DmResult<String> |
| getTxtHAccountNamePrefix | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAccountGroup | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHAccountGroup_Mdl>> |
| getTxtHDesignation | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHCity | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHCity_Mdl>> |
| getTxtHCountry | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHZone | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTDSCategory | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getTxtHNature | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSex | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHMaritalStatus | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDepartment | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHArea | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHBloodGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHReligion | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHActive | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValue |  | DmResult<DefaultValue_Mdl> |
| getTempKeyCode |  | String |

## Validator `EmployeeMaster_Vld`
```java
public class EmployeeMaster_Vld {
    public static void chkSave(EmployeeMaster_Mdl mrTemp, String currentDate) {
        DmShared.isValidText(mrTemp.getSharedKeyFieldName(), "Name", 100);
        DmShared.isValidText(mrTemp.getDisplayName(), "Display name", 200);
        DmShared.isValidText(mrTemp.getGenderCode(), "Gender", 300);
        DmShared.isValidText(mrTemp.getAccGroup(), "A/c group", 400);
        DmShared.isValidText(mrTemp.getNatureCode(), "Nature", 500);
        DmShared.isValidEMail(mrTemp.getEmail(), 700);
        DmShared.isValidMobile(mrTemp.getMobileNo(), 800);
        if (!DmShared.xNull(mrTemp.getSpouseName()).trim().equalsIgnoreCase("")) {
            if (DmShared.xNull(mrTemp.getMaritalStatusShortName()).trim().equalsIgnoreCase("B")
                    || DmShared.xNull(mrTemp.getMaritalStatusShortName()).trim().equalsIgnoreCase("")) {
                throw new DmException(560, "Spouse Name not required.");
            }
        }
        if (!DmShared.xNull(mrTemp.getAadhaarNo()).trim().equalsIgnoreCase("")) {
            if (DmShared.xNull(mrTemp.getAadhaarNo()).length() != 12) {
                throw new DmException(550, "AADHAAR should be 12 digit.");
            }
        }
        if (!DmShared.xNull(mrTemp.getPan()).equalsIgnoreCase("") && DmShared.xNull(mrTemp.getPan()).length() != 10) {
            throw new DmException(600, "PAN should be of 10(Ten) characters.");
        }
        if (!DmShared.dNull(mrTemp.getDob()).trim().equalsIgnoreCase("")) {
            if ((DmShared.convertDate(mrTemp.getDob()).getTime() >= (DmShared.convertDate(currentDate)).getTime())) {
                throw new DmException(1000, "DOB should be less than to Current Date.");
            }
            if ((!DmShared.dNull(mrTemp.getDoa()).trim().equalsIgnoreCase("")) && (!DmShared.dNull(mrTemp.getDob()).trim().equalsIgnoreCase(""))) {
                if ((DmShared.convertDate(mrTemp.getDob()).getTime() >= (DmShared.convertDate(mrTemp.getDoa())).getTime())) {
                    throw new DmException(1000, "DOB should be less than to Anniv. Date.");
                }
            }
            if ((!DmShared.dNull(mrTemp.getDoj()).trim().equalsIgnoreCase("")) && (!DmShared.dNull(mrTemp.getDob()).trim().equalsIgnoreCase(""))) {
                if ((DmShared.convertDate(mrTemp.getDob()).getTime() >= (DmShared.convertDate(mrTemp.getDoj())).getTime())) {
                    throw new DmException(1000, "DOB should be less than to Joining Date.");
                }
            }
            if ((!DmShared.dNull(mrTemp.getDob()).trim().equalsIgnoreCase("")) && (!DmShared.dNull(mrTemp.getPassportIssueDate()).trim().equalsIgnoreCase(""))) {
                if ((DmShared.convertDate(mrTemp.getDob()).getTime() >= (DmShared.convertDate(mrTemp.getPassportIssueDate())).getTime())) {
                    throw new DmException(1000, "DOB should be less than to Passport Issue Date.");
                }
            }
        }
        if (!DmShared.dNull(mrTemp.getDoa()).trim().equalsIgnoreCase("")) {
            if ((DmShared.convertDate(mrTemp.getDoa()).getTime() > (DmShared.convertDate(currentDate)).getTime())) {
                throw new DmException(1100, "Anniv. Date should be less than or equal to Current Date.");
            }
        }
        if (!DmShared.dNull(mrTemp.getDoj()).trim().equalsIgnoreCase("")) {
            if ((DmShared.convertDate(mrTemp.getDoj()).getTime() > (DmShared.convertDate(currentDate)).getTime())) {
                throw new DmException(1200, "Joining Date should be less than or equal to Current Date.");
            }
        }
        if ((!DmShared.dNull(mrTemp.getDoj()).trim().equalsIgnoreCase("")) && (!DmShared.dNull(mrTemp.getDor()).trim().equalsIgnoreCase(""))) {
            if ((DmShared.convertDate(mrTemp.getDoj()).getTime() > (DmShared.convertDate(mrTemp.getDor())).getTime())) {
                throw new DmException(1400, "Joining Date should be less than or equal to Resignation Date.");
            }
        }
        if (!DmShared.dNull(mrTemp.getPassportIssueDate()).equalsIgnoreCase("")) {
            DmShared.isValidText(mrTemp.getPassportExpirydDate(), "Passport Expiry Date", 1500);
        }
        if (!DmShared.dNull(mrTemp.getPassportExpirydDate()).equalsIgnoreCase("")) {
            DmShared.isValidText(mrTemp.getPassportIssueDate(), "Passport Issue Date", 1600);
        }
        if ((!DmShared.dNull(mrTemp.getPassportIssueDate()).trim().equalsIgnoreCase("")) && (!DmShared.dNull(mrTemp.getPassportExpirydDate()).trim().equalsIgnoreCase(""))) {
            if ((DmShared.convertDate(mrTemp.getPassportIssueDate()).getTime() >= (DmShared.convertDate(mrTemp.getPassportExpirydDate())).getTime())) {
                throw new DmException(1700, "Passport Issue Date should be less than  to Passport Expiry Date.");
            }
        }
    }
}
```

## Server `EmployeeMaster_SrvImpl.java` (1856 lines)
Helpers used: `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getConnectionMongo`, `DmServer.getFieldUDT`, `DmServer.getLocalIPAddresses`, `DmServer.getParamList`, `DmServer.getSerialNoBigInt`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnFileDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getUserWithDateTime`, `DmServer.setSsnOldData`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.vNull`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `Select sg.SubCode As SearchCode, sg.Name From SubGroup sg Left Join subgroupNature sgn on sg.filterNature=sgn.code where sgn.manualCode='EM' Order By Name`

### `getAccountNameSuggestion`
`public DmResultPageing<List<String>> getAccountNameSuggestion(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select sg.Name as NAME From SubGroup sg Left Join subgroupNature sgn on sg.filterNature=sgn.code where sgn.manualCode='EM'`

### `getMoveRec`
`public DmResult<EmployeeMaster_Mdl> getMoveRec(String strKey)`

- SQL 1: `select SG.SubCode,SG.initial as InitialName,SG.name as EmployeeName,SG.displayName as DisplayName, SG.manualCode as ManualCode,SG.groupCode as GroupCode,ag.groupName as GroupName, SG.Nature as NatureCode,SGN1.Name as NatureName,SG.Add1,SG.Add2,Sg.CityCode,C.cityName, c.CountryCode,Ctry.Name AS CountryName, SG.PIN, SG.Phone,SG.Mobile, SG.Zone as Zone,ZM.Name as ZoneName, SG.u_EntDt,SG2.DesignationCode,DM.Name AS DesinationName, SG2.departmentCode, SGDep.name as departmentName, SG2.fathername as FatherName,SG2.tADD1,SG2.tADD2,SG2.tCityCode,tcity.cityName as TempCity,tcity.CountryCode as tCountry,tCtry.name as TempCountry,SG2.tPIN as TempPin, SG2.dob as birthDate,SG2.doa as annvDate,SG2.doj as joiningDate, SG2.dor as resignationDate, SG.pan,SG.ifscCode,SG2.bankAcNo,SG2.pfAcNo,SG2.licenseNo,SG2.licenseExpiry,SG2.passportNo,SG2.pIssueDate,SG2.pExpDate,SG2.pIssuePlace, SG.email as EMail,SG2.qualification as Qualification, SG.TDS_Catg,TC.Name AS TCName,SG.remark,SG.isActive,SG2.genderCode as GenderCode,hl1.name as GenderName,SG2.MaritalStatusCode,hl2.name as MaritalStatusName, hl2.shortName as maritalStatusShortName, SG2.religionCode as ReligionCode,hl3.name as Religion,SG2.bloodGroupCode as BloodGroupCode,hl4.name as BloodGroupName ,SG.aadhaarNo , SG.preparedBy, SG.preparedByIP, SG.preparedDt, SG.u_name ,SG.u_EntDt,SG2.spouseName , vSgal.areaCodeLst,vSgal.areaNameLst from subGroup SG left Join acGroup ag on ag.groupCode=SG.groupCode left join subGroup2 SG2 on SG2.subCode=SG.subCode left Join city c on c.cityCode=SG.cityCode left Join city tcity on tcity.cityCode=SG2.tCityCode left join country Ctry on Ctry.code=c.countryCode left join country tCtry on tCtry.code=tcity.CountryCode left Join zoneMast ZM on ZM.code=SG.Zone LEFT JOIN DesignationMast DM ON DM.Code=SG2.DesignationCode LEFT JOIN SubGroup SGDep ON SGDep.SubCode=SG2.departmentCode LEFT JOIN hl7Mast hl1 on hl1.code=SG2.genderCode LEFT JOIN hl7Mast hl2 on hl2.code=SG2.maritalStatusCode LEFT JOIN hl7Mast hl3 on hl3.code=SG2.religionCode LEFT JOIN hl7Mast hl4 on hl4.code=SG2.bloodGroupCode LEFT JOIN TDSCat TC ON TC.Code=SG.TDS_Catg Left Join subgroupNature sgn on SG.filterNature=sgn.code Left Join subgroupNature sgn1 on SG.Nature=sgn1.code LEFT JOIN ( SELECT STRING_AGG (CONVERT(NVARCHAR(max),sgal.areaCode), ',') AS areaCodeLst , STRING_AGG (CONVERT(NVARCHAR(max),am.name), ',') AS areaNameLst , sgal.subCode FROM subGroupAreaList sgal LEFT JOIN areaMast am ON am.code = sgal.areaCode WHERE sgal.subCode = :code group by sgal.subCode ) vSgal on vSgal.subCode = SG.subCode WHERE SG.SubCode=:code and sgn.manualCode='EM'`
- SQL 2: `SELECT subCode,sNo,bankName,accountNo,branch,swiftCode,isDefault,ifscCode,branchAddress FROM bankDetail WHERE subCode = :code ORDER BY sNo`
- Params: `:code` ← `DmShared.iNull(strKey).toString()`; `:code` ← `strKey`

### `getMongoDBFileLink`
`private String getMongoDBFileLink(String ruleCode, String Docid, String Sno, Connection cnTemp) throws Exception`

- SQL 1: `Select fileLink from linkFileCollection where docId=' {…} ' and sNo=' {…} ' and ruleCode=' {…} '`

### `saveRecord`
`public DmResult<String> saveRecord(EmployeeMaster_Mdl mrTemp)`

- SQL 1: `Select Count(*) As Cnt From SubGroup Where Name = :name and SubCode<> :subCode`
- SQL 2: `SELECT vt.v_Type FROM voucher_type vt WHERE vt.category = ' {…} '`
- SQL 3: `Select Count(*) As Cnt From SubGroup where ManualCode = :manualCode and SubCode<> :subCode`
- SQL 4: `Insert Into SubGroup(SubCode,Name,GroupCode,ManualCode ,Nature,Add1,Add2,filterNature,CityCode,PIN ,Phone,Mobile,Remark,PAN,EMail, TDS_Catg,Zone,DisplayName,Initial, preparedBy , preparedDt, preparedByIP, ifscCode,isActive, site_code,aadhaarNo )Values (:subCode, :name, :groupCode, :manualCode, :nature, :add1, :add2, :filterNature, :cityCode,:pin, :phone, :mobile,:remark,:pan,:email,:tdsCatg, :zone, :displayName, :initial, :preparedBy, :preparedDt, :preparedByIP, :ifscCode,:isActive, :siteCode,:aadhaarNo)`
- SQL 5: `update SubGroup Set Name=:name, GroupCode=:groupCode, ManualCode=:ManualCode, Nature=:Nature, Add1=:Add1, Add2=:Add2, CityCode=:CityCode, PIN=:pin, Phone=:Phone, Mobile=:Mobile, Remark=:Remark, u_Name = :uName, u_EntDt = :u_EntDt, PAN=:PAN, EMail=:EMail,TDS_Catg=:TDSCatg, Zone=:Zone, DisplayName = :DisplayName, Initial=:Initial, ifscCode=:ifscCode, isActive=:isActive, aadhaarNo=:aadhaarNo Where SubCode=:SubCode`
- SQL 6: `INSERT INTO subgroupsitelist (subCode, site_Code) VALUES (:subCode, :siteCode) ;`
- SQL 7: `Select Count(*) As Cnt From SubGroup2 Where SubCode= :subCode`
- SQL 8: `update SubGroup2 Set genderCode=:genderCode,maritalStatusCode=:maritalStatusCode,religionCode=:religionCode,FatherName=:fatherName,departmentCode=:departmentCode,DesignationCode=:DesignationCode,tADD1=:tADD1,tADD2=:tADD2,tCityCode=:tCityCode,tPIN=:tPIN,dob=:dob,doa=:doa,doj=:doj,dor=:dor,bloodGroupCode=:bloodGroupCode,pfAcNo=:pfAcNo,bankAcNo=:bankAcNo,licenseNo=:licenseNo,licenseExpiry=:licenseExpiry,passportNo=:passportNo,pIssueDate=:pIssueDate,pExpDate=:pExpDate,pIssuePlace=:pIssuePlace,qualification=:qualification ,spouseName=:spouseName where SubCode=:subCode`
- SQL 9: `DELETE FROM subGroupAreaList WHERE subCode = :subCode`
- Params: `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:subCode` ← `mrTemp.getSharedKeyFieldCode()`; `:manualCode` ← `mrTemp.getManualCode()`; `:subCode` ← `mrTemp.getSharedKeyFieldCode()`; `:subCode` ← `mrTemp.getSharedKeyFieldCode()`; `:name` ← `mrTemp.getSharedKeyFieldName()`; `:groupCode` ← `mrTemp.getAccGroup()`; `:manualCode` ← `mrTemp.getManualCode()`; `:nature` ← `DmShared.foriegnKey(mrTemp.getNatureCode())`; `:add1` ← `mrTemp.getAddress()`; `:add2` ← `mrTemp.getAddress1()`; `:filterNature` ← `DmShared.foriegnKey(filterNature)`; `:cityCode` ← `DmShared.foriegnKey(mrTemp.getCity())`; `:pin` ← `mrTemp.getPin()`; `:phone` ← `mrTemp.getPhoneNo()`; `:mobile` ← `mrTemp.getMobileNo()`; `:remark` ← `mrTemp.getRemark()`; `:pan` ← `mrTemp.getPan()`; `:email` ← `mrTemp.getEmail()`; `:tdsCatg` ← `DmShared.foriegnKey(mrTemp.getTdsCategory())`; `:zone` ← `DmShared.foriegnKey(mrTemp.getZone())`; `:displayName` ← `mrTemp.getDisplayName()`; `:initial` ← `mrTemp.getAccountNamePrefix()`; `:preparedBY` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `timestamp`; `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:ifscCode` ← `mrTemp.getIfsccode()`; `:isActive` ← `DmShared.iNull(mrTemp.getActiveTag()).intValue() == 0 ? "0" : "1"`; `:aadhaarNo` ← `DmShared.xNull(mrTemp.getAadhaarNo())`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:SubCode` ← `mrTemp.getSharedKeyFieldCode()`; `:name` ← `mrTemp.getSharedKeyFieldName()`; `:groupCode` ← `mrTemp.getAccGroup()`; `:ManualCode` ← `mrTemp.getManualCode()`; `:isActive` ← `DmShared.iNull(mrTemp.getActiveTag()).intValue() == 0 ? "0" : "1"`; `:Nature` ← `DmShared.foriegnKey(mrTemp.getNatureCode())`; `:Add1` ← `mrTemp.getAddress()`; `:Add2` ← `mrTemp.getAddress1()`; `:CityCode` ← `DmShared.foriegnKey(mrTemp.getCity())`; `:pin` ← `mrTemp.getPin()`; `:Phone` ← `mrTemp.getPhoneNo()`; `:Mobile` ← `mrTemp.getMobileNo()`; `:Remark` ← `mrTemp.getRemark()`; `:uName` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `timestamp`; `:PAN` ← `mrTemp.getPan()`; `:EMail` ← `mrTemp.getEmail()`; `:Zone` ← `DmShared.foriegnKey(mrTemp.getZone())`; `:DisplayName` ← `mrTemp.getDisplayName()`; `:Initial` ← `mrTemp.getAccountNamePrefix()`; `:ifscCode` ← `mrTemp.getIfsccode()`; `:TDSCatg` ← `DmShared.foriegnKey(mrTemp.getTdsCategory())`; `:aadhaarNo` ← `DmShared.xNull(mrTemp.getAadhaarNo())`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getSharedKeyFieldCode())`; `:siteCode` ← `DmShared.foriegnKey(siteList)`; `:subCode` ← `mrTemp.getSharedKeyFieldCode()`; `:subCode` ← `mrTemp.getSharedKeyFieldCode()`; `:genderCode` ← `DmShared.foriegnKey(mrTemp.getGenderCode())`; `:maritalStatusCode` ← `DmShared.foriegnKey(mrTemp.getMaritalStatusCode())`; `:religionCode` ← `DmShared.foriegnKey(mrTemp.getReligionCode())`; `:fatherName` ← `mrTemp.getFatherName()`; `:departmentCode` ← `DmShared.foriegnKey(mrTemp.getDepartmentCode())`; `:DesignationCode` ← `DmShared.foriegnKey(mrTemp.getDesignation())`; `:tADD1` ← `mrTemp.getAddressTemp()`; `:tADD2` ← `mrTemp.getAddress1Temp()`; `:tCityCode` ← `DmShared.foriegnKey(mrTemp.getCityTemp())`; `:tPIN` ← `mrTemp.getPinTemp()`; `:dob` ← `DmServer.convertDate(mrTemp.getDob())`; `:doa` ← `DmServer.convertDate(mrTemp.getDoa())`; `:doj` ← `DmServer.convertDate(mrTemp.getDoj())`; `:dor` ← `DmServer.convertDate(mrTemp.getDor())`; `:bloodGroupCode` ← `DmShared.foriegnKey(mrTemp.getBloodGroupCode())`; `:pfAcNo` ← `mrTemp.getPfAccNo()`; `:bankAcNo` ← `mrTemp.getBankAccNo()`; `:licenseNo` ← `mrTemp.getLicenseNo()`; `:licenseExpiry` ← `DmServer.convertDate(mrTemp.getLicenseExpiry())`; `:passportNo` ← `mrTemp.getPassportNo()`; `:pIssueDate` ← `DmServer.convertDate(mrTemp.getPassportIssueDate())`; `:pExpDate` ← `DmServer.convertDate(mrTemp.getPassportExpirydDate())`; `:pIssuePlace` ← `mrTemp.getPassportIssuePlace()`; `:qualification` ← `mrTemp.getQualification()`; `:spouseName` ← `DmShared.removeSpace(mrTemp.getSpouseName())`; `:subCode` ← `mrTemp.getSharedKeyFieldCode()`; `:genderCode` ← `DmShared.foriegnKey(mrTemp.getGenderCode())`; `:maritalStatusCode` ← `DmShared.foriegnKey(mrTemp.getMaritalStatusCode())`; `:religionCode` ← `DmShared.foriegnKey(mrTemp.getReligionCode())`; `:fatherName` ← `mrTemp.getFatherName()`; `:departmentCode` ← `DmShared.foriegnKey(mrTemp.getDepartmentCode())`; `:DesignationCode` ← `DmShared.foriegnKey(mrTemp.getDesignation())`; `:tADD1` ← `mrTemp.getAddressTemp()`
- Errors: "Invalid key." | "Duplicate Name Not Allowed !!!" | "Duplicate Manual Code Not Allowed !!!" | "Manual Code is mandatory."
- Other calls: `DmFileManager_Server.manageLinkFileCollection`, `EmployeeMaster_Vld.chkSave`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl modifyReasonMdl)`

- SQL 1: `Delete From Subgroup2 Where SubCode = :SubCode`
- SQL 2: `DELETE FROM subGroupAreaList WHERE subCode = :subCode`
- SQL 3: `Delete From subgroupsitelist Where subCode= :code`
- SQL 4: `Delete From Subgroup Where SubCode = :SubCode`
- SQL 5: `SELECT lfc.sNo, lfc.ruleCode FROM linkFileCollection lfc WHERE docId = :docId`
- Params: `:SubCode` ← `strKey`; `:subCode` ← `strKey`; `:code` ← `strKey`; `:SubCode` ← `strKey`; `:docId` ← `DmShared.iNull(strKey).toString()`

### `getTxtHAccountNamePrefix`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAccountNamePrefix(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT CODE,NAME FROM initialmast`

### `getTxtHAccountGroup`
`public DmResultPageing<List<TxtHAccountGroup_Mdl>> getTxtHAccountGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ag.GroupCode,ag.GroupName , ag.Nature as NatureCode,sgn.name as NatureName, ag.GroupNature FROM AcGroup ag left join subgroupNature sgn on ag.nature= sgn.code Left Join acgroup ag1 On ag.GroupUnder = ag1.GroupCode where ag1.groupName='Employee' or ag.groupName='Employee'`

### `getTxtHSex`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSex(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT CODE, NAME FROM hl7Mast where tableName='administrativeSex' and isActive=1`

### `getTxtHMaritalStatus`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHMaritalStatus(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT CODE, NAME, shortName as maritalStatusShortName FROM hl7Mast where tableName='maritalStatus' and isActive=1`

### `getTxtHDesignation`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDesignation(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT DM.Code,DM.Name FROM DesignationMast DM`

### `getTxtHCity`
`public DmResultPageing<List<TxtHCity_Mdl>> getTxtHCity(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT c.cityCode , c.cityName,c.countryCode, cnt.name as countryName ,sm.name as stateName,c.stateCode FROM city as c left join country as cnt on cnt.code = c.countryCode Left Join stateMast sm on c.stateCode=sm.code`

### `getTxtHCountry`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCountry(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT CODE, NAME FROM country`

### `getTxtHZone`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHZone(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ZM.Code, ZM.Name FROM zonemast ZM`

### `getTxtHTDSCategory`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTDSCategory(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT CODE,NAME FROM TdsCat`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select SG.SubCode,SG.ManualCode As Manual_Code ,SG.Name As Account_Name, SG.Name As displayName, CONCAT(isNull(SG.Add1,'') ,' ', isNull(SG.Add2,'')) AS Address ,isNull(CityName,'') As City,isNull(Ctry.Name,'') As Country, isNull(ZN.Name,'') As Zone,SG.PIN,isNull(SG.Phone,'') As Phone ,isNull(SG.Mobile,'') As Mobile, isNull(SG.EMail,'') As E_Mail,AG.GroupName As Ac_Group ,sgn.name as Nature,SG2.fathername as FatherName,SG2.spouseName, sg.remark From Subgroup SG LEFT JOIN SubGroup2 SG2 on SG2.subcode=SG.subcode Left Join City C On C.CityCode=SG.CityCode Left Join Country Ctry On Ctry.Code=c.CountryCode Left Join ZoneMast ZN On ZN.Code=SG.Zone Left Join AcGroup AG On AG.GroupCode=SG.GroupCode Left join subgroupNature sgn on SG.nature= sgn.code Left join subgroupNature sgn1 on SG.filterNature= sgn1.code where sgn1.manualCode='EM'`

### `getTxtHNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHNature(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select Code ,Name as Nature From subGroupNature`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sdm.subCODE, sdm.NAME FROM departmentMast dm left join subgroup sdm on dm.subcode = sdm.subcode left join subGroupNature as sgn on sgn.code = sdm.nature Left join subGroupSiteList as sgsl on sdm.subCode=sgsl.subCode where sdm.isActive = 1 and sgn.manualCode in ('DP','CM') and sgsl.site_Code=:siteCode {…}`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getTxtHArea`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHArea(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT am.code, am.name FROM areaMast am`

### `getTxtHBloodGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHBloodGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT CODE, NAME FROM hl7Mast where tableName='bloodGroup' and isActive=1`

### `getTxtHReligion`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHReligion(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT CODE, NAME FROM hl7Mast where tableName='religion' and isActive=1`

### `getTxtHActive`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHActive(DmHelper helper, boolean blnToEndOfFile)`


### `getDefaultValue`
`public DmResult<DefaultValue_Mdl> getDefaultValue()`

- SQL 1: `SELECT ec.acGroupEmployeeCode as groupCode, ac.groupName as groupName , ac.nature as natureCode, sgn.name as natureName , ec.ownerDesignationCode,ec.aoDesignationCode,ec.srDesignationCode FROM enviroCompany ec left join acGroup ac on ac.groupCode = ec.acGroupEmployeeCode left join subgroupNature as sgn on ac.nature = sgn.code`
- SQL 2: `select ftm.ruleCode , ftm.fileType , ftm.maxSize , ftm.extFilters , ftm.description from {…} .dbo.fileTypeMast ftm WHERE ruleCode = :ruleCode and isDefault =1`
- Params: `:ruleCode` ← `ProjectRuleCode.EMPLOYEE_SNAPSHOT.getRuleCode()`
- Errors: "Employee  groupCode not exsist."
- Other calls: `EMPLOYEE_SNAPSHOT.getRuleCode`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmFileManager.shared`, `dataman.dmBase.dmFileManagerMongo.server`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFileManager.client`, `dataman.dmToolkit.dmFileManager.server`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmSnapshot.client`, `dataman.dmToolkit.dmSnapshot.shared`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxMobile.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.manageSite.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.self.client`, `dataman.libraryMenu.self.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`, `paperbiz.selfToolkit.userRegisteration.client`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Duplicate Name Not Allowed !!!" |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Duplicate Manual Code Not Allowed !!!" |
| saveRecord | `if (DmShared.xNull(mrTemp.getManualCode()).equalsIgnoreCase(""))` | "Manual Code is mandatory." |
| getDefaultValue | `if (DmShared.xNull(listNode.getGroupCode()).equalsIgnoreCase(""))` | "Employee  groupCode not exsist." |

## Notes (hand-written)
_No hand-written notes yet._
