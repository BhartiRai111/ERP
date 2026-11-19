# pb-master / manufacturerMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/master/src/manufacturerMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: SubGroup, subgroupsitelist
- Update tables: SubGroup, SubGroup2
- Delete cascade (in order): Subgroup2, subgroupsitelist, Subgroup
- Duplicate checks on save:
  - `Select Count(*) As Cnt From SubGroup Where Name = :name and SubCode<> :subCode`
  - `Select Count(*) As Cnt From SubGroup where ManualCode = :manualCode and SubCode<> :subCode`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Account name (Text); Display name (Text); A/c group (Text); nature (Text); City (Text); GSTIN No. (Text); GSTIN Date (Text); rule: PAN should be of 10(Ten) characters.; rule: Please fill the PAN No.; rule: In case of unregistered party type, you should not fill GSTIN No. and GSTIN Date.; rule: In case of unregistered party type AADHAAR No. or PAN No. is mandatory.; rule: GSTIN Date should not be greater than current date.; rule: AADHAAR No. should be 12 digit.
- Lookups/suggestions: getTxtHAccountNamePrefix, getTxtHAccountGroup, getTxtHCostCenter, getTxtHCity, getTxtHZone, getTxtHTDSCategory, getTxtHReferencedTo, getTxtHDuplicate, getTxtHLadgerGroup, getTxtHPartyType, getTxtHNature
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `ManufacturerMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Account Name | DmTextHelp | `txtHAccountNamePrefix` | `KeyField_PrpAcs, 7, false` |
| 2 | - | DmSuggestBox | `txtName` | `100, 10, true, "NAME", DmDataType.STRING` |
| 3 | Display Name | DmTextBox | `txtDisplayName` | `100, true` |
| 4 | Manual Code | DmTextBox | `txtManualCode` | `25, false` |
| 5 | A/c Group | DmTextHelp | `txtHAccountGroup` | `TxtHAccountGroup, 6, true` |
| 6 | Nature | DmTextHelp | `txtHNature` | `com.google.gwt.core.client.KeyField_PrpAcs, 10, true` |
| 7 | Cost Center | DmTextHelp | `txtHCostCenter` | `KeyField_PrpAcs, 4` |
| 8 | Address | DmTextBox | `txtAddress` | `100, false` |
| 9 | - | DmTextBox | `txtAddress1` | `100, false` |
| 10 | City | DmTextHelp | `txtHCity` | `TxtHCity_Mdl_PrpAcs, 4, false` |
| 11 | Pin | DmTextBoxNumeric | `txtPinCode` | `0, false, 6, false` |
| 12 | State | DmTextBox | `txtState` | `100, false` |
| 13 | Party Type (GST) | DmTextHelp | `txtHPartyType` | `KeyField_PrpAcs, 4, false` |
| 14 | GSTIN | DmTextBox | `txtGstTin` | `15, false` |
| 15 | GSTIN Date | DmTextBox | `txtGstDate` | `11, false` |
| 16 | AADHAAR | DmTextBoxNumeric | `txtAadhar` | `0, false, 12, false` |
| 17 | PAN | DmTextBox | `txtPAN` | `10, false` |
| 18 | Father Name | DmTextBox | `txtFatherName` | `50, false` |
| 19 | Zone | DmTextHelp | `txtHZone` | `KeyField_PrpAcs, 4` |
| 20 | Phone No. | DmTextBox | `txtPhoneNo` | `35, false` |
| 21 | Mobile No. | DmTextBox | `txtMobileNo` | `35, false` |
| 22 | Fax No. | DmTextBox | `txtFaxNo` | `35, false` |
| 23 | EMail | DmTextBox | `txtEMail` | `250, false` |
| 24 | TDS Category | DmTextHelp | `txtHTDSCategory` | `KeyField_PrpAcs, 4` |
| 25 | Distributor | DmTextHelp | `txtHReferencedTo` | `TxtHReferencedTo, 3` |
| 26 | Contact Person | DmTextBox | `txtContactPerson` | `35, false` |
| 27 | Policy No. | DmTextBox | `txtPolicyNo` | `50, false` |
| 28 | Range | DmTextBox | `txtRange` | `35, false` |
| 29 | Division | DmTextBox | `txtDivision` | `35, false` |
| 30 | TIN | DmTextBox | `txtTIN` | `20, false` |
| 31 | Duplicate | DmTextHelp | `txtHDuplicate` | `KeyField_PrpAcs, 4` |
| 32 | CST No. | DmTextBox | `txtCSTNo` | `40, false` |
| 33 | ST No. | DmTextBox | `txtSTNo` | `40, false` |
| 34 | ECC No. | DmTextBox | `txtECCNo` | `35, false` |
| 35 | Excise No. | DmTextBox | `txtExciseNo` | `35, false` |
| 36 | Credit Limit | DmTextBoxNumeric | `txtCreditLimit` | `DmTextBoxNumeric.DecimalNature.DEFAULT, false, 10, false` |
| 37 | Due Days | DmTextBoxNumeric | `txtDueDays` | `0, false, 5, false` |
| 38 | IEC No. | DmTextBox | `txtIECNo` | `35, false` |
| 39 | Ledger Group | DmTextHelp | `txtHLedgerGroup` | `KeyField_PrpAcs, 4` |
| 40 | Remark | DmRemarkTextBox | `txtRemark` | `250, false` |

Other widgets (not in tab list): `dmFind` (DmFind), `grdBankDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHAccountNamePrefix`: Name [Text, w0, NAME]
- `txtHAccountGroup`: Group [Text, w0, GroupName]; Nature [Text, w150, NatureName]
- `txtHCostCenter`: Name [Text, w0, NAME]
- `txtHCity`: City [Text, w0, CityName]; State [Text, w100, stateName]; Country [Text, w100, countryName]
- `txtHZone`: Zone [Text, w0, NAME]
- `txtHNature`: Name [Text, w1, Nature]
- `txtHTDSCategory`: TDS Category [Text, w0, NAME]
- `txtHReferencedTo`: Name [Text, w0, NAME]; City [Text, w150, CityName]; Code [Text, w150, ManualCode]
- `txtHDuplicate`: Type [Text, w0, Type]
- `txtHLedgerGroup`: Ledger Group [Text, w0, NAME]
- `txtHPartyType`: Type [Text, w0, Type]
- `dmFind`: Account Name [Text, w200, Account_Name]; Display Name [Text, w200, displayName]; Manual Code [Text, w100, Manual_Code]; Account Group [Text, w150, Ac_Group]; Nature [Text, w100, Nature]; Address [Text, w150, Address]; City [Text, w100, City]; Country [Text, w100, Country]; Zone [Text, w100, Zone]; PIN [Text, w100, Pin]; Phone [Text, w120, Phone]; Mobile [Text, w120, Mobile]; Email [Text, w150, E_Mail]; FAX [Text, w150, Fax]; Cost Center [Text, w100, Cost_Center]; TIN [Text, w100, TIN]; Distributor [Text, w100, Distributor]
- `grdBankDetail`: Account No. [Text, w130]; Bank [Text, w200]; Branch [Text, w200]; Branch Address [Text, w364]; IFSC Code [Text, w130]; Swift No. [Text, w130]; Default [Text, w50]

**Lookup / suggestion / find sources (server RPC called):**
- `txtName` → `getAccountNameSuggestion`
- `txtHAccountNamePrefix` → `getTxtHAccountNamePrefix`
- `txtHAccountGroup` → `getTxtHAccountGroup`
- `txtHCostCenter` → `getTxtHCostCenter`
- `txtHCity` → `getTxtHCity`
- `txtHZone` → `getTxtHZone`
- `txtHTDSCategory` → `getTxtHTDSCategory`
- `txtHReferencedTo` → `getTxtHReferencedTo`
- `txtHDuplicate` → `getTxtHDuplicate`
- `txtHLedgerGroup` → `getTxtHLadgerGroup`
- `txtHPartyType` → `getTxtHPartyType`
- `txtHNature` → `getTxtHNature`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `AccountNamePrefix` ← DmShared.xNull(txtHAccountNamePrefix.getText())
- `SharedKeyFieldName` ← DmShared.xNull(txtName.getText())
- `DisplayName` ← DmShared.xNull(txtDisplayName.getText())
- `ManualCode` ← DmShared.xNull(txtManualCode.getText())
- `AccGroup` ← DmShared.xNull(txtHAccountGroup.getTag())
- `AccGroupName` ← DmShared.xNull(txtHAccountGroup.getText())
- `NatureCode` ← DmShared.xNull(txtHNature.getTag())
- `NatureName` ← DmShared.xNull(txtHNature.getText())
- `CostCenter` ← DmShared.xNull(txtHCostCenter.getTag())
- `FatherName` ← DmShared.xNull(txtFatherName.getText())
- `Address` ← DmShared.xNull(txtAddress.getText())
- `Address1` ← DmShared.xNull(txtAddress1.getText())
- `City` ← DmShared.xNull(txtHCity.getTag())
- `StateNumericCode` ← DmClient.vNull(txtState.getTag(), "00")
- `Zone` ← DmShared.xNull(txtHZone.getTag())
- `Pin` ← DmShared.xNull(txtPinCode.getText())
- `PhoneNo` ← DmShared.xNull(txtPhoneNo.getText())
- `MobileNo` ← DmShared.xNull(txtMobileNo.getText())
- `FaxNo` ← DmShared.xNull(txtFaxNo.getText())
- `Email` ← DmShared.xNull(txtEMail.getText())
- `TdsCategory` ← DmShared.xNull(txtHTDSCategory.getTag())
- `ReferencedTo` ← DmShared.xNull(txtHReferencedTo.getTag())
- `ContactPerson` ← DmShared.xNull(txtContactPerson.getText())
- `Pan` ← DmShared.xNull(txtPAN.getText()).toUpperCase()
- `Tin` ← DmShared.xNull(txtTIN.getText())
- `Duplicate` ← DmShared.xNull(txtHDuplicate.getTag())
- `CstNo` ← DmShared.xNull(txtCSTNo.getText())
- `Stno` ← DmShared.xNull(txtSTNo.getText())
- `EccNo` ← DmShared.xNull(txtECCNo.getText())
- `ExciseNo` ← DmShared.xNull(txtExciseNo.getText())
- `Range` ← DmShared.xNull(txtRange.getText())
- `Division` ← DmShared.xNull(txtDivision.getText())
- `CreditLimit` ← DmClient.vNull(txtCreditLimit.getText(), "0.00")
- `DueDays` ← DmClient.vNull(txtDueDays.getText(), "0")
- `IecNo` ← DmShared.xNull(txtIECNo.getText())
- `LedgerGroup` ← DmShared.xNull(txtHLedgerGroup.getTag())
- `PolicyNo` ← DmShared.xNull(txtPolicyNo.getText())
- `PartyType` ← DmShared.xNull(txtHPartyType.getTag())
- `Remark` ← DmShared.xNull(txtRemark.getText())
- `Aadhar` ← DmShared.xNull(txtAadhar.getText())
- `GsTINDate` ← DmShared.dNull(txtGstDate.getText())
- `GsTin` ← DmShared.xNull(txtGstTin.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtHAccountNamePrefix.setText` ← data.getAccountNamePrefix()
- `txtHAccountNamePrefix.setTag` ← data.getAccountNamePrefix()
- `txtName.setText` ← data.getSharedKeyFieldName()
- `txtDisplayName.setText` ← data.getDisplayName()
- `txtManualCode.setText` ← data.getManualCode()
- `txtHAccountGroup.setText` ← data.getAccGroupName()
- `txtHAccountGroup.setTag` ← data.getAccGroup()
- `txtHNature.setText` ← data.getNatureName()
- `txtHNature.setTag` ← data.getNatureCode()
- `txtHCostCenter.setText` ← data.getCostCenterName()
- `txtHCostCenter.setTag` ← data.getCostCenter()
- `txtFatherName.setText` ← data.getFatherName()
- `txtAddress.setText` ← data.getAddress()
- `txtAddress1.setText` ← data.getAddress1()
- `txtHCity.setText` ← data.getCityName()
- `txtHCity.setTag` ← data.getCity()
- `txtState.setText` ← data.getStateName()
- `txtState.setTag` ← data.getStateNumericCode()
- `txtHZone.setText` ← data.getZoneName()
- `txtHZone.setTag` ← data.getZone()
- `txtPinCode.setText` ← data.getPin()
- `txtPhoneNo.setText` ← data.getPhoneNo()
- `txtMobileNo.setText` ← data.getMobileNo()
- `txtFaxNo.setText` ← data.getFaxNo()
- `txtEMail.setText` ← data.getEmail()
- `txtAadhar.setText` ← data.getAadhar()
- `txtGstDate.setText` ← data.getGsTINDate()
- `txtGstTin.setText` ← data.getGsTin()
- `txtHTDSCategory.setText` ← data.getTdsCategoryName()
- `txtHTDSCategory.setTag` ← data.getTdsCategory()
- `txtHReferencedTo.setText` ← data.getReferencedToName()
- `txtHReferencedTo.setTag` ← data.getReferencedTo()
- `txtContactPerson.setText` ← data.getContactPerson()
- `txtPAN.setText` ← data.getPan()
- `txtTIN.setText` ← data.getTin()
- `txtHDuplicate.setTag` ← data.getDuplicate()
- `txtCSTNo.setText` ← data.getCstNo()
- `txtSTNo.setText` ← data.getStno()
- `txtECCNo.setText` ← data.getEccNo()
- `txtExciseNo.setText` ← data.getExciseNo()
- `txtRange.setText` ← data.getRange()
- `txtDivision.setText` ← data.getDivision()
- `txtCreditLimit.setText` ← String.valueOf(data.getCreditLimit())
- `txtDueDays.setText` ← String.valueOf(data.getDueDays())
- `txtIECNo.setText` ← data.getIecNo()
- `txtHLedgerGroup.setText` ← data.getLedgerGroupName()
- `txtHLedgerGroup.setTag` ← data.getLedgerGroup()
- `txtPolicyNo.setText` ← data.getPolicyNo()
- `txtHPartyType.setTag` ← data.getPartyType()
- `txtRemark.setText` ← data.getRemark()
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**On New click:**
- `clear()`
- `getTempKeyCode()`
- `txtManualCode.setEnabled(false)`
- `txtHPartyType.setText(DmEnum.DmPartyType.Unregistered.getValue())`
- `txtHPartyType.setTag(DmEnum.DmPartyType.Unregistered.getId())`
- `txtHAccountNamePrefix.setFocus(true)`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
clear();
getTempKeyCode();
txtManualCode.setEnabled(false);
if (!defaultValue.isGstApplicable()) {
txtHPartyType.setText(DmEnum.DmPartyType.Unregistered.getValue());
txtHPartyType.setTag(DmEnum.DmPartyType.Unregistered.getId());
txtHAccountNamePrefix.setFocus(true);
```

`topControl.addEditHandeller` → `editClicked()`
```java
txtName.setFocus(true);
txtManualCode.setEnabled(true);
txtState.setEnabled(false);
grdBankDetail.getGrid().getStore().add(new GrdBankDetail_Mdl());
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

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(ManufacturerMaster.this);
```

`txtName.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getAccountNameSuggestion(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<L
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
SrvAsync.invokeRPC(blnCallBackWait).getTxtHAccountNamePrefix(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<L
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
SrvAsync.invokeRPC(blnCallBackWait).getTxtHAccountGroup(DmShared.xNull(defaultValue.getAcGroupManufacturerCode()), helpe
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
if (blnStatus) {
txtHNature.setTag(tRow.getNatureCode());
txtHNature.setText(tRow.getNatureName());
```

`txtHCostCenter.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHCostCenter(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Key
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHCostCenter).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHCostCenter.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCostCenter).show();
```

`txtHCostCenter.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHCostCenter.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCostCenter).show();
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
txtState.setTag(tRow.getStateNumericCode());
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

`txtHTDSCategory.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHTDSCategory(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHTDSCategory).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
try {
if (result.isStatus()) {
txtHTDSCategory.setData(result.getData(), result.getPaging());
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), txtHTDSCategory).show();
```

`txtHTDSCategory.addTextHelpCallBack` → `onSuccess()`
```java
try {
if (result.isStatus()) {
txtHTDSCategory.setData(result.getData(), result.getPaging());
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), txtHTDSCategory).show();
```

`txtHReferencedTo.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHReferencedTo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<T
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHReferencedTo).show();
public void onSuccess(DmResultPageing<List<TxtHReferencedTo_Mdl>> result) {
try {
if (result.isStatus()) {
txtHReferencedTo.setData(result.getData(), result.getPaging());
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), txtHReferencedTo).show();
```

`txtHReferencedTo.addTextHelpCallBack` → `onSuccess()`
```java
try {
if (result.isStatus()) {
txtHReferencedTo.setData(result.getData(), result.getPaging());
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), txtHReferencedTo).show();
```

`txtHDuplicate.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHDuplicate(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyF
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDuplicate).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
try {
if (result.isStatus()) {
txtHDuplicate.setData(result.getData(), result.getPaging());
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), txtHDuplicate).show();
```

`txtHDuplicate.addTextHelpCallBack` → `onSuccess()`
```java
try {
if (result.isStatus()) {
txtHDuplicate.setData(result.getData(), result.getPaging());
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), txtHDuplicate).show();
```

**Top control options:** setRefreshRequired(false), setDisplay(true)

_Print permission "P" is removed for this screen (replace "P" with "*")._
- Confirm: "Are you sure? You want to delete this record."
- Alert: "Due days can't be greater then 32767"
- Alert: "Can be used in browse mode only."

## Files
| file | lines |
|---|---|
| client/ManufacturerMaster.java | 1046 |
| client/ManufacturerMaster_Dsg.java | 1164 |
| server/ManufacturerMaster_SrvImpl.java | 1346 |
| shared/DefaultValue_Mdl.java | 40 |
| shared/Find_Mdl.java | 427 |
| shared/GrdBankDetail_Mdl.java | 77 |
| shared/GrdTdsCategory_Mdl.java | 41 |
| shared/ManufacturerMaster_Mdl.java | 480 |
| shared/ManufacturerMaster_Srv.java | 50 |
| shared/ManufacturerMaster_SrvAsync.java | 50 |
| shared/ManufacturerMaster_Vld.java | 64 |
| shared/TxtHAccountGroup_Mdl.java | 32 |
| shared/TxtHCity_Mdl.java | 59 |
| shared/TxtHReferencedTo_Mdl.java | 31 |
| shared/TxtHVatClause_Mdl.java | 22 |

## UI labels / columns (client)
`Account Name`, `Display Name`, `Manual Code`, `A/c Group`, `Nature`, `Cost Center`, `Address`, `City`, `Pin`, `State`, `Party Type (GST)`, `GSTIN`, `GSTIN Date`, `AADHAAR`, `PAN`, `Father Name`, `Zone`, `Phone No.`, `Mobile No.`, `Fax No.`, `EMail`, `TDS Category`, `Distributor`, `Contact Person`, `Policy No.`, `Range`, `Division`, `TIN`, `Duplicate`, `CST No.`, `ST No.`, `ECC No.`, `Excise No.`, `Credit Limit`, `Due Days`, `IEC No.`, `Ledger Group`, `Remark`

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
| String | acGroupManufacturerCode |
| String | acGroupManufacturerName |
| boolean | gstApplicable |

### ManufacturerMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | tempKeyCode |
| String | AccountNamePrefix |
| String | DisplayName |
| String | ManualCode |
| String | AccGroup |
| String | AccGroupName |
| String | NatureCode |
| String | NatureName |
| String | CostCenter |
| String | CostCenterName |
| String | FatherName |
| String | Address |
| String | Address1 |
| String | City |
| String | CityName |
| String | StateName |
| String | Zone |
| String | ZoneName |
| String | Pin |
| String | PhoneNo |
| String | MobileNo |
| String | FaxNo |
| String | Email |
| String | TdsCategory |
| String | TdsCategoryName |
| String | ReferencedTo |
| String | ReferencedToName |
| String | ContactPerson |
| String | Pan |
| String | Tin |
| String | Duplicate |
| String | CstNo |
| String | Stno |
| String | EccNo |
| String | ExciseNo |
| String | Range |
| String | Division |
| String | CreditLimit |
| String | DueDays |
| String | IecNo |
| String | LedgerGroup |
| String | LedgerGroupName |
| String | PolicyNo |
| String | PartyType |
| String | Remark |
| String | Aadhar |
| String | gsTINDate |
| String | gsTin |
| String | currentDate |
| String | stateNumericCode |
| boolean | gstApplicable |
| List<GrdBankDetail_Mdl> | grdBankDetail |

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

### TxtHAccountGroup_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | natureCode |
| String | natureName |

### TxtHCity_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | stateCode |
| String | stateName |
| String | countryCode |
| String | countryName |
| String | stateNumericCode |

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
| getMoveRec | String strKey | DmResult<ManufacturerMaster_Mdl> |
| saveRecord | ManufacturerMaster_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey | DmResult<String> |
| getTxtHAccountNamePrefix | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAccountGroup | String manufacturerCode, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHAccountGroup_Mdl>> |
| getTxtHCostCenter | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHCity | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHCity_Mdl>> |
| getTxtHZone | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTDSCategory | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHReferencedTo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHReferencedTo_Mdl>> |
| getTxtHDuplicate | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHLadgerGroup | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPartyType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getTxtHNature | String manufacturerCode, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTempKeyCode |  | String |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| getServerDateTime |  | String[] |

## Validator `ManufacturerMaster_Vld`
```java
public class ManufacturerMaster_Vld {
    public static void chkSave(ManufacturerMaster_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getSharedKeyFieldName(), "Account name", 100);
        DmShared.isValidText(mrTemp.getDisplayName(), "Display name", 200);
        DmShared.isValidText(mrTemp.getAccGroup(), "A/c group", 300);
        DmShared.isValidText(mrTemp.getNatureCode(), "nature", 500);
        DmShared.isValidEMail(mrTemp.getEmail(), 400);
        if (!DmShared.xNull(mrTemp.getPartyType()).trim().equalsIgnoreCase("")) {
            DmShared.isValidText(mrTemp.getCity(), "City", 550);
        }
        if (!DmShared.xNull(mrTemp.getPan()).trim().equalsIgnoreCase("")) {
            if (DmShared.xNull(mrTemp.getPan()).length() != 10) {
                throw new DmException(1000, "PAN should be of 10(Ten) characters.");
            } else {
                DmShared.isValidPanNo(mrTemp.getPan(), 1000);
            }
        }
        if (DmShared.xNull(mrTemp.getPartyType()).equalsIgnoreCase((DmEnum.DmPartyType.Registered.getId()))
                || DmShared.xNull(mrTemp.getPartyType()).equalsIgnoreCase((DmEnum.DmPartyType.Composite.getId()))) {
            DmShared.isValidText(mrTemp.getGsTin(), "GSTIN No.", 700);
            DmShared.isValidText(mrTemp.getGsTINDate(), "GSTIN Date", 800);
        }
        if (!DmShared.xNull(mrTemp.getGsTin()).equalsIgnoreCase("")) {
            if (DmShared.xNull(mrTemp.getPan()).trim().equalsIgnoreCase("")) {
                throw new DmException(1000, "Please fill the PAN No.");
            } else {
                DmShared.isValidGSTIN(mrTemp.getStateNumericCode(), mrTemp.getPan(), mrTemp.getGsTin(), 700);
            }
        }
        if (DmShared.xNull(mrTemp.getPartyType()).equalsIgnoreCase((DmEnum.DmPartyType.Unregistered.getId()))) {
            if (!DmShared.xNull(mrTemp.getGsTin()).equalsIgnoreCase("") || !DmShared.xNull(mrTemp.getGsTINDate()).equalsIgnoreCase("")) {
                throw new DmException(850, "In case of unregistered party type, you should not fill GSTIN No. and GSTIN Date.");
            }
            if (mrTemp.isGstApplicable() == true) {
                if (DmShared.xNull(mrTemp.getAadhar()).equalsIgnoreCase("")) {
                    if (DmShared.xNull(mrTemp.getPan()).equalsIgnoreCase("")) {
                        throw new DmException(900, "In case of unregistered party type AADHAAR No. or PAN No. is mandatory.");
                    }
                }
            }
        }
        if (!DmShared.xNull(mrTemp.getGsTINDate()).equalsIgnoreCase("")) {
            if (DmShared.convertDate(mrTemp.getGsTINDate()).getTime() > (DmShared.convertDate(mrTemp.getCurrentDate())).getTime()) {
                throw new DmException(800, "GSTIN Date should not be greater than current date.");
            }
        }
        if (!DmShared.xNull(mrTemp.getAadhar()).equalsIgnoreCase("")) {
            if (DmShared.xNull(mrTemp.getAadhar()).length() != 12) {
                throw new DmException(925, "AADHAAR No. should be 12 digit.");
            }
        }
    }
}
```

## Server `ManufacturerMaster_SrvImpl.java` (1346 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getConnectionMongo`, `DmServer.getFieldUDT`, `DmServer.getSerialNoBigInt`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnFileDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteGSTIN`, `DmServer.getSsnUserName`, `DmServer.getUserWithDateTime`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `Select sg.SubCode As SearchCode, sg.Name From SubGroup sg Left Join subgroupNature sgn on sg.filterNature=sgn.code where sgn.manualCode='MF' Order By Name`

### `getAccountNameSuggestion`
`public DmResultPageing<List<String>> getAccountNameSuggestion(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select sg.Name as NAME From SubGroup sg Left Join subgroupNature sgn on sg.filterNature=sgn.code where sgn.manualCode='MF'`

### `getMoveRec`
`public DmResult<ManufacturerMaster_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT SG.SubCode,SG.Initial AS InitialName,Sg.Name AS CustomerName,SG.STNo,SG.IECCode,SG.isDuplicateTIN,SG.DisplayName, SG.GroupCode,AG.GroupName, SG.ManualCode, SG.Nature as NatureCode , sgn1.Name as NatureName,SG.Add1,SG.Add2, Sg.CityCode,C.cityName, Ctry.Name AS CountryName, SG.PIN, SG.Phone, SG.Mobile,SG.Fax,SG.ContactPerson,CCM.Name AS CCName,SG.CostCenter, SG.remark,SG.PAN,SG.CSTNo,SG.EMail,SG.TINNo,SG.CreditLimit,SG.DueDays, SG.PartyType,SG.ECCCode,SG.Excise,SG.Range,SG.Division, SG.TDS_Catg,TC.Name AS TCName,SG.LedgerGroup,LG.Name AS LGName,SG2.FatherName, SG.Zone,ZM.Name AS ZMName,SG.Distributor,SG1.Name AS DName, SG.PolicyNo,SG.U_Name, SG.preparedBy,SG.preparedDt,SG.u_EntDt, SG.isActive ,sm.name as stateName,sm.numericCode,SG.aadhaarNo,SG.gsTinDate,SG.gsTIN FROM SubGroup SG LEFT JOIN SubGroup2 SG2 ON SG2.SubCode=SG.SubCode LEFT JOIN AcGroup AG ON AG.GroupCode=SG.GroupCode LEFT JOIN City C ON C.CityCode=SG.CityCode LEFT JOIN Country Ctry ON C.countryCode=Ctry.Code LEFT Join stateMAst as sm on C.stateCode=sm.Code LEFT JOIN CostCenterMast CCM ON CCM.Code=SG.CostCenter LEFT JOIN TDSCat TC ON TC.Code=SG.TDS_Catg LEFT JOIN LedgerGroup LG ON LG.Code=SG.LedgerGroup LEFT JOIN ZoneMast ZM ON ZM.Code=SG.Zone Left Join subgroupNature sgn on SG.filterNature=sgn.code Left Join subgroupNature sgn1 on SG.Nature=sgn1.code LEFT JOIN SubGroup SG1 ON SG1.SubCode=SG.Distributor WHERE SG.SubCode=:code and sgn.manualCode='MF'`
- SQL 2: `SELECT subCode,sNo,bankName,accountNo,branch,swiftCode,isDefault,ifscCode,branchAddress FROM bankDetail WHERE subCode = :code ORDER BY sNo`
- Params: `:code` ← `DmShared.iNull(strKey).toString()`; `:code` ← `strKey`

### `saveRecord`
`public DmResult<String> saveRecord(ManufacturerMaster_Mdl mrTemp)`

- SQL 1: `Select Count(*) As Cnt From SubGroup Where Name = :name and SubCode<> :subCode`
- SQL 2: `SELECT vt.v_Type FROM voucher_type vt WHERE vt.category = ' {…} '`
- SQL 3: `Select Count(*) As Cnt From SubGroup where ManualCode = :manualCode and SubCode<> :subCode`
- SQL 4: `Insert Into SubGroup(SubCode,Name,GroupCode,ManualCode,Nature,Add1,Add2,CityCode,PIN,Phone,Mobile,FAX,ContactPerson,Remark,isDuplicateTIN,PolicyNo,preparedDt,CostCenter,PAN,CSTNo,EMail,TINNo,CreditLimit,DueDays,PartyType,ECCCode,Excise,Range,Division,STNo,IECCode,TDS_Catg,Zone,LedgerGroup,Distributor,Site_Code,DisplayName,Initial, preparedBy,filterNature,aadhaarNo,gsTinDate,gsTIN) Values (:subCode, :name, :groupCode, :manualCode, :nature, :add1, :add2, :cityCode, :pin, :phone, :mobile, :fax, :contactPerson, :remark, :duplicateTIN, :policyNo, :preparedDt, :costCenter, :pan, :cstNo, :email,:tinNo, :creditLimit, :dueDays, :partyType, :eccCode, :Excise, :range, :division, :stNo, :iecCode,:tdsCatg, :zone, :ledgerGroup, :distributor, :siteCode, :displayName, :initial, :preparedBy,:filterNature,:aadhaarNo,:gsTinDate,:gsTIN)`
- SQL 5: `update SubGroup Set Name=:name,GroupCode=:groupCode,ManualCode=:ManualCode,Nature=:Nature,Add1=:Add1,Add2=:Add2,CityCode=:CityCode,PIN=:pin,Phone=:Phone,Mobile=:Mobile,FAX=:FAX,ContactPerson=:ContactPerson,Remark=:Remark,CostCenter=:CostCenter,U_Name=:U_Name,U_EntDt=:uEntDt,PAN=:PAN,CSTNo=:CSTNo,STNo=:STNo,PolicyNo=:PolicyNo,EMail=:EMail,TINNo=:TINNo,PartyType=:PartyType,ECCCode=:ECCCode,Excise=:Excise,Range=:Range,Division=:Division,IECCode=:IECCode,CreditLimit=:CreditLimit,DueDays=:DueDays,Zone=:Zone,LedgerGroup=:LedgerGroup,Distributor=:Distributor,DisplayName = :DisplayName,Initial=:Initial,transferDt=:transferDt,aadhaarNo=:aadhaarNo, gsTIN=:gsTIN,gsTinDate=:gsTinDate, TDS_Catg=:TDSCatg Where SubCode=:SubCode`
- SQL 6: `INSERT INTO subgroupsitelist (subCode, site_Code) VALUES (:subCode, :siteCode) ;`
- SQL 7: `Select Count(*) As Cnt From SubGroup2 Where SubCode= :subCode`
- SQL 8: `update SubGroup2 Set FatherName=:fatherName where SubCode=:subCode`
- Params: `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:subCode` ← `mrTemp.getSharedKeyFieldCode()`; `:manualCode` ← `mrTemp.getManualCode()`; `:subCode` ← `mrTemp.getSharedKeyFieldCode()`; `:tinNo` ← `mrTemp.getTin()`; `:subCode` ← `mrTemp.getSharedKeyFieldCode()`; `:subCode` ← `mrTemp.getSharedKeyFieldCode()`; `:name` ← `mrTemp.getSharedKeyFieldName()`; `:groupCode` ← `mrTemp.getAccGroup()`; `:manualCode` ← `mrTemp.getManualCode()`; `:nature` ← `mrTemp.getNatureCode()`; `:add1` ← `mrTemp.getAddress()`; `:add2` ← `mrTemp.getAddress1()`; `:filterNature` ← `DmShared.foriegnKey(filterNature)`; `:cityCode` ← `DmShared.foriegnKey(mrTemp.getCity())`; `:pin` ← `mrTemp.getPin()`; `:phone` ← `mrTemp.getPhoneNo()`; `:mobile` ← `mrTemp.getMobileNo()`; `:fax` ← `mrTemp.getFaxNo()`; `:contactPerson` ← `mrTemp.getContactPerson()`; `:remark` ← `mrTemp.getRemark()`; `:duplicateTIN` ← `mrTemp.getDuplicate()`; `:policyNo` ← `mrTemp.getPolicyNo()`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:costCenter` ← `DmShared.foriegnKey(mrTemp.getCostCenter())`; `:pan` ← `mrTemp.getPan().toUpperCase()`; `:cstNo` ← `mrTemp.getCstNo()`; `:email` ← `mrTemp.getEmail()`; `:tinNo` ← `mrTemp.getTin()`; `:creditLimit` ← `DmServer.vNull(mrTemp.getCreditLimit(), "0.00")`; `:dueDays` ← `DmServer.vNull(mrTemp.getDueDays(), "0")`; `:partyType` ← `mrTemp.getPartyType()`; `:eccCode` ← `mrTemp.getEccNo()`; `:Excise` ← `mrTemp.getExciseNo()`; `:range` ← `mrTemp.getRange()`; `:division` ← `mrTemp.getDivision()`; `:stNo` ← `mrTemp.getStno()`; `:iecCode` ← `mrTemp.getIecNo()`; `:tdsCatg` ← `DmShared.foriegnKey(mrTemp.getTdsCategory())`; `:zone` ← `DmShared.foriegnKey(mrTemp.getZone())`; `:ledgerGroup` ← `DmShared.foriegnKey(mrTemp.getLedgerGroup())`; `:distributor` ← `DmShared.foriegnKey(mrTemp.getReferencedTo())`; `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:displayName` ← `mrTemp.getDisplayName()`; `:initial` ← `mrTemp.getAccountNamePrefix()`; `:aadhaarNo` ← `DmShared.xNull(mrTemp.getAadhar())`; `:gsTinDate` ← `DmServer.convertDate(mrTemp.getGsTINDate())`; `:gsTIN` ← `DmShared.xNull(mrTemp.getGsTin().toUpperCase())`; `:name` ← `mrTemp.getSharedKeyFieldName()`; `:groupCode` ← `DmShared.foriegnKey(mrTemp.getAccGroup())`; `:ManualCode` ← `mrTemp.getManualCode()`; `:Nature` ← `DmShared.foriegnKey(mrTemp.getNatureCode())`; `:Add1` ← `mrTemp.getAddress()`; `:Add2` ← `mrTemp.getAddress1()`; `:CityCode` ← `DmShared.foriegnKey(mrTemp.getCity())`; `:pin` ← `mrTemp.getPin()`; `:Phone` ← `mrTemp.getPhoneNo()`; `:Mobile` ← `mrTemp.getMobileNo()`; `:FAX` ← `mrTemp.getFaxNo()`; `:ContactPerson` ← `mrTemp.getContactPerson()`; `:Remark` ← `mrTemp.getRemark()`; `:CostCenter` ← `DmShared.foriegnKey(mrTemp.getCostCenter())`; `:U_Name` ← `DmServer.getSsnUserName(this)`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:PAN` ← `mrTemp.getPan().toUpperCase()`; `:CSTNo` ← `mrTemp.getCstNo()`; `:STNo` ← `mrTemp.getStno()`; `:PolicyNo` ← `mrTemp.getPolicyNo()`; `:EMail` ← `mrTemp.getEmail()`; `:TINNo` ← `mrTemp.getTin()`; `:PartyType` ← `mrTemp.getPartyType()`; `:ECCCode` ← `mrTemp.getEccNo()`; `:Excise` ← `mrTemp.getExciseNo()`; `:Range` ← `mrTemp.getRange()`; `:Division` ← `mrTemp.getDivision()`; `:IECCode` ← `mrTemp.getIecNo()`; `:CreditLimit` ← `DmServer.vNull(mrTemp.getCreditLimit(), "0.00")`; `:DueDays` ← `DmServer.vNull(mrTemp.getDueDays(), "0")`; `:Zone` ← `DmShared.foriegnKey(mrTemp.getZone())`; `:LedgerGroup` ← `DmShared.foriegnKey(mrTemp.getLedgerGroup())`; `:Distributor` ← `DmShared.foriegnKey(mrTemp.getReferencedTo())`; `:DisplayName` ← `mrTemp.getDisplayName()`; `:Initial` ← `mrTemp.getAccountNamePrefix()`; `:transferDt` ← `null`; `:TDSCatg` ← `DmShared.foriegnKey(mrTemp.getTdsCategory())`; `:SubCode` ← `mrTemp.getSharedKeyFieldCode()`; `:aadhaarNo` ← `DmShared.xNull(mrTemp.getAadhar())`; `:gsTinDate` ← `DmServer.convertDate(mrTemp.getGsTINDate())`; `:gsTIN` ← `DmShared.xNull(mrTemp.getGsTin().toUpperCase())`
- Errors: "Invalid key." | "TIN Should Be Greater Than 11(Eleven) Characters." | "PAN Should Be Of 10(Ten) Characters." | "Duplicate Party Name Not Allowed !!!" | "Duplicate Manual Code Not Allowed !!!" | "Duplicate TIN Not Allowed !!!" | "Manual Code is mandatory."
- Other calls: `DmFileManager_Server.manageLinkFileCollection`, `ManufacturerMaster_Vld.chkSave`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey)`

- SQL 1: `Delete From Subgroup2 Where SubCode = :SubCode`
- SQL 2: `Delete From subgroupsitelist Where subCode= :code`
- SQL 3: `Delete From Subgroup Where SubCode = :SubCode`
- SQL 4: `SELECT lfc.sNo, lfc.ruleCode FROM linkFileCollection lfc WHERE docid = :subCode`
- Params: `:SubCode` ← `strKey`; `:code` ← `strKey`; `:SubCode` ← `strKey`; `:subCode` ← `DmShared.iNull(strKey).toString()`

### `getTxtHAccountNamePrefix`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAccountNamePrefix(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT CODE,NAME FROM initialmast`

### `getTxtHAccountGroup`
`public DmResultPageing<List<TxtHAccountGroup_Mdl>> getTxtHAccountGroup(String manufacturerCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ag.groupCode as GroupCode, ag.groupName as GroupName, ag.groupUnder ,ag.Nature as NatureCode , sgn.name as NatureName, ag.GroupNature FROM acGroup ag left join subgroupNature sgn on ag.nature= sgn.code WHERE (ag.groupCode = :manufacturerCode OR ISNULL(ag.groupUnder,0) = :manufacturerCode)`
- Params: `:manufacturerCode` ← `DmShared.xNull(manufacturerCode)`

### `getTxtHCostCenter`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCostCenter(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT CODE ,NAME FROM CostCenterMast`

### `getTxtHCity`
`public DmResultPageing<List<TxtHCity_Mdl>> getTxtHCity(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select cityCode,cityName,stateCode,sm.name as stateName,sm.numericCode ,countryCode,ctr.name as countryName from city ct left join stateMast sm on ct.stateCode = sm.code left join country ctr on ct.countryCode = ctr.code`

### `getTxtHZone`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHZone(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ZM.Code, ZM.Name FROM zonemast ZM`

### `getTxtHTDSCategory`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTDSCategory(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT CODE,NAME FROM TdsCat`

### `getTxtHReferencedTo`
`public DmResultPageing<List<TxtHReferencedTo_Mdl>> getTxtHReferencedTo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT SG.SubCode,SG.Name AS NAME,isNull(CT.CityName,'') AS CityName,SG.ManualCode FROM SubGroup SG LEFT JOIN City CT ON CT.CityCode=SG.CityCode`

### `getTxtHDuplicate`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDuplicate(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHLadgerGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHLadgerGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select Code,Name From LedgerGroup`

### `getTxtHPartyType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHPartyType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `DmPartyType.values`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select SG.SubCode,'Account Master' As V_Type,SG.ManualCode As Manual_Code,SG.Name As Account_Name, SG.Name As displayName, CONCAT(isNull(SG.Add1,'') ,' ', isNull(SG.Add2,'')) AS Address,isNull(CityName,'') As City,isNull(Ctry.Name,'') As Country, isNull(ZN.Name,'') As Zone,SG.PIN,isNull(SG.Phone,'') As Phone,isNull(SG.Mobile,'') As Mobile, isNull(SG.EMail,'') As E_Mail,isNull(SG.Fax,'') As Fax,AG.GroupName As Ac_Group,sgn.name as Nature, isNull(CCM.Name,'') As Cost_Center,SG.TINNo As TIN, isNull(SG1.Name,'') As Distributor From Subgroup SG Left Join City C On C.CityCode=SG.CityCode Left Join Country Ctry On C.countryCode=Ctry.Code Left Join ZoneMast ZN On ZN.Code=SG.Zone Left Join AcGroup AG On AG.GroupCode=SG.GroupCode Left Join CostCenterMast CCM On CCM.Code=SG.CostCenter Left join subgroupNature sgn on SG.nature= sgn.code Left join subgroupNature sgn1 on SG.filterNature= sgn1.code Left Join SubGroup SG1 On SG1.SubCode=SG.Distributor where sgn1.manualCode='MF'`

### `getTxtHNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHNature(String manufacturerCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sgn.Code, sgn.Name as Nature FROM subgroupNature sgn left join acGroup ag on ag.nature= sgn.code WHERE (ag.groupCode = :manufacturerCode OR ISNULL(ag.groupUnder,0) = :manufacturerCode)`
- Params: `:manufacturerCode` ← `DmShared.xNull(manufacturerCode)`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`

- SQL 1: `select ec.acGroupManufacturerCode, acg7.groupName as acGroupManufacturerName from enviroCompany ec Left join acGroup acg7 on ec.acGroupManufacturerCode =acg7.groupCode`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmFileManagerMongo.server`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFileManager.client`, `dataman.dmToolkit.dmFileManager.server`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextArea.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.manageSite.client`, `dataman.dmToolkit.self.client`, `dataman.libraryMenu.self.shared`, `paperbiz.projectMenu.self.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (!DmShared.xNull(mrTemp.getTin()).equalsIgnoreCase("") && DmShared.xNull(mrTemp.getTin()).length() < 11)` | "TIN Should Be Greater Than 11(Eleven) Characters." |
| saveRecord | `if (!DmShared.xNull(mrTemp.getPan()).equalsIgnoreCase("") && DmShared.xNull(mrTemp.getPan()).length() != 10)` | "PAN Should Be Of 10(Ten) Characters." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Duplicate Party Name Not Allowed !!!" |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Duplicate Manual Code Not Allowed !!!" |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Duplicate TIN Not Allowed !!!" |
| saveRecord | `if (DmShared.xNull(mrTemp.getManualCode()).equalsIgnoreCase(""))` | "Manual Code is mandatory." |

## Notes (hand-written)
_No hand-written notes yet._
