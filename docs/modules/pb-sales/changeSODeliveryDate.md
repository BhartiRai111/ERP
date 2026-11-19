# pb-sales / changeSODeliveryDate

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/sales/src/changeSODeliveryDate/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: sODeliveryDateChangeLog
- Update tables: sOrderDetail, sOrder
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: rule: New Delivery Date is mandatory. ; rule: New Delivery Date should not be less than Order Date. ; rule: Delivery Date not equal to To New Delivery Date.
- Lookups/suggestions: getTxtHParty, getTxtHDeliveryCity, getHNewDays, getTxtHItemNature, getTxtHItemName
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `ChangeSODeliveryDateGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Order Date | DmTextBox | `txtOrderNo` | `` |
| 2 | - | DmTextBox | `txtDate` | `` |
| 3 | Party | DmTextBox | `txtParty` | `` |
| 4 | Distributor | DmTextBox | `txtDistributor` | `` |
| 5 | Party Ord. Date | DmTextBox | `txtPartyOrdNo` | `` |
| 6 | - | DmTextBox | `txtPartyOrdDate` | `` |
| 7 | Item Nature | DmTextBox | `txtItemNature` | `` |
| 8 | Item Name | DmTextBox | `txtItemName` | `` |
| 9 | Reason | DmRemarkTextBox | `txtReason` | `250, true` |
| 10 | New Delivery Date | DmTextBox | `txtDeliveryDate` | `` |
| 11 | - | DmTextBox | `txtNewDeliverydate` | `11, true` |

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtOrderNo.setEnabled(false)`
- `txtDate.setEnabled(false)`
- `txtItemNature.setEnabled(false)`
- `txtParty.setEnabled(false)`
- `txtDistributor.setEnabled(false)`
- `txtPartyOrdNo.setEnabled(false)`
- `txtPartyOrdDate.setEnabled(false)`
- `txtDeliveryDate.setEnabled(false)`
- `txtItemName.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
txtOrderNo.setEnabled(false);
txtDate.setEnabled(false);
txtItemNature.setEnabled(false);
txtParty.setEnabled(false);
txtDistributor.setEnabled(false);
txtPartyOrdNo.setEnabled(false);
txtPartyOrdDate.setEnabled(false);
txtDeliveryDate.setEnabled(false);
txtItemName.setEnabled(false);
```
- Alert: "Reason is mandatory."
- Alert: "New Delivery Date is mandatory."
- Alert: "New Delivery Date should not be less than Order Date."
- Alert: "New Delivery Date can not equal to Delivery Date."

### Screen `ChangeSODeliveryDate`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmGridPaging | `dmGrd` | `Grd_PrpAcs, 22, true` |
| 2 | - | DmTextBox | `txtSearchValue` | `50, false` |

**Grid / popup columns** (header, width, db field):
- `dmGrd`:  [Text, w30];  [Text, w25]; Order No. [Text, w100, recId]; Order Date [Text, w70, v_date]; Item Nature [Text, w80]; Item Name [Text, w160]; Party [Text, w250, partyName]; Distributor  [Text, w250, distributorName]; Party Order No [Text, w120, partyOrderNo]; Party Order Dt [Text, w80, partyOrdDt]; Delivery Date [Text, w77, deliveryDt]

**Lookup / suggestion / find sources (server RPC called):**
- `dmGrd` → `getFillRecords`
- `gridEdit` → `getDefaultValues`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`dmGrd.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(true).getFillRecords(param, helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<ChangeSODe
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<ChangeSODeliveryDate_Mdl>> result) {
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
return setting_Mdl;
```

`settingPopup.addlCallBack` → `onOk()`
```java
setting_Mdl = approvalEntry_Mdl;
dmGrd.reQuery();
```

`gridEdit.addCallBack` → `onOk()`
```java
dmGrd.getDmGrid().commitChanges();
dmGrd.reQuery();
gridEdit.hide();
```
- Confirm: "Are you sure? You want to close."
- Alert: "Reason is mandatory."

