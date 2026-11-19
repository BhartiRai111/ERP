# pb-store / reelSheetIssue

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/store/src/reelSheetIssue/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: stockHead, stockHeadDetail, stockHeadItemId
- Update tables: stockHead, requisitionDetail
- Delete cascade (in order): stockHeadItemId, stockHeadDetail, stockHead
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Voucher Type (Text); Voucher Date (Text)
- Lookups/suggestions: getTxtHVoucher, getTxtHItemName, getTxtHIssueTo, getTxtHPackSize, getTxtHJobWorkChallan
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `ReelSheetIssue`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | ReelSheet Issue | DmTextHelp | `txtHVoucherType` | `KeyField_PrpAcs, 5, true` |
| 2 | Date | DmTextBox | `txtVoucherDate` | `11, true` |
| 3 | Ref. No. | DmTextBox | `txtRefNo` | `20, false` |
| 4 | Ref. Date. | DmTextBox | `txtRefDate` | `11, false` |
| 5 | Issue To | DmTextHelp | `txtHIssueTo` | `TxtHIssueTo_PrpAcs, 6, false` |
| 6 | Entry No. | DmTextBox | `txtVoucherNo` | `20, false` |
| 7 | Job Work Challan | DmTextHelp | `txtHJobWorkChallan` | `TxtHJobWorkChallan_PrpAcs, 6, false` |
| 8 | Remark | DmRemarkTextBox | `txtRemark` | `` |

Other widgets (not in tab list): `txtReqDate` (DmTextBox), `txtBalanacingAc` (DmTextBox), `displayStrip` (DmDisplayStrip), `dmFind` (DmFind), `gridItemDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHVoucherType`: Name [Text, w0, name]
- `txtHIssueTo`: Name [Text, w0, name]; Manual Code [Text, w100, manualCode]; City [Text, w150, cityName]
- `txtHJobWorkChallan`: Challan No. [Text, w120, recId]; Date [Text, w80, v_Date]
- `dmFind`: Issue No. [Text, w150, recId]; Date [Text, w80, replace(convert(varchar, v_Date, 106), ' ','-')]; Type [Text, w80, short_Name]; Department In [Text, w350, departmentName]
- `gridItemDetail`: Reel/Bundle [Text, w140]; Warehouse [Text, w140]; Item Name [Text, w264]; Item Nature [Text, w75]; Unit [Text, w50]; BF [Text, w50]; GSM [Text, w50]; Width [Text, w50]; Length [Text, w55]; Size Unit [Text, w64]; Grain [Text, w50]; Ream Wt. [Text, w80]; SPR [Text, w90]; RPB [Text, w95]; Pack Count [Text, w80]; Quantity [Text, w70]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHVoucherType` → `getTxtHVoucher`
- `txtHIssueTo` → `getTxtHIssueTo`
- `txtHJobWorkChallan` → `getTxtHJobWorkChallan`
- `displayStrip` → `getFind`
- `modifyReason` → `saveRecord`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtVoucherNo.getText())
- `VoucherDate` ← DmShared.dNull(txtVoucherDate.getText())
- `VoucherTypeCode` ← DmShared.xNull(txtHVoucherType.getTag())
- `VoucherTypeName` ← DmShared.xNull(txtHVoucherType.getText())
- `BalancingAc` ← DmShared.xNull(txtBalanacingAc.getTag())
- `BalancingAcName` ← DmShared.xNull(txtBalanacingAc.getText())
- `IssueToCode` ← DmShared.xNull(txtHIssueTo.getTag())
- `IssueToName` ← DmShared.xNull(txtHIssueTo.getText())
- `RefNo` ← DmShared.xNull(txtRefNo.getText())
- `RefDate` ← DmShared.dNull(txtRefDate.getText())
- `RemarkHeader` ← DmShared.xNull(txtRemark.getText())
- `WcDocId` ← DmShared.xNull(txtHJobWorkChallan.getTag())
- `WcRecId` ← DmShared.xNull(txtHJobWorkChallan.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtVoucherNo.setText` ← DmShared.xNull(mrTemp.getSharedKeyFieldName())
- `txtVoucherNo.setTag` ← DmShared.iNull(mrTemp.getRecIdItemIssue().getCounter()).toString()
- `txtVoucherNo.setTag2nd` ← DmShared.xNull(mrTemp.getRecIdItemIssue().getPrefix())
- `txtVoucherDate.setText` ← DmShared.dNull(mrTemp.getVoucherDate())
- `txtHVoucherType.setText` ← DmShared.xNull(mrTemp.getVoucherTypeName())
- `txtHVoucherType.setTag` ← DmShared.xNull(mrTemp.getVoucherTypeCode())
- `txtHIssueTo.setTag` ← DmShared.xNull(mrTemp.getIssueToCode())
- `txtHIssueTo.setText` ← DmShared.xNull(mrTemp.getIssueToName())
- `txtBalanacingAc.setTag` ← DmShared.xNull(mrTemp.getBalancingAc())
- `txtBalanacingAc.setText` ← DmShared.xNull(mrTemp.getBalancingAcName())
- `txtRefDate.setText` ← DmShared.dNull(mrTemp.getRefDate())
- `txtRefNo.setText` ← DmShared.xNull(mrTemp.getRefNo())
- `txtRemark.setText` ← DmShared.xNull(mrTemp.getRemarkHeader())
- `txtHJobWorkChallan.setTag` ← DmShared.xNull(mrTemp.getWcDocId())
- `txtHJobWorkChallan.setText` ← DmShared.xNull(mrTemp.getWcRecId())
- `userControl.setTextBlog` ← preparedByBlog, DmShared.xNull(mrTemp.getSharedPreparedBy())
- `userControl.setTextBlog` ← modifiedByBlog, DmShared.xNull(mrTemp.getSharedModifiedBy())
- userControl.setTextBlog(preparedByBlog, DmShared.xNull(mrTemp.getSharedPreparedBy()))
- userControl.setTextBlog(modifiedByBlog, DmShared.xNull(mrTemp.getSharedModifiedBy()))

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtVoucherNo.setEnabled(false)`
- `txtReqDate.setEnabled(false)`
- `txtVoucherDate.setEnabled(flag)`
- `txtVoucherDate.setEnabled(flag)`
- `txtVoucherDate.setEnabled(false)`
- `txtHVoucherType.setEnabled(false)`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`
- `txtVoucherDate.setText` ← ""

