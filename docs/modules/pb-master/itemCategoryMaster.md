# pb-master / itemCategoryMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/master/src/itemCategoryMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: itemCat
- Update tables: itemCat
- Delete cascade (in order): itemCat
- Duplicate checks on save:
  - `SELECT COUNT(name) AS cnt FROM itemCat WHERE LOWER(name) = :name AND code <> :code`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Category Name (Text); Item Cat. Nature (Text)
- Lookups/suggestions: getSuggestionCategory, getTxtHItemCatNature, getTxtHSubCode
- CSV export: yes

## Screen spec (auto-extracted from client)
### Screen `ItemCategoryMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Category Name | DmSuggestBox | `txtCategoryName` | `50, 10, true, "Name", DmDataType.STRING` |
| 2 | Item Type | DmTextHelp | `txtHItemCatNature` | `KeyField_PrpAcs, 3, true` |
| 3 | Applicable Property | DmTextBox | `txtProperties` | `` |
| 4 | Purchase A/c | DmTextHelp | `txtHPurchaseAc` | `KeyField_PrpAcs1, 6, false` |
| 5 | Purchase Return A/c | DmTextHelp | `txtHPurchaseReturnAc` | `GWT.create(ItemCategoryMaster_Dsg.KeyField_PrpAcs1.class), 6, false` |
| 6 | Purchase Contra A/c | DmTextHelp | `txtHPurchaseContraAc` | `KeyField_PrpAcs1, 6, false` |
| 7 | Sales A/c | DmTextHelp | `txtHSalesAc` | `GWT.create(ItemCategoryMaster_Dsg.KeyField_PrpAcs2.class), 6, false` |
| 8 | Sales Return A/c | DmTextHelp | `txtHSalesReturnAc` | `GWT.create(ItemCategoryMaster_Dsg.KeyField_PrpAcs2.class), 6, false` |
| 9 | Inventory A/c | DmTextHelp | `txtHInventoryAc` | `KeyField_PrpAcs, 6, false` |
| 10 | GR/IR A/c | DmTextHelp | `txtHGRIRAc` | `KeyField_PrpAcs5, 6, false` |
| 11 | Consumption A/c | DmTextHelp | `txtHConsumptionAc` | `KeyField_PrpAcs6, 6, false` |
| 12 | WIP A/c | DmTextHelp | `txtHWIPAc` | `KeyField_PrpAcs7, 6, false` |
| 13 | Transfer A/c | DmTextHelp | `txtHTransferAc` | `KeyField_PrpAcs10, 6, false` |
| 14 | Transit A/c | DmTextHelp | `txtHTransitAc` | `KeyField_PrpAcs8, 6, false` |
| 15 | COGS A/c | DmTextHelp | `txtHCOGSAc` | `KeyField_PrpAcs9, 6, false` |
| 16 | PRCA A/c | DmTextHelp | `txtHPRCAAc` | `KeyField_PrpAcs11, 6, false` |

