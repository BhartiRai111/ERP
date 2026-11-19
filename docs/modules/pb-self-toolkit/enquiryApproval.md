# pb-self-toolkit / enquiryApproval

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/enquiryApproval/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: enquiryVendorDetail, enquiry
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHStatus, getTxtHEnquiryNo, getTxtHSupplier
- CSV export: no

## Files
| file | lines |
|---|---|
| client/EnquiryApproval.java | 416 |
| client/EnquiryApprovalCard.java | 149 |
| client/EnquiryApprovalGridEdit.java | 294 |
| client/EnquiryApprovalGridEdit_Dsg.java | 270 |
| client/EnquiryApproval_Dsg.java | 242 |
| client/Setting.java | 259 |
| client/Setting_Dsg.java | 228 |
| server/EnquiryApproval_SrvImpl.java | 700 |
| shared/DefaultValue_Mdl.java | 22 |
| shared/EnquiryApproval_Mdl.java | 186 |
| shared/EnquiryApproval_Srv.java | 29 |
| shared/EnquiryApproval_SrvAsync.java | 29 |
| shared/GrdEnquiryApproval_Mdl.java | 158 |
| shared/GrdVendorDetaill_Mdl.java | 50 |
| shared/TxtHEnquiryNo_Mdl.java | 33 |
| shared/TxtHSupplier_Mdl.java | 31 |

## UI labels / columns (client)
`Enquiry No.`, `Enquiry Date`, `Reference No.`, `Site`, `Remarks`, `From Date`, `To Date`, `Supplier`, `Status`

## Models (shared DTOs)
### EnquiryApproval_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | IndentNo |
| String | FromDate |
| String | ToDate |
| String | StatusCode |
| String | StatusName |
| String | vendorName |
| String | vendorCode |
| String | enqDate |
| String | refNo |
| String | remark |
| String | siteName |
| String | approvedBy |
| String | isStatus |
| String | approvedDateTime |
| String | wtaCode |
| String | enquiryRecIds |
| String | enquiryDocIds |
| List<GrdVendorDetaill_Mdl> | grdVendorDetail_Mdl |
| List<GrdEnquiryApproval_Mdl> | enquiryApprovalEntry_Mdl |

### TxtHEnquiryNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | enquiryType |
| String | enquiryDate |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | currentDate |

### TxtHSupplier_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | cityName |
| String | manualCode |

### GrdEnquiryApproval_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | SiteCode |
| String | SiteName |
| String | ApprovedBy |
| String | approval |
| String | enquiryNo |
| String | enquiryType |
| String | enquiryDate |
| String | refNo |
| String | remark |
| String | isStatus |
| String | isChecked |
| String | vSNo |
| String | vendorName |
| String | vendorCode |
| String | wtaCode |
| String | isView |

### GrdVendorDetaill_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | isStatus |
| String | isChecked |
| String | isCancel |
| String | image |

## RPC interface
| method | params | returns |
|---|---|---|
| openRecord | boolean isOpen, String docId, String subCode | DmResult<String> |
| getFillRecords | EnquiryApproval_Mdl param, List<String> vCat, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdEnquiryApproval_Mdl>> |
| getTxtHStatus | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| fillVendorGrid | String code | DmResult<EnquiryApproval_Mdl> |
| getTxtHEnquiryNo | DmHelper pageing, boolean blnToEndOfFile, List<String> vCat | DmResultPageing<List<TxtHEnquiryNo_Mdl>> |
| getTxtHSupplier | DmHelper helper, boolean blnToEndOfFile, List<String> vCat, String v_Type, boolean isFromEnquiry | DmResultPageing<List<TxtHSupplier_Mdl>> |
| setApproval | String approvedBy, boolean isApprove, String docId | DmResult<String> |
| getCardDetail | List<String> vCat | DmResult<CardDetail_Mdl> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |

## Server `EnquiryApproval_SrvImpl.java` (700 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.xNull`

