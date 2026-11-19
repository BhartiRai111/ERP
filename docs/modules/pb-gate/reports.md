# pb-gate / reports

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/gate/src/reports/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: rule: From Date is mandatory.; rule: To Date is mandatory.; rule: From Date should be less than or equal to To Date.; Site (Text)
- Lookups/suggestions: getTxtHVoucherType, getTxtHGateNo, getTxtHWeighmentNo, getTxtHOrderNo, getTxtHUnloadingNo, getTxtHSite, getTxtHTransporter, getTxtHPartyName, getTxtHItem, getTxtHType, getTxtHGroupOn, getTxtHGateEntryNum, getTxtHItemDetail, getTxtHVoucher, getHVechileType, getHGateStatus, getHPurpose, getTxtHGateInOutNo, getTxtHYesNo, getHAdviceStatus, getTxtHWeighment, getTxtHVendor, getTxtHVehicleType, getTxtHDepartment, getTxtHContractor
- CSV export: no

## Files
| file | lines |
|---|---|
| client/RptAdviceRegister.java | 738 |
| client/RptGateRegister.java | 754 |
| client/RptUnloadingEntryRegister.java | 650 |
| client/RptVisitorGatePassRegister.java | 457 |
| client/RptWeighmentRegister.java | 674 |
| client/RptWeighmentRegisterManually.java | 615 |
| server/RptAdviceRegister_SrvImpl.java | 966 |
| server/RptGateRegister_SrvImpl.java | 864 |
| server/RptUnloadingEntryRegister_SrvImpl.java | 920 |
| server/RptVisitorGatePassRegister_SrvImpl.java | 536 |
| server/RptWeighmentRegisterManually_SrvImpl.java | 854 |
| server/RptWeighmentRegister_SrvImpl.java | 929 |
| shared/RptAdviceRegister_Srv.java | 45 |
| shared/RptAdviceRegister_SrvAsync.java | 47 |
| shared/RptAdviceRegister_Vld.java | 30 |
| shared/RptGateRegister_Srv.java | 41 |
| shared/RptGateRegister_SrvAsync.java | 42 |
| shared/RptGateRegister_Vld.java | 32 |
| shared/RptUnloadingEntryRegisterRegister_Vld.java | 29 |
| shared/RptUnloadingEntryRegister_Srv.java | 39 |
| shared/RptUnloadingEntryRegister_SrvAsync.java | 40 |
| shared/RptVisitorGatePassRegister_Srv.java | 31 |
| shared/RptVisitorGatePassRegister_SrvAsync.java | 31 |
| shared/RptVisitorGatePassRegister_Vld.java | 29 |
| shared/RptWeighmentRegisterManually_Srv.java | 35 |
| shared/RptWeighmentRegisterManually_SrvAsync.java | 39 |
| shared/RptWeighmentRegister_Srv.java | 40 |
| shared/RptWeighmentRegister_SrvAsync.java | 38 |
| shared/TxtHCity_Mdl.java | 24 |
| shared/TxtHContractor_Mdl.java | 59 |
| shared/TxtHGateEntry_Mdl.java | 42 |
| shared/TxtHGateNo_Mdl.java | 44 |
| shared/TxtHItemDetail_Mdl.java | 24 |
| shared/TxtHItem_Mdl.java | 60 |
| shared/TxtHOrderNo_Mdl.java | 44 |
| shared/TxtHPartyName_Mdl.java | 33 |
| shared/TxtHTransporter_Mdl.java | 32 |
| shared/TxtHUnloadingNo_Mdl.java | 44 |
| shared/TxtHVoucher_Mdl.java | 24 |
| shared/TxtHWeighmentNo_Mdl.java | 43 |
| shared/TxtPartyName_Mdl.java | 61 |
| shared/TxtTransporters_Mdl.java | 61 |

## Models (shared DTOs)
### TxtHVoucher_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | shortName |

### TxtHTransporter_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityName |

### TxtHGateNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | v_Date |
| String | short_Name |
| String | v_Time |

### TxtHItemDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |

### TxtHItem_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | itemGroup |
| String | itemCategory |
| String | unit |
| String | unitCode |
| String | manualCode |

### TxtHOrderNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | v_Date |
| String | v_Time |
| String | short_Name |

### TxtTransporters_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | stateCode |
| String | stateName |
| String | manualCode |
| String | cityName |
| String | partyType |

### TxtHPartyName_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityName |

### TxtHGateEntry_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | v_Date |
| String | v_Time |
| String | shortName |

### TxtHWeighmentNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | v_Date |
| String | v_Time |
| String | short_Name |

### TxtPartyName_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | stateCode |
| String | stateName |
| String | manualCode |
| String | cityName |
| String | partyType |

### TxtHUnloadingNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | v_Date |
| String | v_Time |
| String | short_Name |

### TxtHCity_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | stateName |

