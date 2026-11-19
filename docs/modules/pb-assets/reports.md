# pb-assets / reports

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/assets/src/reports/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: rule: From Date is mandatory.; rule: To Date is mandatory.; rule: To Date should greater than From Date ; rule: To Date should greater than or equal to From Date 
- Lookups/suggestions: getTxtHAssetGroup, getTxtHDepartment, getTxtHAssetItem, getTxtHAssetName, getTxtHProperties, getTxtHAssetNature, getTxtHTypeOfExpense, getTxtHDateApplicable, getTxtHIncludeOpening
- CSV export: no

## Files
| file | lines |
|---|---|
| client/AssetLedger.java | 423 |
| client/AssetMovmentRegister.java | 267 |
| client/AssetsRegister.java | 315 |
| client/DepriciationReport.java | 299 |
| client/RptAssetPropetyDueReport.java | 302 |
| client/RptAssetsRegisterExcel.java | 339 |
| client/RptMachineDownRegister.java | 269 |
| server/AssetLedger_SrvImpl.java | 528 |
| server/AssetMovmentRegister_SrvImpl.java | 336 |
| server/AssetsRegister_SrvImpl.java | 420 |
| server/DepriciationReport_SrvImpl.java | 360 |
| server/RptAssetPropetyDueReport_SrvImpl.java | 400 |
| server/RptAssetsRegisterExcel_SrvImpl.java | 766 |
| server/RptMachineDownRegister_SrvImpl.java | 319 |
| shared/AssetLedger_Srv.java | 28 |
| shared/AssetLedger_SrvAsync.java | 28 |
| shared/AssetLedger_Vld.java | 26 |
| shared/AssetMovmentRegister_Srv.java | 27 |
| shared/AssetMovmentRegister_SrvAsync.java | 27 |
| shared/AssetMovmentRegister_Vld.java | 26 |
| shared/AssetsRegister_Srv.java | 22 |
| shared/AssetsRegister_SrvAsync.java | 22 |
| shared/AssetsRegister_Vld.java | 27 |
| shared/DepriciationReport_Srv.java | 27 |
| shared/DepriciationReport_SrvAsync.java | 27 |
| shared/DepriciationReport_Vld.java | 26 |
| shared/RptAssetPropetyDueReport_Srv.java | 22 |
| shared/RptAssetPropetyDueReport_SrvAsync.java | 22 |
| shared/RptAssetPropetyDueReport_Vld.java | 31 |
| shared/RptAssetsRegisterExcel_Srv.java | 26 |
| shared/RptAssetsRegisterExcel_SrvAsync.java | 28 |
| shared/RptAssetsRegisterExcel_Vld.java | 27 |
| shared/RptMachineDownRegister_Srv.java | 25 |
| shared/RptMachineDownRegister_SrvAsync.java | 25 |
| shared/RptMachineDownRegister_Vld.java | 31 |

## RPC interface
| method | params | returns |
|---|---|---|
| execute | String session, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, String widgetName | DmResult<KeyFieldExtended> |
| getTxtHAssetGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDepartment | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAssetItem | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDateApplicable | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHProperties | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, String widgetName | DmResult<String> |
| getTxtHDepartment | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAssetGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAssetItem | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAssetName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAssetNature | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTypeOfExpense | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHIncludeOpening | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHProperties | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, String widgetName | DmResult<String> |
| getTxtHAssetGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAssetItem | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAssetName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getStatus | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, String widgetName | DmResult<String> |
| getTxtHDepartment | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAssetGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAssetItem | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAssetName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHProperties | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, String widgetName | DmResult<String> |
| getTxtHDepartment | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAssetGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAssetItem | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAssetName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, String widgetName | DmResult<String> |
| getTxtHDepartment | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAssetGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAssetItem | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAssetNature | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTypeOfExpense | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, String widgetName | DmResult<String> |
| getTxtHAssetGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDepartment | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAssetItem | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDateApplicable | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHProperties | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `AssetMovmentRegister_Vld`
```java
public class AssetMovmentRegister_Vld {
    public static void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter) {
        if (DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(100, "From Date is mandatory.");
        }
        if (DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(100, "To Date is mandatory.");
        }
    }
}
```

