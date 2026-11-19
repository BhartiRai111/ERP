# pb-dashboard / packingList

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/dashBoard/src/packingList/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHPackingListNo, getTxtHSupplier, getTxtHDeliveryCity, getTxtHDeliveryTo
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `PackingList`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmGridPaging | `gridPackingList` | `GrdPaging_PrpAcs, gridRows, false` |

Other widgets (not in tab list): `txtSearchValue` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `gridPackingList`:  [Text, w30]; PackingList No. [Text, w120, recId]; Date [Text, w80, replace(convert(varchar, v_Date, 106), ' ','-')]; Short Name [Text, w70, short_Name]; Item Nature [Text, w80, itemNatureName]; Party Name [Text, w250, partyName]; Delivery To [Text, w250, deliveryToName]; Delivery City [Text, w250, deliveryCityName]; Transporter [Text, w250, transporterName]; Vehicle Type [Text, w120, vehicleTypeName]; Mode [Text, w120, transportMode]; Vehicle No [Text, w120, vehicleNo]; LR No. [Text, w120, lrNo]; LR Date [Text, w80, replace(convert(varchar, lrDate, 106), ' ','-')]

**Lookup / suggestion / find sources (server RPC called):**
- `gridPackingList` → `getPackingListDetail`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`settingPopup.addPackingListCallBack` → `onOk()`
```java
searchValue_Mdl = new SearchValue_Mdl();
searchValue_Mdl = sm;
gridPackingList.reQuery();
```

`gridPackingList.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(false).getPackingListDetail(searchValue_Mdl, helper, blnToEndOfFile, new AsyncCallback<DmResultPagein
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdPackingList_Mdl>> result) {
if (result.isStatus()) {
gridPackingList.setData(result.getData(), result.getPaging());
gridPackingList.getDmGrid().commitChanges();
```

`gridPackingList.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
gridPackingList.setData(result.getData(), result.getPaging());
gridPackingList.getDmGrid().commitChanges();
```

`gridPackingList.addCallBack` → `getParam()`
```java
return searchValue_Mdl;
```
- Confirm: "Are you sure? You want to close."
- Alert: "Please select the PackingList."

### Screen `Setting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | From Date | DmTextBox | `txtFromDate` | `15, false` |
| 2 | To Date | DmTextBox | `txtToDate` | `15, false` |
| 3 | Customer | DmTextHelpMulti | `txtHCustomer` | `TxtHSupplier_PrpAcs, 5` |
| 4 | Delivery To | DmTextHelpMulti | `txtHDeliveryTo` | `TxtHDeliveryTo_PrpAcs, 4` |
| 5 | Delivery City | DmTextHelpMulti | `txtHDeliveryCity` | `TxtHCity_Mdl_PrpAcs, 5` |
| 6 | PackingList No. | DmTextHelpMulti | `txtHPackingList` | `KeyField_PrpAcs1, 7` |

**Grid / popup columns** (header, width, db field):
- `txtHDeliveryTo`: Name [Text, w250, supplierName]; Manual Code [Text, w100, manualCode]; City [Text, w90, cityName]
- `txtHCustomer`: Name [Text, w0, name]; Manual Code [Text, w100, manualCode]; City [Text, w150, cityName]
- `txtHDeliveryCity`: City [Text, w100, CityName]; State [Text, w100, stateName]
- `txtHPackingList`: PackingList No. [Text, w120, pklNo]; PackingList Type [Text, w100, category]; PackingList Date [Text, w100, v_Date]

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtFromDate.addValueChangeHandler` → `onValueChange()`
```java
txtFromDate.setText(DmClient.returnDate(txtFromDate.getText()));
```

`txtToDate.addValueChangeHandler` → `onValueChange()`
```java
txtToDate.setText(DmClient.returnDate(txtToDate.getText()));
```

`txtHCustomer.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHSupplier(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHS
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHCustomer).show();
public void onSuccess(DmResultPageing<List<TxtHSupplier_Mdl>> result) {
if (result.isStatus()) {
txtHCustomer.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCustomer).show();
```

`txtHCustomer.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHCustomer.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCustomer).show();
```

`txtHCustomer.addTextHelpMultiCallBack` → `getSelection()`
```java
if (blnStatus) {
```

`txtHDeliveryTo.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHDeliveryTo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Txt
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDeliveryTo).show();
public void onSuccess(DmResultPageing<List<TxtHParty_Mdl>> result) {
if (result.isStatus()) {
txtHDeliveryTo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDeliveryTo).show();
```

`txtHDeliveryTo.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHDeliveryTo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDeliveryTo).show();
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

`txtHPackingList.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHPackingListNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHPackingList).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHPackingList.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPackingList).show();
```

`txtHPackingList.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHPackingList.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPackingList).show();
```

`txtHPackingList.addTextHelpMultiCallBack` → `getSelection()`
```java
if (blnStatus) {
```
- Alert: "From Date should be less than or equal to To Date."

