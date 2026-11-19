# dm-toolkit / dmReportLayout

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/dmReportLayout/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHManageTemplate, getTxtHAcName
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `DmReportLayout`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `cbCheckUncheckAll` (DmCheckBox), `cbHideFilterPrint` (DmCheckBox)

**Event handlers (condensed code, CRUD flow and field callbacks):**

`mnuSendPdf.addSelectionHandler` → `onSelection()`
```java
new DmConfirmMessageBox("Are you sure? You want to Send Pdf Message.", new DialogHideEvent.DialogHideHandler() {
public void onDialogHide(DialogHideEvent event) {
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
reportProperty.setExportType(DmExportType.PDF);
reportProperty.setProcessMessage(true);
if (processMessageQueueMdl != null && !DmShared.xNull(processMessageQueueMdl.getTemplateCodeCSV()).trim().equalsIgnoreCa
reportProperty.setProcessMsgQueue_Mdl(processMessageQueueMdl);
reportProperty.setConstPrefixName(constPrefixName);
reportProperty.setConstSuffixName(constSuffixName);
onView();
...
```

`mnuSendPdf.addSelectionHandler` → `onDialogHide()`
```java
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
reportProperty.setExportType(DmExportType.PDF);
reportProperty.setProcessMessage(true);
if (processMessageQueueMdl != null && !DmShared.xNull(processMessageQueueMdl.getTemplateCodeCSV()).trim().equalsIgnoreCa
reportProperty.setProcessMsgQueue_Mdl(processMessageQueueMdl);
reportProperty.setConstPrefixName(constPrefixName);
reportProperty.setConstSuffixName(constSuffixName);
onView();
```

`mnuSendExcel.addSelectionHandler` → `onSelection()`
```java
new DmConfirmMessageBox("Are you sure? You want to Send Excel Message.", new DialogHideEvent.DialogHideHandler() {
public void onDialogHide(DialogHideEvent event) {
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
reportProperty.setExportType(DmExportType.EXCEL);
reportProperty.setProcessMessage(true);
if (processMessageQueueMdl != null && !DmShared.xNull(processMessageQueueMdl.getTemplateCodeCSV()).trim().equalsIgnoreCa
reportProperty.setProcessMsgQueue_Mdl(processMessageQueueMdl);
reportProperty.setConstPrefixName(constPrefixName);
reportProperty.setConstSuffixName(constSuffixName);
onView();
...
```

`mnuSendExcel.addSelectionHandler` → `onDialogHide()`
```java
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
reportProperty.setExportType(DmExportType.EXCEL);
reportProperty.setProcessMessage(true);
if (processMessageQueueMdl != null && !DmShared.xNull(processMessageQueueMdl.getTemplateCodeCSV()).trim().equalsIgnoreCa
reportProperty.setProcessMsgQueue_Mdl(processMessageQueueMdl);
reportProperty.setConstPrefixName(constPrefixName);
reportProperty.setConstSuffixName(constSuffixName);
onView();
```

`mnuSendWord.addSelectionHandler` → `onSelection()`
```java
new DmConfirmMessageBox("Are you sure? You want to Send Word Message.", new DialogHideEvent.DialogHideHandler() {
public void onDialogHide(DialogHideEvent event) {
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
reportProperty.setExportType(DmExportType.WORD);
reportProperty.setProcessMessage(true);
if (processMessageQueueMdl != null && !DmShared.xNull(processMessageQueueMdl.getTemplateCodeCSV()).trim().equalsIgnoreCa
reportProperty.setProcessMsgQueue_Mdl(processMessageQueueMdl);
reportProperty.setConstPrefixName(constPrefixName);
reportProperty.setConstSuffixName(constSuffixName);
onView();
...
```

