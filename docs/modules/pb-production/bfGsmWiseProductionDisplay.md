# pb-production / bfGsmWiseProductionDisplay

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/production/src/bfGsmWiseProductionDisplay/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: 'From Date' (Text); 'To Date' (Text); 'Site Name' (Text); rule: From Date cannot be greater than To Date
- Lookups/suggestions: getTxtHSite, getTxtHItemCateory, getTxtHItemGroup, getTxtHItemName, getTxtHBfGsmWise, getTxtHItemNature, getTxtHBf, getTxtHGsm, getTxtHWidth, getTxtHLength
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `BfGsmWiseProductionDisplay`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmTextBox | `txtSearchValue` | `100, false` |

Other widgets (not in tab list): `grdDisplay` (DmGridPaging)

**Grid / popup columns** (header, width, db field):
- `grdDisplay`: Item Name [Text, w320, itemName]; BF [Text, w100, bfCode]; Reel/Bundle [Text, w100, packCount]; Qty [Text, w100, quantity]; Item Name [Text, w320, itemName]; GSM [Text, w100, gsmCode]; Reel/Bundle [Text, w100, packCount]; Qty [Text, w100, quantity]; Item Name [Text, w320, itemName]; BF [Text, w100, bfCode]; GSM [Text, w100, gsmCode]; Reel/Bundle [Text, w100, packCount]; Qty [Text, w100, quantity]; Date [Text, w120, v_Date]; BF [Text, w100, bfCode]; Reel/Bundle [Text, w100, packCount]; Qty [Text, w100, quantity]; Date [Text, w120, v_Date]; GSM [Text, w100, gsmCode]; Reel/Bundle [Text, w100, packCount]; Qty [Text, w100, quantity]; Date [Text, w120, v_Date]; BF [Text, w100, bfCode]; GSM [Text, w100, gsmCode]; Reel/Bundle [Text, w100, packCount]; Qty [Text, w100, quantity]

**Lookup / suggestion / find sources (server RPC called):**
- `setting` → `getDefault`
- `grdDisplay` → `getBfGsmProductionDetail`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`setting.addCallBack` → `onOk()`
```java
setting_Mdl = acDisplaySetting;
iniGrdDisplay();
refreshGrid();
```

`grdDisplay.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnToEndOfFile).getBfGsmProductionDetail(setting_Mdl, helper, blnToEndOfFile, param, new AsyncCallbac
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdBfGsmWiseProductionDisplay_Mdl>> result) {
if (result.isStatus()) {
grdDisplay.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`grdDisplay.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
grdDisplay.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`grdDisplay.addCallBack` → `getParam()`
```java
return null;
```
- Confirm: "Are you sure? You want to close."

### Screen `Setting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | From Date | DmTextBox | `txtFromDate` | `11, true` |
| 2 | To Date | DmTextBox | `txtToDate` | `11, true` |
| 3 | Site Name | DmTextHelpMulti | `txtHSite` | `KeyField_PrpAcs, 7, true` |
| 4 | Item Category | DmTextHelpMulti | `txtHItemCategory` | `KeyField_PrpAcs, 8` |
| 5 | Item Group | DmTextHelpMulti | `txtHItemGroup` | `KeyField_PrpAcs, 8` |
| 6 | Item Name | DmTextHelpMulti | `txtHItemName` | `GridItem_PrpAcs, 10` |
| 7 | Item Nature | DmTextHelp | `txtHItemNature` | `TxtHItemNature_PrpAcs, 3, false` |
| 8 | BF | DmTextHelpMulti | `txtHBf` | `com.google.gwt.core.client.KeyField_PrpAcs, 5, false` |
| 9 | GSM | DmTextHelpMulti | `txtHGsm` | `com.google.gwt.core.client.KeyField_PrpAcs, 5, false` |
| 10 | Width | DmTextHelpMulti | `txtHWidth` | `TxtHWidth_PrpAcs, 5, false` |
| 11 | Length | DmTextHelpMulti | `txtHLength` | `TxtHLength_PrpAcs, 5, false` |
| 12 | BF/GSM Wise | DmTextHelp | `txtHBfGsmWise` | `TxtHBfGsmWise_PrpAcs, 9, true` |

Other widgets (not in tab list): `txtClosing` (DmTextBoxNumeric)