## Validator `AssetLedger_Vld`
```java
public class AssetLedger_Vld {
    public static void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter) {
        if (DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(100, "From Date is mandatory.");
        }
        if (DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(100, "To Date is mandatory.");
        }
    }
}
```

## Validator `AssetsRegister_Vld`
```java
public class AssetsRegister_Vld {
    public static void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter) {
        if (!DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("") && DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(100, "To Date is mandatory.");
        }
        if (!DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase("") && DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(100, "From Date is mandatory.");
        }
    }
}
```

## Validator `RptMachineDownRegister_Vld`
```java
public class RptMachineDownRegister_Vld {
    public static void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter) {
        if (DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(100, "From Date is mandatory.");
        }
        if (DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(100, "To Date is mandatory.");
        }
         if (DmServer.convertDate(DmShared.dNull(hashMapFilter.get("toDate").getText())).getTime() < DmServer.convertDate(DmShared.dNull(hashMapFilter.get("fromDate").getText())).getTime()) {
            throw new DmException(300, "To Date should greater than From Date ");
        }
    }
}
```

## Validator `DepriciationReport_Vld`
```java
public class DepriciationReport_Vld {
    public static void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter) {
        if (DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(100, "From Date is mandatory.");
        }
        if (DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(100, "To Date is mandatory.");
        }
    }
}
```

## Validator `RptAssetPropetyDueReport_Vld`
```java
public class RptAssetPropetyDueReport_Vld {
    public static void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter) {
        if (DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(100, "From Date is mandatory.");
        }
        if (DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(100, "To Date is mandatory.");
        }
        if (DmServer.convertDate(DmShared.dNull(hashMapFilter.get("toDate").getText())).getTime() < DmServer.convertDate(DmShared.dNull(hashMapFilter.get("fromDate").getText())).getTime()) {
            throw new DmException(300, "To Date should greater than or equal to From Date ");
        }
    }
}
```

## Validator `RptAssetsRegisterExcel_Vld`
```java
public class RptAssetsRegisterExcel_Vld {
      public static void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter) {
        if (!DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("") && DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(100, "To Date is mandatory.");
        }
        if (!DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase("") && DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(100, "From Date is mandatory.");
        }
    }
}
```

## Server `RptAssetPropetyDueReport_SrvImpl.java` (400 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.showJasper`, `DmShared.xNull`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, String widgetName)`

- SQL 1: `select apd.dueDate as nextDueDate, apd.lastServiceNo, apd.lastServiceDate, am.manualCode as assetName, dep.name as currentDepartment, al.amtDr as purchaseAmount,apd.lastServiceAmount, aim.name as assetItem, apm.name as propertyName from assetPropertiesDetail apd left join assetMast am on am.code = apd.assetCode left join assetPropertyMast apm on apm.code = apd.propertyCode left join subGroup dep on dep.subCode = am.currentDepartmentCode left join assetItemMast aim on aim.code = am.assetItemCode left join assetLedger al on al.docId = am.code where apd.dueDate is not null {…} order by aim.name, apm.name, apd.dueDate`
- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText())`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText())`
- Other calls: `RptAssetPropetyDueReport_Vld.validateInput`

### `getTxtHAssetGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAssetGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code, name from assetGroupMast`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select dm.subCode as code, sg.name from departmentMast dm left join subGroup sg on sg.subCode = dm.subCode left join subGroupSiteList sgsl on sgsl.subCode = dm.subCode where sg.isActive = 1 and sgsl.site_Code = :siteCode`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHAssetItem`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAssetItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select aim.code, aim.name from assetItemMast aim left join assetSiteList asl on asl.code = aim.code where asl.site_Code = :siteCode`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHAssetName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAssetName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code, manualCode as name from assetMast where currentSite_code = :siteCode`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHProperties`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHProperties(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code , name from assetPropertyMast`

## Server `DepriciationReport_SrvImpl.java` (360 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.showJasper`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.xNull`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, String widgetName)`