### `getFillRecords`
`public DmResultPageing<List<GrdEnquiryApproval_Mdl>> getFillRecords(EnquiryApproval_Mdl param, List<String> vCat, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select idd.subCode,sg.name AS VendorName,id.recId,id.docId,id.v_Date, id.remark,id.site_Code,sm.name AS siteName,id.approvedBy,id.referenceNo, FORMAT(id.approvalDateTime,'dd-MMM-yyyy HH:mm') AS approvalDateTime ,id.wtaCode, id.v_Type,vt.short_Name AS EnqType, idd.isStatus from enquiry AS id LEFT JOIN enquiryVendorDetail AS idd ON idd.docId=id.docId LEFT JOIN siteMast AS sm ON sm.code=id.site_Code LEFT JOIN subGroup AS sg on sg.subCode=idd.subCode LEFT JOIN voucher_Type AS vt on vt.v_Type=id.v_Type {…} where 1=1 {…}`
- Params: `:fromv_Date` ← `DmServer.convertDate(param.getFromDate())`; `:tov_Date` ← `DmServer.convertDate(param.getToDate())`

### `fillVendorGrid`
`public DmResult<EnquiryApproval_Mdl> fillVendorGrid(String code)`

- SQL 1: `select id.recId,id.docId,id.v_Date, id.remark,id.site_Code,sm.name AS siteName,id.approvedBy,id.referenceNo, FORMAT(id.approvalDateTime,'dd-MMM-yyyy HH:mm') AS approvalDateTime , id.v_Type,vt.description AS IndType,id.wtaCode from enquiry AS id LEFT JOIN siteMast AS sm ON sm.code=id.site_Code LEFT JOIN voucher_Type AS vt on vt.v_Type=id.v_Type where id.docId=:docId`
- SQL 2: `select idd.subCode,sg.name AS VendorName,id.docId,id.v_Date,id.approvedBy, FORMAT(id.approvalDateTime,'dd-MMM-yyyy HH:mm') AS approvalDateTime , idd.isStatus from enquiry AS id LEFT JOIN enquiryVendorDetail AS idd ON idd.docId=id.docId LEFT JOIN siteMast AS sm ON sm.code=id.site_Code LEFT JOIN subGroup AS sg on sg.subCode=idd.subCode LEFT JOIN voucher_Type AS vt on vt.v_Type=id.v_Type where id.docId=:docId order by VendorName Asc`
- Params: `:docId` ← `DmShared.xNull(code)`; `:docId` ← `DmShared.xNull(code)`

### `getTxtHStatus`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHStatus(DmHelper helper, boolean blnToEndOfFile)`


### `openRecord`
`public DmResult<String> openRecord(boolean isOpen, String docId, String subCode)`

- SQL 1: `select approvedBy from enquiry where docId = :docId`
- SQL 2: `Update enquiryVendorDetail set isStatus=:isStatus where docId=:docId AND subCode=:subCode`
- SQL 3: `select Count(isStatus) as cnt from enquiryVendorDetail where docId = :docId AND subCode=:subCode AND isStatus = 1`
- SQL 4: `UPDATE enquiryVendorDetail SET isStatus = 1 WHERE docId = :docId AND subCode=:subCode`
- SQL 5: `Update enquiry set u_EntDt_LatestLine = :uEntDt where docId=:docId`
- Params: `:docId` ← `DmShared.iNull(docId).toString()`; `:docId` ← `DmShared.iNull(docId).toString()`; `:subCode` ← `DmShared.iNull(subCode).toString()`; `:isStatus` ← `"0"`; `:docId` ← `DmShared.iNull(docId).toString()`; `:subCode` ← `DmShared.iNull(subCode).toString()`; `:docId` ← `DmShared.iNull(docId).toString()`; `:subCode` ← `DmShared.iNull(subCode).toString()`; `:docId` ← `DmShared.iNull(docId).toString()`; `:uEntDt` ← `DmServer.getServerTimestamp()`
- Errors: "Record is not approved by this user. Please refresh data." | "Record is already cancelled,Please refresh record."

