# pb-self-toolkit / reelBundleSearchPopup

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/reelBundleSearchPopup/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHDepartment, getTxtHOrderNo, getTxtHReelNo
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `GridEditPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Bundle(s) | DmTextBoxNumeric | `txtPackCount` | `4, false, 12, true` |
| 2 | Weight | DmTextBoxNumeric | `txtWeight` | `4, false, 12, true` |

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtWeight.setEnabled(false)`
- `txtPackCount.setEnabled(flag)`

**`manageDisplay()` logic (condensed code):**
```java
boolean flag = this.mode.equalsIgnoreCase("New") || this.mode.equalsIgnoreCase("Edit");
txtWeight.setEnabled(false);
txtPackCount.setEnabled(flag);
```
- Alert: "Bundle is mandatory."
- Alert: "Bundle("
- Alert: "Weight is mandatory."
- Alert: "Weight("

### Screen `ReelBundleSearchPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Show Used Reels | DmGridPaging | `reelSearchGrid` | `GrdPaging_PrpAcs, 13, false` |
| 2 | - | DmTextBox | `txtSearchValue` | `50, false` |
| 3 | SNo. | DmTextHelp | `txtHDepartment` | `KeyField_PrpAcs, 5, true` |
| 4 | - | DmTextHelp | `txtHOrderNo` | `TxtHOrderNo_PrpAcs, 6, true` |
| 5 | - | DmTextBox | `txtOrderV_SNo` | `10, false` |
| 6 | Item Name | DmTextBox | `txtItem` | `250, false` |
| 7 | Properties | DmTextBox | `txtProperties` | `250, false` |
| 8 | - | DmCheckBox | `cbShowUsedReels` | `` |

Other widgets (not in tab list): `txtHBarcode` (DmTextBox), `txtRate` (DmTextBox), `checkUncheckAll` (DmCheckBox)

**Grid / popup columns** (header, width, db field):
- `txtHDepartment`: Name [Text, w260, name]
- `txtHOrderNo`: Order No. [Text, w120, recId]; Order Date [Text, w80, replace(convert(varchar, soDate, 106), ' ','-')]; ItemName [Text, w220, itemName]; BF [Text, w50, bfCode]; GSM [Text, w50, gsmCode]; Width [Text, w60, sizeWidth]; Length [Text, w60, sizeLength]; Size Unit [Text, w60, sizeUnitName]; Pending Qty [Text, w100, pendingQty]
- `reelSearchGrid`:  [Text, w30, isChecked]; Reel/Bundle No. [Text, w120, name]; SO Date [Text, w80, replace(convert(varchar, soV_Date, 106), ' ','-')]; Bill No. [Text, w120, pBillRecId]; Bill Date [Text, w80, replace(convert(varchar, pBillDate, 106), ' ','-')]; Weight [Text, w100, quantity]; Reel/Bundle(s) [Text, w100, packCount]; Godown [Text, w120, departmentName]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHDepartment` → `getTxtHDepartment`
- `txtHOrderNo` → `getTxtHOrderNo`
- `reelSearchGrid` → `getTxtHReelNo`

**Enable/disable rules** (`manageDIsplay(flag)`, flag = editing mode):
- `cbShowUsedReels.setVisible(false)`
- `lblShowUsedReels.setVisible(false)`
- `cbShowUsedReels.setVisible(true)`
- `lblShowUsedReels.setVisible(true)`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHDepartment.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHDepartment(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Key
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDepartment).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHDepartment.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDepartment).show();
```

`txtHDepartment.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHDepartment.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDepartment).show();
```

`txtHDepartment.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHDepartment.getTag()).equalsIgnoreCase(DmShared.xNull(txtHDepartment.getTag2nd()))) {
clearPartially();
txtHOrderNo.clear();
txtOrderV_SNo.clear();
txtHDepartment.setTag2nd(DmShared.xNull(txtHDepartment.getTag()));
svm.setDepartmentCode(DmShared.xNull(txtHDepartment.getTag()));
svm.setDepartmentName(DmShared.xNull(txtHDepartment.getText()));
reQuery();
```

`txtHOrderNo.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHOrderNo(hvm, helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<T
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHOrderNo).show();
public void onSuccess(DmResultPageing<List<TxtHSaleOrder_Mdl>> result) {
if (!result.isStatus()) {
result.getData().clear();
new DmAlertMessageBox(result.getErrorMessage(), txtHOrderNo).show();
txtHOrderNo.setData(result.getData(), result.getPaging());
```

