# pb-master / warehouseMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/master/src/warehouseMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: subGroup, warehouseMast, departmentmast, subGroupSiteList
- Update tables: subGroup, warehouseMast, departmentmast
- Delete cascade (in order): subGroupSiteList, departmentmast, subGroup, warehouseMast
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Warehouse Name (Text); Stack (Text); Rack (Text); Active (Text); Legal Name (Text); Trade Name (Text); Address (Text); City (Text); PIN (Text); GSTIN (Text); GSTIN Date (Text); PAN (Text); rule: Define state numeric code in 
- Lookups/suggestions: getSuggestionWarehouse, getTxtHSite, getTxtHYesNo, getTxtHCity
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `SitePopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Site | DmTextHelp | `txtHSite` | `txtHSite_PrpAcs, 7, false` |

**Grid / popup columns** (header, width, db field):
- `txtHSite`: Name [Text, w0, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHSite` → `getTxtHSite`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHSite.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHSite(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSite).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHSite.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSite).show();
```

`txtHSite.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSite.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSite).show();
```

`txtHSite.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
```

### Screen `WarehouseMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Warehouse | DmSuggestBox | `txtWarehouse` | `100, 10, true, "name", DmDataType.STRING` |
| 2 | Stack | DmTextHelp | `txtHStack` | `KeyField_PrpAcs, 5, true` |
| 3 | Rack | DmTextHelp | `txtHRack` | `KeyField_PrpAcs, 5, true` |
| 4 | Legal Name | DmTextBox | `txtLegalName` | `100, false` |
| 5 | Trade Name | DmTextBox | `txtTradeName` | `100, false` |
| 6 | Address | DmTextBox | `txtAddress1` | `50, false` |
| 7 | - | DmTextBox | `txtAddress2` | `50, false` |
| 8 | City | DmTextHelp | `txtHCity` | `TxtHCity_Mdl_PrpAcs, 4, false` |
| 9 | PIN | DmTextBoxNumeric | `txtPinNo` | `DmTextBoxNumeric.DecimalNature.DEFAULT, false, 6, false` |
| 10 | State | DmTextBox | `txtState` | `100, false` |
| 11 | Country | DmTextBox | `txtCountry` | `100, false` |
| 12 | GSTIN | DmTextBox | `txtGSTin` | `15, false` |
| 13 | GSTIN Date | DmTextBox | `txtGSTINDate` | `11, false` |
| 14 | PAN | DmTextBox | `txtPanNo` | `10, false` |
| 15 | Mobile No. | DmTextBoxMobile | `txtMobile` | `false` |
| 16 | Email | DmTextBox | `txtEmail` | `250, false` |

