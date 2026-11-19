# pb-gate / gateAdvicePopup

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/gate/src/gateAdvicePopup/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: gateAdviceDetail
- Update tables: gateAdviceDetail
- Delete cascade (in order): gateAdviceDetail
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Item Name (Text); Approx Weight (Text); Unit (Text); rule: Please fill all item properties.; rule: Please re-select the item to refresh its properties.; rule: Pack Count is mandatory.; rule: Pack Size is mandatory.; rule: Weight should not be zero.
- Lookups/suggestions: getTxtHOrderNo, getTxtHItem, getTxtHPackSize
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `AdviceGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmTextHelp | `txtHOrderNo` | `TxtHOrderNo_PrpAcs, 7, false` |
| 2 | Party | DmTextBox | `txtPartyName` | `` |
| 3 | Item Name | DmTextHelp | `txtHItemName` | `TxtHItemName_PrpAcs, 7, true` |
| 4 | - | DmTextBox | `txtProperties` | `` |
| 5 | Pack Unit | DmTextHelp | `txtHPackSize` | `com.google.gwt.core.client.TxtHPackSize_PrpAcs, 6, false` |
| 6 | - | DmTextBoxNumeric | `txtPackCount` | `4, true, 12, false` |
| 7 | Unit | DmTextBoxNumeric | `txtApproxWt` | `4, false, 9, true` |
| 8 | - | DmTextBox | `txtUnit` | `10, true` |

Other widgets (not in tab list): `txtPackUnit` (DmTextBox), `cbIsAllowOtherSiteOrder` (DmCheckBox)

**Grid / popup columns** (header, width, db field):
- `txtHOrderNo`: Order No. [Text, w120, poRecId]; Ref. No. [Text, w120, poRefNo]; Party [Text, w150]; Item [Text, w150, itemName]; Properties [Text, w150, properties]; Pack Size [Text, w150, packSizeName]; Pack Count [Text, w80, packCount]; Order Qty. [Text, w80, orderQty]; Unit [Text, w60, unitName]
- `txtHItemName`: Item Name [Text, w200, name]; Manual Code [Text, w100, manualCode]
- `txtHPackSize`: Name [Text, w1, packSizeName]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHOrderNo` → `getTxtHOrderNo`
- `txtHItemName` → `getTxtHItem`
- `txtHPackSize` → `getTxtHPackSize`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `DocId` ← DmShared.xNull(txtApproxWt.getTag())
- `OrderDocId` ← DmShared.xNull(txtHOrderNo.getTag())
- `OrderVsno` ← DmShared.xNull(txtHOrderNo.getTag2nd())
- `SharedKeyFieldCode` ← DmShared.xNull(txtHItemName.getTag())
- `ItemProperty` ← DmShared.xNull(txtHItemName.getTag2nd())
- `SharedKeyFieldName` ← DmShared.xNull(txtHItemName.getText())
- `PropertiesCsv` ← DmShared.xNull(txtProperties.getText())
- `ApproxWt` ← DmShared.xNull(txtApproxWt.getText())
- `vSno` ← DmShared.xNull(txtApproxWt.getTag2nd())
- `UnitCode` ← DmShared.xNull(txtUnit.getTag())
- `UnitName` ← DmShared.xNull(txtUnit.getText())
- `PackSizeCode` ← DmShared.xNull(txtHPackSize.getTag())
- `PackSizeName` ← DmShared.xNull(txtHPackSize.getText())
- `PackSizeSkuCode` ← DmShared.xNull(txtHPackSize.getTag2nd())
- `PackSizeSkuName` ← DmShared.xNull(txtPackCount.getTag())
- `PackUnitCode` ← DmShared.xNull(txtPackUnit.getTag())
- `PackUnitName` ← DmShared.xNull(txtPackUnit.getText())
- `PackCount` ← DmShared.xNull(txtPackCount.getText())
- `PackUnitConversionRate` ← DmShared.xNull(txtPackUnit.getTag2nd())
- `PackUnitDecimalPlaces` ← DmShared.xNull(txtPackCount.getTag2nd())
- `OrderNo` ← DmShared.xNull(txtHOrderNo.getText())
- `PartyName` ← DmShared.xNull(txtPartyName.getText())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtPartyName.setEnabled(false)`
- `txtHItemName.setEnabled(false)`
- `txtHItemName.setEnabled(true)`

**`manageDisplay()` logic (condensed code):**
```java
txtPartyName.setEnabled(false);
if ((DmShared.iNull(txtHOrderNo.getTag()).longValue() > 0)) {
txtHItemName.setEnabled(false);
} else {
txtHItemName.setEnabled(true);
manageFocus();
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`propertyPopup.addCallBack` → `onOk()`
```java
propertyPopup_Mdl = property_Mdl;
txtProperties.setTag(DmShared.xNull(propertyPopup_Mdl.getIbCode()));
txtProperties.setText(propertyPopup_Mdl.getPropertiesCsv());
txtApproxWt.setFocus(true);
```

`propertyPopup.addCallBack` → `onClose()`
```java
txtProperties.setFocus(true);
```

`txtHOrderNo.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHOrderNo(gateAdvicePopup.defaultValue.getPartyCode(),
gateAdvicePopup.defaultValue.getVoucherDate(), cbIsAllowOtherSiteOrder.getValue(),
helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHOrderNo_Mdl>>>() {
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHOrderNo).show();
public void onSuccess(DmResultPageing<List<TxtHOrderNo_Mdl>> result) {
if (result.isStatus()) {
txtHOrderNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHOrderNo).show();
...
```

`txtHOrderNo.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHOrderNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHOrderNo).show();
```

`txtHOrderNo.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(selectedOrderNoCode).equalsIgnoreCase(DmShared.xNull(txtHOrderNo.getTag()))) {
resetOrder();
selectedOrderNoCode = DmShared.xNull(txtHOrderNo.getTag());
if (blnStatus && t1Row != null) {
txtHOrderNo.setTag2nd(DmShared.xNull(t1Row.getPoV_SNo()));
txtPartyName.setText(DmShared.xNull(t1Row.getPartyName()));
txtHItemName.setText(DmShared.xNull(t1Row.getItemName()));
txtHItemName.setTag(DmShared.xNull(t1Row.getItemCode()));
selectedItemCode = DmShared.xNull(t1Row.getItemCode());
txtHItemName.setTag2nd(DmShared.xNull(t1Row.getItemProperty()));
...
```

`txtHItemName.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItem(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHItemN
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemName).show();
public void onSuccess(DmResultPageing<List<TxtHItemName_Mdl>> result) {
if (result.isStatus()) {
txtHItemName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemName).show();
```

`txtHItemName.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemName).show();
```

`txtHItemName.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(selectedItemCode).equalsIgnoreCase(DmShared.xNull(txtHItemName.getTag()))) {
resetItem();
selectedItemCode = DmShared.xNull(txtHItemName.getTag());
if (blnStatus && t1Row != null) {
txtHItemName.setTag2nd(t1Row.getItemProperty());
itemType = DmShared.xNull(t1Row.getItemType());
isBestBefore = DmShared.xNull(t1Row.getIsBestBefore());
txtHPackSize.setTag(DmShared.xNull(t1Row.getPackSizeCode()));
selectedPackSizeCode = DmShared.xNull(t1Row.getPackSizeCode());
txtHPackSize.setText(DmShared.xNull(t1Row.getPackSizeName()));
...
```

`txtHPackSize.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHPackSize(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHP
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHPackSize).show();
public void onSuccess(DmResultPageing<List<TxtHPackSize_Mdl>> result) {
if (result.isStatus()) {
txtHPackSize.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPackSize).show();
```

`txtHPackSize.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHPackSize.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPackSize).show();
```

`txtHPackSize.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!selectedPackSizeCode.equalsIgnoreCase(DmShared.xNull(txtHPackSize.getTag()))) {
resetPackSize();
selectedPackSizeCode = DmShared.xNull(txtHPackSize.getTag());
if (blnStatus && t1Row != null) {
txtPackUnit.setTag(DmShared.xNull(t1Row.getPackUnitCode()));
txtPackUnit.setTag2nd(DmShared.xNull(t1Row.getPackUnitConversionRate()));
txtHPackSize.setTag2nd(DmShared.xNull(t1Row.getSkuCode()));
if (!DmShared.xNull(t1Row.getSkuCode()).equalsIgnoreCase(t1Row.getPackUnitCode())) {
txtPackUnit.setText(DmShared.xNull(t1Row.getPackUnitName() + " = " + DmShared.xNull(t1Row.getPackUnitConversionRate()) +
} else {
...
```
- Alert: "Item is mandatory."
- Alert: "Pack count is mandatory"
- Alert: "Pack Size is mandatory"
- Alert: "Approx Weight is mandatory."
- Alert: "Properties not applicable for this item."

### Screen `GateAdvicePopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Gate No. | DmTextBox | `txtGateNo` | `20, true` |

Other widgets (not in tab list): `gridAdviceDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `gridAdviceDetail`: Order No. [Text, w150, orderNo]; Party [Text, w250]; Item Name [Text, w300, itemName]; Properties [Text, w270, propertiesCsv]; Pack Size [Text, w150, packSizeName]; pack count [Text, w120, packCount]; Approx. Weight [Text, w120, approxWt]; Unit [Text, w100, UnitName]

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtGateNo.setEnabled(b)`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**`manageDisplay()` logic (condensed code):**
```java
txtGateNo.setEnabled(b);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`adviceGridEdit.addCallBack` → `onOk()`
```java
gridAdviceDetail.commitChanges();
```

`adviceGridEdit.addCallBack` → `onDelete()`
```java
gridAdviceDetail.commitChanges();
```

## Files
| file | lines |
|---|---|
| client/AdviceGridEdit.java | 730 |
| client/AdviceGridEdit_Dsg.java | 341 |
| client/GateAdvicePopup.java | 219 |
| client/GateAdvicePopup_Dsg.java | 177 |
| server/GateAdvicePopup_SrvImpl.java | 815 |
| shared/AdviceGridEdit_Mdl.java | 239 |
| shared/Default_Mdl.java | 73 |
| shared/GateAdvicePopup_Mdl.java | 32 |
| shared/GateAdvicePopup_Srv.java | 28 |
| shared/GateAdvicePopup_SrvAsync.java | 29 |
| shared/GateAdvicePopup_Vld.java | 50 |
| shared/TxtHItemName_Mdl.java | 124 |
| shared/TxtHOrderNo_Mdl.java | 271 |
| shared/TxtHPackSize_Mdl.java | 72 |

## UI labels / columns (client)
`Gate No.`, `Order No.`, `Party`, `Item Name`, `Pack Size`, `Pack Count`, `Pack Unit`, `Approx Weight`, `Unit`

## Models (shared DTOs)
### GateAdvicePopup_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| boolean | isFinalWeighmnet |
| List<AdviceGridEdit_Mdl> | adviceDetails |

### TxtHPackSize_Mdl (extends `KeyField`)
| type | field |
|---|---|
| String | packUnitCode |
| String | packUnitName |
| String | packUnitAllowDecimalPlaces |
| String | skuName |
| String | skuCode |
| String | skuDecimalPlaces |
| String | packUnitConversionRate |

### TxtHItemName_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | itemProperty |
| String | itemType |
| String | isBestBefore |
| String | packSizeCode |
| String | packSizeName |
| String | packUnitCode |
| String | packUnitName |
| String | packUnitConversionRate |
| String | packSizeSkuCode |
| String | packSizeSkuName |
| String | packUnitDecimalPlaces |

### Default_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | voucherDate |
| String | unitCode |
| String | unitName |
| String | partyName |
| String | partyCode |
| String | unitDecimalPlaces |
| String | gateNo |

### TxtHOrderNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | poV_SNo |
| String | poRefNo |
| String | rate |
| String | amount |
| String | orderQty |
| String | pendingQty |
| String | skuQty |
| String | unitCode |
| String | unitName |
| String | properties |
| String | partyName |
| String | propertiesCsv |
| ItemProperty_Mdl | ipm |
| String | itemManualCode |
| String | itemProperty |
| String | itemType |
| String | isBestBefore |
| String | itemName |
| String | itemCode |
| String | packSizeCode |
| String | packSizeName |
| String | packUnitCode |
| String | packUnitName |
| String | packUnitConversionRate |
| String | packSizeSkuCode |
| String | packSizeSkuName |
| String | packUnitDecimalPlaces |
| String | packCount |

### AdviceGridEdit_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | orderNo |
| String | approxWt |
| String | unitCode |
| String | unitName |
| String | unitDecimalPlaces |
| String | propertiesCsv |
| String | itemProperty |
| String | itemType |
| String | isBestBefore |
| String | vSno |
| String | orderDocId |
| String | docId |
| String | orderVsno |
| String | isTransactionsExists |
| String | partyName |
| ItemProperty_Mdl | ipm |
| String | packSizeCode |
| String | packSizeName |
| String | packUnitCode |
| String | packUnitName |
| String | packUnitConversionRate |
| String | packSizeSkuCode |
| String | packSizeSkuName |
| String | packUnitDecimalPlaces |
| String | packCount |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHOrderNo | String subCode, String v_Date,boolean isAllowOtherSiteOrder, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHOrderNo_Mdl>> |
| getTxtHItem | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemName_Mdl>> |
| getDefaultValues | String docId | DmResult<Default_Mdl> |
| saveRecord | AdviceGridEdit_Mdl mrTemp | DmResult<String> |
| deleteRecord | String docId, String v_SNo | DmResult<String> |
| getMoveRec | String strKey | DmResult<GateAdvicePopup_Mdl> |
| getIbCode | ItemProperty_Mdl propertyMdl | DmResult<String> |
| getTxtHPackSize | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHPackSize_Mdl>> |

## Validator `GateAdvicePopup_Vld`
```java
public class GateAdvicePopup_Vld {
    public static void chkSave(AdviceGridEdit_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getSharedKeyFieldName(), "Item Name", 100);
        if (!mrTemp.getItemProperty().equalsIgnoreCase("")) {
            try {
                ItemPropertyPopup_Vld.validateProperties(ItemPropertyPopup_Vld.getTempProperties(mrTemp.getIpm(), false), "0", "", mrTemp.getIpm());
            } catch (DmException e) {
                throw new DmException(200, "Please fill all item properties.");
            }
        }
        if (DmShared.iNull(mrTemp.getIpm()).longValue() == 0) {
            throw new DmException(200, "Please re-select the item to refresh its properties.");
        }
        if (!DmShared.xNull(mrTemp.getPackSizeCode()).equalsIgnoreCase("")) {
            if (DmShared.vNull(mrTemp.getPackCount()).doubleValue() == 0) {
                throw new DmException(600, "Pack Count is mandatory.");
            }
        }
        if (DmShared.vNull(mrTemp.getPackCount()).doubleValue() > 0) {
            if (DmShared.xNull(mrTemp.getPackSizeCode()).equalsIgnoreCase("")) {
                throw new DmException(500, "Pack Size is mandatory.");
            }
        }
        DmShared.isValidText(mrTemp.getApproxWt(), "Approx Weight", 300);
        if (DmShared.vNull(mrTemp.getApproxWt()).longValue() == 0) {
            throw new DmException(300, "Weight should not be zero.");
        }
        DmShared.isValidText(mrTemp.getUnitName(), "Unit", 400);
    }
    public static String getApplicableProperties(String propertyList, String itemTypeManualCode) {
        String sortedPropertiesList = ItemPropertyPopup_Vld.getSortedPropertiesCsv(propertyList);
        String poApplicableProperties = "";
        poApplicableProperties = ItemPropertyPopup_Vld.getApplicableProperties(sortedPropertiesList, true, itemTypeManualCode);
        return poApplicableProperties;
    }
}
```

## Server `GateAdvicePopup_SrvImpl.java` (815 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.foriegnKey`, `DmShared.getNumberFormat`, `DmShared.iNull`, `DmShared.xNull`

