# dm-utility / guiConfig

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/utility/src/guiConfig/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: guiConfig, guiConfigApplicableTo
- Update tables: guiConfig
- Delete cascade (in order): guiconfigapplicableto, guiconfigdetail, guiconfigdetailhelp, guiconfig
- Duplicate checks on save:
  - `select gcd.saveInfieldName,max(gcd.guiRowNo) guiRowNo from guiconfigdetail gcd where gcd.code = :code and isNull(gcd.saveInfieldName,'') <> '' group by gcd.saveInfieldName having count(*) > 1`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: GUI Name (Text); Template Name (Text); Table Name (Text); SQL Query (Text); rule: Field Data Type is mandatory for Row No. ; rule: Please define Caption or Control width % for Row No. ; rule: Sum of Caption and Control width % must be less than or greater than 100 for Row No. ; rule: Is Mandatory is mandatory for Row No. ; rule: Copy is mandatory for Row No. ; rule: Custom Field Name already exists for Row No. ; rule: Row No. could not be conflict for Row No. ; rule: GUI Column Detail is mandatory for Row No. ; rule: GUI Row Detail is mandatory.
- Lookups/suggestions: getTxtHDefault, getTxtHSite, getTxtHTemplate, getTxtHTable, getTxtHSaveInField
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `ColumnGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | S.No. | DmTextBoxNumeric | `txtSNo` | `0, false, 3, true` |
| 2 | Save In Field | DmTextHelp | `txtHSaveInField` | `KeyField_PrpAcs, 10, false` |
| 3 | Field Caption | DmTextBox | `txtFieldCaption` | `250, false` |
| 4 | Field Data Type | DmTextHelp | `txtHDataType` | `KeyField_PrpAcs, 10, true, true` |
| 5 | Caption Width % | DmTextBoxNumeric | `txtCaptionWidth` | `2, false, 3, false` |
| 6 | Control Width % | DmTextBoxNumeric | `txtControlWidth` | `2, false, 3, false` |
| 7 | Custom Field Name | DmTextBox | `txtCustomFieldName` | `30, false` |
| 8 | Caption Style | DmTextBox | `txtCaptionStyle` | `250, false` |
| 9 | Control Style | DmTextBox | `txtControlStyle` | `250, false` |
| 10 | Max Length | DmTextBoxNumeric | `txtMaxLength` | `0, false, 4, false` |
| 11 | Allow Decimal | DmTextBoxNumeric | `txtDecimal` | `0, false, 1, false` |
| 12 | Is Mandatory | DmTextHelp | `txtHMandatory` | `KeyField_PrpAcs, 3, true, true` |
| 13 | Copy | DmTextHelp | `txtHCopy` | `KeyField_PrpAcs, 3, true, true` |
| 14 | Allow Negative | DmTextHelp | `txtHAllowNegative` | `KeyField_PrpAcs, 4, true, false` |
| 15 | View Field Code | DmTextHelp | `txtHViewCode` | `KeyField_PrpAcs, 10, false` |
| 16 | View Field Text | DmTextHelp | `txtHViewText` | `KeyField_PrpAcs, 10, false` |
| 17 | Default Code | DmTextBox | `txtDefaultCode` | `50, false` |
| 18 | Default Text | DmTextBox | `txtDefaultText` | `50, false` |
| 19 | Help Query | DmTextBox | `txtQuery` | `` |
| 20 | Help Width | DmTextBoxNumeric | `txtHelpWidth` | `2, false, 3, false` |
| 21 | Help Row/Page | DmTextBoxNumeric | `txtHelpRow` | `0, false, 2, false` |
| 22 | Field Name | DmTextBox | `txtFieldName` | `50, false` |

**Grid / popup columns** (header, width, db field):
- `txtHMandatory`: Name [Text, w100, Name]
- `txtHCopy`: Name [Text, w100, Name]
- `txtHAllowNegative`: Name [Text, w100, Name]
- `txtHDataType`: Data Type [Text, w0, Data Type]
- `txtHSaveInField`: Column [Text, w10, columnName]
- `txtHViewCode`: Column [Text, w10, Name]
- `txtHViewText`: Column [Text, w10, Name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHSaveInField` → `getTxtHSaveInField`
- `txtHDataType` → `getDataType`
- `txtHMandatory` → `getYesNo`
- `txtHCopy` → `getCopy`
- `txtHAllowNegative` → `getYesNo`
- `txtHViewCode` → `getViewFieldCode`
- `txtHViewText` → `getViewFieldCode`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHSaveInField.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHSaveInField(helper, blnToEndOfFile, DmShared.xNull(config.txtHTable.getTag())
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSaveInField).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHSaveInField.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSaveInField).show();
```

`txtHSaveInField.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSaveInField.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSaveInField).show();
```

`txtHSaveInField.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
txtMaxLength.setText(DmClient.vNull(t1Row.getSharedModifiedBy(), "0"));
txtDefaultText.setMaxLength(DmShared.iNull(txtMaxLength.getText()).intValue() > 50 ? DmShared.iNull(txtMaxLength.getText
```

`txtHDataType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getDataType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDataType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHDataType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDataType).show();
```

`txtHDataType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHDataType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDataType).show();
```

`txtHDataType.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
manageOnDataType(DmShared.iNull(txtHDataType.getTag()).intValue());
if (DmShared.iNull(txtHDataType.getTag()).intValue() == DmDataType.CUSTOM.toInt()) {
manageCustomDisplay(true);
} else {
manageCustomDisplay(false);
```

`txtHMandatory.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExte
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHMandatory).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHMandatory.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHMandatory).show();
```

`txtHMandatory.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHMandatory.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHMandatory).show();
```

`txtHCopy.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getCopy(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExten
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHCopy).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHCopy.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCopy).show();
```

`txtHCopy.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHCopy.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCopy).show();
```

`txtHAllowNegative.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExte
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHAllowNegative).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHAllowNegative.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAllowNegative).show();
```

`txtHAllowNegative.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAllowNegative.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAllowNegative).show();
```

`txtHViewCode.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getViewFieldCode(helper, blnToEndOfFile, DmShared.xNull(config.txtQuery.getText()), 
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHViewCode).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHViewCode.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHViewCode).show();
```

`txtHViewCode.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHViewCode.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHViewCode).show();
```

`txtHViewText.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getViewFieldCode(helper, blnToEndOfFile, DmShared.xNull(config.txtQuery.getText()), 
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHViewText).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHViewText.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHViewText).show();
```

`txtHViewText.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHViewText.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHViewText).show();
```

`btnQuery.addClickHandler` → `onClick()`
```java
try {
setting.show(true, btnQuery, ColumnGridEdit.this, DmShared.xNull(txtQuery.getText()));
} catch (Exception e) {
```

`setting.addCallBack` → `onOk()`
```java
txtQuery.setText(sqlQuery);
if (!DmShared.xNull(sqlQuery).equalsIgnoreCase("")) {
manageHelp(true);
} else {
manageHelp(false);
```
- Alert: "Caption & Control combination's length could not be greater than 100"
- Alert: "Column S.No. is mandatory."
- Alert: "Save In Field is mandatory."
- Alert: "Field Data Type is mandatory."
- Alert: "Please define Caption or Control width."
- Alert: "Captions & Controls length is greater than 100."
- Alert: "Custom Field Name is mandatory."
- Alert: "Is Mandatory is mandatory."
- Alert: "Copy is mandatory."
- Alert: "Field Name already exists."
- Alert: "Custom Field Name already exists."
- Alert: "Length could not be greater than 100"
- Alert: "Length could not be greater than 100"
- Alert: "Decimal value not allow upto 7 digits."

### Screen `GUIConfig`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | GUI Name | DmTextBox | `txtName` | `50, true` |
| 2 | Template Name | DmTextHelp | `txtHTemplate` | `KeyField_PrpAcs, 10, true` |
| 3 | Table Name | DmTextHelp | `txtHTable` | `KeyField_PrpAcs, 10, true` |
| 4 | Report Prefix | DmTextBox | `txtReportPrefix` | `10, false` |
| 5 | SQL Query | DmTextBox | `txtQuery` | `1000, true` |

Other widgets (not in tab list): `dmFind` (DmFind), `grdApplicableSite` (DmGrid), `grdDescription` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHTable`: Table [Text, w150, name]
- `txtHTemplate`: Name [Text, w150, Name]
- `dmFind`: GUI Name [Text, w300, name]; Template Name [Text, w120, guiTemplateName]; Table Name [Text, w150, tableName]; Report Name Prefix [Text, w150, reportNamePrefix]
- `grdApplicableSite`: Applicable To Site [Text, w285]
- `grdDescription`: Row No. [Text, w70]; Description [Text, w800]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHTable` → `getTxtHTable`
- `txtHTemplate` → `getTxtHTemplate`
- `setting` → `getFind`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtName.getText())
- `SqlQuery` ← DmShared.xNull(txtQuery.getText())
- `TableName` ← DmShared.xNull(txtHTable.getTag())
- `TemplateCode` ← DmShared.xNull(txtHTemplate.getTag())
- `TemplateName` ← DmShared.xNull(txtHTemplate.getText())
- `ReportPrefix` ← DmShared.xNull(txtReportPrefix.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtName.setText` ← data.getSharedKeyFieldName()
- `txtHTable.setTag` ← data.getTableName()
- `txtHTable.setText` ← data.getTableName()
- `txtQuery.setText` ← data.getSqlQuery()
- `txtReportPrefix.setText` ← data.getReportPrefix()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `txtHTemplate.setTag` ← data.getTemplateCode()
- `txtHTemplate.setText` ← data.getTemplateName()
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtQuery.setEnabled(false)`

**On New click:**
- `onAdd()`
- `txtName.setFocus(true)`

**`manageDisplay()` logic (condensed code):**
```java
txtQuery.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
onAdd();
txtName.setFocus(true);
```

`topControl.addEditHandeller` → `editClicked()`
```java
manageDisplay();
grdApplicableSite.getGrid().getStore().add(new KeyFieldExtended());
grdDescription.getGrid().getStore().add(new GUIRow_Mdl());
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

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(GUIConfig.this);
```

`importPopup.addCallBack` → `onYes()`
```java
topControlMoveRecSuccess(mdl, true);
```

`txtHTable.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHTable(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyField
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHTable).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHTable.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTable).show();
```

`txtHTable.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHTable.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTable).show();
```

`txtHTemplate.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHTemplate(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHTemplate).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHTemplate.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTemplate).show();
```

`txtHTemplate.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHTemplate.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTemplate).show();
```

`setting.addCallBack` → `onOk()`
```java
txtQuery.setText(sqlQuery);
```

`dmFind.addHelpGridCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getFind(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Find_Mdl>>>()
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<Find_Mdl>> result) {
if (result.isStatus() && result.getData() != null) {
dmFind.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`dmFind.addHelpGridCallBack` → `onSuccess()`
```java
if (result.isStatus() && result.getData() != null) {
dmFind.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`dmFind.addHelpGridCallBack` → `getSelectedRow()`
```java
flwForm.remove(dmFind);
flwForm.add(vrtInnerForm);
if (blnStatus && tRow != null) {
topControlMoveRec(tRow.getSharedKeyFieldCode());
```

`siteGridEdit.addCallBack` → `onOk()`
```java
grdApplicableSite.commitChanges();
```

`siteGridEdit.addCallBack` → `onDelete()`
```java
grdApplicableSite.commitChanges();
```

`rowGridEdit.addCallBack` → `onOk()`
```java
grdDescription.commitChanges();
```

`rowGridEdit.addCallBack` → `onDelete()`
```java
grdDescription.commitChanges();
```

**Top control options:** setRefreshRequired(false), setDisplay(true)

_Print permission "P" is removed for this screen (replace "P" with "*")._
- Confirm: "Are you sure? You want to delete this record."
- Confirm: "Are you sure? You want to export record."
- Alert: "Please enter SQL query!"
- Alert: "Please select Template Name."

### Screen `ImportPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `fileRuleType` (DmFileRuleType), `btnUpload` (DmFileUploadButton)

### Screen `RowGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Row No. | DmTextBoxNumeric | `txtRowNo` | `0, false, 3, true` |

Other widgets (not in tab list): `grdColumn` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `grdColumn`: S.No. [Text, w35]; Save in Field [Text, w100]; Field Caption [Text, w120]; Data Type [Text, w65]; Default Text [Text, w75]; Default Code [Text, w75]; Caption Width [Text, w75]; Control Width [Text, w75]; Allow Decimal [Text, w50]; Allow Negative [Text, w50]; View Field Code [Text, w75]; View Field Text [Text, w75]; Max Length [Text, w50]; Mandatory [Text, w50]

**Event handlers (condensed code, CRUD flow and field callbacks):**

`columnGridEdit.addCallBack` → `onOk()`
```java
grdColumn.commitChanges();
```

`columnGridEdit.addCallBack` → `onDelete()`
```java
grdColumn.commitChanges();
```
- Alert: "Row No. is mandatory."
- Alert: "Column No. could not be conflict."
- Alert: "Captions & Controls length is greater then 100."

### Screen `Setting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmTextArea | `txtQuery` | `8000, false` |

### Screen `SiteGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Site | DmTextHelp | `txtHSite` | `TxtHSite_PrpAcs, 4, true` |

**Grid / popup columns** (header, width, db field):
- `txtHSite`: Site Name [Text, w0, name]; Short Name [Text, w85, manualCode]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHSite` → `getTxtHSite`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHSite.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHSite(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSite).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHSite.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSite).show();
```

`txtHSite.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSite.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSite).show();
```
- Alert: "Site is mandatory."

## Files
| file | lines |
|---|---|
| client/ColumnGridEdit.java | 593 |
| client/ColumnGridEdit_Dsg.java | 452 |
| client/GUIConfig.java | 643 |
| client/GUIConfig_Dsg.java | 321 |
| client/ImportPopup.java | 152 |
| client/ImportPopup_Dsg.java | 199 |
| client/RowGridEdit.java | 194 |
| client/RowGridEdit_Dsg.java | 138 |
| client/Setting.java | 91 |
| client/Setting_Dsg.java | 80 |
| client/SiteGridEdit.java | 139 |
| client/SiteGridEdit_Dsg.java | 69 |
| server/GUIConfig_SrvImpl.java | 1220 |
| shared/Find_Mdl.java | 38 |
| shared/GUIColumn_Mdl.java | 251 |
| shared/GUIConfig_Mdl.java | 76 |
| shared/GUIConfig_Srv.java | 43 |
| shared/GUIConfig_SrvAsync.java | 44 |
| shared/GUIConfig_Vld.java | 101 |
| shared/GUIHelp_Mdl.java | 55 |
| shared/GUIRow_Mdl.java | 57 |

## UI labels / columns (client)
`S.No.`, `Save In Field`, `Field Caption`, `Field Data Type`, `Caption Width %`, `Control Width %`, `Custom Field Name`, `Caption Style`, `Control Style`, `Max Length`, `Allow Decimal`, `Is Mandatory`, `Copy`, `Allow Negative`, `View Field Code`, `View Field Text`, `Default Code`, `Default Text`, `Help Query`, `Help Width`, `Help Row/Page`, `Field Name`, `GUI Name`, `Template Name`, `Table Name`, `Report Prefix`, `SQL Query`, `File Name`, `Message`, `Site`, `Row No.`

## Models (shared DTOs)
### GUIRow_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String srNo, | description |
| List<GUIColumn_Mdl> | guiCol |

### GUIColumn_Mdl (extends `KeyField implements Serializable, IsSerializable`)
### GUIConfig_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | tempKeyCode |
| String templateCode, templateName, tableName, | sqlQuery |
| String | reportPrefix |
| List<GUIRow_Mdl> | grdData |
| List<KeyFieldExtended> | grdSite |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String templateName, tableName, | reportPrefix |

### GUIHelp_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String vSNo, fieldWidth, fieldAlignment, guiRowNo, | sno |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<GUIConfig_Mdl> |
| saveRecord | GUIConfig_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey | DmResult<String> |
| getTempKeyCode |  | String |
| getFind | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getTxtHDefault | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTable | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDataType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTemplate | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getYesNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getCopy | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSaveInField | DmHelper helper, boolean blnToEndOfFile, String tableName | DmResultPageing<List<KeyFieldExtended>> |
| getViewFieldCode | DmHelper helper, boolean blnToEndOfFile, String query | DmResultPageing<List<KeyFieldExtended>> |
| exportJson | String sessionId, GUIConfig_Mdl mrTemp | DmResult<KeyFieldExtended> |
| importJson | String filePath, String fileName | DmResult<GUIConfig_Mdl> |

## Validator `GUIConfig_Vld`
```java
public class GUIConfig_Vld {
    public static void chkSave(GUIConfig_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getSharedKeyFieldName(), "GUI Name", 100);
        DmShared.isValidText(mrTemp.getTemplateCode(), "Template Name", 200);
        DmShared.isValidText(mrTemp.getTableName(), "Table Name", 300);
        DmShared.isValidText(mrTemp.getSqlQuery(), "SQL Query", 400);
        boolean isRowExists = false, isColumnExists = false;
        if (mrTemp.getGrdData() != null) {
            for (int i = 0; i < mrTemp.getGrdData().size(); i++) {
                GUIRow_Mdl row = mrTemp.getGrdData().get(i);
                if (DmShared.iNull(row.getSrNo()).intValue() != 0) {
                    isRowExists = true;
                    isColumnExists = false;
                    if (row.getGuiCol() != null) {
                        for (GUIColumn_Mdl column : row.getGuiCol()) {
                            if (DmShared.iNull(column.getSharedKeyFieldCode()).intValue() != 0) {
                                isColumnExists = true;
                                if (DmShared.xNull(column.getFieldDataTypeTag()).trim().equalsIgnoreCase("")) {
                                    throw new DmException(0, "Field Data Type is mandatory for Row No. " + DmShared.xNull(row.getSrNo()) + " and Sr. No. " + DmShared.xNull(column.getSharedKeyFieldCode()) + ".");
                                }
                                if (DmShared.vNull(column.getCaptionWidth()).doubleValue() == 0 && DmShared.vNull(column.getControlWidth()).doubleValue() == 0) {
                                    throw new DmException(0, "Please define Caption or Control width % for Row No. " + DmShared.xNull(row.getSrNo()) + " and Sr. No. " + DmShared.xNull(column.getSharedKeyFieldCode()) + ".");
                                }
                                if ((DmShared.vNull(column.getCaptionWidth()).doubleValue() + DmShared.vNull(column.getControlWidth()).doubleValue()) > 100.0) {
                                    throw new DmException(0, "Sum of Caption and Control width % must be less than or greater than 100 for Row No. " + DmShared.xNull(row.getSrNo()) + " and Sr. No. " + DmShared.xNull(column.getSharedKeyFieldCode()) + ".");
                                }
                                if (DmShared.xNull(column.getMandatoryTag()).trim().equalsIgnoreCase("")) {
                                    throw new DmException(0, "Is Mandatory is mandatory for Row No. " + DmShared.xNull(row.getSrNo()) + " and Sr. No. " + DmShared.xNull(column.getSharedKeyFieldCode()) + ".");
                                }
                                if (DmShared.xNull(column.getIsCopyTag()).trim().equalsIgnoreCase("")) {
                                    throw new DmException(0, "Copy is mandatory for Row No. " + DmShared.xNull(row.getSrNo()) + " and Sr. No. " + DmShared.xNull(column.getSharedKeyFieldCode()) + ".");
                                }
                                for (GUIColumn_Mdl columnOld : row.getGuiCol()) {
                                    if (DmShared.iNull(columnOld.getSharedKeyFieldCode()).intValue() != 0) {
                                        if (column.getSharedKeyFieldHashCode() != columnOld.getSharedKeyFieldHashCode()) {
                                            if (!DmShared.xNull(column.getCustomFieldName()).trim().equalsIgnoreCase("")) {
                                                if (DmShared.xNull(column.getCustomFieldName()).trim().equalsIgnoreCase(DmShared.xNull(columnOld.getCustomFieldName()).trim())) {
                                                    throw new DmException(0, "Custom Field Name already exists for Row No. " + DmShared.xNull(row.getSrNo()) + " and Sr. No. " + DmShared.xNull(column.getSharedKeyFieldCode()) + ".");
                                                }
                                            }
                                        }
                                    }
                                }
                                for (int j = 0; j < mrTemp.getGrdData().size(); j++) {
                                    GUIRow_Mdl row_Mdl = mrTemp.getGrdData().get(j);
                                    if (DmShared.iNull(row_Mdl.getSrNo()).intValue() != 0) {
                                        if (i != j) {
                                            if (DmShared.iNull(row.getSrNo()).intValue() == DmShared.iNull(row_Mdl.getSrNo()).intValue()) {
                                                throw new DmException(0, "Row No. could not be conflict for Row No. " + (i + 1) + " and " + (j + 1) + ".");
                                            }
                                            for (GUIColumn_Mdl column_Mdl : row_Mdl.getGuiCol()) {
                                                if (DmShared.iNull(column_Mdl.getSharedKeyFieldCode()).intValue() != 0) {
                                                    if (!DmShared.xNull(column_Mdl.getCustomFieldName()).trim().equalsIgnoreCase("")) {
                                                        if (DmShared.xNull(column.getCustomFieldName()).trim().equalsIgnoreCase(DmShared.xNull(column_Mdl.getCustomFieldName()).trim())) {
                                                            throw new DmException(0, "Custom Field Name already exists for Row No. " + DmShared.xNull(row_Mdl.getSrNo()) + " and Sr. No. " + DmShared.xNull(column_Mdl.getSharedKeyFieldCode()) + ".");
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!isColumnExists) {
                        throw new DmException(0, "GUI Column Detail is mandatory for Row No. " + DmShared.xNull(row.getSrNo()) + ".");
                    }
                }
            }
        }
        if (!isRowExists) {
            throw new DmException(0, "GUI Row Detail is mandatory.");
        }
    }
}
```

## Server `GUIConfig_SrvImpl.java` (1220 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnPLTCode`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.vNull`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `select gc.code from guiConfig gc order by gc.name`

### `getMoveRec`
`public DmResult<GUIConfig_Mdl> getMoveRec(String strKey)`


### `getMoveRec`
`private GUIConfig_Mdl getMoveRec(String strKey, Connection cnTemp)`

- SQL 1: `SELECT gc.code, gc.name, gc.tableName, gc.sqlQuery, gc.preparedBy, gc.u_Name, gat.guiTemplateCode, gtm.Name as guiTemplateName, gc.reportNamePrefix FROM guiConfig AS gc LEFT JOIN guiconfigapplicableto AS gat ON gc.code = gat.code LEFT JOIN guiTemplateMast AS gtm ON gat.guiTemplateCode = gtm.code WHERE gc.code = :code`
- SQL 2: `select gat.site_code, sm.name, sm.manualCode from guiConfigApplicableTo AS gat left join sitemast AS sm ON gat.site_code = sm.code where gat.code = :code order by gat.code, gat.site_Code`
- SQL 3: `select gcd.code, gcd.v_SNo, gcd.guiRowNo, gcd.saveInfieldName, gcd.fieldCaption, gcd.fieldDataType, gcd.isMandatory, isNull(gcd.isCopy,1) as isCopy, gcd.maxLength, gcd.captionWidth, gcd.controlWidth, gcd.isAllowNegative, gcd.allowDecimalPlaces, gcd.defaultText, gcd.defaultCode, gcd.viewFieldCode, gcd.viewFieldText, gcd.helpSqlQuery, gcd.helpWidth, gcd.helpRowPerPage, gcd.customFieldName, gcd.captionStyle, gcd.controlStyle, gcd.fieldName from guiConfigDetail gcd where gcd.code = :code order by gcd.guiRowNo, gcd.v_SNo`
- Params: `:code` ← `DmShared.iNull(strKey).toString()`; `:code` ← `DmShared.iNull(strKey).toString()`; `:code` ← `DmShared.iNull(strKey).toString()`

### `fillColumnGrid`
`private GUIColumn_Mdl fillColumnGrid(ResultSet rsTemp) throws Exception`

- Other calls: `DmDataType.valueOf`

### `saveRecord`
`public DmResult<String> saveRecord(GUIConfig_Mdl mrTemp)`

- SQL 1: `Select code from guiConfig where code <> :code and name = :name`
- SQL 2: `Select code from guiConfigApplicableTo where guiTemplateCode = :guiTemplateCode and site_code = :site_code and code <> :code`
- SQL 3: `Select code from guiConfigApplicableTo where guiTemplateCode = :guiTemplateCode and site_code = :site_code and code <> :code`
- SQL 4: `Insert into guiConfig (code, name, tableName, sqlQuery, preparedBy, u_EntDt, reportNamePrefix) values (:code, :name, :tableName, :sqlQuery, :preparedBy, :u_EntDt, :reportNamePrefix) ;`
- SQL 5: `Update guiConfig set code = :code, name = :name, tableName = :tableName, sqlQuery = :sqlQuery, reportNamePrefix = :reportNamePrefix, u_Name = :u_Name, u_EntDt = :u_EntDt, transferDt = NULL where code = :code`
- SQL 6: `Delete from guiConfigApplicableTo where code = :code`
- SQL 7: `Delete from guiConfigDetail where code = :code`
- SQL 8: `Insert into guiConfigApplicableTo (code, guiTemplateCode, site_code) values (:code, :guiTemplateCode, :site_code)`
- SQL 9: `Select gcd.saveInfieldName from guiConfigDetail as gcd left join ( Select sc.name from {…} .sys.columns AS sc right outer join {…} .sys.objects as so on so.object_id = sc.object_id left outer join {…} .sys.types as st on sc.user_type_id = st.user_type_id where (so.name = :tableName) ) as col on gcd.saveInfieldName = col.name where isNull(gcd.saveInfieldName,'') <> '' and isNull(col.name,'') = '' and gcd.code = :code`
- SQL 10: `Select gcd.viewFieldCode,gcd.viewFieldText from guiconfigdetail gcd where gcd.code = :code`
- SQL 11: `select gcd.saveInfieldName,max(gcd.guiRowNo) guiRowNo from guiconfigdetail gcd where gcd.code = :code and isNull(gcd.saveInfieldName,'') <> '' group by gcd.saveInfieldName having count(*) > 1`
- Params: `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName()).trim()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:guiTemplateCode` ← `DmShared.iNull(mrTemp.getTemplateCode()).toString()`; `:site_code` ← `DmShared.iNull(kf.getSharedKeyFieldCode()).toString()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:guiTemplateCode` ← `DmShared.iNull(mrTemp.getTemplateCode()).toString()`; `:site_code` ← `DmServer.getSsnSiteCode(this)`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName()).trim()`; `:tableName` ← `DmShared.xNull(mrTemp.getTableName()).trim()`; `:sqlQuery` ← `DmShared.xNull(mrTemp.getSqlQuery()).trim()`; `:reportNamePrefix` ← `DmShared.xNull(mrTemp.getReportPrefix())`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `DmServer.getServerTimestamp()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName()).trim()`; `:tableName` ← `DmShared.xNull(mrTemp.getTableName()).trim()`; `:sqlQuery` ← `DmShared.xNull(mrTemp.getSqlQuery()).trim()`; `:reportNamePrefix` ← `DmShared.xNull(mrTemp.getReportPrefix())`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `DmServer.getServerTimestamp()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:guiTemplateCode` ← `DmShared.foriegnKey(mrTemp.getTemplateCode())`; `:site_code` ← `DmShared.foriegnKey(lstSite.getSharedKeyFieldCode())`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:guiTemplateCode` ← `DmShared.foriegnKey(mrTemp.getTemplateCode())`; `:site_code` ← `DmServer.getSsnSiteCode(this)`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_SNo` ← `DmServer.vNull(colMdl.getSharedKeyFieldCode(), "0")`; `:guiRowNo` ← `DmServer.vNull(rowMdl.getSrNo(), "0")`; `:saveInfieldName` ← `DmShared.xNull(colMdl.getSharedKeyFieldName())`; `:fieldCaption` ← `DmShared.xNull(colMdl.getFieldCaption())`; `:fieldDataType` ← `DmServer.vNull(colMdl.getFieldDataTypeTag(), "0")`; `:maxLength` ← `DmServer.vNull(colMdl.getMaxLength(), "0")`; `:captionWidth` ← `DmServer.vNull(colMdl.getCaptionWidth(), "0")`; `:controlWidth` ← `DmServer.vNull(colMdl.getControlWidth(), "0")`; `:isMandatory` ← `DmShared.iNull(colMdl.getMandatoryTag()).toString()`; `:isCopy` ← `DmShared.iNull(colMdl.getIsCopyTag()).toString()`; `:isAllowNegative` ← `DmShared.iNull(colMdl.getNegativeTag()).intValue() <= 0 ? "0" : "1"`; `:allowDecimalPlaces` ← `DmServer.vNull(colMdl.getDecimalText(), "0")`; `:isAllowNegative` ← `null`; `:allowDecimalPlaces` ← `null`; `:defaultText` ← `DmShared.xNull(colMdl.getDefaultText())`; `:defaultCode` ← `DmShared.xNull(colMdl.getDefaultCode())`; `:viewFieldCode` ← `DmShared.xNull(colMdl.getViewFieldCode())`; `:viewFieldText` ← `DmShared.xNull(colMdl.getViewFieldText())`; `:helpSqlQuery` ← `null`; `:helpWidth` ← `null`; `:helpRowPerPage` ← `null`; `:helpSqlQuery` ← `DmShared.xNull(colMdl.getHelpQuery())`; `:helpWidth` ← `DmServer.vNull(colMdl.getHelpWidth(), "0")`; `:helpRowPerPage` ← `DmServer.vNull(colMdl.getHelpRow(), "0")`; `:customFieldName` ← `DmShared.xNull(colMdl.getCustomFieldName())`; `:captionStyle` ← `DmShared.xNull(colMdl.getCaptionStyle())`; `:controlStyle` ← `DmShared.xNull(colMdl.getControlStyle())`; `:fieldName` ← `DmShared.xNull(colMdl.getFieldName()).trim()`; `:u_EntDt` ← `DmServer.getServerTimestamp()`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:transferDt` ← `null`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:tableName` ← `mrTemp.getTableName()`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:code` ← `mrTemp.getSharedKeyFieldCode()`
- Errors: "Invalid key." | "GUI Name already exists." | "Record already exists on " | "Save In Field [" | "View Field Text [" | "View Field Code ["
- Other calls: `DmFileManager_Server.manageLinkFileCollection`, `GUIConfig_Vld.chkSave`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey)`

- SQL 1: `DELETE FROM guiconfigapplicableto WHERE code= :code`
- SQL 2: `DELETE FROM guiconfigdetail WHERE code= :code`
- SQL 3: `DELETE FROM guiconfigdetailhelp WHERE code= :code`
- SQL 4: `DELETE FROM guiconfig WHERE code= :code`
- Params: `:code` ← `strKey`; `:code` ← `strKey`; `:code` ← `strKey`; `:code` ← `strKey`

### `getTxtHDefault`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDefault(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code, sm.name, sm.manualCode FROM sitemast sm WHERE sm.pltCode = :pltCode`
- Params: `:pltCode` ← `DmServer.getSsnPLTCode(this)`

