# dm-toolkit / accountAdjustment

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/accountAdjustment/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): ledgerAdj
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHLedger, getTxtHVoucher, getHistory
- CSV export: no

## Files
| file | lines |
|---|---|
| client/AccountAdjustment.java | 332 |
| client/AccountAdjustmentGridEdit.java | 151 |
| client/AccountAdjustmentGridEdit_Dsg.java | 207 |
| client/AccountAdjustment_Dsg.java | 261 |
| client/HistoryPopup.java | 194 |
| client/HistoryPopup_Dsg.java | 126 |
| client/SearchFilterPopup.java | 239 |
| client/SearchFilterPopup_Dsg.java | 225 |
| client/SearchPopup.java | 356 |
| client/SearchPopup_Dsg.java | 169 |
| server/AccountAdjustment_Server.java | 352 |
| server/AccountAdjustment_SrvImpl.java | 413 |
| shared/AccountAdjustment_Mdl.java | 77 |
| shared/AccountAdjustment_Srv.java | 22 |
| shared/AccountAdjustment_SrvAsync.java | 23 |
| shared/AdjustmentSave_Mdl.java | 60 |
| shared/Filter_Mdl.java | 85 |
| shared/GrdAdjustment_Mdl.java | 245 |
| shared/GrdHistory_Mdl.java | 160 |
| shared/TxtHLedger_Mdl.java | 32 |

## UI labels / columns (client)
`Vr. No.`, `Vr. Date`, `Ref. No.`, `Dr/Cr`, `Vr. Type`, `Ledger`, `Working Amount`, `Pending Reference`, `Total Referenced`, `Date`, `Type`, `A/c Name`, `City`, `Narration`, `Amount`, `Adjusted`, `Adjustment`

## Models (shared DTOs)
### AccountAdjustment_Mdl
| type | field |
|---|---|
| boolean | isEditable |
| String | acCode |
| String | acName |
| String | amtCr |
| String | amtDr |
| List<GrdAdjustment_Mdl> | lstAdjustment |

### GrdHistory_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | main_DocId |
| String | main_VNo |
| String | main_VType |
| String | main_VDate |
| String | main_LgName |
| String | main_MnuText |
| String | main_MnuAttachedInModule |
| String | adj_DocId |
| String | adj_VNo |
| String | adj_VType |
| String | adj_VDate |
| String | adj_MnuText |
| String | adj_MnuAttachedInModule |
| String | narration |
| String | amount |
| String | adjustment |

### Filter_Mdl
| type | field |
|---|---|
| String | voucherNo |
| String | voucherDate |
| String | refNo |
| String | voucherTypeCode |
| String | voucherTypeName |
| String | acCode |
| String | acName |
| boolean | showDebit |

### AdjustmentSave_Mdl
| type | field |
|---|---|
| String | vr_DocId |
| String | vr_V_SNo |
| String | vr_SubCode |
| String | vr_V_Date |
| List<GrdAdjustment_Mdl> | lstAdjustments |

### TxtHLedger_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | city |

### GrdAdjustment_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | isSelected |
| String | v_TypeCode |
| String | v_TypeName |
| String | v_No |
| String | v_SNo |
| String | v_Date |
| String | refNo |
| String | refDate |
| String | acCode |
| String | acName |
| String | acManualCode |
| String | cityCode |
| String | cityName |
| String | narration |
| String | amount |
| String | amountDrCr |
| String | adjusted |
| String | adjustedDrCr |
| String | adjustment |
| String | adjustmentDrCr |
| String | history |
| String | isTDS_TCS |

## RPC interface
| method | params | returns |
|---|---|---|
| getLedger | Filter_Mdl param, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdAdjustment_Mdl>> |
| getTxtHLedger | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHLedger_Mdl>> |
| getTxtHVoucher | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDrCr | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getHistory | String adjDocId, String adjV_Sno | DmResult<List<GrdHistory_Mdl>> |

## Server `AccountAdjustment_SrvImpl.java` (413 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.vNull`, `DmShared.convertDate`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getLedger`
`public DmResultPageing<List<GrdAdjustment_Mdl>> getLedger(Filter_Mdl param, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT l.docId , l.recId , l.v_SNo , l.v_No , l.v_Date , l.refNo , l.refDate , l.narration , ISNULL(l.amtDr, 0) - ISNULL(l.amtCr, 0) AS amount , l.v_Type AS v_TypeCode , vt.short_Name AS v_TypeName , l.subCode AS acCode , sg.name AS acName , sg.manualCode AS acManualCode , sg.cityCode , ct.cityName , vLa.adjusted FROM ledger l LEFT JOIN voucher_Type vt ON vt.v_Type = l.v_Type LEFT JOIN subGroup sg ON sg.subcode = l.subCode LEFT JOIN city ct ON ct.cityCode = sg.cityCode LEFT JOIN ( SELECT la.adj_DocId, la.adj_V_SNo, SUM(la.adjustment) AS adjusted FROM ledgerAdj la GROUP By la.adj_DocId, la.adj_V_SNo ) vLa ON vLa.adj_DocId = l.docId AND vLa.adj_V_SNo = l.v_SNo WHERE l.isPosted = 1 {…}`
- Params: `:recId` ← `param.getVoucherNo() + "%"`; `:v_Date` ← `DmShared.convertDate(param.getVoucherDate())`; `:refNo` ← `param.getRefNo() + "%"`