### `getTxtHEnquiryNo`
`public DmResultPageing<List<TxtHEnquiryNo_Mdl>> getTxtHEnquiryNo(DmHelper helper, boolean blnToEndOfFile, List<String> vCat)`

- SQL 1: `SELECT enq.docId, enq.recId as enqRecId, enq.v_Date as enqDate, vt.category FROM enquiry AS enq left join voucher_Type as vt on vt.v_Type = enq.v_Type where enq.site_Code = :site_Code and enq.v_Prefix = :v_Prefix AND vt.Category IN ( {…} :vCat {…} )`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Prefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getTxtHSupplier`
`public DmResultPageing<List<TxtHSupplier_Mdl>> getTxtHSupplier(DmHelper helper, boolean blnToEndOfFile, List<String> vCat, String v_Type, boolean flagFilterByVoucherCategory)`

- SQL 1: `SELECT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_type = acf.v_Type WHERE vt.category IN ( {…} :vCat {…} )`
- SQL 2: `SELECT acf.Nature FROM AcFilteration acf WHERE acf.v_Type = :v_Type`
- SQL 3: `SELECT sg.subCode AS code , sg.name , sg.manualCode , c.cityName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( {…} ) AND sg.isActive = 1 AND sgsl.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Type` ← `DmShared.xNull(v_Type)`

### `walkThrough`
`public DmResult<String> walkThrough(WalkThrough_Mdl walkThrough)`

- Other calls: `STApprovals.generalEnquiryApproval`, `WalkThrough_Server.walkThrough`

### `setApproval`
`public DmResult<String> setApproval(String approvedBy, boolean isApprove, String docId)`

- Other calls: `STApprovals.generalEnquiryApproval`

### `getCardDetail`
`public DmResult<CardDetail_Mdl> getCardDetail(List<String> vCat)`

- SQL 1: `Select count(enq.docId) as cnt, sum(case when DATEDIFF(day, enq.v_Date, CAST(getdate() AS date)) <= {…} then 1 else 0 end) as low, sum(case when DATEDIFF(day, enq.v_Date, CAST(getdate() AS date)) > {…} and DATEDIFF(day, enq.v_Date, CAST(getdate() AS date)) <= {…} then 1 else 0 end) as medium, sum(case when DATEDIFF(day, enq.v_Date, CAST(getdate() AS date)) >= {…} then 1 else 0 end) as high from enquiry as enq left join voucher_type as vt on enq.v_Type = vt.v_Type left join( select enqd.docId from enquiryDetail as enqd left join enquiryVendorDetail as evd on evd.docId= enqd.docId where evd.isStatus = 0 group by enqd.docId ) as enqd on enqd.docId = enq.docId LEFT JOIN walkThroughFootPrint wtfp on wtfp.wtaCode = enq.wtaCode and wtfp.isStatus = 0 where enqd.docId is not null and isNull(enq.approvedBy,'') = '' AND vt.category IN ( {…} :vCat {…} ) AND isNull(wtfp.linkedUser,' {…} ,') like '% {…} ,%' and enq.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `HIGH_PRIORITYDAYS.getDays`, `LOW_PRIORITYDAYS.getDays`, `MEDIUM_PRIORITYDAYS.getDays`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`


### `getCsvViaList`
`private String getCsvViaList(List<String> vCat)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.dashboardCardStrip.client`, `paperbiz.selfToolkit.dashboardCardStrip.shared`, `paperbiz.selfToolkit.enquiryApproval.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| openRecord | `if (!(DmShared.xNull(DmServer.getSsnUserName(this)).equalsIgnoreCase(rsTemp.getString("approvedBy"))` | "Record is not approved by this user. Please refresh data." |
| openRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() == 1)` | "Record is already cancelled,Please refresh record." |

## Notes (hand-written)
_No hand-written notes yet._
