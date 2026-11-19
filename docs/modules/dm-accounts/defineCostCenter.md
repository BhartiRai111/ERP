# dm-accounts / defineCostCenter

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/accounts/src/defineCostCenter/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: costcentermast, CostCenterPath
- Update tables: CostCenterMast
- Delete cascade (in order): CostCenterPath, CostCenterMast
- Duplicate checks on save:
  - `SELECT COUNT(CCM.Code) AS Cnt FROM CostCenterMast CCM WHERE CCM.Name = :name AND CCM.Code <> :code`
  - `SELECT COUNT(CCM.Code) AS Cnt FROM CostCenterMast CCM WHERE CCM.ManualCode = :manualCode AND CCM.Code <> :code`
- "In use" checks before delete:
  - `Select Count(UnderCostCenter) As Cnt From CostCenterPath Where UnderCostCenter= :UnderCostCenter`
- Validations: Cost center (Text); Manual code (Text); Acitve (Text)
- Lookups/suggestions: getTxtHUnderCostCenter, getTxtHActive, getSuggestionCostCenter
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `DefineCostCenter`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Cost Center | DmSuggestBox | `txtCostCenter` | `50, 10, false, "Name", DmDataType.STRING` |
| 2 | Under Cost Center | DmTextHelp | `txtHUnderCostCenter` | `txtHUnderCostCenter_PrpAcs, 7` |
| 3 | Description | DmTextBox | `txtDescription` | `249, false` |
| 4 | Manual Code | DmTextBox | `txtManualCode` | `20, true` |
| 5 | Active | DmTextHelp | `txtHActive` | `KeyField_PrpAcs, 2` |

