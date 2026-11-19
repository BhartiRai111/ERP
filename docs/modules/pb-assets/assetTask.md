# pb-assets / assetTask

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/assets/src/assetTask/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: taskScheduleHistory, taskSchedule
- Update tables: taskSchedule
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHStatus, getTxtHUser, getTxtHMachineItem, getTxtHMachineName, getTxtHTaskType
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `AssetTask`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmGridPaging | `grdTask` | `GrdPaging_PrpAcs, 23, false` |

Other widgets (not in tab list): `txtSearchValue` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `grdTask`: Description [Text, w100, taskDescription]; Remark [Text, w280, remark]; Schedule Date / Time [Text, w130, scheduleDateTime]; Asset Name [Text, w180, assetName]; Complain No. [Text, w120, recId];  [Text, w25]

**Lookup / suggestion / find sources (server RPC called):**
- `grdTask` → `getTaskSchedule`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`taskScheduleDetail.addCallBack` → `close()`
```java
reQuery();
```

`setting.addCallBack` → `onOk()`
```java
searchMdl = searchValue;
reQuery();
Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
public void execute() {
txtSearchValue.setFocus(true);
```

`setting.addCallBack` → `execute()`
```java
txtSearchValue.setFocus(true);
```

`grdTask.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(false).getTaskSchedule(searchValue_Mdl, helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<Lis
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdTaskSchedule_Mdl>> result) {
if (result.isStatus()) {
grdTask.setData(result.getData(), result.getPaging());
grdTask.getDmGrid().commitChanges();
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`grdTask.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
grdTask.setData(result.getData(), result.getPaging());
grdTask.getDmGrid().commitChanges();
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`grdTask.addCallBack` → `getParam()`
```java
return valueIntialize();
```
- Confirm: "Are you sure? You want to close."

### Screen `FilterPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Sch. From Date | DmTextBox | `txtSchFromDate` | `11, false` |
| 2 | Sch. To Date | DmTextBox | `txtSchToDate` | `11, false` |
| 3 | Asset Item | DmTextHelp | `txtHAssetItem` | `KeyField_PrpAcs, 6, false` |
| 4 | Asset Name | DmTextHelpMulti | `txtHAssetName` | `KeyField_PrpAcs, 5` |
| 5 | Status | DmTextHelp | `txtHStatus` | `KeyField_PrpAcs, 5, true, false` |

**Grid / popup columns** (header, width, db field):
- `txtHAssetName`: Name [Text, w125, Name]
- `txtHAssetItem`: Machine Item [Text, w0, name]
- `txtHStatus`: Status [Text, w0]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHAssetItem` → `getTxtHMachineItem`
- `txtHStatus` → `getTxtHStatus`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHAssetItem.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHMachineItem(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHAssetItem).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHAssetItem.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAssetItem).show();
```

`txtHAssetItem.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAssetItem.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAssetItem).show();
```

`txtHAssetItem.addTextHelpCallBack` → `getSelectedRow()`
```java
txtHAssetName.setTag((""));
txtHAssetName.setText("");
```

`txtHAssetName.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHMachineName(txtHAssetItem.getTag(), helper, blnToEndOfFile, new AsyncCallback
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHAssetName).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHAssetName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAssetName).show();
```

`txtHAssetName.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAssetName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAssetName).show();
```

`txtHStatus.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHStatus(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFiel
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHStatus).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHStatus.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHStatus).show();
```

`txtHStatus.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHStatus.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHStatus).show();
```
- Alert: "Schedule From Date should be less than or equal to Schedule To Date."

