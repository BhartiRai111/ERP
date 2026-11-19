# pb-dashboard / pBillDisplay

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/dashBoard/src/pBillDisplay/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHSupplierPT, getTxtHPBillNo, getTxtHType, getTxtHSite
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `PBillDisplayl`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmGridPaging | `gridSaleInvoice` | `com.google.gwt.core.shared.GrdPaging_PrpAcs, 23, false` |

Other widgets (not in tab list): `txtSearchValue` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `gridSaleInvoice`: Detail [Text, w50]; PB No. [Text, w120, recId]; PB Date [Text, w80, replace(convert(varchar, v_Date, 106), ' ','-')]; Type [Text, w70, short_Name]; Party Bill No. [Text, w100, partyBillNo]; Party Bill Date [Text, w100, replace(convert(varchar, partyBillDate, 106), ' ','-')]; Party [Text, w150, partyName]; Transporter [Text, w130, transporterName]; Mode [Text, w50, transportMode]; Gate Entry No. [Text, w100, gateNo]; Net Amount [Text, w100, oh_Amt_net]

### Screen `Setting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | From Date | DmTextBox | `txtFromDate` | `15, false` |
| 2 | To Date | DmTextBox | `txtToDate` | `15, false` |
| 3 | Type | DmTextHelpMulti | `txtHPurchaseType` | `KeyField_PrpAcs, 4` |
| 4 | Supplier | DmTextHelpMulti | `txtHParty` | `TxtHSupplierName_PrpAcs, 7` |
| 5 | Purchase Bill No. | DmTextHelpMulti | `txtHPBill` | `TxtHPurchaseBill_PrpAcs, 7` |
| 6 | Transporter | DmTextHelpMulti | `txtHTransporter` | `TxtHTransporter_PrpAcs, 7` |
| 7 | Site | DmTextHelpMulti | `txtHSite` | `KeyField_PrpAcs, 5, true` |

**Grid / popup columns** (header, width, db field):
- `txtHPBill`: Purchase Bill No. [Text, w120, recId]; Purchase Type [Text, w90, short_Name]; Purchase Date [Text, w90, replace(convert(varchar, v_Date, 106), ' ','-')]
- `txtHParty`: Name [Text, w220, supplierName]; Manual Code [Text, w100, manualCode]; City Name [Text, w100, cityName]
- `txtHTransporter`: Name [Text, w220, supplierName]; Manual Code [Text, w100, manualCode]; City Name [Text, w100, cityName]
- `txtHPurchaseType`: Name [Text, w0, name]
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

`txtHPBill.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHPBillNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHPB
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHPBill).show();
public void onSuccess(DmResultPageing<List<TxtHPBill_Mdl>> result) {
if (result.isStatus()) {
txtHPBill.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPBill).show();
```

`txtHPBill.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHPBill.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPBill).show();
```

