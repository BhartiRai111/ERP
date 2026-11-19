# dm-web-jobs / emailTemplateMast

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/webJobs/src/emailTemplateMast/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: wjTemplateMast
- Update tables: wjTemplateMast, linkFileCollection
- Delete cascade (in order): wjTemplateSendTo, wjTemplateAttachment, wjTemplateFileDetail, wjTemplateMast
- Duplicate checks on save:
  - `SELECT COUNT(name) AS cnt FROM wjTemplateMast WHERE (name = :name) AND (code <> :code) and templateType = {…}`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Template Name (Text); Template Group Name (Text); Data Source Name (Text); File Name (Text); Function Name (Text); Subject Name (Text); rule: Staff Info is mandatory.
- Lookups/suggestions: getTxtHUndTempName, getTxtHFunctionName, getTxtHDataSource, getTxtHSendToNature
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `EmailTemplateGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Procedure Name | DmTextBox | `txtProcName` | `100, true` |
| 2 | RPT Name | DmTextBox | `txtRptName` | `100, true` |
| 3 | File Name | DmTextBox | `txtFileName` | `100, true` |

Other widgets (not in tab list): `txtProcedure` (DmTextArea), `txtRpt` (DmTextArea)

**Event handlers (condensed code, CRUD flow and field callbacks):**

`btnProcedure.addClickHandler` → `onClick()`
```java
try {
pnlGrd2.clear();
pnlGrd2.showRelativeTo(btnProcedure);
pnlGrd2.add(txtProcedure);
txtProcedure.setText("1. Create procedure same as document print With One Varchar Parameter.\n"
+ "\n"
+ " Example :- Create procedure [dbo].[TestProcedure](@UniqueId varchar(21)) \n"
+ " As \n"
+ " Begin \n"
+ " Declare @SqlQry as varchar(max) \n"
...
```

`btnRpt.addClickHandler` → `onClick()`
```java
try {
pnlGrd3.clear();
pnlGrd3.showRelativeTo(btnRpt);
pnlGrd3.add(txtRpt);
txtRpt.setText("1. Create RPT same as document print.");
txtRpt.setReadOnly(true);
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), btnRpt).show();
```
- Alert: "Procedure is mandatory"
- Alert: "Rpt name is mandatory"
- Alert: "File name is mandatory"

### Screen `EmailTemplateMast`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Template Name | DmSuggestBox | `txtTempName` | `100, 7, true, "name", DmDataType.STRING` |
| 2 | Group Name | DmTextHelp | `txtHTemplateGroupName` | `KeyField_PrpAcs, 7, true` |
| 3 | Data Source | DmTextHelp | `txtHDataSource` | `KeyField_PrpAcs, 3, true` |
| 4 | - | DmTextHelp | `txtHFunctionName` | `KeyField_PrpAcs, 7, true` |
| 5 | Subject | DmRemarkTextBox | `txtSubject` | `250, true` |
| 6 | Message | DmTextArea | `txtMsg` | `` |

Other widgets (not in tab list): `txtCSVFunction` (DmTextBox), `txtHelp` (DmTextArea), `dmFind` (DmFind), `fileRuleType` (DmFileRuleType), `grdFieldDetail` (DmGrid), `grdEmailDetail` (DmGrid), `grdImageDetail` (DmGrid), `grdSendDetail` (DmGrid), `btnUpload` (DmFileUploadButton)

**Grid / popup columns** (header, width, db field):
- `txtHTemplateGroupName`: Under Template Name [Text, w0, name]
- `txtHFunctionName`: Function Name [Text, w0, name]
- `txtHDataSource`: Type [Text, w0, Type]
- `dmFind`: Template Name [Text, w200, name]; Template Group Name [Text, w200, templateGroupName]; Data Source [Text, w150, dataSource]; Function Name [Text, w250, functionName]; Message [Text, w350, message]
- `grdFieldDetail`: Field Name [Text, w200]; Format [Text, w200]
- `grdEmailDetail`: Procedure Name [Text, w100]; RPT Name [Text, w80]; File Name [Text, w80]
- `grdImageDetail`: Image Code [Text, w1]; Rule Code [Text, w75]
- `grdSendDetail`: Send To [Text, w0]

**Lookup / suggestion / find sources (server RPC called):**
- `fileManager` → `updateImageContentId`
- `txtTempName` → `getSuggetionTemplate`
- `txtHTemplateGroupName` → `getTxtHUndTempName`
- `txtHFunctionName` → `getTxtHFunctionName`
- `txtHDataSource` → `getTxtHDataSource`
- `cancelReasonPopup` → `getSqlDataSource`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `TempName` ← DmShared.xNull(txtTempName.getText())
- `TempGroupCode` ← DmShared.xNull(txtHTemplateGroupName.getTag())
- `TempGroupName` ← DmShared.xNull(txtHTemplateGroupName.getText())
- `DataSource` ← DmWebJobDataSources.getValueFrom(DmShared.iNull(txtHDataSource.getTag()).intValu
- `FunctionTag` ← DmShared.xNull(txtCSVFunction.getTag())
- `FunctionText` ← DmShared.xNull(txtCSVFunction.getText())
- `FunctionTag` ← DmShared.xNull(txtHFunctionName.getTag())
- `FunctionText` ← DmShared.xNull(txtHFunctionName.getText())
- `Subject` ← DmShared.xNull(txtSubject.getText())
- `Msg` ← DmShared.xNull(txtMsg.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtTempName.setText` ← data.getTempName()
- `txtHTemplateGroupName.setTag` ← data.getTempGroupCode()
- `txtHTemplateGroupName.setText` ← data.getTempGroupName()
- `txtHDataSource.setTag` ← DmShared.iNull(data.getDataSource().getSourceType()).toString()
- `txtHDataSource.setText` ← data.getDataSource().getSourceName()
- `txtCSVFunction.setTag` ← data.getFunctionTag()
- `txtCSVFunction.setText` ← data.getFunctionText()
- `txtHFunctionName.setTag` ← data.getFunctionTag()
- `txtHFunctionName.setText` ← data.getFunctionText()
- `txtSubject.setText` ← data.getSubject()
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
grdEmailDetail.getGrid().getStore().add(new GrdEmail_Mdl());
grdImageDetail.getGrid().getStore().add(new GrdImage_Mdl());
grdSendDetail.getGrid().getStore().add(new GrdSendDetail_Mdl());
setFunctionDiv();
```

`topControl.addDeleteHandeller` → `deleteClicked()`
```java
new DmConfirmMessageBox("Are you sure? You want to delete this record.", new DialogHideEvent.DialogHideHandler() {
public void onDialogHide(DialogHideEvent event) {
if (event.getHideButton().equals(Dialog.PredefinedButton.YES)) {
showModifyReason();
}).show();
```

`topControl.addDeleteHandeller` → `onDialogHide()`
```java
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
EmailTemplateMast_Mdl mrTemp = new EmailTemplateMast_Mdl();
mrTemp = topControlSaveInitialize();
try {
EmailTemplateMast_Vld.chkSave(mrTemp);
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(EmailTemplateMast.this);
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

`fileManager.addCallBack` → `close()`
```java
srvAsync.invokeRPC().updateImageContentId(topControl.getDocId(), new AsyncCallback<DmResult<String>>() {
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtTempName).show();
public void onSuccess(DmResult<String> result) {
if (result.isStatus()) {
} else {
topControlSaveError(result.getErrorNumber(), result.getErrorMessage());
```

`fileManager.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
} else {
topControlSaveError(result.getErrorNumber(), result.getErrorMessage());
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

`cancelReasonPopup.addCallBack` → `onOk()`
```java
try {
reasonInfo_Mdl = modifyReason;
EmailTemplateMast_Mdl mrTemp = new EmailTemplateMast_Mdl();
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

**Top control options:** setRefreshRequired(false), setDisplay(true)

_Print permission "P" is removed for this screen (replace "P" with "*")._

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Confirm: "Are you sure? You want to export this record."
- Alert: "Please select a record."

### Screen `ImageDetailGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Image Code | DmTextBox | `txtImageCode` | `20, true` |
| 2 | Rule Code | DmTextBox | `txtRuleCode` | `20, true` |
- Alert: "Image Field is mandatory"
- Alert: "Rule Code is mandatory"
- Alert: "Image Field & Rule Code already exists."

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
| 2 | E-Mail | DmTextHelp | `txtHNature` | `KeyField_PrpAcs, 5, true, true` |
| 3 | - | DmTextBox | `txtValue` | `100, true` |
| 4 | Description | DmRemarkTextBox | `txtRemark` | `` |

**Grid / popup columns** (header, width, db field):
- `txtHNature`: Name [Text, w0, NAME]

## Files
| file | lines |
|---|---|
| client/EmailTemplateGridEdit.java | 158 |
| client/EmailTemplateGridEdit_Dsg.java | 127 |
| client/EmailTemplateMast.java | 981 |
| client/EmailTemplateMast_Dsg.java | 512 |
| client/ImageDetailGridEdit.java | 112 |
| client/ImageDetailGridEdit_Dsg.java | 67 |
| client/ImportPopup.java | 171 |
| client/ImportPopup_Dsg.java | 200 |
| client/SendGridEdit.java | 201 |
| client/SendGridEditGridEdit_Dsg.java | 128 |
| server/EmailTemplateMast_SrvImpl.java | 1319 |
| shared/EmailTemplateGridEdit_Mdl.java | 41 |
| shared/EmailTemplateMast_Mdl.java | 192 |
| shared/EmailTemplateMast_Srv.java | 47 |
| shared/EmailTemplateMast_SrvAsync.java | 44 |
| shared/EmailTemplateMast_Vld.java | 26 |
| shared/Find_Mdl.java | 64 |
| shared/GrdEmail_Mdl.java | 41 |
| shared/GrdFieldName_Mdl.java | 27 |
| shared/GrdImage_Mdl.java | 32 |
| shared/GrdSendDetail_Mdl.java | 59 |

## UI labels / columns (client)
`Image Code`, `Rule Code`, `Procedure Name`, `RPT Name`, `File Name`, `Name`, `E-Mail`, `Description`, `Message`, `Function Name`, `Template Name`, `Group Name`, `Data Source`, `Subject`

## Models (shared DTOs)
### GrdEmail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | procName |
| String | rptName |
| String | fileName |

### EmailTemplateMast_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| List<GrdSendDetail_Mdl> | lstSend |
| List<GrdEmail_Mdl> | grdEmail |
| List<GrdImage_Mdl> | grdImageDetail |
| List<KeyFieldExtended> | lstStaffInfo |
| String | tempName |
| String | tempGroupCode |
| String | tempGroupName |
| DmWebJobDataSources | datasource |
| String | functionTag |
| String | functionText |
| String | functionQuery |
| String | fileName |
| String | filePath |
| String | uploadFilePath |
| String | uploadFileName |
| String | subject |
| boolean | staffInfo |
| ModifyReason_Mdl | reasonInfo_Mdl |
| String | msg |
| List<GrdFieldName_Mdl> | grdFieldMdl |

### GrdFieldName_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | fieldName |

### GrdSendDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | natureCode |
| String | natureName |
| String | value |
| String | remark |
| String | parsedName |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | tempName |
| String | templateGroupName |
| DmWebJobDataSources | datasource |
| String | functionName |
| String | msg |

### EmailTemplateGridEdit_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | procName |
| String | rptName |
| String | fileName |

### GrdImage_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | imageCode |
| String | ruleCode |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<EmailTemplateMast_Mdl> |
| saveRecord | EmailTemplateMast_Mdl mrTemp | DmResult<String> |
| updateImageContentId | String code | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl reasonInfo_Mdl | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getSqlDataSource | String functionName | DmResult<List<GrdFieldName_Mdl>> |
| getReadFile | EmailTemplateMast_Mdl mrTemp | DmResult<List<GrdFieldName_Mdl>> |
| getTxtHUndTempName | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHFunctionName | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDataSource | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSendToNature | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getSuggetionTemplate | DmHelper helper, boolean blnToEndOfFile | DmResult<List<String>> |
| exportJson | String sessionId, EmailTemplateMast_Mdl mrTemp | DmResult<KeyFieldExtended> |
| importJson | String filePath, String fileName | DmResult<EmailTemplateMast_Mdl> |
| checkImportedValues | EmailTemplateMast_Mdl mrTemp | DmResult<EmailTemplateMast_Mdl> |

## Validator `EmailTemplateMast_Vld`
```java
public class EmailTemplateMast_Vld {
    public static void chkSave(EmailTemplateMast_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getTempName(), "Template Name", 100);
        DmShared.isValidText(mrTemp.getTempGroupName(), "Template Group Name", 200);
        DmShared.isValidText(mrTemp.getDataSource().getSourceName(), "Data Source Name", 300);
        if (DmShared.iNull(mrTemp.getDataSource().getSourceType()).intValue() == DmWebJobDataSources.CSV.getSourceType()) {
            DmShared.isValidText(mrTemp.getFunctionText(), "File Name", 400);
        } else {
            DmShared.isValidText(mrTemp.getFunctionText(), "Function Name", 400);
            DmShared.isValidText(mrTemp.getSubject(), "Subject Name", 500);
        }
        if (mrTemp.isStaffInfo() && mrTemp.getLstStaffInfo().size() == 0) {
            throw new DmException(600, "Staff Info is mandatory.");
        }
    }
}
```

## Server `EmailTemplateMast_SrvImpl.java` (1319 lines)
Helpers used: `DmBaseServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getSerialNoBigInt`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnUserName`, `DmServer.setSsnOldData`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `SELECT code FROM wjTemplateMast where templateType = :templateType ORDER BY name;`
- Params: `:templateType` ← `DmWebJobTypes.EMAIL.getCode()`
- Other calls: `EMAIL.getCode`

### `getMoveRec`
`public DmResult<EmailTemplateMast_Mdl> getMoveRec(String strKey)`

- SQL 1: `Select wjtm.code As code, wjtm.name As name ,wjtm.templateGroupCode AS templateGroupCode,wjtm.u_Name AS u_Name,wjtm.preparedBy AS preparedBy,wjtm.preparedByIP,wjtm.preparedDt, wjtm.datasource AS datasource, wjtm.functionName AS functionName, wjtm.subject AS subject, wjtm.message AS message ,tgm.name AS templateGroupName from wjTemplateMast AS wjtm left join wjTemplateGroupMast AS tgm on wjtm.templateGroupCode = tgm.code Where wjtm.code= :code AND wjtm.templateType= :templateType`
- SQL 2: `Select templateCode, value, nature, name, remark from wjTemplateSendTo WHERE templateCode = :code order by nature;`
- SQL 3: `Select wja.procedureName AS procedureName,wja.rptName AS rptName,wja.fileName AS fileName FROM wjTemplateAttachment AS wja WHERE wja.templateCode = :code ;`
- SQL 4: `Select wji.fileCode AS imageCode,wji.ruleCode AS ruleCode FROM wjTemplateFileDetail AS wji WHERE wji.templateCode = :code ;`
- SQL 5: `Select so.name as SPECIFIC_NAME,ssm.definition as ROUTINE_DEFINITION from sys.objects so left join sys.sql_modules ssm on so.object_id = ssm.object_id where so.type = 'IF' and so.name = :functionName`
- Params: `:code` ← `strKey`; `:templateType` ← `DmWebJobTypes.EMAIL.getCode()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).longValue()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:functionName` ← `DmShared.xNull(mrTemp.getFunctionText())`
- Other calls: `DmWebJobDataSources.getValueFrom`, `EMAIL.getCode`, `SEND_TO_NATURE.getViaKey`, `StaffInfo_Server.getStafInfo`

### `saveRecord`
`public DmResult<String> saveRecord(EmailTemplateMast_Mdl mrTemp)`

- SQL 1: `SELECT COUNT(name) AS cnt FROM wjTemplateMast WHERE (name = :name) AND (code <> :code) and templateType = {…}`
- SQL 2: `INSERT INTO wjTemplateMast (code, name, templateGroupCode, dataSource, functionName, subject, message,preparedBy,u_EntDt, templateType,preparedByIP,prepareddt) VALUES (:code, :name, :templateGroupCode, :dataSource, :functionName, :subject, :message,:preparedBy,GETDATE(), :templateType,:preparedByIP,:uEntDt )`
- SQL 3: `UPDATE wjTemplateMast SET code = :code, name = :name, templateGroupCode = :templateGroupCode, dataSource = :dataSource, functionName = :functionName, subject = :subject, message = :message, u_Name = :u_Name, u_EntDt = GETDATE(), templateType = :templateType WHERE code = :code ;`
- SQL 4: `Delete From wjTemplateAttachment Where templateCode= :code`
- SQL 5: `Delete From wjTemplateSendTo Where templateCode= :code`
- SQL 6: `Delete From wjTemplateFileDetail Where templateCode= :code`
- Params: `:name` ← `DmShared.xNull(mrTemp.getTempName()).toLowerCase()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:name` ← `mrTemp.getTempName()`; `:templateGroupCode` ← `DmShared.foriegnKey(mrTemp.getTempGroupCode())`; `:dataSource` ← `DmShared.iNull(mrTemp.getDataSource().getSourceType()).toString()`; `:functionName` ← `mrTemp.getFunctionText()`; `:templateType` ← `DmWebJobTypes.EMAIL.getCode()`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:name` ← `mrTemp.getTempName()`; `:templateGroupCode` ← `DmShared.foriegnKey(mrTemp.getTempGroupCode())`; `:dataSource` ← `DmShared.iNull(mrTemp.getDataSource().getSourceType()).toString()`; `:functionName` ← `mrTemp.getFunctionText()`; `:templateType` ← `DmWebJobTypes.EMAIL.getCode()`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).longValue()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).longValue()`; `:templateCode` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).longValue()`; `:value` ← `DmShared.xNull(lst.getValue())`; `:nature` ← `DmShared.iNull(lst.getNatureCode()).intValue()`; `:name` ← `DmShared.xNull(lst.getSharedKeyFieldName())`; `:remark` ← `DmShared.xNull(lst.getRemark())`; `:vSno` ← `srNo`; `:code` ← `DmShared.foriegnKey(mrTemp.getSharedKeyFieldCode())`; `:procedureName` ← `DmShared.xNull(lst.getProcName())`; `:rptName` ← `DmShared.xNull(lst.getRptName())`; `:fileName` ← `DmShared.xNull(lst.getFileName())`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:vSno` ← `srNo`; `:code` ← `DmShared.foriegnKey(mrTemp.getSharedKeyFieldCode())`; `:fileCode` ← `DmShared.xNull(lst.getImageCode())`; `:ruleCode` ← `DmShared.xNull(lst.getRuleCode())`
- Errors: "Template Name already exists." | "Invalid key."
- Other calls: `CSV.getSourceName`, `CSV.getSourceType`, `EMAIL.getCode`, `EmailTemplateMast_Vld.chkSave`, `FUNCTION.getSourceType`, `ModifyReason_Server.saveNew`, `StaffInfo_Server.saveStaffInfo`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl reasonInfo_Mdl)`

- SQL 1: `Delete From wjTemplateSendTo Where templateCode = :code`
- SQL 2: `Delete From wjTemplateAttachment Where templateCode = :code`
- SQL 3: `Delete From wjTemplateFileDetail Where templateCode = :code`
- SQL 4: `Delete From wjTemplateMast Where code= :code`
- Params: `:code` ← `DmShared.iNull(strKey).longValue()`; `:code` ← `strKey`; `:code` ← `strKey`; `:code` ← `strKey`
- Other calls: `ModifyReason_Server.saveNew`, `StaffInfo_Server.deleteStaffInfo`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `select wjtm.code As code, wjtm.name As name ,wjtm.templateGroupCode AS templateGroupCode, wjtm.datasource AS datasource, wjtm.functionName AS functionName,wjtm.message AS message ,tgm.name AS templateGroupName from wjTemplateMast AS wjtm left join wjTemplateGroupMast AS tgm on wjtm.templateGroupCode = tgm.code where templateType = :templateType`
- Other calls: `DmWebJobDataSources.getValueFrom`, `EMAIL.getCode`

### `getTxtHUndTempName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHUndTempName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT code, name FROM wjTemplateGroupMast`

### `getTxtHFunctionName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHFunctionName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT name, definition, type_desc FROM sys.sql_modules m INNER JOIN sys.objects o ON m.object_id=o.object_id WHERE (type_desc like '%function%') and (name like 'email_%')`

### `getTxtHDataSource`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDataSource(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `CSV.getSourceName`, `CSV.getSourceType`, `FUNCTION.getSourceName`, `FUNCTION.getSourceType`

### `getSqlDataSource`
`public DmResult<List<GrdFieldName_Mdl>> getSqlDataSource(String functionName)`

- SQL 1: `select * from dbo. {…} ('','','') order by uniqueid offset 0 rows fetch next 1 rows only`
- Errors: "Select function name."

### `getReadFile`
`public DmResult<List<GrdFieldName_Mdl>> getReadFile(EmailTemplateMast_Mdl mrTemp)`

- Errors: "File not Found to read."

### `getSuggetionTemplate`
`public DmResult<List<String>> getSuggetionTemplate(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT name FROM wjTemplateMast where templateType = {…}`
- Other calls: `EMAIL.getCode`

### `getSaveFile`
`public void getSaveFile(EmailTemplateMast_Mdl mrTemp)`


### `getDeleteFile`
`public void getDeleteFile(EmailTemplateMast_Mdl mrTemp)`


### `checkField`
`public void checkField(EmailTemplateMast_Mdl mrTemp)`

- Errors: "Subject Is In Incorrect Format. Field Is Not Find OR Did you forget to open or close any curly braces? " | "Message Is In Incorrect Format. Field Is Not Find OR Did you forget to open or close any curly braces? " | "Procedure Is In Incorrect Format. Field Is Not Find OR Did you forget to open or close any curly braces? " | "Rpt Name Is In Incorrect Format. Field Is Not Find OR Did you forget to open or close any curly braces? " | "File Name Is In Incorrect Format. Field Is Not Find OR Did you forget to open or close any curly braces? "

### `chkBraces`
`public boolean chkBraces(String rtnVal, EmailTemplateMast_Mdl mrTemp, String getByValue) throws DmException`


### `matchField`
`public boolean matchField(String textToSearch, EmailTemplateMast_Mdl mrTemp, String getByValue) throws DmException`


### `chkValue`
`public boolean chkValue(String rtnVal, EmailTemplateMast_Mdl mrTemp, String getByValue) throws DmException`


### `mandatoryField`
`private void mandatoryField(List<GrdFieldName_Mdl> lstTemp) throws Exception`

- Errors: "The Field "

### `exportJson`
`public DmResult<KeyFieldExtended> exportJson(String session, EmailTemplateMast_Mdl mrTemp)`

- Errors: "Invalid request." | "File Name not given."

### `importJson`
`public DmResult<EmailTemplateMast_Mdl> importJson(String filePath, String fileName)`


### `checkImportedValues`
`public DmResult<EmailTemplateMast_Mdl> checkImportedValues(EmailTemplateMast_Mdl mrTemp)`

- SQL 1: `SELECT wjtgm.code, wjtgm.name FROM wjTemplateGroupMast AS wjtgm WHERE wjtgm.name= :templateGroup`
- SQL 2: `select count(so.name) as cnt from sys.objects so left join sys.sql_modules ssm on so.object_id = ssm.object_id where so.type = 'IF' and so.name like :functionName`
- SQL 3: `select so.name as functionName from sys.objects so left join sys.sql_modules ssm on so.object_id = ssm.object_id where so.type = 'IF' and so.name = :functionName`
- Params: `:templateGroup` ← `DmShared.xNull(mrTemp.getTempGroupName()).trim()`; `:functionName` ← `DmShared.xNull(mrTemp.getFunctionText()).trim() + "%"`; `:functionName` ← `DmShared.xNull(newFunction).trim()`

### `updateImageContentId`
`public DmResult<String> updateImageContentId(String code)`

- SQL 1: `UPDATE linkFileCollection SET remarks = fileLink WHERE docId = :code`
- Params: `:code` ← `code`

### `getTxtHSendToNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSendToNature(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `MOBILE.getId`, `SEND_TO_NATURE.values`

### `getParsedName`
`private String getParsedName(String nature, String email)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.server`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmFileManager.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.client`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFileManager.client`, `dataman.dmToolkit.dmFileUploadButton.client`, `dataman.dmToolkit.dmFileUploadButton.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextArea.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.server`, `dataman.dmToolkit.modifyReason.shared`, `dataman.libraryMenu.self.shared`, `dataman.webJobs.emailTemplateMast.shared`, `dataman.webJobs.self.shared`, `dataman.webJobs.staffInfo.client`, `dataman.webJobs.staffInfo.server`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Template Name already exists." |
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| getSqlDataSource | `if (functionName.equalsIgnoreCase(""))` | "Select function name." |
| getReadFile | `if (!tmpFile.exists())` | "File not Found to read." |
| checkField | `if (!chkBraces(mrTemp.getSubject(), mrTemp, "Subject"))` | "Subject Is In Incorrect Format. Field Is Not Find OR Did you forget to open or close any curly braces? " |
| checkField | `if (!chkBraces(mrTemp.getMsg(), mrTemp, "Message"))` | "Message Is In Incorrect Format. Field Is Not Find OR Did you forget to open or close any curly braces? " |
| checkField | `if (!chkBraces(grdEmail.getProcName(), mrTemp, grdEmail.getProcName()))` | "Procedure Is In Incorrect Format. Field Is Not Find OR Did you forget to open or close any curly braces? " |
| checkField | `if (!chkBraces(grdEmail.getRptName(), mrTemp, grdEmail.getRptName()))` | "Rpt Name Is In Incorrect Format. Field Is Not Find OR Did you forget to open or close any curly braces? " |
| checkField | `if (!chkBraces(grdEmail.getFileName(), mrTemp, grdEmail.getFileName()))` | "File Name Is In Incorrect Format. Field Is Not Find OR Did you forget to open or close any curly braces? " |
| matchField | `if (!match.find())` | " is not contain any value in braces." |
| chkValue | `if (tempVal.equalsIgnoreCase(""))` | " is not contain any value in braces." |
| mandatoryField | `if (lstTemp.stream().filter(m -> m.getFieldName().equalsIgnoreCase(lstField)).count() == 0)` | "The Field " |
| exportJson | `if (!DmSession.getSessions().containsKey(sessionId))` | "Invalid request." |
| exportJson | `if (DmShared.xNull(fileName).equalsIgnoreCase(""))` | "File Name not given." |

## Notes (hand-written)
_No hand-written notes yet._
