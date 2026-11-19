# pb-self-toolkit / creditDebitNoteDue

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/creditDebitNoteDue/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: creditDebitNoteDue, creditDebitNoteDueDetail
- Update tables: creditDebitNoteDue, pOrder
- Delete cascade (in order): creditDebitNoteDueDetail, creditDebitNoteDue
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Ref. Type (Text); Entry Date (Text); Diff. Type (Text); Party Name (Text); rule: Items are mandatory.
- Lookups/suggestions: getTxtHVoucher, getTxtHReferenceType, getTxtHDIfferenceType, getTxtHSupplier
- CSV export: no

## Files
| file | lines |
|---|---|
| client/CreditDebitNoteDue.java | 961 |
| client/CreditDebitNoteDuePurchase.java | 23 |
| client/CreditDebitNoteDueSale.java | 23 |
| client/CreditDebitNoteDue_Dsg.java | 437 |
| client/GridEditPopup.java | 143 |
| client/GridEditPopup_Dsg.java | 153 |
| server/CreditDebitNoteDue_SrvImpl.java | 1383 |
| shared/CreditDebitNoteDue_Mdl.java | 199 |
| shared/CreditDebitNoteDue_Srv.java | 38 |
| shared/CreditDebitNoteDue_SrvAsync.java | 38 |
| shared/CreditDebitNoteDue_Vld.java | 31 |
| shared/DefaultValue_Mdl.java | 114 |
| shared/Find_Mdl.java | 50 |
| shared/GrdItemDetail_Mdl.java | 133 |
| shared/GridEditPopup_Mdl.java | 50 |
| shared/TxtHSupplier_Mdl.java | 104 |
| shared/TxtHVoucherType_Mdl.java | 41 |

## UI labels / columns (client)
`Quantity`, `Rate`, `Amount`, `Voucher Type`, `Entry Date`, `Reference Type`, `Entry No.`, `Party Name`, `Difference Type`, `From Date`, `To Date`

## Models (shared DTOs)
### GrdItemDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | docId |
| String | vSno |
| String | invoiceDocId |
| String | invoiceRecId |
| String | invoiceV_SNo |
| String | entryDate |
| String | partyBillNo |
| String | partyBillDate |
| String | itemCode |
| String | itemName |
| String | qty |
| String | rate |
| String | amount |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | newVoucherTypeCode |
| String | newVoucherTypeName |
| String | newVoucherCategory |
| String | VoucherTypeCode |
| String | VoucherTypeName |
| String | refTypeCode |
| String | refTypeName |
| String | currentDate |
| String | currentTime |
| String | voucherCategory |
| boolean | isContinuousNewMode |

### TxtHSupplier_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | stateCode |
| String | stateName |
| String | cityCode |
| String | cityName |
| String | pinCode |
| String | manualCode |
| String | partyType |
| String | gstin |
| String | gstinDate |
| String | dueDays |

### GridEditPopup_Mdl
| type | field |
|---|---|
| String | mode |
| String | qty |
| String | rate |
| String | amount |

### TxtHVoucherType_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | voucherCategory |
| String | balancingAc |
| String | guiTemplateCode |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | entryType |
| String | entryDate |
| String | diffeType |
| String | approval |

