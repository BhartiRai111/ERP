# dm-toolkit / gstVoucherEntryApproval

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/gstVoucherEntryApproval/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHStatus, getTxtHVoucherNo, getTxtHVoucherType
- CSV export: no

## Files
| file | lines |
|---|---|
| client/GstVoucherEntryApproval.java | 324 |
| client/GstVoucherEntryApproval_Dsg.java | 213 |
| client/Setting.java | 246 |
| client/Setting_Dsg.java | 225 |
| server/GstVoucherEntryApproval_SrvImpl.java | 412 |
| shared/DefaultValue_Mdl.java | 22 |
| shared/GrdGstVoucherEntryApproval_Mdl.java | 168 |
| shared/GstVoucherEntryApproval_Srv.java | 22 |
| shared/GstVoucherEntryApproval_SrvAsync.java | 22 |
| shared/Setting_Mdl.java | 77 |
| shared/TxtHVoucherNo_Mdl.java | 32 |

## UI labels / columns (client)
`From Date`, `To Date`, `Status`, `Voucher No.`, `Voucher Type`

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
| String | docId |
| String | recId |
| String | statusCode |
| String | statusName |
| String | voucherTypeCode |

### GrdGstVoucherEntryApproval_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | wtaCode |
| String | refNo |
| String | refDate |
| String | amount |
| String | voucherDate |
| String | narration |
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
| getFillRecords | Setting_Mdl param, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdGstVoucherEntryApproval_Mdl>> |
| getTxtHStatus | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVoucherNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucherNo_Mdl>> |
| getTxtHVoucherType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| setApproval | String approvedBy, boolean isApprove, String docId | DmResult<String> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |

## Server `GstVoucherEntryApproval_SrvImpl.java` (412 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerDateTime`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.xNull`

### `getFillRecords`
`public DmResultPageing<List<GrdGstVoucherEntryApproval_Mdl>> getFillRecords(Setting_Mdl param, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select LM.DocId ,LM.V_Type ,lg.refDate ,lg.refNo ,lg.Amt ,LM.Site_Code ,LM.V_Date ,SG.Name As partyName ,LM.RecId ,LM.narration ,LM.postedBy ,FORMAT(LM.postedByDateTime,'dd-MMM-yyyy HH:mm') AS postedByDateTime ,sm.name AS siteName ,VT.Category ,vt.short_Name as vtypeShortName ,sgn.manualCode As SGNature ,LM.wtaCode, VT.mnuText, VT.mnuAttachedInModule From LedgerM LM Left Join SubGroup SG On LM.SubCode = SG.SubCode Left Join Voucher_Type VT On VT.V_Type = LM.V_Type left join siteMast as sm on sm.code = LM.site_Code LEFT JOIN subGroupNature as sgn on sg.nature = sgn.code LEFT JOIN (select ld.docId,MAX(ld.refDate) AS refDate,MAX(ld.refNo) AS refNo, SUM(ld.amtDr+ld.amtCr) AS Amt from Ledger ld Left Join SubGroup SG2 On ld.SubCode = SG2.SubCode WHERE ld.v_SNo = 1 group by ld.docId )as lg ON lg.docId=LM.docId where VT.Category In (' {…} ',' {…} ', ' {…} ', ' {…} ') {…}`
- Params: `:fromv_Date` ← `DmServer.convertDate(param.getFromDate())`; `:tov_Date` ← `DmServer.convertDate(param.getToDate())`

### `getTxtHStatus`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHStatus(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHVoucherNo`
`public DmResultPageing<List<TxtHVoucherNo_Mdl>> getTxtHVoucherNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT (lm.docId) as code, (lm.recId) as Name FROM ledgerM AS lm LEFT JOIN voucher_Type vt ON vt.v_Type = lm.v_Type where lm.site_Code = ' {…} ' {…}`

### `getTxtHVoucherType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVoucherType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT VT.V_Type AS code, VT.Description AS name,VT.Category FROM Voucher_Type VT WHERE VT.NCat = 'FA' {…}`

### `setApproval`
`public DmResult<String> setApproval(String approvedBy, boolean isApprove, String docId)`

- Other calls: `DmApprovals.gstVoucherApproval`

### `walkThrough`
`public DmResult<String> walkThrough(WalkThrough_Mdl walkThrough)`

- Other calls: `DmApprovals.gstVoucherApproval`, `WalkThrough_Server.walkThrough`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.gstVoucherEntryApproval.shared`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.self.client`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `dataman.libraryMenu.self.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
