# dm-base / dataman_dmBase_self

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmBase/src/dataman_dmBase_self/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: User Name (Text); Administrator (Text); eMail (Text); Is Active (Text)
- Lookups/suggestions: getHeaderFontSize, getHeaders
- CSV export: no

## Files
| file | lines |
|---|---|
| server/DmApiBase.java | 170 |
| server/DmCsvFileFormat_Mdl.java | 67 |
| server/DmCsvReader.java | 207 |
| server/DmCsvToExcelWriter.java | 243 |
| server/DmFontFace.java | 54 |
| server/DmFontResourceProvider_Srvlt.java | 114 |
| server/DmHandelFile.java | 43 |
| server/DmIJasperCompiled.java | 12 |
| server/DmJasperCompiler.java | 338 |
| server/DmJasperStyle.java | 94 |
| server/DmJsonRequest.java | 58 |
| server/ExecCommand.java | 193 |
| server/ZipUtils.java | 93 |
| shared/CodeValueEnum.java | 9 |
| shared/Company.java | 150 |
| shared/DmBaseURL.java | 26 |
| shared/DmCallBack.java | 7 |
| shared/DmCaseInsensitiveComparator.java | 35 |
| shared/DmClientConfig_Mdl.java | 59 |
| shared/DmComprator.java | 17 |
| shared/DmDateTimeFormat.java | 1341 |
| shared/DmDynamicGridRow.java | 18 |
| shared/DmEnum.java | 4581 |
| shared/DmException.java | 33 |
| shared/DmFilter.java | 67 |
| shared/DmHelper.java | 170 |
| shared/DmLocalSettingPrintMap_Mdl.java | 86 |
| shared/DmLocalSettingPrinter_Mdl.java | 64 |
| shared/DmPageSize.java | 54 |
| shared/DmPaging.java | 130 |
| shared/DmPluginPingModel.java | 26 |
| shared/DmPluginSessionModel.java | 22 |
| shared/DmPrintToPrinter_Mdl.java | 32 |
| shared/DmReportProperty_Mdl.java | 90 |
| shared/DmResult.java | 66 |
| shared/DmResultPageing.java | 22 |
| shared/DmSQLParam.java | 146 |
| shared/DmSQLWithParam.java | 105 |
| shared/DmSize.java | 54 |
| shared/DmSort.java | 40 |
| shared/FieldUDT_Mdl.java | 65 |
| shared/IWebCamera.java | 7 |
| shared/KeyField.java | 160 |
| shared/KeyFieldExtended.java | 32 |
| shared/KeyFieldHashCode_T_PrpAcs.java | 19 |
| shared/KeyField_PrpAcs.java | 19 |
| shared/KeyField_PrpAcsAll.java | 41 |
| shared/KeyField_T_PrpAcs.java | 16 |
| shared/KeyField_T_PrpAcsNew.java | 19 |
| shared/MenuLink.java | 24 |
| shared/PCSData.java | 83 |
| shared/PLT.java | 48 |
| shared/ProcessMessageQueue_Mdl.java | 53 |
| shared/ProcessMessage_Mdl.java | 78 |
| shared/RecId.java | 55 |
| shared/ServiceInvoker.java | 37 |
| shared/SetWidgetInvoker.java | 16 |
| shared/SetWidgetInvokerExtended.java | 14 |
| shared/Site.java | 38 |
| shared/UserMaster_Mdl.java | 212 |
| shared/UserMaster_Vld.java | 15 |
| shared/UserMenu.java | 158 |
| shared/UserPermission.java | 66 |
| shared/VoucherPermission.java | 75 |
| shared/VoucherPermissionHandler.java | 24 |
| xls/server/DmXlsWriter.java | 168 |

## Models (shared DTOs)
### DmClientConfig_Mdl
| type | field |
|---|---|
| String | systemCompanyCode |
| String | pltCode |
| DmEnum.DmLocaleCountry | localeCountry |
| DmEnum.TaxType | taxType |
| CurrencyFormat | currencyFormat |

### DmPrintToPrinter_Mdl
| type | field |
|---|---|
| DmReportProperty_Mdl | reportProperty |
| JasperPrint | jasperPrint |

