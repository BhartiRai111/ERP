# dm-utility / productLicensedTo

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/utility/src/productLicensedTo/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: ProductLicensedTo
- Update tables: ProductLicensedTo, SM
- Delete cascade (in order): ProductLicensedTo
- Duplicate checks on save:
  - `SELECT COUNT(comp_Name) AS Cnt FROM productlicensedto WHERE (comp_Name = :compName) AND (pltCode <> :pltCode)`
  - `SELECT COUNT(comp_Name) AS Cnt FROM productlicensedto WHERE (sName = :SName) AND (pltCode <> :pltCode)`
- "In use" checks before delete:
  - `Select Count(*) as cnt from Company Where PLTCode= :PLTCode`
  - `Select Count(*) as cnt from SiteMast Where PLTCode= :PLTCode`
- Validations: Company Name (Text); Short Name (Text)
- Lookups/suggestions: getSuggestion, getTxtHCompany
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `ProductLicensedTo`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Company Name | DmSuggestBox | `txtCompanyName` | `40, 10, true, "comp_name", DmDataType.STRING` |
| 2 | Company Code | DmTextBox | `txtCompanyCode` | `3, false` |
| 3 | Short Name | DmTextBox | `txtShortName` | `10, true` |
| 4 | Lat. Comp. Code | DmTextHelp | `txtHLatCompany` | `DmCompany_PrpAcs, 4, false` |

Other widgets (not in tab list): `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `dmFind`: Company Name [Text, w250, Comp_Name]; Short Name [Text, w250, sname]; Company Code  [Text, w250, latestCompCode]
- `txtHLatCompany`: Current Year [Text, w100, cYear]; Start Date [Text, w80, start_Dt]; End Date [Text, w80, end_Dt]; Cen. Data Path [Text, w100, centralData_Path]

**Lookup / suggestion / find sources (server RPC called):**
- `txtCompanyName` → `getSuggestion`
- `txtHLatCompany` → `getTxtHCompany`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtCompanyName.getText())
- `ShortName` ← DmShared.xNull(txtShortName.getText())
- `LatestCompCode` ← DmShared.xNull(txtHLatCompany.getTag())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- `txtCompanyName.setText` ← data.getSharedKeyFieldName()
- `txtCompanyCode.setText` ← data.getSharedKeyFieldCode()
- `txtShortName.setText` ← data.getShortName()
- `txtHLatCompany.setText` ← data.getLatestCYear()
- `txtHLatCompany.setTag` ← data.getLatestCompCode()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**On New click:**
- `txtCompanyName.setFocus(true)`
- `txtCompanyCode.setEnabled(false)`
- `userControl.setTextBlog(preparedByBlog, "")`
- `userControl.setTextBlog(modifiedByBlog, "")`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
txtCompanyName.setFocus(true);
txtCompanyCode.setEnabled(false);
userControl.setTextBlog(preparedByBlog, "");
userControl.setTextBlog(modifiedByBlog, "");
```

`topControl.addEditHandeller` → `editClicked()`
```java
txtCompanyName.setFocus(true);
txtCompanyCode.setEnabled(false);
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(ProductLicensedTo.this);
```

`txtCompanyName.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getSuggestion(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<String>
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtCompanyName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), (DmTextBox) null).show();
```

`txtCompanyName.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtCompanyName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), (DmTextBox) null).show();
```

`txtHLatCompany.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHCompany(DmShared.iNull(topControl.getDocId()).toString(), helper, blnToEndOfF
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHLatCompany).show();
public void onSuccess(DmResultPageing<List<TxtHCompany_Mdl>> result) {
if (result.isStatus()) {
txtHLatCompany.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHLatCompany).show();
```

`txtHLatCompany.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHLatCompany.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHLatCompany).show();
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
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), (DmTextBox) null).show();
```