**Grid / popup columns** (header, width, db field):
- `txtHSite`: Name [Text, w0, name]
- `txtHItemCategory`: Name [Text, w0, Name]
- `txtHItemGroup`: Name [Text, w125, Name]
- `txtHItemName`: Name [Text, w0, name]; Manual Code [Text, w100, manualCode]
- `txtHBf`: BF [Text, w0, name]
- `txtHGsm`: GSM [Text, w0, name]
- `txtHWidth`: Width [Text, w0, sizeWidth]
- `txtHLength`: Length [Text, w0, sizeLength]
- `txtHBfGsmWise`: Name [Text, w0]
- `txtHItemNature`: Name [Text, w0, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHBfGsmWise` → `getTxtHBfGsmWise`
- `txtHItemNature` → `getTxtHItemNature`

**Event handlers (condensed code, CRUD flow and field callbacks):**

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

`txtHItemCategory.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItemCateory(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemCategory).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHItemCategory.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemCategory).show();
```

`txtHItemCategory.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemCategory.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemCategory).show();
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

`txtHItemName.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItemName(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHI
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemName).show();
public void onSuccess(DmResultPageing<List<TxtHItem_Mdl>> result) {
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

`txtHBf.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHBf(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExt
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHBf).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHBf.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBf).show();
```

`txtHBf.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHBf.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBf).show();
```

`txtHGsm.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHGsm(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldEx
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGsm).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHGsm.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGsm).show();
```

`txtHGsm.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHGsm.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGsm).show();
```

`txtHWidth.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHWidth(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyField
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHWidth).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHWidth.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHWidth).show();
```

`txtHWidth.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHWidth.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHWidth).show();
```

`txtHLength.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHLength(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFiel
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHLength).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHLength.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHLength).show();
```

`txtHLength.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHLength.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHLength).show();
```

`txtHBfGsmWise.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHBfGsmWise(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyF
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHBfGsmWise).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHBfGsmWise.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBfGsmWise).show();
```

`txtHBfGsmWise.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHBfGsmWise.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBfGsmWise).show();
```

`txtHBfGsmWise.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
```

`txtHItemNature.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItemNature(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Key
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemNature).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHItemNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemNature).show();
```

`txtHItemNature.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemNature).show();
```

`txtHItemNature.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
```
- Alert: "To Date should be greater than from date!"
- Alert: "'Site Name' is mandatory."
- Alert: "'BF/GSM Wise' is mandatory."

## Files
| file | lines |
|---|---|
| client/BfGsmWiseProductionDisplay.java | 571 |
| client/BfGsmWiseProductionDisplay_Dsg.java | 216 |
| client/Setting.java | 554 |
| client/Setting_Dsg.java | 423 |
| server/BfGsmWiseProductionDisplay_SrvImpl.java | 1015 |
| shared/BfGsmWiseProductionDisplay_Srv.java | 37 |
| shared/BfGsmWiseProductionDisplay_SrvAsync.java | 36 |
| shared/DefaultValue_Mdl.java | 49 |
| shared/GrdBfGsmWiseProductionDisplay_Mdl.java | 233 |
| shared/RptBfGsmWiseProductionDisplay_Mdl.java | 95 |
| shared/Setting_Mdl.java | 247 |
| shared/Setting_Vld.java | 24 |
| shared/TxtHAcGroup_Mdl.java | 42 |
| shared/TxtHItem_Mdl.java | 52 |
| shared/TxtHParty_Mdl.java | 50 |
| shared/TxtHVoucherNo_Mdl.java | 60 |

## UI labels / columns (client)
`From Date`, `To Date`, `Site Name`, `Item Category`, `Item Group`, `Item Name`, `Item Nature`, `BF`, `GSM`, `Width`, `Length`, `BF/GSM Wise`

## Models (shared DTOs)
### TxtHVoucherNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | voucherType |
| String | voucherDate |
| String | voucherCategory |
| String | shortName |
| String | supplierName |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | totalQty |
| String | totalReel |
| String | totalDispQty |
| String | totalDispReel |

### Setting_Mdl
| type | field |
|---|---|
| String | lblHeading |
| String fromDate, | toDate |
| String siteCode, | siteName |
| String itemCode, | itemName |
| String | itemNatureCode |
| String | itemNatureName |
| String | itemGroupCode |
| String | itemGroupName |
| String | itemCatCode |
| String | itemCatName |
| String | bfCode |
| String | bfName |
| String | gsmCode |
| String | gsmName |
| String | width |
| String | length |
| String | bfgsmWise |
| String | show |
| String | summaryOn |

### RptBfGsmWiseProductionDisplay_Mdl
| type | field |
|---|---|
| String | totalInQty |
| String | totalInEffRate |
| String | totalInValue |
| String | totalOutQty |
| String | totalOutEffRate |
| String | totalOutValue |
| String | partyName |
| String | partyCode |
| Setting_Mdl | displaySettingMdl |

### TxtHItem_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | itemType |
| String | itemCategory |
| String | itemGroup |

### TxtHAcGroup_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | parentGroupCode |
| String | parentGroupName |
| String | nature |

### GrdBfGsmWiseProductionDisplay_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | siteCode |
| String | summaryOnCode |
| String | summaryOnName |
| String | consigneeCode |
| String | consigneeName |
| String | partyCode |
| String | partyName |
| String | itemCode |
| String | itemName |
| String | sizeWidth |
| String | sizeLength |
| String | bfName |
| String | bfCode |
| String | gsmName |
| String | gsmCode |
| String | reelBundle |
| String | qty |
| String | recordType |
| String | categoryCode |
| String | categoryName |
| String | itemGroupCode |
| String | itemGroupName |
| String | v_Date |
| boolean | selected |

### TxtHParty_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityName |
| String | acGroupName |
| String | nature |

## RPC interface
| method | params | returns |
|---|---|---|
| getDefault | Setting_Mdl displaySetting | DmResult<DefaultValue_Mdl> |
| getBfGsmProductionDetail | Setting_Mdl setting_Mdl, DmHelper helper, boolean blnToEndOfFile, KeyFieldExtended keyField | DmResultPageing<List<GrdBfGsmWiseProductionDisplay_Mdl>> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemCateory | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getTxtHBfGsmWise | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHWidth | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHLength | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemNature | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| fillSettings |  | DmResult<Setting_Mdl> |
| getTxtHBf | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGsm | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `Setting_Vld`
```java
public class Setting_Vld {
    public static void chkSave(Setting_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getFromDate(), "'From Date'", 100);
        DmShared.isValidText(mrTemp.getToDate(), "'To Date'", 200);
        DmShared.isValidText(mrTemp.getSiteCode(), "'Site Name'", 300);
        if (!DmShared.dNull(mrTemp.getFromDate()).equalsIgnoreCase("") && !DmShared.dNull(mrTemp.getToDate()).equalsIgnoreCase("")) {
            if (DmShared.convertDate(mrTemp.getFromDate()).getTime() > DmShared.convertDate(mrTemp.getToDate()).getTime()) {
                throw new DmException(100, "From Date cannot be greater than To Date");
            }
        }
    }
}
```

## Server `BfGsmWiseProductionDisplay_SrvImpl.java` (1015 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getDefault`
`public DmResult<DefaultValue_Mdl> getDefault(Setting_Mdl setting_Mdl)`