### Screen `TaskScheduleDetail`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Assign To | DmTextHelp | `txtHAssignTo` | `KeyField_PrpAcs, 7, false` |
| 2 | Task Type | DmTextHelp | `txtHTaskType` | `KeyField_PrpAcs, 7, true, false` |
| 3 | Complain No. | DmTextBox | `txtComplainNo` | `20, false` |
| 4 | Schedule Date & Time | DmTextBox | `txtScheduleDate` | `11, false` |
| 5 | - | DmTextBoxTime | `txtScheduleTime` | `false` |
| 6 | Status Remark | DmTextBox | `txtStatusRemark` | `250, false` |
| 7 | Next Follow-Up | DmTextBox | `txtFollowUpDate` | `11, false` |
| 8 | - | DmTextBoxTime | `txtFollowUpTime` | `false` |
| 9 | Asset Item | DmTextBox | `txtAssetItem` | `20, false` |
| 10 | Asset Name | DmTextBox | `txtAssetName` | `20, false` |

Other widgets (not in tab list): `txtDescription` (DmTextBox), `txtRemark` (DmTextBox), `grdTaskHistory` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHAssignTo`: Name [Text, w0, name]
- `txtHTaskType`: Name [Text, w0, name]
- `grdTaskHistory`: Schedule Date / Time [Text, w130]; Status Remark [Text, w1]; Assign To [Text, w150]; Task Type [Text, w100]; Status [Text, w100]; User Name [Text, w100]; Entry Date [Text, w100]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHAssignTo` → `getTxtHUser`
- `txtHTaskType` → `getTxtHTaskType`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtDescription.setReadOnly(true)`
- `txtRemark.setReadOnly(true)`
- `txtScheduleDate.setEnabled(false)`
- `txtScheduleTime.setEnabled(false)`
- `txtAssetItem.setEnabled(false)`
- `txtAssetName.setEnabled(false)`
- `txtComplainNo.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
txtDescription.setReadOnly(true);
txtRemark.setReadOnly(true);
txtScheduleDate.setEnabled(false);
txtScheduleTime.setEnabled(false);
txtAssetItem.setEnabled(false);
txtAssetName.setEnabled(false);
txtComplainNo.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHAssignTo.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHUser(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHAssignTo).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHAssignTo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAssignTo).show();
```

`txtHAssignTo.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAssignTo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAssignTo).show();
```

`txtHTaskType.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHTaskType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHTaskType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHTaskType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTaskType).show();
```

`txtHTaskType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHTaskType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTaskType).show();
```
- Alert: "Status Remark is mandatory."
- Alert: "Follow-Up Date is mandatory."
- Alert: "Follow-Up Time is mandatory."
- Alert: "Follow-Up Date must be greater than or equal to Schedule Date."
- Alert: "Follow-Up Time should be greater than or equal to Schedule time."
- Alert: "Follow-Up Date must be greater than or equal to current Date."
- Alert: "Follow-Up Time should be greater than or equal to current time."

## Files
| file | lines |
|---|---|
| client/AssetTask.java | 316 |
| client/AssetTask_Dsg.java | 175 |
| client/FilterPopup.java | 222 |
| client/FilterPopup_Dsg.java | 213 |
| client/TaskScheduleDetail.java | 385 |
| client/TaskScheduleDetail_Dsg.java | 446 |
| server/AssetTask_SrvImpl.java | 697 |
| shared/AssetTask_Srv.java | 26 |
| shared/AssetTask_SrvAsync.java | 26 |
| shared/GrdTaskSchedule_Mdl.java | 167 |
| shared/SearchValue_Mdl.java | 104 |
| shared/TaskScheduleHistory_Mdl.java | 153 |

## UI labels / columns (client)
`Assign To`, `Task Type`, `Complain No.`, `Schedule Date & Time`, `Description`, `Remark`, `Status Remark`, `Next Follow-Up`, `Asset Item`, `Asset Name`, `Sch. From Date`, `Sch. To Date`, `Status`

## Models (shared DTOs)
### GrdTaskSchedule_Mdl (extends `TaskScheduleHistory_Mdl implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | voucherTypeCode |
| String | voucherTypeName |
| String | description |
| String | remark |
| String | taskCategoryCode |
| String | taskOwner |
| String | alertBefore |
| String | followUpDate |
| String | followUpTime |
| String | isChecked |
| boolean | current |
| Timestamp | u_EntDt_LatestLine |
| String | assetName |
| String | assetCode |
| String | assetItemName |
| String | assetItemCode |
| TaskScheduleHistory_Mdl | taskScheduleHistory_Mdl |

