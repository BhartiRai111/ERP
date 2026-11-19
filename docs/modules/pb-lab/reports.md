# pb-lab / reports

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/lab/src/reports/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: rule: From Date is mandatory.; rule: To Date is mandatory.; rule: From Date should be less than or equal to To Date.
- Lookups/suggestions: getTxtHSite, getTxtHSampleNum, getTxtHVoucher, getTxtHTestTemplate, getTxtHLabNum, getTxtHItem, getTxtHRefrence, getTxtHStatus
- CSV export: no

## Files
| file | lines |
|---|---|
| client/RptLabRegister.java | 344 |
| client/RptSampleRegister.java | 507 |
| server/RptLabRegister_SrvImpl.java | 483 |
| server/RptSampleRegister_SrvImpl.java | 646 |
| shared/RptLabRegister_Srv.java | 29 |
| shared/RptLabRegister_SrvAsync.java | 30 |
| shared/RptLabRegister_Vld.java | 32 |
| shared/RptSampleRegister_Srv.java | 35 |
| shared/RptSampleRegister_SrvAsync.java | 36 |
| shared/RptSampleRegister_Vld.java | 27 |
| shared/TxtHLabNo_Mdl.java | 33 |
| shared/TxtHSampleNo_Mdl.java | 33 |
| shared/TxtHVoucher_Mdl.java | 23 |

## Models (shared DTOs)
### TxtHVoucher_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | shortName |

### TxtHSampleNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | v_Date |
| String | shortName |

### TxtHLabNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | v_Date |
| String | shortName |

## RPC interface
| method | params | returns |
|---|---|---|
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVoucher | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucher_Mdl>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getTxtHSampleNum | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHSampleNo_Mdl>> |
| getTxtHTestTemplate | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHLabNum | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHLabNo_Mdl>> |

