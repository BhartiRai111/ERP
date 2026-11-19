# dm-web-jobs / smsManager

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/webJobs/src/smsManager/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHTempName
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `DraftPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Template Name | DmTextHelp | `txtHTemplateName` | `TxtHTemp_PrpAcs, 7, true` |
| 2 | Message | DmTextArea | `txtMsg` | `500, true` |

Other widgets (not in tab list): `grdFieldDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHTemplateName`: Template Name [Text, w200, name]; Under Temp [Text, w100, groupName]; Function / File Name [Text, w200, functionName]; Message Template [Text, w100, message]
- `grdFieldDetail`: Field Name [Text, w100]; Format [Text, w200]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHTemplateName` → `getTxtHTempName`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHTemplateName.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHTempName(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Templ
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHTemplateName).show();
public void onSuccess(DmResultPageing<List<Template_Mdl>> result) {
if (result.isStatus()) {
txtHTemplateName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTemplateName).show();
```

`txtHTemplateName.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHTemplateName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTemplateName).show();
```

`txtHTemplateName.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
template_Mdl = t1Row;
txtMsg.setText(template_Mdl.getMessageTemplate());
setMetaData();
```
- Alert: "Template name is mandatory."
- Alert: "Message is mandatory."

### Screen `SmsManagerGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmTextArea | `txtMsgArea` | `` |
- Alert: "Create message."

### Screen `SmsManager`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Template Name | DmTextBox | `txtTemplateName` | `50, true` |
| 2 | Data Source / Function / File Name | DmTextBox | `txtDataSourceName` | `30, true` |
| 3 | - | DmTextBox | `txtFunFile` | `30, true` |
| 4 | Send Records :  | DmTextBox | `txtSearchValue` | `50, false` |

Other widgets (not in tab list): `chkRecordsFilter` (DmCheckBox), `dmSmsGrd` (DmGridPaging)

**Grid / popup columns** (header, width, db field):
- `dmSmsGrd`: isChecked [Text, w25]; send_To_Name [Text, w150, Send_To_Name]; send_To_Address [Text, w150, Send_To_Address]; send_To_Mobile [Text, w100, Send_To_Mobile]; message [Text, w250, Message]; size [Text, w50, Size]; status [Text, w80, Status]

**Lookup / suggestion / find sources (server RPC called):**
- `chkRecordsFilter` → `getMetaData`
- `dmSmsGrd` → `getData`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtDataSourceName.setEnabled(false)`
- `txtFunFile.setEnabled(false)`
- `txtTemplateName.setEnabled(false)`
- `txtSearchValue.setEnabled(flag)`

**`manageDisplay()` logic (condensed code):**
```java
txtDataSourceName.setEnabled(false);
txtFunFile.setEnabled(false);
txtTemplateName.setEnabled(false);
txtSearchValue.setEnabled(flag);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`filterPopupData.addCallBack` → `onOk()`
```java
filterDataList = listFilterData;
setHeaderValue(1);
checkSearchValue();
dmSmsGrd.reQuery();
setCheckUncheck(false);
```

`prepareDataPopup.addCallBack` → `onOk()`
```java
clear();
txtTemplateName.setTag(template_Mdl.getSharedKeyFieldCode());
txtTemplateName.setText(template_Mdl.getSharedKeyFieldName());
txtDataSourceName.setText(DmShared.xNull(template_Mdl.getWebJobDataSource().getSourceName()));
txtFunFile.setText(template_Mdl.getFuncOrFileName());
SmsManager.this.templateMdl = template_Mdl;
manageDisplay(true);
getMetadata(template_Mdl);
```

`chkRecordsFilter.addCallBack` → `valueChanged()`
```java
dmSmsGrd.reQuery();
```

`managerGridEdit.addCallBack` → `onOk()`
```java
dmSmsGrd.getDmGrid().getGrid().getStore().get(rowIndex).getCells().put("message", ddgr.getCells().get("message").toStrin
dmSmsGrd.getDmGrid().commitChanges();
setCheckVal(false, false, ddgr);
```