`txtHParty.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHSupplierPT(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Txt
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
srvAsync.invokeRPC(blnCallBackWait).getTxtHSupplierPT(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Txt
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

`txtHPurchaseType.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHPurchaseType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHPurchaseType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPurchaseType).show();
```

`txtHPurchaseType.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHPurchaseType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPurchaseType).show();
```

`txtHPurchaseType.addTextHelpMultiCallBack` → `getSelection()`
```java
if (blnStatus) {
```

`txtHSite.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHSite(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
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

## Files
| file | lines |
|---|---|
| client/PBillDisplay.java | 409 |
| client/PBillDisplayl_Dsg.java | 243 |
| client/Setting.java | 307 |
| client/Setting_Dsg.java | 291 |
| server/PBillDisplay_SrvImpl.java | 457 |
| shared/CardDetail_Mdl.java | 31 |
| shared/DefaultValue_Mdl.java | 40 |
| shared/GrdPBillDisplay_Mdl.java | 132 |
| shared/PBillDisplay_Srv.java | 24 |
| shared/PBillDisplay_SrvAsync.java | 25 |
| shared/SearchValue_Mdl.java | 122 |
| shared/TxtHPBill_Mdl.java | 34 |
| shared/TxtHSupplierPT_Mdl.java | 32 |

## UI labels / columns (client)
`From Date`, `To Date`, `Type`, `Supplier`, `Purchase Bill No.`, `Transporter`, `Site`

## Models (shared DTOs)
### TxtHPBill_Mdl (extends `KeyField implements Serializable,IsSerializable`)
| type | field |
|---|---|
| String | pBillType |
| String | pBillDate |

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
| String | typeCode |
| String | typeName |
| String | billNoCode |
| String | billNo |
| String | transporterCode |
| String | transporterName |
| String | siteCode |
| String | siteName |

### GrdPBillDisplay_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | type |
| String | v_Date |
| String | partyBillNo |
| String | partyBillDate |
| String | partyName |
| String | gateNo |
| String | transportName |
| String | transportMode |
| String | approvedBy |
| String | netAmount |
| String | isView |
| String | entryName |

### TxtHSupplierPT_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityName |

### CardDetail_Mdl
| type | field |
|---|---|
| String | count |
| List<DmChart_Mdl> | listChardData |

## RPC interface
| method | params | returns |
|---|---|---|
| getFillRecords | SearchValue_Mdl searchValueMdl, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdPBillDisplay_Mdl>> |
| getTxtHPBillNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHPBill_Mdl>> |
| getTxtHSupplierPT | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHSupplierPT_Mdl>> |
| getTxtHType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Server `PBillDisplay_SrvImpl.java` (457 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerDateTime`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.xNull`

### `getFillRecords`
`public DmResultPageing<List<GrdPBillDisplay_Mdl>> getFillRecords(SearchValue_Mdl param, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select pb.docId , pb.recId , pb.v_Date , pb.partyBillDate , pb.partyBillNo , vt.short_Name , sg.name as partyName , sgt.name transporterName , pb.oh_Amt_Net , gio.recId gateNo, pb.transportMode , concat(pb.approvedBy,',',pb.approvalDateTime) as approvedBy from pBill pb LEFT JOIN voucher_Type AS vt ON vt.v_Type = pb.v_Type LEFT JOIN subGroup AS sg ON sg.subCode = pb.subCode LEFT JOIN subGroup AS sgt ON sgt.subCode = pb.transporterCode LEFT JOIN gateInOut gio ON gio.docId = pb.gateDocId where ISNULL(pb.approvedBy, '') <> '' AND ISNULL(pb.cancelledBy,'') = '' {…}`
- Params: `:fromv_Date` ← `DmServer.convertDate(param.getFromDate())`; `:tov_Date` ← `DmServer.convertDate(param.getToDate())`
- Other calls: `TransportMode.getViaKey`

### `getTxtHSupplierPT`
`public DmResultPageing<List<TxtHSupplierPT_Mdl>> getTxtHSupplierPT(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as supplierName , sg.manualCode , c.cityName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( SELECT DISTINCT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_Type = acf.v_Type WHERE vt.category in (' {…} ' ,' {…} ',' {…} ',' {…} ', ' {…} ' ,' {…} ',' {…} ',' {…} ')) AND sg.isActive = 1 AND sgsl.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`

- SQL 1: `select sm.code,sm.name AS site from siteMast sm where code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHPBillNo`
`public DmResultPageing<List<TxtHPBill_Mdl>> getTxtHPBillNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT pb.docId, pb.recId, pb.v_Date , vt.short_Name, pb.v_No FROM pbill AS pb left join voucher_Type as vt on vt.v_Type = pb.v_Type WHERE pb.site_Code = :site_Code AND vt.category in (' {…} ' ,' {…} ',' {…} ',' {…} ', ' {…} ' ,' {…} ',' {…} ',' {…} ')`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.description as name , vt.v_Type FROM voucher_Type vt WHERE vt.category in (' {…} ' ,' {…} ',' {…} ',' {…} ', ' {…} ' ,' {…} ',' {…} ',' {…} ')`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmChart.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.self.client`, `paperbiz.dashBoard.pBillDisplay.shared`, `paperbiz.dashBoard.purchaseDashBoard.shared`, `paperbiz.dashBoard.salesInvoiceDisplay.shared`, `paperbiz.projectMenu.self.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
