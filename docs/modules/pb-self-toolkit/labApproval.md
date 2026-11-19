# pb-self-toolkit / labApproval

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/labApproval/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: lab
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHStatus, getTxtHLabNo, getTxtHSampleNo, getTxtHTechnician, getTxtHItem, getTxtHTestTemplate
- CSV export: no

## Files
| file | lines |
|---|---|
| client/LabApproval.java | 365 |
| client/LabApprovalCard.java | 131 |
| client/LabApproval_Dsg.java | 240 |
| client/Setting.java | 355 |
| client/Setting_Dsg.java | 343 |
| server/LabApproval_SrvImpl.java | 718 |
| shared/DefaultValue_Mdl.java | 22 |
| shared/GrdLabApproval_Mdl.java | 131 |
| shared/LabApproval_Mdl.java | 196 |
| shared/LabApproval_Srv.java | 34 |
| shared/LabApproval_SrvAsync.java | 34 |
| shared/TxtHItem_Mdl.java | 24 |
| shared/TxtHLabNo_Mdl.java | 59 |
| shared/TxtHSampleNo_Mdl.java | 53 |
| shared/TxtHTechnician_Mdl.java | 23 |

## UI labels / columns (client)
`From Date`, `To Date`, `Lab No.`, `Technician`, `Test Template`, `Sample No.`, `Item Name`, `Status`

## Models (shared DTOs)
### TxtHTechnician_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |

### TxtHSampleNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | sampleDate |
| String | sampleTime |
| String | itemName |
| String | testTemplate |

### GrdLabApproval_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | isChecked |
| String | IsView |
| String | labType |
| String | labDateTime |
| String | technician |
| String | testTemplate |
| String | sampleNo |
| String | itemName |
| String | sampleConsumeQty |
| String | unit |
| String | approvedBy |
| String | wtaCode |
| String | Approval |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | currentDate |

### TxtHItem_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |

### TxtHLabNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | labType |
| String | labDate |
| String | labTime |
| String | itemName |
| String | testTemplate |

### LabApproval_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | FromDate |
| String | ToDate |
| String | vSNo |
| String | siteCode |
| String | siteName |
| String | approvedBy |
| String | isStatus |
| String | approvedDateTime |
| String | StatusCode |
| String | StatusName |
| String | labCode |
| String | labRecId |
| String | technicianCode |
| String | technicianName |
| String | testTemplateCode |
| String | testTemplateName |
| String | sampleCode |
| String | sampleRecId |
| String | itemCode |
| String | itemName |

