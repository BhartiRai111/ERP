# pb-store / bfGsmStockClosingDisplay

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/store/src/bfGsmStockClosingDisplay/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: 'From Date' (Text); 'To Date' (Text); 'Site Name' (Text); rule: From Date cannot be greater than To Date
- Lookups/suggestions: getTxtHSite, getTxtHParty, getTxtHGodown, getTxtHItemName, getTxtHBfGsmWise, getTxtHItemNature, getTxtHYesNo, getTxtHBf, getTxtHGsm, getTxtHWidth, getTxtHLength, getTxtHSummaryOn, getTxtHShow
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `BfGsmStockClosingDisplay`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmTextBox | `txtSearchValue` | `100, false` |

Other widgets (not in tab list): `grdDisplay` (DmGridPaging)

**Grid / popup columns** (header, width, db field):
- `grdDisplay`: Item Name [Text, w320, itemName]; BF [Text, w100, bfCode]; Reel/Bundle [Text, w100, reelBundle]; Qty [Text, w100, balanceQty]; Item Name [Text, w320, itemName]; GSM [Text, w100, gsmCode]; Reel/Bundle [Text, w100, reelBundle]; Qty [Text, w100, balanceQty]; Item Name [Text, w320, itemName]; BF [Text, w100, bfCode]; GSM [Text, w100, gsmCode]; Reel/Bundle [Text, w100, reelBundle]; Qty [Text, w100, balanceQty]; Consignee [Text, w220, consigneeName]; Item Name [Text, w320, itemName]; BF [Text, w100, bfCode]; GSM [Text, w100, gsmCode]; Reel/Bundle [Text, w100, reelBundle]; Qty [Text, w100, balanceQty]; Party [Text, w220, partyName]; Item Name [Text, w320, itemName]; BF [Text, w100, bfCode]; Reel/Bundle [Text, w100, reelBundle]; Qty [Text, w100, balanceQty]; Party [Text, w220, partyName]; Item Name [Text, w320, itemName]; GSM [Text, w100, gsmCode]; Reel/Bundle [Text, w100, reelBundle]; Qty [Text, w100, balanceQty]; Party [Text, w220, partyName]

**Lookup / suggestion / find sources (server RPC called):**
- `setting` → `getDefault`
- `grdDisplay` → `getBfGsmstockClosingDetail`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`setting.addCallBack` → `onOk()`
```java
setting_Mdl = acDisplaySetting;
iniGrdDisplay();
refreshGrid();
```

`grdDisplay.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnToEndOfFile).getBfGsmstockClosingDetail(setting_Mdl, helper, blnToEndOfFile, param, new AsyncCallb
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdBfGsmStockClosingDisplay_Mdl>> result) {
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
| 1 | As on Date | DmTextBox | `txtToDate` | `11, true` |
| 2 | Site Name | DmTextHelpMulti | `txtHSite` | `KeyField_PrpAcs, 7, true` |
| 3 | Supplier | DmTextHelpMulti | `txtHParty` | `TxtHSupplierName_PrpAcs, 10` |
| 4 | Godown | DmTextHelpMulti | `txtHGodown` | `KeyField_PrpAcs, 5, false` |
| 5 | Item Name | DmTextHelpMulti | `txtHItemName` | `GridItem_PrpAcs, 10` |
| 6 | Item Nature | DmTextHelp | `txtHItemNature` | `TxtHItemNature_PrpAcs, 3, false` |
| 7 | BF | DmTextHelpMulti | `txtHBf` | `com.google.gwt.core.client.KeyField_PrpAcs, 5, false` |
| 8 | GSM | DmTextHelpMulti | `txtHGsm` | `com.google.gwt.core.client.KeyField_PrpAcs, 5, false` |
| 9 | Width | DmTextHelpMulti | `txtHWidth` | `TxtHWidth_PrpAcs, 5, false` |
| 10 | Length | DmTextHelpMulti | `txtHLength` | `TxtHLength_PrpAcs, 5, false` |
| 11 | BF/GSM Wise | DmTextHelp | `txtHBfGsmWise` | `TxtHBfGsmWise_PrpAcs, 9, true` |
| 12 | Show | DmTextHelp | `txtHShow` | `TxtHShow_PrpAcs, 2, false` |

Other widgets (not in tab list): `txtFromDate` (DmTextBox), `txtClosing` (DmTextBoxNumeric), `txtHConsignee` (DmTextHelpMulti), `txtHSummaryOn` (DmTextHelp)

**Grid / popup columns** (header, width, db field):
- `txtHSite`: Name [Text, w0, name]
- `txtHParty`: Name [Text, w0, name]; Manual Code [Text, w100, manualCode]; City Name [Text, w200, cityName]; Ac Group [Text, w100, acGroupName]
- `txtHConsignee`: Name [Text, w0, name]; Manual Code [Text, w100, manualCode]; City Name [Text, w200, cityName]
- `txtHGodown`: Name [Text, w0, name]
- `txtHItemName`: Name [Text, w0, name]; Manual Code [Text, w100, manualCode]
- `txtHBf`: BF [Text, w0, name]
- `txtHGsm`: GSM [Text, w0, name]
- `txtHWidth`: Width [Text, w0, sizeWidth]
- `txtHLength`: Length [Text, w0, sizeLength]
- `txtHBfGsmWise`: Name [Text, w0]
- `txtHItemNature`: Name [Text, w0, name]
- `txtHSummaryOn`: Name [Text, w0, name]
- `txtHShow`: Name [Text, w0, balanceQty]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHBfGsmWise` → `getTxtHBfGsmWise`
- `txtHItemNature` → `getTxtHItemNature`
- `txtHSummaryOn` → `getTxtHSummaryOn`
- `txtHShow` → `getTxtHShow`

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

