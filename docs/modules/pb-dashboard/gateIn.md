# pb-dashboard / gateIn

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/dashBoard/src/gateIn/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHGateIn, getTxtHSupplierPT
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `GateIn`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmGridPaging | `grdGateInDetail` | `GridGateIn_PrpAcs, gridRows, false` |

Other widgets (not in tab list): `txtSearchValue` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `grdGateInDetail`: Entry No.  [Text, w136, recid]; Date [Text, w80, replace(convert(varchar, v_Date, 106), ' ','-')]; Time [Text, w80, v_Time]; Gate No. [Text, w80, inGateNoCode]; Party Name [Text, w350, PartyName]; Purpose [Text, w125, purpose]; Vehicle Type [Text, w100, vehicleTypeName]; Vehicle No. [Text, w90, vehicleNo];  Driver Name [Text, w150, driver]; Unit [Text, w50, unitName]; Doc.Gross Wt. [Text, w100, docGrossWt];  Doc. Tare Wt. [Text, w100, docTareWt];  Net Wt. [Text, w100, docNetWt]

**Lookup / suggestion / find sources (server RPC called):**
- `grdGateInDetail` → `getFillRecords`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`settingPopup.addGateCallBack` → `onOk()`
```java
searchValue_Mdl = sm;
grdGateInDetail.getDmGrid().commitChanges();
grdGateInDetail.reQuery();
```

`grdGateInDetail.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnToEndOfFile).getFillRecords(searchValue_Mdl, helper, blnToEndOfFile, new AsyncCallback<DmResultPag
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdGateIn_Mdl>> result) {
if (result.isStatus()) {
grdGateInDetail.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`grdGateInDetail.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
grdGateInDetail.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`grdGateInDetail.addCallBack` → `getParam()`
```java
return searchValue_Mdl;
```
- Confirm: "Are you sure? You want to close."
- Alert: "Advice not allowed for loading purpose."
- Alert: "Please select the gate entry."
- Alert: "Please select the gate entry."
- Alert: "Please select the gate entry."
- Alert: "Advice Required for selected Gate Entry No."
- Alert: "First weighment already exist."
- Alert: "Advice already exist."

### Screen `Setting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | From Date | DmTextBox | `txtFromDate` | `15, false` |
| 2 | To Date | DmTextBox | `txtToDate` | `15, false` |
| 3 | Gate Entry No. | DmTextHelpMulti | `txtHGateNo` | `TxtHGateIn_PrpAcs, 7` |
| 4 | Party | DmTextHelpMulti | `txtHSupplier` | `TxtHSupplierName_PrpAcs, 7` |