### TaskScheduleHistory_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | taskId |
| String | taskRowId |
| String | scheduleDate |
| String | scheduleTime |
| String | scheduleDateTime |
| String | assignTo |
| String | taskTypeCode |
| String | taskTypeName |
| String | statusRemark |
| String | statusCode |
| String | statusName |
| String | preparedBy |
| Timestamp | preparedByDate |
| String | u_name |
| String | u_EntDt |

### SearchValue_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | schFromDate |
| String | schToDate |
| String | assetItemTag |
| String | assetItemText |
| String | assetTag |
| String | assetText |
| String | statusTag |
| String | statusText |
| String | fieldName |
| String | fieldNameValue |

## RPC interface
| method | params | returns |
|---|---|---|
| getTaskSchedule | SearchValue_Mdl searchValue_Mdl, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdTaskSchedule_Mdl>> |
| getTxtHStatus | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| saveTaskScheduleRecord | List<GrdTaskSchedule_Mdl> listTaskSchedule | DmResult<String> |
| getTxtHTaskType | DmHelper helper, boolean blnEndofFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHUser | DmHelper helper, boolean blnEndofFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHMachineItem | DmHelper helper, boolean blnEndofFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHMachineName | String assetItemCode,DmHelper helper, boolean blnEndofFile | DmResultPageing<List<KeyFieldExtended>> |
| getTaskScheduleHistory | String taskId | DmResult<List<TaskScheduleHistory_Mdl>> |

## Server `AssetTask_SrvImpl.java` (697 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.xNull`

### `getTaskSchedule`
`public DmResultPageing<List<GrdTaskSchedule_Mdl>> getTaskSchedule(SearchValue_Mdl searchValueMdl, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select ts.taskId as docId,ts.recId, ts.v_Type,ts.description as taskDescription,ts.remark,ts.scheduleDate,ts.scheduleTime, concat(format(ts.scheduleDate, 'dd-MMM-yyyy'),' / ', format(ts.scheduleTime, '00.00')) as scheduleDateTime,ts.taskOwner,ts.taskType, ts.assetCode, am.manualCode as assetName, ts.u_EntDt_LatestLine,ts.preparedByDate, ts.u_name, format(ts.u_EntDt , 'dd-MMM-yyyy HH:mm') as u_EntDt, ts.preparedBy, ts.assignTo, ts.isStatus,aim.name assetItemName,am.assetItemCode from taskSchedule as ts left join voucher_Type as vt on ts.v_Type=vt.v_Type left join assetMast as am on ts.assetCode=am.code left join assetItemMast aim on am.assetItemCode=aim.code where vt.category=' {…} ' {…}`
- Params: `:fromDate` ← `DmServer.convertDate(searchValueMdl.getSchFromDate())`; `:toDate` ← `DmServer.convertDate(searchValueMdl.getSchToDate())`; `:assetItemCode` ← `DmShared.xNull(searchValueMdl.getAssetItemTag())`
- Other calls: `CANCEL.getId`, `CLOSE.getId`, `COMPLETE.getId`, `FOLLOW_UP.getId`, `OPEN.getId`, `RE_OPEN.getId`, `START.getId`, `TaskStatus.getViaKey`, `TaskType.getViaKey`

### `getTxtHStatus`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHStatus(DmHelper helper, boolean blnEndofFile)`

- Other calls: `TaskStatus.values`

### `saveTaskScheduleRecord`
`public DmResult<String> saveTaskScheduleRecord(List<GrdTaskSchedule_Mdl> listTaskSchedule)`

- Other calls: `OPEN.getId`, `TaskStatus.getViaKey`

### `saveTaskSchedule`
`private void saveTaskSchedule(GrdTaskSchedule_Mdl mrTemp, int days, boolean isHistorySave, STEnum.TaskStatus taskStatus, Connection cnTemp) throws Exception`