### `getTxtHTemplate`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTemplate(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT Code,Name FROM guiTemplateMast`

### `getTxtHTable`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTable(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT so.name FROM {…} .sys.objects so WHERE so.Type = 'U'`

### `getTxtHSaveInField`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSaveInField(DmHelper helper, boolean blnToEndOfFile, String tableName)`

- SQL 1: `SELECT sc.name AS columnName, st.name AS dataType, sc.max_length FROM sys.columns AS sc RIGHT OUTER JOIN {…} .sys.objects AS so ON so.object_id = sc.object_id LEFT OUTER JOIN {…} .sys.types AS st ON sc.user_type_id = st.user_type_id WHERE (so.name = ' {…} ') AND (sc.name not in ('docId','V_SNo'))`
- Errors: "Select Table"

### `getDataType`
`public DmResultPageing<List<KeyFieldExtended>> getDataType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `DmDataType.valueOf`, `DmDataType.values`

### `getYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getCopy`
`public DmResultPageing<List<KeyFieldExtended>> getCopy(DmHelper helper, boolean blnToEndOfFile)`


### `getViewFieldCode`
`public DmResultPageing<List<KeyFieldExtended>> getViewFieldCode(DmHelper helper, boolean blnToEndOfFile, String query)`

- Errors: "Please enter SQL query!"

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select gc.code, gc.name, gc.tableName, gtm.Name as guiTemplateName, gc.reportNamePrefix from guiconfig gc left join (SELECT gca.code, MAX(gca.guiTemplateCode) AS guiTemplateCode FROM guiconfigapplicableto gca GROUP BY gca.code) gca on gc.code = gca.code left join guiTemplateMast gtm on gca.guiTemplateCode = gtm.code`

