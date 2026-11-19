# pb-lab / itemWiseQualityParameter

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/lab/src/itemWiseQualityParameter/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: itemImpurityMast, itemImpurityDetail
- Update tables: itemImpurityMast
- Delete cascade (in order): itemImpurityDetail, itemImpurityMast
- Duplicate checks on save:
  - `SELECT COUNT(itemCode) as cnt from itemImpurityMast where itemCode= :itemCode and testTemplateCode= :testTemplateCode and code<> :code`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Item Name (Text); Test Template (Text); Active (Text); Sample Unit (Text); Sample Required Quantity (Text); rule: GridRow; rule: Grid Detail is mandatory.
- Lookups/suggestions: getTxtHItem, getTxtHIsActive, getTxtHTestTemplate, getTxtHSampleUnit, getTxtHCopyFrom
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `CopyFromPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Copy From | DmTextHelp | `txtHCopyFrom` | `TxtHCopyFrom_PrpAcs, 10, false` |
| 2 | Template Name | DmTextBox | `txtTemplateName` | `` |

**Grid / popup columns** (header, width, db field):
- `txtHCopyFrom`: Item Name [Text, w150, itemName]; Template Name [Text, w150, testTemplateName]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHCopyFrom` → `getTxtHCopyFrom`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHCopyFrom.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHCopyFrom(DmShared.xNull(code), helper, blnToEndOfFile, new AsyncCallback<DmRe
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHCopyFrom).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHCopyFrom.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCopyFrom).show();
```

`txtHCopyFrom.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHCopyFrom.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCopyFrom).show();
```

`txtHCopyFrom.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
txtTemplateName.setText(DmShared.xNull(t1Row.getSharedPreparedBy()));
```
- Alert: " Item Selection Is Mandatory ! "

### Screen `ItemWiseQualityParameterGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Range 2 | DmTextBoxNumeric | `txtRange1` | `4, false, 12, true` |
| 2 | - | DmTextBoxNumeric | `txtRange2` | `4, false, 12, true` |
| 3 | Help Source | DmTextBox | `txtHelp` | `250, true` |
| 4 | Standard Value | DmTextBox | `txtStandardValue` | `10, false` |
| 5 | Default Remark | DmRemarkTextBox | `txtRemark` | `250, false` |

Other widgets (not in tab list): `txtQualityParameter` (DmTextBox), `txtUnit` (DmTextBox), `txtRange1Unit` (DmTextBox), `txtRange2Unit` (DmTextBox)
- Alert: "Range 1 is mandatory."
- Alert: "Range 2 is mandatory."
- Alert: "Range 2 should be greater than or equals to Range 1."
- Alert: "Help is mandatory."

### Screen `ItemWiseQualityParameter`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Item Name | DmTextHelp | `txtHItem` | `com.google.gwt.core.shared.TxtHItem_PrpAcs, 7, true` |
| 2 | Test Template | DmTextHelp | `txtHTestTemplate` | `com.google.gwt.core.client.KeyField_PrpAcs, 4, true` |
| 3 | Sample Unit | DmTextHelp | `txtHSampleUnit` | `com.google.gwt.core.client.TxtHUnit_PrpAcs, 3, true` |
| 4 | Sample Required Quantity | DmTextBoxNumeric | `txtSampleReqQty` | `4, false, 12, true` |

