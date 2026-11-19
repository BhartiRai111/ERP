# pb-dashboard / dataModificationLog

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/dashBoard/src/dataModificationLog/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: From Date (Text); To Date (Text)
- Lookups/suggestions: getTxtHSite, getTxtHUserName, getTxtHMode, getTxtHFy, getTxtHVoucherType, getTxtHModules, getTxtHMenus, getTxtHModifyReason
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `DataModificationLog`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmTextBox | `txtSearchValue` | `100, false` |
| 2 | - | DmGridPaging | `grdDisplay` | `GrdDataModification_PrpAcs, 22, false` |

**Grid / popup columns** (header, width, db field):
- `grdDisplay`: Log Date/ Time [Text, w130, logdate]; User Name [Text, w70, username]; Mode [Text, w60, mode]; Record Name-1 [Text, w160, keyname_1]; Record Desc.-1 [Text, w160, keydescription_1]; Menu Name [Text, w160, menuname]; Module Name [Text, w90, modulename]; Modify Reason [Text, w100, modifyreason]; A/c Unit [Text, w55, sitemcode]; F.Y. [Text, w65, financialyear]; Vr. Type [Text, w65, v_type_s_name];  [Text, w50]

**Lookup / suggestion / find sources (server RPC called):**
- `grdDisplay` → `getFillRecords`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`filter.addCallBack` → `onOk()`
```java
searchMdl = searchValue;
lblDate.setText(searchMdl.getFromDate() + " To " + searchMdl.getToDate());
checkSearchValue();
reQuery();
```

`grdDisplay.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnToEndOfFile).getFillRecords(searchMdl, helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<L
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdDataModificationLog_Mdl>> result) {
if (result.isStatus()) {
grdDisplay.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`grdDisplay.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
grdDisplay.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`grdDisplay.addCallBack` → `getParam()`
```java
return searchMdl;
```

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to close."

### Screen `DataPopUp`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Old Data | DmTextArea | `txtNewData` | `` |
| 2 | - | DmTextArea | `txtOldData` | `` |

### Screen `EyeDataPopUp`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Data | DmTextArea | `txtData` | `` |

### Screen `Filter`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | From Date | DmTextBox | `txtFromDate` | `11, true` |
| 2 | To Date | DmTextBox | `txtToDate` | `11, true` |
| 3 | User Name | DmTextHelpMulti | `txtHUserName` | `TxtHUserName_PrpAcs, 10` |
| 4 | Financial Year | DmTextHelpMulti | `txtHFY` | `KeyField_PrpAcs, 5` |
| 5 | Mode | DmTextHelpMulti | `txtHMode` | `KeyField_PrpAcs, 5, false` |
| 6 | Vr. Type | DmTextHelpMulti | `txtHVoucherType` | `TxtHVoucherType_PrpAcs, 10` |
| 7 | Module | DmTextHelpMulti | `txtHModules` | `KeyField_PrpAcs, 5` |
| 8 | Menu | DmTextHelpMulti | `txtHMenus` | `KeyField_PrpAcs, 5` |
| 9 | Modify Reason | DmTextHelpMulti | `txtHModifyReason` | `KeyField_PrpAcs, 5` |
| 10 | Record Name-1 | DmTextBox | `txtRecordName_1` | `` |
| 11 | Record Name-2 | DmTextBox | `txtRecordName_2` | `` |
| 12 | Site Name | DmTextHelpMulti | `txtHSite` | `KeyField_PrpAcs, 5, true` |