### `getTxtHOrderNo`
`public DmResultPageing<List<TxtHOrderNo_Mdl>> getTxtHOrderNo(String subCode, String v_Date, boolean isAllowOtherSiteOrder, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT po.docId AS poDocId , po.recId AS poRecId , po.refNo AS poRefNo , pod.v_SNo AS poV_SNo , pod.quantity AS orderQty , pod.challanedQty , pod.itemCode , im.name AS itemName , im.manualCode AS manualCode , im.isBestBefore , pod.ibCode , pod.unitCode , um.unit AS unitName , im.propertyList , ISNULL(im.purchaseUnitConversionRate, 0) AS purchaseUnitConversionRate , ISNULL(um.decimalPlaces,0) AS purchaseUnitDecimalPlaces , um.gstUnitCode , sc.name as PartyName , city.cityName as cityName , it.manualCode as itemType , pod.packSizeCode , pod.packCount , psm.name as packSizeName , psm.sku as packSizeSku , psm.packUnitCode , psm.packUnitConversionRate , umPsm.unit as packUnitName , umPsmSku.unit as packSizeSkuName , umPsm.decimalPlaces as packUnitDecimalPlaces FROM POrderDetail pod LEFT JOIN pOrder po ON pod.docId = po.docId LEFT JOIN voucher_Type vt ON po.v_Type = vt.v_Type LEFT JOIN itemMast im ON pod.itemCode = im.code LEFT JOIN itemType it on it.code = im.itemType LEFT JOIN unitMast um ON pod.unitCode = um.code LEFT JOIN unitMast ums ON im.sku = ums.code LEFT JOIN subGroup sc ON sc.subCode = po.subCode LEFT JOIN city on city.cityCode = sc.cityCode LEFT JOIN packSizeMast psm on psm.code = pod.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku WHERE vt.category IN (:vCat,:vCatU) AND ISNULL(po.approvedBy,'') <> '' AND isFooter = 0 {…}`
- Params: `:vCat` ← `pvc.GENERAL_PURCHASE_ORDER`; `:vCatU` ← `pvc.GENERAL_PURCHASE_ORDER_UNREGISTERED`; `:subCode` ← `DmShared.iNull(subCode).toString()`; `:v_Date` ← `DmServer.convertDate(v_Date)`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `ItemBatch_Server.getIPM`

