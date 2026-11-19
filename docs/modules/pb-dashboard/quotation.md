# pb-dashboard / quotation

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/dashBoard/src/quotation/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHEnquiryNo, getTxtHQuotationNo, getTxtHSupplier
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `QuotationDetailPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `txtRequisitionNo` (DmTextBox), `txtDate` (DmTextBox), `txtDepartment` (DmTextBox), `txtRequistionBy` (DmTextBox), `gridQuotationDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `gridQuotationDetail`: Item name [Text, w180]; Properties [Text, w180]; Qty. [Text, w80]; Unit [Text, w80]; Rate [Text, w80]; Amount [Text, w80]; Remark [Text, w180]

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtRequisitionNo.setEnabled(false)`
- `txtDate.setEnabled(false)`
- `txtRequistionBy.setEnabled(false)`
- `txtDepartment.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
txtRequisitionNo.setEnabled(false);
txtDate.setEnabled(false);
txtRequistionBy.setEnabled(false);
txtDepartment.setEnabled(false);
```

### Screen `Quotation`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmGridPaging | `gridQuotation` | `GridRequisition_PrpAcs, gridRows, false` |

Other widgets (not in tab list): `txtSearchValue` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `gridQuotation`: Detail [Text, w50]; Quotation No. [Text, w120, recId]; Date [Text, w90, replace(convert(varchar, v_Date, 106), ' ','-')]; Type [Text, w50, short_Name]; Supplier Name [Text, w400, vendorName]; Sup. Ref. No. [Text, w120, vendorRefNo]; Sup. Ref. Date [Text, w90, replace(convert(varchar, vendorRefDate, 106), ' ','-')]; Enquiry No. [Text, w120, enquiryNo]

**Lookup / suggestion / find sources (server RPC called):**
- `gridQuotation` → `getFillRecords`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`settingPopup.addQuotationCallBack` → `onOk()`
```java
quotationSearchValue_Mdl = sm;
gridQuotation.getDmGrid().commitChanges();
gridQuotation.reQuery();
```

`gridQuotation.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnToEndOfFile).getFillRecords(quotationSearchValue_Mdl, helper, blnToEndOfFile, new AsyncCallback<Dm
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdQuotation_Mdl>> result) {
if (result.isStatus()) {
gridQuotation.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`gridQuotation.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
gridQuotation.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`gridQuotation.addCallBack` → `getParam()`
```java
return quotationSearchValue_Mdl;
```
- Confirm: "Are you sure? You want to close."
- Alert: "Please select the quotation."

### Screen `Setting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | From Date | DmTextBox | `txtFromDate` | `15, false` |
| 2 | To Date | DmTextBox | `txtToDate` | `15, false` |
| 3 | Supplier | DmTextHelpMulti | `txtHSupplier` | `TxtHSupplier_PrpAcs, 5` |
| 4 | Quotation No. | DmTextHelpMulti | `txtHQuotationNo` | `TxtHQuotationNo_PrpAcs, 7` |
| 5 | Enquiry No. | DmTextHelpMulti | `txtHEnquiryNo` | `TxtHIEnquiryNo_PrpAcs, 7, false` |

**Grid / popup columns** (header, width, db field):
- `txtHSupplier`: Name [Text, w0, name]; Manual Code [Text, w100, manualCode]; City [Text, w150, cityName]
- `txtHEnquiryNo`: Enquiry No. [Text, w150, enqRecId]; Enq. Ref. No. [Text, w100, refNo]; Date [Text, w80, enqDate]
- `txtHQuotationNo`: Name [Text, w120, name]; Supplier Ref. No. [Text, w100, supplierRefNo]; Supplier Ref. Date [Text, w100, supplierRefDate]

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtFromDate.addValueChangeHandler` → `onValueChange()`
```java
txtFromDate.setText(DmClient.returnDate(txtFromDate.getText()));
```

`txtToDate.addValueChangeHandler` → `onValueChange()`
```java
txtToDate.setText(DmClient.returnDate(txtToDate.getText()));
```

`txtHSupplier.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHSupplier(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHS
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSupplier).show();
public void onSuccess(DmResultPageing<List<TxtHSupplier_Mdl>> result) {
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

`txtHSupplier.addTextHelpMultiCallBack` → `getSelection()`
```java
if (blnStatus) {
```

`txtHQuotationNo.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHQuotationNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Tx
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHQuotationNo).show();
public void onSuccess(DmResultPageing<List<TxtHQuotationNo_Mdl>> result) {
if (result.isStatus()) {
txtHQuotationNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHQuotationNo).show();
```

`txtHQuotationNo.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHQuotationNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHQuotationNo).show();
```

`txtHEnquiryNo.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHEnquiryNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtH
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHEnquiryNo).show();
public void onSuccess(DmResultPageing<List<TxtHEnquiry_Mdl>> result) {
if (result.isStatus()) {
txtHEnquiryNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHEnquiryNo).show();
```

`txtHEnquiryNo.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHEnquiryNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHEnquiryNo).show();
```

`txtHEnquiryNo.addTextHelpMultiCallBack` → `getSelection()`
```java
if (blnStatus) {
```
- Alert: "From Date should be less than or equal to To Date."

## Files
| file | lines |
|---|---|
| client/Quotation.java | 502 |
| client/QuotationCard.java | 131 |
| client/QuotationDetailPopup.java | 121 |
| client/QuotationDetailPopup_Dsg.java | 144 |
| client/Quotation_Dsg.java | 283 |
| client/Setting.java | 253 |
| client/Setting_Dsg.java | 241 |
| server/Quotation_SrvImpl.java | 552 |
| shared/DefaultValue_Mdl.java | 22 |
| shared/GrdQuotationDetail_Mdl.java | 131 |
| shared/GrdQuotation_Mdl.java | 185 |
| shared/QuotationSearchValue_Mdl.java | 85 |
| shared/Quotation_Mdl.java | 104 |
| shared/Quotation_Srv.java | 30 |
| shared/Quotation_SrvAsync.java | 29 |
| shared/TxtHEnquiry_Mdl.java | 32 |
| shared/TxtHQuotationNo_Mdl.java | 32 |
| shared/TxtHSupplier_Mdl.java | 31 |

## UI labels / columns (client)
`Action`, `From Date`, `To Date`, `Supplier`, `Quotation No.`, `Enquiry No.`

## Models (shared DTOs)
### QuotationSearchValue_Mdl
| type | field |
|---|---|
| String | FromDate |
| String | ToDate |
| String | vendorName |
| String | vendorCode |
| String | quotationRecIds |
| String | quotationDocIds |
| String | enquiryCode |
| String | enquiryName |

### Quotation_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | V_No |
| String | requisitionByName |
| String | requisitionByCode |
| String departmentCode, | departmentToCode |
| String departmentName, | departmentToName |
| String | date |
| String | vCat |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | currentDate |

### TxtHSupplier_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | cityName |
| String | manualCode |

### GrdQuotation_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | isView |
| String | quotationNo |
| String | voucherSortName |
| String | quotationDate |
| String | supplierCode |
| String | supplierName |
| String | supplierRefNo |
| String | supplierRefDate |
| String | supplierPartyType |
| String | enquiryRefNo |
| String | siteCode |
| String | isBifurcateServiceCode |
| String | isBifurcateServiceName |
| String | locationCode |
| String | locationName |
| String | rcmCode |
| String | rcmName |
| String | itcCode |
| String | itcName |

### TxtHQuotationNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | supplierRefDate |
| String | supplierRefNo |

### GrdQuotationDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | v_SNo |
| String | itemProperties |
| String | itemQty |
| String | unitName |
| String | unitCode |
| String | rate |
| String | amount |
| String | remark |
| String | reqDate |
| String | action |
| String | check |
| String | reqTime |
| String | isStatus |

### TxtHEnquiry_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | enquiryType |
| String | enquiryDate |

## RPC interface
| method | params | returns |
|---|---|---|
| getCardDetail |  | DmResult<CardDetail_Mdl> |
| getFillRecords | QuotationSearchValue_Mdl searchValueMdl, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdQuotation_Mdl>> |
| getRequisitionDetail | String docId | DmResult<Quotation_Mdl> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| getTxtHEnquiryNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHEnquiry_Mdl>> |
| getTxtHQuotationNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHQuotationNo_Mdl>> |
| getTxtHSupplier | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHSupplier_Mdl>> |
| validateSite | String docId | DmResult<String> |

## Server `Quotation_SrvImpl.java` (552 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnVoucherPrefix`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.xNull`

### `getFillRecords`
`public DmResultPageing<List<GrdQuotation_Mdl>> getFillRecords(QuotationSearchValue_Mdl searchValueMdl, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select q.docId, q.recId, q.v_Date, q.vendorRefNo, q.vendorRefDate, q.enquiryRefNo,q.v_No, q.enquiryRefDate, q.subCode as vendorCode, CONCAT(sgv.displayName,', ',c.cityName) as vendorName , sgv.partyType , en.recId as enquiryNo, vt.short_Name , q.isBifurcateService AS isBifurcateServiceCode , IIF(ISNULL(q.isBifurcateService,0) = 1,'Yes','No') AS isBifurcateServiceName , lm.name as locationName , q.isItc AS itcCode , q.rcmApplicable AS rcmApplicableCode , q.locationCode , IIF(ISNULL(q.rcmApplicable,0) = 1,'Yes','No') AS rcmApplicableName , IIF(ISNULL(q.isItc,0) = 1,'Yes','No') AS itcName , q.site_code from quotation as q left join voucher_Type as vt on q.v_Type = vt.v_Type left join subGroup as sgv on q.subCode = sgv.subCode left join city c on c.cityCode = sgv.cityCode LEFT JOIN ( select docid, enquiryDocid from quotationDetail where enquiryDocid is NOT NULL group by docid,enquiryDocid )vd ON vd.docId = q.docId left join enquiry as en on vd.enquiryDocId = en.docId left join pOrderDetail pod on pod.quotationDocId=q.docId LEFT JOIN locationMast lm ON lm.code = q.locationCode where vt.category IN ( {…} ) and q.site_Code = :site_Code and pod.quotationDocId is null {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:fromv_Date` ← `DmServer.convertDate(searchValueMdl.getFromDate())`; `:tov_Date` ← `DmServer.convertDate(searchValueMdl.getToDate())`
- Other calls: `Quotation.setEnquiryRefNo`, `Quotation.setIsBifurcateServiceCode`, `Quotation.setIsBifurcateServiceName`, `Quotation.setItcCode`, `Quotation.setItcName`, `Quotation.setLocationCode`, `Quotation.setLocationName`, `Quotation.setQuotationDate`, `Quotation.setRcmCode`, `Quotation.setRcmName`, `Quotation.setSharedKeyFieldCode`, `Quotation.setSharedKeyFieldName`, `Quotation.setSiteCode`, `Quotation.setSupplierCode`, `Quotation.setSupplierName`, `Quotation.setSupplierPartyType`, `Quotation.setSupplierRefDate`, `Quotation.setSupplierRefNo`, `Quotation.setVoucherSortName`

### `getRequisitionDetail`
`public DmResult<Quotation_Mdl> getRequisitionDetail(String docId)`

- SQL 1: `select qd.itemCode, qd.v_SNo, im.name as itemName, qd.quantity, qd.unitCode, um.unit as unitName, qd.remark, qd.oh_Amt_Gross as amount,qd.rate,im.propertyList from quotationDetail as qd left join itemMast as im on qd.itemCode = im.code left join unitMast as um on qd.unitCode = um.code LEFT JOIN unitMast ums ON ums.code = im.sku where qd.docId = :docId order by qd.v_SNo`
- Params: `:docId` ← `DmShared.iNull(docId).toString()`

### `getCardDetail`
`public DmResult<CardDetail_Mdl> getCardDetail()`

- SQL 1: `Select count(q.docId) as cnt , sum(case when DATEDIFF(day, q.v_Date, CAST(:currentDate AS date)) <= {…} then 1 else 0 end) as low, sum(case when DATEDIFF(day, q.v_Date, CAST(:currentDate AS date)) > {…} and DATEDIFF(day, q.v_Date, CAST(:currentDate AS date)) <= {…} then 1 else 0 end) as medium, sum(case when DATEDIFF(day, q.v_Date, CAST(:currentDate AS date)) >= {…} then 1 else 0 end) as high from quotation as q left join voucher_type as vt on q.v_Type = vt.v_Type left join pOrderDetail pod on pod.quotationDocId=q.docId where vt.category IN ( {…} ) and q.site_Code = :site_Code and pod.quotationDocId is null`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:currentDate` ← `DmServer.getServerTimestamp()`
- Other calls: `HIGH_PRIORITYDAYS.getDays`, `LOW_PRIORITYDAYS.getDays`, `MEDIUM_PRIORITYDAYS.getDays`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`


### `getTxtHEnquiryNo`
`public DmResultPageing<List<TxtHEnquiry_Mdl>> getTxtHEnquiryNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT enq.docId, enq.recId as enqRecId, enq.v_Date as enqDate, vt.category, enq.v_No, enq.referenceNo as refNo FROM enquiry AS enq left join voucher_Type as vt on vt.v_Type = enq.v_Type where enq.site_Code = :site_Code and enq.v_Prefix = :v_Prefix and vt.category IN (:ie,:se)`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Prefix` ← `DmServer.getSsnVoucherPrefix(this)`; `:ie` ← `pvc.GENERAL_ENQUIRY`; `:se` ← `pvc.SERVICE_ENQUIRY`

### `getTxtHQuotationNo`
`public DmResultPageing<List<TxtHQuotationNo_Mdl>> getTxtHQuotationNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT q.docId AS code , q.recId AS name , q.vendorRefNo AS supplierRefNo , q.vendorRefDate AS supplierRefDate , q.v_No, q.v_Date FROM quotation q LEFT JOIN voucher_Type vt ON q.v_Type = vt.v_Type LEFT JOIN ( SELECT qd.docId, qd.enquiryDocId FROM quotationDetail qd WHERE qd.enquiryDocId IS NOT NULL GROUP BY qd.docId, qd.enquiryDocId ) qd ON q.docId = qd.docId LEFT JOIN enquiry e ON qd.enquiryDocId = e.docId LEFT JOIN ( SELECT pod.quotationDocId FROM pOrderDetail pod WHERE pod.quotationDocId IS NOT NULL GROUP BY pod.quotationDocId ) pod ON q.docId = pod.quotationDocId WHERE vt.Category IN ( {…} ) AND q.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHSupplier`
`public DmResultPageing<List<TxtHSupplier_Mdl>> getTxtHSupplier(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_type = acf.v_Type WHERE vt.category in ( {…} )`
- SQL 2: `SELECT sg.subCode AS code , sg.name , sg.manualCode , c.cityName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( {…} ) AND sg.isActive = 1 AND sgsl.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `validateSite`
`public DmResult<String> validateSite(String docId)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmChart.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.self.client`, `paperbiz.dashBoard.quotation.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.dashboardCardStrip.client`, `paperbiz.selfToolkit.dashboardCardStrip.shared`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
