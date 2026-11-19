# dm-base / dataman_dmBase_dmScheduler

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmBase/src/dataman_dmBase_dmScheduler/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: schedulerMast
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: -
- CSV export: no

## Files
| file | lines |
|---|---|
| server/DmAnonymousWebJobFactory.java | 89 |
| server/DmAutoScheduler.java | 41 |
| server/DmEMailWebJob.java | 232 |
| server/DmMapFormat.java | 281 |
| server/DmMessageParam.java | 30 |
| server/DmObjectLock.java | 44 |
| server/DmSMSWebJob.java | 183 |
| server/DmScheduler.java | 230 |
| server/DmSchedulerEventInvoker.java | 87 |
| server/DmSchedulerFactory.java | 258 |
| server/DmWebJob.java | 42 |
| server/DmWebJobDataRow.java | 125 |
| server/DmWebJobResult.java | 36 |
| server/DmWebJobTemplateEngine.java | 141 |
| server/DmWebJobTemplateInfo.java | 601 |
| server/DmWhatsappWebJob.java | 149 |
| server/IDmWebJob.java | 12 |
| shared/Configuration_Mdl.java | 138 |
| shared/GrdImage_Mdl.java | 49 |
| shared/SchedulerMast_Mdl.java | 360 |
| shared/TemplateMast_Mdl.java | 127 |
| shared/WebJobAttachment_Mdl.java | 106 |
| shared/WebJobResult_Mdl.java | 165 |

## Models (shared DTOs)
### WebJobResult_Mdl
| type | field |
|---|---|
| String | fromEmailId |
| String | subject |
| String | bodyText |
| String | uniqueId |
| List<String> | lstTo |
| List<String> | lstBcc |
| List<String> | lstCc |
| List<String> | lstAttachments |
| String | fromMobile |
| String | url |
| String | format |
| String | resultMsg |
| long | maxlength |
| List<String> | lstSMSMobileNo |
| String | mediaUrl |

### TemplateMast_Mdl
| type | field |
|---|---|
| Long | tempId |
| String | tempName |
| String | externalTemplate |
| DmEnum.DmWebJobTypes | jobType |
| DmEnum.DmWebJobDataSources | dataSource |
| String | msgFormat |
| String | fileorfuncName |
| String | subjectFormat |
| List<String> | lstEmailTo |
| List<String> | lstEmailCc |
| List<String> | lstEmailBcc |
| List<String> | lstSMSMobileNo |

### SchedulerMast_Mdl
| type | field |
|---|---|
| Long | docId |
| String | name |
| PLT | plt |
| DmWebJobTypes | webJobType |
| Long | webJobTemplateId |
| Integer | v_Prefix |
| String | activeDbName |
| String | activeFileDbName |
| String | voucherPrefix |
| Date | startDateTime |
| Date | expDateTime |
| Date | lastExecTime |
| boolean | expUsed |
| boolean | active |
| int | recurQuan |
| String | dayOrmonth |
| DmRecurrenceUnit | recurUnit |
| DmSchedularType | schType |
| String | userName |
| Date | userEntryDate |
| int | companyCode |
| List<DmWeekdays> | lstDays |
| List<DmMonths> | lstMonths |
| List<DmDates> | lstDates |
| List<DmWeeks> | lstWeeks |
| List<Integer> | lstSites |

### Configuration_Mdl
| type | field |
|---|---|
| String | code |
| String | name |
| String | accountName |
| String | password |
| String | smtpServer |
| String | smtpPort |
| String | url |
| String | mediaUrl |
| String | format |
| long | maxlength |
| DmWebJobTypes | webJobType |
| DmEncriptionType | sslRequired |

### WebJobAttachment_Mdl
| type | field |
|---|---|
| long | templateId |
| String | procName |
| String | rptName |
| String | actFileName |
| String | fileDirectory |
| String | displayFileName |
| DmYesNo | isManual |
| String | mongoFileLink |
| String | fileRuleCode |
| String | fileType |

### GrdImage_Mdl
| type | field |
|---|---|
| String | imageCode |
| String | imageCodeValue |
| String | ruleCode |
| String | ruleCodeValue |

## Server `DmMapFormat.java` (281 lines)
### `format`
`public static String format(String pattern, Map arguments)`


### `processPattern`
`public String processPattern(String newPattern) throws IllegalArgumentException`


### `formatObject`
`private String formatObject(Object obj)`

- Other calls: `DateFormat.getDateTimeInstance`, `NumberFormat.getInstance`

