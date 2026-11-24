# Library design (backend + frontend)

> Status: **DRAFT, approval ke liye. Is doc ke baad hi library ka code shuru hoga.**
> Aadhaar: `docs/modules/*` ki specs (legacy se nikali hui). Goal: same project, same logic, same entry points; code same nahi.
> Rules (CLAUDE.md): DB schema unchanged; har step ke baad `mvn -q compile` (backend) aur build/typecheck (frontend); pehle approval.

## 1. Principles
1. **Common cheez ek jagah.** Jo bahut entries ko chahiye wo library me (`dm-*`, `pb-self-toolkit`, frontend `lib/`), kisi module me copy nahi.
2. **Har entry ka ek jaisa shape** (backend endpoints + frontend screen framework), taaki ~430 entries chhote config/code se banen.
3. **Legacy ke messages aur rules same** (error text, mandatory messages, rok-tok). Jahan legacy me bug/typo hai wahan abhi same, aur decision aapka (`docs/modules/dm-base/_module.md` sec 4).
4. **Schema par kuch nahi chalega:** `DmRefreshDataStructure`, `DML_Server`, `repairMenu` ka schema-badalne wala hissa, license check (`DmBuild`) port nahi. Stored procedures (`getCode`, `getItemManualCode`, `get*Id`, `getDocId`) DB ke hain, bas call honge.
5. **Behaviour sabit karne ke liye golden tests** jahan logic nazuk hai (amount in words aur number format ho chuke; stock FIFO, account posting, GST agle).
6. **Security:** SQL me user ka text nahi (parameters), table/column naam whitelist, secrets config/env me. **Password:** DB me MD5 hai aur schema badal nahi sakte, to verify purane format se, par ek `PasswordVerifier` interface ke peeche (baad me strong hash opt-in); login par rate-limit/lockout (sec 7, faisla 1).
7. **Latest stable versions:** koi purani library nahi (Spring Boot 4.1.x, Java 21, ICU4J 77, POI 5.4, **JasperReports 7.1.1** (purana 6.1.1 nahi), Node 22 LTS, latest stable Next.js/AG Grid/TanStack); legacy jars (BoneCP, GXT, purane TaxPro, iText 5...) nahi.

## 2. Backend libraries (Maven modules)

### 2.1 `dm-base` (status: bada hissa ho chuka)
Ho chuka: `db` (Hikari + company catalog), `context`, `api`, `exception`, `paging`, `serial` (`getCode`, docId), `audit`, `util` (Strings/Validate/DateUtil), `enums` (88), `voucher` (233 codes), `permission` models, `file` (CSV/XLS/Zip/LineWriter), `filestore` (Mongo GridFS + attachments), `report` (AmountInWords, NumberFormatter), `lookup` (`CodeLookupService`, `FieldUdt`).
Baaki: scheduler/web-job engine, Jasper engine (6.1 -> 7.x, 73 `.jrxml`), dynamic report builder/`RptFind` Excel, `getRecId` (voucher record id), Mongo temp-collections, startup config.

