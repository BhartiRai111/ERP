# pb-self-toolkit / productionRequisition

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/productionRequisition/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: requisition, requisitionDetail
- Update tables: requisition, requisitionDetail
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Requisition Type (Text); Requisition Date (Text); Requisition By (Text); Department By (Text); Department To (Text); Site By Code (Text); Site To Code (Text); rule: Department to and Department by cannot be same.; rule: GridRow; rule: Requisition Detail is mandatory.
- Lookups/suggestions: -
- CSV export: no

## Files
| file | lines |
|---|---|
| server/ProductionRequisition_Server.java | 514 |
| shared/GrdRequisition_Mdl.java | 205 |
| shared/Requisition_Mdl.java | 260 |
| shared/Requisition_Vld.java | 51 |

## Models (shared DTOs)
### Requisition_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| RecId | recid |
| String | date |
| String | siteByCode |
| String | siteByName |
| String | siteToCode |
| String | siteToName |
| String | departmentByCode |
| String | departmentByName |
| String | departmentToCode |
| String | departmentToName |
| String | subCode |
| String | subName |
| String | approvedBy |
| String | modifiedBy |
| String | preparedBy |
| String | userEntDtLatestLine |
| String | wtaCode |
| String | priorityTypeCode |
| String | priorityTypeName |
| String | requisitionTypeCode |
| String | requisitionTypeName |
| String | remark |
| String | recIdPrefix |
| String | vNo |
| ModifyReason_Mdl | modifyReasonMdl |
| String | itemCodeCsv |
| List<GrdRequisition_Mdl> | grdData |

### GrdRequisition_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | unitCode |
| String | unitName |
| String | allowDecimalPlaces |
| String | date |
| String | remark |
| String | vSNo |
| String | qty |
| String | isStatus |
| String | approval |
| String | approvedBy |
| String | approvedDateTime |
| String | propertyList |
| String | sizeName |
| String | properties |
| String | opDocId |
| String | opVSNo |
| String | woDocId |
| String | worVSNo |
| String | packSizeCode |
| String | packCount |
| String | ibCode |

## Validator `Requisition_Vld`
```java
public class Requisition_Vld {
    public static void chkSave(Requisition_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getRequisitionTypeCode().trim(), "Requisition Type", 100);
        DmShared.isValidText(mrTemp.getDate().trim(), "Requisition Date", 200);
        DmShared.isValidText(mrTemp.getSubCode().trim(), "Requisition By", 300);
        DmShared.isValidText(mrTemp.getDepartmentByCode().trim(), "Department By", 400);
        DmShared.isValidText(mrTemp.getDepartmentToCode().trim(), "Department To", 500);
        DmShared.isValidText(mrTemp.getSiteByCode(), "Site By Code", 0);
        DmShared.isValidText(mrTemp.getSiteToCode(), "Site To Code", 0);
        if (DmShared.xNull(mrTemp.getSiteByCode()).equalsIgnoreCase(DmShared.xNull(mrTemp.getSiteToCode()))) {
            if (DmShared.xNull(mrTemp.getDepartmentByCode()).equalsIgnoreCase(DmShared.xNull(mrTemp.getDepartmentToCode()))) {
                throw new DmException(400, "Department to and Department by cannot be same.");
            }
        }
        boolean flag = false;
        String itemCodeCsv = "";
        List<GrdRequisition_Mdl> listItemDetail = mrTemp.getGrdData();
        for (GrdRequisition_Mdl detail_Mdl : listItemDetail) {
            if (!DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()).trim().equalsIgnoreCase("")) {
                flag = true;
                if (!DmShared.dNull(detail_Mdl.getDate()).trim().equalsIgnoreCase("")) {
                    if (DmShared.convertDate(detail_Mdl.getDate()).getTime() < DmShared.convertDate(mrTemp.getDate()).getTime()) {
                        throw new DmException(600, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Req. Date should be greater than or equal to Requisition Date in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                    if (!DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()).equalsIgnoreCase("")) {
                        if (DmShared.xNull(itemCodeCsv).equalsIgnoreCase("")) {
                            itemCodeCsv = detail_Mdl.getSharedKeyFieldCode();
                        } else {
                            itemCodeCsv += "," + detail_Mdl.getSharedKeyFieldCode();
                        }
                    }
                }
            }
        }
        mrTemp.setItemCodeCsv(itemCodeCsv);
        if (!flag) {
            throw new DmException(600, "Requisition Detail is mandatory.");
        }
    }
}
```

