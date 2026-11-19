# dm-utility / reports

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/utility/src/reports/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Company Name (Text)
- Lookups/suggestions: getTxtHCompany, getTxtHUser, getTxtHModuleName, getTxtHEntryPoint, getTxtHUserRole
- CSV export: no

## Files
| file | lines |
|---|---|
| client/RptUserPermissionRegister.java | 338 |
| server/RptUserPermissionRegister_SrvImpl.java | 491 |
| shared/RptUserPermissionRegister_Srv.java | 30 |
| shared/RptUserPermissionRegister_SrvAsync.java | 30 |
| shared/RptUserPermissionRegister_Vld.java | 13 |
| shared/TxtHCompany_Mdl.java | 19 |
| shared/TxtHUsers_Mdl.java | 32 |

## Models (shared DTOs)
### TxtHUsers_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | description |
| String | userRole |

### TxtHCompany_Mdl (extends `KeyField implements Serializable, IsSerializable`)
## RPC interface
| method | params | returns |
|---|---|---|
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getTxtHCompany | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHUser | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHUsers_Mdl>> |
| getTxtHModuleName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHEntryPoint | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHUserRole | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `RptUserPermissionRegister_Vld`
```java
public class RptUserPermissionRegister_Vld {
    public static void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter) {
        DmShared.isValidText(hashMapFilter.get("company").getTag(), "Company Name", 100);
    }
}
```

## Server `RptUserPermissionRegister_SrvImpl.java` (491 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnCompanyName`, `DmServer.getSsnDBName`, `DmServer.getSsnUserName`, `DmServer.showJasper`, `DmShared.xNull`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`


### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `select upw.comp_Code , upw.userName , um.user_Role , upw.mnuModule , upw.mnuText , upw.permission , cp.controlPermission , upw.vp_Days , upw.sp_days , upw.dp_Days , upw.allowValueUpto , sp.sitePermission , upp.mnuText as parent from user_Permission_Web upw LEFT JOIN user_Permission_Web upp ON upp.comp_Code = upw.comp_Code AND upp.userName = upw.userName AND upp.mnuModule = upw.mnuModule AND upp.mnuName = upw.parent CROSS APPLY ( SELECT STUFF((SELECT char(10) + ISNULL(ucpw.groupText,'') AS [text()] FROM user_Control_Permission_Web ucpw WHERE ucpw.userName = upw.userName and ucpw.mnuModule = upw.mnuModule and ucpw.mnuName = upw.mnuName and ucpw.comp_Code = upw.comp_Code FOR XML PATH(''), TYPE).value('.[1]','nvarchar(max)'), 1,1,'') ) cp(controlPermission) LEFT JOIN userSite us ON us.user_Name = upw.userName AND us.compCode = upw.comp_Code LEFT JOIN userMast um ON um.user_Name = upw.userName CROSS APPLY ( SELECT STUFF((SELECT char(10) + ISNULL(sm.manualCode,'') AS [text()] FROM siteMast sm WHERE us.sitelist LIKE ('%|' + CAST(sm.code AS VARCHAR) + '|%') FOR XML PATH(''), TYPE).value('.[1]','nvarchar(max)'), 1,1,'') ) sp(sitePermission) where upw.isActive = '1' AND upw.userName NOT IN ('SA', 'SUPER') {…} order by ISNULL(um.user_Role,um.user_Name), upw.userName, upw.comp_Code, upw.sno`
- SQL 2: `Select vt.v_type, vt.description, uvp.category, uvp.vTypeSName, uvp.vp_Days, uvp.sp_days, uvp.dp_Days, uvp.allowValueUpto, uvp.isAllowAudit, uvp.comp_Code, uvp.user_Name FROM user_Voucher_Permission as uvp LEFT JOIN {…} .dbo.voucher_Type as vt on uvp.vTypeSName = vt.short_name WHERE uvp.user_Name = $P{user_Name} and uvp.comp_Code = $P{comp_Code} order by uvp.category, vt.short_Name`
- Other calls: `RptUserPermissionRegister_Vld.validateInput`

### `getParameter`
`private String getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:comp_code` ← `DmShared.xNull(filter.get("company").getTag())`

### `getTxtHCompany`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCompany(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT cmp.Comp_Code, CONCAT(plt.Comp_Name,' ','(',YEAR(cmp.Start_Dt),'-',YEAR(cmp.End_Dt),')') as Comp_Name FROM Company as cmp LEFT JOIN productLicensedTo as plt on cmp.pltCode = plt.pltCode`

### `getTxtHUser`
`public DmResultPageing<List<TxtHUsers_Mdl>> getTxtHUser(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select userName_Linked from userMast where user_Name = :userName`
- SQL 2: `Select code, user_Name as name, description, user_Role from userMast where 1 = 1 {…}`
- Params: `:userName` ← `DmServer.getSsnUserName(this)`

### `getTxtHModuleName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHModuleName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select DISTINCT upw.mnuModule as code, upw.mnuModule as name from user_Permission_Web upw`

### `getTxtHEntryPoint`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHEntryPoint(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select DISTINCT upw.mnuName as code, upw.mnuText as name, upw.mnuModule, upp.mnuText as parent from user_Permission_Web upw LEFT JOIN user_Permission_Web upp ON upp.comp_Code = upw.comp_Code AND upp.userName = upw.userName AND upp.mnuModule = upw.mnuModule AND upp.mnuName = upw.parent`

### `getTxtHUserRole`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHUserRole(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select um.user_Name as name from userMast um where ISNULL(um.user_Role,'') = '' AND um.user_Name NOT IN ('SA', 'SUPER')`

## Depends on (imports from other packages)
`dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmReportLayout.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.libraryMenu.self.shared`, `dataman.utility.reports.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
