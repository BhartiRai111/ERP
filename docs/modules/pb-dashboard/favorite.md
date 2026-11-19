# pb-dashboard / favorite

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/dashBoard/src/favorite/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: user_Favorite_Web
- Update tables: user_Favorite_Web
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: -
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `Manage`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `grdAllModule` (DmGrid), `grdFavouriteModule` (DmGrid), `grdAllMenu` (DmGrid), `grdFavouriteMenu` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `grdAllModule`: Module [Text, w1, module]
- `grdFavouriteModule`: Module [Text, w1, module]
- `grdAllMenu`: ... [Text, w180]; Menu Code [Text, w80]
- `grdFavouriteMenu`: ... [Text, w180]; Menu Code [Text, w80]

**Event handlers (condensed code, CRUD flow and field callbacks):**

`setPoitionPopup.addCallBack` → `onOk()`
```java
fillGrdFavModule();
if (moduleName != "") {
fillGrdFavMenu(moduleName);
```
- Alert: "Please select module to open menu."

### Screen `SetPosition`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `grdMenu` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `grdMenu`: Module [Text, w1]

## Files
| file | lines |
|---|---|
| client/Common_Res.java | 128 |
| client/Favorite.java | 176 |
| client/Favorite_Dsg.java | 76 |
| client/Manage.java | 380 |
| client/Manage_Dsg.java | 248 |
| client/SetPosition.java | 267 |
| client/SetPosition_Dsg.java | 161 |
| server/Favorite_SrvImpl.java | 447 |
| shared/Common_Model.java | 35 |
| shared/Favorite_Mdl.java | 84 |
| shared/Favorite_Srv.java | 22 |
| shared/Favorite_SrvAsync.java | 22 |
| shared/GrdModuleMenu_Mdl.java | 32 |
| shared/Image_Mdl.java | 35 |
| shared/MenuDetail_Mdl.java | 23 |
| shared/ModuleDetail_Mdl.java | 30 |

## UI labels / columns (client)
`Favourite`, `Module`, `...`

## Models (shared DTOs)
### Favorite_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | allModuleName |
| String | allMenuName |
| String | allMenuText |
| String | favModuleName |
| String | favMenuName |
| String | favMenuText |
| String | grdType |

### Image_Mdl
| type | field |
|---|---|
| String | name |
| ImageResource | img |

### GrdModuleMenu_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | menuName |
| String | menuShortHand |

### MenuDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | menuName |

### ModuleDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | moduleName |
| List<MenuDetail_Mdl> | menuDetailMdl |

## RPC interface
| method | params | returns |
|---|---|---|
| insertRecord | Favorite_Mdl mrTemp | DmResult<String> |
| deleteRecord | Favorite_Mdl mrTemp | DmResult<String> |
| setPosition | List<GrdModuleMenu_Mdl> listTemp, Favorite_Mdl mrTemp | DmResult<String> |
| getModuleList | String moduleType | DmResult<List<GrdModuleMenu_Mdl>> |
| getFillRecords |  | DmResult<List<ModuleDetail_Mdl>> |
| getMenuList | String menuType, String moduleName | DmResult<List<GrdModuleMenu_Mdl>> |