Other widgets (not in tab list): `dmGrd` (DmGrid), `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `txtHUnderCostCenter`: Cost Center [Text, w0, Name]; Under Cost Center [Text, w200, UName]
- `txtHActive`: Name [Text, w0, Name]
- `dmGrd`: Under Cost Center Hierarchy [Text, w0]
- `dmFind`: Cost Center Name [Text, w0, Cost_Center_Name]; Under Cost Center [Text, w150, Under_Cost_Center]; Manual Code [Text, w100, ManualCode]; Active [Text, w150, isActive]; Description [Text, w200, Description]

**Lookup / suggestion / find sources (server RPC called):**
- `txtCostCenter` → `getSuggestionCostCenter`
- `txtHActive` → `getTxtHActive`
- `txtHUnderCostCenter` → `getTxtHUnderCostCenter`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `ActiveCode` ← DmShared.xNull(txtHActive.getTag())
- `ActiveName` ← DmShared.xNull(txtHActive.getText())
- `SharedKeyFieldName` ← DmShared.xNull(txtCostCenter.getText())
- `Description` ← DmShared.xNull(txtDescription.getText())
- `UnderCostCode` ← DmShared.xNull(txtHUnderCostCenter.getTag())
- `UnderCostName` ← DmShared.xNull(txtHUnderCostCenter.getText())
- `ManualCode` ← DmShared.xNull(txtManualCode.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtCostCenter.setText` ← data.getSharedKeyFieldName()
- `txtDescription.setText` ← data.getDescription()
- `txtManualCode.setText` ← data.getManualCode()
- `txtHActive.setTag` ← data.getActiveCode()
- `txtHActive.setText` ← data.getActiveName()
- `txtHUnderCostCenter.setTag` ← data.getUnderCostCode()
- `txtHUnderCostCenter.setText` ← data.getUnderCostName()
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**On New click:**
- `clear()`
- `txtCostCenter.setFocus(true)`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
clear();
txtCostCenter.setFocus(true);
```

`topControl.addEditHandeller` → `editClicked()`
```java
txtCostCenter.setFocus(true);
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

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(DefineCostCenter.this);
```

`modifyReason.addCallBack` → `onOk()`
```java
try {
modifyReasonMdl = modifyReason;
if (topControl.getMode().equalsIgnoreCase("Browse") && DmShared.iNull(topControl.getDocId()).longValue() > 0) {
topControlDelete(topControl.getDocId());
} else {
topControlSave();
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), (DmTextBox) null).show();
```

`txtCostCenter.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getSuggestionCostCenter(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<Li
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtCostCenter).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtCostCenter.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtCostCenter).show();
```

`txtCostCenter.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtCostCenter.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtCostCenter).show();
```

`txtHActive.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHActive(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFiel
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

`txtHUnderCostCenter.addTextHelpCallBack` → `populateData()`
```java
String costCenterCode = "";
if (!topControl.getMode().equalsIgnoreCase("New")) {
costCenterCode = topControl.getDocId();
SrvAsync.invokeRPC(blnCallBackWait).getTxtHUnderCostCenter(helper, blnToEndOfFile, costCenterCode, new AsyncCallback<DmR
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHUnderCostCenter).show();
public void onSuccess(DmResultPageing<List<TxtHUnderCostCenter_Mdl>> result) {
if (result.isStatus()) {
txtHUnderCostCenter.setData(result.getData(), result.getPaging());
} else {
...
```

`txtHUnderCostCenter.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHUnderCostCenter.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHUnderCostCenter).show();
```

`txtHUnderCostCenter.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus) {
fillGrdParent(DmShared.xNull(tRow.getSharedKeyFieldCode()));
```

`dmFind.addHelpGridCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getFind(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Find_Mdl>>>()
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), dmFind.getTextBox()).show();
public void onSuccess(DmResultPageing<List<Find_Mdl>> result) {
if (result.isStatus()) {
if (result.isStatus()) {
dmFind.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), dmFind.getTextBox()).show();
```

`dmFind.addHelpGridCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
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

**Top control options:** setOtherRequired(false), setRefreshRequired(false), setDisplay(true)

_Print permission "P" is removed for this screen (replace "P" with "*")._

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."

## Files
| file | lines |
|---|---|
| client/DefineCostCenter.java | 499 |
| client/DefineCostCenter_Dsg.java | 255 |
| server/DefineCostCenter_SrvImpl.java | 819 |
| shared/DefineCostCenter_Mdl.java | 91 |
| shared/DefineCostCenter_Srv.java | 41 |
| shared/DefineCostCenter_SrvAsync.java | 42 |
| shared/DefineCostCenter_Vld.java | 14 |
| shared/Find_Mdl.java | 72 |
| shared/TxtHUnderCostCenter_Mdl.java | 36 |

## UI labels / columns (client)
`Cost Center`, `Under Cost Center`, `Description`, `Manual Code`, `Active`

## Models (shared DTOs)
### TxtHUnderCostCenter_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | UnderCostCenter |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | code |
| String | UCostCenter |
| String | ActiveName |
| String | Description |
| String | ManualCode |

### DefineCostCenter_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | UnderCostCode |
| String | UnderCostName |
| String | ActiveCode |
| String | ActiveName |
| String | Description |
| String | ManualCode |
| ModifyReason_Mdl | modifyReasonMdl |

## RPC interface
| method | params | returns |
|---|---|---|
| getSuggestionCostCenter | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<DefineCostCenter_Mdl> |
| saveRecord | DefineCostCenter_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl modifyReasonMdl | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getTxtHUnderCostCenter | DmHelper pageing, boolean blnToEndOfFile, String strCode | DmResultPageing<List<TxtHUnderCostCenter_Mdl>> |
| getTxtHActive | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getGrdUnderCostCenter | String strCode | DmResult<List<KeyFieldExtended>> |

## Validator `DefineCostCenter_Vld`
```java
public class DefineCostCenter_Vld {
    public static void chkSave(DefineCostCenter_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getSharedKeyFieldName(), "Cost center", 100);
        DmShared.isValidText(mrTemp.getManualCode(), "Manual code", 200);
        DmShared.isValidText(mrTemp.getActiveCode(), "Acitve", 300);
    }
}
```

## Server `DefineCostCenter_SrvImpl.java` (819 lines)
Helpers used: `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.setSsnOldData`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.vNull`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `SELECT CODE As SearchCode,NAME FROM CostCenterMast WHERE isNull(CType,'C') = 'C' ORDER BY NAME`

