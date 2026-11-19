# dm-accounts / cityMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/accounts/src/cityMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: City
- Update tables: City, c
- Delete cascade (in order): City
- Duplicate checks on save:
  - `SELECT COUNT(cityName) AS cnt FROM city WHERE (stateCode =:stateCode) AND (countryCode=:countryCode) AND LOWER(cityName)=:cityName AND (cityCode<>:docId)`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: City Name (Text); State Name (Amount); Country Name (Amount)
- Lookups/suggestions: getSuggestionForCityName, getTxtHState, getTxtHDistrict, getTxtHCountry
- CSV export: yes

## Screen spec (auto-extracted from client)
### Screen `CityMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | City Name | DmSuggestBox | `txtCityName` | `0, 10, false, "CityName", DmDataType.STRING` |
| 2 | District Name | DmTextHelp | `txtHDistrict` | `KeyField_PrpAcs, 5, false` |
| 3 | State Name | DmTextHelp | `txtHState` | `KeyField_PrpAcs, 5, true` |
| 4 | STD Code | DmTextBox | `txtSTDCode` | `` |
| 5 | Country Name | DmTextHelp | `txtHCountry` | `KeyField_PrpAcs, 5, true` |

Other widgets (not in tab list): `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `txtHState`: Name [Text, w0, name]
- `txtHDistrict`: Name [Text, w0, name]
- `txtHCountry`: Name [Text, w0, name]
- `dmFind`: City Name [Text, w1, cityName]; District Name [Text, w200, districtName]; State Name [Text, w200, stateName]; STD Code [Text, w200, stdCode]; Country [Text, w200, countryName]

**Lookup / suggestion / find sources (server RPC called):**
- `txtCityName` → `getSuggestionForCityName`
- `txtHState` → `getTxtHState`
- `txtHDistrict` → `getTxtHDistrict`
- `txtHCountry` → `getTxtHCountry`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtCityName.getText())
- `StateTag` ← DmShared.xNull(txtHState.getTag())
- `StateText` ← DmShared.xNull(txtHState.getText())
- `DistrictTag` ← DmShared.xNull(txtHDistrict.getTag())
- `DistrictText` ← DmShared.xNull(txtHDistrict.getText())
- `CountryTag` ← DmShared.xNull(txtHCountry.getTag())
- `CountryText` ← DmShared.xNull(txtHCountry.getText())
- `StdCode` ← DmShared.xNull(txtSTDCode.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtCityName.setText` ← data.getSharedKeyFieldName()
- `txtHState.setTag2nd` ← data.getStateTag()
- `txtHState.setTag` ← data.getStateTag()
- `txtHState.setText` ← data.getStateText()
- `txtHDistrict.setTag2nd` ← data.getDistrictTag()
- `txtHDistrict.setTag` ← data.getDistrictTag()
- `txtHDistrict.setText` ← data.getDistrictText()
- `txtHCountry.setTag` ← data.getCountryTag()
- `txtHCountry.setText` ← data.getCountryText()
- `txtSTDCode.setText` ← data.getStdCode()
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**On New click:**
- `clear()`
- `txtCityName.setFocus(true)`

**`manageDisplay()` logic (condensed code):**
```java
manageDistrict(flag);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
clear();
manageDisplay(true);
txtCityName.setFocus(true);
```