`mnuSendWord.addSelectionHandler` → `onDialogHide()`
```java
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
reportProperty.setExportType(DmExportType.WORD);
reportProperty.setProcessMessage(true);
if (processMessageQueueMdl != null && !DmShared.xNull(processMessageQueueMdl.getTemplateCodeCSV()).trim().equalsIgnoreCa
reportProperty.setProcessMsgQueue_Mdl(processMessageQueueMdl);
reportProperty.setConstPrefixName(constPrefixName);
reportProperty.setConstSuffixName(constSuffixName);
onView();
```

`mnuManageTemplate.addSelectionHandler` → `onSelection()`
```java
dmTemplatePopup.show();
```

`dmTemplatePopup.addCallBack` → `onOk()`
```java
processMessageQueueMdl= new ProcessMessageQueue_Mdl();
processMessageQueueMdl = processMessageQueue_Mdl;
```

`dmTemplatePopup.addCallBack` → `get_CONST_PREFIX()`
```java
return constPrefixName;
```

`dmTemplatePopup.addCallBack` → `get_CONST_SUFFIX()`
```java
return constSuffixName;
```

`dmTemplatePopup.addCallBack` → `getProcessMessageQueue_Mdl()`
```java
return processMessageQueueMdl;
```

**Menu items (Other button):** Process Message (PDF), Process Message (Excel), Process Message (Word) , Manage Template
- Confirm: "Are you sure? You want to close."
- Confirm: "Are you sure? You want to Send Pdf Message."
- Confirm: "Are you sure? You want to Send Excel Message."
- Confirm: "Are you sure? You want to Send Word Message."

### Screen `DmTemplatePopupGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Send To Name | DmTextBoxNumeric | `txtSrno` | `0, false, 4, true` |
| 2 | - | DmTextHelp | `txtHAcName` | `TxtHAcName_PrpAcs, 5, false` |
| 3 | - | DmTextBox | `txtSendToName` | `100, true` |
| 4 | Send To CC | DmTextBox | `txtSendToEmail` | `100, false` |
| 5 | - | DmTextBox | `txtSendToCC` | `100, false` |
| 6 | Send To Mobile | DmTextBox | `txtSendToBCC` | `100, false` |
| 7 | - | DmTextBoxNumeric | `txtSendToMobileNo` | `0, false, 10, false` |

Other widgets (not in tab list): `txtWidgetName` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `txtHAcName`: A/c Name [Text, w300, name]; Manual Code [Text, w100, manualCode]; City [Text, w200, cityName]; Nature [Text, w100, subGroupNatureName]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHAcName` → `getTxtHAcName`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHAcName.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHAcName(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHAcN
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHAcName).show();
public void onSuccess(DmResultPageing<List<TxtHAcName_Mdl>> result) {
if (result.isStatus()) {
txtHAcName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAcName).show();
```

`txtHAcName.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAcName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAcName).show();
```

`txtHAcName.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHAcName.getTag()).equalsIgnoreCase(DmShared.xNull(txtHAcName.getTag2nd()))) {
resetSubGroupDetail();
txtHAcName.setTag2nd(DmShared.xNull(txtHAcName.getTag()));
if (blnStatus && t1Row != null) {
fillSubGroupDetail(t1Row);
```
- Alert: "SR No. is mendatory."
- Alert: "SR. can not be duplicate."
- Alert: "Send To Name is mendatory."
- Alert: "Email or Mobile is mendatory."
- Alert: "Send To Name is mendatory."
- Alert: "Email or Mobile is mendatory."

### Screen `DmTemplatePopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Templates | DmTextHelpMulti | `txtHTemplate` | `KeyField_PrpAcs, 3, true` |

Other widgets (not in tab list): `dmGrdTemplate` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHTemplate`: Manage Template [Text, w1, Name]
- `dmGrdTemplate`: Sr. [Text, w32]; Account Name [Text, w162]; Send To Name [Text, w162]; Send To Email [Text, w162]; Send To CC [Text, w162]; Send To BCC [Text, w162]; Send To Mobile [Text, w95]

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHTemplate.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHManageTemplate(DmShared.xNull(callBack.get_CONST_PREFIX()), DmShared.xNull(ca
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHTemplate).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHTemplate.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTemplate).show();
```