### `getTxtHItem`
`public DmResultPageing<List<TxtHItemName_Mdl>> getTxtHItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , im.manualCode , im.propertyList , it.manualCode as itemType , im.packSizeCode , im.isBestBefore , psm.name as packSizeName , psm.sku as packSizeSku , psm.packUnitCode , psm.packUnitConversionRate , umPsm.unit as packUnitName , umPsmSku.unit as packSizeSkuName , umPsm.decimalPlaces as packUnitDecimalPlaces FROM itemMast im LEFT JOIN itemType it on it.code = im.itemType LEFT JOIN packSizeMast psm on psm.code = im.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku LEFT JOIN itemSiteList sl ON sl.code = im.code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHPackSize`
`public DmResultPageing<List<TxtHPackSize_Mdl>> getTxtHPackSize(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT psm.code, psm.name as packSizeName, psm.packUnitConversionRate, psm.packUnitCode ,um.unit as packUnitName ,um.decimalPlaces as packUnitDecimalPlaces , psm.sku , ums.unit as skuName, ums.decimalPlaces as skuDecimalPlaces FROM packSizeMast as psm LEFT JOIN unitmast AS um ON um.code = psm.packUnitCode LEFT JOIN unitmast as ums on ums.code = psm.sku WHERE psm.isActive=1`

