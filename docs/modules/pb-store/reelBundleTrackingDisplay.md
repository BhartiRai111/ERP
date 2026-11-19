# pb-store / reelBundleTrackingDisplay

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/store/src/reelBundleTrackingDisplay/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHReelNo, getTxtHItemNature
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `ReelBundleTrackingDisplaySetting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Matching With Reel/Bundle No | DmTextBox | `txtReelBundleNo` | `11, false` |
| 2 | Matching With Barcode | DmTextBox | `txtBarCode` | `11, false` |
| 3 | Reel/Bundle No. | DmTextHelp | `txtHReelBundleNo` | `TxtHReelBundleNo_PrpAcs, 12, true` |
| 4 | Properties | DmTextBox | `txtProperties` | `250, false` |

Other widgets (not in tab list): `txtFromDate` (DmTextBox), `txtToDate` (DmTextBox), `txtClosing` (DmTextBoxNumeric), `txtHSite` (DmTextHelpMulti), `txtHItemNature` (DmTextHelp)

**Grid / popup columns** (header, width, db field):
- `txtHSite`: Name [Text, w150, name]
- `txtHReelBundleNo`: Reel/Bundle No. [Text, w140, name]; BarCode [Text, w140, barCode]; Recieve Date [Text, w130, replace(convert(varchar, v_Date, 106), ' ','-')]
- `txtHItemNature`: Item Nature [Text, w0, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHItemNature` → `getTxtHItemNature`
- `txtHReelBundleNo` → `getTxtHReelNo`

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
if (!DmShared.xNull(txtHItemNature.getTag()).equalsIgnoreCase(DmShared.xNull(txtHItemNature.getTag2nd()))) {
clear();
txtHItemNature.setTag2nd(DmShared.xNull(txtHItemNature.getTag()));
```

`txtHReelBundleNo.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHReelNo(txtHItemNature.getTag(), txtReelBundleNo.getText(), txtBarCode.getText
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHReelBundleNo).show();
public void onSuccess(DmResultPageing<List<TxtHReelBundleNo_Mdl>> result) {
if (result.isStatus()) {
txtHReelBundleNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHReelBundleNo).show();
```

`txtHReelBundleNo.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHReelBundleNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHReelBundleNo).show();
```

`txtHReelBundleNo.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHReelBundleNo.getTag()).equalsIgnoreCase(DmShared.xNull(txtHReelBundleNo.getTag2nd()))) {
txtProperties.setText("");
txtHReelBundleNo.setTag2nd(DmShared.xNull(txtHReelBundleNo.getTag()));
if (blnStatus && t1Row != null) {
txtProperties.setText(DmShared.xNull(t1Row.getPropertyDispCsv()));
```
- Alert: "'From Date' is mandatory."
- Alert: "'To Date' is mandatory."
- Alert: "To Date should be greater than from date!"
- Alert: "Period difference cannot be greater than 366 days."
- Alert: "Site Name is mandatory."

### Screen `ReelBundleTrackingDisplay`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmTextBox | `txtSearchValue` | `100, false` |
| 2 | - | DmGridPaging | `grdDisplay` | `GrdSalesDetail_PrpAcs, 20` |

**Grid / popup columns** (header, width, db field):
- `grdDisplay`: Entry No. [Text, w150, recId]; Date [Text, w100, replace(convert(varchar, v_Date, 106), ' ','-')]; Entry Type [Text, w150, entryType]; Party [Text, w250, partyName]; PreparedBy [Text, w150, preparedBy]; Rec Qty [Text, w125, recQty]; Rec PackCount [Text, w125, recPackCount]; Issue Qty. [Text, w100, issueQty]; Issue PackCount [Text, w105, isuPackCount]

**Lookup / suggestion / find sources (server RPC called):**
- `displaySetting` → `getDefault`
- `grdDisplay` → `getReelBundleTrackingDetail`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`displaySetting.addCallBack` → `onOk()`
```java
displaySettingMdl = acDisplaySetting;
refreshGrid();
```

`grdDisplay.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnToEndOfFile).getReelBundleTrackingDetail(displaySettingMdl, helper, blnToEndOfFile, param, new Asy
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<ReelBundleTrackingDisplayGrid_Mdl>> result) {
if (result.isStatus()) {
grdDisplay.setData(result.getData(), result.getPaging());
Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
public void execute() {
int rowIndex = DmShared.iNull(displayRouteList.get((displayRouteList.size() - 1)).getClickRowIndex()).intValue();
int colIndex = DmShared.iNull(displayRouteList.get((displayRouteList.size() - 1)).getClickColIndex()).intValue();
...
```

`grdDisplay.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
grdDisplay.setData(result.getData(), result.getPaging());
Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
public void execute() {
int rowIndex = DmShared.iNull(displayRouteList.get((displayRouteList.size() - 1)).getClickRowIndex()).intValue();
int colIndex = DmShared.iNull(displayRouteList.get((displayRouteList.size() - 1)).getClickColIndex()).intValue();
renderSelectionFill(rowIndex, colIndex);
if (flagOnLoadRefresh) {
flagOnLoadRefresh = false;
if ((displayRouteList.size() > 0)) {
...
```

`grdDisplay.addCallBack` → `execute()`
```java
int rowIndex = DmShared.iNull(displayRouteList.get((displayRouteList.size() - 1)).getClickRowIndex()).intValue();
int colIndex = DmShared.iNull(displayRouteList.get((displayRouteList.size() - 1)).getClickColIndex()).intValue();
renderSelectionFill(rowIndex, colIndex);
if (flagOnLoadRefresh) {
flagOnLoadRefresh = false;
if ((displayRouteList.size() > 0)) {
((CellSelectionModel) grdDisplay.getDmGrid().getGrid().getSelectionModel()).selectCell(rowIndex, colIndex);
} else {
txtSearchValue.setFocus(true);
```

`grdDisplay.addCallBack` → `getParam()`
```java
return null;
```
- Confirm: "Are you sure? You want to Close"

## Files
| file | lines |
|---|---|
| client/ReelBundleTrackingDisplay.java | 500 |
| client/ReelBundleTrackingDisplaySetting.java | 343 |
| client/ReelBundleTrackingDisplaySetting_Dsg.java | 213 |
| client/ReelBundleTrackingDisplay_Dsg.java | 257 |
| server/ReelBundleTrackingDisplay_SrvImpl.java | 563 |
| shared/DefaultValue_Mdl.java | 158 |
| shared/DisplayRoute_Mdl.java | 108 |
| shared/ReelBundleTrackingDisplayGrid_Mdl.java | 180 |
| shared/ReelBundleTrackingDisplaySetting_Mdl.java | 138 |
| shared/ReelBundleTrackingDisplay_Srv.java | 22 |
| shared/ReelBundleTrackingDisplay_SrvAsync.java | 21 |
| shared/RptReelBundleTrackingDisplay_Mdl.java | 52 |
| shared/TxtHReelBundleNo_Mdl.java | 52 |

## UI labels / columns (client)
`Matching With Reel/Bundle No`, `Matching With Barcode`, `Reel/Bundle No.`, `Properties`

## Models (shared DTOs)
### DisplayRoute_Mdl
| type | field |
|---|---|
| String code, | name |
| String | lblHeading |
| String | clickRowIndex |
| String | clickColIndex |
| boolean | ledgerPeriodicSummary |
| String fromDate, | toDate |
| ReelBundleTrackingDisplaySetting_Mdl | displaySetting_mdl |

### RptReelBundleTrackingDisplay_Mdl
| type | field |
|---|---|
| String | totalPurchaseAmt |
| ReelBundleTrackingDisplaySetting_Mdl | displaySettingMdl |
| List<ReelBundleTrackingDisplayGrid_Mdl> | purchaseDetailDisplay_Mdls |
| String | tableName |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | totalPurchaseAmt |
| String | totalPurchaseDispAmt |
| String | totalTaxableAmt |
| String | totalTaxableDispAmt |
| String | totalCgstAmt |
| String | totalCgstDispAmt |
| String | totalIgstAmt |
| String | totalIgstDispAmt |
| String | totalSgstUgstAmt |
| String | totalSgstUgstDispAmt |
| String | totalCessAmt |
| String | totalCessDispAmt |
| String | totalTcs |
| String | totalTcsDisp |
| String | totalSkuQty |
| String | totalSkuQtyDisp |

### ReelBundleTrackingDisplayGrid_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | docId |
| String | siteCode |
| String | siteName |
| String | voucherType |
| String | voucherDate |
| String | voucherCat |
| String | partyName |
| String | preparedBy |
| String | recQty |
| String | issueQty |
| String | recPackCount |
| String | isuPackCount |
| String | moduleName |
| String | menuText |
| boolean | selected |
| String | tableName |
| String | isBold |
| String | isChild |

### TxtHReelBundleNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | barCode |
| String | v_Date |
| String | propertyDispCsv |
| ReelBundleTrackingDisplaySetting_Mdl | reelBundleTrackingDisplaySetting_Mdl |

### ReelBundleTrackingDisplaySetting_Mdl
| type | field |
|---|---|
| String | currentSiteCode |
| String fromDate, | toDate |
| String siteCode, | siteName |
| String | propertyDispCSV |
| String | itemNatureCode |
| String | itemNatureName |
| String | reelBundleNoTag |
| String | reelBundleNoText |
| String | matchingBarCode |
| String | matchingReelBundleNo |

## RPC interface
| method | params | returns |
|---|---|---|
| getDefault | ReelBundleTrackingDisplaySetting_Mdl displaySetting, List<DisplayRoute_Mdl> displayRouteList, String loginDate | DmResult<DefaultValue_Mdl> |
| getReelBundleTrackingDetail | ReelBundleTrackingDisplaySetting_Mdl displaySetting, DmHelper helper, boolean blnToEndOfFile, KeyFieldExtended keyField | DmResultPageing<List<ReelBundleTrackingDisplayGrid_Mdl>> |
| getSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHReelNo | String itemNature, String reelBundleNo, String barCode, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHReelBundleNo_Mdl>> |
| getTxtHItemNature | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| fillSettings |  | DmResult<ReelBundleTrackingDisplaySetting_Mdl> |

## Server `ReelBundleTrackingDisplay_SrvImpl.java` (563 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.getSsnSiteName`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.vNull`, `DmShared.xNull`

### `getDefault`
`public DmResult<DefaultValue_Mdl> getDefault(ReelBundleTrackingDisplaySetting_Mdl displaySetting, List<DisplayRoute_Mdl> displayRouteList, String loginDate)`

- SQL 1: `IF OBJECT_ID('tempdb.. {…} ') IS NOT NULL DROP TABLE {…} ; CREATE TABLE {…} (orderIndex bigint, docId bigInt, site_Code smallint, siteName varchar(80), category varchar(80), v_Type smallint , recId varchar(50), v_Date date , entryType varchar(80) , partyName varchar(250), preparedBy varchar(80) , recQty decimal(18, 2), issueQty decimal(18, 2) , recPackCount decimal(18, 2) , isuPackCount decimal(18, 2))`
- SQL 2: `SELECT vt.description, vt.category, sii.docId, sii.v_Sno, sii.sno , stk.recId, stk.v_Date, stk.site_Code, sm.name AS siteName, stk.v_Type , stk.partyCode, sg.name AS partyName , sii.itemId, bcm.barCode, bcm.reelBundleNo , stk.recQty, stk.isuQty , stk.packRecQty AS recPackCount, stk.packIsuQty AS isuPackCount , CASE WHEN prd.docId IS NOT NULL THEN prd.preparedBy WHEN pc.docId IS NOT NULL THEN pc.preparedBy WHEN pb.docId IS NOT NULL THEN pb.preparedBy WHEN ot.docId IS NOT NULL THEN ot.preparedBy WHEN sb.docId IS NOT NULL THEN sb.preparedBy WHEN sh.docId IS NOT NULL THEN sh.preparedBy END AS preparedBy FROM barCodeMast bcm LEFT JOIN stockItemId sii ON sii.itemId = bcm.barCodeId LEFT JOIN stock stk ON stk.docId = sii.docId AND stk.v_SNo = sii.v_Sno LEFT JOIN siteMast sm ON sm.code = stk.site_Code LEFT JOIN voucher_Type vt ON vt.v_Type = stk.v_Type LEFT JOIN subGroup sg ON sg.subCode = stk.partyCode LEFT JOIN production prd ON prd.docId = stk.docId LEFT JOIN pChallan pc ON pc.docId = stk.docId LEFT JOIN pBill pb ON pb.docId = stk.docId LEFT JOIN orderTransfer ot ON ot.docId = stk.docId LEFT JOIN sBill sb ON sb.docId = stk.docId LEFT JOIN stockHead sh ON sh.docId = stk.docId WHERE 1 = 1 {…} ORDER BY bcm.barCodeId, stk.v_Date, CASE WHEN prd.docId IS NOT NULL THEN prd.u_EntDt_LatestLine WHEN pc.docId IS NOT NULL THEN pc.u_EntDt_LatestLine WHEN pb.docId IS NOT NULL THEN pb.u_EntDt_LatestLine WHEN ot.docId IS NOT NULL THEN ot.transferDt WHEN sb.docId IS NOT NULL THEN sb.u_EntDt_LatestLine WHEN sh.docId IS NOT NULL THEN sh.u_EntDt_LatestLine END`
- SQL 3: `INSERT INTO {…} (orderIndex, site_Code, siteName, category, v_Type, docId, recId, v_Date, entryType, partyName, preparedBy, recQty, issueQty, recPackCount, isuPackCount) VALUES(:orderIndex, :site_Code, :siteName, :category, :v_Type, :docId, :recId , :v_Date, :entryType, :partyName, :preparedBy, :recQty, :issueQty, :recPackCount, :isuPackCount)`
- Params: `:orderIndex` ← `orderIndex`; `:site_Code` ← `DmShared.xNull(rsTemp.getString("site_Code"))`; `:siteName` ← `DmShared.xNull(rsTemp.getString("siteName"))`; `:category` ← `DmShared.xNull(rsTemp.getString("category"))`; `:v_Type` ← `DmShared.xNull(rsTemp.getString("v_Type"))`; `:docId` ← `DmShared.xNull(rsTemp.getString("docId"))`; `:recId` ← `DmShared.xNull(rsTemp.getString("recId"))`; `:v_Date` ← `DmServer.convertDate(DmShared.dNull(rsTemp.getDate("v_Date")))`; `:entryType` ← `DmShared.xNull(rsTemp.getString("description"))`; `:partyName` ← `DmShared.xNull(rsTemp.getString("partyName"))`; `:preparedBy` ← `DmShared.xNull(rsTemp.getString("preparedBy"))`; `:recQty` ← `DmServer.vNull(DmShared.vNull(rsTemp.getString("recQty")).doubleValue(), "0.00")`; `:issueQty` ← `DmServer.vNull(DmShared.vNull(rsTemp.getString("isuQty")).doubleValue(), "0.00")`; `:recPackCount` ← `DmServer.vNull(DmShared.vNull(rsTemp.getString("recPackCount")).doubleValue(), "0.00")`; `:isuPackCount` ← `DmServer.vNull(DmShared.vNull(rsTemp.getString("isuPackCount")).doubleValue(), "0.00")`
- Errors: "Period difference cannot be greater than 366 days!" | "To Date should be greater than from date!"
- Other calls: `STServer.getDateDifference`

### `getReelBundleTrackingDetail`
`public DmResultPageing<List<ReelBundleTrackingDisplayGrid_Mdl>> getReelBundleTrackingDetail(ReelBundleTrackingDisplaySetting_Mdl displaySetting, DmHelper helper, boolean blnToEndOfFile, KeyFieldExtended keyField)`

- SQL 1: `SELECT rbtd.site_Code, rbtd.siteName, rbtd.category,rbtd.v_Type ,rbtd.orderIndex, rbtd.docId, rbtd.recId , rbtd.v_Date, rbtd.entryType, rbtd.partyName , rbtd.preparedBy, rbtd.recQty, rbtd.issueQty, rbtd.recPackCount, rbtd.isuPackCount,vt.mnuAttachedInModule, vt.mnuText FROM {…} rbtd LEFT JOIN voucher_Type vt ON vt.v_Type = rbtd.v_Type`

### `getTable`
`private String getTable(DmRSSPrivate rssPrivate)`


### `getParameter`
`private void getParameter(ReelBundleTrackingDisplaySetting_Mdl filter, DmPreparedStatement psTemp)`


### `fillSettings`
`public DmResult<ReelBundleTrackingDisplaySetting_Mdl> fillSettings()`


### `getSite`
`public DmResultPageing<List<KeyFieldExtended>> getSite(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm WHERE sm.code in ( {…} :code {…} )`

### `getTxtHReelNo`
`public DmResultPageing<List<TxtHReelBundleNo_Mdl>> getTxtHReelNo(String itemNature, String reelBundleNo, String barcode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT DISTINCT bcm.barCodeId , MAX(bcm.barCode) AS barCode , MAX(bcm.reelBundleNo) AS name , MAX(stk.v_Date) AS v_Date , MAX(stk.recId) AS recId , MAX(ib.propertyDispCsv) AS propertyDispCsv , MAX(ib.itemNature) AS itemNatureCode , MAX(itn.name) AS itemNatureName FROM barCodeMast bcm LEFT JOIN itemId iid ON iid.code = bcm.barCodeId LEFT JOIN itemBatch ib ON ib.code = iid.ibCode LEFT JOIN itemNature itn ON itn.code = ib.itemNature LEFT JOIN itemIdStock iis ON iis.itemId = bcm.barCodeId LEFT JOIN stock stk ON stk.docId = iis.creationDocId AND stk.v_SNo = iis.creationV_SNo WHERE 1 = 1 AND iis.site_Code = :site_Code {…} GROUP BY bcm.barCodeId`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:reelBundleNo` ← `"%" + reelBundleNo.trim() + "%"`; `:barCodeId` ← `"%" + barcode.trim() + "%"`

### `getTxtHItemNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemNature(DmHelper helper, boolean blnToEndOfFile)`


### `getItemNatureDetail`
`private List<KeyFieldExtended> getItemNatureDetail(String itemNatureCode, DmHelper helper, boolean blnToEndOfFile, Connection cnTemp) throws Exception`

- SQL 1: `SELECT itn.code, itn.name, itn.manualCode FROM itemNature as itn WHERE itn.isActive = 1 {…}`
- Params: `:code` ← `itemNatureCode`; `:R` ← `DmShared.xNull(STEnum.ItemNature.REEL.getManualCode())`; `:S` ← `DmShared.xNull(STEnum.ItemNature.SHEET.getManualCode())`
- Other calls: `REEL.getManualCode`, `SHEET.getManualCode`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.self.client`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`, `paperbiz.store.reelBundleTrackingDisplay.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| getDefault | `if (dateDiff > 366)` | "Period difference cannot be greater than 366 days!" |
| getDefault | `} else if (dateDiff < 0)` | "To Date should be greater than from date!" |

## Notes (hand-written)
_No hand-written notes yet._
