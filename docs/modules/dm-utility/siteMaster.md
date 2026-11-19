# dm-utility / siteMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/utility/src/siteMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): From, siteMast
- Duplicate checks on save:
  - `Select Count(Name) As Cnt From SiteMast S Where S.PLTCode = :PLTCode And S.BranchCode = :BranchCode And Code <> :Code`
  - `Select Count(Name) As Cnt From SiteMast S Where S.Name = :Name And Code <> :Code`
  - `Select Count(ManualCode) As Cnt From SiteMast S Where S.ManualCode = :ManualCode And Code <> :Code`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Branch Name (Text); Regional Language (Text); Product License Key (Text); Site Name (Text); Site Display Name (Text); Site Code (Text); Head Office (Text); Manual Code (Text); Registration Type (GST) (Text); PAN (Text); Country (Text); GSTIN No. (Text); GSTIN Date (Text); Site A/c (Text); rule: PAN should be of 10(Ten) characters.; rule: State is mandatory.; rule: GST country code is mandatory; rule: TIN No. and TIN date both are required.; rule: In case of unregistered party type, you should not fill GSTIN No. and GSTIN Date.; rule: GSTIN Date should not be greater than current date.
- Lookups/suggestions: getTxtHBranch, getTxtHPLT, getTxtHHeadOffice, getTxtHRegionalLanguage, getTxtHRegistrationType, getTxtHState, getTxtHAcName, getTxtHCountry
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `SiteMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Branch Name | DmTextHelp | `txtHBranch` | `DmTxtHBranch_PrpAcs, 10` |
| 2 | Regional Language | DmTextHelp | `txtHRegionalLanguage` | `KeyField_PrpAcs, 6, true` |
| 3 | Product Licensed To | DmTextHelp | `txtHProductLicenseKey` | `DmTxtHPLT_PrpAcs, 8` |
| 4 | Site Name | DmTextBox | `txtSiteName` | `100, true` |
| 5 | Site Display Name | DmTextBox | `txtSiteDisplayName` | `100, true` |
| 6 | Cilent Code | DmTextBox | `txtClientCode` | `10, false` |
| 7 | Legal Name | DmTextBox | `txtLegalName` | `100, true` |
| 8 | Trade Name | DmTextBox | `txtTradeName` | `100, false` |
| 9 | Site A/c | DmTextHelp | `txtHSiteAc` | `txtHSiteAc_PrpAcs, 7, false` |
| 10 | Site Code | DmTextBoxNumeric | `txtCode` | `DmTextBoxNumeric.DecimalNature.ROUNDOFF, false, 4, true` |
| 11 | Head Office | DmTextHelp | `txtHHeadOffice` | `KeyField_PrpAcs, 3` |
| 12 | Manual Code | DmTextBox | `txtManualCode` | `2, true` |
| 13 | Site Description | DmTextBox | `txtSiteDescription` | `100, false` |
| 14 | Address | DmTextBox | `txtAddress1` | `50, false` |
| 15 | - | DmTextBox | `txtAddress2` | `50, false` |
| 16 | City | DmTextBox | `txtCity` | `50, false` |
| 17 | PIN | DmTextBoxNumeric | `txtPinNo` | `DmTextBoxNumeric.DecimalNature.DEFAULT, false, 6, false` |
| 18 | State | DmTextHelp | `txtHState` | `DmState_PrpAcs, 5, true` |
| 19 | Country | DmTextHelp | `txtHCountry` | `DmCountry_PrpAcs, 5, true` |
| 20 | Registration Type {GST) | DmTextHelp | `txtHRegistrationType` | `KeyField_PrpAcs, 3, true, true` |
| 21 | Gram | DmTextBox | `txtGram` | `50, false` |
| 22 | Phone No. | DmTextBox | `txtPhone` | `30, false` |
| 23 | Mobile No. | DmTextBoxNumeric | `txtMobileNo` | `0, false, 10, false` |
| 24 | Email | DmTextBox | `txtEmail` | `50, false` |
| 25 | FAX No. | DmTextBox | `txtFaxNo` | `25, false` |
| 26 | TIN | DmTextBox | `txtTinNo` | `12, false` |
| 27 | Date | DmTextBox | `txtTinNoDate` | `12, false` |
| 28 | GSTIN | DmTextBox | `txtGSTin` | `15, false` |
| 29 | GSTIN Date | DmTextBox | `txtGSTINDate` | `11, false` |
| 30 | PAN | DmTextBox | `txtPanNo` | `10, false` |
| 31 | TAN | DmTextBox | `txtTanNo` | `10, false` |
| 32 | IE Code | DmTextBox | `txtIECode` | `20, false` |
| 33 | RBI Code | DmTextBox | `txtRBICode` | `20, false` |
| 34 | CIN | DmTextBox | `txtCin` | `21, false` |
| 35 | Serial Key | DmTextBox | `txtSerialKey` | `25, false` |
| 36 | Notification No. | DmTextBox | `txtNotificationNo` | `20, false` |

Other widgets (not in tab list): `txtDrugLicenseNo` (DmTextBox), `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `txtHProductLicenseKey`: Name [Text, w0, Comp_Name]; Short Name [Text, w100, SName]
- `txtHBranch`: Name [Text, w0, Name]; City Name [Text, w0, City]
- `txtHHeadOffice`: Name [Text, w0, Name]
- `txtHRegionalLanguage`: Name [Text, w0, Name]
- `txtHState`: Name [Text, w0, Name]; State Code [Text, w100, numericCode]
- `txtHCountry`: Name [Text, w200, Name]; GST Code [Text, w60, gstCode]
- `txtHRegistrationType`: Name [Text, w0, Name]
- `txtHSiteAc`: Name [Text, w0, name]; Manual Code [Text, w80, manualCode]
- `dmFind`: Site Name [Text, w100, Name]; Code [Text, w100, Code]; Comapny Name [Text, w200, CompanyName]; Branch Name [Text, w200, BranchName]; Short Name [Text, w200, SName]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHBranch` → `getTxtHBranch`
- `txtHProductLicenseKey` → `getTxtHPLT`
- `txtHHeadOffice` → `getTxtHHeadOffice`
- `txtHRegionalLanguage` → `getTxtHRegionalLanguage`
- `txtHRegistrationType` → `getTxtHRegistrationType`
- `txtHState` → `getTxtHState`
- `txtHCountry` → `getTxtHCountry`
- `txtHSiteAc` → `getTxtHAcName`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `BranchCode` ← DmShared.xNull(txtHBranch.getTag())
- `BranchName` ← DmShared.xNull(txtHBranch.getText())
- `PltCode` ← DmShared.xNull(txtHProductLicenseKey.getTag())
- `PltName` ← DmShared.xNull(txtHProductLicenseKey.getText())
- `SiteName` ← DmShared.xNull(txtSiteName.getText())
- `Code` ← DmShared.xNull(txtCode.getText())
- `RegionalLanguageCode` ← DmShared.xNull(txtHRegionalLanguage.getTag())
- `RegionalLanguageName` ← DmShared.xNull(txtHRegionalLanguage.getText())
- `HoCode` ← DmShared.xNull(txtHHeadOffice.getTag())
- `HoName` ← DmShared.xNull(txtHHeadOffice.getText())
- `ManualCode` ← DmShared.xNull(txtManualCode.getText())
- `SiteDescription` ← DmShared.removeSpace(DmShared.xNull(txtSiteDescription.getText()))
- `Address1` ← DmShared.xNull(txtAddress1.getText())
- `Address2` ← DmShared.xNull(txtAddress2.getText())
- `City` ← DmShared.xNull(txtCity.getText())
- `PinNO` ← DmShared.xNull(txtPinNo.getText())
- `Country` ← DmShared.xNull(txtHCountry.getText())
- `GstCountryCode` ← DmShared.xNull(txtHCountry.getTag())
- `Gram` ← DmShared.xNull(txtGram.getText())
- `Phone` ← DmShared.xNull(txtPhone.getText())
- `FaxNo` ← DmShared.xNull(txtFaxNo.getText())
- `Email` ← DmShared.xNull(txtEmail.getText())
- `NotificationNo` ← DmShared.xNull(txtNotificationNo.getText())
- `TinNo` ← DmShared.xNull(txtTinNo.getText())
- `TinDate` ← DmShared.dNull(txtTinNoDate.getText())
- `PanNo` ← DmShared.xNull(txtPanNo.getText())
- `GsTin` ← DmShared.xNull(txtGSTin.getText())
- `Cin` ← DmShared.xNull(txtCin.getText())
- `GstinDate` ← DmShared.dNull(txtGSTINDate.getText())
- `StateName` ← DmShared.xNull(txtHState.getText())
- `StateNumericCode` ← DmClient.vNull(txtHState.getTag(), "00")
- `RegistrationTypeCode` ← DmShared.xNull(txtHRegistrationType.getTag())
- `DrugLicenseNo` ← DmShared.xNull(txtDrugLicenseNo.getText())
- `ClientCode` ← DmShared.xNull(txtClientCode.getText())
- `SerialKey` ← DmShared.xNull(txtSerialKey.getText())
- `SiteDisplayName` ← DmShared.removeSpace(DmShared.xNull(txtSiteDisplayName.getText()))
- `IeCode` ← DmShared.xNull(txtIECode.getText())
- `RbiCode` ← DmShared.xNull(txtRBICode.getText())
- `TradeName` ← DmShared.removeSpace(DmShared.xNull(txtTradeName.getText()))
- `LegalName` ← DmShared.removeSpace(DmShared.xNull(txtLegalName.getText()))
- `MobileNo` ← DmShared.xNull(txtMobileNo.getText())
- `TanNo` ← DmShared.xNull(txtTanNo.getText())
- `SiteAcName` ← DmShared.xNull(txtHSiteAc.getText())
- `SiteAcCode` ← DmShared.xNull(txtHSiteAc.getTag())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `userControl.setTextBlog` ← preparedByBlog, data.getPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getModifiedBy()
- `txtHBranch.setTag` ← data.getBranchCode()
- `txtHBranch.setText` ← data.getBranchName()
- `txtHRegionalLanguage.setTag` ← data.getRegionalLanguageCode()
- `txtHRegionalLanguage.setText` ← data.getRegionalLanguageName()
- `txtHProductLicenseKey.setText` ← data.getPltName()
- `txtHProductLicenseKey.setTag` ← data.getPltCode()
- `txtHProductLicenseKey.setTag2nd` ← data.getPltShortName()
- `txtSiteName.setText` ← data.getSiteName()
- `txtCode.setText` ← data.getCode()
- `txtHHeadOffice.setTag` ← data.getHoCode()
- `txtHHeadOffice.setText` ← data.getHoName()
- `txtManualCode.setText` ← data.getManualCode()
- `txtSiteDescription.setText` ← data.getSiteDescription()
- `txtAddress1.setText` ← data.getAddress1()
- `txtAddress2.setText` ← data.getAddress2()
- `txtCity.setText` ← data.getCity()
- `txtPinNo.setText` ← data.getPinNO()
- `txtHCountry.setText` ← DmShared.xNull(data.getCountry())
- `txtHCountry.setTag` ← DmShared.xNull(data.getGstCountryCode())
- `txtGram.setText` ← data.getGram()
- `txtPhone.setText` ← data.getPhone()
- `txtFaxNo.setText` ← data.getFaxNo()
- `txtEmail.setText` ← data.getEmail()
- `txtNotificationNo.setText` ← data.getNotificationNo()
- `txtTinNo.setText` ← data.getTinNo()
- `txtTinNoDate.setText` ← data.getTinDate()
- `txtPanNo.setText` ← data.getPanNo()
- `txtGSTin.setText` ← data.getGsTin()
- `txtCin.setText` ← data.getCin()
- `txtGSTINDate.setText` ← data.getGstinDate()
- `txtHState.setText` ← data.getStateName()
- `txtHState.setTag` ← data.getStateNumericCode()
- `txtHRegistrationType.setText` ← data.getRegistrationTypeName()
- `txtHRegistrationType.setTag` ← data.getRegistrationTypeCode()
- `txtDrugLicenseNo.setText` ← data.getDrugLicenseNo()
- `txtClientCode.setText` ← data.getClientCode()
- `txtSerialKey.setText` ← data.getSerialKey()
- `txtSiteDisplayName.setText` ← data.getSiteDisplayName()
- `txtIECode.setText` ← data.getIeCode()
- `txtRBICode.setText` ← data.getRbiCode()
- `txtMobileNo.setText` ← data.getMobileNo()
- `txtLegalName.setText` ← data.getLegalName()
- `txtTradeName.setText` ← data.getTradeName()
- `txtHSiteAc.setText` ← DmShared.xNull(data.getSiteAcName())
- `txtHSiteAc.setTag` ← DmShared.xNull(data.getSiteAcCode())
- `txtTanNo.setTag` ← DmShared.xNull(data.getTanNo())
- userControl.setTextBlog(preparedByBlog, data.getPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getModifiedBy())

**On New click:**
- `clear()`
- `txtHBranch.setFocus(true)`
- `txtSiteName.setEnabled(false)`
- `txtCity.setEnabled(false)`
- `txtHHeadOffice.setTag("0")`
- `txtHHeadOffice.setText("No")`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
txtHBranch.setFocus(true);
txtSiteName.setEnabled(false);
txtCity.setEnabled(false);
txtHHeadOffice.setTag("0");
txtHHeadOffice.setText("No");
clear();
```

