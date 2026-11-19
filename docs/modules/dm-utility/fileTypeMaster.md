# dm-utility / fileTypeMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/utility/src/fileTypeMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): filetypemast
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Rule Code (Text); rule: Only one file type can be default.; rule: Please mention default file type.
- Lookups/suggestions: getTxtHDefault
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `FileTypeMasterGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | File Type | DmTextBox | `txtFileType` | `10, true` |
| 2 | Default | DmTextHelp | `txtHDefault` | `KeyField_PrpAcs, 3, true` |
| 3 | Max. Size (KB) | DmTextBoxNumeric | `txtMaxSize` | `0, false, 9, true` |
| 4 | Filters (Eg- pdf,docx) | DmTextBox | `txtFilter` | `100, true` |

Other widgets (not in tab list): `dmGrd` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHDefault`: Type [Text, w0, Type]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHDefault` → `getTxtHDefault`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHDefault.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHDefault(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFie
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDefault).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHDefault.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDefault).show();
```

`txtHDefault.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHDefault.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDefault).show();
```
- Alert: "File Type is mandatory."
- Alert: "Default is mandatory."
- Alert: "Max. Size is mandatory."
- Alert: "Filter is mandatory."
- Alert: "There is only one Default."

### Screen `FileTypeMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Rule Code | DmTextBox | `txtRuleCode` | `5, false` |
| 2 | Description | DmTextBox | `txtDescription` | `150, false` |

Other widgets (not in tab list): `dmFind` (DmFind), `dmGrd` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `dmFind`: Rule Code [Text, w300, ruleCode]; Description [Text, w800, description]
- `dmGrd`: File Type [Text, w100]; Max. Size (KB) [Text, w150]; Filters [Text, w500]; Default [Text, w100]