### `format`
`public StringBuffer format(Object pat, StringBuffer result, FieldPosition fpos)`

- Errors: "ObjectForKey"

### `parse`
`public String parse(String source)`


## Server `DmAnonymousWebJobFactory.java` (89 lines)
Helpers used: `DmBaseServer.getSsnDBName`, `DmBaseServer.getSsnFileDBName`, `DmBaseServer.getSsnId`, `DmBaseServer.getSsnPLTCode`, `DmBaseServer.getSsnVoucherPrefix`

### `runJobs`
`public void runJobs(DmRSS servletInstance, List<Long> templateIds, List<DmWebJobDataRow> dataRows, List<WebJobAttachment_Mdl> manualEmailAttachments, int siteCode) throws Exception`


### `runJobsAsync`
`public void runJobsAsync(DmRSS servletInstance, List<Long> templateIds, List<String> uniqueIds, List<WebJobAttachment_Mdl> manualEmailAttachments, Integer siteCode) throws Exception`


### `runJobsAsync`
`public void runJobsAsync(DmRSS servletInstance, List<Long> templateIds, HashMap<String, List<WebJobAttachment_Mdl>> hmUniqueIdWiseAttachemnts, Integer siteCode) throws Exception`


### `run`
`private void run(boolean havePreData, DmRSS servletInstance, List<Long> templateIds, List<DmWebJobDataRow> dataRows, List<String> uniqueIds, List<WebJobAttachment_Mdl> manualEmailAttachments, HashMap<String, List<WebJobAttachment_Mdl>> hmUniqueIdWiseAttachemnts, Integer siteCode, boolean flagUniqueIdWiseAttachemnts) throws Exception`

- Other calls: `DmBaseShared.iNull`

## Server `DmWebJobResult.java` (36 lines)
## Server `DmScheduler.java` (230 lines)
### `processForDaily`
`protected boolean processForDaily(Calendar dtPrevious, Calendar dtNow) throws Exception`


### `processForWeekly`
`protected boolean processForWeekly(Calendar dtPrevious, Calendar dtNow) throws Exception`


### `processForMonthly`
`protected boolean processForMonthly(Calendar dtPrevious, Calendar dtNow) throws Exception`


### `getDifferenceFromStartingDateTimeInSeconds`
`public long getDifferenceFromStartingDateTimeInSeconds(Date dtNow, Date lastExecDate)`

- Other calls: `SECONDS.toSeconds`

### `equals`
`public boolean equals(Object obj)`


### `hashCode`
`public int hashCode()`


## Server `DmWebJob.java` (42 lines)
### `compute`
`protected DmWebJobResult<V> compute()`


## Server `DmSchedulerEventInvoker.java` (87 lines)
Helpers used: `DmBaseServer.getConnection`, `DmBaseServer.getWebServiceSchedulars`

### `call`
`public Void call() throws Exception`

- Other calls: `CurrentDateTime.toString`, `SchedulerMaster_Model.toString`, `Template_Model.toString`

### `getTemplates`
`public List<TemplateMast_Mdl> getTemplates() throws Exception`

- SQL 1: `SELECT * FROM WJTEMPLATEMAST WHERE (CODE=:templateCode) AND (TEMPLATETYPE=:templateType)`
- Params: `:templateCode` ← `DmBaseShared.iNull(scheduler.getSchedularModel().getWebJobTemplateId()).longValue()`; `:templateType` ← `DmBaseShared.iNull(scheduler.getWebJobType().getCode()).longValue()`
- Other calls: `DmBaseShared.iNull`, `DmBaseShared.xNull`

## Server `DmWebJobDataRow.java` (125 lines)
### `getMessageFromUnformatted`
`public String getMessageFromUnformatted(String messageToFormat)`

- Other calls: `DmMapFormat.format`

## Server `DmMessageParam.java` (30 lines)
## Server `DmSMSWebJob.java` (183 lines)
Helpers used: `DmBaseServer.dCodify`

### `executeJob`
`public DmWebJobResult<WebJobResult_Mdl> executeJob(Configuration_Mdl configurationMdl, TemplateMast_Mdl templateMastMdl, DmWebJobDataRow dataRow, List<WebJobAttachment_Mdl> attachments)`

- Other calls: `DmBaseShared.xNull`, `DmMapFormat.format`, `URLEncoder.encode`

### `doTrustToCertificates`
`private boolean doTrustToCertificates() throws Exception`