**Grid / popup columns** (header, width, db field):
- `txtHGateNo`: Gate Entry No. [Text, w120, gateNo]; Gate Type [Text, w90, category]; Date [Text, w90, gateDate]
- `txtHSupplier`: Name [Text, w250, name]; City Name [Text, w100, cityName]

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
srvAsync.invokeRPC(blnCallBackWait).getTxtHGateIn(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHGat
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGateNo).show();
public void onSuccess(DmResultPageing<List<TxtHGateIn_Mdl>> result) {
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

`txtHSupplier.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHSupplierPT(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Txt
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
| client/GateIn.java | 541 |
| client/GateInCard.java | 132 |
| client/GateIn_Dsg.java | 337 |
| client/Setting.java | 208 |
| client/Setting_Dsg.java | 194 |
| server/GateIn_SrvImpl.java | 523 |
| shared/DefaultValue_Mdl.java | 22 |
| shared/GateIn_Srv.java | 28 |
| shared/GateIn_SrvAsync.java | 28 |
| shared/GrdGateIn_Mdl.java | 157 |
| shared/SearchValue_Mdl.java | 68 |
| shared/TxtHGateIn_Mdl.java | 32 |
| shared/TxtHSupplierPT_Mdl.java | 31 |

## UI labels / columns (client)
`Action`, `From Date`, `To Date`, `Gate Entry No.`, `Party`

## Models (shared DTOs)
### DefaultValue_Mdl
| type | field |
|---|---|
| String | currentDate |

### GrdGateIn_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | gateNo |
| String | party |
| String | purpose |
| String | vehicleTypeCode |
| String | vehicleTypeName |
| String | vehicleNo |
| String | driver |
| String | unit |
| String | docGrossWt |
| String | docTareWT |
| String | docNetWt |
| String | v_Date |
| String | v_Time |
| String | inGateNoCode |
| String | inGateNoName |

### SearchValue_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | fromDate |
| String | toDate |
| String | gateDocId |
| String | gateRecId |
| String | SupplierCode |
| String | SupplierName |

### TxtHSupplierPT_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityName |

### TxtHGateIn_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | gateType |
| String | gateDate |

## RPC interface
| method | params | returns |
|---|---|---|
| getFillRecords | SearchValue_Mdl searchValueMdl, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdGateIn_Mdl>> |
| getCardDetail |  | DmResult<CardDetail_Mdl> |
| checkWeighmentStatus | String gateDocId | DmResult<String> |
| checkAdviceExist | String gateDocId | DmResult<Boolean> |
| validateGateEntry | String docId | DmResult<String> |
| getTxtHGateIn | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHGateIn_Mdl>> |
| getTxtHSupplierPT | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHSupplierPT_Mdl>> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |

## Server `GateIn_SrvImpl.java` (523 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.xNull`

### `getFillRecords`
`public DmResultPageing<List<GrdGateIn_Mdl>> getFillRecords(SearchValue_Mdl searchValue_Mdl, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select gio.docId, gio.recId,sbg.name as partyName,c.cityName, CASE WHEN gio.loadUnload = {…} THEN ' {…} ' WHEN gio.loadUnload = {…} THEN ' {…} ' END AS purpose, gio.vehicleTypeCode, vtm.name as vehicleTypeName, gio.vehicleNo, gio.inGateNoCode, gnm.name as inGateNoname, gio.driver, um.unit as unitName, gio.docGrossWt, gio.docTareWt, gio.docNetWt, vt.short_Name, gio.v_No, gio.v_Date, gio.v_Time from gateInOut gio left join subGroup sbg ON sbg.subCode=gio.partyCode left join city as c on c.cityCode=sbg.cityCode left join unitMast um ON um.code=gio.unitCode Left join vehicleTypeMast vtm ON vtm.Code = gio.vehicleTypeCode left join voucher_Type vt ON vt.v_Type = gio.v_Type Left join gatenomast gnm ON gnm.code = gio.inGateNoCode WHERE isNull(gio.gateOutBy , '') = '' AND vt.category=(' {…} ') AND gio.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:fromv_Date` ← `DmServer.convertDate(searchValue_Mdl.getFromDate())`; `:tov_Date` ← `DmServer.convertDate(searchValue_Mdl.getToDate())`
- Other calls: `LOADING.getId`, `LOADING.getValue`, `UNLOADING.getId`, `UNLOADING.getValue`

### `getCardDetail`
`public DmResult<CardDetail_Mdl> getCardDetail()`

- SQL 1: `select count(gio.docId) as cnt , sum(case when DATEDIFF(day, gio.v_Date, CAST(:currentDate AS date)) <= {…} then 1 else 0 end) as low, sum(case when DATEDIFF(day, gio.v_Date, CAST(:currentDate AS date)) > {…} and DATEDIFF(day, gio.v_Date, CAST(:currentDate AS date)) <= {…} then 1 else 0 end) as medium, sum(case when DATEDIFF(day, gio.v_Date, CAST(:currentDate AS date)) >= {…} then 1 else 0 end) as high from gateInOut as gio left join voucher_type as vt on gio.v_Type = vt.v_Type WHERE isNull(gio.gateOutBy , '') = '' and vt.category=(' {…} ') and gio.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:currentDate` ← `DmServer.getServerTimestamp()`
- Other calls: `HIGH_PRIORITYDAYS.getDays`, `LOW_PRIORITYDAYS.getDays`, `MEDIUM_PRIORITYDAYS.getDays`

### `checkWeighmentStatus`
`public DmResult<String> checkWeighmentStatus(String gateDocId)`

- SQL 1: `select count(wgmt.gateDocId) as cnt from weighment as wgmt where wgmt.gateDocId= :docId`
- Params: `:docId` ← `DmShared.iNull(gateDocId).toString()`

### `checkAdviceExist`
`public DmResult<Boolean> checkAdviceExist(String gateDocId )`


### `checkAdviceExist`
`private boolean checkAdviceExist(String gateDocId, Connection cnTemp) throws Exception`

- SQL 1: `select count(*) as cnt ,max(gio.loadUnload) as loadUnload from gateAdviceDetail gad left join gateInOut gio ON gio.docId = gad.docId where gad.docId= :docId`
- Params: `:docId` ← `DmShared.iNull(gateDocId).toString()`
- Other calls: `UNLOADING.getId`

### `validateGateEntry`
`public DmResult<String> validateGateEntry(String docId)`


### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`


### `getTxtHGateIn`
`public DmResultPageing<List<TxtHGateIn_Mdl>> getTxtHGateIn(DmHelper helper, boolean blnToEndOfFile )`

- SQL 1: `SELECT gio.docId, gio.recId as gateNo, gio.v_Date as gateDate, vt.category, gio.v_No FROM gateInOut AS gio left join voucher_Type as vt on vt.v_Type = gio.v_Type where gio.site_Code= ' {…} ' and vt.Category = ' {…} '`

### `getTxtHSupplierPT`
`public DmResultPageing<List<TxtHSupplierPT_Mdl>> getTxtHSupplierPT(DmHelper helper, boolean blnToEndOfFile )`

- SQL 1: `SELECT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_type = acf.v_Type WHERE vt.category IN (' {…} ')`
- SQL 2: `SELECT sg.subCode AS code , sg.name , sg.manualCode , c.cityName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( {…} ) AND sg.isActive = 1 AND sgsl.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.self.client`, `paperbiz.dashBoard.gateIn.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.dashboardCardStrip.client`, `paperbiz.selfToolkit.dashboardCardStrip.shared`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
