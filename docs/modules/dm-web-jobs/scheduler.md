# dm-web-jobs / scheduler

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/webJobs/src/scheduler/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: schedulerMast
- Update tables: schedulerMast
- Delete cascade (in order): schedulerMast
- Duplicate checks on save:
  - `Select COUNT(name) AS cnt from schedulerMast where name=:name AND docId <> :docId`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Scheduler Name (Text); Product License To (Text); Database Look Up (Text); Job Type (Text); Template (Text); Active (Text); Site (Text); Task Scheduler (Text); Start Date (Text); Start Time (Text); Weekly Days (Text); Months (Text); Days / On (Text); Days (Text); Monthly Week (Text); Monthly Days (Text); Expire Time (Text); rule: Repeat Every (Day) is mandatory.; rule: Invalid Repeat (Day).; rule: Repeat Every (Week On) is mandatory.; rule: Invalid Repeat (Week).; rule: Expire Date  can't be less than Start Date.; rule: Expire Time can't be less than Start Time.
- Lookups/suggestions: getSuggestionName, getTxtHPLT, getTxtHJobType, getTxtHTemplate, getTxtHPLTSite, getTxtHActive, getTxtHMonthsName, getTxtHMonthsNumber, getTxtHMonthsWeekNumber, getTxtHMonthsWeekName
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `Scheduler`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Scheduler Name | DmSuggestBox | `txtSchedulerName` | `100, 7, true, "Name", DmDataType.STRING` |
| 2 | Product License To | DmTextHelp | `txtHPLT` | `DmDatabase_PrpAcs, 5, true` |
| 3 | DataBase Look Up | DmTextBox | `txtDBLookUp` | `` |
| 4 | Site | DmTextHelpMulti | `txtHSite` | `KeyField_PrpAcs, 10, true` |
| 5 | Job Type | DmTextHelp | `txtHJobType` | `KeyField_PrpAcs, 3, true, true` |
| 6 | Template | DmTextHelp | `txtHTemplate` | `KeyField_PrpAcs, 7, true` |
| 7 | Active | DmTextHelp | `txtHActive` | `KeyField_PrpAcs, 3, true, true` |
| 8 | Start Date/Time | DmTextBox | `txtStartDate` | `30, true` |
| 9 | - | DmTextBoxTime | `txtStartTime` | `true` |
| 10 | Repeat Every | DmTextBoxNumeric | `txtRepeatDay` | `0, false, 1, true` |
| 11 | Repeat Every | DmTextBoxNumeric | `txtRepeatWeek` | `0, false, 1, true` |
| 12 |  Week On | CheckBox | `cbSunday` | `"Sunday"` |
| 13 | - | CheckBox | `cbMonday` | `"Monday"` |
| 14 | - | CheckBox | `cbTuesday` | `"Tuesday"` |
| 15 | - | CheckBox | `cbWednesday` | `"Wednesday"` |
| 16 | - | CheckBox | `cbThursday` | `"Thursday"` |
| 17 | - | CheckBox | `cbFriday` | `"Friday"` |
| 18 | - | CheckBox | `cbSaturday` | `"Saturday"` |
| 19 | Months | DmTextHelpMulti | `txtHMonthsName` | `KeyField_PrpAcs, 13, true` |
| 20 | - | DmTextHelpMulti | `txtHMonthDate` | `KeyField_PrpAcs, 7, true` |
| 21 | - | DmTextHelpMulti | `txtHMonthsOnWeek` | `KeyField_PrpAcs, 8, true` |
| 22 | - | DmTextHelpMulti | `txtHMonthsOnDays` | `KeyField_PrpAcs, 8, true` |

