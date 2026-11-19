# dm-toolkit / dmFileUploadButton

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/dmFileUploadButton/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
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
| client/DmFileUploadButton.java | 756 |
| client/DmFileUploadResources.java | 16 |
| servlets/DmFileDownloader_Srvlt.java | 133 |
| servlets/DmFileUploadButton_Srvlt.java | 92 |
| servlets/DmFileViewer_Srvlt.java | 43 |
| shared/IFileUploadNotifier.java | 11 |

## Widget API (auto-extracted from client classes)

### DmFileUploadButton (DmFileUploadButton.java, 753 lines)
extends/implements: `Composite`

Constructors:
- `DmFileUploadButton()`
- `DmFileUploadButton(IFileUploadNotifier notifier, DmFileRuleType fileRuleType)`
- `DmFileUploadButton(IFileUploadNotifier notifier, DmFileRuleType fileRuleType, CallBack callBack)`

Public methods:
- `String getUploadURL(DmFile selectedFile)`
- `void onUploadSuccess(DmUploadSuccessEvent uploadSuccessEvent)`
- `void onUploadError(DmUploadErrorEvent uploadErrorEvent)`
- `void execute()`
- `int toInt()`
- `int toInt()`
- `String toString()`
- `void setNotifier(IFileUploadNotifier notifier)`
- `IFileUploadNotifier getNotifier()`
- `void onClick(ClickEvent event)`
- `void setSize(String width, String height)`
- `DmStats getStats()`
- `void startUpload()`
- `void cancelUpload(String fileId, boolean triggerErrorEvent)`
- `void cancelUpload()`
- `Button getButton()`
- `void cancelUpload(String fileId)`
- `void cancelUpload(boolean triggerErrorEvent)`
- `boolean isAjaxUploadWithProgressEventsSupported()`
- `void onChange(ChangeEvent event)`
- `void addFilesToQueue(JsArray files)`
- `native JsArray getDroppedFiles(NativeEvent event)`

Nested: interface `CallBack`, enum `ButtonAction`, enum `Cursor`, enum `WindowMode`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmFileManager.shared`, `dataman.dmBase.dmFileManagerMongo.server`, `dataman.dmBase.dmSession.client`, `dataman.dmBase.dmSession.server`, `dataman.dmToolkit.dmFileUploadButton.shared`, `dataman.dmToolkit.dmFileUploader.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.self.server`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
Upload button widget (13 jagah): file chun kar server ke temp folder me bhejta hai (`DmFileUpload_Srvlt`), rule/size/type ke hisaab se (`DmFileRuleType`). Record save par `AttachmentService` (dm-base) Mongo me rakhta hai. Frontend: `<FileUploadButton rule maxSize accept />`.
