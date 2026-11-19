# pb-self-toolkit / grnApproval

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/grnApproval/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHStatus, getTxtHOrderNo, getTxtHSupplier, getTxtHDepartment, getTxtHGrnNo
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `GrnApproval`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmGridPaging | `dmGrd` | `Grd_PrpAcs, gridRows, false` |

**Grid / popup columns** (header, width, db field):
- `dmGrd`:  [Text, w30];  [Text, w25]; Department [Text, w100, departmentName]; Challan No. [Text, w120, pChallanNo]; Challan Date [Text, w90, pChallanDate]; GRN No. [Text, w80, recId]; Date [Text, w80, v_Date]; GRN Type [Text, w90, short_Name]; Unit [Text, w50, unitName]; Challan Qty. [Text, w100, challanQty]; Acpt. Qty. [Text, w90, acceptQty]; SKU Qty. [Text, w70, skuQty]; SKU [Text, w50, skuName]; Rate [Text, w60, rate]; Item Name [Text, w220, itemName]; Item Nature [Text, w75]; BF [Text, w45]; GSM [Text, w50]; Width [Text, w70]; Length [Text, w70]; Size Unit [Text, w65]; Grain [Text, w65]; SPR [Text, w45]; RPB [Text, w45]; Ream Wt. [Text, w75]; Order No. [Text, w80, orderNo]; Supplier [Text, w233, supName]; Direct Supply [Text, w82]; Bifurcate Service [Text, w103]; Customer [Text, w220]

**Lookup / suggestion / find sources (server RPC called):**
- `dmGrd` → `getFillRecords`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`walkThrough.addCallBack` → `onClose()`
```java
dmGrd.reQuery();
```

`settingPopup.addCallBack` → `onOk()`
```java
setting_Mdl = new Setting_Mdl();
setting_Mdl = approval_Mdl;
dmGrd.reQuery();
```

`dmGrd.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(true).getFillRecords(param, helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<GrdGrnAppr
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdGrnApproval_Mdl>> result) {
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
return setting_Mdl;
```
- Confirm: "Are you sure? You want to close."

### Screen `Setting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | From Date | DmTextBox | `txtFromDate` | `15, false` |
| 2 | To Date | DmTextBox | `txtToDate` | `15, false` |
| 3 | GRN No. | DmTextHelpMulti | `txtHGrnNo` | `TxtHGrnNo_PrpAcs, 5` |
| 4 | Order No. | DmTextHelpMulti | `txtHOrderNo` | `TxtHOrderNo_PrpAcs, 7` |
| 5 | Department | DmTextHelpMulti | `txtHDepartment` | `KeyField_PrpAcs, 4` |
| 6 | Supplier | DmTextHelpMulti | `txtHSupplier` | `TxtHSupplier_PrpAcs, 5` |
| 7 | Status | DmTextHelp | `txtHStatus` | `KeyField_PrpAcs, 4, false` |

**Grid / popup columns** (header, width, db field):
- `txtHDepartment`: Name [Text, w0, Name]
- `txtHSupplier`: Name [Text, w250, name]; Manual Code [Text, w100, manualCode]; City [Text, w100, cityName]
- `txtHOrderNo`: Order No. [Text, w0, orderNo]; Order Date [Text, w100, replace(convert(varchar, v_Date, 106), ' ','-')]
- `txtHStatus`: Status [Text, w100, Name]
- `txtHGrnNo`: GRN No. [Text, w120, recId]; GRN Date [Text, w100, replace(convert(varchar, v_Date, 106), ' ','-')]; Challan No. [Text, w120, refNo]; Challan Date [Text, w120, replace(convert(varchar, refDate, 106), ' ','-')]

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

