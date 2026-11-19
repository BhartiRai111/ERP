# dm-accounts / accountMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/accounts/src/accountMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: SubGroup, subgroupsitelist
- Update tables: SubGroup
- Delete cascade (in order): bankDetail, Subgroup2, subgroupsitelist, Subgroup
- Duplicate checks on save:
  - `Select Count(*) As Cnt From SubGroup Where Name = :name and SubCode<> :subCode`
  - `Select Count(*) As Cnt From SubGroup where ManualCode = :manualCode and SubCode<> :subCode`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Account name (Text); Display name (Text); A/c group (Text); Nature (Text); TCS Active (Text); Active (Text); Address (Text); City (Text); GSTIN No. (Text); GSTIN Date (Text); rule: PAN should be of 10(Ten) characters.; rule: For defining GST detail, you should have to fill Party Type first.; rule: Please fill the PAN; rule: In case of unregistered party type you should not fill GSTIN No. and GSTIN Date.; rule: Party type is mandatory; rule:  TransporterId is mandatory; rule: GSTIN Date should not be greater than current date.; rule: AADHAAR no. should be of 12 digits.
- Lookups/suggestions: getTxtHAccountNamePrefix, getTxtHAccountGroup, getTxtHCostCenter, getTxtHCity, getTxtHZone, getTxtHLocation, getTxtHTDSCategory, getTxtHReferencedTo, getTxtHLadgerGroup, getTxtHPartyType, getTxtHNature, getTxtHYesNo
- CSV export: yes

## Screen spec (auto-extracted from client)
### Screen `AccountMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Account Name | DmTextHelp | `txtHAccountNamePrefix` | `KeyField_PrpAcs, 7, false` |
| 2 | - | DmSuggestBox | `txtName` | `100, 10, true, "NAME", DmDataType.STRING` |
| 3 | Display Name | DmTextBox | `txtDisplayName` | `100, true` |
| 4 | Legal Name | DmTextBox | `txtLegalName` | `100, false` |
| 5 | Trade Name | DmTextBox | `txtTradeName` | `100, false` |
| 6 | Manual Code | DmTextBox | `txtManualCode` | `25, false` |
| 7 | A/c Group | DmTextHelp | `txtHAccountGroup` | `TxtHAccountGroup, 6, true` |
| 8 | Debit A/c Group | DmTextHelp | `txtHDebitAcGroup` | `TxtHDebitAcGroup, 6, false` |
| 9 | Credit A/c Group | DmTextHelp | `txtHCreditAcGroup` | `TxtHCreditAcGroup, 6, false` |
| 10 | Nature | DmTextHelp | `txtHNature` | `com.google.gwt.core.client.KeyField_PrpAcs, 10, true` |
| 11 | Cost Center | DmTextHelp | `txtHCostCenter` | `KeyField_PrpAcs, 4` |
| 12 | Address | DmTextBox | `txtAddress` | `100, false` |
| 13 | - | DmTextBox | `txtAddress1` | `100, false` |
| 14 | City | DmTextHelp | `txtHCity` | `TxtHCity, 4, false` |
| 15 | State | DmTextBox | `txtState` | `100, false` |
| 16 | Pin | DmTextBoxNumeric | `txtPinCode` | `0, false, 6, false` |
| 17 | Zone | DmTextHelp | `txtHZone` | `KeyField_PrpAcs, 4` |
| 18 | Party Type (GST) | DmTextHelp | `txtHPartyType` | `KeyField_PrpAcs, 4, true, false` |
| 19 | GSTIN | DmTextBox | `txtgsTIN` | `15, false` |
| 20 | GSTIN Date | DmTextBox | `txtGstDate` | `11, false` |
| 21 | Aadhaar No. | DmTextBoxNumeric | `txtAadhar` | `0, false, 12, false` |
| 22 | Pan No. | DmTextBox | `txtPAN` | `10, false` |
| 23 | Transporter ID | DmTextBox | `txtTransporterId` | `15, false` |
| 24 | Fax No. | DmTextBox | `txtFaxNo` | `35, false` |
| 25 | Phone No. | DmTextBox | `txtPhoneNo` | `35, false` |
| 26 | Mobile No. | DmTextBox | `txtMobileNo` | `35, false` |
| 27 | EMail | DmTextBox | `txtEMail` | `250, false` |
| 28 | TCS Active | DmTextHelp | `txtHIsTcsActive` | `com.google.gwt.core.client.KeyField_PrpAcs, 4, true, false` |
| 29 | TCS Category | DmTextHelp | `txtHTCSCategory` | `KeyField_PrpAcs, 4` |
| 30 | TDS Category | DmTextHelp | `txtHTDSCategory` | `KeyField_PrpAcs, 4` |
| 31 | Referenced To | DmTextHelp | `txtHReferencedTo` | `TxtHReferencedTo, 3` |
| 32 | Credit Limit | DmTextBoxNumeric | `txtCreditLimit` | `DmTextBoxNumeric.DecimalNature.DEFAULT, false, 10, false` |
| 33 | Credit Period | DmTextBoxNumeric | `txtDueDays` | `0, false, 5, false` |
| 34 | Interest Rate | DmTextBoxNumeric | `txtinterestRate` | `4, false, 8, false` |
| 35 | IEC No. | DmTextBox | `txtIECNo` | `35, false` |
| 36 | Ledger Group | DmTextHelp | `txtHLedgerGroup` | `KeyField_PrpAcs, 4` |
| 37 | Remark | DmTextArea | `txtRemark` | `250, false` |

