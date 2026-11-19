# pb-purchase / displaySetting

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/purchase/src/displaySetting/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHSupplier, getTxtHSupplierCity, getTxtHVoucherType, getTxtHPurchaseBillNo, getTxtHPurchaseReturnNo, getTxtHItemGroup, getTxtHItemCat, getTxtHItemType, getTxtHItemStatus, getTxtHOrderStatus, getTxtHItem
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `DisplaySetting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | To Date | DmTextBox | `txtFromDate` | `11, true` |
| 2 | - | DmTextBox | `txtToDate` | `11, true` |
| 3 | Site Name | DmTextHelpMulti | `txtHSite` | `KeyField_PrpAcs, 10, true` |
| 4 | Supplier | DmTextHelpMulti | `txtHSupplier` | `TxtHSupplierName_PrpAcs, 10` |
| 5 | Supplier City | DmTextHelpMulti | `txtHSupplierCity` | `TxtHSupplierCity_PrpAcs, 10` |
| 6 | Voucher Type | DmTextHelpMulti | `txtHVoucherType` | `TxtHVoucherType_PrpAcs, 11` |
| 7 | Voucher No. | DmTextHelpMulti | `txtHVoucherNo` | `com.google.gwt.core.shared.TxtHVoucher_PrpAcs, 7` |
| 8 | Return No. | DmTextHelpMulti | `txtHReturnNo` | `com.google.gwt.core.shared.TxtHReturnNo_PrpAcs, 7` |
| 9 | Periodicity | DmTextHelp | `txtHPeriodicity` | `KeyField_PrpAcs, 4, true, true` |
| 10 | Item Name | DmTextHelpMulti | `txtHItem` | `GridItem_PrpAcs, 10` |
| 11 | Item Category | DmTextHelpMulti | `txtHItemCat` | `KeyField_PrpAcs, 5` |
| 12 | Item Group | DmTextHelpMulti | `txtHItemGroup` | `KeyField_PrpAcs, 5` |
| 13 | Item Type | DmTextHelpMulti | `txtHItemType` | `TxtHItemType_PrpAcs, 6, false` |
| 14 | Item Status | DmTextHelp | `txtHItemStatus` | `KeyField_PrpAcs, 5, false` |
| 15 | Order Status | DmTextHelp | `txtHOrderStatus` | `KeyField_PrpAcs, 4, false` |
| 16 | Greater Than | DmTextBox | `txtGreaterThan` | `11, false` |
| 17 | Less Than | DmTextBox | `txtLessThan` | `11, false` |

Other widgets (not in tab list): `txtClosing` (DmTextBoxNumeric)

**Grid / popup columns** (header, width, db field):
- `txtHSite`: Name [Text, w150, name]
- `txtHSupplier`: Supplier [Text, w250, name]; Manual Code [Text, w100, manualCode]; City Name [Text, w200, cityName]
- `txtHSupplierCity`: City [Text, w120, cityName]; State [Text, w150, stateName]; Country [Text, w120, countryName]
- `txtHVoucherType`: Type [Text, w180, name]; Short Name [Text, w80, short_Name]
- `txtHVoucherNo`: Voucher No. [Text, w120, recId]; Type [Text, w40, short_name]; Date [Text, w80, replace(convert(varchar, v_Date, 106), ' ','-')]; Supplier [Text, w250, supplierName]
- `txtHReturnNo`: Return No. [Text, w120, recId]; Type [Text, w40, short_name]; Date [Text, w80, replace(convert(varchar, v_Date, 106), ' ','-')]; SupplierName [Text, w250, supplierName]
- `txtHPeriodicity`: Periodicity [Text, w100]
- `txtHItemStatus`: Status [Text, w0]
- `txtHOrderStatus`: Status [Text, w0]
- `txtHItemType`: Item Type [Text, w0, name]; Short Name [Text, w70, manualcode]
- `txtHItem`: Name [Text, w1, name]; Manual Code [Text, w100, manualCode]
- `txtHItemCat`: Name [Text, w1, name]
- `txtHItemGroup`: Name [Text, w1, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHPeriodicity` → `getPerioditicity`
- `txtHItemStatus` → `getTxtHItemStatus`
- `txtHOrderStatus` → `getTxtHOrderStatus`

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

