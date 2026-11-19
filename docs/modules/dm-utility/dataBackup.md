# dm-utility / dataBackup

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/utility/src/dataBackup/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: backupInfo
- Update tables: backupInfo
- Delete cascade (in order): backupInfo
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: -
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `AddDirectory`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Type | DmTextHelp | `txtHElement` | `KeyField_PrpAcs, 4, true, true` |
| 2 | Name | DmTextBox | `txtFileName` | `100, true` |
| 3 | - | DmTextHelp | `txtHDatabase` | `KeyField_PrpAcs, 4, true` |
| 4 | Element Location | DmTextBox | `txtFileLocation` | `1000, true` |
| 5 | Backup Location | DmTextBox | `txtBackupLocation` | `1000, true` |

**Grid / popup columns** (header, width, db field):
- `txtHElement`: Element [Text, w0, elementType]
- `txtHDatabase`: Database [Text, w0, dbName]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHElement` → `getElementType`
- `txtHDatabase` → `getDatabaseName`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHElement.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getElementType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFie
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHElement).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHElement.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHElement).show();
```

`txtHElement.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHElement.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHElement).show();
```

`txtHElement.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
txtHDatabase.setText("");
txtHDatabase.setTag2nd("");
txtHDatabase.setTag("");
txtFileName.setText("");
txtFileName.setTag2nd("");
txtFileName.setTag("");
txtFileLocation.setText("");
txtFileLocation.setTag2nd("");
txtFileLocation.setTag("");
...
```

`txtHDatabase.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getDatabaseName(helper, blnToEndOfFile, DmShared.xNull(txtHElement.getTag()), new As
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDatabase).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHDatabase.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDatabase).show();
```

`txtHDatabase.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHDatabase.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDatabase).show();
```

`txtHDatabase.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
txtFileLocation.setText("");
txtFileLocation.setTag("");
txtFileLocation.setTag2nd("");
txtFileLocation.setText(DmShared.xNull(t1Row.getSharedModifiedBy()));
txtFileLocation.setReadOnly((DmShared.iNull(txtHElement.getTag()).intValue() == DmEnum.DataBackUp.DB_COMPANY.getId()
|| DmShared.iNull(txtHElement.getTag()).intValue() == DmEnum.DataBackUp.DB_TRANSACTION.getId()));
```
- Alert: "Type is mandatory."
- Alert: "Name is mandatory."
- Alert: "Name is mandatory."
- Alert: "Element Location is mandatory."
- Alert: "Backup Location is mandatory."

### Screen `BackupGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Element Type | DmTextBox | `txtElementType` | `10, true` |
| 2 | Element Name | DmTextBox | `txtElementName` | `100, true` |
| 3 | Element Location | DmTextBox | `txtElementLocation` | `1000, true` |
| 4 | Backup Location | DmTextBox | `txtBackupLocation` | `1000, true` |
| 5 | File Location | DmTextBox | `txtFileLocation` | `1000, false` |

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtElementLocation.setReadOnly(true)`
- `txtElementName.setEnabled(false)`
- `txtElementType.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
txtElementLocation.setReadOnly(true);
txtElementName.setEnabled(false);
txtElementType.setEnabled(false);
```
- Confirm: "Are you sure? You want to delete element."

### Screen `DataBackup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `grdBackup` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `grdBackup`:  [Text, w30]; Element Type [Text, w100]; Element Name [Text, w150]; File Location [Text, w285]; Backup Location [Text, w200]; Status [Text, w215]

**Defaults when the screen opens** (`iniDefault`):
- calls server `getDefaultValue`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`gridEdit.addCallBack` → `onOk()`
```java
grdBackup.commitChanges();
```

`gridEdit.addCallBack` → `onDelete()`
```java
grdBackup.commitChanges();
```

`addDirectory.addCallBack` → `onOk()`
```java
int index = -1;
if (grdMdl != null) {
index = grdBackup.getGrid().getStore().indexOf(grdMdl);
if (index < 0) {
grdBackup.getGrid().getStore().add(grdMdl);
} else {
grdBackup.getGrid().getStore().update(grdMdl);
grdBackup.commitChanges();
```
- Confirm: "Are you sure? You want to close."
- Confirm: "Are you sure? You want to backup selected files."
- Confirm: "Are you sure? You want to Backup Data."
- Alert: "Select anyone element to backup"

