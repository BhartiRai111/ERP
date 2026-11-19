# pb-purchase / shipmentItemDetail

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/purchase/src/shipmentItemDetail/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: shipmentItemDetail
- Update tables: shipmentItemDetail
- Delete cascade (in order): shipmentItemDetail
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: 'Item Name' (Text); 'Shipment Date' (Text); 'Shipment No.' (Text); Dispatch Date (Text); Entry Date (Text); rule: Dispatch Date should be greater than or equal to Entry Date.
- Lookups/suggestions: getSuggestion, getTxtHVoucherType, getTxtHItem, getTxtHUnit
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `ShipmentItemDetail`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Entry No. | DmTextBox | `txtEntryNo` | `100, true` |
| 2 | Entry Date | DmTextBox | `txtEntryDate` | `100, true` |
| 3 | Shipment Entry No. | DmTextHelp | `txtHShipmentEntryNo` | `com.google.gwt.core.client.TxtHShipmentNo_PrpAcs, 7, true` |
| 4 | Shipment Entry Date | DmTextBox | `txtShipmentEntryDate` | `100, true` |
| 5 | Item Name | DmTextHelp | `txtHItem` | `TxtHItemName_PrpAcs, 7, true` |
| 6 | Item Description | DmTextBox | `txtItemDescription` | `100, false` |
| 7 | Unit | DmTextBox | `txtUnit` | `100, false` |
| 8 | Qty | DmTextBoxNumeric | `txtQty` | `5, false, 75, false` |
| 9 | Rate | DmTextBoxNumeric | `txtrate` | `2, false, 12, false` |
| 10 | Amount | DmTextBoxNumeric | `txtAmount` | `2, false, 75, false` |
| 11 | Mark & Container No. | DmTextBox | `txtContainerNo` | `100, false` |
| 12 | No. & Kind of Pkgs | DmTextBox | `txtKindOfPkge` | `100, false` |
| 13 | No of Balens | DmTextBoxNumeric | `txtNoOfBalens` | `2, false, 75, false` |
| 14 | PI Ref No. | DmTextBox | `txtPIRefNo` | `100, false` |
| 15 | Dispatch Date | DmTextBox | `txtDispatchDate` | `100, false` |
| 16 | PI No. | DmTextBoxNumeric | `txtPIno` | `2, false, 10, false` |
| 17 | PI Ref Date | DmTextBox | `txtPIrefDate` | `100, false` |
| 18 | Bill Of Loading No. | DmTextBoxNumeric | `txtBillOfLodingNo` | `2, false, 10, false` |

Other widgets (not in tab list): `dmFind` (DmFind), `txtHVoucherType` (DmTextHelp)

