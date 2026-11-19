# pb-dashboard / purchaseBill

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/dashBoard/src/purchaseBill/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHPBillNo, getTxtHSupplierPT, getTxtHDeliveryToPT, getTxtHDeliveryCity, getTxtHSite
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `PurchaseBill`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmGridPaging | `grdPurchaseBillDetail` | `GridPurchaseBill_PrpAcs, gridRows, false` |

Other widgets (not in tab list): `txtSearchValue` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `grdPurchaseBillDetail`:  [Text, w30]; Purchase Bill No [Text, w180, voucherNo]; Purchase Bill Date [Text, w100, replace(convert(varchar, voucherDate, 106), ' ','-')]; V Type [Text, w60, voucherShortName]; Bill No. [Text, w120, partyBillNo]; Bill. Date [Text, w100, replace(convert(varchar, partyBillDate, 106), ' ','-')]; Direct Supply [Text, w100, directSupplyName]; Bif. Service [Text, w80, bifurcationName]; Item Nature [Text, w80, itemNatureName]; Supplier Name [Text, w250, SupplierName]; Supplie City [Text, w150, supplieCityName]; Direct Supply [Text, w150, directSupplyName]; CalOn  [Text, w200, calOnName]; Customer Name [Text, w250, customerName]; Customer City [Text, w150, customerCityName]; Delivery to [Text, w250, deliveryToName]; Delivery City [Text, w150, deliveryCityName]; ITC [Text, w120, isItcName]; RCM [Text, w120, rcmName]; Amount [Text, w120, amount]

**Lookup / suggestion / find sources (server RPC called):**
- `grdPurchaseBillDetail` → `getFillRecords`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`settingPopup.addPurchaseFinalizationCallBack` → `onOk()`
```java
searchValue_Mdl = svm;
grdPurchaseBillDetail.getDmGrid().commitChanges();
grdPurchaseBillDetail.reQuery();
```

`grdPurchaseBillDetail.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnToEndOfFile).getFillRecords(searchValue_Mdl, helper, blnToEndOfFile, new AsyncCallback<DmResultPag
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdPurchaseBillDetail_Mdl>> result) {
if (result.isStatus()) {
grdPurchaseBillDetail.setData(result.getData(), result.getPaging());
if (dmCallBack != null) {
DmResult<Object> dmrTemp = new DmResult<>();
dmrTemp.setStatus(true);
dmCallBack.result(dmrTemp);
...
```

`grdPurchaseBillDetail.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
grdPurchaseBillDetail.setData(result.getData(), result.getPaging());
if (dmCallBack != null) {
DmResult<Object> dmrTemp = new DmResult<>();
dmrTemp.setStatus(true);
dmCallBack.result(dmrTemp);
dmCallBack = null;
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`grdPurchaseBillDetail.addCallBack` → `getParam()`
```java
return searchValue_Mdl;
```
- Confirm: "Are you sure? You want to close."
- Alert: "Direct Supply is No Sales Invoice can't be make "
- Alert: "Please select the Bill."
- Alert: "Please select the Bill."

### Screen `Setting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | From Date | DmTextBox | `txtFromDate` | `15, false` |
| 2 | To Date | DmTextBox | `txtToDate` | `15, false` |
| 3 | PBill No. | DmTextHelpMulti | `txtHPBillNo` | `TxtHPINo_PrpAcs, 7` |
| 4 | Supplier | DmTextHelpMulti | `txtHParty` | `TxtHSupplierName_PrpAcs, 7` |
| 5 | Customer | DmTextHelpMulti | `txtHCustomer` | `TxtHConsignee_PrpAcs, 7` |
| 6 | Delivery To | DmTextHelpMulti | `txtHDeliveryTo` | `TxtHDeliveryTo_PrpAcs, 7` |
| 7 | To Place | DmTextHelpMulti | `txtHDeliveryCity` | `TxtHDelivryCity_PrpAcs, 4` |
| 8 | Site | DmTextHelpMulti | `txtHSite` | `KeyField_PrpAcs, 5, true` |

**Grid / popup columns** (header, width, db field):
- `txtHPBillNo`: Voucher No. [Text, w200, recId]; Type [Text, w90, short_Name]; PBill Date [Text, w90, replace(convert(varchar, v_Date, 106), ' ','-')]; Party Bill No [Text, w150, partyBillNo]; Party Bill Date [Text, w90, partyBillDate]
- `txtHParty`: Name [Text, w220, supplierName]; Manual Code [Text, w100, manualCode]; City Name [Text, w100, cityName];  Nature [Text, w100, supplierNature]
- `txtHCustomer`: Name [Text, w220, supplierName]; Manual Code [Text, w100, manualCode]; City Name [Text, w100, cityName];  Nature [Text, w100, supplierNature]
- `txtHDeliveryTo`: Name [Text, w220, supplierName]; Manual Code [Text, w100, manualCode]; City Name [Text, w100, cityName];  Nature [Text, w100, supplierNature]
- `txtHDeliveryCity`: City [Text, w100, cityName]; State [Text, w100, stateName]
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