`txtHParty.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHParty(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHPart
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

`txtHConsignee.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHParty(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHPart
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHConsignee).show();
public void onSuccess(DmResultPageing<List<TxtHParty_Mdl>> result) {
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

`txtHGodown.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHGodown(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFiel
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGodown).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHGodown.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGodown).show();
```

`txtHGodown.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHGodown.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGodown).show();
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

`txtHSummaryOn.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHSummaryOn(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyF
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSummaryOn).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHSummaryOn.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSummaryOn).show();
```

`txtHSummaryOn.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSummaryOn.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSummaryOn).show();
```

`txtHSummaryOn.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
```

`txtHShow.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHShow(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHShow).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHShow.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHShow).show();
```

`txtHShow.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHShow.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHShow).show();
```

`txtHShow.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
```
- Alert: "To Date should be greater than from date!"
- Alert: "'Site Name' is mandatory."
- Alert: "'BF/GSM Wise' is mandatory."

## Files
| file | lines |
|---|---|
| client/BfGsmStockClosingDisplay.java | 647 |
| client/BfGsmStockClosingDisplay_Dsg.java | 216 |
| client/Setting.java | 659 |
| client/Setting_Dsg.java | 516 |
| server/BfGsmStockClosingDisplay_SrvImpl.java | 1180 |
| shared/BfGsmStockClosingDisplay_Srv.java | 43 |
| shared/BfGsmStockClosingDisplay_SrvAsync.java | 42 |
| shared/DefaultValue_Mdl.java | 49 |
| shared/DisplayRoute_Mdl.java | 113 |
| shared/GrdBfGsmStockClosingDisplay_Mdl.java | 222 |
| shared/RptBfGsmStockClosingDisplay_Mdl.java | 95 |
| shared/Setting_Mdl.java | 264 |
| shared/Setting_Vld.java | 24 |
| shared/TxtHAcGroup_Mdl.java | 42 |
| shared/TxtHItem_Mdl.java | 43 |
| shared/TxtHParty_Mdl.java | 50 |
| shared/TxtHVoucherNo_Mdl.java | 60 |

## UI labels / columns (client)
`As on Date`, `Site Name`, `Supplier`, `Godown`, `Item Name`, `Item Nature`, `BF`, `GSM`, `Width`, `Length`, `BF/GSM Wise`, `Show`

## Models (shared DTOs)
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
| String | particularsCode |
| String | prevSummaryOn |
| String forCode, | forName |
| String | clickRowIndex |
| String | clickColIndex |
| Setting_Mdl | setting_mdl |

### GrdBfGsmStockClosingDisplay_Mdl (extends `KeyField implements Serializable, IsSerializable`)
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
| String | itemGroupCode |
| String | itemGroupName |
| String | itemCatCode |
| String | itemCatName |
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
| boolean | selected |

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
| String partyCode, | partyName |
| String | consigneeName |
| String | consigneecode |
| String godownCode, | godownName |
| String itemCode, | itemName |
| String | itemNatureCode |
| String | itemNatureName |
| String | bfCode |
| String | bfName |
| String | gsmCode |
| String | gsmName |
| String | width |
| String | length |
| String | bfgsmWise |
| String | show |
| String | summaryOn |

### TxtHItem_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | unit |
| String | unitCode |

### TxtHAcGroup_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | parentGroupCode |
| String | parentGroupName |
| String | nature |

### TxtHParty_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityName |
| String | acGroupName |
| String | nature |

### RptBfGsmStockClosingDisplay_Mdl
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

## RPC interface
| method | params | returns |
|---|---|---|
| getDefault | Setting_Mdl displaySetting | DmResult<DefaultValue_Mdl> |
| getBfGsmstockClosingDetail | Setting_Mdl setting_Mdl, DmHelper helper, boolean blnToEndOfFile, KeyFieldExtended keyField | DmResultPageing<List<GrdBfGsmStockClosingDisplay_Mdl>> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHParty | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getTxtHGodown | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getTxtHBfGsmWise | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHYesNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHWidth | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHLength | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSummaryOn | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHShow | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
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

## Server `BfGsmStockClosingDisplay_SrvImpl.java` (1180 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getDefault`
`public DmResult<DefaultValue_Mdl> getDefault(Setting_Mdl setting_Mdl)`

