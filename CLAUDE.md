# PaperBiz: GWT → Spring Boot + Next.js (logic-first rewrite)

## Goal
Rebuild the PaperBiz GWT ERP (old folders `paperbiz/` + `dataman_V2/`) as:
- **Backend:** Spring Boot **4.1.x**, **Java 21**, Maven multi-module **modular monolith** (later split into microservices, module by module)
- **Frontend:** **Next.js** (App Router, TypeScript) + AG Grid Community + shadcn/ui + TanStack Query + react-hook-form/zod
- **Database:** the **existing MS SQL Server schema, unchanged**. Do not create or alter tables (no Flyway/Liquibase/ddl-auto).
- Business behaviour must stay the same as the GWT app ("jaisa bana hai waisa hi").

## Approach (changed): understand the logic, then rewrite
This is **not** a line-by-line port and there is **no compatibility layer**. Do not copy GWT code into the new project.
- **Business modules:** first study how each module works: its entries (screens), the logic behind each, tables read/written, validations, save/edit/delete/approve flow, reports, links to other modules. Then design and write fresh Spring Boot code that implements the same behaviour.
- **Libraries (`dm-*`, `pb-*` libraries):** take only selective help from the GWT code; write them cleanly.
- Old GWT code lives in `legacy-gwt/` (reference only, git-ignored).

## Working rules (important)
1. **Ask before making any change. Only proceed after the user says yes** ("pehle puchha karo, jab main haan bolu tabhi karo").
2. **Ask before every commit and every push.** Never commit/push on your own, even if a hook asks.
3. **Never commit or push `legacy-gwt/`.**
4. Work in small steps: show plan, get approval, implement, show `mvn -q compile` result, wait for approval.
5. Reply to the user in Hinglish.
6. Develop on branch `next` (decided by the user; `Other` holds the earlier work).

## Per-module workflow
1. **Understand (read only):** list entries, flows, tables, validations, reports, dependencies.
2. **Spec files:** write **one folder per module** `docs/modules/<module>/` with **one file per entry** (`<entry>.md`: the full detail of that entry) plus `_module.md` for module-wide things (menu mapping, shared concerns, dependencies). No README files. Libraries (`dm-toolkit`, `pb-self-toolkit`, frontend library) are documented the same way, one file per feature/widget.
   An entry spec must cover **both sides**: screen (fields in order with widget type, lookups/text-helps and their source, mandatory, numeric decimals, grid columns, buttons, enable/disable per mode New/Edit/Browse, default values on open, field change events) and server (CRUD flow, SQL, field-to-column mapping, validations, delete rules, errors).
3. **Design:** REST endpoints, services, DTOs; user approves.
4. **Code + compile:** `mvn -q compile` for the module; user approves.

## Order of work (decided with the user)
Goal: same project, same logic, same entry points (not same code). Backend and frontend are built **together**.
1. Skeleton of all Maven modules (done).
2. **Libraries first, both sides:** backend (`dm-base`, `dm-toolkit`, `pb-self-toolkit`, then the rest) and the frontend component library (DmTextBox, DmTextHelp, DmGrid, DmTopControl, DmFind, ModifyReason ...). Spec first, then code.
3. Then modules entry by entry, starting with `master` (redo with screen-level spec), then purchase / sales / store / production / gate / asset / job work / web job / utility.
4. **`dataman_V2/accounts` is postponed** until purchase/sales/store need it.
- Common things needed by many entries live in the shared libraries (`dm-*`, `pb-self-toolkit`), never copied into a module.

## Decisions (library design, `docs/design/library-design.md` sec 7)
- Auth: JWT; password verified against `UserMast.PassWd` (legacy MD5) behind a `PasswordVerifier` interface (known risk; strong-hash opt-in migration later); permissions/session data cached server side.
- Frontend: one Next.js app `paperbiz-frontend/`, pnpm, Node 22 LTS. Reports: **JasperReports 7.1.1** (never the old 6.1.1). License check removed (`LicensePolicy` no-op). `dm-toolkit-r` and `dm-plugin` deferred.
- **Always use latest stable library versions**; no legacy jars. Legacy behaviour/messages stay the same unless the user approves a fix; security/resource bugs are always fixed.
- Integration tests need the user's dev SQL Server + Mongo and a schema-only script (no DB in the container).

## Repo layout
```
paperbiz-backend/        Maven parent (Spring Boot 4.1, Java 21)
  dm-base, dm-toolkit, dm-toolkit-r, dm-utility, dm-plugin,
  dm-company, dm-library-menu, dm-web-jobs, dm-accounts          (from dataman_V2)
  pb-self-toolkit, pb-mi-toolkit, pb-st-plugin, pb-project-menu   (PaperBiz libraries)
  pb-master, pb-sales, pb-purchase, pb-store, pb-production, pb-gate,
  pb-job-work, pb-lab, pb-assets, pb-budget, pb-transport, pb-dashboard
  pb-app                 main Spring Boot app (replaces old paperbiz/paperbiz)
paperbiz-frontend/       Next.js
docs/modules/            analysis notes, one per module
legacy-gwt/              old GWT source, reference only, git-ignored
```
`dmTheme`, `dmWebCam` (and GwtBootstrap3/htmlEditor) are UI-only: no backend module.

## Package names
New clean names (not the old `dataman.*` / `paperbiz.*`): `com.paperbiz.<module>` for `pb-*`, `com.dataman.<lib>` for `dm-*` (assumed; user to confirm).

## Module dependencies (from the old NetBeans classpaths)
- Libraries: `dm-library-menu`→dm-base; `dm-toolkit`→dm-base, dm-library-menu; `dm-toolkit-r`→dm-base; `dm-plugin`→dm-base, dm-library-menu; `dm-utility`, `dm-company`, `dm-web-jobs`, `dm-accounts`→dm-base, dm-toolkit, dm-library-menu
- `pb-project-menu`→dm-base, dm-toolkit; `pb-self-toolkit`→dm-base, dm-toolkit, dm-library-menu, pb-project-menu; `pb-mi-toolkit`→pb-project-menu; `pb-st-plugin`→dm-base, dm-plugin, dm-library-menu, pb-mi-toolkit, pb-project-menu
- Business modules → dm-base, dm-toolkit, pb-project-menu, pb-self-toolkit, plus dm-library-menu for master/sales/purchase/store/production/gate/job-work/lab/dashboard (not assets/budget/transport)
- `pb-app` → all of the above
- `dmBuild.jar` (used by dmBase and paperbiz/paperbiz) is not in the source; check when porting dm-base.

## Legacy codebase facts
- ~1.86M lines Java; per feature `client/` (GXT UI), `shared/` (`*_Mdl`, `*_Vld`, `*_Srv`), `server/` (`*_SrvImpl`); ~518 RPC service impls
- Server: raw JDBC via `DmPreparedStatement`, MS SQL (multi-DB via `setCatalog` per company), MongoDB for files, JasperReports 6.1.1 (73 `.jrxml`), POI, iText 5, Gson, BoneCP
- Session: custom `DmSession` with static tables; ~9.6k `getSsn…(this)` calls (do not carry this design over)

## Definition of done per step
- `mvn -q compile` passes for the module(s) touched
- No DB schema change
- User has reviewed and approved
