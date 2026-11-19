# pb-master / importData

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/master/src/importData/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: -
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `AddImportData`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmTextBox | `txtSearchValue` | `50, false` |
| 2 | - | DmGridPaging | `grdImport` | `GrdPaging_PrpAcs, 18, false` |

**Grid / popup columns** (header, width, db field):
- `grdImport`:  [Text, w30]; Import To [Text, w670, Name]

**Lookup / suggestion / find sources (server RPC called):**
- `grdImport` → `getImportTo`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`grdImport.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(false).getImportTo(searchValue_Mdl, helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Im
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<ImportData_Mdl>> result) {
if (result.isStatus()) {
grdImport.getDmGrid().getGrid().getStore().clear();
grdImport.setData(result.getData(), result.getPaging());
isSearching = false;
} else {
new DmAlertMessageBox(result.getErrorMessage(), (Button) null).show();
...
```

`grdImport.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
grdImport.getDmGrid().getGrid().getStore().clear();
grdImport.setData(result.getData(), result.getPaging());
isSearching = false;
} else {
new DmAlertMessageBox(result.getErrorMessage(), (Button) null).show();
```

`grdImport.addCallBack` → `getParam()`
```java
return setParam();
```

### Screen `ImportDataGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Import To | DmTextBox | `txtImportTo` | `50, false` |
| 2 | Import From | DmTextBox | `txtImportFrom` | `50, false` |
| 3 | - | DmFileUploadButton | `btnUpload` | `this, fileRuleType` |
| 4 | File Format | DmTextBox | `txtFileFormat` | `500, false` |
| 5 | - | DmTextArea | `txtLog` | `` |

Other widgets (not in tab list): `fileRuleType` (DmFileRuleType)
- Confirm: "Are you sure? You want to discard."
- Alert: "Please give the format to download file."

### Screen `ImportData`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `grdImportData` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `grdImportData`: Import To [Text, w350]; File Format [Text, w380]; Import From [Text, w370]; Status [Text, w120]

**Event handlers (condensed code, CRUD flow and field callbacks):**

`gridEditImport.addCallBack` → `onOk()`
```java
grdImportData.getGrid().getStore().remove(rowIndex);
grdImportData.getGrid().getStore().add(rowIndex, grdImportData_Mdl);
grdImportData.commitChanges();
```

`gridEditImport.addCallBack` → `onDelete()`
```java
grdImportData.getGrid().getStore().remove(rowIndex);
grdImportData.commitChanges();
manageListImport(importDataList);
```

`addImportData.addCallBack` → `onOk()`
```java
if (listImportData_Mdl != null) {
for (ImportData_Mdl importData_Mdl : listImportData_Mdl) {
if (importData_Mdl != null) {
if (DmShared.iNull(importData_Mdl.getIsChecked()).intValue() == 1) {
if (!grdImportData.getGrid().getStore().getAll().contains(importData_Mdl)) {
grdImportData.getGrid().getStore().add(importData_Mdl);
importDataList.set(importDataList.indexOf(importData_Mdl), importData_Mdl);
grdImportData.commitChanges();
```
- Confirm: "Are you sure? You want to import data."
- Confirm: "Are you sure? You want to close."
- Alert: "No file found to import in "

## Files
| file | lines |
|---|---|
| client/AddImportData.java | 274 |
| client/AddImportData_Dsg.java | 175 |
| client/ImportData.java | 286 |
| client/ImportDataGridEdit.java | 229 |
| client/ImportDataGridEdit_Dsg.java | 223 |
| client/ImportData_Dsg.java | 188 |
| server/ImportData_SrvImpl.java | 319 |
| shared/ImportData_Srv.java | 22 |
| shared/ImportData_SrvAsync.java | 22 |
| shared/SearchValue_Mdl.java | 38 |

## UI labels / columns (client)
`Import To`, `Import From`, `File Format`

## Models (shared DTOs)
### SearchValue_Mdl
| type | field |
|---|---|
| String | fieldName |
| List<ImportData_Mdl> | lstImport |
| boolean | searching |

## RPC interface
| method | params | returns |
|---|---|---|
| importData | String startDate, String endDate, ImportData_Mdl import_Mdl | DmResult<ImportData_Mdl> |
| getCSVFile | ImportData_Mdl mrTemp, String fileName | DmResult<String> |
| readLogFile | ImportData_Mdl importData_Mdl | DmResult<KeyFieldExtended> |
| getImportTo | SearchValue_Mdl searchValue_Mdl, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<ImportData_Mdl>> |
| getImportData | String yearStart | DmResult<List<ImportData_Mdl>> |

## Server `ImportData_SrvImpl.java` (319 lines)
Helpers used: `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.xNull`

### `importData`
`public DmResult<ImportData_Mdl> importData(String startDate, String endDate, ImportData_Mdl mrTemp)`

- Other calls: `Class.forName`

### `getCSVFile`
`public DmResult<String> getCSVFile(ImportData_Mdl mrTemp, String fileName)`

- Other calls: `Class.forName`, `ImportData_Server.getCSVFormats`

### `readLogFile`
`public DmResult<KeyFieldExtended> readLogFile(ImportData_Mdl importData_Mdl)`


### `getImportTo`
`public DmResultPageing<List<ImportData_Mdl>> getImportTo(SearchValue_Mdl param, DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `ImportData_Server.getCSVFormats`

### `getImportData`
`public DmResult<List<ImportData_Mdl>> getImportData(String yearStart)`


### `setFileFormat`
`private void setFileFormat(List<ImportData_Mdl> importDataLst, String yearStart, String logDirectoryPath) throws Exception`

- Other calls: `Class.forName`, `ImportData_Server.getCSVFormats`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmFileManager.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.client`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFileUploadButton.client`, `dataman.dmToolkit.dmFileUploadButton.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmImportData.shared`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextArea.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmToolContainer.client`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stImportData.server`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