`topControl.addEditHandeller` → `editClicked()`
```java
txtHHeadOffice.setFocus(true);
txtSiteName.setEnabled(false);
txtCity.setEnabled(false);
txtCode.setEnabled(false);
txtHBranch.setEnabled(false);
txtHProductLicenseKey.setEnabled(false);
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(SiteMaster.this);
```

`txtHBranch.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHBranch(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHBra
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHBranch).show();
public void onSuccess(DmResultPageing<List<TxtHBranch_Mdl>> result) {
if (result.isStatus()) {
txtHBranch.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHBranch).show();
```

`txtHBranch.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHBranch.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHBranch).show();
```

`txtHBranch.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus) {
if (tRow != null) {
txtCity.setText(tRow.getCityName());
setSiteName();
```

`txtHProductLicenseKey.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHPLT(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHPLT_Md
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHProductLicenseKey).show();
public void onSuccess(DmResultPageing<List<TxtHPLT_Mdl>> result) {
if (result.isStatus()) {
txtHProductLicenseKey.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHProductLicenseKey).show();
```

`txtHProductLicenseKey.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHProductLicenseKey.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHProductLicenseKey).show();
```

`txtHProductLicenseKey.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus) {
if (t1Row != null) {
txtHProductLicenseKey.setTag2nd(t1Row.getShortName());
setSiteName();
```

