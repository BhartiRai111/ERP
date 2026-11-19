# pb-self-toolkit / purchaseBillApproval

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/purchaseBillApproval/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHItem, getTxtHGrnNo, getTxtHBillNo, getTxtHStatus, getTxtHCategory, getTxtHSupplierPT
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `PurchaseBillApproval`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmGridPaging | `dmGrd` | `Grd_PrpAcs, gridRows, false` |

**Grid / popup columns** (header, width, db field):
- `dmGrd`:  [Text, w30];  [Text, w25]; Bill No. [Text, w120, billNo]; Bill Date [Text, w80, billDate]; Type [Text, w80, short_Name]; Item Nature [Text, w75, itemNatureName]; Supplier [Text, w250, suppName]; Direct Supply [Text, w82]; Bifurcate Service [Text, w103]; Customer [Text, w150]; Delivery To [Text, w150]; Approved By [Text, w130, approvedBy]; Site [Text, w120, siteName]

**Lookup / suggestion / find sources (server RPC called):**
- `dmGrd` → `getFillRecords`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`walkThroughPBill.addCallBack` → `onClose()`
```java
dmGrd.reQuery();
```

`walkThroughPBillDirect.addCallBack` → `onClose()`
```java
dmGrd.reQuery();
```

`settingPopup.addCallBack` → `onOk()`
```java
purchaseApproval_Mdl = new Setting_Mdl();
purchaseApproval_Mdl = approvalEntry_Mdl;
dmGrd.reQuery();
```

`dmGrd.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(true).getFillRecords(param, helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<GrdPurchas
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdPurchaseBillApproval_Mdl>> result) {
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
return purchaseApproval_Mdl;
```
- Confirm: "Are you sure? You want to close."

### Screen `Setting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | From Date | DmTextBox | `txtFromDate` | `15, false` |
| 2 | To Date | DmTextBox | `txtToDate` | `15, false` |
| 3 | Status | DmTextHelp | `txtHStatus` | `KeyField_PrpAcs, 4, false` |
| 4 | Type | DmTextHelpMulti | `txtHCategory` | `TxtHCategoy_PrpAcs, 2` |
| 5 | Supplier | DmTextHelpMulti | `txtHSupplier` | `TxtHSupplierName_PrpAcs, 7` |
| 6 | Bill No. | DmTextHelpMulti | `txtHBillNo` | `TxtHIndentNo_PrpAcs, 7` |

Other widgets (not in tab list): `txtHMedName` (DmTextHelpMulti)

**Grid / popup columns** (header, width, db field):
- `txtHBillNo`: Bill No. [Text, w90, pbRecId]; Bill Type [Text, w80, category]; Bill Date [Text, w80, replace(convert(varchar, pbDate, 106), ' ','-')]
- `txtHCategory`: Description [Text, w150, Name]; Category [Text, w70, Category]
- `txtHSupplier`: Name [Text, w250, name]; Manual Code [Text, w100, manualCode]; City Name [Text, w100, cityName]
- `txtHMedName`: Name [Text, w0, Name]
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