`dmSmsGrd.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC().getData(chkRecordsFilter.getValue(), param, helper, blnToEndOfFile, new AsyncCallback<DmResultPagei
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtTemplateName).show();
public void onSuccess(DmResultPageing<List<DmDynamicGridRow>> result) {
if (result.isStatus()) {
dmSmsGrd.getDmGrid().getGrid().getStore().clear();
dmSmsGrd.setData(result.getData(), result.getPaging());
getCountRecords();
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtTemplateName).show();
...
```

`dmSmsGrd.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
dmSmsGrd.getDmGrid().getGrid().getStore().clear();
dmSmsGrd.setData(result.getData(), result.getPaging());
getCountRecords();
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtTemplateName).show();
```

`dmSmsGrd.addCallBack` → `getParam()`
```java
return valueIntialize();
```
- Confirm: "Sms has already been send for this record. Are you sure? You want to resend."
- Confirm: "Are you sure? You want to send  SMS to selected records."
- Confirm: "Are you sure? You want to close."
- Alert: "Please select template name to filter data."

## Files
| file | lines |
|---|---|
| client/DraftPopup.java | 186 |
| client/DraftPopup_Dsg.java | 242 |
| client/SmsManager.java | 693 |
| client/SmsManagerGridEdit.java | 77 |
| client/SmsManagerGridEdit_Dsg.java | 36 |
| client/SmsManager_Dsg.java | 330 |
| server/SmsManager_SrvImpl.java | 1036 |
| shared/DynamicGrid_Mdl.java | 31 |
| shared/SearchValue_Mdl.java | 56 |
| shared/SmsManager_Srv.java | 29 |
| shared/SmsManager_SrvAsync.java | 29 |

## UI labels / columns (client)
`Template Name`, `Message`, `Drafted Records : `, `Filtered Records : `, `Data Source / Function / File Name`, `Selected Records : `, `Send Records : `

## Models (shared DTOs)
### DynamicGrid_Mdl
| type | field |
|---|---|
| List<DmDynamicGridRow> | dynamicGridRowList |
| List<String> | columnNameList |
| List<FilterDataPopup_Mdl> | filterData_Mdls |

### SearchValue_Mdl
| type | field |
|---|---|
| String | funFile |
| String | tempID |
| String | message |
| String | tableName |
| List<FilterDataPopup_Mdl> | filterDataList |

## RPC interface
| method | params | returns |
|---|---|---|
| sendSms | Template_Mdl templateMdl | DmResult<String> |
| getTxtHTempName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<Template_Mdl>> |
| getMetaData | Template_Mdl templateMdl | DmResult<DynamicGrid_Mdl> |
| getMetaDataOnly | Template_Mdl templateMdl | DmResult<List<GrdFieldName_Mdl>> |
| getData | boolean isCheckedRecord, SearchValue_Mdl mdl, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<DmDynamicGridRow>> |
| updateRecord | Template_Mdl templateMdl, DmDynamicGridRow ddgr, boolean isCheckAll, boolean isCheckChange | DmResult<String> |
| getCountRecords | Template_Mdl template_Mdl | DmResult<KeyFieldExtended> |

## Server `SmsManager_SrvImpl.java` (1036 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getMdbCollection`, `DmServer.getMdbTmpCollectionName`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnVoucherPrefix`, `DmServer.vNull`, `DmShared.iNull`, `DmShared.xNull`

### `getSendSMS`
`private int getSendSMS(DBCollection collection)`


### `sendSms`
`public DmResult<String> sendSms(Template_Mdl templateMdl)`

- Errors: "No record selected to send SMS."