Other widgets (not in tab list): `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `txtHItemCatNature`: Name [Text, w1, itemCatName]
- `dmFind`: Category Name [Text, w350, name]; Item Cat. Name [Text, w200, itemCatName]
- `txtHPurchaseAc`: Purchase A/c [Text, w100, Name]; Manual Code [Text, w100, manualCode]
- `txtHPurchaseContraAc`: Purchase Contra A/c [Text, w150, Name]; Manual Code [Text, w100, manualCode]
- `txtHPurchaseReturnAc`: Purchase Return A/c [Text, w100, Name]; Manual Code [Text, w100, manualCode]
- `txtHSalesReturnAc`: Sales Return A/c [Text, w100, Name]; Manual Code [Text, w100, manualCode]
- `txtHSalesAc`: Sales Return A/c [Text, w100, Name]; Manual Code [Text, w100, manualCode]
- `txtHGRIRAc`: GR/IR A/c [Text, w100, Name]; Manual Code [Text, w100, manualCode]
- `txtHInventoryAc`: Inventory A/c [Text, w100, Name]; Manual Code [Text, w100, manualCode]
- `txtHConsumptionAc`: Consumption A/c [Text, w100, Name]; Manual Code [Text, w100, manualCode]
- `txtHCOGSAc`: COGS A/c [Text, w100, Name]; Manual Code [Text, w100, manualCode]
- `txtHPRCAAc`: PRCA A/c [Text, w100, Name]; Manual Code [Text, w100, manualCode]
- `txtHTransitAc`: Transit A/c [Text, w100, Name]; Manual Code [Text, w100, manualCode]
- `txtHTransferAc`: Transfer A/c [Text, w100, Name]; Manual Code [Text, w100, manualCode]
- `txtHWIPAc`: WIP A/c [Text, w100, Name]; Manual Code [Text, w100, manualCode]

**Lookup / suggestion / find sources (server RPC called):**
- `txtCategoryName` → `getSuggestionCategory`
- `txtHItemCatNature` → `getTxtHItemCatNature`
- `txtHPurchaseAc` → `getTxtHSubCode`
- `txtHPurchaseContraAc` → `getTxtHSubCode`
- `txtHPurchaseReturnAc` → `getTxtHSubCode`
- `txtHSalesReturnAc` → `getTxtHSubCode`
- `txtHSalesAc` → `getTxtHSubCode`
- `txtHGRIRAc` → `getTxtHSubCode`
- `txtHInventoryAc` → `getTxtHSubCode`
- `txtHConsumptionAc` → `getTxtHSubCode`
- `txtHCOGSAc` → `getTxtHSubCode`
- `txtHPRCAAc` → `getTxtHSubCode`
- `txtHTransferAc` → `getTxtHSubCode`
- `txtHTransitAc` → `getTxtHSubCode`
- `txtHWIPAc` → `getTxtHSubCode`
- `applicablePropertyPopup` → `getFind`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtCategoryName.getText())
- `ItemCatNatureTag` ← DmShared.xNull(txtHItemCatNature.getTag())
- `ItemCatNatureText` ← DmShared.xNull(txtHItemCatNature.getText())
- `PropertiesCSv` ← DmShared.xNull(txtProperties.getText())
- `PropertiesTag` ← DmShared.xNull(txtProperties.getTag())
- `InventoryAcTag` ← DmShared.xNull(txtHInventoryAc.getTag())
- `InventoryAcText` ← DmShared.xNull(txtHInventoryAc.getText())
- `PurchaseAcTag` ← DmShared.xNull(txtHPurchaseAc.getTag())
- `PurchaseAcText` ← DmShared.xNull(txtHPurchaseAc.getText())
- `PurchaseContraAcTag` ← DmShared.xNull(txtHPurchaseContraAc.getTag())
- `PurchaseContraAcText` ← DmShared.xNull(txtHPurchaseContraAc.getText())
- `SalesAcTag` ← DmShared.xNull(txtHSalesAc.getTag())
- `SalesAcText` ← DmShared.xNull(txtHSalesAc.getText())
- `PurchaseReturnAcTag` ← DmShared.xNull(txtHPurchaseReturnAc.getTag())
- `PurchaseReturnAcText` ← DmShared.xNull(txtHPurchaseReturnAc.getText())
- `SalesReturnAcTag` ← DmShared.xNull(txtHSalesReturnAc.getTag())
- `SalesReturnAcText` ← DmShared.xNull(txtHSalesReturnAc.getText())
- `GrirAcTag` ← DmShared.xNull(txtHGRIRAc.getTag())
- `GrirAcText` ← DmShared.xNull(txtHGRIRAc.getText())
- `ConsumptionAcTag` ← DmShared.xNull(txtHConsumptionAc.getTag())
- `ConsumptionAcText` ← DmShared.xNull(txtHConsumptionAc.getText())
- `WipAcTag` ← DmShared.xNull(txtHWIPAc.getTag())
- `WipAcText` ← DmShared.xNull(txtHWIPAc.getText())
- `TransferAcTag` ← DmShared.xNull(txtHTransferAc.getTag())
- `TransferAcText` ← DmShared.xNull(txtHTransferAc.getText())
- `TransitAcTag` ← DmShared.xNull(txtHTransitAc.getTag())
- `TransitAcText` ← DmShared.xNull(txtHTransitAc.getText())
- `CogsAcTag` ← DmShared.xNull(txtHCOGSAc.getTag())
- `CogsAcText` ← DmShared.xNull(txtHCOGSAc.getTag())
- `PrcaAcTag` ← DmShared.xNull(txtHPRCAAc.getTag())
- `PrcaAcText` ← DmShared.xNull(txtHPRCAAc.getTag())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- `txtCategoryName.setText` ← data.getSharedKeyFieldName()
- `txtHItemCatNature.setTag` ← data.getItemCatNatureTag()
- `txtHItemCatNature.setText` ← data.getItemCatNatureText()
- `txtProperties.setText` ← DmShared.xNull(data.getPropertiesCSv())
- `txtProperties.setTag` ← DmShared.xNull(data.getPropertiesTag())
- `txtHGRIRAc.setTag` ← DmShared.xNull(data.getGrirAcTag())
- `txtHGRIRAc.setText` ← DmShared.xNull(data.getGrirAcText())
- `txtHConsumptionAc.setTag` ← DmShared.xNull(data.getConsumptionAcTag())
- `txtHConsumptionAc.setText` ← DmShared.xNull(data.getConsumptionAcText())
- `txtHWIPAc.setTag` ← DmShared.xNull(data.getWipAcTag())
- `txtHWIPAc.setText` ← DmShared.xNull(data.getWipAcText())
- `txtHTransferAc.setTag` ← DmShared.xNull(data.getTransferAcTag())
- `txtHTransferAc.setText` ← DmShared.xNull(data.getTransferAcText())
- `txtHTransitAc.setTag` ← DmShared.xNull(data.getTransitAcTag())
- `txtHTransitAc.setText` ← DmShared.xNull(data.getTransitAcText())
- `txtHCOGSAc.setTag` ← DmShared.xNull(data.getCogsAcTag())
- `txtHCOGSAc.setText` ← DmShared.xNull(data.getCogsAcText())
- `txtHPRCAAc.setTag` ← DmShared.xNull(data.getPrcaAcTag())
- `txtHPRCAAc.setText` ← DmShared.xNull(data.getPrcaAcText())
- `txtHInventoryAc.setTag` ← DmShared.xNull(data.getInventoryAcTag())
- `txtHInventoryAc.setText` ← DmShared.xNull(data.getInventoryAcText())
- `txtHPurchaseAc.setTag` ← DmShared.xNull(data.getPurchaseAcTag())
- `txtHPurchaseAc.setText` ← DmShared.xNull(data.getPurchaseAcText())
- `txtHPurchaseContraAc.setTag` ← DmShared.xNull(data.getPurchaseContraAcTag())
- `txtHPurchaseContraAc.setText` ← DmShared.xNull(data.getPurchaseContraAcText())
- `txtHPurchaseReturnAc.setTag` ← DmShared.xNull(data.getPurchaseReturnAcTag())
- `txtHPurchaseReturnAc.setText` ← DmShared.xNull(data.getPurchaseReturnAcText())
- `txtHSalesAc.setTag` ← DmShared.xNull(data.getSalesAcTag())
- `txtHSalesReturnAc.setTag` ← DmShared.xNull(data.getSalesReturnAcTag())
- `txtHSalesAc.setText` ← DmShared.xNull(data.getSalesAcText())
- `txtHSalesReturnAc.setText` ← DmShared.xNull(data.getSalesReturnAcText())
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtHItemCatNature.setEnabled(flag)`