`topControl.addEditHandeller` → `editClicked()`
```java
manageDisplay(true);
txtCityName.setFocus(true);
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(CityMaster.this);
```

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
```

`txtCityName.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getSuggestionForCityName(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<L
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtCityName).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtCityName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtCityName).show();
```

`txtCityName.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtCityName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtCityName).show();
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
new DmAlertMessageBox(result.getErrorMessage(), txtHState).show();
```

`txtHState.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHState.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHState).show();
```

`txtHState.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHState.getTag()).equalsIgnoreCase(DmShared.xNull(txtHState.getTag2nd()))) {
resetState();
txtHState.setTag2nd(DmShared.xNull(txtHState.getTag()));
if (blnStatus && t1Row != null) {
```

`txtHDistrict.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHDistrict(topControl.getMode().equalsIgnoreCase("New") ? "" : DmShared.xNull(t
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDistrict).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHDistrict.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDistrict).show();
```

`txtHDistrict.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHDistrict.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDistrict).show();
```

`txtHDistrict.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHDistrict.getTag()).equalsIgnoreCase(DmShared.xNull(txtHDistrict.getTag2nd()))) {
resetDistrict();
txtHDistrict.setTag2nd(DmShared.xNull(txtHDistrict.getTag()));
if (blnStatus && t1Row != null) {
txtHState.setTag(DmShared.xNull(t1Row.getSharedPreparedBy()));
txtHState.setText(DmShared.xNull(t1Row.getSharedPreparedDate()));
txtHCountry.setTag(DmShared.xNull(t1Row.getSharedModifiedBy()));
txtHCountry.setText(DmShared.xNull(t1Row.getSharedModifyDate()));
manageDistrict(true);
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
new DmAlertMessageBox(result.getErrorMessage(), txtHCountry).show();
```

`txtHCountry.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHCountry.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCountry).show();
```

`txtHCountry.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus) {
if (t1Row != null) {
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

**Top control options:** setRefreshRequired(false), setDisplay(true)

**Menu items (Other button):** Export City

_Print permission "P" is removed for this screen (replace "P" with "*")._
- Confirm: "Are you sure? You want to delete this record."
- Confirm: "Are you sure? You want to export records."

## Files
| file | lines |
|---|---|
| client/CityMaster.java | 554 |
| client/CityMaster_Dsg.java | 229 |
| server/CityMaster_SrvImpl.java | 679 |
| shared/CityMaster_Mdl.java | 90 |
| shared/CityMaster_Srv.java | 29 |
| shared/CityMaster_SrvAsync.java | 29 |
| shared/CityMaster_Vld.java | 13 |
| shared/Find_Mdl.java | 68 |

## UI labels / columns (client)
`City Name`, `District Name`, `State Name`, `STD Code`, `Country Name`

## Models (shared DTOs)
### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | districtName |
| String | stateTag |
| String | stateText |
| String | stdCode |
| String | countryTag |
| String | countryText |

### CityMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | districtTag |
| String | districtText |
| String | stateTag |
| String | stateText |
| String | countryTag |
| String | countryText |
| String | stdCode |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getSuggestionForCityName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getMoveRec | String strKey | DmResult<CityMaster_Mdl> |
| saveRecord | CityMaster_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getTxtHDistrict | String cityCode, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHState | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHCountry | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| exportCSV | String sessionId | DmResult<KeyFieldExtended> |

## Validator `CityMaster_Vld`
```java
public class CityMaster_Vld {
    public static void chkSave(CityMaster_Mdl mrTemp) {
        DmShared.isValidText(DmShared.xNull(mrTemp.getSharedKeyFieldName()), "City Name", 100);
        DmShared.isValidAmount(DmShared.xNull(mrTemp.getStateTag()), "State Name", 200);
        DmShared.isValidAmount(DmShared.xNull(mrTemp.getCountryTag()), "Country Name", 300);
    }
}
```

## Server `CityMaster_SrvImpl.java` (679 lines)
Helpers used: `DmServer.chkIfDocumentUsed`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getSerialNoInteger`, `DmServer.getSsnDBName`, `DmServer.getSsnUserName`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `Select CityCode From City Order By CityName`

### `getSuggestionForCityName`
`public DmResultPageing<List<String>> getSuggestionForCityName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select distinct CityName From City`

### `getMoveRec`
`public DmResult<CityMaster_Mdl> getMoveRec(String strKey)`

- SQL 1: `Select c.cityCode, c.cityName, cd.cityCode AS districtCode, cd.cityName AS districtName, c.stateCode,sm.name as stateName, c.stdCode,c.countryCode,coun.name as CountryName,c.preparedBy,c.u_Name From City as c left join city as cd on cd.cityCode = c.districtCode left join stateMast as sm on sm.code = c.stateCode left join country as coun on coun.code = c.countryCode Where c.CityCode= :cityCode`
- Params: `:cityCode` ← `DmShared.iNull(strKey).toString()`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey)`

- SQL 1: `Delete From City Where CityCode= :cityCode`
- Params: `:cityCode` ← `DmShared.iNull(strKey).toString()`

### `saveRecord`
`public DmResult<String> saveRecord(CityMaster_Mdl mrTemp)`

- SQL 1: `SELECT COUNT(cityName) AS cnt FROM city WHERE (stateCode =:stateCode) AND (countryCode=:countryCode) AND LOWER(cityName)=:cityName AND (cityCode<>:docId)`
- SQL 2: `Insert Into City(CityCode ,CityName, districtCode,stateCode,stdCode,countryCode, U_EntDt, preparedBy) Values (:cityCode ,:cityName, :districtCode,:stateCode,:stdCode,:countryCode,getdate(), :u_Name)`
- SQL 3: `Update City Set CityName = :cityName, districtCode = :districtCode, stateCode = :stateCode, stdCode = :stdCode, countryCode = :countryCode, transferDt = :transferDt, U_Name = :u_Name, U_EntDt = getdate() Where CityCode = :cityCode`
- SQL 4: `UPDATE c SET c.stateCode = cd.stateCode , c.countryCode = cd.countryCode FROM city c LEFT JOIN city cd ON cd.cityCode = c.districtCode WHERE ISNULL(c.districtCode, :zero) = :districtCode`
- Params: `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:cityName` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName()).toLowerCase()`; `:stateCode` ← `DmShared.iNull(mrTemp.getStateTag()).toString()`; `:countryCode` ← `DmShared.iNull(mrTemp.getCountryTag()).toString()`; `:cityCode` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:zero` ← `0`; `:cityCode` ← `mrTemp.getSharedKeyFieldCode()`; `:cityName` ← `mrTemp.getSharedKeyFieldName()`; `:districtCode` ← `DmShared.foriegnKey(mrTemp.getDistrictTag())`; `:stateCode` ← `DmShared.foriegnKey(mrTemp.getStateTag())`; `:countryCode` ← `DmShared.foriegnKey(mrTemp.getCountryTag())`; `:stdCode` ← `mrTemp.getStdCode()`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:cityName` ← `mrTemp.getSharedKeyFieldName()`; `:districtCode` ← `DmShared.foriegnKey(mrTemp.getDistrictTag())`; `:stateCode` ← `DmShared.foriegnKey(mrTemp.getStateTag())`; `:countryCode` ← `DmShared.foriegnKey(mrTemp.getCountryTag())`; `:stdCode` ← `mrTemp.getStdCode()`; `:transferDt` ← `null`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:cityCode` ← `mrTemp.getSharedKeyFieldCode()`; `:districtCode` ← `mrTemp.getSharedKeyFieldCode()`; `:zero` ← `0`
- Errors: "Invalid key." | "City is already exists." | "District cannot be assign, as this city is already assigned as district to other cities."
- Other calls: `CityMaster_Vld.chkSave`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select c.cityCode, c.cityName,c.stateCode,sm.name as stateName, cd.cityName as districtName, c.stdCode,c.countryCode,coun.name as countryName,c.preparedBy,c.u_Name From City as c LEFT JOIN city as cd ON cd.cityCode = c.districtCode left join stateMast as sm on sm.code = c.stateCode left join country as coun on coun.code = c.countryCode`

### `getTxtHState`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHState(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code, name from stateMast`

### `getTxtHDistrict`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDistrict(String cityCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select c.cityCode AS code, c.cityName AS name, c.stateCode, sm.name AS stateName, c.countryCode, cn.name AS countryName From City c LEFT JOIN stateMast sm ON sm.code = c.stateCode LEFT JOIN country cn ON cn.code = c.countryCode WHERE c.districtCode IS NULL {…}`
- Params: `:code` ← `cityCode`

### `getTxtHCountry`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCountry(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code, name from country`

### `exportCSV`
`public DmResult<KeyFieldExtended> exportCSV(String sessionId)`

- SQL 1: `select co.name as country, sm.name as stateName, case when isUT = 1 then 'Yes' else 'No' end as unionTerritory, sm.numericCode, sm.textCode, ct.cityName, ct.stdCode, cd.cityName AS districtName from city ct left join stateMast sm on sm.code = ct.stateCode left join country co on co.code = ct.countryCode left join city as cd on cd.cityCode = ct.districtCode order by co.name, case when isUT= 0 then 0 else 1 end, sm.name, ct.cityName`
- Errors: "Invalid request." | "File Name not given."
- Other calls: `DmImportData_Server.getCSVFormats`

## Depends on (imports from other packages)
`dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.client`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmImportData.server`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.libraryMenu.self.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "City is already exists." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "District cannot be assign, as this city is already assigned as district to other cities." |
| exportCSV | `if (!DmSession.getSessions().containsKey(sessionId))` | "Invalid request." |
| exportCSV | `if (DmShared.xNull(fileName).equalsIgnoreCase(""))` | "File Name not given." |

## Notes (hand-written)
_No hand-written notes yet._