Other widgets (not in tab list): `txtHActive` (DmTextHelp), `dmFind` (DmFind), `grdBankDetail` (DmGrid), `grdContactPerson` (DmGrid), `grdAdditionalPlaceDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHAccountNamePrefix`: Name [Text, w0, NAME]
- `txtHAccountGroup`: Group [Text, w0, GroupName]; Nature [Text, w150, NatureName]
- `txtHDebitAcGroup`: Group [Text, w0, GroupName]; Nature [Text, w150, NatureName]
- `txtHCreditAcGroup`: Group [Text, w0, GroupName]; Nature [Text, w150, NatureName]
- `txtHCostCenter`: Name [Text, w0, NAME]
- `txtHCity`: City [Text, w0, CityName]; State [Text, w100, stateName]; Country [Text, w100, countryName]
- `txtHZone`: Zone [Text, w0, NAME]
- `txtHNature`: Name [Text, w1, Nature]
- `txtHTDSCategory`: TDS Category [Text, w0, NAME]
- `txtHReferencedTo`: Name [Text, w0, NAME]; City [Text, w150, CityName]; Code [Text, w150, ManualCode]
- `txtHLedgerGroup`: Ledger Group [Text, w0, NAME]
- `txtHPartyType`: Type [Text, w0, Type]
- `txtHIsTcsActive`: Name [Text, w0, Name]
- `txtHTCSCategory`: TCS Category [Text, w0, NAME]
- `txtHActive`: Status [Text, w250]
- `dmFind`: Account Name [Text, w200, Account_Name]; Display Name [Text, w200, displayName]; Manual Code [Text, w100, Manual_Code]; Account Group [Text, w150, Ac_Group]; Nature [Text, w100, Nature]; Address [Text, w150, Address]; City [Text, w100, City]; Country [Text, w100, Country]; Zone [Text, w100, Zone]; PIN [Text, w100, Pin]; Phone [Text, w120, Phone]; Mobile [Text, w120, Mobile]; Email [Text, w150, E_Mail]; FAX [Text, w150, Fax]; Cost Center [Text, w100, Cost_Center]; TIN [Text, w100, TIN]; Distributor [Text, w100, Distributor]; Active [Text, w100, active]
- `grdBankDetail`: Account No. [Text, w200]; Bank [Text, w180]; Branch [Text, w160]; Branch Address [Text, w250]; IFSC Code [Text, w100]; Swift No. [Text, w100]; Default [Text, w80]
- `grdContactPerson`: Contact Person [Text, w350]; Mobile No [Text, w180]; LandLine No [Text, w150]; Designation [Text, w250]; Default [Text, w80]
- `grdAdditionalPlaceDetail`: Default [Text, w55]; Place [Text, w150]; Address 1 [Text, w150]; Address 2 [Text, w200]; City [Text, w130]; State [Text, w130]; Country [Text, w200]; Mobile [Text, w130]; Email [Text, w130]; Pin [Text, w130]

