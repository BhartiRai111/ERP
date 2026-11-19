# pb-job-work / jobWorkMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/jobWork/src/jobWorkMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: JobWorkMast
- Update tables: JobWorkMast
- Delete cascade (in order): JobWorkMast
- Duplicate checks on save:
  - `SELECT COUNT(name) AS cnt FROM JobWorkMast WHERE LOWER(name) = :name AND code <> :code`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Name (Text); Job Work Group (Text); Service Item (Text)
- Lookups/suggestions: getSuggestionName, getTxtHJobWorkGroup, getTxtHCostCenter, getTxtHServiceItem
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `JobWorkMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Name | DmSuggestBox | `txtJobWorkName` | `35, 10, true, "Name", DmEnum.DmDataType.STRING` |
| 2 | Job Work Group | DmTextHelp | `txtHJobWorkGroup` | `KeyField_PrpAcs, 4, true` |
| 3 | Cost Center | DmTextHelp | `txtHCostCenter` | `KeyField_PrpAcs, 4, false` |
| 4 | Service Item | DmTextHelp | `txtHServiceItem` | `TxtHServiceItem_PrpAcs, 4, true` |
| 5 | HSN Code | DmTextBox | `txtHsnCode` | `100, false` |

Other widgets (not in tab list): `txtServiceItem` (DmSuggestBox), `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `dmFind`: Job Work Group [Text, w0, name]; Job Work Group Name [Text, w150, JobWorkGroupName]; Cost Center Name [Text, w150, costCenterName]; Service Item Name [Text, w150, serviceItemName]
- `txtHJobWorkGroup`: Type [Text, w0, name]
- `txtHCostCenter`: Type [Text, w0, name]
- `txtHServiceItem`: Service Item [Text, w0, name]; HSN [Text, w100, hsn]

**Lookup / suggestion / find sources (server RPC called):**
- `txtJobWorkName` → `getSuggestionName`
- `txtHJobWorkGroup` → `getTxtHJobWorkGroup`
- `txtHCostCenter` → `getTxtHCostCenter`
- `txtHServiceItem` → `getTxtHServiceItem`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtJobWorkName.getText())
- `JobWorkGroupCode` ← DmShared.xNull(txtHJobWorkGroup.getTag())
- `JobWorkGroupName` ← DmShared.xNull(txtHJobWorkGroup.getText())
- `CostCenterCode` ← DmShared.xNull(txtHCostCenter.getTag())
- `CostCenterName` ← DmShared.xNull(txtHCostCenter.getText())
- `ServiceItemCode` ← DmShared.xNull(txtHServiceItem.getTag())
- `ServiceItemName` ← DmShared.xNull(txtHServiceItem.getText())
- `HsnCode` ← DmShared.xNull(txtHsnCode.getTag())
- `HsnName` ← DmShared.xNull(txtHsnCode.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtJobWorkName.setText` ← data.getSharedKeyFieldName()
- `txtHJobWorkGroup.setTag` ← data.getJobWorkGroupCode()
- `txtHJobWorkGroup.setText` ← data.getJobWorkGroupName()
- `txtHCostCenter.setTag` ← data.getCostCenterCode()
- `txtHCostCenter.setText` ← data.getCostCenterName()
- `txtHServiceItem.setTag` ← data.getServiceItemCode()
- `txtHServiceItem.setText` ← data.getServiceItemName()
- `txtHsnCode.setTag` ← data.getHsnCode()
- `txtHsnCode.setText` ← data.getHsnName()
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtHsnCode.setEnabled(false)`

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
txtJobWorkName.setFocus(true);
txtHsnCode.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`modifyReason.addCallBack` → `onOk()`
```java
try {
JobWorkMaster.this.modifyReasonMdl = modifyReason;
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
txtJobWorkName.setFocus(true);
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(JobWorkMaster.this);
```

`txtJobWorkName.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getSuggestionName(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Str
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtJobWorkName).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtJobWorkName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtJobWorkName).show();
```

`txtJobWorkName.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtJobWorkName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtJobWorkName).show();
```

`txtHJobWorkGroup.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHJobWorkGroup(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<K
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHJobWorkGroup).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHJobWorkGroup.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHJobWorkGroup).show();
```

`txtHJobWorkGroup.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHJobWorkGroup.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHJobWorkGroup).show();
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

`txtHServiceItem.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHServiceItem(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHServiceItem).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHServiceItem.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHServiceItem).show();
```

`txtHServiceItem.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHServiceItem.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHServiceItem).show();
```

`txtHServiceItem.addTextHelpCallBack` → `getSelectedRow()`
```java
txtHsnCode.setTag(DmShared.xNull(t1Row.getSharedPreparedDate()));
txtHsnCode.setText(DmShared.xNull(t1Row.getSharedPreparedBy()));
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