### `getMoveRec`
`public DmResult<DefineCostCenter_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT CCM.Code, CCM.Name, CCM.UndercostCenter, CCM1.Name AS UnderCostCenterName, CCM.ManualCode, CCM.Address, CCM.isActive, CCM.preparedBy,CCM.preparedDt,CCM.preparedByIP,CCM.u_EntDt, CCM.u_Name FROM CostCenterMast CCM LEFT JOIN CostCenterMast CCM1 ON CCM.UnderCostCenter = CCM1.Code WHERE isNull(CCM.CType, 'C') = 'C' AND CCM.Code = :code`
- Params: `:code` ← `DmShared.iNull(strKey).toString()`

### `saveRecord`
`public DmResult<String> saveRecord(DefineCostCenter_Mdl mrTemp)`

- SQL 1: `SELECT COUNT(CCM.Code) AS Cnt FROM CostCenterMast CCM WHERE CCM.Name = :name AND CCM.Code <> :code`
- SQL 2: `SELECT COUNT(CCM.Code) AS Cnt FROM CostCenterMast CCM WHERE CCM.ManualCode = :manualCode AND CCM.Code <> :code`
- SQL 3: `SELECT CODE,SNo,UnderCostCenter FROM CostCenterPath WHERE CODE= :code ORDER BY SNo`
- SQL 4: `DELETE FROM CostCenterPath WHERE CODE = :code`
- SQL 5: `INSERT INTO costcentermast ( CODE, NAME, ManualCode, Address, U_EntDt, Site_Code, UnderCostCenter, isActive, CType, preparedBy,preparedByIP,preparedDt ) VALUES ( :code, :name, :manualCode, :address, :preparedDt, :site_Code, :underCostCenter, :isActive, 'C', :preparedBy,:preparedByIP,:preparedDt) ;`
- SQL 6: `Update CostCenterMast Set Name = :name , UnderCostCenter = :underCostCenter ,U_Name = :userName ,U_EntDt = :U_EntDt ,ManualCode = :manualCode , Address = :address,isActive = :isActive Where Code = :code`
- SQL 7: `INSERT INTO CostCenterPath(CODE,SNo,UnderCostCenter) VALUES (:code,:sno,:uCostCenter)`
- SQL 8: `INSERT INTO CostCenterPath(CODE,SNo,UnderCostCenter) VALUES (:code,:sno,:uCostCenter)`
- SQL 9: `SELECT CODE,SNo,UnderCostCenter FROM CostCenterPath WHERE CODE = :code ORDER BY SNo`
- SQL 10: `SELECT CODE,SNo,UnderCostCenter FROM CostCenterPath WHERE UnderCostCenter = :code ORDER BY SNo`
- SQL 11: `SELECT CODE,SNo,UnderCostCenter FROM CostCenterPath WHERE CODE = :code AND SNo >= :sno ORDER BY SNo`
- SQL 12: `DELETE FROM CostCenterPath WHERE CODE = :code`
- SQL 13: `INSERT INTO CostCenterPath (CODE, SNo, UnderCostCenter) VALUES ( :code, :sno, :uCostCenter ) ;`
- SQL 14: `INSERT INTO CostCenterPath (CODE, SNo, UnderCostCenter) VALUES ( :code, :sno, :uCostCenter) ;`
- Params: `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:manualCode` ← `DmShared.xNull(mrTemp.getManualCode())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:code` ← `DmShared.xNull(mrTemp.getUnderCostCode())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:manualCode` ← `DmShared.xNull(mrTemp.getManualCode())`; `:address` ← `DmShared.xNull(mrTemp.getDescription())`; `:preparedBy` ← `userName`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:preparedDt` ← `timestamp`; `:site_Code` ← `siteCode`; `:underCostCenter` ← `DmShared.foriegnKey(mrTemp.getUnderCostCode())`; `:isActive` ← `DmShared.vNull(mrTemp.getActiveCode()) == 0 ? "0" : "1"`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:underCostCenter` ← `DmShared.foriegnKey(mrTemp.getUnderCostCode())`; `:userName` ← `userName`; `:U_EntDt` ← `timestamp`; `:manualCode` ← `DmShared.xNull(mrTemp.getManualCode())`; `:address` ← `DmShared.xNull(mrTemp.getDescription())`; `:isActive` ← `DmShared.vNull(mrTemp.getActiveCode()) == 0 ? "0" : "1"`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:sno` ← `intSNo`; `:uCostCenter` ← `DmShared.foriegnKey(rsTemp1.getString("UnderCostCenter"))`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:sno` ← `intSNo`; `:uCostCenter` ← `DmShared.foriegnKey(mrTemp.getUnderCostCode())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:code` ← `rsTemp2.getString("UnderCostCenter")`; `:sno` ← `rsTemp2.getInt("SNo")`; `:code` ← `rsTemp2.getString("CODE")`; `:code` ← `DmShared.xNull(rsTemp2.getString("CODE"))`; `:sno` ← `intSNo`; `:uCostCenter` ← `DmShared.xNull(rsTemp.getString("UnderCostCenter"))`; `:code` ← `DmShared.xNull(rsTemp2.getString("CODE"))`; `:sno` ← `intSNo`; `:uCostCenter` ← `DmShared.xNull(rsTemp3.getString("UnderCostCenter"))`
- Errors: "Invalid key." | "Cost Center Name Already Exists." | "Manual Code Already Exists."
- Other calls: `DefineCostCenter_Vld.chkSave`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl modifyReasonMdl)`

- SQL 1: `Select Count(UnderCostCenter) As Cnt From CostCenterPath Where UnderCostCenter= :UnderCostCenter`
- SQL 2: `Delete From CostCenterPath Where Code = :code`
- SQL 3: `Delete From CostCenterMast Where Code = :code`
- Params: `:UnderCostCenter` ← `strKey`; `:code` ← `strKey`; `:code` ← `strKey`
- Errors: "Record Is In Use. You Cannot Delete This Record."

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT CCM.Code, '' AS V_Type, CCM.Name AS Cost_Center_Name, CCM1.Name AS Under_Cost_Center, CCM.U_Name, CCM.U_EntDt, CCM.ManualCode, CCM.Address AS Description, CCM.isActive FROM CostCenterMast CCM LEFT JOIN CostCenterMast CCM1 ON CCM.UnderCostCenter = CCM1.Code WHERE isNull(CCM.CType, 'C') = 'C'`