**On New click:**
- `onAdd()`
- `txtVoucherDate.setFocus(true)`

**`manageDisplay()` logic (condensed code):**
```java
txtVoucherNo.setEnabled(false);
txtReqDate.setEnabled(false);
txtVoucherDate.setEnabled(flag);
if (flag) {
if (dateEditable) {
txtVoucherDate.setEnabled(flag);
} else {
txtVoucherDate.setEnabled(false);
if (topControl.getMode().equalsIgnoreCase("Edit")) {
txtHVoucherType.setEnabled(false);
flwAddOrder.clear();
if (flag) {
flwAddOrder.add(btnAddReelNo);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
```

`topControl.addNewHandeller` → `newClicked()`
```java
onAdd();
txtVoucherDate.setFocus(true);
```

`topControl.addEditHandeller` → `editClicked()`
```java
manageDisplay(true);
gridItemDetail.getGrid().getStore().add(new GrdItemDetail_Mdl());
txtVoucherDate.setFocus(true);
```

`topControl.addDeleteHandeller` → `deleteClicked()`
```java
new DmConfirmMessageBox("Are you sure? You want to delete this record.", new DialogHideEvent.DialogHideHandler() {
public void onDialogHide(DialogHideEvent event) {
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
showModifyReason();
}).show();
```

`topControl.addDeleteHandeller` → `onDialogHide()`
```java
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
showModifyReason();
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
topControlMoveRec(fetchCode);
```

`topControl.addPrintHandeller` → `printClicked()`
```java
prnRecord(false);
```

`topControl.addSaveHandeller` → `saveClicked()`
```java
save();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(ReelSheetIssue.this);
```

