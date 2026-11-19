# pb-self-toolkit / requisitionApproval

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/requisitionApproval/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: RequisitionDetail, Requisition
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHItem, getTxtHStatus, getTxtHIndenter, getTxtHDepartment, getTxtHBrand, getTxtHSKU, getTxtHRequisitionNo
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `RequisitionApprovalGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Requisition No. | DmTextBox | `txtRequitNo` | `` |
| 2 | Requisition Date | DmTextBox | `txtRequitDate` | `` |
| 3 | Department | DmTextBox | `txtDepartment` | `` |
| 4 | Requisition By | DmTextBox | `txtRequisitionBy` | `` |

Other widgets (not in tab list): `txtApproved` (DmTextBox), `txtStatus` (DmTextBox), `txtCurrency` (DmTextBox), `txtWTACode` (DmTextBox), `txtCurrentUser` (DmTextBox), `txtApprovedBy` (DmTextBox), `txtApprovedDateTime` (DmTextBox), `grdMedicineDetails` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `grdMedicineDetails`: C/O [Text, w30]; Item Name [Text, w1]; Properties [Text, w260]; Req. Qty. [Text, w80]; Req. Date [Text, w80]; Remark [Text, w100]

**Lookup / suggestion / find sources (server RPC called):**
- `walkThrough` → `openRecord`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtApproved.setEnabled(false)`
- `txtApprovedBy.setEnabled(false)`
- `txtApprovedDateTime.setEnabled(false)`
- `txtStatus.setEnabled(false)`
- `txtRequitNo.setEnabled(false)`
- `txtRequitDate.setEnabled(false)`
- `txtRequisitionBy.setEnabled(false)`
- `txtDepartment.setEnabled(false)`
- `txtCurrency.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
txtApproved.setEnabled(false);
txtApprovedBy.setEnabled(false);
txtApprovedDateTime.setEnabled(false);
txtStatus.setEnabled(false);
txtRequitNo.setEnabled(false);
txtRequitDate.setEnabled(false);
txtRequisitionBy.setEnabled(false);
txtDepartment.setEnabled(false);
txtCurrency.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`walkThrough.addCallBack` → `onClose()`
```java
fillGrid(DmShared.iNull(txtRequitNo.getTag()).toString());
```
- Alert: "Record is not updated by this user,Can't update"
- Alert: "Record is cancelled,Please refresh record."
- Alert: "Record is completed,Please refresh record."

### Screen `RequisitionApproval`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmGridPaging | `dmGrd` | `Grd_PrpAcs, gridRows, false` |

**Grid / popup columns** (header, width, db field):
- `dmGrd`:  [Text, w30];  [Text, w25]; Req. Qty. [Text, w83, SKUQty]; SKU [Text, w50, unit]; Item Name [Text, w150, itemName]; Properties [Text, w279]; Required Date [Text, w80, reqDate]; Requisition No. [Text, w80, recId]; Req. Date [Text, w80, v_Date]; Vr. Type [Text, w100, IndType]; Approved By [Text, w130, approvedBy]; Department [Text, w80, depName]; Site [Text, w120, siteName]

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
requisitionApproval = approvalEntry_Mdl;
dmGrd.reQuery();
```

`gridEdit.addGridEditCallBack` → `onOk()`
```java
dmGrd.getDmGrid().commitChanges();
dmGrd.reQuery();
```

`dmGrd.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(true).getFillRecords(param, helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<GrdRequisi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdRequisitionApproval_Mdl>> result) {
if (result.isStatus()) {
dmGrd.setData(result.getData(), result.getPaging());
if (RequisitionApproval.this.callBack != null) {
DmResult<Object> dmrTemp = new DmResult<>();
dmrTemp.setStatus(true);
RequisitionApproval.this.callBack.result(dmrTemp);
...
```

`dmGrd.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
dmGrd.setData(result.getData(), result.getPaging());
if (RequisitionApproval.this.callBack != null) {
DmResult<Object> dmrTemp = new DmResult<>();
dmrTemp.setStatus(true);
RequisitionApproval.this.callBack.result(dmrTemp);
RequisitionApproval.this.callBack = null;
```

`dmGrd.addCallBack` → `getParam()`
```java
return requisitionApproval;
```
- Confirm: "Are you sure? You want to close."

### Screen `Setting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | From Date | DmTextBox | `txtFromDate` | `15, false` |
| 2 | To Date | DmTextBox | `txtToDate` | `15, false` |
| 3 | Department | DmTextHelpMulti | `txtHDepartment` | `KeyField_PrpAcs, 4` |
| 4 | Requested By | DmTextHelpMulti | `txtHRequestedBy` | `KeyField_PrpAcs, 5` |
| 5 | Item Name | DmTextHelpMulti | `txtHItemName` | `KeyField_PrpAcs, 5` |
| 6 | Requisition No. | DmTextHelpMulti | `txtHRequisitionNo` | `TxtHRequisitionNo_PrpAcs, 7` |
| 7 | Status | DmTextHelp | `txtHStatus` | `KeyField_PrpAcs, 4, false` |