`txtHHeadOffice.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHHeadOffice(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Key
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHHeadOffice).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHHeadOffice.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHHeadOffice).show();
```

`txtHHeadOffice.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHHeadOffice.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHHeadOffice).show();
```

`txtHHeadOffice.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus) {
```

`txtHRegionalLanguage.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHRegionalLanguage(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<Li
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHRegionalLanguage).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHRegionalLanguage.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHRegionalLanguage).show();
```

`txtHRegionalLanguage.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHRegionalLanguage.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHRegionalLanguage).show();
```

`txtHRegionalLanguage.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus) {
```

`txtHRegistrationType.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHRegistrationType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<Li
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHRegistrationType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHRegistrationType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHRegistrationType).show();
```

`txtHRegistrationType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHRegistrationType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHRegistrationType).show();
```

`txtHState.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHState(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyField
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHState).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHState.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHState).show();
```

`txtHState.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHState.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHState).show();
```

`txtHCountry.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHCountry(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFie
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHCountry).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHCountry.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHCountry).show();
```

`txtHCountry.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHCountry.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHCountry).show();
```

`txtHSiteAc.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHAcName(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFiel
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSiteAc).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHSiteAc.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSiteAc).show();
```

`txtHSiteAc.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSiteAc.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSiteAc).show();
```