`txtHVoucherType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHVoucher(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFie
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

`txtHVoucherType.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
txtBalanacingAc.setTag(DmShared.xNull(t1Row.getSharedPreparedBy()));
txtBalanacingAc.setText(DmShared.xNull(t1Row.getSharedPreparedIP()));
vTypeShortName = DmShared.xNull(t1Row.getSharedModifiedBy());
voucherCategory = t1Row.getSharedCancelledBy();
```

`txtHIssueTo.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHIssueTo(txtHVoucherType.getTag(), helper, blnToEndOfFile, new AsyncCallback<D
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHIssueTo).show();
public void onSuccess(DmResultPageing<List<TxtHIssueTo_Mdl>> result) {
if (result.isStatus()) {
txtHIssueTo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHIssueTo).show();
```

`txtHIssueTo.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHIssueTo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHIssueTo).show();
```

`txtHJobWorkChallan.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHJobWorkChallan(topControl.getMode().equalsIgnoreCase("New") ? "" : topControl
helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHJobWorkChallan_Mdl>>>() {
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHJobWorkChallan).show();
public void onSuccess(DmResultPageing<List<TxtHJobWorkChallan_Mdl>> result) {
if (result.isStatus()) {
txtHJobWorkChallan.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHJobWorkChallan).show();
```

`txtHJobWorkChallan.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHJobWorkChallan.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHJobWorkChallan).show();
```

`txtHJobWorkChallan.addTextHelpCallBack` → `getSelectedRow()`
```java
boolean flagChanged = !DmShared.xNull(txtHJobWorkChallan.getTag()).equalsIgnoreCase(DmShared.xNull(txtHJobWorkChallan.ge
if (flagChanged) {
txtHJobWorkChallan.setTag2nd(txtHJobWorkChallan.getTag());
if (blnStatus && t1Row != null && flagChanged) {
```

`reelSearchPopup.addCallBack` → `ok()`
```java
if (svm != null && svm.getRbDetail_Mdls() != null && svm.getRbDetail_Mdls().size() > 0) {
onAddReelCallback(svm);
```

`displayStrip.addCallBack` → `getDisplayValue()`
```java
TreeMap<String, DisplayStrip_Mdl> tmDisplayStrip = getDisplayStripMap();
TreeMap<String, String> tmResultDisplayStrip = new TreeMap<String, String>(DmComprator.CASE_INSENSITIVE_ORDER);
if (tmDisplayStrip != null && displayStrip != null) {
for (String key : displayStrip.keySet()) {
DisplayStrip_Mdl strip_Mdl = tmDisplayStrip.get(key);
if (DmShared.vNull(strip_Mdl.getValue()).doubleValue() > 0) {
if (DmShared.xNull(DSK_PACK_COUNT).equalsIgnoreCase(key)) {
tmResultDisplayStrip.put(key, "Reel/Bundle: " + strip_Mdl.getValue() + " " + strip_Mdl.getUnit());
} else if (DmShared.xNull(DSK_QTY).equalsIgnoreCase(key)) {
tmResultDisplayStrip.put(key, "Quantity: " + strip_Mdl.getValue() + " " + strip_Mdl.getUnit());
...
```

`displayStrip.addCallBack` → `getTitleBanner()`
```java
return "Total:";
```

`dmFind.addHelpGridCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getFind(topControl.getPermissions(), helper, blnToEndOfFile, new AsyncCallback<DmRes
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

`modifyReason.addCallBack` → `onOk()`
```java
try {
reasonInfo_Mdl = modifyReason;
if (topControl.getMode().equalsIgnoreCase("Browse") && DmShared.iNull(topControl.getDocId()).longValue() > 0) {
topControlDelete(topControl.getDocId());
} else {
topControlSave();
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), (DmTextBox) null).show();
```

**Top control options:** setOtherRequired(false), setRefreshRequired(false), setOtherRequired(true), setDisplay(true)

**Menu items (Other button):** Process Message

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Confirm: "Are you sure? You want to Process Message."
- Alert: "Can be use in Browse Mode only and select a record."
- Alert: "Message is processed successfully!"
- Alert: "Message can be send only in Browse Mode!"

## Files
| file | lines |
|---|---|
| client/ReelSheetIssue.java | 1078 |
| client/ReelSheetIssue_Dsg.java | 466 |
| server/ReelSheetIssue_SrvImpl.java | 1782 |
| shared/DefaultValue_Mdl.java | 94 |
| shared/DisplayStrip_Mdl.java | 70 |
| shared/Find_Mdl.java | 50 |
| shared/GrdItemDetail_Mdl.java | 266 |
| shared/HeaderValue_Mdl.java | 122 |
| shared/ReelSheetIssue_Mdl.java | 225 |
| shared/ReelSheetIssue_Srv.java | 47 |
| shared/ReelSheetIssue_SrvAsync.java | 48 |
| shared/ReelSheetIssue_Vld.java | 21 |
| shared/TxtHIssueTo_Mdl.java | 31 |
| shared/TxtHItemName_Mdl.java | 197 |
| shared/TxtHJobWorkChallan_Mdl.java | 32 |
| shared/TxtHPackSize_Mdl.java | 72 |

## UI labels / columns (client)
`ReelSheet Issue`, `Date`, `Ref. No.`, `Ref. Date.`, `Issue To`, `Entry No.`, `Job Work Challan`, `Remark`

## Models (shared DTOs)
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
| String | skuName |
| String | skuCode |
| String | skuDecimalPlaces |
| String | stock |
| String | isBestBefore |
| String | itemProperty |
| String | purchaseUnitConversionRate |
| String | isubCode |
| String | itemType |
| String | balancingAcCode |
| String | balancingAcName |
| String | filterItemType |
| String | packSizeCode |
| String | packSizeName |
| String | packUnitCode |
| String | packUnitName |
| String | packUnitConversionRate |
| String | packSizeSku |
| String | packUnitDecimalPlaces |

### GrdItemDetail_Mdl (extends `FullPaperProperty_Mdl implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | vSNo |
| String soDocId, soRecId, soV_SNo, | soDate |
| String | departmentCode |
| String | departmentName |
| String | hsnCode |
| String | hsnNo |
| String | quantity |
| String | packCount |
| String | totalQuantity |
| String | totalPackCount |
| String | unitCode |
| String | unitName |
| String | skuQty |
| String | skuCode |
| String | skuName |
| String | skuUnitDecimalPlaces |
| String | isubCode |
| String | balancingAc |
| String | productionDocId |
| String | productionV_SNo |
| String | remark |
| String | itemId |
| String | propertyDisplay |
| ItemProperty_Mdl | itemProperty_Mdl |

### TxtHJobWorkChallan_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | date |
| String | type |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | voucherTypeCode |
| String | voucherTypeName |
| String | balancingAc |
| String | balancingAcName |
| String | currentDate |
| String | siteCode |
| boolean | inventoryAccountingActive |
| String | vTypeShortName |
| String | voucherCategory |

### HeaderValue_Mdl
| type | field |
|---|---|
| String | docId |
| String | v_Type |
| String | vCat |
| String | v_Date |
| String | partyCode |
| String | consigneeCode |
| String | deliveryToCode |
| String | deliveryCityCode |
| String | itemNature |
| String | departmentCode |
| String | transferTypeCode |
| String | directSupply |

### Find_Mdl (extends `KeyField implements Serializable,IsSerializable`)
| type | field |
|---|---|
| String | voucherType |
| String | voucherDate |
| String | receivedBy |
| String | department |

### DisplayStrip_Mdl
| type | field |
|---|---|
| String | value |
| String | unit |

### ReelSheetIssue_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| RecId | recIdItemIssue |
| String | voucherTypeCode |
| String | voucherTypeName |
| String | balancingAc |
| String | balancingAcName |
| String | voucherDate |
| String | refDate |
| String | refNo |
| String | remarkHeader |
| String | issueToCode |
| String | issueToName |
| String | lineItemCodesCsv |
| String | requisitionDocId |
| String | requisitionNo |
| String | requisitionDate |
| String | requisitionItemChangeCondition |
| ModifyReason_Mdl | reasonInfo_Mdl |
| boolean | inventoryAccountingActive |
| String | vTypeShortName |
| String | voucherCategory |
| String | wcDocId |
| String | wcRecId |
| List<GrdItemDetail_Mdl> | listItemDetail |

### TxtHIssueTo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | cityName |
| String | manualCode |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator | UserPermission userPermission | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<ReelSheetIssue_Mdl> |
| saveRecord | ReelSheetIssue_Mdl mrTempCurrent, String voucherPrefix | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl reasonInfo_Mdl | DmResult<String> |
| getFind | UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getTxtHVoucher | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHIssueTo | String v_Type, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHIssueTo_Mdl>> |
| getTxtHItemName | String departmentCode, String vCat, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemName_Mdl>> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| sendMessage | String strKey, String vTypeCode | DmResult<String> |
| getItemDetail | String reqDocId, String itemIssueDocId | DmResult<List<GrdItemDetail_Mdl>> |
| getIbCode | ItemProperty_Mdl mdl | DmResult<String> |
| printRecords | String docId | DmResult<String> |
| getTxtHPackSize | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHPackSize_Mdl>> |
| getTxtBalancingAc | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| fillGridViaReelBundleDetail | ReelSheetSearchPopupFilter_Mdl svm | DmResult<List<GrdItemDetail_Mdl>> |
| getTxtHJobWorkChallan | String docId, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHJobWorkChallan_Mdl>> |

## Validator `ReelSheetIssue_Vld`
```java
public class ReelSheetIssue_Vld {
    public static void chkSave(ReelSheetIssue_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getVoucherTypeCode(), "Voucher Type", 100);
        DmShared.isValidText(mrTemp.getVoucherDate(), "Voucher Date", 200);
    }
    public static String getApplicableProperties(String propertyList, String itemTypeManualCode) {
        String sortedPropertiesList = ItemPropertyPopup_Vld.getSortedPropertiesCsv(propertyList);
        String ApplicableProperties = "";
        ApplicableProperties = ItemPropertyPopup_Vld.getApplicableProperties(sortedPropertiesList, false, itemTypeManualCode);
        return ApplicableProperties;
    }
}
```

## Server `ReelSheetIssue_SrvImpl.java` (1782 lines)
Helpers used: `DmBaseServer.getSsnVoucherPrefix`, `DmServer.checkCurrentSiteRecord`, `DmServer.checkFinancialYearDate`, `DmServer.checkForwardVoucherDateTime`, `DmServer.checkTransactionLockDate`, `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getLocalIPAddresses`, `DmServer.getRecId`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnPLTCode`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserViewCondition`, `DmServer.getUserWithDateTime`, `DmServer.isDocumentEMailRequired`, `DmServer.sendMail`, `DmServer.setSsnOldData`, `DmServer.showJasper`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.foriegnKey`, `DmShared.getNumberFormat`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(UserPermission userPermission)`

- SQL 1: `select sh.docId from stockHead as sh left join voucher_Type as vt on sh.v_Type = vt.v_Type where vt.category IN ( {…} ) and sh.site_Code = :site_Code and sh.v_prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getMoveRec`
`public DmResult<ReelSheetIssue_Mdl> getMoveRec(String strKey)`

- SQL 1: `select sh.docId, sh.recId, sh.v_No, sh.recIdPrefix, sh.v_Date, sh.u_Name, sh.preparedBy, sh.v_Type, vt.description,vt.short_Name , sh.refNo,sh.refDate,sh.remark , sh.preparedDt, sh.u_EntDt, sh.balancingAc,sgb.name as balancingAcName , sh.preparedByIP, vt.category AS voucherCategory, sh.wcDocId, jwc.recId AS wcRecId from stockHead as sh left join voucher_Type as vt on sh.v_Type = vt.v_Type left join subGroup as sgb on sh.balancingAc = sgb.subCode left join jobworkchallan as jwc on jwc.docId = sh.wcDocId where sh.docId = :docId`
- SQL 2: `Select shd.docId, shd.v_SNo, shiid.itemId, bcm.reelBundleNo , shd.subCode AS issueToCode, sgit.name AS issueToName , shd.departmentCode, sgd.name as departmentName , shd.ibCode, im.sku as skuCode, ums.unit as skuName, ums.decimalPlaces AS skuDecimalPlaces , shd.unitCode, um.unit AS unitName , um.code AS purchaseUnitCode , um.unit AS purchaseUnitName , um.decimalPlaces AS purchaseUnitDecimalPlaces , shd.packCount, shd.quantity, shd.soDocId, shd.soV_SNo, so.recId AS soRecId from stockHeadDetail shd LEFT JOIN stockHeadItemId shiid ON shiid.docId = shd.docId AND shiid.v_SNo = shd.v_SNo LEFT JOIN barCodeMast bcm ON bcm.barCodeId = shiid.itemId LEFT JOIN sOrderDetail sod ON sod.docId = shd.soDocId AND sod.v_SNo = shd.soV_SNo LEFT JOIN sOrder so ON so.docId = shd.soDocId left join itemMast as im on shd.itemCode = im.code left join unitMast as ums on ums.code = im.sku LEFT JOIN unitMast um ON um.code = shd.unitCode LEFT JOIN subGroup sgd ON sgd.subCode = shd.departmentCode LEFT JOIN subgroup sgit ON sgit.subCode = shd.subCode where shd.docId = :docId order by shd.v_SNo`
- Params: `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`
- Other calls: `ItemBatch_Server.fillFullPaperProperties`

### `deleteRecord`
`public DmResult<String> deleteRecord(String keyCode, ModifyReason_Mdl reasonInfo_Mdl)`

- SQL 1: `SELECT v_Date from stockHead where docid = :docid`
- SQL 2: `Delete From stockHeadItemId Where docId= :docId`
- SQL 3: `Delete From stockHeadDetail Where docId= :docId`
- SQL 4: `Delete From stockHead Where docId= :docId`
- Params: `:docid` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`
- Other calls: `AccountPosting_Server.deleteAccountPosting`, `ModifyReason_Server.saveNew`, `Stock_Server.deleteStock`

### `saveRecord`
`public DmResult<String> saveRecord(ReelSheetIssue_Mdl mrTemp, String voucherPrefix)`

- SQL 1: `Insert Into stockHead (docId, v_Type, v_No, v_Date, v_Prefix, recId,refNo,refDate,remark, balancingAc, site_Code, transferDt, preparedBy, preparedDt ,recIdPrefix,preparedByIP, wcDocId) values (:docId, :v_Type, :v_No, :v_Date, :v_Prefix, :recId,:refNo,:refDate,:remark, :balancingAc, :site_Code, :transferDt, :preparedBy, :preparedDt,:recIdPrefix,:preparedByIP, :wcDocId)`
- SQL 2: `update stockHead Set v_Date = :v_Date, balancingAc = :balancingAc, transferDt = :transferDt, u_Name = :u_Name, refNo = :refNo, refDate = :refDate, remark = :remark, wcDocId = :wcDocId, u_EntDt = :uEntDt Where docId = :docId`
- SQL 3: `Delete From stockHeadItemId Where docId= :docId`
- SQL 4: `Delete From stockHeadDetail Where docId= :docId`
- SQL 5: `insert into stockHeadDetail(docId, v_SNo, departmentCode, itemCode, quantity, remark,subCode,site_Code, ibCode, oh_Ac_Item, oh_Ac_Balancing,packSizeCode,packCount, soDocId, soV_SNo, itemId ) values (:docId, :v_SNo, :departmentCode, :itemCode, :quantity, :remark,:subCode,:site_Code, :ibCode, :oh_Ac_Item, :oh_Ac_Balancing,:packSizeCode,:packCount, :soDocId, :soV_SNo, :itemId)`
- SQL 6: `INSERT INTO stockHeadItemId (docId, v_SNo, sno, itemId) VALUES (:docId, :v_SNo, :sno, :itemId)`
- Params: `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:recId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:v_Type` ← `DmShared.foriegnKey(mrTemp.getVoucherTypeCode())`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:v_Prefix` ← `voucherPrefix`; `:v_No` ← `DmShared.iNull(mrTemp.getRecIdItemIssue().getCounter()).toString()`; `:recIdPrefix` ← `DmShared.xNull(mrTemp.getRecIdItemIssue().getPrefix())`; `:balancingAc` ← `DmShared.foriegnKey(mrTemp.getBalancingAc())`; `:wcDocId` ← `DmShared.foriegnKey(mrTemp.getWcDocId())`; `:site_Code` ← `DmShared.foriegnKey(siteCode)`; `:transferDt` ← `null`; `:preparedBy` ← `userName`; `:refNo` ← `DmShared.xNull(mrTemp.getRefNo())`; `:refDate` ← `DmServer.convertDate(mrTemp.getRefDate())`; `:remark` ← `DmShared.xNull(mrTemp.getRemarkHeader())`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:transferDt` ← `null`; `:uEntDt` ← `timestamp`; `:u_Name` ← `userName`; `:refNo` ← `DmShared.xNull(mrTemp.getRefNo())`; `:refDate` ← `DmServer.convertDate(mrTemp.getRefDate())`; `:remark` ← `DmShared.xNull(mrTemp.getRemarkHeader())`; `:balancingAc` ← `DmShared.foriegnKey(mrTemp.getBalancingAc())`; `:wcDocId` ← `DmShared.foriegnKey(mrTemp.getWcDocId())`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_SNo` ← `DmShared.iNull(detail_Mdl.getvSNo()).toString()`; `:departmentCode` ← `DmShared.foriegnKey(detail_Mdl.getDepartmentCode())`; `:itemCode` ← `DmShared.foriegnKey(detail_Mdl.getItemCode())`; `:quantity` ← `DmServer.vNull(detail_Mdl.getQuantity())`; `:remark` ← `DmShared.xNull(detail_Mdl.getRemark())`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getIssueToCode())`; `:site_Code` ← `DmShared.foriegnKey(siteCode)`; `:oh_Ac_Item` ← `DmShared.foriegnKey(detail_Mdl.getIsubCode())`; `:ibCode` ← `DmShared.foriegnKey(detail_Mdl.getIbCode())`; `:oh_Ac_Balancing` ← `DmShared.foriegnKey(detail_Mdl.getBalancingAc())`; `:soDocId` ← `DmShared.foriegnKey(detail_Mdl.getSoDocId())`; `:soV_SNo` ← `DmShared.foriegnKey(detail_Mdl.getSoV_SNo())`; `:itemId` ← `DmShared.foriegnKey(detail_Mdl.getSharedKeyFieldCode())`; `:packSizeCode` ← `null`; `:packCount` ← `DmServer.vNull(detail_Mdl.getPackCount())`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_SNo` ← `DmShared.iNull(detail_Mdl.getvSNo()).toString()`; `:sno` ← `DmShared.iNull(detail_Mdl.getvSNo()).toString()`; `:itemId` ← `DmShared.foriegnKey(detail_Mdl.getSharedKeyFieldCode())`
- Errors: "Invalid key."
- Other calls: `AccountPosting_Server.saveAccountPosting`, `ModifyReason_Server.saveNew`, `ReelSheetIssue_Vld.chkSave`, `STAccountPosting.iniItemIssue_AcPostList`, `STServer.checkItemsRestrictedDepartment`, `Stock_Server.deleteStock`, `Stock_Server.saveStock`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select sh.docId, sh.recId, sh.v_Date, sgd.name as departmentName, sgrb.name receivedByName, vt.short_Name,sh.v_No from stockHead as sh left join voucher_Type as vt on sh.v_Type = vt.v_Type left join ( select docId, max(departmentCode) as departmentCode, max(subCOde) as subCOde from stockHeadDetail group by docId ) as st on sh.docId = st.docId left join subGroup as sgd on st.departmentCode = sgd.subCode left join subGroup as sgrb on st.subCode = sgrb.subCode where vt.category IN( {…} ) and sh.site_Code = :site_Code and sh.v_prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getTxtHVoucher`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVoucher(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select vt.v_Type as code, vt.description as name, vt.balancingAc , vt.short_Name, vt.category, sg.name as balancingAcName from voucher_Type vt left join subGroup sg on sg.subCode = vt.balancingAc where vt.category = ' {…} ' and vt.isActive = 1`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`

- SQL 1: `select vt.v_Type as code, vt.description as name , vt.balancingAc, vt.short_Name, vt.category , sgb.name as balancingAcName from voucher_Type vt left join subGroup sgb on sgb.subCode = vt.balancingAc where vt.category = ' {…} ' and vt.isActive = 1`
- SQL 2: `SELECT ec.isInventoryAccountingActive FROM enviroCompany ec`
- Errors: "Inventory Accounting Active is not defined in Environment Setting (Company)."

### `getTxtHItemName`
`public DmResultPageing<List<TxtHItemName_Mdl>> getTxtHItemName(String departmentCode, String vCategory, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code, im.name, im.manualCode, im.sku AS skuCode , um.unit AS skuName, iit.manualCode as itemType ,im.propertyList, im.isBestBefore, um.decimalPlaces, im.purchaseUnitConversionRate, im.subCode AS iSubCode, im.consumptionAc,sgb.name as consumptionAcName , fit.manualCode as filterItemType , im.packSizeCode , psm.name as packSizeName , psm.sku as packSizeSku , psm.packUnitCode , psm.packUnitConversionRate , umPsm.unit as packUnitName , umPsm.decimalPlaces as packUnitDecimalPlaces , umPsmSku.unit as packSizeSkuName {…} FROM itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN itemType iit ON iit.code = im.itemType LEFT JOIN unitmast um ON im.sku = um.code LEFT JOIN subGroup sgb ON sgb.subCode = im.consumptionAc LEFT JOIN packSizeMast psm on psm.code = im.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku LEFT JOIN itemType fit ON fit.code = im.filterItemType {…} WHERE im.isActive = 1 AND sl.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:departmentCode` ← `DmShared.iNull(departmentCode).toString()`; `:SV` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SV", "manualCode", "itemType", "code", cnTem`
- Other calls: `ALLOWED.getCode`, `RESTRICTED.getCode`

### `getTxtHIssueTo`
`public DmResultPageing<List<TxtHIssueTo_Mdl>> getTxtHIssueTo(String v_Type, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name , sg.manualCode , c.cityName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN (SELECT acf.Nature FROM AcFilteration acf WHERE acf.v_Type = :v_Type) AND sg.isActive = 1 AND sgsl.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Type` ← `DmShared.iNull(v_Type).toString()`

### `getItemDetail`
`public DmResult<List<GrdItemDetail_Mdl>> getItemDetail(String reqDocId, String itemIssueDocId)`


### `manageRequisitionStatus`
`private void manageRequisitionStatus(String docId, boolean isDelete, Connection cnTemp) throws Exception`

- SQL 1: `select req.docId as rqDocId, reqd.v_SNo AS rqV_SNo, mbd.Qty as issueQty, reqd.reqQty as reqQty, reqd.issueQty AS req_IssuedQty, reqd.isStatus, req.approvedBy, req.recId as reqNo, vt.short_name as reqType, reqd.issuePackCount AS req_IssuedPackCount, reqd.packCount AS reqPackCount, mbd.packCount as issuePackCount from requisitionDetail reqd left join requisition req on req.docId = reqd.docId left join voucher_type vt on vt.v_type = req.v_Type inner join ( select mbd.requisitionDocId , mbd.requisitionV_SNo , sum(mbd.quantity) as Qty , SUM(mbd.packCount) AS packCount from stockHeadDetail mbd where mbd.docId = :docId group by mbd.requisitionDocId, mbd.requisitionV_SNo ) mbd on reqd.docId = mbd.requisitionDocId and reqd.v_SNo = mbd.requisitionV_SNo`
- SQL 2: `update requisitionDetail set isStatus = :isStatus, issueQty = :issueQty, issuePackCount = :issuePackCount where docId = :docId and v_SNo = :v_SNo`
- Params: `:docId` ← `DmShared.iNull(docId).toString()`; `:docId` ← `DmShared.iNull(rsTemp.getString("rqDocId")).toString()`; `:v_SNo` ← `DmShared.iNull(rsTemp.getString("rqV_SNo")).toString()`; `:isStatus` ← `DmShared.iNull(isStatus).toString()`; `:issueQty` ← `DmServer.vNull(req_IssuedQty)`; `:issuePackCount` ← `DmServer.vNull(req_IssuedPackCount)`; `:uEntDt` ← `DmServer.getServerTimestamp()`

### `getIbCode`
`public DmResult<String> getIbCode(ItemProperty_Mdl mdl)`

- Other calls: `ItemBatch_Server.createItemBatch`

### `printRecords`
`public DmResult<String> printRecords(String docId)`

- SQL 1: `SELECT sh.recId as issueNo, sh.v_Date as issueDate, vt.short_Name as type, (CASE WHEN isNull(sg.name, '') <> '' THEN concat(sg.Initial, '', sg.name) ELSE sg.name END) as issueTo, sg.phone as phoneNo, sg.mobile, sgd.name as department, sh.preparedBy, im.name as itemName, shd.quantity, um.unit as skuName, rq.recId as requisitionNo, sh.refNo, sh.refDate , shd.remark, sh.remark as remarkHeader, ib.batchNo, ib.lotNo, ib.mrp, ib.manufacturingDate, ib.expiryDate, icm.name as colourName, imm.CompanyName as itemMakeName, ism.name as sizeName , ib.propertyDispCsv , psm.name as packSizeName , shd.packCount , umps.unit as packUnit ,sh.wcDocId, jwc.recId AS jwcNo, jwc.v_Date AS jwcDate , jwc.partyCode AS partyCode, sgjwc.name AS partyName, sgjwc.cityCode, ct.cityName ,bcm.barCode,bcm.barCodeId,bcm.reelBundleNo FROM stockHead AS sh LEFT JOIN voucher_Type AS vt ON sh.v_Type = vt.v_Type left join stockHeadDetail as shd on shd.docId = sh.docId LEFT JOIN stockHeadItemId shiid ON shiid.docId = shd.docId AND shiid.v_SNo = shd.v_SNo LEFT JOIN barCodeMast bcm ON bcm.barCodeId = shiid.itemId LEFT JOIN jobWorkChallanDetail AS jwcd ON jwcd.docId = sh.wcDocId AND jwcd.v_SNo = shd.v_SNo LEFT JOIN jobWorkChallan AS jwc ON jwc.docId = sh.wcDocId LEFT JOIN subgroup as sg ON shd.subCode = sg.subCode LEFT JOIN departmentMast as dm ON dm.subCode = shd.departmentCode left join subGroup as sgd on dm.subCode = sgd.subCode LEFT JOIN subGroup AS sgjwc ON sgjwc.subCode = jwc.partyCode LEFT JOIN city AS ct ON ct.cityCode = sgjwc.cityCode left join subGroupNature sgnd on sgnd.code = sgd.nature LEFT JOIN itemMast im ON im.code = shd.itemCode LEFT JOIN unitMast um ON um.code = im.sku LEFT JOIN requisition rq ON rq.docId = shd.requisitionDocId left join packSizeMast as psm on psm.code = shd.packSizeCode left join unitMast as umps on psm.packUnitCode = umps.code LEFT JOIN itemBatch ib ON ib.code = shd.ibCode LEFT JOIN itemColourMast icm ON icm.code = ib.colourCode LEFT JOIN itemSizeMast ism ON ism.code = ib.sizeCode LEFT JOIN itemMakeMast as imm ON ib.itemMakeCode = imm.code left join city as c on c.cityCode = sg.cityCode left join stateMast sm on sm.code = c.stateCode Left join country as cntry on cntry.code = c.countryCode WHERE sh.docId =:docId`
- Params: `:docId` ← `DmShared.xNull(docId)`
- Other calls: `STServer.setJasperVariables`

### `getTxtHPackSize`
`public DmResultPageing<List<TxtHPackSize_Mdl>> getTxtHPackSize(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT psm.code, psm.name as packSizeName, psm.packUnitConversionRate, psm.packUnitCode ,um.unit as packUnitName ,um.decimalPlaces as packUnitDecimalPlaces , psm.sku , ums.unit as skuName, ums.decimalPlaces as skuDecimalPlaces FROM packSizeMast as psm LEFT JOIN unitmast AS um ON um.code = psm.packUnitCode LEFT JOIN unitmast as ums on ums.code = psm.sku WHERE psm.isActive=1`

### `getTxtBalancingAc`
`public DmResultPageing<List<KeyFieldExtended>> getTxtBalancingAc(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode, sg.name, sg.manualCode FROM subGroup sg where sg.isActive = 1 {…}`

### `sendMessage`
`public DmResult<String> sendMessage(String strKey, String vTypeCode)`


### `fillGridViaReelBundleDetail`
`public DmResult<List<GrdItemDetail_Mdl>> fillGridViaReelBundleDetail(ReelSheetSearchPopupFilter_Mdl svm)`

- SQL 1: `SELECT sii.docId, sii.v_Sno, sii.sno , iis.itemId, bcm.reelBundleNo, bcm.barcode , iis.soDocId, iis.soV_SNo, so.recId AS soRecId, so.v_Date AS soV_Date , iis.departmentCode, gd.name AS departmentName , iis.creationDocId, iis.creationV_SNo , iis.itemCode, im.name AS itemName, iis.ibCode , im.sku as skuCode, ums.unit as skuName, ums.decimalPlaces AS skuDecimalPlaces , iis.balanceQty AS quantity, iis.balancePcs AS packCount , um.code AS unitCode, um.unit AS unitName FROM itemIdStock iis LEFT JOIN ( SELECT siiRec.* FROM stockItemId siiRec LEFT JOIN orderTransferDetail otd ON otd.barcodeId = siiRec.itemId AND otd.fromDocId = siiRec.docId AND otd.fromV_SNo = siiRec.v_SNo AND otd.fromSNo = siiRec.sNo WHERE ISNULL(siiRec.recQty,0) > 0 AND otd.docId IS NULL ) sii ON sii.itemId = iis.itemId LEFT JOIN barCodeMast bcm ON bcm.barCodeId = iis.itemId LEFT JOIN itemMast im ON im.code = iis.itemCode LEFT JOIN subGroup gd ON gd.subCode = iis.departmentCode LEFT JOIN sOrderDetail sod ON sod.docId = iis.soDocId AND sod.v_SNo = iis.soV_SNo LEFT JOIN sOrder so ON so.docId = sod.docId LEFT JOIN unitMast um ON um.code = IIF(ISNULL(iis.soDocId, '') <> '', sod.unitCode, im.saleUnit) left join unitMast as ums on ums.code = im.sku WHERE 1 = 1 AND ISNULL(iis.balanceQty, 0) > 0 AND ISNULL(sii.recQty, 0) > 0 AND ( 1 = 2 {…} )`
- Other calls: `ItemBatch_Server.fillFullPaperProperties`

### `getTxtHJobWorkChallan`
`public DmResultPageing<List<TxtHJobWorkChallan_Mdl>> getTxtHJobWorkChallan(String docId, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT jwc.docId, jwc.v_Type, vt.short_Name, jwc.v_prefix, jwc.recId, jwc.v_Date AS v_Date FROM jobWorkChallan jwc LEFT JOIN voucher_Type vt ON vt.v_Type = jwc.v_Type LEFT JOIN ( SELECT MAX(wor.docId) AS worDocId, wor.wcDocId FROM stockHead wor WHERE wor.site_Code = :site_Code AND wor.v_Prefix = :v_Prefix GROUP BY wor.wcDocId ) Vwor ON Vwor.wcDocId = jwc.docId WHERE ISNULL(jwc.approvedBy, :blank ) <> :blank AND jwc.site_Code = :site_Code AND jwc.v_Prefix = :v_Prefix AND jwc.jobWorkNature = :jobWorkNature AND (ISNULL(Vwor.wcDocId, '') = '' OR (ISNULL(:docId, '') <> '' AND Vwor.worDocId = :docId)) {…}`
- Params: `:jobWorkNature` ← `String.valueOf(STEnum.JobWorkNature.RETURNABLE.getCode())`; `:blank` ← `""`; `:v_Prefix` ← `DmBaseServer.getSsnVoucherPrefix(this)`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:docId` ← `DmShared.xNull(docId)`
- Other calls: `RETURNABLE.getCode`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.server`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.accountPosting.server`, `dataman.dmToolkit.dmDisplayStrip.client`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.documentSystemReport.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.server`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.stock.server`, `dataman.dmToolkit.stock.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.itemBatch.server`, `paperbiz.selfToolkit.itemBatch.shared`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.reelSheetSearchPopup.client`, `paperbiz.selfToolkit.reelSheetSearchPopup.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`, `paperbiz.store.reelSheetIssue.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| getDefaultValues | `if (rsTemp.getString("isInventoryAccountingActive") == null)` | "Inventory Accounting Active is not defined in Environment Setting (Company)." |
| manageRequisitionStatus | `if (DmShared.xNull(rsTemp.getString("approvedBy")).trim().equalsIgnoreCase(""))` | "rqDocId" |
| manageRequisitionStatus | `if (isStatus.intValue() == 1)` | "rqDocId" |
| manageRequisitionStatus | `if (req_IssuedQty > reqQty)` | "rqDocId" |
| manageRequisitionStatus | `} else if (req_IssuedQty < 0)` | "rqDocId" |

## Notes (hand-written)
_No hand-written notes yet._