### `getTxtHUnderCostCenter`
`public DmResultPageing<List<TxtHUnderCostCenter_Mdl>> getTxtHUnderCostCenter(DmHelper helper, boolean blnToEndOfFile, String strCode)`

- SQL 1: `SELECT CCM.Code,CCM.Name AS NAME, CCM1.Name AS UName FROM costcentermast CCM LEFT JOIN costcentermast CCM1 ON CCM.UnderCostCenter = CCM1.Code WHERE isNull(CCM.CType,'C')='C' AND CCM.Code <> :code AND CCM.Code NOT IN (SELECT CODE FROM costcenterpath WHERE UnderCostCenter = :code)`
- Params: `:code` ← `DmShared.iNull(strCode).toString()`

### `getTxtHActive`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHActive(DmHelper helper, boolean blnToEndOfFile)`


### `getGrdUnderCostCenter`
`public DmResult<List<KeyFieldExtended>> getGrdUnderCostCenter(String strCode)`

- SQL 1: `SELECT CCM.Code,CCm.Name FROM CostCenterPath CCP LEFT JOIN CostCenterMast CCM ON CCM.Code = CCP.UnderCostCenter WHERE CCP.Code = :code ORDER BY CCP.SNo DESC`
- Params: `:code` ← `DmShared.iNull(strCode).toString()`

### `getSuggestionCostCenter`
`public DmResultPageing<List<String>> getSuggestionCostCenter(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT CODE,NAME FROM CostCenterMast WHERE isNull(CType,'C') = 'C'`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `dataman.libraryMenu.self.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Cost Center Name Already Exists." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Manual Code Already Exists." |
| deleteRecord | `if (rsTemp.getLong("cnt") > 0)` | "Record Is In Use. You Cannot Delete This Record." |

## Notes (hand-written)
_No hand-written notes yet._