### CreditDebitNoteDue_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | entryDate |
| String | voucherTypeCode |
| String | voucherTypeName |
| String | referenceCode |
| String | referenceName |
| String | diffeTypeCode |
| String | diffeTypeName |
| String | partyCode |
| String | partyName |
| String | fromDate |
| String | toDate |
| RecId | recid |
| String | vCatagory |
| String | mode |
| String | u_EntDt_LatestLine |
| String | approvedBy |
| String | v_No |
| String | wtaCode |
| List<GrdItemDetail_Mdl> | grdItemDetails |
| ModifyReason_Mdl | modifyReasonMdl |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator | UserPermission userPermission | DmResult<List<KeyFieldExtended>> |
| getDefaultValues | String menuText | DmResult<DefaultValue_Mdl> |
| getMoveRec | String strKey | DmResult<CreditDebitNoteDue_Mdl> |
| saveRecord | CreditDebitNoteDue_Mdl mrTemp, String voucherPrefix, boolean isApprove | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl reason_Mdl | DmResult<String> |
| getFind | UserPermission userPermission, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| setApproval | String approvedBy, boolean isApprove, String docId | DmResult<String> |
| fillGridDetails | CreditDebitNoteDue_Mdl noteDue_Mdl | DmResult<List<GrdItemDetail_Mdl>> |
| getTxtHVoucher | final String vCat, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucherType_Mdl>> |
| getTxtHReferenceType | final String vCat, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSupplier | String voucherCategory, boolean flagFilterByVoucherCategory, String v_Type, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHSupplier_Mdl>> |
| getTxtHDIfferenceType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `CreditDebitNoteDue_Vld`
```java
public class CreditDebitNoteDue_Vld {
    public static void chkSave(CreditDebitNoteDue_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getReferenceCode(), "Ref. Type", 100);
        DmShared.isValidText(mrTemp.getEntryDate(), "Entry Date", 200);
        DmShared.isValidText(mrTemp.getDiffeTypeCode(), "Diff. Type", 300);
        DmShared.isValidText(mrTemp.getPartyCode(), "Party Name", 400);
        boolean flag = false;
        List<GrdItemDetail_Mdl> listItemDetail = mrTemp.getGrdItemDetails();
        for (GrdItemDetail_Mdl detail_Mdl : listItemDetail) {
            if (!DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()).equalsIgnoreCase("")) {
                flag = true;
            }
        }
        if (!flag) {
            throw new DmException(500, "Items are mandatory.");
        }
    }
}
```

## Server `CreditDebitNoteDue_SrvImpl.java` (1383 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.checkFinancialYearDate`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getLocalIPAddresses`, `DmServer.getRecId`, `DmServer.getServerTimestamp`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserViewCondition`, `DmServer.getUserWithDateTime`, `DmServer.setSsnOldData`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.vNull`, `DmShared.xNull`

### `getTxtHVoucher`
`public DmResultPageing<List<TxtHVoucherType_Mdl>> getTxtHVoucher(final String vCat, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type AS code , vt.description AS name , vt.category , vt.guiTemplateCode , vt.balancingAc FROM voucher_Type vt WHERE vt.category IN ( {…} ) AND vt.isActive = 1`

### `getTxtHReferenceType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHReferenceType(final String vCat, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type AS code , vt.description AS name , vt.category FROM voucher_Type vt WHERE 1 = 1 {…} AND vt.isActive = 1`

### `getTxtHDIfferenceType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDIfferenceType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT dt.code, dt.manualCode, dt.name FROM differenceType dt`

### `getTxtHSupplier`
`public DmResultPageing<List<TxtHSupplier_Mdl>> getTxtHSupplier(String voucherCategory, boolean flagFilterByVoucherCategory, String v_Type, DmHelper helper, boolean blnToEndOfFile)`


### `getSupplier`
`private List<TxtHSupplier_Mdl> getSupplier(String supplierCode, String voucherCategory, boolean flagFilterByVoucherCategory, String v_Type, DmHelper helper, boolean blnToEndOfFile, Connection cnTemp) throws Exception`

- SQL 1: `SELECT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_type = acf.v_Type WHERE vt.category IN ( {…} )`
- SQL 2: `SELECT acf.Nature FROM AcFilteration acf WHERE acf.v_Type = :v_Type`
- SQL 3: `SELECT sg.subCode AS code, sg.name, sg.DueDays, sg.manualCode ,sg.cityCode ,c.cityName, c.stateCode,sm.name as stateName,sg.pin as pinCode, sg.partyType, sg.gstin, sg.gstinDate FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN stateMast sm ON sm.code = c.stateCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( {…} ) AND sg.isActive = 1 AND sgsl.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Type` ← `DmShared.iNull(v_Type).toString()`; `:supplierCode` ← `DmShared.xNull(supplierCode)`

### `setApproval`
`public DmResult<String> setApproval(String approvedBy, boolean isApprove, String docId)`

- Other calls: `STApprovals.creditDeditNoteDue`

### `walkThrough`
`public DmResult<String> walkThrough(WalkThrough_Mdl walkThrough)`

- Other calls: `STApprovals.creditDeditNoteDue`, `WalkThrough_Server.walkThrough`

### `getWalkThroughFor`
`private WalkThroughDefault_Mdl getWalkThroughFor(CreditDebitNoteDue_Mdl mrTemp)`


### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(UserPermission userPermission)`

