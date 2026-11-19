# pb-dashboard / inventoryDashBoard

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/dashBoard/src/inventoryDashBoard/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHSite, getTxtHCategory, getTxtHItemGroup, getTxtHItemName, getTxtHTodayStockType
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `InventoryDashBoard`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `txtSearchValue` (DmTextBox), `txtDate` (DmTextBox), `txtHSite` (DmTextHelpMulti)

**Grid / popup columns** (header, width, db field):
- `txtHSite`: Site [Text, w200, name]

**Lookup / suggestion / find sources (server RPC called):**
- `productionDispatchDisplay` → `getTxtHSite`
- `settingTodayStock` → `getDefaultValues`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`productionDispatchDisplay.addCallBack` → `onClose()`
```java
flwTemp.remove(productionDispatchDisplay);
flwTemp.add(vrtInnerForm);
```

`txtHSite.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHSite(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
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

`settingProductDispatch.addCallBack` → `onOk()`
```java
settingProductionDispatch_Mdl = searchValue_Mdl;
getProductionVsDispatchChart();
```

`settingTodayStock.addCallBack` → `onOk()`
```java
getTodaysStockChart(DmShared.dNull(txtDate.getText()), DmShared.xNull(txtHSite.getTag()));
```
- Confirm: "Are you sure? You want to close."

### Screen `Setting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Unit | DmTextHelp | `txtHSKU` | `KeyField_PrpAcsSKU, 4, true` |
| 2 | Item Group | DmTextHelpMulti | `txtHItemGroup` | `KeyField_PrpAcs, 6, false` |
| 3 | Item Category | DmTextHelpMulti | `txtHCategory` | `KeyField_PrpAcs, 6, false` |
| 4 | Item Name | DmTextHelpMulti | `txtHItemName` | `com.google.gwt.core.shared.TxtHItemName_PrpAcs, 7, false` |

**Grid / popup columns** (header, width, db field):
- `txtHCategory`: Item Category [Text, w220, categoryName]
- `txtHItemGroup`: Item Group [Text, w250, Name]
- `txtHSKU`: Name [Text, w80, unit]
- `txtHItemName`: Item Name [Text, w200, name]; Manual Code [Text, w100, manualCode]; SKU [Text, w50, sku]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHSKU` → `getSKUDetails`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHCategory.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHCategory(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHCategory).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
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

`txtHSKU.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getSKUDetails(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFiel
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSKU).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHSKU.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSKU).show();
```

`txtHSKU.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSKU.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSKU).show();
```

`txtHItemName.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItemName(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemName).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
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
- Alert: "SKU is mandatory!"

### Screen `TodayStockPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Stock Type | DmTextHelp | `txtHTodayStockType` | `KeyField_PrpAcs, 3, true` |

