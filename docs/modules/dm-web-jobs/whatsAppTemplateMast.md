# dm-web-jobs / whatsAppTemplateMast

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/webJobs/src/whatsAppTemplateMast/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: wjTemplateMast, wjTemplateSendTo, wjTemplateAttachment, wjTemplateFileDetail
- Update tables: wjTemplateMast, linkFileCollection
- Delete cascade (in order): wjTemplateSendTo, wjTemplateAttachment, wjTemplateFileDetail, wjTemplateMast
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Template Name (Text); Template Group Name (Text); Data Source Name (Text); File Name (Text); Function Name (Text); External Template Name (Text); rule: Staff Info is mandatory.
- Lookups/suggestions: getTxtHUndTempName, getTxtHFunctionName, getTxtHDataSource, getTxtHSendToNature
- CSV export: no

## Screen spec (auto-extracted from client)
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

### Screen `ReportGridEdit`

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

### Screen `SendGridEditGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Name | DmTextBox | `txtName` | `50, true` |
| 2 | Mobile | DmTextBox | `txtCountryCode` | `3, true` |
| 3 | - | DmTextBox | `txtValue` | `10, true` |
| 4 | Description | DmRemarkTextBox | `txtRemark` | `` |

### Screen `WhatsAppTemplateMast`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Template Name | DmSuggestBox | `txtTempName` | `100, 7, true, "name", DmDataType.STRING` |
| 2 | Group Name | DmTextHelp | `txtHTemplateGroupName` | `KeyField_PrpAcs, 7, true` |
| 3 | Data Source | DmTextHelp | `txtHDataSource` | `KeyField_PrpAcs, 3, true` |
| 4 | - | DmTextHelp | `txtHFunctionName` | `KeyField_PrpAcs, 7, true` |
| 5 | Ext. Template | DmTextBox | `txtExternalTemplate` | `30, true` |
| 6 | Message | DmTextArea | `txtMsg` | `` |

Other widgets (not in tab list): `txtCSVFunction` (DmTextBox), `txtHelp` (DmTextArea), `dmFind` (DmFind), `fileRuleType` (DmFileRuleType), `grdFieldDetail` (DmGrid), `grdReportDetail` (DmGrid), `grdImageDetail` (DmGrid), `grdSendDetail` (DmGrid), `btnUpload` (DmFileUploadButton)

**Grid / popup columns** (header, width, db field):
- `txtHTemplateGroupName`: Under Template Name [Text, w0, name]
- `txtHFunctionName`: Function Name [Text, w0, name]
- `txtHDataSource`: Type [Text, w0, Type]
- `dmFind`: Template Name [Text, w200, name]; Template Group Name [Text, w200, templateGroupName]; Data Source [Text, w150, dataSource]; Function Name [Text, w250, functionName]; Message [Text, w350, message]
- `grdFieldDetail`: Field Name [Text, w200]; Format [Text, w200]
- `grdReportDetail`: Procedure Name [Text, w100]; RPT Name [Text, w80]; File Name [Text, w80]
- `grdImageDetail`: Image Code [Text, w1]; Rule Code [Text, w75]
- `grdSendDetail`: Name [Text, w0]