## Files
| file | lines |
|---|---|
| client/PackingList.java | 440 |
| client/PackingListCard.java | 130 |
| client/PackingList_Dsg.java | 312 |
| client/Setting.java | 277 |
| client/Setting_Dsg.java | 281 |
| server/PackingList_SrvImpl.java | 632 |
| shared/DefaultValue_Mdl.java | 22 |
| shared/GrdPackingList_Mdl.java | 231 |
| shared/PackingList_Srv.java | 28 |
| shared/PackingList_SrvAsync.java | 28 |
| shared/SearchValue_Mdl.java | 104 |
| shared/TxtHParty_Mdl.java | 194 |
| shared/TxtHSupplier_Mdl.java | 32 |

## UI labels / columns (client)
`Action`, `From Date`, `To Date`, `Customer`, `Delivery To`, `Delivery City`, `PackingList No.`

## Models (shared DTOs)
### DefaultValue_Mdl
| type | field |
|---|---|
| String | currentDate |

### TxtHSupplier_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | cityName |
| String | manualCode |

### SearchValue_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | fromDate |
| String | toDate |
| String | supplierName |
| String | supplierCode |
| String | packingListDocId |
| String | packingListRecId |
| String | deliveryToCode |
| String | deliveryToName |
| String | deliveryCityName |
| String | deliveryCityCode |

### TxtHParty_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityCode |
| String | cityName |
| String | stateCode |
| String | stateName |
| String | guiConfigCode |
| String | zoneCode |
| String | zoneName |
| String | partyType |
| String | gstin |
| String | gstinDate |
| String | partyPriceListCode |
| String | partyNature |
| String | dueDays |
| String | pan |
| String | tan |
| String | tcsCategoryCode |
| String | tcsCategoryName |
| boolean | tcsActive |
| String | pinCode |

### GrdPackingList_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | isView |
| String | vtypeName |
| String | vtypeCode |
| String | vcategory |
| String | packingListDate |
| String | vtypeShortName |
| String | supplierName |
| String | supplierCode |
| String | deliveryToName |
| String | deliveryToCode |
| String | deliveryCityName |
| String | deliveryCityCode |
| String | transporterName |
| String | transporterCode |
| String | vehicleTypeName |
| String | vehicleTypeCode |
| String | vehicleModeName |
| String | vehicleModeCode |
| String | vehicleNo |
| String | lrNo |
| String | lrDate |
| String | itemNatureCode |
| String | itemNatureName |
| String | siteCode |

## RPC interface
| method | params | returns |
|---|---|---|
| getPackingListDetail | SearchValue_Mdl searchValue_Mdl, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdPackingList_Mdl>> |
| getCardDetail |  | DmResult<CardDetail_Mdl> |
| getTxtHPackingListNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSupplier | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHSupplier_Mdl>> |
| getTxtHDeliveryTo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getTxtHDeliveryCity | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |

## Server `PackingList_SrvImpl.java` (632 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.xNull`

### `getPackingListDetail`
`public DmResultPageing<List<GrdPackingList_Mdl>> getPackingListDetail(SearchValue_Mdl param, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT pkl.docId, pkl.v_No, pkl.recId, pkl.v_Date, pkl.v_Type, vt.category AS voucherCategory, vt.short_Name, pkl.partyCode, sg.name as partyName, pkl.deliveryTo as deliveryToCode, sgdt.name as deliveryToName, pkl.deliveryCity, dc.cityName as deliveryCityName, pkl.transporterCode, sgtp.name as transporterName, pkl.vehicleTypeCode, vtm.name as vehicleTypeName, pkl.transportMode, pkl.vehicleNo, pkl.lrNo, pkl.itemNature as itemNatureCode, itn.name as itemNatureName, pkl.lrDate, pkl.site_Code FROM pkList pkl LEFT JOIN voucher_Type vt ON vt.v_Type = pkl.v_Type LEFT JOIN subGroup sg ON sg.subCode = pkl.partyCode LEFT JOIN subGroup sgtp ON sgtp.subCode = pkl.transporterCode LEFT JOIN subGroup sgdt on sgdt.subCode = pkl.deliveryTo LEFT JOIN city dc on dc.cityCode = pkl.deliveryCity left join vehicleTypeMast vtm on vtm.code = pkl.vehicleTypeCode left join itemNature itn on itn.code = pkl.itemNature LEFT JOIN ( SELECT sbd.pklDocId FROM sBill sb LEFT JOIN sBillDetail sbd ON sbd.docId = sb.docId LEFT JOIN voucher_Type vt ON vt.v_Type = sb.v_Type WHERE vt.category IN (:SI) AND sbd.pklDocId IS NOT NULL AND sb.docId <> '' AND ISNULL(sb.cancelledBy,'') = '' GROUP BY sbd.pklDocId ) vSI ON vSI.pklDocId = pkl.docId WHERE vt.category IN (:vCat) AND vSI.pklDocId IS NULL AND pkl.site_Code = :site_Code AND isNull(pkl.approvedBy, :blank) <> :blank {…}`
- Params: `:vCat` ← `vCat`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:blank` ← `""`; `:SI` ← `pvc.SALES_INVOICE`; `:fromv_Date` ← `DmServer.convertDate(param.getFromDate())`; `:tov_Date` ← `DmServer.convertDate(param.getToDate())`
- Other calls: `TransportMode.getViaKey`

### `getCardDetail`
`public DmResult<CardDetail_Mdl> getCardDetail()`

- SQL 1: `select count( distinct pkl.docId) as cnt , sum(case when DATEDIFF(day, pkl.v_Date, CAST(:currentDate AS date)) <= {…} then 1 else 0 end) as low, sum(case when DATEDIFF(day, pkl.v_Date, CAST(:currentDate AS date)) > {…} and DATEDIFF(day, pkl.v_Date, CAST(:currentDate AS date)) <= {…} then 1 else 0 end) as medium, sum(case when DATEDIFF(day, pkl.v_Date, CAST(:currentDate AS date)) >= {…} then 1 else 0 end) as high from pkList as pkl left join voucher_type as vt on pkl.v_Type = vt.v_Type LEFT JOIN ( SELECT sbd.pklDocId FROM sBill sb LEFT JOIN sBillDetail sbd ON sbd.docId = sb.docId LEFT JOIN voucher_Type vt ON vt.v_Type = sb.v_Type WHERE vt.category IN (:SI) AND sbd.pklDocId IS NOT NULL AND sb.docId <> '' AND ISNULL(sb.cancelledBy,'') = '' GROUP BY sbd.pklDocId ) vSI ON vSI.pklDocId = pkl.docId Where vt.category IN (:vCat) AND vSI.pklDocId IS NULL and pkl.site_Code = :site_Code AND isNull(pkl.approvedBy,'') <> ''`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:currentDate` ← `DmServer.getServerTimestamp()`; `:vCat` ← `vCat`; `:SI` ← `pvc.SALES_INVOICE`
- Other calls: `HIGH_PRIORITYDAYS.getDays`, `LOW_PRIORITYDAYS.getDays`, `MEDIUM_PRIORITYDAYS.getDays`