`txtHOrderNo.addTextHelpCallBack` → `onSuccess()`
```java
if (!result.isStatus()) {
result.getData().clear();
new DmAlertMessageBox(result.getErrorMessage(), txtHOrderNo).show();
txtHOrderNo.setData(result.getData(), result.getPaging());
```

`txtHOrderNo.addTextHelpCallBack` → `getSelectedRow()`
```java
selectedOrderNo_Mdl = t1Row;
boolean flagChange = !DmShared.xNull(txtHOrderNo.getTag()).equalsIgnoreCase(DmShared.xNull(txtHOrderNo.getTag2nd()))
|| DmShared.iNull(txtOrderV_SNo.getText()).intValue() != DmShared.iNull(t1Row.getV_SNo()).intValue();
if (flagChange) {
clearPartially();
manageGrodColumns();
txtHOrderNo.setTag2nd(DmShared.xNull(txtHOrderNo.getTag()));
txtOrderV_SNo.setText(t1Row.getV_SNo());
svm.setSoDocId(DmShared.xNull(txtHOrderNo.getTag()));
svm.setSoRecId(DmShared.xNull(txtHOrderNo.getText()));
...
```

`gridEditPopup.addCallBack` → `onOk()`
```java
reelSearchGrid.getDmGrid().commitChanges();
```

`reelSearchGrid.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(false).getTxtHReelNo(hvm, svm, helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<ReelBun
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<ReelBundleSearchPopupGrid_Mdl>> result) {
if (!result.isStatus()) {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
reelSearchGrid.setData(result.getData(), result.getPaging());
reelSearchGrid.getDmGrid().commitChanges();
checkUncheckWidget(false);
Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
...
```

`reelSearchGrid.addCallBack` → `onSuccess()`
```java
if (!result.isStatus()) {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
reelSearchGrid.setData(result.getData(), result.getPaging());
reelSearchGrid.getDmGrid().commitChanges();
checkUncheckWidget(false);
Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
public void execute() {
int checkedCount = 0;
try {
List<ReelBundleSearchPopupGrid_Mdl> grid_MdlList = reelSearchGrid.getDmGrid().getGrid().getStore().getAll();
...
```

`reelSearchGrid.addCallBack` → `execute()`
```java
int checkedCount = 0;
try {
List<ReelBundleSearchPopupGrid_Mdl> grid_MdlList = reelSearchGrid.getDmGrid().getGrid().getStore().getAll();
if (grid_MdlList.size() > 0) {
for (ReelBundleSearchPopupGrid_Mdl grid_Mdl : reelSearchGrid.getDmGrid().getGrid().getStore().getAll()) {
if (!DmShared.xNull(grid_Mdl.getSharedKeyFieldCode()).trim().equalsIgnoreCase("")) {
if (DmShared.iNull(grid_Mdl.getIsChecked()).intValue() == 1) {
++checkedCount;
} else {
grid_MdlList.remove(grid_Mdl);
...
```

`reelSearchGrid.addCallBack` → `getParam()`
```java
return valueIntialize();
```

## Files
| file | lines |
|---|---|
| client/GridEditPopup.java | 164 |
| client/GridEditPopup_Dsg.java | 138 |
| client/ReelBundleSearchPopup.java | 587 |
| client/ReelBundleSearchPopup_Dsg.java | 531 |
| server/ReelBundleSearchPopup_SrvImpl.java | 502 |
| shared/HeaderValue_Mdl.java | 139 |
| shared/ReelBundleSearchPopupFilter_Mdl.java | 156 |
| shared/ReelBundleSearchPopupGrid_Mdl.java | 290 |
| shared/ReelBundleSearchPopup_Srv.java | 18 |
| shared/ReelBundleSearchPopup_SrvAsync.java | 18 |
| shared/TxtHItemName_Mdl.java | 250 |
| shared/TxtHSaleOrder_Mdl.java | 177 |

## UI labels / columns (client)
`Bundle(s)`, `Weight`, `Reel/Bundle No.`, `Show Used Reels`, `Godown`, `Order No.`, `SNo.`, `Item Name`, `Properties`

## Models (shared DTOs)
### TxtHItemName_Mdl (extends `FullPaperProperty_Mdl implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | itsBasedOn |
| String | itemUnitCode |
| String | itemUnitName |
| String | itemUnitDisplayName |
| String | itemUnitDecimalPlaces |
| String | skuUnitDecimalPlaces |
| String | itemUnitConversionRate |
| String | itemdesc |
| String | tl_ExcessPer |
| String | tl_ShortagePer |
| String | hsnCode |
| String | hsnNo |
| String | itemGstNature |
| boolean | saleDiscountAllow |
| boolean | saleRateEditable |
| String | isubCode |
| String | salesAc |
| String | skuName |
| String | skuCode |
| String | gstUnitCode |
| String | balancingAc |
| String | itemType |
| String | categoryCode |
| String | categoryName |
| String | currentStock |