### 2.2 `dm-toolkit` (server features, `com.dataman.toolkit`)
| Package | Service(s) | Legacy | Table(s) | Priority |
|---|---|---|---|---|
| `auth` | `AuthService` (login, JWT, lock/unlock, password change via link; `UserMast.PassWd` MD5 verify `PasswordVerifier` ke peeche) | `DmServer.validateUser/validateToken`, `Login_SrvImpl`, `redirect/changePassword` | `UserMast`, `linkVerification` | **L2-1** |
| `session` | `SessionInfoService` (PLT -> company -> site selection, `SessionInfo`), `FeatureFlagService` (CFM), `EnviroService` | `getPLTs/getCompanys/getSites/validateSite`, `STServer.setSsnEnviro/CFM` | `ProductLicensedTo`, `Company`, `UserSite`, `SiteMast`, `enviro`, `enviroCompany`, `userLinkAccount` | **L2-1** |
| `permission` | `PermissionService` (menu tree, control permission, voucher date/value limits), `MenuService` | `getUserMenu/getUserPermission`, `ribbon` | `user_permission_web`, `user_control_permission_web`, `User_Voucher_Permission`, `menuCodification`, `user_Favorite_Web` | **L2-1** |
| `datelock` | `DateLockService` (forward/backward/lock/financial-year checks) | `check*VoucherDateTime`, `checkTransactionLockDate`, `checkFinancialYearDate` | `pltLockDate`, `Company` | **L2-2** |
| `changelog` | `ModifyReasonService` (reason lookup + Mongo `datamodificationlog` write + old/new diff helper) | `modifyReason` | `modifyReasonMast`, Mongo | **L2-2** |
| `crud` | helpers for entries: `AuditColumns`, `DuplicateCheck`, `OptimisticLock` (`u_EntDt_LatestLine`), `LookupQuery` (text-help query builder), `EntryResponse` | patterns of 55 master entries | - | **L2-2** |
| `lookup` | generic lookup endpoint support (paged, starts-with on key column) | `DmTextHelpGrid`/`getTxtH*` | - | **L2-2** |
| `files` | `FileController` (upload to temp, download, thumbnail), `AttachmentService` wiring, file rules | `dmFileManager`, `dmFileServlets`, `dmFileUploader` | `fileTypeMast`, `linkFileCollection`, Mongo | L2-3 |
| `singleline` | `SingleLineMasterService` (config-driven code+name master; table/column whitelist) | `singleLineMaster` | any code+name table | L2-3 |
| `walkthrough` | `WalkThroughService` (apply, move, history, delete) | `walkThrough` | `walkThroughDefault/Detail`, `WalkThroughApplicable`, `WalkThroughFootPrint` | L2-3 |
| `gui` | `GuiConfigService` (dynamic fields) | `dmGUI` | `guiConfig`, `guiConfigDetail`, per-screen tables | L2-3 |
| `terms` | `TermsService` | `dmTermsConfig` | `termsConfig(Detail)`, `termsTrans`, `termsTypeMast` | L2-3 |
| `partyinfo` | `PartyTransInfoService` (party snapshot on document) | `partyDetailPopup` | voucher `*PartyInfo` tables | L2-3 |
| `tds` | `TdsTcsService` (balance/turnover/value), `TdsTransService` | `dmStatic` TDS/TCS, `tdsTrans` | `tdsTrans` | L2-4 |
| `overhead` | `OverheadService`, `OverheadLineService` (+ITS) | `overHead`, `overHeadLine` | overhead tables, `its/itsDetail` | L2-4 |
| `stock` | `StockService` (save, FIFO issue, sales return, delete guards, FIFO recalculation, batch) | `stock` | `stock`, `itemStock`, `stockFifo`, `itemBatchStock`, `stockItemId` | **L2-4 (golden tests)** |
| `accounting` | `AccountPostingWriter`, `AccountAdjustmentService`, `ItemAdjustmentService` | `accountPosting`, `accountAdjustment`, `itemAdjustment` | `LedgerM`, `Ledger`, `ledgerAdj`, `stockOtherAdjustment` | L2-4 |
| `egst` | `EGstService` (e-way bill, e-invoice: token, generate, cancel, print) | `gst`, `eGstPopup` | `gstTrans*`, `eGst*` | L2-5 |
| `importdata` | `ImportDataService` (CSV formats, validation, log) | `dmImportData` | master tables | L2-5 |
| `notify` | `NotificationService`, mail (`spring-mail`), web-socket | `dmNotifications`, `MailServer`, `dmWebSocket` | `wj*` | L2-5 |
| `report` | **JasperReports 7.1.1** glue: `ReportService` (compile `.jrxml` once + cache -> `JasperFillManager` with the company DB connection -> `JasperExportManager`/`JRXlsxExporter` -> bytes), stored-procedure query executer, scriptlet helpers (`AmountInWords`, `NumberFormatter`, watermark), report printer, printer mapping. Artifacts (all 7.1.1): `jasperreports`, `jasperreports-pdf`, `jasperreports-excel-poi`, `jasperreports-fonts`, `jasperreports-jdt` | `dmJasper`, `STReportPrinter`, `printerMapping` | - | L2-6 |