**Grid / popup columns** (header, width, db field):
- `txtHModifyReason`: Name [Text, w1, name]
- `txtHModules`: Name [Text, w1, name]
- `txtHMenus`: Name [Text, w1, name]
- `txtHMode`: Name [Text, w150, Name]
- `txtHSite`: Site [Text, w200, name]
- `txtHUserName`:  Description [Text, w1, description]; Name [Text, w100, name]
- `txtHFY`: Financial Year [Text, w1, name]
- `txtHVoucherType`: Name [Text, w100, short_name]; Description [Text, w150, description]

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHUserName.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHUserName(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHUserName).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHUserName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHUserName).show();
```

`txtHUserName.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHUserName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHUserName).show();
```

`txtHFY.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHFy(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExt
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHFY).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHFY.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHFY).show();
```

`txtHFY.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHFY.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHFY).show();
```

`txtHModules.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHModules(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFie
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHModules).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHModules.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHModules).show();
```

`txtHModules.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHModules.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHModules).show();
```

`txtHMenus.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHMenus(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyField
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHMenus).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHMenus.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHMenus).show();
```

`txtHMenus.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHMenus.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHMenus).show();
```

`txtHVoucherType.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHVoucherType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHVoucherType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHVoucherType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHVoucherType).show();
```

`txtHVoucherType.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHVoucherType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHVoucherType).show();
```

`txtHModifyReason.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHModifyReason(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<K
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHModifyReason).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHModifyReason.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHModifyReason).show();
```

`txtHModifyReason.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHModifyReason.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHModifyReason).show();
```

`txtHMode.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHMode(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHMode).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHMode.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHMode).show();
```

`txtHMode.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHMode.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHMode).show();
```

`txtHSite.addTextHelpMultiCallBack` → `populateData()`
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

`txtHSite.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSite.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSite).show();
```

**Modify reason popup:** yes (Edit save and Delete).
- Alert: "From Date is mandatory."
- Alert: "To Date is mandatory."

## Files
| file | lines |
|---|---|
| client/DataModificationLog.java | 322 |
| client/DataModificationLog_Dsg.java | 258 |
| client/DataPopUp.java | 89 |
| client/DataPopUp_Dsg.java | 122 |
| client/EyeDataPopUp.java | 124 |
| client/EyeDataPopUp_Dsg.java | 107 |
| client/Filter.java | 407 |
| client/Filter_Dsg.java | 397 |
| server/DataModificationLog_SrvImpl.java | 1161 |
| shared/DataModificationLogReport_Mdl.java | 34 |
| shared/DataModificationLog_Srv.java | 34 |
| shared/DataModificationLog_SrvAsync.java | 36 |
| shared/DataModificationLog_Vld.java | 17 |
| shared/DefaultValue_Mdl.java | 50 |
| shared/GrdDataModificationLog_Mdl.java | 196 |
| shared/SearchValue_Mdl.java | 236 |

## UI labels / columns (client)
`Data`, `New Data`, `Old Data`, `From Date`, `To Date`, `User Name`, `Financial Year`, `Mode`, `Vr. Type`, `Module`, `Menu`, `Modify Reason`, `Record Name-1`, `Record Name-2`, `Site Name`

## Models (shared DTOs)
### DefaultValue_Mdl
| type | field |
|---|---|
| String | currentSiteCode |
| String | currentSiteName |
| String | fromDate |
| String | toDate |

### SearchValue_Mdl
| type | field |
|---|---|
| String | fieldName |
| String | fieldNameValue |
| String | fromDate |
| String | toDate |
| String | userNameCode |
| String | userName |
| String | financialyearCode |
| String | financialyear |
| String | modeCode |
| String | mode |
| String | vrTypeShortNameCode |
| String | vrTypeShortName |
| String | moduleCode |
| String | module |
| String | menuCode |
| String | menu |
| String | modifyReasonCode |
| String | modifyReason |
| String | recordName1 |
| String | recordName2 |
| String | siteCodeList |
| String | siteNameList |
| String | siteCode |
| String | siteName |

### DataModificationLogReport_Mdl
| type | field |
|---|---|
| String | logDateTime |
| String | userNameCode |
| String | userName |
| String | financialyear |
| String | modeCode |
| String | mode |
| String | vrTypeShortName |
| String | moduleCode |
| String | module |
| String | menu |
| String | modifyReason |
| String | recordName1 |
| String | recordDesc1 |
| String | recordName2 |
| String | recordDesc2 |
| String | shortSiteName |
| String | remark |
| String | oldData |
| String | newData |

### GrdDataModificationLog_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | view |
| String | logDateTime |
| String | userNameCode |
| String | userName |
| String | financialyear |
| String | modeCode |
| String | mode |
| String | vrTypeShortName |
| String | moduleCode |
| String | module |
| String | menu |
| String | modifyReason |
| String | recordName1 |
| String | recordDesc1 |
| String | recordName2 |
| String | recordDesc2 |
| String | shortSiteName |
| String | remark |
| String | oldData |
| String | newData |

## RPC interface
| method | params | returns |
|---|---|---|
| getFillRecords | SearchValue_Mdl searchValueMdl, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdDataModificationLog_Mdl>> |
| getDefaultValue |  | DmResult<DefaultValue_Mdl> |
| getTxtHUserName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHFy | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVoucherType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHModules | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHMenus | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHModifyReason | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHMode | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDownloadExcel | String widgetName, SearchValue_Mdl searchValueMdl | DmResult<KeyFieldExtended> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtData | String newData, String oldData | DmResult<String> |

## Validator `DataModificationLog_Vld`
```java
public class DataModificationLog_Vld {
    public static void validateInput(SearchValue_Mdl searchValue) {
        DmShared.isValidText(searchValue.getFromDate(), "From Date", 100);
        DmShared.isValidText(searchValue.getToDate(), "To Date", 200);
        if (DmShared.convertDate(searchValue.getFromDate()).getTime() > DmShared.convertDate(searchValue.getToDate()).getTime()) {
            throw new UnsupportedOperationException("From Date not be grater than To Date . ");
        }
    }
}
```

## Server `DataModificationLog_SrvImpl.java` (1161 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getMdbCollection`, `DmServer.getParamList`, `DmServer.getServerDateTime`, `DmServer.getSsnDBName`, `DmServer.getSsnId`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.getSsnSiteSName`, `DmServer.removeAllCharacterExceptAlphaNumeric`, `DmServer.setReportHeaderExcel`, `DmShared.convertDate`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.xNull`