`txtHPBillNo.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHPBillNo( helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHP
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHPBillNo).show();
public void onSuccess(DmResultPageing<List<TxtHPBillNo_Mdl>> result) {
if (result.isStatus()) {
txtHPBillNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPBillNo).show();
```

`txtHPBillNo.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHPBillNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPBillNo).show();
```

`txtHParty.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHSupplierPT(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Txt
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

`txtHCustomer.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHSupplierPT(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Txt
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHCustomer).show();
public void onSuccess(DmResultPageing<List<TxtHSupplierPT_Mdl>> result) {
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

`txtHDeliveryTo.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHDeliveryToPT(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<T
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDeliveryTo).show();
public void onSuccess(DmResultPageing<List<TxtHSupplierPT_Mdl>> result) {
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
SrvAsync.invokeRPC(blnCallBackWait).getTxtHDeliveryCity(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<K
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
- Alert: "Site is mandatory."

## Files
| file | lines |
|---|---|
| client/PurchaseBill.java | 596 |
| client/PurchaseBillCard.java | 129 |
| client/PurchaseBill_Dsg.java | 349 |
| client/Setting.java | 350 |
| client/Setting_Dsg.java | 358 |
| server/PurchaseBill_SrvImpl.java | 740 |
| shared/DefaultValue_Mdl.java | 41 |
| shared/GrdPurchaseBillDetail_Mdl.java | 356 |
| shared/PurchaseBill_Srv.java | 30 |
| shared/PurchaseBill_SrvAsync.java | 30 |
| shared/SearchValue_Mdl.java | 142 |
| shared/TxtHPBillNo_Mdl.java | 51 |
| shared/TxtHSupplierPT_Mdl.java | 41 |

## UI labels / columns (client)
`From Date`, `To Date`, `PBill No.`, `Supplier`, `Customer`, `Delivery To`, `To Place`, `Site`, `Action`

## Models (shared DTOs)
### DefaultValue_Mdl
| type | field |
|---|---|
| String | currentDate |
| String | currentSiteCode |
| String | currentSiteName |

### SearchValue_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | FromDate |
| String | ToDate |
| String | partyName |
| String | partyCode |
| String | consigneeCode |
| String | consigneeName |
| String | deliveryToCode |
| String | deliveryToName |
| String | deliveryCityCode |
| String | deliveryCityName |
| String | PBillCode |
| String | PBillName |
| String | site |
| String | siteName |

### TxtHSupplierPT_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityName |
| String | supplierNature |

### GrdPurchaseBillDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | isView |
| String | voucherNo |
| String | voucherDate |
| String | partyBill |
| String | partyBillDate |
| String | voucherShortName |
| String | supplierCode |
| String | supplierName |
| String | supplierCityCode |
| String | supplierCityName |
| String | supplierType |
| String | locationCode |
| String | locationName |
| String | directSupplyCode |
| String | directSupplyName |
| String | calOnCode |
| String | calOnName |
| String | customerCode |
| String | customerName |
| String | customerCityCode |
| String | customerCityName |
| String | deliveryToCode |
| String | deliveryToName |
| String | deliveryCityCode |
| String | deliveryCityName |
| String | dueDate |
| String | itemNatureCode |
| String | itemNatureName |
| String | bifurcationCode |
| String | bifurcationName |
| String | isItcCode |
| String | isItcName |
| String | rcmCode |
| String | rcmName |
| String | amount |
| String | sitecode |

### TxtHPBillNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | PBillType |
| String | PBillDate |
| String | PartyBillNo |
| String | PartyBillDate |

## RPC interface
| method | params | returns |
|---|---|---|
| getCardDetail |  | DmResult<CardDetail_Mdl> |
| getTxtHPBillNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHPBillNo_Mdl>> |
| getTxtHSupplierPT | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHSupplierPT_Mdl>> |
| getTxtHDeliveryToPT | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHSupplierPT_Mdl>> |
| getTxtHDeliveryCity | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| getFillRecords | SearchValue_Mdl searchValueMdl, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdPurchaseBillDetail_Mdl>> |

## Server `PurchaseBill_SrvImpl.java` (740 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.xNull`

### `getCardDetail`
`public DmResult<CardDetail_Mdl> getCardDetail()`

- SQL 1: `select count(pb.docId) as cnt , sum(case when DATEDIFF(day, pb.v_Date, CAST(:currentDate AS date)) <= {…} then 1 else 0 end) as low, sum(case when DATEDIFF(day, pb.v_Date, CAST(:currentDate AS date)) > {…} and DATEDIFF(day, pb.v_Date, CAST(:currentDate AS date)) <= {…} then 1 else 0 end) as medium, sum(case when DATEDIFF(day, pb.v_Date, CAST(:currentDate AS date)) >= {…} then 1 else 0 end) as high FROM pBill as pb LEFT JOIN voucher_type AS vt on pb.v_Type = vt.v_Type LEFT JOIN ( SELECT pfd.pbDocId FROM pFinalization as pf LEFT JOIN pFinalizationDetail as pfd ON pfd.docId = pf.docId LEFT JOIN voucher_Type vt ON vt.v_Type = pf.v_Type WHERE vt.category IN ('PF','PFU') AND ISNULL(pf.cancelledBy, '') = '' GROUP BY pfd.pbDocId ) vPf ON vPf.pbDocId = pb.docId LEFT JOIN ( SELECT sbd.pbDocId FROM sBill AS sb LEFT JOIN sBillDetail AS sbd ON sbd.docId = sb.docId LEFT JOIN voucher_Type vt ON vt.v_Type = sb.v_Type WHERE vt.category IN ('SI') AND ISNULL(sb.cancelledBy, '') = '' AND sbd.pbDocId IS NOT NULL GROUP BY sbd.pbDocId ) vSb ON vSb.pbDocId = pb.docId WHERE CASE WHEN pb.isDirectSupply = 1 THEN CASE WHEN vPf.pbDocId IS NULL OR vSb.pbDocId IS NULL THEN 1 ELSE 0 END ELSE CASE WHEN vPf.pbDocId IS NULL THEN 1 ELSE 0 END END = 1 AND vt.category IN (:PB,:PBU) AND pb.site_Code = :site_Code AND isNull(pb.approvedBy,'') <> ''`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:currentDate` ← `DmServer.getServerTimestamp()`; `:PB` ← `pvc.PURCHASE_BILL`; `:PBU` ← `pvc.PURCHASE_BILL_UNREGISTERED`
- Other calls: `HIGH_PRIORITYDAYS.getDays`, `LOW_PRIORITYDAYS.getDays`, `MEDIUM_PRIORITYDAYS.getDays`

### `getTxtHPBillNo`
`public DmResultPageing<List<TxtHPBillNo_Mdl>> getTxtHPBillNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT pb.docId , pb.recId , pb.v_Date , vt.short_Name , pb.partyBillNo , pb.partyBillDate from pBill pb LEFT JOIN voucher_Type vt ON vt.v_Type = pb.v_Type LEFT JOIN subGroup sg ON sg.subcode = pb.subCode LEFT JOIN ( SELECT pfd.pbDocId FROM pFinalization as pf LEFT JOIN pFinalizationDetail as pfd ON pfd.docId = pf.docId LEFT JOIN voucher_Type vt ON vt.v_Type = pf.v_Type WHERE vt.category IN (:PF,:PFU) AND ISNULL(pf.cancelledBy, '') = '' GROUP BY pfd.pbDocId ) vPf ON vPf.pbDocId = pb.docId LEFT JOIN ( SELECT sbd.pbDocId FROM sBill AS sb LEFT JOIN sBillDetail AS sbd ON sbd.docId = sb.docId LEFT JOIN voucher_Type vt ON vt.v_Type = sb.v_Type WHERE vt.category IN ('SI') AND ISNULL(sb.cancelledBy, '') = '' AND sbd.pbDocId IS NOT NULL GROUP BY sbd.pbDocId ) vSb ON vSb.pbDocId = pb.docId WHERE CASE WHEN pb.isDirectSupply = 1 THEN CASE WHEN vPf.pbDocId IS NULL OR vSb.pbDocId IS NULL THEN 1 ELSE 0 END ELSE CASE WHEN vPf.pbDocId IS NULL THEN 1 ELSE 0 END END = 1 AND vt.category IN (:PB,:PBU) AND pb.site_Code = ' {…} ' AND isNull(pb.approvedBy,'') <> ''`
- Params: `:PF` ← `pvc.PURCHASE_FINALIZATION`; `:PFU` ← `pvc.PURCHASE_FINALIZATION_UNREGISTERED`; `:PB` ← `pvc.PURCHASE_BILL`; `:PBU` ← `pvc.PURCHASE_BILL_UNREGISTERED`

### `getTxtHSupplierPT`
`public DmResultPageing<List<TxtHSupplierPT_Mdl>> getTxtHSupplierPT(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as supplierName , sg.manualCode , c.cityName ,sgn.name AS supplierNature FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( SELECT DISTINCT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_Type = acf.v_Type WHERE vt.category IN (' {…} ') ) AND sg.isActive = 1 AND sgsl.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHDeliveryToPT`
`public DmResultPageing<List<TxtHSupplierPT_Mdl>> getTxtHDeliveryToPT(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as supplierName , sg.manualCode , c.cityName ,sgn.name AS supplierNature FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.manualCode IN ( 'CT','GD','PT' ) AND sg.isActive = 1 AND sgsl.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHDeliveryCity`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDeliveryCity(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select ct.cityCode, ct.cityName, ct.stateCode, sm.name as stateName from city ct left join stateMast sm on ct.stateCode = sm.code`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`

- SQL 1: `select sm.code,sm.name AS site from siteMast sm where code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getFillRecords`
`public DmResultPageing<List<GrdPurchaseBillDetail_Mdl>> getFillRecords(SearchValue_Mdl param, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type, pb.docId ,pb.recId AS voucherNo , pb.v_Date AS voucherDate , pb.partyBillNo , pb.partyBillDate , vt.short_Name as voucherShortName , pb.subcode AS supplierCode , sgs.name AS supplierName , sgs.cityCode AS supplieCityCode , c.cityName AS supplieCityName , sgs.partyType AS supplierType , sgs.DueDays as dueDays , pb.customerCode , sgc.name AS customerName , pb.customerCode AS customerCityCode , cc.cityName AS customerCityName , pb.deliveryToCode , sgd.name AS deliveryToName , pb.deliveryToCode AS deliveryToCityCode , cd.cityName AS deliveryToCityName , pb.isBifurcateService AS bifurcationCode , IIF(pb.isBifurcateService = 1, 'Yes', 'No') AS bifurcationName , pb.isDirectSupply AS directSupplyCode , IIF(pb.isDirectSupply = 1, 'Yes', 'No') AS directSupplyName , calcOn AS calcOnCode , IIF(pb.calcOn = 1, 'Accept Quantity', 'Document Quantity') AS calcOnName , pb.itemNature AS itemNatureCode , itn.name AS itemNatureName , pb.locationCode , lm.name AS locationName , pb.isItc AS isItcCode , pb.site_Code , IIF(pb.isItc = 1, 'Yes', 'No') AS isItcName , pb.rcmApplicable AS rcmCode , IIF(pb.rcmApplicable = 1, 'Yes', 'No') AS rcmName , pb.oh_Amt_Net AS amount , CASE WHEN vPf.pbDocId IS NOT NULL THEN 1 WHEN vSb.pbDocId IS NOT NULL THEN 2 ELSE 0 END AS billStatus FROM pBill pb LEFT JOIN voucher_Type vt On vt.v_Type = pb.v_Type LEFT JOIN subGroup sgs ON pb.subcode = sgs.subCode LEFT JOIN city c ON c.cityCode = sgs.cityCode LEFT JOIN subGroup sgc ON sgc.subcode = pb.customerCode LEFT JOIN city cc ON cc.cityCode = sgc.cityCode LEFT JOIN subGroup sgd ON sgd.subCode = pb.deliveryToCode LEFT JOIN city cd ON cd.cityCode = sgd.cityCode LEFT JOIN itemNature itn ON itn.code = pb.itemNature LEFT JOIN locationMast lm ON lm.code = pb.locationCode LEFT JOIN ( SELECT pfd.pbDocId FROM pFinalization as pf LEFT JOIN pFinalizationDetail as pfd ON pfd.docId = pf.docId LEFT JOIN voucher_Type vt ON vt.v_Type = pf.v_Type WHERE vt.category IN ('PF','PFU') AND ISNULL(pf.cancelledBy, '') = '' GROUP BY pfd.pbDocId ) vPf ON vPf.pbDocId = pb.docId LEFT JOIN ( SELECT sbd.pbDocId FROM sBill AS sb LEFT JOIN sBillDetail AS sbd ON sbd.docId = sb.docId LEFT JOIN voucher_Type vt ON vt.v_Type = sb.v_Type WHERE vt.category IN ('SI') AND ISNULL(sb.cancelledBy, '') = '' AND sbd.pbDocId IS NOT NULL GROUP BY sbd.pbDocId ) vSb ON vSb.pbDocId = pb.docId WHERE CASE WHEN pb.isDirectSupply = 1 THEN CASE WHEN vPf.pbDocId IS NULL OR vSb.pbDocId IS NULL THEN 1 ELSE 0 END ELSE CASE WHEN vPf.pbDocId IS NULL THEN 1 ELSE 0 END END = 1 AND vt.category IN (:PB,:PBU) AND ISNULL(pb.approvedBy,'')<> '' {…}`
- Params: `:PB` ← `pvc.PURCHASE_BILL`; `:PBU` ← `pvc.PURCHASE_BILL_UNREGISTERED`; `:fromv_Date` ← `DmServer.convertDate(param.getFromDate())`; `:tov_Date` ← `DmServer.convertDate(param.getToDate())`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `paperbiz.dashBoard.purchaseBill.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.dashboardCardStrip.client`, `paperbiz.selfToolkit.dashboardCardStrip.shared`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