### 2.3 `pb-self-toolkit` (`com.paperbiz.selftoolkit`)
| Package | Service(s) | Legacy | Priority |
|---|---|---|---|
| `posting` | `AccountPostingService` (voucher -> ledger lines per voucher type; TDS/TCS lines; FIFO posting; adjustments) | `STAccountPosting` (3.2k lines) | after `stock` + `accounting` |
| `approval` | `ApprovalListService` (generic list/approve/unapprove/cards) + per-document `ApprovalHandler`s (~35) | `STApprovals` (5.4k) + 30 `*Approval` screens | with first business module that needs it |
| `barcode` | `BarcodeService` (reel/bundle numbers, barcode print) | `STServer` | with store/production |
| `credit` | `CreditLimitService`, duplicate-indent / restricted-department checks | `STServer` | with sales/purchase |
| `pricelist` | `PriceListService` (save/delete/best-fit/overhead deductions), `SchemeService` | `priceList`, `Scheme_Server` | before master pricing group |
| `activation` | item/finished item activation | `itemActivation` | with master items |
| `importfmt` | CSV import/export formats | `stImportData` | with master (every `exportCSV`) |
| `popups` | lookups for item property, GST doc info, tax summary, other charges, service grid, stock release, tds popup | popups | with their first entry |
| `enviro` | company/site settings read helpers (`isInventoryAccountingActive`...) | `STServer`, `Stock_Server` | **L2-1** |

### 2.4 Chhote libraries
`dm-library-menu`, `pb-project-menu` (menu names constants -> frontend menu config + permission lookup), `dm-company`, `dm-utility` (admin masters; entries), `dm-web-jobs` (communication; entries), `dm-plugin`, `pb-mi-toolkit` + `pb-st-plugin` (lab analyzers; late), `dm-toolkit-r` (not needed unless a client uses it), `dm-web-cam` (frontend only).

## 3. Common REST contract (har entry par same)
Base: `/api/<module>/<entry>`; JSON; auth header (Bearer JWT; abhi temporary headers `X-Dm-*`).

| Purpose | Endpoint | Request | Response |
|---|---|---|---|
| Screen open defaults | `GET /defaults` | - | entry-specific default values |
| Card list (navigator) | `GET /navigator` | - | `[ {code} ]` in the entry's order |
| Open record | `GET /{code}` | - | full record + audit strip (`preparedBy`, `modifiedBy`...) |
| New | `POST /` | record DTO | `{code}` (server generates key) |
| Edit | `PUT /{code}` | record DTO + `modifyReason{code,remark}` | `{code}` |
| Delete | `DELETE /{code}` | `modifyReason` | - |
| Find popup | `GET /find?start&size&column&q&sort` | `PageQuery` | `PageResult<row>` |
| Lookup (text help) | `GET /lookups/{name}?start&size&q[&params]` | `PageQuery` (starts-with on key column) | `PageResult<{code,name,...}>` |
| Suggest | `GET /suggestions?q&size` | - | `[string]` |
| Export CSV | `GET /export` | - | file (CSV, import-format header) |
| Errors | all | - | `400 {errorNumber, message}` (business) / `500 {errorNumber:0, message}` |

Rules: ek service method = ek transaction; Edit/Delete par modify-reason log; New par nahi; `errorNumber` = jis field par focus jaaye (legacy `isValidText(..., N)`); optimistic-lock wali entries `u_EntDt_LatestLine` bhejti hain ("Record has been changed...").
Template entry: `unitMaster` (`docs/modules/pb-master/unitMaster.md`).

## 4. Frontend library (`paperbiz-frontend`, Next.js App Router, TypeScript)
Stack (CLAUDE.md): AG Grid Community, shadcn/ui, TanStack Query, react-hook-form + zod.

