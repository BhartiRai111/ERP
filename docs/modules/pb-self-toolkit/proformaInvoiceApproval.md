# pb-self-toolkit / proformaInvoiceApproval

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/proformaInvoiceApproval/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHStatus, getTxtHPINo, getTxtHSupplierPT, getTxtHDeliveryCity
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `ProformaInvoiceApproval`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmGridPaging | `dmGrd` | `Grd_PrpAcs, gridRows, false` |

**Grid / popup columns** (header, width, db field):
- `dmGrd`:  [Text, w30];  [Text, w25]; PI. No [Text, w120, piNo]; Type [Text, w50, voucherShortName]; PI. Date [Text, w80, replace(convert(varchar, piDate, 106), ' ','-')]; Ref. No [Text, w120, refNo]; Ref. Date [Text, w80, replace(convert(varchar, refDate, 106), ' ','-')]; Valid Upto [Text, w80, replace(convert(varchar, validUpto, 106), ' ','-')]; Item Nature [Text, w70, itemNatureName]; Bifurcated [Text, w70, isBifurcateServiceName]; Party Name [Text, w250, partyName]; Consignee Name [Text, w250, consigneeName]; Delivery to [Text, w250, deliveryToName]; Delivery City [Text, w150, deliveryCityName]; Sales Representative Name [Text, w200, salesRepresentativeName]; Approved By [Text, w130, approvedBy]

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
pfInvoiceApp_Mdl = pfInvoiceApl_Mdl;
dmGrd.getDmGrid().commitChanges();
dmGrd.reQuery();
```

`dmGrd.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(true).getFillRecords(param, helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<GrdProform
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdProformaInvoiceApproval_Mdl>> result) {
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
return pfInvoiceApp_Mdl;
```
- Confirm: "Are you sure? You want to close."

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
| 7 | Delivery City | DmTextHelpMulti | `txtHDeliveryCity` | `TxtHDelivryCity_PrpAcs, 4` |
| 8 | Status | DmTextHelp | `txtHStatus` | `KeyField_PrpAcs, 4, false` |

**Grid / popup columns** (header, width, db field):
- `txtHPINo`: PI No. [Text, w120, recId]; PI Type [Text, w90, short_Name]; PI Date [Text, w90, replace(convert(varchar, piDate, 106), ' ','-')]
- `txtHParty`: Name [Text, w220, supplierName]; Manual Code [Text, w100, manualCode]; City Name [Text, w100, cityName]
- `txtHConsignee`: Name [Text, w220, supplierName]; Manual Code [Text, w100, manualCode]; City Name [Text, w100, cityName]
- `txtHDeliveryTo`: Name [Text, w220, supplierName]; Manual Code [Text, w100, manualCode]; City Name [Text, w100, cityName]
- `txtHDeliveryCity`: City [Text, w100, cityName]; State [Text, w100, stateName]
- `txtHStatus`: Status [Text, w150, Name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHStatus` → `getTxtHStatus`

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
SrvAsync.invokeRPC(blnCallBackWait).getTxtHStatus(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFiel
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

`txtHPINo.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHPINo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHProfo
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHPINo).show();
public void onSuccess(DmResultPageing<List<TxtHProformaInvoice_Mdl>> result) {
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
- Alert: "From Date should be less than or equal to To Date."

## Files
| file | lines |
|---|---|
| client/ProformaInvoiceApproval.java | 366 |
| client/ProformaInvoiceApprovalCard.java | 132 |
| client/ProformaInvoiceApproval_Dsg.java | 268 |
| client/Setting.java | 342 |
| client/Setting_Dsg.java | 334 |
| server/ProformaInvoiceApproval_SrvImpl.java | 636 |
| shared/DefaultValue_Mdl.java | 40 |
| shared/GrdProformaInvoiceApproval_Mdl.java | 335 |
| shared/ProformaInvoiceApproval_Mdl.java | 292 |
| shared/ProformaInvoiceApproval_Srv.java | 27 |
| shared/ProformaInvoiceApproval_SrvAsync.java | 27 |
| shared/TxtHProformaInvoice_Mdl.java | 34 |
| shared/TxtHSupplierPT_Mdl.java | 31 |

## UI labels / columns (client)
`From Date`, `To Date`, `PI No.`, `Party`, `Consignee`, `Delivery To`, `Delivery City`, `Status`

## Models (shared DTOs)
### GrdProformaInvoiceApproval_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | isChecked |
| String | IsView |
| String | isStatus |
| String | v_SNO |
| String | piNo |
| String | voucherShortName |
| String | piDate |
| String | refNo |
| String | refDate |
| String | validUpto |
| String | siteCode |
| String | siteName |
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
| String | wtaCode |
| String | currentUser |
| String | approvedBy |
| String | approval |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | currentDate |
| String | currentSiteCode |
| String | currentSiteName |

### ProformaInvoiceApproval_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | FromDate |
| String | ToDate |
| String | piCode |
| String | piName |
| String | partyName |
| String | partyCode |
| String | consigneeCode |
| String | consigneeName |
| String | deliveryCityCode |
| String | deliveryCityName |
| String | deliveryToName |
| String | deliveryToCode |
| String | saleOrderDate |
| String | vSNo |
| String | StatusCode |
| String | StatusName |
| String | partyOrderNo |
| String | distributorCode |
| String | distributorName |
| String | saleOrderCode |
| String | saleOrderName |
| String | itemNatureCode |
| String | itemNatureName |
| String | itemCode |
| String | itemName |
| String | approvedBy |
| String | isStatus |
| String | approvedDateTime |
| String | wtaCode |
| List<GrdProformaInvoiceApproval_Mdl> | grdPurchaseApprovalEntry_Lst |

### TxtHSupplierPT_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityName |

### TxtHProformaInvoice_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | piType |
| String | piDate |

## RPC interface
| method | params | returns |
|---|---|---|
| getFillRecords | ProformaInvoiceApproval_Mdl param, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdProformaInvoiceApproval_Mdl>> |
| getTxtHPINo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHProformaInvoice_Mdl>> |
| getTxtHSupplierPT | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHSupplierPT_Mdl>> |
| getTxtHDeliveryCity | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHStatus | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| setApproval | String approvedBy, boolean isApprove, String docId, String caption | DmResult<String> |
| getCardDetail |  | DmResult<CardDetail_Mdl> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |

## Server `ProformaInvoiceApproval_SrvImpl.java` (636 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerDateTime`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.xNull`

### `getFillRecords`
`public DmResultPageing<List<GrdProformaInvoiceApproval_Mdl>> getFillRecords(ProformaInvoiceApproval_Mdl param, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT pi.docId , pi.recId , pi.site_Code , sm.name as stateName , pi.recId AS piNo , vt.short_Name as voucherShortName , pi.v_Date AS piDate , pi.refNo , pi.refDate , pi.validUpto , pi.v_No , pi.v_Prefix , pi.itemNature AS itemNatureCode , itn.name AS itemNatureName , sm.manualCode AS siteManualCode , pi.partyCode , ptip.name AS partyName , pi.locationCode , lm.name AS locationName , pi.isItc AS isItcCode , IIF(pi.isItc = 1, 'Yes', 'No') AS isItcName , pi.consignee as consigneeCode , ptic.name as consigneeName , pi.deliveryTo as deliveryToCode , ptidt.name as deliveryToName , pi.deliveryCity AS deliveryCityCode , dc.cityName as deliveryCityName , pi.salesmanCode AS salesRepresentativeCode , sgso.name AS salesRepresentativeName , pi.isBifurcateService,pi.approvedBy,pi.approvalDateTime,pi.wtaCode , IIF(pi.isBifurcateService = 1, 'Yes', 'No') AS isBifurcateServiceName FROM proformaInvoice pi LEFT JOIN locationMast lm ON lm.code = pi.locationCode LEFT JOIN itemNature itn ON itn.code = pi.itemNature LEFT JOIN voucher_Type vt On vt.v_Type = pi.v_Type LEFT JOIN siteMast sm ON sm.code = pi.site_Code LEFT JOIN partyTransInfo ptip on ptip.docId = pi.docId AND ptip.recordType = :partyTypeRecordType LEFT JOIN partyTransInfo ptic on ptic.docId = pi.docId AND ptic.recordType = :consigneeRecordType LEFT JOIN partyTransInfo ptidt on ptidt.docId = pi.docId AND ptidt.recordType = :deliveryToRecordType LEFT JOIN city dc on dc.cityCode = pi.deliveryCity LEFT JOIN subGroup sgso On sgso.subCode = pi.salesmanCode LEFT JOIN ( SELECT sod.piDocId FROM sOrder so LEFT JOIN sOrderDetail sod ON sod.docId = so.docId LEFT JOIN voucher_Type vt ON vt.v_Type = so.v_Type WHERE vt.category IN (:SO) AND sod.piDocId IS NOT NULL AND so.docId <> '' GROUP BY sod.piDocId ) vSo ON vSo.piDocId = pi.docId {…} WHERE vso.piDocId IS NULL AND vt.category IN (:PI) AND pi.site_Code =:site_Code {…}`
- Params: `:SO` ← `pvc.SALES_ORDER`; `:PI` ← `pvc.PROFORMA_INVOICE`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:fromv_Date` ← `DmServer.convertDate(param.getFromDate())`; `:tov_Date` ← `DmServer.convertDate(param.getToDate())`
- Other calls: `CONSIGNEE.getCode`, `DELIVERY_TO_PARTY.getCode`, `PARTY.getCode`

### `getTxtHStatus`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHStatus(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHPINo`
`public DmResultPageing<List<TxtHProformaInvoice_Mdl>> getTxtHPINo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT so.docId, so.recId , so.v_Date as piDate, vt.category, vt.short_Name FROM proformaInvoice AS so left join voucher_Type as vt on vt.v_Type = so.v_Type where so.site_Code= ' {…} ' and vt.Category = {…}`

### `getTxtHSupplierPT`
`public DmResultPageing<List<TxtHSupplierPT_Mdl>> getTxtHSupplierPT(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as supplierName , sg.manualCode , c.cityName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( SELECT DISTINCT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_Type = acf.v_Type WHERE vt.category = {…} ) AND sg.isActive = 1 AND sgsl.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHDeliveryCity`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDeliveryCity(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select ct.cityCode, ct.cityName, ct.stateCode, sm.name as stateName from city ct left join stateMast sm on ct.stateCode = sm.code`

### `setApproval`
`public DmResult<String> setApproval(String approvedBy, boolean isApprove, String docId, String caption)`

- Other calls: `STApprovals.generalProformaInvoiceApproval`

### `walkThrough`
`public DmResult<String> walkThrough(WalkThrough_Mdl walkThrough)`

- Other calls: `STApprovals.generalProformaInvoiceApproval`, `WalkThrough_Server.walkThrough`

### `getCardDetail`
`public DmResult<CardDetail_Mdl> getCardDetail()`

- SQL 1: `Select count(so.docId) as cnt, sum(case when DATEDIFF(day, so.v_Date, CAST(getdate() AS date)) <= {…} then 1 else 0 end) as low, sum(case when DATEDIFF(day, so.v_Date, CAST(getdate() AS date)) > {…} and DATEDIFF(day, so.v_Date, CAST(getdate() AS date)) <= {…} then 1 else 0 end) as medium, sum(case when DATEDIFF(day, so.v_Date, CAST(getdate() AS date)) >= {…} then 1 else 0 end) as high from proformaInvoice as so left join voucher_type as vt on so.v_Type = vt.v_Type left join( select so.docId from proformaInvoice as so group by so.docId ) as sod on sod.docId = so.docId LEFT JOIN walkThroughFootPrint wtfp on wtfp.wtaCode = so.wtaCode and wtfp.isStatus = 0 where sod.docId is not null and isNull(so.approvedBy,'') = '' and vt.category = ( {…} ) AND isNull(wtfp.linkedUser,' {…} ,') like '% {…} ,%' and so.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `HIGH_PRIORITYDAYS.getDays`, `LOW_PRIORITYDAYS.getDays`, `MEDIUM_PRIORITYDAYS.getDays`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`

- SQL 1: `select sm.code,sm.name AS site from siteMast sm where code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.dashboardCardStrip.client`, `paperbiz.selfToolkit.dashboardCardStrip.shared`, `paperbiz.selfToolkit.proformaInvoiceApproval.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
**Standard approval screen** (pattern `_module.md` me). Is document ke liye: pending/approved list `getFillRecords` (Setting popup ke filters), approve/reject `setApproval(approvedBy, isApprove, docId)`, dashboard card ka count `getCardDetail` (din ke ageing buckets), lookups `getTxtH…`. Document ka approval logic `stStatic/STApprovals` me.
