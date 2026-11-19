# pb-budget / budgetPlanning

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/budget/src/budgetPlanning/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: budgetPlanning, budgetLedger
- Update tables: budgetPlanning, budgetLedger
- Delete cascade (in order): budgetPlanning, budgetLedger
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHCostCenter, getTxtHIsAmt, getTxtHIsAddition, getTxtHVoucherType
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `BudgetPlanningGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Voucher Type | DmTextHelp | `txtHVType` | `KeyField_PrpAcs, 4, true` |
| 2 | Budget Header | DmTextHelp | `txtHBudgetHead` | `BudgetHead_PrpAcs, 10, true, true` |
| 3 | Budget Type | DmTextHelp | `txtHBudgetType` | `BudgetType_PrpAcs, 10, true, true` |
| 4 | Cost Center | DmTextHelp | `txtHCostCenter` | `KeyField_PrpAcs, 10, false` |
| 5 | From Date | DmTextBox | `txtFromDt` | `11, true` |
| 6 | To Date | DmTextBox | `txtToDt` | `11, true` |
| 7 | Is Amount | DmTextHelp | `txtHIsAmt` | `KeyField_PrpAcs, 3, true` |
| 8 | Is Addition | DmTextHelp | `txtHIsAddition` | `KeyField_PrpAcs, 3, true` |
| 9 | Figure In Lackhs | DmTextBox | `txtFigInLackhs` | `10, true` |
| 10 | Manual No. | DmTextBox | `txtManualNo` | `30, true` |

Other widgets (not in tab list): `txtPlanningNo` (DmTextBox), `txtVDate` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `txtHVType`: Voucher Type [Text, w100, Description]
- `txtHBudgetHead`: Budget Header [Text, w150, name]; Short Name [Text, w100, shortName]; Manual No. [Text, w100, refManualNo]
- `txtHBudgetType`: Budget Type [Text, w150, name]; Short Name [Text, w100, shortName]; Manual No. [Text, w100, refManualNo]
- `txtHCostCenter`: Name [Text, w0, NAME]
- `txtHIsAmt`: Name [Text, w0, NAME]
- `txtHIsAddition`: Name [Text, w0, NAME]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHVType` → `getTxtHVoucherType`
- `txtHBudgetHead` → `getBudgetHead`
- `txtHBudgetType` → `getBudgetType`
- `txtHCostCenter` → `getTxtHCostCenter`
- `txtHIsAmt` → `getTxtHIsAmt`
- `txtHIsAddition` → `getTxtHIsAddition`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHVType.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHVoucherType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHVType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHVType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHVType).show();
```

`txtHVType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHVType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHVType).show();
```

`txtHBudgetHead.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getBudgetHead(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<BudgetH
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHBudgetHead).show();
public void onSuccess(DmResultPageing<List<BudgetHead_Mdl>> result) {
if (result.isStatus()) {
txtHBudgetHead.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBudgetHead).show();
```

`txtHBudgetHead.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHBudgetHead.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBudgetHead).show();
```

`txtHBudgetType.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getBudgetType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<BudgetT
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHBudgetType).show();
public void onSuccess(DmResultPageing<List<BudgetType_Mdl>> result) {
if (result.isStatus()) {
txtHBudgetType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBudgetType).show();
```

`txtHBudgetType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHBudgetType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBudgetType).show();
```

`txtHCostCenter.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHCostCenter(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Key
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHCostCenter).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHCostCenter.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCostCenter).show();
```

`txtHCostCenter.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHCostCenter.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCostCenter).show();
```

`txtHIsAmt.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHIsAmt(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyField
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHIsAmt).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHIsAmt.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHIsAmt).show();
```

`txtHIsAmt.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHIsAmt.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHIsAmt).show();
```

`txtHIsAddition.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHIsAddition(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Key
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHIsAddition).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHIsAddition.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHIsAddition).show();
```

`txtHIsAddition.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHIsAddition.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHIsAddition).show();
```
- Alert: "Voucher Type is mandatory."
- Alert: "Budget Head is mandatory."
- Alert: "Budget Type is mandatory."
- Alert: "From Date is mandatory."
- Alert: "To Date is mandatory."
- Alert: "Is Amount is mandatory."
- Alert: "Is Addition is mandatory."
- Alert: "Figure in Lackhs is mandatory."
- Alert: "Manual No. is mandatory."

### Screen `BudgetPlanning`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `dmGrd` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `dmGrd`: Planning No. [Text, w120]; Date [Text, w70]; Budget Header Name [Text, w150]; Budget Type [Text, w150]; Cost Center [Text, w150]; From Date [Text, w75]; To Date [Text, w75]; Is Amount [Text, w100]; Is Addition [Text, w100]; Figure In Lackhs [Text, w100]; Manual No. [Text, w80]

**Event handlers (condensed code, CRUD flow and field callbacks):**

`budgetPlanningGridEdit.addCallBack` → `onOk()`
```java
dmGrd.commitChanges();
```

`budgetPlanningGridEdit.addCallBack` → `onDelete()`
```java
dmGrd.commitChanges();
```
- Confirm: "Are you sure? You want to close."

## Files
| file | lines |
|---|---|
| client/BudgetPlanning.java | 175 |
| client/BudgetPlanningGridEdit.java | 418 |
| client/BudgetPlanningGridEdit_Dsg.java | 313 |
| client/BudgetPlanning_Dsg.java | 159 |
| server/BudgetPlanning_SrvImpl.java | 612 |
| shared/BudgetHead_Mdl.java | 33 |
| shared/BudgetPlanning_Srv.java | 27 |
| shared/BudgetPlanning_SrvAsync.java | 29 |
| shared/BudgetType_Mdl.java | 33 |
| shared/GrdBudgetPlanning_Mdl.java | 203 |

## UI labels / columns (client)
`Voucher Type`, `Planning No.`, `Date`, `Budget Header`, `Budget Type`, `Cost Center`, `From Date`, `To Date`, `Is Amount`, `Is Addition`, `Figure In Lackhs`, `Manual No.`

## Models (shared DTOs)
### GrdBudgetPlanning_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | v_TypeCode |
| String | v_TypeName |
| String | v_Prefix |
| RecId | recId |
| String | v_Date |
| String | budgetHeadCode |
| String | budgetHeadName |
| String | budgetTypeCode |
| String | budgetTypeName |
| String | costCenterCode |
| String | costCenterName |
| String | siteCode |
| String | siteName |
| String | fromDate |
| String | toDate |
| String | isAmountTag |
| String | isAmountText |
| String | isAdditionTag |
| String | isAdditionText |
| String | figureInLackhs |
| String | refManualNo |

### BudgetHead_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | shortName |
| String | manualNo |

### BudgetType_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | shortName |
| String | manualNo |

## RPC interface
| method | params | returns |
|---|---|---|
| getGridDetail |  | DmResult<List<GrdBudgetPlanning_Mdl>> |
| getTxtHCostCenter | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getBudgetHead | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<BudgetHead_Mdl>> |
| getBudgetType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<BudgetType_Mdl>> |
| getTxtHIsAmt | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHIsAddition | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| saveRecord | GrdBudgetPlanning_Mdl mrTemp, String voucherPrefix | DmResult<String> |
| getTxtHVoucherType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| deleteRecord | String strKey | DmResult<String> |

## Server `BudgetPlanning_SrvImpl.java` (612 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getRecId`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getGridDetail`
`public DmResult<List<GrdBudgetPlanning_Mdl>> getGridDetail()`

- SQL 1: `select bp.docid, bp.v_Type,vt.Description as vTypeName, bp.v_No, bp.v_Prefix, bp.recIdPrefix, bp.recId, bp.v_Date, bp.budgetHeadCode, bphm.name as budgetHeadName, bp.budgetTypeCode,btm.name as budgetTypeName, bp.costCenterCode, ccm.name as costCenterName, bp.site_Code,bp.fromDate,bp.toDate, bp.isAmount, bp.isAddition, bp.figureInLackhs, bp.refManualNo,bp.u_EntDt,bp.u_Name,bp.preparedBy from BudgetPlanning as bp left join budgetPlanningHeadMast bphm on bphm.code=bp.budgetHeadCode left join budgetTypeMast btm on btm.code=bp.budgetTypeCode left join costCenterMast ccm on ccm.code=bp.costCenterCode left join voucher_Type vt on vt.v_Type=bp.v_Type where bp.site_Code= :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHCostCenter`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCostCenter(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT CODE ,NAME FROM CostCenterMast`

