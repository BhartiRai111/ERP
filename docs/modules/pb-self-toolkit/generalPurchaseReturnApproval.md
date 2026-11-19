# pb-self-toolkit / generalPurchaseReturnApproval

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/generalPurchaseReturnApproval/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHStatus, getTxtHBillNo, getTxtHSupplier
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `GeneralPurchaseReturnApproval`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmGridPaging | `dmGrd` | `Grd_PrpAcs, gridRows, false` |

**Grid / popup columns** (header, width, db field):
- `dmGrd`:  [Text, w30];  [Text, w25]; Return No. [Text, w100, recId];  Date [Text, w100, v_date]; Return Type [Text, w100, short_Name]; Supplier [Text, w200, suppName]; Bill No. [Text, w150, partyBillNo]; Bill Date [Text, w100, replace(convert(varchar, partyBillDate, 106), ' ','-')]; Net Amount [Text, w120, oh_Amt_Net]; Reason [Text, w200, reason]; Approved By [Text, w100, approvedBy]

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
setting_Mdl = approvalEntry_Mdl;
dmGrd.getDmGrid().commitChanges();
dmGrd.reQuery();
```

`dmGrd.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(true).getFillRecords(param, helper, blnToEndOfFile, vCatGeneralPR, new AsyncCallback<DmResultPageing<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdGeneralPurchaseReturnApproval_Mdl>> result) {
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
| 3 | Status | DmTextHelp | `txtHStatus` | `KeyField_PrpAcs, 2, true, false` |
| 4 | Supplier | DmTextHelpMulti | `txtHSupplier` | `TxtHVendor_PrpAcs, 5` |
| 5 | Bill No. | DmTextHelpMulti | `txtHBillNo` | `TxtHBillNo_PrpAcs, 7` |

**Grid / popup columns** (header, width, db field):
- `txtHBillNo`: Bill No. [Text, w100, recId]; Type [Text, w80, category ]; Bill Date [Text, w100, replace(convert(varchar, v_Date, 106), ' ','-')]; Bill No. [Text, w100, partyBillNo]; Party Bill Date [Text, w120, replace(convert(varchar, partyBillDate, 106), ' ','-')]; Supplier [Text, w200, suppName]
- `txtHSupplier`: Name [Text, w250, Name]; Manual Code [Text, w100, manualCode]; City [Text, w100, cityName]
- `txtHStatus`: Status [Text, w1, Name]

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
SrvAsync.invokeRPC(blnCallBackWait).getTxtHSupplier(true, null, helper, blnToEndOfFile, vCatGeneralPR, new AsyncCallback
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

`txtHBillNo.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHBillNo(vCatGeneralPR, helper, blnToEndOfFile, new AsyncCallback<DmResultPagei
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHBillNo).show();
public void onSuccess(DmResultPageing<List<TxtHBillNo_Mdl>> result) {
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
- Alert: "From Date should be less than or equal to To Date."

## Files
| file | lines |
|---|---|
| client/GeneralPurchaseReturnApproval.java | 379 |
| client/GeneralPurchaseReturnApprovalCard.java | 142 |
| client/GeneralPurchaseReturnApproval_Dsg.java | 254 |
| client/Setting.java | 253 |
| client/Setting_Dsg.java | 242 |
| server/GeneralPurchaseReturnApproval_SrvImpl.java | 470 |
| shared/DefaultValue_Mdl.java | 22 |
| shared/GeneralPurchaseReturnApproval_Srv.java | 26 |
| shared/GeneralPurchaseReturnApproval_SrvAsync.java | 28 |
| shared/GrdGeneralPurchaseReturnApproval_Mdl.java | 177 |
| shared/Setting_Mdl.java | 165 |
| shared/TxtHBillNo_Mdl.java | 59 |
| shared/TxtHGrnNo_Mdl.java | 38 |
| shared/TxtHSupplier_Mdl.java | 122 |

## UI labels / columns (client)
`From Date`, `To Date`, `Status`, `Supplier`, `Bill No.`

## Models (shared DTOs)
### TxtHBillNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | date |
| String | type |
| String | partyBillNo |
| String | partyBillDate |
| String | supplierName |

### TxtHGrnNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | challanNo |
| String | challanDate |

### GrdGeneralPurchaseReturnApproval_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | wtaCode |
| String | entryNo |
| String | entryNoCode |
| String | entryDate |
| String | approvedBy |
| String | approval |
| String | returnCatCode |
| String | returnCatName |
| String | supplierName |
| String | currentUser |
| String | netAmount |
| String | gstReasonCode |
| String | gstReasonName |
| String | partyBillNo |
| String | partyBillDate |
| String | isChecked |
| String | isView |
| String | isStatus |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | currentDate |

### Setting_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | fromDate |
| String | toDate |
| String | supplierName |
| String | supplierCode |
| String | StatusCode |
| String | StatusName |
| String | billnoDocId |
| String | billnoRecId |
| String | vSNo |
| String | siteCode |
| String | siteName |
| String | approvedBy |
| String | isStatus |
| String | approvedDateTime |
| String | wtaCode |
| List<GrdGeneralPurchaseReturnApproval_Mdl> | grdPurchaseBillApproval_Mdl |

### TxtHSupplier_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityCode |
| String | cityName |
| String | gstin |
| String | gstinDate |
| String | aadhaarNo |
| String | panNo |
| String | partyType |
| String | stateCode |
| String | stateName |
| String | stateNumericCode |
| String | isUnionTeritory |

## RPC interface
| method | params | returns |
|---|---|---|
| getFillRecords | Setting_Mdl param, DmHelper helper, boolean blnToEndOfFile, String vCat | DmResultPageing<List<GrdGeneralPurchaseReturnApproval_Mdl>> |
| getTxtHBillNo | String vCat,DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHBillNo_Mdl>> |
| getTxtHStatus | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSupplier | boolean flagFilterByVoucherCategory, String v_Type, DmHelper pageing, boolean blnToEndOfFile, String vCat | DmResultPageing<List<TxtHSupplier_Mdl>> |
| setApproval | String approvedBy, boolean isApprove, String docId | DmResult<String> |
| getCardDetail | String vCat | DmResult<CardDetail_Mdl> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |

## Server `GeneralPurchaseReturnApproval_SrvImpl.java` (470 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.xNull`

### `getFillRecords`
`public DmResultPageing<List<GrdGeneralPurchaseReturnApproval_Mdl>> getFillRecords(Setting_Mdl param, DmHelper helper, boolean blnToEndOfFile, String vCat)`

- SQL 1: `SELECT pb.docId,pb.recId ,pb.v_date , pb.partyBillNo , pb.partyBillDate, sgv.name AS suppName , vt.short_Name ,pb.wtaCode,gsm.name as reason,pb.oh_Amt_Net , concat(pb.approvedBy, ' ' , format(pb.approvalDateTime, 'dd-MMM-yyyy HH.mm')) as approval , pb.approvedBy , case when isNull(pb.isBifurcateService,'') = 0 then 'No' else 'Yes' end as bifurcateService FROM pBill pb LEFT JOIN voucher_Type vt ON pb.v_Type = vt.v_Type LEFT JOIN subGroup sgv ON pb.subCode = sgv.subCode left join gstReasonMast gsm on gsm.code = pb.gstReasonCode {…} WHERE vt.category IN ( {…} ) {…}`
- Params: `:fromv_Date` ← `DmServer.convertDate(param.getFromDate())`; `:tov_Date` ← `DmServer.convertDate(param.getToDate())`

### `getTxtHStatus`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHStatus(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHBillNo`
`public DmResultPageing<List<TxtHBillNo_Mdl>> getTxtHBillNo(String vCat, DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT pb.docId, pb.recId, pb.v_Date, vt.category, pb.partyBillNo , pb.partyBillDate , sg.name AS suppName FROM pBill AS pb LEFT JOIN subGroup sg ON pb.subCode = sg.subCode LEFT JOIN voucher_Type as vt on vt.v_Type = pb.v_Type WHERE vt.category IN ( {…} ) and pb.site_Code = ' {…} '`

### `setApproval`
`public DmResult<String> setApproval(String approvedBy, boolean isApprove, String docId)`

- Other calls: `STApprovals.generalPurchaseReturnApproval`

### `walkThrough`
`public DmResult<String> walkThrough(WalkThrough_Mdl walkThrough)`

- Other calls: `STApprovals.generalPurchaseReturnApproval`, `WalkThrough_Server.walkThrough`

### `getCardDetail`
`public DmResult<CardDetail_Mdl> getCardDetail(String vCat)`

- SQL 1: `Select count(pb.docId) as cnt, sum(case when DATEDIFF(day, pb.v_Date, CAST(getdate() AS date)) <= {…} then 1 else 0 end) as low, sum(case when DATEDIFF(day, pb.v_Date, CAST(getdate() AS date)) > {…} and DATEDIFF(day, pb.v_Date, CAST(getdate() AS date)) <= {…} then 1 else 0 end) as medium, sum(case when DATEDIFF(day, pb.v_Date, CAST(getdate() AS date)) >= {…} then 1 else 0 end) as high from pBill as pb left join voucher_type as vt on pb.v_Type = vt.v_Type left join( select pbd.docId from pBillDetail as pbd where pbd.isStatus = 0 group by pbd.docId ) as vPbd on vPbd.docId = pb.docId LEFT JOIN walkThroughFootPrint wtfp on wtfp.wtaCode = pb.wtaCode and wtfp.isStatus = 0 where vPbd.docId is not null and isNull(pb.approvedBy,'') = '' and vt.category IN ( {…} ) AND isNull(wtfp.linkedUser,' {…} ,') like '% {…} ,%' and pb.site_Code = :site_Code AND isNull(pb.cancelledBy,'') = ''`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `HIGH_PRIORITYDAYS.getDays`, `LOW_PRIORITYDAYS.getDays`, `MEDIUM_PRIORITYDAYS.getDays`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`


### `getTxtHSupplier`
`public DmResultPageing<List<TxtHSupplier_Mdl>> getTxtHSupplier(boolean flagFilterByVoucherCategory, String v_Type, DmHelper helper, boolean blnToEndOfFile, String vCat)`

- SQL 1: `SELECT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_type = acf.v_Type WHERE vt.category IN ( {…} )`
- SQL 2: `SELECT acf.Nature FROM AcFilteration acf WHERE acf.v_Type = :v_Type`
- SQL 3: `SELECT sg.subCode AS code, sg.name, sg.manualCode, c.cityName, c.stateCode, sg.partyType, sg.gstin, sg.gstinDate FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( {…} ) AND sg.isActive = 1 AND sgsl.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Type` ← `DmShared.iNull(v_Type).toString()`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.dashboardCardStrip.client`, `paperbiz.selfToolkit.dashboardCardStrip.shared`, `paperbiz.selfToolkit.generalPurchaseReturnApproval.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
**Standard approval screen** (pattern `_module.md` me). Is document ke liye: pending/approved list `getFillRecords` (Setting popup ke filters), approve/reject `setApproval(approvedBy, isApprove, docId)`, dashboard card ka count `getCardDetail` (din ke ageing buckets), lookups `getTxtH…`. Document ka approval logic `stStatic/STApprovals` me.
