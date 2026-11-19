# pb-lab / sampleEntry

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/lab/src/sampleEntry/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: labSample
- Update tables: labSample
- Delete cascade (in order): labSample
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Sample Entry (Text); Date (Text); Time (Text); Refrence (Text); Item (Text); Test Template (Text); rule: Quantity is mandatory.
- Lookups/suggestions: getTxtHSampleEntryType, getTxtHRefrence, getTxtHItem, getTxtHRefrenceNo, getTxtHTestTemplate
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `SampleEntry`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Sample Type | DmTextHelp | `txtHSampleEntryType` | `KeyField_PrpAcs, 4, true` |
| 2 | Date & Time | DmTextBox | `txtDate` | `12, true` |
| 3 | - | DmTextBoxTime | `txtTime` | `true` |
| 4 | Sample No. | DmTextBox | `txtEntryNo` | `17, false` |
| 5 | Refrence Type | DmTextHelp | `txtHRefrence` | `KeyField_PrpAcs, 5, true` |
| 6 | Refrence No. | DmTextHelp | `txtHRefrenceNo` | `com.google.gwt.core.shared.GridRefrence_PrpAcs, 6, false` |
| 7 | Reel/Bundle No. | DmTextBox | `txtReelBundleNo` | `10, false` |
| 8 | Item Name | DmTextBox | `txtItemName` | `` |
| 9 | Test Template | DmTextHelp | `txtHTestTemplate` | `com.google.gwt.core.shared.GridTestTemplate_PrpAcs, 6, true` |
| 10 | Quantity | DmTextBoxNumeric | `txtQuantity` | `4, false, 12, true` |