Other widgets (not in tab list): `txtHActive` (DmTextHelp), `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `txtHStack`: Name [Text, w0]
- `txtHRack`: Name [Text, w0]
- `txtHActive`: Name [Text, w0]
- `dmFind`: Warehouse  [Text, w250, name]; LegalName [Text, w200, legalName]; TradeName [Text, w220, tradeName]; CityName [Text, w110, cityName]; Pin [Text, w50, pin]; GSTIN [Text, w110, gsTin]; Email [Text, w150, email]; Mobile [Text, w70, mobile]; Active [Text, w50, isActive]
- `txtHCity`: City [Text, w0, CityName]; State [Text, w100, stateName]; Country [Text, w100, countryName]

**Lookup / suggestion / find sources (server RPC called):**
- `txtWarehouse` → `getSuggestionWarehouse`
- `txtHStack` → `getTxtHYesNo`
- `txtHRack` → `getTxtHYesNo`
- `txtHActive` → `getTxtHYesNo`
- `txtHCity` → `getTxtHCity`
- `sitePopup` → `getSiteGstDetail`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← txtWarehouse.getText().trim()
- `StackTag` ← DmShared.xNull(txtHStack.getTag())
- `StackText` ← DmShared.xNull(txtHStack.getText())
- `RackTag` ← DmShared.xNull(txtHRack.getTag())
- `RackText` ← DmShared.xNull(txtHRack.getText())
- `LegalName` ← DmShared.xNull(txtLegalName.getText()).trim()
- `TradeName` ← DmShared.xNull(txtTradeName.getText()).trim()
- `Address1` ← DmShared.xNull(txtAddress1.getText())
- `Address2` ← DmShared.xNull(txtAddress2.getText())
- `CityCode` ← DmShared.xNull(txtHCity.getTag())
- `CityName` ← DmShared.xNull(txtHCity.getText())
- `StateName` ← DmShared.xNull(txtState.getText())
- `StateNumericCode` ← DmShared.xNull(txtState.getTag())
- `CountryName` ← DmShared.xNull(txtCountry.getText())
- `Pin` ← DmShared.xNull(txtPinNo.getText())
- `GstTin` ← DmShared.xNull(txtGSTin.getText())
- `GstDate` ← DmShared.dNull(txtGSTINDate.getText())
- `Email` ← DmShared.xNull(txtEmail.getText())
- `PanNo` ← DmShared.xNull(txtPanNo.getText())
- `Mobile` ← DmShared.xNull(txtMobile.getText())
- `IsActiveTag` ← DmShared.xNull(txtHActive.getTag())
- `IsActiveText` ← DmShared.xNull(txtHActive.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtWarehouse.setText` ← data.getSharedKeyFieldName()
- `txtHStack.setTag` ← DmShared.xNull(data.getStackTag())
- `txtHStack.setText` ← DmShared.xNull(data.getStackText())
- `txtHRack.setTag` ← DmShared.xNull(data.getRackTag())
- `txtHRack.setText` ← DmShared.xNull(data.getRackText())
- `txtLegalName.setText` ← data.getLegalName()
- `txtTradeName.setText` ← data.getTradeName()
- `txtAddress1.setText` ← data.getAddress1()
- `txtAddress2.setText` ← data.getAddress2()
- `txtHCity.setTag` ← data.getCityCode()
- `txtHCity.setTag2nd` ← data.getCityCode()
- `txtHCity.setText` ← data.getCityName()
- `txtState.setText` ← data.getStateName()
- `txtState.setTag` ← data.getStateNumericCode()
- `txtCountry.setText` ← data.getCountryName()
- `txtPinNo.setText` ← data.getPin()
- `txtGSTin.setText` ← data.getGstTin()
- `txtGSTINDate.setText` ← DmShared.dNull(data.getGstDate())
- `txtEmail.setText` ← data.getEmail()
- `txtMobile.setText` ← data.getMobile()
- `txtPanNo.setText` ← data.getPanNo()
- `txtHActive.setTag` ← DmShared.xNull(data.getIsActiveTag())
- `txtHActive.setText` ← DmShared.xNull(data.getIsActiveText())
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtWarehouse.setEnabled(false)`
- `txtHActive.setEnabled(flag)`
- `txtState.setEnabled(false)`
- `txtCountry.setEnabled(false)`

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
if (!topControl.getMode().equalsIgnoreCase("New")) {
txtWarehouse.setEnabled(false);
txtHActive.setEnabled(flag);
txtState.setEnabled(false);
txtCountry.setEnabled(false);
if (flag) {
lblGSTDetail.getElement().getStyle().setCursor(Style.Cursor.POINTER);
lblGSTDetail.setTitle("Click to copy GST Detail from Site");
lblGSTDetail.getElement().getStyle().setTextDecoration(Style.TextDecoration.UNDERLINE);
lblGSTDetail.getElement().getStyle().setColor("blue");
} else {
lblGSTDetail.getElement().getStyle().setCursor(Style.Cursor.DEFAULT);
lblGSTDetail.setTitle("");
lblGSTDetail.getElement().getStyle().setTextDecoration(Style.TextDecoration.NONE);
lblGSTDetail.getElement().getStyle().setColor("");
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
onAdd();
```