**Lookup / suggestion / find sources (server RPC called):**
- `txtName` → `getAccountNameSuggestion`
- `txtHAccountNamePrefix` → `getTxtHAccountNamePrefix`
- `txtHAccountGroup` → `getTxtHAccountGroup`
- `txtHDebitAcGroup` → `getTxtHAccountGroup`
- `txtHCreditAcGroup` → `getTxtHAccountGroup`
- `txtHCostCenter` → `getTxtHCostCenter`
- `txtHCity` → `getTxtHCity`
- `txtHZone` → `getTxtHZone`
- `txtHTDSCategory` → `getTxtHTDSCategory`
- `txtHReferencedTo` → `getTxtHReferencedTo`
- `txtHLedgerGroup` → `getTxtHLadgerGroup`
- `txtHPartyType` → `getTxtHPartyType`
- `txtHNature` → `getTxtHNature`
- `txtHTCSCategory` → `getTxtHTDSCategory`
- `txtHIsTcsActive` → `getTxtHYesNo`
- `txtHActive` → `getTxtHYesNo`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `AccountNamePrefix` ← DmShared.xNull(txtHAccountNamePrefix.getText())
- `ManualCode` ← DmShared.removeSpace(DmShared.xNull(txtManualCode.getText()))
- `SharedKeyFieldName` ← DmShared.removeSpace(DmShared.xNull(txtName.getText()))
- `DisplayName` ← DmShared.removeSpace(DmShared.xNull(txtDisplayName.getText()))
- `LegalName` ← DmShared.removeSpace(DmShared.xNull(txtLegalName.getText()))
- `TradeName` ← DmShared.removeSpace(DmShared.xNull(txtTradeName.getText()))
- `TransportId` ← DmShared.xNull(txtTransporterId.getText())
- `AccGroup` ← DmShared.xNull(txtHAccountGroup.getTag())
- `AccGroupName` ← DmShared.xNull(txtHAccountGroup.getText())
- `DebitAcGroup` ← DmShared.xNull(txtHDebitAcGroup.getTag())
- `DebitAcGroupName` ← DmShared.xNull(txtHDebitAcGroup.getText())
- `CreditAcGroup` ← DmShared.xNull(txtHCreditAcGroup.getTag())
- `CreditAcGroupName` ← DmShared.xNull(txtHCreditAcGroup.getText())
- `NatureCode` ← DmShared.xNull(txtHNature.getTag())
- `NatureName` ← DmShared.xNull(txtHNature.getText())
- `CostCenter` ← DmShared.xNull(txtHCostCenter.getTag())
- `CostCenterName` ← DmShared.xNull(txtHCostCenter.getText())
- `Address` ← DmShared.xNull(txtAddress.getText())
- `Address1` ← DmShared.xNull(txtAddress1.getText())
- `City` ← DmShared.xNull(txtHCity.getTag())
- `CityName` ← DmShared.xNull(txtHCity.getText())
- `StateNumericCode` ← DmShared.xNull(txtState.getTag())
- `StateName` ← DmShared.xNull(txtState.getText())
- `Zone` ← DmShared.xNull(txtHZone.getTag())
- `ZoneName` ← DmShared.xNull(txtHZone.getText())
- `Pin` ← DmShared.xNull(txtPinCode.getText())
- `PhoneNo` ← DmShared.xNull(txtPhoneNo.getText())
- `MobileNo` ← DmShared.xNull(txtMobileNo.getText())
- `FaxNo` ← DmShared.xNull(txtFaxNo.getText())
- `Email` ← DmShared.xNull(txtEMail.getText())
- `TdsCategory` ← DmShared.xNull(txtHTDSCategory.getTag())
- `TdsCategoryName` ← DmShared.xNull(txtHTDSCategory.getText())
- `ReferencedTo` ← DmShared.xNull(txtHReferencedTo.getTag())
- `ReferencedToName` ← DmShared.xNull(txtHReferencedTo.getText())
- `Pan` ← DmShared.xNull(txtPAN.getText())
- `CreditLimit` ← DmClient.vNull(txtCreditLimit.getText(), "0.00")
- `DueDays` ← DmClient.vNull(txtDueDays.getText(), "0")
- `IecNo` ← DmShared.xNull(txtIECNo.getText())
- `LedgerGroup` ← DmShared.xNull(txtHLedgerGroup.getTag())
- `LedgerGroupName` ← DmShared.xNull(txtHLedgerGroup.getText())
- `PartyType` ← DmShared.xNull(txtHPartyType.getTag())
- `Remark` ← DmShared.xNull(txtRemark.getText())
- `GsTIN` ← DmShared.xNull(txtgsTIN.getText())
- `Aadhaar` ← DmShared.xNull(txtAadhar.getText())
- `GsTINDate` ← DmShared.dNull(txtGstDate.getText())
- `TcsCategory` ← DmShared.xNull(txtHTCSCategory.getTag())
- `TcsCategoryName` ← DmShared.xNull(txtHTCSCategory.getText())
- `InterestRate` ← DmShared.xNull(txtinterestRate.getText())
- `IsTcsActiveTag` ← DmShared.xNull(txtHIsTcsActive.getTag())
- `IsTcsActiveText` ← DmShared.xNull(txtHIsTcsActive.getText())
- `IsActiveTag` ← DmShared.xNull(txtHActive.getTag())
- `IsActiveText` ← DmShared.xNull(txtHActive.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtHAccountNamePrefix.setText` ← data.getAccountNamePrefix()
- `txtHAccountNamePrefix.setTag` ← data.getAccountNamePrefix()
- `txtManualCode.setText` ← data.getManualCode()
- `txtName.setText` ← data.getSharedKeyFieldName()
- `txtDisplayName.setText` ← data.getDisplayName()
- `txtLegalName.setText` ← data.getLegalName()
- `txtTradeName.setText` ← data.getTradeName()
- `txtTransporterId.setText` ← data.getTransportId()
- `txtHAccountGroup.setText` ← data.getAccGroupName()
- `txtHAccountGroup.setTag` ← data.getAccGroup()
- `txtHAccountGroup.setTag2nd` ← data.getAccGroup()
- `txtHDebitAcGroup.setTag` ← data.getDebitAcGroup()
- `txtHDebitAcGroup.setText` ← data.getDebitAcGroupName()
- `txtHCreditAcGroup.setTag` ← data.getCreditAcGroup()
- `txtHCreditAcGroup.setText` ← data.getCreditAcGroupName()
- `txtHNature.setText` ← data.getNatureName()
- `txtHNature.setTag` ← data.getNatureCode()
- `txtHNature.setTag2nd` ← data.getNatureCode()
- `txtHCostCenter.setText` ← data.getCostCenterName()
- `txtHCostCenter.setTag` ← data.getCostCenter()
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
- `txtgsTIN.setText` ← data.getGsTIN()
- `txtHTCSCategory.setText` ← data.getTcsCategoryName()
- `txtHTCSCategory.setTag` ← data.getTcsCategory()
- `txtHIsTcsActive.setText` ← data.getIsTcsActiveText()
- `txtHIsTcsActive.setTag` ← data.getIsTcsActiveTag()
- `txtHTDSCategory.setText` ← data.getTdsCategoryName()
- `txtHTDSCategory.setTag` ← data.getTdsCategory()
- `txtHReferencedTo.setText` ← data.getReferencedToName()
- `txtHReferencedTo.setTag` ← data.getReferencedTo()
- `txtPAN.setText` ← data.getPan()
- `txtAadhar.setText` ← data.getAadhaar()
- `txtGstDate.setText` ← data.getGsTINDate()
- `txtCreditLimit.setText` ← String.valueOf(data.getCreditLimit())
- `txtinterestRate.setText` ← DmShared.xNull(data.getInterestRate())
- `txtDueDays.setText` ← String.valueOf(data.getDueDays())
- `txtIECNo.setText` ← data.getIecNo()
- `txtHLedgerGroup.setText` ← data.getLedgerGroupName()
- `txtHLedgerGroup.setTag` ← data.getLedgerGroup()
- `txtHPartyType.setTag` ← data.getPartyType()
- `txtRemark.setText` ← data.getRemark()
- `txtHActive.setTag` ← DmShared.xNull(data.getIsActiveTag())
- `txtHActive.setText` ← DmShared.xNull(data.getIsActiveText())
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtHActive.setEnabled(flag)`
- `txtState.setEnabled(false)`
- `txtManualCode.setEnabled(false)`
- `txtManualCode.setEnabled(true)`

**On New click:**
- `clear()`
- `txtHPartyType.setText(DmEnum.DmPartyType.Unregistered.getValue())`
- `txtHPartyType.setTag(DmEnum.DmPartyType.Unregistered.getId())`
- `txtHIsTcsActive.setTag("0")`
- `txtHIsTcsActive.setText("No")`
- `txtHActive.setTag("1")`
- `txtHActive.setText("Yes")`
- `txtHAccountNamePrefix.setFocus(true)`

**`manageDisplay()` logic (condensed code):**
```java
txtHActive.setEnabled(flag);
txtState.setEnabled(false);
txtManualCode.setEnabled(false);
manageTransporterControls();
if (topControl.getMode().equalsIgnoreCase("Edit")) {
txtManualCode.setEnabled(true);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
clear();
manageDisplay(true);
if (!defaultValue.isGstApplicable()) {
txtHPartyType.setText(DmEnum.DmPartyType.Unregistered.getValue());
txtHPartyType.setTag(DmEnum.DmPartyType.Unregistered.getId());
txtHIsTcsActive.setTag("0");
txtHIsTcsActive.setText("No");
txtHActive.setTag("1");
txtHActive.setText("Yes");
txtHAccountNamePrefix.setFocus(true);
...
```

`topControl.addEditHandeller` → `editClicked()`
```java
manageDisplay(true);
txtName.setFocus(true);
grdBankDetail.getGrid().getStore().add(new GrdBankDetail_Mdl());
grdContactPerson.getGrid().getStore().add(new GrdContactPerson_Mdl());
grdAdditionalPlaceDetail.getGrid().getStore().add(new GrdPlaceDetail_Mdl());
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

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(AccountMaster.this);
```

`modifyReason.addCallBack` → `onOk()`
```java
try {
reasonInfo_Mdl = modifyReason;
if (topControl.getMode().equalsIgnoreCase("Browse") && DmShared.iNull(topControl.getDocId()).longValue() > 0) {
topControlDelete(topControl.getDocId());
} else {
topControlSave();
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), (DmTextBox) null).show();
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
srvAsync.invokeRPC(blnCallBackWait).getTxtHAccountGroup("NA", helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<
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
if (!DmShared.xNull(txtHAccountGroup.getTag()).equalsIgnoreCase(DmShared.xNull(txtHAccountGroup.getTag2nd()))) {
resetAcGroup();
txtHAccountGroup.setTag2nd(DmShared.xNull(txtHAccountGroup.getTag()));
if (blnStatus && tRow != null) {
acGroupNatureCode = DmShared.iNull(tRow.getGroupNatureCode()).toString();
if (DmShared.xNull(txtHNature.getTag()).trim().equalsIgnoreCase("")) {
txtHNature.setTag(tRow.getNatureCode());
txtHNature.setTag2nd(tRow.getNatureCode());
txtHNature.setText(tRow.getNatureName());
natureManualCode = DmShared.xNull(tRow.getNatureManualCode());
...
```

`txtHDebitAcGroup.addTextHelpCallBack` → `populateData()`
```java
String contraHelpBalType = "";
if (DmShared.iNull(acGroupNatureCode).intValue() == 0
|| DmShared.iNull(acGroupNatureCode).intValue() == 4
|| DmShared.iNull(acGroupNatureCode).intValue() == 5) {
contraHelpBalType = "Dr";
srvAsync.invokeRPC(blnCallBackWait).getTxtHAccountGroup(contraHelpBalType, helper, blnToEndOfFile, new AsyncCallback<DmR
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDebitAcGroup).show();
public void onSuccess(DmResultPageing<List<TxtHAccountGroup_Mdl>> result) {
if (result.isStatus()) {
...
```

`txtHDebitAcGroup.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHDebitAcGroup.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDebitAcGroup).show();
```

`txtHCreditAcGroup.addTextHelpCallBack` → `populateData()`
```java
String contraHelpBalType = "";
if (DmShared.iNull(acGroupNatureCode).intValue() == 1
|| DmShared.iNull(acGroupNatureCode).intValue() == 2
|| DmShared.iNull(acGroupNatureCode).intValue() == 3) {
contraHelpBalType = "Cr";
srvAsync.invokeRPC(blnCallBackWait).getTxtHAccountGroup(contraHelpBalType, helper, blnToEndOfFile, new AsyncCallback<DmR
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHCreditAcGroup).show();
public void onSuccess(DmResultPageing<List<TxtHAccountGroup_Mdl>> result) {
if (result.isStatus()) {
...
```

`txtHCreditAcGroup.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHCreditAcGroup.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCreditAcGroup).show();
```

`txtHCostCenter.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHCostCenter(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Key
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
String St_Coun = DmShared.xNull(tRow.getStateName()) + " " + "-" + " " + DmShared.xNull(tRow.getCountryName());
txtState.setText(St_Coun);
txtState.setTag(DmShared.xNull(tRow.getStateNumericCode()));
txtState.setEnabled(false);
} else {
txtState.setText("");
txtState.setTag("");
```

`txtHZone.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHZone(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
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
srvAsync.invokeRPC(blnCallBackWait).getTxtHTDSCategory(helper, blnToEndOfFile, DmEnum.TDS_TCS_Type.TDS.getCode(), new As
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHTDSCategory).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
try {
if (result.isStatus()) {
txtHTDSCategory.setData(result.getData(), result.getPaging());
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), txtHTDSCategory).show();
```

**Top control options:** setRefreshRequired(false), setDisplay(true)

_Print permission "P" is removed for this screen (replace "P" with "*")._

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Confirm: "Are you sure? You want to export records."
- Alert: "Due days can't be greater then 32767"
- Alert: "Can be used in browse mode only."

## Files
| file | lines |
|---|---|
| client/AccountMaster.java | 1396 |
| client/AccountMaster_Dsg.java | 1366 |
| server/AccountMaster_SrvImpl.java | 1657 |
| shared/AccountMaster_Mdl.java | 551 |
| shared/AccountMaster_Srv.java | 51 |
| shared/AccountMaster_SrvAsync.java | 51 |
| shared/AccountMaster_Vld.java | 74 |
| shared/DefaultValue_Mdl.java | 22 |
| shared/Find_Mdl.java | 447 |
| shared/GrdBankDetail_Mdl.java | 77 |
| shared/GrdPlaceDetail_Mdl.java | 104 |
| shared/GrdTdsCategory_Mdl.java | 54 |
| shared/TxtHAccountGroup_Mdl.java | 49 |
| shared/TxtHCity_Mdl.java | 59 |
| shared/TxtHHSN_Mdl.java | 23 |
| shared/TxtHReferencedTo_Mdl.java | 44 |
| shared/TxtHVatClause_Mdl.java | 35 |

## UI labels / columns (client)
`Account Name`, `Display Name`, `Legal Name`, `Trade Name`, `Manual Code`, `A/c Group`, `Debit A/c Group`, `Credit A/c Group`, `Nature`, `Cost Center`, `Address`, `City`, `State`, `Pin`, `Zone`, `Party Type (GST)`, `GSTIN`, `GSTIN Date`, `Aadhaar No.`, `Pan No.`, `Transporter ID`, `Fax No.`, `Phone No.`, `Mobile No.`, `EMail`, `TCS Active`, `TCS Category`, `TDS Category`, `Referenced To`, `Credit Limit`, `Credit Period`, `Interest Rate`, `IEC No.`, `Ledger Group`, `Remark`, `Active`, `Bank Account Detail`, `Contact Person Detail`, `Additional Places of Business`

## Models (shared DTOs)
### TxtHReferencedTo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | CityName |
| String | ManualCode |

### GrdPlaceDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | address1 |
| String | address2 |
| String | cityName |
| String | stateName |
| String | countryName |
| String | mobile |
| String | pin |
| String | place |
| String | email |
| String | isDefault |

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

### TxtHHSN_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | hsn |

### DefaultValue_Mdl
| type | field |
|---|---|
| boolean | gstApplicable |

### AccountMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | AccountNamePrefix |
| String | ManualCode |
| String | DisplayName |
| String | LegalName |
| String | tradeName |
| String | transportId |
| String | AccGroup |
| String | AccGroupName |
| String | acGroupNatureCode |
| String | debitAcGroup |
| String | creditAcGroup |
| String | debitAcGroupName |
| String | creditAcGroupName |
| String | NatureCode |
| String | NatureName |
| String | natureManualCode |
| String | CostCenter |
| String | CostCenterName |
| String | Address |
| String | Address1 |
| String | City |
| String | CityName |
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
| String | Pan |
| String | CreditLimit |
| String | DueDays |
| String | IecNo |
| String | LedgerGroup |
| String | LedgerGroupName |
| String | PartyType |
| String | Remark |
| String | stateName |
| String | gsTIN |
| String | aadhaar |
| String | interestRate |
| String | gsTINDate |
| String | currentDate |
| String | stateNumericCode |
| boolean | gstApplicable |
| String | tcsCategory |
| String | tcsCategoryName |
| String | isTcsActiveTag |
| String | isTcsActiveText |
| String | isActiveTag |
| String | isActiveText |
| List<GrdBankDetail_Mdl> | grdBankDetail |
| List<GrdContactPerson_Mdl> | listContectPerson |
| List<GrdPlaceDetail_Mdl> | grdAdditionalPlaceDetail |
| ModifyReason_Mdl | reasonInfo_Mdl |

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
| String | LegalName |
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
| String | active |

### TxtHAccountGroup_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | natureCode |
| String | natureName |
| String | natureManualCode |
| String | groupNatureCode |

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
| getMoveRec | String strKey | DmResult<AccountMaster_Mdl> |
| saveRecord | AccountMaster_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl reasonInfo_Mdl | DmResult<String> |
| getTxtHAccountNamePrefix | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAccountGroup | String contraHelpBalType, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHAccountGroup_Mdl>> |
| getTxtHCostCenter | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHCity | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHCity_Mdl>> |
| getTxtHZone | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHLocation | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTDSCategory | DmHelper pageing, boolean blnToEndOfFile, String type | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHReferencedTo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHReferencedTo_Mdl>> |
| getTxtHLadgerGroup | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPartyType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getTxtHNature | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| exportCSV | String sessionId | DmResult<KeyFieldExtended> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| getTxtHYesNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `AccountMaster_Vld`
```java
public class AccountMaster_Vld {
    public static void chkSave(AccountMaster_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getSharedKeyFieldName(), "Account name", 100);
        DmShared.isValidText(mrTemp.getDisplayName(), "Display name", 200);
        DmShared.isValidText(mrTemp.getAccGroup(), "A/c group", 300);
        DmShared.isValidText(mrTemp.getNatureCode(), "Nature", 500);
        DmShared.isValidEMail(mrTemp.getEmail(), 400);
        DmShared.isValidText(mrTemp.getIsTcsActiveText(), "TCS Active", 1100);
        DmShared.isValidText(mrTemp.getIsActiveText(), "Active", 1110);
        if (!DmShared.xNull(mrTemp.getPan()).equalsIgnoreCase("")) {
            if (DmShared.xNull(mrTemp.getPan()).length() != 10) {
                throw new DmException(950, "PAN should be of 10(Ten) characters.");
            } else {
                DmShared.isValidPanNo(mrTemp.getPan(), 950);
            }
        }
        if (!DmShared.xNull(mrTemp.getPartyType()).equalsIgnoreCase("")) {
            if (DmShared.xNull(mrTemp.getPartyType()).equalsIgnoreCase((DmEnum.DmPartyType.Registered.getId()))
                    || DmShared.xNull(mrTemp.getPartyType()).equalsIgnoreCase((DmEnum.DmPartyType.Composite.getId()))) {
                DmShared.isValidText(mrTemp.getAddress(), "Address", 470);
                DmShared.isValidText(mrTemp.getCity(), "City", 450);
                DmShared.isValidText(mrTemp.getGsTIN(), "GSTIN No.", 700);
                DmShared.isValidText(mrTemp.getGsTINDate(), "GSTIN Date", 800);
            }
            if (!DmShared.xNull(mrTemp.getGsTIN()).equalsIgnoreCase("")
                    || !DmShared.dNull(mrTemp.getGsTINDate()).equalsIgnoreCase("")) {
                if (DmShared.xNull(mrTemp.getPartyType()).trim().equalsIgnoreCase("")) {
                    throw new DmException(600, "For defining GST detail, you should have to fill Party Type first.");
                } else if (DmShared.xNull(mrTemp.getPan()).trim().equalsIgnoreCase("")) {
                    throw new DmException(950, "Please fill the PAN");
                } else {
                    DmShared.isValidGSTIN(DmShared.xNull(mrTemp.getStateNumericCode()), mrTemp.getPan(), mrTemp.getGsTIN(), 700);
                }
            }
            if (DmShared.xNull(mrTemp.getPartyType()).equalsIgnoreCase((DmEnum.DmPartyType.Unregistered.getId()))) {
                if (!DmShared.xNull(mrTemp.getGsTIN()).equalsIgnoreCase("") || !DmShared.xNull(mrTemp.getGsTINDate()).equalsIgnoreCase("")) {
                    throw new DmException(850, "In case of unregistered party type you should not fill GSTIN No. and GSTIN Date.");
                }
            }
        } else {
            if (!DmShared.xNull(mrTemp.getGsTIN()).equalsIgnoreCase("") || !DmShared.xNull(mrTemp.getGsTINDate()).equalsIgnoreCase("")) {
                throw new DmException(1000, "Party type is mandatory");
            }
        }
        if (DmShared.xNull(mrTemp.getNatureManualCode()).trim().equalsIgnoreCase("TP")
                && (DmShared.xNull(mrTemp.getPartyType()).equalsIgnoreCase(DmEnum.DmPartyType.Unregistered.getId())
                || DmShared.xNull(mrTemp.getPartyType()).trim().equalsIgnoreCase(""))) {
            if (DmShared.xNull(mrTemp.getTransportId()).trim().equalsIgnoreCase("")) {
                throw new DmException(960, " TransporterId is mandatory");
            }
        }
        if (!DmShared.xNull(mrTemp.getGsTINDate()).equalsIgnoreCase("")) {
            if (DmShared.convertDate(mrTemp.getGsTINDate()).getTime() > (DmShared.convertDate(mrTemp.getCurrentDate())).getTime()) {
                throw new DmException(800, "GSTIN Date should not be greater than current date.");
            }
        }
        if (!DmShared.xNull(mrTemp.getAadhaar()).equalsIgnoreCase("")) {
            if (DmShared.xNull(mrTemp.getAadhaar()).length() != 12) {
                throw new DmException(900, "AADHAAR no. should be of 12 digits.");
            }
        }
    }
}
```

## Server `AccountMaster_SrvImpl.java` (1657 lines)
Helpers used: `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getFieldUDT`, `DmServer.getLocalIPAddresses`, `DmServer.getSerialNoBigInt`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteGSTIN`, `DmServer.getSsnUserName`, `DmServer.setSsnOldData`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.vNull`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `Select sg.SubCode As SearchCode, sg.Name From SubGroup sg Left Join subgroupNature sgn on sg.filterNature=sgn.code where sgn.manualCode='LG' Order By Name`

### `getAccountNameSuggestion`
`public DmResultPageing<List<String>> getAccountNameSuggestion(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select sg.Name as NAME From SubGroup sg Left Join subgroupNature sgn on sg.filterNature=sgn.code where sgn.manualCode='LG'`

### `getMoveRec`
`public DmResult<AccountMaster_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT SG.SubCode,SG.Initial AS InitialName,Sg.Name AS CustomerName , SG.IECCode,SG.isDuplicateTIN,SG.DisplayName,SG.LegalName , SG.acGroupDr,AGdr.GroupName as GroupNameDr,SG.acGroupCr,AGcr.GroupName as GroupNameCr , SG.IECCode,SG.isDuplicateTIN,SG.DisplayName,SG.LegalName, SG.GroupCode,AG.GroupName, ag.groupNature, SG.ManualCode, SG.Nature as NatureCode , sgn1.Name as NatureName, sgn1.manualCode as natureManualCode, SG.Add1,SG.Add2, Sg.CityCode,C.cityName, Ctry.Name AS CountryName, SG.PIN, SG.Phone, SG.Mobile,SG.Fax,CCM.Name AS CCName,SG.CostCenter,sm.name as stateName, SG.remark,SG.PAN,SG.CSTNo,SG.EMail,SG.TINNo,SG.CreditLimit,SG.DueDays, SG.PartyCat,SG.PartyType, SG.TDS_Catg,tds.Name AS TCName,SG.LedgerGroup,LG.Name AS LGName, SG.Zone,ZM.Name AS ZMName,SG.Distributor,SG1.Name AS DName ,SG.U_Name, SG.preparedBy, SG.preparedDt, sg.preparedByIP, sg.u_EntDt ,SG.isActive,SG.gsTIN,SG.aadhaarNo,SG.gsTinDate, sm.numericCode , SG.tcs_Catg, tcs.Name as TcsName , SG.isTcsActive ,SG.transporterId ,SG.interestRate ,SG.tradeName FROM SubGroup SG LEFT JOIN SubGroup2 SG2 ON SG2.SubCode=SG.SubCode LEFT JOIN DesignationMast DM ON DM.Code=SG2.DesignationCode LEFT JOIN AcGroup AG ON AG.GroupCode=SG.GroupCode LEFT JOIN AcGroup AGdr ON AGdr.GroupCode=SG.acGroupDr LEFT JOIN AcGroup AGcr ON AGcr.GroupCode=SG.acGroupCr LEFT JOIN City C ON C.CityCode=SG.CityCode LEFT JOIN Country Ctry ON C.countryCode=Ctry.Code LEFT JOIN stateMast sm ON C.stateCode=sm.Code LEFT JOIN CostCenterMast CCM ON CCM.Code=SG.CostCenter LEFT JOIN TDSCat tds ON tds.Code=SG.TDS_Catg LEFT JOIN TDSCat tcs ON tcs.Code=SG.tcs_Catg LEFT JOIN LedgerGroup LG ON LG.Code=SG.LedgerGroup LEFT JOIN ZoneMast ZM ON ZM.Code=SG.Zone Left Join subgroupNature sgn on SG.filterNature=sgn.code Left Join subgroupNature sgn1 on SG.Nature=sgn1.code LEFT JOIN SubGroup SG1 ON SG1.SubCode=SG.Distributor WHERE SG.SubCode=:code and sgn.manualCode='LG'`
- SQL 2: `SELECT subCode,sNo,bankName,accountNo,branch,swiftCode,isDefault,ifscCode,branchAddress FROM bankDetail WHERE subCode = :code ORDER BY sNo`
- SQL 3: `SELECT apb.subCode, apb.sNo, apb.place, apb.add1,add2, ct.cityName, sm.name as stateName, ctr.name as countryName , apb.pin, apb.mobile, apb.eMail, apb.isDefault FROM additionalPlaceOfBusiness apb left join city ct on ct.cityCode=apb.cityCode left join stateMast sm on ct.stateCode = sm.code left join country ctr on ct.countryCode = ctr.code WHERE apb.subCode = :code ORDER BY apb.sNo`
- SQL 4: `SELECT code,contactPerson,mobile,phone AS landLineNo,designation,isDefault FROM addressBook where subCode= :code order by contactPerson`
- Params: `:code` ← `DmShared.iNull(strKey).toString()`; `:code` ← `strKey`; `:code` ← `strKey`; `:code` ← `strKey`

### `saveRecord`
`public DmResult<String> saveRecord(AccountMaster_Mdl mrTemp)`

- SQL 1: `Select Count(*) As Cnt From SubGroup Where Name = :name and SubCode<> :subCode`
- SQL 2: `SELECT vt.v_Type FROM voucher_type vt WHERE vt.category = ' {…} '`
- SQL 3: `Select Count(*) As Cnt From SubGroup where ManualCode = :manualCode and SubCode<> :subCode`
- SQL 4: `Insert Into SubGroup(SubCode,Name,GroupCode,acGroupDr,acGroupCr,ManualCode,Nature,Add1,Add2,CityCode,PIN, Phone,Mobile,FAX,Remark,preparedDt,CostCenter,PAN,EMail, CreditLimit,DueDays,PartyType,IECCode,TDS_Catg,Zone,LedgerGroup, Distributor,Site_Code,DisplayName , LegalName ,Initial, preparedBy, preparedByIP , filterNature,gsTIN,aadhaarNo,gsTinDate ,tcs_Catg, isTcsActive , tradeName ,transporterId, isActive, interestRate ) Values (:subCode, :name, :groupCode, :acGroupDr, :acGroupCr, :manualCode, :nature , :add1, :add2, :cityCode, :pin, :phone, :mobile, :fax, :remark , :preparedDt, :costCenter, :pan, :email , :creditLimit, :dueDays, :partyType, :iecCode, :tdsCatg, :zone, :ledgerGroup, :distributor, :siteCode , :displayName , :legalName , :initial , :preparedBY, :preparedByIP, :filterNature ,:gsTIN,:aadhaarNo,:gsTinDate , :tcs_Catg, :isTcsActive ,:tradeName,:transporterId, :isActive, :interestRate )`
- SQL 5: `update SubGroup Set Name=:name, GroupCode=:groupCode, acGroupDr = :acGroupDr, acGroupCr = :acGroupCr, ManualCode=:ManualCode, Nature=:Nature, Add1=:Add1, Add2=:Add2, CityCode=:CityCode, PIN=:pin, Phone=:Phone, Mobile=:Mobile, FAX=:FAX, Remark=:Remark, CostCenter=:CostCenter, U_Name=:U_Name, U_EntDt=:U_EntDt, PAN=:PAN, EMail=:EMail, PartyType=:PartyType, IECCode=:IECCode, CreditLimit=:CreditLimit, DueDays=:DueDays, Zone=:Zone, LedgerGroup=:LedgerGroup, Distributor=:Distributor, DisplayName = :DisplayName, LegalName = :LegalName, Initial=:Initial, transferDt=:transferDt, TDS_Catg=:TDSCatg, gsTIN=:gsTIN, aadhaarNo=:aadhaarNo, gsTinDate=:gsTinDate , tcs_Catg = :tcs_Catg , isTcsActive = :isTcsActive , transporterId = :transporterId , tradeName = :tradeName , isActive = :isActive , interestRate = :interestRate Where SubCode=:SubCode`
- SQL 6: `INSERT INTO subgroupsitelist (subCode, site_Code) VALUES (:subCode, :siteCode) ;`
- Params: `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:subCode` ← `mrTemp.getSharedKeyFieldCode()`; `:manualCode` ← `mrTemp.getManualCode()`; `:subCode` ← `mrTemp.getSharedKeyFieldCode()`; `:subCode` ← `mrTemp.getSharedKeyFieldCode()`; `:name` ← `mrTemp.getSharedKeyFieldName()`; `:groupCode` ← `mrTemp.getAccGroup()`; `:acGroupDr` ← `DmShared.foriegnKey(mrTemp.getDebitAcGroup())`; `:acGroupCr` ← `DmShared.foriegnKey(mrTemp.getCreditAcGroup())`; `:manualCode` ← `mrTemp.getManualCode()`; `:nature` ← `mrTemp.getNatureCode()`; `:add1` ← `mrTemp.getAddress()`; `:add2` ← `mrTemp.getAddress1()`; `:filterNature` ← `DmShared.foriegnKey(filterNature)`; `:cityCode` ← `DmShared.foriegnKey(mrTemp.getCity())`; `:pin` ← `mrTemp.getPin()`; `:phone` ← `mrTemp.getPhoneNo()`; `:mobile` ← `mrTemp.getMobileNo()`; `:fax` ← `mrTemp.getFaxNo()`; `:remark` ← `mrTemp.getRemark()`; `:costCenter` ← `DmShared.foriegnKey(mrTemp.getCostCenter())`; `:pan` ← `mrTemp.getPan()`; `:email` ← `mrTemp.getEmail()`; `:creditLimit` ← `DmServer.vNull(mrTemp.getCreditLimit(), "0.00")`; `:dueDays` ← `DmServer.vNull(mrTemp.getDueDays(), "0")`; `:partyType` ← `mrTemp.getPartyType()`; `:iecCode` ← `mrTemp.getIecNo()`; `:tdsCatg` ← `DmShared.foriegnKey(mrTemp.getTdsCategory())`; `:zone` ← `DmShared.foriegnKey(mrTemp.getZone())`; `:ledgerGroup` ← `DmShared.foriegnKey(mrTemp.getLedgerGroup())`; `:distributor` ← `DmShared.foriegnKey(mrTemp.getReferencedTo())`; `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:displayName` ← `DmShared.xNull(mrTemp.getDisplayName())`; `:legalName` ← `DmShared.xNull(mrTemp.getLegalName())`; `:transporterId` ← `DmShared.xNull(mrTemp.getTransportId())`; `:tradeName` ← `DmShared.xNull(mrTemp.getTradeName())`; `:initial` ← `DmShared.xNull(mrTemp.getAccountNamePrefix())`; `:gsTIN` ← `DmShared.xNull(mrTemp.getGsTIN())`; `:aadhaarNo` ← `DmShared.xNull(mrTemp.getAadhaar())`; `:gsTinDate` ← `DmServer.convertDate(mrTemp.getGsTINDate())`; `:tcs_Catg` ← `DmShared.foriegnKey(mrTemp.getTcsCategory())`; `:isTcsActive` ← `DmShared.iNull(mrTemp.getIsTcsActiveTag()).toString()`; `:isActive` ← `DmShared.iNull(mrTemp.getIsActiveTag()).intValue()`; `:interestRate` ← `DmServer.vNull(mrTemp.getInterestRate())`; `:preparedBY` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `timestamp`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:name` ← `mrTemp.getSharedKeyFieldName()`; `:groupCode` ← `mrTemp.getAccGroup()`; `:acGroupCr` ← `DmShared.foriegnKey(mrTemp.getCreditAcGroup())`; `:acGroupDr` ← `DmShared.foriegnKey(mrTemp.getDebitAcGroup())`; `:ManualCode` ← `mrTemp.getManualCode()`; `:Nature` ← `mrTemp.getNatureCode()`; `:Add1` ← `mrTemp.getAddress()`; `:Add2` ← `mrTemp.getAddress1()`; `:CityCode` ← `DmShared.foriegnKey(mrTemp.getCity())`; `:pin` ← `mrTemp.getPin()`; `:Phone` ← `mrTemp.getPhoneNo()`; `:Mobile` ← `mrTemp.getMobileNo()`; `:FAX` ← `mrTemp.getFaxNo()`; `:Remark` ← `mrTemp.getRemark()`; `:CostCenter` ← `DmShared.foriegnKey(mrTemp.getCostCenter())`; `:U_Name` ← `DmServer.getSsnUserName(this)`; `:PAN` ← `mrTemp.getPan()`; `:EMail` ← `mrTemp.getEmail()`; `:PartyType` ← `mrTemp.getPartyType()`; `:IECCode` ← `mrTemp.getIecNo()`; `:CreditLimit` ← `DmServer.vNull(mrTemp.getCreditLimit(), "0.00")`; `:DueDays` ← `DmServer.vNull(mrTemp.getDueDays(), "0")`; `:Zone` ← `DmShared.foriegnKey(mrTemp.getZone())`; `:LedgerGroup` ← `DmShared.foriegnKey(mrTemp.getLedgerGroup())`; `:Distributor` ← `DmShared.foriegnKey(mrTemp.getReferencedTo())`; `:DisplayName` ← `mrTemp.getDisplayName()`; `:LegalName` ← `mrTemp.getLegalName()`; `:Initial` ← `mrTemp.getAccountNamePrefix()`; `:transferDt` ← `null`; `:TDSCatg` ← `DmShared.foriegnKey(mrTemp.getTdsCategory())`; `:gsTIN` ← `DmShared.xNull(mrTemp.getGsTIN())`; `:SubCode` ← `mrTemp.getSharedKeyFieldCode()`; `:aadhaarNo` ← `DmShared.xNull(mrTemp.getAadhaar())`; `:gsTinDate` ← `DmServer.convertDate(mrTemp.getGsTINDate())`; `:U_EntDt` ← `timestamp`; `:tcs_Catg` ← `DmShared.foriegnKey(mrTemp.getTcsCategory())`; `:isTcsActive` ← `DmShared.iNull(mrTemp.getIsTcsActiveTag()).toString()`; `:transporterId` ← `DmShared.xNull(mrTemp.getTransportId())`; `:tradeName` ← `DmShared.xNull(mrTemp.getTradeName())`; `:isActive` ← `DmShared.iNull(mrTemp.getIsActiveTag()).intValue()`; `:interestRate` ← `DmServer.vNull(mrTemp.getInterestRate())`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getSharedKeyFieldCode())`; `:siteCode` ← `DmShared.foriegnKey(SiteList)`
- Errors: "Invalid key." | "PAN Should Be Of 10(Ten) Characters." | "Duplicate Party Name Not Allowed !!!" | "Duplicate Manual Code Not Allowed !!!" | "Manual Code is mandatory."
- Other calls: `AccountMaster_Vld.chkSave`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl reasonInfo_Mdl)`

- SQL 1: `Delete From bankDetail Where SubCode = :SubCode`
- SQL 2: `Delete From Subgroup2 Where SubCode = :SubCode`
- SQL 3: `Delete From subgroupsitelist Where subCode= :code`
- SQL 4: `Delete From Subgroup Where SubCode = :SubCode`
- Params: `:SubCode` ← `strKey`; `:SubCode` ← `strKey`; `:code` ← `strKey`; `:SubCode` ← `strKey`

### `getTxtHAccountNamePrefix`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAccountNamePrefix(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT CODE,NAME FROM initialmast`