### `getTxtHLedger`
`public DmResultPageing<List<TxtHLedger_Mdl>> getTxtHLedger(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode , sg.name , sg.manualCode , ct.cityName FROM subGroup sg LEFT JOIN city ct ON ct.cityCode = sg.cityCode LEFT JOIN subGroupSiteList sgl ON sgl.subCode = sg.subCode WHERE sgl.site_Code = :site_Code AND sg.isActive = 1`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHVoucher`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVoucher(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type as code , vt.description as name FROM voucher_Type vt WHERE vt.isActive = 1`

### `getDrCr`
`public DmResultPageing<List<KeyFieldExtended>> getDrCr(DmHelper helper, boolean blnToEndOfFile)`


### `getHistory`
`public DmResult<List<GrdHistory_Mdl>> getHistory(String adjDocId, String adjV_Sno)`

- SQL 1: `SELECT l.docId AS main_DocId , l.recId AS main_VNo , vt.short_Name AS main_VType , vt.mnuText AS main_MnuText , vt.mnuAttachedInModule AS main_MnuAttachedInModule , l.v_Date AS main_VDate , NULL AS adj_DocId , NULL AS adj_VNo , NULL AS adj_VType , NULL AS adj_MnuText , NULL AS adj_MnuAttachedInModule , NULL AS adj_VDate , sg.name AS main_LgName , l.narration , ISNULL(l.amtDr, 0) - ISNULL(l.amtCr, 0) AS amount , NULL AS adjustment FROM ledger l LEFT JOIN voucher_Type vt ON vt.v_Type = l.v_Type LEFT JOIN subGroup sg ON sg.subCode = l.subCode WHERE docId = :adjDocId UNION ALL SELECT b.docId AS main_DocId , b.recId AS main_VNo , vtb.short_Name AS main_VType , vtb.mnuText AS main_MnuText , vtb.mnuAttachedInModule AS main_MnuAttachedInModule , b.v_Date AS main_VDate , l.docId AS adj_DocId , l.recId AS adj_VNo , vtl.short_Name AS adj_VType , vtl.mnuText AS adj_MnuText , vtl.mnuAttachedInModule AS adj_MnuAttachedInModule , l.v_Date AS adj_VDate , sgb.name AS main_LgName , l.narration , NULL AS amount , la.adjustment FROM ledgerAdj la LEFT JOIN ledger l ON l.docId = la.vr_DocId AND l.v_SNo = la.vr_V_SNo LEFT JOIN voucher_Type vtl ON vtl.v_Type = l.v_Type LEFT JOIN subGroup sgl ON sgl.subCode = l.subCode LEFT JOIN ledger b ON b.docId = la.adj_DocId AND b.v_SNo = la.adj_V_SNo LEFT JOIN voucher_Type vtb ON vtb.v_Type = b.v_Type LEFT JOIN subGroup sgb ON sgb.subCode = b.subCode WHERE la.adj_docId = :adjDocId`
- Params: `:adjDocId` ← `DmShared.xNull(adjDocId)`

## Server `AccountAdjustment_Server.java` (352 lines)
Helpers used: `DmServer.convertDate`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `saveRecord`
`public static DmResult<String> saveRecord(AdjustmentSave_Mdl mrTemp, Connection cnTemp)`

- Params: `:vr_DocId` ← `DmShared.foriegnKey(mrTemp.getVr_DocId())`; `:vr_V_SNo` ← `DmShared.foriegnKey(mrTemp.getVr_V_SNo())`; `:vr_SubCode` ← `DmShared.foriegnKey(mrTemp.getVr_SubCode())`; `:vr_V_Date` ← `DmServer.convertDate(mrTemp.getVr_V_Date())`; `:adj_DocId` ← `DmShared.foriegnKey(gaMdl.getSharedKeyFieldCode())`; `:adj_V_SNo` ← `DmShared.foriegnKey(gaMdl.getV_SNo())`; `:adj_SubCode` ← `DmShared.foriegnKey(gaMdl.getAcCode())`; `:adj_V_Date` ← `DmServer.convertDate(gaMdl.getV_Date())`; `:adjustment` ← `DmServer.vNull(-1 * DmShared.vNull(gaMdl.getAdjustment()))`; `:adjustment` ← `DmServer.vNull(gaMdl.getAdjustment())`; `:isTdsTcs` ← `DmShared.iNull(gaMdl.getIsTDS_TCS()).toString()`

### `getAdjustments`
`public static List<GrdAdjustment_Mdl> getAdjustments(String vr_DocId, String vr_V_SNo, Connection cnTemp)`

