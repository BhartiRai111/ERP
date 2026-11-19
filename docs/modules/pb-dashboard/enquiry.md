# pb-dashboard / enquiry

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/dashBoard/src/enquiry/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHEnquiryNo, getTxtHSupplier, getTxtHItem
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `Enquiry`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmGridPaging | `grdEnquiryDetail` | `GridEnquiry_PrpAcs, gridRows, false` |

Other widgets (not in tab list): `txtSearchValue` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `grdEnquiryDetail`:  [Text, w35]; Supplier Name  [Text, w395, SupplierName]; Enquiry No.  [Text, w90, recId]; Type [Text, w70, short_Name]; Date [Text, w95, replace(convert(varchar, v_Date, 106), ' ','-')]; Reference No. [Text, w120, referenceNo]; Remarks  [Text, w450, remark]

**Lookup / suggestion / find sources (server RPC called):**
- `grdEnquiryDetail` → `getFillRecords`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`settingPopup.addEnquiryCallBack` → `onOk()`
```java
searchValue_Mdl = svm;
grdEnquiryDetail.reQuery();
```

`grdEnquiryDetail.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnToEndOfFile).getFillRecords(searchValue_Mdl, helper, blnToEndOfFile, new AsyncCallback<DmResultPag
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdEnquiry_Mdl>> result) {
if (result.isStatus()) {
grdEnquiryDetail.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`grdEnquiryDetail.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
grdEnquiryDetail.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`grdEnquiryDetail.addCallBack` → `getParam()`
```java
return searchValue_Mdl;
```
- Confirm: "Are you sure? You want to close."
- Alert: "Please select the enquiry."

### Screen `Setting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | From Date | DmTextBox | `txtFromDate` | `15, false` |
| 2 | To Date | DmTextBox | `txtToDate` | `15, false` |
| 3 | Supplier | DmTextHelpMulti | `txtHSupplier` | `TxtHSupplier_PrpAcs, 5` |
| 4 | Item Name | DmTextHelpMulti | `txtHItemName` | `TxtHItemName_PrpAcs, 5` |
| 5 | Enquiry No. | DmTextHelpMulti | `txtHEnquiryNo` | `TxtHIndentNo_PrpAcs, 7` |

**Grid / popup columns** (header, width, db field):
- `txtHEnquiryNo`: Enquiry No. [Text, w90, enqRecId]; Enquiry Type [Text, w80, category]; Enquiry Date [Text, w80, enqDate]
- `txtHSupplier`: Name [Text, w0, name]; Manual Code [Text, w100, manualCode]; City [Text, w150, cityName]
- `txtHItemName`: Name [Text, w250, name]; Unit [Text, w50, purchaseUnitName]

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

`txtHItemName.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItem(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHItemN
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemName).show();
public void onSuccess(DmResultPageing<List<TxtHItemName_Mdl>> result) {
if (result.isStatus()) {
txtHItemName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemName).show();
```

`txtHItemName.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemName).show();
```