**Lookup / suggestion / find sources (server RPC called):**
- `fileManager` → `updateImageContentId`
- `txtTempName` → `getSuggetionTemplate`
- `txtHTemplateGroupName` → `getTxtHUndTempName`
- `txtHFunctionName` → `getTxtHFunctionName`
- `txtHDataSource` → `getTxtHDataSource`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `TempName` ← DmShared.xNull(txtTempName.getText())
- `TempGroupCode` ← DmShared.xNull(txtHTemplateGroupName.getTag())
- `TempGroupName` ← DmShared.xNull(txtHTemplateGroupName.getText())
- `Datasource` ← DmWebJobDataSources.getValueFrom(DmShared.iNull(txtHDataSource.getTag()).intValu
- `FunctionTag` ← DmShared.xNull(txtCSVFunction.getTag())
- `FunctionText` ← DmShared.xNull(txtCSVFunction.getText())
- `FunctionTag` ← DmShared.xNull(txtHFunctionName.getTag())
- `FunctionText` ← DmShared.xNull(txtHFunctionName.getText())
- `ExternalTemplate` ← DmShared.xNull(txtExternalTemplate.getText())
- `Msg` ← DmShared.xNull(txtMsg.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtTempName.setText` ← data.getTempName()
- `txtHTemplateGroupName.setTag` ← data.getTempGroupCode()
- `txtHTemplateGroupName.setText` ← data.getTempGroupName()
- `txtHDataSource.setTag` ← DmShared.iNull(data.getDatasource().getSourceType()).toString()
- `txtHDataSource.setText` ← data.getDatasource().getSourceName()
- `txtCSVFunction.setTag` ← data.getFunctionTag()
- `txtCSVFunction.setText` ← data.getFunctionText()
- `txtHFunctionName.setTag` ← data.getFunctionTag()
- `txtHFunctionName.setText` ← data.getFunctionText()
- `txtExternalTemplate.setText` ← data.getExternalTemplate()
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
grdReportDetail.getGrid().getStore().add(new GrdReport_Mdl());
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
WhatsAppTemplateMast_Mdl mrTemp = new WhatsAppTemplateMast_Mdl();
mrTemp = topControlSaveInitialize();
try {
WhatsAppTemplateMast_Vld.chkSave(mrTemp);
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(WhatsAppTemplateMast.this);
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

`modifyReason.addCallBack` → `onOk()`
```java
try {
modifyReason_Mdl = modifyReason;
WhatsAppTemplateMast_Mdl mrTemp = new WhatsAppTemplateMast_Mdl();
if (topControl.getMode().equalsIgnoreCase("Browse") && DmShared.iNull(topControl.getDocId()).longValue() > 0) {
topControlDelete(topControl.getDocId());
} else {
mrTemp = topControlSaveInitialize();
topControlSave(mrTemp);
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), (DmTextBox) null).show();
...
```

`importPopup.addCallBack` → `onYes()`
```java
topControlMoveRecSuccess(mdl, true);
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

## Files
| file | lines |
|---|---|
| client/ImageDetailGridEdit.java | 104 |
| client/ImageDetailGridEdit_Dsg.java | 66 |
| client/ImportPopup.java | 180 |
| client/ImportPopup_Dsg.java | 198 |
| client/ReportGridEdit.java | 148 |
| client/ReportGridEdit_Dsg.java | 134 |
| client/SendGridEdit.java | 139 |
| client/SendGridEditGridEdit_Dsg.java | 92 |
| client/WhatsAppTemplateMast.java | 976 |
| client/WhatsAppTemplateMast_Dsg.java | 506 |
| server/WhatsAppTemplateMast_SrvImpl.java | 1374 |
| shared/Find_Mdl.java | 63 |
| shared/GrdFieldName_Mdl.java | 26 |
| shared/GrdImage_Mdl.java | 31 |
| shared/GrdReport_Mdl.java | 40 |
| shared/GrdSendDetail_Mdl.java | 49 |
| shared/WhatsAppTemplateMast_Mdl.java | 184 |
| shared/WhatsAppTemplateMast_Srv.java | 44 |
| shared/WhatsAppTemplateMast_SrvAsync.java | 44 |
| shared/WhatsAppTemplateMast_Vld.java | 24 |

## UI labels / columns (client)
`Image Code`, `Rule Code`, `Name`, `Mobile`, `Description`, `File Name`, `Message`, `Procedure Name`, `RPT Name`, `Function Name`, `Template Name`, `Group Name`, `Data Source`, `Ext. Template`

## Models (shared DTOs)
### WhatsAppTemplateMast_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
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
| String | externalTemplate |
| boolean | staffInfo |
| String | msg |
| ModifyReason_Mdl | modifyReason_Mdl |
| List<GrdFieldName_Mdl> | lstFieldMdl |
| List<GrdSendDetail_Mdl> | lstSend |
| List<GrdReport_Mdl> | lstReport |
| List<GrdImage_Mdl> | lstImageDetail |
| List<KeyFieldExtended> | lstStaffInfo |

### GrdFieldName_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | fieldName |

### GrdSendDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | value |
| String | countryCode |
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

### GrdReport_Mdl (extends `KeyField implements Serializable, IsSerializable`)
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
| getMoveRec | String strKey | DmResult<WhatsAppTemplateMast_Mdl> |
| saveRecord | WhatsAppTemplateMast_Mdl mrTemp | DmResult<String> |
| updateImageContentId | String code | DmResult<String> |
| deleteRecord | String strKey, String name, ModifyReason_Mdl reasonInfo_Mdl | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getSqlDataSource | String functionName | DmResult<List<GrdFieldName_Mdl>> |
| getReadFile | WhatsAppTemplateMast_Mdl mrTemp | DmResult<List<GrdFieldName_Mdl>> |
| getTxtHUndTempName | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHFunctionName | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDataSource | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSendToNature | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getSuggetionTemplate | DmHelper helper, boolean blnToEndOfFile | DmResult<List<String>> |
| exportJson | String sessionId, WhatsAppTemplateMast_Mdl mrTemp | DmResult<KeyFieldExtended> |
| importJson | String filePath, String fileName | DmResult<WhatsAppTemplateMast_Mdl> |
| checkImportedValues | WhatsAppTemplateMast_Mdl mrTemp | DmResult<WhatsAppTemplateMast_Mdl> |

## Validator `WhatsAppTemplateMast_Vld`
```java
public class WhatsAppTemplateMast_Vld {
    public static void chkSave(WhatsAppTemplateMast_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getTempName(), "Template Name", 100);
        DmShared.isValidText(mrTemp.getTempGroupName(), "Template Group Name", 200);
        DmShared.isValidText(mrTemp.getDatasource().getSourceName(), "Data Source Name", 300);
        if (DmShared.iNull(mrTemp.getDatasource().getSourceType()).intValue() == DmWebJobDataSources.CSV.getSourceType()) {
            DmShared.isValidText(mrTemp.getFunctionText(), "File Name", 400);
        } else {
            DmShared.isValidText(mrTemp.getFunctionText(), "Function Name", 400);
            DmShared.isValidText(mrTemp.getExternalTemplate(), "External Template Name", 500);
        }
        if (mrTemp.isStaffInfo() && mrTemp.getLstStaffInfo().isEmpty()) {
            throw new DmException(600, "Staff Info is mandatory.");
        }
    }
}
```

## Server `WhatsAppTemplateMast_SrvImpl.java` (1374 lines)
Helpers used: `DmBaseServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.getClientIpAddress`, `DmServer.getConnection`, `DmServer.getSerialNoBigInt`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnUserName`, `DmServer.setSsnOldData`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `Select code from wjTemplateMast where templateType = :templateType order by name`
- Params: `:templateType` ← `DmWebJobTypes.WHATSAPP.getCode()`
- Other calls: `WHATSAPP.getCode`

### `getSuggetionTemplate`
`public DmResult<List<String>> getSuggetionTemplate(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select name from wjTemplateMast where templateType = :templateType`
- Other calls: `WHATSAPP.getCode`

### `getMoveRec`
`public DmResult<WhatsAppTemplateMast_Mdl> getMoveRec(String strKey)`

- SQL 1: `Select wjtm.code, wjtm.name, wjtm.templateGroupCode, tgm.name AS templateGroupName, wjtm.datasource, wjtm.functionName, wjtm.externalTemplate, wjtm.message, wjtm.u_Name, wjtm.preparedBy from wjTemplateMast as wjtm left join wjTemplateGroupMast as tgm on wjtm.templateGroupCode = tgm.code Where wjtm.code = :code;`
- SQL 2: `Select templateCode, value, nature, name, remark from wjTemplateSendTo where templateCode = :code order by nature;`
- SQL 3: `Select wja.procedureName, wja.rptName, wja.fileName from wjTemplateAttachment as wja where wja.templateCode = :code;`
- SQL 4: `Select wji.fileCode as imageCode, wji.ruleCode from wjTemplateFileDetail as wji where wji.templateCode = :code;`
- SQL 5: `select so.name as SPECIFIC_NAME,ssm.definition as ROUTINE_DEFINITION from sys.objects so left join sys.sql_modules ssm on so.object_id = ssm.object_id where so.type = 'IF' and so.name = :functionName`
- Params: `:code` ← `DmShared.iNull(strKey).longValue()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).longValue()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).longValue()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).longValue()`; `:functionName` ← `DmShared.xNull(mrTemp.getFunctionText())`
- Other calls: `DmWebJobDataSources.getValueFrom`, `SEND_TO_NATURE.getViaKey`, `StaffInfo_Server.getStafInfo`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, String name, ModifyReason_Mdl modifyReason_Mdl)`

