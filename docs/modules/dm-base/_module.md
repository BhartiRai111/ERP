# dm-base: conversion note (from legacy `dataman_V2/dmBase`)

> Status: **dm-base ke saare planned hisse ban chuke hain** (voucher no. `RecIdService`, scheduler, Jasper 7.1.1 engine, grid report builder). Commits `next` branch par push ho chuke hain.
> Legacy: `legacy-gwt/dataman_V2/dmBase/src` (176 java files, ~34k lines). New: `paperbiz-backend/dm-base`, package `com.dataman.base`.
> Approach: logic samajh kar saaf naya code (compat layer nahi). GWT code seedha copy sirf wahan jahan constants/algorithm hi asli cheez hai (enums, voucher codes, amount-in-words).
> Verification: `mvn -q compile` poore reactor par pass, `dm-base` ke **60 unit tests pass**. Mongo aur SQL Server asli server par nahi chalaye (container me nahi hain); `RecIdService` aur `SerialNoService` ke SQL/stored procedure calls sirf compile + format tests se verified hain, asli DB par test baaki (dev DB milne par).

> **Reports:** faisla (user): **HTML/PDF template** (Thymeleaf + OpenHTMLtoPDF) pehli priority; Jasper sirf optional library. Detail: `report-templates.md`.

## 1. Kya ho gaya (new code)

| Package (`com.dataman.base.*`) | Legacy | Kaam | Verify |
|---|---|---|---|
| `db` | `DmBaseServer.getConnection`, BoneCP, `DmConnectionPoolingConfig` | HikariCP + har request me company DB (`setCatalog`) aur close par default catalog wapas. `FieldUdt` = `dbo.fieldUDT` table parameter | compile + test (FieldUdt) |
| `context` | `getSsn…(this)` (~140 getters), `DmRSSPrivate` | `UserContext`(user, company DB, site, IP) + request filter (**temporary headers**, JWT baad me). `SessionInfo/UserInfo/CompanyInfo/SiteInfo` models | compile |
| `api`, `exception` | `DmResult`, `DmException` | `DmException(errorNumber, message)`, `ApiError`, global handler (HTTP 400/500, legacy messages) | compile |
| `paging` | `DmHelper`, `DmPaging`, `DmFilter`, `DmSort`, `evaluateHelper` | `PageQuery/PageResult/PagedSql/PagedQueryExecutor` (filter, sort, offset-fetch, "to end of file") | unit tests |
| `serial` | `getSerialNoInteger/BigInt`, `getDocId` | `SerialNoService`: `getCode` proc keys (4/14 digit, prefix wala 11 digit), `docId` | format tests |
| `serial` (RecId) | `DmBaseServer.getRecId` (~270 lines) | `RecIdService.resolve(...)`: `numberSystem/numberSystemFormat` se format, `getRecId` proc se counter, date-lock (`Voucher_Type.acceptEntryAfter`), financial-year check (`Company`), duplicate voucher no. check. `RecIdFormat` = token expand (`{vouchersname}`, `{year}`, `{counter}` ...), `RecId` record | format tests |
| `scheduler` | `dmScheduler` (`DmSchedulerFactory`, `DmScheduler`, `DmAutoScheduler`) | `SchedulePlan` (schedulerMast row), `ScheduleEvaluator` (ONCE/DAILY/WEEKLY/MONTHLY, legacy rules), `SchedulerService` (registry + 1-minute `@Scheduled` tick, worker thread per job, ek job fail ho to baaki chalte hain), `ScheduledJobHandler` (job type ke hisaab se handler; EMAIL/SMS/WHATSAPP/EVENT ke handler `dm-web-jobs` me aayenge) | unit tests |
| `report.html` | `DmBaseServer.fillJasper/showJasper`, Jasper scriptlet `DmJasper` | `HtmlReportService` (Thymeleaf template, `systemCustomized` override, HTML -> PDF with fonts), `DmReportHelper` (`${dm.amount/qty/words/date}`), `ReportDataService` (report stored procedure -> result sets, incl. `fieldUDT`) | unit tests (PDF text checked) |
| `report.jasper` (optional) | `compileJasper/fillJasper/showJasper`, `DmJasperCompiler`, `DmWaterMark` | `ReportCompiler` (systemCustomized → system lookup, memory cache jo .jrxml badalne par refresh hota hai, sub reports auto-compile, font override for regional language, legacy scriptlet class mapping), `ReportService` (fill from ResultSet / list / connection with `fieldUDT`; `dmCurrentDateTime`, `SUBREPORT_DIR`, `dmFrmRptFormulaFieldDisplay`; export PDF 1.7 / XLSX / DOCX as bytes), `WatermarkRenderer` | unit tests (groovy + sub report + 3 exports) |
| `report.grid` | `dmDynamicReportBuilder`, `RptFind.downloadExcel`, `DmMasterReport*` | `GridReport/GridColumn/PageSetup`, `GridExcelExporter` (legacy layout: header lines, shaded heading row, `#.00`), `GridPdfExporter` interface: default `HtmlGridPdfExporter`, optional `JasperGridPdfExporter` | unit tests (H2) |
| `audit` | `DmServer.formatTimestamp/getUserWithDateTime` | `AuditService` | tests |
| `util` | `DmBaseShared`, `DmShared` (base part) | `Strings` (xNull, iNull, vNull, foreignKey, removeSpace, properCase, sentenceCase, space, isNumeric), `Validate` (mandatory, amount, email, pan, mobile, gstin), `DateUtil` | tests |
| `enums` | `DmEnum` (99 enums) | **88 enums** + `CodeValueEnum` (har enum ek file) | compile |
| `voucher` | `DmVoucherCategory`, `DmVoucherGroup` (**448 files me use**) | `VoucherCategory` (233 `static final` codes + group maps), `VoucherGroup` | tests |
| `permission` | `UserMenu`, `UserPermission`, `VoucherPermission` | `MenuPermission` (A=add, E=edit, D=delete, P=print), `VoucherPermission`, `UserPermissions` | tests |
| `file` | `DmHandelFile`, `ZipUtils`, `DmCsvReader`, `DmXlsWriter` | `LineFileWriter`, `ZipFolder`, `CsvFileReader` (opencsv), `XlsWriter` (POI) | tests |
| `filestore` | `DmFileManagerMongo` (+ `dmFileManager` models) | `MongoFileStore` (modern driver, GridFS), `FileLinkRepository` (`LINKFILECOLLECTION`), `AttachmentService` | mock tests (Mongo asli par nahi) |
| `report` | `dmBaseJasper` (`DmAmtInWord*`, `DmNumberFormat`) | `AmountInWords` (+Lakh/Million), `NumberFormatter` (ICU4J 77.1), `AmountFormatter`, `LocaleSettings` | **golden tests vs legacy** |
| `lookup` | `getCodeViaManualCode`, `getVType` | `CodeLookupService` (table/column naam validate) | tests |

