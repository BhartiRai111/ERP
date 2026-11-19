# pb-production / reports

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/production/src/reports/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Site (Text); rule: From Date is mandatory.; rule: To Date is mandatory.; rule: From Date should be less than or equal to To Date.; Sales Order Deatil (Text); Include Wastage (Text); Report For (Text); Group On (Text)
- Lookups/suggestions: getTxtHSite, getTxtHStatus, getTxtHProductionOrderNo, getTxtHWorkOrderNo, getTxtHJCNo, getHProcess, getTxtHDepartment, getTxtHPlanSite, getTxtHOrderNo, getTxtHOrderPlanNo, getTxtHParty, getTxtHVType, getTxtHItem, getTxtHExpenseAc, getTxtHMachine, getTxtHVoucherType, getTxtHMasterWorkOrderNo, getTxtHType, getTxtHReportFor, getTxtHGroupOn, getTxtHProcessReceiveNo, getTxtHItemName, getTxtHProcess, getTxtHReceiveType, getTxtHYesNo, getTxtHWarehouse
- CSV export: no

## Files
| file | lines |
|---|---|
| client/RptJobCloseRegister.java | 437 |
| client/RptOrderPlanRegister.java | 556 |
| client/RptProcessExpenseRegister.java | 461 |
| client/RptProcessIssueReceiveRegister.java | 418 |
| client/RptProcessMachineRegister.java | 421 |
| client/RptProductionOrderRegister.java | 344 |
| client/RptProductionSummaryRegister.java | 597 |
| client/RptWorkOrderRegister.java | 643 |
| server/RptJobCloseRegister_SrvImpl.java | 819 |
| server/RptOrderPlanRegister_SrvImpl.java | 1117 |
| server/RptProcessExpenseRegister_SrvImpl.java | 1139 |
| server/RptProcessIssueReceiveRegister_SrvImpl.java | 960 |
| server/RptProcessMachineRegister_SrvImpl.java | 1174 |
| server/RptProductionOrderRegister_SrvImpl.java | 791 |
| server/RptProductionSummaryRegister_SrvImpl.java | 1151 |
| server/RptWorkOrderRegister_SrvImpl.java | 888 |
| shared/ExcelCellData_Mdl.java | 130 |
| shared/RptJobCloseRegister_Srv.java | 32 |
| shared/RptJobCloseRegister_SrvAsync.java | 32 |
| shared/RptJobCloseRegister_Vld.java | 28 |
| shared/RptOrderPlanRegister_Srv.java | 37 |
| shared/RptOrderPlanRegister_SrvAsync.java | 37 |
| shared/RptOrderPlanRegister_Vld.java | 29 |
| shared/RptProcessExpenseRegister_Srv.java | 34 |
| shared/RptProcessExpenseRegister_SrvAsync.java | 33 |
| shared/RptProcessExpenseRegister_Vld.java | 28 |
| shared/RptProcessIssueReceiveRegister_Srv.java | 32 |
| shared/RptProcessIssueReceiveRegister_SrvAsync.java | 32 |
| shared/RptProcessIssueReceiveRegister_Vld.java | 28 |
| shared/RptProcessMachineRegister_Srv.java | 34 |
| shared/RptProcessMachineRegister_SrvAsync.java | 34 |
| shared/RptProcessMachineRegister_Vld.java | 28 |
| shared/RptProductionOrderRegister_Srv.java | 34 |
| shared/RptProductionOrderRegister_SrvAsync.java | 34 |
| shared/RptProductionOrderRegister_Vld.java | 28 |
| shared/RptProductionSummaryRegister_Srv.java | 37 |
| shared/RptProductionSummaryRegister_SrvAsync.java | 37 |
| shared/RptProductionSummaryRegister_Vld.java | 31 |
| shared/RptWorkOrderRegister_Srv.java | 38 |
| shared/RptWorkOrderRegister_SrvAsync.java | 38 |
| shared/RptWorkOrderRegister_Vld.java | 29 |
| shared/TxtHItem_Mdl.java | 68 |
| shared/TxtHMasterWorkOrder_Mdl.java | 32 |
| shared/TxtHParty_Mdl.java | 31 |
| shared/TxtHProductionOrder_Mdl.java | 32 |
| shared/TxtHSalesOrder_Mdl.java | 59 |
| shared/TxtHVoucher_Mdl.java | 23 |
| shared/TxtHWorkOrder_Mdl.java | 32 |

## Models (shared DTOs)
### TxtHWorkOrder_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | woType |
| String | woDate |

### TxtHVoucher_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | shortName |

### TxtHMasterWorkOrder_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | mwoType |
| String | mwoDate |

### TxtHItem_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | itemGroup |
| String | itemCategory |
| String | unit |
| String | unitCode |
| String | manualCode |
| String | itemType |

### TxtHProductionOrder_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | poType |
| String | poDate |

### ExcelCellData_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | title |
| String | fieldName |
| DmDataType | dataType |
| boolean | isHeaderData |
| int | groupIndex |

### TxtHParty_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityName |

### TxtHSalesOrder_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | soType |
| String | soDate |
| String | partyName |
| String | distributorName |
| String | consigneeName |

## RPC interface
| method | params | returns |
|---|---|---|
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<KeyFieldExtended> |
| getItemName | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getTxtHProductionOrderNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHProductionOrder_Mdl>> |
| getTxtHStatus | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getGroupName | String excludeGroup, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<KeyFieldExtended> |
| getTxtHWorkOrderNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHWorkOrder_Mdl>> |
| getTxtHDepartment | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getItemName | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getHProcess | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<KeyFieldExtended> |
| getTxtHWorkOrderNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHWorkOrder_Mdl>> |
| getTxtHDepartment | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getItemName | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getHProcess | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHMachine | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHExpenseAc | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<KeyFieldExtended> |
| getTxtHWorkOrderNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHWorkOrder_Mdl>> |
| getTxtHDepartment | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getItemName | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getHProcess | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHExpenseAc | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVoucherType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucher_Mdl>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getTxtHWorkOrderNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHWorkOrder_Mdl>> |
| getItemName | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getTxtHStatus | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getGroupName | String excludeGroup, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getHProcess | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getSoDeatil | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHMasterWorkOrderNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHMasterWorkOrder_Mdl>> |

## RPC interface
| method | params | returns |
|---|---|---|
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<KeyFieldExtended> |
| getTxtHWorkOrderNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHWorkOrder_Mdl>> |
| getTxtHJCNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDepartment | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getItemName | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getHProcess | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHReportFor | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGroupOn | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHWorkOrderNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHWorkOrder_Mdl>> |
| getTxtHProcessReceiveNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHWarehouse | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemName | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getTxtHProcess | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHReceiveType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHYesNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<KeyFieldExtended> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPlanSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItem | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getTxtHOrderNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHSalesOrder_Mdl>> |
| getTxtHOrderPlanNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHSalesOrder_Mdl>> |
| getTxtHParty | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getTxtHDepartment | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<KeyFieldExtended> |

## Validator `RptProcessIssueReceiveRegister_Vld`
```java
public class RptProcessIssueReceiveRegister_Vld {
    public static void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter) {
        if (DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(20, "From Date is mandatory.");
        }
        if (DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(40, "To Date is mandatory.");
        }
        if ((!DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) && (!DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase(""))) {
            if (DmShared.convertDate(hashMapFilter.get("fromDate").getText()).getTime() > DmShared.convertDate(hashMapFilter.get("toDate").getText()).getTime()) {
                throw new DmException(100, "From Date should be less than or equal to To Date.");
            }
        }
        DmShared.isValidText(hashMapFilter.get("site").getTag(), "Site", 200);
    }
}
```

## Validator `RptProcessMachineRegister_Vld`
```java
public class RptProcessMachineRegister_Vld {
    public static void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter) {
        if (DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(20, "From Date is mandatory.");
        }
        if (DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(40, "To Date is mandatory.");
        }
        if ((!DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) && (!DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase(""))) {
            if (DmShared.convertDate(hashMapFilter.get("fromDate").getText()).getTime() > DmShared.convertDate(hashMapFilter.get("toDate").getText()).getTime()) {
                throw new DmException(100, "From Date should be less than or equal to To Date.");
            }
        }
        DmShared.isValidText(hashMapFilter.get("site").getTag(), "Site", 200);
    }
}
```

