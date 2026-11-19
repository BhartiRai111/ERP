# pb-dashboard / unloading

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/dashBoard/src/unloading/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHUnloadingNo, getTxtHPartyName, getTxtHGateEntryNo, getTxtHWeighment
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `Setting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | From Date | DmTextBox | `txtFromDate` | `15, false` |
| 2 | To Date | DmTextBox | `txtToDate` | `15, false` |
| 3 | Unloading No. | DmTextHelpMulti | `txtHUnloadingNo` | `TxtHUnloadingNo_PrpAcs, 7, false` |
| 4 | Gate Entry No. | DmTextHelpMulti | `txtHGateEntryNo` | `TxtHGateEntryNo_PrpAcs, 6, false` |
| 5 | Weighment No. | DmTextHelpMulti | `txtHWeighment` | `TxtHWeighment_PrpAcs, 6, false` |
| 6 | Party Name | DmTextHelpMulti | `txtHPartyName` | `TxtHPartyName_PrpAcs, 6, false` |

**Grid / popup columns** (header, width, db field):
- `txtHGateEntryNo`: Gate Entry No. [Text, w0, recId]; Party Name [Text, w200, partyName]; Gate Entry Date [Text, w120, gateEntryDate]
- `txtHUnloadingNo`: Unloading No. [Text, w90, unlodRecId]; Unloading Type [Text, w100, category]; Unloading Date [Text, w100, unlodDate]
- `txtHWeighment`: Weighment No. [Text, w120, weighmentName]; Gate Entry No. [Text, w120, gateName]; Party [Text, w180, partyName]
- `txtHPartyName`: Name [Text, w0, name]; Manual Code [Text, w100, manualCode]; City [Text, w80, cityName]

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtFromDate.addValueChangeHandler` → `onValueChange()`
```java
txtFromDate.setText(DmClient.returnDate(txtFromDate.getText()));
```

`txtToDate.addValueChangeHandler` → `onValueChange()`
```java
txtToDate.setText(DmClient.returnDate(txtToDate.getText()));
```

`txtHUnloadingNo.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHUnloadingNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Tx
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHUnloadingNo).show();
public void onSuccess(DmResultPageing<List<TxtHUnloadingNo_Mdl>> result) {
if (result.isStatus()) {
txtHUnloadingNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHUnloadingNo).show();
```

`txtHUnloadingNo.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHUnloadingNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHUnloadingNo).show();
```

`txtHUnloadingNo.addTextHelpMultiCallBack` → `getSelection()`
```java
if (blnStatus) {
```

`txtHPartyName.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHPartyName(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtH
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHPartyName).show();
public void onSuccess(DmResultPageing<List<TxtHPartyName_Mdl>> result) {
if (result.isStatus()) {
txtHPartyName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPartyName).show();
```

`txtHPartyName.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHPartyName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPartyName).show();
```

`txtHWeighment.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHWeighment(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtH
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHWeighment).show();
public void onSuccess(DmResultPageing<List<TxtHWeighment_Mdl>> result) {
if (result.isStatus()) {
txtHWeighment.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHWeighment).show();
```

`txtHWeighment.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHWeighment.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHWeighment).show();
```

`txtHGateEntryNo.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHGateEntryNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Tx
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGateEntryNo).show();
public void onSuccess(DmResultPageing<List<TxtHGateEntry_Mdl>> result) {
if (result.isStatus()) {
txtHGateEntryNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGateEntryNo).show();
```

`txtHGateEntryNo.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHGateEntryNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGateEntryNo).show();
```
- Alert: "From Date should be less than or equal to To Date."

### Screen `UnloadingDetailPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Unloading No. | DmTextBox | `txtUnloadingNo` | `50, false` |
| 2 | Date | DmTextBox | `txtDate` | `11, false` |
| 3 | Gate Entry No. | DmTextBox | `txtGateNo` | `50, false` |
| 4 | Weighment | DmTextBox | `txtWeighment` | `50, false` |