**On New click:**
- `clear()`
- `txtCategoryName.setFocus(true)`

**`manageDisplay()` logic (condensed code):**
```java
txtHItemCatNature.setEnabled(flag);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`modifyReason.addCallBack` → `onOk()`
```java
try {
ItemCategoryMaster.this.modifyReasonMdl = modifyReason;
if (topControl.getMode().equalsIgnoreCase("Browse") && DmShared.iNull(topControl.getDocId()).longValue() > 0) {
topControlDelete(topControl.getDocId());
} else {
topControlSave();
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), (DmTextBox) null).show();
```

`topControl.addNewHandeller` → `newClicked()`
```java
clear();
txtCategoryName.setFocus(true);
manageDisplay(true);
```

`topControl.addEditHandeller` → `editClicked()`
```java
txtCategoryName.setFocus(true);
manageDisplay(false);
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(ItemCategoryMaster.this);
```

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
```

`txtCategoryName.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getSuggestionCategory(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtCategoryName).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtCategoryName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtCategoryName).show();
```

`txtCategoryName.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtCategoryName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtCategoryName).show();
```

`txtHItemCatNature.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHItemCatNature(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemCatNature).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHItemCatNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemCatNature).show();
```

`txtHItemCatNature.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemCatNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemCatNature).show();
```

`txtHPurchaseAc.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHSubCode("PR", helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHPurchaseAc).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHPurchaseAc.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPurchaseAc).show();
```

`txtHPurchaseAc.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHPurchaseAc.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPurchaseAc).show();
```

`txtHPurchaseContraAc.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHSubCode("", helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHPurchaseContraAc).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHPurchaseContraAc.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPurchaseContraAc).show();
```

`txtHPurchaseContraAc.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHPurchaseContraAc.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPurchaseContraAc).show();
```