### `getDefaultValue`
`public DmResult<DefaultValue_Mdl> getDefaultValue()`


### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getFillRecords`
`public DmResultPageing<List<GrdDataModificationLog_Mdl>> getFillRecords(SearchValue_Mdl searchValueMdl, DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `BasicDBObjectBuilder.start`, `DataModificationLog_Vld.validateInput`

### `getDownloadExcel`
`public DmResult<KeyFieldExtended> getDownloadExcel(String widgetName, SearchValue_Mdl searchValueMdl)`

- Errors: "Invalid request." | "File Name not given."
- Other calls: `BLACK.getIndex`, `BasicDBObjectBuilder.start`, `Color.decode`, `DataModificationLog_Vld.validateInput`

### `getTxtHUserName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHUserName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select user_Name as code, user_Name as name, description from userMast Where coalesce(user_Role,'') <>'' and lower(user_Name) not in ('sa','super') and isActive = 1`

### `getTxtHMode`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHMode(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Delete`

### `getTxtData`
`public DmResult<String> getTxtData(String newData, String oldData)`

- Other calls: `Maps.difference`

### `getTxtHFy`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHFy(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select comp_Code as code,cyear as name from company`

### `getTxtHVoucherType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVoucherType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select v_type as code, v_type, description, short_name from voucher_type`

### `getTxtHModules`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHModules(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select distinct mnumodule as name from {…} .dbo.user_permission_web`

### `getTxtHMenus`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHMenus(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select CONCAT(MnuText, 'þ' , MnuName, 'þ' ,MnuModule )As Code, MnuText as name , MnuName From {…} .dbo.User_Permission_Web Where lower(UserName)=lower('SA')`

### `getTxtHModifyReason`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHModifyReason(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select code, name from modifyReasonMast`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmBase.self.xls`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextArea.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `paperbiz.dashBoard.dataModificationLog.shared`, `paperbiz.projectMenu.self.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| getDownloadExcel | `if (!DmSession.getSessions().containsKey(sessionId))` | "Invalid request." |
| getDownloadExcel | `if (DmShared.xNull(fileName).equalsIgnoreCase(""))` | "File Name not given." |

## Notes (hand-written)
_No hand-written notes yet._
