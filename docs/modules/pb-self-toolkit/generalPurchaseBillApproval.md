# pb-self-toolkit / generalPurchaseBillApproval

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/generalPurchaseBillApproval/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHGrnNo, getTxtHBillNo, getTxtHStatus, getTxtHCategory, getTxtHSupplierPT
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `GeneralPurchaseBillApproval`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmGridPaging | `dmGrd` | `Grd_PrpAcs, gridRows, false` |

**Grid / popup columns** (header, width, db field):
- `dmGrd`:  [Text, w30];  [Text, w25]; Bill No. [Text, w110, billNo]; Bill Date [Text, w80, billDate]; Type [Text, w80, short_Name]; Party BillNo. [Text, w140, partyBillNo]; Party Bill Date [Text, w140, partyBillDate]; Due Date [Text, w100, dueDate]; Supplier [Text, w250, suppName]; Approved By [Text, w130, approvedBy]; Site [Text, w120, siteName]

**Lookup / suggestion / find sources (server RPC called):**
- `dmGrd` → `getFillRecords`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`walkThroughPBill.addCallBack` → `onClose()`
```java
dmGrd.reQuery();
```

`walkThroughPBillDirect.addCallBack` → `onClose()`
```java
dmGrd.reQuery();
```

`settingPopup.addCallBack` → `onOk()`
```java
purchaseApproval_Mdl = new Setting_Mdl();
purchaseApproval_Mdl = approvalEntry_Mdl;
dmGrd.reQuery();
```

`dmGrd.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(true).getFillRecords(param, helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<GrdGeneral
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdGeneralPurchaseBillApproval_Mdl>> result) {
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
return purchaseApproval_Mdl;
```
- Confirm: "Are you sure? You want to close."

### Screen `Setting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | From Date | DmTextBox | `txtFromDate` | `15, false` |
| 2 | To Date | DmTextBox | `txtToDate` | `15, false` |
| 3 | Status | DmTextHelp | `txtHStatus` | `KeyField_PrpAcs, 4, false` |
| 4 | Type | DmTextHelpMulti | `txtHCategory` | `TxtHCategoy_PrpAcs, 2` |
| 5 | Supplier | DmTextHelpMulti | `txtHSupplier` | `TxtHSupplierName_PrpAcs, 7` |
| 6 | GRN No. | DmTextHelpMulti | `txtHGrnNo` | `TxtHGRNNo_Mdl_PrpAcs, 5` |
| 7 | Bill No. | DmTextHelpMulti | `txtHBillNo` | `TxtHIndentNo_PrpAcs, 7` |

**Grid / popup columns** (header, width, db field):
- `txtHBillNo`: Bill No. [Text, w90, pbRecId]; Bill Type [Text, w80, category]; Bill Date [Text, w80, replace(convert(varchar, pbDate, 106), ' ','-')]
- `txtHSupplier`: Name [Text, w250, name]; Manual Code [Text, w100, manualCode]; City Name [Text, w100, cityName]
- `txtHCategory`: Description [Text, w150, Name]; Category [Text, w70, Category]
- `txtHGrnNo`: Name [Text, w0, Name];  Date [Text, w80, replace(convert(varchar, Date, 106), ' ','-')]
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

`txtHSupplier.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHSupplierPT(true, null, helper, blnToEndOfFile, new AsyncCallback<DmResultPage
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

`txtHGrnNo.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHGrnNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyField
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGrnNo).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
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

`txtHBillNo.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHBillNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHGen
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHBillNo).show();
public void onSuccess(DmResultPageing<List<TxtHGeneralPurchaseBillNo_Mdl>> result) {
if (result.isStatus()) {
txtHBillNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBillNo).show();
```

`txtHBillNo.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHBillNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBillNo).show();
```

`txtHBillNo.addTextHelpMultiCallBack` → `getSelection()`
```java
if (blnStatus) {
```