### `getTxtHAccountGroup`
`public DmResultPageing<List<TxtHAccountGroup_Mdl>> getTxtHAccountGroup(String contraHelpBalType, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ag.GroupCode,ag.GroupName , ag.Nature as NatureCode,sgn.name as NatureName , ag.GroupNature, sgn.manualCode as natureManualCode FROM AcGroup ag left join subgroupNature sgn on ag.nature= sgn.code where sgn.code not in( {…} ) {…}`

### `getTxtHCostCenter`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCostCenter(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT CODE ,NAME FROM CostCenterMast`

### `getTxtHCity`
`public DmResultPageing<List<TxtHCity_Mdl>> getTxtHCity(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT c.cityCode,c.cityName,c.stateCode,sm.name AS stateName,c.countryCode,cn.name AS countryName,sm.numericCode FROM city AS c LEFT JOIN stateMast AS sm ON c.stateCode = sm.code LEFT JOIN country AS cn ON c.countryCode = cn.Code`

### `getTxtHZone`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHZone(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ZM.Code, ZM.Name FROM zonemast ZM`

### `getTxtHLocation`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHLocation(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `With In State`

### `getTxtHTDSCategory`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTDSCategory(DmHelper helper, boolean blnToEndOfFile, String type)`

- SQL 1: `SELECT CODE,NAME FROM TdsCat where type = :type`
- Params: `:type` ← `DmShared.xNull(type)`

