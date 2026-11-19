# pb-job-work / displaySetting

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/jobWork/src/displaySetting/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHSupplier, getTxtHSupplierCity, getTxtHVoucherType, getTxtHVoucherNo, getTxtHItem, getTxtHItemCat, getTxtHItemGroup
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `DisplaySetting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | To Date | DmTextBox | `txtFromDate` | `11, true` |
| 2 | - | DmTextBox | `txtToDate` | `11, true` |
| 3 | Site Name | DmTextHelpMulti | `txtHSite` | `KeyField_PrpAcs, 7, true` |
| 4 | Party | DmTextHelpMulti | `txtHParty` | `TxtHSupplierName_PrpAcs, 10` |
| 5 | Party City | DmTextHelpMulti | `txtHPartyCity` | `TxtHCity_PrpAcs, 10` |
| 6 | Item Name | DmTextHelpMulti | `txtHItem` | `GridItem_PrpAcs, 10` |
| 7 | Item Group | DmTextHelpMulti | `txtHItemGroup` | `KeyField_PrpAcs, 10` |
| 8 | Item Catagory | DmTextHelpMulti | `txtHItemCat` | `KeyField_PrpAcs, 10` |
| 9 | Voucher Type | DmTextHelpMulti | `txtHVoucherType` | `TxtHVoucherType_PrpAcs, 10` |
| 10 | Voucher No. | DmTextHelpMulti | `txtHVoucherNo` | `com.google.gwt.core.shared.TxtHVoucherNo_PrpAcs, 7` |
| 11 | Periodicity | DmTextHelp | `txtHPeriodicity` | `KeyField_PrpAcs, 4, true, true` |

**Grid / popup columns** (header, width, db field):
- `txtHSite`: Name [Text, w150, name]
- `txtHParty`: Party [Text, w250, name]; Manual Code [Text, w100, manualCode]; City Name [Text, w200, cityName]
- `txtHPartyCity`: City [Text, w120, cityName]; State [Text, w150, stateName]; Country [Text, w120, countryName]
- `txtHVoucherType`: Type [Text, w120, name]; Short Name [Text, w80, short_Name]
- `txtHVoucherNo`: Voucher No. [Text, w120, recId]; Type [Text, w40, short_name]; Date [Text, w80, replace(convert(varchar, v_Date, 106), ' ','-')]; Party [Text, w250, partyName]
- `txtHPeriodicity`: Periodicity [Text, w100]
- `txtHItemGroup`: Name [Text, w1, NAME]
- `txtHItemCat`: Name [Text, w1, name]
- `txtHItem`: Name [Text, w1, name]; Manual Code [Text, w100, manualCode]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHPeriodicity` → `getPerioditicity`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtHPeriodicity.setEnabled(isEnablePeriodicity)`

**`manageDisplay()` logic (condensed code):**
```java
txtHPeriodicity.setEnabled(isEnablePeriodicity);
txtHPeriodicity.setMandatory(isEnablePeriodicity);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHSite.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getSite(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExten
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

`txtHPeriodicity.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getPerioditicity(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyF
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHPeriodicity).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHPeriodicity.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPeriodicity).show();
```

`txtHPeriodicity.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHPeriodicity.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPeriodicity).show();
```

`txtHParty.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHSupplier(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHP
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHParty).show();
public void onSuccess(DmResultPageing<List<TxtHParty_Mdl>> result) {
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

`txtHPartyCity.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHSupplierCity(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<K
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHPartyCity).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHPartyCity.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPartyCity).show();
```

`txtHPartyCity.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHPartyCity.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPartyCity).show();
```

`txtHVoucherType.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHVoucherType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHVoucherType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHVoucherType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHVoucherType).show();
```

`txtHVoucherType.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHVoucherType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHVoucherType).show();
```

`txtHVoucherNo.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHVoucherNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtH
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHVoucherNo).show();
public void onSuccess(DmResultPageing<List<TxtHVoucherNo_Mdl>> result) {
if (result.isStatus()) {
txtHVoucherNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHVoucherNo).show();
```

`txtHVoucherNo.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHVoucherNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHVoucherNo).show();
```

`txtHItemGroup.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItemGroup(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyF
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemGroup).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHItemGroup.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemGroup).show();
```

`txtHItemGroup.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemGroup.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemGroup).show();
```

`txtHItemCat.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItemCat(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFie
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemCat).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHItemCat.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemCat).show();
```

`txtHItemCat.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemCat.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemCat).show();
```

`txtHItem.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItem(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHItem_
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItem).show();
public void onSuccess(DmResultPageing<List<TxtHItem_Mdl>> result) {
if (result.isStatus()) {
txtHItem.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItem).show();
```

`txtHItem.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItem.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItem).show();
```
- Alert: "'From Date' is mandatory."
- Alert: "'To Date' is mandatory."
- Alert: "To Date should be greater than from date!"
- Alert: "Period difference cannot be greater than 366 days."
- Alert: "Site Name is mandatory."
- Alert: "Periodicity is mandatory."

## Files
| file | lines |
|---|---|
| client/DisplaySetting.java | 521 |
| client/DisplaySetting_Dsg.java | 424 |
| server/DisplaySetting_SrvImpl.java | 632 |
| shared/AcntLedger_Mdl.java | 31 |
| shared/DisplayRoute_Mdl.java | 106 |
| shared/DisplaySetting_Mdl.java | 224 |
| shared/DisplaySetting_Srv.java | 29 |
| shared/DisplaySetting_SrvAsync.java | 29 |
| shared/TxtHItem_Mdl.java | 68 |
| shared/TxtHParty_Mdl.java | 31 |
| shared/TxtHVoucherNo_Mdl.java | 59 |

## UI labels / columns (client)
`From Date`, `To Date`, `Site Name`, `Party`, `Party City`, `Item Name`, `Item Group`, `Item Catagory`, `Voucher Type`, `Voucher No.`, `Periodicity`

## Models (shared DTOs)
### DisplaySetting_Mdl
| type | field |
|---|---|
| String | currentSiteCode |
| String fromDate, | toDate |
| String siteCode, | siteName |
| String periodicityCode, | periodicityName |
| String partyCode, | partyName |
| String itemCode, | itemName |
| String itemGroupCode, | itemGroupName |
| String itemCatCode, | itemCatName |
| String partyCityCode, | partyCityName |
| String voucherTypeCode, | voucherType |
| String voucherNoCode, | voucherNo |

### TxtHVoucherNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | voucherType |
| String | voucherDate |
| String | voucherCategory |
| String | shortName |
| String | partyName |

### DisplayRoute_Mdl
| type | field |
|---|---|
| PageName | pageName |
| CodeType | codeType |
| String | lblHeading |
| String | clickRowIndex |
| String | clickColIndex |
| String fromDate, | toDate |
| DisplaySetting_Mdl | displaySetting_mdl |

### TxtHItem_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | itemGroup |
| String | itemCategory |
| String | unit |
| String | unitCode |
| String | manualCode |
| String | itemType |

### AcntLedger_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | city |

### TxtHParty_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityName |

## RPC interface
| method | params | returns |
|---|---|---|
| getSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getPerioditicity | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSupplier | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getTxtHSupplierCity | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVoucherType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVoucherNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucherNo_Mdl>> |
| getTxtHItem | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getTxtHItemCat | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| fillSettings |  | DmResult<DisplaySetting_Mdl> |

## Server `DisplaySetting_SrvImpl.java` (632 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.getSsnSiteName`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.xNull`

### `fillSettings`
`public DmResult<DisplaySetting_Mdl> fillSettings()`


### `getSite`
`public DmResultPageing<List<KeyFieldExtended>> getSite(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm WHERE sm.code in ( {…} :code {…} )`

### `getPerioditicity`
`public DmResultPageing<List<KeyFieldExtended>> getPerioditicity(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHSupplier`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHSupplier(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code, sg.name, sg.manualCode, c.cityName FROM subGroup sg LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN (SELECT DISTINCT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_Type = acf.v_Type WHERE vt.category IN (:GPB,:GPBU,:PBIL,:PBILU,:GPBD,:GPBDU,:SPB,:SPBU,:GPR,:GPRU,:PRTN,:PRTNU,:SPRTN,:SPRNU) ) AS ACF ON ACF.nature = sg.nature WHERE ACF.nature IS NOT NULL`
- Params: `:GPB` ← `pvc.GENERAL_PURCHASE_BILL`; `:GPBU` ← `pvc.GENERAL_PURCHASE_BILL_UNREGISTERED`; `:PBIL` ← `pvc.PURCHASE_BILL`; `:PBILU` ← `pvc.PURCHASE_BILL_UNREGISTERED`; `:GPBD` ← `pvc.GENERAL_PURCHASE_BILL_DIRECT`; `:GPBDU` ← `pvc.GENERAL_PURCHASE_BILL_DIRECT_UNREGISTERED`; `:SPB` ← `pvc.SERVICE_PURCHASE_BILL`; `:SPBU` ← `pvc.SERVICE_PURCHASE_BILL_UNREGISTERED`; `:GPR` ← `pvc.GENERAL_PURCHASE_RETURN`; `:GPRU` ← `pvc.GENERAL_PURCHASE_RETURN_UNREGISTERED`; `:PRTN` ← `pvc.PURCHASE_RETURN`; `:PRTNU` ← `pvc.PURCHASE_RETURN_UNREGISTERED`; `:SPRTN` ← `pvc.SERVICE_PURCHASE_RETURN`; `:SPRNU` ← `pvc.SERVICE_PURCHASE_RETURN_UNREGISTERED`

### `getTxtHSupplierCity`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSupplierCity(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT c.cityCode , c.cityName, s.name as stateName, cnt.name AS countryName FROM city c LEFT JOIN stateMast s ON s.code = c.stateCode LEFT JOIN country cnt ON cnt.code = c.countryCode`

### `getTxtHVoucherType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVoucherType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type, vt.description AS name, vt.short_Name FROM voucher_type vt WHERE vt.category IN (:JWC,:JWORD,:JWMR,:JWCB)`
- Params: `:JWC` ← `pvc.JOB_WORK_CHALLAN`; `:JWORD` ← `pvc.JOB_WORK_ORDER`; `:JWMR` ← `pvc.MATERIAL_RECEIVE`; `:JWCB` ← `pvc.CONTRACTOR_BILL`

### `getTxtHVoucherNo`
`public DmResultPageing<List<TxtHVoucherNo_Mdl>> getTxtHVoucherNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select pb.docId, pb.recId, pb.v_Date, pb.v_No, pb.v_Prefix, vt.short_name , sg.name as supplierName, sm.manualCode AS siteManualCode , CASE WHEN pb.site_Code = {…} THEN 1 ELSE 2 END as tempSr FROM jobWorkChallan pb LEFT JOIN voucher_Type vt On vt.v_Type = pb.v_Type LEFT JOIN subGroup sg On sg.subCode = pb.partyCode LEFT JOIN siteMast sm ON sm.code = pb.site_Code WHERE vt.category IN (:JWC,:JWORD,:JWMR,:JWCB) AND pb.site_Code IN( {…} :siteList {…} ) AND ISNULL(pb.approvedBy,'')<>''`
- Params: `:JWC` ← `pvc.JOB_WORK_CHALLAN`; `:JWORD` ← `pvc.JOB_WORK_ORDER`; `:JWMR` ← `pvc.MATERIAL_RECEIVE`; `:JWCB` ← `pvc.CONTRACTOR_BILL`

### `getTxtHItem`
`public DmResultPageing<List<TxtHItem_Mdl>> getTxtHItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select im.code,im.name,um.code as unitCode,um.unit,im.manualCode from itemMast as im left join unitmast as um on im.sku=um.code WHERE (im.filterItemType = :filterItemType or im.isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemCat`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemCat(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select ic.code, ic.name from itemcat ic where (ic.filterItemType = :filterItemType or ic.isCommon=1 )`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT CODE, NAME, filteritemType FROM itemgroup where (filterItemType = :filterItemType or isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getPosition`
`public DmResultPageing<List<KeyFieldExtended>> getPosition(DmHelper helper, boolean blnToEndOfFile)`


### `getCostCenter`
`public DmResultPageing<List<KeyFieldExtended>> getCostCenter(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT code, name FROM costCenterMast WHERE (ISNULL(underCostCenter, 0) = 0)`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `paperbiz.jobWork.displaySetting.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