### TxtHContractor_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | itemGroup |
| String | itemCategory |
| String | unit |
| String | unitCode |
| String | manualCode |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHVoucher | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHWeighment | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGateEntryNum | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHGateEntry_Mdl>> |
| getTxtHVendor | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHContractor_Mdl>> |
| getTxtHVehicleType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItem | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getTxtHDepartment | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHContractor | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHContractor_Mdl>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getGroupName | String excludeGroup, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVoucher | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucher_Mdl>> |
| getHPurpose | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHYesNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPartyName | String voucherCategory, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtPartyName_Mdl>> |
| getHGateStatus | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getHVechileType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getTxtHTransporter | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtTransporters_Mdl>> |
| getTxtHGateEntryNum | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHGateEntry_Mdl>> |
| getTxtCity | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHCity_Mdl>> |
| getGroupName | String excludeGroup, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getHAdviceStatus | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getTxtHVoucherType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGateNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHGateNo_Mdl>> |
| getTxtHWeighmentNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHWeighmentNo_Mdl>> |
| getTxtHOrderNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHOrderNo_Mdl>> |
| getTxtHUnloadingNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHUnloadingNo_Mdl>> |
| getTxtHPartyName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHPartyName_Mdl>> |
| getTxtHItem | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemDetail_Mdl>> |
| getTxtHTransporter | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHTransporter_Mdl>> |

## RPC interface
| method | params | returns |
|---|---|---|
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVoucher | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucher_Mdl>> |
| getHPurpose | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPartyName | String voucherCategory, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtPartyName_Mdl>> |
| getHGateStatus | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getHVechileType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getTxtHTransporter | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtTransporters_Mdl>> |
| getTxtHGateEntryNum | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHGateEntry_Mdl>> |
| getTxtCity | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHCity_Mdl>> |
| getTxtHYesNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGateInOutNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVoucher | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucher_Mdl>> |
| getTxtCity | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHCity_Mdl>> |
| getHVechileType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getHGateStatus | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGateInOutNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVoucherType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGroupOn | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGateEntryNum | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHGateEntry_Mdl>> |
| getTxtHPartyName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHPartyName_Mdl>> |
| getTxtHItemDetail | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemDetail_Mdl>> |
| getTxtHTransporter | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHTransporter_Mdl>> |
| getGroupName | String excludeGroup, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHUnloadingNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHUnloadingNo_Mdl>> |
| getTxtHOrderNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHOrderNo_Mdl>> |
| getTxtHType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `RptAdviceRegister_Vld`
```java
public class RptAdviceRegister_Vld {
    public static void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter) {
        if (DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(10, "From Date is mandatory.");
        }
        if (DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(20, "To Date is mandatory.");
        }
        if ((!DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) && (!DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase(""))) {
            if (DmShared.convertDate(hashMapFilter.get("fromDate").getText()).getTime() > DmShared.convertDate(hashMapFilter.get("toDate").getText()).getTime()) {
                throw new DmException(80, "From Date should be less than or equal to To Date.");
            }
        }
    }
}
```

## Validator `RptUnloadingEntryRegisterRegister_Vld`
```java
public class RptUnloadingEntryRegisterRegister_Vld {
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

## Validator `RptGateRegister_Vld`
```java
public class RptGateRegister_Vld {
    public static void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter) {
        if (DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(10, "From Date is mandatory.");
        }
        if (DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(20, "To Date is mandatory.");
        }
        if ((!DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) && (!DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase(""))) {
            if (DmShared.convertDate(hashMapFilter.get("fromDate").getText()).getTime() > DmShared.convertDate(hashMapFilter.get("toDate").getText()).getTime()) {
                throw new DmException(80, "From Date should be less than or equal to To Date.");
            }
        }
    }
    }
