# pb-st-plugin / radiology

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/stPlugin/src/radiology/`). Hand-written notes are in the "Notes" section.

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
| DmProcess.java | 102 |
| DocumentFindAndReplace.java | 249 |
| RadiologyDocCommands.java | 322 |
| RadiologyReportDownloader.java | 100 |
| RadiologyReportUploader.java | 103 |

## Depends on (imports from other packages)
`dataman.dmPlugin.CFunctions`, `dataman.dmPlugin.server.NanoHTTPD`, `dataman.dmPlugin.server.router`, `dataman.stPlugin.STFunctions`

## Classes (plain classes: auto-extracted)

### DmProcess (DmProcess.java, 102 lines)

Public methods:
- `List<Integer> getProcessIds()`
- `void killAllChildProcesses(List<Integer> listChildProcessesId)`

### DocumentFindAndReplace (DocumentFindAndReplace.java, 249 lines)

Public methods:
- `List<Text> getTextElements()`
- `int getStartIndexOfVariableInFirstTextElement()`
- `void setStartIndexOfVariableInEndTextElement(int startIndexOfVariable)`
- `int getStartIndexOfVariableInEndTextElement()`
- `void setStartIndexOfVariableInFirstTextElement(int startIndexOfVariable)`
- `String getVariableName()`
- `void setVariableName(String variableName)`
- `String getOldVariableValue()`
- `void setOldVariableValue(String oldVariableValue)`
- `String getNewVariableValue()`
- `void setNewVariableValue(String newVariableValue)`
- `void processFindAndReplace()`
- `void searchAndReplace(Map<String, String> searchMappings)`
- `void saveDocumentToFilePath(String target)`
- `void saveDocumentToFilePath(File target)`

### RadiologyDocCommands (RadiologyDocCommands.java, 319 lines)
class; extends/implements: `RouterNanoHTTPD.DefaultHandler`

Public methods:
- `NanoHTTPD.Response post(RouterNanoHTTPD.UriResource uriResource, Map<String, String> urlParams, NanoHTTPD.IHTTPSession session)`
- `String getText()`
- `NanoHTTPD.Response.IStatus getStatus()`
- `String getMimeType()`

### RadiologyReportDownloader (RadiologyReportDownloader.java, 100 lines)
class; extends/implements: `Runnable`

Public methods:
- `void run()`

### RadiologyReportUploader (RadiologyReportUploader.java, 103 lines)
class; extends/implements: `Runnable`

Public methods:
- `RadiologyDocumentUploadRequest_Mdl runAfterProcess()`
- `void run()`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