`txtHSiteAc.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
```

**Top control options:** setOtherRequired(false), setRefreshRequired(false), setDisplay(true)

_Print permission "P" is removed for this screen (replace "P" with "*")._
- Confirm: "Are you sure? You want to delete this record."

## Files
| file | lines |
|---|---|
| client/SiteMaster.java | 761 |
| client/SiteMaster_Dsg.java | 799 |
| server/SiteMaster_SrvImpl.java | 1102 |
| shared/Find_Mdl.java | 68 |
| shared/SiteMaster_Mdl.java | 463 |
| shared/SiteMaster_Srv.java | 39 |
| shared/SiteMaster_SrvAsync.java | 39 |
| shared/SiteMaster_Vld.java | 126 |
| shared/TxtHBranch_Mdl.java | 22 |
| shared/TxtHPLT_Mdl.java | 22 |

## UI labels / columns (client)
`Branch Name`, `Regional Language`, `Product Licensed To`, `Site Name`, `Site Display Name`, `Cilent Code`, `Legal Name`, `Trade Name`, `Site A/c`, `Site Code`, `Head Office`, `Manual Code`, `Site Description`, `Address`, `City`, `PIN`, `State`, `Country`, `Registration Type {GST)`, `Gram`, `Phone No.`, `Mobile No.`, `Email`, `FAX No.`, `TIN`, `Date`, `GSTIN`, `GSTIN Date`, `PAN`, `TAN`, `IE Code`, `RBI Code`, `CIN`, `Serial Key`, `Notification No.`

## Models (shared DTOs)
### SiteMaster_Mdl (extends `KeyField implements Serializable`)
| type | field |
|---|---|
| String | regionalLanguageCode |
| String | regionalLanguageName |
| String | branchName |
| String | branchCode |
| String | pltCode |
| String | pltName |
| String | pltShortName |
| String | siteCode |
| String | siteName |
| String | code |
| String | hoCode |
| String | hoName |
| String | manualCode |
| String | siteDescription |
| String | address1 |
| String | address2 |
| String | city |
| String | pinNO |
| String | country |
| String | gstCountryCode |
| String | gram |
| String | phone |
| String | faxNo |
| String | email |
| String | notificationNo |
| String | tinNo |
| String | tinDate |
| String | panNo |
| String | preparedBy |
| String | modifiedBy |
| String | gsTin |
| String | cin |
| String | gstinDate |
| String | stateName |
| String | stateNumericCode |
| String | registrationTypeCode |
| String | registrationTypeName |
| String | currentDate |
| String | drugLicenseNo |
| String | clientCode |
| String | serialKey |
| String | siteDisplayName |
| String | ieCode |
| String | rbiCode |
| String | tradeName |
| String | legalName |
| String | mobileNo |
| String | siteAcCode |
| String | siteAcName |
| String | tanNo |

### TxtHBranch_Mdl (extends `KeyField implements Serializable`)
| type | field |
|---|---|
| String | cityName |

### TxtHPLT_Mdl (extends `KeyField implements Serializable`)
| type | field |
|---|---|
| String | shortName |

### Find_Mdl (extends `KeyField implements Serializable`)
| type | field |
|---|---|
| String | srlNo |
| String | codeValue |
| String | siteName |
| String | companyName |
| String | branchName |
| String | shortName |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<SiteMaster_Mdl> |
| saveRecord | SiteMaster_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey, String pltCode | DmResult<String> |
| getTxtHBranch | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHBranch_Mdl>> |
| getTxtHRegionalLanguage | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPLT | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHPLT_Mdl>> |
| getTxtHHeadOffice | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getTxtHRegistrationType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHState | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHCountry | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAcName | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getServerDateTime |  | String[] |