`topControl.addEditHandeller` → `editClicked()`
```java
onEdit();
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(WarehouseMaster.this);
```

`modifyReason.addCallBack` → `onOk()`
```java
try {
WarehouseMaster.this.modifyReasonMdl = modifyReason;
if (topControl.getMode().equalsIgnoreCase("Browse") && DmShared.iNull(topControl.getDocId()).longValue() > 0) {
topControlDelete(topControl.getDocId());
} else {
topControlSave();
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), (DmTextBox) null).show();
```

`txtWarehouse.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getSuggestionWarehouse(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<Lis
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtWarehouse).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtWarehouse.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtWarehouse).show();
```

`txtWarehouse.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtWarehouse.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtWarehouse).show();
```

`txtHStack.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyField
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHStack).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHStack.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHStack).show();
```

`txtHStack.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHStack.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHStack).show();
```

`txtHRack.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyField
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHRack).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHRack.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHRack).show();
```

`txtHRack.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHRack.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHRack).show();
```

`txtHActive.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyField
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHActive).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHActive.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHActive).show();
```

`txtHActive.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHActive.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHActive).show();
```

`txtHCity.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHCity(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHCity_
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
srvAsync.invokeRPC(blnCallBackWait).getFind(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Find_Mdl>>>()
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

`sitePopup.addCallBack` → `onOk()`
```java
sitePopup.hide();
iniFillData(siteCode);
```

`sitePopup.addCallBack` → `onClose()`
```java
txtLegalName.setFocus(true);
```

**Top control options:** setOtherRequired(false), setRefreshRequired(false), setDisplay(true)

_Print permission "P" is removed for this screen (replace "P" with "*")._

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."

## Files
| file | lines |
|---|---|
| client/SitePopup.java | 162 |
| client/SitePopup_Dsg.java | 133 |
| client/WarehouseMaster.java | 765 |
| client/WarehouseMaster_Dsg.java | 492 |
| server/WarehouseMaster_SrvImpl.java | 957 |
| shared/Find_Mdl.java | 158 |
| shared/SitePopup_Mdl.java | 153 |
| shared/TxtHCity_Mdl.java | 50 |
| shared/WarehouseMaster_Mdl.java | 215 |
| shared/WarehouseMaster_Srv.java | 31 |
| shared/WarehouseMaster_SrvAsync.java | 31 |
| shared/WarehouseMaster_Vld.java | 56 |

## UI labels / columns (client)
`Site`, `GST Detail :`, `Warehouse`, `Stack`, `Rack`, `Legal Name`, `Trade Name`, `Address`, `City`, `PIN`, `State`, `Country`, `GSTIN`, `GSTIN Date`, `PAN`, `Mobile No.`, `Email`, `Active`

## Models (shared DTOs)
### SitePopup_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | legalName |
| String | tradeName |
| String | address1 |
| String | address2 |
| String | cityCode |
| String | cityName |
| String | stateName |
| String | stateNumericCode |
| String | CountryNmae |
| String | pin |
| String | gstTin |
| String | GstDate |
| String | email |
| String | mobile |
| String | PanNo |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | stackName |
| String | rackName |
| String | legalName |
| String | tradeName |
| String | address1 |
| String | address2 |
| String | cityCode |
| String | cityName |
| String | stateName |
| String | countryName |
| String | pin |
| String | gstTin |
| String | gstDate |
| String | email |
| String | mobile |
| String | isActive |

### WarehouseMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | rackTag |
| String | rackText |
| String | stackTag |
| String | stackText |
| String | legalName |
| String | tradeName |
| String | address1 |
| String | address2 |
| String | cityCode |
| String | cityName |
| String | stateName |
| String | stateNumericCode |
| String | countryName |
| String | pin |
| String | gstTin |
| String | gstDate |
| String | email |
| String | mobile |
| String | panNo |
| String | isActiveTag |
| String | isActiveText |
| ModifyReason_Mdl | modifyReasonMdl |

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
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getSuggestionWarehouse | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getTxtHYesNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHCity | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHCity_Mdl>> |
| getMoveRec | String strKey | DmResult<WarehouseMaster_Mdl> |
| saveRecord | WarehouseMaster_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl modifyReasonMdl | DmResult<String> |
| getSiteGstDetail | String siteCode | DmResult<SitePopup_Mdl> |

## Validator `WarehouseMaster_Vld`
```java
public class WarehouseMaster_Vld {
    public static void chkSave(WarehouseMaster_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getSharedKeyFieldName().trim(), "Warehouse Name ", 100);
        DmShared.isValidText(mrTemp.getStackTag().trim(), "Stack ", 200);
        DmShared.isValidText(mrTemp.getRackTag().trim(), "Rack ", 300);
        DmShared.isValidText(mrTemp.getIsActiveText().trim(), "Active", 1200);
        boolean flagCheckGstDetail = false;
        if (!DmShared.xNull(mrTemp.getLegalName()).trim().equalsIgnoreCase("")
                || !DmShared.xNull(mrTemp.getTradeName()).trim().equalsIgnoreCase("")
                || !DmShared.xNull(mrTemp.getGstTin()).trim().equalsIgnoreCase("")
                || !DmShared.dNull(mrTemp.getGstDate()).trim().equalsIgnoreCase("")) {
            flagCheckGstDetail = true;
        }
        if (flagCheckGstDetail) {
            DmShared.isValidText(mrTemp.getLegalName(), "Legal Name", 400);
            DmShared.isValidText(mrTemp.getTradeName(), "Trade Name", 600);
            DmShared.isValidText(mrTemp.getAddress1(), "Address", 601);
            DmShared.isValidText(mrTemp.getCityCode(), "City", 700);
            if (DmShared.xNull(mrTemp.getStateNumericCode()).trim().equalsIgnoreCase("")) {
                throw new DmException(700, "Define state numeric code in " + mrTemp.getCityName() + "!");
            }
            DmShared.isValidText(mrTemp.getPin(), "PIN", 800);
        }
        if (!DmShared.xNull(mrTemp.getPanNo()).equalsIgnoreCase("")) {
            DmShared.isValidPanNo(mrTemp.getPanNo(), 1001);
        }
        if (!DmShared.xNull(mrTemp.getMobile()).equalsIgnoreCase("")) {
            DmShared.isValidMobile(mrTemp.getMobile(), 1002);
        }
        if (!DmShared.xNull(mrTemp.getEmail()).equalsIgnoreCase("")) {
            DmShared.isValidEMail(mrTemp.getEmail(), 1100);
        }
        if (!DmShared.xNull(mrTemp.getGstTin()).trim().equalsIgnoreCase("")
                || !DmShared.dNull(mrTemp.getGstDate()).trim().equalsIgnoreCase("")) {
            DmShared.isValidText(mrTemp.getGstTin(), "GSTIN", 900);
            DmShared.isValidText(mrTemp.getGstDate(), "GSTIN Date", 1000);
            DmShared.isValidText(mrTemp.getPanNo(), "PAN", 1001);
            DmShared.isValidGSTIN(mrTemp.getStateNumericCode(), mrTemp.getPanNo(), mrTemp.getGstTin(), 900);
        }
    }
}
```

## Server `WarehouseMaster_SrvImpl.java` (957 lines)
Helpers used: `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getSerialNoInteger`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getUserWithDateTime`, `DmServer.setSsnOldData`, `DmShared.convertDate`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `SELECT whm.code, sgsl.site_Code FROM warehouseMast whm LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = whm.code WHERE sgsl.site_Code = :site_Code ORDER BY whm.code`
- Params: `:site_Code` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `getSuggestionWarehouse`
`public DmResultPageing<List<String>> getSuggestionWarehouse(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT whm.name AS name FROM warehouseMast AS whm`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code, sm.name from siteMast sm`

### `getMoveRec`
`public DmResult<WarehouseMaster_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT whm.code ,whm.name AS name ,whm.stackCode ,whm.rackCode , whm.legalName, whm.tradeName, whm.add1, whm.add2 , whm.cityCode ,ct.cityName as cityName, sm.name as stateName, sm.numericCode AS stateNumericCode, ctr.name as countryName , whm.pin, whm.gsTin, whm.gstinDate, whm.eMail, whm.mobile ,whm.pan , whm.isActive , whm.u_Name as u_Name, whm.preparedBy as preparedBy, whm.preparedByIP , whm.preparedDt, whm.u_name ,whm.u_EntDt FROM warehouseMast AS whm LEFT JOIN city ct on whm.cityCode = ct.cityCode LEFT JOIN stateMast sm on ct.stateCode = sm.code LEFT JOIN country ctr on ct.countryCode = ctr.code WHERE whm.code = :code`
- Params: `:code` ← `strKey`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl modifyReasonMdl)`