`dmFind.addHelpGridCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
dmFind.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), (DmTextBox) null).show();
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
| client/ProductLicensedTo.java | 366 |
| client/ProductLicensedTo_Dsg.java | 189 |
| server/ProductLicensedTo_SrvImpl.java | 522 |
| shared/Find_Mdl.java | 31 |
| shared/ProductLicensedTo_Mdl.java | 41 |
| shared/ProductLicensedTo_Srv.java | 25 |
| shared/ProductLicensedTo_SrvAsync.java | 25 |
| shared/ProductLicensedTo_Vld.java | 15 |
| shared/TxtHCompany_Mdl.java | 41 |

## UI labels / columns (client)
`Company Name`, `Company Code`, `Short Name`, `Lat. Comp. Code`

## Models (shared DTOs)
### ProductLicensedTo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | shortName |
| String | latestCompCode |
| String | latestCYear |

### TxtHCompany_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | startDt |
| String | endDt |
| String | centralDataPath |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | sname |
| String | latestCompCode |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getSuggestion | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getMoveRec | String strKey | DmResult<ProductLicensedTo_Mdl> |
| saveRecord | ProductLicensedTo_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getTxtHCompany | String pltCode, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHCompany_Mdl>> |

## Validator `ProductLicensedTo_Vld`
```java
public class ProductLicensedTo_Vld {
    public static void chkSave(ProductLicensedTo_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getSharedKeyFieldName(), "Company Name", 100);
        DmShared.isValidText(mrTemp.getShortName(), "Short Name", 200);
    }
}
```

## Server `ProductLicensedTo_SrvImpl.java` (522 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getServerTimestamp`, `DmServer.getSsnUserName`, `DmShared.dNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `Select PLTCode As SearchCode,Comp_Name As Name From ProductLicensedTo order by Comp_Name ;`

### `getSuggestion`
`public DmResultPageing<List<String>> getSuggestion(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select comp_name From ProductLicensedTo`

### `getMoveRec`
`public DmResult<ProductLicensedTo_Mdl> getMoveRec(String strKey)`

- SQL 1: `Select plt.Comp_Name As Name,plt.PLTCode,plt.sname,plt.latestCompCode, cmp.cYear,plt.u_Name,plt.preparedBy From ProductLicensedTo as plt left join company as cmp on plt.latestCompCode=cmp.comp_Code Where plt.PLTCode= :PLTCode order by plt.Comp_Name`
- Params: `:PLTCode` ← `strKey`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey)`

- SQL 1: `Select Count(*) as cnt from Company Where PLTCode= :PLTCode`
- SQL 2: `Select Count(*) as cnt from SiteMast Where PLTCode= :PLTCode`
- SQL 3: `Delete From ProductLicensedTo Where PLTCode= :PLTCode`
- Params: `:PLTCode` ← `strKey`; `:PLTCode` ← `strKey`; `:PLTCode` ← `strKey`
- Errors: "Company Already In Use."

### `saveRecord`
`public DmResult<String> saveRecord(ProductLicensedTo_Mdl mrTemp)`

- SQL 1: `SELECT ISNULL(MAX(CAST(pltCode AS integer)), 0) + 1 AS Mx FROM productlicensedto`
- SQL 2: `SELECT COUNT(comp_Name) AS Cnt FROM productlicensedto WHERE (comp_Name = :compName) AND (pltCode <> :pltCode)`
- SQL 3: `SELECT COUNT(comp_Name) AS Cnt FROM productlicensedto WHERE (sName = :SName) AND (pltCode <> :pltCode)`
- SQL 4: `Insert Into ProductLicensedTo(PLTCode,Comp_Name,sname,latestCompCode,PreparedBY,u_EntDt) Values (:PLTCode, :Comp_Name, :sname,:latestCompCode,:PreparedBY,:u_EntDt)`
- SQL 5: `Update ProductLicensedTo Set Comp_Name= :Comp_Name,sname= :sname , latestCompCode= :latestCompCode,u_Name=:uName,u_EntDt =:u_EntDt Where PLTCode = :PLTCode`
- SQL 6: `UPDATE SM SET SM.name = BM.name + ' [' + p.sName + ']' FROM company AS C LEFT OUTER JOIN sitemast AS SM ON SM.pltCode = C.pltCode LEFT OUTER JOIN branchmast AS BM ON BM.code = SM.branchCode LEFT JOIN productLicensedTo as p on p.pltCode=c.pltCode WHERE (SM.pltCode = :PLTCode)`
- SQL 7: `SELECT C.centralData_Path FROM company AS C LEFT OUTER JOIN sys.databases AS SD ON SD.name = RTRIM(LTRIM(C.centralData_Path)) WHERE (ISNULL(SD.name, '') <> '') AND (C.pltCode = :pltCode) GROUP BY C.centralData_Path`
- SQL 8: `SELECT COUNT(name) AS cnt FROM {…} CentralData_Path.sys.objects T WHERE T.name = 'SiteMast'`
- Params: `:compName` ← `mrTemp.getSharedKeyFieldName()`; `:pltCode` ← `mrTemp.getSharedKeyFieldCode()`; `:SName` ← `mrTemp.getShortName()`; `:pltCode` ← `mrTemp.getSharedKeyFieldCode()`; `:PLTCode` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:Comp_Name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:sname` ← `DmShared.xNull(mrTemp.getShortName())`; `:latestCompCode` ← `DmShared.foriegnKey(mrTemp.getLatestCompCode())`; `:PreparedBY` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `DmServer.getServerTimestamp()`; `:Comp_Name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:PLTCode` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:sname` ← `DmShared.xNull(mrTemp.getShortName())`; `:latestCompCode` ← `DmShared.foriegnKey(mrTemp.getLatestCompCode())`; `:uName` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `DmServer.getServerTimestamp()`; `:PLTCode` ← `mrTemp.getSharedKeyFieldCode()`; `:PLTCode` ← `mrTemp.getSharedKeyFieldCode()`; `:PLTCode` ← `mrTemp.getSharedKeyFieldCode()`
- Errors: "Invalid key." | "Duplicate Company Name." | "Duplicate Short Name."
- Other calls: `ProductLicensedTo_Vld.chkSave`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select plt.Comp_Name As Comp_Name,plt.PLTCode,plt.sname,plt.latestCompCode,cmp.cYear as cYear From ProductLicensedTo as plt left join company as cmp on plt.latestCompCode=cmp.comp_Code Where plt.PLTCode= cmp.PLTCode`

### `getTxtHCompany`
`public DmResultPageing<List<TxtHCompany_Mdl>> getTxtHCompany(String pltCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT comp_Code as Code ,start_dt,end_Dt,cYear,centralData_Path FROM company where pltCode= :pltCode`
- Params: `:pltCode` ← `DmShared.iNull(pltCode).toString()`

## Depends on (imports from other packages)
`dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.libraryMenu.self.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| deleteRecord | `if (rsTemp.getLong("cnt") > 0)` | "Company Already In Use." |
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Duplicate Company Name." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Duplicate Short Name." |

## Notes (hand-written)
_No hand-written notes yet._
