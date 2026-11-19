# pb-self-toolkit / generalSalesInvoiceApproval

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/generalSalesInvoiceApproval/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHSalesInvoice, getTxtHParty, getTxtHDeliveryCity, getTxtHItem, getTxtHGateNo, getTxtHStatus, getTxtHTransporter, getTxtHDistributor
- CSV export: no

## Files
| file | lines |
|---|---|
| client/GeneralSalesInvoiceApproval.java | 361 |
| client/GeneralSalesInvoiceApprovalCard.java | 143 |
| client/GeneralSalesInvoiceApproval_Dsg.java | 284 |
| client/Setting.java | 409 |
| client/Setting_Dsg.java | 410 |
| server/GeneralSalesInvoiceApproval_SrvImpl.java | 825 |
| shared/DefaultValue_Mdl.java | 22 |
| shared/GeneralSalesInvoiceApproval_Mdl.java | 250 |
| shared/GeneralSalesInvoiceApproval_Srv.java | 35 |
| shared/GeneralSalesInvoiceApproval_SrvAsync.java | 36 |
| shared/GrdGeneralSalesInvoiceApproval_Mdl.java | 222 |
| shared/TxtHDistributor_Mdl.java | 41 |
| shared/TxtHGateIn_Mdl.java | 32 |
| shared/TxtHGeneralSalesInvoice_Mdl.java | 32 |
| shared/TxtHSupplierPT_Mdl.java | 31 |

## UI labels / columns (client)
`From Date`, `To Date`, `Party`, `Distributor`, `Consignee`, `Delivery City`, `Invoice No.`, `Status`, `Gate Entry No`, `Transporter`

## Models (shared DTOs)
### TxtHGeneralSalesInvoice_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | type |
| String | date |

### GeneralSalesInvoiceApproval_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | FromDate |
| String | ToDate |
| String | StatusCode |
| String | StatusName |
| String | partyName |
| String | partyCode |
| String | distributorCode |
| String | distributorName |
| String | trasnporterName |
| String | trasnporterCode |
| String | gateNo |
| String | gateCode |
| String | consigneeCode |
| String | consigneeName |
| String | deliveryCityCode |
| String | deliveryCityName |
| String | saleInvoiceCode |
| String | saleInvoiceName |
| String | saleInvoiceDate |
| String | deliveryToName |
| String | vSNo |
| String | siteCode |
| String | siteName |
| String | isStatus |
| String | wtaCode |

### TxtHDistributor_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityName |
| String | priceListCode |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | currentDate |

### TxtHSupplierPT_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityName |

### GrdGeneralSalesInvoiceApproval_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | isChecked |
| String | IsView |
| String | isStatus |
| String | invoiceNo |
| String | type |
| String | date |
| String | party |
| String | ApprovedBy |
| String | distributor |
| String | consignee |
| String | deliveryTo |
| String | toPlace |
| String | supplimentryBill |
| String | refNo |
| String | gateNo |
| String | transporter |
| String | transportMode |
| String | ewayBillRequired |
| String | ewayBillNo |
| String | ewayBillDate |
| String | policyNo |
| String | Approval |
| String | wtaCode |

### TxtHGateIn_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | gateType |
| String | gateDate |

## RPC interface
| method | params | returns |
|---|---|---|
| getFillRecords | GeneralSalesInvoiceApproval_Mdl param, DmHelper helper, boolean blnToEndOfFile, String vCat | DmResultPageing<List<GrdGeneralSalesInvoiceApproval_Mdl>> |
| getTxtHSalesInvoice | DmHelper pageing, boolean blnToEndOfFile, String vCat | DmResultPageing<List<TxtHGeneralSalesInvoice_Mdl>> |
| getTxtHGateNo | DmHelper pageing, boolean blnToEndOfFile, String vCat | DmResultPageing<List<TxtHGateIn_Mdl>> |
| getTxtHParty | DmHelper pageing, boolean blnToEndOfFile, String vCat | DmResultPageing<List<TxtHSupplierPT_Mdl>> |
| getTxtHTransporter | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHSupplierPT_Mdl>> |
| getTxtHDistributor | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHDistributor_Mdl>> |
| getTxtHItem | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDeliveryCity | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHStatus | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| setApproval | String approvedBy, boolean isApprove, String docId | DmResult<String> |
| getCardDetail | String vCat | DmResult<CardDetail_Mdl> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |

## Server `GeneralSalesInvoiceApproval_SrvImpl.java` (825 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.xNull`

### `getFillRecords`
`public DmResultPageing<List<GrdGeneralSalesInvoiceApproval_Mdl>> getFillRecords(GeneralSalesInvoiceApproval_Mdl param, DmHelper helper, boolean blnToEndOfFile, String vCat)`

- SQL 1: `SELECT sb.docId,sb.recId,sb.v_Date ,sb.itemNature ,sb.partyCode AS supplierCode,sg.name AS partyName, sb.distributor AS distributorCode,sgd.name AS distributorName,vt.short_Name, sb.consignee AS consigneeCode,sgc.name AS consigneeName,sb.v_No, sb.deliveryTo AS deliveryToCode,sgdt.name AS deliveryToName, sb.deliveryCity,c.cityName, sb.u_Name,sb.preparedBy,sb.approvedBy, format(sb.approvalDateTime,'dd-MMM-yyyy HH.mm') as approvalDateTime,sb.wtaCode ,sb.policyCode , pm.name as policyName, sb.refDate , sb.refNo , sb.isSupplementary , sb.isEWayBillRequired , sb.eWayBillDt , sb.eWayBillNo , sb.gateDocId , gtIo.recId as gateRecId ,sb.transporterCode , sgt.name as transporterName , sb.transportMode FROM sBill AS sb LEFT JOIN voucher_Type AS vt ON vt.v_Type = sb.v_Type LEFT JOIN subGroup AS sg ON sg.subCode = sb.partyCode LEFT JOIN subGroup AS sgd ON sgd.subCode = sb.distributor LEFT JOIN subGroup AS sgt ON sgt.subCode = sb.transporterCode LEFT JOIN subGroup AS sgc ON sgc.subCode = sb.consignee LEFT JOIN subGroup AS sgdt ON sgdt.subCode = sb.deliveryTo LEFT JOIN city AS c ON c.cityCode = sb.deliveryCity LEFT JOIN policyMast as pm ON pm.code = sb.policyCode LEFT JOIN gateInOut as gtIo ON gtIo.docId = sb.gateDocId {…} where vt.category IN ( {…} ) {…}`
- Params: `:fromv_Date` ← `DmServer.convertDate(param.getFromDate())`; `:tov_Date` ← `DmServer.convertDate(param.getToDate())`
- Other calls: `TransportMode.getViaKey`

### `getTxtHSalesInvoice`
`public DmResultPageing<List<TxtHGeneralSalesInvoice_Mdl>> getTxtHSalesInvoice(DmHelper helper, boolean blnToEndOfFile, String vCat)`

- SQL 1: `SELECT so.docId, so.recId , so.v_Date as date, vt.category FROM sBill AS so left join voucher_Type as vt on vt.v_Type = so.v_Type where so.site_Code= ' {…} ' and vt.Category IN ( {…} )`