- SQL 1: `IF OBJECT_ID('tempdb.. {…} ') IS NOT NULL DROP TABLE {…} ; CREATE TABLE {…} (orderIndex int, summaryOnCode bigint, summaryOnName varchar(50) , consignee bigint, consigneeName varchar(50), partyCode bigint, partyName varchar(50) , departmentCode bigint , itemCode bigint, ibCode bigint, balanceReelBundle decimal(18,7), balanceQty decimal(18,7) , recordType TINYINT DEFAULT 0)`
- SQL 2: `SELECT MAX(CASE WHEN ISNULL(so.partyCode, '') <> '' THEN so.partyCode WHEN ISNULL(pb.subCode, '') <> '' THEN pb.subCode ELSE pc.subCode END) AS summaryOnCode , MAX(sgpso.name) AS summaryOnName , MAX(iis.partyCode) AS partyCode , MAX(ptip.name) AS partyName , MAX(so.consignee) AS consignee , MAX(ptic.name) AS consigneeName , MAX(iis.itemId) AS itemId, MAX(iis.departmentCode) AS departmentCode , MAX(iis.itemCode) AS itemCode , MAX(iis.ibCode) AS ibCode , SUM(iis.balanceQty) AS balanceQty , SUM(iis.balancePcs) AS balanceReelBundle , MAX(ib.itemNature) AS itemNatureCode , MAX(imn.name) AS itemNatureName FROM itemIdStock iis LEFT JOIN itemBatch ib ON ib.code = iis.ibCode LEFT JOIN pChallan pc ON pc.docId = iis.creationDocId AND pc.v_Type = iis.v_Type LEFT JOIN pBill pb ON pb.docId = iis.creationDocId AND pb.v_Type = iis.v_Type LEFT JOIN sOrder so ON so.docId = iis.soDocId LEFT JOIN partyTransInfo ptip ON ptip.subCode = iis.partyCode AND ptip.recordType = :partyRecordType LEFT JOIN partyTransInfo ptic ON ptic.subCode = so.consignee AND ptic.recordType = :consigneeRecordType LEFT JOIN subGroup sgpso ON sgpso.subCode = (CASE WHEN ISNULL(so.partyCode, '') <> ''THEN so.partyCode WHEN ISNULL(pb.subCode, '') <> '' THEN pb.subCode ELSE pc.subCode END) LEFT JOIN itemNature imn ON imn.code = ib.itemNature WHERE 1 = 1 AND ISNULL(iis.balanceQty, 0) > 0 {…} GROUP BY {…} ORDER BY {…}`
- SQL 3: `INSERT INTO {…} (orderIndex, summaryOnCode, summaryOnName,consignee,consigneeName,partyCode,partyName, departmentCode , itemCode, ibCode, balanceReelBundle,balanceQty, recordType) VALUES( :orderIndex, :summaryOnCode, :summaryOnName, :consignee, :consigneeName, :partyCode, :partyName , :departmentCode, :itemCode, :ibCode, :balanceReelBundle, :balanceQty, :recordType)`
- Params: `:partyRecordType` ← `DmEnum.PartyTransRecordType.PARTY.getCode()`; `:consigneeRecordType` ← `DmEnum.PartyTransRecordType.CONSIGNEE.getCode()`; `:fromDate` ← `DmServer.convertDate(DmShared.dNull(setting_Mdl.getFromDate()))`; `:toDate` ← `DmServer.convertDate(setting_Mdl.getToDate())`; `:orderIndex` ← `orderIndex`; `:recordType` ← `0`; `:summaryOnCode` ← `DmShared.xNull(rsTemp.getString("summaryOnCode"))`; `:summaryOnName` ← `DmShared.xNull(rsTemp.getString("summaryOnName"))`; `:consignee` ← `DmShared.xNull(rsTemp.getString("consignee"))`; `:consigneeName` ← `DmShared.xNull(rsTemp.getString("consigneeName"))`; `:partyCode` ← `DmShared.xNull(rsTemp.getString("partyCode"))`; `:partyName` ← `DmShared.xNull(rsTemp.getString("partyName"))`; `:departmentCode` ← `DmShared.xNull(rsTemp.getString("departmentCode"))`; `:itemCode` ← `DmShared.xNull(rsTemp.getString("itemCode"))`; `:ibCode` ← `DmShared.xNull(rsTemp.getString("ibCode"))`; `:balanceQty` ← `DmServer.vNull(rsTemp.getString("balanceQty"))`; `:balanceReelBundle` ← `DmServer.vNull(DmShared.vNull(rsTemp.getString("balanceReelBundle")))`; `:orderIndex` ← `orderIndex`; `:recordType` ← `1`; `:summaryOnCode` ← `""`; `:summaryOnName` ← `""`; `:consignee` ← `""`; `:consigneeName` ← `""`; `:partyCode` ← `""`; `:partyName` ← `""`; `:departmentCode` ← `""`; `:itemCode` ← `""`; `:ibCode` ← `""`; `:balanceQty` ← `DmServer.vNull(totalQty, "0.00")`; `:balanceReelBundle` ← `DmServer.vNull(totalReelBundle, "0.00")`
- Other calls: `CONSIGNEE.getCode`, `DmJasper.formatAmount`, `PARTY.getCode`, `Setting_Vld.chkSave`