### Screen `Setting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | From Delivery Date | DmTextBox | `txtFromDelDate` | `15, false` |
| 2 | To Delivery Date | DmTextBox | `txtToDelDate` | `15, false` |
| 3 | Party | DmTextHelpMulti | `txtHParty` | `TxtHSupplierName_PrpAcs, 7` |
| 4 | Distributor | DmTextHelpMulti | `txtHDistributor` | `TxtHDistributor_PrpAcs, 7` |
| 5 | Consignee | DmTextHelpMulti | `txtHConsignee` | `TxtHConsignee_PrpAcs, 7` |
| 6 | To Place | DmTextHelpMulti | `txtHDeliveryCity` | `com.google.gwt.core.shared.TxtHCity_Mdl_PrpAcs, 5, false` |
| 7 | Item Nature | DmTextHelpMulti | `txtHItemNature` | `KeyField_PrpAcs, 4` |
| 8 | Item Name | DmTextHelpMulti | `txtHItemName` | `com.google.gwt.core.client.TxtHItemName_PrpAcs, 4, false` |
| 9 | Delivey To | DmTextHelpMulti | `txtHDeliveryTo` | `TxtHDeliveryTo_PrpAcs, 7, false` |
| 10 | Type | DmTextHelp | `txtHType` | `KeyField_PrpAcs, 3, false` |
| 11 | - | DmTextBox | `txtExtendDeliveryDays` | `3, false` |
| 12 | Reason | DmRemarkTextBox | `txtRemark` | `250, false` |

**Grid / popup columns** (header, width, db field):
- `txtHParty`: Name [Text, w220, supplierName]; Manual Code [Text, w100, manualCode]; City Name [Text, w100, cityName]
- `txtHDistributor`: Name [Text, w220, supplierName]; Manual Code [Text, w100, manualCode]; City Name [Text, w100, cityName]
- `txtHConsignee`: Name [Text, w220, supplierName]; Manual Code [Text, w100, manualCode]; City Name [Text, w100, cityName]
- `txtHDeliveryCity`: City [Text, w150, CityName]; State [Text, w100, stateName]
- `txtHDeliveryTo`: Name [Text, w250, supplierName]; Manual Code [Text, w100, manualCode]
- `txtHType`: Name [Text, w0]
- `txtHItemNature`: Name [Text, w0, Name]
- `txtHItemName`: Item Name [Text, w200, name]; Manual Code [Text, w90, manualCode]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHType` → `getHNewDays`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtFromDelDate.addValueChangeHandler` → `onValueChange()`
```java
txtFromDelDate.setText(DmClient.returnDate(txtFromDelDate.getText()));
```

`txtToDelDate.addValueChangeHandler` → `onValueChange()`
```java
txtToDelDate.setText(DmClient.returnDate(txtToDelDate.getText()));
```

