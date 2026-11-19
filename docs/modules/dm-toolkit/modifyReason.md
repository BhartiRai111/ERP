# dm-toolkit / modifyReason

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/modifyReason/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHReason
- CSV export: no

## Files
| file | lines |
|---|---|
| client/ModifyReason.java | 222 |
| client/ModifyReason_Dsg.java | 195 |
| server/ModifyReason_Server.java | 167 |
| server/ModifyReason_SrvImpl.java | 195 |
| shared/GrdCancelReason_Mdl.java | 101 |
| shared/ModifyData_Mdl.java | 149 |
| shared/ModifyReason_Mdl.java | 53 |
| shared/ModifyReason_Srv.java | 17 |
| shared/ModifyReason_SrvAsync.java | 17 |

## UI labels / columns (client)
`Reason`, `Remark`

## Models (shared DTOs)
### ModifyReason_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | mode |
| String | remark |
| List<GrdCancelReason_Mdl> | lstCancelReason |

### GrdCancelReason_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | docId |
| String | v_SNo |
| String | mode |
| String | modifyReason |
| String | remark |
| String | dateTime |
| String | userName |
| String | siteSName |

### ModifyData_Mdl
| type | field |
|---|---|
| String | keyId_1 |
| String | keyName_1 |
| String | keyDescription_1 |
| String | keyId_2 |
| String | keyName_2 |
| String | keyDescription_2 |
| String | tableName |
| String | vrTypeSName |
| String | vrTypeDescription |
| String | moduleName |
| String | menuName |
| String | controlText |
| String | oldData |
| String | newData |
| String | modelClassName |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHReason | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getEditResion | String primaryId, String tableName, long secondaryId | DmResult<List<GrdCancelReason_Mdl>> |
| getReasonDetails | String keyId_1, String keyId_2, String tableName | DmResult<List<GrdCancelReason_Mdl>> |

## Server `ModifyReason_SrvImpl.java` (195 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getMdbCollection`, `DmServer.getSsnDBName`, `DmServer.getSsnPLTCode`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.xNull`

### `getTxtHReason`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHReason(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select code, name from modifyReasonMast`

### `getEditResion`
`public DmResult<List<GrdCancelReason_Mdl>> getEditResion(String primaryId, String tableName, long secondaryId)`


### `getReasonDetails`
`public DmResult<List<GrdCancelReason_Mdl>> getReasonDetails(String keyId_1, String keyId_2, String tableName)`


## Server `ModifyReason_Server.java` (167 lines)
Helpers used: `DmServer.getClientIpAddress`, `DmServer.getLocalIPAddresses`, `DmServer.getMdbCollection`, `DmServer.getServerTimestamp`, `DmServer.getSsnCompanyCYear`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnId`, `DmServer.getSsnPLTCode`, `DmServer.getSsnPLTName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteName`, `DmServer.getSsnSiteSName`, `DmServer.getSsnUserDescription`, `DmServer.getSsnUserName`, `DmShared.iNull`, `DmShared.xNull`

### `save`
`public static void save(ModifyReason_Mdl modifyReason_Mdl, String primaryId, String tableName, long secondaryId, DmRSS dmRSS) throws Exception`

- Errors: "Reason can't empty."

### `getDefaultMdl`
`public static ModifyReason_Mdl getDefaultMdl(String mode, String remark)`


### `saveNew`
`public static void saveNew(ModifyData_Mdl modifyReasonData_Mdl, ModifyReason_Mdl modifyReason_Mdl, DmRSS dmRSS) throws Exception`

- Errors: "Reason can't empty."

### `getModifyReasonDataMdl`
`public static ModifyData_Mdl getModifyReasonDataMdl(String keyId_1, String keyName_1, String keyDescription_1, String tableName, String moduleName, String menuName, String oldData, String newData)`


### `getModifyReasonDataMdl`
`public static ModifyData_Mdl getModifyReasonDataMdl(String keyId_1, String keyName_1, String keyDescription_1, String tableName, String moduleName, String menuName, String vrTypeSName, String vrTypeDescription, String oldData, String newData)`


### `getModifyReasonDataMdl`
`public static ModifyData_Mdl getModifyReasonDataMdl(String keyId_1, String keyName_1, String keyDescription_1, String keyId_2, String keyName_2, String keyDescription_2, String tableName, String moduleName, String menuName, String vrTypeSName, String vrTypeDescription, String oldData, String newData)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.modifyReason.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
**Kya hai:** Edit/Delete par "kyun badla" ka popup + **change log**. 645 files use karte hain; master ki har entry ye call karti hai.
**Popup (client):** reason lookup (`getTxtHReason`: SQL `modifyReasonMast(code, name)`), remark text; mode = New/Edit/Delete.
**Log kahan jaata hai: MongoDB (SQL nahi).** Company ke file DB me collection **`datamodificationlog`** (`ModifyReason_Server.saveNew`). Har document: sessionId, pltCode/pltName, companyCode, financialYear, siteMCode/siteName, userName/userDescription, ipAddresses, logDate, keyId_1/keyName_1/keyDescription_1 (+ _2), tableName, v_type short name/description, moduleName, menuName, controlText, mode, modifyReasonCode/modifyReason, remark, **oldData, newData** (model ka text), modelClassName.
- Reason code khali/0 ho to error **"Reason can't empty."**
- Purani collection `modifyreason` (`save(...)`) ab bhi hai (pltCode, siteCode, docId, secondaryId, tableName, reason, remark, mode, user, time, IP).
- `getDefaultMdl(mode, remark)`: reason code "1" = **"System Generated"** (jab user se poochna nahi hota).
- `oldData/newData` har entry apne `saveLog` me banati hai (session me rakhe purane model aur naye model ka fark).
**Naya design:** `ModifyReasonService.log(...)` jo Mongo me ye hi document likhe (`MongoFileStore` jaisa Mongo client, `dm-base`); purana model session me nahi, DB se request ke andar padh kar fark banega. Frontend: `ModifyReasonDialog` (lookup + remark).