### `getTxtHPackingListNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHPackingListNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select pkl.docId, pkl.recId as pklNo, pkl.v_No, vt.category, pkl.v_Date from pkList as pkl LEFT JOIN voucher_Type as vt on pkl.v_Type = vt.v_Type LEFT JOIN ( SELECT sbd.pklDocId FROM sBill sb LEFT JOIN sBillDetail sbd ON sbd.docId = sb.docId LEFT JOIN voucher_Type vt ON vt.v_Type = sb.v_Type WHERE vt.category IN (:SI) AND sbd.pklDocId IS NOT NULL AND sb.docId <> '' AND ISNULL(sb.cancelledBy,'') = '' GROUP BY sbd.pklDocId ) vSI ON vSI.pklDocId = pkl.docId WHERE vSI.pklDocId IS NULL AND pkl.site_Code IN ( {…} :site_Code {…} ) {…}`
- Params: `:vCat` ← `vCat`; `:SI` ← `pvc.SALES_INVOICE`

### `getTxtHSupplier`
`public DmResultPageing<List<TxtHSupplier_Mdl>> getTxtHSupplier(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHDeliveryCity`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDeliveryCity(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select ct.cityCode, ct.cityName, ct.stateCode, sm.name as stateName from city ct left join stateMast sm on ct.stateCode = sm.code`

### `getTxtHDeliveryTo`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHDeliveryTo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as supplierName , sg.manualCode , sg.guiConfigCode , sg.zone , zm.name as zoneName , sg.DueDays , c.cityName , c.cityCode , c.districtCode , c.stateCode , c.countryCode , cd.cityName AS districtName, sm.name as stateName, sm.numericCode as stateNumericCode , ct.name as countryName, ct.gstCode AS gstCountryCode , sg.partyType, sg.gstin, sg.gstinDate , sg.legalName, sg.tradeName, sg.add1, sg.add2, sg.pin, sg.mobile, sg.phone, sg.eMail , sg.pan, sg.tan, sg.isTcsActive, sg.tcs_Catg , tc.name as tcsCategoryName , sg.priceListCode as partyPlCode , sgn.manualCode AS partyNature FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature LEFT JOIN zoneMast zm ON zm.code = sg.zone LEFT JOIN city cd ON cd.cityCode = c.districtCode LEFT JOIN stateMast sm ON sm.code = c.stateCode LEFT JOIN country ct ON ct.code = c.countryCode LEFT JOIN tdsCat tc ON tc.code = sg.tcs_Catg WHERE sg.isActive = 1 AND sgsl.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`


### `getSupplierDetails`
`private List<TxtHSupplier_Mdl> getSupplierDetails(DmHelper helper, boolean blnToEndOfFile, Connection cnTemp) throws Exception`

- SQL 1: `SELECT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_type = acf.v_Type WHERE vt.category IN (:vCat)`
- SQL 2: `SELECT sg.subCode AS code , sg.name , sg.manualCode , c.cityName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( {…} ) AND sg.isActive = 1 AND sgsl.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vCat` ← `vCat`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmChart.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `paperbiz.dashBoard.packingList.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.dashboardCardStrip.client`, `paperbiz.selfToolkit.dashboardCardStrip.shared`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