- SQL 1: `select t.assetCode, t.assetName, t.assetItem, t.amtOP, t.amtAddUpto180, t.amtAddGreater180, t.amtDelOther, t.amtDelSales, t.amtDelDepriciation from ( select aLG.assetCode, max(asm.manualCode) as assetName, max(aim.name) as assetItem , sum(case when aLG.v_date < :fromDate then isNull(aLG.amtDr,0) - isNull(aLG.amtCr,0) else 0 end) amtOP , sum(case when aLG.v_date >= :fromDate and isNull(aLG.amtDr,0) > 0 and dateDiff(d, :fromDate , aLG.v_Date) <= 180 then isNull(aLG.amtDr,0) else 0 end) amtAddUpto180, sum(case when aLG.v_date >= :fromDate and isNull(aLG.amtDr,0) > 0 and dateDiff(d, :fromDate , aLG.v_Date) > 180 then isNull(aLG.amtDr,0) else 0 end) amtAddGreater180, sum(case when aLG.v_date >= :fromDate and isNull(aLG.amtCr,0) > 0 {…} then isNull(aLG.amtDr,0) else 0 end) amtDelOther, sum(case when aLG.v_date >= :fromDate and isNull(aLG.amtCr,0) > 0 {…} then isNull(aLG.amtDr,0) else 0 end) amtDelSales, sum(case when aLG.v_date >= :fromDate and isNull(aLG.amtCr,0) > 0 {…} then isNull(aLG.amtDr,0) else 0 end) amtDelDepriciation from assetLedger aLG left join assetMast asm on asm.code = aLG.assetCode left join assetItemMast aim on aim.code = asm.assetItemCode where aLG.v_date <= :toDate and asm.currentSite_code = :siteCode {…} group by aLG.assetCode ) as t order by t.assetItem, t.assetName`
- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText())`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText())`; `:siteCode` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `AssetsRegister_Vld.validateInput`

### `getTxtHAssetGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAssetGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code, name from assetGroupMast`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select dm.subCode as code, sg.name from departmentMast dm left join subGroup sg on sg.subCode = dm.subCode left join subGroupSiteList sgsl on sgsl.subCode = dm.subCode where sg.isActive = 1 and sgsl.site_Code = :siteCode`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHAssetItem`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAssetItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select aim.code, aim.name from assetItemMast aim left join assetSiteList asl on asl.code= aim.Code where asl.site_Code = :siteCode`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHAssetNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAssetNature(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `AssetNature.values`

### `getTxtHTypeOfExpense`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTypeOfExpense(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `ExpenceType.values`

## Server `AssetsRegister_SrvImpl.java` (420 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.showJasper`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.xNull`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, String widgetName)`

- SQL 1: `select asm.code as assetCode, asm.manualCode as assetName, aim.name as assetItemName, apm.name as propertyName, asm.currentDepartmentCode as departmentCode, asm.currentSite_code as currentSite_code, apd.associatedValue, isNull(sdp.name,'') as departmentName, isNull(sm.manualCode,'') as currentSite_MCode, ast.v_date, ast.recId, asm.assetMake, asm.assetAccessories, asm.assetSpecification, asm.assetRemark, asm.dateOfPurchase, asm.dateOfInstallation, sup.name as supplierName , aLG.depriciationAmount , aLG.purchaseAmount, aLG.revenueAmount, aLG.capitalAmount, apd.dueDate, apd.lastServiceAmount, apd.lastServiceNo, apd.lastServiceDate, apd.remark as detailRemark from assetMast asm left join assetStock ast on asm.code = ast.docId left join siteMast sm on sm.code = asm.currentSite_code left join assetPropertiesDetail apd on apd.assetCode = asm.code left join assetPropertyMast apm on apd.propertyCode = apm.code left join ( select aLG.assetCode, sum(case when alg.isNature = {…} then isNull(aLG.amtDr,0) - isNull(aLG.amtCr,0) else 0 end) as depriciationAmount, sum(case when alg.docId = alg.assetCode then isNull(aLG.amtDr,0) - isNull(aLG.amtCr,0) else 0 end) as purchaseAmount, sum(case when alg.typeOfExpense = {…} then isNull(aLG.amtDr,0) - isNull(aLG.amtCr,0) else 0 end) as revenueAmount, sum(case when alg.typeOfExpense = {…} then isNull(aLG.amtDr,0) - isNull(aLG.amtCr,0) else 0 end) as capitalAmount from assetLedger aLG group by aLG.assetCode ) as aLG on aLG.assetCode = asm.code left join assetItemMast aim on aim.code = asm.assetItemCode left join subgroup sdp on sdp.subcode = asm.currentDepartmentCode left join subGroup as sup ON asm.assetSupplierCode = sup.subCode left join assetGroupMast as agm on aim.assetGroupCode = agm.code where asm.currentSite_code = :siteCode {…} order by {…} aim.name, asm.manualCode`
- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText())`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText())`; `:siteCode` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `AssetsRegister_Vld.validateInput`

