# pb-self-toolkit / packingListApproval

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/packingListApproval/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHPackingNo, getTxtHParty, getTxtHDeliveryCity, getTxtHItem, getTxtHGateNo, getTxtHStatus, getTxtHItemNature
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `PackingListApproval`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmGridPaging | `dmGrd` | `Grd_PrpAcs, gridRows, false` |

**Grid / popup columns** (header, width, db field):
- `dmGrd`:  [Text, w30];  [Text, w25]; Invoice No. [Text, w100, recId]; Type [Text, w70, type]; Date [Text, w80, date]; Item Nature [Text, w80, itemNature]; Party [Text, w200, partyName]; Consignee [Text, w180, consigneeName]; Delivery To [Text, w180, deliveryToName]; To Place [Text, w80, toPlaceName]; Gate Entry No. [Text, w100, gateRecId]; Transporter [Text, w180, transporterName]; Mode [Text, w80, trasnMode]; Approved By [Text, w130, approvedBy]

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
packingApp_Mdl = approvalEntry_Mdl;
dmGrd.getDmGrid().commitChanges();
dmGrd.reQuery();
```

`dmGrd.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(true).getFillRecords(param, helper, blnToEndOfFile, vCat, new AsyncCallback<DmResultPageing<List<GrdP
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdPackingListApproval_Mdl>> result) {
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
return packingApp_Mdl;
```
- Confirm: "Are you sure? You want to close."

### Screen `Setting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | From Date | DmTextBox | `txtFromDate` | `15, false` |
| 2 | To Date | DmTextBox | `txtToDate` | `15, false` |
| 3 | Item Nature | DmTextHelp | `txtHItemNature` | `KeyField_PrpAcs, 3, false` |
| 4 | Party | DmTextHelpMulti | `txtHParty` | `TxtHSupplierName_PrpAcs, 7` |
| 5 | Consignee | DmTextHelpMulti | `txtHConsignee` | `TxtHConsignee_PrpAcs, 7` |
| 6 | To Place | DmTextHelpMulti | `txtHDeliveryCity` | `TxtHDelivryCity_PrpAcs, 4` |
| 7 | Invoice No. | DmTextHelpMulti | `txtHPackingNo` | `TxtHPackingNo_PrpAcs, 7` |
| 8 | Status | DmTextHelp | `txtHStatus` | `KeyField_PrpAcs, 4, false` |
| 9 | Gate Entry No | DmTextHelpMulti | `txtHGateEntryNo` | `TxtHGateIn_PrpAcs, 7` |
| 10 | Transporter | DmTextHelpMulti | `txtHTransporter` | `TxtHTransporterName_PrpAcs, 7` |

**Grid / popup columns** (header, width, db field):
- `txtHPackingNo`: Packing No. [Text, w120, recId]; Packing Type [Text, w90, category]; Packing Date [Text, w90, date]
- `txtHStatus`: Status [Text, w150, Name]
- `txtHParty`: Name [Text, w220, supplierName]; Manual Code [Text, w100, manualCode]; City Name [Text, w100, cityName]
- `txtHItemNature`: Item Nature [Text, w100, name]
- `txtHConsignee`: Name [Text, w220, supplierName]; Manual Code [Text, w100, manualCode]; City Name [Text, w100, cityName]
- `txtHDeliveryCity`: City [Text, w0, cityName]; State [Text, w100, stateName]
- `txtHGateEntryNo`: Gate Entry No. [Text, w120, gateNo]; Gate Type [Text, w90, category]; Date [Text, w90, gateDate]
- `txtHTransporter`: Name [Text, w220, supplierName]; Manual Code [Text, w100, manualCode]; City Name [Text, w100, cityName]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHStatus` → `getTxtHStatus`
- `txtHItemNature` → `getTxtHItemNature`

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

`txtHPackingNo.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHPackingNo(helper, blnToEndOfFile, vCat, new AsyncCallback<DmResultPageing<Lis
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHPackingNo).show();
public void onSuccess(DmResultPageing<List<TxtHPackingNo_Mdl>> result) {
if (result.isStatus()) {
txtHPackingNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPackingNo).show();
```

`txtHPackingNo.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHPackingNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPackingNo).show();
```

`txtHGateEntryNo.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHGateNo(helper, blnToEndOfFile, vCat, new AsyncCallback<DmResultPageing<List<T
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGateEntryNo).show();
public void onSuccess(DmResultPageing<List<TxtHGateIn_Mdl>> result) {
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

`txtHParty.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHParty(helper, blnToEndOfFile, vCat, new AsyncCallback<DmResultPageing<List<Tx
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHParty).show();
public void onSuccess(DmResultPageing<List<TxtHSupplierPT_Mdl>> result) {
if (result.isStatus()) {
txtHParty.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHParty).show();
```

`txtHParty.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHParty.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHParty).show();
```

`txtHItemNature.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItemNature(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Key
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemNature).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHItemNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemNature).show();
```

`txtHItemNature.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemNature).show();
```

`txtHTransporter.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHParty(helper, blnToEndOfFile, vCat, new AsyncCallback<DmResultPageing<List<Tx
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHTransporter).show();
public void onSuccess(DmResultPageing<List<TxtHSupplierPT_Mdl>> result) {
if (result.isStatus()) {
txtHTransporter.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTransporter).show();
```

`txtHTransporter.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHTransporter.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTransporter).show();
```

`txtHConsignee.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHParty(helper, blnToEndOfFile, vCat, new AsyncCallback<DmResultPageing<List<Tx
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHConsignee).show();
public void onSuccess(DmResultPageing<List<TxtHSupplierPT_Mdl>> result) {
if (result.isStatus()) {
txtHConsignee.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHConsignee).show();
```

`txtHConsignee.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHConsignee.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHConsignee).show();
```

`txtHDeliveryCity.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHDeliveryCity(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<K
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDeliveryCity).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHDeliveryCity.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDeliveryCity).show();
```

`txtHDeliveryCity.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHDeliveryCity.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDeliveryCity).show();
```

`txtHDeliveryCity.addTextHelpMultiCallBack` → `getSelection()`
```java
if (blnStatus) {
```
- Alert: "From Date should be less than or equal to To Date."

## Files
| file | lines |
|---|---|
| client/PackingListApproval.java | 328 |
| client/PackingListApprovalCard.java | 139 |
| client/PackingListApproval_Dsg.java | 259 |
| client/Setting.java | 409 |
| client/Setting_Dsg.java | 396 |
| server/PackingListApproval_SrvImpl.java | 736 |
| shared/DefaultValue_Mdl.java | 22 |
| shared/GrdPackingListApproval_Mdl.java | 168 |
| shared/PackingListApproval_Mdl.java | 247 |
| shared/PackingListApproval_Srv.java | 34 |
| shared/PackingListApproval_SrvAsync.java | 34 |
| shared/TxtHGateIn_Mdl.java | 32 |
| shared/TxtHPackingNo_Mdl.java | 32 |
| shared/TxtHSupplierPT_Mdl.java | 31 |

## UI labels / columns (client)
`From Date`, `To Date`, `Item Nature`, `Party`, `Consignee`, `To Place`, `Invoice No.`, `Status`, `Gate Entry No`, `Transporter`

## Models (shared DTOs)
### PackingListApproval_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | FromDate |
| String | ToDate |
| String | StatusCode |
| String | StatusName |
| String | partyName |
| String | partyCode |
| String | itemNatureCode |
| String | itemNatureName |
| String | trasnporterName |
| String | trasnporterCode |
| String | gateNo |
| String | gateCode |
| String | consigneeCode |
| String | consigneeName |
| String | deliveryCityCode |
| String | deliveryCityName |
| String | packingNoCode |
| String | packingNoName |
| String | packingNoDate |
| String | deliveryToName |
| String | vSNo |
| String | siteCode |
| String | siteName |
| String | isStatus |
| String | wtaCode |

### TxtHPackingNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | type |
| String | date |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | currentDate |

### GrdPackingListApproval_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | isChecked |
| String | IsView |
| String | isStatus |
| String | packingNo |
| String | type |
| String | date |
| String | party |
| String | itemNature |
| String | ApprovedBy |
| String | consignee |
| String | deliveryTo |
| String | toPlace |
| String | gateNo |
| String | transporter |
| String | transportMode |
| String | Approval |
| String | wtaCode |

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
| getFillRecords | PackingListApproval_Mdl param, DmHelper helper, boolean blnToEndOfFile, String vCat | DmResultPageing<List<GrdPackingListApproval_Mdl>> |
| getTxtHPackingNo | DmHelper pageing, boolean blnToEndOfFile, String vCat | DmResultPageing<List<TxtHPackingNo_Mdl>> |
| getTxtHGateNo | DmHelper pageing, boolean blnToEndOfFile, String vCat | DmResultPageing<List<TxtHGateIn_Mdl>> |
| getTxtHParty | DmHelper pageing, boolean blnToEndOfFile, String vCat | DmResultPageing<List<TxtHSupplierPT_Mdl>> |
| getTxtHItem | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDeliveryCity | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHStatus | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| setApproval | String approvedBy, boolean isApprove, String docId | DmResult<String> |
| getCardDetail | String vCat | DmResult<CardDetail_Mdl> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| getTxtHItemNature | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Server `PackingListApproval_SrvImpl.java` (736 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerDateTime`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.xNull`

### `getFillRecords`
`public DmResultPageing<List<GrdPackingListApproval_Mdl>> getFillRecords(PackingListApproval_Mdl param, DmHelper helper, boolean blnToEndOfFile, String vCat)`

- SQL 1: `SELECT pkl.docId,pkl.recId,pkl.v_Date , itn.name as itemNatureName ,pkl.partyCode AS supplierCode,sg.name AS partyName, vt.short_Name, pkl.consignee AS consigneeCode,sgc.name AS consigneeName,pkl.v_No, pkl.deliveryTo AS deliveryToCode,sgdt.name AS deliveryToName, pkl.deliveryCity,c.cityName, pkl.u_Name,pkl.preparedBy,pkl.approvedBy, format(pkl.approvalDateTime,'dd-MMM-yyyy HH.mm') as approvalDateTime,pkl.wtaCode , pkl.gateDocId , gtIo.recId as gateRecId ,pkl.transporterCode , sgt.name as transporterName , pkl.transportMode FROM pkList AS pkl LEFT JOIN voucher_Type AS vt ON vt.v_Type = pkl.v_Type LEFT JOIN subGroup AS sg ON sg.subCode = pkl.partyCode LEFT JOIN itemNature AS itn ON itn.code = pkl.itemNature LEFT JOIN subGroup AS sgt ON sgt.subCode = pkl.transporterCode LEFT JOIN subGroup AS sgc ON sgc.subCode = pkl.consignee LEFT JOIN subGroup AS sgdt ON sgdt.subCode = pkl.deliveryTo LEFT JOIN city AS c ON c.cityCode = pkl.deliveryCity LEFT JOIN gateInOut as gtIo ON gtIo.docId = pkl.gateDocId {…} where vt.category IN ( {…} ) {…}`
- Params: `:fromv_Date` ← `DmServer.convertDate(param.getFromDate())`; `:tov_Date` ← `DmServer.convertDate(param.getToDate())`; `:distributor` ← `DmShared.xNull(param.getItemNatureCode())`
- Other calls: `TransportMode.getViaKey`

### `getTxtHPackingNo`
`public DmResultPageing<List<TxtHPackingNo_Mdl>> getTxtHPackingNo(DmHelper helper, boolean blnToEndOfFile, String vCat)`

- SQL 1: `SELECT pkl.docId, pkl.recId , pkl.v_Date as date, vt.category FROM pkList AS pkl left join voucher_Type as vt on vt.v_Type = pkl.v_Type where pkl.site_Code= ' {…} ' and vt.Category IN ( {…} )`

### `getTxtHParty`
`public DmResultPageing<List<TxtHSupplierPT_Mdl>> getTxtHParty(DmHelper helper, boolean blnToEndOfFile, String vCat)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as supplierName , sg.manualCode , c.cityName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( SELECT DISTINCT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_Type = acf.v_Type WHERE vt.category IN( {…} ) ) AND sg.isActive = 1 AND sgsl.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHDeliveryCity`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDeliveryCity(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select ct.cityCode, ct.cityName, ct.stateCode, sm.name as stateName from city ct left join stateMast sm on ct.stateCode = sm.code`

### `setApproval`
`public DmResult<String> setApproval(String approvedBy, boolean isApprove, String docId)`


### `walkThrough`
`public DmResult<String> walkThrough(WalkThrough_Mdl walkThrough)`

- Other calls: `WalkThrough_Server.walkThrough`

### `getCardDetail`
`public DmResult<CardDetail_Mdl> getCardDetail(String vCat)`

- SQL 1: `Select count(pkl.docId) as cnt, sum(case when DATEDIFF(day, pkl.v_Date, CAST(getdate() AS date)) <= {…} then 1 else 0 end) as low, sum(case when DATEDIFF(day, pkl.v_Date, CAST(getdate() AS date)) > {…} and DATEDIFF(day, pkl.v_Date, CAST(getdate() AS date)) <= {…} then 1 else 0 end) as medium, sum(case when DATEDIFF(day, pkl.v_Date, CAST(getdate() AS date)) >= {…} then 1 else 0 end) as high from pkList as pkl left join voucher_type as vt on pkl.v_Type = vt.v_Type left join( select pkl.docId from pkList as pkl group by pkl.docId ) as vSbd on vSbd.docId = pkl.docId LEFT JOIN walkThroughFootPrint wtfp on wtfp.wtaCode = pkl.wtaCode and wtfp.isStatus = 0 where vSbd.docId is not null and isNull(pkl.approvedBy,'') = '' and vt.category IN ( {…} ) AND isNull(wtfp.linkedUser,' {…} ,') like '% {…} ,%' and pkl.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `HIGH_PRIORITYDAYS.getDays`, `LOW_PRIORITYDAYS.getDays`, `MEDIUM_PRIORITYDAYS.getDays`

### `getTxtHItem`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select im.code as itemCode, im.name as itemName, um.unit as itemUnitName from itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN unitMast um ON um.code = im.saleUnit WHERE im.IsActive = 1 AND (im.filterItemType = :filterItemType OR im.isCommon = 1) AND sl.site_Code = :site_Code`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`


### `getTxtHGateNo`
`public DmResultPageing<List<TxtHGateIn_Mdl>> getTxtHGateNo(DmHelper helper, boolean blnToEndOfFile, String vCat)`

- SQL 1: `SELECT gio.docId, gio.recId as gateNo , gio.v_Date as gateDate, vt.category, gio.v_No FROM gateInOut AS gio left join voucher_Type as vt on vt.v_Type = gio.v_Type where gio.site_Code= ' {…} ' and vt.Category IN ( ' {…} ' )`

### `getTxtHStatus`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHStatus(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHItemNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemNature(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select itn.code, itn.name, itn.manualCode from itemNature as itn`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.dashboardCardStrip.client`, `paperbiz.selfToolkit.dashboardCardStrip.shared`, `paperbiz.selfToolkit.packingListApproval.shared`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
**Standard approval screen** (pattern `_module.md` me). Is document ke liye: pending/approved list `getFillRecords` (Setting popup ke filters), approve/reject `setApproval(approvedBy, isApprove, docId)`, dashboard card ka count `getCardDetail` (din ke ageing buckets), lookups `getTxtH…`. Document ka approval logic `stStatic/STApprovals` me.
