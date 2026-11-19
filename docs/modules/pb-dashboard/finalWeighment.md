# pb-dashboard / finalWeighment

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/dashBoard/src/finalWeighment/`). Hand-written notes are in the "Notes" section.

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
### Screen `FinalWeighment`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmGridPaging | `grdFinalWeighment` | `GridFinalWeighment_PrpAcs, gridRows, false` |

Other widgets (not in tab list): `txtSearchValue` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `grdFinalWeighment`: Entry No.  [Text, w136, recid]; In Date [Text, w80, replace(convert(varchar, v_Date, 106), ' ','-')]; In Time [Text, w60, v_Time]; In Gate No. [Text, w100, inGateNoCode]; Out Date [Text, w70, replace(convert(varchar, gateOutDate, 106), ' ','-')]; Out Time [Text, w60, gateOutTime]; Out Gate No. [Text, w100, outGateNoname]; Party Name [Text, w200, PartyName];  [Text, w25]; Purpose [Text, w70, purpose]; Vehicle Type [Text, w100, vehicleTypeName]; Vehicle No. [Text, w90, vehicleNo];  Driver Name [Text, w115, driver]; Unit [Text, w40, unitName]; D.Gross Wt. [Text, w85, docGrossWt]; D.Tare Wt. [Text, w80, docTareWt]; D.Net Wt. [Text, w80, docNetWt]

**Lookup / suggestion / find sources (server RPC called):**
- `grdFinalWeighment` → `getFillRecords`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`settingPopup.addGateCallBack` → `onOk()`
```java
searchValue_Mdl = sm;
grdFinalWeighment.getDmGrid().commitChanges();
grdFinalWeighment.reQuery();
```

`grdFinalWeighment.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnToEndOfFile).getFillRecords(searchValue_Mdl, helper, blnToEndOfFile, new AsyncCallback<DmResultPag
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdFinalWeighment_Mdl>> result) {
if (result.isStatus()) {
grdFinalWeighment.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`grdFinalWeighment.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
grdFinalWeighment.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`grdFinalWeighment.addCallBack` → `getParam()`
```java
return searchValue_Mdl;
```
- Confirm: "Are you sure? You want to close."
- Alert: "Please select the gate entry."
- Alert: "Please select the Party"
- Alert: "Purchase Bill Direct or GRN is Already exist"
- Alert: "Please select the Party"
- Alert: "Please select the gate entry."
- Alert: "GRN not allowed for selected record."
- Alert: "Please select the Party"
- Alert: "Purchase Bill Direct or GRN is Already exist"
- Alert: "Please select the Party"
- Alert: "Please select the gate entry."
- Alert: "Purchase Bill Direct not allowed for selected record."

### Screen `PartyPopUp`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `grdSelectParty` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `grdSelectParty`: Party Name [Text, w500, partyName]; City [Text, w200, city]
- Alert: "Purchase Bill Direct or Grn is Already exist"
- Alert: "Please select the Party"
- Alert: "Purchase Bill Direct or Grn is Already exist"
- Alert: "Please select the gate entry."

### Screen `Setting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | From Date | DmTextBox | `txtFromDate` | `15, false` |
| 2 | To Date | DmTextBox | `txtToDate` | `15, false` |
| 3 | Gate Entry No. | DmTextHelpMulti | `txtHGateNo` | `TxtHGateOut_PrpAcs, 7` |
| 4 | Party | DmTextHelpMulti | `txtHSupplier` | `TxtHSupplierName_PrpAcs, 7` |

**Grid / popup columns** (header, width, db field):
- `txtHGateNo`: Gate Entry No. [Text, w120, gateNo]; Gate Type [Text, w90, category];  Out Date [Text, w90, gateDate]
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
srvAsync.invokeRPC(blnCallBackWait).getTxtHGateIn(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHFin
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGateNo).show();
public void onSuccess(DmResultPageing<List<TxtHFinalWeighment_Mdl>> result) {
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
| client/FinalWeighment.java | 630 |
| client/FinalWeighmentCard.java | 133 |
| client/FinalWeighment_Dsg.java | 387 |
| client/PartyPopUp.java | 177 |
| client/PartyPopUp_Dsg.java | 194 |
| client/Setting.java | 208 |
| client/Setting_Dsg.java | 195 |
| server/FinalWeighment_SrvImpl.java | 526 |
| shared/DefaultValue_Mdl.java | 22 |
| shared/FinalWeighment_Srv.java | 27 |
| shared/FinalWeighment_SrvAsync.java | 27 |
| shared/GrdFinalWeighment_Mdl.java | 240 |
| shared/Party_Mdl.java | 51 |
| shared/SearchValue_Mdl.java | 68 |
| shared/TxtHFinalWeighment_Mdl.java | 32 |
| shared/TxtHSupplierPT_Mdl.java | 31 |

## UI labels / columns (client)
`Action`, `From Date`, `To Date`, `Gate Entry No.`, `Party`

## Models (shared DTOs)
### Party_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | city |
| String | gateDocId |
| String | partyType |
| String | isStatus |

### GrdFinalWeighment_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | gateNo |
| String | partyCode |
| String | partyName |
| String | partyType |
| String | singleParty |
| String | purpose |
| String | purposeCode |
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
| String | out_Date |
| String | out_Time |
| String | pendingPbdOrGrn |
| String | inGateNoCode |
| String | inGateNoName |
| String | outGateNoCode |
| String | outGateNoName |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | currentDate |

### TxtHFinalWeighment_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | gateType |
| String | gateDate |

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

## RPC interface
| method | params | returns |
|---|---|---|
| getFillRecords | SearchValue_Mdl searchValueMdl, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdFinalWeighment_Mdl>> |
| getCardDetail |  | DmResult<CardDetail_Mdl> |
| getTxtHGateIn | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHFinalWeighment_Mdl>> |
| getTxtHSupplierPT | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHSupplierPT_Mdl>> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| getPartyList | String docid | DmResult<List<Party_Mdl>> |
| checkCurrentSiteRecord | String docId | DmResult<String> |

## Server `FinalWeighment_SrvImpl.java` (526 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.xNull`

### `getFillRecords`
`public DmResultPageing<List<GrdFinalWeighment_Mdl>> getFillRecords(SearchValue_Mdl searchValue_Mdl, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select gio.docId, gio.recId, gio.partyCode as partyCode, gio.vehicleTypeCode, vtm.name as vehicleTypeName, gio.inGateNoCode, gnm.name as inGateNoname , sbg.name as partyName, gnmout.name as outGateNoname , gio.outGateNoCode , sbg.partyType as partyType, pc.totalParty, c.cityName, CASE WHEN gio.loadUnload = {…} THEN ' {…} ' WHEN gio.loadUnload = {…} THEN ' {…} ' END AS purpose, gio.loadUnload AS purposeCode, CASE WHEN gio.loadUnload = {…} THEN CASE WHEN vUl.gateDocId IS NOT NULL THEN 1 ELSE 0 END ELSE 0 END AS pendingPbdOrGrn, gio.vehicleNo, gio.driver, um.unit as unitName, gio.docGrossWt, gio.docTareWt, gio.docNetWt, vt.short_Name, gio.v_No, gio.v_Date, gio.v_Time, gio.gateOutDate, gio.gateOutTime from gateInOut gio left join weighment wg on wg.gateDocId = gio.docId left join subGroup sbg ON sbg.subCode=gio.partyCode left join ( select uld.gateDocId as gateDocId, count(distinct po.subCode) as totalParty from unloadingDetail uld LEFT JOIN pOrder po on po.docId= uld.poDocId WHERE po.subCode is not null group by uld.gateDocId ) as pc on pc.gateDocId = gio.docId left join ( select uld.gateDocId as gateDocId from unloadingDetail uld WHERE uld.isStatus = 0 group by uld.gateDocId ) as vUl on vUl.gateDocId = gio.docId left join city as c on c.cityCode=sbg.cityCode left join unitMast um ON um.code=gio.unitCode Left join vehicleTypeMast vtm ON vtm.Code = gio.vehicleTypeCode Left join gatenomast gnm ON gnm.code = gio.inGateNoCode Left join gatenomast gnmout ON gnmout.code = gio.outGateNoCode left join voucher_Type vt ON vt.v_Type = gio.v_Type WHERE wg.isFinalWeighment = 1 AND CASE WHEN gio.loadUnload = {…} THEN CASE WHEN vUl.gateDocId IS NOT NULL THEN 1 ELSE CASE WHEN ISNULL(gio.gateOutBy,'') = '' THEN 1 ELSE 0 END END ELSE 1 END = 1 AND vt.category=(' {…} ') AND gio.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:fromv_Date` ← `DmServer.convertDate(searchValue_Mdl.getFromDate())`; `:tov_Date` ← `DmServer.convertDate(searchValue_Mdl.getToDate())`
- Other calls: `LOADING.getId`, `LOADING.getValue`, `UNLOADING.getId`, `UNLOADING.getValue`

### `getCardDetail`
`public DmResult<CardDetail_Mdl> getCardDetail()`

- SQL 1: `select count(gio.docId) as cnt , sum(case when DATEDIFF(day, gio.v_Date, CAST(:currentDate AS date)) <= {…} then 1 else 0 end) as low, sum(case when DATEDIFF(day, gio.v_Date, CAST(:currentDate AS date)) > {…} and DATEDIFF(day, gio.v_Date, CAST(:currentDate AS date)) <= {…} then 1 else 0 end) as medium, sum(case when DATEDIFF(day, gio.v_Date, CAST(:currentDate AS date)) >= {…} then 1 else 0 end) as high from gateInOut as gio left join voucher_type as vt on gio.v_Type = vt.v_Type left join weighment wg on wg.gateDocId = gio.docId left join ( select uld.gateDocId as gateDocId from unloadingDetail uld WHERE uld.isStatus = 0 group by uld.gateDocId ) as vUl on vUl.gateDocId = gio.docId WHERE wg.isFinalWeighment = 1 AND CASE WHEN gio.loadUnload = {…} THEN CASE WHEN vUl.gateDocId IS NOT NULL THEN 1 ELSE CASE WHEN ISNULL(gio.gateOutBy,'') = '' THEN 1 ELSE 0 END END ELSE 1 END = 1 and vt.category=(' {…} ') and gio.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:currentDate` ← `DmServer.getServerTimestamp()`
- Other calls: `HIGH_PRIORITYDAYS.getDays`, `LOW_PRIORITYDAYS.getDays`, `MEDIUM_PRIORITYDAYS.getDays`, `UNLOADING.getId`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`


### `getTxtHGateIn`
`public DmResultPageing<List<TxtHFinalWeighment_Mdl>> getTxtHGateIn(DmHelper helper, boolean blnToEndOfFile )`

- SQL 1: `SELECT gio.docId, gio.recId as gateNo, gio.gateOutDate as gateDate, vt.category, gio.v_No FROM gateInOut AS gio left join voucher_Type as vt on vt.v_Type = gio.v_Type where gio.site_Code= ' {…} ' and vt.Category = ' {…} '`

### `getTxtHSupplierPT`
`public DmResultPageing<List<TxtHSupplierPT_Mdl>> getTxtHSupplierPT(DmHelper helper, boolean blnToEndOfFile )`

- SQL 1: `SELECT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_type = acf.v_Type WHERE vt.category IN (' {…} ')`
- SQL 2: `SELECT sg.subCode AS code , sg.name , sg.manualCode , c.cityName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( {…} ) AND sg.isActive = 1 AND sgsl.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getPartyList`
`public DmResult<List<Party_Mdl>> getPartyList(String docid)`

- SQL 1: `select uld.gateDocId AS gateDocId , po.subCode AS partyCode , MAX(sg.name) As partyName , MAX(sg.partyType) As partyType, MAX(c.cityName) as cityName , uld.isStatus as isStatus from unloadingDetail uld LEFT JOIN pOrder po on po.docId= uld.poDocId LEFT JOIN subGroup sg ON sg.subCode = po.subCode LEFT JOIN city as c on c.cityCode = sg.cityCode WHERE po.subCode is not null AND uld.isStatus <> 1 and uld.gateDocId = :gateDocId GROUP BY uld.gateDocId, po.subCode ,c.cityCode, uld.isStatus`
- Params: `:gateDocId` ← `docid`

### `checkCurrentSiteRecord`
`public DmResult<String> checkCurrentSiteRecord(String docId)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.self.client`, `paperbiz.dashBoard.finalWeighment.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.dashboardCardStrip.client`, `paperbiz.selfToolkit.dashboardCardStrip.shared`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