**Top control options:** setOtherRequired(false), setRefreshRequired(false), setDisplay(true)

_Print permission "P" is removed for this screen (replace "P" with "*")._

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."

## Files
| file | lines |
|---|---|
| client/JobWorkMaster.java | 505 |
| client/JobWorkMaster_Dsg.java | 243 |
| server/JobWorkMaster_SrvImpl.java | 704 |
| shared/Find_Mdl.java | 50 |
| shared/JobWorkMaster_Mdl.java | 97 |
| shared/JobWorkMaster_Srv.java | 29 |
| shared/JobWorkMaster_SrvAsync.java | 29 |
| shared/JobWorkMaster_Vld.java | 14 |

## UI labels / columns (client)
`Name`, `Job Work Group`, `Cost Center`, `Service Item`, `HSN Code`

## Models (shared DTOs)
### JobWorkMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | jobWorkGroupCode |
| String | jobWorkGroupName |
| String | costCenterCode |
| String | costCenterName |
| String | serviceItemCode |
| String | serviceItemName |
| String | hsnCode |
| String | hsnName |
| ModifyReason_Mdl | modifyReasonMdl |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | jobWorkGroupName |
| String | costCenterName |
| String | serviceItemName |
| String | hsnName |

## RPC interface
| method | params | returns |
|---|---|---|
| getMoveRec | String siteCode | DmResult<JobWorkMaster_Mdl> |
| saveRecord | JobWorkMaster_Mdl mrTemp | DmResult<String> |
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| deleteRecord | String strKey, ModifyReason_Mdl modifyReasonMdl | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getSuggestionName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getTxtHJobWorkGroup | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHCostCenter | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHServiceItem | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `JobWorkMaster_Vld`
```java
public class JobWorkMaster_Vld {
    public static void chkSave(JobWorkMaster_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getSharedKeyFieldName().trim(), "Name", 100);
        DmShared.isValidText(mrTemp.getJobWorkGroupName(), "Job Work Group", 200);
        DmShared.isValidText(mrTemp.getServiceItemName().trim(), "Service Item", 300);
    }
}
```