- SQL 1: `IF OBJECT_ID('tempdb.. {…} ') IS NOT NULL DROP TABLE {…} ; CREATE TABLE {…} ( orderIndex int, v_Date date, itemCode bigint, ibCode bigint, packCount decimal(18,7), quantity decimal(18,7) , recordType TINYINT DEFAULT 0)`
- SQL 2: `SELECT MAX(pr.docId) AS docId, MAX(pr.recId) AS recId, MAX(pr.v_Date) AS v_Date , MAX(prd.itemCode) AS itemCode, MAX(prd.ibCode) AS ibCode , SUM(prd.quantity) AS quantity, SUM(prd.packCount) AS packCount , MAX(ib.bfCode) AS bfCode, MAX(ib.gsmCode) AS gsmCode , MAX(ib.sizeWidth) AS sizeWidth, MAX(ib.sizeLength) AS sizeLength , MAX(ib.itemNature) AS itemNatureCode , MAX(imn.name) AS itemNatureName FROM production pr LEFT JOIN productionDetail prd ON prd.docId = pr.docId LEFT JOIN itemBatch ib ON ib.code = prd.ibCode LEFT JOIN itemNature imn ON imn.code = ib.itemNature LEFT JOIN itemMast im ON im.code = prd.itemCode LEFT JOIN itemCat imc ON imc.code = im.itemCategoryCode LEFT JOIN itemGroup ig ON ig.code = im.itemGroup WHERE 1 = 1 AND ISNULL(prd.quantity, 0) > 0 {…} GROUP BY {…} ORDER BY {…}`
- SQL 3: `INSERT INTO {…} (orderIndex, v_Date, itemCode, ibCode, packCount,quantity, recordType) VALUES(:orderIndex, :v_Date, :itemCode, :ibCode, :packCount, :quantity, :recordType )`
- Params: `:toDate` ← `DmServer.convertDate(setting_Mdl.getToDate())`; `:orderIndex` ← `orderIndex`; `:recordType` ← `0`; `:itemCode` ← `DmShared.xNull(rsTemp.getString("itemCode"))`; `:ibCode` ← `DmShared.xNull(rsTemp.getString("ibCode"))`; `:quantity` ← `DmServer.vNull(rsTemp.getString("quantity"))`; `:packCount` ← `DmServer.vNull(DmShared.vNull(rsTemp.getString("packCount")))`; `:v_Date` ← `DmServer.convertDate(DmShared.dNull(rsTemp.getDate("v_Date")))`; `:orderIndex` ← `orderIndex`; `:recordType` ← `1`; `:itemCode` ← `""`; `:ibCode` ← `""`; `:quantity` ← `DmServer.vNull(totalQty, "0.00")`; `:packCount` ← `DmServer.vNull(totalReelBundle, "0.00")`
- Other calls: `DmJasper.formatAmount`, `Setting_Vld.chkSave`