### 4.1 Layout
```
paperbiz-frontend/
  src/app/(auth)/login, select-company, select-site, lock
  src/app/(app)/layout (menu + top bar), home, <module>/<entry>/page.tsx
  src/lib/api (client, errors, paging types)  src/lib/auth (session, JWT)
  src/lib/permission (usePermission, menu)    src/lib/format (number/date)
  src/components/dm/*   (widget library, below)
  src/components/entry/*  (EntryScreen framework)
  src/entries/<module>/<entry>.tsx  (config + custom parts)
```

### 4.2 Widget library (`components/dm`) - spec: `docs/modules/dm-toolkit/<widget>.md`
| Component | Replaces | Key behaviour (from legacy) |
|---|---|---|
| `DmTextBox`, `DmTextArea`, `DmRemarkTextBox` | dmTextBox/TextArea/RemarkTextBox | mandatory = laal left border; maxLength; select-all on focus; disabled keeps value; remark = popup editor |
| `DmNumber` | dmTextBoxNumeric | digits/`.`/leading `-` only; decimals up to N (max 7), `#.##` style no trailing zeros; negative -> 0 if not allowed; natures ROUNDOFF/CEILING/FLOOR |
| `DmTextHelp`, `DmTextHelpMulti` | dmTextHelp(+Multi, Grid) | code+name; popup paged grid; type = starts-with search on key column; Up/Down/Enter/Esc/Tab; First/Prev/Next/Last; blur restores last pick (unless appendable); multi = comma codes |
| `DmSuggest` | dmSuggestBox | auto-suggest strings, free text allowed |
| `DmCheckbox`, `DmTime`, `DmMobile`, `DmDate` | dmCheckBox/TextBoxTime/Mobile | mobile digits per base country; dates dd-MMM-yyyy |
| `DmGrid`, `DmGridPaging`, `DmGridEditPanel` | dmGrid* | AG Grid wrapper; column {field, dbField, type, width}; server paging adapter (`PageQuery`/`PageResult`); row editor dialog (New/Ok/Delete/Close) |
| `DmFind` | dmFind | full-screen find grid; header click = search column; Ctrl+R/F/Arrows/P/K, Esc |
| `DmTopControl`, `DmToolContainer` | dmTopControl | buttons New/Edit/Delete/Card/First/Prev/Next/Last/Find/Print/Save/Cancel/Refresh/Other/Close; modes Browse/New/Edit; A/E/D/P permission; hotkeys F2/Ctrl+N/E/D/T/F/P/S/R/O, Esc; confirm on cancel |
| `DmUserControlStrip` | dmUserControl | prepared/modified/system-generated blogs; control permission |
| `DmAlert`, `DmConfirm` | dmMessageBox | alert then focus returns to the field; confirm Promise |
| `ModifyReasonDialog` | modifyReason | reason lookup + remark (Edit save, Delete) |
| `DmFileUploadButton`, `AttachmentList` | dmFileUploadButton/dmFileManager | rule/size/type; remark; delete; preview |
| `DmDynamicFields`, `DmTermsPanel` | dmGUI, dmTermsConfig | config-driven inputs |
| `DmDisplayStrip`, `DmChart`, `DmMenu` | dmDisplayStrip, dmChart, dmMenu* | summary bar, charts, menu tree |
| `ApprovalScreen`, `DocumentTrackerScreen` | `*Approval`, pb-dashboard lists | settings popup + paged list + card counts |

### 4.3 Entry framework (`components/entry`)
`<EntryScreen config>`: form fields in order (from spec), mode state (`Browse/New/Edit`), top control, find, card/navigator, modify-reason on edit/delete, defaults on open, enable/disable rules (`vl` list => all fields editable only in New/Edit; entry-specific extra rules), save/load mapping (field <-> DTO), field events (callbacks), confirm/alert texts. Custom entries (vouchers with grids) compose the same parts.
Hooks: `useEntryMode`, `useSession` (company/site/year/prefix/tax type/locale), `usePermission(menu)`, `useLookup(source)`.

