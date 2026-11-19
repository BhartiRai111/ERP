# dm-base: conversion note (from legacy `dataman_V2/dmBase`)

> Status: **DRAFT, review ke liye. Kuch bhi commit/push nahi hua.**
> Legacy: `legacy-gwt/dataman_V2/dmBase/src` (176 java files, ~34k lines). New: `paperbiz-backend/dm-base`, package `com.dataman.base`.
> Approach: logic samajh kar saaf naya code (compat layer nahi). GWT code seedha copy sirf wahan jahan constants/algorithm hi asli cheez hai (enums, voucher codes, amount-in-words).
> Verification: `mvn -q compile` poore reactor par pass, `dm-base` ke **40 unit tests pass**. Mongo aur SQL Server asli server par nahi chalaye (container me nahi hain); wo hisse sirf compile + mock tests se verified hain.

## 1. Kya ho gaya (new code)

| Package (`com.dataman.base.*`) | Legacy | Kaam | Verify |
|---|---|---|---|
| `db` | `DmBaseServer.getConnection`, BoneCP, `DmConnectionPoolingConfig` | HikariCP + har request me company DB (`setCatalog`) aur close par default catalog wapas. `FieldUdt` = `dbo.fieldUDT` table parameter | compile + test (FieldUdt) |
| `context` | `getSsn…(this)` (~140 getters), `DmRSSPrivate` | `UserContext`(user, company DB, site, IP) + request filter (**temporary headers**, JWT baad me). `SessionInfo/UserInfo/CompanyInfo/SiteInfo` models | compile |
| `api`, `exception` | `DmResult`, `DmException` | `DmException(errorNumber, message)`, `ApiError`, global handler (HTTP 400/500, legacy messages) | compile |
| `paging` | `DmHelper`, `DmPaging`, `DmFilter`, `DmSort`, `evaluateHelper` | `PageQuery/PageResult/PagedSql/PagedQueryExecutor` (filter, sort, offset-fetch, "to end of file") | unit tests |
| `serial` | `getSerialNoInteger/BigInt`, `getDocId` | `SerialNoService`: `getCode` proc keys (4/14 digit, prefix wala 11 digit), `docId` | format tests |
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
| Scheduler + web jobs (email/SMS/WhatsApp, templates) | `dmScheduler` (23 files, ~3.7k lines), `dmEventbus` | 27 files use karte hain. Spring `@Scheduled`/Quartz + `ApplicationEventPublisher` se naya banega; ek instance par ek job (multi-instance me double run se bachna) |
| Jasper engine | `DmBaseServer.compileJasper/fillJasper/showJasper`, `DmJasperCompiler`, `DmJasperStyle`, fonts (`createFontsInitalization`), `DmWaterMark`, `DmBaseJasper` scriptlet, `DmJRXlsExporter` (2,107 lines, Jasper ka patched exporter), `net.sf.jasperreports.*` | **Jasper 6.1.1 → 7.x upgrade** ke saath, 73 `.jrxml` ke PDF compare ke saath. `AmountInWords/AmountFormatter` pehle hi tayyar hain (reports ke scriptlet ki jagah) |
| Dynamic report builder | `dmDynamicReportBuilder`, `RptFind`, `DmMasterReport*`, `DmXlsWriter` header helpers (`setReportHeaderExcel`) | Find popup ka Excel export; Jasper stage ke saath |
| `getRecId` (voucher record id, date/prefix format) | `DmBaseServer` ~270 lines | Voucher modules (sales/purchase) chahiye; master ko nahi |
| `DmRefreshDataStructure` ke alawa DB utilities | `executeScalar`, `executeSQL`, `getParamList`, `prepareContraText*`, `deleteExpiredLinkVerification`, `deleteFilesAndDirectories`, `createRepository*` | Zaroorat ke hisaab se |
| Mongo temp collections / sessions | `getMdbCollection`, `evaluateHelper(Mongo)`, `deleteGlobalTemporaryCollections…` | Session-based temp data; naye design me session ke saath decide |
| File sync at start-up | `synchronizeTransactionalWithFileServer` | Har company ke file DB me har `fileTypeMast.ruleCode` ka folder banata hai; `MongoFileStore.createFolder` tayyar, startup wiring baaki |
| Start-up config | `getInitializeBaseINI` (`config.properties`: localeCountry, DB/Mongo creds, CORS headers) | Naye me `application.yml` / env (neeche) |
| `CsvContentType`, `DmCsvFileFormat_Mdl` | | `selfToolkit` ke ImportData ke saath |

## 4. Legacy quirks mile (aapko decide karna hai)
1. **`DmHelper.prepareQuery`:** multiple filters ke beech AND/OR nahi lagta tha (bug) aur column naam seedha SQL me jaata tha. Naye me `AND`/`OR` lagta hai aur columns whitelist se aate hain.
2. **Amount in words:** legacy typo "Ninteen" (19) aur sub-rupee amount par "Rupee & One Paisa Only" (Zero nahi). Abhi **same** rakha hai (golden test lock karta hai). Theek karna ho to batao.
3. **`isValidText(value, label, N)`:** `N` error number hai, length nahi (`Validate.mandatory` me bhi).
4. **`getCodeViaManualCode`:** table/column naam SQL text me jodta tha. Naye me naam `[A-Za-z0-9_.]+` se check hote hain.
5. **`DmXlsWriter`:** constructor ke errors chhupata tha aur `close()` me file stream leak hota tha; `XlsWriter` me theek.
6. **File upload:** legacy me SQL insert fail ho to Mongo file orphan reh jaati thi; `AttachmentService` ab use hata deta hai.
7. **`DmLocaleCountry.getLocaleViaDmCode`:** unknown code par **USA** deta hai (legacy); `LocaleSettings` blank = IND, unknown = USA, legacy jaisa.
8. **`DmVoucherCategory`:** hospital product ke ~50 khaali (`""`) constants (OPD/IPD waghera) chhode.
9. **CSV reader:** escape character `|` (non-strict), header row spaces collapse + lower-case keys; data row me headers se zyada values ho to ab clear error.

## 5. Config (`application.yml` / environment; secrets repo me nahi)
| Property | Matlab |
|---|---|
| `dataman.db.url`, `username`, `password`, `default-catalog`, `maximum-pool-size`, `minimum-idle` | SQL Server pool (DataSource tabhi banta hai jab `url` set ho) |
| `dataman.mongo.host`, `port`, `username`, `password`, `auth-source` | File server (Mongo) |
| `dataman.locale.country` | Base country dm code: IND / KHM / USA / KHM-USA (default IND) |
| Request headers (temporary) | `X-Dm-User`, `X-Dm-Company-Db`, `X-Dm-Site` |

## 6. Dependencies jode (dm-base/pom.xml)
Spring Boot starters (webmvc, jdbc, test), `mssql-jdbc` (BOM), `mongodb-driver-sync` (BOM), `opencsv 5.9`, `poi-ooxml 5.4.1`, `icu4j 77.1`.

## 7. Aage ka order (mera suggestion)
1. Review: upar ki list aur "port nahi" faisle (khaaskar `dmRefreshDataStructure`, `DmBuild`, `codify`).
2. `dm-toolkit` ka modify-reason change log (master ki har entry ko chahiye).
3. `pb-master` ke simple masters; wahin se shared base abstractions pakki hongi.
4. Scheduler/Jasper jaise bade hisse apne alag steps me.