### `getPSTemp`
`private DmPreparedStatement getPSTemp(String sqlQuery, Connection cnTemp) throws Exception`

- SQL 1: `Select * from ( {…} ) as t where docId = :docId`
- Params: `:docId` ← `null`

### `exportJson`
`public DmResult<KeyFieldExtended> exportJson(String sessionId, GUIConfig_Mdl mrTemp)`

- SQL 1: `select gc.code, gc.name from guiConfig gc order by gc.name`
- Errors: "Invalid request." | "File Name not given."

### `importJson`
`public DmResult<GUIConfig_Mdl> importJson(String filePath, String fileName)`

- SQL 1: `SELECT sm.code, sm.name, sm.manualCode FROM sitemast sm WHERE sm.code = :siteCode`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmFileManager.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.client`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFileManager.client`, `dataman.dmToolkit.dmFileManager.server`, `dataman.dmToolkit.dmFileUploadButton.client`, `dataman.dmToolkit.dmFileUploadButton.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextArea.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.libraryMenu.self.shared`, `dataman.utility.guiConfig.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (DmShared.iNull(mrTemp.getSharedKeyFieldCode()).longValue() == 0)` | "Invalid key." |
| saveRecord | `if (rsTemp.next())` | "GUI Name already exists." |
| saveRecord | `if (rsTemp.next())` | "Record already exists on " |
| saveRecord | `if (rsTemp.next())` | "Save In Field [" |
| saveRecord | `if (!flag)` | "View Field Text [" |
| saveRecord | `if (!flag)` | "View Field Code [" |
| getTxtHSaveInField | `if (DmShared.xNull(tableName).equalsIgnoreCase(""))` | "Select Table" |
| getViewFieldCode | `if (DmShared.xNull(query).trim().equalsIgnoreCase(""))` | "Please enter SQL query!" |
| exportJson | `if (!DmSession.getSessions().containsKey(sessionId))` | "Invalid request." |
| exportJson | `if (DmShared.xNull(fileName).equalsIgnoreCase(""))` | "File Name not given." |

## Notes (hand-written)
_No hand-written notes yet._