### `getTxtHAssetGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAssetGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code, name from assetGroupMast`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select dm.subCode as code, sg.name from departmentMast dm left join subGroup sg on sg.subCode = dm.subCode left join subGroupSiteList sgsl on sgsl.subCode = dm.subCode where sg.isActive = 1 and sgsl.site_Code = :siteCode`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHAssetItem`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAssetItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select aim.code, aim.name from assetItemMast aim left join assetSiteList asl on asl.code= aim.Code where asl.site_Code = :siteCode`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHDateApplicable`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDateApplicable(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHProperties`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHProperties(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code , name from assetPropertyMast`

## Server `RptMachineDownRegister_SrvImpl.java` (319 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.showJasper`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.xNull`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, String widgetName)`

- SQL 1: `SELECT 0 as maintainingAmount, am.manualCode, mmd.recId, mmd.v_Date, mmd.machineDownDate, format(mmd.machineDownTime, '00.00') as machineDownTime, mmd.downReason, mmd.machineUpDate, format(mmd.machineUpTime, '00.00') as machineUpTime, mmd.mechanicName, mmd.mechanicRemark, mmd.assembleParts, mmd.site_Code, mmd.preparedBy,aim.code as assetItemCode,aim.name as assetItemName FROM machineMaintainanceDetail mmd left join assetMast am on am.code=mmd.assetCode left join assetItemMast aim on aim.code=am.assetItemCode left join assetGroupMast agm on agm.code=aim.assetGroupCode left join voucher_Type vt on vt.v_Type = mmd.v_Type where vt.category in ( {…} ) {…} order by mmd.v_Date,vt.short_Name, mmd.v_No, mmd.recId`
- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText())`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText())`; `:siteCode` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `RptMachineDownRegister_Vld.validateInput`

### `getTxtHAssetGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAssetGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code, name from assetGroupMast`

### `getTxtHAssetItem`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAssetItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select aim.code, aim.name from assetItemMast aim left join assetSiteList asl on asl.code = aim.code where asl.site_Code = :siteCode`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHAssetName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAssetName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code, manualCode as name from assetMast where currentSite_code = :siteCode`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`

### `getStatus`
`public DmResultPageing<List<KeyFieldExtended>> getStatus(DmHelper helper, boolean blnToEndOfFile)`


## Server `AssetLedger_SrvImpl.java` (528 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.showJasper`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.xNull`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, String widgetName)`