## Validator `SiteMaster_Vld`
```java
public class SiteMaster_Vld {
    public enum RegisteredType {
        COMPOSITE("C", "Composite"), REGISTERED("R", "Registered"), UNREGISTERED("U", "Unregistered");
        private final String id;
        private final String value;
        public String getId() {
            return id;
        }
        public String getValue() {
            return value;
        }
        RegisteredType(String id, String text) {
            this.id = id;
            this.value = text;
        }
        public static RegisteredType getKeyOf(String id) {
            RegisteredType returnRegis = null;
            if (id.equalsIgnoreCase(REGISTERED.getId())) {
                returnRegis = REGISTERED;
            } else if (id.equalsIgnoreCase(UNREGISTERED.getId())) {
                returnRegis = UNREGISTERED;
            } else if (id.equalsIgnoreCase(COMPOSITE.getId())) {
                returnRegis = COMPOSITE;
            }
            return returnRegis;
        }
        public static RegisteredType getValueOf(String text) {
            RegisteredType returnRegis = null;
            if (text.equalsIgnoreCase(REGISTERED.getValue())) {
                returnRegis = REGISTERED;
            } else if (text.equalsIgnoreCase(UNREGISTERED.getValue())) {
                returnRegis = UNREGISTERED;
            } else if (text.equalsIgnoreCase(COMPOSITE.getValue())) {
                returnRegis = COMPOSITE;
            }
            return returnRegis;
        }
    }
    public static void chkSave(SiteMaster_Mdl mrTemp) {
        Boolean flag = false;
        DmShared.isValidText(mrTemp.getBranchCode(), "Branch Name ", 100);
        DmShared.isValidText(mrTemp.getRegionalLanguageCode(), "Regional Language ", 200);
        DmShared.isValidText(mrTemp.getPltCode(), "Product License Key", 300);
        DmShared.isValidText(mrTemp.getSiteName(), "Site Name ", 400);
        DmShared.isValidText(mrTemp.getSiteDisplayName(), "Site Display Name", 450);
        DmShared.isValidText(mrTemp.getCode(), "Site Code ", 500);
        DmShared.isValidText(mrTemp.getHoCode(), "Head Office ", 600);
        DmShared.isValidText(mrTemp.getManualCode(), " Manual Code ", 700);
        DmShared.isValidText(mrTemp.getRegistrationTypeCode(), "Registration Type (GST)", 770);
        DmShared.isValidEMail(mrTemp.getEmail(), 800);
        DmShared.isValidMobile(mrTemp.getMobileNo(), 650);
        if (!DmShared.xNull(mrTemp.getPanNo()).trim().equalsIgnoreCase("")) {
            if (DmShared.xNull(mrTemp.getPanNo()).length() != 10) {
                throw new DmException(850, "PAN should be of 10(Ten) characters.");
            } else {
                DmShared.isValidPanNo(mrTemp.getPanNo(), 850);
            }
        }
        if (!DmShared.xNull(mrTemp.getGsTin()).equalsIgnoreCase("")) {
            DmShared.isValidText(mrTemp.getPanNo(), "PAN", 850);
        }
        if (DmShared.vNull(mrTemp.getStateNumericCode()).intValue() <= 0) {
            throw new DmException(750, "State is mandatory.");
        }
        DmShared.isValidText(mrTemp.getCountry(), "Country", 760);
        if(DmShared.xNull(mrTemp.getGstCountryCode()).trim().equalsIgnoreCase("")){
            throw new DmException(760, "GST country code is mandatory");
        }
        if (!DmShared.xNull(mrTemp.getTinNo()).equalsIgnoreCase("")) {
            if (DmShared.xNull(mrTemp.getTinDate()).equalsIgnoreCase("")) {
                flag = true;
            }
        } else {
            if (DmShared.xNull(mrTemp.getTinNo()).equalsIgnoreCase("")) {
                if (!DmShared.xNull(mrTemp.getTinDate()).equalsIgnoreCase("")) {
                    flag = true;
                }
            }
        }
        if (flag) {
            throw new DmException(900, "TIN No. and TIN date both are required.");
        }
        if (DmShared.xNull(mrTemp.getRegistrationTypeCode()).equalsIgnoreCase((RegisteredType.REGISTERED.getId()))
                || DmShared.xNull(mrTemp.getRegistrationTypeCode()).equalsIgnoreCase((RegisteredType.COMPOSITE.getId()))) {
            DmShared.isValidText(mrTemp.getGsTin(), "GSTIN No.", 1000);
            DmShared.isValidText(mrTemp.getGstinDate(), "GSTIN Date", 1100);
        }
        if (DmShared.xNull(mrTemp.getRegistrationTypeCode()).equalsIgnoreCase((RegisteredType.REGISTERED.getId()))) {
            DmShared.isValidText(mrTemp.getSiteAcCode(), "Site A/c", 1200);
        }
        if (!DmShared.xNull(mrTemp.getGsTin()).equalsIgnoreCase("")) {
            DmShared.isValidGSTIN(mrTemp.getStateNumericCode(), mrTemp.getPanNo(), mrTemp.getGsTin(), 1000);
        }
        if (DmShared.xNull(mrTemp.getRegistrationTypeCode()).equalsIgnoreCase((RegisteredType.UNREGISTERED.getId()))) {
            if (!DmShared.xNull(mrTemp.getGsTin()).equalsIgnoreCase("") || !DmShared.xNull(mrTemp.getGstinDate()).equalsIgnoreCase("")) {
                throw new DmException(1000, "In case of unregistered party type, you should not fill GSTIN No. and GSTIN Date.");
            }
        }
        if (!DmShared.xNull(mrTemp.getGstinDate()).equalsIgnoreCase("")) {
            if (DmShared.convertDate(mrTemp.getGstinDate()).getTime() > (DmShared.convertDate(mrTemp.getCurrentDate())).getTime()) {
                throw new DmException(1100, "GSTIN Date should not be greater than current date.");
            }
        }
    }
}
```

## Server `SiteMaster_SrvImpl.java` (1102 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnUserName`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `Select Code As SearchCode From SiteMast order By name`

### `getMoveRec`
`public DmResult<SiteMaster_Mdl> getMoveRec(String strKey)`

