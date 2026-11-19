# pb-master / dscMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/master/src/dscMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: dscMast
- Update tables: dscMast
- Delete cascade (in order): dscMast
- Duplicate checks on save:
  - `SELECT COUNT(*) AS cnt FROM dscMast WHERE srNo = :srNo AND code <> :code`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Password (Text)
- Lookups/suggestions: -
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `DscMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Serial No. | DmTextBox | `txtserialNo` | `100, false` |
| 2 | Description | DmTextBox | `txtDescription` | `250, false` |

Other widgets (not in tab list): `dmFind` (DmFind), `fileRuleType` (DmFileRuleType), `btnUpload` (DmFileUploadButton)

**Grid / popup columns** (header, width, db field):
- `dmFind`: Serial No. [Text, w100, serialNo]; Description [Text, w100, description]

**Lookup / suggestion / find sources (server RPC called):**
- `modifyReason` → `getFind`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SerialNo` ← txtserialNo.getText()
- `Password` ← txtPassword.getText()
- `Description` ← txtDescription.getText()

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- `txtDescription.setText` ← data.getDescription()
- `txtserialNo.setText` ← data.getSerialNo()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
boolean flag = false;
if (topControl.getMode().trim().equalsIgnoreCase("New") || topControl.getMode().trim().equalsIgnoreCase("Edit")) {
flag = true;
btnUpload.getButton().setEnabled(false);
if (!DmShared.xNull(txtserialNo.getText()).trim().isEmpty() && !DmShared.xNull(txtPassword.getText()).trim().isEmpty()) 
btnUpload.getButton().setEnabled(flag);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
onAdd();
```

`topControl.addEditHandeller` → `editClicked()`
```java
onEdit();
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
if (DmShared.xNull(topControl.getMode()).equalsIgnoreCase("Edit")) {
showModifyReason();
} else {
topControlSave();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(DscMaster.this);
```

`modifyReason.addCallBack` → `onOk()`
```java
try {
DscMaster.this.modifyReasonMdl = modifyReason;
if (topControl.getMode().equalsIgnoreCase("Browse") && DmShared.iNull(topControl.getDocId()).longValue() > 0) {
topControlDelete(topControl.getDocId());
} else {
topControlSave();
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), (DmTextBox) null).show();
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

**Top control options:** setRefreshRequired(false), setDisplay(true)

_Print permission "P" is removed for this screen (replace "P" with "*")._

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Alert: "Please Enter Serial No."
- Alert: "Please Enter password."

## Files
| file | lines |
|---|---|
| client/DscMaster.java | 522 |
| client/DscMaster_Dsg.java | 189 |
| server/DscMaster_SrvImpl.java | 615 |
| shared/DscMaster_Mdl.java | 80 |
| shared/DscMaster_Srv.java | 23 |
| shared/DscMaster_SrvAsync.java | 24 |
| shared/DscMaster_Vld.java | 13 |
| shared/Find_Mdl.java | 32 |
| shared/TxtHCity_Mdl.java | 50 |

## UI labels / columns (client)
`Serial No.`, `Password`, `Description`

## Models (shared DTOs)
### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | serialNo |
| String | description |

### TxtHCity_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | stateCode |
| String | stateName |
| String | stateNumericCode |
| String | countryName |

### DscMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | serialNo |
| String | description |
| String | password |
| String | serverDirectoryPath |
| String | serverFileName |
| String | fileName |
| ModifyReason_Mdl | modifyReasonMdl |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<DscMaster_Mdl> |
| saveRecord | DscMaster_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl modifyReasonMdl | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |

## Validator `DscMaster_Vld`
```java
public class DscMaster_Vld {
    public static void chkSave(DscMaster_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getPassword().trim(), "Password", 100);
    }
}
```

## Server `DscMaster_SrvImpl.java` (615 lines)
Helpers used: `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getSerialNoInteger`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.setSsnOldData`, `DmShared.iNull`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `SELECT code FROM dscMast ORDER BY code`

### `getMoveRec`
`public DmResult<DscMaster_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT code, srNo, serverFileName, description, fileName FROM dscMast WHERE code = :code`
- Params: `:code` ← `strKey`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl modifyReasonMdl)`

- SQL 1: `DELETE FROM dscMast WHERE code = :code`
- Params: `:code` ← `strKey`

### `saveRecord`
`public DmResult<String> saveRecord(DscMaster_Mdl mrTemp)`

- SQL 1: `SELECT COUNT(*) AS cnt FROM dscMast WHERE srNo = :srNo AND code <> :code`
- SQL 2: `INSERT INTO dscMast (code, srNo, serverFileName, password, description, fileName) VALUES (:code, :srNo, :serverFileName, :password, :description, :fileName)`
- SQL 3: `UPDATE dscMast SET srNo = :srNo, password = :password, description = :description, serverFileName = :serverFileName, fileName = :fileName WHERE code = :code`
- Params: `:srNo` ← `DmShared.xNull(mrTemp.getSerialNo())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:srNo` ← `matchedSerialNo`; `:password` ← `mrTemp.getPassword()`; `:description` ← `description`; `:serverFileName` ← `mrTemp.getServerFileName()`; `:fileName` ← `mrTemp.getFileName()`; `:srNo` ← `matchedSerialNo`; `:password` ← `mrTemp.getPassword()`; `:description` ← `description`; `:serverFileName` ← `mrTemp.getServerFileName()`; `:fileName` ← `mrTemp.getFileName()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`
- Errors: "Invalid key." | "Serial No. already exists."
- Other calls: `DscMaster_Vld.chkSave`

### `validateDscSerialNo`
`private KeyFieldExtended validateDscSerialNo(final boolean blnAdd, String serialNo, final String serverDirectoryPath, final String serverFileName, final String pfxPassword) throws Exception`

- Errors: "Please enter Serial No." | "Please enter password." | "Please select PFX file." | "Invalid Serial No." | "Unable to create 'dscPfx' directory." | "Uploaded PFX file not found."
- Other calls: `DscSigningService.getHeaderInfo`, `Files.move`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT code, srNo AS serialNo, description, serverFileName fileName FROM dscMast WHERE 1 = 1`

### `getProcessAfterUploaded`
`public DmResult<String> getProcessAfterUploaded(String filePath, String fileName, String serialNo, String pfxPassword)`

- Errors: "Please enter Serial No." | "Please enter Password."
- Other calls: `DscSigningService.getHeaderInfo`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmFileManager.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.digitalSignature.server`, `dataman.dmToolkit.dmFileUploadButton.client`, `dataman.dmToolkit.dmFileUploadButton.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (DmShared.xNull(mrTemp.getSharedKeyFieldCode()).equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Serial No. already exists." |
| validateDscSerialNo | `if (serialNo.isEmpty())` | "Please enter Serial No." |
| validateDscSerialNo | `if (serialNo.isEmpty())` | "Please enter password." |
| validateDscSerialNo | `if (blnAdd)` | "Please select PFX file." |
| validateDscSerialNo | `if (detailTemp == null)` | "Invalid Serial No." |
| validateDscSerialNo | `if (!targetDir.exists() && !targetDir.mkdirs())` | "Unable to create 'dscPfx' directory." |
| validateDscSerialNo | `if (!sourceFile.isFile())` | "Uploaded PFX file not found." |
| getProcessAfterUploaded | `if (serialNo.isEmpty())` | "Please enter Serial No." |
| getProcessAfterUploaded | `if (DmShared.xNull(pfxPassword).trim().isEmpty())` | "Please enter Password." |

## Notes (hand-written)
_No hand-written notes yet._