- SQL 1: `select t.docId, t.assetCode, t.assetName, t.recId, t.v_date, t.refNo, t.refDate, case when t.isNature = {…} then ' {…} ' when t.isNature = {…} then ' {…} ' when t.isNature = {…} then ' {…} ' when t.isNature = {…} then ' {…} ' when t.isNature = {…} then ' {…} ' when t.isNature = {…} then ' {…} ' when t.isNature = {…} then ' {…} ' when t.isNature = {…} then ' {…} ' when t.isNature = {…} then ' {…} ' else '' end as isNature , case when t.typeOfExpense = {…} then ' {…} ' when t.typeOfExpense = {…} then ' {…} ' else '' end as typeOfExpense ,t.remark, isNull(t.amtDr,0) as amtDr, isNull(t.amtCr,0) as amtCr, t.assetItem, t.sNo, t.currentDepartmentCode as departmentCode, t.currentSite_code, t.propertyName, t.associatedValue, t.dueDate, t.lastServiceAmount, t.lastServiceNo, t.lastServiceDate, t.detailRemark from (`
- SQL 2: `select aLG.docId, aLG.assetCode, asm.manualCode as assetName, aLG.recId, aLG.v_date, aLG.refNo, aLG.refDate, aLG.isNature, aLG.typeOfExpense, aLG.remark, aLG.amtDr, aLG.amtCr, aim.name as assetItem, 1 as sNo, asm.currentDepartmentCode, asm.currentSite_code, apm.name as propertyName, apd.associatedValue, apd.dueDate, apd.lastServiceAmount, apd.lastServiceNo, apd.lastServiceDate, apd.remark as detailRemark from assetLedger aLG left join assetMast asm on asm.code = aLG.assetCode left join assetPropertiesDetail apd on apd.assetCode = asm.code left join assetPropertyMast apm on apd.propertyCode = apm.code left join assetItemMast aim on aim.code = asm.assetItemCode where aLG.v_date between :fromDate and :toDate and asm.currentSite_code = :siteCode {…} ) as t order by t.assetItem, t.assetName, t.sNo, t.v_Date, t.recId`
- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText())`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText())`; `:siteCode` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `ADDITION.getId`, `ADDITION.getValue`, `APPRICIATION.getId`, `APPRICIATION.getValue`, `AssetsRegister_Vld.validateInput`, `CAPITAL.getId`, `CAPITAL.getValue`, `DELETION.getId`, `DELETION.getValue`, `DEPRICIATION.getId`, `DEPRICIATION.getValue`, `IMPAIRMENT.getId`, `IMPAIRMENT.getValue`, `PURCHASE.getId`, `PURCHASE.getValue`, `REPAIR.getId`, `REPAIR.getValue`, `REVENUE.getId`, `REVENUE.getValue`, `SALES.getId`, `SALES.getValue`, `SCRAP.getId`, `SCRAP.getValue`

### `getTxtHAssetGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAssetGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code, name from assetGroupMast`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select dm.subCode as code, sg.name from departmentMast dm left join subGroup sg on sg.subCode = dm.subCode left join subGroupSiteList sgsl on sgsl.subCode = dm.subCode where sg.isActive = 1 and sgsl.site_Code = :siteCode`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHAssetItem`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAssetItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select aim.code, aim.name from assetItemMast aim left join assetSiteList asl on asl.code= aim.Code where asl.site_Code = :siteCode`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHAssetName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAssetName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code, manualCode as name from assetMast where currentSite_code = :siteCode`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHAssetNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAssetNature(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `AssetNature.values`

### `getTxtHTypeOfExpense`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTypeOfExpense(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `ExpenceType.values`

### `getTxtHIncludeOpening`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHIncludeOpening(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHProperties`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHProperties(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code , name from assetPropertyMast`

## Server `RptAssetsRegisterExcel_SrvImpl.java` (766 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.xNull`

### `execute`
`public DmResult<KeyFieldExtended> execute(String session, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, String widgetName)`