## Server `Favorite_SrvImpl.java` (447 lines)
Helpers used: `DmServer.getConnection`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.iNull`, `DmShared.xNull`

### `insertRecord`
`public DmResult<String> insertRecord(Favorite_Mdl mrTemp)`

- SQL 1: `Insert Into user_Favorite_Web (mnuModule, mnuName, mnuText, userName) values (:mnuModule, :mnuName, :mnuText, :userName)`
- Params: `:mnuModule` ← `DmShared.xNull(mrTemp.getAllModuleName())`; `:mnuText` ← `DmShared.xNull(mrTemp.getAllMenuText())`; `:mnuModule` ← `mrTemp.getAllModuleName()`; `:mnuName` ← `mrTemp.getAllMenuName()`; `:mnuText` ← `mrTemp.getAllMenuText()`; `:userName` ← `DmServer.getSsnUserName(this)`
- Errors: "Please select menu to add !"

### `deleteRecord`
`public DmResult<String> deleteRecord(Favorite_Mdl mrTemp)`

- Params: `:mnuModule` ← `DmShared.xNull(mrTemp.getFavModuleName())`; `:mnuText` ← `DmShared.xNull(mrTemp.getFavMenuText())`
- Errors: "Please select menu to remove !"

### `setPosition`
`public DmResult<String> setPosition(List<GrdModuleMenu_Mdl> listTemp, Favorite_Mdl mrTemp)`

- SQL 1: `Update user_Favorite_Web set menuSNo = :menuSNo where mnuModule = :mnuModule and mnuText = :mnuText`
- SQL 2: `Update user_Favorite_Web set moduleSNo = :moduleSNo where mnuModule = :mnuModule`
- Params: `:mnuModule` ← `DmShared.xNull(mrTemp.getFavModuleName())`; `:mnuText` ← `DmShared.xNull(menu_Mdl.getSharedKeyFieldName())`; `:menuSNo` ← `DmServer.vNull(i + 1, "0")`; `:mnuModule` ← `DmShared.xNull(menu_Mdl.getSharedKeyFieldName())`; `:moduleSNo` ← `DmServer.vNull(i + 1, "0")`

### `getModuleList`
`public DmResult<List<GrdModuleMenu_Mdl>> getModuleList(String moduleType)`

- SQL 1: `select Max(mnuModule) as module from user_Permission_Web where userName = ' {…} ' group by mnuModule order by mnuModule`
- SQL 2: `select Max(mnuModule) as module from user_Favorite_Web where userName = ' {…} ' group by mnuModule order by Max(moduleSNo) asc`

### `getMenuList`
`public DmResult<List<GrdModuleMenu_Mdl>> getMenuList(String menuType, String moduleName)`

- SQL 1: `select upw.mnuName ,upw.mnuText,mc.mnuShortHand from user_Permission_Web upw inner join ( select upw.mnuModule, upw.mnuName ,upw.mnuText from user_Permission_Web upw left join user_Permission_Web upw1 on upw1.userName = 'SA' and upw.mnuModule = upw1.mnuModule and upw.mnuName = upw1.parent where upw.userName = 'SA' and upw.mnuModule = :mnuModule and upw1.mnuName is Null ) as u on u.mnuModule = upw.mnuModule and u.mnuName = upw.mnuName left join menuCodification as mc ON mc.mnuModule=upw.mnuModule AND mc.mnuName=upw.mnuName where upw.userName = :userName and upw.comp_Code = :compCode and upw.mnuModule = :mnuModule order by upw.mnuModule,upw.mnuText asc`
- Params: `:mnuModule` ← `moduleName`; `:userName` ← `userName`; `:compCode` ← `compCode`
- Errors: "Please select module to open menu !"

### `getFillRecords`
`public DmResult<List<ModuleDetail_Mdl>> getFillRecords()`

- SQL 1: `SELECT Max(mnuModule) as mnuModule FROM user_Favorite_Web where userName= ' {…} ' group by mnuModule order by Max(moduleSNo) asc, Max(mnuModule) asc`

### `getMenuModuleWise`
`public List<MenuDetail_Mdl> getMenuModuleWise(String moduleName, Connection cnTemp) throws Exception`

- SQL 1: `SELECT mnuText FROM user_Favorite_Web where mnuModule=:mnuModule and userName=:userName order by moduleSNo, mnuModule, menuSNo, mnuText`
- Params: `:mnuModule` ← `DmShared.xNull(moduleName)`; `:userName` ← `DmShared.xNull(DmServer.getSsnUserName(this))`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.libraryMenu.self.shared`, `paperbiz.dashBoard.favorite.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| insertRecord | (unconditional) | "Please select menu to add !" |
| deleteRecord | (unconditional) | "Please select menu to remove !" |
| getMenuList | (unconditional) | "Please select module to open menu !" |

## Notes (hand-written)
**Favorites (user ka apna shortcut menu).** Table `user_Favorite_Web(userName, mnuModule, mnuName, mnuText, menuSNo, moduleSNo)`. Screens: Favorite (list), Manage (menu list `getMenuList` se jodna, `getModuleList`), SetPosition (order: `menuSNo` aur `moduleSNo` update). Server: `insertRecord`, `deleteRecord`, `setPosition`, `getFillRecords`. Menu list `user_Permission_Web` se (sirf user ke allowed items). Frontend: favorites panel + drag-to-reorder.
