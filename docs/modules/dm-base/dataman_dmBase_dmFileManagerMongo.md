# dm-base / dataman_dmBase_dmFileManagerMongo

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmBase/src/dataman_dmBase_dmFileManagerMongo/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: LINKFILECOLLECTION
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: -
- CSV export: no

## Files
| file | lines |
|---|---|
| server/DmFileManagerMongo.java | 702 |

## Server `DmFileManagerMongo.java` (702 lines)
Helpers used: `DmBaseServer.getConnection`, `DmBaseServer.getConnectionMongo`

### `synchronizeTransactionalWithFileServer`
`public static boolean synchronizeTransactionalWithFileServer() throws Exception`

- SQL 1: `select count(*) as cnt from sysDatabases where name = ' {…} '`
- SQL 2: `select distinct centralFileData_Path from {…} .dbo.company`
- SQL 3: `select distinct ruleCode from {…} .dbo.fileTypeMast`
- Other calls: `DmBaseShared.iNull`, `DmBaseShared.xNull`

### `createFolder`
`public boolean createFolder(String folderName)`

- Errors: "DmFileManagerMongo: File database not selected."

### `getFile`
`public GridFSDBFile getFile(String fileId) throws Exception`

- Errors: "DmFileManagerMongo: File database not selected."

### `isFileExists`
`public boolean isFileExists(String fileId) throws Exception`

- Errors: "DmFileManagerMongo: File database not selected." | "some exception is drawn while checking for exsitance of file."

### `isFileExistsInFolder`
`public boolean isFileExistsInFolder(String folderName, String fileId) throws Exception`

- Errors: "file database not selected." | "folder doesn't exist"

### `getFileFromFolder`
`public GridFSDBFile getFileFromFolder(String folderName, String fileLink) throws Exception`

- Errors: "DmFileManagerMongo: File database not selected." | "DmFileManagerMongo: Folder doesn't exist."

### `renameFolder`
`public boolean renameFolder(String oldFolderName, String newFolderName) throws Exception`

- Errors: "DmFileManagerMongo: File database not selected."

### `removeFolder`
`public boolean removeFolder(String folderName) throws Exception`

- Errors: "DmFileManagerMongo: File database not selected."

### `isFolderExists`
`private boolean isFolderExists(String folderName) throws Exception`

- Errors: "DmFileManagerMongo: File database not selected."

### `createFileOnDBFolder`
`public GridFSInputFile createFileOnDBFolder(String folderName) throws Exception`

- Errors: "DmFileManagerMongo: File database not selected." | "DmFileManagerMongo: Folder doesn't exist."

### `createFileOnDBFolder`
`public GridFSInputFile createFileOnDBFolder(String folderName, File file) throws Exception`

- Errors: "DmFileManagerMongo: File database not selected." | "DmFileManagerMongo: Folder doesn't exists."

### `createFileOnDBFolder`
`public GridFSInputFile createFileOnDBFolder(String folderName, InputStream inStream) throws Exception`

- Errors: "DmFileManagerMongo: File database not selected." | "DmFileManagerMongo: Folder doesn't exists."

### `createFileOnDBFolder`
`public GridFSInputFile createFileOnDBFolder(String folderName, String fileName) throws Exception`

- Errors: "DmFileManagerMongo: file database not selected." | "DmFileManagerMongo: folder doesn't exists."

### `saveFileFromStream`
`public boolean saveFileFromStream(String fileName, GridFSInputFile fileToSave) throws Exception`

- Errors: "file database not selected."

### `saveFileNormally`
`public boolean saveFileNormally(String fileName, GridFSInputFile fileToSave) throws Exception`

- Errors: "file database not selected."

### `deleteFile`
`public boolean deleteFile(String folderName, String fileId) throws Exception`

- Errors: "file database not selected." | "folder doesn't exist"

### `deleteFiles`
`public boolean deleteFiles(String folderName, List<String> fileIds) throws Exception`

- Errors: "file database not selected." | "please provide file ids to delete them." | "unable to delete file from specified folder."

### `deleteFiles`
`public boolean deleteFiles(String docId, String sNo, String ruleCode, Connection cnTemp) throws Exception`

- SQL 1: `SELECT fileLink,docId,sNo,ruleCode FROM LINKFILECOLLECTION WHERE (docId=:docId) and (sNo=:sNo) and (ruleCode=:ruleCode)`
- SQL 2: `DELETE FROM LINKFILECOLLECTION WHERE (docId=:docId) and (sNo=:sNo) and (ruleCode=:ruleCode)`
- Params: `:docId` ← `docId`; `:sNo` ← `sNo`; `:ruleCode` ← `ruleCode`; `:docId` ← `docId`; `:sNo` ← `sNo`; `:ruleCode` ← `ruleCode`
- Errors: "DocId not provided to get Uploaded File Link." | "SNo. not provided to get Uploaded File Link." | "RuleCode not provided to get Uploaded File Link." | "Connection is Not found to Update Records." | "Unable to Delete File Link."
- Other calls: `DmBaseShared.xNull`

### `saveFileInMongo`
`public String saveFileInMongo(String fileLinkId, String ruleCode, DmFile_Mdl fileModel) throws Exception`

- Errors: "RuleCode not provided to Upload File." | "File Model not found to Save Record." | "File not found to get Uploaded." | "Unable to create File On specified Directory."
- Other calls: `DmBaseShared.xNull`

### `saveFile`
`public String saveFile(String docId, String sNo, String ruleCode, Connection cnTemp, DmFile_Mdl fileModel, String userName) throws Exception`

- Errors: "DocId not provided to Upload File." | "SNo. not provided to Upload File." | "RuleCode not provided to Upload File." | "Connection is Not found to Update Records." | "File Model not found to Save Record." | "File not found to get Uploaded." | "Unable to create File On specified Directory." | "Unable to save File."
- Other calls: `DmBaseShared.xNull`

### `saveFileDetail`
`private boolean saveFileDetail(GridFSInputFile mongoInFile, Connection cnTemp, String docId, String sNo, String ruleCode, DmFile_Mdl fileModel, long totalFileSize, String userName) throws Exception`

- SQL 1: `INSERT INTO LINKFILECOLLECTION (fileLink,docId,sNo,ruleCode,fileName,fileSize,u_Name,u_EntDt) VALUES (:fileLink,:docId,:sNo,:ruleCode,:fileName,:fileSize,:userName,:u_EntDt)`
- Params: `:fileLink` ← `mongoInFile.getId().toString()`; `:docId` ← `docId`; `:sNo` ← `sNo`; `:ruleCode` ← `ruleCode`; `:fileName` ← `fileModel.getFileName()`; `:fileSize` ← `DmBaseShared.iNull(totalFileSize).toString()`; `:userName` ← `userName`; `:u_EntDt` ← `new java.sql.Date((new java.util.Date()).getTime())`
- Other calls: `DmBaseShared.iNull`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.server`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmFileManager.shared`, `dataman.dmBase.dmPrepareStatement.server`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
