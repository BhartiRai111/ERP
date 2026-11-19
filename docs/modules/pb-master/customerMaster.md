# pb-master / customerMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/master/src/customerMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: SubGroup, subgroupsitelist, SubGroup2
- Update tables: SubGroup
- Delete cascade (in order): bankDetail, Subgroup2, subgroupsitelist, Subgroup
- Duplicate checks on save:
  - `Select Count(*) As Cnt From SubGroup Where Name = :name and SubCode<> :subCode`
  - `Select Count(*) As Cnt From SubGroup where ManualCode = :manualCode and SubCode<> :subCode`
- "In use" checks before delete:
  - `select count(*) as cnt from addressBook where subCode = :subCode`
- Validations: name (Text); Display name (Text); A/c group (Text); nature (Text); City (Amount); Party Type (Text); GSTIN No. (Text); GSTIN Date (Text); rule: PAN No should be 10 digit.; rule: Please fill the PAN No.; rule: In case of unregistered party type, you should not fill GSTIN No. and GSTIN Date.; rule: In case of unregistered party type AADHAAR No. or PAN No. is mandatory.; rule: GSTIN Date should not be greater than current date.; rule: AADHAAR No. should be 12 digit.
- Lookups/suggestions: getTxtHAccountNamePrefix, getTxtHAccountGroup, getTxtHCostCenter, getTxtHCity, getTxtHZone, getTxtHPartyCategory, getTxtHTDSCategory, getTxtHReferencedTo, getTxtHDuplicate, getTxtHLedgerGroup, getTxtHPartyType, getTxtHNature
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `CustomerMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmGUI | `gui` | `topControl, 450` |
| 2 | Customer Name | DmTextHelp | `txtHAccountNamePrefix` | `KeyField_PrpAcs, 7, false` |
| 3 | - | DmSuggestBox | `txtName` | `100, 10, true, "NAME", DmDataType.STRING` |
| 4 | Display Name | DmTextBox | `txtDisplayName` | `100, true` |
| 5 | Manual Code | DmTextBox | `txtManualCode` | `25, false` |
| 6 | A/c Group | DmTextHelp | `txtHAccountGroup` | `TxtHAccountGroup, 6, true` |
| 7 | Nature | DmTextHelp | `txtHNature` | `com.google.gwt.core.client.KeyField_PrpAcs, 10, true` |
| 8 | Cost Center | DmTextHelp | `txtHCostCenter` | `KeyField_PrpAcs, 4` |
| 9 | Party Category | DmTextHelp | `txtHPartyCategory` | `KeyField_PrpAcs, 4, true, false` |
| 10 | Referenced To | DmTextHelp | `txtHReferencedTo` | `TxtHReferencedTo, 6` |
| 11 | Address | DmTextBox | `txtAddress` | `100, false` |
| 12 | - | DmTextBox | `txtAddress1` | `100, false` |
| 13 | City | DmTextHelp | `txtHCity` | `TxtHCity_Mdl_PrpAcs, 4, true` |
| 14 | Pin | DmTextBoxNumeric | `txtPinCode` | `0, false, 6, false` |
| 15 | State | DmTextBox | `txtState` | `100, false` |
| 16 | Party Type(GST) | DmTextHelp | `txtHPartyType` | `KeyField_PrpAcs, 4, true, true` |
| 17 | GSTIN | DmTextBox | `txtGstTin` | `15, false` |
| 18 | GSTIN Date | DmTextBox | `txtGstDate` | `11, false` |
| 19 | AADHAAR | DmTextBoxNumeric | `txtAadhar` | `0, false, 12, false` |
| 20 | PAN | DmTextBox | `txtPAN` | `10, false` |
| 21 | Zone | DmTextHelp | `txtHZone` | `KeyField_PrpAcs, 4` |
| 22 | Phone No. | DmTextBox | `txtPhoneNo` | `35, false` |
| 23 | Mobile No. | DmTextBox | `txtMobileNo` | `35, false` |
| 24 | Fax No. | DmTextBox | `txtFaxNo` | `35, false` |
| 25 | EMail | DmTextBox | `txtEMail` | `250, false` |
| 26 | TDS Category | DmTextHelp | `txtHTDSCategory` | `KeyField_PrpAcs, 4` |
| 27 | Invoice Dis. | DmTextBoxNumeric | `txtInvoiceDiscount` | `2, false, 8, false` |
| 28 | Credit Limit | DmTextBoxNumeric | `txtCreditLimit` | `DmTextBoxNumeric.DecimalNature.DEFAULT, false, 10, false` |
| 29 | Credit Period | DmTextBoxNumeric | `txtDueDays` | `0, false, 5, false` |
| 30 | TIN | DmTextBox | `txtTIN` | `20, false` |
| 31 | Duplicate | DmTextHelp | `txtHDuplicate` | `KeyField_PrpAcs, 4, true, false` |
| 32 | IEC No. | DmTextBox | `txtIECNo` | `35, false` |
| 33 | Ledger Group | DmTextHelp | `txtHLedgerGroup` | `KeyField_PrpAcs, 4` |
| 34 | Registration No. | DmTextBox | `txtRegistration` | `20, false` |
| 35 | Remark | DmRemarkTextBox | `txtRemark` | `250, false` |

Other widgets (not in tab list): `dmFind` (DmFind), `grdBankDetail` (DmGrid), `grdContactPerson` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHAccountNamePrefix`: Name [Text, w0, NAME]
- `txtHAccountGroup`: Group [Text, w0, GroupName]; Nature [Text, w150, NatureName]
- `txtHCostCenter`: Name [Text, w0, NAME]
- `txtHCity`: City [Text, w0, CityName]; State [Text, w100, stateName]; Country [Text, w100, countryName]
- `txtHZone`: Zone [Text, w0, NAME]
- `txtHNature`: Name [Text, w1, Nature]
- `txtHPartyCategory`: Name [Text, w0, Name]
- `txtHTDSCategory`: TDS Category [Text, w0, NAME]
- `txtHReferencedTo`: Name [Text, w0, NAME]; City [Text, w150, CityName]; Code [Text, w150, ManualCode]
- `txtHDuplicate`: Type [Text, w0, Type]
- `txtHLedgerGroup`: Ledger Group [Text, w0, NAME]
- `txtHPartyType`: Type [Text, w0, Type]
- `dmFind`: Customer Name [Text, w200, customerName]; Display Name [Text, w200, displayName]; Manual Code [Text, w100, Manual_Code]; Account Group [Text, w150, Ac_Group]; Nature [Text, w100, Nature]; Address [Text, w150, Address]; City [Text, w100, City]; Country [Text, w100, Country]; Zone [Text, w100, Zone]; PIN [Text, w100, Pin]; Phone [Text, w120, Phone]; Mobile [Text, w120, Mobile]; Email [Text, w150, E_Mail]; FAX [Text, w150, Fax]; Cost Center [Text, w100, Cost_Center]; TIN [Text, w100, TIN]; Referenced To [Text, w100, Distributor]
- `grdBankDetail`: Account No. [Text, w150]; Bank [Text, w200]; Branch [Text, w200]; Branch Address [Text, w350]; IFSC Code [Text, w130]; Swift No. [Text, w130]; Default [Text, w55]
- `grdContactPerson`: Contact Person [Text, w500]; Mobile No. [Text, w200]; LandLine No. [Text, w150]; Designation [Text, w300]; Default [Text, w65]