`txtHPurchaseReturnAc.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHSubCode("PR", helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHPurchaseReturnAc).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHPurchaseReturnAc.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPurchaseReturnAc).show();
```

`txtHPurchaseReturnAc.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHPurchaseReturnAc.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPurchaseReturnAc).show();
```

`txtHSalesReturnAc.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHSubCode("SL", helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSalesReturnAc).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHSalesReturnAc.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSalesReturnAc).show();
```

`txtHSalesReturnAc.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSalesReturnAc.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSalesReturnAc).show();
```

`txtHSalesAc.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHSubCode("SL", helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSalesAc).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHSalesAc.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSalesAc).show();
```

`txtHSalesAc.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSalesAc.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSalesAc).show();
```

`txtHGRIRAc.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHSubCode("", helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGRIRAc).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHGRIRAc.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGRIRAc).show();
```

`txtHGRIRAc.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHGRIRAc.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGRIRAc).show();
```

`txtHInventoryAc.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHSubCode("", helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHInventoryAc).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHInventoryAc.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHInventoryAc).show();
```

`txtHInventoryAc.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHInventoryAc.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHInventoryAc).show();
```

`txtHConsumptionAc.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHSubCode("", helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHConsumptionAc).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHConsumptionAc.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHConsumptionAc).show();
```

**Top control options:** setRefreshRequired(false), setDisplay(true)

**Menu items (Other button):** Export Data

_Print permission "P" is removed for this screen (replace "P" with "*")._

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Confirm: "Are you sure? You want to export records."

## Files
| file | lines |
|---|---|
| client/ItemCategoryMaster.java | 967 |
| client/ItemCategoryMaster_Dsg.java | 648 |
| server/ItemCategoryMaster_SrvImpl.java | 836 |
| shared/Find_Mdl.java | 49 |
| shared/ItemCategoryMaster_Mdl.java | 317 |
| shared/ItemCategoryMaster_Srv.java | 28 |
| shared/ItemCategoryMaster_SrvAsync.java | 29 |
| shared/ItemCategoryMaster_Vld.java | 12 |
| shared/TxtHHSN_Mdl.java | 23 |
| shared/TxtHItemType_Mdl.java | 23 |

## UI labels / columns (client)
`Category Name`, `Item Type`, `Applicable Property`, `Purchase A/c`, `Purchase Return A/c`, `Purchase Contra A/c`, `Sales A/c`, `Sales Return A/c`, `Inventory A/c`, `GR/IR A/c`, `Consumption A/c`, `WIP A/c`, `Transfer A/c`, `Transit A/c`, `COGS A/c`, `PRCA A/c`

## Models (shared DTOs)
### TxtHItemType_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | shortname |

### ItemCategoryMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | itemCatNatureTag |
| String | itemCatNatureText |
| String | itemTag |
| String | itemText |
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
| String | grirAcTag |
| String | grirAcText |
| String | consumptionAcTag |
| String | consumptionAcText |
| String | wipAcTag |
| String | wipAcText |
| String | transferAcTag |
| String | transferAcText |
| String | transitAcTag |
| String | transitAcText |
| String | cogsAcTag |
| String | cogsAcText |
| String | prcaAcTag |
| String | prcaAcText |
| String | propertiesCSv |
| String | propertiesTag |
| ModifyReason_Mdl | modifyReasonMdl |

### TxtHHSN_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | hsn |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | shortname |
| String | itemCatCode |
| String | itemCatName |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getSuggestionCategory | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getTxtHItemCatNature | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<ItemCategoryMaster_Mdl> |
| saveRecord | ItemCategoryMaster_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl modifyReasonMdl | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| exportCSV | String sessionId | DmResult<KeyFieldExtended> |
| getTxtHSubCode | String subgroupNature, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `ItemCategoryMaster_Vld`
```java
public class ItemCategoryMaster_Vld {
    public static void chkSave(ItemCategoryMaster_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getSharedKeyFieldName().trim(), "Category Name", 100);
        DmShared.isValidText(mrTemp.getItemCatNatureTag().trim(), "Item Cat. Nature", 200);
    }
}
```

## Server `ItemCategoryMaster_SrvImpl.java` (836 lines)
Helpers used: `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnUserName`, `DmServer.getUserWithDateTime`, `DmServer.setSsnOldData`, `DmShared.foriegnKey`, `DmShared.removeSpace`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `SELECT CODE FROM itemCat`

### `getSuggestionCategory`
`public DmResultPageing<List<String>> getSuggestionCategory(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select Name From itemCat`