- SQL 1: `Delete From wjTemplateSendTo Where templateCode = :code;`
- SQL 2: `Delete From wjTemplateAttachment Where templateCode = :code;`
- SQL 3: `Delete From wjTemplateFileDetail Where templateCode = :code;`
- SQL 4: `Delete From wjTemplateMast Where code = :code;`
- Params: `:code` ← `DmShared.iNull(strKey).longValue()`; `:code` ← `DmShared.iNull(strKey).longValue()`; `:code` ← `DmShared.iNull(strKey).longValue()`; `:code` ← `DmShared.iNull(strKey).longValue()`
- Other calls: `ModifyReason_Server.saveNew`, `StaffInfo_Server.deleteStaffInfo`

### `saveRecord`
`public DmResult<String> saveRecord(WhatsAppTemplateMast_Mdl mrTemp)`

- SQL 1: `Select code from wjTemplateMast where templateType = :templateType and name = :name and code <> :code;`
- SQL 2: `Insert into wjTemplateMast (code, name, templateGroupCode, dataSource, functionName, externalTemplate, message, templateType, preparedBy, preparedDt, preparedByIp) values (:code, :name, :templateGroupCode, :dataSource, :functionName, :externalTemplate, :message, :templateType, :preparedBy, :preparedDt, :preparedByIp);`
- SQL 3: `Update wjTemplateMast set name = :name, templateGroupCode = :templateGroupCode, dataSource = :dataSource, functionName = :functionName, externalTemplate = :externalTemplate, message = :message, u_Name = :u_Name, u_EntDt = :u_EntDt where code = :code ;`
- SQL 4: `Delete From wjTemplateSendTo Where templateCode = :code;`
- SQL 5: `Delete From wjTemplateAttachment Where templateCode = :code;`
- SQL 6: `Delete From wjTemplateFileDetail Where templateCode = :code;`
- SQL 7: `INSERT INTO wjTemplateSendTo (templateCode, nature, value, name, remark) VALUES (:templateCode, :nature, :value, :name, :remark);`
- SQL 8: `INSERT INTO wjTemplateAttachment (templateCode, v_SNo, procedureName, rptName, fileName) VALUES (:code, :vSno, :procedureName, :rptName, :fileName);`
- SQL 9: `INSERT INTO wjTemplateFileDetail (templateCode, v_SNo, fileCode,ruleCode) VALUES (:code, :vSno, :fileCode,:ruleCode);`
- Params: `:templateType` ← `DmWebJobTypes.WHATSAPP.getCode()`; `:name` ← `DmShared.xNull(mrTemp.getTempName()).toLowerCase(Locale.ENGLISH)`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).longValue()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).longValue()`; `:name` ← `mrTemp.getTempName()`; `:templateGroupCode` ← `DmShared.foriegnKey(mrTemp.getTempGroupCode())`; `:dataSource` ← `DmShared.iNull(mrTemp.getDatasource().getSourceType()).toString()`; `:functionName` ← `mrTemp.getFunctionText()`; `:templateType` ← `DmWebJobTypes.WHATSAPP.getCode()`; `:externalTemplate` ← `mrTemp.getExternalTemplate()`; `:message` ← `mrTemp.getMsg()`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:preparedByIp` ← `DmServer.getClientIpAddress(this)`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).longValue()`; `:name` ← `mrTemp.getTempName()`; `:templateGroupCode` ← `DmShared.foriegnKey(mrTemp.getTempGroupCode())`; `:dataSource` ← `DmShared.iNull(mrTemp.getDatasource().getSourceType()).toString()`; `:functionName` ← `mrTemp.getFunctionText()`; `:externalTemplate` ← `mrTemp.getExternalTemplate()`; `:message` ← `mrTemp.getMsg()`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `DmServer.getServerTimestamp()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).longValue()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).longValue()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).longValue()`; `:templateCode` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).longValue()`; `:nature` ← `SEND_TO_NATURE.MOBILE.getId()`; `:value` ← `mobileValue`; `:name` ← `DmShared.xNull(lst.getSharedKeyFieldName())`; `:remark` ← `DmShared.xNull(lst.getRemark())`; `:code` ← `DmShared.foriegnKey(mrTemp.getSharedKeyFieldCode())`; `:vSno` ← `srNo`; `:procedureName` ← `DmShared.xNull(mdl.getProcName())`; `:rptName` ← `DmShared.xNull(mdl.getRptName())`; `:fileName` ← `DmShared.xNull(mdl.getFileName())`; `:code` ← `DmShared.foriegnKey(mrTemp.getSharedKeyFieldCode())`; `:vSno` ← `srNo`; `:fileCode` ← `DmShared.xNull(mdl.getImageCode())`; `:ruleCode` ← `DmShared.xNull(mdl.getRuleCode())`
- Errors: "Invalid key." | "Template Name already exists."
- Other calls: `CSV.getSourceName`, `CSV.getSourceType`, `FUNCTION.getSourceType`, `MOBILE.getId`, `ModifyReason_Server.saveNew`, `StaffInfo_Server.saveStaffInfo`, `WHATSAPP.getCode`, `WhatsAppTemplateMast_Vld.chkSave`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `Select wjtm.code, wjtm.name, tgm.name as templateGroupName, wjtm.datasource, wjtm.functionName, wjtm.message from wjTemplateMast as wjtm left join wjTemplateGroupMast as tgm on wjtm.templateGroupCode = tgm.code where templateType = :templateType`
- Other calls: `DmWebJobDataSources.getValueFrom`, `WHATSAPP.getCode`

