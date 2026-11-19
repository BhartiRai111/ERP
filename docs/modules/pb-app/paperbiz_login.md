# pb-app / paperbiz_login

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/paperbiz/src/paperbiz_login/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: -
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `Login`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmTextBox | `txtUserName` | `` |

## Files
| file | lines |
|---|---|
| client/Login.java | 191 |
| client/Login_Dsg.java | 223 |
| server/Login_SrvImpl.java | 106 |
| shared/Initialze_Mdl.java | 21 |
| shared/Login_Model.java | 37 |
| shared/Login_Srv.java | 17 |
| shared/Login_SrvAsync.java | 17 |
| shared/Login_Validation.java | 14 |

## Models (shared DTOs)
### Initialze_Mdl
## RPC interface
| method | params | returns |
|---|---|---|
| initialize |  | DmResult<Initialze_Mdl> |
| validateUser | Login_Model.Body bTemp | DmResult<PCSData> |
| validateDBPassword | String password | DmResult<Boolean> |
| lockUser |  | DmResult<String> |

## Server `Login_SrvImpl.java` (106 lines)
Helpers used: `DmServer.compareDBPassword`, `DmServer.getCompanys`, `DmServer.getPLTs`, `DmServer.getSites`, `DmServer.getSsnPLTCode`, `DmServer.getSsnUserDescription`, `DmServer.getSsnUserName`, `DmServer.validateUser`, `DmShared.xNull`

### `validateUser`
`public DmResult<PCSData> validateUser(Login_Model.Body bTemp)`

- Other calls: `DmLocaleCompany.getLocaleViaPltCode`, `Login_Validation.chkLogin`

### `validateDBPassword`
`public DmResult<Boolean> validateDBPassword(String password)`


### `lockUser`
`public DmResult<String> lockUser()`


### `initialize`
`public DmResult<Initialze_Mdl> initialize()`


## Depends on (imports from other packages)
`dataman.dmBase.dmSession.client`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmInputBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `paperbiz.login.shared.Initialze_Mdl`, `paperbiz.login.shared.Login_Model`, `paperbiz.login.shared.Login_Srv`, `paperbiz.login.shared.Login_SrvAsync`, `paperbiz.login.shared.Login_Validation`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
**Login screen (shell ka pehla step).** Fields: User Name, Password (client `Login_Validation.chkLogin`).
**Flow (`validateUser`):**
1. `DmServer.validateUser(USERNAME, user, password)` (MD5 check, dekhein `dm-toolkit/dmStatic`). Galat ho to **"Access denied"**.
2. Sahi ho to session status `LOGGED_IN`.
3. `getPLTs` -> PLT list (aur login date). `pcsMain.setLocaleCompanyCode(DmLocaleCompany.getLocaleViaPltCode(plt))`.
4. Agar PLT select ho gaya (sirf ek ho to) -> `getCompanys`; company select ho gayi to -> `getSites` (regional language, site list). Site me error aaye to company selection reset aur error message.
5. Response `PCSData` = user, PLT/company/site lists + selected + login date + locale.
**Aur:** `initialize()` -> mobile digit rules (base locale ke `mobileDigit`). `lockUser()` -> session status **LOCK** (lock screen). `validateDBPassword(password)` -> `compareDBPassword` (lock screen unlock me **SQL user ka DB password** match hota hai).
**Naya design:** `POST /api/auth/login` -> JWT (`UserContext`: user, PLT, company DB, site), `POST /api/auth/select-company`, `/select-site`, `POST /api/auth/lock` / `unlock`. Same messages ("Access denied").