## Files
| file | lines |
|---|---|
| client/AddDirectory.java | 263 |
| client/AddDirectory_Dsg.java | 184 |
| client/BackupGridEdit.java | 143 |
| client/BackupGridEdit_Dsg.java | 113 |
| client/DataBackup.java | 253 |
| client/DataBackup_Dsg.java | 159 |
| server/DBCompany_Server.java | 162 |
| server/DBMongo_Server.java | 151 |
| server/DBTransaction_Server.java | 162 |
| server/DataBackup_SrvImpl.java | 331 |
| server/ExecCommand.java | 193 |
| server/FSDir_Server.java | 150 |
| shared/DataBackup_Srv.java | 24 |
| shared/DataBackup_SrvAsync.java | 23 |
| shared/GrdDataBackup_Mdl.java | 109 |

## UI labels / columns (client)
`Element Type`, `Element Name`, `Element Location`, `Backup Location`, `File Location`, `Type`, `Name`

## Models (shared DTOs)
### GrdDataBackup_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String elementType, elementTypeCode, elementName, elementLocation, backupLocation, status, | backupFileLocation |
| boolean | checked |

## RPC interface
| method | params | returns |
|---|---|---|
| backupElement | List<GrdDataBackup_Mdl> mrTemp | DmResult<List<GrdDataBackup_Mdl>> |
| saveRecord | GrdDataBackup_Mdl mrTemp | DmResult<String> |
| deleteRecord | String elementName, String elementType | DmResult<String> |
| getDefaultValue |  | DmResult<List<GrdDataBackup_Mdl>> |
| getElementType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDatabaseName | DmHelper pageing, boolean blnToEndOfFile, String elementType | DmResultPageing<List<KeyFieldExtended>> |

## Server `DBCompany_Server.java` (162 lines)
Helpers used: `DmServer.getConnection`, `DmServer.getServerTimestamp`, `DmShared.iNull`, `DmShared.xNull`

### `handle`
`public void handle(Event event)`

- SQL 1: `select elementType, elementName, elementLocation, backupLocation from backupInfo where elementType = :elementType`
- Params: `:elementType` ← `DataBackUp.DB_COMPANY.getId()`
- Other calls: `DB_COMPANY.getId`, `DataBackUp.getKeyOf`, `SchedulerMaster_Model.toString`

### `backup`
`public void backup(List<GrdDataBackup_Mdl> mrTemp)`

- Errors: "Element Name not found" | "Element Location not found" | "Backup Location not found"
- Other calls: `DB_COMPANY.getId`

## Server `FSDir_Server.java` (150 lines)
Helpers used: `DmServer.getConnection`, `DmServer.getServerTimestamp`, `DmShared.iNull`, `DmShared.xNull`

### `handle`
`public void handle(Event event)`

- SQL 1: `select elementType, elementName, elementLocation, backupLocation from backupInfo where elementType = :elementType`
- Params: `:elementType` ← `DataBackUp.FS_DIR.getId()`
- Other calls: `DataBackUp.getKeyOf`, `FS_DIR.getId`, `SchedulerMaster_Model.toString`

### `backup`
`public void backup(List<GrdDataBackup_Mdl> mrTemp)`

- Errors: "Element Name not found" | "Element Location not found" | "Backup Location not found"
- Other calls: `FS_DIR.getId`

## Server `DBMongo_Server.java` (151 lines)
Helpers used: `DmServer.getConnection`, `DmServer.getServerTimestamp`, `DmShared.iNull`, `DmShared.xNull`

### `handle`
`public void handle(Event event)`

- SQL 1: `select elementType, elementName, elementLocation, backupLocation from backupInfo where elementType = :elementType`
- Params: `:elementType` ← `DataBackUp.DB_MONGO.getId()`
- Other calls: `DB_MONGO.getId`, `DataBackUp.getKeyOf`, `SchedulerMaster_Model.toString`

### `backup`
`public void backup(List<GrdDataBackup_Mdl> mrTemp)`