- SQL 1: `DELETE FROM subGroupSiteList WHERE subCode= :code`
- SQL 2: `DELETE FROM departmentmast WHERE subCode= :code`
- SQL 3: `DELETE FROM subGroup WHERE subCode= :code`
- SQL 4: `DELETE FROM warehouseMast WHERE code= :code`
- Params: `:code` ← `strKey`; `:code` ← `strKey`; `:code` ← `strKey`; `:code` ← `strKey`

### `saveRecord`
`public DmResult<String> saveRecord(WarehouseMaster_Mdl mrTemp)`

- SQL 1: `INSERT INTO subGroup(subCode ,name ,legalName ,tradeName ,add1 ,add2 ,cityCode ,pin ,mobile ,email ,gsTin ,gstinDate ,pan ,site_Code ,isActive ,preparedBy ,preparedDt ,preparedByIP, nature, groupcode, filterNature ) VALUES (:subCode, :name, :legalName, :tradeName, :add1, :add2, :cityCode, :pin, :mobile, :email, :gsTin, :gstinDate , :pan, :site_Code, :isActive, :preparedBy, :preparedDt, :preparedByIP, :nature, :groupcode, :filterNature )`
- SQL 2: `INSERT INTO warehouseMast( code ,name ,stackCode ,rackCode ,legalName ,tradeName ,add1 ,add2 ,cityCode ,pin ,mobile ,email ,gsTin ,gstinDate ,pan ,site_Code ,isActive ,preparedBy ,preparedDt ,preparedByIP ) VALUES (:code ,:name ,:stackCode ,:rackCode ,:legalName ,:tradeName ,:add1 ,:add2 ,:cityCode ,:pin ,:mobile ,:email ,:gsTin ,:gstinDate ,:pan ,:site_Code ,:isActive ,:preparedBy ,:preparedDt ,:preparedByIP)`
- SQL 3: `Insert Into departmentmast(subCode,nature) Values (:subcode,:nature)`
- SQL 4: `UPDATE subGroup SET legalName = :legalName ,tradeName = :tradeName ,add1 = :add1 ,add2 = :add2 ,cityCode = :cityCode ,pin = :pin ,gsTin = :gsTin ,gstinDate = :gstinDate ,eMail = :eMail ,mobile = :mobile ,pan = :pan ,isActive = :isActive ,u_Name = :uName ,u_EntDt = :u_EntDt WHERE subCode = :subCode`
- SQL 5: `UPDATE warehouseMast SET stackCode = :stackCode ,rackCode = :rackCode ,legalName = :legalName ,tradeName = :tradeName ,add1 = :add1 ,add2 = :add2 ,cityCode = :cityCode ,pin = :pin ,gsTin = :gsTin ,gstinDate = :gstinDate ,eMail = :eMail ,mobile = :mobile ,pan = :pan ,isActive = :isActive ,u_Name = :uName ,u_EntDt = :u_EntDt WHERE code = :code`
- SQL 6: `Update departmentmast Set nature = :nature Where subcode = :code`
- SQL 7: `DELETE FROM subGroupSiteList WHERE subcode = :subcode AND site_Code = :site_Code`
- SQL 8: `INSERT INTO subGroupSiteList(subCode, site_Code) VALUES (:subcode, :siteCode );`
- Params: `:subCode` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:legalName` ← `DmShared.xNull(mrTemp.getLegalName())`; `:tradeName` ← `DmShared.xNull(mrTemp.getTradeName())`; `:add1` ← `DmShared.xNull(mrTemp.getAddress1())`; `:add2` ← `DmShared.xNull(mrTemp.getAddress2())`; `:cityCode` ← `DmShared.foriegnKey(mrTemp.getCityCode())`; `:pin` ← `DmShared.xNull(mrTemp.getPin())`; `:gsTin` ← `mrTemp.getGstTin()`; `:gstinDate` ← `DmServer.convertDate(mrTemp.getGstDate())`; `:pan` ← `DmShared.xNull(mrTemp.getPanNo())`; `:mobile` ← `DmShared.xNull(mrTemp.getMobile())`; `:eMail` ← `DmShared.xNull(mrTemp.getEmail())`; `:site_Code` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`; `:preparedBY` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `timestamp`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:nature` ← `nature`; `:groupcode` ← `DmServer.getCodeViaManualCode("1", "1", "enviroCompany", "acGroupDepartmentCode", cnTemp)`; `:filterNature` ← `nature`; `:isActive` ← `DmShared.iNull(mrTemp.getIsActiveTag()).intValue()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:stackCode` ← `DmShared.xNull(mrTemp.getStackTag())`; `:rackCode` ← `DmShared.xNull(mrTemp.getRackTag())`; `:legalName` ← `DmShared.xNull(mrTemp.getLegalName())`; `:tradeName` ← `DmShared.xNull(mrTemp.getTradeName())`; `:add1` ← `DmShared.xNull(mrTemp.getAddress1())`; `:add2` ← `DmShared.xNull(mrTemp.getAddress2())`; `:cityCode` ← `DmShared.foriegnKey(mrTemp.getCityCode())`; `:pin` ← `DmShared.xNull(mrTemp.getPin())`; `:gsTin` ← `mrTemp.getGstTin()`; `:gstinDate` ← `DmServer.convertDate(mrTemp.getGstDate())`; `:pan` ← `DmShared.xNull(mrTemp.getPanNo())`; `:mobile` ← `DmShared.xNull(mrTemp.getMobile())`; `:eMail` ← `DmShared.xNull(mrTemp.getEmail())`; `:site_Code` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`; `:preparedBY` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `timestamp`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:isActive` ← `DmShared.iNull(mrTemp.getIsActiveTag()).intValue()`; `:subcode` ← `mrTemp.getSharedKeyFieldCode()`; `:nature` ← `DmServer.getCodeViaManualCode("GD", "manualCode", "subGroupNature", "name", cnTemp)`; `:legalName` ← `mrTemp.getLegalName()`; `:tradeName` ← `mrTemp.getTradeName()`; `:add1` ← `mrTemp.getAddress1()`; `:add2` ← `mrTemp.getAddress2()`; `:cityCode` ← `DmShared.foriegnKey(mrTemp.getCityCode())`; `:pin` ← `mrTemp.getPin()`; `:gsTin` ← `mrTemp.getGstTin()`; `:gstinDate` ← `DmServer.convertDate(mrTemp.getGstDate())`; `:eMail` ← `mrTemp.getEmail()`; `:mobile` ← `mrTemp.getMobile()`; `:pan` ← `mrTemp.getPanNo()`; `:isActive` ← `DmShared.iNull(mrTemp.getIsActiveTag()).intValue()`; `:uName` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `timestamp`; `:subCode` ← `mrTemp.getSharedKeyFieldCode()`; `:stackCode` ← `DmShared.iNull(mrTemp.getStackTag()).intValue()`; `:rackCode` ← `DmShared.iNull(mrTemp.getRackTag()).intValue()`; `:legalName` ← `mrTemp.getLegalName()`; `:tradeName` ← `mrTemp.getTradeName()`; `:add1` ← `mrTemp.getAddress1()`; `:add2` ← `mrTemp.getAddress2()`; `:cityCode` ← `DmShared.foriegnKey(mrTemp.getCityCode())`; `:pin` ← `mrTemp.getPin()`; `:gsTin` ← `mrTemp.getGstTin()`; `:gstinDate` ← `DmServer.convertDate(mrTemp.getGstDate())`; `:eMail` ← `mrTemp.getEmail()`; `:mobile` ← `mrTemp.getMobile()`; `:pan` ← `mrTemp.getPanNo()`; `:isActive` ← `DmShared.iNull(mrTemp.getIsActiveTag()).intValue()`; `:uName` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `timestamp`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:nature` ← `DmServer.getCodeViaManualCode("GD", "manualCode", "subGroupNature", "name", cnTemp)`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:subcode` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:site_Code` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`; `:subcode` ← `DmShared.foriegnKey(mrTemp.getSharedKeyFieldCode())`; `:siteCode` ← `DmShared.foriegnKey(DmServer.getSsnSiteCode(this))`
- Errors: "GSTIN Date should not be greater than current date." | "Invalid key."
- Other calls: `WarehouseMaster_Vld.chkSave`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT whm.code ,whm.name AS name , IIF((whm.stackCode = 1),'Yes','No') AS stackName , IIF((whm.rackCode = 1),'Yes','No') AS rackName , whm.legalName, whm.tradeName, whm.pin, whm.gsTin, whm.gstinDate, whm.eMail, whm.mobile , ct.cityName as cityName, sm.name as stateName, ctr.name as countryName , IIF((whm.isActive = 1),'Yes','No') AS isActive FROM warehouseMast AS whm LEFT JOIN city ct on whm.cityCode = ct.cityCode LEFT JOIN stateMast sm on ct.stateCode = sm.code LEFT JOIN country ctr on ct.countryCode = ctr.code LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = whm.code WHERE sgsl.site_Code = :site_Code`
- Params: `:site_Code` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `getTxtHYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHCity`
`public DmResultPageing<List<TxtHCity_Mdl>> getTxtHCity(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select cityCode,cityName,stateCode,sm.name as stateName,sm.numericCode, ctr.name as countryName from city ct left join stateMast sm on ct.stateCode = sm.code left join country ctr on ct.countryCode = ctr.code`

### `getSiteGstDetail`
`public DmResult<SitePopup_Mdl> getSiteGstDetail(String siteCode)`

- SQL 1: `select s.Address1, s.Address2,s.pin,s.email, s.Country,s.gsTin,s.gstinDate ,s.stateName ,s.tradeName,s.legalName,s.mobile,S.PanNo, s.stateNumericCode , c.cityName as city, c.cityCode, st.code as stateCode From siteMast s LEFT JOIN stateMast st on st.numericCode = s.stateNumericCode LEFT JOIN city c ON c.cityName = s.city AND c.stateCode = st.code Where s.code= :code`
- Params: `:code` ← `siteCode`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxMobile.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `paperbiz.projectMenu.self.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (DmShared.convertDate(mrTemp.getGstDate()).getTime() > (DmShared.convertDate(DmShared.dNull(DmServer.getServerDateTime()[0]))).getTime())` | "GSTIN Date should not be greater than current date." |
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |

## Notes (hand-written)
_No hand-written notes yet._