### DmReportProperty_Mdl
| type | field |
|---|---|
| int | noOfPrint |
| boolean | printToPrinter |
| boolean | processMessage |
| DmExportType | exportType |
| String | printerCode |
| String | constPrefixName |
| String | constSuffixName |
| ProcessMessageQueue_Mdl | processMsgQueue_Mdl |

### DmLocalSettingPrintMap_Mdl
| type | field |
|---|---|
| String moduleName, menuText, reportName, | printerCode |
| String | fieldUniqueId |

### DmLocalSettingPrinter_Mdl
| type | field |
|---|---|
| String printerCode, | printerName |
| DmEnum.DmPrinterImageType | printerImageType |

### ProcessMessage_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | accountCode |
| String | accountName |
| String | sendToName |
| String | sendToEmail |
| String | sendToCC |
| String | sendToBCC |
| String | sendToMobile |

### ProcessMessageQueue_Mdl
| type | field |
|---|---|
| String | widgetName |
| String | templateCodeCSV |
| String | templateNameCSV |
| List<ProcessMessage_Mdl> | processMessageMdl_lst |

### FieldUDT_Mdl
| type | field |
|---|---|
| String | fieldCaption |
| String | text |
| String | tag |
| String | tag2nd |

### UserMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | userId |
| String | roleCode |
| String | roleName |
| String | description |
| String | lockTimeOut |
| String | administratorYNCode |
| String | administratorYNName |
| String | activeYNCode |
| String | activeYNName |
| String | linkedAccountCode |
| String | linkedAccountName |
| String | natureCode |
| String | natureName |
| String | oldPassword |
| String | newPassword |
| String | confirmPassword |
| String | email |
| String | mobile |
| String | cpUserName |
| String | cpUserNameCode |
| String | gridLinkedUser |
| String | tokenKey |

## Validator `UserMaster_Vld`
```java
public class UserMaster_Vld {
    public static void chkSave(UserMaster_Mdl mrTemp) {
        DmBaseShared.isValidText(mrTemp.getSharedKeyFieldName(), "User Name", 100);
        DmBaseShared.isValidText(mrTemp.getAdministratorYNCode(), "Administrator", 200);
        DmBaseShared.isValidText(mrTemp.getEmail(), "eMail", 300);
        DmBaseShared.isValidEMail(mrTemp.getEmail(), 400);
        DmBaseShared.isValidText(mrTemp.getActiveYNCode(), "Is Active", 500);
    }
}
```

## Server `DmCsvToExcelWriter.java` (243 lines)
### `getCsvFiles`
`public List<String> getCsvFiles()`


### `startUp`
`private void startUp(String outputFilePath, String csvFolderPath, List<DmCsvFileFormat_Mdl> csvFiles) throws Exception`

- Errors: "Unable to create output file directories." | "Neither csv folder path nor csv files are provided."
- Other calls: `DmBaseShared.xNull`

### `filterCsvFiles`
`private void filterCsvFiles(String csvFolderPath) throws Exception`


### `startWriting`
`public void startWriting() throws Exception`

- Other calls: `Workbook.createWorkbook`

### `writeHeaders`
`public void writeHeaders(WritableSheet workSheet, List<String> lstHeaders, int colIndexStartFrom, int rowIndexStartFrom) throws Exception`


### `writeContents`
`public void writeContents(WritableSheet workSheet, TreeMap<String, DmCsvFileFormat_Mdl.CsvContentType> declaredColumns, List<String> lstHeaders, List<DmDynamicGridRow> rows, int colIndexStartFrom, int rowIndexStartFrom) throws Exception`

- Other calls: `DmBaseShared.vNull`, `DmBaseShared.xNull`

## Server `DmCsvFileFormat_Mdl.java` (67 lines)
### `hashCode`
`public int hashCode()`


### `equals`
`public boolean equals(Object obj)`


## Server `DmJsonRequest.java` (58 lines)
Helpers used: `DmBaseServer.convertFromJson`