`txtHSupplier.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHSupplier(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHP
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSupplier).show();
public void onSuccess(DmResultPageing<List<TxtHParty_Mdl>> result) {
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

`txtHSupplierCity.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHSupplierCity(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<K
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSupplierCity).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHSupplierCity.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSupplierCity).show();
```

`txtHSupplierCity.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSupplierCity.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSupplierCity).show();
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
srvAsync.invokeRPC(blnCallBackWait).getTxtHPurchaseBillNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List
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

`txtHReturnNo.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHPurchaseReturnNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<Li
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHReturnNo).show();
public void onSuccess(DmResultPageing<List<TxtHVoucherNo_Mdl>> result) {
if (result.isStatus()) {
txtHReturnNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHReturnNo).show();
```

`txtHReturnNo.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHReturnNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHReturnNo).show();
```

`txtHItemType.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItemType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHItemType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemType).show();
```

`txtHItemType.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemType).show();
```

`txtHItemType.addTextHelpMultiCallBack` → `getSelection()`
```java
if (blnStatus) {
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

`txtHItemStatus.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItemStatus(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Key
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemStatus).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHItemStatus.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemStatus).show();
```

`txtHItemStatus.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemStatus.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemStatus).show();
```

`txtHOrderStatus.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHOrderStatus(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHOrderStatus).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHOrderStatus.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHOrderStatus).show();
```

`txtHOrderStatus.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHOrderStatus.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHOrderStatus).show();
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
| client/DisplaySetting.java | 650 |
| client/DisplaySetting_Dsg.java | 612 |
| server/DisplaySetting_SrvImpl.java | 843 |
| shared/AcntLedger_Mdl.java | 31 |
| shared/DisplayRoute_Mdl.java | 106 |
| shared/DisplaySetting_Mdl.java | 322 |
| shared/DisplaySetting_Srv.java | 38 |
| shared/DisplaySetting_SrvAsync.java | 38 |
| shared/TxtHItem_Mdl.java | 68 |
| shared/TxtHParty_Mdl.java | 31 |
| shared/TxtHVoucherNo_Mdl.java | 60 |

## UI labels / columns (client)
`From Date`, `To Date`, `Site Name`, `Supplier`, `Supplier City`, `Voucher Type`, `Voucher No.`, `Return No.`, `Periodicity`, `Item Name`, `Item Category`, `Item Group`, `Item Type`, `Item Status`, `Order Status`, `Greater Than`, `AND`, `Less Than`

## Models (shared DTOs)
### DisplaySetting_Mdl
| type | field |
|---|---|
| String | currentSiteCode |
| String fromDate, | toDate |
| String siteCode, | siteName |
| String periodicityCode, | periodicityName |
| String supplierCode, | supplierName |
| String supplierCityCode, | supplierCityName |
| String voucherTypeCode, | voucherType |
| String voucherNoCode, | voucherNo |
| String returnNoCode, | returnNo |
| String itemTypeCode, | itemTypeName |
| String itemCode, | itemName |
| String itemGroupCode, | itemGroupName |
| String itemCategoryCode, | itemCategoryName |
| String itemStatusCode, | itemStatusName |
| String orderStatusCode, | orderStatusName |
| String greaterThan, | lessThan |

### TxtHVoucherNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | voucherType |
| String | voucherDate |
| String | voucherCategory |
| String | shortName |
| String | supplierName |

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
| getTxtHPurchaseBillNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucherNo_Mdl>> |
| getTxtHPurchaseReturnNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucherNo_Mdl>> |
| fillSettings |  | DmResult<DisplaySetting_Mdl> |
| getTxtHItem | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getTxtHItemCat | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemStatus | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHOrderStatus | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Server `DisplaySetting_SrvImpl.java` (843 lines)
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