**Grid / popup columns** (header, width, db field):
- `txtHTodayStockType`: Stock On [Text, w180, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHTodayStockType` → `getTxtHTodayStockType`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHTodayStockType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHTodayStockType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHTodayStockType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHTodayStockType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTodayStockType).show();
```

`txtHTodayStockType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHTodayStockType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTodayStockType).show();
```

## Files
| file | lines |
|---|---|
| client/InventoryDashBoard.java | 1340 |
| client/InventoryDashBoard_Dsg.java | 502 |
| client/Setting.java | 282 |
| client/Setting_Dsg.java | 272 |
| client/TodayStockPopup.java | 165 |
| client/TodayStockPopup_Dsg.java | 140 |
| server/InventoryDashBoard_SrvImpl.java | 1691 |
| shared/AgeingChartSetting_Mdl.java | 43 |
| shared/CardDetail_Mdl.java | 111 |
| shared/DefaultValue_Mdl.java | 78 |
| shared/InventoryDashBoard_Srv.java | 48 |
| shared/InventoryDashBoard_SrvAsync.java | 48 |
| shared/RequitionFilter_Mdl.java | 124 |

## UI labels / columns (client)
`0`, `-`, `Date`, `Site`, `Day Requisition`, `Day Indent`, `Day PO`, `Values`, `Day Sales Order`, `Quick Links`, `Production Vs Dispatch`, `Pending Requisition`, `Today's Stock`, `Pending Indent`, `Stock Type`, `Unit`, `Item Group`, `Item Category`, `Item Name`

## Models (shared DTOs)
### DefaultValue_Mdl
| type | field |
|---|---|
| String | currentDate |
| String | currentSiteCode |
| String | currentSiteName |
| String | unitCode |
| String | unitName |
| String | displayTypeCode |
| String | displayTypeName |

### RequitionFilter_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | FromDate |
| String | ToDate |
| String | ItemName |
| String | ItemCode |
| String | DepartmentName |
| String | DepartmentCode |
| String | IndenterName |
| String | IndenterCode |
| String | requisitionDocIds |
| String | requisitionRecIds |
| String | siteCode |
| String | siteName |

### AgeingChartSetting_Mdl
| type | field |
|---|---|
| int | slab1 |
| int | slab2 |
| int | slab3 |
| int | slab4 |
| int | slab5 |

### CardDetail_Mdl
| type | field |
|---|---|
| String | count |
| String | itemType |
| String | stockValue |
| String | itemTypeCode |
| String | supplierName |
| String | supplierCode |
| String | supplierManualCode |
| List<DmChart_Mdl> | listChartDateRecQty |
| List<DmChart_Mdl> | listChartDateIsuQty |
| String | unitName |
| List<DmChart_Mdl> | listChardData |

## RPC interface
| method | params | returns |
|---|---|---|
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| getServerDateTime |  | DmResult<String[]> |
| getDayRequisition | String date, String siteList | DmResult<String> |
| getDayIndent | String date, String siteList | DmResult<String> |
| getDayPo | String date, String siteList | DmResult<String> |
| getDaySO | String date, String siteList | DmResult<String> |
| getTodaysStockChart | String date, String siteList | DmResult<CardDetail_Mdl> |
| getTodaysStockChartSupplier | String date, String siteList | DmResult<CardDetail_Mdl> |
| getAgeingPendingRequisition | String date, String siteList | DmResult<CardDetail_Mdl> |
| getAgeingPendingIndent | String date, String siteList | DmResult<CardDetail_Mdl> |
| getProductionVsDispatchChart | SearchValue_Mdl searchValue_Mdl | DmResult<CardDetail_Mdl> |
| getTxtHSite | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getSKUDetails | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHCategory | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemGroup | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemName | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTodayStockType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Server `InventoryDashBoard_SrvImpl.java` (1691 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerDateTime`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.vNull`, `DmShared.convertDate`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`

- SQL 1: `select sm.code,sm.name AS site from siteMast sm where code = :site_Code`
- SQL 2: `SELECT TOP 1 im.sku , MAX(um.unit) AS skuName FROM itemMast im LEFT JOIN unitMast um on um.code = im.sku WHERE im.filterItemType IN (:FM,:SM) GROUP BY sku`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:FM` ← `DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTemp)`; `:SM` ← `DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTemp)`

### `getDayRequisition`
`public DmResult<String> getDayRequisition(String date, String siteList)`

- SQL 1: `SELECT COUNT(req.docId) as cnt from requisition as req left join voucher_type as vt on req.v_Type = vt.v_Type LEFT JOIN( select reqd.docId from requisitionDetail as reqd WHERE reqd.isStatus IN (0,2) AND CASE WHEN reqd.isStatus = 0 THEN IIF(( ISNULL(reqd.reqQty, 0) - ISNULL(reqd.issueQty, 0)) > 0,1,0) ELSE 1 END = 1 group by reqd.docId ) as reqd on reqd.docId = req.docId WHERE reqd.docId is not null and ISNULL(req.approvedBy,'') <> '' and vt.category IN (:REQ,:SREQ) and req.v_Date = :asOnDate {…}`
- Params: `:asOnDate` ← `DmServer.convertDate(date)`; `:REQ` ← `pvc.REQUISITION`; `:SREQ` ← `pvc.SERVICE_REQUISITION`

### `getDayIndent`
`public DmResult<String> getDayIndent(String date, String siteList)`

- SQL 1: `select count(ind.docId) as cnt from indent as ind left join voucher_type as vt on ind.v_Type = vt.v_Type left join( select indd.docId from indentDetail as indd where indd.isStatus IN (0,2) AND CASE WHEN indd.isStatus = 0 THEN IIF((ISNULL(indd.aprQty,0) - ISNULL(indd.orderedQty,0)) > 0,1,0) ELSE 1 END = 1 group by indd.docId ) as iddd on iddd.docId = ind.docId where iddd.docId is not null and vt.category IN (:IND,:SIND) and isNull(ind.approvedBy,'') <> '' and ind.v_Date = :asOnDate {…}`
- Params: `:asOnDate` ← `DmServer.convertDate(date)`; `:IND` ← `pvc.GENERAL_INDENT`; `:SIND` ← `pvc.SERVICE_INDENT`

### `getDayPo`
`public DmResult<String> getDayPo(String date, String siteList)`

- Other calls: `PurchaseDashBoard_Server.getDayPurchaseOrder`

### `getDaySO`
`public DmResult<String> getDaySO(String date, String siteList)`

- Other calls: `SalesDashBoard_Server.getDaySalesOrder`

### `getTodaysStockChart`
`public DmResult<CardDetail_Mdl> getTodaysStockChart(String date, String siteList)`

- SQL 1: `SELECT MAX(it.name) as itemType, im.itemType as itemTypeCode , SUM(CASE WHEN ISNULL(stk.recQty,0) > 0 THEN ISNULL( {…} ,0) WHEN ISNULL(stk.isuQty,0) < 0 THEN ABS(ISNULL( {…} ,0)) WHEN ISNULL(stk.isuQty,0) > 0 THEN (0 - ISNULL( {…} ,0)) WHEN ISNULL(stk.recQty,0) < 0 THEN ISNULL( {…} ,0) WHEN (ISNULL(stk.isuQty,0) + ISNULL(stk.recQty,0) ) = 0 THEN ISNULL( {…} ,0) ELSE 0 END) AS stockValue FROM stock stk LEFT JOIN itemMast im ON im.code = stk.itemCode LEFT JOIN itemType it ON it.code = im.itemType LEFT JOIN voucher_Type vt ON vt.v_Type = stk.v_Type WHERE stk.v_date <= :v_date {…} GROUP BY im.itemType`
- Params: `:v_date` ← `DmServer.convertDate(currentDate)`

### `getTodaysStockChartSupplier`
`public DmResult<CardDetail_Mdl> getTodaysStockChartSupplier(String date, String siteList)`

- SQL 1: `WITH cteItemStock AS ( SELECT iStk.site_Code, iStk.departmentCode, iStk.itemCode, iStk.ibCode , iStk.stockValueDocId, iStk.stockValueV_Sno, iStk1.stockValueSno , iStk1.landedSKURate, stk.partyCode AS mainPartyCode , vt.description AS vTypeDescription FROM itemStock iStk LEFT JOIN stock stk ON stk.docId = iStk.stockValueDocId AND stk.v_SNo = iStk.stockValueV_Sno AND stk.stockValueSno = stk.stockValueSno LEFT JOIN voucher_Type vt ON vt.v_Type = iStk.stockValueV_Type LEFT JOIN itemStock iStk1 ON iStk1.stockValueDocId = iStk.stockValueDocId AND iStk1.stockValueV_Sno = iStk.stockValueV_Sno AND iStk1.site_Code = iStk.site_Code AND iStk1.departmentCode = iStk.departmentCode AND iStk1.itemCode = iStk.itemCode AND iStk1.ibCode = iStk.ibCode WHERE iStk.stockValueSno = 1 ), cteStock AS ( SELECT stk.docId, stk.v_SNo, stk.v_Type, stk.v_Date , stk.site_Code, stk.departmentCode, stk.itemCode, stk.ibCode , sf.stockValueDocId, sf.stockValueV_Sno, sf.stockValueSno , sf.recQty, sf.isuQty FROM stock stk LEFT JOIN stockFifo sf ON sf.docId = stk.docId AND sf.v_SNo = stk.v_SNo )`
- SQL 2: `SELECT cIStk.mainPartyCode , MAX(sg.name) AS mainPartyName , MAX(sg.manualCode) AS partyManualCode , CAST(SUM((ISNULL(cStk.recQty, 0) - ISNULL(cStk.isuQty, 0)) * ISNULL(cIStk.landedSKURate, 0)) AS DECIMAL(18,2)) AS stockValue FROM cteStock cStk LEFT JOIN cteItemStock cIStk ON cIStk.stockValueDocId = cStk.stockValueDocId AND cIStk.stockValueV_Sno = cStk.stockValueV_Sno AND cIStk.stockValueSno = cStk.stockValueSno AND cIStk.site_Code = cStk.site_Code AND cIStk.departmentCode = cStk.departmentCode AND cIStk.itemCode = cStk.itemCode AND cIStk.ibCode = cStk.ibCode LEFT JOIN itemMast im ON im.code = cStk.itemCode LEFT JOIN subGroup sg ON sg.subCode = cIStk.mainPartyCode LEFT JOIN unitMast um ON um.code = im.sku WHERE cStk.v_date <= :v_date {…} GROUP BY cIStk.mainPartyCode HAVING SUM((ISNULL(cStk.recQty, 0) - ISNULL(cStk.isuQty, 0))) <> 0 ORDER BY mainPartyName`
- Params: `:v_date` ← `DmServer.convertDate(currentDate)`

### `getAgeingPendingRequisition`
`public DmResult<CardDetail_Mdl> getAgeingPendingRequisition(String date, String siteList)`

- SQL 1: `SELECT COUNT(CASE WHEN req.V_Date > ' {…} ' AND req.V_Date <= ' {…} ' THEN req.docId ELSE NULL END) as slab1 , COUNT(CASE WHEN req.V_Date > ' {…} ' AND req.V_Date <= ' {…} ' THEN req.docId ELSE NULL END) as slab2 , COUNT(CASE WHEN req.V_Date > ' {…} ' AND req.V_Date <= ' {…} ' THEN req.docId ELSE NULL END) as slab3 , COUNT(CASE WHEN req.V_Date > ' {…} ' AND req.V_Date <= ' {…} ' THEN req.docId ELSE NULL END) as slab4 , COUNT(CASE WHEN req.V_Date > ' {…} ' AND req.V_Date <= ' {…} ' THEN req.docId ELSE NULL END) as slab5 , COUNT(CASE WHEN req.V_Date <= ' {…} ' THEN req.docId ELSE NULL END) as slab6 from requisition as req left join voucher_type as vt on req.v_Type = vt.v_Type left join( SELECT reqd.docId FROM requisitionDetail as reqd WHERE reqd.isStatus = 0 AND (( ISNULL(reqd.reqQty, 0) - ISNULL(reqd.issueQty, 0)) > 0) GROUP BY reqd.docId ) as reqd on reqd.docId = req.docId WHERE reqd.docId is not null and vt.category IN (:REQ,:SREQ,:PREQ) and req.v_date <= :asOnDate {…}`
- Params: `:asOnDate` ← `DmServer.convertDate(date)`; `:REQ` ← `pvc.REQUISITION`; `:SREQ` ← `pvc.SERVICE_REQUISITION`; `:PREQ` ← `pvc.PRODUCTION_REQUISITION`
- Other calls: `CalendarUtil.addDaysToDate`

### `getAgeingPendingIndent`
`public DmResult<CardDetail_Mdl> getAgeingPendingIndent(String date, String siteList)`

- SQL 1: `SELECT COUNT(CASE WHEN ind.V_Date > ' {…} ' AND ind.V_Date <= ' {…} ' THEN ind.docId ELSE NULL END) as slab1 , COUNT(CASE WHEN ind.V_Date > ' {…} ' AND ind.V_Date <= ' {…} ' THEN ind.docId ELSE NULL END) as slab2 , COUNT(CASE WHEN ind.V_Date > ' {…} ' AND ind.V_Date <= ' {…} ' THEN ind.docId ELSE NULL END) as slab3 , COUNT(CASE WHEN ind.V_Date > ' {…} ' AND ind.V_Date <= ' {…} ' THEN ind.docId ELSE NULL END) as slab4 , COUNT(CASE WHEN ind.V_Date > ' {…} ' AND ind.V_Date <= ' {…} ' THEN ind.docId ELSE NULL END) as slab5 , COUNT(CASE WHEN ind.V_Date <= ' {…} ' THEN ind.docId ELSE NULL END) as slab6 from indent as ind LEFT JOIN voucher_type AS vt on ind.v_Type = vt.v_Type LEFT JOIN( SELECT indd.docId FROM indentDetail as indd WHERE indd.isStatus = 0 AND ((ISNULL(indd.aprQty,0) - ISNULL(indd.orderedQty,0)) > 0) GROUP BY indd.docId ) as indd on indd.docId = ind.docId WHERE indd.docId is not null and vt.category IN (:IND,:SIND) and ind.v_date <= :asOnDate {…}`
- Params: `:asOnDate` ← `DmServer.convertDate(date)`; `:IND` ← `pvc.GENERAL_INDENT`; `:SIND` ← `pvc.SERVICE_INDENT`
- Other calls: `CalendarUtil.addDaysToDate`

### `getProductionVsDispatchChart`
`public DmResult<CardDetail_Mdl> getProductionVsDispatchChart(SearchValue_Mdl searchValue_Mdl)`

- SQL 1: `SELECT um.unit FROM unitMast um WHERE um.code = :sku`
- SQL 2: `SELECT ISNULL(SUM(CASE WHEN MONTH(stk.v_Date) = 4 THEN IIF(ISNULL(stk.recQty,0) > 0,ISNULL(stk.recQty,0),0) ELSE 0 END),0) as aprRecQty, ISNULL(SUM(CASE WHEN MONTH(stk.v_Date) = 4 THEN IIF((ISNULL(stk.isuQty,0)>0) OR (ISNULL(stk.recQty,0)<0),(ISNULL(stk.isuQty,0) - ISNULL(stk.recQty,0)),0) ELSE 0 END),0) as aprIsuQty, ISNULL(SUM(CASE WHEN MONTH(stk.v_Date) = 5 THEN IIF(ISNULL(stk.recQty,0) > 0,ISNULL(stk.recQty,0),0) ELSE 0 END),0) as mayRecQty, ISNULL(SUM(CASE WHEN MONTH(stk.v_Date) = 5 THEN IIF((ISNULL(stk.isuQty,0)>0) OR (ISNULL(stk.recQty,0)<0),(ISNULL(stk.isuQty,0) - ISNULL(stk.recQty,0)),0) ELSE 0 END),0) as mayIsuQty, ISNULL(SUM(CASE WHEN MONTH(stk.v_Date) = 6 THEN IIF(ISNULL(stk.recQty,0) > 0,ISNULL(stk.recQty,0),0) ELSE 0 END),0) as junRecQty, ISNULL(SUM(CASE WHEN MONTH(stk.v_Date) = 6 THEN IIF((ISNULL(stk.isuQty,0)>0) OR (ISNULL(stk.recQty,0)<0),(ISNULL(stk.isuQty,0) - ISNULL(stk.recQty,0)),0) ELSE 0 END),0) as junIsuQty, ISNULL(SUM(CASE WHEN MONTH(stk.v_Date) = 7 THEN IIF(ISNULL(stk.recQty,0) > 0,ISNULL(stk.recQty,0),0) ELSE 0 END),0) as julRecQty, ISNULL(SUM(CASE WHEN MONTH(stk.v_Date) = 7 THEN IIF((ISNULL(stk.isuQty,0)>0) OR (ISNULL(stk.recQty,0)<0),(ISNULL(stk.isuQty,0) - ISNULL(stk.recQty,0)),0) ELSE 0 END),0) as julIsuQty, ISNULL(SUM(CASE WHEN MONTH(stk.v_Date) = 8 THEN IIF(ISNULL(stk.recQty,0) > 0,ISNULL(stk.recQty,0),0) ELSE 0 END),0) as augRecQty, ISNULL(SUM(CASE WHEN MONTH(stk.v_Date) = 8 THEN IIF((ISNULL(stk.isuQty,0)>0) OR (ISNULL(stk.recQty,0)<0),(ISNULL(stk.isuQty,0) - ISNULL(stk.recQty,0)),0) ELSE 0 END),0) as augIsuQty, ISNULL(SUM(CASE WHEN MONTH(stk.v_Date) = 9 THEN IIF(ISNULL(stk.recQty,0) > 0,ISNULL(stk.recQty,0),0) ELSE 0 END),0) as sepRecQty, ISNULL(SUM(CASE WHEN MONTH(stk.v_Date) = 9 THEN IIF((ISNULL(stk.isuQty,0)>0) OR (ISNULL(stk.recQty,0)<0),(ISNULL(stk.isuQty,0) - ISNULL(stk.recQty,0)),0) ELSE 0 END),0) as sepIsuQty, ISNULL(SUM(CASE WHEN MONTH(stk.v_Date) = 10 THEN IIF(ISNULL(stk.recQty,0) > 0,ISNULL(stk.recQty,0),0) ELSE 0 END),0) as octRecQty, ISNULL(SUM(CASE WHEN MONTH(stk.v_Date) = 10 THEN IIF((ISNULL(stk.isuQty,0)>0) OR (ISNULL(stk.recQty,0)<0),(ISNULL(stk.isuQty,0) - ISNULL(stk.recQty,0)),0) ELSE 0 END),0) as octIsuQty, ISNULL(SUM(CASE WHEN MONTH(stk.v_Date) = 11 THEN IIF(ISNULL(stk.recQty,0) > 0,ISNULL(stk.recQty,0),0) ELSE 0 END),0) as novRecQty, ISNULL(SUM(CASE WHEN MONTH(stk.v_Date) = 11 THEN IIF((ISNULL(stk.isuQty,0)>0) OR (ISNULL(stk.recQty,0)<0),(ISNULL(stk.isuQty,0) - ISNULL(stk.recQty,0)),0) ELSE 0 END),0) as novIsuQty, ISNULL(SUM(CASE WHEN MONTH(stk.v_Date) = 12 THEN IIF(ISNULL(stk.recQty,0) > 0,ISNULL(stk.recQty,0),0) ELSE 0 END),0) as decRecQty, ISNULL(SUM(CASE WHEN MONTH(stk.v_Date) = 12 THEN IIF((ISNULL(stk.isuQty,0)>0) OR (ISNULL(stk.recQty,0)<0),(ISNULL(stk.isuQty,0) - ISNULL(stk.recQty,0)),0) ELSE 0 END),0) as decIsuQty, ISNULL(SUM(CASE WHEN MONTH(stk.v_Date) = 1 THEN IIF(ISNULL(stk.recQty,0) > 0,ISNULL(stk.recQty,0),0) ELSE 0 END),0) as janRecQty, ISNULL(SUM(CASE WHEN MONTH(stk.v_Date) = 1 THEN IIF((ISNULL(stk.isuQty,0)>0) OR (ISNULL(stk.recQty,0)<0),(ISNULL(stk.isuQty,0) - ISNULL(stk.recQty,0)),0) ELSE 0 END),0) as janIsuQty, ISNULL(SUM(CASE WHEN MONTH(stk.v_Date) = 2 THEN IIF(ISNULL(stk.recQty,0) > 0,ISNULL(stk.recQty,0),0) ELSE 0 END),0) as febRecQty, ISNULL(SUM(CASE WHEN MONTH(stk.v_Date) = 2 THEN IIF((ISNULL(stk.isuQty,0)>0) OR (ISNULL(stk.recQty,0)<0),(ISNULL(stk.isuQty,0) - ISNULL(stk.recQty,0)),0) ELSE 0 END),0) as febIsuQty, ISNULL(SUM(CASE WHEN MONTH(stk.v_Date) = 3 THEN IIF(ISNULL(stk.recQty,0) > 0,ISNULL(stk.recQty,0),0) ELSE 0 END),0) as marRecQty, ISNULL(SUM(CASE WHEN MONTH(stk.v_Date) = 3 THEN IIF((ISNULL(stk.isuQty,0)>0) OR (ISNULL(stk.recQty,0)<0),(ISNULL(stk.isuQty,0) - ISNULL(stk.recQty,0)),0) ELSE 0 END),0) as marIsuQty FROM unitMast um LEFT JOIN itemMast AS im ON im.sku = um.code LEFT JOIN stock AS stk ON stk.itemCode = im.code LEFT JOIN siteMast AS sm ON sm.code = stk.site_Code LEFT JOIN voucher_Type vt ON vt.v_Type = stk.v_Type WHERE stk.v_Date `
- Params: `:sku` ← `searchValue_Mdl.getSkuCode()`; `:startDate` ← `DmServer.convertDate(searchValue_Mdl.getFromDate())`; `:endDate` ← `DmServer.convertDate(searchValue_Mdl.getToDate())`; `:sku` ← `searchValue_Mdl.getSkuCode()`; `:FM` ← `DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTemp)`; `:SM` ← `DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTemp)`

### `getProductionVsDispatchChart_`
`public DmResult<CardDetail_Mdl> getProductionVsDispatchChart_(SearchValue_Mdl searchValue_Mdl)`

- SQL 1: `SELECT um.unit FROM unitMast um WHERE um.code = :sku`
- SQL 2: `SELECT ISNULL(SUM(CASE WHEN MONTH(stk.v_Date) = 4 THEN stk.recQty ELSE 0 END),0) as aprRecQty, ISNULL(SUM(CASE WHEN MONTH(stk.v_Date) = 4 THEN stk.isuQty ELSE 0 END),0) as aprIsuQty, ISNULL(SUM(CASE WHEN MONTH(stk.v_Date) = 5 THEN stk.recQty ELSE 0 END),0) as mayRecQty, ISNULL(SUM(CASE WHEN MONTH(stk.v_Date) = 5 THEN stk.isuQty ELSE 0 END),0) as mayIsuQty, ISNULL(SUM(CASE WHEN MONTH(stk.v_Date) = 6 THEN stk.recQty ELSE 0 END),0) as junRecQty, ISNULL(SUM(CASE WHEN MONTH(stk.v_Date) = 6 THEN stk.isuQty ELSE 0 END),0) as junIsuQty, ISNULL(SUM(CASE WHEN MONTH(stk.v_Date) = 7 THEN stk.recQty ELSE 0 END),0) as julRecQty, ISNULL(SUM(CASE WHEN MONTH(stk.v_Date) = 7 THEN stk.isuQty ELSE 0 END),0) as julIsuQty, ISNULL(SUM(CASE WHEN MONTH(stk.v_Date) = 8 THEN stk.recQty ELSE 0 END),0) as augRecQty, ISNULL(SUM(CASE WHEN MONTH(stk.v_Date) = 8 THEN stk.isuQty ELSE 0 END),0) as augIsuQty, ISNULL(SUM(CASE WHEN MONTH(stk.v_Date) = 9 THEN stk.recQty ELSE 0 END),0) as sepRecQty, ISNULL(SUM(CASE WHEN MONTH(stk.v_Date) = 9 THEN stk.isuQty ELSE 0 END),0) as sepIsuQty, ISNULL(SUM(CASE WHEN MONTH(stk.v_Date) = 10 THEN stk.recQty ELSE 0 END),0) as octRecQty, ISNULL(SUM(CASE WHEN MONTH(stk.v_Date) = 10 THEN stk.isuQty ELSE 0 END),0) as octIsuQty, ISNULL(SUM(CASE WHEN MONTH(stk.v_Date) = 11 THEN stk.recQty ELSE 0 END),0) as novRecQty, ISNULL(SUM(CASE WHEN MONTH(stk.v_Date) = 11 THEN stk.isuQty ELSE 0 END),0) as novIsuQty, ISNULL(SUM(CASE WHEN MONTH(stk.v_Date) = 12 THEN stk.recQty ELSE 0 END),0) as decRecQty, ISNULL(SUM(CASE WHEN MONTH(stk.v_Date) = 12 THEN stk.isuQty ELSE 0 END),0) as decIsuQty, ISNULL(SUM(CASE WHEN MONTH(stk.v_Date) = 1 THEN stk.recQty ELSE 0 END),0) as janRecQty, ISNULL(SUM(CASE WHEN MONTH(stk.v_Date) = 1 THEN stk.isuQty ELSE 0 END),0) as janIsuQty, ISNULL(SUM(CASE WHEN MONTH(stk.v_Date) = 2 THEN stk.recQty ELSE 0 END),0) as febRecQty, ISNULL(SUM(CASE WHEN MONTH(stk.v_Date) = 2 THEN stk.isuQty ELSE 0 END),0) as febIsuQty, ISNULL(SUM(CASE WHEN MONTH(stk.v_Date) = 3 THEN stk.recQty ELSE 0 END),0) as marRecQty, ISNULL(SUM(CASE WHEN MONTH(stk.v_Date) = 3 THEN stk.isuQty ELSE 0 END),0) as marIsuQty FROM unitMast um LEFT JOIN itemMast AS im ON im.sku = um.code LEFT JOIN stock AS stk ON stk.itemCode = im.code LEFT JOIN siteMast AS sm ON sm.code = stk.site_Code LEFT JOIN voucher_Type vt ON vt.v_Type = stk.v_Type WHERE stk.v_Date BETWEEN :startDate AND :endDate AND vt.category IN (:DPR,:PR,:SI,:SIO) AND im.filterItemType IN (:FM,:SM) {…}`
- Params: `:sku` ← `searchValue_Mdl.getSkuCode()`; `:DPR` ← `pvc.PRODUCTION_RECEIVE`; `:PR` ← `pvc.PROCESS_RECEIVE`; `:SI` ← `pvc.SALES_INVOICE`; `:SIO` ← `pvc.GENERAL_SALES_INVOICE`; `:startDate` ← `DmServer.convertDate(searchValue_Mdl.getFromDate())`; `:endDate` ← `DmServer.convertDate(searchValue_Mdl.getToDate())`; `:sku` ← `searchValue_Mdl.getSkuCode()`; `:FM` ← `DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTemp)`; `:SM` ← `DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTemp)`

### `getServerDateTime`
`public DmResult<String[]> getServerDateTime()`


### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHCategory`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCategory(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ic.code AS categoryCode , ic.name AS categoryName FROM itemcat ic`

### `getTxtHItemGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ig.code, ig.name FROM itemgroup ig`

### `getSKUDetails`
`public DmResultPageing<List<KeyFieldExtended>> getSKUDetails(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code, unit from unitmast`

### `getTxtHItemName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , um.unit AS sku , im.manualCode FROM itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN unitmast um ON im.sku = um.code WHERE im.IsActive = 1 AND (im.filterItemType IN (:FM,:SM) OR im.isCommon = 1) AND sl.site_Code = :site_Code AND im.isQuotationMandatory = 0`
- Params: `:FM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:SM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHTodayStockType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTodayStockType(DmHelper helper, boolean blnToEndOfFile)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmChart.shared`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.self.client`, `paperbiz.dashBoard.indent.shared`, `paperbiz.dashBoard.inventoryDashBoard.shared`, `paperbiz.dashBoard.productionDispatchDisplay.client`, `paperbiz.dashBoard.productionDispatchDisplay.shared`, `paperbiz.dashBoard.purchaseDashBoard.server`, `paperbiz.dashBoard.purchaseDashBoard.shared`, `paperbiz.dashBoard.salesDashBoard.server`, `paperbiz.dashBoard.salesDashBoard.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stStatic.client`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
