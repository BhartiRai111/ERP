# pb-app / paperbiz_lock

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/paperbiz/src/paperbiz_lock/`). Hand-written notes are in the "Notes" section.

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
| client/Lock.java | 140 |
| client/Lock_Dsg.java | 143 |

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmStatic.client`, `paperbiz.login.client.Login`, `paperbiz.login.shared.Login_Model`, `paperbiz.login.shared.Login_Srv`, `paperbiz.login.shared.Login_SrvAsync`, `paperbiz.login.shared.Login_Validation`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
Lock screen: `lockUser` se session status LOCK; unlock ke liye password (user ka password `validateUser` ya SQL DB password `validateDBPassword`). `UserMast.UserLockTimeOut` (minutes) ke baad session khud lock hota hai (`DmSession.setLockTimeOut`).
