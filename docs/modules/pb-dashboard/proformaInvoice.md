# pb-dashboard / proformaInvoice

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/dashBoard/src/proformaInvoice/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHPINo, getTxtHDeliveryCity, getTxtHSite, getTxtHSupplierPT
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `ProformaInvoice`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmGridPaging | `grdProformaInvoiceDetail` | `GridProformaInvoice_PrpAcs, gridRows, false` |

Other widgets (not in tab list): `txtSearchValue` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `grdProformaInvoiceDetail`:  [Text, w30]; PI. No [Text, w120, piNo]; Type [Text, w50, voucherShortName]; PI. Date [Text, w80, replace(convert(varchar, piDate, 106), ' ','-')]; Ref. No [Text, w120, refNo]; Ref. Date [Text, w80, replace(convert(varchar, refDate, 106), ' ','-')]; Valid Upto [Text, w80, replace(convert(varchar, validUpto, 106), ' ','-')]; Item Nature [Text, w80, itemNatureName]; Bifurcated [Text, w70, isBifurcateServiceName]; Party Name [Text, w250, partyName]; Consignee Name [Text, w250, consigneeName]; Delivery to [Text, w250, deliveryToName]; Delivery City [Text, w150, deliveryCityName]; Sales Representative Name [Text, w200, salesRepresentativeName]

**Lookup / suggestion / find sources (server RPC called):**
- `grdProformaInvoiceDetail` → `getFillRecords`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`settingPopup.addCallBack` → `onOk()`
```java
searchValue_Mdl = svm;
grdProformaInvoiceDetail.getDmGrid().commitChanges();
grdProformaInvoiceDetail.reQuery();
```

`grdProformaInvoiceDetail.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnToEndOfFile).getFillRecords(searchValue_Mdl, helper, blnToEndOfFile, new AsyncCallback<DmResultPag
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdProformaInvoiceDetail_Mdl>> result) {
if (result.isStatus()) {
grdProformaInvoiceDetail.setData(result.getData(), result.getPaging());
if (dmCallBack != null) {
DmResult<Object> dmrTemp = new DmResult<>();
dmrTemp.setStatus(true);
dmCallBack.result(dmrTemp);
...
```

`grdProformaInvoiceDetail.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
grdProformaInvoiceDetail.setData(result.getData(), result.getPaging());
if (dmCallBack != null) {
DmResult<Object> dmrTemp = new DmResult<>();
dmrTemp.setStatus(true);
dmCallBack.result(dmrTemp);
dmCallBack = null;
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`grdProformaInvoiceDetail.addCallBack` → `getParam()`
```java
return searchValue_Mdl;
```
- Confirm: "Are you sure? You want to close."
- Alert: "Please select the Order."

### Screen `Setting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | From Date | DmTextBox | `txtFromDate` | `15, false` |
| 2 | To Date | DmTextBox | `txtToDate` | `15, false` |
| 3 | PI No. | DmTextHelpMulti | `txtHPINo` | `TxtHPINo_PrpAcs, 7` |
| 4 | Party | DmTextHelpMulti | `txtHParty` | `TxtHSupplierName_PrpAcs, 7` |
| 5 | Consignee | DmTextHelpMulti | `txtHConsignee` | `TxtHConsignee_PrpAcs, 7` |
| 6 | Delivery To | DmTextHelpMulti | `txtHDeliveryTo` | `TxtHDeliveryTo_PrpAcs, 7` |
| 7 | To Place | DmTextHelpMulti | `txtHDeliveryCity` | `TxtHDelivryCity_PrpAcs, 4` |
| 8 | Site | DmTextHelpMulti | `txtHSite` | `KeyField_PrpAcs, 5, true` |

**Grid / popup columns** (header, width, db field):
- `txtHPINo`: PI No. [Text, w120, recId]; PI Type [Text, w90, voucherShortName]; PI Date [Text, w90, replace(convert(varchar, piDate, 106), ' ','-')]
- `txtHParty`: Name [Text, w220, supplierName]; Manual Code [Text, w100, manualCode]; City Name [Text, w100, cityName]
- `txtHConsignee`: Name [Text, w220, supplierName]; Manual Code [Text, w100, manualCode]; City Name [Text, w100, cityName]
- `txtHDeliveryTo`: Name [Text, w220, supplierName]; Manual Code [Text, w100, manualCode]; City Name [Text, w100, cityName]
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

`txtHPINo.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHPINo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHPINo_
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHPINo).show();
public void onSuccess(DmResultPageing<List<TxtHPINo_Mdl>> result) {
if (result.isStatus()) {
txtHPINo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPINo).show();
```