## Server `JobWorkMaster_SrvImpl.java` (704 lines)
Helpers used: `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnUserName`, `DmServer.getUserWithDateTime`, `DmServer.setSsnOldData`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getMoveRec`
`public DmResult<JobWorkMaster_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT jwm.code,jwm.name , jwm.jobWorkGroupCode,jwg.name AS jobWorkGroupName , jwm.costCenterCode,ccn.name AS costCenterName , jwm.serviceItemCode,im.name AS serviceItemName , jwm.hsnCode ,hm.hsn AS hsn , jwm.u_EntDt,jwm.u_Name , jwm.preparedBy,jwm.preparedDt,jwm.transferDt,jwm.preparedByIP FROM JobWorkMast AS jwm LEFT JOIN jobWorkGroup jwg ON jwg.code=jwm.jobWorkGroupCode LEFT JOIN costCenterMast ccn ON ccn.code=jwm.costCenterCode LEFT JOIN itemMast im on im.code = jwm.serviceItemCode LEFT JOIN hsnMast hm on hm.code = jwm.hsnCode WHERE jwm.code= :code`
- Params: `:code` ← `strKey`

### `saveRecord`
`public DmResult<String> saveRecord(JobWorkMaster_Mdl mrTemp)`

- SQL 1: `SELECT COUNT(name) AS cnt FROM JobWorkMast WHERE LOWER(name) = :name AND code <> :code`
- SQL 2: `Insert Into JobWorkMast(code ,name,jobWorkGroupCode,costCenterCode,serviceItemCode,hsnCode,u_EntDt,u_Name ,preparedBy,preparedDt,transferDt,preparedByIP) Values (:code ,:name,:jobWorkGroupCode,:costCenterCode,:serviceItemCode,:hsnCode,:u_EntDt,:u_Name,:preparedBy,:preparedDt,:transferDt,:preparedByIP)`
- SQL 3: `Update JobWorkMast Set name = :name, jobWorkGroupCode = :jobWorkGroupCode, costCenterCode = :costCenterCode, serviceItemCode = :serviceItemCode, hsnCode = :hsnCode, u_Name = :u_Name, u_EntDt = :U_EntDt Where code = :code`
- Params: `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName()).toLowerCase()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:jobWorkGroupCode` ← `DmShared.foriegnKey(mrTemp.getJobWorkGroupCode())`; `:costCenterCode` ← `DmShared.foriegnKey(mrTemp.getCostCenterCode())`; `:serviceItemCode` ← `DmShared.foriegnKey(mrTemp.getServiceItemCode())`; `:hsnCode` ← `DmShared.foriegnKey(mrTemp.getHsnCode())`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `timestamp`; `:transferDt` ← `DmServer.getServerTimestamp()`; `:preparedBY` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:jobWorkGroupCode` ← `DmShared.foriegnKey(mrTemp.getJobWorkGroupCode())`; `:costCenterCode` ← `DmShared.foriegnKey(mrTemp.getCostCenterCode())`; `:serviceItemCode` ← `DmShared.foriegnKey(mrTemp.getServiceItemCode())`; `:hsnCode` ← `DmShared.foriegnKey(mrTemp.getHsnCode())`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `DmServer.getServerTimestamp()`
- Errors: " Name already exists." | "Invalid key."
- Other calls: `JobWorkMaster_Vld.chkSave`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `Select jwm.code, jwm.name From JobWorkMast jwm left join jobWorkGroup as jwg on (jwg.code=jwm.jobWorkGroupCode) left join costCenterMast as ccn on (ccn.code=jwm.costCenterCode) left join itemMast im on im.code = jwm.serviceItemCode left join hsnMast hm on hm.code = jwm.hsnCode Order By name`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl modifyReasonMdl)`

- SQL 1: `Delete From JobWorkMast Where code= :code`
- Params: `:code` ← `strKey`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT jwm.code , jwm.Name ,jwm.jobWorkGroupCode ,jwg.name AS jobWorkGroupName ,jwm.costCenterCode , ccm.name AS costCenterName ,jwm.serviceItemCode ,im.name AS serviceItemName FROM JobWorkMast jwm LEFT JOIN jobWorkGroup jwg ON jwg.code = jwm.jobWorkGroupCode LEFT JOIN costCenterMast ccm ON ccm.code = jwm.costCenterCode LEFT JOIN itemMast im ON im.code = jwm.serviceItemCode`

### `getSuggestionName`
`public DmResultPageing<List<String>> getSuggestionName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select jwm.code,jwm.name From JobWorkMast as jwm`

### `getTxtHJobWorkGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHJobWorkGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT jg.code , jg.name FROM jobWorkGroup jg`

### `getTxtHCostCenter`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCostCenter(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ccm.code , ccm.name FROM costCenterMast ccm`

### `getTxtHServiceItem`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHServiceItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , im.hsnCode ,hm.hsn FROM itemMast im LEFT JOIN hsnMast hm ON hm.code = im.hsnCode WHERE im.filterItemType = :filterItemType`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("OI", "manualCode", "itemType", "code", cnTem`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `paperbiz.jobWork.jobWorkMaster.shared`, `paperbiz.projectMenu.self.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | " Name already exists." |
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |

## Notes (hand-written)
_No hand-written notes yet._