## Server `ProductionRequisition_Server.java` (514 lines)
Helpers used: `DmServer.chkIfDocumentUsed`, `DmServer.convertDate`, `DmServer.getCodeViaManualCode`, `DmServer.getDocId`, `DmServer.getParamList`, `DmServer.getRecId`, `DmServer.getServerTimestamp`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.xNull`

### `saveOrderPlanRequisition`
`public static void saveOrderPlanRequisition(Requisition_Mdl mrTemp, String voucherPrefix, DmRSSPrivate rss, Connection cnTemp) throws Exception`


### `deleteOrderPlanRequisition`
`public static void deleteOrderPlanRequisition(String opDocId, Connection cnTemp) throws Exception`

- Errors: "Invalid Key"

### `saveWorkOrderRequisition`
`public static void saveWorkOrderRequisition(Requisition_Mdl mrTemp, String voucherPrefix, DmRSSPrivate rss, Connection cnTemp) throws Exception`

- SQL 1: `SELECT req.docId, req.departmentCode, req.departmentToCode, req.site_Code, req.site_Code_To, reqd.woDocId, reqd.worV_SNo from requisition req LEFT JOIN requisitionDetail reqd ON reqd.docId = req.docId WHERE req.docId <> :reqDocId AND req.departmentCode = :deptByCode AND req.departmentToCode = :deptToCode AND req.site_Code = :siteByCode AND req.site_Code_To = :siteToCode AND reqd.woDocId = :woDocId AND reqd.worV_SNo = :worV_SNo`
- Params: `:reqDocId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:deptByCode` ← `DmShared.xNull(mrTemp.getDepartmentByCode())`; `:deptToCode` ← `DmShared.xNull(mrTemp.getDepartmentToCode())`; `:siteByCode` ← `DmShared.xNull(mrTemp.getSiteByCode())`; `:siteToCode` ← `DmShared.xNull(mrTemp.getSiteToCode())`; `:woDocId` ← `DmShared.iNull(mrTemp.getGrdData().get(0).getWoDocId()).toString()`; `:worV_SNo` ← `DmShared.iNull(mrTemp.getGrdData().get(0).getWorVSNo()).toString()`
- Errors: "Record already exists with this department and site!"

### `deleteWorkOrderRequisition`
`public static void deleteWorkOrderRequisition(String reqDocId, Connection cnTemp) throws Exception`

- Errors: "Invalid Key"

### `saveRequisition`
`private static void saveRequisition(Requisition_Mdl mrTemp, String voucherPrefix, DmRSSPrivate rss, Connection cnTemp) throws Exception`

- SQL 1: `SELECT vt.v_type, vt.description FROM voucher_Type vt WHERE vt.category = :vCat`
- SQL 2: `SELECT u_EntDt_LatestLine FROM requisition WHERE docId = :docId`
- SQL 3: `INSERT INTO requisition (docId, v_Type,v_No, recId, v_Date, v_Prefix, departmentCode, requestedByCode, site_Code,wtaCode, preparedBy, preparedDt,u_EntDt_LatestLine,departmentToCode,priorityTypeCode,remark,site_Code_to,recIdPrefix)VALUES (:docid, :v_type, :v_No, :recid, :v_date, :v_prefix, :departmentcode, :requestedbycode, :site_code,:wtaCode ,:preparedby, :uEntDt,:uEntDt,:departmentToCode,:priorityTypeCode,:remark,:site_Code_to,:recIdPrefix)`
- SQL 4: `UPDATE requisition SET departmentCode = :departmentcode, departmentToCode = :departmentToCode, requestedByCode = :requestedbycode, priorityTypeCode = :priorityTypeCode, site_Code_to= :site_Code_to, remark = :remark, v_date=:v_date, u_Name = :u_name, u_EntDt=:uEntDt, u_EntDt_LatestLine=:uEntDt where docid=:docid`
- SQL 5: `Delete From requisitiondetail where docid= :docid and (isStatus) NOT IN('1','2')`
- SQL 6: `INSERT INTO requisitionDetail (docId, v_SNo, itemCode, ibCode, reqQty, reqDate, remark ,opDocId, opV_SNo, isStatus, packSizeCode,packCount,woDocId, worV_SNo )VALUES (:docid, :v_sno, :itemcode, :ibCode, :reqqty, :reqdate, :remark , :opDocId, :opV_SNo, :isStatus, :packSizeCode,:packCount, :woDocId, :worV_SNo )`
- SQL 7: `UPDATE requisitionDetail SET v_SNo = :v_SNo WHERE docid = :docId AND v_SNo = :previous_VSno`
- Params: `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:vCat` ← `new DmVoucherCategory().PRODUCTION_REQUISITION`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:departmentBy` ← `DmShared.iNull(mrTemp.getDepartmentByCode()).toString()`; `:departmentTo` ← `DmShared.iNull(mrTemp.getDepartmentToCode()).toString()`; `:restricted` ← `DmEnum.ItemDepartmentNature.RESTRICTED.getCode()`; `:allowed` ← `DmEnum.ItemDepartmentNature.ALLOWED.getCode()`; `:uEntDt` ← `timestamp`; `:docid` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:v_type` ← `DmShared.xNull(voucherType)`; `:v_No` ← `DmShared.iNull(vNo).toString()`; `:recid` ← `DmShared.xNull(mrTemp.getRecid().getRecIdValue())`; `:wtaCode` ← `DmShared.foriegnKey(mrTemp.getWtaCode())`; `:v_date` ← `DmServer.convertDate(mrTemp.getDate())`; `:v_prefix` ← `DmShared.xNull(voucherPrefix)`; `:departmentcode` ← `DmShared.foriegnKey(mrTemp.getDepartmentByCode())`; `:departmentToCode` ← `DmShared.foriegnKey(mrTemp.getDepartmentToCode())`; `:requestedbycode` ← `DmShared.foriegnKey(mrTemp.getSubCode())`; `:priorityTypeCode` ← `DmShared.foriegnKey(mrTemp.getPriorityTypeCode())`; `:site_code` ← `DmShared.foriegnKey(mrTemp.getSiteByCode())`; `:site_Code_to` ← `DmShared.foriegnKey(mrTemp.getSiteToCode())`; `:preparedby` ← `DmShared.xNull(userName)`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:recIdPrefix` ← `DmShared.xNull(mrTemp.getRecid().getPrefix())`; `:u_name` ← `DmShared.xNull(userName)`; `:uEntDt` ← `timestamp`; `:departmentcode` ← `DmShared.foriegnKey(mrTemp.getDepartmentByCode())`; `:departmentToCode` ← `DmShared.foriegnKey(mrTemp.getDepartmentToCode())`; `:requestedbycode` ← `DmShared.foriegnKey(mrTemp.getSubCode())`; `:priorityTypeCode` ← `DmShared.foriegnKey(mrTemp.getPriorityTypeCode())`; `:docid` ← `mrTemp.getSharedKeyFieldCode()`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:site_Code_to` ← `DmShared.foriegnKey(mrTemp.getSiteToCode())`; `:v_date` ← `DmShared.xNull(mrTemp.getDate())`; `:docid` ← `mrTemp.getSharedKeyFieldCode()`; `:docid` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:v_SNo` ← `DmShared.iNull(detailMdl.getvSNo()).toString()`; `:itemcode` ← `DmShared.foriegnKey(detailMdl.getSharedKeyFieldCode())`; `:reqqty` ← `DmServer.vNull(detailMdl.getQty())`; `:reqdate` ← `DmServer.convertDate(detailMdl.getDate())`; `:remark` ← `DmShared.xNull(detailMdl.getRemark())`; `:ibCode` ← `DmShared.foriegnKey(detailMdl.getIbCode())`; `:opDocId` ← `DmShared.foriegnKey(detailMdl.getOpDocId())`; `:opV_SNo` ← `DmShared.foriegnKey(detailMdl.getOpVSNo())`; `:woDocId` ← `DmShared.foriegnKey(detailMdl.getWoDocId())`; `:worV_SNo` ← `DmShared.foriegnKey(detailMdl.getWorVSNo())`; `:packSizeCode` ← `DmShared.foriegnKey(detailMdl.getPackSizeCode())`; `:packCount` ← `DmServer.vNull(detailMdl.getPackCount())`; `:isStatus` ← `"0"`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:v_SNo` ← `DmShared.iNull(detailMdl.getvSNo()).intValue()`; `:previous_VSno` ← `previous_VSno`
- Errors: "Define voucher type for production Requisition " | " Production requisition record is approved. You can't edit this record." | "Record has been changed, can't update record, refresh record then update" | "itemDeptValidate" | "Invalid key."
- Other calls: `ALLOWED.getCode`, `RESTRICTED.getCode`, `Requisition_Vld.chkSave`, `WalkThrough_Server.applyWalkThrough`

### `deleteRequisition`
`private static void deleteRequisition(String reqDocId, String opDocId, Connection cnTemp) throws Exception`

- SQL 1: `SELECT COUNT(*) cnt FROM requisition req LEFT JOIN requisitionDetail reqd ON reqd.docId = req.docId WHERE ISNULL(req.approvedBy, '') <> '' {…}`
- SQL 2: `SELECT COUNT(*) cnt FROM requisitionDetail reqd WHERE ((ISNULL(reqd.indentQty, 0) + ISNULL(reqd.issueQty, 0)) > 0 OR reqd.isStatus = :status) {…}`
- SQL 3: `SELECT DISTINCT reqd.docId FROM requisitionDetail reqd WHERE :one = :one {…}`
- SQL 4: `select wtaCode,approvedBy FROM requisition WHERE docId = :docId`
- SQL 5: `Delete From requisitiondetail Where docid= :code`
- SQL 6: `Delete From requisition Where docid= :code`
- Params: `:opDocId` ← `DmShared.iNull(opDocId).toString()`; `:reqDocId` ← `DmShared.iNull(reqDocId).toString()`; `:opDocId` ← `DmShared.iNull(opDocId).toString()`; `:reqDocId` ← `DmShared.iNull(reqDocId).toString()`; `:status` ← `"2"`; `:one` ← `1`; `:opDocId` ← `DmShared.iNull(opDocId).toString()`; `:reqDocId` ← `DmShared.iNull(reqDocId).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:code` ← `strKey`; `:code` ← `strKey`
- Errors: "Invalid Key" | "Requistion approved. You can't edit / delete this record." | "Transaction exist. You can't edit / delete this record." | "Requistion is approved. You can't delete this record."
- Other calls: `ModifyReason_Server.delete`, `WalkThrough_Server.deleteWalkThrough`

### `getWalkThroughFor`
`private static WalkThroughDefault_Mdl getWalkThroughFor(Requisition_Mdl mrTemp, DmRSSPrivate rss)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `paperbiz.selfToolkit.modifyReason.server`, `paperbiz.selfToolkit.modifyReason.shared`, `paperbiz.selfToolkit.productionRequisition.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