### `getTxtHReferencedTo`
`public DmResultPageing<List<TxtHReferencedTo_Mdl>> getTxtHReferencedTo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT SG.SubCode,SG.Name AS NAME,isNull(CT.CityName,'') AS CityName,SG.ManualCode FROM SubGroup SG LEFT JOIN City CT ON CT.CityCode=SG.CityCode`

### `getTxtHLadgerGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHLadgerGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select Code,Name From LedgerGroup`

### `getTxtHPartyType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHPartyType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `DmPartyType.values`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select SG.SubCode,'Account Master' As V_Type,SG.ManualCode As Manual_Code,SG.Name As Account_Name, SG.Name As displayName,SG.Name As legalName, CONCAT(isNull(SG.Add1,'') ,' ', isNull(SG.Add2,'')) AS Address,isNull(CityName,'') As City,isNull(Ctry.Name,'') As Country, isNull(ZN.Name,'') As Zone,SG.PIN,isNull(SG.Phone,'') As Phone,isNull(SG.Mobile,'') As Mobile, isNull(SG.EMail,'') As E_Mail,isNull(SG.Fax,'') As Fax,AG.GroupName As Ac_Group,sgn.name as Nature, isNull(CCM.Name,'') As Cost_Center,SG.TINNo As TIN, isNull(SG1.Name,'') As Distributor, CASE WHEN sg.isActive = 1 THEN 'Yes' ELSE 'No' END AS active From Subgroup SG Left Join City C On C.CityCode=SG.CityCode Left Join Country Ctry On C.countryCode=Ctry.Code Left Join ZoneMast ZN On ZN.Code=SG.Zone Left Join AcGroup AG On AG.GroupCode=SG.GroupCode Left Join CostCenterMast CCM On CCM.Code=SG.CostCenter Left join subgroupNature sgn on SG.nature= sgn.code Left join subgroupNature sgn1 on SG.filterNature= sgn1.code Left Join SubGroup SG1 On SG1.SubCode=SG.Distributor where sgn1.manualCode='LG'`

