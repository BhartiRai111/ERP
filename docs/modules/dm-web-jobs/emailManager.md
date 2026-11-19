# dm-web-jobs / emailManager

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/webJobs/src/emailManager/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHTempName, getHelp
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `DraftPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Template Name | DmTextHelp | `txtHTemplateName` | `TxtHTemp_PrpAcs, 7, true` |

**Grid / popup columns** (header, width, db field):
- `txtHTemplateName`: Template Name [Text, w200, name]; Under Temp [Text, w100, groupName]; Function / File Name [Text, w200, functionName]; Message [Text, w100, message]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHTemplateName` → `getTxtHTempName`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHTemplateName.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHTempName(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Templ
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
```
- Alert: "Template name is mandatory."

### Screen `EmailManager`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Template Name | DmTextBox | `txtTemplateName` | `50, false` |
| 2 | Data Source / Function / File Name | DmTextBox | `txtDataSourceName` | `30, true` |
| 3 | - | DmTextBox | `txtFunFile` | `30, true` |
| 4 | Send Records :  | DmTextBox | `txtSearchValue` | `50, false` |
| 5 | - | DmTextBox | `txtUploadFile` | `30, true` |
| 6 | - | DmFileUploadButton | `btnUpload` | `this, fileRuleType` |

Other widgets (not in tab list): `chkRecordsFilter` (DmCheckBox), `dmEmailGrd` (DmGridPaging), `fileRuleType` (DmFileRuleType)

**Grid / popup columns** (header, width, db field):
- `dmEmailGrd`: ischecked [Text, w25]; send_to_name [Text, w100, Send_To_Name]; send_to_address [Text, w100, Send_To_Address]; send_to_email [Text, w100, Send_To_Email]; send_bcc_email [Text, w100, Send_BCc_EMail]; send_cc_email [Text, w100, Send_Cc_EMail]; subject [Text, w100, Subject]; message [Text, w200, Message]; size [Text, w50, Size]; status [Text, w100, Status]

**Lookup / suggestion / find sources (server RPC called):**
- `chkRecordsFilter` → `getMetaData`
- `dmEmailGrd` → `getData`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtDataSourceName.setEnabled(false)`
- `txtFunFile.setEnabled(false)`
- `txtTemplateName.setEnabled(false)`
- `txtUploadFile.setEnabled(false)`
- `txtSearchValue.setEnabled(flag)`

**`manageDisplay()` logic (condensed code):**
```java
txtDataSourceName.setEnabled(false);
txtFunFile.setEnabled(false);
txtTemplateName.setEnabled(false);
txtUploadFile.setEnabled(false);
txtSearchValue.setEnabled(flag);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`filterPopupData.addCallBack` → `onOk()`
```java
filterDataList = listFilterData;
setHeaderValue(1);
checkSearchValue();
dmEmailGrd.reQuery();
setCheckUncheck(false);
```

`prepareDataPopup.addCallBack` → `onOk()`
```java
clear();
txtTemplateName.setTag(DmShared.xNull(template_Mdl.getSharedKeyFieldCode()));
txtTemplateName.setText(DmShared.xNull(template_Mdl.getSharedKeyFieldName()));
txtDataSourceName.setText(DmShared.xNull(template_Mdl.getWebJobDataSource().getSourceName()));
txtFunFile.setText(DmShared.xNull(template_Mdl.getFuncOrFileName()));
EmailManager.this.templateMdl = template_Mdl;
manageDisplay(true);
getMetadata(template_Mdl);
```

`chkRecordsFilter.addCallBack` → `valueChanged()`
```java
dmEmailGrd.reQuery();
```

`gridEdit.addCallBack` → `onOk()`
```java
dmEmailGrd.getDmGrid().getGrid().getStore().get(rowIndex).getCells().put("message", ddgr.getCells().get("message").toStr
dmEmailGrd.getDmGrid().getGrid().getStore().get(rowIndex).getCells().put("subject", ddgr.getCells().get("subject").toStr
dmEmailGrd.getDmGrid().commitChanges();
setCheckVal(false, false, ddgr);
```

`dmEmailGrd.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC().getData(chkRecordsFilter.getValue(), param, helper, blnToEndOfFile, new AsyncCallback<DmResultPagei
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtTemplateName).show();
public void onSuccess(DmResultPageing<List<DmDynamicGridRow>> result) {
if (result.isStatus()) {
dmEmailGrd.getDmGrid().getGrid().getStore().clear();
dmEmailGrd.setData(result.getData(), result.getPaging());
getCountRecords();
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtTemplateName).show();
...
```

`dmEmailGrd.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
dmEmailGrd.getDmGrid().getGrid().getStore().clear();
dmEmailGrd.setData(result.getData(), result.getPaging());
getCountRecords();
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtTemplateName).show();
```

`dmEmailGrd.addCallBack` → `getParam()`
```java
return valueIntialize();
```
- Confirm: "Email has already been send for this record. Are you sure? You want to resend."
- Confirm: "Are you sure? You want to close."
- Confirm: "Are you sure? You want to send Email to selected records."
- Confirm: "Are you sure? You want to clear the Attachment."
- Alert: "Please select template name to filter data."

### Screen `FilterDataPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `textBoxDate1` (DmTextBox), `textBoxDate2` (DmTextBox), `textHelpMulti` (DmTextHelpMulti), `textBox` (DmTextBox), `textBoxNumeric` (DmTextBoxNumeric)

**Grid / popup columns** (header, width, db field):
- `textHelpMulti`: Name [Text, w0]

**Event handlers (condensed code, CRUD flow and field callbacks):**

`textBoxDate1.addBlurHandler` → `onBlur()`
```java
textBoxDate1.setText(DmClient.returnDate(textBoxDate1.getText()));
```

`textBoxDate2.addBlurHandler` → `onBlur()`
```java
textBoxDate2.setText(DmClient.returnDate(textBoxDate2.getText()));
```

`txtHelp.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getHelp(columnName, tableName, helper, blnToEndOfFile, new AsyncCallback<DmResultPag
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHelp).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHelp.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHelp).show();
```

`txtHelp.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHelp.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHelp).show();
```

### Screen `MessageGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmTextArea | `txtMsgArea` | `` |

## Files
| file | lines |
|---|---|
| client/DraftPopup.java | 143 |
| client/DraftPopup_Dsg.java | 132 |
| client/EmailManager.java | 712 |
| client/EmailManager_Dsg.java | 374 |
| client/FilterDataPopup.java | 467 |
| client/FilterDataPopup_Dsg.java | 71 |
| client/MessageGridEdit.java | 93 |
| client/MessageGridEdit_Dsg.java | 36 |
| server/EmailManager_SrvImpl.java | 1104 |
| shared/DynamicGrid_Mdl.java | 30 |
| shared/EmailManager_Srv.java | 31 |
| shared/EmailManager_SrvAsync.java | 28 |
| shared/FilterDataPopup_Mdl.java | 86 |
| shared/SearchValue_Mdl.java | 64 |
| shared/Template_Mdl.java | 78 |

## UI labels / columns (client)
`Template Name`, `Drafted Records : `, `Filtered Records : `, `Data Source / Function / File Name`, `Selected Records : `, `Send Records : `

## Models (shared DTOs)
### DynamicGrid_Mdl
| type | field |
|---|---|
| List<DmDynamicGridRow> | dynamicGridRowList |
| List<String> | columnNameList |
| List<FilterDataPopup_Mdl> | filterData_Mdls |

### FilterDataPopup_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | columnName |
| String | dataType |
| String | helpTag |
| String | helpText |
| String | valueFrom |
| String | valueTo |
| String | contains |
| String | isChecked |

### SearchValue_Mdl
| type | field |
|---|---|
| String | funOrFileName |
| String | templateId |
| String | messageTemplate |
| String | subjectTemplate |
| String | sessionTable |
| List<FilterDataPopup_Mdl> | filterDataList |

### Template_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | templateGroupText |
| String | templateGroupTag |
| String | funcOrFileName |
| String | messageTemplate |
| String | subjectTemplate |
| String | sessionTable |
| DmWebJobDataSources | webJobDataSource |

## RPC interface
| method | params | returns |
|---|---|---|
| sendEmail | Template_Mdl templateMdl, DmIFile_Mdl file | DmResult<String> |
| getTxtHTempName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<Template_Mdl>> |
| getMetaData | Template_Mdl info_Mdl | DmResult<DynamicGrid_Mdl> |
| getCountRecords | Template_Mdl template_Mdl | DmResult<KeyFieldExtended> |
| getData | boolean isCheckedRecord, SearchValue_Mdl mdl, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<DmDynamicGridRow>> |
| updateRecord | Template_Mdl templateMdl, DmDynamicGridRow ddgr, boolean isCheckAll, boolean isCheckChange | DmResult<String> |
| getHelp | String columnName, String tableName, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Server `EmailManager_SrvImpl.java` (1104 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getMdbCollection`, `DmServer.getMdbTmpCollectionName`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnVoucherPrefix`, `DmShared.iNull`, `DmShared.xNull`

### `sendEmail`
`public DmResult<String> sendEmail(Template_Mdl templateMdl, DmIFile_Mdl file)`

- Errors: "No record selected to send eMail."

### `getTxtHTempName`
`public DmResultPageing<List<Template_Mdl>> getTxtHTempName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT wtm.code, wtm.name, wtm.message, wtm.templateGroupCode, wtg.name AS groupName, wtm.dataSource, wtm.functionName, wtm.subject FROM wjTemplateMast AS wtm LEFT JOIN wjTemplateGroupMast AS wtg ON wtg.code = wtm.templateGroupCode WHERE (wtm.templateType = :templateType)`
- Other calls: `DmWebJobDataSources.getValueFrom`, `EMAIL.getCode`

### `getMetaData`
`public DmResult<DynamicGrid_Mdl> getMetaData(Template_Mdl templateMdl)`

- Other calls: `CSV.getSourceType`

### `getData`
`public DmResultPageing<List<DmDynamicGridRow>> getData(boolean isCheckedRecord, SearchValue_Mdl mdl, DmHelper helper, boolean blnToEndOfFile)`


### `getDefaultColumns`
`private List<String> getDefaultColumns()`


### `getCsv`
`private void getCsv(DynamicGrid_Mdl mrTemp, Template_Mdl templateMdl, Connection cnTemp) throws IOException, Exception`

- SQL 1: `Select * from {…} (cast(' {…} ' as varchar),`
- Errors: "File not found."
- Other calls: `DmMapFormat.format`

### `getSql`
`private void getSql(DynamicGrid_Mdl mrTemp, Template_Mdl templateMdl, String siteCode, Connection cnTemp) throws IOException, Exception`

- SQL 1: `SELECT * FROM {…} (:templateId, :v_Prefix, :siteCode)`
- Params: `:templateId` ← `DmShared.iNull(templateMdl.getSharedKeyFieldCode()).toString()`; `:v_Prefix` ← `DmShared.iNull(DmServer.getSsnVoucherPrefix(this)).intValue()`; `:siteCode` ← `DmShared.iNull(siteCode).intValue()`
- Other calls: `DmMapFormat.format`

### `updateRecord`
`public DmResult<String> updateRecord(Template_Mdl templateMdl, DmDynamicGridRow ddgr, boolean isCheckAll, boolean isCheckChange)`


### `getHelp`
`public DmResultPageing<List<KeyFieldExtended>> getHelp(String columnName, String tableName, DmHelper helper, boolean blnToEndOfFile)`


### `getCountRecords`
`public DmResult<KeyFieldExtended> getCountRecords(Template_Mdl template_Mdl)`


### `getselectedRecords`
`private int getselectedRecords(DBCollection collection)`


### `getfilteredRecords`
`private int getfilteredRecords(DBCollection collection)`


### `getPrepareRecords`
`private int getPrepareRecords(DBCollection collection)`


### `getSendRecords`
`private int getSendRecords(DBCollection collection)`


### `getCaseWhenIsChecked`
`private int getCaseWhenIsChecked(DBCollection collection, String uniqueId)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmFileManager.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmScheduler.server`, `dataman.dmBase.dmScheduler.shared`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmCheckBox.client`, `dataman.dmToolkit.dmFileUploadButton.client`, `dataman.dmToolkit.dmFileUploadButton.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextArea.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.self.client`, `dataman.libraryMenu.self.shared`, `dataman.webJobs.emailManager.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| sendEmail | `if (count <= 0)` | "No record selected to send eMail." |
| getCsv | `if (!uploadedFile.exists())` | "File not found." |

## Notes (hand-written)
_No hand-written notes yet._