- Other calls: `HttpsURLConnection.setDefaultHostnameVerifier`, `HttpsURLConnection.setDefaultSSLSocketFactory`, `SSLContext.getInstance`, `Security.addProvider`

### `getMobileNo`
`private String getMobileNo(String mobileNo) throws Exception`

- Errors: "Mobile no. is blank."
- Other calls: `DmBaseShared.xNull`

## Server `DmSchedulerFactory.java` (258 lines)
### `addEventHandler`
`public <T extends AbstractEventHandler> void addEventHandler(String eventName, T eventHandler)`


### `removeEventHandler`
`public <T extends AbstractEventHandler> void removeEventHandler(String eventName, T eventHandler)`


### `publishEvent`
`public <T extends Event> void publishEvent(T event)`


### `scheduleRemove`
`public boolean scheduleRemove(String docId)`

- Other calls: `DmBaseShared.iNull`

### `schedule`
`protected boolean schedule(SchedulerMast_Mdl schedular, Date dtNow, boolean calledForRemoval)`


### `getListedSchedulers`
`public List<SchedulerMast_Mdl> getListedSchedulers() throws Exception`


### `clearAutoSchedulars`
`public void clearAutoSchedulars() throws Exception`


### `run`
`public void run()`

- Other calls: `Collectors.toList`, `DmSessionInner.checkSessionTasks`, `Executors.newCachedThreadPool`, `Thread.sleep`

### `fireSchdulers`
`public void fireSchdulers(List<DmAutoScheduler> executableSchedulars, ExecutorService cachedPool, ForkJoinPool forkjoinPool)`


### `printDiagnostics`
`private void printDiagnostics()`

- SQL 1: `Executing diagnostics.... {…} with main class ... {…}`

## Server `DmWebJobTemplateInfo.java` (601 lines)
Helpers used: `DmBaseServer.getConnection`, `DmBaseServer.getConnectionMongo`

### `fillTemplateDetails`
`public void fillTemplateDetails()`

- SQL 1: `Select wjtm.code, wjtm.name,wjtm.externalTemplate, wjtm.templateType, wjtm.dataSource, wjtm.functionName, wjtm.subject, wjtm.message, wjtst.nature, wjtst.value from wjTemplateMast as wjtm left join wjTemplateSendTo as wjtst on wjtm.code = wjtst.templateCode where wjtm.code = :templateId`
- SQL 2: `Select fileCode, ruleCode from wjTemplateFileDetail where templateCode = :templateCode`
- Params: `:templateId` ← `DmBaseShared.iNull(templateId).longValue()`; `:templateCode` ← `this.getTemplateId()`
- Errors: "Active DB Name is mandatory."
- Other calls: `DmBaseShared.iNull`, `DmBaseShared.xNull`, `DmWebJobDataSources.getValueFrom`, `DmWebJobTypes.getViaCode`

### `initialize`
`public boolean initialize(int siteCode)`

- Other calls: `DmBaseShared.iNull`, `DmBaseShared.xNull`

### `fillConfigDetail`
`private void fillConfigDetail(int siteCode, Connection cnTemp) throws Exception`

- SQL 1: `Select wjam.code, wjam.name, wjam.accountName, wjam.password, wjam.smtpServer, wjam.smtpPort, wjam.smtpSSL, wjam.cc, wjam.bcc, wjam.url, wjam.mediaUrl, wjam.format, wjam.maxlength, wjam.nature from wjApplyConfig as wjac left join {…} ..wjAccountMast as wjam on wjac.wjAccountCode = wjam.code where wjam.nature = :nature {…} order by (case when wjac.pltCode = :pltCode then 1 else 0 end) + (case when wjac.site_Code = :site_Code then 3 else 0 end) + (case when wjac.templateCode = :templateCode then 5 else 0 end) desc`
- Params: `:nature` ← `(this.getWebJobType() != null ? DmBaseShared.iNull(this.getWebJobType().getCode()).intValu`; `:pltCode` ← `DmBaseShared.iNull(pltCode).intValue()`; `:site_Code` ← `DmBaseShared.iNull(siteCode).intValue()`; `:templateCode` ← `DmBaseShared.iNull(templateId).longValue()`
- Other calls: `DmBaseShared.iNull`, `DmBaseShared.xNull`, `DmEncriptionType.getViaCode`, `DmWebJobTypes.getViaCode`, `EMAIL.getCode`, `SMS.getCode`, `WHATSAPP.getCode`

### `fillDataAndParameters`
`public void fillDataAndParameters(int siteCode, Connection cnTemp) throws Exception`