### `getTxtHUndTempName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHUndTempName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select code, name from wjTemplateGroupMast`

### `getTxtHFunctionName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHFunctionName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT name, definition, type_desc FROM sys.sql_modules m INNER JOIN sys.objects o ON m.object_id=o.object_id WHERE (type_desc like '%function%') and (name like 'wa_%')`

### `getTxtHDataSource`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDataSource(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `CSV.getSourceName`, `CSV.getSourceType`, `FUNCTION.getSourceName`, `FUNCTION.getSourceType`

### `getTxtHSendToNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSendToNature(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `MOBILE.getId`, `SEND_TO_NATURE.values`

### `getSqlDataSource`
`public DmResult<List<GrdFieldName_Mdl>> getSqlDataSource(String functionName)`

- SQL 1: `Select * from {…} ('',0,0) order by uniqueid offset 0 rows fetch next 1 rows only`
- Errors: "Select function name."

### `getReadFile`
`public DmResult<List<GrdFieldName_Mdl>> getReadFile(WhatsAppTemplateMast_Mdl mrTemp)`

- Errors: "File not Found to read."

### `getSaveFile`
`private void getSaveFile(WhatsAppTemplateMast_Mdl mrTemp)`


### `getDeleteFile`
`private void getDeleteFile(String code)`


