# pb-purchase / grnSearch

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/purchase/src/grnSearch/`). Hand-written notes are in the "Notes" section.

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
### Screen `GrnSearch`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmGridPaging | `gridGrnDetail` | `GrdPaging_PrpAcs, 10, false` |
| 2 | - | DmTextBox | `txtSearchValue` | `50, false` |

**Grid / popup columns** (header, width, db field):
- `gridGrnDetail`:  [Text, w30]; Item Name [Text, w140, itemName]; Unit [Text, w50, unitName]; GRN Qty. [Text, w80, acceptQty]; Pending Qty. [Text, w80, pendingQty]; Remark [Text, w150, remark]; GRN No. [Text, w80, pcRecId]; GRN Date [Text, w75, pcV_Date]

**Lookup / suggestion / find sources (server RPC called):**
- `gridGrnDetail` → `getItemDetail`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`gridGrnDetail.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(true).getItemDetail(v_Type, docId, subCode, v_Date, vCategory, searchValue_Mdl, helper, blnToEndOfFil
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdGrnDetails_Mdl>> result) {
if (result.isStatus()) {
gridGrnDetail.setData(result.getData(), result.getPaging());
List<GrdGrnDetails_Mdl> listIndentDetails = gridGrnDetail.getDmGrid().getGrid().getStore().getAll();
for (int i = 0; i < keyFields.size(); i++) {
KeyFieldExtended kf = keyFields.get(i);
for (int j = 0; j < listIndentDetails.size(); j++) {
...
```

`gridGrnDetail.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
gridGrnDetail.setData(result.getData(), result.getPaging());
List<GrdGrnDetails_Mdl> listIndentDetails = gridGrnDetail.getDmGrid().getGrid().getStore().getAll();
for (int i = 0; i < keyFields.size(); i++) {
KeyFieldExtended kf = keyFields.get(i);
for (int j = 0; j < listIndentDetails.size(); j++) {
GrdGrnDetails_Mdl details_Mdl = listIndentDetails.get(j);
if ((DmShared.xNull(kf.getSharedKeyFieldCode()).trim().equalsIgnoreCase(DmShared.xNull(details_Mdl.getSharedKeyFieldCode
&& (DmShared.xNull(kf.getSharedKeyFieldName()).trim().equalsIgnoreCase(DmShared.xNull(details_Mdl.getSharedKeyFieldName(
gridGrnDetail.getDmGrid().getGrid().getStore().get(j).setIsChecked("1");
...
```

`gridGrnDetail.addCallBack` → `getParam()`
```java
return valueIntialize();
```

## Files
| file | lines |
|---|---|
| client/GrnSearch.java | 276 |
| client/GrnSearch_Dsg.java | 222 |
| server/GrnSearch_SrvImpl.java | 145 |
| shared/GrdGrnDetails_Mdl.java | 89 |
| shared/GrnSearch_Srv.java | 12 |
| shared/GrnSearch_SrvAsync.java | 12 |
| shared/SearchValue_Mdl.java | 68 |
| shared/SettingValue_Mdl.java | 21 |

## Models (shared DTOs)
### GrdGrnDetails_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | pcV_SNo |
| String | pcV_Date |
| String | pcQty |
| String | pendingQty |
| String | itemName |
| String | unitName |
| String | remark |
| String | isChecked |

### SearchValue_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | fromDate |
| String | toDate |
| String | departmentTag |
| String | departmentText |
| String | fieldName |
| String | fieldNameValue |

### SettingValue_Mdl
| type | field |
|---|---|
| String | pendding |

## RPC interface
| method | params | returns |
|---|---|---|
| getItemDetail | String v_Type, String docId, String subCode, String v_Date, String vCategory, SearchValue_Mdl searchValue_Mdl, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdGrnDetails_Mdl>> |

## Server `GrnSearch_SrvImpl.java` (145 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getItemCatFilteration`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.xNull`

### `getItemDetail`
`public DmResultPageing<List<GrdGrnDetails_Mdl>> getItemDetail(String v_Type, String docId, String subCode, String v_Date, String vCategory, SearchValue_Mdl searchValueMdl, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT pc.docId AS pcDocId , pc.recId AS pcRecId , pc.v_Date AS pcV_Date , pcd.v_SNo AS pcV_Sno , im.name AS itemName , um.unit AS unitName , pcd.remark , pcd.acceptQty {…} FROM pChallanDetail pcd LEFT JOIN pChallan pc ON pcd.docId = pc.docId`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:subCode` ← `DmShared.iNull(subCode).toString()`; `:docId` ← `DmShared.xNull(docId)`; `:v_Date` ← `DmServer.convertDate(v_Date)`; `:category` ← `DmShared.xNull(pvc.GENERAL_PURCHASE_CHALLAN)`; `:category` ← `DmShared.xNull(pvc.GENERAL_PURCHASE_CHALLAN_UNREGISTERED)`; `:category` ← `DmShared.xNull(pvc.SERVICE_PURCHASE_CHALLAN)`; `:category` ← `DmShared.xNull(pvc.SERVICE_PURCHASE_CHALLAN_UNREGISTERED)`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmToolContainer.client`, `paperbiz.purchase.grnSearch.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
