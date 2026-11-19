# pb-self-toolkit / salesReturnApproval

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/salesReturnApproval/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHSalesReturn, getTxtHParty, getTxtHItem, getTxtHGateNo, getTxtHStatus, getTxtHSalesInvoice, getTxtHItemNature, getTxtHTransporter
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `SalesReturnApproval`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmGridPaging | `dmGrd` | `Grd_PrpAcs, gridRows, false` |

**Grid / popup columns** (header, width, db field):
- `dmGrd`:  [Text, w30];  [Text, w25]; Return No. [Text, w100, recId]; Type [Text, w50, type]; Date [Text, w80, date]; Ref. No. [Text, w100, refNo]; Ref Dt. [Text, w90, refDate]; Bif. Service [Text, w100]; Party [Text, w200, partyName]; Gate Entry No. [Text, w100, gateRecId]; Transporter [Text, w180, transporterName]; Mode [Text, w80, trasnMode]; EWay Bill No. [Text, w140, eWayBillNo]; EWay Bill Date [Text, w80, eWayBillDate]; Approved By [Text, w130, approvedBy]

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
saleApp_Mdl = approvalEntry_Mdl;
dmGrd.getDmGrid().commitChanges();
dmGrd.reQuery();
```

`dmGrd.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(true).getFillRecords(param, helper, blnToEndOfFile, vCatSR, new AsyncCallback<DmResultPageing<List<Gr
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdSalesReturnApproval_Mdl>> result) {
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
return saleApp_Mdl;
```
- Confirm: "Are you sure? You want to close."

### Screen `Setting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | From Date | DmTextBox | `txtFromDate` | `15, false` |
| 2 | To Date | DmTextBox | `txtToDate` | `15, false` |
| 3 | Return No. | DmTextHelpMulti | `txtHSaleReturn` | `TxtHSaleReturn_PrpAcs, 7` |
| 4 | Invoice No. | DmTextHelpMulti | `txtHSaleInvoice` | `TxtHSaleInvoice_PrpAcs, 7` |
| 5 | Gate Entry No | DmTextHelpMulti | `txtHGateEntryNo` | `TxtHGateIn_PrpAcs, 7` |
| 6 | Party | DmTextHelpMulti | `txtHParty` | `TxtHSupplierName_PrpAcs, 7` |
| 7 | Transporter | DmTextHelpMulti | `txtHTransporter` | `TxtHTransporterName_PrpAcs, 7` |
| 8 | Status | DmTextHelp | `txtHStatus` | `KeyField_PrpAcs, 4, false` |

**Grid / popup columns** (header, width, db field):
- `txtHSaleReturn`: Return No. [Text, w120, recId]; Type [Text, w90, category]; Date [Text, w90, replace(convert(varchar, date, 106), ' ','-')]
- `txtHStatus`: Status [Text, w150, Name]
- `txtHParty`: Name [Text, w220, supplierName]; Manual Code [Text, w100, manualCode]; City Name [Text, w100, cityName]
- `txtHGateEntryNo`: Gate Entry No. [Text, w120, gateNo]; Gate Type [Text, w90, category]; Date [Text, w90, replace(convert(varchar, gateDate, 106), ' ','-')]
- `txtHTransporter`: Name [Text, w220, supplierName]; Manual Code [Text, w100, manualCode]; City Name [Text, w100, cityName]
- `txtHSaleInvoice`: Sale Invoice No. [Text, w120, recId]; Invoice Type [Text, w90, category]; Invoice Date [Text, w90, replace(convert(varchar, date, 106), ' ','-')]

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

`txtHSaleReturn.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHSalesReturn(helper, blnToEndOfFile, vCat, new AsyncCallback<DmResultPageing<L
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSaleReturn).show();
public void onSuccess(DmResultPageing<List<TxtHSalesReturn_Mdl>> result) {
if (result.isStatus()) {
txtHSaleReturn.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSaleReturn).show();
```

`txtHSaleReturn.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSaleReturn.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSaleReturn).show();
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

`txtHTransporter.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHTransporter(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Tx
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

`txtHSaleInvoice.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHSalesInvoice(helper, blnToEndOfFile, new DmVoucherCategory().SALES_INVOICE, n
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSaleInvoice).show();
public void onSuccess(DmResultPageing<List<TxtHSalesInvoice_Mdl>> result) {
if (result.isStatus()) {
txtHSaleInvoice.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSaleInvoice).show();
```

`txtHSaleInvoice.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSaleInvoice.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSaleInvoice).show();
```
- Alert: "From Date should be less than or equal to To Date."

## Files
| file | lines |
|---|---|
| client/SalesReturnApproval.java | 359 |
| client/SalesReturnApprovalCard.java | 142 |
| client/SalesReturnApproval_Dsg.java | 259 |
| client/Setting.java | 347 |
| client/Setting_Dsg.java | 345 |
| server/SalesReturnApproval_SrvImpl.java | 796 |
| shared/DefaultValue_Mdl.java | 22 |
| shared/GrdSalesReturnApproval_Mdl.java | 169 |
| shared/SalesReturnApproval_Mdl.java | 209 |
| shared/SalesReturnApproval_Srv.java | 36 |
| shared/SalesReturnApproval_SrvAsync.java | 38 |
| shared/TxtHGateIn_Mdl.java | 32 |
| shared/TxtHSalesInvoice_Mdl.java | 32 |
| shared/TxtHSalesReturn_Mdl.java | 32 |
| shared/TxtHSupplierPT_Mdl.java | 31 |

## UI labels / columns (client)
`From Date`, `To Date`, `Return No.`, `Invoice No.`, `Gate Entry No`, `Party`, `Transporter`, `Status`

## Models (shared DTOs)
### TxtHSalesReturn_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | type |
| String | date |

### SalesReturnApproval_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | FromDate |
| String | ToDate |
| String | StatusCode |
| String | StatusName |
| String | partyName |
| String | partyCode |
| String | trasnporterName |
| String | trasnporterCode |
| String | gateNo |
| String | gateCode |
| String | saleReturnCode |
| String | saleReturnName |
| String | saleReturnDate |
| String | deliveryToName |
| String | vSNo |
| String | siteCode |
| String | siteName |
| String | isStatus |
| String | wtaCode |
| String | saleInvoiceCode |
| String | saleInvoiceName |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | currentDate |

### TxtHSupplierPT_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityName |

### GrdSalesReturnApproval_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | isChecked |
| String | IsView |
| String | isStatus |
| String | type |
| String | date |
| String | party |
| String | bifurcateService |
| String | ApprovedBy |
| String | refNo |
| String | refDate |
| String | gateNo |
| String | transporter |
| String | transportMode |
| String | ewayBillNo |
| String | ewayBillDate |
| String | Approval |
| String | wtaCode |

### TxtHSalesInvoice_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | type |
| String | date |

### TxtHGateIn_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | gateType |
| String | gateDate |

## RPC interface
| method | params | returns |
|---|---|---|
| getFillRecords | SalesReturnApproval_Mdl param, DmHelper helper, boolean blnToEndOfFile, String vCat | DmResultPageing<List<GrdSalesReturnApproval_Mdl>> |
| getTxtHSalesReturn | DmHelper pageing, boolean blnToEndOfFile, String vCat | DmResultPageing<List<TxtHSalesReturn_Mdl>> |
| getTxtHGateNo | DmHelper pageing, boolean blnToEndOfFile, String vCat | DmResultPageing<List<TxtHGateIn_Mdl>> |
| getTxtHParty | DmHelper pageing, boolean blnToEndOfFile, String vCat | DmResultPageing<List<TxtHSupplierPT_Mdl>> |
| getTxtHTransporter | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHSupplierPT_Mdl>> |
| getTxtHItem | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSalesInvoice | DmHelper pageing, boolean blnToEndOfFile, String vCat | DmResultPageing<List<TxtHSalesInvoice_Mdl>> |
| getTxtHStatus | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| setApproval | String approvedBy, boolean isApprove, String docId | DmResult<String> |
| getCardDetail | String vCat | DmResult<CardDetail_Mdl> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| getTxtHItemNature | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Server `SalesReturnApproval_SrvImpl.java` (796 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.xNull`

### `getFillRecords`
`public DmResultPageing<List<GrdSalesReturnApproval_Mdl>> getFillRecords(SalesReturnApproval_Mdl param, DmHelper helper, boolean blnToEndOfFile, String vCat)`

- SQL 1: `SELECT sr.docId ,sr.v_Date ,case when isNull(sr.isBifurcateService,'') = 0 then 'No' else 'Yes' end as bifurcateService , sr.partyCode AS supplierCode,sg.name AS partyName, vt.short_Name, sr.v_No, sr.u_Name,sr.preparedBy,sr.approvedBy, format(sr.approvalDateTime,'dd-MMM-yyyy HH.mm') as approvalDateTime,sr.wtaCode , sr.refDate , sr.refNo , sr.recId , sr.eWayBillDt , sr.eWayBillNo , sr.gateDocId , gtIo.recId as gateRecId ,sr.transporterCode , sgt.name as transporterName , sr.transportMode FROM sBill AS sr LEFT JOIN voucher_Type AS vt ON vt.v_Type = sr.v_Type LEFT JOIN subGroup AS sg ON sg.subCode = sr.partyCode LEFT JOIN subGroup AS sgt ON sgt.subCode = sr.transporterCode LEFT JOIN gateInOut as gtIo ON gtIo.docId = sr.gateDocId {…} where vt.category IN ( {…} ) {…}`
- Params: `:fromv_Date` ← `DmServer.convertDate(param.getFromDate())`; `:tov_Date` ← `DmServer.convertDate(param.getToDate())`
- Other calls: `TransportMode.getViaKey`

### `getTxtHSalesReturn`
`public DmResultPageing<List<TxtHSalesReturn_Mdl>> getTxtHSalesReturn(DmHelper helper, boolean blnToEndOfFile, String vCat)`

- SQL 1: `SELECT so.docId, so.recId , so.v_Date as date, vt.category FROM sBill AS so left join voucher_Type as vt on vt.v_Type = so.v_Type where so.site_Code= ' {…} ' and vt.Category IN ( {…} )`

### `getTxtHParty`
`public DmResultPageing<List<TxtHSupplierPT_Mdl>> getTxtHParty(DmHelper helper, boolean blnToEndOfFile, String vCat)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as supplierName , sg.manualCode , c.cityName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( SELECT DISTINCT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_Type = acf.v_Type WHERE vt.category IN ( {…} ) ) AND sg.isActive = 1 AND sgsl.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `setApproval`
`public DmResult<String> setApproval(String approvedBy, boolean isApprove, String docId)`

- Other calls: `STApprovals.generalSalesReturnApproval`

### `walkThrough`
`public DmResult<String> walkThrough(WalkThrough_Mdl walkThrough)`

- Other calls: `STApprovals.generalSalesReturnApproval`, `WalkThrough_Server.walkThrough`

### `getCardDetail`
`public DmResult<CardDetail_Mdl> getCardDetail(String vCat)`

- SQL 1: `Select count(sr.docId) as cnt, sum(case when DATEDIFF(day, sr.v_Date, CAST(getdate() AS date)) <= {…} then 1 else 0 end) as low, sum(case when DATEDIFF(day, sr.v_Date, CAST(getdate() AS date)) > {…} and DATEDIFF(day, sr.v_Date, CAST(getdate() AS date)) <= {…} then 1 else 0 end) as medium, sum(case when DATEDIFF(day, sr.v_Date, CAST(getdate() AS date)) >= {…} then 1 else 0 end) as high from sBill as sr left join voucher_type as vt on sr.v_Type = vt.v_Type LEFT JOIN walkThroughFootPrint wtfp on wtfp.wtaCode = sr.wtaCode and wtfp.isStatus = 0 where isNull(sr.approvedBy,'') = '' and vt.category IN ( {…} ) AND isNull(wtfp.linkedUser,' {…} ,') like '% {…} ,%' and sr.site_Code = :site_Code AND isNull(sr.cancelledBy,'') =''`
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

- SQL 1: `SELECT gio.docId, gio.recId as gateNo , gio.v_Date as gateDate, vt.category , gio.v_No FROM gateInOut AS gio left join voucher_Type as vt on vt.v_Type = gio.v_Type where gio.site_Code= ' {…} ' and vt.Category IN (' {…} ')`

### `getTxtHStatus`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHStatus(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHSalesInvoice`
`public DmResultPageing<List<TxtHSalesInvoice_Mdl>> getTxtHSalesInvoice(DmHelper helper, boolean blnToEndOfFile, String vCat)`

- SQL 1: `SELECT so.docId, so.recId , so.v_Date as date, vt.category FROM sBill AS so left join voucher_Type as vt on vt.v_Type = so.v_Type where so.site_Code= ' {…} ' and vt.Category IN (' {…} ')`

### `getTxtHItemNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemNature(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select itn.code, itn.name, itn.manualCode from itemNature as itn`

### `getTxtHTransporter`
`public DmResultPageing<List<TxtHSupplierPT_Mdl>> getTxtHTransporter(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code, sg.name as supplierName, sg.manualCode, c.cityName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode WHERE sg.isActive = 1 AND sgsl.site_Code = :site_Code AND sg.nature = :nature`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:nature` ← `DmServer.getCodeViaManualCode("TP", "manualCode", "subGroupNature", "code", cnTemp)`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.dashboardCardStrip.client`, `paperbiz.selfToolkit.dashboardCardStrip.shared`, `paperbiz.selfToolkit.salesReturnApproval.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
**Standard approval screen** (pattern `_module.md` me). Is document ke liye: pending/approved list `getFillRecords` (Setting popup ke filters), approve/reject `setApproval(approvedBy, isApprove, docId)`, dashboard card ka count `getCardDetail` (din ke ageing buckets), lookups `getTxtH…`. Document ka approval logic `stStatic/STApprovals` me.