Other widgets (not in tab list): `txtHIsActive` (DmTextHelp), `dmFind` (DmFind), `dmGrd` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHTestTemplate`: Test Template [Text, w0, Description]
- `txtHItem`: Item Name [Text, w250, itemName]; Manual Code [Text, w100, manualCode]
- `txtHSampleUnit`: Unit [Text, w0, unit]
- `txtHIsActive`: Active [Text, w0, Name]
- `dmFind`: Item Name [Text, w0, itemName]; Test Template [Text, w100, testTemplateName]; Sample Required [Text, w100, sampleReqQty]; Unit [Text, w60, sampleUnitName]; Active [Text, w80, isActive]
- `dmGrd`: Parameter [Text, w0]; Unit [Text, w80]; Range 1 [Text, w100]; Range 2 [Text, w100]; Help Source [Text, w300]; Standard Value [Text, w120]; Default Remark [Text, w300]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHItem` → `getTxtHItem`
- `txtHTestTemplate` → `getTxtHTestTemplate`
- `txtHSampleUnit` → `getTxtHSampleUnit`
- `txtHIsActive` → `getTxtHIsActive`
- `copyFromPopup` → `getMoveRec`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `ItemCode` ← DmShared.xNull(txtHItem.getTag())
- `ItemName` ← DmShared.xNull(txtHItem.getText())
- `TestTemplateCode` ← DmShared.xNull(txtHTestTemplate.getTag())
- `TestTemplateName` ← DmShared.xNull(txtHTestTemplate.getText())
- `IsActiveTag` ← DmShared.xNull(txtHIsActive.getTag())
- `IsActiveText` ← DmShared.xNull(txtHIsActive.getText())
- `SampleQty` ← DmShared.xNull(txtSampleReqQty.getText())
- `SampleUnitCode` ← DmShared.xNull(txtHSampleUnit.getTag())
- `SampleUnitName` ← DmShared.xNull(txtHSampleUnit.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtHTestTemplate.setTag` ← data.getTestTemplateCode()
- `txtHTestTemplate.setText` ← data.getTestTemplateName()
- `txtHItem.setTag` ← data.getItemCode()
- `txtHItem.setText` ← data.getItemName()
- `txtSampleReqQty.setText` ← data.getSampleQty()
- `txtHSampleUnit.setTag` ← data.getSampleUnitCode()
- `txtHSampleUnit.setTag2nd` ← data.getSampleUnitDecimalPlaces()
- `txtHSampleUnit.setText` ← data.getSampleUnitName()
- `txtHIsActive.setTag` ← data.getIsActiveTag()
- `txtHIsActive.setText` ← data.getIsActiveText()
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtHIsActive.setEnabled(flag)`

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
txtHIsActive.setEnabled(flag);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
onAdd();
```

`topControl.addEditHandeller` → `editClicked()`
```java
manageDisplay(true);
txtHItem.setFocus(true);
```

`topControl.addDeleteHandeller` → `deleteClicked()`
```java
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

`topControl.addSaveHandeller` → `saveClicked()`
```java
if (topControl.getMode().equalsIgnoreCase("Edit")) {
showModifyReason();
} else {
topControlSave();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(ItemWiseQualityParameter.this);
```

`topControl.addRefershHandeller` → `refreshClicked()`
```java
topControlMoveRec(topControl.getDocId());
```

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
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

`txtHItem.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHItem(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHItem_
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItem).show();
public void onSuccess(DmResultPageing<List<TxtHItem_Mdl>> result) {
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

`txtHTestTemplate.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHTestTemplate(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<K
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHTestTemplate).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
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
if (blnStatus && t1Row != null) {
fillImpurityDetail(t1Row.getSharedKeyFieldCode());
```

`txtHSampleUnit.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHSampleUnit(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Txt
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSampleUnit).show();
public void onSuccess(DmResultPageing<List<TxtHUnit_Mdl>> result) {
if (result.isStatus()) {
txtHSampleUnit.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSampleUnit).show();
```

`txtHSampleUnit.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSampleUnit.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSampleUnit).show();
```

`txtHSampleUnit.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && tRow != null) {
txtHSampleUnit.setTag2nd(DmShared.iNull(tRow.getUnitDecimalPlaces()).toString());
```

`txtHIsActive.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHIsActive(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHIsActive).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHIsActive.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHIsActive).show();
```

`txtHIsActive.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHIsActive.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHIsActive).show();
```

`copyFromPopup.addCallBack` → `onOk()`
```java
copyRecord(strKey);
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

`gridEdit.addCallBack` → `onOk()`
```java
dmGrd.commitChanges();
```

`gridEdit.addCallBack` → `onDelete()`
```java
dmGrd.commitChanges();
```

**Top control options:** setOtherRequired(true), setRefreshRequired(false), setDisplay(true)

**Menu items (Other button):** Copy from

_Print permission "P" is removed for this screen (replace "P" with "*")._

**Modify reason popup:** yes (Edit save and Delete).
- Alert: "Please select Item Name"
- Alert: "Please select Test Template Name"