### `getBfGsmstockClosingDetail`
`public DmResultPageing<List<GrdBfGsmStockClosingDisplay_Mdl>> getBfGsmstockClosingDetail(Setting_Mdl setting_Mdl, DmHelper helper, boolean blnToEndOfFile, KeyFieldExtended keyField)`

- SQL 1: `SELECT t.orderIndex, t.summaryOnCode, t.summaryOnName , t.consignee, t.consigneeName , t.partyCode, t.partyName , t.itemCode, im.name AS itemName, ISNULL(ib.sizeWidth, 0) AS sizeWidth, ISNULL(ib.sizeLength, 0) AS sizeLength , ISNULL(ib.bfCode, '') AS bfCode, ISNULL(ib.gsmCode, '') AS gsmCode, t.balanceQty,t.balanceReelBundle, t.recordType FROM {…} t LEFT JOIN itemMast im ON im.code = t.itemCode LEFT JOIN itemBatch ib ON ib.code = t.ibCode`

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

### `getTxtHParty`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHParty(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code, sg.name, sg.manualCode, c.cityName, acg.groupName AS acGroupName , (CASE WHEN sgn.manualCode = :SU THEN 0 WHEN sgn.manualCode = :CT THEN 1 ELSE 2 END) AS orderBy FROM subGroup sg LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN (SELECT DISTINCT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_Type = acf.v_Type WHERE vt.category IN (:PC,:PCU,:PBD,:PBDU,:SO) ) AS ACF ON ACF.nature = sg.nature LEFT JOIN acGroup acg ON acg.groupCode = sg.groupCode LEFT JOIN subGroupNature sgn ON sgn.code = acg.nature WHERE ACF.nature IS NOT NULL`
- Params: `:SU` ← `"SU"`; `:CT` ← `"CT"`; `:PC` ← `pvc.PURCHASE_CHALLAN`; `:PCU` ← `pvc.PURCHASE_CHALLAN_UNREGISTERED`; `:PBD` ← `pvc.PURCHASE_BILL_DIRECT`; `:PBDU` ← `pvc.PURCHASE_BILL_DIRECT_UNREGISTERED`; `:SO` ← `pvc.SALES_ORDER`

### `getTxtHGodown`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGodown(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select dm.subCode as code, sg.name from departmentMast as dm left join subGroup as sg on dm.subCode = sg.subCode left join subGroupNature sgn on sgn.code = sg.nature where sgn.manualCode IN ('CM','GD') {…}`
- Other calls: `STServer.getSsnDepartmentCodeList`

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

### `getTxtHYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHYesNo(DmHelper helper, boolean blnToEndOfFile)`


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

### `getTxtHSummaryOn`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSummaryOn(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHShow`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHShow(DmHelper helper, boolean blnToEndOfFile)`


### `getPosition`
`public DmResultPageing<List<KeyFieldExtended>> getPosition(DmHelper helper, boolean blnToEndOfFile)`


### `getCostCenter`
`public DmResultPageing<List<KeyFieldExtended>> getCostCenter(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT code, name FROM costCenterMast WHERE (ISNULL(underCostCenter, 0) = 0)`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmJasper.server`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`, `paperbiz.store.bfGsmStockClosingDisplay.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
