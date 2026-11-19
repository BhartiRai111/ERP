# dm-web-jobs / smsTemplate

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/webJobs/src/smsTemplate/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: wjTemplateMast
- Update tables: wjTemplateMast
- Delete cascade (in order): wjTemplateSendTo, wjTemplateMast
- Duplicate checks on save:
  - `SELECT COUNT(name) AS cnt FROM wjTemplateMast WHERE (name = :name) AND (code <> :code) and templateType = {…}`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Template Name (Text); Template Group Name (Text); Data Source Name (Text); File Name (Text); Function Name (Text); rule: Staff Info is mandatory.
- Lookups/suggestions: getTxtHUndTempName, getTxtHFunctionName, getTxtHDataSource
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `ImportPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `fileRuleType` (DmFileRuleType), `btnUpload` (DmFileUploadButton)

### Screen `SendGridEditGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Name | DmTextBox | `txtName` | `50, true` |
| 2 | Mobile | DmTextBox | `txtValue` | `10, true` |
| 3 | Description | DmRemarkTextBox | `txtRemark` | `` |

### Screen `SmsTemplate`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Message | DmTextArea | `txtMsg` | `500, false` |
| 2 | Template Name | DmSuggestBox | `txtTempName` | `100, 7, true, "name", DmDataType.STRING` |
| 3 | Group Name | DmTextHelp | `txtHTemplateGroupName` | `KeyField_PrpAcs, 7, true` |
| 4 | Data Source | DmTextHelp | `txtHDataSource` | `KeyField_PrpAcs, 3, true` |
| 5 | - | DmTextHelp | `txtHFunctionName` | `KeyField_PrpAcs, 7, true` |

Other widgets (not in tab list): `txtHelp` (DmTextArea), `txtRefV_Type` (DmTextBox), `txtCSVFunction` (DmTextBox), `dmFind` (DmFind), `grdFieldDetail` (DmGrid), `grdSendDetail` (DmGrid), `fileRuleType` (DmFileRuleType), `btnUpload` (DmFileUploadButton)

**Grid / popup columns** (header, width, db field):
- `txtHTemplateGroupName`: Template Group Name [Text, w0, name]
- `txtHFunctionName`: Function Name [Text, w0, name]
- `txtHDataSource`: Type [Text, w0, Type]
- `dmFind`: Template Name [Text, w250, name]; Template Group Name [Text, w150, templateGroupName]; Data Source [Text, w150, dataSource]; Function Name [Text, w150, functionName]; Message [Text, w200, message]
- `grdFieldDetail`: Field Name [Text, w200]; Format [Text, w200]
- `grdSendDetail`: Name [Text, w0]

