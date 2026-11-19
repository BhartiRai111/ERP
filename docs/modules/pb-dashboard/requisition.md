# pb-dashboard / requisition

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/dashBoard/src/requisition/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHDepartment, getTxtHItem, getTxtHIndenter, getTxtHRequisitionNo, getTxtHStatus, getTxtHSite
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `RequisitionDetailPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Requisition No. | DmTextBox | `txtRequisitionNo` | `50, false` |
| 2 | Date | DmTextBox | `txtDate` | `11, false` |
| 3 | Requisition By | DmTextBox | `txtRequistionBy` | `50, false` |
| 4 | Department | DmTextBox | `txtDepartment` | `50, false` |

Other widgets (not in tab list): `gridRequisitionDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `gridRequisitionDetail`: Item name [Text, w180]; Properties [Text, w150]; Qty. [Text, w80]; Unit [Text, w65]; Remark [Text, w160]

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtRequisitionNo.setEnabled(false)`
- `txtDate.setEnabled(false)`
- `txtRequistionBy.setEnabled(false)`
- `txtDepartment.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
txtRequisitionNo.setEnabled(false);
txtDate.setEnabled(false);
txtRequistionBy.setEnabled(false);
txtDepartment.setEnabled(false);
```

### Screen `Requisition`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmGridPaging | `gridRequisition` | `GridRequisition_PrpAcs, gridRows, false` |

Other widgets (not in tab list): `txtRequisitionNo` (DmTextBox), `txtDate` (DmTextBox), `txtDepartment` (DmTextBox), `txtRequistionBy` (DmTextBox), `txtSearchValue` (DmTextBox), `txtHDepartmentBy` (DmTextHelp)

**Grid / popup columns** (header, width, db field):
- `txtHDepartmentBy`: Name [Text, w0, NAME]
- `gridRequisition`: Detail [Text, w50]; Requisition No. [Text, w220, recId]; Date [Text, w110, replace(convert(varchar, v_Date, 106), ' ','-')]; Requisition By [Text, w310, requestedBy]; Department By [Text, w310, departmentBy]; Department To [Text, w310, departmentToName]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHDepartmentBy` → `getTxtHDepartment`
- `gridRequisition` → `getFillRecords`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtDate.setEnabled(false)`
- `txtDepartment.setEnabled(false)`
- `txtRequisitionNo.setEnabled(false)`
- `txtRequistionBy.setEnabled(false)`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**`manageDisplay()` logic (condensed code):**
```java
txtDate.setEnabled(false);
txtDepartment.setEnabled(false);
txtRequisitionNo.setEnabled(false);
txtRequistionBy.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`settingPopup.addCallBack` → `onOk()`
```java
searchValue_Mdl = sm;
gridRequisition.reQuery();
```

`txtHDepartmentBy.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHDepartment(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Key
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDepartmentBy).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHDepartmentBy.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDepartmentBy).show();
```

`txtHDepartmentBy.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHDepartmentBy.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDepartmentBy).show();
```

`txtHDepartmentBy.addTextHelpCallBack` → `getSelectedRow()`
```java
clear();
reQuery();
```

`gridRequisition.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnToEndOfFile).getFillRecords(txtHDepartmentBy.getTag(), setting_Mdl, helper, blnToEndOfFile, new As
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdRequisition_Mdl>> result) {
if (result.isStatus()) {
gridRequisition.setData(result.getData(), result.getPaging());
gridRequisition.getDmGrid().commitChanges();
if (dmCallBack != null) {
DmResult<Object> dmrTemp = new DmResult<>();
dmrTemp.setStatus(true);
...
```

`gridRequisition.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
gridRequisition.setData(result.getData(), result.getPaging());
gridRequisition.getDmGrid().commitChanges();
if (dmCallBack != null) {
DmResult<Object> dmrTemp = new DmResult<>();
dmrTemp.setStatus(true);
dmCallBack.result(dmrTemp);
dmCallBack = null;
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
...
```

`gridRequisition.addCallBack` → `getParam()`
```java
return searchValue_Mdl;
```
- Confirm: "Are you sure? You want to close."
- Alert: "Record is Cancelled!"
- Alert: "Record is Completed!"
- Alert: "No item is pending for indent"
- Alert: "Please select the requisition."
- Alert: "Record is Cancelled!"
- Alert: "Record is Completed!"
- Alert: "No item is pending for issue"
- Alert: "Please select the requisition."
- Alert: "Record is Cancelled!"
- Alert: "Record is Completed!"
- Alert: "No item is pending for tranfer"
- Alert: "Please select the requisition."