- SQL 1: `Select startDate, taskOwner, isStatus, u_EntDt_LatestLine from taskSchedule where taskId = :taskId`
- SQL 2: `Insert into taskScheduleHistory (taskId, taskRowId, scheduleDate, scheduleTime, assignTo, taskType, statusRemark, isStatus, preparedBy, preparedByDate, u_name, u_EntDt) Select taskId, :taskRowId, scheduleDate, scheduleTime, assignTo, taskType, :statusRemark, :isStatus, preparedBy, preparedByDate, :u_name, :u_EntDt from taskSchedule where taskId = :taskId`
- SQL 3: `Insert Into taskSchedule (taskId, v_type, scheduleDate, scheduleTime, description, remark, taskCategoryCode, taskOwner, assignTo, alertBefore, taskType, isStatus, preparedBy, preparedByDate, u_EntDt_LatestLine) values (:taskId, :v_type, :scheduleDate, :scheduleTime, :description, :remark, :taskCategoryCode, :taskOwner, :assignTo, :alertBefore, :taskType, :isStatus, :preparedBy, :preparedByDate, :preparedByDate)`
- SQL 4: `Update taskSchedule set scheduleDate = :scheduleDate, scheduleTime = :scheduleTime, description = :description, remark = :remark, taskCategoryCode = :taskCategoryCode, taskOwner = :taskOwner, assignTo = :assignTo, alertBefore = :alertBefore, taskType = :taskType, isStatus = :isStatus, taskRowId = :taskRowId, u_Name = :u_Name, u_EntDt = :u_EntDt, u_EntDt_LatestLine = :u_EntDt where taskId = :taskId`
- Params: `:taskId` ← `DmShared.iNull(mrTemp.getTaskId()).toString()`; `:taskId` ← `DmShared.iNull(mrTemp.getTaskId()).toString()`; `:taskRowId` ← `mrTemp.getTaskRowId()`; `:statusRemark` ← `DmShared.xNull(history_Mdl.getStatusRemark())`; `:isStatus` ← `DmShared.iNull(history_Mdl.getStatusCode()).toString()`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `DmServer.getServerTimestamp()`; `:taskId` ← `DmShared.iNull(mrTemp.getTaskId()).toString()`; `:v_Type` ← `DmShared.foriegnKey(mrTemp.getVoucherTypeCode())`; `:scheduleDate` ← `DmServer.convertDate(mrTemp.getScheduleDate())`; `:scheduleTime` ← `DmServer.vNull(mrTemp.getScheduleTime(), "00.00")`; `:description` ← `DmShared.xNull(mrTemp.getDescription())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:taskCategoryCode` ← `DmShared.foriegnKey(mrTemp.getTaskCategoryCode())`; `:taskOwner` ← `DmShared.xNull(mrTemp.getTaskOwner())`; `:assignTo` ← `DmShared.xNull(mrTemp.getAssignTo())`; `:alertBefore` ← `DmShared.xNull(mrTemp.getAlertBefore())`; `:taskType` ← `DmShared.iNull(mrTemp.getTaskTypeCode()).toString()`; `:isStatus` ← `DmShared.iNull(mrTemp.getStatusCode()).toString()`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:preparedByDate` ← `DmServer.getServerTimestamp()`; `:taskId` ← `DmShared.iNull(mrTemp.getTaskId()).toString()`; `:scheduleDate` ← `DmServer.convertDate(mrTemp.getScheduleDate())`; `:scheduleTime` ← `DmServer.vNull(mrTemp.getScheduleTime(), "00.00")`; `:description` ← `DmShared.xNull(mrTemp.getDescription())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:taskCategoryCode` ← `DmShared.foriegnKey(mrTemp.getTaskCategoryCode())`; `:taskOwner` ← `DmShared.xNull(mrTemp.getTaskOwner())`; `:assignTo` ← `DmShared.xNull(mrTemp.getAssignTo())`; `:alertBefore` ← `DmShared.xNull(mrTemp.getAlertBefore())`; `:taskType` ← `DmShared.iNull(mrTemp.getTaskTypeCode()).toString()`; `:isStatus` ← `DmShared.iNull(mrTemp.getStatusCode()).toString()`; `:taskRowId` ← `DmShared.foriegnKey(mrTemp.getTaskRowId())`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `DmServer.getServerTimestamp()`
- Errors: "Invalid key." | "Record has been changed, can't modify this record, please refresh then update" | "Task already completed." | "Task already cancel." | "Task already close." | "Only Task Owner can close / cancel task." | "You can only Re-Open after Close / Cancel / Complete."
- Other calls: `CANCEL.getId`, `CLOSE.getId`, `COMPLETE.getId`

### `getTxtHUser`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHUser(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select code, user_Name as name from userMast`