### `getDefaultValues`
`public DmResult<Default_Mdl> getDefaultValues(String docId)`

- SQL 1: `SELECT gio.docId , gio.recId , gio.v_Date , gio.partyCode , sgb.name as PartyName FROM gateInOut gio left join subGroup sgb ON sgb.subCode=gio.partyCode`
- SQL 2: `select um.unit as unitName, env.gt_UnitCode as unitcode , um.decimalPlaces from enviro env left join unitmast um on um.code=env.gt_UnitCode WHERE env.site_Code = :site_Code`
- Params: `:docId` ← `DmShared.iNull(docId).toString()`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Errors: "Gate default unit is not defined in environment setting."

### `saveRecord`
`public DmResult<String> saveRecord(AdviceGridEdit_Mdl mrTemp)`

- SQL 1: `select ISNULL(MAX(v_Sno),0)+1 as maxId from gateAdviceDetail where docId=:docId`
- SQL 2: `SELECT count(*) as cnt from gateInOut gio where gio.docId = :docId AND gio.isFinalWeighment = 1`
- SQL 3: `INSERT INTO gateAdviceDetail (docId, v_SNo, poDocId, poV_SNo, itemCode, ibCode, packSizeCode, packCount, approxWt,unitCode, preparedBy,preparedDt) VALUES (:docId, :v_SNo, :poDocId, :poV_SNo, :itemcode, :ibCode, :packSizeCode, :packCount, :approxWt, :unitCode, :preparedBy,:preparedDt)`
- SQL 4: `UPDATE gateAdviceDetail SET itemCode = :itemCode , poDocId = :poDocId , poV_SNo = :poV_SNo , ibCode = :ibCode , packSizeCode = :packSizeCode, packCount = :packCount, approxWt = :approxWt, unitCode = :unitCode WHERE docId = :docId and v_SNo=:v_SNo ;`
- Params: `:docId` ← `DmShared.iNull(mrTemp.getDocId()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getDocId()).toString()`; `:docId` ← `DmShared.xNull(mrTemp.getDocId())`; `:v_SNo` ← `DmShared.xNull(mrTemp.getvSno())`; `:poDocId` ← `DmShared.foriegnKey(mrTemp.getOrderDocId())`; `:poV_SNo` ← `DmShared.foriegnKey(mrTemp.getOrderVsno())`; `:itemcode` ← `DmShared.foriegnKey(mrTemp.getSharedKeyFieldCode())`; `:ibCode` ← `DmShared.foriegnKey(mrTemp.getIpm().getIbCode())`; `:packSizeCode` ← `DmShared.foriegnKey(mrTemp.getPackSizeCode())`; `:packCount` ← `DmServer.vNull(mrTemp.getPackCount())`; `:approxWt` ← `DmServer.vNull(mrTemp.getApproxWt())`; `:unitCode` ← `DmShared.xNull(mrTemp.getUnitCode())`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:preparedBy` ← `userName`; `:docId` ← `DmShared.xNull(mrTemp.getDocId())`; `:v_SNo` ← `DmShared.xNull(mrTemp.getvSno())`; `:itemcode` ← `DmShared.foriegnKey(mrTemp.getSharedKeyFieldCode())`; `:poDocId` ← `DmShared.foriegnKey(mrTemp.getOrderDocId())`; `:poV_SNo` ← `DmShared.foriegnKey(mrTemp.getOrderVsno())`; `:ibCode` ← `DmShared.foriegnKey(mrTemp.getIpm().getIbCode())`; `:packSizeCode` ← `DmShared.foriegnKey(mrTemp.getPackSizeCode())`; `:packCount` ← `DmServer.vNull(mrTemp.getPackCount())`; `:approxWt` ← `DmServer.vNull(mrTemp.getApproxWt())`; `:unitCode` ← `DmShared.xNull(mrTemp.getUnitCode())`
- Errors: "Gate Advice not allowed, Final weighment is complete."

### `getMoveRec`
`public DmResult<GateAdvicePopup_Mdl> getMoveRec(String docId)`

- SQL 1: `select gio.isFinalWeighment from gateInOut gio where gio.docId = :docId`
- SQL 2: `SELECT gad.docId , gad.v_sno , gad.itemCode , im.displayName as itemName , im.propertyList , im.isBestBefore , gad.poV_SNo , gad.poDocId , po.v_No , po.recId as orderNo , gad.ibCode , gad.approxWt , gad.unitCode , um.unit as unitName , um.decimalPlaces , uld.docId as unloadingDocId , sc.name as PartyName , city.cityName as cityName , it.manualCode as itemType , gad.packSizeCode , gad.packCount , psm.name as packSizeName , psm.sku as packSizeSku , psm.packUnitCode , psm.packUnitConversionRate , umPsm.unit as packUnitName , umPsmSku.unit as packSizeSkuName , umPsm.decimalPlaces as packUnitDecimalPlaces FROM gateAdviceDetail gad left join itemMast im on im.code=gad.itemCode left join itemType it on it.code = im.itemType left join pOrder po on po.docId=gad.poDocId left join unitmast um on um.code=gad.unitCode LEFT JOIN subGroup sc ON sc.subCode = po.subCode LEFT JOIN city on city.cityCode = sc.cityCode left join unloadingDetail uld on uld.gateDocId=gad.docId and uld.gateAdviceV_Sno=gad.v_Sno LEFT JOIN packSizeMast psm on psm.code = gad.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku WHERE gad.docId = :docId`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:docId` ← `DmShared.xNull(docId)`
- Other calls: `ItemBatch_Server.getIPM`

### `deleteRecord`
`public DmResult<String> deleteRecord(String docId, String v_SNo)`

- SQL 1: `Delete From gateAdviceDetail where docid= :docId and v_Sno=:v_SNo`
- Params: `:docId` ← `docId`; `:v_SNo` ← `v_SNo`

### `getIbCode`
`public DmResult<String> getIbCode(ItemProperty_Mdl mdl)`

- Other calls: `ItemBatch_Server.createItemBatch`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmCheckBox.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `paperbiz.gate.gateAdvicePopup.shared`, `paperbiz.selfToolkit.itemBatch.server`, `paperbiz.selfToolkit.itemBatch.shared`, `paperbiz.selfToolkit.itemPropertyPopup.client`, `paperbiz.selfToolkit.itemPropertyPopup.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| getDefaultValues | `if (unitCode == null)` | "Gate default unit is not defined in environment setting." |

## Notes (hand-written)
_No hand-written notes yet._