**Lookup / suggestion / find sources (server RPC called):**
- `txtTempName` → `getSuggetionTemplate`
- `txtHTemplateGroupName` → `getTxtHUndTempName`
- `txtHFunctionName` → `getTxtHFunctionName`
- `txtHDataSource` → `getTxtHDataSource`
- `modifyReason` → `getSqlDataSource`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `TempName` ← DmShared.xNull(txtTempName.getText())
- `TemplateGroupCode` ← DmShared.xNull(txtHTemplateGroupName.getTag())
- `TemplateGroupName` ← DmShared.xNull(txtHTemplateGroupName.getText())
- `DataSource` ← DmWebJobDataSources.getValueFrom(DmShared.iNull(txtHDataSource.getTag()).intValu
- `FunctionTag` ← DmShared.xNull(txtCSVFunction.getTag())
- `FunctionText` ← DmShared.xNull(txtCSVFunction.getText())
- `FunctionTag` ← DmShared.xNull(txtHFunctionName.getTag())
- `FunctionText` ← DmShared.xNull(txtHFunctionName.getText())
- `Msg` ← DmShared.xNull(txtMsg.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtTempName.setText` ← data.getTempName()
- `txtHTemplateGroupName.setTag` ← data.getTemplateGroupCode()
- `txtHTemplateGroupName.setText` ← data.getTemplateGroupName()
- `txtHDataSource.setTag` ← DmShared.iNull(data.getDataSource().getSourceType()).toString()
- `txtHDataSource.setText` ← data.getDataSource().getSourceName()
- `txtCSVFunction.setTag` ← data.getFunctionTag()
- `txtCSVFunction.setText` ← data.getFunctionText()
- `txtHFunctionName.setTag` ← data.getFunctionTag()
- `txtHFunctionName.setText` ← data.getFunctionText()
- `txtMsg.setText` ← data.getMsg()
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**On New click:**
- `onAdd()`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
onAdd();
```

`topControl.addEditHandeller` → `editClicked()`
```java
txtTempName.setFocus(true);
setFunctionDiv();
grdSendDetail.getGrid().getStore().add(new GrdSendDetail_Mdl());
```

`topControl.addDeleteHandeller` → `deleteClicked()`
```java
new DmConfirmMessageBox("Are you sure? You want to delete this record.", new DialogHideEvent.DialogHideHandler() {
public void onDialogHide(DialogHideEvent event) {
if (event.getHideButton().equals(Dialog.PredefinedButton.YES)) {
if (event.getHideButton().equals(Dialog.PredefinedButton.YES)) {
showModifyReason();
}).show();
```

`topControl.addDeleteHandeller` → `onDialogHide()`
```java
if (event.getHideButton().equals(Dialog.PredefinedButton.YES)) {
if (event.getHideButton().equals(Dialog.PredefinedButton.YES)) {
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
SmsTemplate_Mdl mrTemp = new SmsTemplate_Mdl();
mrTemp = topControlSaveInitialize();
try {
SmsTemplate_Vld.chkSave(mrTemp);
if (topControl.getMode().equalsIgnoreCase("Edit")) {
showModifyReason();
} else {
topControlSave(mrTemp);
} catch (DmException e) {
topControlSaveError(e.getErrorNo(), e.getMessage());
...
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(SmsTemplate.this);
```

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
```

`staffInfo.addCallBack` → `onOk()`
```java
lstStaff.clear();
lstStaff.addAll(lstQueue);
```

`staffInfo.addCallBack` → `onClose()`
```java
btnStaffInfo.setFocus(true);
```

`txtTempName.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getSuggetionTemplate(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtTempName).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtTempName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtTempName).show();
```

`txtTempName.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtTempName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtTempName).show();
```

`importPopup.addCallBack` → `onYes()`
```java
topControlMoveRecSuccess(mdl, true);
```

`txtHTemplateGroupName.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHUndTempName(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHTemplateGroupName).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHTemplateGroupName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTemplateGroupName).show();
```

`txtHTemplateGroupName.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHTemplateGroupName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTemplateGroupName).show();
```

`txtHFunctionName.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHFunctionName(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<K
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHFunctionName).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHFunctionName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHFunctionName).show();
```

`txtHFunctionName.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHFunctionName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHFunctionName).show();
```

`txtHFunctionName.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && tRow != null) {
showDataSource();
```

`txtHDataSource.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHDataSource(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Key
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDataSource).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHDataSource.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDataSource).show();
```

`txtHDataSource.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHDataSource.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDataSource).show();
```

`txtHDataSource.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && tRow != null) {
setFunctionDiv();
grdFieldDetail.getGrid().getStore().clear();
```

`modifyReason.addCallBack` → `onOk()`
```java
try {
reasonInfo_Mdl = modifyReason;
SmsTemplate_Mdl mrTemp = new SmsTemplate_Mdl();
if (topControl.getMode().equalsIgnoreCase("Browse") && DmShared.iNull(topControl.getDocId()).longValue() > 0) {
topControlDelete(topControl.getDocId());
} else {
mrTemp = topControlSaveInitialize();
topControlSave(mrTemp);
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), (DmTextBox) null).show();
...
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

`sendGridEdit.addCallBack` → `onOk()`
```java
grdSendDetail.commitChanges();
```

`sendGridEdit.addCallBack` → `onDelete()`
```java
grdSendDetail.commitChanges();
```

**Top control options:** setRefreshRequired(false), setDisplay(true)

_Print permission "P" is removed for this screen (replace "P" with "*")._

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Confirm: "Are you sure? You want to export this record."
- Alert: "Please select a record."

## Files
| file | lines |
|---|---|
| client/ImportPopup.java | 186 |
| client/ImportPopup_Dsg.java | 202 |
| client/SendGridEdit.java | 148 |
| client/SendGridEditGridEdit_Dsg.java | 80 |
| client/SmsTemplate.java | 858 |
| client/SmsTemplate_Dsg.java | 427 |
| server/SmsTemplate_SrvImpl.java | 1135 |
| shared/Find_Mdl.java | 64 |
| shared/GrdFieldName_Mdl.java | 27 |
| shared/GrdSendDetail_Mdl.java | 42 |
| shared/SmsTemplate_Mdl.java | 160 |
| shared/SmsTemplate_Srv.java | 42 |
| shared/SmsTemplate_SrvAsync.java | 41 |
| shared/SmsTemplate_Vld.java | 37 |

## UI labels / columns (client)
`Name`, `Mobile`, `Description`, `File Name`, `Message`, `Function Name`, `Template Name`, `Group Name`, `Data Source`

## Models (shared DTOs)
### GrdFieldName_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | fieldName |

### GrdSendDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | value |
| String | remark |
| String | parsedName |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | tempName |
| String | templateGroupName |
| DmEnum.DmWebJobDataSources | datasource |
| String | functionName |
| String | msg |

### SmsTemplate_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | tempName |
| String | templateGroupCode |
| String | templateGroupName |
| DmWebJobDataSources | dataSource |
| String | functionTag |
| String | functionText |
| String | fileName |
| String | filePath |
| String | uploadFilePath |
| String | uploadFileName |
| String | msg |
| String | functionQuery |
| List<KeyFieldExtended> | lstStaffInfo |
| List<GrdSendDetail_Mdl> | lstSend |
| boolean | staffInfo |
| ModifyReason_Mdl | reasonInfo_Mdl |
| List<GrdFieldName_Mdl> | grdFieldMdl |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<SmsTemplate_Mdl> |
| saveRecord | SmsTemplate_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl reasonInfo_Mdl | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getSqlDataSource | String functionName | DmResult<List<GrdFieldName_Mdl>> |
| getReadFile | SmsTemplate_Mdl mrTemp | DmResult<List<GrdFieldName_Mdl>> |
| getTxtHUndTempName | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHFunctionName | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDataSource | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getSuggetionTemplate | DmHelper helper, boolean blnToEndOfFile | DmResult<List<String>> |
| exportJson | String sessionId, SmsTemplate_Mdl mrTemp | DmResult<KeyFieldExtended> |
| importJson | String filePath, String fileName | DmResult<SmsTemplate_Mdl> |
| checkImportedValues | SmsTemplate_Mdl mrTemp | DmResult<SmsTemplate_Mdl> |

## Validator `SmsTemplate_Vld`
```java
public class SmsTemplate_Vld {
    public static void chkSave(SmsTemplate_Mdl mrTemp) {
        boolean flag = false;
        DmShared.isValidText(mrTemp.getTempName(), "Template Name", 100);
        DmShared.isValidText(mrTemp.getTemplateGroupName(), "Template Group Name", 200);
        DmShared.isValidText(mrTemp.getDataSource().getSourceName(), "Data Source Name", 300);
        if (DmShared.iNull(mrTemp.getDataSource().getSourceType()).intValue() == DmWebJobDataSources.CSV.getSourceType()) {
            DmShared.isValidText(mrTemp.getFunctionText(), "File Name", 400);
        } else {
            DmShared.isValidText(mrTemp.getFunctionText(), "Function Name", 400);
        }
        if (mrTemp.isStaffInfo() && mrTemp.getLstStaffInfo().size() != 0) {
            for (KeyFieldExtended keyField : mrTemp.getLstStaffInfo()) {
                if (DmShared.iNull(keyField.getSharedKeyFieldCode()).longValue() != 0) {
                    flag = true;
                }
            }
            if (!flag) {
                throw new DmException(600, "Staff Info is mandatory.");
            }
        }
    }
}
```

## Server `SmsTemplate_SrvImpl.java` (1135 lines)
Helpers used: `DmBaseServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getSerialNoBigInt`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnUserName`, `DmServer.setSsnOldData`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `SELECT code FROM wjTemplateMast where templateType = :templateType ORDER BY name;`
- Params: `:templateType` ← `DmWebJobTypes.SMS.getCode()`
- Other calls: `SMS.getCode`

### `getMoveRec`
`public DmResult<SmsTemplate_Mdl> getMoveRec(String strKey)`

- SQL 1: `select wjtm.code As code, wjtm.name As name ,wjtm.templateGroupCode AS templateGroupCode,wjtm.u_Name AS u_Name,wjtm.preparedBy AS preparedBy, wjtm.datasource AS datasource, wjtm.functionName AS functionName,wjtm.message AS message ,tgm.name AS templateGroupName,wjtm.preparedByIP,wjtm.preparedDt from wjTemplateMast AS wjtm left join wjTemplateGroupMast AS tgm on wjtm.templateGroupCode = tgm.code Where wjtm.code= :code AND wjtm.templateType= :templateType`
- SQL 2: `select so.name as SPECIFIC_NAME,ssm.definition as ROUTINE_DEFINITION from sys.objects so left join sys.sql_modules ssm on so.object_id = ssm.object_id where so.type = 'IF' and so.name = :functionName`
- SQL 3: `Select templateCode, value, nature, name, remark from wjTemplateSendTo WHERE templateCode = :code order by nature ;`
- Params: `:code` ← `DmShared.iNull(strKey).toString()`; `:templateType` ← `DmWebJobTypes.SMS.getCode()`; `:functionName` ← `DmShared.xNull(mrTemp.getFunctionText())`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).longValue()`
- Other calls: `DmWebJobDataSources.getValueFrom`, `SEND_TO_NATURE.getViaKey`, `SMS.getCode`, `StaffInfo_Server.getStafInfo`

### `saveRecord`
`public DmResult<String> saveRecord(SmsTemplate_Mdl mrTemp)`

- SQL 1: `SELECT COUNT(name) AS cnt FROM wjTemplateMast WHERE (name = :name) AND (code <> :code) and templateType = {…}`
- SQL 2: `INSERT INTO wjTemplateMast (code, name, templateGroupCode, dataSource, functionName, message,preparedBy,templateType,preparedByIP,prepareddt) VALUES (:code, :name, :templateGroupCode, :dataSource, :functionName, :message,:preparedBy,:templateType,:preparedByIP,:uEntDt )`
- SQL 3: `UPDATE wjTemplateMast SET code = :code, name = :name, templateGroupCode = :templateGroupCode, dataSource = :dataSource, functionName = :functionName, message = :message, u_Name = :u_Name, u_EntDt = :uEntDt, templateType = :templateType WHERE code = :code ;`
- SQL 4: `Delete From wjTemplateSendTo Where templateCode= :code`
- Params: `:name` ← `DmShared.xNull(mrTemp.getTempName()).toLowerCase()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:name` ← `mrTemp.getTempName()`; `:templateGroupCode` ← `DmShared.foriegnKey(mrTemp.getTemplateGroupCode())`; `:dataSource` ← `DmShared.iNull(mrTemp.getDataSource().getSourceType()).toString()`; `:templateType` ← `DmWebJobTypes.SMS.getCode()`; `:functionName` ← `mrTemp.getFunctionText()`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:name` ← `mrTemp.getTempName()`; `:templateGroupCode` ← `DmShared.foriegnKey(mrTemp.getTemplateGroupCode())`; `:dataSource` ← `DmShared.iNull(mrTemp.getDataSource().getSourceType()).toString()`; `:templateType` ← `DmWebJobTypes.SMS.getCode()`; `:functionName` ← `mrTemp.getFunctionText()`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).longValue()`; `:templateCode` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).longValue()`; `:value` ← `DmShared.xNull(lst.getValue())`; `:nature` ← `SEND_TO_NATURE.MOBILE.getId()`; `:name` ← `DmShared.xNull(lst.getSharedKeyFieldName())`; `:remark` ← `DmShared.xNull(lst.getRemark())`
- Errors: "Template Name already exists." | "Invalid key."
- Other calls: `CSV.getSourceName`, `CSV.getSourceType`, `FUNCTION.getSourceType`, `MOBILE.getId`, `ModifyReason_Server.saveNew`, `SMS.getCode`, `SmsTemplate_Vld.chkSave`, `StaffInfo_Server.saveStaffInfo`

### `checkField`
`public void checkField(SmsTemplate_Mdl mrTemp)`

- Errors: "Message Is In Incorrect Format. Field Is Not Find OR Did you forget to open or close any curly braces? "

### `chkBraces`
`public boolean chkBraces(String rtnVal, SmsTemplate_Mdl mrTemp, String getByValue) throws DmException`


### `matchField`
`public boolean matchField(String textToSearch, SmsTemplate_Mdl mrTemp, String getByValue) throws DmException`


### `chkValue`
`public boolean chkValue(String rtnVal, SmsTemplate_Mdl mrTemp, String getByValue) throws DmException`


### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl reasonInfo_Mdl)`