### Screen `Setting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | From Date | DmTextBox | `txtFromDate` | `15, false` |
| 2 | To Date | DmTextBox | `txtToDate` | `15, false` |
| 3 | Department | DmTextHelpMulti | `txtHDepartment` | `KeyField_PrpAcs, 4` |
| 4 | Requested By | DmTextHelpMulti | `txtHRequestedBy` | `KeyField_PrpAcs, 5` |
| 5 | Item Name | DmTextHelpMulti | `txtHItemName` | `KeyField_PrpAcs, 5` |
| 6 | Requisition No | DmTextHelpMulti | `txtHRequisitionNo` | `TxtHRequisitionNo_PrpAcs, 7` |
| 7 | Site | DmTextHelpMulti | `txtHSite` | `KeyField_PrpAcs, 5, true` |
| 8 | Status | DmTextHelpMulti | `txtHStatus` | `KeyField_PrpAcs, 4` |

**Grid / popup columns** (header, width, db field):
- `txtHRequisitionNo`: Requisition No. [Text, w90, requisitionNo]; Req. Type [Text, w80, category]; Req. Date [Text, w80, replace(convert(varchar, requisitionDate, 106), ' ','-')]
- `txtHDepartment`: Name [Text, w0, Name]
- `txtHRequestedBy`: Name [Text, w0, Name]
- `txtHItemName`: Name [Text, w0, Name]
- `txtHStatus`: Status [Text, w200]
- `txtHSite`: Site [Text, w200, name]

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

`txtHStatus.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC().getTxtHStatus(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExtended>>>() 
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHStatus).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHStatus.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHStatus).show();
```

`txtHStatus.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHStatus.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHStatus).show();
```

`txtHSite.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHSite(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSite).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHSite.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSite).show();
```

`txtHSite.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSite.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSite).show();
```
- Alert: "From Date should be less than or equal to To Date."

## Files
| file | lines |
|---|---|
| client/Requisition.java | 802 |
| client/RequisitionCard.java | 130 |
| client/RequisitionDetailPopup.java | 121 |
| client/RequisitionDetailPopup_Dsg.java | 224 |
| client/Requisition_Dsg.java | 344 |
| client/Setting.java | 398 |
| client/Setting_Dsg.java | 293 |
| server/Requisition_SrvImpl.java | 800 |
| shared/DefaultValue_Mdl.java | 42 |
| shared/GrdRequisitionDetail_Mdl.java | 152 |
| shared/GrdRequisition_Mdl.java | 150 |
| shared/Requisition_Mdl.java | 104 |
| shared/Requisition_Srv.java | 35 |
| shared/Requisition_SrvAsync.java | 35 |
| shared/SearchValue_Mdl.java | 141 |
| shared/TxtHRequisitionNo_Mdl.java | 32 |

## UI labels / columns (client)
`Action`, `Requisition No.`, `Date`, `Requisition By`, `Department`, `From Date`, `To Date`, `Requested By`, `Item Name`, `Requisition No`, `Site`, `Status`

## Models (shared DTOs)
### TxtHRequisitionNo_Mdl (extends `KeyField implements Serializable,IsSerializable`)
| type | field |
|---|---|
| String | reqType |
| String | reqDate |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | currentDate |
| String | currentSiteCode |
| String | currentSiteName |

### GrdRequisitionDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | v_SNo |
| String | itemQty |
| String | unitName |
| String | unitCode |
| String | reqDate |
| String | remark |
| String | action |
| String | check |
| String | reqTime |
| String | isStatus |
| String | properties |
| String | itemType |
| String | propertyList |
| String | ibCode |
| ItemProperty_Mdl | ipm |

### SearchValue_Mdl
| type | field |
|---|---|
| String | FromDate |
| String | ToDate |
| String | ItemName |
| String | ItemCode |
| String | DepartmentName |
| String | DepartmentCode |
| String | IndenterName |
| String | IndenterCode |
| String | requisitionDocIds |
| String | requisitionRecIds |
| String | siteCode |
| String | siteName |
| String | statusCode |
| String | statusName |

### Requisition_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | V_No |
| String | requisitionByName |
| String | requisitionByCode |
| String departmentCode, | departmentToCode |
| String departmentName, | departmentToName |
| String | date |
| String | vCat |

