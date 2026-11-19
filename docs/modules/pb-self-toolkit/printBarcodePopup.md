# pb-self-toolkit / printBarcodePopup

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/printBarcodePopup/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: -
- CSV export: no

## Files
| file | lines |
|---|---|
| client/PrintBarcodePopup.java | 351 |
| client/PrintBarcodePopup_Dsg.java | 236 |
| server/PrintBarcodePopup_SrvImpl.java | 207 |
| shared/PrintBarcodePopupGrid_Mdl.java | 33 |
| shared/PrintBarcodePopup_Srv.java | 14 |
| shared/PrintBarcodePopup_SrvAsync.java | 14 |

## Models (shared DTOs)
### PrintBarcodePopupGrid_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | reelBundleNo |
| String | isChecked |

## RPC interface
| method | params | returns |
|---|---|---|
| getReelDetail | String v_Type, String docId, String barcodeId, String reelBundleNo | DmResult<List<PrintBarcodePopupGrid_Mdl>> |
| printBarcode | String v_Type, String docId, String itemIdCSV | DmResult<String> |

## Server `PrintBarcodePopup_SrvImpl.java` (207 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.fillJasper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.showJasper`, `DmShared.xNull`

### `getReelDetail`
`public DmResult<List<PrintBarcodePopupGrid_Mdl>> getReelDetail(String v_Type, String docId, String barCode, String reelBundleNo)`

- SQL 1: `SELECT stk.docId, stk.v_SNo, sid.itemId AS code , bcm.barCode AS name, bcm.reelBundleNo FROM stock stk LEFT JOIN voucher_Type vt ON vt.v_Type = stk.v_Type LEFT JOIN stockItemId sid ON sid.docId = stk.docId AND sid.v_SNo = stk.v_SNo LEFT JOIN barCodeMast bcm ON bcm.barCodeId = sid.itemId WHERE 1 = 1 AND stk.docId = :docId AND ISNULL(sid.recQty, 0) > 0 {…}`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:barCode` ← `DmShared.xNull(barCode) + "%"`; `:reelBundleNo` ← `DmShared.xNull(reelBundleNo) + "%"`

### `printBarcode`
`public DmResult<String> printBarcode(String v_Type, String docId, String itemIdCSV)`

- SQL 1: `SELECT stk.docId, stk.v_SNo , stk.recId, stk.v_Date , stk.v_Type, vt.category, vt.short_Name, vt.description , stk.partyCode, sg.name AS partyName , stk.soDocId, stk.soV_SNo, so.recId AS soRecId, so.v_Date AS soV_Date , stk.poDocId, stk.poV_SNo, po.recId AS poRecId, po.v_Date AS poV_Date , sid.itemId AS barcodeId, bcm.barCode, bcm.reelBundleNo , stk.itemCode, im.name AS itemName , ib.bfCode, ib.gsmCode , ib.sizeWidth, ib.sizeLength , ib.sizeUnitCode, um.unit AS sizeUnitName , sid.recQty, sid.recPcs AS recPackCount , ib.sheetsPerReam, ib.reamsPerBundle, ib.reamWeight , stk.remark , sid.departmentCode, sgd.name AS departmentName FROM stock stk LEFT JOIN voucher_Type vt ON vt.v_Type = stk.v_Type LEFT JOIN subGroup sg ON sg.subCode = stk.partyCode LEFT JOIN sOrderDetail sod ON sod.docId = stk.soDocId AND sod.v_SNo = stk.soV_SNo LEFT JOIN sOrder so ON so.docId = sod.docId LEFT JOIN pOrderDetail pod ON pod.docId = stk.poDocId AND pod.v_SNo = stk.poV_SNo LEFT JOIN pOrder po ON po.docId = pod.docId LEFT JOIN stockItemId sid ON sid.docId = stk.docId AND sid.v_SNo = stk.v_SNo LEFT JOIN barCodeMast bcm ON bcm.barCodeId = sid.itemId LEFT JOIN itemBatch ib ON ib.code = stk.ibCode LEFT JOIN itemMast im ON im.code = stk.itemCode LEFT JOIN unitMast um ON um.code = ib.sizeUnitCode LEFT JOIN subGroup sgd ON sgd.subCode = sid.departmentCode WHERE 1 = 1 AND ISNULL(sid.recQty, 0) > 0 {…}`
- Params: `:docId` ← `docId`
- Other calls: `STServer.setJasperVariables`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmCheckBox.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.documentSystemReport.client`, `paperbiz.selfToolkit.printBarcodePopup.shared`, `paperbiz.selfToolkit.stStatic.server`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
