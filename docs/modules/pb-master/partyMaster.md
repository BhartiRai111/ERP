# pb-master / partyMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/master/src/partyMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: SubGroup, subgroupsitelist, SubGroup2
- Update tables: SubGroup, SubGroup2
- Delete cascade (in order): bankDetail, subgroupItemCategory, Subgroup2, subGroupAreaList, subgroupsitelist, Subgroup
- Duplicate checks on save:
  - `Select Count(*) As Cnt From SubGroup Where Name = :name and SubCode<> :subCode`
  - `Select Count(*) As Cnt From SubGroup where ManualCode = :manualCode and SubCode<> :subCode`
- "In use" checks before delete:
  - `select count(*) as cnt from addressBook where subCode = :subCode`
- Validations: Name (Text); Display name (Text); A/c group (Text); nature (Text); Address (Text); City (Amount); Party Type (Text); TCS Active (Text); Self Transport (Text); Active (Text); GSTIN No. (Text); GSTIN Date (Text); rule: PAN No should be 10 digit.; rule:  Transporter ID is mandatory; rule: Please fill the PAN No.; rule: In case of unregistered party type, you should not fill GSTIN No. and GSTIN Date.; rule: GSTIN Date should not be greater than current date.; rule: AADHAAR no. should be of 12 digits.
- Lookups/suggestions: getTxtHAccountNamePrefix, getTxtHAccountGroup, getTxtHNature, getTxtHCostCenter, getTxtHCity, getTxtHZone, getTxtHArea, getTxtHPartyCategory, getTxtHTDSCategory, getTxtHReferencedTo, getTxtHLedgerGroup, getTxtHPartyType, getTxtHItemCategory, getTxtHYesNo, getTxtHMsmeType, getTxtHSalesman, getTxtHRelationshipManager
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `ItemCategoryGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Item Category | DmTextHelp | `txtHItemCategory` | `KeyField_PrpAcs, 10` |

**Grid / popup columns** (header, width, db field):
- `txtHItemCategory`: Category [Text, w0, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHItemCategory` → `getTxtHItemCategory`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHItemCategory.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHItemCategory(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<K
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemCategory).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHItemCategory.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHItemCategory).show();
```

`txtHItemCategory.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemCategory.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHItemCategory).show();
```

### Screen `PartyMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmTermsConfig | `termsConfig` | `topControl, tabPanel, 3` |
| 2 | Name | DmTextHelp | `txtHAccountNamePrefix` | `KeyField_PrpAcs, 7, false` |
| 3 | - | DmSuggestBox | `txtName` | `100, 10, true, "NAME", DmDataType.STRING` |
| 4 | Display Name | DmTextBox | `txtDisplayName` | `100, true` |
| 5 | Legal Name | DmTextBox | `txtLegalName` | `100, true` |
| 6 | Trade Name | DmTextBox | `txtTradeName` | `50, false` |
| 7 | Manual Code | DmTextBox | `txtManualCode` | `25, false` |
| 8 | Account Group | DmTextHelp | `txtHAccountGroup` | `TxtHAccountGroup, 6, true` |
| 9 | Debit A/c Group | DmTextHelp | `txtHDebitAcGroup` | `TxtHDebitAcGroup, 6, false` |
| 10 | Credit A/c Group | DmTextHelp | `txtHCreditAcGroup` | `TxtHCreditAcGroup, 6, false` |
| 11 | Nature | DmTextHelp | `txtHNature` | `com.google.gwt.core.client.KeyField_PrpAcs, 5, true` |
| 12 | Cost Center | DmTextHelp | `txtHCostCenter` | `KeyField_PrpAcs, 4` |
| 13 | TCS Active | DmTextHelp | `txtHIsTcsActive` | `com.google.gwt.core.client.KeyField_PrpAcs, 4, true, true` |
| 14 | TCS Category | DmTextHelp | `txtHTCSCategory` | `KeyField_PrpAcs, 4` |
| 15 | TDS Category | DmTextHelp | `txtHTDSCategory` | `KeyField_PrpAcs, 4` |
| 16 | Category | DmTextHelp | `txtHPartyCategory` | `KeyField_PrpAcs, 4, true, false` |
| 17 | Ledger Group | DmTextHelp | `txtHLedgerGroup` | `KeyField_PrpAcs, 4` |
| 18 | Referenced To | DmTextHelp | `txtHReferencedTo` | `TxtHReferencedTo, 6` |
| 19 | Address | DmTextBox | `txtAddress` | `100, true` |
| 20 | - | DmTextBox | `txtAddress1` | `100, false` |
| 21 | City | DmTextHelp | `txtHCity` | `TxtHCity_Mdl_PrpAcs, 4, true` |
| 22 | Pin | DmTextBoxNumeric | `txtPinCode` | `0, false, 6, false` |
| 23 | State | DmTextBox | `txtState` | `100, false` |
| 24 | Zone | DmTextHelp | `txtHZone` | `KeyField_PrpAcs, 4` |
| 25 | Party Area | DmTextHelp | `txtHArea` | `KeyField_PrpAcs, 4` |
| 26 | Area List | DmTextHelpMulti | `txtHAreaList` | `KeyField_PrpAcs, 6` |
| 27 | Phone No. | DmTextBox | `txtPhoneNo` | `35, false` |
| 28 | Mobile No. | DmTextBox | `txtMobileNo` | `35, false` |
| 29 | EMail | DmTextBox | `txtEMail` | `250, false` |
| 30 | Salesman | DmTextHelp | `txtHSalesman` | `com.google.gwt.core.client.KeyField_PrpAcs, 5, false` |
| 31 | Relationship Mgr. | DmTextHelp | `txtHRelationshipManager` | `com.google.gwt.core.client.KeyField_PrpAcs, 5, false` |
| 32 | MSME Type | DmTextHelp | `txtHMsmeType` | `TxtHMsmeType_PrpAcs, 7` |
| 33 | MSME No. | DmTextBox | `txtMsmeNumber` | `16, false` |
| 34 | Party Type(GST) | DmTextHelp | `txtHPartyType` | `KeyField_PrpAcs, 4, true, true` |
| 35 | Self Transport | DmTextHelp | `txtHIsSelfTransport` | `com.google.gwt.core.client.KeyField_PrpAcs, 4, true, true` |
| 36 | GSTIN | DmTextBox | `txtGstTin` | `15, false` |
| 37 | GSTIN Date | DmTextBox | `txtGstDate` | `11, false` |
| 38 | AADHAAR | DmTextBoxNumeric | `txtAadhar` | `0, false, 12, false` |
| 39 | PAN | DmTextBox | `txtPAN` | `10, false` |
| 40 | CIN No. | DmTextBox | `txtRegistration` | `20, false` |
| 41 | Transporter ID | DmTextBox | `txtTransporterId` | `15, false` |
| 42 | Credit Limit | DmTextBoxNumeric | `txtCreditLimit` | `DmTextBoxNumeric.DecimalNature.DEFAULT, false, 10, false` |
| 43 | Credit Period | DmTextBoxNumeric | `txtDueDays` | `0, false, 5, false` |
| 44 | Interest Rate | DmTextBoxNumeric | `txtinterestRate` | `4, false, 8, false` |
| 45 | Remark | DmRemarkTextBox | `txtRemark` | `250, false` |