- SQL 1: `SELECT docid FROM creditDebitNoteDue cdn LEFT JOIN voucher_Type vt ON vt.v_Type=cdn.v_Type WHERE vt.category IN ( {…} ) AND cdn.site_Code = :site_Code AND cdn.v_prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getMoveRec`
`public DmResult<CreditDebitNoteDue_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT cdn.docId, cdn.v_Type, vt.description , cdn.recId, vt.category AS vCategory , cdn.refV_Type, vtr.description AS refVourcher , cdn.v_Date, cdn.diffType, dt.name , cdn.fromDate, cdn.toDate , cdn.preparedBy,cdn.preparedDt , cdn.preparedByIP, cdn.u_name, cdn.u_EntDt , cdn.u_EntDt_LatestLine,cdn.wtaCode,cdn.approvedBy , cdn.v_No,cdn.recIdPrefix ,cdn.approvalDateTime FROM creditDebitNoteDue cdn LEFT JOIN voucher_Type vt ON vt.v_Type = cdn.v_Type LEFT JOIN voucher_Type vtr ON vtr.v_Type = cdn.refV_Type LEFT JOIN differenceType dt ON dt.code = cdn.diffType WHERE cdn.docId = :docId`
- SQL 2: `SELECT cdnd.docId, cdnd.v_SNo , cdnd.invoiceDocId, pb.recid AS invoiceRecId , cdnd.partyCode, sg.name AS partyName , cdnd.partyBillNo, cdnd.partyBillDate , cdnd.itemCode, im.name AS itemName , cdnd.qty, cdnd.rate, cdnd.amount , gt.qtyDiff, gt.rateDiff, gt.value FROM creditDebitNoteDueDetail cdnd LEFT JOIN pBill pb ON pb.docId = cdnd.invoiceDocId LEFT JOIN gstTrans gt ON gt.docId = pb.docId LEFT JOIN subGroup sg ON sg.subCode = cdnd.partyCode LEFT JOIN itemMast im ON im.code = cdnd.itemCode WHERE cdnd.docId=:docId`
- Params: `:docId` ← `strKey`; `:docId` ← `strKey`

### `saveRecord`
`public DmResult<String> saveRecord(CreditDebitNoteDue_Mdl mrTemp, String voucherPrefix, boolean isApprove)`

