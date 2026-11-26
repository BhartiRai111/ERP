# dm-toolkit: login, session, permission (L2-1)

Code: backend `dm-toolkit` (`com.dataman.toolkit.auth|session|permission`), frontend `paperbiz-frontend/src/lib/session`, `src/components/session`, `src/app/login`, `src/app/page.tsx`.

## Screens
- **Login** (`/login`): User name, Password, Login. Name is mandatory ("User Name is mandatory."); an empty password is allowed (as before). Wrong user or password: "Access denied". After login the selection step shows only what is still not chosen, in the old order: company (PLT), financial year (company), site. A single choice is taken automatically.
- **Home** (`/`): company, year, site, user; buttons Change year/site, Lock, Logout; the menu of every module the user may use (tree from permissions).
- **Lock screen**: covers the app; opened with the user's own password; Logout available.

## Server flow
| Step | Rule (legacy `validateUser`, `getPLTs`, `getCompanys`, `getSites`) | Tables |
|---|---|---|
| Login | `userMast` row with `isActive=1` matching user name (or e-mail / mobile for those modes); exactly one row; `PassWd` must equal `MD5(lower(user_Name)+password)` (hex, any case) computed by SQL Server `HashBytes` behind `PasswordVerifier`. Session starts LOGGED_IN with the user's `UserLockTimeOut`. | `userMast`, `userNature` |
| PLT | SA/SUPER: all. Others: PLTs where `userSite` has the user (via `company`); linked account from `userLinkAccount`. | `productLicensedTo`, `company`, `userSite`, `userLinkAccount` |
| Company | companies of the PLT, newest start date first; non-SA only those in `userSite`; gives company DB (`CentralData_Path`) and file DB. | `company`, `userSite` |
| Site | non-SA: `userSite.SiteList` (pipe wrapped codes) and `CostCenterList`; sites of that PLT with a branch; none: "Permission denied." (company stays unselected); linked account name from `subGroup.displayName`. | `siteMast` (company DB), `branchMast`, `subGroup` |
| Menus | per module in `sNo` order from `user_Permission_Web` (active, `comp_Code` = company or -1); tree by parent/level; letters A/E/D/P; view/save/delete limit dates = today minus (days-1); value limit; control groups; voucher limits from `user_Voucher_Permission`. Cached on the server session until the next selection. | `user_Permission_Web`, `menuCodification`, `user_Control_Permission_Web`, `user_Voucher_Permission`, `voucher_Type` |

## REST
`POST /api/auth/login {userName,password,mode}`, `POST /api/auth/select {pltCode,companyCode,siteCode}`, `GET /api/auth/me`, `POST /api/auth/lock`, `POST /api/auth/unlock {password}`, `POST /api/auth/logout`, `GET /api/permissions`, `GET /api/permissions/menu/{module}`. Login/select/me answer with the lists, what is selected, and a **new token** for the selection. Errors: `400 {errorNumber,message}`; no/expired token `401`; locked session `423`.

## Token and session
- Token: signed (HS256), claims only user, session id, PLT, company code, company DB, file DB, site, expiry (`dataman.auth.token-minutes`, default 480). Secret `dataman.auth.jwt-secret` (>= 32 characters) comes from the environment.
- Everything else lives on the server (`SessionStore`): `SessionInfo`, permissions, lock state. Idle longer than the user's lock time-out: LOCKED (only `/api/auth/**` allowed). Idle a whole token life: dropped. One backend instance only; several instances need a shared store.
- `JwtContextFilter` sets the user context (user, company DB, site, address) for every `/api/**` call except login. Dev mode `dataman.auth.dev-headers=true` accepts `X-Dm-*` headers instead (never in production).
- Frontend keeps the token in memory + `sessionStorage`; the API client sends `Authorization: Bearer`; 401 sends the user to login, 423 shows the lock screen.

## Changed from legacy (security)
- Unlock: legacy compared the typed password with the **SQL Server login password**; now the user's own password is checked.
- Wrong passwords are counted per user + address: 5 in a row block for 15 minutes (`dataman.auth.max-failures`, `lock-minutes`).
- User and company are SQL parameters (legacy pasted them into the SQL text). MD5 stays (known risk); `PasswordVerifier` is the switch point for a strong hash.
- Keep `sendStringParametersAsUnicode=false` on the JDBC URL so `HashBytes` sees the same bytes as before.

## Not done yet
- `eGstParam` of the site (E-GST, L2-5), enviro/feature flags (`enviro`, `enviroCompany`), user favourites (`user_Favorite_Web`), `linkVerification` password-change links, `user_Restrict_System` (allowed IP list), `repairMenu` (admin menu sync).
- SQL runs only against the real databases: needs the dev DB for the integration test (login with a real user).