**Grid / popup columns** (header, width, db field):
- `txtHRequisitionNo`: Requisition No. [Text, w90, requisitionNo]; Req. Type [Text, w80, category]; Req. Date [Text, w80, requisitionDate]
- `txtHDepartment`: Name [Text, w0, Name]
- `txtHRequestedBy`: Name [Text, w0, Name]
- `txtHItemName`: Name [Text, w0, Name]
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

`txtHRequestedBy.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHIndenter(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHRequestedBy).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHRequestedBy.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHRequestedBy).show();
```

`txtHRequestedBy.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHRequestedBy.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHRequestedBy).show();
```

`txtHRequestedBy.addTextHelpMultiCallBack` → `getSelection()`
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

`txtHDepartment.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHDepartment(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Key
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

`txtHItemName.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHItem(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemName).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
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

`txtHItemName.addTextHelpMultiCallBack` → `getSelection()`
```java
if (blnStatus) {
```

`txtHRequisitionNo.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHRequisitionNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHRequisitionNo).show();
public void onSuccess(DmResultPageing<List<TxtHRequisitionNo_Mdl>> result) {
if (result.isStatus()) {
txtHRequisitionNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHRequisitionNo).show();
```

`txtHRequisitionNo.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHRequisitionNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHRequisitionNo).show();
```

`txtHRequisitionNo.addTextHelpMultiCallBack` → `getSelection()`
```java
if (blnStatus) {
```
- Alert: "From Date should be less than or equal to To Date."

## Files
| file | lines |
|---|---|
| client/RequisitionApproval.java | 424 |
| client/RequisitionApprovalCard.java | 137 |
| client/RequisitionApprovalGridEdit.java | 290 |
| client/RequisitionApprovalGridEdit_Dsg.java | 286 |
| client/RequisitionApproval_Dsg.java | 261 |
| client/Setting.java | 346 |
| client/Setting_Dsg.java | 279 |
| server/RequisitionApproval_SrvImpl.java | 968 |
| shared/DefaultValue_Mdl.java | 22 |
| shared/GrdMedicineDetail_Mdl.java | 166 |
| shared/GrdRequisitionApproval_Mdl.java | 248 |
| shared/RequisitionApproval_Mdl.java | 255 |
| shared/RequisitionApproval_Srv.java | 38 |
| shared/RequisitionApproval_SrvAsync.java | 38 |
| shared/TxtHRequisitionNo_Mdl.java | 33 |

## UI labels / columns (client)
`Requisition No.`, `Requisition Date`, `Department`, `Requisition By`, `From Date`, `To Date`, `Requested By`, `Item Name`, `Status`

## Models (shared DTOs)
### GrdRequisitionApproval_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | IndNo |
| String | IndDate |
| String | ItemName |
| String | properties |
| String | ItemCode |
| String | ReqDate |
| String | SKUQty |
| String | SiteCode |
| String | SiteName |
| String | ApprovedBy |
| String | Approval |
| String | voucherType |
| String | isStatus |
| String | DepartmentCode |
| String | DepartmentName |
| String | SKUCode |
| String | SKUName |
| String | isChecked |
| String | vSNo |
| String | wtaCode |
| String | isView |
| String | displayProperties |
| String | ibCode |
| String | itemType |
| ItemProperty_Mdl | ipm |

### TxtHRequisitionNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | reqType |
| String | reqDate |

### GrdMedicineDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | ApprovedBy |
| String | ApprovedDateTime |
| String | requisitionNo |
| String | isStatus |
| String | isChecked |
| String | vSNo |
| String | reqQty |
| String | medicineName |
| String | reqDate |
| String | Remark |
| String | image |
| ItemProperty_Mdl | ipm |
| String | properties |
| String | itemType |
| String | ibCode |
| String | propertyList |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | currentDate |

### RequisitionApproval_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | FromDate |
| String | ToDate |
| String | ItemName |
| String | ItemCode |
| String | DepartmentName |
| String | DepartmentCode |
| String | StatusCode |
| String | StatusName |
| String | IndenterName |
| String | IndenterCode |
| String | requisitionDocIds |
| String | requisitionRecIds |
| String | requitDocId |
| String | requitRecId |
| String | vSNo |
| String | requitDate |
| String | requitType |
| String | siteCode |
| String | siteName |
| String | approvedBy |
| String | isStatus |
| String | approvedDateTime |
| String | requestedByCode |
| String | requestedByName |
| String | wtaCode |
| List<GrdMedicineDetail_Mdl> | grdItemDetail_Mdl |
| List<GrdRequisitionApproval_Mdl> | requisitionApprovalEntry_Mdl |

