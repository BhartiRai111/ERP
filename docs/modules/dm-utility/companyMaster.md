# dm-utility / companyMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/utility/src/companyMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: Company
- Update tables: Company, ProductLicensedTo
- Delete cascade (in order): Company
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Product License (Text); Current Year (Text); Previous Year (Text); Year Start Date (Text); Year End Date (Text); Voucher Prefix (Text); Database Name (Text); File Database Name (Text); Latest Financial Year (Text); rule: Year End Date must be greater than Year Start Date.
- Lookups/suggestions: getTxtHProductLicensekey, getTxtHCity
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `CompanyMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Product Licensed To | DmTextHelp | `txtHProductLicenseKey` | `TxtHProductLicenseKey_PrpAcs, 10, true` |
| 2 | Company Code | DmTextBox | `txtCompanyCode` | `3, false` |
| 3 | Short Name | DmTextBox | `txtShortName` | `9, false` |
| 4 | Current Year | DmTextBox | `txtCurrentYear` | `9, true` |
| 5 | Previous Year | DmTextBox | `txtPreviousYear` | `9, true` |
| 6 | Year Start Date | DmTextBox | `txtYearStartDate` | `15, true` |
| 7 | Year End Date | DmTextBox | `txtYearEndDate` | `15, true` |
| 8 | Voucher Prefix | DmTextBoxNumeric | `txtVoucherPrefix` | `0, false, 2, true` |
| 9 | Latest Financial Year | DmTextHelp | `txtHLatFinancial` | `KeyField_PrpAcs, 3, true` |
| 10 | Database Name | DmTextBox | `txtDatabaseName` | `100, true` |
| 11 | File Database Name | DmTextBox | `txtFileDatabaseName` | `100, true` |
| 12 | Address | DmTextBox | `txtAddress` | `100, false` |
| 13 | - | DmTextBox | `txtAddress1` | `100, false` |
| 14 | City | DmTextHelp | `txtHCity` | `txtHCity_PrpAcs, 7` |
| 15 | State | DmTextBox | `txtState` | `100, false` |
| 16 | PIN | DmTextBoxNumeric | `txtPinCode` | `DmTextBoxNumeric.DecimalNature.ROUNDOFF, false, 6, false` |

Other widgets (not in tab list): `dmFind` (DmFind), `dmGrd` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHProductLicenseKey`: Name [Text, w0, Comp_Name]; Short Name [Text, w0, SName]
- `dmFind`: Name [Text, w1, Name]; Year Start Date [Text, w150, start_Dt]; Year End Date [Text, w180, end_Dt]; Financial Year [Text, w150, cYear]
- `txtHLatFinancial`: Type [Text, w0, Name]
- `txtHCity`: City [Text, w0, CityName]; State [Text, w125, stateName]; Country [Text, w100, countryName]
- `dmGrd`: Company Name [Text, w125]; Current Year [Text, w75]; Previous Year [Text, w80]; Strat Date [Text, w80]; End Date [Text, w80]; Voucher Prefix [Text, w90]; Database Name [Text, w120]; File Db Name [Text, w90]; Lat.Fin.Year [Text, w90]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHProductLicenseKey` → `getTxtHProductLicensekey`
- `txtHLatFinancial` → `getYesNo`
- `txtHCity` → `getTxtHCity`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `ProductLicenseKyCode` ← DmShared.xNull(txtHProductLicenseKey.getTag())
- `ProductLicenseKyName` ← DmShared.xNull(txtHProductLicenseKey.getText())
- `CompanyCode` ← DmShared.xNull(txtCompanyCode.getText())
- `ShortName` ← DmShared.xNull(txtShortName.getText())
- `CurrentYear` ← DmShared.xNull(txtCurrentYear.getText())
- `PreviousYear` ← DmShared.xNull(txtPreviousYear.getText())
- `YearStartDate` ← DmShared.dNull(txtYearStartDate.getText())
- `YearEndDate` ← DmShared.dNull(txtYearEndDate.getText())
- `VoucherPrefix` ← DmShared.xNull(txtVoucherPrefix.getText())
- `DataBaseName` ← DmShared.xNull(txtDatabaseName.getText().trim())
- `FileDataBaseName` ← DmShared.xNull(txtFileDatabaseName.getText().trim())
- `FinacialYearTag` ← DmShared.xNull(txtHLatFinancial.getTag())
- `Address` ← DmShared.xNull(txtAddress.getText())
- `Address1` ← DmShared.xNull(txtAddress1.getText())
- `CityCode` ← DmShared.xNull(txtHCity.getTag())
- `CityName` ← DmShared.xNull(txtHCity.getText())
- `PinCode` ← DmShared.xNull(txtPinCode.getText())
- `StateName` ← DmShared.xNull(txtState.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtHProductLicenseKey.setText` ← data.getProductLicenseKyName()
- `txtHProductLicenseKey.setTag` ← data.getProductLicenseKyCode()
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- `txtCompanyCode.setText` ← data.getCompanyCode()
- `txtShortName.setText` ← data.getShortName()
- `txtCurrentYear.setText` ← data.getCurrentYear()
- `txtPreviousYear.setText` ← data.getPreviousYear()
- `txtYearStartDate.setText` ← data.getYearStartDate()
- `txtYearEndDate.setText` ← data.getYearEndDate()
- `txtVoucherPrefix.setText` ← data.getVoucherPrefix()
- `txtDatabaseName.setText` ← data.getDataBaseName()
- `txtFileDatabaseName.setText` ← data.getFileDataBaseName()
- `txtHLatFinancial.setTag` ← data.getFinacialYearTag()
- `txtHLatFinancial.setText` ← data.getFinacialYearText()
- `txtAddress.setText` ← data.getAddress()
- `txtAddress1.setText` ← data.getAddress1()
- `txtHCity.setTag` ← data.getCityCode()
- `txtHCity.setText` ← data.getCityName()
- `txtState.setText` ← data.getStateName()
- `txtPinCode.setText` ← data.getPinCode()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**On New click:**
- `clear()`
- `txtHProductLicenseKey.setFocus(true)`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
clear();
txtHProductLicenseKey.setFocus(true);
```

`topControl.addEditHandeller` → `editClicked()`
```java
txtHProductLicenseKey.setEnabled(false);
txtCompanyCode.setEnabled(false);
txtShortName.setEnabled(false);
txtCurrentYear.setFocus(true);
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(CompanyMaster.this);
```

`txtHProductLicenseKey.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHProductLicensekey(helper, blnToEndOfFile, "", new AsyncCallback<DmResultPagei
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHProductLicenseKey).show();
public void onSuccess(DmResultPageing<List<TxtHProductLicenseKey_Mdl>> result) {
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
if (blnStatus && tRow != null) {
txtShortName.setText(tRow.getShortName());
fillRecords(tRow.getSharedKeyFieldCode());
```

`txtHLatFinancial.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExte
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHLatFinancial).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHLatFinancial.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHLatFinancial).show();
```

`txtHLatFinancial.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHLatFinancial.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHLatFinancial).show();
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