## Files
| file | lines |
|---|---|
| client/CopyFromPopup.java | 151 |
| client/CopyFromPopup_Dsg.java | 159 |
| client/ItemWiseQualityParameter.java | 710 |
| client/ItemWiseQualityParameterGridEdit.java | 178 |
| client/ItemWiseQualityParameterGridEdit_Dsg.java | 227 |
| client/ItemWiseQualityParameter_Dsg.java | 322 |
| server/ItemWiseQualityParameter_SrvImpl.java | 828 |
| shared/Find_Mdl.java | 50 |
| shared/GrdItemWiseQualityParameter_Mdl.java | 104 |
| shared/ItemWiseQualityParameter_Mdl.java | 158 |
| shared/ItemWiseQualityParameter_Srv.java | 34 |
| shared/ItemWiseQualityParameter_SrvAsync.java | 33 |
| shared/ItemWiseQualityParameter_Vld.java | 43 |
| shared/TxtHItemWiseQualityParameter_Mdl.java | 32 |
| shared/TxtHItem_Mdl.java | 23 |
| shared/TxtHUnit_Mdl.java | 23 |

## UI labels / columns (client)
`Parameter`, `Unit`, `Range 1`, `Range 2`, `Help Source`, `Standard Value`, `Default Remark`, `Item Name`, `Test Template`, `Sample Unit`, `Sample Required Quantity`, `Active`, `Copy From`, `Template Name`

## Models (shared DTOs)
### TxtHItemWiseQualityParameter_Mdl (extends `KeyField implements Serializable,IsSerializable`)
| type | field |
|---|---|
| String | departmentCode |
| String | departmentName |

### ItemWiseQualityParameter_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | itemCode |
| String | itemName |
| String | testTemplateCode |
| String | testTemplateName |
| String | isActiveTag |
| String | isActiveText |
| String | modifiedBy |
| String | preparedBy |
| String | userEntDtLatestLine |
| String | vNo |
| String | sampleQty |
| String | sampleUnitCode |
| String | sampleUnitName |
| String | sampleUnitDecimalPlaces |
| ModifyReason_Mdl | reasonInfo_Mdl |
| List<GrdItemWiseQualityParameter_Mdl> | grdData |

### TxtHItem_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |

### TxtHUnit_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | unitDecimalPlaces |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | testTemplateName |
| String | active |
| String | sampleReqQty |
| String | sampleUnitName |

### GrdItemWiseQualityParameter_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | vSNo |
| String | range1 |
| String | range2 |
| String | unitCode |
| String | unitName |
| String | decimalPlaces |
| String | helpSource |
| String | defaultRemark |
| String | valueType |
| String | standardValue |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator | UserPermission userPermission | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<ItemWiseQualityParameter_Mdl> |
| saveRecord | ItemWiseQualityParameter_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey,ModifyReason_Mdl reasonInfo_Mdl | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getTxtHItem | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getTxtHTestTemplate | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSampleUnit | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHUnit_Mdl>> |
| getTxtHIsActive | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getImpurityDetail | String testTemplateCode | DmResult<List<GrdItemWiseQualityParameter_Mdl>> |
| getTxtHCopyFrom | String docid, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `ItemWiseQualityParameter_Vld`
```java
public class ItemWiseQualityParameter_Vld {
    public static void chkSave(ItemWiseQualityParameter_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getItemCode(), "Item Name", 100);
        DmShared.isValidText(mrTemp.getTestTemplateCode(), "Test Template", 200);
        DmShared.isValidText(mrTemp.getIsActiveTag(), "Active", 300);
        DmShared.isValidText(mrTemp.getSampleUnitCode(), "Sample Unit", 500);
        DmShared.isValidText(mrTemp.getSampleQty(), "Sample Required Quantity", 600);
        boolean flag = false;
        List<GrdItemWiseQualityParameter_Mdl> listItemDetail = mrTemp.getGrdData();
        for (GrdItemWiseQualityParameter_Mdl detail_Mdl : listItemDetail) {
            if (!DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()).trim().equalsIgnoreCase("")) {
                flag = true;
                if (DmShared.xNull(detail_Mdl.getValueType()).equalsIgnoreCase(DmShared.iNull(STEnum.ImpurityValueType.RANGE.getId()).toString())) {
                    if (DmShared.xNull(detail_Mdl.getRange1()).trim().equalsIgnoreCase("")) {
                        throw new DmException(400, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter Range 1 in line no." + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                    if (DmShared.xNull(detail_Mdl.getRange1()).trim().equalsIgnoreCase("")) {
                        throw new DmException(400, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter Range 2 in line no." + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                } else if (DmShared.xNull(detail_Mdl.getValueType()).equalsIgnoreCase(DmShared.iNull(STEnum.ImpurityValueType.HELP.getId()).toString())) {
                    if (DmShared.xNull(detail_Mdl.getHelpSource()).trim().equalsIgnoreCase("")) {
                        throw new DmException(400, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter Help in line no." + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
            }
        }
        if (!flag) {
            throw new DmException(400, "Grid Detail is mandatory.");
        }
    }
}
```

