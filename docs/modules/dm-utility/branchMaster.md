# dm-utility / branchMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/utility/src/branchMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: BranchMast
- Update tables: BranchMast, SM
- Delete cascade (in order): BranchMast
- Duplicate checks on save:
  - `Select Count(Name) As Cnt From BranchMast BM Where BM.Name= :branchName And Code <> :Code`
  - `Select Count(Name) As Cnt From BranchMast BM Where BM.ManualCode = :ManualCode And Code <> :Code`
- "In use" checks before delete:
  - `Select Count(*) as cnt from SiteMast where branchCode= :branchcode`
- Validations: Branch Name (Text); Branch Code (Text); City Name (Text)
- Lookups/suggestions: getSuggestion
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `BranchMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Branch Name | DmSuggestBox | `txtBranchName` | `35, 10, true, "Name", DmDataType.STRING` |
| 2 | Branch Code | DmTextBox | `txtBranchCode` | `6, true` |
| 3 | City Name | DmTextBox | `txtCityName` | `35, true` |

Other widgets (not in tab list): `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `dmFind`: Branch Name [Text, w250, Name]; Branch Code [Text, w100, ManualCode]; City Name [Text, w300, City]; User Name [Text, w200, U_Name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtBranchName` → `getSuggestion`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `BranchCode` ← DmShared.xNull(txtBranchCode.getText().trim())
- `SharedKeyFieldName` ← DmShared.xNull(txtBranchName.getText().trim())
- `CityName` ← DmShared.xNull(txtCityName.getText().trim())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- `txtBranchCode.setText` ← data.getBranchCode()
- `txtBranchName.setText` ← data.getSharedKeyFieldName()
- `txtCityName.setText` ← data.getCityName()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**On New click:**
- `clear()`
- `txtBranchName.setFocus(true)`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
clear();
txtBranchName.setFocus(true);
```

`topControl.addEditHandeller` → `editClicked()`
```java
txtBranchName.setFocus(true);
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(BranchMaster.this);
```

`txtBranchName.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getSuggestion(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<String>
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtBranchName).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtBranchName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtBranchName).show();
```

`txtBranchName.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtBranchName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtBranchName).show();
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

## Files
| file | lines |
|---|---|
| client/BranchMaster.java | 339 |
| client/BranchMaster_Dsg.java | 142 |
| server/BranchMaster_SrvImpl.java | 456 |
| shared/BranchMaster_Mdl.java | 45 |
| shared/BranchMaster_Srv.java | 37 |
| shared/BranchMaster_SrvAsync.java | 36 |
| shared/BranchMaster_Vld.java | 13 |
| shared/Find_Mdl.java | 54 |

## UI labels / columns (client)
`Branch Name`, `Branch Code`, `City Name`

## Models (shared DTOs)
### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | branchCode |
| String | cityName |
| String | userName |

### BranchMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | branchCode |
| String | cityName |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getSuggestion | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getMoveRec | String strKey | DmResult<BranchMaster_Mdl> |
| saveRecord | BranchMaster_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |

## Validator `BranchMaster_Vld`
```java
public class BranchMaster_Vld {
    public static void chkSave(BranchMaster_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getSharedKeyFieldName().trim(), "Branch Name", 100);
        DmShared.isValidText(mrTemp.getBranchCode().trim(), "Branch Code", 200);
        DmShared.isValidText(mrTemp.getCityName().trim(), "City Name", 300);
    }
}
```

## Server `BranchMaster_SrvImpl.java` (456 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getSsnUserName`, `DmShared.removeSpace`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `Select Code As BranchCode From BranchMast Order By Name;`

### `getSuggestion`
`public DmResultPageing<List<String>> getSuggestion(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select Name From BranchMast`