Other widgets (not in tab list): `txtExpiryDate` (DmTextBox), `txtExpiryTime` (DmTextBoxTime), `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `txtHPLT`: P.L.T Name [Text, w0, name]
- `txtHTemplate`: Name [Text, w0, name]
- `txtHSite`: Site Name [Text, w1, Name]
- `txtHActive`: Name [Text, w0, name]
- `txtHJobType`: Type [Text, w0, name]
- `txtHMonthsName`: Months [Text, w1, Name]
- `txtHMonthDate`: Date [Text, w1, Name]
- `txtHMonthsOnWeek`: Week [Text, w1, Name]
- `txtHMonthsOnDays`: Days [Text, w1, Name]
- `dmFind`: Scheduler Name [Text, w1, schedulerName]; Product License To [Text, w250, pltname]; Job Type [Text, w55, jobType]; Task Scheduler [Text, w85, taskScheduler]; Start Date / Time [Text, w130, startDateTime]; Expire Date / Time [Text, w130, expireDateTime]; Active [Text, w40, active]; Status [Text, w50]

**Lookup / suggestion / find sources (server RPC called):**
- `txtSchedulerName` → `getSuggestionName`
- `txtHPLT` → `getTxtHPLT`
- `txtHJobType` → `getTxtHJobType`
- `txtHTemplate` → `getTxtHTemplate`
- `txtHActive` → `getTxtHActive`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtSchedulerName.getText())
- `PltCode` ← DmShared.xNull(txtHPLT.getTag())
- `DbLookUp` ← DmShared.xNull(txtDBLookUp.getText())
- `SiteCode` ← DmShared.xNull(txtHSite.getTag())
- `JobTypeCode` ← DmShared.xNull(txtHJobType.getTag())
- `TemplateCode` ← DmShared.xNull(txtHTemplate.getTag())
- `TemplateName` ← DmShared.xNull(txtHTemplate.getText())
- `IsActiveCode` ← DmClient.vNull(txtHActive.getTag(), "0")
- `StartDate` ← DmShared.dNull(txtStartDate.getText())
- `StartTime` ← DmShared.xNull((txtStartTime.getText()))
- `RecurrenceQty` ← DmClient.vNull(txtRepeatDay.getText(), "0")
- `ExpiryDate` ← DmShared.dNull(txtExpiryDate.getText())
- `ExpiryTime` ← DmShared.xNull(txtExpiryTime.getText())
- `RecurrenceQty` ← DmClient.vNull(txtRepeatWeek.getText(), "0")
- `ExpiryDate` ← DmShared.dNull(txtExpiryDate.getText())
- `ExpiryTime` ← DmShared.xNull(txtExpiryTime.getText())
- `ExpiryDate` ← DmShared.dNull(txtExpiryDate.getText())
- `ExpiryTime` ← DmShared.xNull(txtExpiryTime.getText())
- `MonthsCode` ← DmShared.xNull(txtHMonthsName.getTag())
- `DateCode` ← DmShared.xNull(txtHMonthDate.getTag())
- `WeekCode` ← DmShared.xNull(txtHMonthsOnWeek.getTag())
- `DaysCode` ← DmShared.xNull(txtHMonthsOnDays.getTag())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtSchedulerName.setText` ← DmShared.xNull(data.getSharedKeyFieldName())
- `txtHPLT.setTag` ← DmShared.xNull(data.getPltCode())
- `txtHPLT.setText` ← DmShared.xNull(data.getPltName())
- `txtHSite.setTag` ← DmShared.xNull(data.getSiteCode())
- `txtHSite.setText` ← DmShared.xNull(data.getSiteName())
- `txtDBLookUp.setText` ← DmShared.xNull(data.getDbLookUp())
- `txtHJobType.setTag` ← DmShared.xNull(data.getJobTypeCode())
- `txtHJobType.setText` ← DmShared.xNull(data.getJobTypeName())
- `txtHTemplate.setTag` ← DmShared.xNull(data.getTemplateCode())
- `txtHTemplate.setText` ← DmShared.xNull(data.getTemplateName())
- `txtHActive.setTag` ← DmClient.vNull(data.getIsActiveCode(), "0")
- `txtHActive.setText` ← DmShared.xNull(data.getIsActiveName())
- `txtStartDate.setText` ← DmShared.dNull(data.getStartDate())
- `txtStartTime.setText` ← DmShared.xNull(data.getStartTime())
- `txtExpiryDate.setText` ← DmShared.dNull(data.getExpiryDate())
- `txtExpiryTime.setText` ← DmShared.xNull(data.getExpiryTime())
- `txtRepeatDay.setText` ← DmShared.xNull(data.getRecurrenceQty())
- `txtRepeatWeek.setText` ← DmShared.xNull(data.getRecurrenceQty())
- `txtHMonthsName.setTag` ← DmShared.xNull(data.getMonthsCode())
- `txtHMonthDate.setTag` ← DmShared.xNull(data.getDateCode())
- `txtHMonthDate.setText` ← DmShared.xNull(data.getDateCode()).replace("-1", "Last")
- `txtHMonthsOnWeek.setTag` ← DmShared.xNull(data.getWeekCode())
- `txtHMonthsOnDays.setTag` ← DmShared.xNull(data.getDaysCode())
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtDBLookUp.setEnabled(false)`

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
txtDBLookUp.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
onAdd();
manageDisplay(true);
```