```

## Validator `RptVisitorGatePassRegister_Vld`
```java
public class RptVisitorGatePassRegister_Vld {
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

## Server `RptWeighmentRegisterManually_SrvImpl.java` (854 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.processWjMessageQueue`, `DmServer.saveLog`, `DmServer.showJasper`, `DmShared.convertDate`, `DmShared.dNull`, `DmShared.isValidText`, `DmShared.xNull`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `select wgmnt.docId , wgmnt.v_Type , wgmnt.v_date , wgmnt.recId as weighmentRecId , wgmnt.outWeighDate , wgmnt.outWeighTime , wgmnt.inWeight , wgmnt.outWeight , wgmnt.netWeight , um.unit as unitName , sg.name as partyName , gateIO.recId as gateRecId , gateIO.loadUnload , gateIO.vehicleNo , wgmnt.gateDocId , wgmnt.v_Time , wgmnt.v_No , c.cityName as partyCityName , sgb.name as transporter , im.name as itemName , wgmnt.inManualWeighBy , wgmnt.inManualWeighDate , wgmnt.outManualWeighBy , wgmnt.outManualWeighDate , uld.quantity as unloadingNetWeight , po.recId as poRecId , ul.recId as unloadingRecId FROM weighment as wgmnt left join voucher_Type as vt on wgmnt.v_Type = vt.v_Type left join subGroup as sg on wgmnt.partyCode = sg.subCode left join city c ON c.cityCode = sg.cityCode left join gateInOut as gateIO on wgmnt.gateDocId = gateIO.docId left join subGroup sgb ON sgb.subCode=gateIO.transporterCode left join unitMast as um on um.code = wgmnt.unitCode left join unloading as ul on ul.weighmentDocId = wgmnt.docId left join unloadingDetail as uld on uld.docId = ul.docId left join itemMast as im on im.code = uld.itemCode left join gateAdviceDetail gad ON gad.docId = uld.gateDocId AND gad.v_Sno = uld.gateAdviceV_Sno left join pOrder as po on po.docId=uld.poDocId where 1=1 {…}`
- Other calls: `PRINT.getValue`, `SYSTEM_ACTIVITY.getName`

### `validateInput`
`public void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter)`

- Errors: "From Date is mandatory." | "To Date is mandatory." | "From Date should be less than or equal to To Date."

### `getParameter`
`private String getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `getTxtHVoucherType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVoucherType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT V_type, Description as voucherType FROM Voucher_Type VT WHERE Category = ' {…} ' and isActive=1`

### `getTxtHGateNo`
`public DmResultPageing<List<TxtHGateNo_Mdl>> getTxtHGateNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT gtIO.docId, gtIO.recId as gateRecId , gtIO.v_Date, gtIO.v_Time, vt.short_Name, gtIO.v_No , CASE WHEN gtIO.site_Code = {…} THEN 1 ELSE 2 END as tempSr from gateInOut as gtIO LEFT JOIN voucher_Type vt ON gtIO.v_Type = vt.v_Type LEFT JOIN siteMast sm ON sm.code = gtIO.site_Code WHERE vt.category = :vCat and gtIO.site_Code in( {…} :siteList {…} )`
- Params: `:vCat` ← `pvc.GATE`

### `getTxtHWeighmentNo`
`public DmResultPageing<List<TxtHWeighmentNo_Mdl>> getTxtHWeighmentNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT wgmnt.docId, wgmnt.recId as weighmentRecId , wgmnt.v_Date, wgmnt.v_Time, vt.short_Name, wgmnt.v_No , CASE WHEN wgmnt.site_Code = {…} THEN 1 ELSE 2 END as tempSr from weighment as wgmnt LEFT JOIN voucher_Type vt ON wgmnt.v_Type = vt.v_Type LEFT JOIN siteMast sm ON sm.code = wgmnt.site_Code WHERE vt.category = ' {…} ' and wgmnt.site_Code in( {…} :siteList {…} )`

### `getTxtHOrderNo`
`public DmResultPageing<List<TxtHOrderNo_Mdl>> getTxtHOrderNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT po.docId , po.recId AS poRecId , po.v_Date , po.v_No, vt.short_Name , CASE WHEN po.site_Code = {…} THEN 1 ELSE 2 END as tempSr FROM pOrder po LEFT JOIN voucher_Type vt ON po.v_Type = vt.v_Type LEFT JOIN siteMast sm ON sm.code = po.site_Code WHERE vt.category IN (:vCat,:vCatU) AND ISNULL(po.approvedBy,'') <> '' and po.site_Code in( {…} :siteList {…} )`
- Params: `:vCat` ← `pvc.GENERAL_PURCHASE_ORDER`; `:vCatU` ← `pvc.GENERAL_PURCHASE_ORDER_UNREGISTERED`

### `getTxtHUnloadingNo`
`public DmResultPageing<List<TxtHUnloadingNo_Mdl>> getTxtHUnloadingNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ul.docId , ul.recId AS unloadingRecId , ul.v_Date , ul.v_Time, vt.short_Name, ul.v_No , CASE WHEN ul.site_Code = {…} THEN 1 ELSE 2 END as tempSr FROM Unloading ul LEFT JOIN voucher_Type vt ON ul.v_Type = vt.v_Type LEFT JOIN siteMast sm ON sm.code = ul.site_Code WHERE vt.category = :vCat and ul.site_Code in( {…} :siteList {…} )`
- Params: `:vCat` ← `pvc.UNLOADING`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name as site FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHTransporter`
`public DmResultPageing<List<TxtHTransporter_Mdl>> getTxtHTransporter(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code, sg.name as transporter, sg.manualCode, c.cityName FROM subGroup sg LEFT JOIN city c ON c.cityCode = sg.cityCode WHERE sg.nature = :nature`
- Params: `:nature` ← `DmServer.getCodeViaManualCode("TP", "manualCode", "subGroupNature", "code", cnTemp)`

### `getTxtHPartyName`
`public DmResultPageing<List<TxtHPartyName_Mdl>> getTxtHPartyName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code, sg.name as partyName, sg.manualCode, c.cityName FROM subGroup sg LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature`

### `getTxtHItem`
`public DmResultPageing<List<TxtHItemDetail_Mdl>> getTxtHItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select im.code, im.name as itemName, im.manualCode from itemMast im WHERE (im.filterItemType = :filterItemType or im.isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`; `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`

## Server `RptWeighmentRegister_SrvImpl.java` (929 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.processWjMessageQueue`, `DmServer.saveLog`, `DmServer.showJasper`, `DmServer.vNull`, `DmShared.convertDate`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.xNull`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `select {…} as group1Code , {…} as group1Name , {…} as group2Code , {…} as group2Name , wgmnt.docId , wgmnt.v_Type , wgmnt.v_date , wgmnt.recId as WgmntRecId , wgmnt.outWeighDate , wgmnt.outWeighTime , wgmnt.partyCode , wgmnt.unitCode , wgmnt.inWeight as firstWgnmt , wgmnt.outWeight as secondWgmnt , uld.quantity as netWt , po.recId as poRecId , um.unit as unitName , sg.name as partyName , im.Name as itemName , gateIO.recId as gateRecId , gateIO.loadUnload , gateIO.vehicleNo , gateIO.transporterCode , wgmnt.gateDocId , vt.short_Name as voucherName , wgmnt.v_Time , wgmnt.v_No , vtm.name AS vehicleType , c.cityName as partyCityName , sgb.displayName as transporter , gateIOd.itemDescription , uld.departmentCode , sg2.name as godownName , ul.recId as unloadingRecId , vtm.name as vehicleType FROM weighment as wgmnt left join voucher_Type as vt on wgmnt.v_Type = vt.v_Type left join subGroup as sg on wgmnt.partyCode = sg.subCode left join city c ON c.cityCode = sg.cityCode left join gateInOut as gateIO on wgmnt.gateDocId = gateIO.docId left join gateInOutDetail as gateIOd on gateIOd.docId = gateIO.docId left join subGroup sgb ON sgb.subCode=gateIO.transporterCode left join unitMast as um on um.code = wgmnt.unitCode left join unloading as ul on ul.weighmentDocId= wgmnt.docId left join unloadingDetail as uld on uld.docId = ul.docId left join itemMast as im on im.code = uld.itemCode left join gateAdviceDetail as gad on gad.docId=uld.gateDocId AND gad.v_Sno = uld.gateAdviceV_Sno left join pOrder as po on po.docId=gad.poDocId Left Join vehicleTypeMast as vtm ON vtm.code = gateIO.vehicleTypeCode left join subGroup as sg2 on sg2.subCode= uld.departmentCode where 1=1 {…}`
- Other calls: `PRINT.getValue`, `SYSTEM_ACTIVITY.getName`

### `validateInput`
`public void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter)`

- Errors: "From Date is mandatory." | "To Date is mandatory." | "From Date should be less than or equal to To Date."

### `getParameter`
`private String getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `getTxtHVoucherType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVoucherType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT V_type, Description as voucherType FROM Voucher_Type VT WHERE Category = ' {…} ' and isActive=1`

### `getTxtHType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHType(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHTransporter`
`public DmResultPageing<List<TxtHTransporter_Mdl>> getTxtHTransporter(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code, sg.name as transporter, sg.manualCode, c.cityName FROM subGroup sg LEFT JOIN city c ON c.cityCode = sg.cityCode WHERE sg.nature = :nature`
- Params: `:nature` ← `DmServer.getCodeViaManualCode("TP", "manualCode", "subGroupNature", "code", cnTemp)`

### `getTxtHPartyName`
`public DmResultPageing<List<TxtHPartyName_Mdl>> getTxtHPartyName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code, sg.name as partyName, sg.manualCode, c.cityName FROM subGroup sg LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature`

### `getTxtHGroupOn`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGroupOn(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHGateEntryNum`
`public DmResultPageing<List<TxtHGateEntry_Mdl>> getTxtHGateEntryNum(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select gio.recId , gio.docId ,gio.v_Date , gio.v_Time,gio.v_No , vt.short_Name , CASE WHEN gio.site_Code = {…} THEN 1 ELSE 2 END as tempSr from gateInOut gio LEFT JOIN voucher_Type vt ON vt.v_Type = gio.v_Type LEFT JOIN siteMast sm ON sm.code = gio.site_Code WHERE vt.category=(' {…} ') AND gio.site_Code in( {…} :siteList {…} )`

### `getTxtHItemDetail`
`public DmResultPageing<List<TxtHItemDetail_Mdl>> getTxtHItemDetail(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code,name ,manualCode from itemMast im WHERE (im.filterItemType = :filterItemType or im.isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`; `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getGroupName`
`public DmResultPageing<List<KeyFieldExtended>> getGroupName(String excludeGroup, DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHUnloadingNo`
`public DmResultPageing<List<TxtHUnloadingNo_Mdl>> getTxtHUnloadingNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ul.docId , ul.recId AS unloadingRecId , ul.v_Date , ul.v_Time , ul.v_No , vt.short_Name , CASE WHEN ul.site_Code = {…} THEN 1 ELSE 2 END as tempSr FROM Unloading ul LEFT JOIN voucher_Type vt ON ul.v_Type = vt.v_Type LEFT JOIN siteMast sm ON sm.code = ul.site_Code WHERE vt.category = :vCat and ul.site_Code in( {…} :siteList {…} )`
- Params: `:vCat` ← `pvc.UNLOADING`

### `getTxtHOrderNo`
`public DmResultPageing<List<TxtHOrderNo_Mdl>> getTxtHOrderNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT po.docId , po.recId AS poRecId , po.v_Date , po.v_No, vt.short_Name , CASE WHEN po.site_Code = {…} THEN 1 ELSE 2 END as tempSr FROM pOrder po LEFT JOIN voucher_Type vt ON po.v_Type = vt.v_Type LEFT JOIN siteMast sm ON sm.code = po.site_Code WHERE vt.category IN (:vCat,:vCatU) AND ISNULL(po.approvedBy,'') <> '' and po.site_Code in( {…} :siteList {…} )`
- Params: `:vCat` ← `pvc.GENERAL_PURCHASE_ORDER`; `:vCatU` ← `pvc.GENERAL_PURCHASE_ORDER_UNREGISTERED`

## Server `RptGateRegister_SrvImpl.java` (864 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.processWjMessageQueue`, `DmServer.saveLog`, `DmServer.showJasper`, `DmServer.vNull`, `DmShared.convertDate`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.xNull`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `validateInput`
`public void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter)`

- Errors: "From Date is mandatory." | "To Date is mandatory." | "From Date should be less than or equal to To Date."

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHVoucher`
`public DmResultPageing<List<TxtHVoucher_Mdl>> getTxtHVoucher(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type as code , vt.description as voucherType , vt.category , vt.short_Name FROM voucher_Type vt WHERE vt.category=(' {…} ') AND vt.isActive = 1`

### `getHVechileType`
`public DmResultPageing<List<KeyFieldExtended>> getHVechileType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vtm.code,vtm.name From vehicleTypeMast vtm`

### `getHGateStatus`
`public DmResultPageing<List<KeyFieldExtended>> getHGateStatus(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHGateEntryNum`
`public DmResultPageing<List<TxtHGateEntry_Mdl>> getTxtHGateEntryNum(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select gio.recId , gio.docId ,gio.v_Date , gio.v_Time,gio.v_No , vt.short_Name , CASE WHEN gio.site_Code = {…} THEN 1 ELSE 2 END as tempSr from gateInOut gio LEFT JOIN voucher_Type vt ON vt.v_Type = gio.v_Type LEFT JOIN siteMast sm ON sm.code = gio.site_Code WHERE vt.category=(' {…} ') AND gio.site_Code in( {…} :siteList {…} )`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT gio.docId ,gio.v_Type ,gio.v_No ,gio.recIdPrefix ,gio.recId ,gio.v_Date ,gio.v_Time ,gio.partyCode ,sgb.name as partyName ,gio.transporterCode ,gio.gateOutBy ,gio.gateOutDate ,gio.gateOutTime ,gio.gateOutBy ,gio.driver ,gio.loadUnload ,vtm.name AS vehicleType ,gio.vehicleNo ,case when gio.isWeighmentRequired = 1 then 'Yes' else 'No' end as isWeighmentRequired ,gio.docGrossWt ,gio.docTareWt ,gio.docNetWt ,gio.unitCode ,gio.u_Name ,gio.preparedBy ,giod.itemDescription ,giod.qty ,giod.remark ,c.cityName ,sg.name as transporter ,vt.description AS voucherTypeName ,um.unit as unitName ,gni.name AS gateInNo ,gno.name AS gateOutNo ,gio.isRejected ,gio.rejectionRemark ,gio.refNo ,gio.refDate FROM gateInOut gio left join gateInOutDetail giod ON giod.docId=gio.docId left join subGroup sgb ON sgb.subCode=gio.partyCode Left join unitMast um ON um.Code=gio.unitCode left join subGroup sg ON sg.subCode=gio.transporterCode LEFT JOIN city c ON c.cityCode = sgb.cityCode Left Join vehicleTypeMast as vtm ON vtm.code = gio.vehicleTypeCode LEFT JOIN gateNoMast as gni ON gni.code = gio.inGateNoCode LEFT JOIN gateNoMast as gno ON gno.code = gio.outGateNoCode LEFT JOIN voucher_Type vt ON gio.v_Type = vt.v_Type {…} ORDER BY gio.v_Date, gio.v_Time, vt.short_Name, gio.v_No, gio.recId, giod.v_SNo`
- Other calls: `PRINT.getValue`, `RptGateRegister_Vld.validateInput`, `SYSTEM_ACTIVITY.getName`

### `getParameter`
`private void getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`

### `getHPurpose`
`public DmResultPageing<List<KeyFieldExtended>> getHPurpose(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `GatePurpose.values`

### `getTxtHPartyName`
`public DmResultPageing<List<TxtPartyName_Mdl>> getTxtHPartyName(String voucherCategory, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code, sg.name as partyName, sg.manualCode, c.cityName FROM subGroup sg LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature`

### `getTxtHTransporter`
`public DmResultPageing<List<TxtTransporters_Mdl>> getTxtHTransporter(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code, sg.name, sg.manualCode, c.cityName FROM subGroup sg LEFT JOIN city c ON c.cityCode = sg.cityCode WHERE sg.nature = :nature`
- Params: `:nature` ← `DmServer.getCodeViaManualCode("TP", "manualCode", "subGroupNature", "code", cnTemp)`

### `getTxtCity`
`public DmResultPageing<List<TxtHCity_Mdl>> getTxtCity(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select ct.cityCode , ct.cityName , sm.name as stateName from city ct left join stateMast sm on sm.code = ct.stateCode`

### `getTxtHGateInOutNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGateInOutNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT gn.code AS code , gn.name AS name FROM gateNoMast gn`

### `getTxtHYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHYesNo(DmHelper helper, boolean blnToEndOfFile)`


## Server `RptAdviceRegister_SrvImpl.java` (966 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.processWjMessageQueue`, `DmServer.saveLog`, `DmServer.showJasper`, `DmServer.vNull`, `DmShared.convertDate`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.xNull`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `validateInput`
`public void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter)`

- Errors: "From Date is mandatory." | "To Date is mandatory." | "From Date should be less than or equal to To Date."

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHVoucher`
`public DmResultPageing<List<TxtHVoucher_Mdl>> getTxtHVoucher(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type as code , vt.description as voucherType , vt.category , vt.short_Name FROM voucher_Type vt WHERE vt.category=(' {…} ') AND vt.isActive = 1`

### `getHVechileType`
`public DmResultPageing<List<KeyFieldExtended>> getHVechileType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vtm.code,vtm.name From vehicleTypeMast vtm`

### `getHGateStatus`
`public DmResultPageing<List<KeyFieldExtended>> getHGateStatus(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHGateEntryNum`
`public DmResultPageing<List<TxtHGateEntry_Mdl>> getTxtHGateEntryNum(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select gio.recId , gio.docId ,gio.v_Date , gio.v_Time,gio.v_No , vt.short_Name , CASE WHEN gio.site_Code = {…} THEN 1 ELSE 2 END as tempSr from gateInOut gio LEFT JOIN voucher_Type vt ON vt.v_Type = gio.v_Type LEFT JOIN siteMast sm ON sm.code = gio.site_Code WHERE vt.category=(' {…} ') AND gio.loadunload = :UNLOADING AND gio.site_Code in( {…} :siteList {…} )`
- Other calls: `UNLOADING.getId`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT {…} as group1Code , {…} as group1Name , {…} as group2Code , {…} as group2Name ,gio.docId ,gio.v_Type ,gio.v_No ,gio.recIdPrefix ,gio.recId ,gio.v_Date ,gio.v_Time ,gio.partyCode ,sgb.name as partyName ,gio.transporterCode ,gio.gateOutBy ,gio.gateOutDate ,gio.gateOutTime ,gio.gateOutBy ,gio.driver ,gio.loadUnload ,vtm.name as vehicleType ,gio.vehicleNo ,case when gio.isWeighmentRequired = 1 then 'Yes' else 'No' end as isWeighmentRequired ,gio.docGrossWt ,gio.docTareWt ,gio.docNetWt ,gio.unitCode ,gio.u_Name ,gio.preparedBy ,c.cityName ,sg.name as transporter ,ctrans.cityName as transporterCity ,vt.description AS voucherTypeName ,um.unit as unitName ,po.recId as orderNo ,im.name as itemName ,gad. approxWt ,im.propertyList ,gad.approxWt ,case when isNull(uld.docId,'') <> '' then 'Unloaded' else 'Unloading Pending' end as adviceStatus FROM gateInOut gio left join gateAdviceDetail gad on gad.docId = gio.docId LEFT JOIN unloadingDetail uld ON uld.gateDocId = gad.docId AND uld.gateAdviceV_Sno = gad.v_Sno left join pOrder po on po.docId =gad.poDocId LEFT JOIN itemMast im ON im.code = gad.itemCode left join subGroup sgb ON sgb.subCode=gio.partyCode Left join unitMast um ON um.Code=gad.unitCode left join subGroup sg ON sg.subCode=gio.transporterCode left Join city ctrans on ctrans.cityCode=sg.cityCode LEFT JOIN city c ON c.cityCode = sgb.cityCode LEFT JOIN vehicleTypeMast vtm ON vtm.code = gio.vehicleTypeCode LEFT JOIN voucher_Type vt ON gio.v_Type = vt.v_Type {…} ORDER BY {…} {…} , {…} {…} {…} , {…} {…} {…} gio.v_Date, gio.loadUnload,vt.short_Name ,gio.v_No, gio.recId , gio.v_Time`
- Other calls: `PRINT.getValue`, `RptAdviceRegister_Vld.validateInput`, `SYSTEM_ACTIVITY.getName`

### `getParameter`
`private void getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`

### `getHPurpose`
`public DmResultPageing<List<KeyFieldExtended>> getHPurpose(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `GatePurpose.values`

### `getTxtHYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHPartyName`
`public DmResultPageing<List<TxtPartyName_Mdl>> getTxtHPartyName(String voucherCategory, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code, sg.name as partyName, sg.manualCode, c.cityName FROM subGroup sg LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature`

### `getTxtHTransporter`
`public DmResultPageing<List<TxtTransporters_Mdl>> getTxtHTransporter(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code, sg.name, sg.manualCode, c.cityName FROM subGroup sg LEFT JOIN city c ON c.cityCode = sg.cityCode WHERE sg.nature = :nature`
- Params: `:nature` ← `DmServer.getCodeViaManualCode("TP", "manualCode", "subGroupNature", "code", cnTemp)`

### `getTxtCity`
`public DmResultPageing<List<TxtHCity_Mdl>> getTxtCity(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select ct.cityCode , ct.cityName , sm.name as stateName from city ct left join stateMast sm on sm.code = ct.stateCode`

### `getGroupName`
`public DmResultPageing<List<KeyFieldExtended>> getGroupName(String excludeGroup, DmHelper helper, boolean blnToEndOfFile)`


### `getHAdviceStatus`
`public DmResultPageing<List<KeyFieldExtended>> getHAdviceStatus(DmHelper helper, boolean blnToEndOfFile)`


## Server `RptUnloadingEntryRegister_SrvImpl.java` (920 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.processWjMessageQueue`, `DmServer.saveLog`, `DmServer.showJasper`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.xNull`

### `getTxtHVoucher`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVoucher(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type as code , vt.description as name FROM voucher_Type vt WHERE vt.category=(' {…} ') AND vt.isActive = 1`

### `getTxtHWeighment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHWeighment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select wg.docId as weighmentCode,wg.recId as weighmentName,wg.v_Date as weighmentDate ,wg.v_No , vt.short_Name as weighmentType , CASE WHEN wg.site_Code = {…} THEN 1 ELSE 2 END as tempSr from weighment wg LEFT JOIN voucher_Type vt ON vt.v_Type = wg.v_Type LEFT JOIN siteMast sm ON sm.code = wg.site_Code WHERE wg.site_Code in( {…} :siteList {…} )`

### `getTxtHGateEntryNum`
`public DmResultPageing<List<TxtHGateEntry_Mdl>> getTxtHGateEntryNum(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select gio.recId , gio.docId ,gio.v_Date , gio.v_Time,gio.v_No , vt.short_Name , CASE WHEN gio.site_Code = {…} THEN 1 ELSE 2 END as tempSr from gateInOut gio LEFT JOIN voucher_Type vt ON vt.v_Type = gio.v_Type LEFT JOIN siteMast sm ON sm.code = gio.site_Code WHERE vt.category=(' {…} ') AND gio.site_Code in( {…} :siteList {…} )`

### `getTxtHVendor`
`public DmResultPageing<List<TxtHContractor_Mdl>> getTxtHVendor(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name AS name,sg.manualCode ,c.cityName as city FROM subgroup sg LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature left join city as c on c.cityCode = sg.cityCode WHERE sgn.code IN ( SELECT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_type = acf.v_Type )`

### `getTxtHVehicleType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVehicleType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vtm.code,vtm.name From vehicleTypeMast vtm`

### `getTxtHItem`
`public DmResultPageing<List<TxtHItem_Mdl>> getTxtHItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , um.code AS unitCode , um.unit,im.manualCode FROM itemMast im LEFT JOIN unitmast um ON im.sku = um.code WHERE (im.filterItemType = :filterItemType or im.isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`; `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT dm.subCode AS code, sg.name FROM departmentMast dm LEFT JOIN subGroup sg ON sg.subCode = dm.subCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.manualCode IN ('CM','PR') {…}`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getTxtHContractor`
`public DmResultPageing<List<TxtHContractor_Mdl>> getTxtHContractor(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name AS name,sg.manualCode ,c.cityName as city FROM subgroup sg LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature left join city as c on c.cityCode = sg.cityCode WHERE sgn.code IN ( SELECT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_type = acf.v_Type )`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `select {…} as group1Code , {…} as group1Name , {…} as group2Code , {…} as group2Name , ul.docId,ul.v_Date, vt.short_Name,ul.v_Time,ul.recId,wg.recId as weighmentName,gio.recId as gateName,ISNULL(sg.name,'') as partyName, ISNULL(c.cityName,'') as cityName,ul.preparedBy,um.decimalPlaces as decimalPlaces, vtm.name as vehicleType, gio.vehicleNo,im.name as itemName, im.propertyList,dm.name as departmentName, po.recId as poRecId, uld.gateAdviceV_Sno, sg1.name as contractorName, um.unit as unitName, uld.noOfLabour as totalUnloader, 0 as unloadingAmt, uld.approxQty AS approxWt, icm.name as colorName, ism.name as sizeName, imm.companyName as itemMakeName, gio.transporterCode, sgt.name as transporterName, cn.cityName as cityNameTransporter, ls.recId as sampleNo, ls.v_Date as sampleDate, iml.isLabRequired, gio.gateOutDate, gio.gateOutTime, wg.outWeighDate, wg.outWeighTime, vt.category as vType, case when isNull(ls.docId ,'') <>'' then 'Yes' else case when iml.isLabRequired=2 then 'NA' else 'No' end end as 'sample', ib.propertyDispCsv from unloading ul left join voucher_Type vt on vt.v_Type = ul.v_Type LEFT JOIN subGroup sg ON sg.subCode = ul.partyCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN city cn ON cn.cityCode = sg.cityCode and sg.nature = 9 LEFT JOIN weighment wg ON wg.docId = ul.weighmentDocId LEFT JOIN (select uld.docid, MAX(uld.gateDocId) as gateDocId from unloadingDetail uld GROUP BY uld.docid) vUld ON vUld.docid = ul.docId LEFT JOIN gateInOut gio ON gio.docId = vUld.gateDocId LEFT JOIN unloadingDetail uld ON uld.docId = ul.docId LEFT JOIN itemMast im ON im.code = uld.itemCode LEFT JOIN itemBatch ib ON ib.code = uld.ibCode LEFT JOIN subGroup dm On dm.subCode = uld.departmentCode LEFT JOIN subGroup sg1 ON sg1.subCode = uld.contractorCode left join subGroup sgt ON sgt.subCode=gio.transporterCode LEFT JOIN unitMast um ON um.code = uld.unitCode LEFT JOIN vehicleTypeMast vtm ON vtm.code = gio.vehicleTypeCode LEFT JOIN pOrder po ON po.docId = uld.poDocId LEFT JOIN gateAdviceDetail gad ON gad.docId = uld.gateDocId AND gad.v_Sno = uld.gateAdviceV_Sno LEFT JOIN itemColourMast icm ON icm.code = ib.colourCode LEFT JOIN itemSizeMast ism ON ism.code = ib.sizeCode LEFT JOIN itemMakeMast imm ON imm.code = ib.itemMakeCode left join labSample as ls on ls.refDocId=uld.docId and ls.refV_Sno=uld.v_SNo left join itemMast as iml on iml.code =uld.itemCode {…} order by {…} {…} {…} , {…} {…} {…} , ul.v_Date, vt.short_Name, ul.v_No, ul.recId`
- Other calls: `PRINT.getValue`, `RptUnloadingEntryRegisterRegister_Vld.validateInput`, `SYSTEM_ACTIVITY.getName`

### `getParameter`
`private void getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`

### `getGroupName`
`public DmResultPageing<List<KeyFieldExtended>> getGroupName(String excludeGroup, DmHelper helper, boolean blnToEndOfFile)`


### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

## Server `RptVisitorGatePassRegister_SrvImpl.java` (536 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.processWjMessageQueue`, `DmServer.saveLog`, `DmServer.showJasper`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.xNull`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHVoucher`
`public DmResultPageing<List<TxtHVoucher_Mdl>> getTxtHVoucher(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type as code , vt.description as name , vt.category , vt.short_Name FROM voucher_Type vt WHERE vt.category=(' {…} ') AND vt.isActive = 1`

### `getTxtCity`
`public DmResultPageing<List<TxtHCity_Mdl>> getTxtCity(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select ct.cityCode , ct.cityName , sm.name as stateName from city ct left join stateMast sm on sm.code = ct.stateCode`

### `getHVechileType`
`public DmResultPageing<List<KeyFieldExtended>> getHVechileType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vtm.code,vtm.name From vehicleTypeMast vtm`

### `getHGateStatus`
`public DmResultPageing<List<KeyFieldExtended>> getHGateStatus(DmHelper helper, boolean blnToEndOfFile)`


### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT vgp.docId ,vgp.recId ,vgp.v_Date ,vgp.v_Time ,vgp.name ,vgp.address ,vgp.mobile ,vtm.name AS vehicleType ,vgp.vehicleNo ,vgp.personToMeet ,vgp.purpose ,vgp.gateOutDate ,vgp.gateOutTime , ctb.cityName FROM visitorGatePass vgp LEFT JOIN City ctb ON ctb.cityCode = vgp.cityCode Left Join vehicleTypeMast as vtm ON vtm.code = vgp.vehicleTypeCode LEFT JOIN voucher_Type vt ON vgp.v_Type = vt.v_Type {…} ORDER BY vgp.v_Date Asc,vgp.v_Time Asc,vgp.recId Asc`
- Other calls: `PRINT.getValue`, `RptVisitorGatePassRegister_Vld.validateInput`, `SYSTEM_ACTIVITY.getName`

### `getParameter`
`private void getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`

### `getTxtHGateInOutNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGateInOutNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT gn.code AS code , gn.name AS name FROM gateNoMast gn`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmReportLayout.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `paperbiz.gate.gateEntry.shared`, `paperbiz.gate.reports.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| validateInput | `if (DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase(""))` | "From Date is mandatory." |
| validateInput | `if (DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase(""))` | "To Date is mandatory." |
| validateInput | `if (DmShared.convertDate(hashMapFilter.get("fromDate").getText()).getTime() > DmShared.convertDate(hashMapFilter.get("toDate").getText()).getTime())` | "From Date should be less than or equal to To Date." |

## Notes (hand-written)
_No hand-written notes yet._