## RPC interface
| method | params | returns |
|---|---|---|
| openRecord | boolean isOpen, String docId, String v_SNo | DmResult<String> |
| fillItemGrid | String code | DmResult<RequisitionApproval_Mdl> |
| getFillRecords | RequisitionApproval_Mdl param, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdRequisitionApproval_Mdl>> |
| getTxtHItem | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDepartment | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHBrand | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSKU | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHStatus | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHIndenter | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHRequisitionNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHRequisitionNo_Mdl>> |
| setApproval | String approvedBy, boolean isApprove, String docId | DmResult<String> |
| getCardDetail |  | DmResult<CardDetail_Mdl> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |

## Server `RequisitionApproval_SrvImpl.java` (968 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.xNull`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`


### `getFillRecords`
`public DmResultPageing<List<GrdRequisitionApproval_Mdl>> getFillRecords(RequisitionApproval_Mdl param, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select rq.departmentCode , sdm.name AS depName , rq.requestedByCode , sg.name AS intenderName , rq.recId , rq.docId , rq.v_Date , rqd.itemCode , im.name AS itemName , rqd.reqDate , rqd.remark , rq.site_Code , sm.name AS siteName , rq.approvedBy , FORMAT(rq.approvalDateTime,'dd-MMM-yyyy HH:mm') AS approvalDateTime , rq.wtaCode , rq.v_Type,vt.category AS IndType , im.sku , um.unit , imm.companyName as itemMakeName , rqd.isStatus , rqd.reqQty AS SKUQty,rqd.v_SNo ,rqd.ibCode , im.propertyList , it.manualCode as itemType from requisition AS rq LEFT JOIN requisitionDetail AS rqd ON rqd.docId=rq.docId LEFT JOIN itemMast AS im ON im.code=rqd.itemCode LEFT JOIN itemType it on it.code = im.itemType LEFT JOIN unitMast AS um ON im.sku = um.Code LEFT JOIN itemMakeMast AS imm ON imm.code = im.itemMakeCode LEFT JOIN siteMast AS sm ON sm.code=rq.site_Code LEFT JOIN subGroup AS sg on sg.subCode=rq.requestedByCode LEFT JOIN subgroup AS sdm on sdm.subcode=rq.departmentCode LEFT JOIN voucher_Type AS vt on vt.v_Type=rq.v_Type {…} where vt.category IN ( {…} ) {…}`
- Params: `:fromv_Date` ← `DmServer.convertDate(param.getFromDate())`; `:tov_Date` ← `DmServer.convertDate(param.getToDate())`
- Other calls: `ItemBatch_Server.getIPM`, `ItemPropertyPopup_Vld.getItemApplicableProperties`

### `openRecord`
`public DmResult<String> openRecord(boolean isOpen, String docId, String v_SNo)`