### `getTxtHParty`
`public DmResultPageing<List<TxtHSupplierPT_Mdl>> getTxtHParty(DmHelper helper, boolean blnToEndOfFile, String vCat)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as supplierName , sg.manualCode , c.cityName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( SELECT DISTINCT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_Type = acf.v_Type WHERE vt.category IN( {…} ) ) AND sg.isActive = 1 AND sgsl.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHDeliveryCity`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDeliveryCity(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select ct.cityCode, ct.cityName, ct.stateCode, sm.name as stateName from city ct left join stateMast sm on ct.stateCode = sm.code`

### `setApproval`
`public DmResult<String> setApproval(String approvedBy, boolean isApprove, String docId)`

- Other calls: `STApprovals.generalSalesInvoiceApproval`

### `walkThrough`
`public DmResult<String> walkThrough(WalkThrough_Mdl walkThrough)`

- Other calls: `STApprovals.generalSalesInvoiceApproval`, `WalkThrough_Server.walkThrough`

### `getCardDetail`
`public DmResult<CardDetail_Mdl> getCardDetail(String vCat)`

- SQL 1: `Select count(sb.docId) as cnt, sum(case when DATEDIFF(day, sb.v_Date, CAST(getdate() AS date)) <= {…} then 1 else 0 end) as low, sum(case when DATEDIFF(day, sb.v_Date, CAST(getdate() AS date)) > {…} and DATEDIFF(day, sb.v_Date, CAST(getdate() AS date)) <= {…} then 1 else 0 end) as medium, sum(case when DATEDIFF(day, sb.v_Date, CAST(getdate() AS date)) >= {…} then 1 else 0 end) as high from sBill as sb left join voucher_type as vt on sb.v_Type = vt.v_Type left join( select sb.docId from sBill as sb group by sb.docId ) as vSbd on vSbd.docId = sb.docId LEFT JOIN walkThroughFootPrint wtfp on wtfp.wtaCode = sb.wtaCode and wtfp.isStatus = 0 where vSbd.docId is not null and isNull(sb.approvedBy,'') = '' and vt.category IN ( {…} ) AND isNull(wtfp.linkedUser,' {…} ,') like '% {…} ,%' and sb.site_Code = :site_Code AND isNull(sb.cancelledBy,'') = ''`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `HIGH_PRIORITYDAYS.getDays`, `LOW_PRIORITYDAYS.getDays`, `MEDIUM_PRIORITYDAYS.getDays`

### `getTxtHItem`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select im.code as itemCode, im.name as itemName, um.unit as itemUnitName from itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN unitMast um ON um.code = im.saleUnit WHERE im.IsActive = 1 AND (im.filterItemType = :filterItemType OR im.isCommon = 1) AND sl.site_Code = :site_Code`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`


### `getTxtHGateNo`
`public DmResultPageing<List<TxtHGateIn_Mdl>> getTxtHGateNo(DmHelper helper, boolean blnToEndOfFile, String vCat)`

- SQL 1: `SELECT gio.docId, gio.recId as gateNo , gio.v_Date as gateDate, vt.category, gio.v_No FROM gateInOut AS gio left join voucher_Type as vt on vt.v_Type = gio.v_Type where gio.site_Code= ' {…} ' and vt.Category IN ( ' {…} ' )`

### `getTxtHStatus`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHStatus(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHTransporter`
`public DmResultPageing<List<TxtHSupplierPT_Mdl>> getTxtHTransporter(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code, sg.name as supplierName, sg.manualCode, c.cityName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode WHERE sg.isActive = 1 AND sgsl.site_Code = :site_Code AND sg.nature = :nature`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:nature` ← `DmServer.getCodeViaManualCode("TP", "manualCode", "subGroupNature", "code", cnTemp)`

### `getTxtHDistributor`
`public DmResultPageing<List<TxtHDistributor_Mdl>> getTxtHDistributor(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as name , sg.manualCode , c.cityName , sg.priceListCode FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode WHERE sg.partyCat= :partyCat AND sg.isActive = 1 AND sgsl.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:partyCat` ← `DmEnum.PartyCategory.DISTRIBUTOR.getId()`
- Other calls: `DISTRIBUTOR.getId`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.dashboardCardStrip.client`, `paperbiz.selfToolkit.dashboardCardStrip.shared`, `paperbiz.selfToolkit.generalSalesInvoiceApproval.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