- SQL 1: `Delete From wjTemplateSendTo Where templateCode = :code`
- SQL 2: `Delete From wjTemplateMast Where code= :code`
- Params: `:code` ← `DmShared.iNull(strKey).longValue()`; `:code` ← `strKey`
- Other calls: `ModifyReason_Server.saveNew`, `StaffInfo_Server.deleteStaffInfo`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `select wjtm.code As code, wjtm.name As name ,wjtm.templateGroupCode AS templateGroupCode, wjtm.datasource AS datasource, wjtm.functionName AS functionName,wjtm.message AS message ,tgm.name AS templateGroupName from wjTemplateMast AS wjtm left join wjTemplateGroupMast AS tgm on wjtm.templateGroupCode = tgm.code where templateType = :templateType`
- Other calls: `DmWebJobDataSources.getValueFrom`, `SMS.getCode`

### `getTxtHUndTempName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHUndTempName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT code, name FROM wjTemplateGroupMast`

### `getTxtHFunctionName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHFunctionName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT name, definition, type_desc FROM sys.sql_modules m INNER JOIN sys.objects o ON m.object_id=o.object_id WHERE (type_desc like '%function%') and (name like 'sms_%')`

### `getTxtHDataSource`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDataSource(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `CSV.getSourceName`, `CSV.getSourceType`, `FUNCTION.getSourceName`, `FUNCTION.getSourceType`