`txtHParty.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHParty(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHSupp
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHParty).show();
public void onSuccess(DmResultPageing<List<TxtHSupplier_Mdl>> result) {
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

`txtHDistributor.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHParty(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHSupp
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDistributor).show();
public void onSuccess(DmResultPageing<List<TxtHSupplier_Mdl>> result) {
if (result.isStatus()) {
txtHDistributor.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDistributor).show();
```

`txtHDistributor.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHDistributor.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDistributor).show();
```

`txtHConsignee.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHParty(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHSupp
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHConsignee).show();
public void onSuccess(DmResultPageing<List<TxtHSupplier_Mdl>> result) {
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

`txtHDeliveryTo.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHParty(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHSupp
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDeliveryTo).show();
public void onSuccess(DmResultPageing<List<TxtHSupplier_Mdl>> result) {
if (result.isStatus()) {
txtHDeliveryTo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDeliveryTo).show();
```

`txtHDeliveryTo.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHDeliveryTo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDeliveryTo).show();
```

`txtHDeliveryCity.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHDeliveryCity(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<K
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDeliveryCity).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHDeliveryCity.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDeliveryCity).show();
```

`txtHDeliveryCity.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHDeliveryCity.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDeliveryCity).show();
```

`txtHDeliveryCity.addTextHelpMultiCallBack` → `getSelection()`
```java
if (blnStatus) {
```

`txtHType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getHNewDays(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHType).show();
```

`txtHType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHType).show();
```

`txtHItemNature.addTextHelpMultiCallBack` → `populateData()`
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

`txtHItemNature.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemNature).show();
```

`txtHItemNature.addTextHelpMultiCallBack` → `getSelection()`
```java
if (blnStatus) {
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
- Alert: "From Delivery Date should be less than or equal to To Delivery Date."

## Files
| file | lines |
|---|---|
| client/ChangeSODeliveryDate.java | 448 |
| client/ChangeSODeliveryDateGridEdit.java | 231 |
| client/ChangeSODeliveryDateGridEdit_Dsg.java | 326 |
| client/ChangeSODeliveryDate_Dsg.java | 231 |
| client/Setting.java | 430 |
| client/Setting_Dsg.java | 430 |
| server/ChangeSODeliveryDate_SrvImpl.java | 615 |
| shared/ChangeSODeliveryDate_Mdl.java | 239 |
| shared/ChangeSODeliveryDate_Srv.java | 28 |
| shared/ChangeSODeliveryDate_SrvAsync.java | 29 |
| shared/ChangeSODeliveryDate_Vld.java | 25 |
| shared/Default_Mdl.java | 40 |
| shared/Setting_Mdl.java | 257 |
| shared/TxtHSalesOrder_Mdl.java | 32 |
| shared/TxtHSupplier_Mdl.java | 31 |

## UI labels / columns (client)
`Order No.`, `Order Date`, `Party`, `Distributor`, `Party Ord. No.`, `Party Ord. Date`, `Item Nature`, `Item Name`, `Reason`, `Delivery Date`, `New Delivery Date`, `From Delivery Date`, `To Delivery Date`, `Consignee`, `To Place`, `Delivey To`, `Type`

## Models (shared DTOs)
### ChangeSODeliveryDate_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | isChecked |
| String | IsView |
| String | orderNo |
| String | orderDate |
| String | partyCode |
| String | partyName |
| String | supplierStateCode |
| String | distributorCode |
| String | distributorName |
| String | partyOrderNo |
| String | partyOrderDate |
| String | deliveryToCode |
| String | deliveryToName |
| String | deliveryDate |
| String | newdeliveryDate |
| String | remark |
| String | vNo |
| String | toPlace |
| String | itemNature |
| String | typeTag |
| String | typeText |
| String | itemName |
| String | itemCode |
| String | manualCode |
| String | vSNo |

### Default_Mdl
| type | field |
|---|---|
| String | extendDt |
| String | date |
| String | SL_ExtendDeliveryDays |

### Setting_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | FromDate |
| String | ToDate |
| String | StatusCode |
| String | StatusName |
| String | partyName |
| String | partyCode |
| String | partyOrderNo |
| String | distributorCode |
| String | distributorName |
| String | consigneeCode |
| String | consigneeName |
| String | deliveryCityCode |
| String | deliveryCityName |
| String | saleOrderCode |
| String | saleOrderName |
| String | itemCode |
| String | itemName |
| String | deliveryToCode |
| String | deliveryToName |
| String | deliveryDate |
| String | remark |
| String | itemNatureCode |
| String | itemNatureName |
| String | typeTag |
| String | typeText |
| String | v_SNo |
| String | numberOfdays |

### TxtHSupplier_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityName |

### TxtHSalesOrder_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | soType |
| String | soDate |

## RPC interface
| method | params | returns |
|---|---|---|
| getFillRecords | Setting_Mdl param, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<ChangeSODeliveryDate_Mdl>> |
| getTxtHDeliveryCity | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| saveRecord | ChangeSODeliveryDate_Mdl grdExtendedSaleOrder, String extendedDate | DmResult<String> |
| getTxtHParty | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHSupplier_Mdl>> |
| getDefaultValues |  | DmResult<Default_Mdl> |
| getHNewDays | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemNature | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `ChangeSODeliveryDate_Vld`
```java
public class ChangeSODeliveryDate_Vld {
    public static void chkSave(ChangeSODeliveryDate_Mdl grdExtendedSaleOrder) {
        if (DmShared.xNull(grdExtendedSaleOrder.getNewdeliveryDate()).equalsIgnoreCase("")) {
            throw new DmException(100, "New Delivery Date is mandatory. ");
        }
        if (!DmShared.xNull(grdExtendedSaleOrder.getNewdeliveryDate()).equalsIgnoreCase("") && !DmShared.xNull(grdExtendedSaleOrder.getOrderDate()).equalsIgnoreCase("")) {
            if ((DmShared.convertDate(grdExtendedSaleOrder.getNewdeliveryDate()).getTime() < (DmShared.convertDate(grdExtendedSaleOrder.getOrderDate())).getTime())) {
                throw new DmException(200, "New Delivery Date should not be less than Order Date. ");
            }
        } else if (!DmShared.xNull(grdExtendedSaleOrder.getDeliveryDate()).equalsIgnoreCase("") && !DmShared.xNull(grdExtendedSaleOrder.getNewdeliveryDate()).equalsIgnoreCase("")) {
            if ((DmShared.convertDate(grdExtendedSaleOrder.getDeliveryDate()).getTime() == (DmShared.convertDate(grdExtendedSaleOrder.getNewdeliveryDate())).getTime())) {
                throw new DmException(200, "Delivery Date not equal to To New Delivery Date.");
            }
        }
    }
}
```

## Server `ChangeSODeliveryDate_SrvImpl.java` (615 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.xNull`

### `getDefaultValues`
`public DmResult<Default_Mdl> getDefaultValues()`

- SQL 1: `select e.SL_ExtendDeliveryDays FROM enviro e WHERE e.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getFillRecords`
`public DmResultPageing<List<ChangeSODeliveryDate_Mdl>> getFillRecords(Setting_Mdl param, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT so.docId,so.recId,so.v_Date,ib.itemNature,ina.name AS itemNatureName, so.partyCode AS supplierCode, im.name AS itemName, sod.v_SNo, im.code AS itemCode, sg.name AS partyName, so.partyOrdNo as partyOrderNo,partyOrdDt, so.distributor AS distributorCode , sgd.name AS distributorName, c1.cityName as partyCityName, c2.cityName as distributorCityName ,sodc.reason , vt.short_Name , so.v_No, so.deliveryTo AS deliveryToCode,sgdt.name AS deliveryToName, so.deliveryCity,c.cityName,sod.deliveryDt, so.u_Name,so.preparedBy,so.approvedBy, sod.isStatus FROM sOrder AS so {…} LEFT JOIN sOrderDetail AS sod ON sod.docId = so.docId LEFT JOIN itemBatch ib ON ib.code = sod.ibCode LEFT JOIN itemNature ina ON in.code = ib.itemNature left join itemMast as im on sod.itemCode = im.code LEFT JOIN voucher_Type AS vt ON vt.v_Type = so.v_Type LEFT JOIN subGroup AS sg ON sg.subCode = so.partyCode LEFT JOIN city c1 ON c1.cityCode = sg.cityCode LEFT JOIN subGroup AS sgd ON sgd.subCode = so.distributor LEFT JOIN city c2 ON c2.cityCode = sgd.cityCode LEFT JOIN subGroup AS sgdt ON sgdt.subCode = so.deliveryTo LEFT JOIN sODeliveryDateChangeLog AS sodc ON sodc.docId = sod.docId and sodc.v_sno = sod.v_sno LEFT JOIN city AS c ON c.cityCode = so.deliveryCity {…} where vt.category IN ( {…} ) {…} AND isNull(so.approvedBy,'') <> ''`
- Params: `:fromv_Date` ← `DmServer.convertDate(param.getFromDate())`; `:tov_Date` ← `DmServer.convertDate(param.getToDate())`

### `getTxtHParty`
`public DmResultPageing<List<TxtHSupplier_Mdl>> getTxtHParty(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as supplierName , sg.manualCode , c.cityName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( SELECT DISTINCT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_Type = acf.v_Type WHERE vt.category IN (' {…} ',' {…} ') ) AND sg.isActive = 1 AND sgsl.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `saveRecord`
`public DmResult<String> saveRecord(ChangeSODeliveryDate_Mdl grdExtendedSaleOrder, String extendedDate)`

- SQL 1: `Update sOrderDetail set deliveryDt=:deliveryDt where docId=:docId AND v_Sno=:v_Sno`
- SQL 2: `Update sOrder set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 3: `SELECT (ISNULL(MAX(sNo),0) + 1) as sno from sODeliveryDateChangeLog WHERE docId = :docId AND v_SNo = :v_SNo`
- SQL 4: `INSERT INTO sODeliveryDateChangeLog ( docId ,v_SNo,sNo,deliveryDate ,newDeliveryDate,reason,logBy,logDate) Values ( :docId, :v_SNo, :sNo, :deliveryDate, :newDeliveryDate,:reason ,:logBy ,:logDate)`
- Params: `:docId` ← `DmShared.xNull(grdExtendedSaleOrder.getSharedKeyFieldCode())`; `:v_Sno` ← `DmShared.xNull(grdExtendedSaleOrder.getvSNo())`; `:deliveryDt` ← `DmServer.convertDate(extendedDate)`; `:docId` ← `DmShared.xNull(grdExtendedSaleOrder.getSharedKeyFieldCode())`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:docId` ← `DmShared.xNull(grdExtendedSaleOrder.getSharedKeyFieldCode())`; `:v_SNo` ← `DmShared.xNull(grdExtendedSaleOrder.getvSNo())`; `:docId` ← `DmShared.xNull(grdExtendedSaleOrder.getSharedKeyFieldCode())`; `:v_SNo` ← `DmShared.xNull(grdExtendedSaleOrder.getvSNo())`; `:sNo` ← `sNo`; `:deliveryDate` ← `DmServer.convertDate(grdExtendedSaleOrder.getDeliveryDate())`; `:newDeliveryDate` ← `DmServer.convertDate(extendedDate)`; `:reason` ← `DmShared.xNull(grdExtendedSaleOrder.getRemark())`; `:logBy` ← `DmShared.xNull(DmServer.getSsnUserName(this)).trim()`; `:logDate` ← `DmServer.getServerTimestamp()`
- Other calls: `ChangeSODeliveryDate_Vld.chkSave`

### `getTxtHDeliveryCity`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDeliveryCity(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select ct.cityCode, ct.cityName, ct.stateCode, sm.name as stateName from city ct left join stateMast sm on ct.stateCode = sm.code`

### `getHNewDays`
`public DmResultPageing<List<KeyFieldExtended>> getHNewDays(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHItemNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemNature(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT itn.code, itn.name, itn.manualCode FROM itemNature AS itn WHERE itn.isActive = 1`

### `getTxtHItemName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , im.manualCode , im.itsBasedOn , im.itemDesc , im.saleUnit as itemUnitCode , um.unit as itemUnitName , um.decimalPlaces as itemUnitDecimalPlaces , ISNULL(im.saleUnitConversionRate, 0) AS itemSaleUnitConversionRate , im.tl_ExcessPer , im.tl_ShortagePer FROM itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN unitMast um on um.code = im.saleUnit WHERE im.IsActive = 1 AND (im.filterItemType = :filterItemType OR im.isCommon = 1) AND sl.site_Code = :site_Code`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `paperbiz.projectMenu.self.shared`, `paperbiz.sales.changeSODeliveryDate.shared`, `paperbiz.sales.reports.shared`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