**Lookup / suggestion / find sources (server RPC called):**
- `gridEdit` → `syncronizeFileDB`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtRuleCode.getText())
- `Description` ← DmShared.xNull(txtDescription.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtRuleCode.setText` ← data.getSharedKeyFieldName()
- `txtDescription.setText` ← data.getDescription()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**On New click:**
- `clear()`
- `txtRuleCode.setFocus(true)`
- `txtDescription.setEnabled(false)`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
clear();
txtRuleCode.setFocus(true);
txtDescription.setEnabled(false);
```

`topControl.addEditHandeller` → `editClicked()`
```java
txtRuleCode.setEnabled(false);
txtDescription.setEnabled(false);
dmGrd.getGrid().getStore().add(new GrdFileTypeMaster_Mdl());
gridEdit.txtFileType.setFocus(true);
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(FileTypeMaster.this);
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

`gridEdit.addCallBack` → `onOk()`
```java
dmGrd.commitChanges();
```

`gridEdit.addCallBack` → `onDelete()`
```java
dmGrd.commitChanges();
```

**Top control options:** setRefreshRequired(false), setDisplay(true)
- Confirm: "Are you sure? You want to delete this record."
- Confirm: "Are you sure? you want to syncronize file db."
- Confirm: "Are you sure? You want to export records."

## Files
| file | lines |
|---|---|
| client/FileTypeMaster.java | 454 |
| client/FileTypeMasterGridEdit.java | 192 |
| client/FileTypeMasterGridEdit_Dsg.java | 115 |
| client/FileTypeMaster_Dsg.java | 216 |
| server/FileTypeMaster_SrvImpl.java | 476 |
| shared/FileTypeMaster_Mdl.java | 31 |
| shared/FileTypeMaster_Srv.java | 28 |
| shared/FileTypeMaster_SrvAsync.java | 29 |
| shared/FileTypeMaster_Vld.java | 28 |
| shared/Find_Mdl.java | 23 |
| shared/GrdFileTypeMaster_Mdl.java | 41 |

## UI labels / columns (client)
`File Type`, `Default`, `Max. Size (KB)`, `Filters (Eg- pdf,docx)`, `Rule Code`, `Description`

## Models (shared DTOs)
### FileTypeMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| List<GrdFileTypeMaster_Mdl> | grdData |
| String | description |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | description |

### GrdFileTypeMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | filter |
| String | defaultCode |
| String | defaultName |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<FileTypeMaster_Mdl> |
| saveRecord | FileTypeMaster_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getTxtHDefault | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| syncronizeFileDB |  | DmResult<Boolean> |
| exportDataCSV | String session | DmResult<KeyFieldExtended> |

## Validator `FileTypeMaster_Vld`
```java
public class FileTypeMaster_Vld {
    public static void chkSave(FileTypeMaster_Mdl mrTemp) {
        boolean isDefault = false;
        DmShared.isValidText(DmShared.xNull(mrTemp.getSharedKeyFieldName()).trim(), "Rule Code", 100);
        for (GrdFileTypeMaster_Mdl grdFileTypeMaster : mrTemp.getGrdData()) {
            if (grdFileTypeMaster != null) {
                if (DmShared.vNull(grdFileTypeMaster.getDefaultCode()).doubleValue() == 1) {
                    if (isDefault) {
                        throw new DmException(200, "Only one file type can be default.");
                    }
                    isDefault = true;
                }
            }
        }
        if (!isDefault) {
            throw new DmException(200, "Please mention default file type.");
        }
    }
}
```

## Server `FileTypeMaster_SrvImpl.java` (476 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.filterKeyFieldList`, `DmShared.removeSpace`, `DmShared.vNull`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `SELECT ruleCode FROM filetypemast GROUP BY ruleCode ORDER BY ruleCode`

### `getMoveRec`
`public DmResult<FileTypeMaster_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT ruleCode, description ,fileType, maxSize, isDefault, extFilters, u_Name FROM filetypemast WHERE ruleCode= :ruleCode`
- Params: `:ruleCode` ← `strKey`
- Other calls: `ObjListTemp.setDefaultCode`, `ObjListTemp.setDefaultName`, `ObjListTemp.setFilter`, `ObjListTemp.setSharedKeyFieldCode`, `ObjListTemp.setSharedKeyFieldName`

### `saveRecord`
`public DmResult<String> saveRecord(FileTypeMaster_Mdl mrTemp)`

- SQL 1: `SELECT COUNT(ruleCode) AS cnt FROM filetypemast WHERE LOWER(ruleCode) = :ruleCode`
- SQL 2: `Delete From filetypemast where ruleCode= :ruleCode`
- Params: `:ruleCode` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName()).toLowerCase()`; `:ruleCode` ← `mrTemp.getSharedKeyFieldName()`; `:ruleCode` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:description` ← `DmShared.xNull(mrTemp.getDescription())`; `:fileType` ← `DmShared.xNull(gtc.getSharedKeyFieldCode())`; `:maxSize` ← `DmShared.xNull(gtc.getSharedKeyFieldName())`; `:isDefault` ← `DmServer.vNull(gtc.getDefaultCode(), "0")`; `:extFilters` ← `DmShared.xNull(gtc.getFilter())`; `:uName` ← `DmServer.getSsnUserName(this)`
- Errors: "One row is mandatory." | "Rule Code already exists." | "Invalid key."
- Other calls: `FileTypeMaster_Vld.chkSave`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey)`

- SQL 1: `Delete From filetypemast where ruleCode= :ruleCode`
- Params: `:ruleCode` ← `strKey`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ruleCode , description FROM filetypemast GROUP BY ruleCode , description`

### `getTxtHDefault`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDefault(DmHelper helper, boolean blnToEndOfFile)`


### `syncronizeFileDB`
`public DmResult<Boolean> syncronizeFileDB()`

- Other calls: `DmFileManagerMongo.synchronizeTransactionalWithFileServer`

### `exportDataCSV`
`public DmResult<KeyFieldExtended> exportDataCSV(String sessionId)`

- SQL 1: `select ruleCode, description, fileType, maxsize, extFilters, case isDefault when 0 then 'No' else 'Yes' end as isDefault from fileTypeMast order by ruleCode asc`
- Errors: "Invalid request." | "File Name not given."

## Depends on (imports from other packages)
`dataman.dmBase.dmFileManagerMongo.server`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.client`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.libraryMenu.self.shared`, `dataman.utility.fileTypeMaster.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (DmShared.xNull(mrTemp.getGrdData().get(0).getSharedKeyFieldCode()).equalsIgnoreCase(""))` | "One row is mandatory." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Rule Code already exists." |
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| exportDataCSV | `if (!DmSession.getSessions().containsKey(sessionId))` | "Invalid request." |
| exportDataCSV | `if (DmShared.xNull(fileName).equalsIgnoreCase(""))` | "File Name not given." |

## Notes (hand-written)
_No hand-written notes yet._