**Grid / popup columns** (header, width, db field):
- `dmFind`: Shipment Item Detail No [Text, w150, recId]; Date [Text, w75, replace(convert(varchar, v_Date, 106), ' ','-')]; Type [Text, w70, short_Name]; Shipment Entry No [Text, w90, shipmentEntryNo]; Ship. Entry Date [Text, w75, replace(convert(varchar, shipmentDate, 106), ' ','-')]; Item Name [Text, w250, itemName]; item Desc [Text, w200, itemDesc]; Quantity [Text, w75, quantity]; Amount [Text, w75, amount]; Unit [Text, w75, unitName]; Rate [Text, w75, rate]; dispatch Date [Text, w175, dispatchDate]
- `txtHItem`: Item Name [Text, w175, ItemName]; Quantity [Text, w100, quantity]; ItemDescription [Text, w100, itemDesc]; Unit Name [Text, w100, unitName]; Rate [Text, w100, rate]
- `txtHVoucherType`: Type [Text, w0, name]
- `txtHShipmentEntryNo`: Shipment Entry No [Text, w100, shipmentEntryNo]; PiRef Date [Text, w100, PIRefDate]; PI No [Text, w75, PINo]; PI Ref No [Text, w100, PIRefNo]; Loading NO [Text, w175, LoadingNO]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHVoucherType` → `getTxtHVoucherType`
- `txtHShipmentEntryNo` → `getShipmentNO`
- `txtHItem` → `getTxtHItem`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `EntryNo` ← DmShared.xNull(txtEntryNo.getText())
- `VoucherTypeCode` ← DmShared.xNull(txtHVoucherType.getTag())
- `EntryDate` ← DmShared.dNull(txtEntryDate.getText())
- `ShipmentEntryCode` ← DmShared.xNull(txtHShipmentEntryNo.getTag())
- `ShipmentEntryName` ← DmShared.xNull(txtHShipmentEntryNo.getText())
- `ShipmentEntryDate` ← DmShared.dNull(txtShipmentEntryDate.getText())
- `ItemCode` ← DmShared.xNull(txtHItem.getTag())
- `ItemName` ← DmShared.xNull(txtHItem.getText())
- `ItemDesc` ← DmShared.xNull(txtItemDescription.getText())
- `UnitCode` ← DmShared.xNull(txtUnit.getTag())
- `UnitName` ← DmShared.xNull(txtUnit.getText())
- `Rate` ← DmShared.xNull(txtrate.getText())
- `Qty` ← DmShared.xNull(txtQty.getText())
- `Amount` ← DmShared.xNull(txtAmount.getText())
- `ContainerNo` ← DmShared.xNull(txtContainerNo.getText())
- `NoofBales` ← DmShared.xNull(txtNoOfBalens.getText())
- `NoOfPckgs` ← DmShared.xNull(txtKindOfPkge.getText())
- `DispatchDate` ← DmShared.dNull(txtDispatchDate.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtHVoucherType.setTag` ← data.getVoucherTypeCode()
- `txtHVoucherType.setText` ← data.getVoucherTypeName()
- `txtHVoucherType.setText` ← data.getVoucherTypeName()
- `txtEntryNo.setText` ← data.getSharedKeyFieldName()
- `txtEntryDate.setText` ← data.getEntryDate()
- `txtHShipmentEntryNo.setTag` ← data.getShipmentEntryCode()
- `txtHShipmentEntryNo.setText` ← data.getShipmentEntryName()
- `txtShipmentEntryDate.setText` ← data.getEntryDate()
- `txtHItem.setTag` ← data.getItemCode()
- `txtHItem.setText` ← data.getItemName()
- `txtItemDescription.setText` ← data.getItemDesc()
- `txtUnit.setTag` ← data.getUnitCode()
- `txtUnit.setText` ← data.getUnitName()
- `txtQty.setText` ← data.getQty()
- `txtrate.setText` ← data.getRate()
- `txtAmount.setText` ← data.getAmount()
- `txtContainerNo.setText` ← data.getContainerNo()
- `txtKindOfPkge.setText` ← data.getNoOfPckgs()
- `txtNoOfBalens.setText` ← data.getNoofBales()
- `txtDispatchDate.setText` ← data.getDispatchDate()
- `txtPIno.setText` ← data.getPINo()
- `txtPIrefDate.setText` ← data.getPiRefDate()
- `txtPIRefNo.setText` ← DmShared.xNull(data.getPIRefNo())
- `txtBillOfLodingNo.setText` ← data.getLoadingNO()
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtEntryDate.setEnabled(false)`
- `txtEntryNo.setEnabled(false)`
- `txtShipmentEntryDate.setEnabled(false)`
- `txtPIno.setEnabled(false)`
- `txtPIRefNo.setEnabled(false)`
- `txtBillOfLodingNo.setEnabled(false)`
- `txtPIrefDate.setEnabled(false)`
- `txtItemDescription.setEnabled(false)`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**On New click:**
- `clear()`
- `setDefaultValues()`
- `manageDisplay()`
- `txtHShipmentEntryNo.setFocus(true)`

**`manageDisplay()` logic (condensed code):**
```java
txtEntryDate.setEnabled(false);
txtEntryNo.setEnabled(false);
txtShipmentEntryDate.setEnabled(false);
txtPIno.setEnabled(false);
txtPIRefNo.setEnabled(false);
txtBillOfLodingNo.setEnabled(false);
txtPIrefDate.setEnabled(false);
txtItemDescription.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
clear();
setDefaultValues();
manageDisplay();
txtHShipmentEntryNo.setFocus(true);
```

`topControl.addDeleteHandeller` → `deleteClicked()`
```java
new DmConfirmMessageBox("Are you sure? You want to delete this record.", new DialogHideEvent.DialogHideHandler() {
public void onDialogHide(DialogHideEvent event) {
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
showModifyReason();
}).show();
clear();
```

`topControl.addDeleteHandeller` → `onDialogHide()`
```java
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
showModifyReason();
```

`topControl.addEditHandeller` → `editClicked()`
```java
manageDisplay();
```

`topControl.addCardHandeller` → `cardClicked()`
```java
topControlCard();
```

`topControl.addFindHandeller` → `findClicked()`
```java
flwForm.remove(vrtInnerForm);
flwForm.add(dmFind);
```

`topControl.addMoveRecHandeller` → `moveRec()`
```java
topControlMoveRec(strFetchCode);
```

`topControl.addSaveHandeller` → `saveClicked()`
```java
if (topControl.getMode().equalsIgnoreCase("Edit")) {
showModifyReason();
} else {
topControlSave();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(ShipmentItemDetail.this);
```

`modifyReason.addCallBack` → `onOk()`
```java
try {
modifyReasonMdl = modifyReason;
if (topControl.getMode().equalsIgnoreCase("Browse") && DmShared.iNull(topControl.getDocId()).longValue() > 0) {
topControlDelete(topControl.getDocId());
} else {
topControlSave();
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), (DmTextBox) null).show();
```

`txtHVoucherType.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHVoucherType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHVoucherType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHVoucherType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHVoucherType).show();
```

`txtHVoucherType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHVoucherType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHVoucherType).show();
```

`txtHShipmentEntryNo.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getShipmentNO(txtHVoucherType.getTag(), helper, blnToEndOfFile, new AsyncCallback<Dm
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHShipmentEntryNo).show();
public void onSuccess(DmResultPageing<List<TxtHShipmentNo_Mdl>> result) {
if (result.isStatus()) {
txtHShipmentEntryNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHShipmentEntryNo).show();
```

`txtHShipmentEntryNo.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHShipmentEntryNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHShipmentEntryNo).show();
```

`txtHShipmentEntryNo.addTextHelpCallBack` → `getSelectedRow()`
```java
clearShipmentNo();
onShipmentSelection(blnStatus, t1Row);
```

`txtHItem.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHItem(DmShared.xNull(txtHShipmentEntryNo.getTag()), helper, blnToEndOfFile, ne
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItem).show();
public void onSuccess(DmResultPageing<List<TxtHItemName_Mdl>> result) {
if (result.isStatus()) {
txtHItem.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItem).show();
```

`txtHItem.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItem.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItem).show();
```

`txtHItem.addTextHelpCallBack` → `getSelectedRow()`
```java
clearItemName();
onItemSelection(blnStatus, t1Row);
```

`dmFind.addHelpGridCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getFind(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Find_Mdl>>>()
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), dmFind.getTextBox()).show();
public void onSuccess(DmResultPageing<List<Find_Mdl>> result) {
if (result.isStatus()) {
dmFind.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), dmFind.getTextBox()).show();
```

`dmFind.addHelpGridCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
dmFind.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), dmFind.getTextBox()).show();
```

`dmFind.addHelpGridCallBack` → `getSelectedRow()`
```java
flwForm.remove(dmFind);
flwForm.add(vrtInnerForm);
if (blnStatus) {
topControlMoveRec(tRow.getSharedKeyFieldCode());
```

**Top control options:** setRefreshRequired(false), setOtherRequired(false), setDisplay(true)

_Print permission "P" is removed for this screen (replace "P" with "*")._

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Alert: "Please select Shipment No."
- Alert: "Please select Shipment No."

## Files
| file | lines |
|---|---|
| client/ShipmentItemDetail.java | 728 |
| client/ShipmentItemDetail_Dsg.java | 511 |
| server/ShipmentItemDetail_SrvImpl.java | 936 |
| shared/DefaultValue_Mdl.java | 120 |
| shared/Find_Mdl.java | 113 |
| shared/ShipmentItemDetail_Mdl.java | 337 |
| shared/ShipmentItemDetail_Srv.java | 32 |
| shared/ShipmentItemDetail_SrvAsync.java | 32 |
| shared/ShipmentItemDetail_Vld.java | 23 |
| shared/TxtHItemName_Mdl.java | 78 |
| shared/TxtHShipmentNo_Mdl.java | 77 |

## UI labels / columns (client)
`Entry No.`, `Entry Date`, `Shipment Entry No.`, `Shipment Entry Date`, `Item Name`, `Item Description`, `Unit`, `Qty`, `Rate`, `Amount`, `Mark & Container No.`, `No. & Kind of Pkgs`, `No of Balens`, `PI Ref No.`, `Dispatch Date`, `PI No.`, `PI Ref Date`, `Bill Of Loading No.`

## Models (shared DTOs)
### TxtHShipmentNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | shipmentEntryNo |
| String | shipmentEntrydate |
| String | poDocId |
| String | piNo |
| String | piRefNo |
| String | loadingNO |
| String | piRefDate |

### TxtHItemName_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | itemdescription |
| String | unitCode |
| String | unitName |
| String | rate |
| String | quantity |
| String | manualGstType |
| String | gstTypeCode |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | newVoucherTypeCode |
| String | newVoucherTypeName |
| String | newEntryDate |
| String | voucherTypeCode |
| String | voucherTypeName |
| String | guiTemplateCode |
| String | entryDate |
| boolean | continuousMode |
| String siteCode, | siteName |
| String | newVoucherCategory |
| String | voucherCategory |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | v_Type |
| String | v_Date |
| String | shipmentEntryNo |
| String | shipmentEntryDate |
| String | itemName |
| String | unit |
| String | itemDesc |
| String | qty |
| String | rate |
| String | amount |
| String | dispatchDate |

### ShipmentItemDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| ModifyReason_Mdl | reasonInfo_Mdl |
| String | voucherCategory |
| RecId | recIdEntry |
| String | voucherTypeCode |
| String | voucherTypeName |
| String | entryNo |
| String | entryDate |
| String | shipmentEntryCode |
| String | shipmentEntryName |
| String | shipmentEntryDate |
| String | itemCode |
| String | itemName |
| String | itemDesc |
| String | unitCode |
| String | unitName |
| String | qty |
| String | rate |
| String | amount |
| String | containerNo |
| String | noOfPckgs |
| String | NoofBales |
| String | dispatchDate |
| String | BillOfLoadingNo |
| String | PIRefNo |
| String | PINo |
| String | loadingNO |
| String | piRefDate |
| String | site_Code |
| String | u_EntDt |
| String | u_Name |
| String | preparedDt |
| String | preparedBy |
| String | modifiedBy |
| String | manualGstType |
| String | gstTypeCode |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getSuggestion | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getMoveRec | String strKey | DmResult<ShipmentItemDetail_Mdl> |
| saveRecord | ShipmentItemDetail_Mdl mrTemp, String voucherPrefix | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl reasonInfo_Mdl | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getTxtHVoucherType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<ShipmentItemDetail_Mdl>> |
| getTxtHItem | String ShipmentDocId, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemName_Mdl>> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| getTxtHUnit | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getShipmentNO | String v_Type, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHShipmentNo_Mdl>> |

## Validator `ShipmentItemDetail_Vld`
```java
public class ShipmentItemDetail_Vld {
    public static void chkSave(ShipmentItemDetail_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getItemName(), "'Item Name'", 150);
        DmShared.isValidText(mrTemp.getShipmentEntryDate(), "'Shipment Date'", 200);
        DmShared.isValidText(mrTemp.getShipmentEntryName(), "'Shipment No.'", 100);
        DmShared.isValidText(mrTemp.getDispatchDate(), "Dispatch Date", 250);
        DmShared.isValidText(mrTemp.getEntryDate(), "Entry Date", 300);
        if (!DmShared.dNull(mrTemp.getDispatchDate()).equalsIgnoreCase("")) {
            if ((DmShared.convertDate(mrTemp.getEntryDate()).getTime() > (DmShared.convertDate(DmShared.dNull(mrTemp.getDispatchDate())).getTime()))) {
                throw new DmException(250, "Dispatch Date should be greater than or equal to Entry Date.");
            }
        }
    }
}
```

## Server `ShipmentItemDetail_SrvImpl.java` (936 lines)
Helpers used: `DmServer.checkForwardVoucherDateTime`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getRecId`, `DmServer.getSerialNoInteger`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.setSsnOldData`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `Select docId From shipmentItemDetail ;`

### `getSuggestion`
`public DmResultPageing<List<String>> getSuggestion(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT name FROM areaMast`

### `getMoveRec`
`public DmResult<ShipmentItemDetail_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT sid.docId , sid.v_Type,vt.description as voucherName,sid.v_Date,sid.recId , sm.docId AS shipmentEntryCode, sm.recId AS shipmentEntryName, sm.v_date AS shipmentEntryDate , sid.itemCode ,im.name AS ItemName ,sid.itemDesc, sid.unitCode ,um.unit AS unitName ,sid.isManualGstType ,sid.gstTypeCode , sid.rate, sid.qty AS quantity ,sid.amount,sid.containerNo , sid.NoofBales,sid.noOfPckgs,sid.dispatchDate , sid.shipmentEntryCode,sid.u_EntDt,sid.preparedBy,sid.preparedDt,sid.u_EntDt,sid.u_Name, sid.modifiedBy --,pod.v_Date AS PIRefDate , pod.v_No AS PINo, --pod.refNo AS PIRefNo, pod.oh_Amt_Gross AS LoadingNO FROM shipmentItemDetail AS sid LEFT JOIN voucher_Type AS vt ON vt.v_Type = sid.v_Type LEFT JOIN shipmentEntry AS sm ON sm.docId = sid.shipmentEntryCode --LEFT JOIN pOrder pod on pod.docId = sm.againstPONo --LEFT JOIN pOrderDetail po on po.itemCode = sid.itemCode LEFT JOIN itemMast AS im ON im.code = sid.itemCode LEFT JOIN unitMast AS um ON um.code = im.sku WHERE sid.docId = :docId`
- Params: `:docId` ← `DmShared.iNull(strKey).toString()`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl reasonInfo_Mdl)`

- SQL 1: `Delete From shipmentItemDetail Where docId= :docId`
- Params: `:docId` ← `DmShared.iNull(strKey).toString()`

### `saveRecord`
`public DmResult<String> saveRecord(ShipmentItemDetail_Mdl mrTemp, String voucherPrefix)`

- SQL 1: `INSERT INTO shipmentItemDetail (docId ,recId ,recIdPrefix ,v_Type ,v_No ,v_Prefix ,v_Date ,shipmentEntryCode ,itemCode ,itemDesc, ,unitCode ,isManualGstType ,gstTypeCode ,qty ,rate ,amount ,containerNo ,noOfPckgs ,site_Code ,u_EntDt ,u_Name ,preparedDt ,preparedBy ,dispatchDate ,NoofBales) VALUES(:docId ,:recId ,:recIdPrefix ,:v_Type ,:v_No ,:v_Prefix ,:v_Date, ,:shipmentEntryCode ,:itemCode ,:itemDesc ,:unitCode ,:isManualGstType ,:gstTypeCode ,:qty ,:rate ,:amount ,:containerNo ,:noOfPckgs ,:site_Code ,:u_EntDt ,:u_Name ,:preparedDt ,:preparedBy ,:dispatchDate ,:NoofBales)`
- SQL 2: `UPDATE shipmentItemDetail SET shipmentEntryCode =:shipmentEntryCode , itemCode = :itemCode , itemDesc = :itemDesc , unitCode = :unitCode , isManualGstType = :isManualGstType , gstTypeCode = :gstTypeCode , qty = :qty , rate = :rate , amount = :amount , containerNo = :containerNo , noOfPckgs = :noOfPckgs , dispatchDate = :dispatchDate , noofBales = :noofBales , U_Name = :u_name , U_EntDt = :U_EntDt , modifiedBy = :modifiedBy WHERE docId = :docId`
- Params: `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:recId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:recIdPrefix` ← `DmShared.xNull(DmShared.xNull(mrTemp.getRecIdEntry().getPrefix()))`; `:v_Type` ← `DmShared.foriegnKey(mrTemp.getVoucherTypeCode())`; `:v_No` ← `DmShared.iNull(mrTemp.getRecIdEntry().getCounter()).toString()`; `:v_Prefix` ← `DmShared.xNull(voucherPrefix)`; `:v_Date` ← `DmServer.convertDate(mrTemp.getEntryDate())`; `:site_Code` ← `DmShared.foriegnKey(DmServer.getSsnSiteCode(this))`; `:shipmentEntryCode` ← `DmShared.foriegnKey(mrTemp.getShipmentEntryCode())`; `:itemCode` ← `DmShared.foriegnKey(mrTemp.getItemCode())`; `:itemDesc` ← `DmShared.foriegnKey(mrTemp.getItemDesc())`; `:unitCode` ← `DmShared.foriegnKey(mrTemp.getUnitCode())`; `:isManualGstType` ← `DmShared.xNull(mrTemp.getManualGstType())`; `:gstTypeCode` ← `DmShared.xNull(mrTemp.getGstTypeCode())`; `:qty` ← `DmShared.xNull(mrTemp.getQty())`; `:rate` ← `DmServer.vNull(mrTemp.getRate())`; `:amount` ← `DmServer.vNull(mrTemp.getAmount())`; `:containerNo` ← `DmShared.xNull(mrTemp.getContainerNo())`; `:noOfPckgs` ← `DmServer.vNull(mrTemp.getNoOfPckgs())`; `:NoofBales` ← `DmServer.vNull(mrTemp.getNoofBales())`; `:dispatchDate` ← `DmServer.convertDate(mrTemp.getDispatchDate())`; `:u_name` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `timestamp`; `:preparedBY` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `timestamp`; `:shipmentEntryCode` ← `DmShared.xNull(mrTemp.getShipmentEntryCode())`; `:itemCode` ← `DmShared.xNull(mrTemp.getItemCode())`; `:itemDesc` ← `DmShared.xNull(mrTemp.getItemDesc())`; `:unitCode` ← `DmShared.foriegnKey(mrTemp.getUnitCode())`; `:isManualGstType` ← `DmShared.xNull(mrTemp.getManualGstType())`; `:gstTypeCode` ← `DmShared.xNull(mrTemp.getGstTypeCode())`; `:qty` ← `DmShared.xNull(mrTemp.getQty())`; `:rate` ← `DmShared.xNull(mrTemp.getRate())`; `:amount` ← `DmShared.xNull(mrTemp.getAmount())`; `:containerNo` ← `DmShared.xNull(mrTemp.getContainerNo())`; `:noOfPckgs` ← `DmShared.xNull(mrTemp.getNoOfPckgs())`; `:noofBales` ← `DmShared.xNull(mrTemp.getNoofBales())`; `:dispatchDate` ← `DmServer.convertDate(mrTemp.getDispatchDate())`; `:u_name` ← `DmServer.getSsnUserName(this)`; `:modifiedBy` ← `DmServer.getSsnUserName(this)`; `:U_EntDt` ← `timestamp`; `:docId` ← `mrTemp.getSharedKeyFieldCode()`
- Errors: "Invalid key."

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sid.docId,sid.recId ,sid.v_Date ,sid.v_Type ,vt.short_Name ,sid.shipmentEntryCode, se.recId AS shipmentEntryNo ,se.v_Date AS shipmentEntryDate ,sid.itemCode, im.name AS itemName,sid.itemDesc, um.unit AS unitName ,sid.rate,sid.qty AS quantity ,sid.amount,sid.dispatchDate FROM shipmentItemDetail AS sid LEFT JOIN voucher_Type AS vt ON vt.v_Type = sid.v_Type LEFT JOIN shipmentEntry AS se ON se.docId = sid.shipmentEntryCode LEFT JOIN itemMast AS im ON im.code = sid.itemCode LEFT JOIN unitMast AS um ON um.code = im.sku WHERE sid.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHVoucherType`
`public DmResultPageing<List<ShipmentItemDetail_Mdl>> getTxtHVoucherType(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type, vt.description AS name , vt.category, vt.guiTemplateCode FROM voucher_Type vt WHERE vt.isActive = 1 {…}`

### `getTxtHItem`
`public DmResultPageing<List<TxtHItemName_Mdl>> getTxtHItem(String shipmentDocId, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.docId, sm.recId , sm.againstPONo , pod.itemCode, im.name AS ItemName , pod.itemDesc, pod.isManualGstType, pod.gstTypeCode , pod.quantity,pod.unitCode, um.unit AS unitName ,pod.rate FROM shipmentEntry AS sm LEFT JOIN pOrderDetail AS pod ON pod.docId = sm.againstPONo LEFT JOIN itemMast im ON im.code = pod.itemCode LEFT JOIN unitMast um ON um.code = pod.unitCode {…}`
- Params: `:docId` ← `DmShared.xNull(shipmentDocId)`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`

- SQL 1: `SELECT TOP 1 vt.v_Type AS code , vt.description AS name , vt.guiTemplateCode , vt.category FROM voucher_Type vt WHERE vt.category = :vCat AND vt.isActive = 1`
- Params: `:vCat` ← `pvc.IM_SHIPMENT_ITEM_DETAIL`

### `getTxtHUnit`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHUnit(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT um.code, um.unit AS name FROM unitMast um`

### `getShipmentNO`
`public DmResultPageing<List<TxtHShipmentNo_Mdl>> getShipmentNO(String v_Type, DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.docId ,sm.recId AS shipmentEntryNo ,sm.v_Date AS shipmentEntryDate, sm.againstPONo, po.v_Date AS PIRefDate , po.v_No AS PINo, po.refNo AS PIRefNo, po.oh_Amt_Gross AS LoadingNO FROM shipmentEntry AS sm LEFT JOIN pOrder AS po ON po.docId = sm.againstPONo`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.purchase.shipmentItemDetail.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |

## Notes (hand-written)
_No hand-written notes yet._
