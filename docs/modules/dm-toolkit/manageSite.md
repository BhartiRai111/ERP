# dm-toolkit / manageSite

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/manageSite/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHSiteList
- CSV export: no

## Files
| file | lines |
|---|---|
| client/ManageSite.java | 321 |
| client/ManageSite_Dsg.java | 152 |
| server/ManageSite_SrvImpl.java | 239 |
| shared/GridSite_Mdl.java | 23 |
| shared/ManageSite_Srv.java | 20 |
| shared/ManageSite_SrvAsync.java | 20 |

## Models (shared DTOs)
### GridSite_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | delete |

## RPC interface
| method | params | returns |
|---|---|---|
| getSiteList | String tableName, String keyFieldName, String keyFieldValue, String siteFieldName | DmResult<List<GridSite_Mdl>> |
| saveRecord | List<GridSite_Mdl> mrTemp, String tableName, String keyFieldName, String keyFieldValue, String siteFieldName | DmResult<String> |
| getTxtHSiteList | DmHelper pageing, boolean blnToEndOfFile, String siteList | DmResultPageing<List<KeyFieldExtended>> |
| deleteSiteList | String tableName, String keyFieldName, String keyFieldValue, String siteFieldName, String siteFieldValue | DmResult<String> |

## Server `ManageSite_SrvImpl.java` (239 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmShared.xNull`

### `getSiteList`
`public DmResult<List<GridSite_Mdl>> getSiteList(String tableName, String keyFieldName, String keyFieldValue, String siteFieldName)`

- SQL 1: `SELECT sm.name,sm.code FROM {…} sl LEFT JOIN sitemast sm ON sl. {…} =sm.code WHERE sl. {…} = :code`
- Params: `:code` ← `keyFieldValue`

### `saveRecord`
`public DmResult<String> saveRecord(List<GridSite_Mdl> mrTemp, String tableName, String keyFieldName, String keyFieldValue, String siteFieldName)`

- SQL 1: `DELETE FROM {…} where {…} = :code`
- Params: `:code` ← `DmShared.xNull(keyFieldValue)`; `:code` ← `DmShared.xNull(keyFieldValue)`; `:name` ← `DmShared.xNull(mrTemp.get(i).getSharedKeyFieldCode())`
- Errors: "Parameters Name is Blank"

### `getTxtHSiteList`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSiteList(DmHelper helper, boolean blnToEndOfFile, String siteList)`

- SQL 1: `SELECT CODE,NAME FROM sitemast WHERE CODE NOT IN ( {…} :siteList {…} )`

### `deleteSiteList`
`public DmResult<String> deleteSiteList(String tableName, String keyFieldName, String keyFieldValue, String siteFieldName, String siteFieldValue)`

- SQL 1: `Delete From {…} where {…} =:code and {…} =:sCode`
- Params: `:code` ← `keyFieldValue`; `:sCode` ← `siteFieldValue`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmHelpGridMulti.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.manageSite.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (DmShared.xNull(tableName).equalsIgnoreCase("") \|\| DmShared.xNull(keyFieldName).equalsIgnoreCase(""))` | "Parameters Name is Blank" |

## Notes (hand-written)
_No hand-written notes yet._