## Server `ItemWiseQualityParameter_SrvImpl.java` (828 lines)
Helpers used: `DmServer.chkIfDocumentUsed`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getUserWithDateTime`, `DmServer.setSsnOldData`, `DmServer.vNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(UserPermission userPermission)`

- SQL 1: `SELECT iim.CODE FROM itemImpurityMast as iim left join itemMast as im on im.code= iim.itemCode WHERE iim.testTemplateCode IS NOT NULL ORDER BY im.NAME`

### `getMoveRec`
`public DmResult<ItemWiseQualityParameter_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT iim.code, iim.itemCode,im.name as itemName, iim.testTemplateCode, tmm.description as testTemplateName, iim.isActive, iim.preparedBy,iim.preparedDt, iim.sampleUnit as sampleUnitCode, iim.sampleReqQty,iim.preparedByIP, um.unit as SampleUnitName, um.decimalPlaces as sampleUnitDecimalPlaces, iim.u_Name,iim.u_EntDt from itemImpurityMast iim left join itemMast as im on im.code = iim.itemCode left join testTemplateMast as tmm on tmm.code =iim.testTemplateCode left join unitMast as um on um.code = iim.sampleUnit where iim.code=:code`
- SQL 2: `SELECT iid.sNo,iid.impurityCode, im.name as qualityParameter, iid.range1, iid.range2 , im.unitCode, um.unit, um.decimalPlaces, im.valueType, iid.helpSource, iid.defaultRemark, iid.standardValue from itemImpurityDetail iid left join impurityMast as im on im.code = iid.impurityCode left join unitMast as um on um.code = im.unitCode where iid.code=:code`
- Params: `:code` ← `strKey`; `:code` ← `strKey`
- Other calls: `HELP.getId`, `ObjListTemp.setRange1`, `ObjListTemp.setRange2`, `ObjListTemp.setSharedKeyFieldCode`, `ObjListTemp.setSharedKeyFieldName`, `RANGE.getId`, `REMARK.getId`

### `saveRecord`
`public DmResult<String> saveRecord(ItemWiseQualityParameter_Mdl mrTemp)`

- SQL 1: `SELECT COUNT(itemCode) as cnt from itemImpurityMast where itemCode= :itemCode and testTemplateCode= :testTemplateCode and code<> :code`
- SQL 2: `Insert Into itemImpurityMast(code , itemCode, testTemplateCode, isActive, sampleUnit, sampleReqQty, preparedBy, preparedDt,preparedByIP) Values (:code, :itemCode, :testTemplateCode, :isActive, :sampleUnit, :sampleReqQty, :preparedBy, :preparedDt,:preparedByIP)`
- SQL 3: `Update itemImpurityMast Set itemCode = :itemCode, testTemplateCode = :testTemplateCode, sampleUnit = :sampleUnit, sampleReqQty = :sampleReqQty, isActive = :isActive, U_Name = :u_Name, U_EntDt = :uEntDt Where code = :code`
- SQL 4: `Delete From itemImpurityDetail Where code = :code`
- SQL 5: `INSERT INTO itemImpurityDetail ( code, sNo, impurityCode {…} ) Values ( :code, :sNo, :impurityCode {…} )`
- Params: `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:itemCode` ← `mrTemp.getItemCode()`; `:testTemplateCode` ← `mrTemp.getTestTemplateCode()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:itemCode` ← `DmShared.foriegnKey(mrTemp.getItemCode())`; `:testTemplateCode` ← `DmShared.foriegnKey(mrTemp.getTestTemplateCode())`; `:sampleUnit` ← `DmShared.xNull(mrTemp.getSampleUnitCode())`; `:sampleReqQty` ← `DmShared.xNull(mrTemp.getSampleQty())`; `:isActive` ← `DmShared.xNull(mrTemp.getIsActiveTag())`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:itemCode` ← `DmShared.foriegnKey(mrTemp.getItemCode())`; `:testTemplateCode` ← `DmShared.foriegnKey(mrTemp.getTestTemplateCode())`; `:sampleUnit` ← `DmShared.xNull(mrTemp.getSampleUnitCode())`; `:sampleReqQty` ← `DmShared.xNull(mrTemp.getSampleQty())`; `:isActive` ← `DmShared.xNull(mrTemp.getIsActiveTag())`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:uEntDt` ← `timestamp`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:range1` ← `DmServer.vNull(detail_Mdl.getRange1())`; `:range2` ← `DmServer.vNull(detail_Mdl.getRange2())`; `:helpSource` ← `DmShared.xNull(detail_Mdl.getHelpSource())`; `:defaultRemark` ← `DmShared.xNull(detail_Mdl.getDefaultRemark())`; `:standardValue` ← `DmShared.xNull(detail_Mdl.getStandardValue())`; `:code` ← `DmShared.foriegnKey(mrTemp.getSharedKeyFieldCode())`; `:sNo` ← `DmShared.iNull(detail_Mdl.getvSNo()).toString()`; `:impurityCode` ← `DmShared.foriegnKey(detail_Mdl.getSharedKeyFieldCode())`
- Errors: "Invalid key." | "Item with same test method already exist."
- Other calls: `HELP.getId`, `ItemWiseQualityParameter_Vld.chkSave`, `ModifyReason_Server.saveNew`, `RANGE.getId`, `REMARK.getId`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl reasonInfo_Mdl)`

- SQL 1: `Delete From itemImpurityDetail Where code= :code`
- SQL 2: `Delete From itemImpurityMast Where code=:code`
- Params: `:code` ← `strKey`; `:code` ← `strKey`
- Other calls: `ModifyReason_Server.saveNew`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT iim.code, im.name as itemName, tmm.description as testTemplateName, iim.sampleReqQty, iim.sampleUnit, um.unit as sampleUnitName, iim.isActive FROM itemImpurityMast iim left join itemMast as im on im.code= iim.itemCode left join unitMast as um on um.code= iim.sampleUnit left join testTemplateMast as tmm on tmm.code= iim.testTemplateCode WHERE iim.testTemplateCode IS NOT NULL`