- SQL 1: `select apd.propertyCode, apm.name from assetPropertiesDetail apd left join assetPropertyMast apm on apm.code = apd.propertyCode where 1=1 {…} group by apm.name , apd.propertyCode`
- SQL 2: `Select asm.code as TmpAssetCode {…}`
- SQL 3: `select isNull(sdp.name,'') as [DEPARTMENT NAME],asm.manualCode as [EQUIPMENT ID], aim.name as [NAME OF EQUIPMENT],asm.assetMake as MAKE , asm.dateOfPurchase as [DATE OF PURCHASE],asm.dateOfInstallation as [DATE OF INSTALLATION], sup.name as SUPPLIER ,sdc.name as [SERVICE PROVIDER],property.* from assetMast asm left join assetStock ast on asm.code = ast.docId left join siteMast sm on sm.code = asm.currentSite_code left join ( select aLG.assetCode, sum(case when alg.isNature = {…} then isNull(aLG.amtDr,0) - isNull(aLG.amtCr,0) else 0 end) as depriciationAmount, sum(case when alg.docId = alg.assetCode then isNull(aLG.amtDr,0) - isNull(aLG.amtCr,0) else 0 end) as purchaseAmount, sum(case when alg.typeOfExpense = {…} then isNull(aLG.amtDr,0) - isNull(aLG.amtCr,0) else 0 end) as revenueAmount, sum(case when alg.typeOfExpense = {…} then isNull(aLG.amtDr,0) - isNull(aLG.amtCr,0) else 0 end) as capitalAmount from assetLedger aLG group by aLG.assetCode ) as aLG on aLG.assetCode = asm.code left join assetItemMast aim on aim.code = asm.assetItemCode left join subgroup sdp on sdp.subcode = asm.currentDepartmentCode left join subGroup as sup ON asm.assetSupplierCode = sup.subCode left join subGroup as sdc ON asm.serviceproviderCode = sdc.subCode left join assetGroupMast as agm on aim.assetGroupCode = agm.code left join ( {…} ) as property on asm.code=property.TmpAssetCode where asm.currentSite_code = :siteCode {…} And property.TmpAssetCode is not null order by {…} aim.name, asm.manualCode`
- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText())`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText())`; `:siteCode` ← `DmServer.getSsnSiteCode(this)`
- Errors: "Invalid request." | "File Name not given."
- Other calls: `RptAssetsRegisterExcel_Vld.validateInput`

### `getTxtHAssetGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAssetGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code, name from assetGroupMast`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select dm.subCode as code, sg.name from departmentMast dm left join subGroup sg on sg.subCode = dm.subCode left join subGroupSiteList sgsl on sgsl.subCode = dm.subCode where sg.isActive = 1 and sgsl.site_Code = :siteCode`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHAssetItem`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAssetItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select aim.code, aim.name from assetItemMast aim left join assetSiteList asl on asl.code= aim.Code where asl.site_Code = :siteCode`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHDateApplicable`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDateApplicable(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHProperties`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHProperties(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code , name from assetPropertyMast`

## Server `AssetMovmentRegister_SrvImpl.java` (336 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.showJasper`, `DmShared.xNull`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, String widgetName)`

- SQL 1: `select ast.docId, ast.recId, ast.v_Date, aim.name as assetItemName, asm.manualCode as assetName, sdm.name as departmentName, case when isNull(ast.recQty,0) > 0 then 'Receive' else 'Issue' end transactionType, ast.remark, ast.u_Name, ast.u_entDt from assetStock ast left join assetMast asm on ast.assetCode = asm.code left join assetItemMast aim on asm.assetItemCode = aim.code left join subgroup sdm on sdm.subCode = ast.departmentCode {…} order by aim.name, asm.manualCode, ast.u_entDt, ast.v_date, ast.docId, ast.recQty`
- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText())`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText())`; `:siteCode` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `AssetsRegister_Vld.validateInput`

### `getTxtHAssetGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAssetGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code, name from assetGroupMast`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select dm.subCode as code, sg.name from departmentMast dm left join subGroup sg on sg.subCode = dm.subCode left join subGroupSiteList sgsl on sgsl.subCode = dm.subCode where sg.isActive = 1 and sgsl.site_Code = :siteCode`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHAssetItem`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAssetItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select aim.code, aim.name from assetItemMast aim left join assetSiteList asl on asl.code= aim.Code where asl.site_Code = :siteCode`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHAssetName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAssetName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code, manualCode as name from assetMast where currentSite_code = :siteCode`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.client`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmReportLayout.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `paperbiz.assets.reports.shared`, `paperbiz.assets.self.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stStatic.server`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| execute | `if (!DmSession.getSessions().containsKey(sessionId))` | "Invalid request." |
| execute | `if (DmShared.xNull(fileName).equalsIgnoreCase(""))` | "File Name not given." |

## Notes (hand-written)
_No hand-written notes yet._
