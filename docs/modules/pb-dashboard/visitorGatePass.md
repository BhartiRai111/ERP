# pb-dashboard / visitorGatePass

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/dashBoard/src/visitorGatePass/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHGateNo, getTxtHOrganization
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `Setting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | From Date | DmTextBox | `txtFromDate` | `15, false` |
| 2 | To Date | DmTextBox | `txtToDate` | `15, false` |
| 3 | Gate No. | DmTextHelpMulti | `txtHGateNo` | `KeyField_PrpAcs, 5, false` |
| 4 | Organization | DmTextHelpMulti | `txtHOrganization` | `KeyField_PrpAcs, 5, false` |

**Grid / popup columns** (header, width, db field):
- `txtHGateNo`: Gate No. [Text, w200, gateNo]
- `txtHOrganization`: Organization [Text, w200, organization]

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtFromDate.addValueChangeHandler` → `onValueChange()`
```java
txtFromDate.setText(DmClient.returnDate(txtFromDate.getText()));
```

`txtToDate.addValueChangeHandler` → `onValueChange()`
```java
txtToDate.setText(DmClient.returnDate(txtToDate.getText()));
```

`txtHGateNo.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHGateNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFiel
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGateNo).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHGateNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGateNo).show();
```

`txtHGateNo.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHGateNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGateNo).show();
```

`txtHOrganization.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHOrganization(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<K
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHOrganization).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHOrganization.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHOrganization).show();
```

`txtHOrganization.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHOrganization.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHOrganization).show();
```
- Alert: "From Date should be less than or equal to To Date."

### Screen `VisitorGatePass`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmGridPaging | `grdVisitorDetail` | `GridVisitorgatePass_PrpAcs, gridRows, false` |

Other widgets (not in tab list): `txtSearchValue` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `grdVisitorDetail`: Gate Pass No.  [Text, w100, recId]; Date In [Text, w80, replace(convert(varchar, v_Date, 106), ' ','-')]; Time In [Text, w75, v_Time]; Gate No [Text, w100, gateNo]; Visitor Name [Text, w100, name]; Organization [Text, w150, organization]; Mobile [Text, w90, mobile]; Address [Text, w150, address]; City Name [Text, w100, cityName]; Vehicle Type [Text, w100, vehicleType]; Vehicle No [Text, w100, vehicleNo]; Person To Meet [Text, w100, personToMeet]; Purpose [Text, w100, purpose]

**Lookup / suggestion / find sources (server RPC called):**
- `grdVisitorDetail` → `getFillRecords`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`settingPopup.addGateCallBack` → `onOk()`
```java
searchValue_Mdl = sm;
grdVisitorDetail.getDmGrid().commitChanges();
grdVisitorDetail.reQuery();
```

`grdVisitorDetail.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnToEndOfFile).getFillRecords(searchValue_Mdl, helper, blnToEndOfFile, new AsyncCallback<DmResultPag
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdVisitorGatePass_Mdl>> result) {
if (result.isStatus()) {
grdVisitorDetail.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`grdVisitorDetail.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
grdVisitorDetail.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`grdVisitorDetail.addCallBack` → `getParam()`
```java
return searchValue_Mdl;
```
- Confirm: "Are you sure? You want to close."
- Alert: "Please select the visitor"
- Alert: "Visitor Already Out"

## Files
| file | lines |
|---|---|
| client/Setting.java | 203 |
| client/Setting_Dsg.java | 170 |
| client/VisitorGatePass.java | 426 |
| client/VisitorGatePassCard.java | 126 |
| client/VisitorGatePass_Dsg.java | 309 |
| server/VisitorGatePass_SrvImpl.java | 419 |
| shared/DefaultValue_Mdl.java | 24 |
| shared/GrdVisitorGatePass_Mdl.java | 232 |
| shared/SearchValue_Mdl.java | 60 |
| shared/VisitorGatePass_Srv.java | 27 |
| shared/VisitorGatePass_SrvAsync.java | 28 |

## UI labels / columns (client)
`Action`, `From Date`, `To Date`, `Gate No.`, `Organization`

## Models (shared DTOs)
### GrdVisitorGatePass_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | docId |
| String | recId |
| String | voucherTypeCode |
| String | voucherTypeName |
| String | entryNo |
| String | inDate |
| String | inTime |
| String | visitorName |
| String | address |
| String | vehicleType |
| String | vehicleNo |
| String | personToMeet |
| String | gateOutDate |
| String | gateOutTime |
| String | GateOutBy |
| String | purpose |
| String | city_Code |
| String | mobile |
| String | v_Type |
| ModifyReason_Mdl | modifyReasonMdl |
| String | organizationName |
| String | gateNo |
| DmSnapshot_Mdl | snapshot_Mdl |
| String | city_Name |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | currentDate |

