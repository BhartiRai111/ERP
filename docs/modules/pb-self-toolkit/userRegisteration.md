# pb-self-toolkit / userRegisteration

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/userRegisteration/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHMobileApp
- CSV export: no

## Files
| file | lines |
|---|---|
| client/UserRegisteration.java | 385 |
| client/UserRegisteration_Dsg.java | 257 |
| server/UserRegisteration_SrvImpl.java | 437 |
| shared/DefaultValue_Mdl.java | 115 |
| shared/UserRegisteration_Srv.java | 24 |
| shared/UserRegisteration_SrvAsync.java | 24 |

## UI labels / columns (client)
`User Name`, `User Role`, `Mobile`, `E-mail`

## Models (shared DTOs)
### DefaultValue_Mdl (extends `KeyField implements Serializable,IsSerializable`)
| type | field |
|---|---|
| String | userCode |
| String | userName |
| String | subCode |
| String | administratorCode |
| String | administratorName |
| String | lockTimeOut |
| String | natureCode |
| String | natureName |
| String | description |
| String | mobile |
| String | email |

## RPC interface
| method | params | returns |
|---|---|---|
| getDefaultValues | String subCode, String userRole | DmResult<DefaultValue_Mdl> |
| getMoveRec | String strKey | DmResult<DefaultValue_Mdl> |
| saveRecord | DefaultValue_Mdl mrTemp | DmResult<String> |
| deleteRecord | String subCode, String userName | DmResult<String> |
| chkUserAvailability | String userName | DmResult<String> |
| genrateUser | String partyName,String userRole | DmResult<String> |
| getTxtHMobileApp | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Server `UserRegisteration_SrvImpl.java` (437 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getFieldUDT`, `DmServer.getManageUser`, `DmServer.getSsnDBName`, `DmServer.getSsnPLTCode`, `DmServer.getSsnSiteCode`, `DmServer.validUserName`, `DmShared.iNull`, `DmShared.xNull`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues(String subCode, String userRole)`

- SQL 1: `SELECT um.code , um.user_Name from userMast um LEFT JOIN userLinkAccount ula on ula.user_Name = um.user_Name AND ula.pltCode = :pltCode where ula.subcode = :subCode`
- SQL 2: `SELECT sg.subcode,sg.displayName,sg.email,sg.mobile FROM subGroup sg WHERE sg.subcode = :subCode`
- Params: `:pltCode` ← `DmServer.getSsnPLTCode(this)`; `:subCode` ← `DmShared.xNull(subCode)`; `:userRole` ← `DmShared.xNull(userRole)`; `:subCode` ← `DmShared.xNull(subCode)`

### `getMoveRec`
`public DmResult<DefaultValue_Mdl> getMoveRec(String userCode)`

- SQL 1: `SELECT um.code, um.User_Name , um.Description, um.isAdmin, um.code, um.User_Role, um.email, um.mobile, um.nature, un.name as natureName, um.UserLockTimeOut , ula.subCode FROM UserMast um LEFT JOIN userNature un on un.code = um.nature LEFT JOIN userLinkAccount ula on ula.user_Name = um.User_Name AND ula.pltCode = :pltCode WHERE um.code = :code`
- Params: `:code` ← `userCode`; `:pltCode` ← `DmShared.xNull(DmServer.getSsnPLTCode(this))`

### `saveRecord`
`public DmResult<String> saveRecord(DefaultValue_Mdl mrTemp)`


### `getUserMasterDetail`
`private UserMaster_Mdl getUserMasterDetail(DefaultValue_Mdl dvm) throws Exception`


### `deleteRecord`
`public DmResult<String> deleteRecord(String subCode, String userName)`


### `getTxtHMobileApp`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHMobileApp(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ma.code, ma.name, ma.shortName FROM mobileApp ma WHERE ma.isActive = :one`

### `chkUserAvailability`
`public DmResult<String> chkUserAvailability(String userName)`

- SQL 1: `SELECT COUNT(user_Name) cnt FROM userMast where user_Name = :user_Name`
- Params: `:user_Name` ← `userName`

### `genrateUser`
`public DmResult<String> genrateUser(String partyName, String userRole)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxMobile.client`, `dataman.dmToolkit.dmToolContainer.client`, `paperbiz.selfToolkit.userRegisteration.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