Other widgets (not in tab list): `txtInvoiceDiscount` (DmTextBoxNumeric), `txtHActive` (DmTextHelp), `grdBankDetail` (DmGrid), `grdContactPerson` (DmGrid), `grdAdditionalPlaceDetail` (DmGrid), `gridItemCat` (DmGrid), `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `txtHAccountGroup`: Group [Text, w0, GroupName]; Nature [Text, w150, NatureName]
- `txtHAccountNamePrefix`: Name [Text, w0, NAME]
- `txtHDebitAcGroup`: Group [Text, w0, GroupName]; Nature [Text, w150, NatureName]
- `txtHCreditAcGroup`: Group [Text, w0, GroupName]; Nature [Text, w150, NatureName]
- `txtHCostCenter`: Name [Text, w0, NAME]
- `txtHCity`: City [Text, w0, CityName]; State [Text, w100, stateName]; Country [Text, w100, countryName]
- `txtHZone`: Zone [Text, w0, NAME]
- `txtHArea`: Area [Text, w250, name]
- `txtHAreaList`: Area [Text, w150, name]
- `txtHNature`: Name [Text, w1, Nature]
- `txtHPartyCategory`: Name [Text, w0, Name]
- `txtHTDSCategory`: TDS Category [Text, w0, NAME]
- `txtHReferencedTo`: Name [Text, w0, NAME]; City [Text, w150, CityName]; Code [Text, w150, ManualCode]
- `txtHLedgerGroup`: Ledger Group [Text, w0, NAME]
- `txtHPartyType`: Type [Text, w0, Type]
- `txtHTCSCategory`: TCS Category [Text, w0, NAME]
- `txtHIsTcsActive`: Type [Text, w0, Name]
- `txtHMsmeType`: Name [Text, w220, name]
- `txtHIsSelfTransport`: Type [Text, w0, Name]
- `txtHActive`: Status [Text, w250]
- `txtHSalesman`: Name [Text, w1, salesman]
- `txtHRelationshipManager`: Name [Text, w1, relationshipManager]
- `grdBankDetail`: Account No. [Text, w150]; Bank [Text, w200]; Branch [Text, w200]; Branch Address [Text, w350]; IFSC Code [Text, w130]; Swift No. [Text, w130]; Default [Text, w55]
- `grdContactPerson`: Contact Person [Text, w500]; Mobile No. [Text, w200]; LandLine No. [Text, w150]; Designation [Text, w300]; Default [Text, w65]
- `grdAdditionalPlaceDetail`: Default [Text, w55]; Place [Text, w150]; Address 1 [Text, w150]; Address 2 [Text, w200]; City [Text, w130]; State [Text, w130]; Country [Text, w200]; Mobile [Text, w130]; Email [Text, w130]; Pin [Text, w130]
- `gridItemCat`: Item Category [Text, w440]
- `dmFind`: Name [Text, w200, Name]; Display Name [Text, w200, displayName]; Manual Code [Text, w100, Manual_Code]; Account Group [Text, w150, Ac_Group]; Nature [Text, w100, Nature]; GSTIN [Text, w120, Manual_Code]; PAN [Text, w100, Manual_Code]; Address [Text, w150, Address]; City [Text, w100, City]; Country [Text, w100, Country]; Zone [Text, w100, Zone]; Area Name [Text, w200, areaName]; PIN [Text, w100, Pin]; Phone [Text, w120, Phone]; Mobile [Text, w120, Mobile]; Email [Text, w150, E_Mail]; Cost Center [Text, w100, Cost_Center]; Referenced To [Text, w100, Distributor]; MSME Type [Text, w100, msmeName]; Price List [Text, w100, priceListName]; Active [Text, w100, active]

**Lookup / suggestion / find sources (server RPC called):**
- `txtName` → `getAccountNameSuggestion`
- `txtHAccountNamePrefix` → `getTxtHAccountNamePrefix`
- `txtHAccountGroup` → `getTxtHAccountGroup`
- `txtHNature` → `getTxtHNature`
- `txtHDebitAcGroup` → `getTxtHAccountGroup`
- `txtHCreditAcGroup` → `getTxtHAccountGroup`
- `txtHCostCenter` → `getTxtHCostCenter`
- `txtHCity` → `getTxtHCity`
- `txtHZone` → `getTxtHZone`
- `txtHArea` → `getTxtHArea`
- `txtHPartyCategory` → `getTxtHPartyCategory`
- `txtHTDSCategory` → `getTxtHTDSCategory`
- `txtHReferencedTo` → `getTxtHReferencedTo`
- `txtHLedgerGroup` → `getTxtHLedgerGroup`
- `txtHPartyType` → `getTxtHPartyType`
- `txtHTCSCategory` → `getTxtHTDSCategory`
- `txtHIsTcsActive` → `getTxtHYesNo`
- `txtHMsmeType` → `getTxtHMsmeType`
- `txtHIsSelfTransport` → `getTxtHYesNo`
- `txtHActive` → `getTxtHYesNo`
- `txtHSalesman` → `getTxtHSalesman`
- `txtHRelationshipManager` → `getTxtHRelationshipManager`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `CustomerNamePrefix` ← DmShared.xNull(txtHAccountNamePrefix.getText())
- `ManualCode` ← DmShared.removeSpace(DmShared.xNull(txtManualCode.getText()))
- `SharedKeyFieldName` ← DmShared.removeSpace(DmShared.xNull(txtName.getText()))
- `DisplayName` ← DmShared.removeSpace(DmShared.xNull(txtDisplayName.getText()))
- `LegalName` ← DmShared.removeSpace(DmShared.xNull(txtLegalName.getText()))
- `TradeName` ← DmShared.removeSpace(DmShared.xNull(txtTradeName.getText()))
- `AcGroup` ← DmShared.xNull(txtHAccountGroup.getTag())
- `AcgroupName` ← DmShared.xNull(txtHAccountGroup.getText())
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
- `StateNumericCode` ← DmClient.vNull(txtState.getTag(), "00")
- `StateName` ← DmShared.xNull(txtState.getText())
- `Zone` ← DmShared.xNull(txtHZone.getTag())
- `ZoneName` ← DmShared.xNull(txtHZone.getText())
- `AreaCode` ← DmShared.xNull(txtHArea.getTag())
- `AreaName` ← DmShared.xNull(txtHArea.getText())
- `AreaCodeCsv` ← DmShared.xNull(txtHAreaList.getTag())
- `AreaNameCsv` ← DmShared.xNull(txtHAreaList.getText())
- `Pin` ← DmShared.xNull(txtPinCode.getText())
- `PhoneNo` ← DmShared.xNull(txtPhoneNo.getText())
- `MobileNo` ← DmShared.xNull(txtMobileNo.getText())
- `Email` ← DmShared.xNull(txtEMail.getText())
- `SalesmanCode` ← DmShared.xNull((txtHSalesman.getTag()))
- `SalesmanName` ← DmShared.xNull((txtHSalesman.getText()))
- `RelationShipManagerCode` ← DmShared.xNull(txtHRelationshipManager.getTag())
- `RelationShipManagerName` ← DmShared.xNull(txtHRelationshipManager.getText())
- `PartyCategory` ← DmShared.xNull(txtHPartyCategory.getTag())
- `PartyCategoryName` ← DmShared.xNull(txtHPartyCategory.getText())
- `TdsCategory` ← DmShared.xNull(txtHTDSCategory.getTag())
- `TdsCategoryName` ← DmShared.xNull(txtHTDSCategory.getText())
- `ReferencedTo` ← DmShared.xNull(txtHReferencedTo.getTag())
- `ReferencedToName` ← DmShared.xNull(txtHReferencedTo.getText())
- `Pan` ← DmShared.xNull(txtPAN.getText()).toUpperCase()
- `CreditLimit` ← DmClient.vNull(txtCreditLimit.getText(), "0.00")
- `CreditPeriod` ← DmClient.vNull(txtDueDays.getText(), "0")
- `LedgerGroup` ← DmShared.xNull(txtHLedgerGroup.getTag())
- `LedgerGroupName` ← DmShared.xNull(txtHLedgerGroup.getText())
- `PartyType` ← DmShared.xNull(txtHPartyType.getTag())
- `RegistrationNo` ← DmShared.xNull(txtRegistration.getText())
- `Remark` ← DmShared.xNull(txtRemark.getText())
- `InvoiceDiscount` ← DmClient.vNull(txtInvoiceDiscount.getText(), "0.00")
- `Aadhar` ← DmShared.xNull(txtAadhar.getText())
- `GsTINDate` ← DmShared.dNull(txtGstDate.getText())
- `GsTin` ← DmShared.xNull(txtGstTin.getText())
- `TransportId` ← DmShared.xNull(txtTransporterId.getText())
- `IsSelfTransportTag` ← DmShared.xNull(txtHIsSelfTransport.getTag())
- `IsSelfTransportText` ← DmShared.xNull(txtHIsSelfTransport.getText())
- `TcsCategory` ← DmShared.xNull(txtHTCSCategory.getTag())
- `TcsCategoryName` ← DmShared.xNull(txtHTCSCategory.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtHAccountNamePrefix.setText` ← mrTemp.getCustomerNamePrefix()
- `txtHAccountNamePrefix.setTag` ← mrTemp.getCustomerNamePrefix()
- `txtName.setText` ← mrTemp.getSharedKeyFieldName()
- `txtDisplayName.setText` ← mrTemp.getDisplayName()
- `txtLegalName.setText` ← mrTemp.getLegalName()
- `txtTradeName.setText` ← mrTemp.getTradeName()
- `txtManualCode.setText` ← mrTemp.getManualCode()
- `txtHDebitAcGroup.setTag` ← mrTemp.getDebitAcGroup()
- `txtHDebitAcGroup.setText` ← mrTemp.getDebitAcGroupName()
- `txtHCreditAcGroup.setTag` ← mrTemp.getCreditAcGroup()
- `txtHCreditAcGroup.setText` ← mrTemp.getCreditAcGroupName()
- `txtHNature.setText` ← mrTemp.getNatureName()
- `txtHNature.setTag` ← mrTemp.getNatureCode()
- `txtHNature.setTag2nd` ← mrTemp.getNatureCode()
- `txtHCostCenter.setText` ← mrTemp.getCostCenterName()
- `txtHCostCenter.setTag` ← mrTemp.getCostCenter()
- `txtAddress.setText` ← mrTemp.getAddress()
- `txtAddress1.setText` ← mrTemp.getAddress1()
- `txtHCity.setText` ← mrTemp.getCityName()
- `txtHCity.setTag` ← mrTemp.getCity()
- `txtState.setText` ← mrTemp.getStateName()
- `txtState.setTag` ← mrTemp.getStateNumericCode()
- `txtHZone.setText` ← mrTemp.getZoneName()
- `txtHZone.setTag` ← mrTemp.getZone()
- `txtHArea.setText` ← mrTemp.getAreaName()
- `txtHArea.setTag` ← mrTemp.getAreaCode()
- `txtHAreaList.setText` ← mrTemp.getAreaNameCsv()
- `txtHAreaList.setTag` ← mrTemp.getAreaCodeCsv()
- `txtPinCode.setText` ← mrTemp.getPin()
- `txtPhoneNo.setText` ← mrTemp.getPhoneNo()
- `txtMobileNo.setText` ← mrTemp.getMobileNo()
- `txtEMail.setText` ← mrTemp.getEmail()
- `txtHSalesman.setTag` ← mrTemp.getSalesmanCode()
- `txtHSalesman.setText` ← mrTemp.getSalesmanName()
- `txtHRelationshipManager.setTag` ← mrTemp.getRelationShipManagerCode()
- `txtHRelationshipManager.setText` ← mrTemp.getRelationShipManagerName()
- `txtHPartyCategory.setText` ← mrTemp.getPartyCategoryName()
- `txtHPartyCategory.setTag` ← mrTemp.getPartyCategory()
- `txtHTDSCategory.setText` ← mrTemp.getTdsCategoryName()
- `txtHTDSCategory.setTag` ← mrTemp.getTdsCategory()
- `txtHReferencedTo.setText` ← mrTemp.getReferencedToName()
- `txtHReferencedTo.setTag` ← mrTemp.getReferencedTo()
- `txtPAN.setText` ← mrTemp.getPan()
- `txtAadhar.setText` ← mrTemp.getAadhar()
- `txtGstDate.setText` ← mrTemp.getGsTINDate()
- `txtGstTin.setText` ← mrTemp.getGsTin()
- `txtTransporterId.setText` ← mrTemp.getTransportId()
- `txtHIsSelfTransport.setTag` ← DmShared.xNull(mrTemp.getIsSelfTransportTag())
- `txtHIsSelfTransport.setText` ← DmShared.xNull(mrTemp.getIsSelfTransportText())
- `txtCreditLimit.setText` ← String.valueOf(mrTemp.getCreditLimit())
- `txtDueDays.setText` ← String.valueOf(mrTemp.getCreditPeriod())
- `txtHLedgerGroup.setText` ← mrTemp.getLedgerGroupName()
- `txtHLedgerGroup.setTag` ← mrTemp.getLedgerGroup()
- `txtInvoiceDiscount.setText` ← mrTemp.getInvoiceDiscount()
- `txtinterestRate.setText` ← mrTemp.getInterestRate()
- `txtHAccountGroup.setText` ← mrTemp.getAcgroupName()
- `txtHAccountGroup.setTag` ← mrTemp.getAcGroup()
- `txtHAccountGroup.setTag2nd` ← mrTemp.getAcGroup()
- `txtHMsmeType.setText` ← mrTemp.getMsmeName()
- `txtHMsmeType.setTag` ← mrTemp.getMsmeCode()

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtState.setEnabled(false)`
- `txtRemark.setEnabled(flag)`
- `txtHActive.setEnabled(flag)`
- `txtManualCode.setEnabled(true)`

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
txtState.setEnabled(false);
txtRemark.setEnabled(flag);
txtHActive.setEnabled(flag);
if (flag) {
manageTransporterControls();
if (topControl.getMode().equalsIgnoreCase("Edit")) {
txtManualCode.setEnabled(true);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`modifyReason.addCallBack` → `onOk()`
```java
try {
PartyMaster.this.modifyReasonMdl = modifyReason;
if (topControl.getMode().equalsIgnoreCase("Browse") && DmShared.iNull(topControl.getDocId()).longValue() > 0) {
topControlDelete(topControl.getDocId());
} else {
topControlSave();
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), (DmTextBox) null).show();
```

`topControl.addNewHandeller` → `newClicked()`
```java
onAdd();
```

`topControl.addEditHandeller` → `editClicked()`
```java
manageDisplay(true);
grdBankDetail.getGrid().getStore().add(new GrdBankDetail_Mdl());
grdAdditionalPlaceDetail.getGrid().getStore().add(new GrdPlacesDetail_Mdl());
gridItemCat.getGrid().getStore().add(new KeyFieldExtended());
grdContactPerson.getGrid().getStore().add(new GrdContactPerson_Mdl());
txtName.setFocus(true);
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

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(PartyMaster.this);
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
SrvAsync.invokeRPC(blnCallBackWait).getTxtHAccountGroup(DmShared.xNull(defaultValue.getAcGroupCustomerCode()), DmShared.
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
boolean flagChanged = false;
if (!DmShared.xNull(txtHAccountGroup.getTag()).equalsIgnoreCase(DmShared.xNull(txtHAccountGroup.getTag2nd()))) {
resetAcGroup();
txtHAccountGroup.setTag2nd(DmShared.xNull(txtHAccountGroup.getTag()));
flagChanged = true;
if (blnStatus && tRow != null) {
acGroupNatureCode = DmShared.iNull(tRow.getGroupNatureCode()).toString();
if (DmShared.xNull(txtHNature.getTag()).trim().equalsIgnoreCase("")) {
txtHNature.setTag(tRow.getNatureCode());
txtHNature.setTag2nd(tRow.getNatureCode());
...
```

`txtHNature.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHNature(allowCustomer, allowSupplier, allowTransporter, helper, blnToEndOfFile
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
boolean flagNatureChange = false;
if (!DmShared.xNull(txtHNature.getTag()).equalsIgnoreCase(DmShared.xNull(txtHNature.getTag2nd()))) {
resetNature();
txtHNature.setTag2nd(DmShared.xNull(txtHNature.getTag()));
flagNatureChange = true;
if (blnStatus && tRow != null) {
natureManualCode = DmShared.xNull(tRow.getSharedPreparedBy());
voucherTypeCode = DmShared.xNull(tRow.getSharedPreparedIP());
if (flagNatureChange) {
termsConfig.initialize();
...
```

`txtHDebitAcGroup.addTextHelpCallBack` → `populateData()`
```java
String contraHelpBalType = "";
if (DmShared.iNull(acGroupNatureCode).intValue() == 0
|| DmShared.iNull(acGroupNatureCode).intValue() == 4
|| DmShared.iNull(acGroupNatureCode).intValue() == 5) {
contraHelpBalType = "Dr";
SrvAsync.invokeRPC(blnCallBackWait).getTxtHAccountGroup("", "", "", false, false, false, contraHelpBalType, helper, blnT
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

`txtHDebitAcGroup.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus) {
```

`txtHCreditAcGroup.addTextHelpCallBack` → `populateData()`
```java
String contraHelpBalType = "";
if (DmShared.iNull(acGroupNatureCode).intValue() == 1
|| DmShared.iNull(acGroupNatureCode).intValue() == 2
|| DmShared.iNull(acGroupNatureCode).intValue() == 3) {
contraHelpBalType = "Cr";
SrvAsync.invokeRPC(blnCallBackWait).getTxtHAccountGroup("", "", "", false, false, false, contraHelpBalType, helper, blnT
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

`txtHCreditAcGroup.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus) {
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

**Top control options:** setRefreshRequired(false), setDisplay(true), setDisplay(false)

_Print permission "P" is removed for this screen (replace "P" with "*")._

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Alert: "App registeration saved successfully!"
- Alert: "App registeration deleted successfully!"
- Alert: "Due days can't be greater then 32767"
- Alert: "Can be used in browse mode only."
- Alert: "Party is not allowed for App registration!"
- Alert: "Can be used in browse mode only."

## Files
| file | lines |
|---|---|
| client/ItemCategoryGridEdit.java | 134 |
| client/ItemCategoryGridEdit_Dsg.java | 62 |
| client/PartyMaster.java | 1856 |
| client/PartyMaster_Dsg.java | 1611 |
| server/PartyMaster_SrvImpl.java | 2226 |
| shared/DefaultValue_Mdl.java | 188 |
| shared/Find_Mdl.java | 482 |
| shared/GrdBankDetail_Mdl.java | 77 |
| shared/GrdContactPerson_Mdl.java | 50 |
| shared/GrdPlacesDetail_Mdl.java | 104 |
| shared/GrdTdsCategory_Mdl.java | 41 |
| shared/PartyMaster_Mdl.java | 752 |
| shared/PartyMaster_Srv.java | 63 |
| shared/PartyMaster_SrvAsync.java | 63 |
| shared/PartyMaster_Vld.java | 80 |
| shared/TxtHAccountGroup_Mdl.java | 58 |
| shared/TxtHCity_Mdl.java | 59 |
| shared/TxtHReferencedTo_Mdl.java | 31 |
| shared/TxtHVatClause_Mdl.java | 22 |

## UI labels / columns (client)
`Name`, `Display Name`, `Legal Name`, `Trade Name`, `Manual Code`, `Account Group`, `Debit A/c Group`, `Credit A/c Group`, `Nature`, `Cost Center`, `TCS Active`, `TCS Category`, `TDS Category`, `Category`, `Ledger Group`, `Referenced To`, `Address`, `City`, `Pin`, `State`, `Zone`, `Party Area`, `Area List`, `Phone No.`, `Mobile No.`, `EMail`, `Salesman`, `Relationship Mgr.`, `MSME Type`, `MSME No.`, `Party Type(GST)`, `Self Transport`, `GSTIN`, `GSTIN Date`, `AADHAAR`, `PAN`, `CIN No.`, `Transporter ID`, `Credit Limit`, `Credit Period`, `Interest Rate`, `Active`, `Remark`, `Deals In`, `Bank Account Detail`, `Contact Person Detail`, `Additional Places of Business`, `Item Category`

## Models (shared DTOs)
### TxtHReferencedTo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | CityName |
| String | ManualCode |

### GrdContactPerson_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | mobileNo |
| String | landlineNo |
| String | designation |
| String | isChecked |

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
| String | siteCode |
| String | voucherTag |
| String | voucherText |
| String | voucherCategory |
| String | guiTemplateCode |
| String | acGroupCustomerCode |
| String | acGroupCustomerName |
| String | acGroupTransporterCode |
| String | acGroupTransporterName |
| String | acGroupSuplierCode |
| String | acGroupSupplierName |
| String | transporterGuiTemplateCode |
| String | transporterGuiTemplateText |
| String | supplierGuiTemplateCode |
| String | supplierGuiTemplateText |
| String | customerGuiTemplateCode |
| String | customerGuiTemplateText |
| boolean | gstApplicable |
| DmFileRuleType | fileRuleType |

### TxtHVatClause_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | VatNonVat |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | code |
| String | customerNamePrefix |
| String | customerName |
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
| String | areaName |
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
| String | msmeTypeName |
| String | priceListName |
| String | active |
| String | GSTIN |
| String | PAN |

### PartyMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | tempKeyCode |
| String | customerNamePrefix |
| String | displayName |
| String | legalName |
| String | manualCode |
| String | natureCode |
| String | natureName |
| String | natureManualCode |
| String | costCenter |
| String | costCenterName |
| String | address |
| String | address1 |
| String | city |
| String | cityName |
| String | stateName |
| String | stateNumericCode |
| String | zone |
| String | zoneName |
| String | areaCode |
| String | areaName |
| String | areaCodeCsv |
| String | areaNameCsv |
| String | pin |
| String | phoneNo |
| String | mobileNo |
| String | email |
| String | salesmanCode |
| String | salesmanName |
| String | relationShipManagerCode |
| String | relationShipManagerName |
| String | partyCategory |
| String | partyCategoryName |
| String | tdsCategory |
| String | tdsCategoryName |
| String | referencedTo |
| String | referencedToName |
| String | pan |
| String | creditLimit |
| String | creditPeriod |
| String | ledgerGroup |
| String | ledgerGroupName |
| String | partyType |
| String | remark |
| String | registrationNo |
| String | invoiceDiscount |
| String | vTypeTag |
| String | Aadhar |
| String | gsTINDate |
| String | gsTin |
| String | currentDate |
| String | acGroup |
| String | acgroupName |
| String | acGroupNatureCode |
| String | debitAcGroup |
| String | creditAcGroup |
| String | debitAcGroupName |
| String | creditAcGroupName |
| String | tcsCategory |
| String | tcsCategoryName |
| String | isTcsActiveTag |
| String | isTcsActiveText |
| String | msmeName |
| String | msmeCode |
| String | msmeNo |
| String | MsmeTypeName |
| String | tradeName |
| String | transportId |
| String | isSelfTransportTag |
| String | isSelfTransportText |
| String | isActiveTag |
| String | isActiveText |
| String | interestRate |
| String | guiConfigCode |
| String | voucherTypeCode |
| List<DmTermsConfigDetail_Mdl> | terms_MdlList |
| List<KeyFieldExtended> | grdItemCategory |
| boolean | gstApplicable |
| List<GrdBankDetail_Mdl> | grdBankDetail |
| List<GrdPlacesDetail_Mdl> | grdAdditionalPlaceDetail |
| List<GrdContactPerson_Mdl> | listContectPerson |
| ModifyReason_Mdl | modifyReasonMdl |

### TxtHAccountGroup_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | natureCode |
| String | natureName |
| String | natureManualCode |
| String | groupNatureCode |
| String | voucherTypeCode |

### TxtHCity_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | stateCode |
| String | stateName |
| String | stateNumericCode |
| String | countryCode |
| String | countryName |

### GrdPlacesDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
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

### GrdTdsCategory_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | fieldValue |
| String | fieldName |
| String | sNo |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getAccountNameSuggestion | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getMoveRec | String strKey | DmResult<PartyMaster_Mdl> |
| saveRecord | PartyMaster_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl modifyReasonMdl | DmResult<String> |
| getTxtHAccountNamePrefix | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAccountGroup | String customerCode, String supplierCode, String transporterCode, boolean allowCustomer, boolean allowTransporter, boolean allowSupplier, String contraHelpBalType, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHAccountGroup_Mdl>> |
| getTxtHCostCenter | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHCity | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHCity_Mdl>> |
| getTxtHZone | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHArea | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPartyCategory | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTDSCategory | DmHelper pageing, boolean blnToEndOfFile, String type | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHReferencedTo | String partyCode, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHReferencedTo_Mdl>> |
| getTxtHYesNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHLedgerGroup | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPartyType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getTxtHNature | boolean allowCustomer, boolean allowSupplier, boolean allowTransporter, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemCategory | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| getTempKeyCode |  | String |
| getServerDateTime |  | String[] |
| getTxtHMsmeType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSalesman | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHRelationshipManager | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `PartyMaster_Vld`
```java
public class PartyMaster_Vld {
    public static void chkSave(PartyMaster_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getSharedKeyFieldName(), "Name", 100);
        DmShared.isValidText(mrTemp.getDisplayName(), "Display name", 200);
        DmShared.isValidText(mrTemp.getAcgroupName(), "A/c group", 300);
        DmShared.isValidText(mrTemp.getNatureCode(), "nature", 500);
        DmShared.isValidEMail(mrTemp.getEmail(), 400);
        DmShared.isValidText(mrTemp.getAddress(), "Address", 410);
        DmShared.isValidAmount(mrTemp.getCity(), "City", 450);
        DmShared.isValidText(mrTemp.getPartyType(), "Party Type", 600);
        DmShared.isValidText(mrTemp.getIsTcsActiveText(), "TCS Active", 1000);
        DmShared.isValidText(mrTemp.getIsSelfTransportText(), "Self Transport", 970);
        DmShared.isValidText(mrTemp.getIsActiveText(), "Active", 1060);
        DmShared.isValidMobile(mrTemp.getMobileNo(), 650);
        if (!DmShared.xNull(mrTemp.getPan()).trim().equalsIgnoreCase("")) {
            if (DmShared.xNull(mrTemp.getPan()).length() != 10) {
                throw new DmException(950, "PAN No should be 10 digit.");
            } else {
                DmShared.isValidPanNo(mrTemp.getPan(), 950);
            }
        }
        if (DmShared.xNull(mrTemp.getNatureManualCode()).equalsIgnoreCase("TP")) {
            if (!DmShared.xNull(mrTemp.getIsSelfTransportTag()).equalsIgnoreCase("1")) {
                if ((DmShared.xNull(mrTemp.getPartyType()).equalsIgnoreCase(DmEnum.DmPartyType.Unregistered.getId())
                        || DmShared.xNull(mrTemp.getPartyType()).trim().equalsIgnoreCase(""))) {
                    if (DmShared.xNull(mrTemp.getTransportId()).trim().equalsIgnoreCase("")) {
                        throw new DmException(960, " Transporter ID is mandatory");
                    }
                }
            }
        }
        if (DmShared.xNull(mrTemp.getPartyType()).equalsIgnoreCase((DmEnum.DmPartyType.Registered.getId()))
                || DmShared.xNull(mrTemp.getPartyType()).equalsIgnoreCase((DmEnum.DmPartyType.Composite.getId()))) {
            DmShared.isValidText(mrTemp.getGsTin(), "GSTIN No.", 700);
            DmShared.isValidText(mrTemp.getGsTINDate(), "GSTIN Date", 800);
        }
        if (!DmShared.xNull(mrTemp.getGsTin()).equalsIgnoreCase("")) {
            if (DmShared.xNull(mrTemp.getPan()).trim().equalsIgnoreCase("")) {
                throw new DmException(950, "Please fill the PAN No.");
            } else {
                DmShared.isValidGSTIN(mrTemp.getStateNumericCode(), mrTemp.getPan(), mrTemp.getGsTin(), 700);
            }
        }
        if (DmShared.xNull(mrTemp.getPartyType()).equalsIgnoreCase((DmEnum.DmPartyType.Unregistered.getId()))) {
            if (!DmShared.xNull(mrTemp.getGsTin()).equalsIgnoreCase("") || !DmShared.xNull(mrTemp.getGsTINDate()).equalsIgnoreCase("")) {
                throw new DmException(850, "In case of unregistered party type, you should not fill GSTIN No. and GSTIN Date.");
            }
        }
        if (!DmShared.xNull(mrTemp.getGsTINDate()).equalsIgnoreCase("")) {
            if (DmShared.convertDate(mrTemp.getGsTINDate()).getTime() > (DmShared.convertDate(mrTemp.getCurrentDate())).getTime()) {
                throw new DmException(800, "GSTIN Date should not be greater than current date.");
            }
        }
        if (!DmShared.xNull(mrTemp.getAadhar()).trim().equalsIgnoreCase("")) {
            if (DmShared.xNull(mrTemp.getAadhar()).length() != 12) {
                throw new DmException(900, "AADHAAR no. should be of 12 digits.");
            }
        }
        DmTermsConfig_Vld.chkSave(mrTemp.getTerms_MdlList());
    }
}
```

## Server `PartyMaster_SrvImpl.java` (2226 lines)
Helpers used: `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getConnectionMongo`, `DmServer.getLocalIPAddresses`, `DmServer.getParamList`, `DmServer.getSerialNoBigInt`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnFileDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteGSTIN`, `DmServer.getSsnUserName`, `DmServer.getUserWithDateTime`, `DmServer.setSsnOldData`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`

- SQL 1: `select guiTemplateCode from voucher_Type where category=' {…} '`
- SQL 2: `SELECT vt.v_Type, vt.description FROM voucher_type vt WHERE vt.category = ' {…} '`
- SQL 3: `select ec.acGroupSupplierCode,ec.acGroupCustomerCode,ec.acGroupTransporterCode, ec.customerGuiTemplateCode, ec.supplierGuiTemplateCode, ec.transporterGuiTemplateCode, acgs.groupName as acGroupSupplierName, acgc.groupName as acGroupCustomerName, acgt.groupName as acGroupTransporterName, gt.name AS customerGuiTemplateName, gtm.name AS supplierGuiTemplateName, gtmm.name AS TransporterGuiTemplateName from enviroCompany ec Left JOIN acGroup acgs ON ec.acGroupSupplierCode =acgs.groupCode Left JOIN acGroup acgc ON ec.acGroupCustomerCode = acgc.groupCode Left JOIN acGroup acgt ON ec.acGroupTransporterCode = acgt.groupCode LEFT JOIN guiTemplateMast gt ON ec.customerGuiTemplateCode = gt.code LEFT JOIN guiTemplateMast gtm ON ec.supplierGuiTemplateCode = gtm.code LEFT JOIN guiTemplateMast gtmm ON ec.TransporterGuiTemplateCode = gtmm.code`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `Select sg.SubCode As SearchCode, sg.Name From SubGroup sg where sg.filterNature = :filterNature Order By Name`
- Params: `:filterNature` ← `DmServer.getCodeViaManualCode("PT", "manualCode", "subgroupNature", "Code", cnTemp)`

### `getAccountNameSuggestion`
`public DmResultPageing<List<String>> getAccountNameSuggestion(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select sg.Name as NAME From SubGroup sg Left Join subgroupNature sgn on sg.filterNature=sgn.code where sg.filterNature=:filterNature`
- Params: `:filterNature` ← `DmServer.getCodeViaManualCode("PT", "manualCode", "subgroupNature", "Code", cnTemp)`

### `getMoveRec`
`public DmResult<PartyMaster_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT SG.SubCode,SG.Initial AS InitialName,Sg.Name AS CustomerName,SG.DisplayName,SG.LegalName , SG.groupCode,AG.GroupName as acGroupName, ag.groupNature, SG.acGroupDr,AGdr.GroupName as GroupNameDr,SG.acGroupCr , AGcr.GroupName as GroupNameCr, SG.ManualCode, SG.Nature as NatureCode , sgn1.Name as NatureName, sgn1.manualCode as natureManualCode,SG.Add1,SG.Add2 , Sg.CityCode,C.cityName,Ctry.Name AS CountryName,sm.name as stateName, sm.numericCode,SG.PIN, SG.Phone, SG.registrationNo , SG.Mobile,CCM.Name AS CCName,SG.CostCenter, SG.remark,SG.PAN,SG.EMail,SG.TINNo,SG.CreditLimit,SG.DueDays, SG.PartyCat , SG.PartyType, sg.guiConfigCode, SG.TDS_Catg,TC.Name AS TCName,SG.LedgerGroup,LG.Name AS LGName, SG.Zone, am.code AS areaCode , am.name AS areaName, ZM.Name AS ZMName,SG.Distributor,SG1.Name AS DName, SG.U_Name, SG.preparedBy, SG.preparedByIP,SG.preparedDt , SG.u_EntDt,SG.isActive, SG.invoiceDiscount,SG.aadhaarNo,SG.gsTinDate,SG.gsTIN , SG.tcs_Catg,TC1.Name as TcsName , SG.isTcsActive , SG2.msmeCode, SG2.msmeNo, SG.transporterId, sg.isSelfTransport , vSgal.areaCodeLst,vSgal.areaNameLst , SG.tradeName, SG.interestRate , sg2.salesmanCode,sgsm.name AS salesManName,sg2.relationshipManagerCode,sgrm.name AS relationShipManagerName , sg.v_Type FROM SubGroup SG LEFT JOIN SubGroup2 SG2 ON SG2.SubCode=SG.SubCode LEFT JOIN subGroup sgsm ON sgsm.subCode = sg2.salesmanCode LEFT JOIN subGroup sgrm ON sgrm.subCode = sg2.relationshipManagerCode LEFT JOIN AcGroup AG ON AG.GroupCode=SG.groupCode LEFT JOIN AcGroup AGdr ON AGdr.GroupCode=SG.acGroupDr LEFT JOIN AcGroup AGcr ON AGcr.GroupCode=SG.acGroupCr LEFT JOIN City C ON C.CityCode=SG.CityCode LEFT JOIN Country Ctry ON C.countryCode=Ctry.Code LEFT JOIN stateMast sm ON C.stateCode=sm.Code LEFT JOIN CostCenterMast CCM ON CCM.Code=SG.CostCenter LEFT JOIN TDSCat TC ON TC.Code=SG.TDS_Catg LEFT JOIN LedgerGroup LG ON LG.Code=SG.LedgerGroup LEFT JOIN ZoneMast ZM ON ZM.Code=SG.Zone Left Join subgroupNature sgn1 on SG.Nature=sgn1.code LEFT JOIN SubGroup SG1 ON SG1.SubCode=SG.Distributor LEFT JOIN TDSCat TC1 ON TC1.Code=SG.tcs_Catg LEFT JOIN areaMast am ON am.code = SG.areaCode LEFT JOIN ( SELECT STRING_AGG (CONVERT(NVARCHAR(max),sgal.areaCode), ',') AS areaCodeLst , STRING_AGG (CONVERT(NVARCHAR(max),am.name), ',') AS areaNameLst , sgal.subCode FROM subGroupAreaList sgal LEFT JOIN areaMast am ON am.code = sgal.areaCode WHERE sgal.subCode = :code group by sgal.subCode ) vSgal on vSgal.subCode = SG.subCode WHERE SG.subCode = :code and sg.filterNature=:filterNature`
- SQL 2: `SELECT sgic.itemCategoryCode, ic.name as itemCategoryName FROM subgroupItemCategory sgic LEFT JOIN itemCat ic ON ic.code = sgic.itemCategoryCode WHERE sgic.subCode = :subCode`
- SQL 3: `SELECT subCode,sNo,bankName,accountNo,branch,swiftCode,isDefault,ifscCode,branchAddress FROM bankDetail WHERE subCode = :code ORDER BY sNo`
- SQL 4: `SELECT subCode,sNo,place,add1,add2, ct.cityName, sm.name as stateName, ctr.name as countryName,pin,mobile,eMail,isDefault FROM additionalPlaceOfBusiness apb left join city ct on ct.cityCode=apb.cityCode left join stateMast sm on ct.stateCode = sm.code left join country ctr on ct.countryCode = ctr.code WHERE subCode = :code ORDER BY sNo`
- SQL 5: `SELECT code,contactPerson,mobile,phone AS landLineNo,designation,isDefault FROM addressBook where subCode= :code order by contactPerson`
- Params: `:code` ← `DmShared.iNull(strKey).toString()`; `:filterNature` ← `DmServer.getCodeViaManualCode("PT", "manualCode", "subgroupNature", "Code", cnTemp)`; `:subCode` ← `DmShared.iNull(strKey).toString()`; `:code` ← `strKey`; `:code` ← `strKey`; `:code` ← `strKey`
- Other calls: `DmTermsConfig_Server.getTermsConfig`, `MSME_Type.getViaCode`, `PartyCategory.getViaKey`

### `saveRecord`
`public DmResult<String> saveRecord(PartyMaster_Mdl mrTemp)`

- SQL 1: `Select Count(*) As Cnt From SubGroup Where Name = :name and SubCode<> :subCode`
- SQL 2: `SELECT e.siteManualCodeCSV FROM enviro e WHERE e.site_Code = :site_Code`
- SQL 3: `Select Count(*) As Cnt From SubGroup where ManualCode = :manualCode and SubCode<> :subCode`
- SQL 4: `Insert Into SubGroup(SubCode,Name ,groupCode,acGroupDr,acGroupCr,ManualCode,Nature,Add1,Add2,CityCode,PIN,registrationNo, Phone,Mobile,Remark,preparedDt,CostCenter,PAN,EMail,CreditLimit,DueDays,PartyCat,PartyType,TDS_Catg,Zone,areaCode,LedgerGroup,Distributor,Site_Code,DisplayName,Initial, preparedBy, preparedByIP,filterNature,invoiceDiscount ,aadhaarNo,gsTinDate,gsTIN, guiConfigCode ,LegalName ,tcs_Catg,isTcsActive,transporterId, isSelfTransport, tradeName, interestRate, v_Type, isActive) Values (:subCode, :name, :groupCode, :acGroupDr, :acGroupCr, :manualCode, :nature, :add1, :add2, :cityCode, :pin,:registrationNo, :phone, :mobile, :remark, :preparedDt, :costCenter, :pan, :email,:creditLimit, :dueDays, :partyCat, :partyType,:tdsCatg, :zone, :areaCode, :ledgerGroup, :distributor , :siteCode, :displayName, :initial, :preparedBy, :preparedByIP,:filterNature, :invoiceDiscount,:aadhaarNo,:gsTinDate,:gsTIN, :guiConfigCode , :legalName , :tcs_Catg , :isTcsActive , :transporterId, :isSelfTransport, :tradeName, :interestRate, :v_Type, :isActive)`
- SQL 5: `update SubGroup Set Name=:name, groupCode = :groupCode,acGroupDr = :acGroupDr,acGroupCr = :acGroupCr,ManualCode=:ManualCode,Nature=:Nature,Add1=:Add1,Add2=:Add2,CityCode=:CityCode,PIN=:pin,Phone=:Phone,Mobile=:Mobile,Remark=:Remark,CostCenter=:CostCenter,U_Name=:U_Name,U_EntDt=:uEntDt,PAN=:PAN,EMail=:EMail,PartyCat=:PartyCat,PartyType=:PartyType,CreditLimit=:CreditLimit,DueDays=:DueDays,Zone=:Zone,areaCode=:areaCode,LedgerGroup=:LedgerGroup,Distributor=:Distributor,DisplayName = :DisplayName,LegalName = :LegalName,Initial=:Initial,transferDt=:transferDt,registrationNo=:registrationNo,TDS_Catg=:TDSCatg, invoiceDiscount=:invoiceDiscount,aadhaarNo=:aadhaarNo, gsTIN=:gsTIN, gsTinDate = :gsTinDate, tcs_Catg = :tcs_Catg , isTcsActive = :isTcsActive , transporterId = :transporterId , isSelfTransport = :isSelfTransport , tradeName = :tradeName , interestRate = :interestRate, guiConfigCode = :guiConfigCode, v_Type = :v_Type, isActive = :isActive Where SubCode=:SubCode`
- SQL 6: `SELECT sm.code FROM siteMast sm WHERE sm.manualCode IN ( {…} :siteManualCodeCSV {…} )`
- SQL 7: `INSERT INTO subgroupsitelist (subCode, site_Code) VALUES (:subCode, :siteCode) ;`
- SQL 8: `Insert Into SubGroup2 (SubCode) Values (:subCode)`
- SQL 9: `Update SubGroup2 SET msmeCode=:msmeCode , msmeNo=:msmeNo , salesmanCode=:salesmanCode , relationshipManagerCode=:relationshipManagerCode WHERE subCode = :subCode`
- SQL 10: `Delete From subgroupItemCategory Where subCode = :subCode`
- SQL 11: `DELETE FROM subGroupAreaList WHERE subCode = :subCode`
- Params: `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:subCode` ← `mrTemp.getSharedKeyFieldCode()`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:manualCode` ← `mrTemp.getManualCode()`; `:subCode` ← `mrTemp.getSharedKeyFieldCode()`; `:subCode` ← `mrTemp.getSharedKeyFieldCode()`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:guiConfigCode` ← `DmShared.foriegnKey(mrTemp.getGuiConfigCode())`; `:groupCode` ← `DmShared.foriegnKey(mrTemp.getAcGroup())`; `:acGroupDr` ← `DmShared.foriegnKey(mrTemp.getDebitAcGroup())`; `:acGroupCr` ← `DmShared.foriegnKey(mrTemp.getCreditAcGroup())`; `:manualCode` ← `DmShared.foriegnKey(mrTemp.getManualCode())`; `:nature` ← `DmShared.foriegnKey(mrTemp.getNatureCode())`; `:add1` ← `DmShared.xNull(mrTemp.getAddress())`; `:add2` ← `DmShared.xNull(mrTemp.getAddress1())`; `:filterNature` ← `DmShared.foriegnKey(filterNature)`; `:cityCode` ← `DmShared.foriegnKey(mrTemp.getCity())`; `:pin` ← `DmShared.xNull(mrTemp.getPin())`; `:phone` ← `mrTemp.getPhoneNo()`; `:mobile` ← `mrTemp.getMobileNo()`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:preparedBY` ← `DmServer.getSsnUserName(this)`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:preparedDt` ← `timestamp`; `:costCenter` ← `DmShared.foriegnKey(mrTemp.getCostCenter())`; `:pan` ← `DmShared.xNull(mrTemp.getPan().toUpperCase())`; `:email` ← `mrTemp.getEmail()`; `:creditLimit` ← `DmServer.vNull(mrTemp.getCreditLimit(), "0.00")`; `:dueDays` ← `DmServer.vNull(mrTemp.getCreditPeriod(), "0")`; `:partyCat` ← `DmShared.foriegnKey(mrTemp.getPartyCategory())`; `:partyType` ← `mrTemp.getPartyType()`; `:tdsCatg` ← `DmShared.foriegnKey(mrTemp.getTdsCategory())`; `:zone` ← `DmShared.foriegnKey(mrTemp.getZone())`; `:areaCode` ← `DmShared.foriegnKey(mrTemp.getAreaCode())`; `:ledgerGroup` ← `DmShared.foriegnKey(mrTemp.getLedgerGroup())`; `:distributor` ← `DmShared.foriegnKey(mrTemp.getReferencedTo())`; `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:displayName` ← `DmShared.xNull(mrTemp.getDisplayName())`; `:legalName` ← `DmShared.xNull(mrTemp.getLegalName())`; `:initial` ← `DmShared.xNull(mrTemp.getCustomerNamePrefix())`; `:registrationNo` ← `DmShared.xNull(mrTemp.getRegistrationNo())`; `:invoiceDiscount` ← `DmServer.vNull(mrTemp.getInvoiceDiscount(), "0.00")`; `:aadhaarNo` ← `DmShared.xNull(mrTemp.getAadhar())`; `:gsTinDate` ← `DmServer.convertDate(mrTemp.getGsTINDate())`; `:gsTIN` ← `DmShared.xNull(mrTemp.getGsTin().toUpperCase())`; `:tcs_Catg` ← `DmShared.foriegnKey(mrTemp.getTcsCategory())`; `:isTcsActive` ← `DmShared.iNull(mrTemp.getIsTcsActiveTag()).toString()`; `:isSelfTransport` ← `DmShared.iNull(mrTemp.getIsSelfTransportTag()).toString()`; `:transporterId` ← `DmShared.xNull(mrTemp.getTransportId())`; `:tradeName` ← `DmShared.xNull(mrTemp.getTradeName())`; `:interestRate` ← `DmServer.vNull(mrTemp.getInterestRate())`; `:v_Type` ← `DmShared.foriegnKey(mrTemp.getVoucherTypeCode())`; `:isActive` ← `DmShared.iNull(mrTemp.getIsActiveTag()).intValue()`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:groupCode` ← `DmShared.foriegnKey(mrTemp.getAcGroup())`; `:acGroupCr` ← `DmShared.foriegnKey(mrTemp.getCreditAcGroup())`; `:acGroupDr` ← `DmShared.foriegnKey(mrTemp.getDebitAcGroup())`; `:ManualCode` ← `DmShared.foriegnKey(mrTemp.getManualCode())`; `:Nature` ← `DmShared.foriegnKey(mrTemp.getNatureCode())`; `:Add1` ← `DmShared.xNull(mrTemp.getAddress())`; `:Add2` ← `DmShared.xNull(mrTemp.getAddress1())`; `:CityCode` ← `DmShared.foriegnKey(mrTemp.getCity())`; `:pin` ← `DmShared.xNull(mrTemp.getPin())`; `:Phone` ← `mrTemp.getPhoneNo()`; `:Mobile` ← `mrTemp.getMobileNo()`; `:Remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:CostCenter` ← `DmShared.foriegnKey(mrTemp.getCostCenter())`; `:U_Name` ← `DmServer.getSsnUserName(this)`; `:PAN` ← `DmShared.xNull(mrTemp.getPan().toUpperCase())`; `:EMail` ← `mrTemp.getEmail()`; `:uEntDt` ← `timestamp`; `:PartyCat` ← `mrTemp.getPartyCategory()`; `:PartyType` ← `mrTemp.getPartyType()`; `:CreditLimit` ← `DmServer.vNull(mrTemp.getCreditLimit(), "0.00")`; `:DueDays` ← `DmServer.vNull(mrTemp.getCreditPeriod(), "0")`; `:Zone` ← `DmShared.foriegnKey(mrTemp.getZone())`; `:areaCode` ← `DmShared.foriegnKey(mrTemp.getAreaCode())`; `:LedgerGroup` ← `DmShared.foriegnKey(mrTemp.getLedgerGroup())`; `:Distributor` ← `DmShared.foriegnKey(mrTemp.getReferencedTo())`; `:DisplayName` ← `DmShared.xNull(mrTemp.getDisplayName())`; `:LegalName` ← `DmShared.xNull(mrTemp.getLegalName())`; `:Initial` ← `DmShared.xNull(mrTemp.getCustomerNamePrefix())`; `:transferDt` ← `null`; `:TDSCatg` ← `DmShared.foriegnKey(mrTemp.getTdsCategory())`; `:registrationNo` ← `DmShared.xNull(mrTemp.getRegistrationNo())`; `:SubCode` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:invoiceDiscount` ← `DmServer.vNull(mrTemp.getInvoiceDiscount(), "0.00")`; `:aadhaarNo` ← `DmShared.xNull(mrTemp.getAadhar())`; `:gsTinDate` ← `DmServer.convertDate(mrTemp.getGsTINDate())`; `:gsTIN` ← `DmShared.xNull(mrTemp.getGsTin().toUpperCase())`
- Errors: "Invalid key." | "PAN Should Be Of 10(Ten) Characters." | "Duplicate Party Name Not Allowed !!!" | "Duplicate Manual Code Not Allowed !!!" | "Manual Code is mandatory."
- Other calls: `DmFileManager_Server.manageLinkFileCollection`, `DmTermsConfig_Server.delete`, `DmTermsConfig_Server.save`, `PartyMaster_Vld.chkSave`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl modifyReasonMdl)`

- SQL 1: `select count(*) as cnt from addressBook where subCode = :subCode`
- SQL 2: `Delete From bankDetail Where SubCode = :SubCode`
- SQL 3: `Delete From subgroupItemCategory Where subCode = :subCode`
- SQL 4: `Delete From Subgroup2 Where SubCode = :SubCode`
- SQL 5: `DELETE FROM subGroupAreaList WHERE subCode = :subCode`
- SQL 6: `Delete From subgroupsitelist Where subCode= :SubCode`
- SQL 7: `Delete From Subgroup Where SubCode = :SubCode`
- SQL 8: `SELECT lfc.sNo, lfc.ruleCode FROM linkFileCollection lfc WHERE docid = :subCode`
- Params: `:subCode` ← `DmShared.iNull(strKey).toString()`; `:SubCode` ← `DmShared.iNull(strKey).toString()`; `:subCode` ← `DmShared.iNull(strKey).toString()`; `:SubCode` ← `DmShared.iNull(strKey).toString()`; `:subCode` ← `strKey`; `:SubCode` ← `DmShared.iNull(strKey).toString()`; `:SubCode` ← `DmShared.iNull(strKey).toString()`; `:subCode` ← `DmShared.iNull(strKey).toString()`
- Errors: "Record is already in use You can't delete this record."
- Other calls: `DmTermsConfig_Server.delete`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select SG.SubCode,SG.ManualCode As Manual_Code,SG.Name, SG.displayName, SG.legalName, CONCAT(isNull(SG.Add1,'') ,' ', isNull(SG.Add2,'')) AS Address,isNull(CityName,'') As City,isNull(Ctry.Name,'') As Country, isNull(ZN.Name,'') As Zone, am.name AS areaName, SG.PIN,isNull(SG.Phone,'') As Phone,isNull(SG.Mobile,'') As Mobile, isNull(SG.EMail,'') As E_Mail,AG.GroupName As Ac_Group,sgn.name as Nature, isNull(CCM.Name,'') As Cost_Center, isNull(SG1.Name,'') As Distributor , case when SG2.msmeCode = {…} then 'Micro' when SG2.msmeCode = {…} then 'Small' when SG2.msmeCode = {…} then 'Medium' else '' end as msmeName , sg.gsTin, sg.pan , SG.priceListCode ,plm.name as priceListName, CASE WHEN sg.isActive = 1 THEN 'Yes' ELSE 'No' END AS active From Subgroup SG LEFT JOIN subGroup2 sg2 ON sg2.subCode = sg.subCode Left Join City C On C.CityCode=SG.CityCode Left Join Country Ctry On C.countryCode=Ctry.Code Left Join ZoneMast ZN On ZN.Code=SG.Zone Left Join AcGroup AG On AG.GroupCode=SG.GroupCode Left Join CostCenterMast CCM On CCM.Code=SG.CostCenter Left join subgroupNature sgn on SG.nature= sgn.code Left Join SubGroup SG1 On SG1.SubCode=SG.Distributor LEFT JOIN priceListMast plm on plm.code=SG.priceListCode LEFT JOIN areaMast am ON am.code = SG.areaCode where SG.filterNature = :filterNature`
- Params: `:filterNature` ← `DmShared.xNull(DmServer.getCodeViaManualCode("PT", "manualCode", "subgroupNature", "code",`
- Other calls: `MEDIUM.getCode`, `MICRO.getCode`, `SMALL.getCode`

### `getVoucherType`
`private String getVoucherType(String natureManualCode, Connection cnTemp) throws Exception`


### `getTxtHAccountNamePrefix`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAccountNamePrefix(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT CODE,NAME FROM initialmast`

### `getTxtHAccountGroup`
`public DmResultPageing<List<TxtHAccountGroup_Mdl>> getTxtHAccountGroup(String customerCode, String supplierCode, String transporterCode, boolean allowCustomer, boolean allowTransporter, boolean allowSupplier, String contraHelpBalType, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ag.groupCode as GroupCode, ag.groupName as GroupName, ag.groupNature ,ag.Nature as NatureCode, sgn.name as NatureName, sgn.manualCode as natureManualCode FROM acGroup ag left join subgroupNature sgn on ag.nature= sgn.code Where :one=:one {…}`
- Params: `:customerCode` ← `DmShared.xNull(customerCode)`; `:supplierCode` ← `DmShared.xNull(supplierCode)`; `:TransporterCode` ← `DmShared.xNull(transporterCode)`

### `getTxtHNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHNature(boolean allowCustomer, boolean allowSupplier, boolean allowTransporter, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select sgn.code,sgn.name as Nature, sgn.manualCode as natureManualCode from subGroupNature sgn Where 1=1 AND ( {…} )`

### `getTxtHCostCenter`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCostCenter(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT CODE ,NAME FROM CostCenterMast where isActive=1`

### `getTxtHCity`
`public DmResultPageing<List<TxtHCity_Mdl>> getTxtHCity(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select cityCode,cityName,stateCode,sm.name as stateName,sm.numericCode, countryCode,ctr.name as countryName from city ct left join stateMast sm on ct.stateCode = sm.code left join country ctr on ct.countryCode = ctr.code`

### `getTxtHZone`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHZone(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ZM.Code, ZM.Name FROM zonemast ZM`

### `getTxtHArea`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHArea(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT am.code, am.name FROM areaMast am`

### `getTxtHPartyCategory`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHPartyCategory(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `PartyCategory.values`

### `getTxtHTDSCategory`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTDSCategory(DmHelper helper, boolean blnToEndOfFile, String type)`

- SQL 1: `SELECT CODE,NAME FROM TdsCat where type = :type`
- Params: `:type` ← `DmShared.xNull(type)`

### `getTxtHReferencedTo`
`public DmResultPageing<List<TxtHReferencedTo_Mdl>> getTxtHReferencedTo(String partyCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT SG.SubCode,SG.Name AS NAME,isNull(CT.CityName,'') AS CityName,SG.ManualCode FROM SubGroup SG LEFT JOIN City CT ON CT.CityCode=SG.CityCode WHERE sg.filterNature=:filterNature AND sg.partyCat= :partyCat AND sg.subCode <> :partyCode`
- Params: `:filterNature` ← `DmServer.getCodeViaManualCode("PT", "manualCode", "subgroupNature", "Code", cnTemp)`; `:partyCat` ← `DmEnum.PartyCategory.DISTRIBUTOR.getId()`; `:partyCode` ← `DmShared.iNull(partyCode).toString()`
- Other calls: `DISTRIBUTOR.getId`

### `getTxtHLedgerGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHLedgerGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select Code,Name From LedgerGroup`

### `getTxtHPartyType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHPartyType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `DmPartyType.values`

### `getTxtHItemCategory`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemCategory(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select imc.code, imc.name from itemcat imc`

### `getTxtHYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHMsmeType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHMsmeType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `MSME_Type.values`

### `getTxtHSalesman`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSalesman(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ec.srDesignationCode FROM enviroCompany ec`
- SQL 2: `SELECT sg.subCode AS code , sg.name AS salesman FROM subGroup sg LEFT JOIN subGroup2 sg2 ON sg2.subCode = sg.subCode LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode WHERE sg.isActive = 1 AND sgsl.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`; `:designationCode` ← `DmShared.xNull(designationCode)`

### `getTxtHRelationshipManager`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHRelationshipManager(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ec.rsmDesignationCode FROM enviroCompany ec`
- SQL 2: `SELECT sg.subCode AS code , sg.name AS relationshipManager FROM subGroup sg LEFT JOIN subGroup2 sg2 ON sg2.subCode = sg.subCode LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode WHERE sg.isActive = 1 AND sgsl.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`; `:designationCode` ← `DmShared.xNull(designationCode)`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmFileManager.shared`, `dataman.dmBase.dmFileManagerMongo.server`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFileManager.client`, `dataman.dmToolkit.dmFileManager.server`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGUI.client`, `dataman.dmToolkit.dmGUI.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTermsConfig.client`, `dataman.dmToolkit.dmTermsConfig.server`, `dataman.dmToolkit.dmTermsConfig.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.manageSite.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.self.client`, `dataman.libraryMenu.self.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stStatic.shared`, `paperbiz.selfToolkit.userRegisteration.client`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (!DmShared.xNull(mrTemp.getPan()).equalsIgnoreCase("") && DmShared.xNull(mrTemp.getPan()).length() != 10)` | "PAN Should Be Of 10(Ten) Characters." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Duplicate Party Name Not Allowed !!!" |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Duplicate Manual Code Not Allowed !!!" |
| saveRecord | `if (DmShared.xNull(mrTemp.getManualCode()).equalsIgnoreCase(""))` | "Manual Code is mandatory." |
| deleteRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Record is already in use You can't delete this record." |

## Notes (hand-written)
_No hand-written notes yet._