### Golden tests (legacy ke output se compare)
- **Amount in words:** legacy `DmAmtInWordLakh/Million` ko compile karke 1,788 cases ka output `src/test/resources/golden/amount-in-words.txt` me; naya code **sab me identical** (legacy typo jaise "Ninteen" aur "Rupee & One Paisa Only" bhi same hain).
- **Number format:** legacy `DmNumberFormat` (ICU 4.8.1.1) ke 721 cases `golden/number-format.txt`; naya (ICU 77.1) 697 me identical, **24 me ek hi fark**: 0.005 ko 0 decimals me round karo to legacy `"0."`, naya `"0"`.

## 2. Jo port NAHI kiya (aur kyun)

| Legacy | Faisla | Wajah |
|---|---|---|
| `DmResult`, `DmResultPageing`, `DmSQLParam`, `DmSQLWithParam`, `DmPreparedStatement*`, `ParseResult*`, `DmSavePoint` | Spring se replace | Named params = `NamedParameterJdbcTemplate`; savepoint/commit = `@Transactional`; result = HTTP + `ApiError` |
| `DmConnectionPoolingConfig`, BoneCP | HikariCP | upar |
| `dmSession/*` (servlet, session, GWT RPC, filters), `DmGarbageCleaner`, `DmSessionCleaner` | replace (login/JWT step) | GWT RPC aur static session table; naya design `context` |
| Sab `client/` classes, `*_PrpAcs`, `SetWidgetInvoker`, `ServiceInvoker`, `DmDateTimeFormat` (2,179 lines), `DmCallBack`, `IWebCamera` | skip | GWT/GXT UI; frontend (Next.js) me banega |
| **`dmRefreshDataStructure`** (1,178 lines) | **port nahi** | Ye tables/keys/indexes par **ALTER/CREATE/DROP** chalata hai (ALTER 11, CREATE 4, DROP 10). Aapka rule: DB schema unchanged. 17 files isse use karte hain (kuch DB maintenance screens) |
| `ExecCommand` | skip (abhi) | OS commands chalata hai (`Runtime.exec`); sirf `utility/dataBackup` use karta hai. Wahan decide karenge |
| `dataman.queue` (BigQueue disk queue, ~3k lines) | skip (abhi) | Sirf `dmToolkit` ke rdp/sftp polling use karte hain; wahan decide hoga (library ya Spring alternative) |
| `DmCsvToExcelWriter`, `DmApiBase` | skip (abhi) | 0 aur 1 file me use; zaroorat padne par |
| `DmBuild.verifyVersion` (`dmBuild.jar`) | **port nahi** | Source nahi hai; legacy startup me version/license check lagta hai. License logic naye project me nahi laaya |
| `codify/dCodify` (3 files use) | **abhi nahi** | **Hardcoded AES keys** (`codifyKey1/2` code me). Naya design: key config/secret se, algorithm aapke saath decide karke |
| `objectToBase64/base64ToObject` | skip | Java serialization se object; security risk. 1 file use karta hai |