### `getTxtHItem`
`public DmResultPageing<List<TxtHItem_Mdl>> getTxtHItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code,im.name as itemName, im.manualCode FROM itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code WHERE im.isActive = 1 AND (im.filterItemType <> :filterItemType OR im.isCommon = 1) AND sl.site_Code = :site_Code`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("OI", "manualCode", "itemType", "code", cnTem`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHIsActive`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHIsActive(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHTestTemplate`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTestTemplate(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code, description from testTemplateMast where isActive = 1`

### `getTxtHSampleUnit`
`public DmResultPageing<List<TxtHUnit_Mdl>> getTxtHSampleUnit(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code, unit, decimalPlaces from unitMast`

### `getTxtHCopyFrom`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCopyFrom(String code, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT iim.code, iim.itemCode,im.name as itemName, iim.testTemplateCode, tmm.description as testTemplateName from itemImpurityMast iim left join itemMast as im on im.code = iim.itemCode left join testTemplateMast as tmm on tmm.code =iim.testTemplateCode where iim.code <> :code AND iim.testTemplateCode IS NOT NULL`
- Params: `:code` ← `DmShared.iNull(code).longValue()`

### `getImpurityDetail`
`public DmResult<List<GrdItemWiseQualityParameter_Mdl>> getImpurityDetail(String testTemplateCode)`

- SQL 1: `select tmd.impurityCode,im.name as impurityName, im.unitCode, im.valueType, um.unit, um.decimalPlaces from testTemplateDetail tmd left join impurityMast im on im.code = tmd.impurityCode left join unitMast as um on um.code = im.unitCode where tmd.code = :testTemplateCode and im.nature=:nature`
- Params: `:testTemplateCode` ← `testTemplateCode`; `:nature` ← `DmShared.iNull(STEnum.ImpurityNature.LAB.getId()).toString()`
- Other calls: `LAB.getId`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.server`, `dataman.dmToolkit.modifyReason.shared`, `paperbiz.lab.itemWiseQualityParameter.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Item with same test method already exist." |

## Notes (hand-written)
_No hand-written notes yet._