`txtHSupplier.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHSupplierPT(true, null, helper, blnToEndOfFile, new AsyncCallback<DmResultPage
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

`txtHMedName.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHItem(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHMedName).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHMedName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHMedName).show();
```

`txtHMedName.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHMedName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHMedName).show();
```

`txtHMedName.addTextHelpMultiCallBack` → `getSelection()`
```java
if (blnStatus) {
```

`txtHBillNo.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHBillNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHPur
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHBillNo).show();
public void onSuccess(DmResultPageing<List<TxtHPurchaseBillNo_Mdl>> result) {
if (result.isStatus()) {
txtHBillNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBillNo).show();
```

`txtHBillNo.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHBillNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBillNo).show();
```

`txtHBillNo.addTextHelpMultiCallBack` → `getSelection()`
```java
if (blnStatus) {
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

`txtHCategory.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHCategory(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHC
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHCategory).show();
public void onSuccess(DmResultPageing<List<TxtHCategory_Mdl>> result) {
if (result.isStatus()) {
txtHCategory.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCategory).show();
```

`txtHCategory.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHCategory.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCategory).show();
```

`txtHCategory.addTextHelpMultiCallBack` → `getSelection()`
```java
if (blnStatus) {
```
- Alert: "From Date should be less than or equal to To Date."

## Files
| file | lines |
|---|---|
| client/PurchaseBillApproval.java | 429 |
| client/PurchaseBillApprovalCard.java | 140 |
| client/PurchaseBillApproval_Dsg.java | 246 |
| client/Setting.java | 323 |
| client/Setting_Dsg.java | 273 |
| server/PurchaseBillApproval_SrvImpl.java | 703 |
| shared/DefaultValue_Mdl.java | 22 |
| shared/GrdItemDetail_Mdl.java | 175 |
| shared/GrdPurchaseBillApproval_Mdl.java | 256 |
| shared/PurchaseBillApproval_Mdl.java | 253 |
| shared/PurchaseBillApproval_Srv.java | 32 |
| shared/PurchaseBillApproval_SrvAsync.java | 32 |
| shared/Setting_Mdl.java | 254 |
| shared/TxtHCategory_Mdl.java | 23 |
| shared/TxtHGRNNo_Mdl.java | 32 |
| shared/TxtHIndentNo_Mdl.java | 32 |
| shared/TxtHPurchaseBillNo_Mdl.java | 32 |
| shared/TxtHSupplierPT_Mdl.java | 33 |

## UI labels / columns (client)
`From Date`, `To Date`, `Status`, `Type`, `Supplier`, `Bill No.`

## Models (shared DTOs)
### TxtHPurchaseBillNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | billType |
| String | billDate |

### GrdItemDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | ApprovedBy |
| String | ApprovedDateTime |
| String | indNo |
| String | indDocId |
| String | isStatus |
| String | isChecked |
| String | isCancel |
| String | vSNo |
| String | indQty |
| String | unit |
| String | itemName |
| String | itemGroup |
| String | reqDate |
| String | ApprovedQuantity |
| String | ApprovalRemarks |
| String | remark |
| String | image |

### TxtHCategory_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
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
| String | ItemName |
| String | ItemCode |
| String | grnNoTag |
| String | grnNoText |
| String | supplierName |
| String | supplierCode |
| String | StatusCode |
| String | StatusName |
| String | IndDocId |
| String | IndReccId |
| String | categoryCode |
| String | categoryName |
| String | billDocIds |
| String | billReccIds |
| String | vSNo |
| String | IndDate |
| String | IndType |
| String | siteCode |
| String | siteName |
| String | approvedBy |
| String | isStatus |
| String | approvedDateTime |
| String | wtaCode |
| List<GrdPurchaseBillApproval_Mdl> | grdPurchaseBillApproval_Mdl |

### PurchaseBillApproval_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | FromDate |
| String | ToDate |
| String | ItemName |
| String | ItemCode |
| String | ItemGroupName |
| String | ItemGroupCode |
| String | DepartmentName |
| String | DepartmentCode |
| String | StatusCode |
| String | StatusName |
| String | IndenterName |
| String | IndenterCode |
| String | IndDocId |
| String | IndReccId |
| String | IndentDocIds |
| String | IndentReccIds |
| String | vSNo |
| String | IndDate |
| String | IndType |
| String | siteCode |
| String | siteName |
| String | approvedBy |
| String | isStatus |
| String | approvedDateTime |
| String | wtaCode |
| List<GrdItemDetail_Mdl> | grdItemDetail_Mdl |
| List<GrdPurchaseBillApproval_Mdl> | grdPurchaseBillApproval_Mdl |

### GrdPurchaseBillApproval_Mdl (extends `FullPaperProperty_Mdl implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | wtaCode |
| String | billNo |
| String | billDate |
| String | itemNatureCode |
| String | itemNatureName |
| String | vtypeTag |
| String | vtypeText |
| String | vCat |
| String | supplierTag |
| String | supplierText |
| String | SiteCode |
| String | SiteName |
| String | ApprovedBy |
| String | Approval |
| String | isStatus |
| String | isChecked |
| String | currentUser |
| String | vSNo |
| String | isView |
| String | directSupply |
| String | bifurcateService |
| String | customerName |
| String | customerCode |
| String | deliveryToName |
| String | deliveryToCode |
| List<GrdItemDetail_Mdl> | grdItemDetails |

### TxtHSupplierPT_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityName |

### TxtHGRNNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | challanNo |
| String | challanDate |

### TxtHIndentNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | indentType |
| String | indentDate |

## RPC interface
| method | params | returns |
|---|---|---|
| getFillRecords | Setting_Mdl param, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdPurchaseBillApproval_Mdl>> |
| getTxtHItem | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGrnNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHCategory | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHCategory_Mdl>> |
| getTxtHBillNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHPurchaseBillNo_Mdl>> |
| getTxtHStatus | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| setApproval | String approvedBy, boolean isApprove, String docId | DmResult<String> |
| getCardDetail |  | DmResult<CardDetail_Mdl> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| getTxtHSupplierPT | boolean flagFilterByVoucherCategory, String v_Type, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHSupplierPT_Mdl>> |

## Server `PurchaseBillApproval_SrvImpl.java` (703 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.xNull`

### `getFillRecords`
`public DmResultPageing<List<GrdPurchaseBillApproval_Mdl>> getFillRecords(Setting_Mdl param, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT pb.docId as billDocId,pb.recId as billNo,pb.wtaCode,pb.approvedBy,format(pb.approvalDateTime,'dd-MMM-yyyy HH.mm') as approvalDateTime , pb.preparedBy,pb.subCode,sg.name AS suppName,pb.v_Date as billDate , pb.v_Type,vt.short_Name,vt.description, vt.category AS vCat , itemNature AS itemNatureCode,itn.name AS itemNatureName , sm.name as siteName , case when isNull(pb.isDirectSupply,'') = 0 then 'No' else 'Yes' end as directSupply , case when isNull(pb.isBifurcateService,'') = 0 then 'No' else 'Yes' end as bifurcateService , pb.customerCode , sgc.name as customerName , sgdel.name as deliveryToName,pb.deliveryToCode FROM pBill pb LEFT JOIN voucher_Type AS vt ON vt.v_Type=pb.v_Type LEFT JOIN subGroup AS sg ON sg.subCode=pb.subCode LEFT JOIN itemNature itn ON itn.code = pb.itemNature LEFT JOIN siteMast sm on sm.code = pb.site_Code LEFT JOIN subGroup sgc ON sgc.subCode = pb.customerCode LEFT JOIN subGroup sgdel ON sgdel.subCode = pb.deliveryToCode {…} WHERE {…}`
- Params: `:fromv_Date` ← `DmServer.convertDate(param.getFromDate())`; `:tov_Date` ← `DmServer.convertDate(param.getToDate())`

### `getTxtHItem`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code, im.name FROM itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code WHERE im.isActive = 1 AND (im.filterItemType = :filterItemType OR im.isCommon = 1) AND sl.site_Code = :site_Code`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("PH", "manualCode", "itemType", "code", cnTem`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHGrnNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGrnNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT Max(pc.docId) as code, Max(pc.recId) as Name FROM pBill AS pb left join pBillDetail pbd on pbd.docId = pb.docId LEFT JOIN pChallan pc ON pc.docId=pbd.pcDocId left join voucher_Type as vt on vt.v_Type = pb.v_Type where pb.site_Code = ' {…} ' and vt.category IN ( {…} ) group by pc.docId`

### `getTxtHBillNo`
`public DmResultPageing<List<TxtHPurchaseBillNo_Mdl>> getTxtHBillNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT pb.docId, pb.recId as pbRecId, pb.v_Date as pbDate, vt.category FROM pBill AS pb left join voucher_Type as vt on vt.v_Type = pb.v_Type where vt.category in ( {…} ) and pb.site_Code = ' {…} '`

### `getTxtHStatus`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHStatus(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHCategory`
`public DmResultPageing<List<TxtHCategory_Mdl>> getTxtHCategory(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select description ,category AS name,v_Type AS code from voucher_Type where category IN( {…} )`

### `getCardDetail`
`public DmResult<CardDetail_Mdl> getCardDetail()`

- SQL 1: `Select count(pb.docId) as cnt, sum(case when DATEDIFF(day, pb.v_Date, CAST(getdate() AS date)) <= {…} then 1 else 0 end) as low, sum(case when DATEDIFF(day, pb.v_Date, CAST(getdate() AS date)) > {…} and DATEDIFF(day, pb.v_Date, CAST(getdate() AS date)) <= {…} then 1 else 0 end) as medium, sum(case when DATEDIFF(day, pb.v_Date, CAST(getdate() AS date)) >= {…} then 1 else 0 end) as high from pBill as pb left join voucher_type as vt on pb.v_Type = vt.v_Type left join( select pbd.docId from pBillDetail as pbd where pbd.isStatus = 0 group by pbd.docId ) as pbdd on pbdd.docId = pb.docId LEFT JOIN walkThroughFootPrint wtfp on wtfp.wtaCode = pb.wtaCode and wtfp.isStatus = 0 where pbdd.docId is not null and isNull(pb.approvedBy,'') = '' and vt.category IN ( {…} ) AND isNull(wtfp.linkedUser,' {…} ,') like '% {…} ,%' and pb.site_Code = :site_Code AND isNull(pb.cancelledBy,'') = ''`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `HIGH_PRIORITYDAYS.getDays`, `LOW_PRIORITYDAYS.getDays`, `MEDIUM_PRIORITYDAYS.getDays`

### `setApproval`
`public DmResult<String> setApproval(String approvedBy, boolean isApprove, String docId)`

- Other calls: `STApprovals.generalPurchaseBillApproval`

### `walkThrough`
`public DmResult<String> walkThrough(WalkThrough_Mdl walkThrough)`

- Other calls: `STApprovals.generalPurchaseBillApproval`, `WalkThrough_Server.walkThrough`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`


### `getTxtHSupplierPT`
`public DmResultPageing<List<TxtHSupplierPT_Mdl>> getTxtHSupplierPT(boolean flagFilterByVoucherCategory, String v_Type, DmHelper pageing, boolean blnToEndOfFile)`


### `getSupplierDetail`
`private List<TxtHSupplierPT_Mdl> getSupplierDetail(String vendorCode, boolean flagFilterByVoucherCategory, String v_Type, DmHelper pageing, boolean blnToEndOfFile, Connection cnTemp) throws Exception`

- SQL 1: `SELECT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_type = acf.v_Type WHERE vt.category IN ( {…} )`
- SQL 2: `SELECT acf.Nature FROM AcFilteration acf WHERE acf.v_Type = :v_Type`
- SQL 3: `SELECT sg.subCode AS code , sg.name , sg.manualCode , c.cityName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( {…} ) AND sg.isActive = 1 AND sgsl.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vendorCode` ← `vendorCode`; `:v_Type` ← `DmShared.iNull(v_Type).toString()`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.dashboardCardStrip.client`, `paperbiz.selfToolkit.dashboardCardStrip.shared`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.purchaseBillApproval.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