### ReelBundleSearchPopupFilter_Mdl
| type | field |
|---|---|
| String | fieldName |
| String | fieldNameValue |
| String | departmentCode |
| String | departmentName |
| String | soDocId |
| String | soV_SNo |
| String | soRecId |
| String | ibCode |
| String | itemCode |
| String | rate |
| boolean | showUsedReels |
| String | selectedItemIdCSV |
| String | usedItemIdCSVInSI |
| boolean | clearFilter |
| List<ReelBundleSearchPopupGrid_Mdl> | rbDetail_Mdls |

### TxtHSaleOrder_Mdl (extends `FullPaperProperty_Mdl implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | v_SNo |
| String | date |
| String | soRefNo |
| String | soRefDate |
| String | qty |
| String | pendingQty |
| String | rate |
| String | VTypeShortName |
| String | unitName |
| String | itemDesc |
| String | remark |
| String | deliveryDate |
| String | consigneeCode |
| String | consigneeName |
| String | deliveryToCode |
| String | deliveryToName |
| String | deliveryCityCode |
| String | deliveryCityName |

### HeaderValue_Mdl
| type | field |
|---|---|
| String | docId |
| String | v_Type |
| String | vCat |
| String | v_Date |
| String | partyCode |
| String | consigneeCode |
| String | deliveryToCode |
| String | deliveryCityCode |
| String | itemNature |
| String | departmentCode |
| String | directSupply |
| String | pBillDocId |
| String | pBillRecId |
| String | mode |

### ReelBundleSearchPopupGrid_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | barCode |
| String | soDocId |
| String | soV_SNo |
| String | soRecId |
| String | soV_Date |
| String | PBillDocId |
| String | PBillRecId |
| String | PBill_Date |
| String | departmentCode |
| String | departmentName |
| String | itemNatureCode |
| String | itemNatureName |
| String | itemNatureManualCode |
| String | itemCode |
| String | itemName |
| String | ibCode |
| String | bfCode |
| String | gsmCode |
| String | sizeWidth |
| String | sizeLength |
| String | sizeUnitCode |
| String | sizeUnitName |
| String | grain |
| String | weight |
| String | packCount |
| String | totalWeight |
| String | totalPackCount |
| String | rate |
| String | amount |
| String | isChecked |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHDepartment | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHOrderNo | HeaderValue_Mdl hvm, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHSaleOrder_Mdl>> |
| getTxtHReelNo | HeaderValue_Mdl hvm, ReelBundleSearchPopupFilter_Mdl svm, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<ReelBundleSearchPopupGrid_Mdl>> |

