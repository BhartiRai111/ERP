# dm-utility / menuCodification

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/utility/src/menuCodification/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): menuCodification
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Entry (Text)
- Lookups/suggestions: getTxtHMenuName
- CSV export: yes

## Screen spec (auto-extracted from client)
### Screen `MenuCodificationGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Menu | DmTextHelp | `txtHMenuName` | `TxtHMenuName_PrpAcs, 7, true` |
| 2 | Menu Code | DmTextBox | `txtMenuCode` | `6, true` |

Other widgets (not in tab list): `txtMenuText` (DmTextBox), `txtModuleName` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `txtHMenuName`: Menu Text [Text, w120, mnuName]; Menu Name [Text, w150, mnuText]; Module Name [Text, w100, mnuModule]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHMenuName` → `getTxtHMenuName`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldName` ← DmShared.xNull(txtHMenuName.getText())
- `MenuName` ← DmShared.xNull(txtHMenuName.getTag())
- `ModuleName` ← DmShared.xNull(txtModuleName.getText())
- `MenuCode` ← DmShared.xNull(txtMenuCode.getText())

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHMenuName.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHMenuName(DmShared.xNull(txtModuleName.getText()), helper, blnToEndOfFile, new
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHMenuName).show();
public void onSuccess(DmResultPageing<List<TxtHMenuName_Mdl>> result) {
if (result.isStatus()) {
txtHMenuName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHMenuName).show();
```

`txtHMenuName.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHMenuName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHMenuName).show();
```

`txtHMenuName.addTextHelpCallBack` → `getSelectedRow()`
```java
txtHMenuName.setText(DmShared.xNull(tRow.getSharedKeyFieldName()));
txtHMenuName.setTag(DmShared.xNull(tRow.getMenuName()));
txtModuleName.setText(DmShared.xNull(tRow.getModuleName()));
```
- Alert: "Menu Name is mandatory."
- Alert: "Menu Code is mandatory."
- Alert: "Menu Code already exists."
- Alert: "Menu Name already exists."

### Screen `MenuCodification`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `grdAllModule` (DmGrid), `grdAllMenu` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `grdAllModule`: Module [Text, w1, module]
- `grdAllMenu`: ... [Text, w180]; Menu Code [Text, w80]

**Lookup / suggestion / find sources (server RPC called):**
- `gridEdit` → `getMenuList`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`gridEdit.addCallBack` → `onOk()`
```java
grdAllMenu.commitChanges();
```

`gridEdit.addCallBack` → `onDelete()`
```java
grdAllMenu.commitChanges();
```
- Confirm: "Are you sure? You want to close."
- Confirm: "Are you sure? You want to export records."

## Files
| file | lines |
|---|---|
| client/MenuCodification.java | 301 |
| client/MenuCodificationGridEdit.java | 257 |
| client/MenuCodificationGridEdit_Dsg.java | 93 |
| client/MenuCodification_Dsg.java | 223 |
| server/MenuCodification_SrvImpl.java | 418 |
| shared/GrdDescription_Mdl.java | 48 |
| shared/GrdModuleMenu_Mdl.java | 32 |
| shared/MenuCodification_Mdl.java | 20 |
| shared/MenuCodification_Srv.java | 24 |
| shared/MenuCodification_SrvAsync.java | 25 |
| shared/MenuCodification_Vld.java | 11 |
| shared/TxtHMenuName_Mdl.java | 32 |

## UI labels / columns (client)
`Menu`, `Menu Code`

## Models (shared DTOs)
### GrdDescription_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | menuName |
| String | menuCode |
| String | moduleName |

### GrdModuleMenu_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | menuName |
| String | menuShortHand |

### TxtHMenuName_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | moduleName |
| String | menuName |

### MenuCodification_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| List<GrdDescription_Mdl> | grdDescrip_Mdl |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHMenuName | String moduleName, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHMenuName_Mdl>> |
| saveRecord | GrdDescription_Mdl mrTemp | DmResult<String> |
| deleteRecord | GrdDescription_Mdl strKey | DmResult<String> |
| getModuleList | String moduleType | DmResult<List<GrdModuleMenu_Mdl>> |
| getMenuList | String sorting, String moduleName | DmResult<List<GrdDescription_Mdl>> |
| exportCSV | String sessionId | DmResult<KeyFieldExtended> |

## Validator `MenuCodification_Vld`
```java
public class MenuCodification_Vld {
    public static void chkSave(GrdDescription_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getSharedKeyFieldName().trim(), "Entry", 100);
    }
}
```

## Server `MenuCodification_SrvImpl.java` (418 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnUserName`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getTxtHMenuName`
`public DmResultPageing<List<TxtHMenuName_Mdl>> getTxtHMenuName(String moduleName, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select upw.mnuName ,upw.mnuText,upw.mnuModule from user_Permission_Web upw inner join ( select upw.mnuModule, upw.mnuName ,upw.mnuText from user_Permission_Web upw left join user_Permission_Web upw1 on upw1.userName = 'SA' and upw.mnuModule = upw1.mnuModule and upw.mnuName = upw1.parent where upw.userName = 'SA' and upw.mnuModule = :mnuModule and upw1.mnuName is Null ) as u on u.mnuModule = upw.mnuModule and u.mnuName = upw.mnuName left join menuCodification as mc ON mc.mnuModule=upw.mnuModule AND mc.mnuName=upw.mnuName where upw.userName = :userName and upw.comp_Code = :compCode and upw.mnuModule = :mnuModule`
- Params: `:mnuModule` ← `moduleName`; `:userName` ← `userName`; `:compCode` ← `compCode`

### `saveRecord`
`public DmResult<String> saveRecord(GrdDescription_Mdl mrTemp)`

- SQL 1: `select count(mnuShortHand) AS cnt from menuCodification as mc where mnuShortHand=:mnuShortHand group by mnuShortHand`
- SQL 2: `Delete From menuCodification Where mnuModule=:mnuModule AND mnuName=:mnuName and mnuText = :mnuText`
- Params: `:mnuShortHand` ← `mrTemp.getMenuCode()`; `:mnuModule` ← `mrTemp.getModuleName()`; `:mnuName` ← `mrTemp.getMenuName()`; `:mnuText` ← `mrTemp.getSharedKeyFieldName()`; `:mnuModule` ← `DmShared.xNull(mrTemp.getModuleName())`; `:mnuName` ← `DmShared.xNull(mrTemp.getMenuName())`; `:mnuText` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:mnuShortHand` ← `DmShared.xNull(mrTemp.getMenuCode()).toUpperCase()`
- Errors: "Menu code already exist."
- Other calls: `MenuCodification_Vld.chkSave`

### `deleteRecord`
`public DmResult<String> deleteRecord(GrdDescription_Mdl mrTemp)`

- SQL 1: `Delete From menuCodification where mnuModule=:mnuModule AND mnuName=:mnuName`
- Params: `:mnuModule` ← `DmShared.xNull(mrTemp.getModuleName())`; `:mnuName` ← `DmShared.xNull(mrTemp.getMenuName())`

### `getModuleList`
`public DmResult<List<GrdModuleMenu_Mdl>> getModuleList(String moduleType)`

- SQL 1: `select Max(mnuModule) as module from user_Permission_Web where userName = ' {…} ' group by mnuModule order by mnuModule`

### `getMenuList`
`public DmResult<List<GrdDescription_Mdl>> getMenuList(String sortingType, String moduleName)`

- SQL 1: `select upw.mnuName ,upw.mnuText,mc.mnuShortHand from user_Permission_Web upw inner join ( select upw.mnuModule, upw.mnuName ,upw.mnuText from user_Permission_Web upw left join user_Permission_Web upw1 on upw1.userName = 'SA' and upw.mnuModule = upw1.mnuModule and upw.mnuName = upw1.parent where upw.userName = 'SA' and upw.mnuModule = :mnuModule and upw1.mnuName is Null ) as u on u.mnuModule = upw.mnuModule and u.mnuName = upw.mnuName left join menuCodification as mc ON mc.mnuModule=upw.mnuModule AND mc.mnuName=upw.mnuName where upw.userName = :userName and upw.comp_Code = :compCode and upw.mnuModule = :mnuModule {…}`
- Params: `:mnuModule` ← `moduleName`; `:userName` ← `userName`; `:compCode` ← `compCode`

### `exportCSV`
`public DmResult<KeyFieldExtended> exportCSV(String sessionId)`

- SQL 1: `select mnuModule, mnuName, mnuText, mnuShortHand from menuCodification order by mnuModule, mnuName, mnuShortHand`
- Errors: "Invalid request." | "File Name not given."
- Other calls: `DmImportData_Server.getCSVFormats`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.client`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmImportData.server`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.libraryMenu.self.shared`, `dataman.utility.menuCodification.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).longValue() > 0)` | "Menu code already exist." |
| exportCSV | `if (!DmSession.getSessions().containsKey(sessionId))` | "Invalid request." |
| exportCSV | `if (DmShared.xNull(fileName).equalsIgnoreCase(""))` | "File Name not given." |

## Notes (hand-written)
_No hand-written notes yet._
