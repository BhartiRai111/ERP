# dm-toolkit / dmFileManager

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/dmFileManager/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: linkFileCollection
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: -
- CSV export: no

## Files
| file | lines |
|---|---|
| client/DmAttachment.java | 403 |
| client/DmAttachment_Dsg.java | 125 |
| client/DmFileManager.java | 186 |
| client/DmFileManager_Dsg.java | 101 |
| client/DmFileType.java | 321 |
| client/DmFileType_Dsg.java | 76 |
| server/DmFileManager_Server.java | 39 |
| server/DmFileManager_SrvImpl.java | 484 |
| shared/DmFileManager_Srv.java | 26 |
| shared/DmFileManager_SrvAsync.java | 26 |

## RPC interface
| method | params | returns |
|---|---|---|
| getFileType | String keyCode, String tempKeyCode, String sNo, String ruleCode, String fileTypeName, HashMap<String, String> multiDocIdWithRuleCode, List<DmFileParameter_Mdl> listFileParameter | DmResult<List<DmFileRuleType>> |
| allocateResource | List<DmAttachment_Mdl> attachment | DmResult<List<DmAttachment_Mdl>> |
| deleteFile | DmAttachment_Mdl fileAttachment | DmResult<Boolean> |
| saveEditedFile | DmAttachment_Mdl fileAttachment, DmFile_Mdl fileModel | DmResult<DmAttachment_Mdl> |
| getActualImageSize | DmAttachment_Mdl fileAttachment | DmResult<DmSize> |
| updateRemarks | DmAttachment_Mdl fileAttachment, String remarks | DmResult<String> |

## Server `DmFileManager_Server.java` (39 lines)
Helpers used: `DmShared.iNull`

### `manageLinkFileCollection`
`public static void manageLinkFileCollection(String docId, long sNo, String tempDocId, DmRSS rss, Connection cnTemp) throws Exception`

- SQL 1: `Update linkFileCollection set docId = :docId, sNo = :sNo, tempDocId = null, sessionId = null where tempDocId = :tempDocId and sessionId = :sessionId ;`
- Params: `:docId` ← `DmShared.iNull(docId).longValue()`; `:sNo` ← `sNo`; `:tempDocId` ← `tempDocId`; `:sessionId` ← `sessionId`

## Server `DmFileManager_SrvImpl.java` (484 lines)
Helpers used: `DmServer.getConnection`, `DmServer.getConnectionMongo`, `DmServer.getParamList`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnFileDBName`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.iNull`, `DmShared.xNull`

### `allocateResource`
`public DmResult<List<DmAttachment_Mdl>> allocateResource(List<DmAttachment_Mdl> listAttachments)`

- Other calls: `DmDateTimeFormat.getFormat`, `UUID.randomUUID`

### `deleteFile`
`public DmResult<Boolean> deleteFile(DmAttachment_Mdl fileAttachment)`

- SQL 1: `Delete from linkFileCollection where fileLink = :fileLink`
- Params: `:fileLink` ← `fileAttachment.getFileLink()`
- Errors: "Unable to delete Files from Database."

### `saveEditedFile`
`public DmResult<DmAttachment_Mdl> saveEditedFile(DmAttachment_Mdl fileAttachment, DmFile_Mdl fileModel)`

- Errors: "File not found : "

### `getActualImageSize`
`public DmResult<DmSize> getActualImageSize(DmAttachment_Mdl fileAttachment)`


### `getFileType`
`public DmResult<List<DmFileRuleType>> getFileType(String keyCode, String tempKeyCode, String sNo, String ruleCode, String fileTypeName, HashMap<String, String> multiDocIdWithRuleCode, List<DmFileParameter_Mdl> listFileParameter)`

- SQL 1: `Select ftm.fileType, max(ftm.maxSize) as maxSize, max(ftm.extFilters) as extFilters from fileTypeMast as ftm where lower(ftm.ruleCode) in ( {…} ) {…} {…} {…} and (lower(ftm.fileType) = :fileTypeName) group by ftm.fileType order by ftm.fileType`
- Params: `:fileTypeName` ← `fileTypeName.toLowerCase()`
- Other calls: `Collectors.toList`, `Comparator.comparing`

### `getAttachedFiles`
`public List<DmAttachment_Mdl> getAttachedFiles(String keyCode, String tempKeyCode, String sNo, String ruleCode, String sessionId, Connection cnTemp)`

- Params: `:docId` ← `DmShared.iNull(keyCode).longValue()`; `:tempDocId` ← `tempKeyCode`; `:sessionId` ← `sessionId`; `:sNo` ← `DmShared.iNull(sNo).longValue()`; `:ruleCode` ← `ruleCode.toLowerCase()`
- Other calls: `DmDateTimeFormat.getFormat`

### `getAttachedFilesOther`
`public List<DmAttachment_Mdl> getAttachedFilesOther(String docIds, String ruleCode, Connection cnTemp)`

- SQL 1: `Select lfc.docId, lfc.sNo, lfc.sessionId, lfc.fileLink, lfc.ruleCode, lfc.fileType, lfc.fileName, lfc.fileSize, lfc.remarks, lfc.u_Name, lfc.u_EntDt from linkFileCollection as lfc where lfc.ruleCode = :ruleCode and lfc.docId in ( {…} :docId {…} ) order by lfc.u_EntDt desc`
- Params: `:ruleCode` ← `ruleCode`
- Other calls: `DmDateTimeFormat.getFormat`

### `updateRemarks`
`public DmResult<String> updateRemarks(DmAttachment_Mdl fileAttachment, String remarks)`

- SQL 1: `Update linkFileCollection set remarks = :remarks where fileLink = :fileLink`
- Params: `:fileLink` ← `fileAttachment.getFileLink()`; `:docId` ← `fileAttachment.getDocId()`; `:tempDocId` ← `fileAttachment.getTempDocId()`; `:sessionId` ← `fileAttachment.getSessionId()`; `:remarks` ← `remarks`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmFileManager.shared`, `dataman.dmBase.dmFileManagerMongo.server`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.client`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFileManager.shared`, `dataman.dmToolkit.dmFileUploader.client`, `dataman.dmToolkit.dmImageCanvas.client`, `dataman.dmToolkit.dmInputBox.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.self.server`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| deleteFile | `if (!mongoCon.deleteFile(fileAttachment.getRuleCode(), fileAttachment.getFileLink()))` | "Unable to delete Files from Database." |
| saveEditedFile | `if (!file.exists())` | "File not found : " |

## Notes (hand-written)
Attachment ka server hissa (`linkFileCollection` table): `getFileType` (rule se allowed type), `allocateResource`, `deleteFile`, `saveEditedFile` (image edit; **"File not found : "**), `getActualImageSize`, `updateRemarks` (file ki remark). Upload ki file pehle session ke temp folder me aati hai aur record save par Mongo GridFS me jaati hai (dekhein `dm-base` `AttachmentService`). Frontend: `<FileUploadButton rule />` + attachment list (remark, delete, preview).