Other widgets (not in tab list): `grdUnloadingDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `grdUnloadingDetail`: Order No. [Text, w140]; Item Name [Text, w237]; Properties [Text, w180]; Approx wt. [Text, w90]; Unit [Text, w50]; Contractor [Text, w240]; Godown [Text, w100]; No. Of Labours [Text, w90];  [Text, w30]

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtUnloadingNo.setEnabled(false)`
- `txtDate.setEnabled(false)`
- `txtWeighment.setEnabled(false)`
- `txtGateNo.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
txtUnloadingNo.setEnabled(false);
txtDate.setEnabled(false);
txtWeighment.setEnabled(false);
txtGateNo.setEnabled(false);
```
- Alert: "Lab Sample already exist."
- Alert: "Lab Sample is not required for this item."
- Alert: "Lab Sample already exist with this item."

### Screen `Unloading`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmGridPaging | `gridUnloading` | `GridUnloading_PrpAcs, gridRows, false` |

Other widgets (not in tab list): `txtRequisitionNo` (DmTextBox), `txtDate` (DmTextBox), `txtDepartment` (DmTextBox), `txtRequistionBy` (DmTextBox), `txtSearchValue` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `gridUnloading`: Detail [Text, w50]; Unloading No. [Text, w120, recId]; Date [Text, w110, v_date]; Type [Text, w80, short_Name]; Gate Entry No. [Text, w120, gateNo]; Weighment No. [Text, w120, weighmentNo]; Party Name [Text, w420, PartyName]; Vehicle Type [Text, w110, vehicleType]; Vehicle No. [Text, w110, vehicleNo]