`topControl.addEditHandeller` → `editClicked()`
```java
onEdit();
manageDisplay(true);
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
Scheduler_Mdl mrTemp = new Scheduler_Mdl();
mrTemp = topControlSaveInitialize();
if (topControl.getMode().equalsIgnoreCase("Edit")) {
showModifyReason();
} else {
topControlSave(mrTemp);
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(Scheduler.this);
```

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
```

`modifyReason.addCallBack` → `onOk()`
```java
try {
reasonInfo_Mdl = modifyReason;
Scheduler_Mdl mrTemp = new Scheduler_Mdl();
if (topControl.getMode().equalsIgnoreCase("Browse") && DmShared.iNull(topControl.getDocId()).longValue() > 0) {
topControlDelete(topControl.getDocId());
} else {
mrTemp = topControlSaveInitialize();
topControlSave(mrTemp);
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), (DmTextBox) null).show();
...
```

`txtSchedulerName.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getSuggestionName(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Str
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtSchedulerName).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtSchedulerName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtSchedulerName).show();
```

`txtSchedulerName.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtSchedulerName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtSchedulerName).show();
```

`txtHPLT.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHPLT(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<DataBase_M
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHPLT).show();
public void onSuccess(DmResultPageing<List<DataBase_Mdl>> result) {
if (result.isStatus()) {
txtHPLT.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPLT).show();
```

`txtHPLT.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHPLT.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPLT).show();
```

`txtHPLT.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
txtDBLookUp.setText(DmShared.xNull(t1Row.getDatabasename()));
} else {
txtDBLookUp.setText("");
txtHTemplate.setTag("");
txtHTemplate.setText("");
```

`txtHSite.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHPLTSite(txtHPLT.getTag(), helper, blnToEndOfFile, new AsyncCallback<DmResultP
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

`txtHJobType.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHJobType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFie
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHJobType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHJobType.setData(result.getData(), result.getPaging());
txtHTemplate.setEnabled(true);
txtHTemplate.setTag("");
txtHTemplate.setText("");
} else {
...
```

`txtHJobType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHJobType.setData(result.getData(), result.getPaging());
txtHTemplate.setEnabled(true);
txtHTemplate.setTag("");
txtHTemplate.setText("");
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHJobType).show();
```

`txtHTemplate.addTextHelpCallBack` → `populateData()`
```java
if (DmShared.xNull(txtDBLookUp.getText()).equalsIgnoreCase("") || DmShared.xNull(txtHJobType.getText()).equalsIgnoreCase
} else {
SrvAsync.invokeRPC(blnCallBackWait).getTxtHTemplate(txtDBLookUp.getText(), txtHJobType.getTag(), helper, blnToEndOfFile,
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHTemplate).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHTemplate.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTemplate).show();
...
```

`txtHTemplate.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHTemplate.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTemplate).show();
```

`txtHMonthsName.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHMonthsName(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Key
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHMonthsName).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHMonthsName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHMonthsName).show();
```

`txtHMonthsName.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHMonthsName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHMonthsName).show();
```

`txtHMonthDate.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHMonthsNumber(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<K
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHMonthDate).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHMonthDate.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHMonthDate).show();
```

`txtHMonthDate.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHMonthDate.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHMonthDate).show();
```

`txtHMonthsOnWeek.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHMonthsWeekNumber(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<Li
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHMonthsOnWeek).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHMonthsOnWeek.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHMonthsOnWeek).show();
```

`txtHMonthsOnWeek.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHMonthsOnWeek.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHMonthsOnWeek).show();
```

`txtHMonthsOnDays.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHMonthsWeekName(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHMonthsOnDays).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHMonthsOnDays.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHMonthsOnDays).show();
```

`txtHMonthsOnDays.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHMonthsOnDays.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHMonthsOnDays).show();
```

**Top control options:** setRefreshRequired(false), setDisplay(true)

_Print permission "P" is removed for this screen (replace "P" with "*")._

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."

## Files
| file | lines |
|---|---|
| client/Scheduler.java | 1182 |
| client/Scheduler_Dsg.java | 743 |
| server/Scheduler_SrvImpl.java | 962 |
| shared/DataBase_Mdl.java | 24 |
| shared/Find_Mdl.java | 76 |
| shared/Scheduler_Mdl.java | 276 |
| shared/Scheduler_Srv.java | 44 |
| shared/Scheduler_SrvAsync.java | 44 |
| shared/Scheduler_Vld.java | 67 |

## UI labels / columns (client)
`Scheduler Name`, `Product License To`, `DataBase Look Up`, `Site`, `Job Type`, `Template`, `Active`, `Start Date/Time`, `Expire Date/Time`, `Repeat Every`, `Day`, ` Week On`, `Months`

## Models (shared DTOs)
### Scheduler_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | pltCode |
| String | pltName |
| String | dbLookUp |
| String | siteName |
| String | siteCode |
| String | jobTypeCode |
| String | jobTypeName |
| String | templateCode |
| String | templateName |
| String | isActiveCode |
| String | isActiveName |
| String | startDate |
| String | startTime |
| String | expiryDate |
| String | expiryTime |
| String | rbTaskScheduler |
| String | recurrenceQty |
| String | recurrenceUnit |
| String | monthsName |
| String | monthsCode |
| String | rbDayOrMonth |
| String | dateCode |
| String | dateName |
| String | daysCode |
| String | daysName |
| String | weekCode |
| String | weekName |
| String | cbDays |
| ModifyReason_Mdl | reasonInfo_Mdl |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | pltName |
| String | jobType |
| String | taskScheduler |
| String | active |
| String | startDateTime |
| String | expireDateTime |
| String | isAlive |

### DataBase_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | databasename |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getSuggestionName | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getMoveRec | String strKey | DmResult<Scheduler_Mdl> |
| saveRecord | Scheduler_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl reasonInfo_Mdl | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getTxtHPLT | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<DataBase_Mdl>> |
| getTxtHTemplate | String databBaseName, String schedulerType, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHActive | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHJobType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHMonthsName | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHMonthsNumber | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHMonthsWeekNumber | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHMonthsWeekName | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPLTSite | String tag, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |

## Validator `Scheduler_Vld`
```java
public class Scheduler_Vld {
    public static void chkSave(Scheduler_Mdl mrTemp) {
        DmShared.isValidText(DmShared.xNull(mrTemp.getSharedKeyFieldName()), "Scheduler Name", 100);
        DmShared.isValidText(DmShared.xNull(mrTemp.getPltCode()), "Product License To", 150);
        DmShared.isValidText(DmShared.xNull(mrTemp.getDbLookUp()), "Database Look Up", 150);
        DmShared.isValidText(DmShared.xNull(mrTemp.getJobTypeCode()), "Job Type", 200);
        DmShared.isValidText(DmShared.xNull(mrTemp.getTemplateCode()), "Template", 250);
        DmShared.isValidText(DmShared.xNull(mrTemp.getIsActiveCode()), "Active", 350);
        DmShared.isValidText(DmShared.xNull(mrTemp.getSiteCode()), "Site", 415);
        DmShared.isValidText(DmShared.xNull(mrTemp.getRbTaskScheduler()), "Task Scheduler", 400);
        DmShared.isValidText(DmShared.xNull(mrTemp.getStartDate()), "Start Date", 450);
        DmShared.isValidText(DmShared.xNull(mrTemp.getStartTime()), "Start Time", 500);
        if (DmShared.xNull(mrTemp.getRbTaskScheduler()).equalsIgnoreCase("2")) {
            if (DmShared.iNull(mrTemp.getRecurrenceQty()).intValue() == 0) {
                throw new DmException(550, "Repeat Every (Day) is mandatory.");
            }
            if (!((DmShared.iNull(mrTemp.getRecurrenceQty()).intValue() > 0) && (DmShared.iNull(mrTemp.getRecurrenceQty()).intValue() < 8))) {
                throw new DmException(550, "Invalid Repeat (Day).");
            }
        }
        if (DmShared.xNull(mrTemp.getRbTaskScheduler()).equalsIgnoreCase("3")) {
            if (DmShared.iNull(mrTemp.getRecurrenceQty()).intValue() == 0) {
                throw new DmException(600, "Repeat Every (Week On) is mandatory.");
            }
            if (!((DmShared.iNull(mrTemp.getRecurrenceQty()).intValue() > 0) && (DmShared.iNull(mrTemp.getRecurrenceQty()).intValue() < 6))) {
                throw new DmException(600, "Invalid Repeat (Week).");
            }
            DmShared.isValidText(DmShared.xNull(mrTemp.getCbDays()), "Weekly Days", 650);
        }
        if (DmShared.xNull(mrTemp.getRbTaskScheduler()).equalsIgnoreCase("4")) {
            DmShared.isValidText(DmShared.xNull(mrTemp.getMonthsCode()), "Months", 700);
            DmShared.isValidText(DmShared.xNull(mrTemp.getRbDayOrMonth()), "Days / On", 750);
            if (DmShared.xNull(mrTemp.getRbDayOrMonth()).equalsIgnoreCase("d")) {
                DmShared.isValidText(DmShared.xNull(mrTemp.getDateCode()), "Days", 800);
            } else if (DmShared.xNull(mrTemp.getRbDayOrMonth()).equalsIgnoreCase("m")) {
                DmShared.isValidText(DmShared.xNull(mrTemp.getWeekCode()), "Monthly Week", 850);
                DmShared.isValidText(DmShared.xNull(mrTemp.getDaysCode()), "Monthly Days", 900);
            }
        }
        if (!(DmShared.xNull(mrTemp.getExpiryDate()).equalsIgnoreCase(""))) {
            if (DmShared.convertDate(DmClient.returnDate(DmShared.xNull(mrTemp.getExpiryDate()))).getTime() < DmShared.convertDate(DmClient.returnDate(DmShared.xNull(mrTemp.getStartDate()))).getTime()) {
                throw new DmException(1000, "Expire Date  can't be less than Start Date.");
            }
        }
        if (!(DmShared.xNull(mrTemp.getExpiryDate()).equalsIgnoreCase(""))) {
            if (DmShared.convertDate(DmClient.returnDate(DmShared.xNull(mrTemp.getExpiryDate()))).getTime() == DmShared.convertDate(DmClient.returnDate(DmShared.xNull(mrTemp.getStartDate()))).getTime()) {
                if (Double.compare(Double.parseDouble(mrTemp.getExpiryTime()), Double.parseDouble(mrTemp.getStartTime())) <= 0) {
                    throw new DmException(1100, "Expire Time can't be less than Start Time.");
                }
            }
        }
        if (!(DmShared.xNull(mrTemp.getExpiryDate()).equalsIgnoreCase(""))) {
            DmShared.isValidText(DmShared.xNull(mrTemp.getExpiryTime()), "Expire Time", 1100);
        }
    }
}
```

## Server `Scheduler_SrvImpl.java` (962 lines)
Helpers used: `DmServer.addScheduler`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getClientIpAddress`, `DmServer.getConnection`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnUserName`, `DmServer.getWebServiceSchedulars`, `DmServer.setSsnOldData`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `select docId from schedulerMast order by name`

### `getSuggestionName`
`public DmResultPageing<List<String>> getSuggestionName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select name from schedulerMast`

### `getMoveRec`
`public DmResult<Scheduler_Mdl> getMoveRec(String strKey)`

- SQL 1: `select sm.docId, sm.name as schedulerName, sm.PLTCode, plt.comp_Name as pltName, com.centralData_Path AS dbLookUp, sm.selSiteCodes as siteCode, sm.startDateTime, sm.expDateTime, sm.jobType, sm.WSTCode as templateCode, sm.isActive, sm.preparedBy, sm.u_Name, sm.schType as taskScheduler, sm.recurQuantity, sm.recurUnit, sm.selDays as wmDays,sm.preparedDt,sm.preparedByIP, sm.dayOrMonth, sm.selMonths as months, sm.selDates as dates, sm.selWeeks as weeks, sm.responsibleUser from schedulerMast as sm left join productLicensedTo as plt on sm.PLTCode = plt.pltCode left join company AS com ON plt.latestCompCode = com.comp_Code WHERE sm.docid = :docid`
- SQL 2: `SELECT name FROM siteMast WHERE code IN ( {…} )`
- SQL 3: `SELECT wtmp.code, wtmp.name FROM wJTemplateMast AS wtmp WHERE wtmp.code = :templateCode`
- Params: `:docId` ← `DmShared.iNull(strKey).toString()`; `:templateCode` ← `DmShared.xNull(mrTemp.getTemplateCode())`
- Other calls: `DmWebJobTypes.getViaCode`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl reasonInfo_Mdl)`

- SQL 1: `Delete From schedulerMast Where docId= :docId`
- Params: `:docId` ← `DmShared.iNull(strKey).toString()`
- Other calls: `ModifyReason_Server.saveNew`

### `saveRecord`
`public DmResult<String> saveRecord(Scheduler_Mdl mrTemp)`

- SQL 1: `Select COUNT(name) AS cnt from schedulerMast where name=:name AND docId <> :docId`
- SQL 2: `INSERT INTO schedulerMast (docId, PLTCode, name, WSTCode, startDateTime, expDateTime, isActive, jobType, selSiteCodes, recurQuantity, recurUnit, schType, selDays, dayOrMonth, selMonths, selDates, selWeeks, preparedBy, preparedByIP, prepareddt, responsibleUser) VALUES (:docId, :PLTCode, :name, :WSTCode, :startDateTime, :expDateTime, :isActive, :jobType, :selSiteCodes, :recurQuantity, :recurUnit, :schType, :selDays, :dayOrMonth, :selMonths, :selDates, :selWeeks, :preparedBy, :preparedByIP, :u_EntDate, :responsibleUser)`
- SQL 3: `Update schedulerMast set PLTCode = :PLTCode , name = :name , WSTCode = :WSTCode , startDateTime = :startDateTime, expDateTime = :expDateTime, isActive = :isActive , jobType = :jobType , selSiteCodes = :selSiteCodes , recurQuantity = :recurQuantity , recurUnit = :recurUnit , schType = :schType , selDays = :selDays , dayOrMonth = :dayOrMonth , selMonths = :selMonths , selDates = :selDates , selWeeks = :selWeeks , responsibleUser = :responsibleUser , u_EntDt = :u_EntDate, u_Name = :u_Name where docid = :docId`
- Params: `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:PLTCode` ← `DmShared.foriegnKey(mrTemp.getPltCode())`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:WSTCode` ← `DmShared.foriegnKey(mrTemp.getTemplateCode())`; `:startDateTime` ← `DmShared.xNull(mrTemp.getStartDate()) + " " + DmShared.xNull(mrTemp.getStartTime()).replac`; `:expDateTime` ← `null`; `:expDateTime` ← `DmShared.xNull(mrTemp.getExpiryDate()) + " " + DmShared.xNull(mrTemp.getExpiryTime()).repl`; `:isActive` ← `DmServer.vNull(mrTemp.getIsActiveCode(), "0")`; `:jobType` ← `DmServer.vNull(mrTemp.getJobTypeCode(), "0")`; `:selSiteCodes` ← `DmShared.xNull("-1")`; `:selSiteCodes` ← `DmShared.xNull(mrTemp.getSiteCode()).replaceAll(",", "\\|")`; `:recurQuantity` ← `DmServer.vNull(mrTemp.getRecurrenceQty(), "0")`; `:recurUnit` ← `DmServer.vNull(mrTemp.getRecurrenceUnit(), "0")`; `:schType` ← `DmShared.xNull(mrTemp.getRbTaskScheduler())`; `:selDays` ← `DmShared.xNull(mrTemp.getCbDays())`; `:selDays` ← `DmShared.xNull(mrTemp.getDaysCode()).replaceAll(",", "\\|")`; `:selDays` ← `""`; `:dayOrMonth` ← `DmShared.xNull(mrTemp.getRbDayOrMonth())`; `:selMonths` ← `DmShared.xNull(mrTemp.getMonthsCode()).replaceAll(",", "\\|")`; `:selDates` ← `DmShared.xNull(mrTemp.getDateCode()).replaceAll(",", "\\|")`; `:selWeeks` ← `DmShared.xNull(mrTemp.getWeekCode()).replaceAll(",", "\\|")`; `:responsibleUser` ← `DmServer.getSsnUserName(this)`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:u_EntDate` ← `DmServer.getServerTimestamp()`; `:preparedByIP` ← `DmServer.getClientIpAddress(this)`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:PLTCode` ← `DmShared.foriegnKey(mrTemp.getPltCode())`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:WSTCode` ← `DmShared.foriegnKey(mrTemp.getTemplateCode())`; `:startDateTime` ← `new Timestamp(calendar.getTimeInMillis())`; `:u_EntDate` ← `DmServer.getServerTimestamp()`; `:responsibleUser` ← `DmServer.getSsnUserName(this)`; `:expDateTime` ← `null`; `:expDateTime` ← `new Timestamp(calendar.getTimeInMillis())`; `:isActive` ← `DmShared.iNull(mrTemp.getIsActiveCode()).intValue()`; `:jobType` ← `DmShared.iNull(mrTemp.getJobTypeCode()).intValue()`; `:selSiteCodes` ← `DmShared.xNull("-1")`; `:selSiteCodes` ← `DmShared.xNull(mrTemp.getSiteCode()).replaceAll(",", "\\|")`; `:recurQuantity` ← `DmShared.iNull(mrTemp.getRecurrenceQty()).intValue()`; `:recurUnit` ← `DmShared.iNull(mrTemp.getRecurrenceUnit()).intValue()`; `:schType` ← `DmShared.iNull(mrTemp.getRbTaskScheduler()).intValue()`; `:selDays` ← `DmShared.xNull(mrTemp.getCbDays())`; `:selDays` ← `DmShared.xNull(mrTemp.getDaysCode()).replaceAll(",", "\\|")`; `:selDays` ← `DmShared.xNull("")`; `:dayOrMonth` ← `DmShared.xNull(mrTemp.getRbDayOrMonth())`; `:selMonths` ← `DmShared.xNull(mrTemp.getMonthsCode()).replaceAll(",", "\\|")`; `:selDates` ← `DmShared.xNull(mrTemp.getDateCode()).replaceAll(",", "\\|")`; `:selWeeks` ← `DmShared.xNull(mrTemp.getWeekCode()).replaceAll(",", "\\|")`; `:u_Name` ← `DmShared.xNull(DmServer.getSsnUserName(this))`
- Errors: "Invalid key." | "Scheduler Name already exists."
- Other calls: `ModifyReason_Server.saveNew`, `Scheduler_Vld.chkSave`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.docId, sm.Name AS schedulerName, plt.comp_Name AS pltname, sm.responsibleUser, CASE WHEN sm.jobType = 0 THEN 'SMS' WHEN sm.jobType = 1 THEN 'E-Mail' when sm.jobType=2 then 'Event' END AS jobType , CASE WHEN sm.schType = 1 THEN 'Ones' WHEN sm.schType = 2 THEN 'Daily' WHEN sm.schType = 3 THEN 'Weekly' WHEN sm.schType = 4 THEN 'Monthly' END AS taskScheduler, CASE WHEN sm.isActive = 1 THEN 'Yes' WHEN sm.isActive = 0 THEN 'No' END AS active, FORMAT(startdatetime,'dd-MMM-yyyy / HH:mm') AS startDateTime, FORMAT(expDateTime,'dd-MMM-yyyy / HH:mm') AS expireDateTime FROM schedulerMast AS sm LEFT JOIN productlicensedto AS plt ON sm.pltCode = plt.pltCode`

### `getTxtHPLT`
`public DmResultPageing<List<DataBase_Mdl>> getTxtHPLT(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT plt.pltCode as code ,plt.comp_Name as name , com.centralData_Path as databaseName FROM productlicensedto AS plt left join company AS com ON plt.latestCompCode = com.comp_Code where com.comp_Code is not null`

### `getTxtHJobType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHJobType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `DmWebJobTypes.values`

### `getTxtHTemplate`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTemplate(String databBaseName, String schedulerType, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT wtmp.code, wtmp.name FROM wJTemplateMast AS wtmp WHERE wtmp.templateType=:schedulerType`
- Params: `:schedulerType` ← `schedulerType`

### `getTxtHPLTSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHPLTSite(String tag, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT SM.Code,SM.Name FROM SiteMast SM`

### `getTxtHActive`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHActive(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHMonthsName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHMonthsName(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `DmMonths.values`

### `getTxtHMonthsNumber`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHMonthsNumber(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `DmDates.values`

### `getTxtHMonthsWeekNumber`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHMonthsWeekNumber(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `DmWeeks.values`

### `getTxtHMonthsWeekName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHMonthsWeekName(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `DmWeekdays.values`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`


### `getSchedulerStatus`
`private String getSchedulerStatus(String docId)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.server`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmScheduler.shared`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextBoxTime.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.server`, `dataman.dmToolkit.modifyReason.shared`, `dataman.libraryMenu.self.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Scheduler Name already exists." |

## Notes (hand-written)
_No hand-written notes yet._