### `checkField`
`private void checkField(WhatsAppTemplateMast_Mdl mrTemp)`

- Errors: "External Template Is In Incorrect Format. Field Is Not Find OR Did you forget to open or close any curly braces? " | "Message Is In Incorrect Format. Field Is Not Find OR Did you forget to open or close any curly braces? " | "Rpt Name Is In Incorrect Format. Field Is Not Find OR Did you forget to open or close any curly braces? " | "File Name Is In Incorrect Format. Field Is Not Find OR Did you forget to open or close any curly braces? "

### `chkBraces`
`private boolean chkBraces(String rtnVal, WhatsAppTemplateMast_Mdl mrTemp, String getByValue) throws DmException`


### `matchField`
`private boolean matchField(String textToSearch, WhatsAppTemplateMast_Mdl mrTemp, String getByValue) throws DmException`


### `chkValue`
`private boolean chkValue(String rtnVal, WhatsAppTemplateMast_Mdl mrTemp, String getByValue) throws DmException`


### `mandatoryField`
`private void mandatoryField(List<GrdFieldName_Mdl> lstTemp) throws Exception`

- Errors: "The field "

### `exportJson`
`public DmResult<KeyFieldExtended> exportJson(String session, WhatsAppTemplateMast_Mdl mrTemp)`