## RPC interface
| method | params | returns |
|---|---|---|
| openRecord | boolean isOpen, String docId, String vSNo | DmResult<String> |
| getTxtHStatus | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| setApproval | String approvedBy, boolean isApprove, String docId | DmResult<String> |
| getCardDetail |  | DmResult<CardDetail_Mdl> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| getFillRecords | LabApproval_Mdl param, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdLabApproval_Mdl>> |
| getTxtHLabNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHLabNo_Mdl>> |
| getTxtHSampleNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHSampleNo_Mdl>> |
| getTxtHTechnician | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHTechnician_Mdl>> |
| getTxtHItem | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getTxtHTestTemplate | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Server `LabApproval_SrvImpl.java` (718 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.xNull`

### `getFillRecords`
`public DmResultPageing<List<GrdLabApproval_Mdl>> getFillRecords(LabApproval_Mdl param, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select lab.docId, lab.v_No, lab.v_Type, vt.short_Name, lab.v_Prefix, lab.v_Date,lab.v_Time, lab.recId, lab.recIdPrefix, lab.technician as technicianCode, sg.name as technicianName,lab.labSampleDocId,smp.recId as sampleRecId, lab.sampleConsumedQty, lab.preparedBy, lab.preparedDt, lab.u_Name, lab.u_EntDt, lab.approvedBy, lab.approvalDateTime, lab.u_EntDt_LatestLine, lab.wtaCode, lab.site_Code , tmm.description as testTemplateName , im.name as itemName, um.unit from lab lab LEFT JOIN voucher_Type vt ON vt.v_Type = lab.v_Type LEFT JOIN subGroup sg ON sg.subCode = lab.technician LEFT JOIN labsample smp ON smp.docId = lab.labSampleDocId LEFT JOIN testTemplateMast tmm ON tmm.code = smp.testTemplateCode LEFT JOIN itemMast im ON im.code = smp.itemCode LEFT JOIN unitMast um ON um.code = smp.sampleUnit {…} where vt.category = {…} {…}`
- Params: `:fromv_Date` ← `DmServer.convertDate(param.getFromDate())`; `:tov_Date` ← `DmServer.convertDate(param.getToDate())`

### `openRecord`
`public DmResult<String> openRecord(boolean isOpen, String docId, String vSNo)`

- SQL 1: `select approvedBy from lab where docId = :docId`
- SQL 2: `Update lab set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- Params: `:docId` ← `DmShared.iNull(docId).toString()`; `:docId` ← `DmShared.iNull(docId).toString()`; `:uEntDt` ← `DmServer.getServerTimestamp()`
- Errors: "Record is not approved by this user. Please refresh data."

### `getTxtHStatus`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHStatus(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHLabNo`
`public DmResultPageing<List<TxtHLabNo_Mdl>> getTxtHLabNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT lab.docId, lab.recId as labRecId, lab.v_Date as labDate, lab.v_Time as labTime, vt.category , tmm.description as testTemplateName , im.name as itemName FROM lab AS lab left join voucher_Type as vt on vt.v_Type = lab.v_Type LEFT JOIN labsample smp ON smp.docId = lab.labSampleDocId LEFT JOIN testTemplateMast tmm ON tmm.code = smp.testTemplateCode LEFT JOIN itemMast im ON im.code = smp.itemCode where lab.site_Code = ' {…} ' AND vt.category= {…}`

### `setApproval`
`public DmResult<String> setApproval(String approvedBy, boolean isApprove, String docId)`

- Other calls: `STApprovals.labApproval`

### `walkThrough`
`public DmResult<String> walkThrough(WalkThrough_Mdl walkThrough)`

- Other calls: `STApprovals.labApproval`, `WalkThrough_Server.walkThrough`

### `getCardDetail`
`public DmResult<CardDetail_Mdl> getCardDetail()`

- SQL 1: `Select count(lab.docId) as cnt, sum(case when DATEDIFF(day, lab.v_Date, CAST(getdate() AS date)) <= {…} then 1 else 0 end) as low, sum(case when DATEDIFF(day, lab.v_Date, CAST(getdate() AS date)) > {…} and DATEDIFF(day, lab.v_Date, CAST(getdate() AS date)) <= {…} then 1 else 0 end) as medium, sum(case when DATEDIFF(day, lab.v_Date, CAST(getdate() AS date)) >= {…} then 1 else 0 end) as high from lab as lab left join voucher_type as vt on lab.v_Type = vt.v_Type left join( select lab.docId from lab as lab group by lab.docId ) as undt on undt.docId = lab.docId LEFT JOIN walkThroughFootPrint wtfp on wtfp.wtaCode = lab.wtaCode and wtfp.isStatus = 0 where undt.docId is not null and isNull(lab.approvedBy,'') = '' and vt.category = (' {…} ') AND isNull(wtfp.linkedUser,' {…} ,') like '% {…} ,%' and lab.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `HIGH_PRIORITYDAYS.getDays`, `LOW_PRIORITYDAYS.getDays`, `MEDIUM_PRIORITYDAYS.getDays`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`


### `getTxtHSampleNo`
`public DmResultPageing<List<TxtHSampleNo_Mdl>> getTxtHSampleNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select smp.docId, smp.recId, smp.v_Date , smp.v_Time, tmm.description as testTemplateName , im.name as itemName from labsample smp LEFT JOIN testTemplateMast tmm ON tmm.code = smp.testTemplateCode LEFT JOIN itemMast im ON im.code = smp.itemCode`

### `getTxtHTechnician`
`public DmResultPageing<List<TxtHTechnician_Mdl>> getTxtHTechnician(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT e.labChemistDesignationCode FROM enviroCompany e`
- SQL 2: `select sg.name, sg.subcode, sg.manualCode from designationMast dm left join subGroup2 sg2 ON sg2.DesignationCode = dm.code left join subGroup sg ON sg.subcode = sg2.subCode WHERE dm.code = :chemistCode`
- Params: `:chemistCode` ← `DmShared.iNull(labChemistDesignationCode).toString()`

### `getTxtHItem`
`public DmResultPageing<List<TxtHItem_Mdl>> getTxtHItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code,im.name as itemName, im.manualCode FROM itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code WHERE im.isActive = 1 AND (im.filterItemType <> :filterItemType OR im.isCommon = 1) AND sl.site_Code = :site_Code`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("OI", "manualCode", "itemType", "code", cnTem`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHTestTemplate`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTestTemplate(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code, description from testTemplateMast where active = 1`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.dashboardCardStrip.client`, `paperbiz.selfToolkit.dashboardCardStrip.shared`, `paperbiz.selfToolkit.labApproval.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| openRecord | `if (!(DmShared.xNull(DmServer.getSsnUserName(this)).equalsIgnoreCase(rsTemp.getString("approvedBy"))` | "Record is not approved by this user. Please refresh data." |

## Notes (hand-written)
_No hand-written notes yet._