**Lookup / suggestion / find sources (server RPC called):**
- `gridUnloading` → `getFillRecords`

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
setting_Mdl = sm;
gridUnloading.reQuery();
```

`gridUnloading.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnToEndOfFile).getFillRecords(setting_Mdl, helper, blnToEndOfFile, new AsyncCallback<DmResultPageing
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdUnloading_Mdl>> result) {
if (result.isStatus()) {
gridUnloading.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`gridUnloading.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
gridUnloading.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`gridUnloading.addCallBack` → `getParam()`
```java
return setting_Mdl;
```

`detailPopup.addCallBack` → `onOk()`
```java
showSampleEntry(keyFieldExtended);
```
- Confirm: "Are you sure? You want to close."
- Alert: "Please select the Unloading No."
- Alert: "Second Weighment already exist."

## Files
| file | lines |
|---|---|
| client/Setting.java | 288 |
| client/Setting_Dsg.java | 289 |
| client/Unloading.java | 505 |
| client/UnloadingCard.java | 129 |
| client/UnloadingDetailPopup.java | 245 |
| client/UnloadingDetailPopup_Dsg.java | 279 |
| client/Unloading_Dsg.java | 299 |
| server/Unloading_SrvImpl.java | 751 |
| shared/DefaultValue_Mdl.java | 23 |
| shared/GrdUnloadingDetail_Mdl.java | 242 |
| shared/GrdUnloading_Mdl.java | 150 |
| shared/SampleDetail_Mdl.java | 86 |
| shared/Setting_Mdl.java | 177 |
| shared/TxtHGateEntry_Mdl.java | 125 |
| shared/TxtHPartyName_Mdl.java | 60 |
| shared/TxtHUnloadingNo_Mdl.java | 35 |
| shared/TxtHUnloading_Mdl.java | 51 |
| shared/TxtHWeighment_Mdl.java | 87 |
| shared/Unloading_Mdl.java | 117 |
| shared/Unloading_Srv.java | 33 |
| shared/Unloading_SrvAsync.java | 35 |

## UI labels / columns (client)
`Action`, `Unloading No.`, `Date`, `Gate Entry No.`, `Weighment`, `From Date`, `To Date`, `Weighment No.`, `Party Name`

## Models (shared DTOs)
### TxtHUnloading_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | unloadingV_Sno |
| String | unloadingDate |
| String | unloadingTime |
| String | poRecId |

### GrdUnloadingDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | orderDocId |
| String | orderRecId |
| String | orderV_Sno |
| String | gateV_Sno |
| String | propertiesCsv |
| String | propertyList |
| String | approxweight |
| String | unitCode |
| String | unitName |
| String | unitDecimalPlaces |
| String | contractorCode |
| String | contractorName |
| String | totalUnloader |
| String | unLoadingCharge |
| String | departmentCode |
| String | departmentName |
| String | itemCode |
| String | itemName |
| String | v_SNo |
| String | isLabRequired |
| boolean | labSampleStatus |
| String | shortName |
| String | description |
| String | unloadingType |
| ItemProperty_Mdl | ipm |

### Unloading_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | GateNoCode |
| String | GateNoName |
| String | WeighmentNoName |
| String | WeighmentNOCode |
| String | UnloadingCode |
| String | UnloadingName |
| String | date |
| String | vCat |
| String | v_Type |
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
| String | GateNoCode |
| String | GateNoName |
| String | WeighmentNoName |
| String | WeighmentNOCode |
| String | PartyName |
| String | PartyCode |
| String | UnloadingCode |
| String | UnloadingName |
| String | vSNo |
| String | siteCode |
| String | siteName |
| String | approvedBy |
| String | isStatus |
| String | approvedDateTime |
| String | StatusCode |
| String | StatusName |

### GrdUnloading_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | isChecked |
| String | IsView |
| String | unloadingType |
| String | unloadingDate |
| String | firstWeightmentNo |
| String | WeightmentdocId |
| String | partyName |
| String | vehicleType |
| String | vehicleNo |
| String | approvedBy |
| String | Approval |
| String | isStatus |
| String | gateNo |
| String | shortName |
| String | description |

### TxtHWeighment_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | gateDocId |
| String | gateName |
| String | partyCode |
| String | partyName |
| String | vehicleNo |
| String | vehicleType |
| String | weighmentDate |
| String | weighmentTime |

### TxtHPartyName_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | stateCode |
| String | stateName |
| String | manualCode |
| String | cityName |
| String | partyType |

### TxtHGateEntry_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | v_GateDate |
| String | v_GateTime |
| String | partyCode |
| String | partyName |
| String | site_Code |
| String | unit |
| String | purpose |
| String | doc_Weight |
| String | tare_Weight |
| String | net_Weight |
| String | gateEntryDate |

### SampleDetail_Mdl
| type | field |
|---|---|
| String | docId |
| String | v_sno |
| String | itemCode |
| String | itemName |
| String | v_Type |
| String | description |
| String | short_name |
| String | recId |

### TxtHUnloadingNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | unloadingType |
| String | unloadingDate |

## RPC interface
| method | params | returns |
|---|---|---|
| getCardDetail |  | DmResult<CardDetail_Mdl> |
| getFillRecords | Setting_Mdl setting_Mdl, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdUnloading_Mdl>> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| getTxtHUnloadingNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHUnloadingNo_Mdl>> |
| getTxtHGateEntryNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHGateEntry_Mdl>> |
| getTxtHWeighment | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHWeighment_Mdl>> |
| getTxtHPartyName | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHPartyName_Mdl>> |
| getUnloadingDetail | String docId | DmResult<Unloading_Mdl> |
| checkWeighmentExist | String gateDocId | DmResult<Boolean> |
| checkSampleExist | KeyFieldExtended keyFieldExtended | DmResult<Boolean> |

## Server `Unloading_SrvImpl.java` (751 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.xNull`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`


### `checkWeighmentExist`
`public DmResult<Boolean> checkWeighmentExist(String gateDocId)`

- SQL 1: `select count(*) as cnt from weighment as wgmt where isnull(wgmt.outweighby,'') <> '' and wgmt.docId= :docId`
- Params: `:docId` ← `DmShared.iNull(gateDocId).toString()`

### `checkSampleExist`
`public DmResult<Boolean> checkSampleExist(KeyFieldExtended keyFieldExtended)`


### `getFillRecords`
`public DmResultPageing<List<GrdUnloading_Mdl>> getFillRecords(Setting_Mdl setting_Mdl, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select ul.docId, ul.v_Type as voucherCode,ul.weighmentDocId,ul.v_date,vt.description as voucherName,ul.v_No, ul.recIdPrefix, ul.recId, ul.v_Date as ulDate, ul.v_Time as ulTime , ul.site_Code,vt.short_Name, ul.weighmentDocId as weightmenCode, sm.name as siteName, sg.name as PartyName, wg.recId as weighmentNo, gio.recId as gateNo,wg.gateDocId as gateCode,ul.u_Name, ul.preparedBy, ul.preparedDt, ul.u_EntDt, gio.vehicleType,sgp.name as GIOPartyName,gio.vehicleNo, wg.v_Date as weighmentDate, wg.v_Time as weighmentTime from unloading ul LEFT JOIN siteMast sm ON sm.code = ul.site_Code LEFT JOIN voucher_Type vt ON vt.v_Type = ul.v_Type LEFT JOIN subGroup sg ON sg.subCode = ul.partyCode LEFT JOIN weighment wg ON wg.docId = ul.weighmentDocId LEFT JOIN ( select uld.docid, MAX(uld.gateDocId) as gateDocId from unloadingDetail uld GROUP BY uld.docid ) vUld ON vUld.docid = ul.docId LEFT JOIN gateInOut gio ON gio.docId = vUld.gateDocId left join subGroup sgp ON sgp.subCode=gio.partyCode where ul.site_Code = :site_Code and isnull(wg.outweighby,'') = '' and vUld.docId IS NOT NULL and vt.category in (' {…} ') {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:fromv_Date` ← `DmServer.convertDate(setting_Mdl.getFromDate())`; `:tov_Date` ← `DmServer.convertDate(setting_Mdl.getToDate())`

### `getUnloadingDetail`
`public DmResult<Unloading_Mdl> getUnloadingDetail(String docId)`

- SQL 1: `SELECT rq.docId, rq.recId , rq.v_Date,wg.recId as weighmentNo, gio.recId as gateNo, rq.preparedBy, rq.u_Name,rq.u_EntDt_LatestLine,vt.category , vt.description ,vt.v_Type from unloading rq LEFT JOIN weighment wg ON wg.docId = rq.weighmentDocId LEFT JOIN unloadingDetail AS uld ON uld.docId = rq.docId LEFT JOIN gateInOut gio ON gio.docId =uld .gateDocId LEFT JOIN voucher_type AS VT ON rq.v_Type = VT.v_Type where rq.docid=:docid`
- SQL 2: `select uld.itemCode,uld.docId,uld.v_sno,im.name as itemName,im.isLabRequired , uld.departmentCode, deprtm.nature as departmentName, im.propertyList, um.decimalPlaces as decimalPlaces, uld.contractorCode, po.recId as poRecId, po.docId as poDocId, uld.gateAdviceV_Sno, sg.name as contractorName, uld.unitCode, um.unit as unitName, uld.noOfLabour as totalUnloader, uld.unloadingAmt, uld.approxQty as approxWt from unloadingDetail uld LEFT JOIN itemMast im ON im.code = uld.itemCode LEFT JOIN subGroup dm On dm.subCode = uld.departmentCode LEFT JOIN subGroup sg ON sg.subCode = uld.contractorCode LEFT JOIN unitMast um ON um.code = uld.unitCode LEFT JOIN pOrder po ON po.docId = uld.poDocId LEFT JOIN gateAdviceDetail gad ON gad.docId = uld.gateDocId AND gad.v_Sno = uld.gateAdviceV_Sno LEFT JOIN departmentMast deprtm ON deprtm.subCode = uld.departmentCode where uld.docId = :docId`
- Params: `:docid` ← `docId`; `:docid` ← `docId`

### `isRecordExistInLabSample`
`private boolean isRecordExistInLabSample(String unloadingDocId, String v_Sno, String itemCode, Connection cnTemp) throws SQLException`

- SQL 1: `select count(*) as cnt from labSample as labs where labs.refDocId=:docId and labs.refV_Sno=:V_Sno and itemCode=:itemCode`
- Params: `:docId` ← `DmShared.xNull(unloadingDocId)`; `:V_Sno` ← `DmShared.xNull(v_Sno)`; `:itemCode` ← `DmShared.xNull(itemCode)`

### `getCardDetail`
`public DmResult<CardDetail_Mdl> getCardDetail()`

- SQL 1: `Select count(unlod.docId) as cnt , sum(case when DATEDIFF(day, unlod.v_Date, CAST(:currentDate AS date)) <= {…} then 1 else 0 end) as low, sum(case when DATEDIFF(day, unlod.v_Date, CAST(:currentDate AS date)) > {…} and DATEDIFF(day, unlod.v_Date, CAST(:currentDate AS date)) <= {…} then 1 else 0 end) as medium, sum(case when DATEDIFF(day, unlod.v_Date, CAST(:currentDate AS date)) >= {…} then 1 else 0 end) as high from unloading as unlod left join voucher_type as vt on unlod.v_Type = vt.v_Type LEFT JOIN weighment wgmnt ON wgmnt.docid = unlod.weighmentDocid where ISNULL(wgmnt.outWeighBy, '') = '' and vt.category = (' {…} ') and unlod.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:currentDate` ← `DmServer.getServerTimestamp()`
- Other calls: `HIGH_PRIORITYDAYS.getDays`, `LOW_PRIORITYDAYS.getDays`, `MEDIUM_PRIORITYDAYS.getDays`

### `getTxtHUnloadingNo`
`public DmResultPageing<List<TxtHUnloadingNo_Mdl>> getTxtHUnloadingNo(DmHelper helper, boolean blnToEndOfFile )`

- SQL 1: `SELECT unlod.docId, unlod.recId as unlodRecId, unlod.v_Date as unlodDate, vt.category FROM unloading AS unlod left join voucher_Type as vt on vt.v_Type = unlod.v_Type where unlod.site_Code = ' {…} ' AND vt.category= ' {…} '`

### `getTxtHPartyName`
`public DmResultPageing<List<TxtHPartyName_Mdl>> getTxtHPartyName(DmHelper helper, boolean blnToEndOfFile )`

- SQL 1: `SELECT sg.subCode AS code, sg.name, sg.manualCode, c.cityName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sg.isActive = 1 and sgn.code in ( SELECT acf.Nature FROM AcFilteration acf left join voucher_Type vtw On vtw.v_Type = acf.v_Type WHERE vtw.category in (' {…} ') ) AND sg.isActive = 1 AND sgsl.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHGateEntryNo`
`public DmResultPageing<List<TxtHGateEntry_Mdl>> getTxtHGateEntryNo(DmHelper helper, boolean blnToEndOfFile )`

- SQL 1: `select gtIO.docId, gtIO.recId , gtIO.v_Date as gateEntryDate ,gtIO.v_Time as gateEntryTime , gtIO.v_Date as v_Date , gtIO.v_No, gtIO.partyCode , gtIO.loadUnload , gtIO.docGrossWt , gtIO.docTareWt , gtIO.docNetWt , gtIO.unitCode, sg.name as partyName , um.unit as unitName , vt.short_Name from gateInOut as gtIO left join subGroup as sg on sg.subCode = gtIO.partyCode left join unitMast as um on um.code = gtIO.unitCode LEFT JOIN voucher_Type vt ON gtIO.v_Type = vt.v_Type where gtIO.isWeighmentRequired = 1 And isnull(gtIO.gateOutBy, '' ) = '' {…}`

### `getTxtHWeighment`
`public DmResultPageing<List<TxtHWeighment_Mdl>> getTxtHWeighment(DmHelper helper, boolean blnToEndOfFile )`

- SQL 1: `select wg.docId as weighmentCode, wg.v_Date,wg.recId as weighmentName, wg.partyCode,sg.name as partyName, wg.gateDocId as gateCode, gio.recId as gateName, gio.vehicleType, gio.vehicleNo, wg.v_Date as weighmentDate, wg.v_Time as weighmentTime from weighment wg LEFT JOIN subGroup sg ON sg.subCode = wg.partyCode LEFT JOIN gateInOut gio ON gio.docId = wg.gateDocId`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxTime.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.self.client`, `paperbiz.dashBoard.requisition.shared`, `paperbiz.dashBoard.unloading.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.dashboardCardStrip.client`, `paperbiz.selfToolkit.dashboardCardStrip.shared`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