- SQL 1: `SELECT l.docId , l.recId , l.v_SNo , l.v_No , l.v_Date , l.refNo , l.refDate , l.narration , ISNULL(l.amtDr, 0) - ISNULL(l.amtCr, 0) AS amount , l.v_Type AS v_TypeCode , vt.short_Name AS v_TypeName , l.subCode AS acCode , sg.name AS acName , sg.manualCode AS acManualCode , sg.cityCode , ct.cityName , vLa.adjusted , la.adjustment FROM ledgerAdj la LEFT JOIN ledger l ON l.docId = la.adj_DocId AND l.v_SNo = la.adj_V_SNo LEFT JOIN voucher_Type vt ON vt.v_Type = l.v_Type LEFT JOIN subGroup sg ON sg.subcode = l.subCode LEFT JOIN city ct ON ct.cityCode = sg.cityCode LEFT JOIN ( SELECT la.adj_DocId, la.adj_V_SNo, SUM(la.adjustment) AS adjusted FROM ledgerAdj la WHERE NOT (la.vr_docId = :vr_docId AND la.vr_V_SNo = :vr_V_SNo) GROUP By la.adj_DocId, la.adj_V_SNo ) vLa ON vLa.adj_DocId = l.docId AND vLa.adj_V_SNo = l.v_SNo WHERE la.vr_docId = :vr_docId AND la.vr_V_SNo = :vr_V_SNo`
- Params: `:vr_docId` ← `DmShared.xNull(vr_DocId)`; `:vr_V_SNo` ← `DmShared.xNull(vr_V_SNo)`

### `validateAdjustment`
`public static void validateAdjustment(String vr_DocId, Connection cnTemp)`

- SQL 1: `SELECT COUNT(*) AS cnt FROM ledger l LEFT JOIN ( SELECT la.vr_DocId, la.vr_V_SNo, ISNULL(SUM(la.adjustment), 0) AS totalAdjustment FROM ledgerAdj la WHERE la.vr_DocId = :vr_DocId GROUP BY la.vr_DocId, la.vr_V_SNo ) vLa ON vLa.vr_DocId = l.docId AND vLa.vr_V_SNo = l.v_SNo WHERE l.docId = :vr_DocId AND ABS(ISNULL(l.amtDr, 0) - ISNULL(l.amtCr, 0)) < ABS(vLa.totalAdjustment)`
- SQL 2: `SELECT la.adj_DocId, la.adj_V_SNo FROM ledgerAdj la WHERE la.vr_DocId = :vr_DocId GROUP BY la.adj_DocId, la.adj_V_SNo`
- SQL 3: `SELECT CASE WHEN ISNULL(l.amtDr, 0) - ISNULL(l.amtCr, 0) > 0 AND (ISNULL(l.amtDr, 0) - ISNULL(l.amtCr, 0)) - ISNULL(vLa.totalAdjustment, 0) < 0 THEN 1 WHEN ISNULL(l.amtDr, 0) - ISNULL(l.amtCr, 0) < 0 AND (ISNULL(l.amtDr, 0) - ISNULL(l.amtCr, 0)) + ISNULL(vLa.totalAdjustment, 0) > 0 THEN 1 ELSE 0 END AS error FROM ledger l LEFT JOIN ( SELECT la.adj_DocId, la.adj_V_SNo, SUM(la.adjustment) AS totalAdjustment FROM ledgerAdj la WHERE la.adj_DocId = :adj_DocId AND la.adj_V_SNo = :adj_V_SNo GROUP BY la.adj_DocId, la.adj_V_SNo ) vLa ON vLa.adj_DocId = l.docId AND vLa.adj_V_SNo = l.v_SNo WHERE l.docId = :adj_DocId AND l.v_SNo = :adj_V_SNo`
- Params: `:vr_DocId` ← `DmShared.xNull(vr_DocId)`; `:vr_DocId` ← `DmShared.xNull(vr_DocId)`; `:adj_DocId` ← `DmShared.xNull(rsTemp.getString("adj_DocId"))`; `:adj_V_SNo` ← `DmShared.xNull(rsTemp.getString("adj_V_SNo"))`
- Errors: "You cannot adjust excess amount."

### `deleteRecord`
`public static DmResult<String> deleteRecord(String vrDocId, Connection cnTemp)`

- SQL 1: `DELETE FROM ledgerAdj WHERE vr_DocId = :vr_DocId`
- Params: `:vr_DocId` ← `DmShared.iNull(vrDocId).longValue()`

### `deleteAdjustment`
`public static DmResult<String> deleteAdjustment(String vrDocId, String vrVSNo, String adjDocId, String adjVSNo, Connection cnTemp)`

- SQL 1: `DELETE FROM ledgerAdj WHERE vr_DocId = :vr_DocId AND adj_DocId = :adj_DocId {…}`
- Params: `:vr_DocId` ← `DmShared.iNull(vrDocId).longValue()`; `:adj_DocId` ← `DmShared.iNull(adjDocId).longValue()`; `:vr_V_SNo` ← `DmShared.iNull(vrVSNo).intValue()`; `:adj_V_SNo` ← `DmShared.iNull(adjVSNo).intValue()`
- Errors: "Voucher ID cannot be blank!" | "Adjustment ID cannot be blank!"

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.accountAdjustment.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.self.client`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