## 5. Build order (milestones)
| # | Milestone | Backend | Frontend | Done when |
|---|---|---|---|---|
| L1 | Foundation | `dm-base` rest (config, startup) | Next.js scaffold, API client, errors, layout | compile/build pass |
| L2-1 | Login & session | `auth`, `session`, `permission`, `enviro` | login, select company/site, lock, menu from permissions | login with a real DB user works (needs your DB) |
| L2-2 | Entry basics | `datelock`, `changelog`, `crud`, `lookup` | `DmTextBox/Number/TextHelp/Suggest`, `DmTopControl`, `DmFind`, `DmGrid`, dialogs, `EntryScreen` | **vertical slice: `unitMaster` end-to-end** |
| L2-3 | Shared features | `files`, `singleline`, `walkthrough`, `gui`, `terms`, `partyinfo` | uploader, dynamic fields, terms, approval screens | each has tests |
| L2-4 | Stock & accounts | `tds`, `overhead`, `stock`, `accounting`, `posting` | grids/popups for vouchers | **golden tests vs legacy** for stock FIFO and postings |
| L2-5 | Integrations | `egst`, `importdata`, `notify` | popups | contract tests (sandbox APIs) |
| L2-6 | Reports | Jasper 7 | report viewer/print | 73 reports PDF compare |
Phir modules: `master` (spec ke saath dobara), phir purchase/sales/store...

## 6. Testing
- **Unit tests:** pure logic (done for dm-base). 
- **Golden tests:** legacy classes ko compile karke output compare (amount words, number format; next: stock FIFO, posting SQL lists).
- **Integration:** container me SQL Server/Mongo nahi hain, to SQL/Mongo ke tests aapki DB par ya Testcontainers par (aapka faisla). Tab tak compile + mock tests.
- **Frontend:** component tests (hotkeys, lookup behaviour), Playwright for entry flows (CRUD, permissions).

## 7. Faisle (final; aapne "tumhe jaisa lage" kaha, sirf purana version/MD5 nahi chahiye)
1. **Auth:** JWT (signed, chhota access token + refresh): claims = user, PLT, company code, **company DB**, file DB, site, session id. Menu/permissions, site details, `enviro`, feature flags **server cache** me (login par DB se load; permission badle to turant asar; lock timeout bhi server side). **Password:** `UserMast.PassWd` ka `MD5(lower(user)+password)` verify, `PasswordVerifier` interface ke peeche (schema badal nahi sakte aur purani app saath chal sakti hai). MD5 kamzor hai (known risk): baad me, purani app band hone par, strong hash (Argon2id) ka opt-in migration. Abhi se: login rate-limit/lockout, HTTPS, password kabhi log nahi.
2. **Integration tests:** aapki dev/test SQL Server + Mongo (schema-only restore). Main tests likhunga, aap local par chalakar result denge. **Mujhe DB ka schema script chahiye** (tables, stored procedures, `dbo.fieldUDT`, views; data ke bina) taaki columns verify hon. Testcontainers sirf CI me jahan Docker daemon ho.
3. **Frontend:** ek hi Next.js app `paperbiz-frontend/` (module-wise routes), **pnpm**, **Node 22 LTS**, TypeScript strict.
4. **Reports:** **JasperReports 7.1.1** (latest stable; `jasperreports` + `jasperreports-pdf` + `jasperreports-excel-poi`), 73 `.jrxml` reuse; har report ka PDF purane (6.1.1) se compare.
5. **Legacy quirks:** behaviour aur messages abhi same; security/resource bug (SQL injection, parameters, file leak, AND filters) hamesha theek; typo wale messages ek batch me aapko dikha kar baad me; messages ek jagah.
6. **`repairMenu`:** baad me, admin-only data sync (dry-run pehle). `rds*` aur `DML` schema scripts port nahi.
7. **License key:** abhi nahi; `LicensePolicy` khaali interface (default allow).
8. **`dm-toolkit-r`:** chhod diya. **`dm-plugin`:** abhi chhod diya (gate/weighbridge aur direct-print ke milestone par local agent).
Baaki (approval ke baad): L1 (frontend scaffold + `dm-base` ka baaki) aur L2-1 (login/session/permission) se code shuru.