### `fillDataAndParametersFromDB`
`private void fillDataAndParametersFromDB(int siteCode, Connection cnTemp) throws Exception`

- SQL 1: `Select * from {…} (:templateId, :v_Prefix, :siteCode) WHERE (1 = 1) {…}`
- Params: `:templateId` ← `DmBaseShared.iNull(this.getTemplateId()).toString()`; `:v_Prefix` ← `DmBaseShared.iNull(v_Prefix).intValue()`; `:siteCode` ← `DmBaseShared.iNull(siteCode).intValue()`
- Other calls: `DmBaseShared.iNull`, `DmBaseShared.xNull`

### `fillDataAndParametersFromFile`
`private void fillDataAndParametersFromFile() throws Exception`


### `addWebJobRow`
`protected void addWebJobRow(DmWebJobDataRow row)`


### `getDataFromMongoDB`
`private List<WebJobAttachment_Mdl> getDataFromMongoDB(boolean isDynamic, String directoryPath, String docId, String ruleCode, DmFileManagerMongo fileManagerMongo, Connection cnTemp) throws Exception`

- SQL 1: `Select fileLink, fileType from linkFileCollection where lower(ruleCode) = lower(:ruleCode) {…}`
- Params: `:docId` ← `DmBaseShared.xNull(docId).trim()`; `:ruleCode` ← `DmBaseShared.xNull(ruleCode).trim()`
- Other calls: `DmBaseShared.xNull`

### `equals`
`public boolean equals(Object obj)`


### `hashCode`
`public int hashCode()`


## Server `IDmWebJob.java` (12 lines)
## Server `DmEMailWebJob.java` (232 lines)
Helpers used: `DmBaseServer.dCodify`

### `executeJob`
`public DmWebJobResult<WebJobResult_Mdl> executeJob(Configuration_Mdl configurationMdl, TemplateMast_Mdl templateMastMdl, DmWebJobDataRow dataRow, List<WebJobAttachment_Mdl> attachments)`

- Other calls: `DmBaseShared.iNull`, `DmBaseShared.xNull`, `EmailBuilder.startingBlank`

### `attachImage`
`public void attachImage(MimeMultipart multiPart, BodyPart bodyPart, WebJobResult_Mdl model, WebJobAttachment_Mdl attachment, List<MimeBodyPart> fileAttachments) throws MessagingException`

- Other calls: `DmBaseShared.xNull`

## Server `DmWhatsappWebJob.java` (149 lines)
Helpers used: `DmBaseServer.convertFromJson`, `DmBaseServer.dCodify`

### `executeJob`
`public DmWebJobResult<WebJobResult_Mdl> executeJob(Configuration_Mdl configurationMdl, TemplateMast_Mdl templateMastMdl, DmWebJobDataRow dataRow, List<WebJobAttachment_Mdl> attachments)`

- Errors: "API call failed..."
- Other calls: `DmBaseShared.xNull`, `DmMapFormat.format`

### `uploadMedia`
`private String uploadMedia(String senderId, String tokenId, String mediaURL, WebJobAttachment_Mdl attachmentMdl) throws Exception`

- Errors: "File : " | "Media Type can not be null." | "API call failed..."
- Other calls: `DmApiBase.getContent_Type`, `DmBaseShared.xNull`

## Server `DmObjectLock.java` (44 lines)
### `lockObject`
`public boolean lockObject()`


### `releaseObject`
`public boolean releaseObject()`


## Server `DmAutoScheduler.java` (41 lines)
### `shouldExecute`
`public boolean shouldExecute(Calendar dtPrevious, Calendar dtNow)`


## Server `DmWebJobTemplateEngine.java` (141 lines)
Helpers used: `DmBaseServer.deleteFilesAndDirectories`, `DmBaseServer.getConnection`, `DmBaseServer.getWebServiceSchedulars`

### `compute`
`protected final List<DmWebJob<? extends IDmWebJob<V>, V>> compute()`

- Other calls: `DmBaseShared.iNull`

### `processAfterExecution`
`private void processAfterExecution()`

- SQL 1: `Update schedulerMast set isActive = :isActive where docId = :docId;`
- Params: `:isActive` ← `DmEnum.DmYesNo.NO.toInt()`; `:docId` ← `this.schedulerMastMdl.getDocId()`
- Other calls: `DmTemplateEngine.processAfterExecution`, `NO.toInt`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.server`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmEventbus.server`, `dataman.dmBase.dmFileManagerMongo.server`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmScheduler.shared`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.jvmSerialization.shared`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