- Errors: "Invalid request." | "File Name not given."

### `importJson`
`public DmResult<WhatsAppTemplateMast_Mdl> importJson(String filePath, String fileName)`


### `checkImportedValues`
`public DmResult<WhatsAppTemplateMast_Mdl> checkImportedValues(WhatsAppTemplateMast_Mdl mrTemp)`

- SQL 1: `Select wjtgm.code, wjtgm.name from wjTemplateGroupMast as wjtgm where wjtgm.name = :templateGroup`
- SQL 2: `select count(so.name) as cnt from sys.objects so left join sys.sql_modules ssm on so.object_id = ssm.object_id where so.type = 'IF' and so.name like :functionName`
- SQL 3: `select so.name as functionName from sys.objects so left join sys.sql_modules ssm on so.object_id = ssm.object_id where so.type = 'IF' and so.name = :functionName`
- Params: `:templateGroup` ← `DmShared.xNull(mrTemp.getTempGroupName()).trim()`; `:functionName` ← `DmShared.xNull(mrTemp.getFunctionText()).trim() + "%"`; `:functionName` ← `DmShared.xNull(newFunction).trim()`

### `updateImageContentId`
`public DmResult<String> updateImageContentId(String code)`

- SQL 1: `UPDATE linkFileCollection SET remarks = fileLink WHERE docId = :code`
- Params: `:code` ← `DmShared.iNull(code).longValue()`

### `getParsedName`
`private String getParsedName(String nature, String mobile)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.server`, `dataman.dmBase.dmFileManager.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmScheduler.server`, `dataman.dmBase.dmSession.client`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFileManager.client`, `dataman.dmToolkit.dmFileUploadButton.client`, `dataman.dmToolkit.dmFileUploadButton.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextArea.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.server`, `dataman.dmToolkit.modifyReason.shared`, `dataman.libraryMenu.self.shared`, `dataman.webJobs.self.shared`, `dataman.webJobs.staffInfo.client`, `dataman.webJobs.staffInfo.server`, `dataman.webJobs.whatsAppTemplateMast.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (DmShared.iNull(mrTemp.getSharedKeyFieldCode()).longValue() == 0)` | "Invalid key." |
| saveRecord | `if (rsTemp.next())` | "Template Name already exists." |
| getSqlDataSource | `if (functionName.equalsIgnoreCase(""))` | "Select function name." |
| getReadFile | `if (!tmpFile.exists())` | "File not Found to read." |
| checkField | `if (!chkBraces(mrTemp.getExternalTemplate(), mrTemp, "externalTemplate"))` | "External Template Is In Incorrect Format. Field Is Not Find OR Did you forget to open or close any curly braces? " |
| checkField | `if (!chkBraces(mrTemp.getMsg(), mrTemp, "Message"))` | "Message Is In Incorrect Format. Field Is Not Find OR Did you forget to open or close any curly braces? " |
| checkField | `if (!chkBraces(grdEmail.getRptName(), mrTemp, grdEmail.getRptName()))` | "Rpt Name Is In Incorrect Format. Field Is Not Find OR Did you forget to open or close any curly braces? " |
| checkField | `if (!chkBraces(grdEmail.getFileName(), mrTemp, grdEmail.getFileName()))` | "File Name Is In Incorrect Format. Field Is Not Find OR Did you forget to open or close any curly braces? " |
| matchField | `if (!match.find())` | " is not contain any value in braces." |
| chkValue | `if (tempVal.equalsIgnoreCase(""))` | " is not contain any value in braces." |
| mandatoryField | `if (lstTemp.stream().filter(m -> m.getFieldName().equalsIgnoreCase(lstField)).count() == 0)` | "The field " |
| exportJson | `if (!DmSession.getSessions().containsKey(sessionId))` | "Invalid request." |
| exportJson | `if (DmShared.xNull(fileName).equalsIgnoreCase(""))` | "File Name not given." |

## Notes (hand-written)
_No hand-written notes yet._
