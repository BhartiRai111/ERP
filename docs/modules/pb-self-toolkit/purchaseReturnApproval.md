# pb-self-toolkit / purchaseReturnApproval

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/purchaseReturnApproval/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHStatus, getTxtHBillNo, getTxtHSupplier
- CSV export: no

## Files
| file | lines |
|---|---|
| client/PurchaseReturnApproval.java | 351 |
| client/PurchaseReturnApprovalCard.java | 144 |
| client/PurchaseReturnApproval_Dsg.java | 267 |
| client/Setting.java | 253 |
| client/Setting_Dsg.java | 243 |
| server/PurchaseReturnApproval_SrvImpl.java | 470 |
| shared/DefaultValue_Mdl.java | 22 |
| shared/GrdPurchaseReturnApproval_Mdl.java | 208 |
| shared/PurchaseReturnApproval_Srv.java | 26 |
| shared/PurchaseReturnApproval_SrvAsync.java | 28 |
| shared/Setting_Mdl.java | 165 |
| shared/TxtHBillNo_Mdl.java | 59 |
| shared/TxtHGrnNo_Mdl.java | 32 |
| shared/TxtHSupplier_Mdl.java | 122 |

## UI labels / columns (client)
`From Date`, `To Date`, `Status`, `Supplier`, `Bill No.`

## Models (shared DTOs)
### TxtHBillNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | date |
| String | type |
| String | partyBillNo |
| String | partyBillDate |
| String | supplierName |

### TxtHGrnNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | challanDate |
| String | challanNo |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | currentDate |

### Setting_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | fromDate |
| String | toDate |
| String | supplierName |
| String | supplierCode |
| String | StatusCode |
| String | StatusName |
| String | billnoDocId |
| String | billnoRecId |
| String | vSNo |
| String | siteCode |
| String | siteName |
| String | approvedBy |
| String | isStatus |
| String | approvedDateTime |
| String | wtaCode |
| List<GrdPurchaseReturnApproval_Mdl> | grdPurchaseBillApproval_Mdl |

### TxtHSupplier_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityCode |
| String | cityName |
| String | gstin |
| String | gstinDate |
| String | aadhaarNo |
| String | panNo |
| String | partyType |
| String | stateCode |
| String | stateName |
| String | stateNumericCode |
| String | isUnionTeritory |

### GrdPurchaseReturnApproval_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | wtaCode |
| String | entryNo |
| String | entryNoCode |
| String | entryDate |
| String | approvedBy |
| String | approval |
| String | returnCatCode |
| String | returnCatName |
| String | supplierName |
| String | currentUser |
| String | netAmount |
| String | gstReasonCode |
| String | gstReasonName |
| String | partyBillNo |
| String | partyBillDate |
| String | isChecked |
| String | isView |
| String | isStatus |
| String | bifurcateService |
| String | soReffNo |
| String | soReffDate |

## RPC interface
| method | params | returns |
|---|---|---|
| getFillRecords | Setting_Mdl param, DmHelper helper, boolean blnToEndOfFile, String vCat | DmResultPageing<List<GrdPurchaseReturnApproval_Mdl>> |
| getTxtHBillNo | String vCat,DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHBillNo_Mdl>> |
| getTxtHStatus | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSupplier | boolean flagFilterByVoucherCategory, String v_Type, DmHelper pageing, boolean blnToEndOfFile, String vCat | DmResultPageing<List<TxtHSupplier_Mdl>> |
| setApproval | String approvedBy, boolean isApprove, String docId | DmResult<String> |
| getCardDetail | String vCat | DmResult<CardDetail_Mdl> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |

## Server `PurchaseReturnApproval_SrvImpl.java` (470 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.xNull`

### `getFillRecords`
`public DmResultPageing<List<GrdPurchaseReturnApproval_Mdl>> getFillRecords(Setting_Mdl param, DmHelper helper, boolean blnToEndOfFile, String vCat)`