### `getMoveRec`
`public DmResult<BranchMaster_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT B.ManualCode,B.Code,B.Name,City,b.preparedBY,b.u_name From BranchMast as b Where b.Code= :bracnhCode Order By B.Name`
- Params: `:bracnhCode` ← `strKey`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey)`

- SQL 1: `Select Count(*) as cnt from SiteMast where branchCode= :branchcode`
- SQL 2: `Delete From BranchMast Where Code = :branchcode`
- Params: `:branchcode` ← `strKey`; `:branchcode` ← `strKey`
- Errors: "Branch already in use."

### `saveRecord`
`public DmResult<String> saveRecord(BranchMaster_Mdl mrTemp)`

- SQL 1: `Select Count(Name) As Cnt From BranchMast BM Where BM.Name= :branchName And Code <> :Code`
- SQL 2: `Select Count(Name) As Cnt From BranchMast BM Where BM.ManualCode = :ManualCode And Code <> :Code`
- SQL 3: `SELECT C.CentralData_Path FROM Company C LEFT JOIN SiteMast SM ON SM.PLTCode = C.PLTCode LEFT JOIN sys.databases AS SD ON SD.name = RTRIM(LTRIM(C.CentralData_Path)) WHERE isNull(SD.name, '') <> '' AND SM.BranchCode = :branchCode GROUP BY C.CentralData_Path`
- SQL 4: `Insert Into BranchMast(Code,ManualCode,Name,City, preparedBY,U_EntDt ) Values (:Code,:ManualCode,:Name,:City,:preparedBY,getdate())`
- SQL 5: `Update BranchMast Set Name = :branchName, City = :cityName, ManualCode = :code, U_Name = :userName, U_EntDt = getdate() Where Code = :branchCode`
- SQL 6: `UPDATE SM SET SM.NAME = CONCAT(:name, ' [' , plt.sName , ']'), SM.city = :city FROM COMPANY AS C LEFT JOIN SITEMAST AS SM ON SM.PLTCODE = C.PLTCODE left join productLicensedTo as plt on plt.pltCode= sm.pltCode WHERE SM.BRANCHCODE = :branchCode`
- SQL 7: `SELECT COUNT(*) AS cnt FROM {…} CentralData_Path.sys.objects T WHERE T.name = 'SiteMast'`
- Params: `:branchName` ← `mrTemp.getSharedKeyFieldName()`; `:Code` ← `mrTemp.getSharedKeyFieldCode()`; `:ManualCode` ← `mrTemp.getBranchCode()`; `:Code` ← `mrTemp.getSharedKeyFieldCode()`; `:branchCode` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:Code` ← `mrTemp.getSharedKeyFieldCode()`; `:ManualCode` ← `mrTemp.getBranchCode()`; `:Name` ← `mrTemp.getSharedKeyFieldName()`; `:City` ← `mrTemp.getCityName()`; `:preparedBY` ← `DmServer.getSsnUserName(this)`; `:branchName` ← `mrTemp.getSharedKeyFieldName()`; `:cityName` ← `mrTemp.getCityName()`; `:code` ← `mrTemp.getBranchCode()`; `:userName` ← `DmServer.getSsnUserName(this)`; `:branchCode` ← `mrTemp.getSharedKeyFieldCode()`; `:name` ← `mrTemp.getSharedKeyFieldName()`; `:branchCode` ← `mrTemp.getSharedKeyFieldCode()`; `:city` ← `mrTemp.getCityName()`; `:branchCode` ← `mrTemp.getSharedKeyFieldCode()`
- Errors: "Branch Name already exists." | "Branch Code already exists." | "Invalid key."
- Other calls: `BranchMaster_Vld.chkSave`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select Code,ManualCode, Name,City,U_Name From BranchMast`

## Depends on (imports from other packages)
`dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.libraryMenu.self.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| deleteRecord | `if (rsTemp.getLong("cnt") > 0)` | "Branch already in use." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Branch Name already exists." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Branch Code already exists." |
| saveRecord | `if (mrTemp.getBranchCode().equalsIgnoreCase(""))` | "Invalid key." |

## Notes (hand-written)
_No hand-written notes yet._