### GrdRequisition_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | isView |
| String | V_No |
| String | requisitionByName |
| String | requisitionByCode |
| String | departmentCode |
| String | departmentName |
| String | date |
| String | reqDate |
| String | departmentToCode |
| String | departmentToName |
| String | priorityColor |
| String | v_Type |
| String | v_TypeCat |
| String | pendingQty |
| String | statusCode |

## RPC interface
| method | params | returns |
|---|---|---|
| getCardDetail |  | DmResult<CardDetail_Mdl> |
| getFillRecords | String departmentCode, SearchValue_Mdl setting_Mdl, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdRequisition_Mdl>> |
| getRequisitionDetail | String docId | DmResult<Requisition_Mdl> |
| getTxtHDepartment | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| getTxtHItem | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHIndenter | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHRequisitionNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHRequisitionNo_Mdl>> |
| getTxtHStatus | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSite | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| validateSite | String strKey | DmResult<String> |

## Server `Requisition_SrvImpl.java` (800 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`

- SQL 1: `select sm.code,sm.name AS site from siteMast sm where code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getFillRecords`
`public DmResultPageing<List<GrdRequisition_Mdl>> getFillRecords(String departmentCode, SearchValue_Mdl setting_Mdl, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select rs.docId, rs.recId, rs.requestedByCode, req.name requestedBy, rs.departmentCode, depby.name departmentBy, depTo.name departmentToName,rs.departmentToCode, rs.v_Date , vt.short_Name, vt.category, rs.v_No, hl7.priorityIndex, rs.v_Type, vrd.pendingQty , hl7.colorName, vrd.reqDate AS reqDT , CASE WHEN vrd.totalPending = 0 THEN CASE WHEN vrd.totalCompleted > 0 THEN 2 ELSE 1 END ELSE 0 END AS statusCode from requisition rs left join ( SELECT rqd.DocId, SUM( ISNULL(rqd.reqQty, 0) - ISNULL(rqd.issueQty, 0)) AS pendingQty , MIN(rqd.reqDate) AS reqDate , SUM(IIF(rqd.isStatus=0,1,0)) AS totalPending , SUM(IIF(rqd.isStatus=2,1,0)) AS totalCompleted FROM requisitionDetail rqd WHERE 1=1 {…} group by rqd.DocId ) vrd on vrd.DocId = rs.docId left join subGroup req on req.subCode = rs.requestedByCode left join subGroup depby on depby.subCode = rs.departmentCode left join subGroup depTo on depTo.subCode = rs.departmentToCode LEFT JOIN voucher_type AS vt ON rs.v_Type = vt.v_Type left join hl7Mast as hl7 on hl7.code = rs.priorityTypeCode where ISNULL(rs.approvedBy,'') <> '' AND vrd.docId IS NOT NULL AND vt.category IN (:REQ,:SREQ,:PREQ) {…}`
- Params: `:REQ` ← `pvc.REQUISITION`; `:SREQ` ← `pvc.SERVICE_REQUISITION`; `:PREQ` ← `pvc.PRODUCTION_REQUISITION`; `:departmentCode` ← `DmShared.iNull(departmentCode).toString()`; `:fromv_Date` ← `DmServer.convertDate(setting_Mdl.getFromDate())`; `:tov_Date` ← `DmServer.convertDate(setting_Mdl.getToDate())`; `:site_Code_To` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getRequisitionDetail`
`public DmResult<Requisition_Mdl> getRequisitionDetail(String docId)`

- SQL 1: `SELECT rq.docId, rq.recId, rq.v_Date,sg.subCode,sg.name AS subgroup, sdp.subcode AS departmentCode,sdp.name departmentName,rq.preparedBy, rq.u_Name,rq.u_EntDt_LatestLine,rq.departmentToCode,sdt.name departmentToName,vt.category from requisition rq LEFT JOIN subGroup sg ON sg.subCode = rq.requestedByCode LEFT JOIN subgroup sdp ON sdp.subcode = rq.departmentCode LEFT JOIN subgroup sdt ON sdt.subcode = rq.departmentToCode LEFT JOIN voucher_type AS VT ON rq.v_Type = VT.v_Type where rq.docid=:docid`
- SQL 2: `SELECT rqd.docId, rqd.v_SNo, rqd.reqQty ,rqd.reqDate,rqd.remark ,rqd.reqTime ,rqd.isStatus, rqd.itemcode as code,im.name,um.unit AS sku,um.code skuCode , im.propertyList,rqd.ibCode, it.manualCode as itemType from requisitiondetail rqd LEFT JOIN itemMast im ON im.code = rqd.itemCode LEFT JOIN itemType it on it.code = im.itemType LEFT JOIN unitMast um ON um.code = im.sku where rqd.docid=:docid`
- Params: `:docid` ← `docId`; `:docid` ← `docId`
- Other calls: `ItemBatch_Server.getIPM`, `ItemPropertyPopup_Vld.getItemApplicableProperties`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT dm.subCode, sg.name FROM departmentMast dm LEFT JOIN subGroup sg ON sg.subCode = dm.subCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature Left join subGroupSiteList as sgsl on sg.subCode=sgsl.subCode WHERE sgn.manualCode IN ('CM','DP') and sgsl.site_Code = :siteCode and sg.isActive = 1 {…}`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getCardDetail`
`public DmResult<CardDetail_Mdl> getCardDetail()`

- SQL 1: `SELECT COUNT(req.docId) AS cnt , SUM(CASE WHEN DATEDIFF(day, req.v_Date, CAST(:currentDate AS date)) <= :lowPriority THEN :one ELSE :zero END) AS low , SUM(CASE WHEN DATEDIFF(day, req.v_Date, CAST(:currentDate AS date)) > :lowPriority AND DATEDIFF(day, req.v_Date, CAST(:currentDate AS date)) <= :mediumPriority THEN :one ELSE :zero END) AS medium , SUM(CASE WHEN DATEDIFF(day, req.v_Date, CAST(:currentDate AS date)) >= :highPriority THEN :one ELSE :zero END) AS high FROM requisition AS req LEFT JOIN voucher_type AS vt on req.v_Type = vt.v_Type LEFT JOIN( SELECT reqd.docId FROM requisitionDetail as reqd WHERE reqd.isStatus = :zero GROUP BY reqd.docId ) AS reqd on reqd.docId = req.docId WHERE reqd.docId IS NOT NULL AND isNull(req.approvedBy,:blank) <> :blank AND vt.category IN (:REQ,:SREQ,:PREQ) AND req.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:currentDate` ← `DmServer.getServerTimestamp()`; `:blank` ← `""`; `:REQ` ← `pvc.REQUISITION`; `:SREQ` ← `pvc.SERVICE_REQUISITION`; `:PREQ` ← `pvc.PRODUCTION_REQUISITION`; `:lowPriority` ← `STEnum.PriorityDays.LOW_PRIORITYDAYS.getDays()`; `:mediumPriority` ← `STEnum.PriorityDays.MEDIUM_PRIORITYDAYS.getDays()`; `:highPriority` ← `STEnum.PriorityDays.HIGH_PRIORITYDAYS.getDays()`; `:zero` ← `0`; `:one` ← `1`
- Other calls: `HIGH_PRIORITYDAYS.getDays`, `LOW_PRIORITYDAYS.getDays`, `MEDIUM_PRIORITYDAYS.getDays`, `STServer.getSsnDepartmentCodeList`

### `getTxtHItem`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.name, im.code FROM itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code WHERE (im.filterItemType = :filterItemType OR im.isCommon = :one ) AND sl.site_Code = :site_Code`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHIndenter`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHIndenter(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT MAX(sg.name) AS name ,MAX(mid.requestedByCode) AS requestedByCode FROM requisition mid LEFT JOIN subGroup AS sg ON sg.subCode=mid.requestedByCode GROUP BY mid.requestedByCode`

### `getTxtHRequisitionNo`
`public DmResultPageing<List<TxtHRequisitionNo_Mdl>> getTxtHRequisitionNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT rq.docId, rq.recId as requisitionNo, rq.v_No, rq.v_Date as requisitionDate, rq.v_Type,vt.category,vt.short_Name , rq.site_Code FROM requisition AS rq LEFT JOIN voucher_Type AS vt ON rq.v_Type = vt.v_Type WHERE rq.site_Code_To IN ( {…} :site_Code_To {…} ) AND vt.category IN (:REQ,:SREQ,:PREQ)`
- Params: `:REQ` ← `pvc.REQUISITION`; `:SREQ` ← `pvc.SERVICE_REQUISITION`; `:PREQ` ← `pvc.PRODUCTION_REQUISITION`

### `getTxtHStatus`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHStatus(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `validateSite`
`public DmResult<String> validateSite(String strKey)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.self.client`, `paperbiz.dashBoard.inventoryDashBoard.shared`, `paperbiz.dashBoard.requisition.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.dashboardCardStrip.client`, `paperbiz.selfToolkit.dashboardCardStrip.shared`, `paperbiz.selfToolkit.itemBatch.server`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