- SQL 1: `Update RequisitionDetail set isStatus=:isStatus where docId=:docId AND v_SNo=:vSNo`
- SQL 2: `select Count(isStatus) as cnt from RequisitionDetail where docId = :docId AND v_SNo=:VSNo AND isStatus = 1`
- SQL 3: `select Count(isStatus) as cnt from RequisitionDetail where docId = :docId AND v_SNo=:VSNo AND isStatus = 2`
- SQL 4: `UPDATE RequisitionDetail SET isStatus = 1 WHERE docId = :docId AND v_SNo =:vSNo`
- SQL 5: `Update Requisition set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- Params: `:docId` ← `DmShared.iNull(docId).toString()`; `:vSNo` ← `DmShared.iNull(v_SNo).toString()`; `:isStatus` ← `"0"`; `:docId` ← `DmShared.iNull(docId).toString()`; `:VSNo` ← `DmShared.iNull(v_SNo).toString()`; `:docId` ← `DmShared.iNull(docId).toString()`; `:VSNo` ← `DmShared.iNull(v_SNo).toString()`; `:docId` ← `DmShared.iNull(docId).toString()`; `:vSNo` ← `DmShared.iNull(v_SNo).toString()`; `:docId` ← `DmShared.iNull(docId).toString()`; `:uEntDt` ← `DmServer.getServerTimestamp()`
- Errors: "Record is already cancelled,Please refresh record." | "Record is completed,Please refresh record."

### `fillItemGrid`
`public DmResult<RequisitionApproval_Mdl> fillItemGrid(String code)`

- SQL 1: `SELECT req.docId,req.recId,req.v_Date, req.approvedBy,req.departmentCode,sgd.name AS depName, FORMAT(req.approvalDateTime,'dd-MMM-yyyy HH:mm') AS approvalDateTime,req.wtaCode, req.requestedByCode AS reqByCode,sgv.name AS reqByName FROM requisition AS req LEFT JOIN voucher_Type AS vt ON req.v_Type = vt.v_Type LEFT JOIN subGroup AS sgv ON req.requestedByCode = sgv.subCode LEFT JOIN departmentMast AS dm on dm.subCode=req.departmentCode LEFT JOIN subGroup AS sgd ON dm.subCode= sgd.subCode where req.docId=:docId`
- SQL 2: `select reqd.docId , reqd.itemCode , im.name as itemName , reqd.reqQty , reqd.reqDate , reqd.remark , reqd.v_SNo, reqd.isStatus , im.propertyList , reqd.ibCode , it.manualCode as itemType from requisitionDetail as reqd left join itemMast as im on reqd.itemCode = im.code LEFT JOIN itemBatch ib on ib.code = reqd.ibCode LEFT JOIN itemType it on it.code = im.itemType where reqd.docId=:docId order by itemName Asc`
- Params: `:docId` ← `DmShared.xNull(code)`; `:docId` ← `DmShared.xNull(code)`
- Other calls: `ItemBatch_Server.getIPM`, `ItemPropertyPopup_Vld.getItemApplicableProperties`

### `getTxtHItem`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.name, im.code FROM itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code WHERE (im.filterItemType = :filterItemType OR im.isCommon = 1) AND sl.site_Code = :site_Code`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHStatus`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHStatus(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHIndenter`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHIndenter(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select max(sg.name) AS Name,max(mid.requestedByCode) AS requestedByCode from requisition mid LEFT JOIN subGroup AS sg on sg.subCode=mid.requestedByCode GROUP BY mid.requestedByCode`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT dm.subCode, sg.name FROM departmentMast dm LEFT JOIN subGroup sg ON sg.subCode = dm.subCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.manualCode IN ('CM','PR') AND sg.isActive = 1 {…}`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getCardDetail`
`public DmResult<CardDetail_Mdl> getCardDetail()`

- SQL 1: `Select count(req.docId) as cnt, sum(case when DATEDIFF(day, req.v_Date, CAST(getdate() AS date)) <= {…} then 1 else 0 end) as low, sum(case when DATEDIFF(day, req.v_Date, CAST(getdate() AS date)) > {…} and DATEDIFF(day, req.v_Date, CAST(getdate() AS date)) <= {…} then 1 else 0 end) as medium, sum(case when DATEDIFF(day, req.v_Date, CAST(getdate() AS date)) > {…} then 1 else 0 end) as high from requisition as req left join voucher_type as vt on req.v_Type = vt.v_Type left join( select reqd.docId from requisitionDetail as reqd where reqd.isStatus = 0 group by reqd.docId ) as reqd on reqd.docId = req.docId LEFT JOIN walkThroughFootPrint wtfp on wtfp.wtaCode = req.wtaCode and wtfp.isStatus = 0 where reqd.docId is not null and isNull(req.approvedBy,'') = '' and vt.category IN ( {…} ) AND isNull(wtfp.linkedUser,' {…} ,') like '% {…} ,%' and req.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `LOW_PRIORITYDAYS.getDays`, `MEDIUM_PRIORITYDAYS.getDays`

### `getTxtHBrand`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHBrand(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select max(mm.itemMakeCode) AS CODE, max(mmm.companyName) AS NAME from itemMast AS mm LEFT JOIN itemMakeMast AS mmm ON mmm.code = mm.itemMakeCode group by mm.itemMakeCode`

### `getTxtHSKU`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSKU(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select Code,unit AS Name from unitMast`

### `getTxtHRequisitionNo`
`public DmResultPageing<List<TxtHRequisitionNo_Mdl>> getTxtHRequisitionNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select rq.docId, rq.recId as requisitionNo, rq.v_Date as requisitionDate, rq.v_Type,vt.category,vt.short_Name from requisition as rq left join voucher_Type as vt on rq.v_Type = vt.v_Type where rq.site_Code = ' {…} ' and vt.category IN ( {…} )`

### `setApproval`
`public DmResult<String> setApproval(String approvedBy, boolean isApprove, String docId)`

- Other calls: `STApprovals.requisitionApproval`

### `walkThrough`
`public DmResult<String> walkThrough(WalkThrough_Mdl walkThrough)`

- Other calls: `STApprovals.requisitionApproval`, `WalkThrough_Server.walkThrough`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.dashboardCardStrip.client`, `paperbiz.selfToolkit.dashboardCardStrip.shared`, `paperbiz.selfToolkit.itemBatch.server`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.requisitionApproval.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| openRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() == 1)` | "Record is already cancelled,Please refresh record." |
| openRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() == 1)` | "Record is completed,Please refresh record." |

## Notes (hand-written)
_No hand-written notes yet._