### `getTxtHNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHNature(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select sgn.Code ,sgn.Name as Nature, sgn.manualCode as natureManualCode From subGroupNature sgn where sgn.code not in( {…} )`

### `exportCSV`
`public DmResult<KeyFieldExtended> exportCSV(String sessionId)`

- SQL 1: `select sg.initial, sg.name, sg.displayName, sgn.name AS nature, ag.groupName, lg.name AS ledgerGroup, ct.cityName from subGroup sg left join subGroupNature sgn on sgn.code = sg.nature left join acGroup ag on ag.groupCode = sg.groupCode left join ledgerGroup lg on lg.code = sg.ledgerGroup left join city ct on ct.cityCode = sg.cityCode where sg.filterNature = :filterNature order by sg.name, sg.displayName`
- Params: `:filterNature` ← `DmServer.getCodeViaManualCode("LG", "manualCode", "subGroupNature", "code", cnTemp)`
- Errors: "Invalid request." | "File Name not given."
- Other calls: `DmImportData_Server.getCSVFormats`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`


### `getTxtHYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getRestrictedNatureParam`
`private void getRestrictedNatureParam(DmSQLWithParam sqlWithParam, Connection cnTemp)`

- Params: `:party` ← `DmServer.getCodeViaManualCode("PT", "manualCode", "subGroupNature", "code", cnTemp)`; `:customer` ← `DmServer.getCodeViaManualCode("CT", "manualCode", "subGroupNature", "code", cnTemp)`; `:employee` ← `DmServer.getCodeViaManualCode("EM", "manualCode", "subGroupNature", "code", cnTemp)`; `:supplier` ← `DmServer.getCodeViaManualCode("SU", "manualCode", "subGroupNature", "code", cnTemp)`; `:transporter` ← `DmServer.getCodeViaManualCode("TP", "manualCode", "subGroupNature", "code", cnTemp)`; `:department` ← `DmServer.getCodeViaManualCode("DP", "manualCode", "subGroupNature", "code", cnTemp)`; `:process` ← `DmServer.getCodeViaManualCode("PC", "manualCode", "subGroupNature", "code", cnTemp)`; `:common` ← `DmServer.getCodeViaManualCode("CM", "manualCode", "subGroupNature", "code", cnTemp)`; `:warehouse` ← `DmServer.getCodeViaManualCode("GD", "manualCode", "subGroupNature", "code", cnTemp)`

## Depends on (imports from other packages)
`dataman.accounts.addressBook.shared`, `dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.client`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmImportData.server`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextArea.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.manageSite.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.self.client`, `dataman.libraryMenu.self.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (!DmShared.xNull(mrTemp.getPan()).equalsIgnoreCase("") && DmShared.xNull(mrTemp.getPan()).length() != 10)` | "PAN Should Be Of 10(Ten) Characters." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Duplicate Party Name Not Allowed !!!" |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Duplicate Manual Code Not Allowed !!!" |
| saveRecord | `if (DmShared.xNull(mrTemp.getManualCode()).equalsIgnoreCase(""))` | "Manual Code is mandatory." |
| exportCSV | `if (!DmSession.getSessions().containsKey(sessionId))` | "Invalid request." |
| exportCSV | `if (DmShared.xNull(fileName).equalsIgnoreCase(""))` | "File Name not given." |

## Notes (hand-written)
_No hand-written notes yet._