`txtHEnquiryNo.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHEnquiryNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtH
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHEnquiryNo).show();
public void onSuccess(DmResultPageing<List<TxtHEnquiryNo_Mdl>> result) {
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
| client/Enquiry.java | 482 |
| client/EnquiryCard.java | 131 |
| client/Enquiry_Dsg.java | 282 |
| client/Setting.java | 249 |
| client/Setting_Dsg.java | 234 |
| server/Enquiry_SrvImpl.java | 488 |
| shared/DefaultValue_Mdl.java | 22 |
| shared/Enquiry_Srv.java | 26 |
| shared/Enquiry_SrvAsync.java | 26 |
| shared/GrdEnquiry_Mdl.java | 77 |
| shared/SearchValue_Mdl.java | 95 |
| shared/TxtHEnquiryNo_Mdl.java | 32 |
| shared/TxtHItemName_Mdl.java | 41 |
| shared/TxtHSupplier_Mdl.java | 31 |

## UI labels / columns (client)
`Action`, `From Date`, `To Date`, `Supplier`, `Item Name`, `Enquiry No.`

## Models (shared DTOs)
### TxtHItemName_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | purchaseUnitCode |
| String | purchaseUnitName |

### TxtHEnquiryNo_Mdl (extends `KeyField implements Serializable,IsSerializable`)
| type | field |
|---|---|
| String | enquiryType |
| String | enquiryDate |

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
| String | IndentNo |
| String | FromDate |
| String | ToDate |
| String | vendorName |
| String | vendorCode |
| String | enquiryRecIds |
| String | enquiryDocIds |
| String | itemName |
| String | itemCode |

### GrdEnquiry_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | vendorName |
| String | vendorCode |
| String | enquiryDate |
| String | enquiryType |
| String | refNo |
| String | remark |
| String | isView |

## RPC interface
| method | params | returns |
|---|---|---|
| getFillRecords | SearchValue_Mdl searchValueMdl, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdEnquiry_Mdl>> |
| getCardDetail |  | DmResult<CardDetail_Mdl> |
| getTxtHEnquiryNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHEnquiryNo_Mdl>> |
| getTxtHSupplier | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHSupplier_Mdl>> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| getTxtHItem | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemName_Mdl>> |
| checkCurrentSiteRecord | String docId | DmResult<String> |

## Server `Enquiry_SrvImpl.java` (488 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnVoucherPrefix`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.xNull`

### `getFillRecords`
`public DmResultPageing<List<GrdEnquiry_Mdl>> getFillRecords(SearchValue_Mdl searchValueMdl, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT enq.docid, enq.recId, CONCAT(sbg.name,', ',c.cityName) as SupplierName, sbg.subCode as vendorCode, enq.v_Date, vt.short_Name, enq.referenceNo, enq.remark, enq.v_No FROM enquiry as enq LEFT JOIN enquiryVendorDetail as evd on evd.docId=enq.docId LEFT JOIN ( SELECT vQd.enquiryDocId,q.subCode FROM quotation q LEFT JOIN ( SELECT qd.docid,qd.enquiryDocId FROM quotationDetail qd GROUP BY qd.docid,qd.enquiryDocId ) vQd on vQd.docId = q.docid WHERE vQd.enquiryDocId IS NOT NULL GROUP BY vQd.enquiryDocId,q.subCode ) vQd ON vQd.enquiryDocId = evd.docid AND vQd.subCode = evd.subCode LEFT JOIN subGroup as sbg on sbg.subCode=evd.subCode LEFT JOIN city as c on c.cityCode=sbg.cityCode LEFT JOIN voucher_Type as vt on enq.v_Type=vt.v_Type {…} WHERE enq.site_Code = :site_Code AND vt.category IN ( {…} ) AND isNull(enq.approvedBy,'') <> '' AND vQd.enquiryDocId IS NULL AND enq.v_prefix = :v_Prefix {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Prefix` ← `DmServer.getSsnVoucherPrefix(this)`; `:fromv_Date` ← `DmServer.convertDate(searchValueMdl.getFromDate())`; `:tov_Date` ← `DmServer.convertDate(searchValueMdl.getToDate())`

### `getCardDetail`
`public DmResult<CardDetail_Mdl> getCardDetail()`

- SQL 1: `SELECT COUNT(enq.docId) AS cnt , SUM(CASE WHEN DATEDIFF(day, enq.v_Date, CAST(:currentDate AS date)) <= {…} THEN 1 ELSE 0 END) AS low, SUM(CASE WHEN DATEDIFF(day, enq.v_Date, CAST(:currentDate AS date)) > {…} AND DATEDIFF(day, enq.v_Date, CAST(:currentDate AS date)) <= {…} THEN 1 ELSE 0 END) AS medium, SUM(CASE WHEN DATEDIFF(day, enq.v_Date, CAST(:currentDate AS date)) >= {…} THEN 1 ELSE 0 END) AS high FROM enquiry AS enq LEFT JOIN ( SELECT enqvd.docid AS enqDocid,COUNT(enqvd.subCode) as cntSubCode FROM enquiryVendorDetail enqvd GROUP BY enqvd.docid ) vEnqvd ON vEnqvd.enqDocid = enq.docid LEFT JOIN ( SELECT vQd.enquiryDocId,COUNT(q.subCode) as cntSubCode FROM quotation q LEFT JOIN ( SELECT qd.docid,qd.enquiryDocId FROM quotationDetail qd GROUP BY qd.docid,qd.enquiryDocId ) vQd on vQd.docId = q.docid WHERE vQd.enquiryDocId IS NOT NULL GROUP BY vQd.enquiryDocId ) vQd ON vQd.enquiryDocId = enq.docid LEFT JOIN voucher_type as vt on enq.v_Type = vt.v_Type WHERE vt.category IN ( {…} ) AND ISNULL(vQd.cntSubCode,0)<ISNULL(vEnqvd.cntSubCode,0) AND ISNULL(enq.approvedBy,'') <> '' AND enq.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:currentDate` ← `DmServer.getServerTimestamp()`
- Other calls: `HIGH_PRIORITYDAYS.getDays`, `LOW_PRIORITYDAYS.getDays`, `MEDIUM_PRIORITYDAYS.getDays`

### `getTxtHEnquiryNo`
`public DmResultPageing<List<TxtHEnquiryNo_Mdl>> getTxtHEnquiryNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT enq.docId, enq.recId as enqRecId, enq.v_Date as enqDate, vt.category, enq.v_No FROM enquiry AS enq left join voucher_Type as vt on vt.v_Type = enq.v_Type where enq.site_Code = :site_Code and enq.v_Prefix = :v_Prefix and vt.category = :category`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Prefix` ← `DmServer.getSsnVoucherPrefix(this)`; `:category` ← `new DmVoucherCategory().GENERAL_ENQUIRY`

### `getTxtHSupplier`
`public DmResultPageing<List<TxtHSupplier_Mdl>> getTxtHSupplier(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_type = acf.v_Type WHERE vt.category IN ( {…} )`
- SQL 2: `SELECT sg.subCode AS code , sg.name , sg.manualCode , c.cityName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( {…} ) AND sg.isActive = 1 AND sgsl.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`


### `getTxtHItem`
`public DmResultPageing<List<TxtHItemName_Mdl>> getTxtHItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , im.manualCode , im.purchaseUnit AS purchaseUnitCode , um.unit AS purchaseUnitName FROM itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN unitmast um ON im.purchaseUnit = um.code LEFT JOIN unitmast ums ON im.sku = ums.code WHERE im.IsActive = 1 AND (im.filterItemType IN (:FM, :SM) OR im.itemType = :SV OR im.isCommon = 1) AND sl.site_Code = :site_Code AND im.isQuotationMandatory = 0`
- Params: `:SV` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SV", "manualCode", "itemType", "code", cnTem`; `:FM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:SM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `checkCurrentSiteRecord`
`public DmResult<String> checkCurrentSiteRecord(String docId)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.self.client`, `paperbiz.dashBoard.enquiry.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.dashboardCardStrip.client`, `paperbiz.selfToolkit.dashboardCardStrip.shared`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
