# pb-dashboard / generalGrn

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/dashBoard/src/generalGrn/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHGrnNo, getTxtHSupplier, getTxtHYesNo, getTxtHLocation
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `GeneralGrn`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmGridPaging | `gridGrnDetail` | `GrdPaging_PrpAcs, gridRows, false` |

Other widgets (not in tab list): `txtSearchValue` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `gridGrnDetail`:  [Text, w30];  [Text, w30]; GRN No. [Text, w120, pcRecId]; Short Name [Text, w120, vtShortName]; GRN Date [Text, w80, replace(convert(varchar, pcV_Date, 106), ' ','-')]; Ref No. [Text, w120, pChallanNo]; Ref Date. [Text, w80, replace(convert(varchar, pChallanDate, 106), ' ','-')]; Supplier Name [Text, w250, supplierName]; GST location [Text, w120, locationName]; RCM [Text, w50, rcmApplicableName]; ITC [Text, w50, itcName]; Gate Entry No. [Text, w120, gateRecId]; Gate Date [Text, w80, gateEntryDate]; Transporter [Text, w250, rcmApplicableName]; TP Payment BY [Text, w150, tpPaymentByName]; Vehicle Type [Text, w120, vehicleTypeName]; Vehicle No. [Text, w120, vehicleNo]; E-Way No [Text, w120, EWayBillNo]; E-Way Date [Text, w80, EWayBillDt]; LR No. [Text, w120, LRNo]; LR Date [Text, w80, LRDate]; Remark [Text, w250, remark]

**Lookup / suggestion / find sources (server RPC called):**
- `gridGrnDetail` → `getGrnDetail`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`settingPopup.addGRNCallBack` → `onOk()`
```java
searchValue_Mdl = new SearchValue_Mdl();
searchValue_Mdl = sm;
gridGrnDetail.reQuery();
```

`gridGrnDetail.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(false).getGrnDetail(searchValue_Mdl, helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<G
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdGeneralGrn_Mdl>> result) {
if (result.isStatus()) {
gridGrnDetail.setData(result.getData(), result.getPaging());
for (HashMap<String, String> kf : keyFields) {
for (GrdGeneralGrn_Mdl grdGrn_Mdl : gridGrnDetail.getDmGrid().getGrid().getStore().getAll()) {
if (DmShared.xNull(kf.get("docid")).equalsIgnoreCase(DmShared.xNull(grdGrn_Mdl.getSharedKeyFieldCode()))) {
grdGrn_Mdl.setIsChecked("1");
...
```

`gridGrnDetail.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
gridGrnDetail.setData(result.getData(), result.getPaging());
for (HashMap<String, String> kf : keyFields) {
for (GrdGeneralGrn_Mdl grdGrn_Mdl : gridGrnDetail.getDmGrid().getGrid().getStore().getAll()) {
if (DmShared.xNull(kf.get("docid")).equalsIgnoreCase(DmShared.xNull(grdGrn_Mdl.getSharedKeyFieldCode()))) {
grdGrn_Mdl.setIsChecked("1");
gridGrnDetail.getDmGrid().commitChanges();
```

`gridGrnDetail.addCallBack` → `getParam()`
```java
return searchValue_Mdl;
```
- Confirm: "Are you sure? You want to close."
- Alert: "Please select GRN from same Voucher"
- Alert: "Please select GRN from same supplier"
- Alert: "Please select GRN from same Site"
- Alert: "Please select GRN from same Gst Transaction"
- Alert: "Please select GRN from same ITC"
- Alert: "Please select GRN from same RCM"
- Alert: "Please select GRN from same Bifurcate Service."
- Alert: "Please select the GRN."

### Screen `Setting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | From Date | DmTextBox | `txtFromDate` | `15, false` |
| 2 | To Date | DmTextBox | `txtToDate` | `15, false` |
| 3 | Supplier | DmTextHelpMulti | `txtHSupplier` | `TxtHSupplier_PrpAcs, 5` |
| 4 | Tranporter | DmTextHelpMulti | `txtHTransporter` | `TxtHSupplier_PrpAcs, 5` |
| 5 | RCM | DmTextHelp | `txtHRCMApplicable` | `KeyField_PrpAcs, 3, false` |
| 6 | ITC | DmTextHelp | `txtHItc` | `com.google.gwt.core.shared.KeyField_PrpAcs, 4, true, false` |
| 7 | GRN No. | DmTextHelpMulti | `txtHGRN` | `KeyField_PrpAcs1, 7` |

Other widgets (not in tab list): `txtHLocation` (DmTextHelpMulti)