- Errors: "Element Name not found" | "Element Location not found" | "Backup Location not found"
- Other calls: `DB_MONGO.getId`

## Server `DataBackup_SrvImpl.java` (331 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.xNull`

### `getDefaultValue`
`public DmResult<List<GrdDataBackup_Mdl>> getDefaultValue()`

- SQL 1: `select elementType, elementName, elementLocation, backupLocation from backupInfo order by elementType, elementName`
- Other calls: `DataBackUp.getKeyOf`

### `deleteRecord`
`public DmResult<String> deleteRecord(String elementName, String elementType)`

- SQL 1: `delete from backupInfo where elementType = :type and elementName = :name`
- Params: `:type` ← `elementType`; `:name` ← `elementName`

### `saveRecord`
`public DmResult<String> saveRecord(GrdDataBackup_Mdl mrTemp)`

- SQL 1: `select elementType, elementName, elementLocation, backupLocation from backupInfo where elementType = :type and elementName = :name`
- SQL 2: `insert into backupInfo (elementType, elementName, elementLocation, backupLocation) values (:type,:name,:elementLocation,:backupLocation)`
- SQL 3: `update backupInfo set elementLocation = :elementLocation , backupLocation = :backupLocation where elementType = :type and elementName = :name`
- Params: `:type` ← `mrTemp.getElementTypeCode()`; `:name` ← `mrTemp.getElementName()`; `:type` ← `mrTemp.getElementTypeCode()`; `:name` ← `mrTemp.getElementName()`; `:elementLocation` ← `mrTemp.getElementLocation()`; `:backupLocation` ← `mrTemp.getBackupLocation()`; `:type` ← `mrTemp.getElementTypeCode()`; `:name` ← `mrTemp.getElementName()`; `:elementLocation` ← `mrTemp.getElementLocation()`; `:backupLocation` ← `mrTemp.getBackupLocation()`

### `backupElement`
`public DmResult<List<GrdDataBackup_Mdl>> backupElement(List<GrdDataBackup_Mdl> mrTemp)`


### `getElementType`
`public DmResultPageing<List<KeyFieldExtended>> getElementType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `DataBackUp.values`

### `getDatabaseName`
`public DmResultPageing<List<KeyFieldExtended>> getDatabaseName(DmHelper helper, boolean blnToEndOfFile, String elementType)`

- SQL 1: `select db.name dbName, mf.physical_name physicalPath from sys.master_Files mf left join sys.databases db on db.database_id = mf.database_id where db.name = ' {…} ' and mf.type_desc = 'ROWS'`
- SQL 2: `select max(db.name) dbName, max(mf.physical_name) physicalPath from company c left join sys.databases db on db.name = c.centralData_Path left join sys.master_files mf on mf.database_id = db.database_id where mf.type_desc = 'ROWS' group by db.database_id`
- SQL 3: `select c.centralFileData_Path dbName, '' as physicalPath from company c where ISNULL(c.centralFileData_Path, '') <> '' group by c.centralFileData_Path`
- Other calls: `DataBackUp.getKeyOf`

## Server `DBTransaction_Server.java` (162 lines)
Helpers used: `DmServer.getConnection`, `DmServer.getServerTimestamp`, `DmShared.iNull`, `DmShared.xNull`

### `handle`
`public void handle(Event event)`

- SQL 1: `select elementType, elementName, elementLocation, backupLocation from backupInfo where elementType = :elementType`
- Params: `:elementType` ← `DataBackUp.DB_TRANSACTION.getId()`
- Other calls: `DB_TRANSACTION.getId`, `DataBackUp.getKeyOf`, `SchedulerMaster_Model.toString`

### `backup`
`public void backup(List<GrdDataBackup_Mdl> mrTemp)`

- Errors: "Element Name not found" | "Element Location not found" | "Backup Location not found"
- Other calls: `DB_TRANSACTION.getId`

## Server `ExecCommand.java` (193 lines)
### `getOutput`
`public String getOutput()`


### `getError`
`public String getError()`


### `makeArray`
`private String[] makeArray(String command)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmEventbus.server`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmScheduler.shared`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.libraryMenu.self.shared`, `dataman.utility.dataBackup.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