## Validator `RptWorkOrderRegister_Vld`
```java
public class RptWorkOrderRegister_Vld {
    public static void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter) {
        if (DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(20, "From Date is mandatory.");
        }
        if (DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(40, "To Date is mandatory.");
        }
        if ((!DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) && (!DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase(""))) {
            if (DmShared.convertDate(hashMapFilter.get("fromDate").getText()).getTime() > DmShared.convertDate(hashMapFilter.get("toDate").getText()).getTime()) {
                throw new DmException(100, "From Date should be less than or equal to To Date.");
            }
        }
        DmShared.isValidText(hashMapFilter.get("site").getTag(), "Site", 200);
        DmShared.isValidText(hashMapFilter.get("SoDeatil").getTag(), "Sales Order Deatil", 200);
    }
}
```

## Validator `RptJobCloseRegister_Vld`
```java
public class RptJobCloseRegister_Vld {
    public static void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter) {
        if (DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(20, "From Date is mandatory.");
        }
        if (DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(40, "To Date is mandatory.");
        }
        if ((!DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) && (!DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase(""))) {
            if (DmShared.convertDate(hashMapFilter.get("fromDate").getText()).getTime() > DmShared.convertDate(hashMapFilter.get("toDate").getText()).getTime()) {
                throw new DmException(100, "From Date should be less than or equal to To Date.");
            }
        }
        DmShared.isValidText(hashMapFilter.get("site").getTag(), "Site", 200);
    }
}
```

## Validator `RptProductionOrderRegister_Vld`
```java
public class RptProductionOrderRegister_Vld {
    public static void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter) {
        if (DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(20, "From Date is mandatory.");
        }
        if (DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(40, "To Date is mandatory.");
        }
        if ((!DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) && (!DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase(""))) {
            if (DmShared.convertDate(hashMapFilter.get("fromDate").getText()).getTime() > DmShared.convertDate(hashMapFilter.get("toDate").getText()).getTime()) {
                throw new DmException(100, "From Date should be less than or equal to To Date.");
            }
        }
    }
}
```

## Validator `RptOrderPlanRegister_Vld`
```java
public class RptOrderPlanRegister_Vld {
    public static void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter) {
        if (DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(20, "From Date is mandatory.");
        }
        if (DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(40, "To Date is mandatory.");
        }
        if ((!DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) && (!DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase(""))) {
            if (DmShared.convertDate(hashMapFilter.get("fromDate").getText()).getTime() > DmShared.convertDate(hashMapFilter.get("toDate").getText()).getTime()) {
                throw new DmException(100, "From Date should be less than or equal to To Date.");
            }
        }
    }
}
```

## Validator `RptProcessExpenseRegister_Vld`
```java
public class RptProcessExpenseRegister_Vld {
    public static void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter) {
        if (DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(20, "From Date is mandatory.");
        }
        if (DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(40, "To Date is mandatory.");
        }
        if ((!DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) && (!DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase(""))) {
            if (DmShared.convertDate(hashMapFilter.get("fromDate").getText()).getTime() > DmShared.convertDate(hashMapFilter.get("toDate").getText()).getTime()) {
                throw new DmException(100, "From Date should be less than or equal to To Date.");
            }
        }
        DmShared.isValidText(hashMapFilter.get("site").getTag(), "Site", 200);
    }
}
```

## Validator `RptProductionSummaryRegister_Vld`
```java
public class RptProductionSummaryRegister_Vld {
    public static void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter) {
        if (DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(20, "From Date is mandatory.");
        }
        if (DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(40, "To Date is mandatory.");
        }
        if ((!DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) && (!DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase(""))) {
            if (DmShared.convertDate(hashMapFilter.get("fromDate").getText()).getTime() > DmShared.convertDate(hashMapFilter.get("toDate").getText()).getTime()) {
                throw new DmException(100, "From Date should be less than or equal to To Date.");
            }
        }
        DmShared.isValidText(hashMapFilter.get("includeWastage").getTag(), "Include Wastage", 200);
        DmShared.isValidText(hashMapFilter.get("reportFor").getTag(), "Report For", 200);
        DmShared.isValidText(hashMapFilter.get("groupOn").getTag(), "Group On", 200);
    }
}
```