`dmFind.addHelpGridCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getFind(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Find_Mdl>>>()
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), dmFind.getTextBox()).show();
public void onSuccess(DmResultPageing<List<Find_Mdl>> result) {
if (result.isStatus()) {
dmFind.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), dmFind.getTextBox()).show();
```

`dmFind.addHelpGridCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
dmFind.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), dmFind.getTextBox()).show();
```

`dmFind.addHelpGridCallBack` → `getSelectedRow()`
```java
flwForm.remove(dmFind);
flwForm.add(vrtInnerForm);
if (blnStatus) {
topControlMoveRec(tRow.getSharedKeyFieldCode());
```

**Top control options:** setOtherRequired(false), setRefreshRequired(false), setDisplay(true)

_Print permission "P" is removed for this screen (replace "P" with "*")._
- Confirm: "Are you sure? You want to delete this record."
- Alert: "Voucher Prefix should be less than 32768"

## Files
| file | lines |
|---|---|
| client/CompanyMaster.java | 555 |
| client/CompanyMaster_Dsg.java | 507 |
| server/CompanyMaster_SrvImpl.java | 584 |
| shared/CompanyMaster_Mdl.java | 198 |
| shared/CompanyMaster_Srv.java | 30 |
| shared/CompanyMaster_SrvAsync.java | 42 |
| shared/CompanyMaster_Vld.java | 23 |
| shared/Find_Mdl.java | 41 |
| shared/GrdCompanyMaster_Mdl.java | 86 |
| shared/TxtHCity_Mdl.java | 50 |
| shared/TxtHProductLicenseKey_Mdl.java | 32 |

## UI labels / columns (client)
`Product Licensed To`, `Company Code`, `Short Name`, `Current Year`, `Previous Year`, `Year Start Date`, `Year End Date`, `Voucher Prefix`, `Latest Financial Year`, `Database Name`, `File Database Name`, `Address`, `City`, `State`, `PIN`, `Product To License Details`

## Models (shared DTOs)
### TxtHProductLicenseKey_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | shortName |
| String | latestCompCode |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | yearStartDate |
| String | yearEndDate |
| String | currentFinancialYear |

### CompanyMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | productLicenseKyCode |
| String | productLicenseKyName |
| String | companyCode |
| String | shortName |
| String | currentYear |
| String | previousYear |
| String | yearStartDate |
| String | yearEndDate |
| String | voucherPrefix |
| String | dataBaseName |
| String | fileDataBaseName |
| String | finacialYearTag |
| String | finacialYearText |
| String | Address |
| String | Address1 |
| String | CityName |
| String | CityCode |
| String | StateName |
| String | PinCode |
| List<GrdCompanyMaster_Mdl> | grdData |
| List<Find_Mdl> | findList |

### GrdCompanyMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | currentYear |
| String | previousYear |
| String | yearStart |
| String | yearEnd |
| String | voucherPrefix |
| String | databaseName |
| String | fileDatabaseName |
| String | latestCompCode |

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
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<CompanyMaster_Mdl> |
| saveRecord | CompanyMaster_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey | DmResult<String> |
| getTxtHProductLicensekey | DmHelper pageing, boolean blnToEndOfFile, String strCode | DmResultPageing<List<TxtHProductLicenseKey_Mdl>> |
| fillGrid | String pltCode | DmResult<List<GrdCompanyMaster_Mdl>> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getYesNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHCity | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHCity_Mdl>> |

## Validator `CompanyMaster_Vld`
```java
public class CompanyMaster_Vld {
    public static void chkSave(CompanyMaster_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getProductLicenseKyCode(), "Product License", 100);
        DmShared.isValidText(mrTemp.getCurrentYear(), "Current Year", 200);
        DmShared.isValidText(mrTemp.getPreviousYear(), "Previous Year", 300);
        DmShared.isValidText(mrTemp.getYearStartDate(), "Year Start Date", 400);
        DmShared.isValidText(mrTemp.getYearEndDate(), "Year End Date", 500);
        if (DmShared.convertDate(mrTemp.getYearEndDate()).getTime() <= DmShared.convertDate(mrTemp.getYearStartDate()).getTime()) {
            throw new DmException(600, "Year End Date must be greater than Year Start Date.");
        }
        DmShared.isValidText(mrTemp.getVoucherPrefix().trim(), "Voucher Prefix", 700);
        DmShared.isValidText(mrTemp.getDataBaseName().trim(), "Database Name", 800);
        DmShared.isValidText(mrTemp.getFileDataBaseName().trim(), "File Database Name", 900);
        DmShared.isValidText(mrTemp.getFinacialYearTag().trim(), "Latest Financial Year", 1000);
    }
}
```

## Server `CompanyMaster_SrvImpl.java` (584 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnUserName`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `Select Comp_Code As SearchCode From Company order by Comp_Code`