`txtHStatus.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHStatus(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFiel
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

`txtHCategory.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHCategory(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHC
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHCategory).show();
public void onSuccess(DmResultPageing<List<TxtHCategory_Mdl>> result) {
if (result.isStatus()) {
txtHCategory.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCategory).show();
```

`txtHCategory.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHCategory.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCategory).show();
```

`txtHCategory.addTextHelpMultiCallBack` → `getSelection()`
```java
if (blnStatus) {
```
- Alert: "From Date should be less than or equal to To Date."

## Files
| file | lines |
|---|---|
| client/GeneralPurchaseBillApproval.java | 424 |
| client/GeneralPurchaseBillApprovalCard.java | 141 |
| client/GeneralPurchaseBillApproval_Dsg.java | 239 |
| client/Setting.java | 328 |
| client/Setting_Dsg.java | 296 |
| server/GeneralPurchaseBillApproval_SrvImpl.java | 642 |
| shared/DefaultValue_Mdl.java | 22 |
| shared/GeneralPurchaseBillApproval_Mdl.java | 253 |
| shared/GeneralPurchaseBillApproval_Srv.java | 30 |
| shared/GeneralPurchaseBillApproval_SrvAsync.java | 30 |
| shared/GrdGeneralPurchaseBillApproval_Mdl.java | 208 |
| shared/GrdItemDetail_Mdl.java | 175 |
| shared/Setting_Mdl.java | 254 |
| shared/TxtHCategory_Mdl.java | 23 |
| shared/TxtHGeneralGRNNo_Mdl.java | 32 |
| shared/TxtHGeneralPurchaseBillNo_Mdl.java | 32 |
| shared/TxtHIndentNo_Mdl.java | 32 |
| shared/TxtHSupplierPT_Mdl.java | 32 |

## UI labels / columns (client)
`From Date`, `To Date`, `Status`, `Type`, `Supplier`, `GRN No.`, `Bill No.`

## Models (shared DTOs)
### TxtHGeneralGRNNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
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

### GrdGeneralPurchaseBillApproval_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | wtaCode |
| String | billNo |
| String | billDate |
| String | partyBillNo |
| String | partyBillDate |
| String | dueDate |
| String | vtypeTag |
| String | vtypeText |
| String | vCat |
| String | supplierTag |
| String | supplierText |
| String | SiteCode |
| String | SiteName |
| String | ApprovedBy |
| String | Approval |
| String | isStatus |
| String | isChecked |
| String | currentUser |
| String | vSNo |
| String | isView |
| List<GrdItemDetail_Mdl> | grdItemDetails |

### TxtHCategory_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | description |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | currentDate |

### Setting_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | FromDate |
| String | ToDate |
| String | ItemName |
| String | ItemCode |
| String | grnNoTag |
| String | grnNoText |
| String | supplierName |
| String | supplierCode |
| String | StatusCode |
| String | StatusName |
| String | IndDocId |
| String | IndReccId |
| String | categoryCode |
| String | categoryName |
| String | billDocIds |
| String | billReccIds |
| String | vSNo |
| String | IndDate |
| String | IndType |
| String | siteCode |
| String | siteName |
| String | approvedBy |
| String | isStatus |
| String | approvedDateTime |
| String | wtaCode |
| List<GrdGeneralPurchaseBillApproval_Mdl> | grdPurchaseBillApproval_Mdl |

### TxtHSupplierPT_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityName |

### TxtHIndentNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | indentType |
| String | indentDate |

### TxtHGeneralPurchaseBillNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | billType |
| String | billDate |

### GeneralPurchaseBillApproval_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | FromDate |
| String | ToDate |
| String | ItemName |
| String | ItemCode |
| String | ItemGroupName |
| String | ItemGroupCode |
| String | DepartmentName |
| String | DepartmentCode |
| String | StatusCode |
| String | StatusName |
| String | IndenterName |
| String | IndenterCode |
| String | IndDocId |
| String | IndReccId |
| String | IndentDocIds |
| String | IndentReccIds |
| String | vSNo |
| String | IndDate |
| String | IndType |
| String | siteCode |
| String | siteName |
| String | approvedBy |
| String | isStatus |
| String | approvedDateTime |
| String | wtaCode |
| List<GrdItemDetail_Mdl> | grdItemDetail_Mdl |
| List<GrdGeneralPurchaseBillApproval_Mdl> | grdPurchaseBillApproval_Mdl |

## RPC interface
| method | params | returns |
|---|---|---|
| getFillRecords | Setting_Mdl param, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdGeneralPurchaseBillApproval_Mdl>> |
| getTxtHGrnNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHBillNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHGeneralPurchaseBillNo_Mdl>> |
| getTxtHStatus | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHCategory | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHCategory_Mdl>> |
| getCardDetail |  | DmResult<CardDetail_Mdl> |
| setApproval | String approvedBy, boolean isApprove, String docId | DmResult<String> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| getTxtHSupplierPT | boolean flagFilterByVoucherCategory, String v_Type, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHSupplierPT_Mdl>> |

## Server `GeneralPurchaseBillApproval_SrvImpl.java` (642 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.xNull`

### `getFillRecords`
`public DmResultPageing<List<GrdGeneralPurchaseBillApproval_Mdl>> getFillRecords(Setting_Mdl param, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT pb.docId as billDocId,pb.recId as billNo ,pb.partyBillNo ,pb.partyBillDate, pb.dueDate, pb.wtaCode,pb.approvedBy , FORMAT(pb.approvalDateTime,'dd-MMM-yyyy HH.mm') as approvalDateTime , pb.preparedBy,pb.subCode,sg.name AS suppName,pb.v_Date as billDate , pb.v_Type,vt.short_Name,vt.description, vt.category AS vCat , pb.oh_Amt_Gross AS amount,sm.name as siteName , CASE WHEN isNull(pb.isDirectSupply,'') = 0 THEN 'No' ELSE 'Yes' END as directSupply ,CASE WHEN isNull(pb.isBifurcateService,'') = 0 THEN 'No' ELSE 'Yes' END as bifurcateService ,pb.customerCode ,sgc.name as customerName ,sgdel.name as deliveryToName,pb.deliveryToCode FROM pBill pb LEFT JOIN voucher_Type AS vt ON vt.v_Type=pb.v_Type LEFT JOIN subGroup AS sg ON sg.subCode=pb.subCode LEFT JOIN siteMast sm on sm.code = pb.site_Code LEFT JOIN subGroup sgc ON sgc.subCode = pb.customerCode LEFT JOIN subGroup sgdel ON sgdel.subCode = pb.deliveryToCode {…} where {…}`
- Params: `:fromv_Date` ← `DmServer.convertDate(param.getFromDate())`; `:tov_Date` ← `DmServer.convertDate(param.getToDate())`

### `getTxtHGrnNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGrnNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT Max(pc.docId) as code, Max(pc.recId) as Name , Max(pc.v_Date) AS Date FROM pBill AS pb LEFT JOIN pBillDetail pbd on pbd.docId = pb.docId LEFT JOIN pChallan pc ON pc.docId=pbd.pcDocId LEFT JOIN voucher_Type as vt on vt.v_Type = pb.v_Type WHERE pb.site_Code = ' {…} ' AND vt.category IN ( {…} ) group by pc.v_Date, pc.docId`

### `getTxtHBillNo`
`public DmResultPageing<List<TxtHGeneralPurchaseBillNo_Mdl>> getTxtHBillNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT pb.docId, pb.recId as pbRecId, pb.v_Date as pbDate, vt.category FROM pBill AS pb left join voucher_Type as vt on vt.v_Type = pb.v_Type where vt.category in ( {…} ) and pb.site_Code = ' {…} '`

### `getTxtHStatus`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHStatus(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHCategory`
`public DmResultPageing<List<TxtHCategory_Mdl>> getTxtHCategory(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select description ,category AS name,v_Type AS code from voucher_Type where category IN( {…} )`

### `getCardDetail`
`public DmResult<CardDetail_Mdl> getCardDetail()`

- SQL 1: `Select count(pb.docId) as cnt , SUM(case when DATEDIFF(day, pb.v_Date, CAST(getdate() AS date)) <= {…} then 1 else 0 end) as low , SUM(case when DATEDIFF(day, pb.v_Date, CAST(getdate() AS date)) > {…} and DATEDIFF(day, pb.v_Date, CAST(getdate() AS date)) <= {…} then 1 else 0 end) as medium , SUM(case when DATEDIFF(day, pb.v_Date, CAST(getdate() AS date)) >= {…} then 1 else 0 end) as high FROM pBill as pb LEFT JOIN voucher_type as vt on pb.v_Type = vt.v_Type LEFT JOIN( select pbd.docId from pBillDetail as pbd where pbd.isStatus = 0 group by pbd.docId ) as pbdd on pbdd.docId = pb.docId LEFT JOIN walkThroughFootPrint wtfp on wtfp.wtaCode = pb.wtaCode and wtfp.isStatus = 0 WHERE pbdd.docId is not null AND isNull(pb.approvedBy,'') = '' AND vt.category IN ( {…} ) AND isNull(wtfp.linkedUser,' {…} ,') like '% {…} ,%' AND pb.site_Code = :site_Code AND isNull(pb.cancelledBy,'') = ''`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `HIGH_PRIORITYDAYS.getDays`, `LOW_PRIORITYDAYS.getDays`, `MEDIUM_PRIORITYDAYS.getDays`

### `setApproval`
`public DmResult<String> setApproval(String approvedBy, boolean isApprove, String docId)`

- Other calls: `STApprovals.generalGrnApproval`

### `walkThrough`
`public DmResult<String> walkThrough(WalkThrough_Mdl walkThrough)`

- Other calls: `STApprovals.generalGrnApproval`, `WalkThrough_Server.walkThrough`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`


### `getTxtHSupplierPT`
`public DmResultPageing<List<TxtHSupplierPT_Mdl>> getTxtHSupplierPT(boolean flagFilterByVoucherCategory, String v_Type, DmHelper pageing, boolean blnToEndOfFile)`


### `getSupplierDetail`
`private List<TxtHSupplierPT_Mdl> getSupplierDetail(String vendorCode, boolean flagFilterByVoucherCategory, String v_Type, DmHelper pageing, boolean blnToEndOfFile, Connection cnTemp) throws Exception`

- SQL 1: `SELECT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_type = acf.v_Type WHERE vt.category IN ( {…} )`
- SQL 2: `SELECT acf.Nature FROM AcFilteration acf WHERE acf.v_Type = :v_Type`
- SQL 3: `SELECT sg.subCode AS code , sg.name , sg.manualCode , c.cityName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( {…} ) AND sg.isActive = 1 AND sgsl.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vendorCode` ← `vendorCode`; `:v_Type` ← `DmShared.iNull(v_Type).toString()`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.dashboardCardStrip.client`, `paperbiz.selfToolkit.dashboardCardStrip.shared`, `paperbiz.selfToolkit.generalPurchaseBillApproval.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
**Standard approval screen** (pattern `_module.md` me). Is document ke liye: pending/approved list `getFillRecords` (Setting popup ke filters), approve/reject `setApproval(approvedBy, isApprove, docId)`, dashboard card ka count `getCardDetail` (din ke ageing buckets), lookups `getTxtH…`. Document ka approval logic `stStatic/STApprovals` me.
