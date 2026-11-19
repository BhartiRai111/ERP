# dm-toolkit / dmFileUploader

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/dmFileUploader/`). Hand-written notes are in the "Notes" section.

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
| client/DmConfigurable.java | 80 |
| client/DmFile.java | 115 |
| client/DmStats.java | 31 |
| client/DmUploader.java | 1300 |
| client/events/DmFileDialogCompleteEvent.java | 32 |
| client/events/DmFileDialogCompleteHandler.java | 10 |
| client/events/DmFileDialogStartEvent.java | 11 |
| client/events/DmFileDialogStartHandler.java | 10 |
| client/events/DmFileQueueErrorEvent.java | 69 |
| client/events/DmFileQueueErrorHandler.java | 10 |
| client/events/DmFileQueuedEvent.java | 20 |
| client/events/DmFileQueuedHandler.java | 10 |
| client/events/DmSWFUploadLoadedEvent.java | 11 |
| client/events/DmSWFUploadLoadedHandler.java | 10 |
| client/events/DmUploadCompleteEvent.java | 14 |
| client/events/DmUploadCompleteHandler.java | 10 |
| client/events/DmUploadErrorEvent.java | 81 |
| client/events/DmUploadErrorHandler.java | 10 |
| client/events/DmUploadEvent.java | 21 |
| client/events/DmUploadProgressEvent.java | 29 |
| client/events/DmUploadProgressHandler.java | 10 |
| client/events/DmUploadStartEvent.java | 14 |
| client/events/DmUploadStartHandler.java | 10 |
| client/events/DmUploadSuccessEvent.java | 75 |
| client/events/DmUploadSuccessHandler.java | 10 |

## Widget API (auto-extracted from client classes)

### DmConfigurable (DmConfigurable.java, 78 lines)

Public methods:
- `T setOption(String path, Object value)`
- `JSONObject getOptions()`

### DmFile (DmFile.java, 114 lines)
extends/implements: `JavaScriptObject`


Public methods:
- `int toInt()`
- `native String getServerResourceId()`
- `native void setServerResourceId(String serverResourceId)`
- `native String getId()`
- `native int getIndex()`
- `native String getName()`
- `long getSize()`
- `native String getType()`
- `Date getModificationDate()`
- `Status getStatus()`
- `native double getCurrentSpeed()`
- `native double getAverageSpeed()`
- `native double getMovingAverageSpeed()`
- `native double getTimeRemaining()`
- `native double getTimeElapsed()`
- `native double getPercentUploaded()`
- `native double getSizeUploaded()`

Nested: enum `Status`

### DmStats (DmStats.java, 30 lines)
extends/implements: `JavaScriptObject`


Public methods:
- `native int getUploadsInProgress()`
- `native int getFilesQueued()`
- `native int getSuccessfulUploads()`
- `native int getUploadErrors()`
- `native int getUploadsCancelled()`
- `native int getQueueErrors()`

### DmUploader (DmUploader.java, 1188 lines)
extends/implements: `AbsolutePanel`

Constructors:
- `DmUploader()`

Public methods:
- `int toInt()`
- `int toInt()`
- `String toString()`
- `DmUploader setUploadURL(String uploadURL)`
- `DmUploader setAjaxUploadURL(String ajaxUploadURL)`
- `DmUploader setAjaxUploadEnabled(boolean ajaxUploadEnabled)`
- `DmUploader setFilePostName(String filePostName)`
- `DmUploader setPostParams(JSONObject postParams)`
- `DmUploader setUseQueryString(boolean useQueryString)`
- `DmUploader setPreserveRelativeURLs(boolean preserveRelativeURLs)`
- `DmUploader setRequeueOnError(boolean requeueOnError)`
- `DmUploader setHttpSuccess(long... httpSuccess)`
- `DmUploader setAssumeSuccessTimeout(long assumeSuccessTimeout)`
- `DmUploader setFileTypes(String fileTypes)`
- `DmUploader setFileTypesDescription(String fileTypesDescription)`
- `DmUploader setFileSizeLimit(String fileSizeLimit)`
- `DmUploader setFileUploadLimit(long fileUploadLimit)`
- `DmUploader setFileQueueLimit(long fileQueueLimit)`
- `DmUploader setPreventSWFCaching(boolean preventSWFCaching)`
- `DmUploader setDebug(boolean debug)`
- `DmUploader setButtonImageURL(String buttonImageURL)`
- `DmUploader setButtonWidth(int buttonWidth)`
- `DmUploader setButtonHeight(int buttonHeight)`
- `DmUploader setButtonText(String buttonText)`
- `DmUploader setButtonTextStyle(String buttonTextStyle)`
- `DmUploader setButtonTextTopPadding(int buttonTextTopPadding)`
- `DmUploader setButtonTextLeftPadding(int buttonTextLeftPadding)`
- `DmUploader setButtonAction(ButtonAction buttonAction)`
- `DmUploader setButtonDisabled(boolean buttonDisabled)`
- `boolean getButtonDisabled()`
- `DmUploader setButtonCursor(Cursor cursor)`
- `DmUploader setButtonWindowMode(WindowMode buttonWindowMode)`
- `DmUploader setCustomSettings(JSONObject customSettings)`
- `JSONObject getCustomSettings()`
- `DmStats getStats()`
- `DmUploader setSWFUploadLoadedHandler(DmSWFUploadLoadedHandler swfUploadLoadedHandler)`
- `DmUploader setFileDialogStartHandler(DmFileDialogStartHandler fileDialogStartHandler)`
- `DmUploader setFileQueuedHandler(DmFileQueuedHandler fileQueuedHandler)`
- `DmUploader setFileQueueErrorHandler(DmFileQueueErrorHandler fileQueueErrorHandler)`
- `DmUploader setFileDialogCompleteHandler(DmFileDialogCompleteHandler fileDialogCompleteHandler)`
- `DmUploader setUploadCompleteHandler(DmUploadCompleteHandler uploadCompleteHandler)`
- `DmUploader setUploadErrorHandler(DmUploadErrorHandler uploadErrorHandler)`
- `DmUploader setUploadProgressHandler(DmUploadProgressHandler uploadProgressHandler)`
- `DmUploader setUploadStartHandler(DmUploadStartHandler uploadStartHandler)`
- `DmUploader setUploadSuccessHandler(DmUploadSuccessHandler uploadSuccessHandler)`
- `DmUploader setOption(String path, Object value)`
- `void startUpload()`
- `void cancelUpload(String fileId, boolean triggerErrorEvent)`
- `void cancelUpload()`
- `void cancelUpload(String fileId)`
- `void cancelUpload(boolean triggerErrorEvent)`
- `boolean isAjaxUploadWithProgressEventsSupported()`
- `void onClick(ClickEvent event)`
- `void onMouseOver(MouseOverEvent event)`
- `void onMouseOut(MouseOutEvent event)`
- `void onMouseDown(MouseDownEvent event)`
- `void onMouseUp(MouseUpEvent event)`
- `void onChange(ChangeEvent event)`
- `void addFilesToQueue(JsArray files)`
- `native JsArray getDroppedFiles(NativeEvent event)`

Nested: enum `ButtonAction`, enum `Cursor`, enum `WindowMode`

### DmFileDialogCompleteEvent (DmFileDialogCompleteEvent.java, 31 lines)
Constructors:
- `DmFileDialogCompleteEvent(int numberOfFilesSelected, int numberOfFilesQueued, int totalFilesInQueue)`

Public methods:
- `int getNumberOfFilesQueued()`
- `int getNumberOfFilesSelected()`
- `int getTotalFilesInQueue()`

### DmFileDialogCompleteHandler (DmFileDialogCompleteHandler.java, 9 lines)

Public methods:
- `boolean onFileDialogComplete(DmFileDialogCompleteEvent fileDialogCompleteEvent)`

### DmFileDialogStartEvent (DmFileDialogStartEvent.java, 10 lines)
Constructors:
- `DmFileDialogStartEvent()`

### DmFileDialogStartHandler (DmFileDialogStartHandler.java, 9 lines)

Public methods:
- `boolean onFileDialogStartEvent(DmFileDialogStartEvent fileDialogStartEvent)`

### DmFileQueueErrorEvent (DmFileQueueErrorEvent.java, 68 lines)
Constructors:
- `DmFileQueueErrorEvent(DmFile file, int errorCode, String message)`

Public methods:
- `int toInt()`
- `DmFile getFile()`
- `ErrorCode getErrorCode()`
- `int getErrorCodeAsInt()`
- `String getMessage()`

Nested: enum `ErrorCode`

### DmFileQueueErrorHandler (DmFileQueueErrorHandler.java, 9 lines)

Public methods:
- `boolean onFileQueueError(DmFileQueueErrorEvent fileQueueErrorEvent)`

### DmFileQueuedEvent (DmFileQueuedEvent.java, 19 lines)
Constructors:
- `DmFileQueuedEvent(DmFile file)`

Public methods:
- `DmFile getFile()`

### DmFileQueuedHandler (DmFileQueuedHandler.java, 9 lines)

Public methods:
- `boolean onFileQueued(DmFileQueuedEvent fileQueuedEvent)`

### DmSWFUploadLoadedEvent (DmSWFUploadLoadedEvent.java, 10 lines)
Constructors:
- `DmSWFUploadLoadedEvent()`

### DmSWFUploadLoadedHandler (DmSWFUploadLoadedHandler.java, 9 lines)

Public methods:
- `boolean onSWFUploadLoaded(DmSWFUploadLoadedEvent swfUploadLoadedEvent)`

### DmUploadCompleteEvent (DmUploadCompleteEvent.java, 13 lines)
extends/implements: `DmUploadEvent`

Constructors:
- `DmUploadCompleteEvent(DmFile file)`

### DmUploadCompleteHandler (DmUploadCompleteHandler.java, 9 lines)

Public methods:
- `boolean onUploadComplete(DmUploadCompleteEvent uploadCompleteEvent)`

### DmUploadErrorEvent (DmUploadErrorEvent.java, 80 lines)
extends/implements: `DmUploadEvent`

Constructors:
- `DmUploadErrorEvent(DmFile file, int errorCode, String message)`

Public methods:
- `int toInt()`
- `ErrorCode getErrorCode()`
- `int getErrorCodeAsInt()`
- `String getMessage()`

Nested: enum `ErrorCode`

### DmUploadErrorHandler (DmUploadErrorHandler.java, 9 lines)

Public methods:
- `boolean onUploadError(DmUploadErrorEvent uploadErrorEvent)`

### DmUploadEvent (DmUploadEvent.java, 20 lines)

Public methods:
- `DmFile getFile()`

### DmUploadProgressEvent (DmUploadProgressEvent.java, 28 lines)
extends/implements: `DmUploadEvent`

Constructors:
- `DmUploadProgressEvent(DmFile file, long bytesComplete, long bytesTotal)`

Public methods:
- `long getBytesComplete()`
- `long getBytesTotal()`

### DmUploadProgressHandler (DmUploadProgressHandler.java, 9 lines)

Public methods:
- `boolean onUploadProgress(DmUploadProgressEvent uploadProgressEvent)`

### DmUploadStartEvent (DmUploadStartEvent.java, 13 lines)
extends/implements: `DmUploadEvent`

Constructors:
- `DmUploadStartEvent(DmFile file)`

### DmUploadStartHandler (DmUploadStartHandler.java, 9 lines)

Public methods:
- `boolean onUploadStart(DmUploadStartEvent uploadStartEvent)`

### DmUploadSuccessEvent (DmUploadSuccessEvent.java, 71 lines)
extends/implements: `DmUploadEvent`

Constructors:
- `DmUploadSuccessEvent(DmFile file, String serverData, String responseReceived)`

Public methods:
- `String getServerData()`
- `String getResponseReceived()`

### DmUploadSuccessHandler (DmUploadSuccessHandler.java, 9 lines)

Public methods:
- `boolean onUploadSuccess(DmUploadSuccessEvent uploadSuccessEvent)`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.shared`, `dataman.dmToolkit.dmFileUploader.client`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