- SQL 1: `SELECT sg.subCode AS code, sg.name, sg.manualCode, c.cityName FROM subGroup sg LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN (SELECT DISTINCT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_Type = acf.v_Type WHERE vt.category IN (:GPB,:GPBU,:PBIL, :GPBUS,:PBILU,:GPBD,:GPBDU,:SPB,:SPBU,:GPR,:GPRU,:PRTN,:PRTNU,:SPRTN,:SPRNU) ) AS ACF ON ACF.nature = sg.nature WHERE ACF.nature IS NOT NULL`
- Params: `:GPB` ← `pvc.GENERAL_PURCHASE_BILL`; `:GPBU` ← `pvc.GENERAL_PURCHASE_BILL_UNREGISTERED`; `:PBIL` ← `pvc.PURCHASE_BILL`; `:GPBUS` ← `pvc.GENERAL_PURCHASE_BILL_UNSTOCKABLE`; `:PBILU` ← `pvc.PURCHASE_BILL_UNREGISTERED`; `:GPBD` ← `pvc.GENERAL_PURCHASE_BILL_DIRECT`; `:GPBDU` ← `pvc.GENERAL_PURCHASE_BILL_DIRECT_UNREGISTERED`; `:SPB` ← `pvc.SERVICE_PURCHASE_BILL`; `:SPBU` ← `pvc.SERVICE_PURCHASE_BILL_UNREGISTERED`; `:GPR` ← `pvc.GENERAL_PURCHASE_RETURN`; `:GPRU` ← `pvc.GENERAL_PURCHASE_RETURN_UNREGISTERED`; `:PRTN` ← `pvc.PURCHASE_RETURN`; `:PRTNU` ← `pvc.PURCHASE_RETURN_UNREGISTERED`; `:SPRTN` ← `pvc.SERVICE_PURCHASE_RETURN`; `:SPRNU` ← `pvc.SERVICE_PURCHASE_RETURN_UNREGISTERED`

### `getTxtHSupplierCity`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSupplierCity(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT c.cityCode , c.cityName, s.name as stateName, cnt.name AS countryName FROM city c LEFT JOIN stateMast s ON s.code = c.stateCode LEFT JOIN country cnt ON cnt.code = c.countryCode`

### `getTxtHVoucherType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVoucherType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type AS code, vt.description AS name, vt.short_Name FROM voucher_type vt WHERE vt.category IN (:GPB,:GPBU,:PBIL, :GPBUS,:PBILU,:GPBD,:GPBDU,:SPB,:SPBU,:SPBD,:SPBDU,:GPR,:GPRU,:PRTN,:PRTNU,:SPRTN,:SPRNU)`
- Params: `:GPB` ← `pvc.GENERAL_PURCHASE_BILL`; `:GPBU` ← `pvc.GENERAL_PURCHASE_BILL_UNREGISTERED`; `:PBIL` ← `pvc.PURCHASE_BILL`; `:GPBUS` ← `pvc.GENERAL_PURCHASE_BILL_UNSTOCKABLE`; `:PBILU` ← `pvc.PURCHASE_BILL_UNREGISTERED`; `:GPBD` ← `pvc.GENERAL_PURCHASE_BILL_DIRECT`; `:GPBDU` ← `pvc.GENERAL_PURCHASE_BILL_DIRECT_UNREGISTERED`; `:SPB` ← `pvc.SERVICE_PURCHASE_BILL`; `:SPBU` ← `pvc.SERVICE_PURCHASE_BILL_UNREGISTERED`; `:SPBD` ← `pvc.SERVICE_PURCHASE_BILL_DIRECT`; `:SPBDU` ← `pvc.SERVICE_PURCHASE_BILL_DIRECT_UNREGISTERED`; `:GPR` ← `pvc.GENERAL_PURCHASE_RETURN`; `:GPRU` ← `pvc.GENERAL_PURCHASE_RETURN_UNREGISTERED`; `:PRTN` ← `pvc.PURCHASE_RETURN`; `:PRTNU` ← `pvc.PURCHASE_RETURN_UNREGISTERED`; `:SPRTN` ← `pvc.SERVICE_PURCHASE_RETURN`; `:SPRNU` ← `pvc.SERVICE_PURCHASE_RETURN_UNREGISTERED`

