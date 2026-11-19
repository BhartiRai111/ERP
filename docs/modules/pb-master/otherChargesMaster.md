# pb-master / otherChargesMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/master/src/otherChargesMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: itemMast
- Update tables: itemmast
- Delete cascade (in order): itemMast
- Duplicate checks on save:
  - `Select Count(ManualCode) As Cnt From itemMast mm Where mm.ManualCode = :ManualCode And Code <> :Code`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Name (Text); Category (Text); Group (Text); SKU (Text); Active (Text)
- Lookups/suggestions: getSuggestionItem, getTxtHCategory, getTxtHGroup, getHType, getTxtHValuationType, getTxtHVoucherType, getTxtHVoucherGroup
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `OtherChargesMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Name | DmSuggestBox | `txtItemName` | `50, 10, true, "Name", DmEnum.DmDataType.STRING` |
| 2 | SKU | DmTextHelp | `txtHSKU` | `SKU_PrpAcs, 4, true` |
| 3 | Manual Code | DmTextBox | `txtManualCode` | `20, false` |
| 4 | Unique ID | DmTextBox | `txtUniqueId` | `4, false` |
| 5 | Category | DmTextHelp | `txtHCategory` | `TxtHCategory_PrpAcs, 6, true` |
| 6 | Group Nature | DmTextHelp | `txtHItemType` | `KeyField_PrpAcs, 3, true` |
| 7 | Group | DmTextHelp | `txtHGroup` | `KeyField_PrpAcs, 4, true` |
| 8 | Valuation Type | DmTextHelp | `txtHValuationType` | `KeyField_PrpAcs, 4, true, false` |
| 9 | Active | DmTextHelp | `txtHActive` | `KeyField_PrpAcs, 4, true, true` |
| 10 | Discription | DmRemarkTextBox | `txtItemDesc` | `250, false` |

Other widgets (not in tab list): `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `txtHSKU`: Name [Text, w0, unit]
- `txtHCategory`: Name [Text, w1, categoryName]
- `txtHGroup`: Name [Text, w0, Name]
- `txtHActive`: Name [Text, w0]
- `txtHItemType`: Name [Text, w0]
- `txtHValuationType`: Type [Text, w0]
- `dmFind`: Item Name [Text, w220, name]; Manual Code [Text, w100, manualCode]; Category [Text, w200, categoryName]; Group [Text, w150, groupName]; Type [Text, w130, typename]; SKU [Text, w90, sku]; Active [Text, w100, activeText]