### `getTxtHTempName`
`public DmResultPageing<List<Template_Mdl>> getTxtHTempName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT wtm.code, wtm.name, wtm.message, wtm.templateGroupCode, wtg.name AS groupName, wtm.dataSource, wtm.functionName FROM wjTemplateMast AS wtm LEFT JOIN wjTemplateGroupMast AS wtg ON wtg.code = wtm.templateGroupCode WHERE (wtm.templateType = :templateType)`
- Other calls: `DmWebJobDataSources.getValueFrom`, `SMS.getCode`

### `getMetaData`
`public DmResult<DynamicGrid_Mdl> getMetaData(Template_Mdl templateMdl)`

- Other calls: `CSV.getSourceType`

### `getColumnDataType`
`private DynamicGrid_Mdl getColumnDataType(DynamicGrid_Mdl mrTemp, Template_Mdl templateMdl, Connection cnTemp)`


### `getCsv`
`private void getCsv(DynamicGrid_Mdl mrTemp, Template_Mdl templateMdl, Connection cnTemp)`

- Errors: "File not found."
- Other calls: `DmMapFormat.format`

### `getSql`
`private void getSql(DynamicGrid_Mdl mrTemp, Template_Mdl templateMdl, String siteCode, Connection cnTemp) throws Exception`

- SQL 1: `SELECT * FROM {…} (:templateId, :v_Prefix, :siteCode)`
- Params: `:templateId` ← `DmShared.iNull(templateMdl.getSharedKeyFieldCode()).toString()`; `:v_Prefix` ← `DmShared.iNull(DmServer.getSsnVoucherPrefix(this)).intValue()`; `:siteCode` ← `DmShared.iNull(siteCode).intValue()`
- Other calls: `DmMapFormat.format`

### `getDefaultColumns`
`private List<String> getDefaultColumns()`


### `getData`
`public DmResultPageing<List<DmDynamicGridRow>> getData(boolean isCheckedRecord, SearchValue_Mdl mdl, DmHelper helper, boolean blnToEndOfFile)`


### `updateRecord`
`public DmResult<String> updateRecord(Template_Mdl templateMdl, DmDynamicGridRow ddgr, boolean isCheckAll, boolean isCheckChange)`


### `getCountRecords`
`public DmResult<KeyFieldExtended> getCountRecords(Template_Mdl template_Mdl)`


### `getMetaDataOnly`
`public DmResult<List<GrdFieldName_Mdl>> getMetaDataOnly(Template_Mdl templateMdl)`

- SQL 1: `Select * from {…} (cast(' {…} ' as varchar), {…} )`
- Errors: "Select function name."

### `mandatoryField`
`private void mandatoryField(List<GrdFieldName_Mdl> lstTemp) throws Exception`

- Errors: "The Field "

### `getPrepareRecords`
`private int getPrepareRecords(DBCollection collection)`


### `getSendRecords`
`private int getSendRecords(DBCollection collection)`


### `getselectedRecords`
`private int getselectedRecords(DBCollection collection)`


### `getfilteredRecords`
`private int getfilteredRecords(DBCollection collection)`


### `getCaseWhenIsChecked`
`private int getCaseWhenIsChecked(DBCollection collection, String uniqueId)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmScheduler.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmCheckBox.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextArea.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.self.client`, `dataman.libraryMenu.self.shared`, `dataman.webJobs.emailManager.client`, `dataman.webJobs.emailManager.shared`, `dataman.webJobs.smsManager.shared`, `dataman.webJobs.smsTemplate.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| sendSms | `if (count <= 0)` | "No record selected to send SMS." |
| getCsv | `if (!uploadedFile.exists())` | "File not found." |
| getMetaDataOnly | `if (templateMdl == null \|\| DmShared.xNull(templateMdl.getFuncOrFileName()).trim().equalsIgnoreCase(""))` | "Select function name." |
| mandatoryField | `if (lstTemp.stream().filter(m -> m.getFieldName().equalsIgnoreCase(lstField)).count() == 0)` | "The Field " |

## Notes (hand-written)
_No hand-written notes yet._