## 3. Abhi baaki (dm-base me)

| Hissa | Legacy | Note |
|---|---|---|
| **73 legacy `.jrxml` reports** | `accounts/reports` (72) + `paperbiz/store` (1) | Naya format (HTML template) chuna gaya; har report apne module ke saath HTML me banega (procedure same, layout naya). Jasper 7 purana format padh nahi sakta, isliye direct load nahi hote |
| Reports ke scriptlet (`DmJasper`) | 72 reports me | Helpers `${dm...}` me aa gaye (amount, qty, words, date); baaki scriptlet functions report-wise dekhenge |
| Web jobs (email/SMS/WhatsApp, templates) | `DmEMailWebJob`, `DmSMSWebJob`, `DmWhatsappWebJob`, `DmWebJobTemplateEngine`, `DmMapFormat` | `dm-web-jobs` module me, `ScheduledJobHandler` implement karke. Multi-instance deploy me double run se bachne ke liye lock (alag decision) |
| Event bus | `dmEventbus` | Spring `ApplicationEventPublisher`; zaroorat padne par |
| DB utilities | `executeScalar`, `executeSQL`, `getParamList`, `prepareContraText*`, `deleteExpiredLinkVerification`, `deleteFilesAndDirectories`, `createRepository*` | Zaroorat ke hisaab se |
| Mongo temp collections / sessions | `getMdbCollection`, `evaluateHelper(Mongo)`, `deleteGlobalTemporaryCollections…` | Session-based temp data; login/session step me decide |
| File sync at start-up | `synchronizeTransactionalWithFileServer` | `MongoFileStore.createFolder` tayyar, startup wiring baaki |
| Start-up config | `getInitializeBaseINI` | Naye me `application.yml` / env (neeche) |
| `CsvContentType`, `DmCsvFileFormat_Mdl` | | `selfToolkit` ke ImportData ke saath |
| Excel header helper | `setReportHeaderExcel` | Header lines ab `GridReport.headerLines` me caller deta hai (company/site session se, login step ke baad wire hoga) |

### Jasper (optional library) ke notes
- Jasper 7 ke liye teen cheezein Jaspersoft ki apni repository (`jaspersoft.jfrog.io`) par hain jo yahan nahi khulti: patched `openpdf` (Central ka `openpdf 1.3.43` use kiya), `groovy-sandbox` (exclude, sirf sandbox expressions ke liye), aur `commons-collections4` ka 4.5.0 (POI 4.4 laata hai, isliye explicitly jodha). Test me PDF/XLSX/DOCX export aur groovy expressions chalte hain.
- Legacy ke `DmJRXlsExporter` (patched 2,107 lines) ki jagah Jasper ka standard `JRXlsxExporter` use hota hai; Excel reports ki styling me chhote farak ho sakte hain.
- Legacy `.jasper` files disk par cache hoti thi aur .jrxml badalne par refresh nahi hoti thi; ab memory cache hai jo file ka time dekhta hai.