- SQL 1: `Select S.Code, S.Name,isNull(S.isHO,'') AS ho_Yn,S.Address1, S.Address2,S.City,S.TinNo,S.NotificationNo,S.PanNo ,S.PreparedBy,S.u_name, S.pin,S.ManualCode,S.regionalLanguage, S.phone, S.fax, S.email, S.gram, S.Country,S.TinDate,S.SiteDesc ,s.gsTin,s.cin, S.BranchCode As BCode,S.PLTCode, B.Name As BName , SLT.Comp_Name,SLT.SName, S.gstinDate,S.registrationType, S.stateNumericCode,S.stateName,S.drugLicenseNo ,s.clientCode,s.serialKey, s.siteDisplayName,s.IECode,s.RBICode ,s.tradeName,s.legalName,s.mobile, s.tan, S.gstCountryCode From SiteMast S Left Join BranchMast b on s.BranchCode = b.Code left join ProductLicensedTo SLT on SLT.PLTCode = S.PLTCode Where S.Code= :code order by S.Name`
- SQL 2: `Select s.subCode AS siteAcCode, sg.name as siteAcName From SiteMast S LEFT JOIN subGroup sg ON sg.subCode = s.subCode Where s.code = :code AND sg.subCode IS NOT NULL`
- Params: `:code` ← `strKey`; `:code` ← `strKey`
- Other calls: `RegisteredType.getKeyOf`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, String pltCode)`

- SQL 1: `SELECT * FROM usersite WHERE sitelist LIKE :siteCode`
- SQL 2: `Select CentralData_Path From Company C left Join sys.databases as SD on SD.name = RTrim(LTrim(C.CentralData_Path)) Where PLTCode = :PLTCode And isNull(SD.name,'')<>'' Group By CentralData_Path`
- SQL 3: `Delete From {…} CentralData_Path.dbo.SiteMast Where Code= :Code`
- SQL 4: `Delete From siteMast Where Code= :Code`
- Params: `:siteCode` ← `"%" + strKey + "%"`; `:PLTCode` ← `pltCode`; `:Code` ← `strKey`; `:Code` ← `strKey`
- Errors: "Could not delete, Site is in use."

### `saveRecord`
`public DmResult<String> saveRecord(SiteMaster_Mdl mrTemp)`

- SQL 1: `Select Count(Name) As Cnt From SiteMast S Where S.PLTCode = :PLTCode And S.BranchCode = :BranchCode And Code <> :Code`
- SQL 2: `Select Count(Name) As Cnt From SiteMast S Where S.Name = :Name And Code <> :Code`
- SQL 3: `Select Count(ManualCode) As Cnt From SiteMast S Where S.ManualCode = :ManualCode And Code <> :Code`
- SQL 4: `Insert Into {…} .dbo.SiteMast(Code,BranchCode, PLTCode, Name,isHO,SiteDesc, address1,address2,city,TinNo, NotificationNo,PanNo,pin,ManualCode, Phone, Fax, Email, Gram, Country, gstCountryCode, TinDate, preparedBy, preparedDt,regionalLanguage,gstin,cin,gstinDate,stateNumericCode, stateName,registrationType,drugLicenseNo,clientCode,serialKey,IECode,RBICode,siteDisplayName,legalName,tradeName,mobile, tan, subCode) Values ( :Code,:BranchCode, :PLTCode, :Name,:isHO,:SiteDesc, :address1,:address2,:city,:TinNo, :NotificationNo,:PanNo,:pin,:ManualCode, :Phone, :Fax, :Email, :Gram, :Country, :gstCountryCode, :TinDate,:preparedBy, :preparedDt,:regionalLanguage,:gstin,:cin,:gstinDate,:stateNumericCode, :stateName,:registrationType,:drugLicenseNo,:clientCode,:serialKey,:IECode,:RBICode,:siteDisplayName ,:legalName,:tradeName,:mobile, :tan, :subCode )`
- SQL 5: `Update {…} .dbo.SiteMast Set PLTCode = :PLTCode ,BranchCode= :BranchCode ,Name= :Name , isHO= :isHO ,Pin= :Pin, PanNo= :PanNo ,NotificationNo= :NotificationNo ,tinno= :tinno ,City= :City ,address2= :address2 , ManualCode= :ManualCode ,phone= :phone ,fax= :fax ,email= :email ,gram= :gram, Country= :Country,gstCountryCode= :gstCountryCode,TinDate= :TinDate , u_Name= :u_Name, u_EntDt = :u_EntDt, address1= :address1 ,SiteDesc= :SiteDesc,regionalLanguage= :regionalLanguage,gstin= :gstin,cin= :cin, gstinDate=:gstinDate,stateNumericCode=:stateNumericCode,stateName=:stateName,registrationType=:registrationType, drugLicenseNo=:drugLicenseNo,rbicode=:rbicode,iecode=:iecode,serialKey=:serialKey, siteDisplayName=:siteDisplayName,clientCode=:clientCode , legalName=:legalName, tradeName=:tradeName, mobile=:mobile , tan = :tan, subCode = :subCode Where Code = :Code`
- SQL 6: `Select CentralData_Path From Company C left Join sys.databases as SD on SD.name = RTrim(LTrim(C.CentralData_Path)) Where PLTCode = :PLTCode And isNull(SD.name,'')<>'' Group By CentralData_Path`
- SQL 7: `SELECT COUNT(*) AS cnt FROM {…} CentralData_Path.sys.objects T WHERE T.name = 'SiteMast'`
- SQL 8: `Select Count(Code) as cnt From {…} CentralData_Path.dbo.SiteMast Where Code =:Code`
- Params: `:Code` ← `mrTemp.getCode()`; `:PLTCode` ← `mrTemp.getPltCode()`; `:BranchCode` ← `mrTemp.getBranchCode()`; `:Code` ← `mrTemp.getSharedKeyFieldCode()`; `:Name` ← `mrTemp.getSiteName()`; `:Code` ← `mrTemp.getSharedKeyFieldCode()`; `:ManualCode` ← `mrTemp.getManualCode()`; `:Code` ← `mrTemp.getSharedKeyFieldCode()`; `:Code` ← `mrTemp.getSharedKeyFieldCode()`; `:Code` ← `mrTemp.getSharedKeyFieldCode()`; `:BranchCode` ← `mrTemp.getBranchCode()`; `:PLTCode` ← `mrTemp.getPltCode()`; `:Name` ← `DmShared.xNull(mrTemp.getSiteName())`; `:isHO` ← `(DmShared.iNull(mrTemp.getHoCode()).intValue() == 0 ? "0" : "1")`; `:SiteDesc` ← `DmShared.xNull(mrTemp.getSiteDescription())`; `:address1` ← `DmShared.xNull(mrTemp.getAddress1())`; `:address2` ← `DmShared.xNull(mrTemp.getAddress2())`; `:city` ← `DmShared.xNull(mrTemp.getCity())`; `:TinNo` ← `DmShared.xNull(mrTemp.getTinNo())`; `:NotificationNo` ← `DmShared.xNull(mrTemp.getNotificationNo())`; `:PanNo` ← `DmShared.xNull(mrTemp.getPanNo())`; `:NotificationNo` ← `DmShared.xNull(mrTemp.getNotificationNo())`; `:pin` ← `DmShared.xNull(mrTemp.getPinNO())`; `:ManualCode` ← `DmShared.xNull(mrTemp.getManualCode())`; `:Phone` ← `DmShared.xNull(mrTemp.getPhone())`; `:Fax` ← `DmShared.xNull(mrTemp.getFaxNo())`; `:Email` ← `DmShared.xNull(mrTemp.getEmail())`; `:Gram` ← `DmShared.xNull(mrTemp.getGram())`; `:Country` ← `DmShared.xNull(mrTemp.getCountry())`; `:gstCountryCode` ← `DmShared.foriegnKey(mrTemp.getGstCountryCode())`; `:regionalLanguage` ← `mrTemp.getRegionalLanguageCode()`; `:TinDate` ← `DmServer.convertDate(mrTemp.getTinDate())`; `:gstin` ← `DmShared.xNull(mrTemp.getGsTin().toUpperCase())`; `:cin` ← `DmShared.xNull(mrTemp.getCin())`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:gstinDate` ← `DmServer.convertDate(mrTemp.getGstinDate())`; `:stateNumericCode` ← `DmShared.xNull(mrTemp.getStateNumericCode())`; `:stateName` ← `DmShared.xNull(mrTemp.getStateName())`; `:registrationType` ← `DmShared.xNull(mrTemp.getRegistrationTypeCode())`; `:drugLicenseNo` ← `DmShared.xNull(mrTemp.getDrugLicenseNo())`; `:clientCode` ← `DmShared.xNull(mrTemp.getClientCode())`; `:serialKey` ← `DmShared.xNull(mrTemp.getSerialKey())`; `:IECode` ← `DmShared.xNull(mrTemp.getIeCode())`; `:RBICode` ← `DmShared.xNull(mrTemp.getRbiCode())`; `:siteDisplayName` ← `DmShared.xNull(mrTemp.getSiteDisplayName())`; `:tradeName` ← `DmShared.xNull(mrTemp.getTradeName())`; `:legalName` ← `DmShared.xNull(mrTemp.getLegalName())`; `:mobile` ← `DmShared.xNull(mrTemp.getMobileNo())`; `:tan` ← `DmShared.xNull(mrTemp.getTanNo())`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getSiteAcCode())`; `:PLTCode` ← `mrTemp.getPltCode()`; `:BranchCode` ← `mrTemp.getBranchCode()`; `:Name` ← `DmShared.xNull(mrTemp.getSiteName())`; `:isHO` ← `(DmShared.iNull(mrTemp.getHoCode()).intValue() == 0 ? "0" : "1")`; `:Pin` ← `DmShared.xNull(mrTemp.getPinNO())`; `:PanNo` ← `DmShared.xNull(mrTemp.getPanNo().toUpperCase())`; `:NotificationNo` ← `DmShared.xNull(mrTemp.getNotificationNo())`; `:tinno` ← `DmShared.xNull(mrTemp.getTinNo())`; `:City` ← `DmShared.xNull(mrTemp.getCity())`; `:address2` ← `DmShared.xNull(mrTemp.getAddress2())`; `:ManualCode` ← `DmShared.xNull(mrTemp.getManualCode())`; `:phone` ← `DmShared.xNull(mrTemp.getPhone())`; `:fax` ← `DmShared.xNull(mrTemp.getFaxNo())`; `:email` ← `DmShared.xNull(mrTemp.getEmail())`; `:gram` ← `DmShared.xNull(mrTemp.getGram())`; `:Country` ← `DmShared.xNull(mrTemp.getCountry())`; `:gstCountryCode` ← `DmShared.xNull(DmShared.foriegnKey(mrTemp.getGstCountryCode()))`; `:TinDate` ← `DmServer.convertDate(mrTemp.getTinDate())`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `DmServer.getServerTimestamp()`; `:address1` ← `DmShared.xNull(mrTemp.getAddress1())`; `:SiteDesc` ← `DmShared.xNull(mrTemp.getSiteDescription())`; `:regionalLanguage` ← `DmShared.xNull(mrTemp.getRegionalLanguageCode())`; `:Code` ← `mrTemp.getSharedKeyFieldCode()`; `:gstin` ← `DmShared.xNull(mrTemp.getGsTin().toUpperCase())`; `:cin` ← `DmShared.xNull(mrTemp.getCin())`; `:gstinDate` ← `DmServer.convertDate(mrTemp.getGstinDate())`; `:stateNumericCode` ← `DmShared.xNull(mrTemp.getStateNumericCode())`; `:stateName` ← `DmShared.xNull(mrTemp.getStateName())`; `:registrationType` ← `DmShared.xNull(mrTemp.getRegistrationTypeCode())`; `:drugLicenseNo` ← `DmShared.xNull(mrTemp.getDrugLicenseNo())`; `:clientCode` ← `DmShared.xNull(mrTemp.getClientCode())`; `:serialKey` ← `DmShared.xNull(mrTemp.getSerialKey())`; `:iecode` ← `DmShared.xNull(mrTemp.getIeCode())`; `:rbicode` ← `DmShared.xNull(mrTemp.getRbiCode())`; `:siteDisplayName` ← `DmShared.xNull(mrTemp.getSiteDisplayName())`; `:tradeName` ← `DmShared.xNull(mrTemp.getTradeName())`; `:legalName` ← `DmShared.xNull(mrTemp.getLegalName())`; `:mobile` ← `DmShared.xNull(mrTemp.getMobileNo())`
- Errors: "Manual Code must be alphabet only." | "Code Already Exists." | "Invalid key." | "Combination Of [Product Licensed To + Branch Name] Already Exists." | "Site Name Already Exists." | "Manual Code Already Exists." | "There Should Be Only One Head Office."
- Other calls: `SiteMaster_Vld.chkSave`

### `getTxtHBranch`
`public DmResultPageing<List<TxtHBranch_Mdl>> getTxtHBranch(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT B.Code, B.Name, B.City From BranchMast B`

### `getTxtHPLT`
`public DmResultPageing<List<TxtHPLT_Mdl>> getTxtHPLT(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT PLTCode, Comp_Name,SName From ProductLicensedTo`

### `getTxtHHeadOffice`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHHeadOffice(DmHelper helper, boolean blnToEndOfFile)`


### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select S.Code,S.Name,SLT.Comp_Name as CompanyName,B.Name as BranchName,SLT.SName From SiteMast S Left Join BranchMast B on S.BranchCode = B.Code Left join ProductLicensedTo SLT on SLT.PLTCode = S.PLTCode`

### `getTxtHRegionalLanguage`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHRegionalLanguage(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `BANGALI.toString`, `ENGLISH.toString`, `HINDI.toString`, `MARATHI.toString`, `PUNJABI.toString`

### `getTxtHRegistrationType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHRegistrationType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `RegisteredType.values`

### `getTxtHState`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHState(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT Name,numericCode from stateMast where isnull(numericCode,'0') <> '0'`

### `getTxtHAcName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAcName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subcode as code, sg.name ,sg.manualCode FROM subGroup sg WHERE sg.isActive = 1 AND sg.nature= :nature`
- Params: `:nature` ← `DmServer.getCodeViaManualCode("ST", "manualCode", "subgroupNature", "Code", cnTemp)`

### `getTxtHCountry`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCountry(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT name,gstCode from country where isnull(gstCode,'') <> ''`

## Depends on (imports from other packages)
`dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.libraryMenu.self.shared`, `dataman.utility.siteMaster.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| deleteRecord | (unconditional) | "Could not delete, Site is in use." |
| saveRecord | `if (!DmShared.xNull(mrTemp.getManualCode()).trim().matches("[a-z A-Z]+"))` | "Manual Code must be alphabet only." |
| saveRecord | `if (rsTemp.getLong("Cnt") > 0)` | "Code Already Exists." |
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (rsTemp.getLong("Cnt") > 0)` | "Combination Of [Product Licensed To + Branch Name] Already Exists." |
| saveRecord | `if (rsTemp.getLong("Cnt") > 0)` | "Site Name Already Exists." |
| saveRecord | `if (rsTemp.getLong("Cnt") > 0)` | "Manual Code Already Exists." |
| saveRecord | `if (rsTemp.getLong("Cnt") > 0)` | "There Should Be Only One Head Office." |

## Notes (hand-written)
_No hand-written notes yet._
