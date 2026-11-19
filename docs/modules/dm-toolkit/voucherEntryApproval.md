# dm-toolkit / voucherEntryApproval

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/voucherEntryApproval/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHStatus, getTxtHVoucherNo, getTxtHCostCenter, getTxtHVoucherType
- CSV export: no

## Files
| file | lines |
|---|---|
| client/Setting.java | 278 |
| client/Setting_Dsg.java | 254 |
| client/VoucherEntryApproval.java | 318 |
| client/VoucherEntryApproval_Dsg.java | 253 |
| server/VoucherEntryApproval_SrvImpl.java | 480 |
| shared/DefaultValue_Mdl.java | 22 |
| shared/GrdVoucherEntryApproval_Mdl.java | 186 |
| shared/Setting_Mdl.java | 95 |
| shared/TxtHVoucherNo_Mdl.java | 32 |
| shared/VoucherEntryApproval_Srv.java | 24 |
| shared/VoucherEntryApproval_SrvAsync.java | 24 |

## UI labels / columns (client)
`From Date`, `To Date`, `Status`, `Voucher No.`, `Cost Center`, `Voucher Type`

## Models (shared DTOs)
### TxtHVoucherNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | reffNo |
| String | reffDate |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | currentDate |

### Setting_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | FromDate |
| String | ToDate |
| String | CostCenterName |
| String | CostCenterCode |
| String | docId |
| String | recId |
| String | statusCode |
| String | statusName |
| String | voucherTypeCode |

### GrdVoucherEntryApproval_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | wtaCode |
| String | refNo |
| String | refDate |
| String | amount |
| String | voucherDate |
| String | costCenter |
| String | narration |
| String | firstActName |
| String | headerActName |
| String | SiteName |
| String | ApprovedBy |
| String | Approval |
| String | isStatus |
| String | isChecked |
| String | voucherType |
| String | voucherCategory |
| String | isView |
| String | mnuText |
| String | mnuAttachedInModule |

## RPC interface
| method | params | returns |
|---|---|---|
| getFillRecords | Setting_Mdl param, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdVoucherEntryApproval_Mdl>> |
| getTxtHStatus | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHCostCenter | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVoucherNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucherNo_Mdl>> |
| getTxtHVoucherType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| setApproval | String approvedBy, boolean isApprove, String docId | DmResult<String> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |

## Server `VoucherEntryApproval_SrvImpl.java` (480 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerDateTime`, `DmServer.getSsnCostCenterList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.xNull`

### `getFillRecords`
`public DmResultPageing<List<GrdVoucherEntryApproval_Mdl>> getFillRecords(Setting_Mdl param, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select LM.DocId,LM.V_Type,lg.refDate,lg.refNo,lg.subcode,lg.name AS FirstActName,lg.Amt,LM.Site_Code,LM.V_Date,SG.Name As HeaderActName,LM.RecId, LM.narration,LC.Cnt_CCM,LC.CCMName,LM.postedBy,FORMAT(LM.postedByDateTime,'dd-MMM-yyyy HH:mm') AS postedByDateTime,sm.name AS siteName, VT.Category, vt.short_Name as vtypeShortName, sgn.manualCode As SGNature,LM.wtaCode,LC.CostCenter, VT.mnuText, VT.mnuAttachedInModule From LedgerM LM Left Join SubGroup SG On LM.SubCode = SG.SubCode Left Join Voucher_Type VT On VT.V_Type = LM.V_Type left join siteMast as sm on sm.code = LM.site_Code LEFT JOIN subGroupNature as sgn on sg.nature = sgn.code LEFT JOIN ( select ld.docId,MAX(ld.refDate) AS refDate,MAX(ld.refNo) AS refNo,MAX(ld.subcode) AS subcode,MAX(SG2.name) AS name, MAX(ld.amtDr-ld.amtCr) AS Amt from Ledger ld Left Join SubGroup SG2 On ld.SubCode = SG2.SubCode group by docId )as lg ON lg.docId=LM.docId Left Join ( Select LGC.DocId,Count(Distinct LGC.CostCenter) As Cnt_CCM,Max(LGC.CostCenter) As CostCenter,Max(CCM.Name) As CCMName From LedgerCostCenter LGC Left Join CostCenterMast CCM On CCM.Code = LGC.CostCenter Group By LGC.DocId ) As LC On LC.DocId = LM.DocId where VT.Category In (' {…} ',' {…} ',' {…} ',' {…} ') {…}`
- Params: `:fromv_Date` ← `DmServer.convertDate(param.getFromDate())`; `:tov_Date` ← `DmServer.convertDate(param.getToDate())`

### `getTxtHStatus`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHStatus(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHVoucherNo`
`public DmResultPageing<List<TxtHVoucherNo_Mdl>> getTxtHVoucherNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT (lm.docId) as code, (lm.recId) as Name FROM ledgerM AS lm LEFT JOIN voucher_Type vt ON vt.v_Type = lm.v_Type where lm.site_Code = ' {…} ' {…}`

### `getTxtHCostCenter`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCostCenter(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select Code,Name,ManualCode,Site_Code From CostCenterMast Where isNull(UnderCostCenter,'') = '' And isActive = 1 {…}`

### `getTxtHVoucherType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVoucherType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT VT.V_Type AS CODE, VT.Description As name FROM Voucher_Type VT WHERE VT.V_Type IN ( SELECT V_Type FROM Ledger WHERE Site_code IN ( {…} :code {…} ) ) {…}`

### `setApproval`
`public DmResult<String> setApproval(String approvedBy, boolean isApprove, String docId)`

- Other calls: `DmApprovals.voucherApproval`

### `walkThrough`
`public DmResult<String> walkThrough(WalkThrough_Mdl walkThrough)`

- Other calls: `DmApprovals.voucherApproval`, `WalkThrough_Server.walkThrough`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.self.client`, `dataman.dmToolkit.voucherEntryApproval.shared`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `dataman.libraryMenu.self.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