- SQL 1: `SELECT u_EntDt_LatestLine FROM creditDebitNoteDue WHERE docId = :docId`
- SQL 2: `INSERT INTO creditDebitNoteDue (docId, v_No, v_Type, recId , v_Prefix, recIdPrefix, v_Date, refV_Type, diffType, fromDate, toDate , wtaCode, u_EntDt, u_Name, preparedBy, preparedDt , site_Code, transferDt, preparedByIP ) VALUES (:docId, :v_No, :v_Type, :recId , :v_Prefix, :recIdPrefix, :v_Date, :refV_Type, :diffType, :fromDate, :toDate , :wtaCode, :u_EntDt, :u_Name, :preparedBy, :preparedDt , :site_Code, :transferDt, :preparedByIP )`
- SQL 3: `UPDATE creditDebitNoteDue SET refV_Type = :refV_Type , diffType = :diffType , fromDate = :fromDate , toDate = :toDate , u_EntDt = :uEntDt , u_EntDt_LatestLine= :uEntDt , u_name = :u_name, recId = :recId, v_No = :v_No, v_Date = :v_Date WHERE docId = :docId ;`
- SQL 4: `DELETE FROM creditDebitNoteDueDetail WHERE docId = :docId`
- SQL 5: `INSERT INTO creditDebitNoteDueDetail (docId ,v_SNo ,partyCode ,invoiceDocId , partyBillNo ,partyBillDate ,itemCOde ,qty ,rate ,amount ) VALUES (:docId , :v_SNo , :partyCode , :invoiceDocId , :partyBillNo , :partyBillDate , :itemCode , :qty , :rate , :amount)`
- Params: `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `mrTemp.getSharedKeyFieldCode()`; `:v_No` ← `DmShared.iNull(v_No).toString()`; `:v_Type` ← `DmShared.xNull(mrTemp.getVoucherTypeCode())`; `:recId` ← `mrTemp.getRecid().getRecIdValue()`; `:v_Prefix` ← `voucherPrefix`; `:recIdPrefix` ← `DmShared.xNull(mrTemp.getRecid().getPrefix())`; `:v_Date` ← `DmServer.convertDate(mrTemp.getEntryDate())`; `:refV_Type` ← `DmShared.xNull(mrTemp.getReferenceCode())`; `:diffType` ← `DmShared.xNull(mrTemp.getDiffeTypeCode())`; `:fromDate` ← `DmServer.convertDate(mrTemp.getFromDate())`; `:toDate` ← `DmServer.convertDate(mrTemp.getToDate())`; `:wtaCode` ← `DmShared.foriegnKey(mrTemp.getWtaCode())`; `:u_EntDt` ← `timestamp`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `timestamp`; `:site_code` ← `DmServer.getSsnSiteCode(this)`; `:transferDt` ← `null`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:refV_Type` ← `DmShared.xNull(mrTemp.getReferenceCode())`; `:diffType` ← `DmShared.xNull(mrTemp.getDiffeTypeCode())`; `:fromDate` ← `DmServer.convertDate(mrTemp.getFromDate())`; `:toDate` ← `DmServer.convertDate(mrTemp.getToDate())`; `:u_name` ← `DmServer.getSsnUserName(this)`; `:uEntDt` ← `timestamp`; `:v_No` ← `DmShared.iNull(mrTemp.getV_No()).toString()`; `:recId` ← `mrTemp.getRecid().getRecIdValue()`; `:v_Date` ← `DmServer.convertDate(mrTemp.getEntryDate())`; `:docId` ← `mrTemp.getSharedKeyFieldCode()`; `:docId` ← `mrTemp.getSharedKeyFieldCode()`; `:docId` ← `mrTemp.getSharedKeyFieldCode()`; `:v_SNo` ← `DmShared.iNull(detail.getvSno()).intValue()`; `:partyCode` ← `DmShared.foriegnKey(detail.getSharedKeyFieldCode())`; `:invoiceDocId` ← `DmShared.foriegnKey(detail.getInvoiceDocId())`; `:partyBillNo` ← `DmShared.foriegnKey(detail.getPartyBillNo())`; `:partyBillDate` ← `DmShared.foriegnKey(detail.getPartyBillDate())`; `:itemCode` ← `DmShared.foriegnKey(detail.getItemCode())`; `:qty` ← `DmShared.xNull(detail.getQty())`; `:rate` ← `DmShared.xNull(detail.getRate())`; `:amount` ← `DmShared.xNull(detail.getAmount())`
- Errors: "Record is approved. You can not edit this record." | "Invalid key." | "Record has been changed, can't update record, refresh record then update"
- Other calls: `CreditDebitNoteDue_Vld.chkSave`, `STApprovals.creditDeditNoteDue`, `WalkThrough_Server.applyWalkThrough`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl reason_Mdl)`

- SQL 1: `SELECT v_date from creditDebitNoteDue where docId = :docId`
- SQL 2: `select approvedBy,wtaCode FROM creditDebitNoteDue WHERE docId = :docId`
- SQL 3: `Delete From creditDebitNoteDueDetail Where docId= :docId`
- SQL 4: `Delete From creditDebitNoteDue Where docId= :docId`
- Params: `:docId` ← `DmShared.xNull(strKey)`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `strKey`; `:docId` ← `strKey`
- Errors: "Record is approved. You can't delete this record."
- Other calls: `WalkThrough_Server.deleteWalkThrough`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT cdn.docId, cdn.v_Type, vt.description, cdn.recId , cdn.v_Date, cdn.diffType, dt.name , cdn.v_No,cdn.recIdPrefix , cdn.approvedBy, cdn.approvalDateTime FROM creditDebitNoteDue cdn LEFT JOIN voucher_Type vt ON vt.v_Type = cdn.v_Type LEFT JOIN differenceType dt ON dt.code = cdn.diffType WHERE vt.category IN ( {…} ) AND cdn.site_Code = :site_Code AND cdn.v_prefix=:v_prefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_prefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues(String menuText)`

- SQL 1: `SELECT TOP 1 vt.v_Type AS code , vt.description AS name , vt.category FROM voucher_Type vt WHERE vt.category IN ( {…} ) AND isActive = 1`

### `fillGridDetails`
`public DmResult<List<GrdItemDetail_Mdl>> fillGridDetails(CreditDebitNoteDue_Mdl noteDue_Mdl)`