### `sendRequest`
`public <T> DmJsonResponse_Mdl<T> sendRequest(String jsonString, String sessionId, String targetURL, TypeToken<DmJsonResponse_Mdl<T>> typeToken) throws Exception`

- Other calls: `DmBaseShared.xNull`

## Server `DmCsvReader.java` (207 lines)
### `readLines`
`public void readLines(Callback callback, boolean strictQuotes) throws Exception`

- Errors: "File not provided" | "File not found."
- Other calls: `DmBaseShared.removeSpace`, `DmBaseShared.xNull`

## Server `ZipUtils.java` (93 lines)
### `zipIt`
`public void zipIt()`


### `generateFileList`
`private void generateFileList(File node)`


## Server `DmApiBase.java` (170 lines)
### `startProcessApiRequest`
`public Response startProcessApiRequest(String url, HTTP_METHOD http_method, String content_type, String requestData, HashMap<String, String> headerList) throws Exception`

- Other calls: `MediaType.parse`, `RequestBody.create`

### `startProcessApiRequest`
`public Response startProcessApiRequest(String url, HTTP_METHOD http_method, String content_type, File requestFile, HashMap<String, String> headerList) throws Exception`

- Other calls: `MediaType.parse`, `MultipartBody.Builder`, `RequestBody.create`

### `startProcessApiRequest`
`public Response startProcessApiRequest(String url, HTTP_METHOD http_method, String content_type, RequestBody requestBody, HashMap<String, String> headerList) throws Exception`

- Other calls: `Request.Builder`

### `getCertifiedClient`
`private OkHttpClient getCertifiedClient(String security_context_instance, int connectTimeout, TimeUnit connectTimeOutUnit, int writeTimeout, TimeUnit writeTimeOutUnit, int readTimeout, TimeUnit readTimeOutUnit) throws Exception`

- Other calls: `OkHttpClient.Builder`, `SSLContext.getInstance`

### `getContent_Type`
`public static String getContent_Type(String extension)`


## Server `DmHandelFile.java` (43 lines)
### `startUp`
`private void startUp(String filePath, boolean createWhenNotExists) throws Exception`


## Server `DmJasperStyle.java` (94 lines)
## Server `DmFontResourceProvider_Srvlt.java` (114 lines)
### `processRequest`
`private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException`

- Other calls: `DmBaseShared.xNull`

### `searchFileFromDirectory`
`private File searchFileFromDirectory(File parentDirectory, String fontName) throws Exception`

- Other calls: `Font.createFont`

### `prepareRegionalCss`
`private StringBuffer prepareRegionalCss(String clientUrl)`

- Other calls: `RegionalLanguage.values`

## Server `DmIJasperCompiled.java` (12 lines)
## Server `DmJasperCompiler.java` (338 lines)
### `parseStyles`
`private void parseStyles(DmJasperStyle... lstStyles)`


### `visitSubreport`
`public void visitSubreport(JRSubreport subreport)`


### `visitTextField`
`public void visitTextField(JRTextField textField)`

- Other calls: `DmBaseShared.xNull`

### `compileReport`
`public void compileReport() throws Exception`


### `compileSubReport`
`private void compileSubReport(JRExpression subReportExpression) throws Exception`

- Other calls: `JRLoader.loadObject`

### `saveCompiledJasperReport`
`public static synchronized boolean saveCompiledJasperReport(JasperReport reportObjectToSave, String fullReportPathToSave) throws Exception`


### `compileJasper`
`private JasperReport compileJasper(File reportFilePath, JasperReport compiledJasperReport) throws Exception`

- Other calls: `DmBaseShared.xNull`, `JasperCompileManager.compileReport`

## Server `ExecCommand.java` (193 lines)
### `getOutput`
`public String getOutput()`


### `getError`
`public String getError()`


### `makeArray`
`private String[] makeArray(String command)`


## Server `DmFontFace.java` (54 lines)
### `equals`
`public boolean equals(Object obj)`


### `hashCode`
`public int hashCode()`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.server`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmDynamicReportBuilder.shared`, `dataman.dmBase.dmSession.client`, `dataman.dmBase.jvmSerialization.shared`, `dataman.dmBase.self.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
