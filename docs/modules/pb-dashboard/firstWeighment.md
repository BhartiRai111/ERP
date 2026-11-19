# pb-dashboard / firstWeighment

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/dashBoard/src/firstWeighment/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHGateIn, getTxtHWeighment, getTxtHSupplierPT
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `FirstWeighment`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmGridPaging | `grdFirstWeighmentDetail` | `GridFirstWeighment_PrpAcs, gridRows, false` |

Other widgets (not in tab list): `txtSearchValue` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `grdFirstWeighmentDetail`: Weighment No.  [Text, w120, recId]; Date [Text, w80, replace(convert(varchar, v_Date, 106), ' ','-')]; Time [Text, w50, v_Time]; W.S. No [Text, w140, weighSlipNo]; Party [Text, w300, partyName]; Gate Entry No [Text, w120, gateRecId]; Purpose [Text, w80, loadUnload]; Vehicle Type [Text, w90, vehicleType]; Vehicle No. [Text, w120, vehicleNo]

**Lookup / suggestion / find sources (server RPC called):**
- `grdFirstWeighmentDetail` → `getFillRecords`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`settingPopup.addGateCallBack` → `onOk()`
```java
searchValue_Mdl = sm;
grdFirstWeighmentDetail.getDmGrid().commitChanges();
grdFirstWeighmentDetail.reQuery();
```

`grdFirstWeighmentDetail.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnToEndOfFile).getFillRecords(searchValue_Mdl, helper, blnToEndOfFile, new AsyncCallback<DmResultPag
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdFirstWeighment_Mdl>> result) {
if (result.isStatus()) {
grdFirstWeighmentDetail.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`grdFirstWeighmentDetail.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
grdFirstWeighmentDetail.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`grdFirstWeighmentDetail.addCallBack` → `getParam()`
```java
return searchValue_Mdl;
```
- Confirm: "Are you sure? You want to close."
- Alert: "Please select the first Weighment."

### Screen `Setting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | From Date | DmTextBox | `txtFromDate` | `15, false` |
| 2 | To Date | DmTextBox | `txtToDate` | `15, false` |
| 3 | Gate Entry No. | DmTextHelpMulti | `txtHGateNo` | `TxtHGateIn_PrpAcs, 7` |
| 4 | Party | DmTextHelpMulti | `txtHSupplier` | `TxtHSupplierName_PrpAcs, 7` |
| 5 | Weighment No. | DmTextHelpMulti | `txtHWeighmentNo` | `TxtHWeighment_PrpAcs, 7` |

**Grid / popup columns** (header, width, db field):
- `txtHGateNo`: Gate Entry No. [Text, w120, gateNo]; Gate Type [Text, w90, category]; Date [Text, w90, gateDate]
- `txtHWeighmentNo`: Weighment No. [Text, w120, weighmentNo]; Weighment Type [Text, w90, category]; Date [Text, w90, weighmentDate]
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

`txtHWeighmentNo.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHWeighment(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtH
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHWeighmentNo).show();
public void onSuccess(DmResultPageing<List<TxtHWeighment_Mdl>> result) {
if (result.isStatus()) {
txtHWeighmentNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHWeighmentNo).show();
```