### SearchValue_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | fromDate |
| String | toDate |
| String | gateDocId |
| String | organizationName |
| String | gateNo |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHGateNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHOrganization | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| getFillRecords | SearchValue_Mdl searchValueMdl, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdVisitorGatePass_Mdl>> |
| checkVisitorOut | String visitorDocId | DmResult<Boolean> |
| getCardDetail |  | DmResult<CardDetail_Mdl> |

## Server `VisitorGatePass_SrvImpl.java` (419 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.xNull`

### `getTxtHGateNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGateNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select DISTINCT vgp.gateNo from visitorGatePass vgp Left Join voucher_Type vt on vgp.v_Type = vt.v_Type where vgp.site_Code = ' {…} ' and vgp.gateNo IS NOT NULL and vt.Category = ' {…} '`

### `getTxtHOrganization`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHOrganization(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select DISTINCT vgp.organization from visitorGatePass vgp Left Join voucher_Type vt on vgp.v_Type = vt.v_Type where vgp.site_Code = ' {…} ' and vgp.organization IS NOT NULL and vt.Category = ' {…} '`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`


### `getFillRecords`
`public DmResultPageing<List<GrdVisitorGatePass_Mdl>> getFillRecords(SearchValue_Mdl searchValueMdl, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vgp.docId ,vgp.recId ,vgp.v_Type ,vgp.v_No ,vgp.v_Date ,vgp.v_Time ,vgp.name ,vgp.u_Name ,vgp.cityCode ,vgp.address ,vgp.mobile ,vgp.vehicleType ,vgp.vehicleNo ,vgp.personToMeet ,vgp.purpose ,vgp.gateOutDate ,vgp.gateOutTime ,vgp.preparedBy ,vgp.gateOutBy , ct.cityName , vgp.organization , vgp.gateNo , vgp.preparedDt , vgp.u_EntDt , vt.description AS voucherTypeName FROM visitorGatePass vgp LEFT JOIN voucher_Type vt ON vt.v_Type = vgp.v_Type LEFT JOIN City ct ON ct .cityCode = vgp.cityCode WHERE vgp.gateOutBy IS NULL and vgp.gateOutDate IS NULL AND vt.category=(' {…} ') AND vgp.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:fromv_Date` ← `DmServer.convertDate(searchValueMdl.getFromDate())`; `:tov_Date` ← `DmServer.convertDate(searchValueMdl.getToDate())`

### `checkVisitorOut`
`public DmResult<Boolean> checkVisitorOut(String gateDocId)`


### `checkVisitorOut`
`private boolean checkVisitorOut(String visitorDocId, Connection cnTemp) throws Exception`

- SQL 1: `select COUNT(*) as cnt from visitorGatePass vgp where vgp.gateOutBy IS NOT NULL and vgp.gateOutBy IS NOT NULL and vgp.docId = :docId`
- Params: `:docId` ← `DmShared.iNull(visitorDocId).toString()`

### `getCardDetail`
`public DmResult<CardDetail_Mdl> getCardDetail()`

- SQL 1: `select count(vgp.docId) as cnt , sum(case when DATEDIFF(day, vgp.v_Date, CAST(:currentDate AS date)) <= {…} then 1 else 0 end) as low, sum(case when DATEDIFF(day, vgp.v_Date, CAST(:currentDate AS date)) > {…} and DATEDIFF(day, vgp.v_Date, CAST(:currentDate AS date)) <= {…} then 1 else 0 end) as medium, sum(case when DATEDIFF(day, vgp.v_Date, CAST(:currentDate AS date)) >= {…} then 1 else 0 end) as high from visitorGatePass as vgp left join voucher_type as vt on vgp.v_Type = vt.v_Type WHERE vgp.gateOutBy IS NULL and vgp.gateOutDate IS NULL and vt.category=(' {…} ') and vgp.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:currentDate` ← `DmServer.getServerTimestamp()`
- Other calls: `HIGH_PRIORITYDAYS.getDays`, `LOW_PRIORITYDAYS.getDays`, `MEDIUM_PRIORITYDAYS.getDays`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmSnapshot.shared`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.self.client`, `paperbiz.dashBoard.visitorGatePass.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.dashboardCardStrip.client`, `paperbiz.selfToolkit.dashboardCardStrip.shared`, `paperbiz.selfToolkit.modifyReason.shared`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