## RPC interface
| method | params | returns |
|---|---|---|
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVoucher | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucher_Mdl>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getTxtHSampleNum | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHSampleNo_Mdl>> |
| getTxtHItem | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTestTemplate | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHRefrence | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHStatus | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getGroupName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `RptSampleRegister_Vld`
```java
public class RptSampleRegister_Vld {
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

## Validator `RptLabRegister_Vld`
```java
public class RptLabRegister_Vld {
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

## Server `RptLabRegister_SrvImpl.java` (483 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.processWjMessageQueue`, `DmServer.showJasper`, `DmShared.convertDate`, `DmShared.dNull`, `DmShared.xNull`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `select lab.docId, lab.v_No, lab.v_Type, vt.short_Name as voucherTypeText, lab.v_Prefix, lab.v_Date, lab.v_Time, lab.recId, lab.recIdPrefix, lab.technician as technicianCode, sg.name as technicianName,lab.labSampleDocId,smp.recId as sampleRecId, lab.sampleConsumedQty, lab.preparedBy, lab.preparedDt As preparedDate, lab.u_Name, lab.u_EntDt, lab.approvedBy, lab.approvalDateTime, lab.u_EntDt_LatestLine, lab.wtaCode, lab.site_Code , ld.impurityCode, ipm.name as impurityName, ld.range1, ld.range2, ld.formulaForQuantity, ld.formulaForAmount, ld.foundValue, ipm.valueType from lab lab LEFT JOIN voucher_Type vt ON vt.v_Type = lab.v_Type LEFT JOIN subGroup sg ON sg.subCode = lab.technician LEFT JOIN labSample smp ON smp.docId = lab.labSampleDocId LEFT JOIN labDetail ld ON ld.docId = lab.docId LEFT JOIN impurityMast ipm ON ipm.code = ld.impurityCode WHERE vt.category = ' {…} ' AND ISNULL(lab.approvedBy, '') <> '' {…} ORDER BY lab.recId Asc, lab.v_Date Desc,vt.short_Name Asc,lab.v_No Asc`
- Other calls: `RptLabRegister_Vld.validateInput`

### `getParameter`
`private void getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`

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

### `getTxtHSampleNum`
`public DmResultPageing<List<TxtHSampleNo_Mdl>> getTxtHSampleNum(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select smpl.recId , smpl.docId ,smpl.v_Date , smpl.v_No, vt.short_Name from labSample smpl LEFT JOIN voucher_Type vt ON vt.v_Type = smpl.v_Type WHERE vt.category=(' {…} ')`

### `getTxtHVoucher`
`public DmResultPageing<List<TxtHVoucher_Mdl>> getTxtHVoucher(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type as code , vt.description as voucherType , vt.category , vt.short_Name FROM voucher_Type vt WHERE vt.category=(' {…} ') AND vt.isActive = 1`

### `getTxtHTestTemplate`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTestTemplate(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select tm.Code, tm.Description from testTemplateMast tm`

### `getTxtHLabNum`
`public DmResultPageing<List<TxtHLabNo_Mdl>> getTxtHLabNum(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select lab.recId , lab.docId ,lab.v_Date , lab.v_No, vt.short_Name from lab lab LEFT JOIN voucher_Type vt ON vt.v_Type = lab.v_Type WHERE vt.category=(' {…} ')`

## Server `RptSampleRegister_SrvImpl.java` (646 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.processWjMessageQueue`, `DmServer.showJasper`, `DmShared.convertDate`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.xNull`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT {…} as groupOnCode , {…} as groupOnName ,smp.docId,smp.v_Time, smp.v_Type,vt1.short_Name AS v_TypeName,lb.labSampleDocId,vt.short_Name as refvTypeName , smp.refRecId,tmm.Description as testTemplateName, smp.recId, smp.recIdPrefix, smp.v_No, smp.v_Date,smp.itemCode,im.displayName as itemName,smp.testTemplateCode,smp.refVType, smp.sampleQty,iim.sampleUnit, um.unit, um.decimalPlaces ,CASE WHEN isNull(lb.labSampleDocId,0) <> 0 THEN 'Completed' ELSE 'Pending' END AS status FROM labSample smp LEFT JOIN voucher_type vt ON vt.v_Type = smp.refVType LEFT JOIN voucher_type vt1 ON vt1.v_Type = smp.v_Type LEFT JOIN lab lb ON lb.labSampleDocId = smp.DocId LEFT JOIN itemMast im ON im.code = smp.itemCode left join testTemplateMast as tmm on tmm.code=smp.testTemplateCode LEFT JOIN itemImpurityMast iim ON iim.Code = smp.itemImpurityCode LEFT JOIN unitMast um ON um.code = iim.sampleUnit WHERE vt1.category = ' {…} ' {…} ORDER BY groupOnName, smp.v_Date ,smp.v_Time,vt.short_Name ,smp.v_No ,smp.recId`
- Other calls: `RptSampleRegister_Vld.validateInput`

### `getParameter`
`private void getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`

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

### `getTxtHSampleNum`
`public DmResultPageing<List<TxtHSampleNo_Mdl>> getTxtHSampleNum(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select smpl.recId , smpl.docId ,smpl.v_Date , smpl.v_No, vt.short_Name from sample smpl LEFT JOIN voucher_Type vt ON vt.v_Type = smpl.v_Type WHERE vt.category=(' {…} ')`

### `getTxtHVoucher`
`public DmResultPageing<List<TxtHVoucher_Mdl>> getTxtHVoucher(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type as code , vt.description as voucherType , vt.category , vt.short_Name FROM voucher_Type vt WHERE vt.category=(' {…} ') AND vt.isActive = 1`

### `getTxtHItem`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select im.code as itemCode, im.name as itemName, um.unit as itemUnitName from itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN unitMast um ON um.code = im.saleUnit WHERE im.IsActive = 1 AND (im.filterItemType = :filterItemType OR im.isCommon = 1) AND sl.site_Code = :site_Code`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHTestTemplate`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTestTemplate(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select tm.Code, tm.Description from testTemplateMast tm`

### `getTxtHRefrence`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHRefrence(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select lab_SampleRefVType from enviro e where e.site_Code = :site_Code`
- SQL 2: `Select A.Items,VT.Description ,VT.Category From Voucher_TYpe VT Left Join( Select Items from dbo.FA_Split(' {…} ',',') ) A On VT.V_Type=A.Items Where IsNull(A.Items,'')<>''`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHStatus`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHStatus(DmHelper helper, boolean blnToEndOfFile)`


### `getGroupName`
`public DmResultPageing<List<KeyFieldExtended>> getGroupName(DmHelper helper, boolean blnToEndOfFile)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmReportLayout.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `paperbiz.lab.reports.shared`, `paperbiz.projectMenu.self.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| validateInput | `if (DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase(""))` | "From Date is mandatory." |
| validateInput | `if (DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase(""))` | "To Date is mandatory." |
| validateInput | `if (DmShared.convertDate(hashMapFilter.get("fromDate").getText()).getTime() > DmShared.convertDate(hashMapFilter.get("toDate").getText()).getTime())` | "From Date should be less than or equal to To Date." |

## Notes (hand-written)
_No hand-written notes yet._