**Lookup / suggestion / find sources (server RPC called):**
- `modifyReason` → `getDefaultValues`
- `txtItemName` → `getSuggestionItem`
- `txtHSKU` → `getSKU`
- `txtHCategory` → `getTxtHCategory`
- `txtHItemType` → `getHType`
- `txtHGroup` → `getTxtHGroup`
- `txtHValuationType` → `getTxtHValuationType`
- `txtHActive` → `getYesNo`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `ManualCode` ← DmShared.removeSpace(txtManualCode.getText()).trim()
- `UniqueId` ← DmShared.xNull(txtUniqueId.getText())
- `SharedKeyFieldName` ← DmShared.xNull(txtItemName.getText())
- `ActiveTag` ← DmShared.xNull(txtHActive.getTag())
- `ActiveText` ← DmShared.xNull(txtHActive.getText())
- `CategoryText` ← DmShared.xNull(txtHCategory.getText())
- `CategoryTag` ← DmShared.xNull(txtHCategory.getTag())
- `GroupText` ← DmShared.xNull(txtHGroup.getText())
- `GroupTag` ← DmShared.xNull(txtHGroup.getTag())
- `ItemTypeTag` ← DmShared.xNull(txtHItemType.getTag())
- `ItemTypeText` ← DmShared.xNull(txtHItemType.getText())
- `SkuTag` ← DmShared.xNull(txtHSKU.getTag())
- `SkuText` ← DmShared.xNull(txtHSKU.getText())
- `ItemDesc` ← DmShared.xNull(txtItemDesc.getText())
- `OverHeadEvalTypeCode` ← DmShared.xNull(txtHValuationType.getTag())
- `OverHeadEvalType` ← DmShared.xNull(txtHValuationType.getText())
- `CategoryText` ← DmShared.xNull(txtHCategory.getText())
- `CategoryTag` ← DmShared.xNull(txtHCategory.getTag())
- `GroupText` ← DmShared.xNull(txtHGroup.getText())
- `GroupTag` ← DmShared.xNull(txtHGroup.getTag())
- `SkuTag` ← DmShared.xNull(txtHSKU.getTag())
- `SkuText` ← DmShared.xNull(txtHSKU.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtManualCode.setText` ← mrTemp.getManualCode()
- `txtUniqueId.setText` ← DmShared.xNull(mrTemp.getUniqueId())
- `txtHActive.setTag` ← mrTemp.getActiveTag()
- `txtHActive.setText` ← mrTemp.getActiveText()
- `txtHCategory.setTag` ← mrTemp.getCategoryTag()
- `txtHCategory.setText` ← mrTemp.getCategoryText()
- `txtHItemType.setText` ← mrTemp.getItemTypeText()
- `txtHItemType.setTag` ← mrTemp.getItemTypeTag()
- `txtHGroup.setTag` ← mrTemp.getGroupTag()
- `txtHGroup.setText` ← mrTemp.getGroupText()
- `txtHSKU.setTag` ← mrTemp.getSkuTag()
- `txtHSKU.setText` ← mrTemp.getSkuText()
- `txtItemDesc.setText` ← DmShared.xNull(mrTemp.getItemDesc())
- `txtHValuationType.setTag` ← DmShared.xNull(mrTemp.getOverHeadEvalTypeCode())
- `txtHValuationType.setText` ← DmShared.xNull(mrTemp.getOverHeadEvalType())
- `txtItemName.setText` ← mrTemp.getSharedKeyFieldName()
- `userControl.setTextBlog` ← preparedByBlog, mrTemp.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, mrTemp.getSharedModifiedBy()
- userControl.setTextBlog(preparedByBlog, mrTemp.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, mrTemp.getSharedModifiedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtHActive.setEnabled(flag)`
- `txtHItemType.setEnabled(false)`
- `txtManualCode.setEnabled(false)`
- `txtManualCode.setEnabled(flag)`
- `txtUniqueId.setEnabled(false)`
- `txtUniqueId.setEnabled(true)`
- `txtHSKU.setEnabled(false)`
- `txtHSKU.setEnabled(true)`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
txtHActive.setEnabled(flag);
txtHItemType.setEnabled(false);
txtManualCode.setEnabled(false);
if (topControl.getMode().equalsIgnoreCase("Edit")) {
txtManualCode.setEnabled(flag);
if (!DmShared.xNull(txtUniqueId.getText()).equalsIgnoreCase("")) {
txtUniqueId.setEnabled(false);
} else {
txtUniqueId.setEnabled(true);
if (isItemUsed.equalsIgnoreCase("1")) {
txtHSKU.setEnabled(false);
} else {
txtHSKU.setEnabled(true);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`modifyReason.addCallBack` → `onOk()`
```java
try {
OtherChargesMaster.this.modifyReasonMdl = modifyReason;
if (topControl.getMode().equalsIgnoreCase("Browse") && DmShared.iNull(topControl.getDocId()).longValue() > 0) {
topControlDelete(topControl.getDocId());
} else {
topControlSave();
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), (DmTextBox) null).show();
```

`topControl.addNewHandeller` → `newClicked()`
```java
onAdd();
```

`topControl.addEditHandeller` → `editClicked()`
```java
manageDisplay(true);
```

`topControl.addDeleteHandeller` → `deleteClicked()`
```java
new DmConfirmMessageBox("Are you sure? You want to delete this record.", new DialogHideEvent.DialogHideHandler() {
public void onDialogHide(DialogHideEvent event) {
if (event.getHideButton().equals(Dialog.PredefinedButton.YES)) {
showModifyReason();
}).show();
```

`topControl.addDeleteHandeller` → `onDialogHide()`
```java
if (event.getHideButton().equals(Dialog.PredefinedButton.YES)) {
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
if (DmShared.xNull(topControl.getMode()).equalsIgnoreCase("Edit")) {
showModifyReason();
} else {
topControlSave();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(OtherChargesMaster.this);
```

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
```

`txtItemName.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getSuggestionItem(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Str
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtItemName).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtItemName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtItemName).show();
```

`txtItemName.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtItemName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtItemName).show();
```

`txtHSKU.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getSKU(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Sku_Mdl>>>() {
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSKU).show();
public void onSuccess(DmResultPageing<List<Sku_Mdl>> result) {
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

`txtHSKU.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && tRow != null) {
```

`txtHCategory.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHCategory(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHC
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHCategory).show();
public void onSuccess(DmResultPageing<List<TxtHCategory_Mdl>> result) {
if (result.isStatus()) {
txtHCategory.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCategory).show();
```

`txtHCategory.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHCategory.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCategory).show();
```

`txtHCategory.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && tRow != null) {
```

`txtHItemType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getHType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExte
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHItemType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemType).show();
```

`txtHItemType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemType).show();
```

`txtHGroup.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHGroup(txtHItemType.getTag(), helper, blnToEndOfFile, new AsyncCallback<DmResu
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGroup).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHGroup.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGroup).show();
```

`txtHGroup.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHGroup.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGroup).show();
```

`txtHGroup.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
```

`txtHValuationType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHValuationType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHValuationType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHValuationType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHValuationType).show();
```

`txtHValuationType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHValuationType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHValuationType).show();
```

`txtHActive.addKeyDownHandler` → `onKeyDown()`
```java
txtHActive.addTextHelpCallBack(new DmTextHelp.TextHelpCallBack<KeyFieldExtended>() {
public void populateData(DmHelper helper, boolean blnToEndOfFile, boolean blnCallBackWait) {
srvAsync.invokeRPC(blnCallBackWait).getYesNo(helper, blnToEndOfFile, true, new AsyncCallback<DmResultPageing<List<KeyFie
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHActive).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHActive.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHActive).show();
...
```

`txtHActive.addKeyDownHandler` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getYesNo(helper, blnToEndOfFile, true, new AsyncCallback<DmResultPageing<List<KeyFie
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHActive).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHActive.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHActive).show();
```

`txtHActive.addKeyDownHandler` → `onSuccess()`
```java
if (result.isStatus()) {
txtHActive.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHActive).show();
```

`txtHActive.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getYesNo(helper, blnToEndOfFile, true, new AsyncCallback<DmResultPageing<List<KeyFie
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHActive).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHActive.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHActive).show();
```

**Top control options:** setOtherRequired(true), setRefreshRequired(false), setDisplay(true)

**Menu items (Other button):** Manage Site

_Print permission "P" is removed for this screen (replace "P" with "*")._

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Alert: "Can be use in Browse Mode only and select a record."

## Files
| file | lines |
|---|---|
| client/OtherChargesMaster.java | 755 |
| client/OtherChargesMaster_Dsg.java | 353 |
| server/OtherChargesMaster_SrvImpl.java | 1189 |
| shared/DefaultValue_Mdl.java | 59 |
| shared/Find_Mdl.java | 80 |
| shared/OtherChargesMaster_Srv.java | 43 |
| shared/OtherChargesMaster_SrvAsync.java | 43 |
| shared/OtherChargesMaster_Vld.java | 21 |
| shared/OtherChargesMaster_mdl.java | 184 |
| shared/Sku_Mdl.java | 23 |
| shared/TxtHCategory_Mdl.java | 212 |
| shared/TxtHHSN_Mdl.java | 43 |
| shared/TxtHVoucherType_Mdl.java | 31 |

## UI labels / columns (client)
`Name`, `SKU`, `Manual Code`, `Unique ID`, `Category`, `Group Nature`, `Group`, `Valuation Type`, `Active`, `Discription`

## Models (shared DTOs)
### Sku_Mdl (extends `KeyField implements IsSerializable, Serializable`)
| type | field |
|---|---|
| String | allowedDecimalPlaces |

### TxtHCategory_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | inventoryAcTag |
| String | inventoryAcText |
| String | purchaseAcTag |
| String | purchaseAcText |
| String | purchaseContraAcTag |
| String | purchaseContraAcText |
| String | salesAcTag |
| String | salesAcText |
| String | purchaseReturnAcTag |
| String | salesReturnAcTag |
| String | purchaseReturnAcText |
| String | salesReturnAcText |
| String | wipAcText |
| String | transitAcTag |
| String | transitAcText |
| String | cogsAcTag |
| String | cogsAcText |
| String | grirAcTag |
| String | grirAcText |
| String | consumptionAcTag |
| String | consumptionAcText |
| String | wipAcTag |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | skuUnitCode |
| String | skuUnitName |
| String | itemTypeCode |
| String | itemTypeName |
| String | itemTypeManualCode |

### TxtHVoucherType_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | shortName |
| String | description |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | category |
| String | group |
| String | skuName |
| String | active |
| String | ManualCode |
| String | itemTypeTag |
| String | itemTypeText |

## RPC interface
| method | params | returns |
|---|---|---|
| getSuggestionItem | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getSKU | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Sku_Mdl>> |
| getTxtHCategory | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHCategory_Mdl>> |
| getTxtHGroup | String itemTypeCode, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<OtherChargesMaster_mdl> |
| saveRecord | OtherChargesMaster_mdl mrTemp | DmResult<String> |
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| deleteRecord | String strKey, ModifyReason_Mdl modifyReasonMdl | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getYesNo | DmHelper pageing, boolean blnToEndOfFile, boolean isActiveCall | DmResultPageing<List<KeyFieldExtended>> |
| getHType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHValuationType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVoucherType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucherType_Mdl>> |
| getTxtHVoucherGroup | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |

## Validator `OtherChargesMaster_Vld`
```java
public class OtherChargesMaster_Vld {
    public OtherChargesMaster_Vld() {
    }
    public static void chkSave(OtherChargesMaster_mdl mrTemp) {
        DmShared.isValidText(mrTemp.getSharedKeyFieldName(), "Name", 200);
        DmShared.isValidText(mrTemp.getCategoryTag(), "Category", 300);
        DmShared.isValidText(mrTemp.getGroupTag(), "Group", 400);
        DmShared.isValidText(mrTemp.getSkuTag(), "SKU", 500);
        DmShared.isValidText(mrTemp.getActiveTag(), "Active", 600);
    }
}
```

## Server `OtherChargesMaster_SrvImpl.java` (1189 lines)
Helpers used: `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getFieldUDT`, `DmServer.getLocalIPAddresses`, `DmServer.getSerialNoBigInt`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnUserName`, `DmServer.getUserWithDateTime`, `DmServer.setSsnOldData`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.vNull`, `DmShared.xNull`

### `getSuggestionItem`
`public DmResultPageing<List<String>> getSuggestionItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT NAME FROM itemMast`

### `getSKU`
`public DmResultPageing<List<Sku_Mdl>> getSKU(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code, unit, decimalPlaces from unitmast`

### `getTxtHCategory`
`public DmResultPageing<List<TxtHCategory_Mdl>> getTxtHCategory(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select ic.code as categoryCode, ic.name as categoryName , ic.purchaseAc AS purchaseAcCode, ic.purchaseContraAc AS purchaseContraAcCode ,ic.purchaseReturnAc AS purchaseReturnAcCode ,ic.salesAc as salesAcCode, ic.salesReturnAc as salesReturnAcCode , ic.subCode as inventoryAc,sgp.name as purchaseAcName,sgpc.name AS purchaseContraAcName , sgpr.name as purchaseReturnAcName, sgs.name as salesAcName, sgsr.name as salesReturnAcName,sgi.name as inventoryAcName , ic.grirAc,ic.consumptionAc,ic.wipAc,ic.transitAc,ic.cogsAc , sgg.name as grirAcName, sgc.name as consumptionName, sgw.name as wipAcName , sgt.name as transitAcName, sgco.name as cogsAcName from itemcat ic left join subgroup AS sgp ON ic.purchaseAc = sgp.subCode left join subgroup AS sgpc ON ic.purchaseContraAc = sgpc.subCode left join subgroup AS sgpr ON ic.purchaseReturnAc = sgpr.subCode left join subgroup AS sgs ON ic.salesAc = sgs.subCode left join subgroup AS sgsr ON ic.salesReturnAc = sgsr.subCode left join subgroup AS sgi ON ic.subCode = sgi.subCode left join subgroup AS sgg ON ic.grirAc = sgg.subCode left join subgroup AS sgc ON ic.consumptionAc = sgc.subCode left join subgroup AS sgw ON ic.wipAc = sgw.subCode left join subgroup AS sgt ON ic.transitAc = sgt.subCode left join subgroup AS sgco ON ic.cogsAc = sgco.subCode`

### `getTxtHGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGroup(String itemTypeCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ig.code, ig.name FROM itemGroup ig LEFT JOIN itemtype it ON it.code = ig.filterItemType WHERE it.code = :itemTypeCode`
- Params: `:itemTypeCode` ← `DmShared.xNull(itemTypeCode)`

### `getMoveRec`
`public DmResult<OtherChargesMaster_mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT im.code,im.name, im.uniqueId , im.itemCategoryCode , ic.name AS categoryName , im.itemGroup AS groupCode , ig.name AS groupName , im.itemType AS typeCode , it.name AS typeName , im.sku AS skuCode , um.unit AS sku , im.isActive, im.preparedBY , im.preparedByIP , im.u_name , im.preparedDt , im.u_EntDt , im.ManualCode , im.itemDesc , im.serviceEvalTypecode FROM itemMast AS im LEFT JOIN itemCat AS ic ON im.itemCategoryCode = ic.code LEFT JOIN itemGroup AS ig ON im.itemGroup = ig.code LEFT JOIN itemType AS it ON im.itemType = it.code LEFT JOIN unitMast AS um ON um.code = im.sku WHERE im.code = :code ;`
- Params: `:code` ← `strKey`
- Other calls: `OtherChargesValuationType.getViaCode`

### `saveRecord`
`public DmResult<String> saveRecord(OtherChargesMaster_mdl mrTemp)`

- SQL 1: `Select Count(Name) As Cnt From itemMast im Where im.name = :name`
- SQL 2: `SELECT vt.v_Type FROM voucher_type vt WHERE vt.category = ' {…} '`
- SQL 3: `Select Count(ManualCode) As Cnt From itemMast mm Where mm.ManualCode = :ManualCode And Code <> :Code`
- SQL 4: `INSERT INTO itemMast (code, name, itemGroup, itemType,filterItemType, sku , preparedBY, preparedByIP, preparedDt, isActive, itemCategoryCode , manualCode, uniqueId, itemDesc, serviceEvalTypeCode) VALUES (:code, :name, :itemGroup, :itemType,:filterItemType, :sku , :preparedBY, :preparedByIP, :preparedDt, :isActive, :itemCategoryCode , :manualCode, :uniqueId, :itemDesc, :serviceEvalTypeCode)`
- SQL 5: `UPDATE itemmast SET name = :name , itemGroup = :itemGroup , sku = :sku , u_Name = :userName , u_EntDt = :uEntDt , transferDt = NULL , itemCategoryCode = :itemCategoryCode , manualCode = :manualCode , itemType= :itemType , uniqueId = :uniqueId , serviceEvalTypeCode = :serviceEvalTypeCode , itemDesc = :itemDesc WHERE CODE = :CODE ;`
- Params: `:name` ← `mrTemp.getSharedKeyFieldName()`; `:Code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:Code` ← `mrTemp.getSharedKeyFieldCode()`; `:uniqueId` ← `mrTemp.getUniqueId()`; `:manualCode` ← `mrTemp.getManualCode()`; `:Code` ← `mrTemp.getSharedKeyFieldCode()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:itemGroup` ← `DmShared.foriegnKey(mrTemp.getGroupTag())`; `:manualCode` ← `mrTemp.getManualCode()`; `:itemType` ← `DmShared.foriegnKey(mrTemp.getItemTypeTag())`; `:filterItemType` ← `DmShared.foriegnKey(mrTemp.getItemTypeTag())`; `:sku` ← `DmShared.foriegnKey(mrTemp.getSkuTag())`; `:itemCategoryCode` ← `DmShared.foriegnKey(mrTemp.getCategoryTag())`; `:preparedBY` ← `DmServer.getSsnUserName(this)`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:preparedDt` ← `timestamp`; `:isActive` ← `DmShared.xNull(mrTemp.getActiveTag())`; `:itemDesc` ← `DmShared.xNull(mrTemp.getItemDesc())`; `:serviceEvalTypeCode` ← `DmShared.foriegnKey(mrTemp.getOverHeadEvalTypeCode())`; `:uniqueId` ← `DmShared.foriegnKey(DmShared.removeSpace(mrTemp.getUniqueId()))`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:itemGroup` ← `DmShared.foriegnKey(mrTemp.getGroupTag())`; `:manualCode` ← `mrTemp.getManualCode()`; `:itemType` ← `DmShared.foriegnKey(mrTemp.getItemTypeTag())`; `:sku` ← `DmShared.foriegnKey(mrTemp.getSkuTag())`; `:userName` ← `DmServer.getSsnUserName(this)`; `:uEntDt` ← `timestamp`; `:itemCategoryCode` ← `DmShared.foriegnKey(mrTemp.getCategoryTag())`; `:itemDesc` ← `DmShared.xNull(mrTemp.getItemDesc())`; `:serviceEvalTypeCode` ← `DmShared.foriegnKey(mrTemp.getOverHeadEvalTypeCode())`; `:uniqueId` ← `DmShared.foriegnKey(DmShared.removeSpace(mrTemp.getUniqueId()))`
- Errors: "Other Charges Name already exists." | "Invalid key." | "unique Id already exists." | "Invalid Manual Code." | "Manual Code already exists."
- Other calls: `OtherChargesMaster_Vld.chkSave`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `SELECT CODE FROM itemMast WHERE itemType = :itemType ORDER BY NAME`
- Params: `:itemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("OI", "manualCode", "itemType", "code", cnTem`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl modifyReason_Mdl)`

- SQL 1: `Delete From itemMast Where code=:code`
- Params: `:code` ← `strKey`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , mg.name AS groupName , um.unit AS sku , im.isActive , mc.name AS categoryName , it.code as typeCode , it.name as typename , im.manualCode , case im.isActive when 0 then 'InActive' when 1 then 'Active' when 2 then 'Hold' end as activeText FROM itemmast AS im LEFT JOIN itemCat AS mc ON im.itemCategoryCode = mc.code LEFT JOIN itemGroup AS mg ON im.itemGroup = mg.code LEFT JOIN itemType AS it ON im.itemType = it.code LEFT JOIN unitmast AS um ON im.sku = um.code WHERE im.itemType = :itemType`
- Params: `:itemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("OI", "manualCode", "itemType", "code", cnTem`

### `getYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getYesNo(DmHelper helper, boolean blnToEndOfFile, boolean isActiveCall)`


### `getHType`
`public DmResultPageing<List<KeyFieldExtended>> getHType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT it.code, it.name, it.manualCode FROM itemType it WHERE it.manualCode IN('OI','SV')`

### `getTxtHValuationType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHValuationType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `OtherChargesValuationType.values`

### `getTxtHVoucherType`
`public DmResultPageing<List<TxtHVoucherType_Mdl>> getTxtHVoucherType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type ,CONCAT(vt.description, ' (', vt.category, ')') AS NAME ,vt.short_Name ,vt.description FROM voucher_type vt`

### `getTxtHVoucherGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVoucherGroup(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT vg.code, vg.name, vg.manualCode FROM voucherGroup vg`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`

- SQL 1: `SELECT it.code, it.name, it.manualCode FROM itemType it WHERE it.manualCode = 'OI'`
- SQL 2: `SELECT um.code,um.unit FROM unitMast um WHERE um.unit = 'NOS'`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.manageSite.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (rsTemp.getLong("Cnt") > 0)` | "Other Charges Name already exists." |
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (rsTemp.getLong("Cnt") > 0)` | "unique Id already exists." |
| saveRecord | `if (DmShared.xNull(mrTemp.getManualCode()).trim().equalsIgnoreCase(""))` | "Invalid Manual Code." |
| saveRecord | `if (rsTemp.getLong("Cnt") > 0)` | "Manual Code already exists." |

## Notes (hand-written)
_No hand-written notes yet._