- SQL 1: `SELECT pb.docId,pb.recId ,pb.v_date , pb.partyBillNo , pb.partyBillDate, sgv.name AS suppName , vt.short_Name ,pb.wtaCode,gsm.name as reason,pb.oh_Amt_Net , concat(pb.approvedBy, ' ' ,format(pb.approvalDateTime, 'dd-MMM-yyyy HH.mm')) as approval , pb.approvedBy ,case when isNull(pb.isBifurcateService,'') = 0 then 'No' else 'Yes' end as bifurcateService FROM pBill pb LEFT JOIN voucher_Type vt ON pb.v_Type = vt.v_Type LEFT JOIN subGroup sgv ON pb.subCode = sgv.subCode left join gstReasonMast gsm on gsm.code = pb.gstReasonCode {…} WHERE vt.category IN ( {…} ) {…}`
- Params: `:fromv_Date` ← `DmServer.convertDate(param.getFromDate())`; `:tov_Date` ← `DmServer.convertDate(param.getToDate())`

### `getTxtHStatus`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHStatus(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHBillNo`
`public DmResultPageing<List<TxtHBillNo_Mdl>> getTxtHBillNo(String vCat, DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT pb.docId, pb.recId, pb.v_Date, vt.category, pb.partyBillNo , pb.partyBillDate , sg.name AS suppName FROM pBill AS pb LEFT JOIN subGroup sg ON pb.subCode = sg.subCode LEFT JOIN voucher_Type as vt on vt.v_Type = pb.v_Type WHERE vt.category IN ( {…} ) and pb.site_Code = ' {…} '`

### `setApproval`
`public DmResult<String> setApproval(String approvedBy, boolean isApprove, String docId)`

- Other calls: `STApprovals.generalPurchaseReturnApproval`

### `walkThrough`
`public DmResult<String> walkThrough(WalkThrough_Mdl walkThrough)`

- Other calls: `STApprovals.generalPurchaseReturnApproval`, `WalkThrough_Server.walkThrough`

### `getCardDetail`
`public DmResult<CardDetail_Mdl> getCardDetail(String vCat)`

- SQL 1: `Select count(pb.docId) as cnt, sum(case when DATEDIFF(day, pb.v_Date, CAST(getdate() AS date)) <= {…} then 1 else 0 end) as low, sum(case when DATEDIFF(day, pb.v_Date, CAST(getdate() AS date)) > {…} and DATEDIFF(day, pb.v_Date, CAST(getdate() AS date)) <= {…} then 1 else 0 end) as medium, sum(case when DATEDIFF(day, pb.v_Date, CAST(getdate() AS date)) >= {…} then 1 else 0 end) as high from pBill as pb left join voucher_type as vt on pb.v_Type = vt.v_Type left join( select pbd.docId from pBillDetail as pbd where pbd.isStatus = 0 group by pbd.docId ) as vPbd on vPbd.docId = pb.docId LEFT JOIN walkThroughFootPrint wtfp on wtfp.wtaCode = pb.wtaCode and wtfp.isStatus = 0 where vPbd.docId is not null and isNull(pb.approvedBy,'') = '' and vt.category IN ( {…} ) AND isNull(wtfp.linkedUser,' {…} ,') like '% {…} ,%' and pb.site_Code = :site_Code AND isNull(pb.cancelledBy,'') = ''`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `HIGH_PRIORITYDAYS.getDays`, `LOW_PRIORITYDAYS.getDays`, `MEDIUM_PRIORITYDAYS.getDays`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`


### `getTxtHSupplier`
`public DmResultPageing<List<TxtHSupplier_Mdl>> getTxtHSupplier(boolean flagFilterByVoucherCategory, String v_Type, DmHelper helper, boolean blnToEndOfFile, String vCat)`

- SQL 1: `SELECT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_type = acf.v_Type WHERE vt.category IN ( {…} )`
- SQL 2: `SELECT acf.Nature FROM AcFilteration acf WHERE acf.v_Type = :v_Type`
- SQL 3: `SELECT sg.subCode AS code, sg.name, sg.manualCode, c.cityName, c.stateCode, sg.partyType, sg.gstin, sg.gstinDate FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( {…} ) AND sg.isActive = 1 AND sgsl.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Type` ← `DmShared.iNull(v_Type).toString()`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.dashboardCardStrip.client`, `paperbiz.selfToolkit.dashboardCardStrip.shared`, `paperbiz.selfToolkit.purchaseReturnApproval.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