Other widgets (not in tab list): `txtQuantityBox` (DmTextBox), `txtWTACode` (DmTextBox), `dmFind` (DmFind), `dmGrd` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `dmFind`: Sample No. [Text, w120, recId]; Sample Type [Text, w80, short_Name]; Date [Text, w100, v_Date]; Time [Text, w50, v_Time]; Refrence Type [Text, w120, refrenceType]; Refrence No. [Text, w120, refRecId]; Item Name [Text, w0, itemName]; Quantity [Text, w80, sampleQty]; Unit [Text, w60, unit]
- `txtHSampleEntryType`: Type [Text, w100, Description]
- `txtHRefrence`: Description [Text, w200, Description]; Category [Text, w80, Category]
- `txtHRefrenceNo`: Refrence No. [Text, w120, recID]; Date [Text, w80, v_Date]; Item Name [Text, w300, itemName]; Manual Code [Text, w80, manualCode]; ReelBundleNo [Text, w100, reelBundleNo]
- `txtHTestTemplate`: Template name [Text, w150, Description]; Req. Qty. [Text, w100, sampleReqQty]; Unit [Text, w50, sampleUnit]
- `dmGrd`: Parameter [Text, w300]; Unit [Text, w80]; Range 1 [Text, w100]; Range 2 [Text, w100]; Help Source [Text, w300]; Default Remark [Text, w0]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHSampleEntryType` → `getTxtHSampleEntryType`
- `txtHRefrence` → `getTxtHRefrence`
- `txtHRefrenceNo` → `getTxtHRefrenceNo`
- `txtHTestTemplate` → `getTxtHTestTemplate`
- `modifyReason` → `getDefaultDateTime`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSave`):
- `NewVoucherTypeCode` ← txtHSampleEntryType.getTag()
- `NewVoucherTypeName` ← txtHSampleEntryType.getText()
- `NewDate` ← txtDate.getText()

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtEntryNo.getText())
- `U_EntDt_LatestLine` ← DmShared.xNull(txtHSampleEntryType.getTag2nd())
- `EntryTypeCode` ← DmShared.xNull(txtHSampleEntryType.getTag())
- `EntryTypeName` ← DmShared.xNull(txtHSampleEntryType.getText())
- `EntryNo` ← DmShared.xNull(txtEntryNo.getText())
- `EntryDate` ← DmShared.dNull(txtDate.getText())
- `EntryTime` ← DmShared.xNull(txtTime.getText())
- `RefrenceCode` ← DmShared.xNull(txtHRefrence.getTag())
- `RefrenceName` ← DmShared.xNull(txtHRefrence.getText())
- `ItemCode` ← DmShared.xNull(txtItemName.getTag())
- `ItemName` ← DmShared.xNull(txtItemName.getText())
- `ItemId` ← DmShared.xNull(txtReelBundleNo.getTag())
- `ReelBundleNo` ← DmShared.xNull(txtReelBundleNo.getText())
- `RefrenceNODocId` ← DmShared.xNull(txtHRefrenceNo.getTag())
- `RefrenceNORecId` ← DmShared.xNull(txtHRefrenceNo.getText())
- `Quantity` ← DmShared.xNull(txtQuantity.getText())
- `SampleReqQty` ← DmShared.xNull(txtQuantity.getTag())
- `SampleReqQty` ← DmShared.xNull(txtQuantity.getText())
- `SampleUnitCode` ← DmShared.xNull(txtQuantityBox.getTag2nd())
- `TestTemplateCode` ← DmShared.xNull(txtHTestTemplate.getTag())
- `TestTemplateName` ← DmShared.xNull(txtHTestTemplate.getText())
- `ItemImpurityCode` ← DmShared.xNull(txtHTestTemplate.getTag2nd())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `userControl.setTextBlog` ← preparedByBlog, mrTemp.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, mrTemp.getSharedModifiedBy()
- `txtHSampleEntryType.setText` ← mrTemp.getEntryTypeName()
- `txtHSampleEntryType.setTag` ← mrTemp.getEntryTypeCode()
- `txtHSampleEntryType.setTag2nd` ← DmShared.iNull(mrTemp.getU_EntDt_LatestLine()).toString()
- `txtEntryNo.setText` ← mrTemp.getEntryNo()
- `txtEntryNo.setTag` ← DmShared.iNull(mrTemp.getObjRecid().getCounter()).toString()
- `txtEntryNo.setTag2nd` ← DmShared.xNull(mrTemp.getObjRecid().getPrefix())
- `txtDate.setText` ← mrTemp.getEntryDate()
- `txtTime.setText` ← mrTemp.getEntryTime()
- `txtHRefrence.setTag` ← mrTemp.getRefrenceCode()
- `txtHRefrence.setTag2nd` ← mrTemp.getRefrenceCode()
- `txtHRefrence.setText` ← mrTemp.getRefrenceName()
- `txtItemName.setTag` ← mrTemp.getItemCode()
- `txtItemName.setText` ← mrTemp.getItemName()
- `txtReelBundleNo.setTag` ← mrTemp.getItemId()
- `txtReelBundleNo.setText` ← mrTemp.getReelBundleNo()
- `txtHRefrenceNo.setTag` ← mrTemp.getRefrenceNODocId()
- `txtHRefrenceNo.setText` ← mrTemp.getRefrenceNORecId()
- `txtQuantity.setText` ← mrTemp.getQuantity()
- `txtQuantity.setTag` ← mrTemp.getSampleReqQty()
- `txtQuantityBox.setText` ← mrTemp.getSampleUnitName()
- `txtQuantityBox.setTag2nd` ← mrTemp.getSampleUnitCode()
- `txtQuantityBox.setTag` ← mrTemp.getUnitDecimalPlaces()
- `txtHTestTemplate.setTag` ← mrTemp.getTestTemplateCode()
- `txtHTestTemplate.setTag2nd` ← mrTemp.getItemImpurityCode()
- `txtHTestTemplate.setText` ← mrTemp.getTestTemplateName()
- userControl.setTextBlog(preparedByBlog, mrTemp.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, mrTemp.getSharedModifiedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtEntryNo.setEnabled(false)`
- `txtDate.setEnabled(false)`
- `txtTime.setEnabled(false)`
- `txtTime.setEnabled(false)`
- `txtDate.setEnabled(false)`
- `txtTime.setEnabled(true)`
- `txtDate.setEnabled(true)`
- `txtItemName.setEnabled(false)`
- `txtReelBundleNo.setEnabled(false)`
- `txtHRefrenceNo.setEnabled(false)`
- `txtHRefrenceNo.setEnabled(true)`

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
txtEntryNo.setEnabled(false);
txtDate.setEnabled(false);
txtTime.setEnabled(false);
if (topControl.getMode().equalsIgnoreCase("Edit") || topControl.getMode().equalsIgnoreCase("New")) {
if (!dateTimeEditable) {
txtTime.setEnabled(false);
txtDate.setEnabled(false);
} else if (dateTimeEditable) {
txtTime.setEnabled(true);
txtDate.setEnabled(true);
txtHSampleEntryType.setFocus(true);
txtItemName.setEnabled(false);
txtReelBundleNo.setEnabled(false);
if (DmShared.xNull(txtHRefrence.getTag()).equalsIgnoreCase("") || DmShared.xNull(topControl.getMode()).equalsIgnoreCase(
txtHRefrenceNo.setEnabled(false);
} else {
txtHRefrenceNo.setEnabled(true);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
```

`topControl.addNewHandeller` → `newClicked()`
```java
onAdd();
```

`topControl.addEditHandeller` → `editClicked()`
```java
onEdit();
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
topControlMoveRec(strFetchCode);
```

`topControl.addPrintHandeller` → `printClicked()`
```java
showPrintPopup();
```

`topControl.addSaveHandeller` → `saveClicked()`
```java
saveValidation();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(SampleEntry.this);
```

`txtHSampleEntryType.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHSampleEntryType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<Lis
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSampleEntryType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHSampleEntryType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSampleEntryType).show();
```

`txtHSampleEntryType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSampleEntryType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSampleEntryType).show();
```

`txtHSampleEntryType.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
vTypeShortName = t1Row.getSharedModifiedBy();
```

`txtHRefrence.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHRefrence(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHRefrence).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHRefrence.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHRefrence).show();
```

`txtHRefrence.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHRefrence.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHRefrence).show();
```

`txtHRefrence.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && tRow != null) {
if (!DmShared.xNull(txtHRefrence.getTag2nd()).equalsIgnoreCase(DmShared.xNull(txtHRefrence.getTag()))) {
resetRefrence();
txtHRefrence.setTag2nd(DmShared.xNull(txtHRefrence.getTag()));
txtHRefrenceNo.setEnabled(true);
```

`txtHRefrenceNo.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHRefrenceNo(DmShared.xNull(txtHRefrence.getTag()), DmShared.xNull(topControl.g
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHRefrenceNo).show();
public void onSuccess(DmResultPageing<List<TxtHRefrenceNo_Mdl>> result) {
if (result.isStatus()) {
txtHRefrenceNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHRefrenceNo).show();
```

`txtHRefrenceNo.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHRefrenceNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHRefrenceNo).show();
```

`txtHRefrenceNo.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
if (!DmShared.xNull(txtHRefrenceNo.getTag2nd()).equalsIgnoreCase(DmShared.xNull(txtHRefrenceNo.getTag()))) {
txtItemName.setTag("");
txtItemName.setText("");
txtReelBundleNo.setTag("");
txtReelBundleNo.setText("");
txtHTestTemplate.setTag("");
txtHTestTemplate.setText("");
txtQuantity.setText("");
txtQuantityBox.setText("");
...
```

`txtHTestTemplate.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHTestTemplate(txtItemName.getTag(), helper, blnToEndOfFile, new AsyncCallback<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHTestTemplate).show();
public void onSuccess(DmResultPageing<List<TxtHTestTemplate_Mdl>> result) {
if (result.isStatus()) {
txtHTestTemplate.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTestTemplate).show();
```

`txtHTestTemplate.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHTestTemplate.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTestTemplate).show();
```

`txtHTestTemplate.addTextHelpCallBack` → `getSelectedRow()`
```java
txtHTestTemplate.setTag2nd(t1Row.getSharedModifiedBy());
txtQuantity.setText(t1Row.getSampleReqQty());
txtQuantity.setTag(t1Row.getSampleReqQty());
txtQuantityBox.setText(t1Row.getSampleUnit());
txtQuantityBox.setTag2nd(t1Row.getSampleUnitCode());
txtQuantityBox.setTag(t1Row.getDecimalPlaces());
fillGrdItemWiseLabParameter();
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

`modifyReason.addCallBack` → `onOk()`
```java
reasonInfo_Mdl = modifyReason;
if (topControl.getMode().equalsIgnoreCase("Browse") && DmShared.iNull(topControl.getDocId()).longValue() > 0) {
topControlDelete(topControl.getDocId());
} else {
topControlSave();
```

**Top control options:** setOtherRequired(false), setRefreshRequired(false), setOtherRequired(true), setDisplay(true)

**Menu items (Other button):** Process Message

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Confirm: "Are you sure? You want to Process Message."
- Alert: "Can be use in Browse Mode only and select a record."
- Alert: "Message is processed successfully!"
- Alert: "Message can be send only in Browse Mode!"

### Screen `SamplePrintPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | CheckBox | `documentPrint` | `"", HasDirection.Direction.RTL` |
| 2 | - | CheckBox | `barcodePrint` | `"", HasDirection.Direction.RTL` |

## Files
| file | lines |
|---|---|
| client/SampleEntry.java | 1000 |
| client/SampleEntry_Dsg.java | 461 |
| client/SamplePrintPopup.java | 158 |
| client/SamplePrintPopup_Dsg.java | 173 |
| server/SampleEntry_Server.java | 204 |
| server/SampleEntry_SrvImpl.java | 1020 |
| shared/Default_Mdl.java | 87 |
| shared/Find_Mdl.java | 86 |
| shared/GrdItemWiseLabParameter_Mdl.java | 59 |
| shared/SampleEntry_Mdl.java | 297 |
| shared/SampleEntry_Srv.java | 44 |
| shared/SampleEntry_SrvAsync.java | 44 |
| shared/SampleEntry_Vld.java | 23 |
| shared/TxtHItem_Mdl.java | 50 |
| shared/TxtHRefrenceNo_Mdl.java | 109 |
| shared/TxtHTestTemplate_Mdl.java | 50 |

## UI labels / columns (client)
`Sample Type`, `Date & Time`, `Sample No.`, `Refrence Type`, `Refrence No.`, `Reel/Bundle No.`, `Item Name`, `Test Template`, `Quantity`

## Models (shared DTOs)
### TxtHTestTemplate_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | sampleReqQty |
| String | sampleUnit |
| String | sampleUnitCode |
| String | decimalPlaces |

### Default_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | date |
| String | time |
| String | newVoucherTypeCode |
| String | newVoucherTypeName |
| String | newDate |
| boolean | isContinuousNewMode |
| String | vTypeShortName |
| String | newvTypeShortName |

### SampleEntry_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| RecId | objRecid |
| String | u_EntDt_LatestLine |
| String | entryTypeCode |
| String | entryTypeName |
| String | vTypeShortName |
| String | entryNo |
| String | entryDate |
| String | entryTime |
| String | refrenceCode |
| String | refrenceName |
| String | refrenceNODocId |
| String | refrenceNORecId |
| String | refrenceNOV_Sno |
| String | refrenceNOSno |
| String | itemCode |
| String | itemName |
| String | quantity |
| String | sampleReqQty |
| String | sampleUnitCode |
| String | sampleUnitName |
| String | testTemplateCode |
| String | testTemplateName |
| String | unitDecimalPlaces |
| String | itemImpurityCode |
| String | shortName |
| String | isPartial |
| String | totalSampleNo |
| String | itemId |
| String | reelBundleNo |
| List<GrdItemWiseLabParameter_Mdl> | grdData |
| ModifyReason_Mdl | reasonInfo_Mdl |

### TxtHItem_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | skuName |
| String | sku |
| String | decimalPlaces |

### GrdItemWiseLabParameter_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | range1 |
| String | range2 |
| String | unitName |
| String | helpSource |
| String | defaultRemark |

### TxtHRefrenceNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | refrenceV_Sno |
| String | refrenceSno |
| String | refrenceDate |
| String | itemCode |
| String | itemName |
| String | manualCode |
| String | itemId |
| String | reelBundleNo |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | entryTypeName |
| String | entrydate |
| String | entryTime |
| String | sampleQty |
| String | sampleQtyUnit |
| String | refrenceType |
| String | refRecId |
| String | itemName |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator | UserPermission userPermission | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<SampleEntry_Mdl> |
| sendMessage | String strKey, String vTypeCode | DmResult<String> |
| saveRecord | SampleEntry_Mdl mrTemp, String voucherPrefix | DmResult<String> |
| deleteRecord | String strKey,ModifyReason_Mdl reasonInfo_Mdl | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getDefaultValue |  | DmResult<Default_Mdl> |
| getDefaultDateTime |  | DmResult<Default_Mdl> |
| getTxtHSampleEntryType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHRefrence | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItem | boolean flag, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getTxtHRefrenceNo | String vType, String docId, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHRefrenceNo_Mdl>> |
| getTxtHTestTemplate | String itemCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHTestTemplate_Mdl>> |
| fillUnladingDetail | KeyFieldExtended keyFieldExtended | DmResult<SampleEntry_Mdl> |
| getFillGrdItemWiseLabParameter | String itemImpurityCode | DmResult<List<GrdItemWiseLabParameter_Mdl>> |
| printRecords | String docId | DmResult<String> |

## Validator `SampleEntry_Vld`
```java
public class SampleEntry_Vld {
    public static void chkSave(SampleEntry_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getEntryTypeCode().trim(), "Sample Entry", 100);
        DmShared.isValidText(mrTemp.getEntryDate().trim(), "Date", 200);
        DmShared.isValidText(mrTemp.getEntryTime().trim(), "Time", 500);
        if (DmShared.iNull(mrTemp.getIsPartial()).intValue() == 0) {
            DmShared.isValidText(mrTemp.getRefrenceCode().trim(), "Refrence", 300);
            if (DmShared.vNull(mrTemp.getQuantity()).doubleValue() == 0) {
                throw new DmException(400, "Quantity is mandatory.");
            }
            DmShared.isValidText(mrTemp.getItemCode(), "Item", 700);
        }
        DmShared.isValidText(mrTemp.getTestTemplateCode().trim(), "Test Template", 600);
    }
}
```

## Server `SampleEntry_Server.java` (204 lines)
Helpers used: `DmServer.chkIfDocumentUsed`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.formatTimestamp`, `DmServer.getDocId`, `DmServer.getLocalIPAddresses`, `DmServer.getRecId`, `DmServer.getServerTimestamp`, `DmServer.getSsnOldData`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.xNull`

### `saveRecord`
`public static String saveRecord(SampleEntry_Mdl mrTemp, Connection cnTemp, String voucherPrefix, DmRSSPrivate rss, String className ) throws Exception`

- SQL 1: `select iim.code from itemImpurityMast iim where iim.itemCode = :itemCode and iim.testTemplateCode = :testTemplateCode`
- SQL 2: `INSERT INTO labSample (docId, v_Type, v_No, recIdPrefix, recId, v_Prefix, v_Date, v_Time, itemCode, testTemplateCode, refVType, refDocId, refRecId, refV_Sno,sampleReqQty,sampleUnit, sampleQty, preparedBy, preparedDt,preparedByIP, site_Code, u_EntDt_LatestLine, itemImpurityCode, isPartial, itemId) VALUES (:docId, :v_Type, :v_No, :recIdPrefix, :recId, :v_Prefix, :v_Date, :v_Time, :itemCode,:testTemplateCode,:refVType, :refDocId, :refRecId, :refV_Sno, :sampleReqQty, :sampleUnit, :sampleQty,:preparedBy,:preparedDt,:preparedByIP, :site_Code, :uEntDt, :itemImpurityCode, :isPartial, :itemId)`
- SQL 3: `UPDATE labSample SET v_Date = :v_Date , v_Time = :v_Time, itemImpurityCode = :itemImpurityCode , testTemplateCode = :testTemplateCode , refVType = :refVType , itemCode = :itemCode , refDocId = :refDocId , refRecId = :refRecId, refV_Sno = :refV_Sno , sampleUnit = :sampleUnit , sampleQty = :sampleQty , sampleReqQty = :sampleReqQty, isPartial = :isPartial, itemId = :itemId, u_EntDt = :uEntDt, u_EntDt_LatestLine = :uEntDt, u_name = :u_name WHERE docId = :docId ;`
- Params: `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:itemCode` ← `DmShared.iNull(mrTemp.getItemCode()).toString()`; `:testTemplateCode` ← `DmShared.iNull(mrTemp.getTestTemplateCode()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_Type` ← `DmShared.xNull(mrTemp.getEntryTypeCode())`; `:recId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:v_No` ← `DmShared.iNull(mrTemp.getObjRecid().getCounter()).toString()`; `:v_Date` ← `DmServer.convertDate(mrTemp.getEntryDate())`; `:v_Time` ← `DmShared.xNull(mrTemp.getEntryTime())`; `:v_Prefix` ← `voucherPrefix`; `:recIdPrefix` ← `DmShared.xNull(mrTemp.getObjRecid().getPrefix())`; `:itemCode` ← `DmShared.foriegnKey(mrTemp.getItemCode())`; `:itemImpurityCode` ← `DmShared.foriegnKey(mrTemp.getItemImpurityCode())`; `:testTemplateCode` ← `DmShared.foriegnKey(mrTemp.getTestTemplateCode())`; `:refVType` ← `DmShared.foriegnKey(mrTemp.getRefrenceCode())`; `:refDocId` ← `DmShared.foriegnKey(mrTemp.getRefrenceNODocId())`; `:refRecId` ← `DmShared.foriegnKey(mrTemp.getRefrenceNORecId())`; `:refV_Sno` ← `DmShared.foriegnKey(mrTemp.getRefrenceNOV_Sno())`; `:sampleUnit` ← `DmShared.foriegnKey(mrTemp.getSampleUnitCode())`; `:itemId` ← `DmShared.foriegnKey(mrTemp.getItemId())`; `:sampleQty` ← `DmServer.vNull(mrTemp.getQuantity())`; `:sampleReqQty` ← `DmServer.vNull(mrTemp.getSampleReqQty())`; `:isPartial` ← `DmShared.iNull(mrTemp.getIsPartial()).toString()`; `:preparedBy` ← `DmServer.getSsnUserName(rss)`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(rss)`; `:site_code` ← `DmServer.getSsnSiteCode(rss)`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:uEntDt` ← `timestamp`; `:v_Date` ← `DmServer.convertDate(mrTemp.getEntryDate())`; `:v_Time` ← `DmShared.xNull(mrTemp.getEntryTime())`; `:refVType` ← `DmShared.xNull(mrTemp.getRefrenceCode())`; `:itemCode` ← `DmShared.foriegnKey(mrTemp.getItemCode())`; `:refDocId` ← `DmShared.foriegnKey(mrTemp.getRefrenceNODocId())`; `:refRecId` ← `DmShared.foriegnKey(mrTemp.getRefrenceNORecId())`; `:refV_Sno` ← `DmShared.foriegnKey(mrTemp.getRefrenceNOV_Sno())`; `:sampleUnit` ← `DmShared.foriegnKey(mrTemp.getSampleUnitCode())`; `:isPartial` ← `DmShared.iNull(mrTemp.getIsPartial()).toString()`; `:u_name` ← `DmServer.getSsnUserName(rss)`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:sampleQty` ← `DmServer.vNull(mrTemp.getQuantity())`; `:sampleReqQty` ← `DmServer.vNull(mrTemp.getSampleReqQty())`; `:itemImpurityCode` ← `DmShared.foriegnKey(mrTemp.getItemImpurityCode())`; `:testTemplateCode` ← `DmShared.foriegnKey(mrTemp.getTestTemplateCode())`; `:itemId` ← `DmShared.foriegnKey(mrTemp.getItemId())`
- Errors: "Record has been changed, can't update record, refresh record then update" | "Invalid key." | "Item Wise quality parameter not define for this item and test method."
- Other calls: `ModifyReason_Server.saveNew`

## Server `SampleEntry_SrvImpl.java` (1020 lines)
Helpers used: `DmServer.checkForwardVoucherDateTime`, `DmServer.chkIfDocumentUsed`, `DmServer.compileJasper`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getServerDateTime`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnSiteCode`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserViewCondition`, `DmServer.getUserWithDateTime`, `DmServer.isDocumentEMailRequired`, `DmServer.sendMail`, `DmServer.setSsnOldData`, `DmServer.showJasper`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(UserPermission userPermission)`

- SQL 1: `SELECT smp.docid From labSample smp Inner Join voucher_Type vt on vt.v_type = smp.v_type where smp.v_prefix = :vPrefix and vt.category = ' {…} '`
- Params: `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getMoveRec`
`public DmResult<SampleEntry_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT smp.docId, smp.v_Type,vt.short_Name, vt.description, smp.recId, smp.recIdPrefix , smp.v_No, smp.v_Date, smp.v_Time, smp.itemCode,im.name as itemName, smp.refVType , vt1.description as refVTypeName, smp.sampleQty, smp.preparedBy,smp.preparedDt , smp.itemImpurityCode, smp.u_Name, smp.u_EntDt, smp.u_EntDt_LatestLine, smp.testTemplateCode , tmm.Description as testTemplateName, smp.refDocId, smp.refRecId, smp.refV_Sno, um.unit , um.decimalPlaces,smp.sampleUnit, smp.isPartial,smp.preparedByIP , smp.itemId, bcm.reelBundleNo FROM labSample smp LEFT JOIN voucher_type vt ON vt.v_Type = smp.v_Type LEFT JOIN voucher_type vt1 ON vt1.v_Type = smp.refVType LEFT JOIN itemMast im ON im.code = smp.itemCode LEFT JOIN testTemplateMast tmm ON tmm.Code = smp.testTemplateCode LEFT JOIN itemImpurityMast iim ON iim.code = smp.itemImpurityCode lEFT jOIN unitMast um ON um.code = smp.sampleUnit LEFT JOIN barCodeMast bcm ON bcm.barCodeId = smp.itemId WHERE smp.docId = :docId`
- Params: `:docId` ← `strKey`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl reasonInfo_Mdl)`

- SQL 1: `Delete From labSample Where docId = :docId`
- Params: `:docId` ← `strKey`
- Other calls: `ModifyReason_Server.saveNew`

### `saveRecord`
`public DmResult<String> saveRecord(SampleEntry_Mdl mrTemp, String voucherPrefix)`

- Other calls: `SampleEntry_Server.saveRecord`, `SampleEntry_Vld.chkSave`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select smp.docId,smp.recId,vt.short_Name, smp.v_Date, smp.v_Time, vt1.description as refrenceType ,smp.refRecId, smp.sampleQty, um.unit, smp.v_No, im.name as itemName from labSample smp LEFT JOIN voucher_Type vt ON vt.v_Type = smp.v_Type LEFT JOIN voucher_Type vt1 ON vt1.v_Type = smp.refVType LEFT JOIN itemMast im ON im.code = smp.itemCode LEFT JOIN unitMast um ON um.code = im.sku`

### `getDefaultValue`
`public DmResult<Default_Mdl> getDefaultValue()`

- SQL 1: `SELECT V_type, Description,short_Name FROM Voucher_Type VT WHERE Category = ' {…} ' AND VT.isActive = 1`

### `getTxtHSampleEntryType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSampleEntryType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.V_type, vt.Description,vt.short_Name FROM Voucher_Type vt WHERE vt.isActive = 1 and vt.Category = ' {…} '`

### `getTxtHRefrence`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHRefrence(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select lab_SampleRefVType from enviro e where e.site_Code = :site_Code`
- SQL 2: `Select A.Items,VT.Description ,VT.Category From Voucher_TYpe VT Left Join( Select Items from dbo.FA_Split(' {…} ',',') ) A On VT.V_Type=A.Items Where IsNull(A.Items,'')<>''`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHItem`
`public DmResultPageing<List<TxtHItem_Mdl>> getTxtHItem(boolean flag, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select im.code, im.name, im.manualCode, im.sku, um.unit as skuName, IsNull(um.decimalPlaces,0) as decimalPlaces from itemMast as im left join itemSiteList sl on sl.code = im.code left join unitmast as um on im.sku=um.code where im.IsActive = 1 and (im.filterItemType = :filterItemType OR im.isCommon = 1) and sl.site_Code = :site_Code`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`; `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHRefrenceNo`
`public DmResultPageing<List<TxtHRefrenceNo_Mdl>> getTxtHRefrenceNo(String vType, String docId, DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHTestTemplate`
`public DmResultPageing<List<TxtHTestTemplate_Mdl>> getTxtHTestTemplate(String itemCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select tm.Code as code, tm.description, ipm.code as itemImpurityCode ,ipm.sampleReqQty, um.unit as sampleUnit, um.decimalPlaces, ipm.sampleUnit as sampleUnitCode FROM testTemplateMast tm LEFT JOIN itemImpurityMast ipm ON ipm.testTemplateCode = tm.Code LEFT JOIN unitMast um ON um.code = ipm.sampleUnit where ipm.isActive = 1 {…}`
- Params: `:itemCode` ← `DmShared.xNull(itemCode)`

### `getDefaultDateTime`
`public DmResult<Default_Mdl> getDefaultDateTime()`


### `fillUnladingDetail`
`public DmResult<SampleEntry_Mdl> fillUnladingDetail(KeyFieldExtended keyFieldExtended)`

- SQL 1: `select im.name ,im.code as itemCode, ul.docId , ul.recId , vt.short_Name , vt.description, vt.v_Type, uld.v_SNo as refV_Sno from unloading ul left join unloadingDetail uld On uld.docId = ul.docId left join voucher_Type vt ON vt.v_Type = ul.v_Type left join itemMast im On im.code= uld.itemCode where ul.docId=:docId and uld.v_SNo=:v_Sno and uld.itemCode=:itemCode`
- Params: `:docId` ← `keyFieldExtended.getSharedKeyFieldCode()`; `:itemCode` ← `keyFieldExtended.getSharedKeyFieldName()`; `:v_Sno` ← `keyFieldExtended.getSharedPreparedBy()`

### `getFillGrdItemWiseLabParameter`
`private List<GrdItemWiseLabParameter_Mdl> getFillGrdItemWiseLabParameter(String itemImpurityCode, boolean isMoverec, Connection cnTemp) throws Exception`

- SQL 1: `select iid.impurityCode,im.name as impurityName, um.unit, iid.range1, iid.range2, iid.helpSource, iid.defaultRemark FROM itemImpurityDetail iid LEFT JOIN itemImpuritymAST iim ON iim.code = iid.code LEFT JOIN impurityMast im on im.code = iid.impurityCode LEFT JOIN unitMast um ON um.code = im.unitCode WHERE iid.code = :itemImpurityCode {…}`
- Params: `:itemImpurityCode` ← `itemImpurityCode`

### `getFillGrdItemWiseLabParameter`
`public DmResult<List<GrdItemWiseLabParameter_Mdl>> getFillGrdItemWiseLabParameter(String itemImpurityCode)`


### `printRecords`
`public DmResult<String> printRecords(String docId)`

- SQL 1: `SELECT smp.docId, smp.v_Type, vt.short_Name as vTypeName ,vt.description, smp.recId, smp.recIdPrefix, smp.v_No, smp.v_Date, smp.v_Time, smp.itemCode,im.name as itemName, smp.refVType, vt1.short_Name as refVTypeName, smp.sampleQty,iim.sampleUnit,um.unit as sampleUnitName, um.decimalPlaces, smp.preparedBy ,smp.u_Name, smp.u_EntDt, smp.u_EntDt_LatestLine, smp.testTemplateCode ,tmm.Description as testTemplateName, smp.refDocId, smp.refRecId ,iid.impurityCode,ipm.name as impurityName ,umip.unit as impurityUnit ,iid.range1, iid.range2, iid.helpSource, iid.defaultRemark FROM labSample smp LEFT JOIN voucher_type vt ON vt.v_Type = smp.v_Type LEFT JOIN voucher_type vt1 ON vt1.v_Type = smp.refVType LEFT JOIN itemMast im ON im.code = smp.itemCode LEFT JOIN testTemplateMast tmm ON tmm.Code = smp.testTemplateCode LEFT JOIN itemImpurityMast iim ON iim.Code = smp.itemImpurityCode LEFT JOIN unitMast um ON um.code = iim.sampleUnit left join itemImpurityDetail iid on iid.code=smp.itemImpurityCode LEFT JOIN impurityMast ipm on ipm.code = iid.impurityCode LEFT JOIN unitMast umip ON umip.code = ipm.unitCode WHERE smp.docId = :docId AND iim.isActive = 1 order by smp.v_Date, vt.short_Name, smp.v_No, smp.recId`
- Params: `:docId` ← `DmShared.xNull(docId)`
- Other calls: `STServer.setJasperVariables`

### `sendMessage`
`public DmResult<String> sendMessage(String strKey, String vTypeCode)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextBoxTime.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.documentSystemReport.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.server`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.openSourceReport.client`, `paperbiz.lab.sampleEntry.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stStatic.client`, `paperbiz.selfToolkit.stStatic.server`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