### `getMoveRec`
`public DmResult<CompanyMaster_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT plt.Comp_Name AS Name, cmp.Comp_Code, plt.sname, cmp.PLTCode, cmp.Start_Dt, cmp.End_Dt , cmp.CYear, cmp.PYear, cmp.V_Prefix, cmp.CentralData_Path, cmp.centralFileData_Path , cmp.u_name, cmp.preparedBy, plt.latestcompCode , cmp.Add1, cmp.Add2, ct.CityName, ct.CityCode, ct.stateCode , sm.name as stateName, ct.CountryCode,Ctry.Name AS CountryName, cmp.Pin FROM Company as cmp LEFT JOIN productLicensedTo as plt on cmp.PLTCode = plt.PLTCode LEFT JOIN {…} .dbo.City ct ON ct.CityCode = cmp.CityCode LEFT JOIN {…} .dbo.Country Ctry ON Ctry.Code = ct.CountryCode LEFT JOIN {…} .dbo.stateMast sm ON sm.Code = ct.stateCode WHERE cmp.Comp_Code= :compCode ORDER BY plt.Comp_Name`
- Params: `:compCode` ← `strKey`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey)`

- SQL 1: `Delete From Company Where Comp_Code= :compCode`
- Params: `:compCode` ← `strKey`

### `saveRecord`
`public DmResult<String> saveRecord(CompanyMaster_Mdl mrTemp)`

- SQL 1: `Insert Into Company(Comp_Code, CentralData_Path, centralFileData_Path, Start_Dt, u_EntDt, CYear,PYear,V_Prefix,PLTCode,preparedBY,End_Dt, ADD1, ADD2, CityCode, PIN) Values( :compid , :databaseName, :centralFileData_Path, :startDt, :u_EntDt , :currentyear ,:previousYear,:voucherPrefix , :pltCode,:preparedBY,:endDt, :address1, :address2, :cityCode, :pinCode )`
- SQL 2: `Update Company Set cyear =:currentyear , Pyear =:previousYear , End_Dt = :endDt , Start_Dt = :startDt , V_Prefix = :voucherPrefix , CentralData_Path =:databaseName , centralFileData_Path =:centralFileData_Path , PLTCode = :pltCode , u_name = :u_name , u_EntDt =:u_EntDt , Add1 = :address1 , Add2 = :address2 , CityCode = :cityCode , Pin = :pinCode Where Comp_Code =:compid`
- SQL 3: `Update ProductLicensedTo set latestCompCode = :latestCompCode Where PLTCode = :PLTCode`
- Params: `:compid` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:databaseName` ← `DmShared.xNull(mrTemp.getDataBaseName())`; `:centralFileData_Path` ← `DmShared.xNull(mrTemp.getFileDataBaseName())`; `:startDt` ← `DmServer.convertDate(mrTemp.getYearStartDate())`; `:u_EntDt` ← `DmServer.getServerTimestamp()`; `:currentyear` ← `DmShared.xNull(mrTemp.getCurrentYear())`; `:previousYear` ← `DmShared.xNull(mrTemp.getPreviousYear())`; `:voucherPrefix` ← `DmShared.xNull(mrTemp.getVoucherPrefix())`; `:pltCode` ← `DmShared.foriegnKey(mrTemp.getProductLicenseKyCode())`; `:preparedBY` ← `DmServer.getSsnUserName(this)`; `:endDt` ← `DmServer.convertDate(mrTemp.getYearEndDate())`; `:address1` ← `DmShared.xNull(mrTemp.getAddress())`; `:address2` ← `DmShared.xNull(mrTemp.getAddress1())`; `:cityCode` ← `DmShared.foriegnKey(mrTemp.getCityCode())`; `:pinCode` ← `DmShared.xNull(mrTemp.getPinCode())`; `:currentyear` ← `DmShared.xNull(mrTemp.getCurrentYear())`; `:previousYear` ← `DmShared.xNull(mrTemp.getPreviousYear())`; `:endDt` ← `DmServer.convertDate(mrTemp.getYearEndDate())`; `:startDt` ← `DmServer.convertDate(mrTemp.getYearStartDate())`; `:voucherPrefix` ← `DmShared.xNull(mrTemp.getVoucherPrefix())`; `:databaseName` ← `DmShared.xNull(mrTemp.getDataBaseName())`; `:centralFileData_Path` ← `DmShared.xNull(mrTemp.getFileDataBaseName())`; `:pltCode` ← `DmShared.foriegnKey(mrTemp.getProductLicenseKyCode())`; `:u_name` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `DmServer.getServerTimestamp()`; `:compid` ← `mrTemp.getSharedKeyFieldCode()`; `:address1` ← `DmShared.xNull(mrTemp.getAddress())`; `:address2` ← `DmShared.xNull(mrTemp.getAddress1())`; `:cityCode` ← `DmShared.foriegnKey(mrTemp.getCityCode())`; `:pinCode` ← `DmShared.xNull(mrTemp.getPinCode())`; `:PLTCode` ← `DmShared.iNull(mrTemp.getProductLicenseKyCode()).toString()`; `:latestCompCode` ← `DmShared.foriegnKey(mrTemp.getSharedKeyFieldCode())`
- Errors: "Invalid key."
- Other calls: `CompanyMaster_Vld.chkSave`

### `getTxtHProductLicensekey`
`public DmResultPageing<List<TxtHProductLicenseKey_Mdl>> getTxtHProductLicensekey(DmHelper helper, boolean blnToEndOfFile, String strCode)`

- SQL 1: `SELECT PLTCode, Comp_Name,latestCompCode, SName FROM ProductLicensedTo`

### `fillGrid`
`public DmResult<List<GrdCompanyMaster_Mdl>> fillGrid(String pltCode)`

- SQL 1: `SELECT plt.Comp_Name, cmp.CentralData_Path, cmp.centralFileData_Path, cmp.Start_Dt, cmp.End_Dt, cmp.CYear, cmp.PYear, cmp.V_Prefix, CONCAT(cmp.Comp_Code, '-', cmp.PLTCode )AS ConcatPLTCode, case when plt.latestCompCode = cmp.comp_Code then 'Yes' else 'No' end as isLatest FROM Company as cmp left join productLicensedTo as plt on cmp.pltCode = plt.pltCode WHERE cmp.PLTCode = :pltCode order by plt.Comp_Name`
- Params: `:pltCode` ← `pltCode`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select Comp_Code As Code, p.Comp_Name As Name, start_Dt,end_Dt, cYear From Company c left join productLicensedTo p on p.pltCode = c.pltCode`

### `getYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHCity`
`public DmResultPageing<List<TxtHCity_Mdl>> getTxtHCity(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT c.cityCode,c.cityName,c.stateCode,sm.name AS stateName,c.countryCode,cn.name AS countryName FROM city AS c LEFT JOIN stateMast AS sm ON c.stateCode = sm.code LEFT JOIN country AS cn ON c.countryCode = cn.Code`

## Depends on (imports from other packages)
`dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.libraryMenu.self.shared`, `dataman.utility.companyMaster.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |

## Notes (hand-written)
_No hand-written notes yet._
