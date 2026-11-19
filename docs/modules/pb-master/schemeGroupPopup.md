# pb-master / schemeGroupPopup

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/master/src/schemeGroupPopup/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: schemeGroupMast, schemeGroupDetail
- Update tables: schemeGroupMast
- Delete cascade (in order): schemeGroupDetail, schemeGroupMast
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Scheme Group Name (Text); Item Name (Text)
- Lookups/suggestions: getSuggestionSchemeGroup, getTxtHItemType
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `SchemeGrouprPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Scheme Group Name | DmSuggestBox | `txtSchemeGroupName` | `100, 10, true, "name", DmDataType.STRING` |
| 2 | - | DmTextHelpMulti | `txtHItemName` | `TxtHItemType_PrpAcs, 5, true` |

**Grid / popup columns** (header, width, db field):
- `txtHItemName`: Name [Text, w180, name]; Manual Code [Text, w100, manualCode]; Item Category [Text, w150, itemCategory]

## Files
| file | lines |
|---|---|
| client/SchemeGroupPopup.java | 297 |
| client/SchemeGrouprPopup_Dsg.java | 202 |
| server/SchemeGroupPopup_SrvImpl.java | 456 |
| shared/SchemeGroupPopup_Srv.java | 21 |
| shared/SchemeGroupPopup_SrvAsync.java | 21 |
| shared/SchemeGroupPopup_Vld.java | 13 |
| shared/SchemeGroup_Mdl.java | 32 |
| shared/TxtHItemName_Mdl.java | 50 |

## UI labels / columns (client)
`Scheme Group Name`, `Item Name`

## Models (shared DTOs)
### SchemeGroup_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | itemCodeCsv |
| String | itemNameCsv |

### TxtHItemName_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | itemCategoryCode |
| String | itemCategoryName |
| String | manualCode |
| String | manualName |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHItemType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemName_Mdl>> |
| saveSchemeGroup | SchemeGroup_Mdl mrTemp | DmResult<SchemeGroup_Mdl> |
| getSuggestionSchemeGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getMoveRec | String strKey | DmResult<SchemeGroup_Mdl> |
| deleteRecord | String strKey | DmResult<String> |

## Validator `SchemeGroupPopup_Vld`
```java
public class SchemeGroupPopup_Vld {
    public static void chkSave(SchemeGroup_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getSharedKeyFieldName(), "Scheme Group Name", 100);
        DmShared.isValidText(mrTemp.getItemCodeCsv(), "Item Name", 200);
    }
}
```

## Server `SchemeGroupPopup_SrvImpl.java` (456 lines)
Helpers used: `DmServer.chkIfDocumentUsed`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnUserName`, `DmServer.setSsnOldData`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `saveSchemeGroup`
`public DmResult<SchemeGroup_Mdl> saveSchemeGroup(SchemeGroup_Mdl mrTemp)`

- SQL 1: `select count(*) as cnt from schemeGroupMast where name =:name AND code <> :code`
- SQL 2: `Insert Into schemeGroupMast(code, name , preparedBy, preparedDt, preparedByIP, isActive ) Values (:code,:name , :preparedBy,:preparedDt, :preparedByIP, :isActive)`
- SQL 3: `Update schemeGroupMast SET name=:name , u_Name = :u_Name, u_EntDt = :u_EntDt WHERE code=:code`
- SQL 4: `DELETE FROM schemeGroupDetail WHERE code = :code`
- SQL 5: `Insert Into schemeGroupDetail(code,sno,itemCode) values( :code,:sno,:itemCode)`
- Params: `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:isActive` ← `"1"`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `DmServer.getServerTimestamp()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:sno` ← `DmShared.iNull(sNo).toString()`; `:itemCode` ← `DmShared.foriegnKey(itemCode)`
- Errors: "Invalid key." | "Scheme Group already exist."
- Other calls: `ModifyReason_Server.saveNew`, `SYSTEM_ACTIVITY.getName`, `SchemeGroupPopup_Vld.chkSave`

### `getSuggestionSchemeGroup`
`public DmResultPageing<List<String>> getSuggestionSchemeGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select sg.name as name From schemeGroupMast as sg`

### `getTxtHItemType`
`public DmResultPageing<List<TxtHItemName_Mdl>> getTxtHItemType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , im.itemCategoryCode , ict.name as itemCategoryName,im.manualCode FROM itemMast im left Join itemCat ict on ict.code=im.itemCategoryCode WHERE (im.filterItemType = :filterItemType or im.isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`

### `getMoveRec`
`public DmResult<SchemeGroup_Mdl> getMoveRec(String strKey)`

- SQL 1: `select sgm.code, sgm.name from schemeGroupMast sgm where sgm.code=:code`
- SQL 2: `SELECT STRING_AGG (CONVERT(NVARCHAR(max),sgd.itemCode), ',') AS codeCsv , STRING_AGG (CONVERT(NVARCHAR(max),im.name), ',') as nameCsv FROM schemeGroupDetail sgd LEFT JOIN itemMast im ON sgd.itemCode = im.code WHERE sgd.code = :code`
- Params: `:code` ← `DmShared.iNull(strKey).toString()`; `:code` ← `DmShared.iNull(strKey).toString()`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey)`

- SQL 1: `Delete From schemeGroupDetail Where code = :code`
- SQL 2: `Delete From schemeGroupMast Where code = :code`
- Params: `:code` ← `strKey`; `:code` ← `strKey`
- Errors: "Invalid key."
- Other calls: `ModifyReason_Server.saveNew`, `SYSTEM_ACTIVITY.getName`

## Depends on (imports from other packages)
`dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.modifyReason.server`, `dataman.dmToolkit.modifyReason.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stStatic.server`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveSchemeGroup | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveSchemeGroup | `if (rsTemp.getLong("cnt") > 0)` | "Scheme Group already exist." |
| deleteRecord | `if (DmShared.xNull(strKey).trim().equalsIgnoreCase(""))` | "Invalid key." |

## Notes (hand-written)
_No hand-written notes yet._
