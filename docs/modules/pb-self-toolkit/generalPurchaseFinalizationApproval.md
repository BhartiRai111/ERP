# pb-self-toolkit / generalPurchaseFinalizationApproval

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/generalPurchaseFinalizationApproval/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHStatus, getTxtHPurchaseBill, getTxtHSupplierPT, getTxtHPurchaseFinalization
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `GeneralPurchaseFinalizationApproval`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmGridPaging | `dmGrd` | `Grd_PrpAcs, gridRows, false` |

**Grid / popup columns** (header, width, db field):
- `dmGrd`:  [Text, w30];  [Text, w25]; Finalization No. [Text, w100, recId]; Date [Text, w80, v_Date]; Type [Text, w80, short_Name]; Supplier [Text, w150, supplierName]; Billing On [Text, w120]; Bill No [Text, w100, pbRecId]; Bill Date [Text, w80, pbDate]; Net Amount [Text, w100, oh_Amt_Net]; Approved By [Text, w130, approvedBy]

**Lookup / suggestion / find sources (server RPC called):**
- `dmGrd` → `getFillRecords`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`walkThrough.addCallBack` → `onClose()`
```java
dmGrd.reQuery();
```

`dmGrd.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(true).getFillRecords(param, helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<GrdGeneral
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdGeneralPurchaseFinalizationApproval_Mdl>> result) {
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
| 3 | Finalization No. | DmTextHelpMulti | `txtHPurchaseFinalization` | `TxtHPurchaseFinalization_PrpAcs, 7` |
| 4 | Bill No. | DmTextHelpMulti | `txtHPurchaseBill` | `TxtHPurchaseOrder_PrpAcs, 7` |
| 5 | Supplier | DmTextHelpMulti | `txtHSupplier` | `TxtHSupplierName_PrpAcs, 7` |
| 6 | Status | DmTextHelp | `txtHStatus` | `KeyField_PrpAcs, 4, false` |

**Grid / popup columns** (header, width, db field):
- `txtHPurchaseBill`: Purchase Bill No. [Text, w120, pbOrderNo]; Purchase Type [Text, w90, category]; Purchase Date [Text, w80, replace(convert(varchar, pbOrderDate, 106), ' ','-')]
- `txtHPurchaseFinalization`:  Finalization No. [Text, w120, recId]; Finalization Type [Text, w90, category]; Finalization Date [Text, w80, replace(convert(varchar, pfDate, 106), ' ','-')]; Bill No [Text, w120, pbRecId]; Bill Date [Text, w80, replace(convert(varchar, pbV_date, 106), ' ','-')]
- `txtHSupplier`: Name [Text, w250, name]; Manual Code [Text, w100, manualCode]; City Name [Text, w100, cityName]
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

`txtHPurchaseFinalization.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHPurchaseFinalization(helper, blnToEndOfFile, new AsyncCallback<DmResultPagein
public void onFailure(Throwable caught) {
public void onSuccess(DmResultPageing<List<TxtHPurchaseFinalization_Mdl>> result) {
if (result.isStatus()) {
txtHPurchaseFinalization.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPurchaseFinalization).show();
```

`txtHPurchaseFinalization.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHPurchaseFinalization.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPurchaseFinalization).show();
```

`txtHPurchaseBill.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHPurchaseBill(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<T
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHPurchaseBill).show();
public void onSuccess(DmResultPageing<List<TxtHGeneralPurchaseBill_Mdl>> result) {
if (result.isStatus()) {
txtHPurchaseBill.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPurchaseBill).show();
```

`txtHPurchaseBill.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHPurchaseBill.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPurchaseBill).show();
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
| client/GeneralPurchaseFinalizationApproval.java | 351 |
| client/GeneralPurchaseFinalizationApprovalCard.java | 141 |
| client/GeneralPurchaseFinalizationApproval_Dsg.java | 248 |
| client/Setting.java | 275 |
| client/Setting_Dsg.java | 270 |
| server/GeneralPurchaseFinalizationApproval_SrvImpl.java | 564 |
| shared/DefaultValue_Mdl.java | 22 |
| shared/GeneralPurchaseFinalizationApproval_Mdl.java | 234 |
| shared/GeneralPurchaseFinalizationApproval_Srv.java | 27 |
| shared/GeneralPurchaseFinalizationApproval_SrvAsync.java | 27 |
| shared/GrdGeneralPurchaseFinalizationApproval_Mdl.java | 141 |
| shared/GrdItemDetail_Mdl.java | 194 |
| shared/TxtHGeneralPurchaseBill_Mdl.java | 32 |
| shared/TxtHItemName_Mdl.java | 32 |
| shared/TxtHPurchaseFinalization_Mdl.java | 51 |
| shared/TxtHSupplierPT_Mdl.java | 31 |

## UI labels / columns (client)
`From Date`, `To Date`, `Finalization No.`, `Bill No.`, `Supplier`, `Status`

## Models (shared DTOs)
### TxtHGeneralPurchaseBill_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | pbType |
| String | pbDate |

### TxtHItemName_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | purchaseUnitName |

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

### DefaultValue_Mdl
| type | field |
|---|---|
| String | currentDate |

### TxtHSupplierPT_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityName |

### GeneralPurchaseFinalizationApproval_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | FromDate |
| String | ToDate |
| String | StatusCode |
| String | StatusName |
| String | suppleirName |
| String | suppleirCode |
| String | pfCode |
| String | pfName |
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
| List<GrdGeneralPurchaseFinalizationApproval_Mdl> | grdPurchaseApprovalEntry_Lst |

### GrdGeneralPurchaseFinalizationApproval_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | isStatus |
| String | isChecked |
| String | currentUser |
| String | wtaCode |
| String | IsView |
| String | ApprovedBy |
| String | Approval |
| String | finalizationDate |
| String | supplierName |
| String | billingOn |
| String | purchaseNo |
| String | purchaseDate |
| String | shortName |
| String | ohAmtNet |

### TxtHPurchaseFinalization_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | pfType |
| String | pfDate |
| String | pbNo |
| String | pbDate |

## RPC interface
| method | params | returns |
|---|---|---|
| getFillRecords | GeneralPurchaseFinalizationApproval_Mdl param, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdGeneralPurchaseFinalizationApproval_Mdl>> |
| getTxtHStatus | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPurchaseFinalization | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHPurchaseFinalization_Mdl>> |
| getTxtHPurchaseBill | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHGeneralPurchaseBill_Mdl>> |
| getTxtHSupplierPT | boolean flagFilterByVoucherCategory, String v_Type, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHSupplierPT_Mdl>> |
| setApproval | String approvedBy, boolean isApprove, String docId | DmResult<String> |
| getCardDetail |  | DmResult<CardDetail_Mdl> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |

## Server `GeneralPurchaseFinalizationApproval_SrvImpl.java` (564 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerDateTime`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.xNull`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`


### `getFillRecords`
`public DmResultPageing<List<GrdGeneralPurchaseFinalizationApproval_Mdl>> getFillRecords(GeneralPurchaseFinalizationApproval_Mdl param, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select pf.docId, pf.recId, pf.v_Date, pf.v_No, pf.v_Prefix, pb.recId as pbRecId, pb.v_Date as pbDate, vt.short_Name, sg.name as supplierName, pf.calcOn, pf.oh_Amt_Net, pf.approvedBy, pf.approvalDateTime, pf.wtaCode FROM pFinalization pf LEFT JOIN ( select pfd.docId, max(pfd.pbDocId)as pbDocId from pFinalizationDetail pfd group by pfd.docId ) vPfd ON vPfd.docId = pf.docId LEFT JOIN pBill pb ON pb.docId = vPfd.pbDocId LEFT JOIN voucher_Type vt On vt.v_Type = pf.v_Type LEFT JOIN subGroup sg On sg.subCode = pf.subCode {…} WHERE vt.category IN ( {…} ) {…}`
- Params: `:fromv_Date` ← `DmServer.convertDate(param.getFromDate())`; `:tov_Date` ← `DmServer.convertDate(param.getToDate())`

### `getTxtHStatus`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHStatus(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHPurchaseBill`
`public DmResultPageing<List<TxtHGeneralPurchaseBill_Mdl>> getTxtHPurchaseBill(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT pb.docId, pb.recId as pbOrderNo, pb.v_Date as pbOrderDate, vt.category FROM pBill AS pb LEFT JOIN voucher_Type as vt on vt.v_Type = pb.v_Type WHERE pb.site_Code= ' {…} ' AND vt.category IN ( {…} )`

### `getTxtHSupplierPT`
`public DmResultPageing<List<TxtHSupplierPT_Mdl>> getTxtHSupplierPT(boolean flagFilterByVoucherCategory, String v_Type, DmHelper pageing, boolean blnToEndOfFile)`


### `getSupplierDetail`
`private List<TxtHSupplierPT_Mdl> getSupplierDetail(String vendorCode, boolean flagFilterByVoucherCategory, String v_Type, DmHelper pageing, boolean blnToEndOfFile, Connection cnTemp) throws Exception`

- SQL 1: `SELECT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_type = acf.v_Type WHERE vt.category IN ( {…} )`
- SQL 2: `SELECT acf.Nature FROM AcFilteration acf WHERE acf.v_Type = :v_Type`
- SQL 3: `SELECT sg.subCode AS code , sg.name , sg.manualCode , c.cityName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( {…} ) AND sg.isActive = 1 AND sgsl.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vendorCode` ← `vendorCode`; `:v_Type` ← `DmShared.iNull(v_Type).toString()`

### `setApproval`
`public DmResult<String> setApproval(String approvedBy, boolean isApprove, String docId)`

- Other calls: `STApprovals.generalPurchaseFinalizationApproval`

### `walkThrough`
`public DmResult<String> walkThrough(WalkThrough_Mdl walkThrough)`

- Other calls: `STApprovals.generalPurchaseFinalizationApproval`, `WalkThrough_Server.walkThrough`

### `getCardDetail`
`public DmResult<CardDetail_Mdl> getCardDetail()`

- SQL 1: `Select count(pf.docId) as cnt , sum(case when DATEDIFF(day, pf.v_Date, CAST(getdate() AS date)) <= {…} then 1 else 0 end) as low , sum(case when DATEDIFF(day, pf.v_Date, CAST(getdate() AS date)) > {…} and DATEDIFF(day, pf.v_Date, CAST(getdate() AS date)) <= {…} then 1 else 0 end) as medium , sum(case when DATEDIFF(day, pf.v_Date, CAST(getdate() AS date)) >= {…} then 1 else 0 end) as high from pFinalization as pf left join voucher_type as vt on pf.v_Type = vt.v_Type LEFT JOIN walkThroughFootPrint wtfp on wtfp.wtaCode = pf.wtaCode and wtfp.isStatus = 0 where isNull(pf.approvedBy,'') = '' and vt.category IN ( {…} ) AND isNull(wtfp.linkedUser,' {…} ,') like '% {…} ,%' and pf.site_Code = :site_Code AND isNull(pf.cancelledBy,'') = ''`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `HIGH_PRIORITYDAYS.getDays`, `LOW_PRIORITYDAYS.getDays`, `MEDIUM_PRIORITYDAYS.getDays`

### `getTxtHPurchaseFinalization`
`public DmResultPageing<List<TxtHPurchaseFinalization_Mdl>> getTxtHPurchaseFinalization(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT pf.docId ,vt.category AS category ,pf.recId ,pf.v_Date AS pfDate , vPfd.pbDocId , pb.recId as pbRecId , pb.V_date as pbV_date FROM pFinalization pf LEFT JOIN voucher_Type vt ON vt.v_Type = pf.v_Type LEFT JOIN ( select pfd.docId, MAX(pfd.pbDocId) as pbDocId from pFinalizationDetail pfd group by pfd.docId ) vPfd ON vPfd.docId = pf.docId LEFT JOIN pBill pb ON pb.docId = vPfd.pbDocId WHERE pf.site_Code= ' {…} ' AND vt.category IN ( {…} )`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.dashboardCardStrip.client`, `paperbiz.selfToolkit.dashboardCardStrip.shared`, `paperbiz.selfToolkit.generalPurchaseFinalizationApproval.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