### `getTxtHPurchaseBillNo`
`public DmResultPageing<List<TxtHVoucherNo_Mdl>> getTxtHPurchaseBillNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select pb.docId, pb.recId, pb.v_Date, pb.v_No, pb.v_Prefix, vt.short_name , sg.name as supplierName, sm.manualCode AS siteManualCode , CASE WHEN pb.site_Code = {…} THEN 1 ELSE 2 END as tempSr FROM pBill pb LEFT JOIN voucher_Type vt On vt.v_Type = pb.v_Type LEFT JOIN subGroup sg On sg.subCode = pb.subCode LEFT JOIN siteMast sm ON sm.code = pb.site_Code WHERE vt.category IN (:GPB,:GPBU,:PBIL, :GPBUS,:PBILU,:GPBD,:GPBDU,:SPB,:SPBU) AND pb.site_Code IN( {…} :siteList {…} ) AND ISNULL(pb.approvedBy,'')<>'' AND ISNULL(pb.cancelledBy, '') = ''`
- Params: `:GPB` ← `pvc.GENERAL_PURCHASE_BILL`; `:GPBU` ← `pvc.GENERAL_PURCHASE_BILL_UNREGISTERED`; `:PBIL` ← `pvc.PURCHASE_BILL`; `:GPBUS` ← `pvc.GENERAL_PURCHASE_BILL_UNSTOCKABLE`; `:PBILU` ← `pvc.PURCHASE_BILL_UNREGISTERED`; `:GPBD` ← `pvc.GENERAL_PURCHASE_BILL_DIRECT`; `:GPBDU` ← `pvc.GENERAL_PURCHASE_BILL_DIRECT_UNREGISTERED`; `:SPB` ← `pvc.SERVICE_PURCHASE_BILL`; `:SPBU` ← `pvc.SERVICE_PURCHASE_BILL_UNREGISTERED`

### `getTxtHPurchaseReturnNo`
`public DmResultPageing<List<TxtHVoucherNo_Mdl>> getTxtHPurchaseReturnNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select pb.docId, pb.recId, pb.v_Date, pb.v_No, pb.v_Prefix , vt.short_name, sg.name as supplierName, sm.manualCode AS siteManualCode , CASE WHEN pb.site_Code = {…} THEN 1 ELSE 2 END as tempSr FROM pBill pb LEFT JOIN voucher_Type vt On vt.v_Type = pb.v_Type LEFT JOIN subGroup sg On sg.subCode = pb.subCode LEFT JOIN siteMast sm ON sm.code = pb.site_Code WHERE vt.category IN (:GPR,:GPRU,:PRTN,:PRTNU,:SPRTN,:SPRNU) AND pb.site_Code IN( {…} :siteList {…} ) AND ISNULL(pb.approvedBy,'')<>'' AND ISNULL(pb.cancelledBy, '') = ''`
- Params: `:GPR` ← `pvc.GENERAL_PURCHASE_RETURN`; `:GPRU` ← `pvc.GENERAL_PURCHASE_RETURN_UNREGISTERED`; `:PRTN` ← `pvc.PURCHASE_RETURN`; `:PRTNU` ← `pvc.PURCHASE_RETURN_UNREGISTERED`; `:SPRTN` ← `pvc.SERVICE_PURCHASE_RETURN`; `:SPRNU` ← `pvc.SERVICE_PURCHASE_RETURN_UNREGISTERED`

### `getPosition`
`public DmResultPageing<List<KeyFieldExtended>> getPosition(DmHelper helper, boolean blnToEndOfFile)`


### `getCostCenter`
`public DmResultPageing<List<KeyFieldExtended>> getCostCenter(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT code, name FROM costCenterMast WHERE (ISNULL(underCostCenter, 0) = 0)`

### `getTxtHItemGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ig.code, ig.name FROM itemgroup ig WHERE (ig.filterItemType = :filterItemType or ig.isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemCat`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemCat(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ic.code, ic.name FROM itemCat ic WHERE (ic.filterItemType = :filterItemType or ic.isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code,name,manualcode from itemType WHERE manualcode NOT IN ('OI','SV')`

### `getTxtHItemStatus`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemStatus(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `Status.values`

### `getTxtHOrderStatus`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHOrderStatus(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHItem`
`public DmResultPageing<List<TxtHItem_Mdl>> getTxtHItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , um.code AS unitCode , um.unit,im.manualCode FROM itemMast AS im LEFT JOIN itemType it ON it.code = im.itemType LEFT JOIN unitmast um ON im.sku = um.code WHERE (it.manualcode IN ('FM','SM') or im.isCommon=1)`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `paperbiz.purchase.displaySetting.shared`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