## Server `ReelBundleSearchPopup_SrvImpl.java` (502 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT dm.subCode AS code, sg.name FROM departmentMast dm LEFT JOIN subGroup sg ON sg.subCode = dm.subCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature LEFT JOIN subGroupSiteList as sgsl on sg.subCode=sgsl.subCode WHERE sgn.manualCode IN (:CM,:GD) AND sgsl.site_Code = :siteCode AND sg.isActive = :isActive {…}`
- Params: `:CM` ← `"CM"`; `:GD` ← `"GD"`; `:siteCode` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getTxtHOrderNo`
`public DmResultPageing<List<TxtHSaleOrder_Mdl>> getTxtHOrderNo(HeaderValue_Mdl hvm, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sod.docId, sod.v_SNo , so.v_Date AS v_Date, so.recId AS recId , sod.itemCode AS itemCode , im.name AS itemName , sod.ibCode AS ibCode , imn.name AS itemNatureName , ib.bfCode AS bfCode , ib.gsmCode AS gsmCode , ib.sizeWidth AS sizeWidth , ib.sizeLength AS sizeLength , umsu.unit AS sizeUnitName , ib.sheetsPerReam AS sheetsPerReam , ib.reamsPerBundle AS reamsPerBundle , ib.reamWeight AS reamWeight , ib.propertyDispCsv AS propertyDispCsv , sod.quantity AS qty , (sod.quantity - ISNULL(sod.dispatchedSkuQty, 0) + ISNULL(Vsbd.sbQty, 0)) AS pendingQty , sod.rate , IIF(ISNULL(Vsbd.sbQty, 0) > 0, 0, 1) AS orderIndex FROM sOrder so LEFT JOIN sOrderDetail sod ON sod.docId = so.docId LEFT JOIN itemMast im ON im.code = sod.itemCode LEFT JOIN itemBatch ib ON ib.code = sod.ibCode LEFT JOIN itemNature imn ON imn.code = ib.itemNature LEFT JOIN unitMast umsu ON umsu.code = ib.sizeUnitCode LEFT JOIN voucher_Type vt ON so.v_Type = vt.v_Type LEFT JOIN (SELECT soDocId, soV_SNo, SUM(quantity) AS sbQty FROM sBillDetail sbd WHERE {…} GROUP BY sbd.soDocId, sbd.soV_SNo ) Vsbd ON Vsbd.soDocId = sod.docId AND Vsbd.soV_SNo = sod.v_SNo WHERE 1 = 1 AND sod.closingStatus = :closingStatus AND ( (sod.quantity - ISNULL(sod.dispatchedSkuQty, 0)) > 0 OR ISNULL(Vsbd.sbQty, 0) > 0 -- Edit Mode ) AND ISNULL(so.approvedBy,'') <> '' AND ISNULL(sod.isFooter,0) = 0 AND vt.category = :category AND so.v_Date <= :v_Date AND so.site_Code = :site_Code {…}`
- Params: `:category` ← `pvc.SALES_ORDER`; `:v_Date` ← `DmServer.convertDate(hvm.getV_Date())`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:partyCode` ← `DmShared.iNull(hvm.getPartyCode()).toString()`; `:docId` ← `DmShared.xNull(hvm.getDocId())`; `:itemNature` ← `DmShared.xNull(hvm.getItemNature())`; `:partyCode` ← `DmShared.xNull(hvm.getPartyCode())`; `:deliveryTo` ← `DmShared.xNull(hvm.getDeliveryToCode())`
- Other calls: `OPEN.getCode`

### `getTxtHReelNo`
`public DmResultPageing<List<ReelBundleSearchPopupGrid_Mdl>> getTxtHReelNo(HeaderValue_Mdl hvm, ReelBundleSearchPopupFilter_Mdl svm, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT bcm.reelBundleNo AS code, bcm.reelBundleNo AS name, bcm.barCodeId, bcm.barcode , Vsiid.docId AS siDocId , iis.soDocId, iis.soV_SNo , so.recId AS soRecId, so.v_Date AS soDate, so.v_No AS soV_No , sod.itemDesc , (ISNULL(Vsiid.weight, 0) + iis.balanceQty) AS quantity , (ISNULL(Vsiid.packCount, 0) + iis.balancePcs) AS packCount , IIF(ISNULL(iis.soDocId, '') <> '', sod.rate, 0) AS rate , im.saleUnit AS unitCode, um.unit as unitName , im.saleUnitConversionRate , um.decimalPlaces AS saleUnitDecimalPlaces , pb.docId AS pBillDocId, pb.recId AS pBillRecId, pb.v_Date AS pBillDate , iis.departmentCode, sgd.name AS departmentName {…} , (CASE WHEN {…} THEN '1' ELSE '2' END) AS isChecked , IIF(ISNULL(Vsiid.docId, '') <> '', 1, 2) AS orderByIndexSI , IIF(ISNULL(iis.soDocId, '') <> '', 1, 2) AS orderByIndexSO FROM itemIdStock iis LEFT JOIN stockItemId sii ON sii.itemId = iis.itemId AND sii.ibCode = iis.ibCode AND sii.itemCode = iis.itemCode AND sii.departmentCode = iis.departmentCode AND sii.site_Code = iis.site_Code AND sii.soDocId = iis.soDocId AND sii.soV_SNo = iis.soV_SNo LEFT JOIN voucher_Type vtIIS ON vtIIS.v_Type = iis.v_Type LEFT JOIN pChallanDetail pcd ON pcd.docId = sii.docId AND pcd.v_SNo = sii.v_Sno AND vtIIS.category IN (' {…} ', ' {…} ') LEFT JOIN pBillDetail pbd ON ( (vtIIS.category IN (' {…} ', ' {…} ') AND pbd.docId = sii.docId AND pbd.v_SNo = sii.v_Sno) OR (vtIIS.category IN (' {…} ', ' {…} ') AND pbd.pcDocId = pcd.docId AND pbd.pcV_SNo = pcd.v_SNo) ) LEFT JOIN pBill pb ON pb.docId = pbd.docId LEFT JOIN pOrderDetail pod ON pod.docId = pbd.poDocId AND pod.v_SNo = pbd.poV_SNo LEFT JOIN (SELECT siid.docId, siid.itemId, rbd.weight, rbd.packCount FROM stockItemId siid LEFT JOIN sBillRBDetail rbd ON rbd.docId = siid.docId AND rbd.v_SNo = siid.v_Sno AND rbd.itemId = siid.itemId WHERE 1 = 1 {…} ) Vsiid ON Vsiid.itemId = iis.itemId LEFT JOIN barCodeMast bcm ON bcm.barCodeId = iis.itemId LEFT JOIN itemMast im ON im.code = iis.itemCode LEFT JOIN itemBatch ib ON ib.code = iis.ibCode LEFT JOIN itemBatch soIb ON soIb.code = :ibCode LEFT JOIN itemNature soIbImn ON soIbImn.code = soIb.itemNature LEFT JOIN itemNature ina ON ina.code = ib.itemNature LEFT JOIN unitMast dsum ON dsum.code = ib.sizeUnitCode LEFT JOIN subGroup sgd ON sgd.subCode = iis.departmentCode -- Sale Order LEFT JOIN sOrderDetail sod ON sod.docId = iis.soDocId AND sod.v_SNo = iis.soV_SNo LEFT JOIN sOrder so ON so.docId = sod.docId LEFT JOIN unitMast um ON um.code = IIF(ISNULL(iis.soDocId, '') <> '', sod.unitCode, im.saleUnit) LEFT JOIN voucher_Type vt ON so.v_Type = vt.v_Type LEFT JOIN subGroup sgCon ON sgCon.SubCode = so.consignee LEFT JOIN city cityCon ON cityCon.cityCode = sgCon.cityCode LEFT JOIN subGroup sgDelTo ON sgDelTo.SubCode = so.deliveryTo LEFT join city dc on dc.cityCode=so.deliveryCity WHERE 1 = 1 {…} AND iis.site_Code = :site_Code AND ISNULL(sii.recQty, 0) > 0 AND (CASE WHEN ISNULL(so.v_Date, '') <> '' THEN so.v_Date ELSE :v_Date END) <= :v_Date -- Sale Invoice Edit Mode 'OR' Condition AND (ISNULL(iis.balanceQty, 0) > 0 OR (ISNULL(iis.balanceQty, 0) = 0 AND ISNULL(Vsiid.docId, '') <> '')) --Sale Order Filter Conditions AND (ISNULL(iis.soDocId, '') = '' OR (iis.soDocId = :soDocId AND iis.soV_SNo = :soV_SNo) ) AND ( (soIb.itemNature = 1 AND iis.ibCode = soIb.code) OR ( soIbImn.manualCode = ' {…} ' AND ib.itemNature = soIb.itemNature AND ib.itemCode = soIb.itemCode AND ib.bfCode = soIb.bfCode AND ib.gsmCode = soIb.gsmCode AND ib.sizeWidth = soIb.sizeWidth AND ib.sizeLength = soIb.sizeLength AND ib.sizeUnitCode = soIb.sizeUnitCode AND (soIb.sheetsPerReam = 0 OR soIb.sheetsPerReam = ib.sheetsPerReam) AND (soIb.reamWeight = 0 OR soIb.reamWeight = ib.reamWeight) AND (soIb.reamsPerBundle = 0 OR soIb.reamsPerBundle = ib.reamsPerBundle) AND ISNULL(soIb.grain, '') = ISNULL(ib.grain, '') ) )`
- SQL 2: `selectedItemId {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Date` ← `DmServer.convertDate(DmShared.dNull(hvm.getV_Date()))`; `:soDocId` ← `DmShared.xNull(svm.getSoDocId())`; `:ibCode` ← `DmShared.xNull(svm.getIbCode())`; `:departmentCode` ← `DmShared.xNull(svm.getDepartmentCode())`; `:docId` ← `DmShared.xNull(hvm.getDocId())`; `:pbDocId` ← `DmShared.xNull(hvm.getpBillDocId())`
- Other calls: `ItemBatch_Server.getFullPaperPropertiesField`, `ItemBatch_Server.setPaperPropertiesPreparedStatementValue`, `SHEET.getManualCode`

### `getPaperPropertiesAlias`
`private HashMap getPaperPropertiesAlias(String transTableAlias)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmCheckBox.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `paperbiz.selfToolkit.itemBatch.server`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.reelBundleSearchPopup.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