### `getBudgetHead`
`public DmResultPageing<List<BudgetHead_Mdl>> getBudgetHead(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select bphm.code, bphm.name, bphm.shortName, bphm.refManualNo from budgetPlanningHeadMast as bphm`

### `getBudgetType`
`public DmResultPageing<List<BudgetType_Mdl>> getBudgetType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select btm.code, btm.name, btm.shortName, btm.refManualNo from budgetTypeMast as btm`

### `getTxtHIsAmt`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHIsAmt(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHIsAddition`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHIsAddition(DmHelper helper, boolean blnToEndOfFile)`


### `saveRecord`
`public DmResult<String> saveRecord(GrdBudgetPlanning_Mdl mrTemp, String voucherPrefix)`

- SQL 1: `SELECT COUNT(bp.refManualNo) AS cnt FROM budgetPlanning as bp WHERE LOWER(bp.refManualNo) = :refManualNo`
- SQL 2: `Insert Into budgetPlanning(docId,v_Type,v_No,v_Prefix,recIdPrefix,recId,v_Date,budgetHeadCode, budgetTypeCode,costCenterCode,site_Code,fromDate,toDate,isAmount,isAddition,figureInLackhs, refManualNo,u_Name,u_EntDt,preparedBy) Values (:docId,:v_Type,:v_No,:v_Prefix,:recIdPrefix,:recId,:v_Date,:budgetHeadCode, :budgetTypeCode,:costCenterCode,:site_Code,:fromDate,:toDate,:isAmount,:isAddition,:figureInLackhs, :refManualNo,:u_Name,:u_EntDt,:preparedBy)`
- SQL 3: `Insert Into budgetLedger(docId,v_SNo,refDocId,refV_Sno,additionValue,deductionValue,refRecId,refV_Date) Values (:docId,:v_SNo,:refDocId,:refV_Sno,:additionValue,:deductionValue,:refRecId,:refV_Date)`
- SQL 4: `Update budgetPlanning Set budgetHeadCode = :budgetHeadCode, budgetTypeCode = :budgetTypeCode, costCenterCode = :costCenterCode, fromDate = :fromDate, toDate = :toDate, isAmount = :isAmount, isAddition = :isAddition, figureInLackhs = :figureInLackhs, refManualNo = :refManualNo, U_Name = :u_Name, u_EntDt = :u_EntDt, transferDt = NULL Where docId = :docId`
- SQL 5: `Update budgetLedger Set additionValue= :additionValue, deductionValue= :deductionValue Where docId = :docId and v_SNo = :v_SNo`
- Params: `:refManualNo` ← `DmShared.iNull(mrTemp.getRefManualNo()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_Type` ← `DmShared.xNull(mrTemp.getV_TypeCode())`; `:v_No` ← `DmShared.iNull(mrTemp.getRecId().getCounter()).toString()`; `:v_Prefix` ← `DmShared.xNull(voucherPrefix)`; `:recIdPrefix` ← `DmShared.xNull(mrTemp.getRecId().getPrefix())`; `:recId` ← `DmShared.xNull(mrTemp.getRecId().getRecIdValue())`; `:v_Date` ← `DmServer.convertDate(mrTemp.getV_Date())`; `:budgetHeadCode` ← `DmShared.foriegnKey(mrTemp.getBudgetHeadCode())`; `:budgetTypeCode` ← `DmShared.foriegnKey(mrTemp.getBudgetTypeCode())`; `:costCenterCode` ← `DmShared.foriegnKey(mrTemp.getCostCenterCode())`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:fromDate` ← `DmServer.convertDate(mrTemp.getFromDate())`; `:toDate` ← `DmServer.convertDate(mrTemp.getToDate())`; `:isAmount` ← `DmShared.xNull(mrTemp.getIsAmountTag())`; `:isAddition` ← `DmShared.xNull(mrTemp.getIsAdditionTag())`; `:figureInLackhs` ← `DmServer.vNull(mrTemp.getFigureInLackhs(), "0.00")`; `:refManualNo` ← `DmShared.xNull(mrTemp.getRefManualNo())`; `:u_Name` ← `userName`; `:u_EntDt` ← `DmServer.getServerTimestamp()`; `:preparedBy` ← `userName`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_SNo` ← `"1"`; `:refDocId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:refV_Sno` ← `DmShared.iNull(mrTemp.getRecId().getCounter()).toString()`; `:additionValue` ← `DmServer.vNull(mrTemp.getFigureInLackhs(), "0.00")`; `:deductionValue` ← `"0"`; `:additionValue` ← `"0"`; `:deductionValue` ← `DmServer.vNull(mrTemp.getFigureInLackhs(), "0.00")`; `:refRecId` ← `DmShared.xNull(mrTemp.getRecId().getRecIdValue())`; `:refV_Date` ← `DmServer.convertDate(mrTemp.getV_Date())`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:budgetHeadCode` ← `DmShared.foriegnKey(mrTemp.getBudgetHeadCode())`; `:budgetTypeCode` ← `DmShared.foriegnKey(mrTemp.getBudgetTypeCode())`; `:costCenterCode` ← `DmShared.foriegnKey(mrTemp.getCostCenterCode())`; `:fromDate` ← `DmServer.convertDate(mrTemp.getFromDate())`; `:toDate` ← `DmServer.convertDate(mrTemp.getToDate())`; `:isAmount` ← `DmShared.xNull(mrTemp.getIsAmountTag())`; `:isAddition` ← `DmShared.xNull(mrTemp.getIsAdditionTag())`; `:figureInLackhs` ← `DmServer.vNull(mrTemp.getFigureInLackhs(), "0.00")`; `:refManualNo` ← `DmShared.xNull(mrTemp.getRefManualNo())`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `DmServer.getServerTimestamp()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_SNo` ← `"1"`; `:additionValue` ← `DmServer.vNull(mrTemp.getFigureInLackhs(), "0.00")`; `:deductionValue` ← `"0"`; `:additionValue` ← `"0"`; `:deductionValue` ← `DmServer.vNull(mrTemp.getFigureInLackhs(), "0.00")`
- Errors: "Invalid key." | "Manual No. already exists."

### `getTxtHVoucherType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVoucherType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.V_type, vt.Description FROM Voucher_Type vt WHERE vt.isActive = 1 and vt.Category = {…}`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey)`

- SQL 1: `delete from budgetPlanning Where docId= :docId`
- SQL 2: `delete from budgetLedger Where docId= :docId`
- Params: `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `paperbiz.budget.budgetPlanning.shared`, `paperbiz.projectMenu.self.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (DmShared.iNull(mrTemp.getSharedKeyFieldCode()).longValue() == 0)` | "Invalid key." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Manual No. already exists." |

## Notes (hand-written)
_No hand-written notes yet._