### `getTxtHMachineItem`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHMachineItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select aim.code, aim.name from assetItemMast aim left join assetSiteList asl on asl.code= aim.Code where asl.site_Code = :siteCode`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHMachineName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHMachineName(String assetItemCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code, manualCode as name from assetMast where assetItemCode = :assetItem`
- Params: `:assetItem` ← `assetItemCode`

### `getTxtHTaskType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTaskType(DmHelper helper, boolean blnEndofFile)`

- Other calls: `TaskType.values`

### `copyTaskHistory`
`private TaskScheduleHistory_Mdl copyTaskHistory(GrdTaskSchedule_Mdl mrTemp)`


### `getTaskScheduleHistory`
`public DmResult<List<TaskScheduleHistory_Mdl>> getTaskScheduleHistory(String taskId)`

- SQL 1: `Select ts.taskId, ts.taskRowId, concat(format(ts.scheduleDate, 'dd-MMM-yyyy'),' / ', format(ts.scheduleTime, '00.00')) as scheduleDateTime, ts.scheduleDate, ts.scheduleTime, ts.assignTo, ts.taskType, ts.statusRemark, ts.isStatus, ts.preparedBy , ts.preparedByDate, ts.u_name, format(ts.u_EntDt , 'dd-MMM-yyyy HH:mm') as u_EntDt from taskScheduleHistory as ts where ts.taskId = :taskId order by ts.taskRowId desc`
- Params: `:taskId` ← `DmShared.iNull(taskId).toString()`
- Other calls: `TaskStatus.getViaKey`, `TaskType.getViaKey`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxTime.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `paperbiz.assets.assetTask.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveTaskSchedule | `if (DmShared.iNull(mrTemp.getTaskId()).longValue() == 0)` | "Invalid key." |
| saveTaskSchedule | `if (mrTemp.getU_EntDt_LatestLine().getTime() != rsTemp.getTimestamp("u_EntDt_LatestLine").getTime())` | "Record has been changed, can't modify this record, please refresh then update" |
| saveTaskSchedule | `if (taskStatus == STEnum.TaskStatus.COMPLETE && DmShared.iNull(rsTemp.getString("isStatus")).intValue() == STEnum.TaskStatus.COMPLETE.getId())` | "Task already completed." |
| saveTaskSchedule | `if (taskStatus == STEnum.TaskStatus.CANCEL && DmShared.iNull(rsTemp.getString("isStatus")).intValue() == STEnum.TaskStatus.CANCEL.getId())` | "Task already cancel." |
| saveTaskSchedule | `if (taskStatus == STEnum.TaskStatus.CLOSE && DmShared.iNull(rsTemp.getString("isStatus")).intValue() == STEnum.TaskStatus.CLOSE.getId())` | "Task already close." |
| saveTaskSchedule | `if (!DmShared.xNull(rsTemp.getString("taskOwner")).trim().equalsIgnoreCase(DmShared.xNull(mrTemp.getTaskOwner()).trim()))` | "Only Task Owner can close / cancel task." |
| saveTaskSchedule | `if (taskStatus != STEnum.TaskStatus.RE_OPEN)` | "You can only Re-Open after Close / Cancel / Complete." |

## Notes (hand-written)
_No hand-written notes yet._