### `getBfGsmProductionDetail`
`public DmResultPageing<List<GrdBfGsmWiseProductionDisplay_Mdl>> getBfGsmProductionDetail(Setting_Mdl setting_Mdl, DmHelper helper, boolean blnToEndOfFile, KeyFieldExtended keyField)`

- SQL 1: `SELECT pr.orderIndex, pr.v_Date, pr.itemCode, im.name AS itemName, ISNULL(ib.sizeWidth, 0) AS sizeWidth, ISNULL(ib.sizeLength, 0) AS sizeLength , ISNULL(ib.bfCode, '') AS bfCode, ISNULL(ib.gsmCode, '') AS gsmCode, pr.quantity,pr.packCount, pr.recordType FROM {…} pr LEFT JOIN itemMast im ON im.code = pr.itemCode LEFT JOIN itemBatch ib ON ib.code = pr.ibCode`

### `getParameter`
`private void getParameter(Setting_Mdl setting_Mdl, DmPreparedStatement psTemp)`

- Params: `:fromDate` ← `DmServer.convertDate(DmShared.dNull(setting_Mdl.getFromDate()))`; `:toDate` ← `DmServer.convertDate(DmShared.dNull(setting_Mdl.getToDate()))`

### `getTable`
`private String getTable(DmRSSPrivate rssPrivate)`


### `fillSettings`
`public DmResult<Setting_Mdl> fillSettings()`

- SQL 1: `SELECT name FROM siteMast WHERE code = :code`
- Params: `:code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in ( {…} :code {…} )`

### `getTxtHItemCateory`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemCateory(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code, name from itemCat`

### `getTxtHItemGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code, name from itemGroup`

### `getTxtHItemName`
`public DmResultPageing<List<TxtHItem_Mdl>> getTxtHItemName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , um.code AS unitCode , um.unit,im.manualCode FROM itemMast AS im LEFT JOIN unitmast um ON im.sku = um.code WHERE (im.filterItemType IN (:SM, :FM) or im.isCommon=1)`
- Params: `:SM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`; `:FM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`

### `getTxtHBfGsmWise`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHBfGsmWise(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHItemNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemNature(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT itn.code, itn.name, itn.manualCode FROM itemNature AS itn WHERE itn.isActive = 1 AND itn.manualCode IN (:R, :S)`
- Params: `:R` ← `DmShared.xNull(STEnum.ItemNature.REEL.getManualCode())`; `:S` ← `DmShared.xNull(STEnum.ItemNature.SHEET.getManualCode())`
- Other calls: `REEL.getManualCode`, `SHEET.getManualCode`

### `getTxtHBf`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHBf(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT bf.code, bf.code as name FROM bfMast bf where bf.isActive = 1`

### `getTxtHGsm`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGsm(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT gm.code, gm.code as name FROM gsmMast gm`

### `getTxtHWidth`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHWidth(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT MAX(ib.code) AS code, ib.sizeWidth FROM itemBatch ib WHERE ISNULL(ib.sizeWidth, 0) > 0 GROUP BY ib.sizeWidth`

### `getTxtHLength`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHLength(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT MAX(ib.code) AS code, ib.sizeLength FROM itemBatch ib WHERE ISNULL(ib.sizeLength, 0) > 0 GROUP BY ib.sizeLength`

### `getPosition`
`public DmResultPageing<List<KeyFieldExtended>> getPosition(DmHelper helper, boolean blnToEndOfFile)`


### `getCostCenter`
`public DmResultPageing<List<KeyFieldExtended>> getCostCenter(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT code, name FROM costCenterMast WHERE (ISNULL(underCostCenter, 0) = 0)`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmJasper.server`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `paperbiz.production.bfGsmWiseProductionDisplay.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
