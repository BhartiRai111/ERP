# pb-self-toolkit / generalPurchaseOrderApproval

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/generalPurchaseOrderApproval/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: pOrderDetail, pOrder, IndentDetail
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHStatus, getTxtHPurchaseOrder, getTxtHSupplierPT, getTxtHItem
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `GeneralPurchaseOrderApprovalGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Order No. | DmTextBox | `txtPurchaseNo` | `` |
| 2 | Order Date | DmTextBox | `txtPurchaseDate` | `` |
| 3 | Quotation No. | DmTextBox | `txtQuotationNo` | `` |
| 4 | Supplier | DmTextBox | `txtSupplier` | `` |
| 5 | Currency | DmTextBox | `txtCurrency` | `` |

Other widgets (not in tab list): `txtApproved` (DmTextBox), `txtStatus` (DmTextBox), `txtItemGroup` (DmTextBox), `txtItemName` (DmTextBox), `txtReqDate` (DmTextBox), `txtOrdQty` (DmTextBox), `txtRate` (DmTextBox), `txtSKU` (DmTextBox), `txtWTACode` (DmTextBox), `txtCurrentUser` (DmTextBox), `txtRemarks` (DmTextBox), `txtApprovedBy` (DmTextBox), `txtApprovedDateTime` (DmTextBox), `grdItemlDetails` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `grdItemlDetails`: C/O [Text, w30];  [Text, w25]; PC. [Text, w80]; Qty. [Text, w80]; Unit [Text, w60]; Rate [Text, w60]; Amount [Text, w80]; Req. Date [Text, w80]; Item Name [Text, w120]; Remark [Text, w120]

**Lookup / suggestion / find sources (server RPC called):**
- `walkThrough` → `openRecord`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtApproved.setEnabled(false)`
- `txtApprovedBy.setEnabled(false)`
- `txtApprovedDateTime.setEnabled(false)`
- `txtStatus.setEnabled(false)`
- `txtRate.setEnabled(false)`
- `txtItemGroup.setEnabled(false)`
- `txtItemName.setEnabled(false)`
- `txtReqDate.setEnabled(false)`
- `txtSKU.setEnabled(false)`
- `txtOrdQty.setEnabled(false)`
- `txtPurchaseNo.setEnabled(false)`
- `txtPurchaseDate.setEnabled(false)`
- `txtRemarks.setEnabled(false)`
- `txtQuotationNo.setEnabled(false)`
- `txtSupplier.setEnabled(false)`
- `txtCurrency.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
txtApproved.setEnabled(false);
txtApprovedBy.setEnabled(false);
txtApprovedDateTime.setEnabled(false);
txtStatus.setEnabled(false);
txtRate.setEnabled(false);
txtItemGroup.setEnabled(false);
txtItemName.setEnabled(false);
txtReqDate.setEnabled(false);
txtSKU.setEnabled(false);
txtOrdQty.setEnabled(false);
txtPurchaseNo.setEnabled(false);
txtPurchaseDate.setEnabled(false);
txtRemarks.setEnabled(false);
txtQuotationNo.setEnabled(false);
txtSupplier.setEnabled(false);
txtCurrency.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`walkThrough.addCallBack` → `onClose()`
```java
fillGrid(DmShared.iNull(txtPurchaseNo.getTag()).toString());
```
- Alert: "Record is not updated by this user,Can't update"
- Alert: "Record is cancelled,Please refresh record."
- Alert: "Record is completed,Please refresh record."

### Screen `GeneralPurchaseOrderApproval`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmGridPaging | `dmGrd` | `Grd_PrpAcs, gridRows, false` |

**Grid / popup columns** (header, width, db field):
- `dmGrd`:  [Text, w30];  [Text, w25]; Ordered PC. [Text, w90, orderPackCount]; Ordered Qty. [Text, w90, orderQty]; Unit [Text, w50, unitName]; Rate [Text, w50, rate]; Item Name [Text, w150, itemName]; Properties [Text, w200]; Pack Size [Text, w120]; Req. Date [Text, w80, requiredDate]; Order No. [Text, w120, recId]; Order Date [Text, w80, v_Date]; Supplier [Text, w150, supplierName]; Remarks [Text, w100, remark]; Approved By [Text, w130, approvedBy]

**Lookup / suggestion / find sources (server RPC called):**
- `dmGrd` → `getFillRecords`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`walkThrough.addCallBack` → `onClose()`
```java
dmGrd.reQuery();
```

`gridEdit.addGridEditCallBack` → `onOk()`
```java
dmGrd.getDmGrid().commitChanges();
dmGrd.reQuery();
```

`dmGrd.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(true).getFillRecords(param, helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<GrdGeneral
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdGeneralPurchaseOrderApproval_Mdl>> result) {
if (result.isStatus()) {
dmGrd.setData(result.getData(), result.getPaging());
```

`dmGrd.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
dmGrd.setData(result.getData(), result.getPaging());
```

`dmGrd.addCallBack` → `getParam()`
```java
return purchaseApp_Mdl;
```
- Confirm: "Are you sure? You want to close."

### Screen `Setting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | From Date | DmTextBox | `txtFromDate` | `15, false` |
| 2 | To Date | DmTextBox | `txtToDate` | `15, false` |
| 3 | Item Name | DmTextHelpMulti | `txtHItemName` | `TxtHItemName_PrpAcs, 5` |
| 4 | Order No. | DmTextHelpMulti | `txtHPurchaseOrder` | `TxtHPurchaseOrder_PrpAcs, 7` |
| 5 | Supplier | DmTextHelpMulti | `txtHSupplier` | `TxtHSupplierName_PrpAcs, 7` |
| 6 | Status | DmTextHelp | `txtHStatus` | `KeyField_PrpAcs, 4, false` |

**Grid / popup columns** (header, width, db field):
- `txtHPurchaseOrder`: Purchase Order No. [Text, w120, pOrderNo]; Purchase Type [Text, w90, category]; Purchase Date [Text, w90, replace(convert(varchar, pOrderDate, 106), ' ','-')]
- `txtHSupplier`: Name [Text, w250, name]; Manual Code [Text, w100, manualCode]; City Name [Text, w100, cityName]
- `txtHItemName`: Name [Text, w250, name]; Unit [Text, w50, purchaseUnitName]
- `txtHStatus`: Status [Text, w150, Name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHStatus` → `getTxtHStatus`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtFromDate.addValueChangeHandler` → `onValueChange()`
```java
txtFromDate.setText(DmClient.returnDate(txtFromDate.getText()));
```

`txtToDate.addValueChangeHandler` → `onValueChange()`
```java
txtToDate.setText(DmClient.returnDate(txtToDate.getText()));
```

`txtHStatus.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHStatus(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFiel
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHStatus).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHStatus.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHStatus).show();
```

`txtHStatus.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHStatus.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHStatus).show();
```

`txtHItemName.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItem(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHItemN
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemName).show();
public void onSuccess(DmResultPageing<List<TxtHItemName_Mdl>> result) {
if (result.isStatus()) {
txtHItemName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemName).show();
```

`txtHItemName.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemName).show();
```

`txtHPurchaseOrder.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHPurchaseOrder(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHPurchaseOrder).show();
public void onSuccess(DmResultPageing<List<TxtHGeneralPurchaseOrder_Mdl>> result) {
if (result.isStatus()) {
txtHPurchaseOrder.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPurchaseOrder).show();
```

`txtHPurchaseOrder.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHPurchaseOrder.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPurchaseOrder).show();
```

`txtHSupplier.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHSupplierPT(true, null, helper, blnToEndOfFile, new AsyncCallback<DmResultPage
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSupplier).show();
public void onSuccess(DmResultPageing<List<TxtHSupplierPT_Mdl>> result) {
if (result.isStatus()) {
txtHSupplier.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSupplier).show();
```

`txtHSupplier.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSupplier.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSupplier).show();
```
- Alert: "From Date should be less than or equal to To Date."

## Files
| file | lines |
|---|---|
| client/GeneralPurchaseOrderApproval.java | 393 |
| client/GeneralPurchaseOrderApprovalCard.java | 140 |
| client/GeneralPurchaseOrderApprovalGridEdit.java | 305 |
| client/GeneralPurchaseOrderApprovalGridEdit_Dsg.java | 330 |
| client/GeneralPurchaseOrderApproval_Dsg.java | 272 |
| client/Setting.java | 279 |
| client/Setting_Dsg.java | 258 |
| server/GeneralPurchaseOrderApproval_SrvImpl.java | 1048 |
| shared/DefaultValue_Mdl.java | 22 |
| shared/GeneralPurchaseApproval_Srv.java | 31 |
| shared/GeneralPurchaseApproval_SrvAsync.java | 31 |
| shared/GeneralPurchaseOrderApproval_Mdl.java | 238 |
| shared/GrdGeneralPurchaseOrderApproval_Mdl.java | 392 |
| shared/GrdItemDetail_Mdl.java | 290 |
| shared/TxtHGeneralPurchaseOrder_Mdl.java | 32 |
| shared/TxtHItemName_Mdl.java | 104 |
| shared/TxtHSupplierPT_Mdl.java | 31 |

## UI labels / columns (client)
`From Date`, `To Date`, `Item Name`, `Order No.`, `Supplier`, `Status`, `Order Date`, `Quotation No.`, `Currency`

## Models (shared DTOs)
### TxtHItemName_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | purchaseUnitCode |
| String | purchaseUnitName |
| String | purchaseUnitDisplayName |
| String | purchaseUnitConversionRate |
| String | purchaseUnitDecimalPlaces |
| String | skuName |
| String | skuDecimalPlaces |
| String | itemProperty |
| String | isBestBefore |

### GrdItemDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | isView |
| String | ApprovedBy |
| String | ApprovedDateTime |
| String | orderNo |
| String | orderDate |
| String | isStatus |
| String | isChecked |
| String | vSNo |
| String | orderQty |
| String | unit |
| String | itemName |
| String | reqDate |
| String | rate |
| String | amount |
| String | Remark |
| String | image |
| String | colorCode |
| String | color |
| String | sizeCode |
| String | size |
| String | packSizeCode |
| String | packSizeName |
| String | packUnitCode |
| String | packUnitName |
| String | packUnitDisplayName |
| String | packUnitConversionRate |
| String | packSizeSkuCode |
| String | packSizeSkuName |
| String | packCount |
| String | packUnitDecimalPlaces |

### TxtHGeneralPurchaseOrder_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | poType |
| String | poDate |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | currentDate |

### TxtHSupplierPT_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityName |

### GeneralPurchaseOrderApproval_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | FromDate |
| String | ToDate |
| String | StatusCode |
| String | StatusName |
| String | suppleirName |
| String | suppleirCode |
| String | itemName |
| String | itemCode |
| String | PurchaseDocIds |
| String | PurchaseReccIds |
| String | vSNo |
| String | PurchaseDate |
| String | PurchaseType |
| String | siteCode |
| String | siteName |
| String | departmentCode |
| String | departmentName |
| String | approvedBy |
| String | isStatus |
| String | approvedDateTime |
| String | quotationNo |
| String | currency |
| String | wtaCode |
| List<GrdItemDetail_Mdl> | grdItemDetail_Mdl |
| List<GrdGeneralPurchaseOrderApproval_Mdl> | grdPurchaseApprovalEntry_Lst |

### GrdGeneralPurchaseOrderApproval_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | ApprovedBy |
| String | Approval |
| String | purchaseNo |
| String | purchaseDate |
| String | refNo |
| String | refDate |
| String | remark |
| String | isStatus |
| String | isChecked |
| String | vSNo |
| String | currentUser |
| String | orderQty |
| String | sku |
| String | itemName |
| String | reqDate |
| String | rate |
| String | amount |
| String | approvalRemark |
| String | supplier |
| String | currency |
| String | quotationNo |
| String | indCatName |
| String | indDate |
| String | indNo |
| String | wtaCode |
| String | IsView |
| String | propertiesCsv |
| String | ibCode |
| String | itemType |
| String | itemPropertyList |
| ItemProperty_Mdl | ipm |
| String | packSizeCode |
| String | packSizeName |
| String | packUnitCode |
| String | packUnitName |
| String | packUnitDisplayName |
| String | packUnitConversionRate |
| String | packSizeSkuCode |
| String | packSizeSkuName |
| String | orderedPackCount |
| String | packUnitDecimalPlaces |

## RPC interface
| method | params | returns |
|---|---|---|
| openRecord | boolean isOpen, String docId, String vSNo | DmResult<String> |
| getFillRecords | GeneralPurchaseOrderApproval_Mdl param, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdGeneralPurchaseOrderApproval_Mdl>> |
| getTxtHStatus | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| fillItemGrid | String code | DmResult<GeneralPurchaseOrderApproval_Mdl> |
| getTxtHPurchaseOrder | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHGeneralPurchaseOrder_Mdl>> |
| getTxtHSupplierPT | boolean flagFilterByVoucherCategory, String v_Type, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHSupplierPT_Mdl>> |
| getTxtHItem | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemName_Mdl>> |
| setApproval | String approvedBy, boolean isApprove, String docId | DmResult<String> |
| getCardDetail |  | DmResult<CardDetail_Mdl> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |

## Server `GeneralPurchaseOrderApproval_SrvImpl.java` (1048 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.xNull`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`


### `getFillRecords`
`public DmResultPageing<List<GrdGeneralPurchaseOrderApproval_Mdl>> getFillRecords(GeneralPurchaseOrderApproval_Mdl param, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT po.docId,po.recId,po.v_Date,po.refNo,po.RefDate,po.u_Name, po.preparedBy, po.v_Type,pod.itemCode , im.name as itemName,po.currencyCode,cr.name , pod.quantity AS orderQty, um.unit as unitName , pod.requiredDate, pod.remark , pod.rate, pod.v_SNo,vt.description,po.approvedBy ,format(po.approvalDateTime,'dd-MMM-yyyy HH.mm') as approvalDateTime, pod.isStatus,po.wtaCode ,po.subCode AS supplierCode ,sgv.displayName AS supplierName , q.recId AS quotRecId ,ig.name AS itemGroup ,im.propertyList ,pod.ibCode , it.manualCode as itemType , pod.packSizeCode , pod.packCount as orderPackCount , psm.name as packSizeName , psm.sku as packSizeSku , psm.packUnitCode , psm.packUnitConversionRate , umPsm.unit as packUnitName , umPsmSku.unit as packSizeSkuName , umPsm.decimalPlaces as packUnitDecimalPlaces FROM pOrder AS po LEFT JOIN pOrderDetail AS pod ON pod.docId = po.docId left join itemMast as im on pod.itemCode = im.code LEFT JOIN itemType it on it.code = im.itemType LEFT JOIN itemGroup AS ig ON ig.code=im.itemGroup left join unitMast as um on pod.unitCode = um.code LEFT JOIN voucher_Type AS vt ON po.v_Type = vt.v_Type LEFT JOIN currency AS cr ON cr.code=po.currencyCode LEFT JOIN subGroup AS sgv ON po.subCode = sgv.subCode LEFT JOIN packSizeMast psm on psm.code = pod.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku LEFT JOIN quotation AS q ON q.DocId = pod.quotationDocId {…} where vt.category IN ( {…} ) AND pod.isFooter!='2' {…}`
- Params: `:fromv_Date` ← `DmServer.convertDate(param.getFromDate())`; `:tov_Date` ← `DmServer.convertDate(param.getToDate())`
- Other calls: `ItemBatch_Server.getIPM`, `ItemPropertyPopup_Vld.getItemApplicableProperties`

### `fillItemGrid`
`public DmResult<GeneralPurchaseOrderApproval_Mdl> fillItemGrid(String code)`

- SQL 1: `SELECT po.docId,po.recId,po.v_Date,cr.name, po.approvedBy, FORMAT(po.approvalDateTime,'dd-MMM-yyyy HH:mm') AS approvalDateTime,po.wtaCode, po.subCode AS supplierCode,sgv.displayName AS supplierName, q.recId AS quotRecId FROM pOrder AS po LEFT JOIN voucher_Type AS vt ON po.v_Type = vt.v_Type LEFT JOIN currency AS cr ON cr.code=po.currencyCode LEFT JOIN subGroup AS sgv ON po.subCode = sgv.subCode LEFT JOIN ( SELECT pod.docId, pod.quotationDocId FROM pOrderDetail pod WHERE pod.quotationDocId IS NOT NULL GROUP BY pod.docId, pod.quotationDocId ) pod ON po.docId = pod.docId LEFT JOIN quotation AS q ON q.DocId= pod.quotationDocId where po.docId=:docId And vt.category IN ( {…} )`
- SQL 2: `select pod.docId , pod.itemCode , im.name as itemName , pod.quantity , pod.unitCode , um.unit as unitName , pod.requiredDate , pod.remark , pod.rate , pod.oh_Amt_Net as amount , pod.v_SNo,pod.isStatus , im.propertyList , pod.packSizeCode , pod.packCount , psm.name as packSizeName , psm.sku as packSizeSku , psm.packUnitCode , psm.packUnitConversionRate , umPsm.unit as packUnitName , umPsmSku.unit as packSizeSkuName , umPsm.decimalPlaces as packUnitDecimalPlaces from pOrderDetail as pod left join itemMast as im on pod.itemCode = im.code left join unitMast as um on pod.unitCode = um.code LEFT JOIN packSizeMast psm on psm.code = pod.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku where pod.isFooter = 0 AND docId=:docId order by itemName Asc`
- Params: `:docId` ← `DmShared.xNull(code)`; `:docId` ← `DmShared.xNull(code)`
- Other calls: `Logger.getLogger`

### `openRecord`
`public DmResult<String> openRecord(boolean isOpen, String docId, String vSNo)`

- SQL 1: `select approvedBy from pOrder where docId = :docId`
- SQL 2: `select Count(isStatus) as cnt from pOrderDetail where docId = :docId AND v_SNo=:vSNo AND isStatus = 2`
- SQL 3: `select Count(isStatus) as cnt from pOrderDetail where docId = :docId AND v_SNo=:vSNo AND isStatus = 0`
- SQL 4: `Update pOrderDetail set isStatus=:isStatus where docId=:docId AND v_SNo=:vSNo`
- SQL 5: `select Count(isStatus) as cnt from pOrderDetail where docId = :docId AND v_SNo=:vSNo AND isStatus = 1`
- SQL 6: `UPDATE pOrderDetail SET isStatus = 1 WHERE docId = :docId AND v_SNo=:vSNo`
- SQL 7: `Update pOrder set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- Params: `:docId` ← `DmShared.iNull(docId).toString()`; `:docId` ← `DmShared.iNull(docId).toString()`; `:vSNo` ← `DmShared.iNull(vSNo).toString()`; `:docId` ← `DmShared.iNull(docId).toString()`; `:vSNo` ← `DmShared.iNull(vSNo).toString()`; `:docId` ← `DmShared.iNull(docId).toString()`; `:vSNo` ← `DmShared.iNull(vSNo).toString()`; `:isStatus` ← `"0"`; `:docId` ← `DmShared.iNull(docId).toString()`; `:vSNo` ← `DmShared.iNull(vSNo).toString()`; `:docId` ← `DmShared.iNull(docId).toString()`; `:vSNo` ← `DmShared.iNull(vSNo).toString()`; `:docId` ← `DmShared.iNull(docId).toString()`; `:uEntDt` ← `DmServer.getServerTimestamp()`
- Errors: "Record is not approved by this user. Please refresh data." | "Record is completed,Please refresh record." | "Record is already opened,Please refresh record." | "Record is already cancelled,Please refresh record."

### `manageIndentStatus`
`private void manageIndentStatus(String docId, String vSno, boolean isDelete, Connection cnTemp) throws Exception`

- SQL 1: `select i.docId as indentDocId , id.v_SNo as indentVSno , pid.orderedQty as orderQty , pid.orderedPackCount as orderPackCount , id.aprQty , id.aprPackCount , id.orderedQty , id.orderedPackCount , id.isStatus , i.approvedBy , i.recId as indentNo,vt.short_name intentType from indentDetail id left join indent i on i.docId = id.docId left join voucher_type vt on vt.v_type = i.v_Type inner join ( select pid.indentDocId , pid.indentV_SNo , sum(orderedQty) as orderedQty , sum(orderedPackCount) as orderedPackCount from pOrderIndentDetail pid where pid.docId = :docId and pid.v_Sno = :v_Sno group by pid.indentDocId, pid.indentV_SNo ) pid on id.docId = pid.indentDocId and id.v_Sno = pid.indentV_SNo`
- SQL 2: `update IndentDetail set isStatus = :isStatus, orderedQty=:orderedQty, orderedPackCount=:orderedPackCount where docId = :docId and v_SNo = :v_SNo`
- Params: `:docId` ← `DmShared.iNull(docId).toString()`; `:v_Sno` ← `DmShared.iNull(vSno).toString()`; `:docId` ← `DmShared.iNull(rsTemp.getString("indentDocId")).toString()`; `:v_SNo` ← `DmShared.iNull(rsTemp.getString("indentVSno")).toString()`; `:isStatus` ← `DmShared.iNull(isStatus).toString()`; `:orderedQty` ← `DmServer.vNull(ordedQty)`; `:orderedPackCount` ← `DmServer.vNull(orderedPackCount)`; `:docId` ← `DmShared.iNull(indentDocId).toString()`; `:uEntDt` ← `DmServer.getServerTimestamp()`

### `getTxtHStatus`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHStatus(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHPurchaseOrder`
`public DmResultPageing<List<TxtHGeneralPurchaseOrder_Mdl>> getTxtHPurchaseOrder(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT po.docId, po.recId as pOrderNo, po.v_Date as pOrderDate, vt.category FROM pOrder AS po left join voucher_Type as vt on vt.v_Type = po.v_Type where po.site_Code= ' {…} ' and vt.Category IN ( {…} )`

### `getTxtHSupplierPT`
`public DmResultPageing<List<TxtHSupplierPT_Mdl>> getTxtHSupplierPT(boolean flagFilterByVoucherCategory, String v_Type, DmHelper pageing, boolean blnToEndOfFile)`


### `getSupplierDetail`
`private List<TxtHSupplierPT_Mdl> getSupplierDetail(String vendorCode, boolean flagFilterByVoucherCategory, String v_Type, DmHelper pageing, boolean blnToEndOfFile, Connection cnTemp) throws Exception`

- SQL 1: `SELECT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_type = acf.v_Type WHERE vt.category IN ( {…} )`
- SQL 2: `SELECT acf.Nature FROM AcFilteration acf WHERE acf.v_Type = :v_Type`
- SQL 3: `SELECT sg.subCode AS code , sg.name , sg.manualCode , c.cityName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( {…} ) AND sg.isActive = 1 AND sgsl.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vendorCode` ← `vendorCode`; `:v_Type` ← `DmShared.iNull(v_Type).toString()`

### `getTxtHItem`
`public DmResultPageing<List<TxtHItemName_Mdl>> getTxtHItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , im.manualCode , im.purchaseUnit AS purchaseUnitCode , um.unit AS purchaseUnitName , ISNULL(im.purchaseUnitConversionRate, 0) AS purchaseUnitConversionRate , im.propertyList , ISNULL(um.decimalPlaces,0) AS purchaseUnitDecimalPlaces , ums.code AS skuCode , ums.unit AS skuName , ISNULL(ums.decimalPlaces,0) AS skuDecimalPlaces , ISNULL(im.isBestBefore, 0) AS isBestBefore FROM itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN unitmast um ON im.purchaseUnit = um.code LEFT JOIN unitmast ums ON im.sku = ums.code WHERE im.IsActive = 1 AND (im.filterItemType = :filterItemType OR im.isCommon = 1) AND sl.site_Code = :site_Code AND im.isQuotationMandatory = 0`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `setApproval`
`public DmResult<String> setApproval(String approvedBy, boolean isApprove, String docId)`

- Other calls: `STApprovals.generalPurchaseOrderApproval`

### `walkThrough`
`public DmResult<String> walkThrough(WalkThrough_Mdl walkThrough)`

- Other calls: `STApprovals.generalPurchaseOrderApproval`, `WalkThrough_Server.walkThrough`

### `getCardDetail`
`public DmResult<CardDetail_Mdl> getCardDetail()`

- SQL 1: `Select count(po.docId) as cnt, sum(case when DATEDIFF(day, po.v_Date, CAST(getdate() AS date)) <= {…} then 1 else 0 end) as low, sum(case when DATEDIFF(day, po.v_Date, CAST(getdate() AS date)) > {…} and DATEDIFF(day, po.v_Date, CAST(getdate() AS date)) <= {…} then 1 else 0 end) as medium, sum(case when DATEDIFF(day, po.v_Date, CAST(getdate() AS date)) >= {…} then 1 else 0 end) as high from pOrder as po left join voucher_type as vt on po.v_Type = vt.v_Type left join( select pod.docId from pOrderDetail as pod where pod.isStatus = 0 group by pod.docId ) as pod on pod.docId = po.docId LEFT JOIN walkThroughFootPrint wtfp on wtfp.wtaCode = po.wtaCode and wtfp.isStatus = 0 where pod.docId is not null and isNull(po.approvedBy,'') = '' and vt.category IN ( {…} ) AND isNull(wtfp.linkedUser,' {…} ,') like '% {…} ,%' and po.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `HIGH_PRIORITYDAYS.getDays`, `LOW_PRIORITYDAYS.getDays`, `MEDIUM_PRIORITYDAYS.getDays`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.dashboardCardStrip.client`, `paperbiz.selfToolkit.dashboardCardStrip.shared`, `paperbiz.selfToolkit.generalPurchaseOrderApproval.shared`, `paperbiz.selfToolkit.itemBatch.server`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.purchaseOrderApproval.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| openRecord | `if (!(DmShared.xNull(DmServer.getSsnUserName(this)).equalsIgnoreCase(rsTemp.getString("approvedBy"))` | "Record is not approved by this user. Please refresh data." |
| openRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() == 1)` | "Record is completed,Please refresh record." |
| openRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() == 1)` | "Record is already opened,Please refresh record." |
| openRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() == 1)` | "Record is already cancelled,Please refresh record." |
| manageIndentStatus | `if (DmShared.xNull(rsTemp.getString("approvedBy")).trim().equalsIgnoreCase(""))` | " + DmShared.xNull(rsTemp.getString(" |
| manageIndentStatus | `if (isStatus.intValue() == 1)` | " + DmShared.xNull(rsTemp.getString(" |
| manageIndentStatus | `if (ordedQty > aprQty \|\| ordedQty < 0)` | " + DmShared.xNull(rsTemp.getString(" |

## Notes (hand-written)
**Standard approval screen** (pattern `_module.md` me). Is document ke liye: pending/approved list `getFillRecords` (Setting popup ke filters), approve/reject `setApproval(approvedBy, isApprove, docId)`, dashboard card ka count `getCardDetail` (din ke ageing buckets), lookups `getTxtH…`. Document ka approval logic `stStatic/STApprovals` me.