`txtHWeighmentNo.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHWeighmentNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHWeighmentNo).show();
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
| client/FirstWeighment.java | 442 |
| client/FirstWeighmentCard.java | 130 |
| client/FirstWeighment_Dsg.java | 282 |
| client/Setting.java | 241 |
| client/Setting_Dsg.java | 234 |
| server/FirstWeighment_SrvImpl.java | 461 |
| shared/DefaultValue_Mdl.java | 22 |
| shared/FirstWeighment_Srv.java | 26 |
| shared/FirstWeighment_SrvAsync.java | 26 |
| shared/GrdFirstWeighment_Mdl.java | 104 |
| shared/SearchValue_Mdl.java | 86 |
| shared/TxtHGateIn_Mdl.java | 32 |
| shared/TxtHSupplierPT_Mdl.java | 31 |
| shared/TxtHWeighment_Mdl.java | 32 |

## UI labels / columns (client)
`Action`, `From Date`, `To Date`, `Gate Entry No.`, `Party`, `Weighment No.`

## Models (shared DTOs)
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
| String | gateRecId |
| String | weighmentDocId |
| String | weighmentRecId |
| String | SupplierCode |
| String | SupplierName |

### TxtHWeighment_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | weighmentType |
| String | weighmentDate |

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

### GrdFirstWeighment_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | v_Date |
| String | v_Time |
| String | partyname |
| String | partyCode |
| String | gateRecId |
| String | gateDocId |
| String | vehicleType |
| String | vehicleNo |
| String | purpose |
| String | wsNo |

## RPC interface
| method | params | returns |
|---|---|---|
| getFillRecords | SearchValue_Mdl searchValueMdl, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdFirstWeighment_Mdl>> |
| getCardDetail |  | DmResult<CardDetail_Mdl> |
| getTxtHGateIn | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHGateIn_Mdl>> |
| getTxtHWeighment | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHWeighment_Mdl>> |
| getTxtHSupplierPT | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHSupplierPT_Mdl>> |
| validateUnloadingEntry | String docId | DmResult<String> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |

## Server `FirstWeighment_SrvImpl.java` (461 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnVoucherPrefix`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.xNull`

### `getCardDetail`
`public DmResult<CardDetail_Mdl> getCardDetail()`

- SQL 1: `select count(wgmt.docId) as cnt , sum(case when DATEDIFF(day, wgmt.v_Date, CAST(:currentDate AS date)) <= {…} then 1 else 0 end) as low, sum(case when DATEDIFF(day, wgmt.v_Date, CAST(:currentDate AS date)) > {…} and DATEDIFF(day, wgmt.v_Date, CAST(:currentDate AS date)) <= {…} then 1 else 0 end) as medium, sum(case when DATEDIFF(day, wgmt.v_Date, CAST(:currentDate AS date)) >= {…} then 1 else 0 end) as high from weighment as wgmt left join voucher_type as vt on wgmt.v_Type = vt.v_Type left join unloading as unlod on unlod.weighmentDocId = wgmt.docId WHERE isNull(unlod.weighmentDocId , '') = '' and vt.category=(' {…} ') and wgmt.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:currentDate` ← `DmServer.getServerTimestamp()`
- Other calls: `HIGH_PRIORITYDAYS.getDays`, `LOW_PRIORITYDAYS.getDays`, `MEDIUM_PRIORITYDAYS.getDays`

### `getFillRecords`
`public DmResultPageing<List<GrdFirstWeighment_Mdl>> getFillRecords(SearchValue_Mdl searchValue_Mdl, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select wgmnt.docId , wgmnt.v_Type , wgmnt.recId , wgmnt.v_Date , wgmnt.v_Time , wgmnt.weighSlipNo , wgmnt.partyCode , sg.name as PartyName , sg.cityCode as partyCityCode , c.cityName as partyCityName , gateIO.recId as gateRecId , wgmnt.gateDocId , gateIO.vehicleType , gateIO.vehicleNo , CASE WHEN wgmnt.loadUnload = {…} THEN ' {…} ' WHEN wgmnt.loadUnload = {…} THEN ' {…} ' END AS loadUnload FROM weighment as wgmnt left join voucher_Type as vt on wgmnt.v_Type = vt.v_Type left join subGroup as sg on wgmnt.partyCode = sg.subCode left join city as c on c.cityCode=sg.cityCode left join unloading as unlod on unlod.weighmentDocId = wgmnt.docId left join loading as lod on lod.weighmentDocId = wgmnt.docId left join gateInOut as gateIO on wgmnt.gateDocId = gateIO.docId where (isNull(unlod.weighmentDocId , '') = '' OR isNull(lod.weighmentDocId , '') = '') and wgmnt.site_Code = :site_Code And wgmnt.v_Prefix =:v_Prefix and vt.category=(' {…} ') {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Prefix` ← `DmServer.getSsnVoucherPrefix(this)`; `:fromv_Date` ← `DmServer.convertDate(searchValue_Mdl.getFromDate())`; `:tov_Date` ← `DmServer.convertDate(searchValue_Mdl.getToDate())`
- Other calls: `LOADING.getId`, `LOADING.getValue`, `UNLOADING.getId`, `UNLOADING.getValue`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`


### `getTxtHGateIn`
`public DmResultPageing<List<TxtHGateIn_Mdl>> getTxtHGateIn(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT gio.docId, gio.recId as gateNo, gio.v_Date as gateDate, vt.category, gio.v_No FROM gateInOut AS gio left join voucher_Type as vt on vt.v_Type = gio.v_Type where gio.site_Code= ' {…} ' and vt.Category = ' {…} '`

### `getTxtHWeighment`
`public DmResultPageing<List<TxtHWeighment_Mdl>> getTxtHWeighment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT wmnt.docId, wmnt.recId as weighmentNo, wmnt.v_Date as weighmentDate, vt.category, wmnt.v_No FROM weighment AS wmnt left join voucher_Type as vt on vt.v_Type = wmnt.v_Type where wmnt.site_Code= ' {…} ' and vt.Category = ' {…} '`

### `getTxtHSupplierPT`
`public DmResultPageing<List<TxtHSupplierPT_Mdl>> getTxtHSupplierPT(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_type = acf.v_Type WHERE vt.category IN (' {…} ')`
- SQL 2: `SELECT sg.subCode AS code , sg.name , sg.manualCode , c.cityName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( {…} ) AND sg.isActive = 1 AND sgsl.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `validateUnloadingEntry`
`public DmResult<String> validateUnloadingEntry(String docId)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmChart.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.self.client`, `paperbiz.dashBoard.firstWeighment.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.dashboardCardStrip.client`, `paperbiz.selfToolkit.dashboardCardStrip.shared`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
