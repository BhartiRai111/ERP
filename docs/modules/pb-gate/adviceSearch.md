# pb-gate / adviceSearch

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/gate/src/adviceSearch/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: -
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `AdviceSearch`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmGridPaging | `gridAdviceDetail` | `GrdPaging_PrpAcs, 13, false` |
| 2 | - | DmTextBox | `txtSearchValue` | `50, false` |

**Grid / popup columns** (header, width, db field):
- `gridAdviceDetail`: Order No. [Text, w150, ISNULL(orderNo,'')]; Party [Text, w150, PartyName]; Item Name [Text, w150, itemName]; Properties [Text, w150]; Pack Size [Text, w150, packSizeName]; Pack Count [Text, w100, packCount]; Approx Qty. [Text, w100, approxWt]; Unit [Text, w60, unitName]

**Lookup / suggestion / find sources (server RPC called):**
- `gridAdviceDetail` → `getAdviceDetail`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`gridAdviceDetail.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(false).getAdviceDetail(gateDocId, gateAdviceVsno, searchValue_Mdl, helper, blnToEndOfFile, new AsyncC
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdAdviceDetails_Mdl>> result) {
if (result.isStatus()) {
gridAdviceDetail.setData(result.getData(), result.getPaging());
```

`gridAdviceDetail.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
gridAdviceDetail.setData(result.getData(), result.getPaging());
```

`gridAdviceDetail.addCallBack` → `getParam()`
```java
return valueIntialize();
```
- Alert: "Please select gate Advice detail"

## Files
| file | lines |
|---|---|
| client/AdviceSearch.java | 287 |
| client/AdviceSearch_Dsg.java | 201 |
| server/AdviceSearch_SrvImpl.java | 165 |
| shared/AdviceSearch_Srv.java | 12 |
| shared/AdviceSearch_SrvAsync.java | 12 |
| shared/GrdAdviceDetails_Mdl.java | 252 |
| shared/SearchValue_Mdl.java | 32 |

## Models (shared DTOs)
### GrdAdviceDetails_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | poDocId |
| String | poV_SNo |
| String | poRecid |
| String | itemCode |
| String | itemName |
| String | unitCode |
| String | unitName |
| String | adviceApproxWt |
| String | propertyList |
| String | propertyValueCsv |
| String | partyName |
| ItemProperty_Mdl | ipm |
| String | itemType |
| String | isBestBefore |
| String | purchaseUnitConversionRate |
| String | skuCode |
| String | skuName |
| String | packSizeCode |
| String | packSizeName |
| String | packUnitCode |
| String | packUnitName |
| String | packUnitConversionRate |
| String | packSizeSkuCode |
| String | packSizeSkuName |
| String | packUnitDecimalPlaces |
| String | packCount |

### SearchValue_Mdl (extends `KeyField implements Serializable,IsSerializable`)
| type | field |
|---|---|
| String | fieldName |
| String | fieldNameValue |

## RPC interface
| method | params | returns |
|---|---|---|
| getAdviceDetail | String docId, String gateAdviceVsno, SearchValue_Mdl searchValue_Mdl, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdAdviceDetails_Mdl>> |

## Server `AdviceSearch_SrvImpl.java` (165 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.vNull`, `DmShared.getNumberFormat`, `DmShared.iNull`, `DmShared.xNull`

### `getAdviceDetail`
`public DmResultPageing<List<GrdAdviceDetails_Mdl>> getAdviceDetail(String gateDocId, String gateAdviceVsno, SearchValue_Mdl searchValueMdl, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select gad.docId as gateDocId, gad.v_Sno, po.v_Date, vt.short_Name, po.v_No , gad.poDocId, gad.poV_SNo, po.recId as orderNo , gad.itemCode,im.name as itemName, im.purchaseUnitConversionRate , gad.approxWt as approxQty , im.propertyList , isNull(im.isBestBefore,0) as isBestBefore , it.manualCode as itemType , sc.name as PartyName , city.cityName as cityName , gad.ibCode , um.unit as unitName, gad.unitCode as unitCode , im.sku AS skuCode , ums.unit AS skuName , gad.packSizeCode , gad.packCount , psm.name as packSizeName , psm.sku as packSizeSku , psm.packUnitCode , psm.packUnitConversionRate , umPsm.unit as packUnitName , umPsmSku.unit as packSizeSkuName , umPsm.decimalPlaces as packUnitDecimalPlaces from gateAdviceDetail gad LEFT JOIN unloadingDetail uld ON uld.gateDocId = gad.docId AND uld.gateAdviceV_Sno = gad.v_Sno LEFT JOIN gateInOut gio ON gio.docId = gad.docId LEFT JOIN unitMast um ON um.code = gad.unitCode LEFT JOIN pOrder po ON po.docId = gad.poDocId LEFT JOIN itemMast im ON im.code = gad.itemCode LEFT JOIN unitmast ums ON im.sku = ums.code LEFT JOIN itemType it on it.code = im.itemType LEFT JOIN subGroup sc ON sc.subCode = po.subCode LEFT JOIN city on city.cityCode = sc.cityCode LEFT JOIN voucher_Type vt ON vt.v_Type = gio.v_Type LEFT JOIN packSizeMast psm on psm.code = gad.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku WHERE vt.category=(' {…} ') AND gad.docId = :docId {…}`
- Params: `:docId` ← `DmShared.iNull(gateDocId).toString()`; `:gateAdviceVsno` ← `DmShared.xNull(gateAdviceVsno)`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `ItemBatch_Server.getIPM`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmToolContainer.client`, `paperbiz.gate.adviceSearch.shared`, `paperbiz.selfToolkit.itemBatch.server`, `paperbiz.selfToolkit.itemPropertyPopup.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