### `getTxtHItemCatNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemCatNature(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT it.code AS itemCatCode ,it.name AS itemCatName FROM itemtype as it WHERE it.manualcode NOT IN ('OI','SV')`

### `getMoveRec`
`public DmResult<ItemCategoryMaster_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT ic.code, ic.name, it.code AS itemCatCode ,it.name AS itemCatName , ic.preparedBy, ic.preparedByIP, ic.u_name, ic.preparedDt, ic.u_EntDt , ic.purchaseAc AS purchaseAcCode ,ic.purchaseReturnAc AS purchaseReturnAcCode ,ic.salesAc as salesAcCode , ic.purchaseAc AS purchaseContraAc , sgpc.name AS purchaseContraAcName , ic.salesReturnAc as salesReturnAcCode , ic.propertyList , ic.subCode as inventoryAc,sgp.name as purchaseAcName, sgpr.name as purchaseReturnAcName , sgs.name as salesAcName, sgsr.name as salesReturnAcName,sgi.name as inventoryAcName , ic.grirAc,ic.consumptionAc,ic.wipAc,ic.transferAc,ic.transitAc,ic.cogsAc,ic.prcaAc , sgg.name as grirAcName, sgc.name as consumptionName, sgw.name as wipAcName , sgTer.name as transferAcName, sgt.name as transitAcName, sgco.name as cogsAcName, sgpa.name as prcaAcName FROM itemCat ic LEFT JOIN itemtype as it on ic.filterItemType = it.code LEFT JOIN subgroup AS sgp ON ic.purchaseAc = sgp.subCode LEFT JOIN subgroup AS sgpc ON ic.purchaseContraAc = sgpc.subCode LEFT JOIN subgroup AS sgpr ON ic.purchaseReturnAc = sgpr.subCode LEFT JOIN subgroup AS sgs ON ic.salesAc = sgs.subCode LEFT JOIN subgroup AS sgsr ON ic.salesReturnAc = sgsr.subCode LEFT JOIN subgroup AS sgi ON ic.subCode = sgi.subCode LEFT JOIN subgroup AS sgg ON ic.grirAc = sgg.subCode LEFT JOIN subgroup AS sgc ON ic.consumptionAc = sgc.subCode LEFT JOIN subgroup AS sgw ON ic.wipAc = sgw.subCode LEFT JOIN subgroup AS sgt ON ic.transitAc = sgt.subCode LEFT JOIN subgroup AS sgTer ON ic.transferAc = sgTer.subCode LEFT JOIN subgroup AS sgco ON ic.cogsAc = sgco.subCode LEFT JOIN subgroup AS sgpa ON ic.prcaAc = sgpa.subCode WHERE ic.code= :code`
- Params: `:code` ← `strKey`
- Other calls: `Properties.values`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl modifyReasonMdl)`

- SQL 1: `Delete From itemCat Where code=:code`
- Params: `:code` ← `strKey`

### `saveRecord`
`public DmResult<String> saveRecord(ItemCategoryMaster_Mdl mrTemp)`

- SQL 1: `SELECT COUNT(name) AS cnt FROM itemCat WHERE LOWER(name) = :name AND code <> :code`
- SQL 2: `Insert Into itemCat(code , name, filterItemType, preparedBy, preparedByIP , preparedDt, propertyList ,purchaseAc,purchaseContraAc ,salesAc , subCode , purchaseReturnAc , salesReturnAc ,grirAc,consumptionAc,wipAc,transferAc, transitAc,cogsAc,prcaAc) Values(:code, :name, :filterItemType, :preparedBy, :preparedByIP, :preparedDt, :propertyList , :purchaseAc,:purchaseContraAc ,:salesAc,:subCode, :purchaseReturnAc , :salesReturnAc ,:grirAc,:consumptionAc,:wipAc,:transferAc, :transitAc,:cogsAc,:prcaAc) ;`
- SQL 3: `Update itemCat Set name = :name, propertyList = :propertyList, filterItemType = :filterItemType, U_Name = :u_Name, U_EntDt = :uEntDt, purchaseAc = :purchaseAc, purchaseContraAc = :purchaseContraAc, salesAc = :salesAc, subCode = :subCode, purchaseReturnAc= :purchaseReturnAc, salesReturnAc = :salesReturnAc, grirAc = :grirAc , consumptionAc = :consumptionAc , wipAc = :wipAc , transferAc = :transferAc , transitAc = :transitAc , cogsAc = :cogsAc , prcaAc = :prcaAc Where code = :code`
- Params: `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName()).toLowerCase()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:filterItemType` ← `DmShared.xNull(mrTemp.getItemCatNatureTag())`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `timestamp`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:propertyList` ← `DmShared.xNull(mrTemp.getPropertiesTag())`; `:purchaseAc` ← `DmShared.foriegnKey(mrTemp.getPurchaseAcTag())`; `:purchaseContraAc` ← `DmShared.foriegnKey(mrTemp.getPurchaseContraAcTag())`; `:salesAc` ← `DmShared.foriegnKey(mrTemp.getSalesAcTag())`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getInventoryAcTag())`; `:purchaseReturnAc` ← `DmShared.foriegnKey(mrTemp.getPurchaseReturnAcTag())`; `:salesReturnAc` ← `DmShared.foriegnKey(mrTemp.getSalesReturnAcTag())`; `:grirAc` ← `DmShared.foriegnKey(mrTemp.getGrirAcTag())`; `:consumptionAc` ← `DmShared.foriegnKey(mrTemp.getConsumptionAcTag())`; `:wipAc` ← `DmShared.foriegnKey(mrTemp.getWipAcTag())`; `:transferAc` ← `DmShared.foriegnKey(mrTemp.getTransferAcTag())`; `:transitAc` ← `DmShared.foriegnKey(mrTemp.getTransitAcTag())`; `:cogsAc` ← `DmShared.foriegnKey(mrTemp.getCogsAcTag())`; `:prcaAc` ← `DmShared.foriegnKey(mrTemp.getPrcaAcTag())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:filterItemType` ← `DmShared.xNull(mrTemp.getItemCatNatureTag())`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:uEntDt` ← `timestamp`; `:propertyList` ← `DmShared.xNull(mrTemp.getPropertiesTag())`; `:purchaseAc` ← `DmShared.foriegnKey(mrTemp.getPurchaseAcTag())`; `:purchaseContraAc` ← `DmShared.foriegnKey(mrTemp.getPurchaseContraAcTag())`; `:salesAc` ← `DmShared.foriegnKey(mrTemp.getSalesAcTag())`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getInventoryAcTag())`; `:purchaseReturnAc` ← `DmShared.foriegnKey(mrTemp.getPurchaseReturnAcTag())`; `:salesReturnAc` ← `DmShared.foriegnKey(mrTemp.getSalesReturnAcTag())`; `:grirAc` ← `DmShared.foriegnKey(mrTemp.getGrirAcTag())`; `:consumptionAc` ← `DmShared.foriegnKey(mrTemp.getConsumptionAcTag())`; `:wipAc` ← `DmShared.foriegnKey(mrTemp.getWipAcTag())`; `:transferAc` ← `DmShared.foriegnKey(mrTemp.getTransferAcTag())`; `:transitAc` ← `DmShared.foriegnKey(mrTemp.getTransitAcTag())`; `:cogsAc` ← `DmShared.foriegnKey(mrTemp.getCogsAcTag())`; `:prcaAc` ← `DmShared.foriegnKey(mrTemp.getPrcaAcTag())`
- Errors: "Invalid key." | "Category Name already exists."
- Other calls: `ItemCategoryMaster_Vld.chkSave`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ic.code, ic.name ,it.code AS itemCatCode ,it.name AS itemCatName FROM itemCat ic LEFT JOIN itemType it ON ic.filterItemType = it.code`

### `exportCSV`
`public DmResult<KeyFieldExtended> exportCSV(String sessionId)`

- SQL 1: `SELECT ic.name FROM itemCat ic WHERE ic.filterItemType = :filterItemType order by ic.name`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`
- Errors: "Invalid request." | "File Name not given."
- Other calls: `ImportData_Server.getCSVFormats`

### `getTxtHSubCode`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSubCode(String subgroupNature, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode, sg.name, sg.manualCode FROM subGroup sg where sg.isActive = 1 {…}`
- Params: `:nature` ← `DmShared.xNull(DmServer.getCodeViaManualCode(subgroupNature, "manualCode", "subGroupNature`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.client`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `paperbiz.master.applicablePropertyPopup.client`, `paperbiz.master.applicablePropertyPopup.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.stImportData.server`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Category Name already exists." |
| exportCSV | `if (!DmSession.getSessions().containsKey(sessionId))` | "Invalid request." |
| exportCSV | `if (DmShared.xNull(fileName).equalsIgnoreCase(""))` | "File Name not given." |

## Notes (hand-written)
_No hand-written notes yet._
