# pb-app / paperbiz_common

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/paperbiz/src/paperbiz_common/`). Hand-written notes are in the "Notes" section.

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
| client/Common.java | 14 |
| client/Common_Res.java | 150 |
| server/Common_SrvImpl.java | 539 |
| server/KeyCommunicator.java | 283 |
| server/KeyParam.java | 30 |
| shared/Common_Model.java | 38 |
| shared/Common_Srv.java | 21 |
| shared/Common_SrvAsync.java | 20 |

## RPC interface
| method | params | returns |
|---|---|---|
| reInitialize | PCSEnum pcsEnum | DmResult<PCSData> |
| getUserPermission |  | DmResult<UserPermission> |
| getPACWebPort |  | DmResult<Integer> |
| getCFM |  | DmResult<CustomizeFunctionality_Mdl> |

## Server `KeyCommunicator.java` (283 lines)
### `getMyScoket`
`public KeyParam getMyScoket(KeyParam keyPara)`

- Other calls: `Thread.sleep`

### `createSpace`
`public char[] createSpace(String val, int len)`


### `convetObjectToByteArr`
`public byte[] convetObjectToByteArr(KeyParam keyout)`

- Other calls: `ByteBuffer.allocate`, `CharBuffer.wrap`, `Charset.forName`

### `convertByteArrayToObject`
`public KeyParam convertByteArrayToObject(byte[] readData)`

- Other calls: `ByteBuffer.wrap`

## Server `Common_SrvImpl.java` (539 lines)
Helpers used: `DmServer.getCompanys`, `DmServer.getConnection`, `DmServer.getDMJasperVariables`, `DmServer.getManageUser`, `DmServer.getPLTs`, `DmServer.getSites`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnFileDBName`, `DmServer.getSsnPLTCode`, `DmServer.getSsnSiteCityName`, `DmServer.getSsnSiteClientCode`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteDisplayName`, `DmServer.getSsnSiteGSTIN`, `DmServer.getSsnSiteSerialKey`, `DmServer.getUserPermission`, `DmServer.setDMJasperVariables`, `DmServer.setManageUser`, `DmServer.setSsnNoOfUser`, `DmServer.userLogingLog`, `DmServer.validateSite`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.xNull`

### `reInitialize`
`public DmResult<PCSData> reInitialize(PCSEnum pcsEnum)`


### `getUserPermission`
`public DmResult<UserPermission> getUserPermission()`

- Other calls: `STServer.setSsnEnviro`, `STServer.setSsnUserLinkDetails`

### `getPACWebPort`
`public DmResult<Integer> getPACWebPort()`


### `validateKey`
`public DmResult<String> validateKey()`

- Errors: "Invalid Key." | "License key wil be expiring on "
- Other calls: `STServer.setSsnNoOfMachine`

### `getCFM`
`public DmResult<CustomizeFunctionality_Mdl> getCFM()`

- Other calls: `STServer.getSsnCFM`, `STServer.setSsnCFM`

## Server `KeyParam.java` (30 lines)
## Depends on (imports from other packages)
`dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `paperbiz.common.shared.Common_Srv`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| validateKey | `if (keyOutputParams.RetVal != 1)` | "Invalid Key." |
| validateKey | `if (cal.getTime().getTime() >= expiryDate.getTime().getTime())` | "License key wil be expiring on " |

## Notes (hand-written)
**Shell ke common services:**
- `reInitialize()`: session ka PLT/company/site/user data dobara load (company ya site badalne par).
- `getUserPermission()`: user ke menu/control/voucher permissions + `STServer.setSsnEnviro` (site/company settings `enviro`, `enviroCompany` session me) + `setSsnUserLinkDetails` (user ka linked account).
- `getPACWebPort()`, `getMyScoket()` (WebSocket, chat/notification).
- **`validateKey()`:** **license key** check (**"Invalid Key."**, **"License key wil be expiring on ..."**) aur `STServer.setSsnNoOfMachine` (kitni machines/users allowed).
- `getCFM()`: `CustomizeFunctionality` = feature flags (jaise production module active ya nahi, `CFM.isProductionModuleActive`).