## Server `RptProductionOrderRegister_SrvImpl.java` (791 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnId`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.removeAllCharacterExceptAlphaNumeric`, `DmServer.setReportHeaderExcel`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getItemName`
`public DmResultPageing<List<TxtHItem_Mdl>> getItemName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code as itemCode, im.name as itemName, im.manualCode, it.name as itemType, ums.unit as itemUnitName, umsku.unit as sku FROM itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN itemType it ON it.code=im.filterItemType LEFT JOIN unitMast ums ON ums.code = im.saleUnit LEFT JOIN unitMast umsku ON umsku.code = im.sku WHERE im.IsActive = :isActive AND (im.filterItemType IN (:FM, :SM) OR im.isCommon = 1) AND sl.site_Code = :site_Code {…}`
- Params: `:FM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:SM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHStatus`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHStatus(DmHelper helper, boolean blnToEndOfFile)`


### `getGroupName`
`public DmResultPageing<List<KeyFieldExtended>> getGroupName(String excludeGroup, DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHProductionOrderNo`
`public DmResultPageing<List<TxtHProductionOrder_Mdl>> getTxtHProductionOrderNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT so.docId, so.recId as pOrderNo, so.v_Date as pOrderDate, vt.short_Name ,so.v_No , CASE WHEN so.site_Code = {…} THEN 1 ELSE 2 END as tempSr FROM sOrder AS so left join voucher_Type as vt on vt.v_Type = so.v_Type LEFT JOIN siteMast sm ON sm.code = so.site_Code where vt.Category IN (' {…} ', ' {…} ') AND ISNULL(so.approvedBy, '') <> '' and so.site_Code in( {…} :siteList {…} )`

### `execute`
`public DmResult<KeyFieldExtended> execute(String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`


### `createExcelReport`
`public DmResult<KeyFieldExtended> createExcelReport(String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT so.docId, so.recId as pOrderNo, so.v_Date as pOrderDate, vt.short_Name as orderType, sm.name as site, so.remark, so.preparedBy, so.approvedBy, so.guiConfigCode, sod.docId,sod.v_SNo,sod.itemCode, im.displayName as itemName,sod.itemDesc, um1.decimalPlaces as itemUnitDecimalPlaces, im.itsBasedOn, sod.isStatus, sod.unitCode as itemUnitCode, um1.unit as itemUnitName, sod.quantity, sod.deliveryDt, sod.skuQty, im.itsBasedOn, ums.decimalPlaces as skuUnitDecimalPlaces, sod.ibCode, sod.skuQtyForRate, im.propertyList, it.manualCode as itemType , ib.propertyDispCsv, sod.deliveryDt as deliveryDate FROM sOrder so LEFT JOIN voucher_Type vt ON vt.v_Type = so.v_Type LEFT JOIN siteMast sm ON sm.code = so.site_Code LEFT JOIN sOrderDetail sod ON sod.docId=so.docId LEFT JOIN itemMast im ON im.code = sod.itemCode LEFT JOIN itemType it ON it.code = im.itemType LEFT JOIN unitMast um1 ON um1.code = im.saleUnit LEFT JOIN unitmast ums ON ums.code = im.sku LEFT JOIN hsnMast hsn ON hsn.code = sod.hsnCode LEFT JOIN itemBatch ib ON ib.code=sod.ibCode WHERE vt.category IN (' {…} ', ' {…} ') AND ISNULL(so.approvedBy, '') <> '' {…} ORDER BY so.v_Date ASC, vt.short_Name ASC, so.v_No ASC, sod.v_SNo ASC, so.recId`
- Errors: "Invalid request." | "File Name not given."
- Other calls: `RptProductionOrderRegister_Vld.validateInput`

### `getParameter`
`private String getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`

### `getCellStyle`
`public XSSFCellStyle getCellStyle(DmXlsWriter xlsWriter, XSSFSheet workSheet)`

- Other calls: `BLACK.getIndex`, `Color.decode`

### `printFilterData`
`private int printFilterData(DmXlsWriter xlsWriter, XSSFSheet workSheet, int rowIndex, LinkedHashMap<String, FieldUDT_Mdl> filter, int totalColums)`

- Other calls: `DmBaseShared.xNull`

### `printTotal`
`private int printTotal(DmXlsWriter xlsWriter, XSSFSheet workSheet, int[] columnIndices, int fromRowIndex, int toRowIndex, int printRowIndex) throws Exception`


## Server `RptJobCloseRegister_SrvImpl.java` (819 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnId`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.removeAllCharacterExceptAlphaNumeric`, `DmServer.setReportHeaderExcel`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `execute`
`public DmResult<KeyFieldExtended> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT jc.docId, jc.recId AS jccNo, jc.v_Date AS jccDate, jc.v_Time AS jccTime , wo.recId AS woNo, wo.v_Date AS woDate, sgd.name AS departmentName , jc.oh_Amt_TotalCost AS totalCose, jc.oh_Amt_ReceiveValue AS receiveValue , jc.oh_Amt_RemainingCost AS remainingCost, jc.remark AS headRemark , sgp.name AS processName , CASE WHEN jcd.type = {…} THEN ' {…} ' WHEN ISNULL(jcd.type, 0) = {…} THEN ' {…} ' WHEN ISNULL(jcd.type, 0) = {…} THEN ' {…} ' WHEN ISNULL(jcd.type, 0) = {…} THEN ' {…} ' WHEN ISNULL(jcd.type, 0) = {…} THEN ' {…} ' ELSE '' END AS processType , im.name AS itemName, IB.propertyDispCsv as propertyList , jcd.quantity AS pdQuantity, ums.unit AS skuName--, pd.rate , jcd.oh_Amt_Gross AS grossAmt, jcd.oh_Amt_Adjust AS adjustAmt, jcd.oh_Amt_Net AS netAmt FROM jcClose jc LEFT JOIN jcCloseDetail jcd ON jcd.docId = jc.docId LEFT JOIN voucher_Type vt ON vt.v_Type = jc.v_Type LEFT JOIN wOrder wo ON wo.docId = jc.woDocId LEFT JOIN subGroup sgd ON sgd.subCode = wo.departmentCode LEFT JOIN subGroup sgp ON sgp.subCode = jcd.processCode LEFT JOIN itemMast im ON im.code = jcd.itemCode LEFT JOIN unitMast ums ON im.sku = ums.code LEFT JOIN itemBatch IB ON IB.code = jcd.ibCode WHERE :one = :one {…} ORDER BY jc.v_Date, vt.short_Name, jc.v_No, jc.recId`
- Other calls: `BY_PRODUCT.getCode`, `BY_PRODUCT.getName`, `INPUT.getCode`, `INPUT.getName`, `OUTPUT.getCode`, `OUTPUT.getName`, `RptJobCloseRegister_Vld.validateInput`, `STServer.getSsnDepartmentCodeList`, `SUB_ASSEMBLY.getCode`, `SUB_ASSEMBLY.getName`, `WASTAGE.getCode`, `WASTAGE.getName`

### `getParameter`
`private String getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:one` ← `"1"`; `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getTxtHWorkOrderNo`
`public DmResultPageing<List<TxtHWorkOrder_Mdl>> getTxtHWorkOrderNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT wo.docId, wo.recId as wOrderNo, wo.v_Date as wOrderDate, vt.short_Name ,wo.v_No , CASE WHEN wo.site_Code = {…} THEN 1 ELSE 2 END as tempSr FROM wOrder AS wo left join voucher_Type as vt on vt.v_Type = wo.v_Type LEFT JOIN siteMast sm ON sm.code = wo.site_Code where vt.Category = ' {…} ' AND ISNULL(wo.approvedBy, '') <> '' and wo.site_Code in( {…} :siteList {…} )`

### `getTxtHJCNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHJCNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT jc.docId, jc.recId, jc.v_Date, jc.v_Time ,jc.v_No , CASE WHEN jc.site_Code = {…} THEN 1 ELSE 2 END as tempSr FROM jcClose AS jc left join voucher_Type as vt on vt.v_Type = jc.v_Type LEFT JOIN siteMast sm ON sm.code = jc.site_Code where vt.Category = ' {…} ' and jc.site_Code in( {…} :siteList {…} )`

### `getItemName`
`public DmResultPageing<List<TxtHItem_Mdl>> getItemName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select im.code as itemCode, im.name as itemName ,im.manualCode from itemMast im LEFT JOIN unitmast um ON im.saleUnit = um.code where (im.filterItemType IN (:FM,:SM) OR im.isCommon = 1)`
- Params: `:FM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:SM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getHProcess`
`public DmResultPageing<List<KeyFieldExtended>> getHProcess(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT pm.subCode ,sg.name ,sg.manualCode FROM processMast pm left join subGroup sg on sg.subcode=pm.subCode`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select dm.subCode as code, sg.name from departmentMast dm left join subGroup sg on sg.subCode = dm.subCode left join subGroupSiteList sgsl on sgsl.subCode = dm.subCode where sg.isActive = 1 and sgsl.site_Code = :siteCode and sg.nature IN (:GD,:CM)`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:GD` ← `DmServer.getCodeViaManualCode("GD", "manualCode", "subgroupNature", "Code", cnTemp)`; `:CM` ← `DmServer.getCodeViaManualCode("CM", "manualCode", "subgroupNature", "Code", cnTemp)`

### `generateExcelReport`
`private KeyFieldExtended generateExcelReport(ResultSet rsTemp, List<ExcelCellData_Mdl> dataset, LinkedHashMap<String, FieldUDT_Mdl> filter) throws Exception`

- Errors: "Invalid request." | "File Name not given." | "No record found!"
- Other calls: `BLACK.getIndex`, `Color.decode`, `DATE.toInt`, `DOUBLE.toInt`, `TIMESTAMP.toInt`

### `printFilterData`
`private int printFilterData(DmXlsWriter xlsWriter, XSSFSheet workSheet, int rowIndex, LinkedHashMap<String, FieldUDT_Mdl> filter, int totalColums)`

- Other calls: `DmBaseShared.xNull`

## Server `RptOrderPlanRegister_SrvImpl.java` (1117 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnId`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.removeAllCharacterExceptAlphaNumeric`, `DmServer.setReportHeaderExcel`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHPlanSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHPlanSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code, sm.name FROM siteMast sm`

### `getTxtHOrderNo`
`public DmResultPageing<List<TxtHSalesOrder_Mdl>> getTxtHOrderNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT so.docId, so.recId as sOrderNo, so.v_Date as sOrderDate, vt.short_Name as shortName, so.v_No, CONCAT(sgp.name, IIF(ISNULL(c.cityName, '')='', '', ' - '), c.cityName) as party , CASE WHEN so.site_Code = {…} THEN 1 ELSE 2 END as tempSr FROM sOrder AS so LEFT JOIN voucher_Type as vt on vt.v_Type = so.v_Type LEFT JOIN subGroup sgp on sgp.subCode = so.partyCode LEFT JOIN city c on c.cityCode = sgp.cityCode LEFT JOIN siteMast sm ON sm.code = so.site_Code WHERE so.site_Code in( {…} :siteList {…} ) and vt.Category IN ( ' {…} ' ,' {…} ', ' {…} ' )`

### `getTxtHOrderPlanNo`
`public DmResultPageing<List<TxtHSalesOrder_Mdl>> getTxtHOrderPlanNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT op.docId, op.recId as pOrderNo, op.v_Date as pOrderDate, op.v_No, vt.short_Name as shortName, sgp.name as partyName, c.cityName , CASE WHEN op.site_Code = {…} THEN 1 ELSE 2 END as tempSr FROM orderPlan AS op LEFT JOIN voucher_Type as vt on vt.v_Type = op.v_Type LEFT JOIN orderPlanDetail opd ON opd.docId=op.docId LEFT JOIN sOrder so ON so.docId=opd.soDocId LEFT JOIN subGroup sgp ON sgp.subCode=so.partyCode LEFT JOIN city c on c.cityCode = sgp.cityCode LEFT JOIN siteMast sm ON sm.code = op.site_Code WHERE op.site_Code in( {…} :siteList {…} ) AND vt.Category IN ( ' {…} ')`

### `getTxtHParty`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHParty(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT DISTINCT sgp.subCode as partyCode, sgp.name as partyName, sgp.manualCode, c.cityName as partyCity FROM sOrder AS so LEFT JOIN voucher_Type as vt on vt.v_Type = so.v_Type LEFT JOIN subGroup sgp on sgp.subCode = so.partyCode LEFT JOIN city c on c.cityCode = sgp.cityCode WHERE so.site_Code= ' {…} ' and vt.Category IN ( ' {…} ' ,' {…} ', ' {…} ' )`

### `getTxtHVType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type as code, vt.description, vt.category, vt.short_Name as shortName FROM voucher_Type vt WHERE vt.category IN (' {…} ') AND vt.isActive = 1`

### `getTxtHItem`
`public DmResultPageing<List<TxtHItem_Mdl>> getTxtHItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select im.code as itemCode, im.name as itemName, im.manualCode, it.name as itemType, ums.unit as itemUnitName, umsku.unit as sku from itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN itemType it ON it.code=im.filterItemType LEFT JOIN unitMast ums ON ums.code = im.saleUnit LEFT JOIN unitMast umsku ON umsku.code = im.sku WHERE im.IsActive = 1 AND (im.filterItemType IN (:FM, :SM) OR im.isCommon = 1) AND sl.site_Code = :site_Code`
- Params: `:FM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:SM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select dm.subCode as code, sg.name from departmentMast as dm left join subGroup as sg on dm.subCode = sg.subCode left join subGroupNature sgn on sgn.code = sg.nature where sgn.code <> :pc {…}`
- Params: `:pc` ← `DmServer.getCodeViaManualCode("PC", "manualCode", "subgroupNature", "Code", cnTemp)`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `execute`
`public DmResult<KeyFieldExtended> execute(String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`


### `createExcelReport`
`public KeyFieldExtended createExcelReport(String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, Connection cnTemp) throws Exception`

- SQL 1: `SELECT op.recId as OrderPlanNo, op.v_Date as OrderPlanDate, sm.name as site, vt.short_Name AS vTypeShortName, so.recId as OrderNo, so.v_Date as OrderDate, CONCAT(sgp.name, IIF(ISNULL(c.cityName, '')='', '', ' - '), c.cityName) as Party, sg.name as PlannedBy, sgd.name as department, IM.name as Item, IB.propertyDispCsv as Properties, sod.deliveryDt as DeliveryDate, smp.name as PlannedSite, sod.quantity as orderQty, vOpd.productionQty, vOpd.stockConsumeQty as reservedQty, vOpd.procureQty, sgpd.name AS procureDepartment, ums.unit as skuName FROM orderplan op LEFT JOIN ( SELECT opd.docId, opd.soDocId, opd.soV_SNo, opd.planSiteCode, MAX(opd.itemCode) AS itemCode, MAX(opd.ibCode) AS ibCode , SUM(opd.productionQty) productionQty, sum(opd.stockConsumeQty) stockConsumeQty , SUM(opd.procureQty) procureQty, MAX(procureDepartmentCode) AS procureDepartmentCode FROM orderPlanDetail opd GROUP BY opd.docId, opd.soDocId, opd.soV_SNo, opd.planSiteCode ) vOpd ON vOpd.docid = op.docId LEFT JOIN voucher_Type vt ON vt.v_Type = op.v_Type lEFT join subGroup sg ON sg.subCode = op.plannedByCode LEFT JOIN sOrderDetail sod ON sod.docId = vOpd.soDocId AND sod.v_SNo = vOpd.soV_SNo LEFT JOIN sOrder so ON so.docId = sod.docId LEFT JOIN subGroup sgp ON sgp.subCode = so.partyCode LEFT JOIN city c ON c.cityCode = sgp.cityCode LEFT JOIN subGroup sgd ON sgd.subCode = op.departmentCode LEFT JOIN itemMast IM ON IM.code = vOpd.itemCode LEFT JOIN itemBatch IB ON IB.code = vOpd.ibCode LEFT JOIN siteMast sm ON sm.code = op.site_Code LEFT JOIN siteMast smp ON smp.code = vOpd.planSiteCode LEFT JOIN subGroup sgpd ON sgpd.subCode = vOpd.procureDepartmentCode LEFT JOIN unitMast ums ON ums.code = im.sku WHERE :one = :one {…} ORDER BY op.site_Code, op.v_Date, vt.short_Name, op.v_No, op.recId ASC`
- Errors: "Invalid request." | "File Name not given." | "No record found!"
- Other calls: `RptOrderPlanRegister_Vld.validateInput`, `STServer.getSsnDepartmentCodeList`

### `getParameter`
`private String getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:one` ← `"1"`; `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getCellStyle`
`public XSSFCellStyle getCellStyle(DmXlsWriter xlsWriter, XSSFSheet workSheet)`

- Other calls: `BLACK.getIndex`, `Color.decode`

### `printFilterData`
`private int printFilterData(DmXlsWriter xlsWriter, XSSFSheet workSheet, int rowIndex, LinkedHashMap<String, FieldUDT_Mdl> filter, int totalColums)`

- Other calls: `DmBaseShared.xNull`

### `printTotal`
`private int printTotal(DmXlsWriter xlsWriter, XSSFSheet workSheet, int[] columnIndices, int fromRowIndex, int toRowIndex, int printRowIndex) throws Exception`


## Server `RptProcessMachineRegister_SrvImpl.java` (1174 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnId`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.removeAllCharacterExceptAlphaNumeric`, `DmServer.setReportHeaderExcel`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `execute`
`public DmResult<KeyFieldExtended> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT sm.name AS siteName, wo.recId as woNo, wo.v_Date as woDate, md.docId,md.machineCode,md.sno,md.capacity , sgp.name AS processName, md.capacityUnitCode,md.cycleTime,md.leadTime , md.startDate, md.startTime, md.endDate, md.endTime , md.runTime, md.idleTime, md.runningCost, md.remark , md.machineNo , CASE WHEN isNull(md.startDate, '') <> '' THEN CONCAT(FORMAT(md.startDate,'dd-MMM-yyyy'),' ', CAST(CAST(md.startTime AS DECIMAL(18,2)) AS VARCHAR)) ELSE '' END AS startDateTime , CASE WHEN isNull(md.endDate, '') <> '' THEN CONCAT(FORMAT(md.endDate,'dd-MMM-yyyy'),' ', CAST(CAST(md.endTime AS DECIMAL(18,2)) AS VARCHAR)) ELSE '' END AS endDateTime , (md.cycleTime) / 60 + (md.cycleTime % 60) / 100.00 as displayCycleTime , (md.leadTime) / 60 + (md.leadTime % 60) / 100.00 as displayLeadTime , (md.runTime) / 60 + (md.runTime % 60) / 100.00 as displayRunTime , (md.idleTime) / 60 + (md.idleTime % 60) / 100.00 as displayIdleTime , aim.name as machineName, um.unit as capacityUnitName , um.decimalPlaces AS capacityDecimalPlaces , CONCAT(FORMAT(p.v_Date,'dd-MMM-yyyy'),' ', CAST(CAST(p.v_Time AS DECIMAL(18,2)) AS VARCHAR)) AS entryDateTime , vt.short_Name , p.v_no , p.recid FROM processMachineDetail md LEFT JOIN process p on p.docId = md.docId LEFT JOIN voucher_type vt on vt.v_type = p.v_type LEFT JOIN wOrder wo ON wo.docId = p.woDocId LEFT JOIN siteMast sm ON sm.code = p.site_Code LEFT JOIN subGroup sgp ON sgp.subCode = p.processCode LEFT JOIN assetItemMast aim on aim.code = md.machineCode LEFT JOIN unitMast um on um.code = md.capacityUnitCode WHERE vt.category = :vCat {…} ORDER BY sm.name, p.v_Date, p.v_Time, short_name, v_no, recid, machineName`
- Other calls: `RptProcessMachineRegister_Vld.validateInput`

### `getParameter`
`private String getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:vCat` ← `pvc.PROCESS_MACHINE`; `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`

### `getTxtHWorkOrderNo`
`public DmResultPageing<List<TxtHWorkOrder_Mdl>> getTxtHWorkOrderNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT wo.docId, wo.recId as wOrderNo, wo.v_Date as wOrderDate, vt.short_Name ,wo.v_No , CASE WHEN wo.site_Code = {…} THEN 1 ELSE 2 END as tempSr FROM wOrder AS wo left join voucher_Type as vt on vt.v_Type = wo.v_Type LEFT JOIN siteMast sm ON sm.code = wo.site_Code where vt.Category = ' {…} ' AND ISNULL(wo.approvedBy, '') <> '' and wo.site_Code in( {…} :siteList {…} )`

### `getItemName`
`public DmResultPageing<List<TxtHItem_Mdl>> getItemName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select im.code as itemCode, im.name as itemName ,im.manualCode from itemMast im LEFT JOIN unitmast um ON im.saleUnit = um.code where (im.filterItemType IN (:FM,:SM) OR im.isCommon = 1)`
- Params: `:FM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:SM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getHProcess`
`public DmResultPageing<List<KeyFieldExtended>> getHProcess(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT pm.subCode ,sg.name ,sg.manualCode FROM processMast pm left join subGroup sg on sg.subcode=pm.subCode`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select dm.subCode as code, sg.name from departmentMast dm left join subGroup sg on sg.subCode = dm.subCode left join subGroupSiteList sgsl on sgsl.subCode = dm.subCode where sg.isActive = 1 and sgsl.site_Code = :siteCode and sg.nature IN (:GD,:CM)`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:GD` ← `DmServer.getCodeViaManualCode("GD", "manualCode", "subgroupNature", "Code", cnTemp)`; `:CM` ← `DmServer.getCodeViaManualCode("CM", "manualCode", "subgroupNature", "Code", cnTemp)`

### `getTxtHExpenseAc`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHExpenseAc(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode, sg.name, sg.manualCode FROM subGroup sg where sg.isActive = 1`

### `getTxtHMachine`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHMachine(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select aim.name, aim.code, aim.manualCode FROM assetItemMast aim LEFT JOIN assetGroupMast agm ON agm.code = aim.assetGroupCode WHERE agm.assetNature IN (:assetNature)`
- Other calls: `MACHINE.getId`

### `generateExcelReport`
`private KeyFieldExtended generateExcelReport(ResultSet rsTemp, List<ExcelCellData_Mdl> dataset, LinkedHashMap<String, FieldUDT_Mdl> filter, boolean printRepeatedHeaderData, String headerFieldName) throws Exception`

- Errors: "Invalid request." | "File Name not given." | "No record found!"
- Other calls: `BLACK.getIndex`, `Color.decode`, `DATE.toInt`, `DOUBLE.toInt`, `TIMESTAMP.toInt`

### `printFilterData`
`private int printFilterData(DmXlsWriter xlsWriter, XSSFSheet workSheet, int rowIndex, LinkedHashMap<String, FieldUDT_Mdl> filter, int totalColums)`

- Other calls: `DmBaseShared.xNull`

## Server `RptWorkOrderRegister_SrvImpl.java` (888 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.showJasper`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.xNull`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHVoucherType`
`public DmResultPageing<List<TxtHVoucher_Mdl>> getTxtHVoucherType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type as code , vt.description as voucherType , vt.category , vt.short_Name FROM voucher_Type vt WHERE vt.category=(' {…} ')`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT {…} as groupOnCode , {…} as groupOnName , {…} as group2Code , {…} as group2Name , {…} as SubReportRequired , {…} as SubRecipeRequired , wo.docId , wo.v_No , wo.recIdPrefix , wo.recId , wo.v_Prefix , wo.v_Date , wo.site_Code , wo.remark ,wo.minBatchQty , wo.maxBatchQty ,(wo.cycleTime) / 60 + (wo.cycleTime % 60) / 100.00 as woCycleTime ,(wo.leadTime) / 60 + (wo.leadTime % 60) / 100.00 as woLeadTime ,wo.quantity as quantity , wod.quantity as wodQuantity ,wo.amount as estimatedCost , VWOsod.producedQty , vt.short_Name as voucherShortName , sg.name as ProcessName , uim.unit as skuunitName , im.name as itemName , wopd.processCode ,rm.name as recipeName ,mwo.recId as mwoNo ,op.recId as opNo ,so.recId as soNo ,wod.soV_Sno ,wod.isStatus ,mwo.itemCode ,wod.ibCode ,soim.name as mwoItemName ,soums.unit as sku ,wod.quantity as mwoQuantity ,soums.decimalPlaces as skuDecimalPlaces , ib.propertyDispCsv ,iit.manualCode as itemType ,ISNULL(mwo.quantity,0) - ISNULL(mwo.woQty,0) + ISNULL(wod.quantity,0) as mwoPendingQty , ISNULL(wod.quantity, 0) - ISNULL(VWOsod.producedQty, 0) AS pendingQty , wod.v_SNo as VSNo , CASE WHEN wod.isStatus = 1 THEN 'Cancelled' WHEN wod.isStatus = 2 THEN 'Completed' ELSE 'Open' END AS status FROM dbo.wOrder as wo LEFT JOIN wOrderDetail AS wod ON wod.docId = wo.docId {…} left join voucher_Type vt ON vt.v_Type = wo.v_Type left join itemMast as im on wo.itemCode = im.code left join unitMast as uim on im.sku = uim.code LEFT JOIN wOrderProcessDetail wopd ON wopd.docId = wo.docId Left Join processMast pm on pm.subCode= wopd.processCode left join subGroup sg on sg.subcode=pm.subCode LEFT jOIN recipeMast rm ON rm.code = wo.recipeCode LEFT join masterWorkOrder mwo on mwo.docId=wod.mwoDocId LEFT JOIN orderPlanDetail opd on opd.docId = wod.opDocId AND opd.v_SNo = wod.opV_Sno LEFT JOIN orderPlan op on op.docId = opd.docId LEFT JOIN sOrderDetail sod on sod.docId = wod.soDocId AND sod.v_SNo = wod.soV_Sno LEFT JOIN sOrder so on so.docId = wod.soDocId LEFT JOIN itemMast soim on soim.code = mwo.itemCode left join itemBatch ib on ib.Code=opd.ibCode LEFT JOIN itemType iit ON iit.code = soim.itemType LEFT JOIN unitMast soums on soums.code = soim.sku WHERE vt.category = ' {…} ' AND ISNULL(wo.approvedBy, '') <> '' {…} ORDER BY groupOnName, group2Name, wo.v_Date, vt.short_Name, wo.v_No, wo.recId, wod.v_SNo`
- SQL 2: `SELECT so.recId AS soNo, vts.short_Name as soType , wo.docId ,wod.v_SNo , sgp.name AS partyName, cp.cityName as partyCity , sgd.name AS distributor, cd.cityName as distributorCity , sod.deliveryDt, sod.skuQty AS weight , uim.unit AS skuUnitName, wod.docId, wod.v_SNo FROM dbo.wOrder as wo LEFT JOIN wOrderDetail AS wod ON wod.docId = wo.docId LEFT JOIN voucher_Type vt ON vt.v_Type = wo.v_Type LEFT JOIN sOrderDetail AS sod ON sod.docId = wod.soDocId AND sod.v_SNo = wod.soV_SNo LEFT JOIN sOrder AS so ON so.docId = sod.docId LEFT JOIN voucher_Type vts ON vts.v_Type = so.v_Type LEFT JOIN itemMast AS im ON wod.itemCode = im.code LEFT JOIN unitMast AS uim ON im.sku = uim.code LEFT JOIN subGroup sgp ON sgp.subCode = so.partyCode LEFT JOIN subGroup sgd ON sgd.subCode = so.distributor LEFT JOIN city cp ON cp.cityCode = sgp.cityCode LEFT JOIN city cd ON cd.cityCode = sgd.cityCode WHERE wo.docId = $P{docId} and wod.v_SNo = $P{VSNo} AND ISNULL(wo.approvedBy, '') <> '' ORDER BY wo.v_Date, vt.short_Name, wo.v_No, wo.recId, wod.v_SNo`
- SQL 3: `select md.docId,md.machineCode,md.sno,md.capacity, md.capacityUnitCode,md.cycleTime,md.leadTime, (md.cycleTime) / 60 + (md.cycleTime % 60) / 100.00 as displayCycleTime, (md.leadTime) / 60 + (md.leadTime % 60) / 100.00 as displayLeadTime ,aim.name as machineName, um.unit as capacityUnitName ,md.processCode, sgp.name as processName , md.isManualEntry from wOrderMachineDetail md left join assetItemMast aim on aim.code = md.machineCode left join unitMast um on um.code = md.capacityUnitCode left join subgroup sgp on sgp.subCode = md.processCode where md.docId = $P{docId} and md.processCode= $P{processCode}`
- SQL 4: `select ex.docId, ex.expenseAcCode,ex.evalTypeCode,ex.evalRate, ex.amount , sg.name as expenseAcName, ex.processCode, sgp.name as processName, ex.isManualEntry , CASE WHEN ISNULL(ex.evalTypeCode,0)= {…} THEN ' {…} ' WHEN ISNULL(ex.evalTypeCode,0)= {…} THEN ' {…} ' end as expenseTypeName from wOrderExpenseDetail ex left join subgroup sg on sg.subCode = ex.expenseAcCode left join subgroup sgp on sgp.subCode = ex.processCode where ex.docId = $P{docId} and ex.processCode= $P{processCode}`
- SQL 5: `SELECT wo.docId ,wOrd.type,wOrd.itemCode , imwrd.name as recipeItemName , ib.propertyDispCsv , wOrd.v_SNo , wOrd.processCode ,wOrd.itemCode , sgp.name as processName , iit.manualCode as itemType , um.unit as skuName , um.decimalPlaces as itemUnitDecimalPlaces , wOrd.type , wOrd.isOutputDependent , wOrd.quantity as recipeQuantity , wOrd.rate as recipeRate , wOrd.amount as recipeAmount , wOrd.recipeQty , wOrd.ibCode , wOrd.outputDependentQty , case when wOrd.isOutputDependent='0' then 'No' when wOrd.isOutputDependent='1' then 'Yes' end as outputDependentName ,wOrd.isManualEntry ,CASE WHEN ISNULL(wOrd.type,0)= {…} THEN ' {…} ' WHEN ISNULL(wOrd.type,0)= {…} THEN ' {…} ' WHEN ISNULL(wOrd.type,0)= {…} THEN ' {…} ' WHEN ISNULL(wOrd.type,0)= {…} THEN ' {…} ' WHEN ISNULL(wOrd.type,0)= {…} THEN ' {…} ' end as TypeName FROM wOrder wo LEFT JOIN wOrderRecipeDetail wOrd ON wOrd.docId=wo.docId LEFT JOIN voucher_Type vt ON vt.v_Type = wo.v_Type LEFT JOIN itemMast im on im.code = wo.itemCode LEFT JOIN unitMast ums on ums.code = im.sku LEFT jOIN recipeMast rm ON rm.code = wo.recipeCode LEFT JOIN itemMast imWrd on imWrd.code = wOrd.itemCode LEFT JOIN itemBatch ib on ib.code = wOrd.ibCode LEFT JOIN subgroup sgp on sgp.subCode = wOrd.processCode LEFT JOIN itemType iit ON iit.code = imWrd.itemType LEFT JOIN unitMast um on um.code = imWrd.sku where wo.docId = $P{docId} and wOrd.processCode= $P{processCode}`
- Other calls: `BY_PRODUCT.getCode`, `BY_PRODUCT.getName`, `FIX.getCode`, `FIX.getName`, `INPUT.getCode`, `INPUT.getName`, `OUTPUT.getCode`, `OUTPUT.getName`, `QUANTITY.getCode`, `QUANTITY.getName`, `RptWorkOrderRegister_Vld.validateInput`, `SUB_ASSEMBLY.getCode`, `SUB_ASSEMBLY.getName`, `WASTAGE.getCode`, `WASTAGE.getName`

### `getParameter`
`private String getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`

### `getTxtHWorkOrderNo`
`public DmResultPageing<List<TxtHWorkOrder_Mdl>> getTxtHWorkOrderNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT wo.docId, wo.recId as wOrderNo, wo.v_Date as wOrderDate, vt.short_Name ,wo.v_No , CASE WHEN wo.site_Code = {…} THEN 1 ELSE 2 END as tempSr FROM wOrder AS wo left join voucher_Type as vt on vt.v_Type = wo.v_Type LEFT JOIN siteMast sm ON sm.code = wo.site_Code where vt.Category = ' {…} ' AND ISNULL(wo.approvedBy, '') <> '' and wo.site_Code in( {…} :siteList {…} )`

### `getItemName`
`public DmResultPageing<List<TxtHItem_Mdl>> getItemName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select im.code as itemCode, im.name as itemName ,im.manualCode from itemMast im LEFT JOIN unitmast um ON im.saleUnit = um.code where (im.filterItemType = :filterItemType )`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`

### `getTxtHStatus`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHStatus(DmHelper helper, boolean blnToEndOfFile)`


### `getGroupName`
`public DmResultPageing<List<KeyFieldExtended>> getGroupName(String excludeGroup, DmHelper helper, boolean blnToEndOfFile)`


### `getHProcess`
`public DmResultPageing<List<KeyFieldExtended>> getHProcess(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT pm.subCode ,sg.name ,sg.manualCode FROM processMast pm left join subGroup sg on sg.subcode=pm.subCode`

### `getSoDeatil`
`public DmResultPageing<List<KeyFieldExtended>> getSoDeatil(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHMasterWorkOrderNo`
`public DmResultPageing<List<TxtHMasterWorkOrder_Mdl>> getTxtHMasterWorkOrderNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select mwo.docId, mwo.recId as mwoNo, mwo.v_Date as mwoDate, vt.short_Name ,mwo.v_No , CASE WHEN mwo.site_Code = {…} THEN 1 ELSE 2 END as tempSr FROM masterWorkOrder AS mwo left join voucher_Type as vt on vt.v_Type = mwo.v_Type LEFT JOIN siteMast sm ON sm.code = mwo.site_Code where vt.Category = ' {…} ' AND ISNULL(mwo.approvedBy, '') <> '' and mwo.site_Code in( {…} :siteList {…} )`

## Server `RptProcessIssueReceiveRegister_SrvImpl.java` (960 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnId`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.removeAllCharacterExceptAlphaNumeric`, `DmServer.setReportHeaderExcel`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `execute`
`public DmResult<KeyFieldExtended> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT wo.recId AS woNo, wo.v_Date AS woDate , sgp.name AS processName, sgdp.name AS departmentName , sgp.name AS processName, sgdp.name AS departmentName , vtp.short_Name AS vTypeShortName , vtp.category AS processVCat , CASE WHEN pd.type = {…} THEN ' {…} ' WHEN pd.type = {…} THEN ' {…} ' WHEN pd.type = {…} THEN ' {…} ' WHEN pd.type = {…} THEN ' {…} ' WHEN pd.type = {…} THEN ' {…} ' ELSE '' END AS processSubType, p.v_No AS entryNo, p.v_Date AS entryDate, p.v_Time AS entryTime , im.name AS itemName, ib.propertyDispCsv, psmp.name AS packSizeName, umps.unit AS packUnit, pd.packCount , pd.quantity, ums.unit AS skuName, pd.rate, pd.amount FROM process p LEFT JOIN processDetail pd ON pd.docId = p.docId LEFT JOIN voucher_Type vtp ON vtp.v_Type = p.v_Type LEFT JOIN wOrder wo ON p.woDocId = wo.docId LEFT JOIN voucher_Type vtw ON vtw.v_Type = wo.v_Type LEFT JOIN itemMast im ON im.code = pd.itemCode LEFT JOIN recipeMast rm ON rm.code = wo.recipeCode LEFT JOIN unitMast ums ON ums.code = im.sku LEFT JOIN subGroup sgdp ON sgdp.subCode = pd.departmentCode LEFT JOIN subGroup sgp ON sgp.subCode = p.processCode LEFT JOIN itemBatch ib ON ib.code = pd.ibCode LEFT JOIN packSizeMast psmp ON psmp.code = pd.packSizeCode LEFT JOIN unitMast umps ON umps.code = psmp.packUnitCode LEFT JOIN siteMast sm ON sm.code = p.site_Code WHERE :one = :one AND vtp.category IN (:vCatI, :vCatR) {…} ORDER BY sm.name, wo.v_Date, vtw.short_Name, wo.v_No, wo.recId, p.v_Date, p.v_Time, vtp.serialNo, vtp.short_Name, p.v_No, p.recId, pd.v_SNo`
- Other calls: `BY_PRODUCT.getCode`, `BY_PRODUCT.getName`, `INPUT.getCode`, `INPUT.getName`, `OUTPUT.getCode`, `OUTPUT.getName`, `RptProcessIssueReceiveRegister_Vld.validateInput`, `SUB_ASSEMBLY.getCode`, `SUB_ASSEMBLY.getName`, `WASTAGE.getCode`, `WASTAGE.getName`

### `getParameter`
`private String getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:one` ← `"1"`; `:vCatI` ← `pvc.PROCESS_ISSUE`; `:vCatR` ← `pvc.PROCESS_RECEIVE`; `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`

### `getTxtHWorkOrderNo`
`public DmResultPageing<List<TxtHWorkOrder_Mdl>> getTxtHWorkOrderNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT wo.docId, wo.recId as wOrderNo, wo.v_Date as wOrderDate, vt.short_Name ,wo.v_No , CASE WHEN wo.site_Code = {…} THEN 1 ELSE 2 END as tempSr FROM wOrder AS wo left join voucher_Type as vt on vt.v_Type = wo.v_Type LEFT JOIN siteMast sm ON sm.code = wo.site_Code where vt.Category = ' {…} ' AND ISNULL(wo.approvedBy, '') <> '' and wo.site_Code in( {…} :siteList {…} )`

### `getItemName`
`public DmResultPageing<List<TxtHItem_Mdl>> getItemName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select im.code as itemCode, im.name as itemName ,im.manualCode from itemMast im LEFT JOIN unitmast um ON im.saleUnit = um.code where (im.filterItemType IN (:FM,:SM) OR im.isCommon = 1)`
- Params: `:FM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:SM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getHProcess`
`public DmResultPageing<List<KeyFieldExtended>> getHProcess(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT pm.subCode ,sg.name ,sg.manualCode FROM processMast pm left join subGroup sg on sg.subcode=pm.subCode`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select dm.subCode as code, sg.name from departmentMast dm left join subGroup sg on sg.subCode = dm.subCode left join subGroupSiteList sgsl on sgsl.subCode = dm.subCode where sg.isActive = 1 and sgsl.site_Code = :siteCode and sg.nature IN (:GD,:CM)`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:GD` ← `DmServer.getCodeViaManualCode("GD", "manualCode", "subgroupNature", "Code", cnTemp)`; `:CM` ← `DmServer.getCodeViaManualCode("CM", "manualCode", "subgroupNature", "Code", cnTemp)`

### `getTxtHType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHType(DmHelper helper, boolean blnToEndOfFile)`


### `generateExcelReport`
`private KeyFieldExtended generateExcelReport(ResultSet rsTemp, LinkedHashMap<String, FieldUDT_Mdl> filter) throws Exception`

- Errors: "Invalid request." | "File Name not given." | "No record found!"
- Other calls: `BLACK.getIndex`, `Color.decode`

### `printFilterData`
`private int printFilterData(DmXlsWriter xlsWriter, XSSFSheet workSheet, int rowIndex, LinkedHashMap<String, FieldUDT_Mdl> filter, int totalColums)`

- Other calls: `DmBaseShared.xNull`

## Server `RptProcessExpenseRegister_SrvImpl.java` (1139 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnId`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.removeAllCharacterExceptAlphaNumeric`, `DmServer.setReportHeaderExcel`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `execute`
`public DmResult<KeyFieldExtended> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT sm.name AS siteName, wo.recId as woNo, wo.v_Date as woDate , sgp.name AS processName, ex.docId , ex.expenseAcCode , ex.evalTypeCode , CASE WHEN ex.evalTypeCode = {…} THEN ' {…} ' WHEN ex.evalTypeCode = {…} THEN ' {…} ' ELSE '' END AS evalType, ex.evalRate , ex.amount , sg.name as expenseAcName , CONCAT(FORMAT(p.v_Date,'dd-MMM-yyyy'),' ', CAST(CAST(p.v_Time AS DECIMAL(18,2)) AS VARCHAR)) AS entryDateTime , vt.short_Name , p.v_no , p.recid FROM processExpenseDetail ex LEFT JOIN process p ON p.docId = ex.docId LEFT JOIN voucher_type vt ON vt.v_type = p.v_type LEFT JOIN wOrder wo ON wo.docId = p.woDocId LEFT JOIN siteMast sm ON sm.code = p.site_Code LEFT JOIN subGroup sgp ON sgp.subCode = p.processCode LEFT JOIN subgroup sg ON sg.subCode = ex.expenseAcCode WHERE vt.category = :vCat {…} order by sm.name, p.v_Date, p.v_Time, short_name, v_no, recid, expenseAcName`
- Other calls: `FIX.getCode`, `FIX.getName`, `QUANTITY.getCode`, `QUANTITY.getName`, `RptProcessExpenseRegister_Vld.validateInput`

### `getParameter`
`private String getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:vCat` ← `pvc.PROCESS_EXPENSE`; `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`

### `getTxtHWorkOrderNo`
`public DmResultPageing<List<TxtHWorkOrder_Mdl>> getTxtHWorkOrderNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT wo.docId, wo.recId as wOrderNo, wo.v_Date as wOrderDate, vt.short_Name,wo.v_No , CASE WHEN wo.site_Code = {…} THEN 1 ELSE 2 END as tempSr FROM wOrder AS wo left join voucher_Type as vt on vt.v_Type = wo.v_Type LEFT JOIN siteMast sm ON sm.code = wo.site_Code where vt.Category = ' {…} ' AND ISNULL(wo.approvedBy, '') <> '' and wo.site_Code in( {…} :siteList {…} )`

### `getItemName`
`public DmResultPageing<List<TxtHItem_Mdl>> getItemName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select im.code as itemCode, im.name as itemName ,im.manualCode from itemMast im LEFT JOIN unitmast um ON im.saleUnit = um.code where (im.filterItemType IN (:FM,:SM) OR im.isCommon = 1)`
- Params: `:FM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:SM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getHProcess`
`public DmResultPageing<List<KeyFieldExtended>> getHProcess(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT pm.subCode ,sg.name ,sg.manualCode FROM processMast pm left join subGroup sg on sg.subcode=pm.subCode`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select dm.subCode as code, sg.name from departmentMast dm left join subGroup sg on sg.subCode = dm.subCode left join subGroupSiteList sgsl on sgsl.subCode = dm.subCode where sg.isActive = 1 and sgsl.site_Code = :siteCode and sg.nature IN (:GD,:CM)`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:GD` ← `DmServer.getCodeViaManualCode("GD", "manualCode", "subgroupNature", "Code", cnTemp)`; `:CM` ← `DmServer.getCodeViaManualCode("CM", "manualCode", "subgroupNature", "Code", cnTemp)`

### `getTxtHType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `FIX.getCode`, `FIX.getName`, `QUANTITY.getCode`, `QUANTITY.getName`

### `getTxtHExpenseAc`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHExpenseAc(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode, sg.name, sg.manualCode FROM subGroup sg where sg.isActive = 1`

### `generateExcelReport`
`private KeyFieldExtended generateExcelReport(ResultSet rsTemp, List<ExcelCellData_Mdl> dataset, LinkedHashMap<String, FieldUDT_Mdl> filter, boolean printRepeatedHeaderData, String headerFieldName) throws Exception`

- Errors: "Invalid request." | "File Name not given." | "No record found!"
- Other calls: `BLACK.getIndex`, `Color.decode`, `DATE.toInt`, `DOUBLE.toInt`, `TIMESTAMP.toInt`

### `printFilterData`
`private int printFilterData(DmXlsWriter xlsWriter, XSSFSheet workSheet, int rowIndex, LinkedHashMap<String, FieldUDT_Mdl> filter, int totalColums)`

- Other calls: `DmBaseShared.xNull`

## Server `RptProductionSummaryRegister_SrvImpl.java` (1151 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnId`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.removeAllCharacterExceptAlphaNumeric`, `DmServer.setReportHeaderExcel`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHReportFor`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHReportFor(DmHelper pageing, boolean blnToEndOfFile)`


### `getTxtHGroupOn`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGroupOn(DmHelper pageing, boolean blnToEndOfFile)`


### `getTxtHWorkOrderNo`
`public DmResultPageing<List<TxtHWorkOrder_Mdl>> getTxtHWorkOrderNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT wo.docId, wo.recId as wOrderNo, wo.v_Date as wOrderDate ,vt.short_Name, wo.v_No, im.name as itemName, sm.manualCode AS siteManualCode , CASE WHEN wo.site_Code = {…} THEN 1 ELSE 2 END as tempSr FROM wOrder AS wo LEFT JOIN siteMast sm ON sm.code = wo.site_Code LEFT JOIN voucher_Type as vt ON vt.v_Type = wo.v_Type LEFT JOIN itemMast im ON im.code = wo.itemCode WHERE vt.Category = ' {…} ' AND ISNULL(wo.approvedBy, '') <> '' and wo.site_Code in( {…} :siteList {…} )`

### `getTxtHProcessReceiveNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHProcessReceiveNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT p.docId, p.recId, vt.short_Name, p.v_Date, p.v_Time, p.v_No , CASE WHEN p.site_Code = {…} THEN 1 ELSE 2 END as tempSr FROM process AS p left join voucher_Type as vt on vt.v_Type = p.v_Type LEFT JOIN siteMast sm ON sm.code = p.site_Code where vt.Category = ' {…} ' and p.site_Code in( {…} :siteList {…} )`

### `getTxtHItemName`
`public DmResultPageing<List<TxtHItem_Mdl>> getTxtHItemName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select im.code as itemCode ,im.name as itemName ,im.manualCode ,it.name as itemType ,umsku.unit as sku from itemMast im LEFT JOIN unitmast um ON um.code = im.saleUnit LEFT JOIN itemType it ON it.code = im.filterItemType LEFT JOIN unitMast umsku ON umsku.code = im.sku where (im.filterItemType IN (:FM,:SM) OR im.isCommon = 1)`
- Params: `:FM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:SM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHProcess`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHProcess(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT pm.subCode ,sg.name ,sg.manualCode FROM processMast pm left join subGroup sg on sg.subcode=pm.subCode`

### `getTxtHReceiveType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHReceiveType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `ProcessItemType.values`

### `getTxtHYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHWarehouse`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHWarehouse(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select dm.subCode as code, sg.name from departmentMast dm left join subGroup sg on sg.subCode = dm.subCode left join subGroupSiteList sgsl on sgsl.subCode = dm.subCode where sg.isActive = 1 and sgsl.site_Code = :siteCode and sg.nature IN (:GD,:CM)`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:GD` ← `DmServer.getCodeViaManualCode("GD", "manualCode", "subgroupNature", "Code", cnTemp)`; `:CM` ← `DmServer.getCodeViaManualCode("CM", "manualCode", "subgroupNature", "Code", cnTemp)`

### `execute`
`public DmResult<KeyFieldExtended> execute(String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`


### `createExcelReport`
`private KeyFieldExtended createExcelReport(String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, Connection cnTemp) throws Exception`

- SQL 1: `SELECT STRING_AGG(CONVERT(NVARCHAR(max),sm.manualCode), ',') AS siteCSV FROM siteMast sm WHERE sm.code IN ( {…} )`
- SQL 2: `SELECT * FROM ( SELECT {…} AS group1Name, MAX(sgd.name) AS warehouse , MAX(sm.manualCode) AS siteManualCode , MAX(um.unit) AS skuName, {…} AS properties {…} FROM process p LEFT JOIN processDetail pd ON pd.docId = p.docId LEFT JOIN voucher_Type vt ON vt.v_Type = p.v_Type LEFT JOIN wOrder wo ON wo.docId = p.woDocId LEFT JOIN siteMast sm ON sm.code = p.site_Code LEFT JOIN itemMast im ON im.code = pd.itemCode LEFT JOIN itemBatch ib ON ib.code = pd.ibCode LEFT JOIN unitMast um ON um.code = im.sku LEFT JOIN packSizeMast psm ON psm.code = im.packSizeCode LEFT JOIN unitMast ump ON ump.code = psm.packUnitCode LEFT JOIN subGroup sgd ON sgd.subCode = pd.departmentCode LEFT JOIN itemGroup ig ON ig.code = im.itemGroup LEFT JOIN itemCat ic ON ic.code = im.itemCategoryCode WHERE vt.category = :vCat AND :one = :one {…} GROUP BY p.site_Code, pd.departmentCode, {…} ) AS t PIVOT ( {…} FOR siteManualCode IN ( {…} )) AS PivotTable ORDER BY warehouse, group1Name`
- Errors: "Invalid request." | "File Name not given." | "No record found!"
- Other calls: `RptProductionSummaryRegister_Vld.validateInput`, `STServer.getSsnDepartmentCodeList`

### `getParameter`
`private String getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:vCat` ← `pvc.PROCESS_RECEIVE`; `:one` ← `"1"`; `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`; `:wastage` ← `STEnum.ProcessItemType.WASTAGE.getCode()`
- Other calls: `STServer.getSsnDepartmentCodeList`, `WASTAGE.getCode`

### `getCellStyle`
`public XSSFCellStyle getCellStyle(DmXlsWriter xlsWriter, XSSFSheet workSheet)`

- Other calls: `BLACK.getIndex`, `Color.decode`

### `getCellStyle`
`public XSSFCellStyle getCellStyle(DmXlsWriter xlsWriter, XSSFSheet workSheet, boolean fontBold, String dataFormat)`


### `printFilterData`
`private int printFilterData(DmXlsWriter xlsWriter, XSSFSheet workSheet, int rowIndex, LinkedHashMap<String, FieldUDT_Mdl> filter, int totalColums)`

- Other calls: `DmBaseShared.xNull`

### `printTotal`
`private int printTotal(DmXlsWriter xlsWriter, XSSFSheet workSheet, int[] columnIndices, int fromRowIndex, int toRowIndex, int printRowIndex) throws Exception`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmBase.self.xls`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmReportLayout.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `paperbiz.production.reports.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| createExcelReport | `if (!DmSession.getSessions().containsKey(sessionId))` | "Invalid request." |
| createExcelReport | `if (DmShared.xNull(fileName).equalsIgnoreCase(""))` | "File Name not given." |
| generateExcelReport | `if (!DmSession.getSessions().containsKey(sessionId))` | "Invalid request." |
| generateExcelReport | `if (DmShared.xNull(fileName).equalsIgnoreCase(""))` | "File Name not given." |
| generateExcelReport | `if (status == false)` | "No record found!" |
| createExcelReport | `if (status == false)` | "No record found!" |

## Notes (hand-written)
_No hand-written notes yet._