`txtHSupplier.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHSupplier(null, true, null, helper, blnToEndOfFile, new AsyncCallback<DmResult
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSupplier).show();
public void onSuccess(DmResultPageing<List<TxtHSupplier_Mdl>> result) {
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

`txtHSupplier.addTextHelpMultiCallBack` → `getSelection()`
```java
if (blnStatus) {
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

`txtHDepartment.addTextHelpMultiCallBack` → `populateData()`
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

`txtHDepartment.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHDepartment.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDepartment).show();
```

`txtHDepartment.addTextHelpMultiCallBack` → `getSelection()`
```java
if (blnStatus) {
```

`txtHGrnNo.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHGrnNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHGrnN
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGrnNo).show();
public void onSuccess(DmResultPageing<List<TxtHGrnNo_Mdl>> result) {
if (result.isStatus()) {
txtHGrnNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGrnNo).show();
```

`txtHGrnNo.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHGrnNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGrnNo).show();
```

`txtHGrnNo.addTextHelpMultiCallBack` → `getSelection()`
```java
if (blnStatus) {
```

`txtHOrderNo.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHOrderNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFie
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHOrderNo).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHOrderNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHOrderNo).show();
```

`txtHOrderNo.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHOrderNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHOrderNo).show();
```

`txtHOrderNo.addTextHelpMultiCallBack` → `getSelection()`
```java
if (blnStatus) {
```
- Alert: "From Date should be less than or equal to To Date."

## Files
| file | lines |
|---|---|
| client/GRNApprovalCard.java | 140 |
| client/GrnApproval.java | 381 |
| client/GrnApproval_Dsg.java | 359 |
| client/Setting.java | 322 |
| client/Setting_Dsg.java | 303 |
| server/GrnApproval_SrvImpl.java | 725 |
| shared/DefaultValue_Mdl.java | 22 |
| shared/GrdGrnApproval_Mdl.java | 444 |
| shared/GrdItemDetail_Mdl.java | 176 |
| shared/GrnApproval_Srv.java | 30 |
| shared/GrnApproval_SrvAsync.java | 30 |
| shared/Setting_Mdl.java | 188 |
| shared/TxtHGrnNo_Mdl.java | 55 |
| shared/TxtHSupplier_Mdl.java | 76 |

## UI labels / columns (client)
`From Date`, `To Date`, `GRN No.`, `Order No.`, `Department`, `Supplier`, `Status`

## Models (shared DTOs)
### TxtHGrnNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | grnNo |
| String | grnDate |
| String | challanNo |
| String | challanDate |

### GrdItemDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | ApprovedBy |
| String | ApprovedDateTime |
| String | indNo |
| String | indDocId |
| String | isStatus |
| String | isChecked |
| String | isCancel |
| String | vSNo |
| String | indQty |
| String | unit |
| String | itemName |
| String | itemGroup |
| String | reqDate |
| String | ApprovedQuantity |
| String | ApprovalRemarks |
| String | remark |
| String | image |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | currentDate |

### Setting_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | FromDate |
| String | ToDate |
| String | DepartmentName |
| String | DepartmentCode |
| String | StatusCode |
| String | StatusName |
| String | siteCode |
| String | siteName |
| String | approvedBy |
| String | isStatus |
| String | approvedDateTime |
| String | wtaCode |
| String | SupplierName |
| String | SupplierCode |
| String | GrnNo |
| String | GrnCode |
| String | OrderNo |
| String | OrderCode |
| List<GrdItemDetail_Mdl> | grdItemDetail_Mdl |
| List<GrdGrnApproval_Mdl> | grdIndentApproval_Mdl |

### TxtHSupplier_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | stateCode |
| String | stateName |
| String | cityName |
| String | manualCode |
| String | partyType |
| String | gstin |
| String | gstinDate |

### GrdGrnApproval_Mdl (extends `FullPaperProperty_Mdl implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | wtaCode |
| String | itemCode |
| String | ItemName |
| String | orderNo |
| String | batchNo |
| String | sizeName |
| String | FreeQty |
| String | batchCode |
| String | unitName |
| String | unitCode |
| String | challanQty |
| String | acptQty |
| String | SKUQty |
| String | SKUUnit |
| String | SKUUnitCode |
| String | rate |
| String | approvedBy |
| String | amount |
| String | voucherType |
| String | voucherNo |
| String | supplierCode |
| String | supplierName |
| String | DepartmentCode |
| String | DepartmentName |
| String | challanNo |
| String | challanDate |
| String | remark |
| String | vSNo |
| String | isChecked |
| String | currentUser |
| String | siteCode |
| String | siteName |
| String | approval |
| String | isStatus |
| String | VDate |
| String | orderCode |
| String | isView |
| String | ibCode |
| String | directSupply |
| String | bifurcateService |
| String | customerName |
| String | customerCode |
| String | deliveryToName |
| String | deliveryToCode |
| String | soReffNo |
| String | soReffDate |
| List<GrdItemDetail_Mdl> | grdItemDetails |

## RPC interface
| method | params | returns |
|---|---|---|
| getFillRecords | Setting_Mdl param, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdGrnApproval_Mdl>> |
| getTxtHStatus | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHOrderNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSupplier | String voucherCategory, boolean flagFilterByVoucherCategory, String v_Type, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHSupplier_Mdl>> |
| getTxtHDepartment | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| setApproval | String approvedBy, boolean isApprove, String docId | DmResult<String> |
| getCardDetail |  | DmResult<CardDetail_Mdl> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| getTxtHGrnNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHGrnNo_Mdl>> |

## Server `GrnApproval_SrvImpl.java` (725 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.xNull`

### `getFillRecords`
`public DmResultPageing<List<GrdGrnApproval_Mdl>> getFillRecords(Setting_Mdl param, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT pc.docId,pc.recId,pcd.challanQty ,pcd.unitCode,um.unit as unitName, pcd.acceptQty,pcd.skuQty ,im.sku as skuCode, ums.unit as skuName,pcd.rate , pcd.oh_Amt_Gross as amount,pcd.itemCode, im.name as itemName , pcd.poDocId as orderCode, po.recId as orderNo , pc.v_Type, vt.short_Name,pc.v_Date,pc.subcode as supCode, sgs.name as supName , pc.pChallanNo, pc.pChallanDate,pcd.departmentCode, sgd.name as departmentName,pcd.freeQty, pc.remark, pc.approvedBy,FORMAT(pc.approvalDateTime,'dd-MMM-yyyy HH:mm') AS approvalDateTime,pc.wtaCode,pc.site_Code, sm.name AS siteName , im.propertyList,pcd.ibCode, case when isNull(pc.isDirectSupply,'') = 0 then 'No' else 'Yes' end as directSupply ,case when isNull(pc.isBifurcateService,'') = 0 then 'No' else 'Yes' end as bifurcateService ,pc.customerCode ,sgc.name as customerName ,sgdel.name as deliveryToName ,pc.deliveryToCode ,so.partyOrdNo as soReffNo,so.partyOrdDt as soReffDate, pcd.sizeUnitCode from pChallan AS pc left join pChallanDetail as pcd on pc.docId = pcd.docId left join voucher_Type as vt on pc.v_Type = vt.v_Type left join subGroup as sgd on pcd.departmentCode = sgd.subCode left join subGroup as sgs on pc.subcode = sgs.subCode left join unitMast as um on pcd.unitCode = um.code left join itemMast as im on pcd.itemCode = im.code LEFT JOIN itemType it on it.code = im.itemType left join unitMast as ums on im.sku = ums.code LEFT JOIN siteMast AS sm ON sm.code = pc.site_Code left join pOrder as po on pcd.poDocId = po.docId left join pOrderDetail as pod on pcd.poDocId = pod.docId and pcd.poV_Sno = pod.v_Sno LEFT JOIN subGroup sgc ON sgc.subCode = pc.customerCode LEFT JOIN subGroup sgdel ON sgdel.subCode = pc.deliveryToCode LEFT JOIN sOrder so ON so.docId=pcd.soDocid {…} where ISNULL(pcd.isFooter, 0) = 0 and vt.category in ( {…} ) {…}`
- Params: `:fromv_Date` ← `DmServer.convertDate(param.getFromDate())`; `:tov_Date` ← `DmServer.convertDate(param.getToDate())`
- Other calls: `ItemBatch_Server.fillFullPaperProperties`

### `getTxtHStatus`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHStatus(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHOrderNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHOrderNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT po.docId, po.recId AS orderNo , po.v_Date FROM pOrder AS po LEFT JOIN voucher_Type AS vt ON po.v_Type = vt.v_Type WHERE isNull(po.approvedBy,'') <> '' AND po.site_Code = :site_Code AND vt.category in ( {…} )`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHSupplier`
`public DmResultPageing<List<TxtHSupplier_Mdl>> getTxtHSupplier(String voucherCategory, boolean flagFilterByVoucherCategory, String v_Type, DmHelper helper, boolean blnToEndOfFile)`


### `getSupplier`
`private List<TxtHSupplier_Mdl> getSupplier(String supplierCode, String voucherCategory, boolean flagFilterByVoucherCategory, String v_Type, DmHelper helper, boolean blnToEndOfFile, Connection cnTemp) throws Exception`

- SQL 1: `SELECT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_type = acf.v_Type WHERE vt.category IN ( {…} )`
- SQL 2: `SELECT acf.Nature FROM AcFilteration acf WHERE acf.v_Type = :v_Type`
- SQL 3: `SELECT sg.subCode AS code, sg.name, sg.manualCode, c.cityName, c.stateCode, sg.partyType, sg.gstin, sg.gstinDate FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( {…} ) AND sg.isActive = 1 AND sgsl.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Type` ← `DmShared.iNull(v_Type).toString()`; `:supplierCode` ← `DmShared.xNull(supplierCode)`
- Other calls: `Composite.getId`, `Registered.getId`, `Unregistered.getId`

### `setApproval`
`public DmResult<String> setApproval(String approvedBy, boolean isApprove, String docId)`

- Other calls: `STApprovals.generalGrnApproval`

### `walkThrough`
`public DmResult<String> walkThrough(WalkThrough_Mdl walkThrough)`

- Other calls: `STApprovals.generalGrnApproval`, `WalkThrough_Server.walkThrough`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT dm.subCode AS code, sg.name FROM departmentMast dm LEFT JOIN subGroup sg ON sg.subCode = dm.subCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.manualCode IN ('CM','DP','GD') AND sg.isActive = 1 {…}`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getCardDetail`
`public DmResult<CardDetail_Mdl> getCardDetail()`

- SQL 1: `Select count(pc.docId) as cnt, sum(case when DATEDIFF(day, pc.v_Date, CAST(getdate() AS date)) <= {…} then 1 else 0 end) as low, sum(case when DATEDIFF(day, pc.v_Date, CAST(getdate() AS date)) > {…} and DATEDIFF(day, pc.v_Date, CAST(getdate() AS date)) <= {…} then 1 else 0 end) as medium, sum(case when DATEDIFF(day, pc.v_Date, CAST(getdate() AS date)) >= {…} then 1 else 0 end) as high from pChallan as pc left join voucher_type as vt on pc.v_Type = vt.v_Type left join( select pcd.docId from pChallanDetail as pcd where pcd.isStatus = 0 group by pcd.docId ) as pcd on pcd.docId = pc.docId LEFT JOIN walkThroughFootPrint wtfp on wtfp.wtaCode = pc.wtaCode and wtfp.isStatus = 0 where pcd.docId is not null and isNull(pc.approvedBy,'') = '' and vt.category IN ( {…} ) AND isNull(wtfp.linkedUser,' {…} ,') like '% {…} ,%' and pc.site_Code = :site_Code And ISNULL(pc.cancelledBy,'') = ''`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `HIGH_PRIORITYDAYS.getDays`, `LOW_PRIORITYDAYS.getDays`, `MEDIUM_PRIORITYDAYS.getDays`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`


### `getTxtHGrnNo`
`public DmResultPageing<List<TxtHGrnNo_Mdl>> getTxtHGrnNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT pc.docId, pc.recId, pc.v_Date , pc.pChallanNo as refNo, pc.pChallanDate as refDate FROM pChallan pc LEFT JOIN voucher_Type vt ON vt.v_Type = pc.v_Type where vt.category in ( {…} ) and pc.site_Code = ' {…} '`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.dashboardCardStrip.client`, `paperbiz.selfToolkit.dashboardCardStrip.shared`, `paperbiz.selfToolkit.grnApproval.shared`, `paperbiz.selfToolkit.itemBatch.server`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
**Standard approval screen** (pattern `_module.md` me). Is document ke liye: pending/approved list `getFillRecords` (Setting popup ke filters), approve/reject `setApproval(approvedBy, isApprove, docId)`, dashboard card ka count `getCardDetail` (din ke ageing buckets), lookups `getTxtH…`. Document ka approval logic `stStatic/STApprovals` me.