`txtHTemplate.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHTemplate.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTemplate).show();
```

`templateGridEdit.addCallBack` → `get_CONST_PREFIX()`
```java
return callBack.get_CONST_PREFIX();
```

`templateGridEdit.addCallBack` → `get_CONST_SUFFIX()`
```java
return callBack.get_CONST_SUFFIX();
```

`templateGridEdit.addCallBack` → `onOk()`
```java
dmGrdTemplate.commitChanges();
```

`templateGridEdit.addCallBack` → `onDelete()`
```java
dmGrdTemplate.commitChanges();
```

`templateGridEdit.addCallBack` → `getProcessMessageMdlLst()`
```java
return dmGrdTemplate.getGrid().getStore().getAll();
```
- Alert: "Template is mendatory."

## Files
| file | lines |
|---|---|
| client/DmReportLayout.java | 427 |
| client/DmReportLayout_Dsg.java | 324 |
| client/DmTemplatePopup.java | 265 |
| client/DmTemplatePopupGridEdit.java | 338 |
| client/DmTemplatePopupGridEdit_Dsg.java | 265 |
| client/DmTemplatePopup_Dsg.java | 185 |
| server/DmReportLayout_SrvImpl.java | 131 |
| server/DmReportLayout_Srvlt.java | 50 |
| shared/ColumnConfig_Mdl.java | 121 |
| shared/ColumnKeyMapper.java | 72 |
| shared/DmReportLayout_Srv.java | 16 |
| shared/DmReportLayout_SrvAsync.java | 17 |
| shared/DynamicGridItemModel.java | 23 |
| shared/DynamicValueProviderFactory.java | 27 |
| shared/GridConfig.java | 105 |
| shared/GridConfig_Mdl.java | 50 |
| shared/TxtHAcName_Mdl.java | 69 |

## UI labels / columns (client)
`Templates`, `SR.`, `A/c Name`, `Send To Name`, `Send To Email`, `Send To CC`, `Send To BCC`, `Send To Mobile`, `Hide/Show All Filters`

## Models (shared DTOs)
### TxtHAcName_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | displayName |
| String | cityName |
| String | email |
| String | mobile |
| String | nature |

### ColumnConfig_Mdl
| type | field |
|---|---|
| String | moduleName |
| int | index |
| String | name |
| int | width |
| boolean | wrap |
| boolean | visiblity |
| String | gridColKey |
| String | mapperKey |

### GridConfig_Mdl
| type | field |
|---|---|
| String | moduleName |
| String | menuName |
| String | type |
| List<ColumnConfig_Mdl> | columnConfig_Mdls |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHManageTemplate | String prefix,String suffix, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAcName | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHAcName_Mdl>> |

## Server `DmReportLayout_SrvImpl.java` (131 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmShared.xNull`

### `getTxtHManageTemplate`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHManageTemplate(String constPrefix, String constSuffix, DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT code,name from wjTemplateMast where name LIKE ' {…} [\_]% {…} {…} [\_]% {…} '`

### `getTxtHAcName`
`public DmResultPageing<List<TxtHAcName_Mdl>> getTxtHAcName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code, sg.name, sg.manualCode, c.cityName , sg.displayName, sg.email, sg.mobile, sgn.name AS subGroupNatureName FROM subGroup sg LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature`

## Server `DmReportLayout_Srvlt.java` (50 lines)
### `processRequest`
`public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.server`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.client`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmCheckBox.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmReportLayout.shared`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextBoxTime.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.self.client`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
Report layout templates (`getTxtHManageTemplate`, `getTxtHAcName`, `processRequest`): user-defined report column/layout (master report builder). Detail dekhna baaki.