## 4. Legacy quirks mile (aapko decide karna hai)
1. **`DmHelper.prepareQuery`:** multiple filters ke beech AND/OR nahi lagta tha (bug) aur column naam seedha SQL me jaata tha. Naye me `AND`/`OR` lagta hai aur columns whitelist se aate hain.
2. **Amount in words:** legacy typo "Ninteen" (19) aur sub-rupee amount par "Rupee & One Paisa Only" (Zero nahi). Abhi **same** rakha hai (golden test lock karta hai). Theek karna ho to batao.
3. **`isValidText(value, label, N)`:** `N` error number hai, length nahi (`Validate.mandatory` me bhi).
4. **`getCodeViaManualCode`:** table/column naam SQL text me jodta tha. Naye me naam `[A-Za-z0-9_.]+` se check hote hain.
5. **`DmXlsWriter`:** constructor ke errors chhupata tha aur `close()` me file stream leak hota tha; `XlsWriter` me theek.
6. **File upload:** legacy me SQL insert fail ho to Mongo file orphan reh jaati thi; `AttachmentService` ab use hata deta hai.
7. **`DmLocaleCountry.getLocaleViaDmCode`:** unknown code par **USA** deta hai (legacy); `LocaleSettings` blank = IND, unknown = USA, legacy jaisa.
8. **`DmVoucherCategory`:** hospital product ke ~50 khaali (`""`) constants (OPD/IPD waghera) chhode.
10. **`getRecId` manual counter check:** legacy `numberSystemCounter` me `nsGroup = voucherType` se dhundhta tha, jabki table me `nsGroup` store hota hai (aur `getRecId` proc bhi wahi use karti hai). Naye me `nsGroup` use hota hai; message wahi: "Given counter can not be greater than existing counter."
11. **Monthly schedule "Last" week:** legacy me kabhi match nahi hota tha (-1 ko weeks ki ginti se compare karta tha). Naye me month ke aakhri hafte me match hota hai.
12. **Table/field naam `getRecId` me:** SQL me seedha jodte the; ab identifier regex se check hote hain.
9. **CSV reader:** escape character `|` (non-strict), header row spaces collapse + lower-case keys; data row me headers se zyada values ho to ab clear error.

## 5. Config (`application.yml` / environment; secrets repo me nahi)
| Property | Matlab |
|---|---|
| `dataman.db.url`, `username`, `password`, `default-catalog`, `maximum-pool-size`, `minimum-idle` | SQL Server pool (DataSource tabhi banta hai jab `url` set ho) |
| `dataman.mongo.host`, `port`, `username`, `password`, `auth-source` | File server (Mongo) |
| `dataman.locale.country` | Base country dm code: IND / KHM / USA / KHM-USA (default IND) |
| `dataman.reports.dir` | Report templates ka folder (`system`, `systemCustomized`, `osr` ke andar `.html`; Jasper ke liye `.jrxml`), default `reports` |
| `dataman.reports.fonts-dir`, `template-cache`, `grid-pdf` | PDF fonts folder, template cache, `html` (default) / `jasper` |
| `dataman.reports.cache-dir` | Compiled sub reports ka folder (default: system temp ke andar `paperbiz-reports`) |
| `dataman.reports.scriptlet-classes.<old class>` | Purane scriptlet class ka naya class |
| `dataman.scheduler.enabled` / `dataman.scheduler.tick-millis` | Scheduler on/off (default on) aur tick (default 60000 ms) |
| Request headers (temporary) | `X-Dm-User`, `X-Dm-Company-Db`, `X-Dm-Site` |

## 6. Dependencies jode (dm-base/pom.xml)
Spring Boot starters (webmvc, jdbc, test), `mssql-jdbc` (BOM), `mongodb-driver-sync` (BOM), `opencsv 5.9`, `poi-ooxml 5.4.1`, `icu4j 77.1`, **Thymeleaf** (Boot starter), **OpenHTMLtoPDF 1.1.117**, `jsoup 1.24.1`, **JasperReports 7.1.1** (optional: `jasperreports`, `-pdf`, `-excel-poi`, `-fonts`, `-jdt`, `-groovy`), `openpdf 1.3.43`, `commons-collections4 4.5.0`, test me `h2`.

## 7. Aage ka order (mera suggestion)
1. Review: "port nahi" faisle (khaaskar `dmRefreshDataStructure`, `DmBuild`, `codify`) aur reports ka faisla (upar section 3).
2. DB schema doc (`docs/design/database-schema.md`) aur dev DB (types, tables, procedures, seed).
3. L2-1: login / session / permission (backend + frontend).
4. L2-2: entry basics (`dm-toolkit`: modify-reason change log, date lock, crud, lookup) + frontend widgets; phir `pb-master` ki simple masters.