**Lookup / suggestion / find sources (server RPC called):**
- `txtName` → `getAccountNameSuggestion`
- `txtHAccountNamePrefix` → `getTxtHAccountNamePrefix`
- `txtHAccountGroup` → `getTxtHAccountGroup`
- `txtHCostCenter` → `getTxtHCostCenter`
- `txtHCity` → `getTxtHCity`
- `txtHZone` → `getTxtHZone`
- `txtHPartyCategory` → `getTxtHPartyCategory`
- `txtHTDSCategory` → `getTxtHTDSCategory`
- `txtHReferencedTo` → `getTxtHReferencedTo`
- `txtHDuplicate` → `getTxtHDuplicate`
- `txtHLedgerGroup` → `getTxtHLedgerGroup`
- `txtHPartyType` → `getTxtHPartyType`
- `txtHNature` → `getTxtHNature`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `CustomerNamePrefix` ← DmShared.xNull(txtHAccountNamePrefix.getText())
- `SharedKeyFieldName` ← DmShared.xNull(txtName.getText())
- `DisplayName` ← DmShared.xNull(txtDisplayName.getText())
- `ManualCode` ← DmShared.xNull(txtManualCode.getText())
- `AccGroup` ← DmShared.xNull(txtHAccountGroup.getTag())
- `AccGroupName` ← DmShared.xNull(txtHAccountGroup.getText())
- `NatureCode` ← DmShared.xNull(txtHNature.getTag())
- `NatureName` ← DmShared.xNull(txtHNature.getText())
- `CostCenter` ← DmShared.xNull(txtHCostCenter.getTag())
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
- `PartyCategory` ← DmShared.xNull(txtHPartyCategory.getTag())
- `PartyCategoryName` ← DmShared.xNull(txtHPartyCategory.getText())
- `TdsCategory` ← DmShared.xNull(txtHTDSCategory.getTag())
- `ReferencedTo` ← DmShared.xNull(txtHReferencedTo.getTag())
- `Pan` ← DmShared.xNull(txtPAN.getText()).toUpperCase()
- `Tin` ← DmShared.xNull(txtTIN.getText())
- `Duplicate` ← DmShared.xNull(txtHDuplicate.getTag())
- `CreditLimit` ← DmClient.vNull(txtCreditLimit.getText(), "0.00")
- `CreditPeriod` ← DmClient.vNull(txtDueDays.getText(), "0")
- `IecNo` ← DmShared.xNull(txtIECNo.getText())
- `LedgerGroup` ← DmShared.xNull(txtHLedgerGroup.getTag())
- `PartyType` ← DmShared.xNull(txtHPartyType.getTag())
- `RegistrationNo` ← DmShared.xNull(txtRegistration.getText())
- `Remark` ← DmShared.xNull(txtRemark.getText())
- `InvoiceDiscount` ← DmClient.vNull(txtInvoiceDiscount.getText(), "0.00")
- `Aadhar` ← DmShared.xNull(txtAadhar.getText())
- `GsTINDate` ← DmShared.dNull(txtGstDate.getText())
- `GsTin` ← DmShared.xNull(txtGstTin.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtHAccountNamePrefix.setText` ← data.getCustomerNamePrefix()
- `txtHAccountNamePrefix.setTag` ← data.getCustomerNamePrefix()
- `txtName.setText` ← data.getSharedKeyFieldName()
- `txtDisplayName.setText` ← data.getDisplayName()
- `txtManualCode.setText` ← data.getManualCode()
- `txtHAccountGroup.setText` ← data.getAccGroupName()
- `txtHAccountGroup.setTag` ← data.getAccGroup()
- `txtHNature.setText` ← data.getNatureName()
- `txtHNature.setTag` ← data.getNatureCode()
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
- `txtHPartyCategory.setText` ← data.getPartyCategoryName()
- `txtHPartyCategory.setTag` ← data.getPartyCategory()
- `txtHTDSCategory.setText` ← data.getTdsCategoryName()
- `txtHTDSCategory.setTag` ← data.getTdsCategory()
- `txtHReferencedTo.setText` ← data.getReferencedToName()
- `txtHReferencedTo.setTag` ← data.getReferencedTo()
- `txtPAN.setText` ← data.getPan()
- `txtTIN.setText` ← data.getTin()
- `txtAadhar.setText` ← data.getAadhar()
- `txtGstDate.setText` ← data.getGsTINDate()
- `txtGstTin.setText` ← data.getGsTin()
- `txtHDuplicate.setTag` ← data.getDuplicate()
- `txtCreditLimit.setText` ← String.valueOf(data.getCreditLimit())
- `txtDueDays.setText` ← String.valueOf(data.getCreditPeriod())
- `txtIECNo.setText` ← data.getIecNo()
- `txtHLedgerGroup.setText` ← data.getLedgerGroupName()
- `txtHLedgerGroup.setTag` ← data.getLedgerGroup()
- `txtInvoiceDiscount.setText` ← data.getInvoiceDiscount()
- `txtHPartyType.setTag` ← data.getPartyType()
- `txtRemark.setText` ← data.getRemark()
- `txtRegistration.setText` ← data.getRegistrationNo()
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**On New click:**
- `clear()`
- `onAdd()`
- `txtManualCode.setEnabled(false)`
- `txtState.setEnabled(false)`
- `txtHAccountNamePrefix.setFocus(true)`
- `txtRemark.setEnabled(true)`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`gui.addCallback` → `getGUITemplate()`
```java
return defaultValue.getGuiTemplate();
```

`gui.addCallback` → `addCustom()`
```java
return null;
```

`gui.addCallback` → `guiDataCopied()`
```java
if (gui_Mdl != null) {
gui.initialize(gui_Mdl);
```

`topControl.addNewHandeller` → `newClicked()`
```java
clear();
txtManualCode.setEnabled(false);
txtState.setEnabled(false);
txtHAccountNamePrefix.setFocus(true);
txtRemark.setEnabled(true);
gui.initializeViaTemplateCode();
onAdd();
```

`topControl.addEditHandeller` → `editClicked()`
```java
txtName.setFocus(true);
txtManualCode.setEnabled(true);
txtState.setEnabled(false);
txtRemark.setEnabled(true);
grdBankDetail.getGrid().getStore().add(new GrdBankDetail_Mdl());
grdContactPerson.getGrid().getStore().add(new GrdContactPerson_Mdl());
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(CustomerMaster.this);
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
SrvAsync.invokeRPC(blnCallBackWait).getTxtHAccountGroup(DmShared.xNull(defaultValue.getAcGroupCustomerCode()), helper, b
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

`txtHPartyCategory.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHPartyCategory(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHPartyCategory).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
try {
if (result.isStatus()) {
txtHPartyCategory.setData(result.getData(), result.getPaging());
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), txtHPartyCategory).show();
```

`txtHPartyCategory.addTextHelpCallBack` → `onSuccess()`
```java
try {
if (result.isStatus()) {
txtHPartyCategory.setData(result.getData(), result.getPaging());
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), txtHPartyCategory).show();
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

**Top control options:** setRefreshRequired(false), setDisplay(true)

_Print permission "P" is removed for this screen (replace "P" with "*")._
- Confirm: "Are you sure? You want to delete this record."
- Alert: "Due days can't be greater then 32767"
- Alert: "Can be used in browse mode only."

## Files
| file | lines |
|---|---|
| client/CustomerMaster.java | 1159 |
| client/CustomerMaster_Dsg.java | 1144 |
| server/CustomerMaster_SrvImpl.java | 1430 |
| shared/CustomerMaster_Mdl.java | 473 |
| shared/CustomerMaster_Srv.java | 52 |
| shared/CustomerMaster_SrvAsync.java | 52 |
| shared/CustomerMaster_Vld.java | 63 |
| shared/DefaultValue_Mdl.java | 96 |
| shared/Find_Mdl.java | 418 |
| shared/GrdBankDetail_Mdl.java | 77 |
| shared/GrdContactPerson_Mdl.java | 50 |
| shared/GrdTdsCategory_Mdl.java | 41 |
| shared/TxtHAccountGroup_Mdl.java | 32 |
| shared/TxtHCity_Mdl.java | 59 |
| shared/TxtHReferencedTo_Mdl.java | 31 |
| shared/TxtHVatClause_Mdl.java | 22 |

## UI labels / columns (client)
`Customer Name`, `Display Name`, `Manual Code`, `A/c Group`, `Nature`, `Cost Center`, `Party Category`, `Referenced To`, `Address`, `City`, `Pin`, `State`, `Party Type(GST)`, `GSTIN`, `GSTIN Date`, `AADHAAR`, `PAN`, `Zone`, `Phone No.`, `Mobile No.`, `Fax No.`, `EMail`, `TDS Category`, `Invoice Dis.`, `Credit Limit`, `Credit Period`, `TIN`, `Duplicate`, `IEC No.`, `Ledger Group`, `Registration No.`, `Remark`, `Bank Account Detail`, `Contact Person Detail`

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
| String | guiTemplate |
| String | acGroupCustomerCode |
| String | acGroupCustomerName |
| boolean | gstApplicable |
| DmFileRuleType | fileRuleType |

### CustomerMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | tempKeyCode |
| String | customerNamePrefix |
| String | displayName |
| String | manualCode |
| String | accGroup |
| String | accGroupName |
| String | natureCode |
| String | natureName |
| String | costCenter |
| String | costCenterName |
| String | guiConfigCode |
| String | address |
| String | address1 |
| String | city |
| String | cityName |
| String | stateName |
| String | stateNumericCode |
| String | zone |
| String | zoneName |
| String | pin |
| String | phoneNo |
| String | mobileNo |
| String | faxNo |
| String | email |
| String | partyCategory |
| String | partyCategoryName |
| String | tdsCategory |
| String | tdsCategoryName |
| String | referencedTo |
| String | referencedToName |
| String | pan |
| String | tin |
| String | duplicate |
| String | creditLimit |
| String | creditPeriod |
| String | iecNo |
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
| DmGUI_Mdl | guiMdl |
| boolean | gstApplicable |
| List<GrdBankDetail_Mdl> | grdBankDetail |
| List<GrdContactPerson_Mdl> | listContectPerson |

### TxtHVatClause_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | VatNonVat |

### Find_Mdl
| type | field |
|---|---|
| String | code |
| String | customerNamePrefix |
| String | customerName |
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
| String | stateNumericCode |
| String | countryCode |
| String | countryName |

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
| getMoveRec | String strKey | DmResult<CustomerMaster_Mdl> |
| saveRecord | CustomerMaster_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey, String guiConfigCode | DmResult<String> |
| getTxtHAccountNamePrefix | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAccountGroup | String customerCode, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHAccountGroup_Mdl>> |
| getTxtHCostCenter | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHCity | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHCity_Mdl>> |
| getTxtHZone | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPartyCategory | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTDSCategory | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHReferencedTo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHReferencedTo_Mdl>> |
| getTxtHDuplicate | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHLedgerGroup | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPartyType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getTxtHNature | String customerCode, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| getTempKeyCode |  | String |
| getServerDateTime |  | String[] |

## Validator `CustomerMaster_Vld`
```java
public class CustomerMaster_Vld {
    public static void chkSave(CustomerMaster_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getSharedKeyFieldName(), "name", 100);
        DmShared.isValidText(mrTemp.getDisplayName(), "Display name", 200);
        DmShared.isValidText(mrTemp.getAccGroup(), "A/c group", 300);
        DmShared.isValidText(mrTemp.getNatureCode(), "nature", 500);
        DmShared.isValidEMail(mrTemp.getEmail(), 400);
        DmShared.isValidAmount(mrTemp.getCity(), "City", 450);
        DmShared.isValidText(mrTemp.getPartyType(), "Party Type", 600);
        if (!DmShared.xNull(mrTemp.getPan()).trim().equalsIgnoreCase("")) {
            if (DmShared.xNull(mrTemp.getPan()).length() != 10) {
                throw new DmException(950, "PAN No should be 10 digit.");
            } else {
                DmShared.isValidPanNo(mrTemp.getPan(), 950);
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
        if (!DmShared.xNull(mrTemp.getAadhar()).trim().equalsIgnoreCase("")) {
            if (DmShared.xNull(mrTemp.getAadhar()).length() != 12) {
                throw new DmException(925, "AADHAAR No. should be 12 digit.");
            }
        }
    }
}
```

## Server `CustomerMaster_SrvImpl.java` (1430 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getConnectionMongo`, `DmServer.getFieldUDT`, `DmServer.getSerialNoBigInt`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnFileDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteGSTIN`, `DmServer.getSsnUserName`, `DmServer.getUserWithDateTime`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `Select sg.SubCode As SearchCode, sg.Name From SubGroup sg where sg.filterNature = :filterNature Order By Name`
- Params: `:filterNature` ← `DmServer.getCodeViaManualCode("CT", "manualCode", "subgroupNature", "Code", cnTemp)`

### `getAccountNameSuggestion`
`public DmResultPageing<List<String>> getAccountNameSuggestion(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select sg.Name as NAME From SubGroup sg Left Join subgroupNature sgn on sg.filterNature=sgn.code where sg.filterNature=:filterNature`
- Params: `:filterNature` ← `DmServer.getCodeViaManualCode("CT", "manualCode", "subgroupNature", "Code", cnTemp)`

### `getMoveRec`
`public DmResult<CustomerMaster_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT SG.SubCode,SG.Initial AS InitialName,Sg.Name AS CustomerName,SG.IECCode,SG.isDuplicateTIN,SG.DisplayName, SG.GroupCode,AG.GroupName, SG.ManualCode, SG.Nature as NatureCode , sgn1.Name as NatureName,SG.Add1,SG.Add2, Sg.CityCode,C.cityName,Ctry.Name AS CountryName,sm.name as stateName, sm.numericCode,SG.PIN, SG.Phone, SG.registrationNo, SG.Mobile,SG.Fax,CCM.Name AS CCName,SG.CostCenter, SG.remark,SG.PAN,SG.EMail,SG.TINNo,SG.CreditLimit,SG.DueDays, SG.PartyCat,SG.PartyType, sg.guiConfigCode, SG.TDS_Catg,TC.Name AS TCName,SG.LedgerGroup,LG.Name AS LGName, SG.Zone,ZM.Name AS ZMName,SG.Distributor,SG1.Name AS DName, SG.U_Name, SG.preparedBy,SG.preparedDt,SG.u_EntDt,SG.isActive, SG.invoiceDiscount,SG.aadhaarNo,SG.gsTinDate,SG.gsTIN FROM SubGroup SG LEFT JOIN SubGroup2 SG2 ON SG2.SubCode=SG.SubCode LEFT JOIN AcGroup AG ON AG.GroupCode=SG.GroupCode LEFT JOIN City C ON C.CityCode=SG.CityCode LEFT JOIN Country Ctry ON C.countryCode=Ctry.Code LEFT JOIN stateMast sm ON C.stateCode=sm.Code LEFT JOIN CostCenterMast CCM ON CCM.Code=SG.CostCenter LEFT JOIN TDSCat TC ON TC.Code=SG.TDS_Catg LEFT JOIN LedgerGroup LG ON LG.Code=SG.LedgerGroup LEFT JOIN ZoneMast ZM ON ZM.Code=SG.Zone Left Join subgroupNature sgn1 on SG.Nature=sgn1.code LEFT JOIN SubGroup SG1 ON SG1.SubCode=SG.Distributor WHERE SG.subCode = :code and sg.filterNature=:filterNature`
- SQL 2: `SELECT subCode,sNo,bankName,accountNo,branch,swiftCode,isDefault,ifscCode,branchAddress FROM bankDetail WHERE subCode = :code ORDER BY sNo`
- SQL 3: `SELECT code,contactPerson,mobile,phone AS landLineNo,designation,isDefault FROM addressBook where subCode= :code order by contactPerson`
- Params: `:code` ← `DmShared.iNull(strKey).toString()`; `:filterNature` ← `DmServer.getCodeViaManualCode("CT", "manualCode", "subgroupNature", "Code", cnTemp)`; `:code` ← `strKey`; `:code` ← `strKey`
- Other calls: `DmGUI_Server.getGUIViaConfigCode`

### `saveRecord`
`public DmResult<String> saveRecord(CustomerMaster_Mdl mrTemp)`

- SQL 1: `Select Count(*) As Cnt From SubGroup Where Name = :name and SubCode<> :subCode`
- SQL 2: `Select Count(*) As Cnt From SubGroup where ManualCode = :manualCode and SubCode<> :subCode`
- SQL 3: `Insert Into SubGroup(SubCode,Name,GroupCode,ManualCode,Nature,Add1,Add2,CityCode,PIN,registrationNo, Phone,Mobile,FAX,Remark,isDuplicateTIN,preparedDt,CostCenter,PAN,EMail,TINNo,CreditLimit,DueDays,PartyCat,PartyType,IECCode,TDS_Catg,Zone,LedgerGroup,Distributor,Site_Code,DisplayName,Initial, preparedBy,filterNature,invoiceDiscount, aadhaarNo,gsTinDate,gsTIN, guiConfigCode) Values (:subCode, :name, :groupCode, :manualCode, :nature, :add1, :add2, :cityCode, :pin,:registrationNo, :phone, :mobile, :fax,:remark, :duplicateTIN, :preparedDt, :costCenter, :pan, :email,:tinNo, :creditLimit, :dueDays, :partyCat, :partyType, :iecCode,:tdsCatg, :zone, :ledgerGroup, :distributor, :siteCode, :displayName, :initial, :preparedBy,:filterNature, :invoiceDiscount,:aadhaarNo,:gsTinDate,:gsTIN, :guiConfigCode)`
- SQL 4: `update SubGroup Set Name=:name,GroupCode=:groupCode,ManualCode=:ManualCode,Nature=:Nature,Add1=:Add1,Add2=:Add2,CityCode=:CityCode,PIN=:pin,Phone=:Phone,Mobile=:Mobile,FAX=:FAX,Remark=:Remark,CostCenter=:CostCenter,U_Name=:U_Name,U_EntDt=:uEntDt,PAN=:PAN,EMail=:EMail,TINNo=:TINNo,PartyCat=:PartyCat,PartyType=:PartyType,IECCode=:IECCode,CreditLimit=:CreditLimit,DueDays=:DueDays,Zone=:Zone,LedgerGroup=:LedgerGroup,Distributor=:Distributor,DisplayName = :DisplayName,Initial=:Initial,transferDt=:transferDt,registrationNo=:registrationNo,TDS_Catg=:TDSCatg, invoiceDiscount=:invoiceDiscount,aadhaarNo=:aadhaarNo, gsTIN=:gsTIN,gsTinDate=:gsTinDate Where SubCode=:SubCode`
- SQL 5: `INSERT INTO subgroupsitelist (subCode, site_Code) VALUES (:subCode, :siteCode) ;`
- SQL 6: `Insert Into SubGroup2 (SubCode) Values (:subCode)`
- Params: `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:subCode` ← `mrTemp.getSharedKeyFieldCode()`; `:manualCode` ← `mrTemp.getManualCode()`; `:subCode` ← `mrTemp.getSharedKeyFieldCode()`; `:tinNo` ← `mrTemp.getTin()`; `:subCode` ← `mrTemp.getSharedKeyFieldCode()`; `:subCode` ← `mrTemp.getSharedKeyFieldCode()`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:guiConfigCode` ← `DmShared.foriegnKey(mrTemp.getGuiConfigCode())`; `:groupCode` ← `DmShared.foriegnKey(mrTemp.getAccGroup())`; `:manualCode` ← `DmShared.foriegnKey(mrTemp.getManualCode())`; `:nature` ← `DmShared.foriegnKey(mrTemp.getNatureCode())`; `:add1` ← `DmShared.xNull(mrTemp.getAddress())`; `:add2` ← `DmShared.xNull(mrTemp.getAddress1())`; `:filterNature` ← `DmShared.foriegnKey(filterNature)`; `:cityCode` ← `DmShared.foriegnKey(mrTemp.getCity())`; `:pin` ← `DmShared.xNull(mrTemp.getPin())`; `:phone` ← `mrTemp.getPhoneNo()`; `:mobile` ← `mrTemp.getMobileNo()`; `:fax` ← `DmShared.xNull(mrTemp.getFaxNo())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:duplicateTIN` ← `DmServer.vNull(mrTemp.getDuplicate(), "0")`; `:preparedBY` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:costCenter` ← `DmShared.foriegnKey(mrTemp.getCostCenter())`; `:pan` ← `DmShared.xNull(mrTemp.getPan().toUpperCase())`; `:email` ← `mrTemp.getEmail()`; `:tinNo` ← `DmShared.xNull(mrTemp.getTin())`; `:creditLimit` ← `DmServer.vNull(mrTemp.getCreditLimit(), "0.00")`; `:dueDays` ← `DmServer.vNull(mrTemp.getCreditPeriod(), "0")`; `:partyCat` ← `DmShared.foriegnKey(mrTemp.getPartyCategory())`; `:partyType` ← `mrTemp.getPartyType()`; `:iecCode` ← `mrTemp.getIecNo()`; `:tdsCatg` ← `DmShared.foriegnKey(mrTemp.getTdsCategory())`; `:zone` ← `DmShared.foriegnKey(mrTemp.getZone())`; `:ledgerGroup` ← `DmShared.foriegnKey(mrTemp.getLedgerGroup())`; `:distributor` ← `DmShared.foriegnKey(mrTemp.getReferencedTo())`; `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:displayName` ← `DmShared.xNull(mrTemp.getDisplayName())`; `:initial` ← `DmShared.xNull(mrTemp.getCustomerNamePrefix())`; `:registrationNo` ← `DmShared.xNull(mrTemp.getRegistrationNo())`; `:invoiceDiscount` ← `DmServer.vNull(mrTemp.getInvoiceDiscount(), "0.00")`; `:aadhaarNo` ← `DmShared.xNull(mrTemp.getAadhar())`; `:gsTinDate` ← `DmServer.convertDate(mrTemp.getGsTINDate())`; `:gsTIN` ← `DmShared.xNull(mrTemp.getGsTin().toUpperCase())`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:groupCode` ← `DmShared.foriegnKey(mrTemp.getAccGroup())`; `:ManualCode` ← `DmShared.foriegnKey(mrTemp.getManualCode())`; `:Nature` ← `DmShared.foriegnKey(mrTemp.getNatureCode())`; `:Add1` ← `DmShared.xNull(mrTemp.getAddress())`; `:Add2` ← `DmShared.xNull(mrTemp.getAddress1())`; `:CityCode` ← `DmShared.foriegnKey(mrTemp.getCity())`; `:pin` ← `DmShared.xNull(mrTemp.getPin())`; `:Phone` ← `mrTemp.getPhoneNo()`; `:Mobile` ← `mrTemp.getMobileNo()`; `:FAX` ← `DmShared.xNull(mrTemp.getFaxNo())`; `:Remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:CostCenter` ← `DmShared.foriegnKey(mrTemp.getCostCenter())`; `:U_Name` ← `DmServer.getSsnUserName(this)`; `:PAN` ← `DmShared.xNull(mrTemp.getPan().toUpperCase())`; `:EMail` ← `mrTemp.getEmail()`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:TINNo` ← `DmShared.xNull(mrTemp.getTin())`; `:PartyCat` ← `mrTemp.getPartyCategory()`; `:PartyType` ← `mrTemp.getPartyType()`; `:IECCode` ← `mrTemp.getIecNo()`; `:CreditLimit` ← `DmServer.vNull(mrTemp.getCreditLimit(), "0.00")`; `:DueDays` ← `DmServer.vNull(mrTemp.getCreditPeriod(), "0")`; `:Zone` ← `DmShared.foriegnKey(mrTemp.getZone())`; `:LedgerGroup` ← `DmShared.foriegnKey(mrTemp.getLedgerGroup())`; `:Distributor` ← `DmShared.foriegnKey(mrTemp.getReferencedTo())`; `:DisplayName` ← `DmShared.xNull(mrTemp.getDisplayName())`; `:Initial` ← `DmShared.xNull(mrTemp.getCustomerNamePrefix())`; `:transferDt` ← `null`; `:TDSCatg` ← `DmShared.foriegnKey(mrTemp.getTdsCategory())`; `:registrationNo` ← `DmShared.xNull(mrTemp.getRegistrationNo())`; `:SubCode` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:invoiceDiscount` ← `DmServer.vNull(mrTemp.getInvoiceDiscount(), "0.00")`; `:aadhaarNo` ← `DmShared.xNull(mrTemp.getAadhar())`; `:gsTinDate` ← `DmServer.convertDate(mrTemp.getGsTINDate())`; `:gsTIN` ← `DmShared.xNull(mrTemp.getGsTin().toUpperCase())`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getSharedKeyFieldCode())`; `:siteCode` ← `DmShared.foriegnKey(siteCode)`; `:subCode` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`
- Errors: "Invalid key." | "TIN Should Be Greater Than 11(Eleven) Characters." | "PAN Should Be Of 10(Ten) Characters." | "Duplicate Party Name Not Allowed !!!" | "Duplicate Manual Code Not Allowed !!!" | "Duplicate TIN Not Allowed !!!" | "Manual Code is mandatory."
- Other calls: `CustomerMaster_Vld.chkSave`, `DmFileManager_Server.manageLinkFileCollection`, `DmGUI_Server.deleteViaConfigCode`, `DmGUI_Server.saveViaConfigCode`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, String guiConfigCode)`

- SQL 1: `select count(*) as cnt from addressBook where subCode = :subCode`
- SQL 2: `Delete From bankDetail Where SubCode = :SubCode`
- SQL 3: `Delete From Subgroup2 Where SubCode = :SubCode`
- SQL 4: `Delete From subgroupsitelist Where subCode= :SubCode`
- SQL 5: `Delete From Subgroup Where SubCode = :SubCode`
- SQL 6: `SELECT lfc.sNo, lfc.ruleCode FROM linkFileCollection lfc WHERE docid = :subCode`
- Params: `:subCode` ← `DmShared.iNull(strKey).toString()`; `:SubCode` ← `DmShared.iNull(strKey).toString()`; `:SubCode` ← `DmShared.iNull(strKey).toString()`; `:SubCode` ← `DmShared.iNull(strKey).toString()`; `:SubCode` ← `DmShared.iNull(strKey).toString()`; `:subCode` ← `DmShared.iNull(strKey).toString()`
- Errors: "Record is already in use You can't delete this record."
- Other calls: `DmGUI_Server.deleteViaConfigCode`

### `getTxtHAccountNamePrefix`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAccountNamePrefix(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT CODE,NAME FROM initialmast`

### `getTxtHAccountGroup`
`public DmResultPageing<List<TxtHAccountGroup_Mdl>> getTxtHAccountGroup(String customerCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ag.groupCode as GroupCode, ag.groupName as GroupName ,ag.Nature as NatureCode , sgn.name as NatureName FROM acGroup ag left join subgroupNature sgn on ag.nature= sgn.code WHERE (ag.groupCode = :customerCode OR ISNULL(ag.groupUnder,0) = :customerCode)`
- Params: `:customerCode` ← `DmShared.xNull(customerCode)`

### `getTxtHCostCenter`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCostCenter(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT CODE ,NAME FROM CostCenterMast where isActive=1`

### `getTxtHCity`
`public DmResultPageing<List<TxtHCity_Mdl>> getTxtHCity(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select cityCode,cityName,stateCode,sm.name as stateName,sm.numericCode, countryCode,ctr.name as countryName from city ct left join stateMast sm on ct.stateCode = sm.code left join country ctr on ct.countryCode = ctr.code`

### `getTxtHZone`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHZone(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ZM.Code, ZM.Name FROM zonemast ZM`

### `getTxtHPartyCategory`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHPartyCategory(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHTDSCategory`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTDSCategory(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT CODE,NAME FROM TdsCat`

### `getTxtHReferencedTo`
`public DmResultPageing<List<TxtHReferencedTo_Mdl>> getTxtHReferencedTo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT SG.SubCode,SG.Name AS NAME,isNull(CT.CityName,'') AS CityName,SG.ManualCode FROM SubGroup SG LEFT JOIN City CT ON CT.CityCode=SG.CityCode WHERE sg.filterNature=:filterNature`
- Params: `:filterNature` ← `DmServer.getCodeViaManualCode("CT", "manualCode", "subgroupNature", "Code", cnTemp)`

### `getTxtHDuplicate`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDuplicate(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHLedgerGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHLedgerGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select Code,Name From LedgerGroup`

### `getTxtHPartyType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHPartyType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `DmPartyType.values`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select SG.SubCode,SG.ManualCode As Manual_Code,SG.Name As customerName, SG.Name As displayName, CONCAT(isNull(SG.Add1,'') ,' ', isNull(SG.Add2,'')) AS Address,isNull(CityName,'') As City,isNull(Ctry.Name,'') As Country, isNull(ZN.Name,'') As Zone,SG.PIN,isNull(SG.Phone,'') As Phone,isNull(SG.Mobile,'') As Mobile, isNull(SG.EMail,'') As E_Mail,isNull(SG.Fax,'') As Fax,AG.GroupName As Ac_Group,sgn.name as Nature, isNull(CCM.Name,'') As Cost_Center,SG.TINNo As TIN, isNull(SG1.Name,'') As Distributor From Subgroup SG Left Join City C On C.CityCode=SG.CityCode Left Join Country Ctry On C.countryCode=Ctry.Code Left Join ZoneMast ZN On ZN.Code=SG.Zone Left Join AcGroup AG On AG.GroupCode=SG.GroupCode Left Join CostCenterMast CCM On CCM.Code=SG.CostCenter Left join subgroupNature sgn on SG.nature= sgn.code Left Join SubGroup SG1 On SG1.SubCode=SG.Distributor where SG.filterNature=:filterNature`
- Params: `:filterNature` ← `DmServer.getCodeViaManualCode("CT", "manualCode", "subgroupNature", "Code", cnTemp)`

### `getTxtHNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHNature(String customerCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sgn.Code, sgn.Name as Nature FROM subgroupNature sgn left join acGroup ag on ag.nature= sgn.code WHERE (ag.groupCode = :customerCode OR ISNULL(ag.groupUnder,0) = :customerCode)`
- Params: `:customerCode` ← `DmShared.xNull(customerCode)`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`

- SQL 1: `select guiTemplateCode from voucher_Type where category=' {…} '`
- SQL 2: `SELECT vt.v_Type, vt.description FROM voucher_type vt WHERE vt.category = ' {…} '`
- SQL 3: `select ec.acGroupCustomerCode, acg7.groupName as acGroupCustomerName from enviroCompany ec Left join acGroup acg7 on ec.acGroupCustomerCode =acg7.groupCode`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmFileManager.shared`, `dataman.dmBase.dmFileManagerMongo.server`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFileManager.client`, `dataman.dmToolkit.dmFileManager.server`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGUI.client`, `dataman.dmToolkit.dmGUI.server`, `dataman.dmToolkit.dmGUI.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.manageSite.client`, `dataman.dmToolkit.self.client`, `dataman.libraryMenu.self.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stStatic.server`

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
| deleteRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Record is already in use You can't delete this record." |

## Notes (hand-written)
_No hand-written notes yet._