- SQL 1: `SELECT gth.docId, gth.v_Date, gth.recId , gt.v_Sno , gth.v_Type, vt.description AS voucherName , gth.partyBillNo, gth.partyBillDt , gth.partyCode, sg.name AS partyName , gt.itemCode, im.name AS itemName , gt.rateDiff, gt.qtyDiff, gt.qty, gt.rate, gt.value , gt.otherDiff, gt.OverAllDiff, gt.itemGrossAmount, pb.differenceType FROM gstTransHead gth LEFT JOIN gstTrans gt ON gt.docId = gth.docId LEFT JOIN pBillDetail pbd ON pbd.docId = gt.docId AND pbd.v_SNo = gt.v_Sno LEFT JOIN pBill pb ON pb.docId = pbd.docId LEFT JOIN voucher_Type vt ON vt.v_Type = gt.v_Type LEFT JOIN subGroup sg ON sg.subCode = gt.partyCode LEFT JOIN itemMast im ON im.code = gt.itemCode WHERE gt.v_Type = :v_Type {…} AND gt.partyCode = :partyCode AND gt.v_Date BETWEEN :fromDate AND :toDate AND pbd.isStatus = 0 AND pb.differenceType = :differenceType`
- SQL 2: `SELECT gth.docId, gth.v_Date, gth.recId , gt.v_Sno , gth.v_Type, vt.description AS voucherName , gth.partyBillNo, gth.partyBillDt , gth.partyCode, sg.name AS partyName , gt.itemCode, im.name AS itemName , gt.rateDiff, gt.qtyDiff, gt.qty, gt.rate, gt.value , gt.otherDiff, gt.OverAllDiff, gt.itemGrossAmount FROM gstTransHead gth LEFT JOIN gstTrans gt ON gt.docId = gth.docId LEFT JOIN pBillDetail pbd ON pbd.docId = gt.docId AND pbd.v_SNo = gt.v_Sno LEFT JOIN voucher_Type vt ON vt.v_Type = gt.v_Type LEFT JOIN subGroup sg ON sg.subCode = gt.partyCode LEFT JOIN itemMast im ON im.code = gt.itemCode WHERE gt.v_Type = :v_Type {…} AND gt.partyCode = :partyCode AND gt.v_Date BETWEEN :fromDate AND :toDate AND pbd.isStatus = 0`
- Params: `:v_Type` ← `DmShared.xNull(noteDue_Mdl.getReferenceCode())`; `:partyCode` ← `DmShared.xNull(noteDue_Mdl.getPartyCode())`; `:fromDate` ← `DmShared.xNull(noteDue_Mdl.getFromDate())`; `:toDate` ← `DmShared.xNull(noteDue_Mdl.getToDate())`; `:differenceType` ← `DmShared.xNull(noteDue_Mdl.getDiffeTypeCode())`; `:v_Type` ← `DmShared.xNull(noteDue_Mdl.getReferenceCode())`; `:partyCode` ← `DmShared.xNull(noteDue_Mdl.getPartyCode())`; `:fromDate` ← `DmShared.xNull(noteDue_Mdl.getFromDate())`; `:toDate` ← `DmShared.xNull(noteDue_Mdl.getToDate())`; `:toDate` ← `DmShared.xNull(noteDue_Mdl.getToDate())`

### `managePBillStatus`
`private void managePBillStatus(String docId, String v_SNo, boolean isDelete, Connection cnTemp) throws Exception`

- SQL 1: `UPDATE pOrder SET u_EntDt_LatestLine = :uEntDt WHERE docId IN ( {…} )`
- Params: `:docId` ← `DmShared.iNull(docId).toString()`; `:v_SNo` ← `DmShared.iNull(v_SNo).toString()`; `:isStatus` ← `DmShared.iNull(2).toString()`; `:isStatus` ← `DmShared.iNull(0).toString()`; `:uEntDt` ← `DmServer.getServerTimestamp()`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `dataman.libraryMenu.self.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.creditDebitNoteDue.shared`, `paperbiz.selfToolkit.stStatic.server`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Record is approved. You can not edit this record." |
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (DmShared.iNull(mrTemp.getU_EntDt_LatestLine()).longValue() != rsTemp.getTimestamp("u_EntDt_LatestLine").getTime())` | "Record has been changed, can't update record, refresh record then update" |
| deleteRecord | `if (!DmShared.xNull(rsTemp.getString("approvedBy")).equalsIgnoreCase(""))` | "Record is approved. You can't delete this record." |

## Notes (hand-written)
_No hand-written notes yet._