`txtHPINo.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHPINo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPINo).show();
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

`txtHConsignee.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHSupplierPT(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Txt
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

`txtHDeliveryTo.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHSupplierPT(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Txt
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
| client/ProformaInvoice.java | 496 |
| client/ProformaInvoiceCard.java | 129 |
| client/ProformaInvoice_Dsg.java | 314 |
| client/Setting.java | 350 |
| client/Setting_Dsg.java | 340 |
| server/ProformaInvoice_SrvImpl.java | 606 |
| shared/DefaultValue_Mdl.java | 41 |
| shared/GrdProformaInvoiceDetail_Mdl.java | 275 |
| shared/ProformaInvoice_Srv.java | 28 |
| shared/ProformaInvoice_SrvAsync.java | 30 |
| shared/SearchValue_Mdl.java | 149 |
| shared/TxtHPINo_Mdl.java | 33 |
| shared/TxtHSupplierPT_Mdl.java | 32 |

## UI labels / columns (client)
`Action`, `From Date`, `To Date`, `PI No.`, `Party`, `Consignee`, `Delivery To`, `To Place`, `Site`

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
| String | PICode |
| String | PIName |
| String | site |
| String | siteName |

### TxtHPINo_Mdl (extends `KeyField implements Serializable,IsSerializable`)
| type | field |
|---|---|
| String | PIType |
| String | PIDate |

### TxtHSupplierPT_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityName |

### GrdProformaInvoiceDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | v_SNO |
| String | isView |
| String | piNo |
| String | voucherShortName |
| String | piDate |
| String | refNo |
| String | refDate |
| String | validUpto |
| String | siteCode |
| String | itemNatureCode |
| String | itemNatureName |
| String | itcCode |
| String | itcName |
| String | partyCode |
| String | partyName |
| String | locationCode |
| String | locationName |
| String | consigneeCode |
| String | consigneeName |
| String | deliveryToCode |
| String | deliveryToName |
| String | deliveryDate |
| String | deliveryCityCode |
| String | deliveryCityName |
| String | salesRepresentativeCode |
| String | salesRepresentativeName |
| String | isBifurcateServiceCode |
| String | isBifurcateServiceName |

## RPC interface
| method | params | returns |
|---|---|---|
| getCardDetail |  | DmResult<CardDetail_Mdl> |
| getTxtHPINo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHPINo_Mdl>> |
| getTxtHSupplierPT | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHSupplierPT_Mdl>> |
| getTxtHDeliveryCity | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| getFillRecords | SearchValue_Mdl searchValueMdl, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdProformaInvoiceDetail_Mdl>> |

## Server `ProformaInvoice_SrvImpl.java` (606 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.xNull`

### `getCardDetail`
`public DmResult<CardDetail_Mdl> getCardDetail()`

- SQL 1: `select count(pi.docId) as cnt , sum(case when DATEDIFF(day, pi.v_Date, CAST(:currentDate AS date)) <= {…} then 1 else 0 end) as low, sum(case when DATEDIFF(day, pi.v_Date, CAST(:currentDate AS date)) > {…} and DATEDIFF(day, pi.v_Date, CAST(:currentDate AS date)) <= {…} then 1 else 0 end) as medium, sum(case when DATEDIFF(day, pi.v_Date, CAST(:currentDate AS date)) >= {…} then 1 else 0 end) as high FROM ProformaInvoice as pi LEFT JOIN voucher_type AS vt on pi.v_Type = vt.v_Type LEFT JOIN ( SELECT sod.piDocId FROM sOrder so LEFT JOIN sOrderDetail sod ON sod.docId = so.docId LEFT JOIN voucher_Type vt ON vt.v_Type = so.v_Type WHERE vt.category IN ('SO') AND sod.piDocId IS NOT NULL AND so.docId <> '' GROUP BY sod.piDocId ) vSo ON vSo.piDocId = pi.docId WHERE vso.piDocId IS NULL AND vt.category IN (:PI) AND pi.site_Code = :site_Code AND isNull(pi.approvedBy,'') <> ''`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:currentDate` ← `DmServer.getServerTimestamp()`; `:PI` ← `pvc.PROFORMA_INVOICE`
- Other calls: `HIGH_PRIORITYDAYS.getDays`, `LOW_PRIORITYDAYS.getDays`, `MEDIUM_PRIORITYDAYS.getDays`

### `getTxtHPINo`
`public DmResultPageing<List<TxtHPINo_Mdl>> getTxtHPINo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT pi.docId , pi.recId , vt.short_Name as voucherShortName , pi.v_Date AS piDate FROM proformaInvoice pi LEFT JOIN voucher_Type vt On vt.v_Type = pi.v_Type LEFT JOIN siteMast sm ON sm.code = pi.site_Code LEFT JOIN ( SELECT sod.piDocId FROM sOrder so LEFT JOIN sOrderDetail sod ON sod.docId = so.docId LEFT JOIN voucher_Type vt ON vt.v_Type = so.v_Type WHERE vt.category IN (:SO) AND sod.piDocId IS NOT NULL AND so.docId <> '' GROUP BY sod.piDocId ) vSo ON vSo.piDocId = pi.docId WHERE vso.piDocId IS NULL AND vt.category IN (:PI) AND ISNULL(pi.approvedBy,'')<> ''`
- Params: `:PI` ← `pvc.PROFORMA_INVOICE`; `:SO` ← `pvc.SALES_ORDER`

### `getTxtHDeliveryCity`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDeliveryCity(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select ct.cityCode, ct.cityName, ct.stateCode, sm.name as stateName from city ct left join stateMast sm on ct.stateCode = sm.code`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHSupplierPT`
`public DmResultPageing<List<TxtHSupplierPT_Mdl>> getTxtHSupplierPT(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as supplierName , sg.manualCode , c.cityName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( SELECT DISTINCT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_Type = acf.v_Type WHERE vt.category IN (' {…} ') ) AND sg.isActive = 1 AND sgsl.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`

- SQL 1: `select sm.code,sm.name AS site from siteMast sm where code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getFillRecords`
`public DmResultPageing<List<GrdProformaInvoiceDetail_Mdl>> getFillRecords(SearchValue_Mdl param, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT pi.docId , pi.recId , pi.site_Code , pi.recId AS piNo , vt.short_Name as voucherShortName , pi.v_Date AS piDate , pi.refNo , pi.refDate , pi.validUpto , pi.v_No , pi.v_Prefix , pi.itemNature AS itemNatureCode , itn.name AS itemNatureName , sm.manualCode AS siteManualCode , pi.partyCode , ptip.name AS partyName , pi.locationCode , lm.name AS locationName , pi.isItc AS isItcCode , IIF(pi.isItc = 1, 'Yes', 'No') AS isItcName , pi.consignee as consigneeCode , ptic.name as consigneeName , pi.deliveryTo as deliveryToCode , ptidt.name as deliveryToName , pi.deliveryCity AS deliveryCityCode , dc.cityName as deliveryCityName , pi.salesmanCode AS salesRepresentativeCode , sgso.name AS salesRepresentativeName , pi.isBifurcateService , IIF(pi.isBifurcateService = 1, 'Yes', 'No') AS isBifurcateServiceName FROM proformaInvoice pi LEFT JOIN locationMast lm ON lm.code = pi.locationCode LEFT JOIN itemNature itn ON itn.code = pi.itemNature LEFT JOIN voucher_Type vt On vt.v_Type = pi.v_Type LEFT JOIN siteMast sm ON sm.code = pi.site_Code LEFT JOIN partyTransInfo ptip on ptip.docId = pi.docId AND ptip.recordType = :partyTypeRecordType LEFT JOIN partyTransInfo ptic on ptic.docId = pi.docId AND ptic.recordType = :consigneeRecordType LEFT JOIN partyTransInfo ptidt on ptidt.docId = pi.docId AND ptidt.recordType = :deliveryToRecordType LEFT JOIN city dc on dc.cityCode = pi.deliveryCity LEFT JOIN subGroup sgso On sgso.subCode = pi.salesmanCode LEFT JOIN ( SELECT sod.piDocId FROM sOrder so LEFT JOIN sOrderDetail sod ON sod.docId = so.docId LEFT JOIN voucher_Type vt ON vt.v_Type = so.v_Type WHERE vt.category IN (:SO) AND sod.piDocId IS NOT NULL AND so.docId <> '' GROUP BY sod.piDocId ) vSo ON vSo.piDocId = pi.docId WHERE vso.piDocId IS NULL AND vt.category IN (:PI) AND ISNULL(pi.approvedBy,'') <> '' {…}`
- Params: `:SO` ← `pvc.SALES_ORDER`; `:PI` ← `pvc.PROFORMA_INVOICE`; `:fromv_Date` ← `DmServer.convertDate(param.getFromDate())`; `:tov_Date` ← `DmServer.convertDate(param.getToDate())`
- Other calls: `CONSIGNEE.getCode`, `DELIVERY_TO_PARTY.getCode`, `PARTY.getCode`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `paperbiz.dashBoard.proformaInvoice.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.dashboardCardStrip.client`, `paperbiz.selfToolkit.dashboardCardStrip.shared`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