**Grid / popup columns** (header, width, db field):
- `txtHLocation`: Name [Text, w0, Name]
- `txtHSupplier`: Name [Text, w0, name]; Manual Code [Text, w100, manualCode]; City [Text, w150, cityName]
- `txtHTransporter`: Name [Text, w0, name]; Manual Code [Text, w100, manualCode]; City [Text, w150, cityName]
- `txtHGRN`: GRN No. [Text, w120, grnNo]; GRN Type [Text, w100, category]; GRN Date [Text, w100, v_Date]
- `txtHItc`: Type [Text, w0, Name]
- `txtHRCMApplicable`: Name [Text, w0]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHItc` → `getTxtHYesNo`
- `txtHRCMApplicable` → `getTxtHYesNo`

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

`txtHTransporter.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHSupplier(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHS
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHTransporter).show();
public void onSuccess(DmResultPageing<List<TxtHSupplier_Mdl>> result) {
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

`txtHTransporter.addTextHelpMultiCallBack` → `getSelection()`
```java
if (blnStatus) {
```

`txtHLocation.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHLocation(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHLocation).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHLocation.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHLocation).show();
```

`txtHLocation.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHLocation.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHLocation).show();
```

`txtHItc.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyField
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItc).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHItc.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItc).show();
```

`txtHItc.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItc.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItc).show();
```

`txtHRCMApplicable.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyField
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHRCMApplicable).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHRCMApplicable.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHRCMApplicable).show();
```

`txtHRCMApplicable.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHRCMApplicable.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHRCMApplicable).show();
```

`txtHGRN.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHGrnNo(widgetName,helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<Li
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGRN).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHGRN.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGRN).show();
```

`txtHGRN.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHGRN.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGRN).show();
```

`txtHGRN.addTextHelpMultiCallBack` → `getSelection()`
```java
if (blnStatus) {
```
- Alert: "From Date should be less than or equal to To Date."

## Files
| file | lines |
|---|---|
| client/GeneralGrn.java | 562 |
| client/GeneralGrnCard.java | 130 |
| client/GeneralGrn_Dsg.java | 356 |
| client/Setting.java | 340 |
| client/Setting_Dsg.java | 293 |
| server/GeneralGrn_SrvImpl.java | 665 |
| shared/DefaultValue_Mdl.java | 22 |
| shared/GeneralGrn_Srv.java | 32 |
| shared/GeneralGrn_SrvAsync.java | 31 |
| shared/GrdGeneralGrn_Mdl.java | 321 |
| shared/SearchValue_Mdl.java | 141 |
| shared/TxtHSupplier_Mdl.java | 33 |

## UI labels / columns (client)
`Action`, `From Date`, `To Date`, `Supplier`, `Tranporter`, `RCM`, `ITC`, `GRN No.`

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
| String | locationCode |
| String | locationName |
| String | supplierName |
| String | supplierCode |
| String | transporterName |
| String | transporterCode |
| String | itcCode |
| String | itcName |
| String | rcmCode |
| String | rcmName |
| String | grnDocId |
| String | grnRecId |

### GrdGeneralGrn_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | pcV_Date |
| String | remark |
| String | isChecked |
| String | isView |
| String | supplierCode |
| String | supplierName |
| String | supplierPartyType |
| String | refNo |
| String | refDate |
| String | gstLocationCode |
| String | gstLocationName |
| String | itcCode |
| String | itcName |
| String | rcmCode |
| String | rcmName |
| String | siteCode |
| String | bifurcateServiceCode |
| String | bifurcateServiceName |
| String | transporterCode |
| String | transporterName |
| String | gateNo |
| String | gateDate |
| String | tpPaymentByCode |
| String | tpPaymentByName |
| String | vehicleTypeCode |
| String | vehicleTypeName |
| String | vehicleNumber |
| String | ewayBillNo |
| String | ewayBillDate |
| String | lrNo |
| String | lrDate |
| String | vtypeCode |
| String | vtypeName |
| String | vtypeShortName |

## RPC interface
| method | params | returns |
|---|---|---|
| getGrnDetail | SearchValue_Mdl searchValue_Mdl, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdGeneralGrn_Mdl>> |
| getCardDetail |  | DmResult<CardDetail_Mdl> |
| getTxtHGrnNo | String widgetName,DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSupplier | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHSupplier_Mdl>> |
| getTxtHYesNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHLocation | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| validateSite | List<HashMap<String,String>> keyLst | DmResult<List<HashMap<String,String>>> |

## Server `GeneralGrn_SrvImpl.java` (665 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.xNull`

### `getGrnDetail`
`public DmResultPageing<List<GrdGeneralGrn_Mdl>> getGrnDetail(SearchValue_Mdl param, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT pcd.docId AS pcDocId , MAX(pc.recId) AS pcRecId , MAX(pc.v_Date) AS pcV_Date , MAX(pc.pChallanNo) AS pChallanNo , MAX(pc.pChallanDate) AS pChallanDate , MAX(pc.subCode) AS supplierCode , MAX(pc.site_code) AS siteCode, MAX(concat(ssg.name,', ',sc.cityName)) AS supplierName , MAX(pc.locationCode) AS locationCode, MAX(lm.name) AS locationName, MAX(pc.transporterCode) AS transporterCode, MAX(concat(tsg.name,', ',tc.cityName)) AS transporterName , MAX(ssg.partyType) AS partyType , MAX(pc.rcmApplicable) AS rcmApplicableCode , MAX(IIF(ISNULL(pc.rcmApplicable,:zero)= :one ,:yes,:no)) AS rcmApplicableName , MAX(pc.isBifurcateService) AS isBifurcateServiceCode , MAX(IIF(ISNULL(pc.isBifurcateService,0) = 1,'Yes','No')) AS isBifurcateServiceName , MAX(pc.isItc) AS itcCode , MAX(IIF(ISNULL(pc.isItc,:zero)= :one ,:yes,:no)) AS itcName , MAX(pc.remark) AS remark , MAX(gio.recId) as gateRecId , MAX(gio.v_Date) as gateEntryDate , MAX(pc.vehicleTypeCode) AS vehicleTypeCode , MAX(vm.name) AS vehicleTypeName, MAX(pc.vehicleNo) AS vehicleNo , MAX(pc.tpPaymentBy) AS tpPaymentByCode , MAX(IIF(ISNULL(pc.tpPaymentBy,:zero) = :companyCode,:companyName,:partyName )) AS tpPaymentByName, MAX(pc.EWayBillNo) AS EWayBillNo , MAX(pc.EWayBillDt) AS EWayBillDt , MAX(pc.LRNo) as LRNo , MAX(pc.LRDate) as LRDate, MAX(vt.v_Type) AS vTypeCode, MAX(vt.description) AS vTypeName, MAX(vt.short_name) AS vtShortName FROM pChallanDetail pcd LEFT JOIN pChallan pc ON pcd.docId = pc.docId LEFT JOIN voucher_Type vt ON pc.v_Type = vt.v_Type LEFT JOIN subGroup ssg ON ssg.subcode = pc.subCode LEFT JOIN subGroup tsg ON tsg.subcode = pc.transporterCode LEFT JOIN city sc ON sc.cityCode = ssg.cityCode LEFT JOIN city tc ON tc.cityCode = tsg.cityCode LEFT JOIN locationMast lm ON lm.code = pc.locationCode LEFT JOIN gateInOut gio ON gio.docId = pc.gateDocId LEFT JOIN vehicleTypeMast vm on vm.code = pc.vehicleTypeCode WHERE vt.category IN (:GPCHL,:GPCHU,:SPCHL,:SPCHU) AND pc.site_Code = :site_Code AND isNull(pc.approvedBy, :blank) <> :blank AND ISNULL(pcd.isFooter, :zero) = :zero AND pcd.isStatus = :zero {…} GROUP BY pcd.docid`
- Params: `:GPCHL` ← `pvc.GENERAL_PURCHASE_CHALLAN`; `:GPCHU` ← `pvc.GENERAL_PURCHASE_CHALLAN_UNREGISTERED`; `:SPCHL` ← `pvc.SERVICE_PURCHASE_CHALLAN`; `:SPCHU` ← `pvc.SERVICE_PURCHASE_CHALLAN_UNREGISTERED`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:companyName` ← `STEnum.TransporterPaymentBy.COMPANY.getValue()`; `:partyName` ← `STEnum.TransporterPaymentBy.PARTY.getValue()`; `:blank` ← `""`; `:yes` ← `"Yes"`; `:no` ← `"No"`; `:fromv_Date` ← `DmServer.convertDate(param.getFromDate())`; `:tov_Date` ← `DmServer.convertDate(param.getToDate())`; `:isItc` ← `DmShared.xNull(param.getItcCode())`; `:rcmApplicable` ← `DmShared.xNull(param.getRcmCode())`
- Other calls: `COMPANY.getId`, `COMPANY.getValue`, `PARTY.getValue`

### `getCardDetail`
`public DmResult<CardDetail_Mdl> getCardDetail()`

- SQL 1: `select count( distinct pc.docId) as cnt , sum(case when DATEDIFF(day, pc.v_Date, CAST(:currentDate AS date)) <= {…} then 1 else 0 end) as low, sum(case when DATEDIFF(day, pc.v_Date, CAST(:currentDate AS date)) > {…} and DATEDIFF(day, pc.v_Date, CAST(:currentDate AS date)) <= {…} then 1 else 0 end) as medium, sum(case when DATEDIFF(day, pc.v_Date, CAST(:currentDate AS date)) >= {…} then 1 else 0 end) as high from pChallan as pc left join voucher_type as vt on pc.v_Type = vt.v_Type left join( select pcd.docId from pchallanDetail as pcd where pcd.isStatus = 0 AND ISNULL(pcd.isFooter, 0) = 0 AND ISNULL(pcd.acceptQty,0) - ISNULL(pcd.billedQty,0) > 0 group by pcd.docId ) as pcdd on pcdd.docId = pc.docId WHERE pcdd.docId is not null AND vt.category IN (:GPCHL,:GPCHU) AND pc.site_Code = :site_Code AND isNull(pc.approvedBy,'') <> ''`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:currentDate` ← `DmServer.getServerTimestamp()`; `:GPCHL` ← `pvc.GENERAL_PURCHASE_CHALLAN`; `:GPCHU` ← `pvc.GENERAL_PURCHASE_CHALLAN_UNREGISTERED`
- Other calls: `HIGH_PRIORITYDAYS.getDays`, `LOW_PRIORITYDAYS.getDays`, `MEDIUM_PRIORITYDAYS.getDays`

### `getTxtHGrnNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGrnNo(String widgetName, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select pc.docId, pc.recId as grnNo, pc.v_No, vt.category, pc.v_Date from pChallan as pc LEFT JOIN voucher_Type as vt on pc.v_Type = vt.v_Type WHERE pc.site_Code IN ( {…} :site_Code {…} ) AND vt.category IN (:GPCHL,:GPCHU)`
- Params: `:GPCHL` ← `pvc.GENERAL_PURCHASE_CHALLAN`; `:GPCHU` ← `pvc.GENERAL_PURCHASE_CHALLAN_UNREGISTERED`

### `getTxtHSupplier`
`public DmResultPageing<List<TxtHSupplier_Mdl>> getTxtHSupplier(DmHelper helper, boolean blnToEndOfFile)`


### `getSupplierDetails`
`private List<TxtHSupplier_Mdl> getSupplierDetails(DmHelper helper, boolean blnToEndOfFile, Connection cnTemp) throws Exception`

- SQL 1: `SELECT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_type = acf.v_Type WHERE vt.category IN (:GPCHL,:GPCHU)`
- SQL 2: `SELECT sg.subCode AS code , sg.name , sg.manualCode , c.cityName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( {…} ) AND sg.isActive = 1 AND sgsl.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:GPCHL` ← `pvc.GENERAL_PURCHASE_CHALLAN`; `:GPCHU` ← `pvc.GENERAL_PURCHASE_CHALLAN_UNREGISTERED`

### `getTxtHYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHLocation`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHLocation(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT s.isUt FROM siteMast sm LEFT JOIN stateMast s on s.numericCode = sm.stateNumericCode WHERE sm.code = :siteCode`
- SQL 2: `SELECT lm.code, lm.name from locationMast lm WHERE lm.isActive = 1 AND lm.manualCode <> :manualCode`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:manualCode` ← `DmEnum.DmGstLocation_SystemDefined.INTRA_STATE.getManualCode()`; `:manualCode` ← `DmEnum.DmGstLocation_SystemDefined.UNION_TERRITORY.getManualCode()`
- Other calls: `INTRA_STATE.getManualCode`, `UNION_TERRITORY.getManualCode`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`


### `validateSite`
`public DmResult<List<HashMap<String, String>>> validateSite(List<HashMap<String, String>> keyLst)`

- Errors: "Record belongs to other site.<br>This cannot be used in current site."

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmChart.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `paperbiz.dashBoard.generalGrn.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.dashboardCardStrip.client`, `paperbiz.selfToolkit.dashboardCardStrip.shared`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| validateSite | `if(!DmShared.xNull(keyfield.get("siteCode")).equalsIgnoreCase(DmServer.getSsnSiteCode(this)))` | "Record belongs to other site.<br>This cannot be used in current site." |

## Notes (hand-written)
_No hand-written notes yet._