### `getSqlDataSource`
`public DmResult<List<GrdFieldName_Mdl>> getSqlDataSource(String functionName)`

- SQL 1: `select * from dbo. {…} ('','','') order by uniqueid offset 0 rows fetch next 1 rows only`
- Errors: "Select function name."

### `getReadFile`
`public DmResult<List<GrdFieldName_Mdl>> getReadFile(SmsTemplate_Mdl mrTemp)`

- Errors: "File not Found to read."

### `getSuggetionTemplate`
`public DmResult<List<String>> getSuggetionTemplate(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT name FROM wjTemplateMast where templateType = {…}`
- Other calls: `SMS.getCode`

### `getSaveFile`
`public void getSaveFile(SmsTemplate_Mdl mrTemp)`


### `getDeleteFile`
`public void getDeleteFile(SmsTemplate_Mdl mrTemp)`


### `mandatoryField`
`private void mandatoryField(List<GrdFieldName_Mdl> lstTemp) throws Exception`

- Errors: "The Field "

### `exportJson`
`public DmResult<KeyFieldExtended> exportJson(String session, SmsTemplate_Mdl mrTemp)`

- Errors: "Invalid request." | "File Name not given."

### `importJson`
`public DmResult<SmsTemplate_Mdl> importJson(String filePath, String fileName)`


### `checkImportedValues`
`public DmResult<SmsTemplate_Mdl> checkImportedValues(SmsTemplate_Mdl mrTemp)`

- SQL 1: `SELECT wjtgm.code, wjtgm.name FROM wjTemplateGroupMast AS wjtgm WHERE wjtgm.name= :templateGroup`
- SQL 2: `select count(so.name) as cnt from sys.objects so left join sys.sql_modules ssm on so.object_id = ssm.object_id where so.type = 'IF' and so.name like :functionName`
- SQL 3: `select so.name as functionName from sys.objects so left join sys.sql_modules ssm on so.object_id = ssm.object_id where so.type = 'IF' and so.name = :functionName`
- Params: `:templateGroup` ← `DmShared.xNull(mrTemp.getTemplateGroupName()).trim()`; `:functionName` ← `DmShared.xNull(mrTemp.getFunctionText()).trim() + "%"`; `:functionName` ← `DmShared.xNull(newFunction).trim()`

### `getParsedName`
`private String getParsedName(String nature, String email)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.server`, `dataman.dmBase.dmFileManager.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.client`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFileUploadButton.client`, `dataman.dmToolkit.dmFileUploadButton.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextArea.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.server`, `dataman.dmToolkit.modifyReason.shared`, `dataman.libraryMenu.self.shared`, `dataman.webJobs.self.shared`, `dataman.webJobs.smsTemplate.shared`, `dataman.webJobs.staffInfo.client`, `dataman.webJobs.staffInfo.server`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Template Name already exists." |
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| checkField | `if (!chkBraces(mrTemp.getMsg(), mrTemp, "Message"))` | "Message Is In Incorrect Format. Field Is Not Find OR Did you forget to open or close any curly braces? " |
| matchField | `if (!match.find())` | " is not contain any value in braces." |
| chkValue | `if (tempVal.equalsIgnoreCase(""))` | " is not contain any value in braces." |
| getSqlDataSource | `if (functionName.equalsIgnoreCase(""))` | "Select function name." |
| getReadFile | `if (!tmpFile.exists())` | "File not Found to read." |
| mandatoryField | `if (lstTemp.stream().filter(m -> m.getFieldName().equalsIgnoreCase(lstField)).count() == 0)` | "The Field " |
| exportJson | `if (!DmSession.getSessions().containsKey(sessionId))` | "Invalid request." |
| exportJson | `if (DmShared.xNull(fileName).equalsIgnoreCase(""))` | "File Name not given." |

## Notes (hand-written)
_No hand-written notes yet._
