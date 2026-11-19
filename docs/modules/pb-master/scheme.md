# pb-master / scheme

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/master/src/scheme/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: scheme, s
- Delete cascade (in order): scheme
- Duplicate checks on save: none found
- "In use" checks before delete:
  - `SELECT COUNT(cnt) AS cnt FROM (SELECT td.applicableFrom AS cnt FROM scheme td WHERE td.schemeCode = :code and td.entryFrom = :entryFrom and td.site_Code = :site_Code GROUP BY td.applicableFrom ) AS ta`
  - `select COUNT(*) as cnt from scheme s where s.schemeCode = :schemeCode and s.entryFrom = :entryFrom and s.site_Code = :site_Code and s.isStatus = 1`
- Validations: scheme (Text); rule: From date is Mandatory; rule: Scheme detail is mandatory.
- Lookups/suggestions: getSuggestionCategory, getTxtHItemCat, getTxtHItemName, getTxtHDiscountType, getTxtHScheme, getTxtHCopyFromScheme, getTxtHUnit, getTxtHSchemeGroup
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `CloseSchemePopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Date | DmTextBox | `txtClosingDate` | `11, true` |
- Alert: "Closing date should be grater than or equal to applicable from date"

### Screen `ItemGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Item Category | DmTextHelp | `txtHItemCategory` | `KeyField_PrpAcs, 6, false` |
| 2 | Unit | DmTextHelp | `txtHItemName` | `TxtHItemName_PrpAcs, 6, false` |
| 3 | - | DmTextHelp | `txtHUnit` | `com.google.gwt.core.client.TxtHUnit_PrpAcs, 5, false` |
| 4 | - | DmTextHelp | `txtHSchemeGroup` | `SchemeGroupList_PrpAcs, 10, false` |
| 5 | To Amount | DmTextBoxNumeric | `txtFromRate` | `2, false, 10, false` |
| 6 | - | DmTextBoxNumeric | `txtToRate` | `2, false, 10, false` |
| 7 | - | DmTextBoxNumeric | `txtFromAmt` | `2, false, 10, false` |
| 8 | - | DmTextBoxNumeric | `txtToAmt` | `2, false, 10, false` |
| 9 | Interval | DmTextBoxNumeric | `txtFromQty` | `4, false, 12, false` |
| 10 | - | DmTextBoxNumeric | `txtToQty` | `4, false, 12, false` |
| 11 | - | DmTextBoxNumeric | `txtInterval` | `4, false, 10, false` |
| 12 | - | DmTextHelp | `txtHDiscountType` | `KeyField_PrpAcs, 3, true` |
| 13 | - | DmTextBoxNumeric | `txtAtRate` | `4, false, 10, false` |
| 14 | - | DmTextBoxNumeric | `txtAmt` | `2, false, 10, false` |
| 15 | Remark | DmRemarkTextBox | `txtRemark` | `250, false` |

**Grid / popup columns** (header, width, db field):
- `txtHItemCategory`: Name [Text, w0, Name]
- `txtHItemName`: Name [Text, w300, name]; Manual Code [Text, w100, manualCode]; Unit [Text, w60, saleUnit]; Category [Text, w150, categoryName]
- `txtHDiscountType`: Name [Text, w0]
- `txtHUnit`: Name [Text, w100, unitName]
- `txtHSchemeGroup`: Name [Text, w120, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHItemCategory` → `getTxtHItemCat`
- `txtHItemName` → `getTxtHItemName`
- `txtHDiscountType` → `getTxtHDiscountType`
- `txtHUnit` → `getTxtHUnit`
- `txtHSchemeGroup` → `getTxtHSchemeGroup`

**Save: model field ← widget** (`saveInitialize`):
- `ItemCatName` ← DmShared.xNull(txtHItemCategory.getText())
- `ItemCatCode` ← DmShared.xNull(txtHItemCategory.getTag())
- `ItemCode` ← DmShared.xNull(txtHItemName.getTag())
- `ItemName` ← DmShared.xNull(txtHItemName.getText())
- `SchemeGroupCode` ← DmShared.xNull(txtHSchemeGroup.getTag())
- `SchemeGroupName` ← DmShared.xNull(txtHSchemeGroup.getText())
- `Interval` ← DmShared.xNull(txtInterval.getText())
- `UnitCode` ← DmShared.xNull(txtHUnit.getTag())
- `Unit` ← DmShared.xNull(txtHUnit.getText())
- `AmtFrom` ← DmClient.vNull(txtFromAmt.getText(), "0.00")
- `AmtTo` ← DmClient.vNull(txtToAmt.getText(), "0.00")
- `QtyFrom` ← DmShared.xNull(txtFromQty.getText())
- `QtyTo` ← DmShared.xNull(txtToQty.getText())
- `RateFrom` ← DmClient.vNull(txtFromRate.getText(), "0.00")
- `RateTo` ← DmClient.vNull(txtToRate.getText(), "0.00")
- `DiscountType` ← DmShared.xNull(txtHDiscountType.getText())
- `DiscountTypeCode` ← DmShared.xNull(txtHDiscountType.getTag())
- `AtRate` ← DmShared.xNull(txtAtRate.getText())
- `Amount` ← DmClient.vNull(txtAmt.getText(), "0.00")
- `Remark` ← DmShared.xNull(txtRemark.getText())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtHUnit.setEnabled(true)`
- `txtHUnit.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
manageDiscountType(DmShared.iNull(txtHDiscountType.getTag()).intValue());
if (DmShared.xNull(txtHItemName.getTag()).equalsIgnoreCase("")) {
txtHUnit.setEnabled(true);
} else {
txtHUnit.setEnabled(false);
setLblSchemeGroupDesign();
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`createSchemeGroup.addCallBack` → `onOk()`
```java
if (mrTemp != null) {
txtHSchemeGroup.setTag(mrTemp.getSharedKeyFieldCode());
txtHSchemeGroup.setText(mrTemp.getSharedKeyFieldName());
txtHSchemeGroup.setFocus(true);
```

`createSchemeGroup.addCallBack` → `onClose()`
```java
txtHSchemeGroup.setFocus(true);
```

`lblSchemeGroup.addClickHandler` → `onClick()`
```java
showCreateNewSchemeGroup();
```

`txtHItemCategory.addTextHelpCallBack` → `populateData()`
```java
boolean flagShowHelp = true;
if (!DmShared.xNull(txtHSchemeGroup.getTag()).equalsIgnoreCase("")) {
flagShowHelp = false;
srvAsync.invokeRPC(blnCallBackWait).getTxtHItemCat(helper, blnToEndOfFile, flagShowHelp, new AsyncCallback<DmResultPagei
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemCategory).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHItemCategory.setData(result.getData(), result.getPaging());
} else {
...
```

`txtHItemCategory.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemCategory.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemCategory).show();
```

`txtHItemCategory.addTextHelpCallBack` → `getSelectedRow()`
```java
setLblSchemeGroupDesign();
if (!DmShared.xNull(txtHItemCategory.getTag()).equalsIgnoreCase(DmShared.xNull(txtHItemCategory.getTag2nd()))) {
resetItemCategory();
txtHItemCategory.setTag2nd(DmShared.xNull(txtHItemCategory.getTag()));
if (blnStatus && t1Row != null) {
```

`txtHItemName.addTextHelpCallBack` → `populateData()`
```java
boolean flagShowHelp = true;
if (!DmShared.xNull(txtHSchemeGroup.getTag()).equalsIgnoreCase("")) {
flagShowHelp = false;
srvAsync.invokeRPC(blnCallBackWait).getTxtHItemName(helper, blnToEndOfFile, DmShared.xNull(txtHItemCategory.getTag()), f
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemName).show();
public void onSuccess(DmResultPageing<List<TxtHItemType_Mdl>> result) {
if (result.isStatus()) {
txtHItemName.setData(result.getData(), result.getPaging());
} else {
...
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
if (!DmShared.xNull(txtHItemName.getTag()).equalsIgnoreCase(DmShared.xNull(txtHItemName.getTag2nd()))) {
resetUnit();
txtHItemName.setTag2nd(DmShared.xNull(txtHItemName.getTag()));
if (blnStatus && t1Row != null) {
if (!DmShared.xNull(t1Row.getSaleUnit()).equalsIgnoreCase("")) {
txtHUnit.setText(DmShared.xNull(t1Row.getSaleUnitName()));
txtHUnit.setTag(DmShared.xNull(t1Row.getSaleUnit()));
txtHUnit.setEnabled(false);
manageDisplay();
```

`txtHDiscountType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHDiscountType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<K
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDiscountType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHDiscountType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDiscountType).show();
```

`txtHDiscountType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHDiscountType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDiscountType).show();
```

`txtHDiscountType.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHDiscountType.getTag()).equalsIgnoreCase(DmShared.xNull(txtHDiscountType.getTag2nd()))) {
txtHDiscountType.setTag2nd(DmShared.xNull(txtHDiscountType.getTag()));
if (blnStatus && t1Row != null) {
txtHDiscountType.setTag2nd(DmShared.xNull(txtHDiscountType.getTag()));
manageDiscountType(DmShared.iNull(txtHDiscountType.getTag()).intValue());
```

`txtHUnit.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHUnit(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHUnit).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHUnit.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHUnit).show();
```

`txtHUnit.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHUnit.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHUnit).show();
```

`txtHUnit.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
```

`txtHSchemeGroup.addTextHelpCallBack` → `populateData()`
```java
boolean flagShowHelp = false;
if (DmShared.vNull(clsParent.txtCategory.getTag()).intValue() == STEnum.SchemeCategory.MIX_SCHEME.getCode()) {
if (DmShared.xNull(txtHItemCategory.getTag()).equalsIgnoreCase("")
&& DmShared.xNull(txtHItemName.getTag()).equalsIgnoreCase("")) {
flagShowHelp = true;
srvAsync.invokeRPC(blnCallBackWait).getTxtHSchemeGroup(helper, blnToEndOfFile, flagShowHelp, new AsyncCallback<DmResultP
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSchemeGroup).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
...
```

`txtHSchemeGroup.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSchemeGroup.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSchemeGroup).show();
```

`txtHSchemeGroup.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
```
- Confirm: "Are you sure? You want to Delete Scheme."
- Alert: "Item Category or Item Name or Scheme Group is mandatory."
- Alert: "Scheme Group is not required."
- Alert: "Only one range is required among <b>Rate Range, Amount Range and Quantity Range</b>."
- Alert: "To rate  is mandatory"
- Alert: "From rate  is mandatory"
- Alert: "To rate should be greater than or equal to From Date"
- Alert: "Only one range is required among <b>Rate Range, Amount Range and Quantity Range</b>."
- Alert: "To Amount is mandatory"
- Alert: "From Amount is mandatory"
- Alert: "To Amount should be greater than or equal to From Amount"
- Alert: "Only one range is required among <b>Rate Range, Amount Range and Quantity Range</b>."
- Alert: "To Qty  is mandatory"
- Alert: "From Qty  is mandatory"
- Alert: "To Qty should be greater than or equal to From Qty"
- Alert: "Unit is Mandatory"
- Alert: "Interval is not required."
- Alert: "Discount Type is mandatory."
- Alert: "Interval is not required"
- Alert: "Amount is mandatory"
- Alert: "At Rate is mandatory"

### Screen `SchemeApplicableFrom`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Copy Scheme | DmTextHelp | `txtHCopyFromScheme` | `TxtHCopyFromScheme_PrpAcs, 5, false` |
| 2 | Site | DmTextBox | `txtSite` | `` |
| 3 | Copy Applicable Date | DmTextBox | `txtDtCopyFrom` | `11, false` |
| 4 | New Applicable Date | DmTextBox | `txtDtNew` | `100, true` |
| 5 | Copy Scheme | DmTextHelp | `txtHCopyFromScheme` | `TxtHCopyFromScheme_PrpAcs, 5, false` |
| 6 | Site | DmTextBox | `txtSite` | `` |
| 7 | Copy Applicable Date | DmTextBox | `txtDtCopyFrom` | `11, false` |

Other widgets (not in tab list): `dmGrdApplicableDate` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHCopyFromScheme`: Name [Text, w0, name]; Type [Text, w100, type]; Category [Text, w100, category]; Applicable From [Text, w100, replace(convert(varchar, applicableFrom, 106), ' ','-')]; Site [Text, w150, site_Name]
- `dmGrdApplicableDate`: Applicable From [Text, w1]; Applicable To [Text, w125]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHCopyFromScheme` → `getTxtHCopyFromScheme`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHCopyFromScheme.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHCopyFromScheme(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHCopyFromScheme).show();
public void onSuccess(DmResultPageing<List<TxtHCopyScheme_Mdl>> result) {
if (result.isStatus()) {
txtHCopyFromScheme.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCopyFromScheme).show();
```

`txtHCopyFromScheme.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHCopyFromScheme.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCopyFromScheme).show();
```

`txtHCopyFromScheme.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
txtDtCopyFrom.setText(DmShared.dNull(t1Row.getSharedPreparedBy()));
txtSite.setTag(DmShared.xNull(t1Row.getSiteCode()));
txtSite.setText(DmShared.xNull(t1Row.getSiteName()));
} else {
txtDtCopyFrom.setText("");
txtSite.setTag("");
txtSite.setText("");
```
- Confirm: "Are you sure? You want to delete "
- Alert: "Please Select Applicable From "
- Alert: "Copy Scheme is mandatory."
- Alert: "Please Select a Date"
- Alert: "Copy TDS is mandatory."
- Alert: "New Applicable Date is mandatory."
- Alert: "Scheme is closed can't craete it."

### Screen `Scheme`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Scheme | DmTextHelp | `txtHScheme` | `SchemeList_PrpAcs, 10, true` |
| 2 | Type | DmTextBox | `txtType` | `11, false` |
| 3 | Category | DmTextBox | `txtCategory` | `11, false` |
| 4 | Applicable From | DmTextBox | `txtApplicableFrom` | `11, true` |
| 5 | Applicable Upto | DmTextBox | `txtApplicableTo` | `11, false` |

Other widgets (not in tab list): `dmFind` (DmFind), `grdItemDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `dmFind`: Name [Text, w200, schemeName]; Scheme Type [Text, w110, type]; Scheme Category [Text, w110, category]
- `txtHScheme`: Name [Text, w120, name]
- `grdItemDetail`: Item  Category [Text, w180]; Item Name [Text, w220]; Scheme Group [Text, w200]; Unit [Text, w50]; Frm. Rate [Text, w70]; To Rate [Text, w65]; Frm. Amt [Text, w65]; To Amt [Text, w65]; Frm.Qty. [Text, w65]; To Qty. [Text, w65]; Interval [Text, w70]; Disc. Type [Text, w80]; At Rate [Text, w65]; Amount [Text, w70]; Remark [Text, w150]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHScheme` → `getTxtHScheme`
- `itemGridEdit` → `fillGrid`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← DmShared.xNull(txtHScheme.getTag())
- `SharedKeyFieldName` ← DmShared.xNull(txtHScheme.getText())
- `CategoryCode` ← DmShared.xNull(txtCategory.getTag())
- `TypeCode` ← DmShared.xNull(txtType.getTag())
- `ApplicableFromDate` ← DmShared.dNull(txtApplicableFrom.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtApplicableFrom.setText` ← mrTemp.getApplicableFromDate()
- `txtHScheme.setText` ← mrTemp.getSharedKeyFieldName()
- `txtHScheme.setTag` ← mrTemp.getSharedKeyFieldCode()
- `txtHScheme.setTag2nd` ← mrTemp.getSharedKeyFieldCode()
- `txtCategory.setText` ← mrTemp.getCategoryName()
- `txtType.setText` ← mrTemp.getTypeName()
- `txtCategory.setTag` ← mrTemp.getCategoryCode()
- `txtType.setTag` ← mrTemp.getTypeCode()
- `txtApplicableFrom.setText` ← mrTemp.getApplicableFromDate()
- `txtApplicableFrom.setTag` ← mrTemp.getApplicableFromDate()
- `txtApplicableTo.setText` ← mrTemp.getApplicableToDate()

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtType.setEnabled(false)`
- `txtCategory.setEnabled(false)`
- `txtHScheme.setEnabled(flag)`
- `txtApplicableFrom.setEnabled(true)`
- `txtApplicableFrom.setEnabled(false)`
- `txtApplicableTo.setEnabled(false)`

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
txtType.setEnabled(false);
txtCategory.setEnabled(false);
txtHScheme.setEnabled(flag);
if (topControl.getMode().equalsIgnoreCase("New")) {
txtApplicableFrom.setEnabled(true);
} else {
txtApplicableFrom.setEnabled(false);
txtApplicableTo.setEnabled(false);
setLblSchemeDesign();
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`createScheme.addCallBack` → `onOk()`
```java
if (mrTemp != null) {
if (DmShared.iNull(mrTemp.getSharedKeyFieldCode()).longValue() > 0) {
txtHScheme.setTag(mrTemp.getSharedKeyFieldCode());
txtHScheme.setText(mrTemp.getSharedKeyFieldName());
txtType.setTag(DmShared.xNull(mrTemp.getTypeCode()));
txtType.setText(DmShared.xNull(mrTemp.getType()));
txtCategory.setTag(DmShared.xNull(mrTemp.getCategoryCode()));
txtCategory.setText(DmShared.xNull(mrTemp.getCategory()));
txtApplicableFrom.setText(DmShared.dNull(DmClient.getStartDate()));
txtApplicableTo.setText("31-Dec-9999");
...
```

`createScheme.addCallBack` → `onClose()`
```java
txtHScheme.setFocus(true);
```

`closeSchemePopup.addCallBack` → `onOk()`
```java
if (!DmShared.dNull(closingDate).equalsIgnoreCase("")) {
txtApplicableTo.setText(DmShared.dNull(closingDate));
topControlMoveRec(txtHScheme.getTag());
```

`closeSchemePopup.addCallBack` → `onClose()`
```java
txtHScheme.setFocus(true);
```

`txtHScheme.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHScheme(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHSch
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHScheme).show();
public void onSuccess(DmResultPageing<List<TxtHScheme_Mdl>> result) {
if (result.isStatus()) {
txtHScheme.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHScheme).show();
```

`txtHScheme.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHScheme.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHScheme).show();
```

`txtHScheme.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHScheme.getTag()).equalsIgnoreCase(DmShared.xNull(txtHScheme.getTag2nd()))) {
resetScheme();
txtHScheme.setTag2nd(DmShared.xNull(txtHScheme.getTag()));
if (blnStatus && t1Row != null) {
txtType.setText(DmShared.xNull(t1Row.getType()));
txtType.setTag(DmShared.xNull(t1Row.getTypeCode()));
txtCategory.setText(DmShared.xNull(t1Row.getCategory()));
txtCategory.setTag(DmShared.xNull(t1Row.getCategoryCode()));
txtApplicableFrom.setText(DmShared.dNull(t1Row.getApplicableFrom()));
txtApplicableTo.setText(DmShared.dNull(t1Row.getApplicableTo()));
...
```

`applicableFrom.addCallBack` → `onOk()`
```java
try {
txtApplicableFrom.setTag(DmShared.dNull(oldDtApplicable));
txtApplicableFrom.setText(DmShared.dNull(newDtApplicable));
txtApplicableTo.setText(DmShared.dNull(newApplicableTo));
if (!DmShared.dNull(oldDtApplicable).equalsIgnoreCase("")) {
fillGrid(topControl.getDocId(), "", txtApplicableFrom.getTag());
} catch (Exception ex) {
new DmAlertMessageBox(ex.getMessage(), (DmTextBox) null).show();
```

`applicableFrom.addCallBack` → `onDelete()`
```java
topControlMoveRec(DmShared.xNull(topControl.getDocId()));
```

`applicableFrom.addCallBack` → `onCopyScheme()`
```java
if (!DmShared.dNull(newDtApplicable).equalsIgnoreCase("")) {
txtApplicableFrom.setText(DmShared.dNull(newDtApplicable));
if (!DmShared.xNull(copySchemeCode).trim().equalsIgnoreCase("") && !DmShared.xNull(copySiteCode).trim().equalsIgnoreCase
fillGrid(copySchemeCode, copySiteCode, copyDtApplicable);
```

`itemGridEdit.addCallBack` → `onOk()`
```java
grdItemDetail.commitChanges();
```

`itemGridEdit.addCallBack` → `onDelete()`
```java
grdItemDetail.commitChanges();
```

`topControl.addNewHandeller` → `newClicked()`
```java
onAdd();
manageFlwApplicable(true);
```

`topControl.addEditHandeller` → `editClicked()`
```java
grdItemDetail.getGrid().getStore().add(new GrdItemDetail_Mdl());
txtHScheme.setFocus(true);
manageDisplay(false);
manageFlwApplicable(false);
```

`topControl.addDeleteHandeller` → `deleteClicked()`
```java
new DmConfirmMessageBox("Are you sure? You want to delete this record.", new DialogHideEvent.DialogHideHandler() {
public void onDialogHide(DialogHideEvent event) {
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
topControlDelete(topControl.getDocId());
}).show();
```

`topControl.addDeleteHandeller` → `onDialogHide()`
```java
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
topControlDelete(topControl.getDocId());
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
topControlSave();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(Scheme.this);
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

**Top control options:** setOtherRequired(false), setRefreshRequired(false), setDisplay(true)

_Print permission "P" is removed for this screen (replace "P" with "*")._
- Confirm: "Are you sure? You want to delete this record."
- Alert: "Select Record To View/Add ApplicableFrom"
- Alert: "Already Closed"
- Alert: "Already Open"

## Files
| file | lines |
|---|---|
| client/CloseSchemePopup.java | 171 |
| client/CloseSchemePopup_Dsg.java | 114 |
| client/ItemGridEdit.java | 750 |
| client/ItemGridEdit_Dsg.java | 540 |
| client/Scheme.java | 771 |
| client/SchemeApplicableFrom.java | 426 |
| client/SchemeApplicableFrom_Dsg.java | 412 |
| client/Scheme_Dsg.java | 339 |
| server/Scheme_Server.java | 454 |
| server/Scheme_SrvImpl.java | 1278 |
| shared/Find_Mdl.java | 52 |
| shared/GrdItemDetail_Mdl.java | 315 |
| shared/SchemeMaster_Vld.java | 33 |
| shared/Scheme_Mdl.java | 80 |
| shared/Scheme_Srv.java | 47 |
| shared/Scheme_SrvAsync.java | 47 |
| shared/TxtHCopyScheme_Mdl.java | 68 |
| shared/TxtHItemType_Mdl.java | 140 |
| shared/TxtHScheme_Mdl.java | 77 |

## UI labels / columns (client)
`Scheme Group`, `Item Category`, `Item Name`, `Unit`, `From Rate`, `To Rate`, `From Amount`, `To Amount`, `From Quantity`, `To Quantity`, `Interval`, `Discount Type`, `At Rate`, `Amount`, `Remark`, `Scheme`, `Type`, `Category`, `Applicable From`, `Applicable Upto`, `COPY Scheme`, `Copy Scheme`, `Site`, `Copy Applicable Date`, `New Applicable Date`, `Date`

## Models (shared DTOs)
### TxtHItemType_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | categoryCode |
| String | itemGroupCode |
| String | itemTypeCode |
| String | itemTypeName |
| String | skuCode |
| String | purchaseUnit |
| String | saleUnit |
| String | manualCode |
| String | CategoryName |
| String | subCode |
| String | saleUnitName |
| String | packSizeCode |
| String | packSizeName |
| String | freeItemValue |

### Scheme_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | typeName |
| String | typeCode |
| String | categoryName |
| String | categoryCode |
| String | applicableFromDate |
| String | applicableToDate |
| List<GrdItemDetail_Mdl> | GrdData |

### GrdItemDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | entryFrom |
| String | entryFromNature |
| String | itemCatName |
| String | itemCatCode |
| String | itemName |
| String | itemCode |
| String | schemeGroupName |
| String | schemeGroupCode |
| String | unitCode |
| String | unit |
| String | qtyFrom |
| String | qtyTo |
| String | rateFrom |
| String | rateTo |
| String | amtFrom |
| String | amtTo |
| String | interval |
| String | applicableTo |
| String | applicableFrom |
| String | nature |
| String | discountType |
| String | discountTypeCode |
| String | atRate |
| String | amount |
| String | schemeCode |
| String | partyCode |
| String | siteCode |
| String | remark |
| String | uName |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | schemeType |
| String | schemeTypeTag |
| String | schemeCategory |
| String | schemeCategoryTag |

### TxtHCopyScheme_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | type |
| String | typeCode |
| String | categoryCode |
| String | category |
| String | siteCode |
| String | siteName |

### TxtHScheme_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | type |
| String | typeCode |
| String | category |
| String | categoryCode |
| String | applicableFrom |
| String | applicableTo |
| String | closingDate |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getSuggestionCategory | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getMoveRec | String strKey | DmResult<Scheme_Mdl> |
| saveRecord | Scheme_Mdl mrTemp | DmResult<String> |
| updateClosingDate | TxtHScheme_Mdl mrTemp, boolean isClose | DmResult<String> |
| deleteRecord | String strKey | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getTxtHItemCat | DmHelper helper, boolean blnToEndOfFile, boolean flagShowHelp | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemName | DmHelper helper, boolean blnToEndOfFile, String itemGroup, boolean flagShowHelp | DmResultPageing<List<TxtHItemType_Mdl>> |
| getTxtHDiscountType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHScheme | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHScheme_Mdl>> |
| getTxtHCopyFromScheme | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHCopyScheme_Mdl>> |
| fillGridApplicableDate | String keyCode | DmResult<List<KeyFieldExtended>> |
| getMaxApplicableDate | String keyCode | DmResult<KeyFieldExtended> |
| deleteRecordApplicable | String keyCode, String dNull | DmResult<String> |
| fillGrid | String keyCode, String copySiteCode, String applicableDate | DmResult<List<GrdItemDetail_Mdl>> |
| getTxtHUnit | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSchemeGroup | DmHelper helper, boolean blnToEndOfFile, boolean flagshowData | DmResultPageing<List<KeyFieldExtended>> |

## Validator `SchemeMaster_Vld`
```java
public class SchemeMaster_Vld {
    public static void chkSave(Scheme_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getSharedKeyFieldName().trim(), "scheme", 100);
        if (DmShared.xNull(mrTemp.getApplicableFromDate()).equalsIgnoreCase("")) {
            throw new DmException(400, "From date is Mandatory");
        }
        boolean flag = false;
        List<GrdItemDetail_Mdl> listItemDetail = mrTemp.getGrdData();
        for (GrdItemDetail_Mdl detail_Mdl : listItemDetail) {
            if (!DmShared.xNull(detail_Mdl.getItemCatCode()).equalsIgnoreCase("")
                    || !DmShared.xNull(detail_Mdl.getItemCode()).equalsIgnoreCase("")
                    || !DmShared.xNull(detail_Mdl.getSchemeGroupCode()).equalsIgnoreCase("")) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            throw new DmException(500, "Scheme detail is mandatory.");
        }
    }
}
```

## Server `Scheme_Server.java` (454 lines)
Helpers used: `DmServer.addDate`, `DmServer.convertDate`, `DmServer.getServerTimestamp`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.xNull`

### `saveScheme`
`public static void saveScheme(List<GrdItemDetail_Mdl> schemeList, String effectiveDate, String schemeCode, String entryFrom, String partyCode, String siteCode, Connection cnTemp) throws Exception`

- SQL 1: `select MAX(smd.applicableFrom) applicableFromMax, MIN(smd.applicableFrom) applicableFromMin from scheme smd where smd.schemeCode = :code and entryFrom = :entryFrom and site_Code = :site_Code {…}`
- SQL 2: `update scheme set applicableTo = :applicableToDate , transferDt = :transferDt where applicableFrom = :applicableFromDate and schemeCode = :code and entryFrom = :entryFrom and site_Code = :site_Code {…}`
- SQL 3: `select applicableFrom, applicableTo FROM scheme where applicableFrom > :applicableFromDate and schemeCode = :code and entryFrom = :entryFrom and site_Code = :site_Code {…} group by applicableFrom, applicableTo order by applicableFrom asc offset 0 rows fetch next 1 rows only`
- SQL 4: `update scheme set applicableTo = :applicableToDate , transferDt = :transferDt where applicableFrom = :applicableFromDate and schemeCode = :code and entryFrom = :entryFrom and site_Code = :site_Code {…}`
- Params: `:code` ← `DmShared.iNull(schemeCode).toString()`; `:entryFrom` ← `DmShared.iNull(entryFrom).toString()`; `:site_Code` ← `siteCode`; `:partyCode` ← `partyCode`; `:applicableFromDate` ← `DmServer.convertDate(effectiveDate)`; `:code` ← `DmShared.xNull(schemeCode)`; `:entryFrom` ← `DmShared.iNull(entryFrom).toString()`; `:site_Code` ← `siteCode`; `:partyCode` ← `partyCode`; `:applicableToDate` ← `DmServer.convertDate(DmServer.addDate(Calendar.DATE, -1, DmShared.dNull(effectiveDate)))`; `:applicableFromDate` ← `rsTemp.getDate("applicableFrom")`; `:code` ← `DmShared.xNull(schemeCode)`; `:entryFrom` ← `DmShared.iNull(entryFrom).toString()`; `:site_Code` ← `siteCode`; `:transferDt` ← `null`; `:partyCode` ← `partyCode`; `:applicableFromDate` ← `DmServer.convertDate(effectiveDate)`; `:code` ← `DmShared.xNull(schemeCode).toString()`; `:entryFrom` ← `DmShared.iNull(entryFrom).toString()`; `:site_Code` ← `siteCode`; `:partyCode` ← `partyCode`; `:applicableFromDate` ← `DmServer.convertDate(maxApplicableDate)`; `:applicableToDate` ← `DmServer.convertDate(DmServer.addDate(Calendar.DATE, -1, DmShared.dNull(effectiveDate)))`; `:code` ← `DmShared.xNull(schemeCode)`; `:entryFrom` ← `DmShared.iNull(entryFrom).toString()`; `:site_Code` ← `siteCode`; `:transferDt` ← `null`; `:partyCode` ← `partyCode`; `:entryFrom` ← `String.valueOf(entryFrom)`; `:entryFromNature` ← `DmShared.iNull(grdItmDtlMdl.getEntryFromNature()).toString()`; `:site_Code` ← `DmShared.xNull(siteCode)`; `:applicableFrom` ← `DmServer.convertDate(effectiveDate)`; `:applicableTo` ← `DmServer.convertDate(applicableTo)`; `:schemeCode` ← `DmShared.foriegnKey(schemeCode)`; `:nature` ← `DmShared.iNull(STEnum.SchemeListNature.None.getId()).toString()`; `:subCode` ← `DmShared.foriegnKey(partyCode)`; `:itemCode` ← `DmShared.foriegnKey(grdItmDtlMdl.getItemCode())`; `:itemCategoryCode` ← `DmShared.foriegnKey(grdItmDtlMdl.getItemCatCode())`; `:unitCode` ← `DmShared.foriegnKey(grdItmDtlMdl.getUnitCode())`; `:qtyFrom` ← `DmServer.vNull(grdItmDtlMdl.getQtyFrom())`; `:qtyTo` ← `DmServer.vNull(grdItmDtlMdl.getQtyTo())`; `:amountFrom` ← `DmServer.vNull(grdItmDtlMdl.getAmtFrom())`; `:amountTo` ← `DmServer.vNull(grdItmDtlMdl.getAmtTo())`; `:rateFrom` ← `DmServer.vNull(grdItmDtlMdl.getRateFrom())`; `:rateTo` ← `DmServer.vNull(grdItmDtlMdl.getRateTo())`; `:discountType` ← `DmShared.foriegnKey(grdItmDtlMdl.getDiscountTypeCode())`; `:atRate` ← `DmServer.vNull(grdItmDtlMdl.getAtRate())`; `:amount` ← `DmServer.vNull(grdItmDtlMdl.getAmount())`; `:u_Name` ← `grdItmDtlMdl.getuName()`; `:interval` ← `DmServer.vNull(grdItmDtlMdl.getInterval())`; `:schemeGroupCode` ← `DmShared.foriegnKey(grdItmDtlMdl.getSchemeGroupCode())`; `:remark` ← `DmShared.xNull(grdItmDtlMdl.getRemark())`; `:u_EntDt` ← `DmServer.getServerTimestamp()`; `:transferDt` ← `null`
- Other calls: `None.getId`

### `deleteScheme`
`public static void deleteScheme(String applicableFrom, STEnum.SchemeEntryFrom schemeEntryFrom, String keyCode, String schemeCode, String site_Code, Connection cnTemp)`

- SQL 1: `select applicableFrom,applicableTo from scheme where applicableFrom = :applicableFrom and entryFrom = :entryFrom and site_Code = :site_Code and schemeCode = :schemeCode {…} order by applicableFrom desc offset 0 rows fetch next 1 rows only`
- SQL 2: `update scheme set applicableTo = :applicableTo , transferDt = :transferDt where applicableFrom = :applicableFrom and entryFrom = :entryFrom and site_Code = :site_Code and schemeCode = :schemeCode {…}`
- SQL 3: `delete from scheme where applicableFrom = :applicableFrom and entryFrom = :entryFrom and site_Code = :site_Code and schemeCode = :schemeCode {…}`
- Params: `:applicableFrom` ← `DmServer.convertDate(applicableFrom)`; `:entryFrom` ← `String.valueOf(schemeEntryFrom.toInt())`; `:site_Code` ← `site_Code`; `:schemeCode` ← `schemeCode`; `:keyCode` ← `keyCode`; `:applicableFrom` ← `DmServer.convertDate(applicableFrom)`; `:entryFrom` ← `String.valueOf(schemeEntryFrom.toInt())`; `:site_Code` ← `site_Code`; `:schemeCode` ← `schemeCode`; `:keyCode` ← `keyCode`; `:applicableTo` ← `DmServer.convertDate(applicableTo)`; `:applicableFrom` ← `DmServer.convertDate(DmShared.dNull(rsTemp.getDate("applicableFrom")))`; `:entryFrom` ← `String.valueOf(schemeEntryFrom.toInt())`; `:site_Code` ← `site_Code`; `:schemeCode` ← `schemeCode`; `:transferDt` ← `null`; `:keyCode` ← `keyCode`; `:applicableFrom` ← `DmServer.convertDate(applicableFrom)`; `:entryFrom` ← `String.valueOf(schemeEntryFrom.toInt())`; `:site_Code` ← `site_Code`; `:schemeCode` ← `schemeCode`; `:keyCode` ← `keyCode`
- Errors: "Scheme Entry From not found."

### `updateCloseOpenScheme`
`public static void updateCloseOpenScheme(TxtHScheme_Mdl mrTemp, boolean isClose, String entryFrom, String site_code, Connection cnTemp)`

- SQL 1: `select MAX(s.isStatus) AS isStatus from scheme s where s.schemeCode = :schemeCode and s.applicableFrom = :applicableFrom and s.entryFrom = :entryFrom and s.applicableTo = :applicableTo and s.site_Code = :site_Code {…}`
- SQL 2: `update s set s.isStatus = 1 ,s.applicableTo = :applicableTo from scheme s where s.schemeCode = :schemeCode and s.entryFrom = :entryFrom and s.applicableFrom = :applicableFrom and s.site_Code = :site_Code and s.isStatus = 0 {…}`
- SQL 3: `select COUNT(*) as cnt ,MAX(s.isStatus) AS isStatus from scheme s where s.schemeCode = :schemeCode and s.entryFrom = :entryFrom and s.site_Code = :site_Code {…}`
- SQL 4: `update s set s.isStatus = 0 ,s.applicableTo = :applicableTo from scheme s where s.schemeCode = :schemeCode and s.entryFrom = :entryFrom and s.site_Code = :site_Code and s.isStatus = 1 {…}`
- Params: `:schemeCode` ← `mrTemp.getSharedKeyFieldCode()`; `:entryFrom` ← `DmShared.iNull(entryFrom).toString()`; `:site_Code` ← `DmShared.xNull(site_code)`; `:applicableFrom` ← `DmServer.convertDate(mrTemp.getApplicableFrom())`; `:applicableTo` ← `DmServer.convertDate("31-Dec-9999")`; `:subCode` ← `DmShared.xNull(mrTemp.getSharedPreparedBy())`; `:schemeCode` ← `mrTemp.getSharedKeyFieldCode()`; `:entryFrom` ← `DmShared.iNull(entryFrom).toString()`; `:site_Code` ← `DmShared.xNull(site_code)`; `:applicableFrom` ← `DmServer.convertDate(mrTemp.getApplicableFrom())`; `:applicableTo` ← `DmServer.convertDate(mrTemp.getClosingDate())`; `:subCode` ← `DmShared.xNull(mrTemp.getSharedPreparedBy())`; `:schemeCode` ← `mrTemp.getSharedKeyFieldCode()`; `:entryFrom` ← `DmShared.iNull(entryFrom).toString()`; `:site_Code` ← `DmShared.xNull(site_code)`; `:subCode` ← `DmShared.xNull(mrTemp.getSharedPreparedBy())`; `:schemeCode` ← `mrTemp.getSharedKeyFieldCode()`; `:entryFrom` ← `DmShared.iNull(entryFrom).toString()`; `:site_Code` ← `DmShared.xNull(site_code)`; `:applicableTo` ← `DmServer.convertDate("31-Dec-9999")`; `:subCode` ← `DmShared.xNull(mrTemp.getSharedPreparedBy())`
- Errors: "Scheme is already closed." | "No Record Found" | "Scheme is already open"
- Other calls: `PARTY_SCHEME.toInt`

## Server `Scheme_SrvImpl.java` (1278 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `SELECT sm.code,sm.name as schemeName,sm.type ,sm.category from SchemeMast sm INNER JOIN (select distinct s.schemeCode from scheme s where s.entryFrom =:entryFrom and s.site_code =:sitecode ) as vS on vS.schemeCode=sm.code`
- Params: `:entryFrom` ← `String.valueOf(entryFrom.toInt())`; `:sitecode` ← `siteCode`

### `getSuggestionCategory`
`public DmResultPageing<List<String>> getSuggestionCategory(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select name From schemeMast`

### `getMoveRec`
`public DmResult<Scheme_Mdl> getMoveRec(String strKey)`

- SQL 1: `Select sm.Code ,sm.Name ,sm.preparedBy ,sm.u_Name , vs.applicableFrom , vs.applicableTo ,sm.type , sm.category From schemeMast sm LEFT JOIN ( select max(s.applicableFrom) applicableFrom, max(s.applicableTo) applicableTo , s.schemeCode from scheme s where s.schemeCode = :code and s.applicableFrom <= '9999-12-31' and s.entryFrom =:entryFrom and s.site_Code =:sitecode group by s.schemeCode ) vs on vs.schemeCode = sm.code Where sm.code = :code`
- Params: `:code` ← `DmShared.iNull(strKey).toString()`; `:entryFrom` ← `String.valueOf(entryFrom.toInt())`; `:sitecode` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `SchemeCategory.getViaKey`, `SchemeType.getViaKey`

### `fillSchemeGrid`
`private List<GrdItemDetail_Mdl> fillSchemeGrid(String keyCode, String copySiteCode, String applicableDate, Connection cnTemp) throws Exception`

- SQL 1: `SELECT sm.name as schemeName , s.entryFrom,s.entryFromNature,s.schemeCode ,s.applicableFrom,s.applicableTo,s.nature,s.subCode ,s.itemCode ,s.itemCategoryCode ,s.unitCode,s.qtyFrom,s.qtyTo,s.rateFrom ,s.rateTo,s.amountFrom,amountTo ,s.discountType,s.atRate ,im.name as itemName,ic.name as itemCatName ,um.unit as unitName , s.amount , s.remark , s.schemeGroupCode ,s.interval ,sgm.name as schemeGroupName FROM scheme AS s LEFT JOIN schemeMast AS sm ON sm.code=s.schemeCode LEFT JOIN itemMast AS im ON im.Code=s.itemCode LEFT JOIN itemCat AS ic ON ic.code = s.itemCategoryCode LEFT JOIN unitMast AS um ON um.Code=s.unitCode LEFT JOIN schemeGroupMast AS sgm ON sgm.Code=s.schemeGroupCode where s.schemeCode=:code and s.applicableFrom =:applicableFrom and s.site_Code = :site_Code {…}`
- Params: `:code` ← `DmShared.iNull(keyCode).toString()`; `:applicableFrom` ← `DmServer.convertDate(applicableDate)`; `:entryFrom` ← `DmShared.iNull(entryFrom).toString()`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:site_Code` ← `copySiteCode`
- Other calls: `SCHEME_MASTER.toInt`, `SchemeDiscountType.getViaKey`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey)`

- SQL 1: `SELECT COUNT(cnt) AS cnt FROM (SELECT td.applicableFrom AS cnt FROM scheme td WHERE td.schemeCode = :code and td.entryFrom = :entryFrom and td.site_Code = :site_Code GROUP BY td.applicableFrom ) AS tab`
- SQL 2: `select COUNT(*) as cnt from scheme s where s.schemeCode = :schemeCode and s.entryFrom = :entryFrom and s.site_Code = :site_Code and s.isStatus = 1`
- SQL 3: `Delete From scheme Where schemeCode= :code and entryFrom = :entryFrom and site_Code = :site_Code`
- Params: `:code` ← `strKey`; `:entryFrom` ← `DmShared.iNull(entryFrom).toString()`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:schemeCode` ← `strKey`; `:entryFrom` ← `DmShared.iNull(entryFrom).toString()`; `:site_Code` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`; `:code` ← `strKey`; `:entryFrom` ← `DmShared.iNull(entryFrom).toString()`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Errors: "Record have multiple Scheme List. Could not delete this record." | "Scheme is Closed. Can't delete record! "
- Other calls: `SCHEME_MASTER.toInt`

### `saveRecord`
`public DmResult<String> saveRecord(Scheme_Mdl mrTemp)`

- Errors: "Invalid key."
- Other calls: `SCHEME_MASTER.toInt`, `SchemeMaster_Vld.chkSave`, `Scheme_Server.deleteScheme`

### `saveScheme`
`private String saveScheme(Scheme_Mdl mrTemp, Connection cnTemp)`

- Other calls: `SCHEME_MASTER.toInt`, `Scheme_Server.saveScheme`

### `getSchemeListMdl`
`private GrdItemDetail_Mdl getSchemeListMdl(String siteCode, String schemeCode, String effectiveDate, String itemCode, String itemCatCode, String unitCode, String qtyFrom, String qtyTo, String amtFrom, String amtTo, String rateFrom, String rateTo, String discountType, String rate, String amount, String remark, String u_Name, String schemeGroupCode, String interval)`

- Other calls: `NONE.toInt`, `SCHEME_MASTER.toInt`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code,sm.name as schemeName,sm.type ,sm.category from SchemeMast sm INNER JOIN (select distinct s.schemeCode from scheme s where s.entryFrom =:entryFrom and s.site_code =:sitecode ) as vS on vS.schemeCode=sm.code`
- Params: `:entryFrom` ← `String.valueOf(entryFrom.toInt())`; `:sitecode` ← `siteCode`
- Other calls: `SchemeType.getViaKey`

### `getTxtHItemCat`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemCat(DmHelper helper, boolean blnToEndOfFile, boolean flagShowHelp)`

- SQL 1: `SELECT ic.code, ic.name FROM itemCat ic WHERE (ic.filterItemType = :filterItemType OR ic.isCommon = 1) {…}`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemName`
`public DmResultPageing<List<TxtHItemType_Mdl>> getTxtHItemName(DmHelper helper, boolean blnToEndOfFile, String itemCat, boolean flagShowHelp)`

- SQL 1: `SELECT im.code, im.name , ig.name AS groupName , um.unit AS sku, im.itemCategoryCode, im.isActive, it.name as itemTypeName,it.code as itemTypeCode, ic.name AS categoryName ,im.manualCode, im.isActive as itemStatusCode , CAST(im.u_EntDt as date) as modifiedDate ,im.subcode,im.saleUnit ,umi.unit as saleUnitName,im.packSizeCode,im.itemPacking as packSizeName FROM itemmast AS im LEFT JOIN itemcat AS ic ON im.itemCategoryCode = ic.code LEFT JOIN itemgroup AS ig ON im.itemGroup = ig.code LEFT JOIN unitMast AS um ON im.sku = um.code left join itemType as it on im.ItemType=it.code left join unitMast as umi on umi.code=im.saleUnit {…}`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:itemCat` ← `DmShared.xNull(itemCat)`

### `getTxtHDiscountType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDiscountType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `SchemeDiscountType.values`

### `getTxtHScheme`
`public DmResultPageing<List<TxtHScheme_Mdl>> getTxtHScheme(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select sm.code,sm.name, sm.type,sm.category, vs.applicableFrom, vs.applicableTo from schemeMast as sm LEFT JOIN (Select max(s.applicableFrom) as applicableFrom, max(s.applicableTo) as applicableTo, s.SchemeCode from Scheme s WHERE s.entryFrom = :entryFrom group by s.schemeCode) vs on vs.schemeCode = sm.code where sm.isActive = '1'`
- Params: `:entryFrom` ← `DmShared.iNull(SchemeEntryFrom.SCHEME_MASTER.toInt()).toString()`
- Other calls: `SCHEME_MASTER.toInt`, `SchemeCategory.getViaKey`, `SchemeType.getViaKey`

### `getTxtHCopyFromScheme`
`public DmResultPageing<List<TxtHCopyScheme_Mdl>> getTxtHCopyFromScheme(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code, MAX(sm.name) AS name ,scm.applicableFrom, max(sm.type) as type,max(sm.category) as category , scm.site_code, MAX(s.name) as site_Name FROM schemeMast sm INNER JOIN scheme scm ON scm.schemeCode = sm.code LEFT JOIN siteMast s ON s.code = scm.site_code WHERE scm.entryFrom =:entryFrom GROUP BY scm.site_Code, sm.code, scm.applicableFrom`
- Params: `:entryFrom` ← `String.valueOf(entryFrom.toInt())`
- Other calls: `SchemeCategory.getViaKey`, `SchemeType.getViaKey`

### `fillGridApplicableDate`
`public DmResult<List<KeyFieldExtended>> fillGridApplicableDate(String code)`

- SQL 1: `SELECT MAX(schemeCode) AS code, applicableFrom , MAX(applicableTo) AS applicableTo , max(isStatus) as isStatus FROM scheme WHERE schemeCode = :code and entryFrom = :entryFrom and site_code = :site_code GROUP BY applicableFrom ORDER BY applicableFrom`
- Params: `:code` ← `code`; `:site_code` ← `DmServer.getSsnSiteCode(this)`; `:entryFrom` ← `DmShared.iNull(SchemeEntryFrom.SCHEME_MASTER.toInt()).toString()`
- Other calls: `SCHEME_MASTER.toInt`

### `getMaxApplicableDate`
`public DmResult<KeyFieldExtended> getMaxApplicableDate(String keyCode)`

- SQL 1: `select top 1 sm.code, max(scmd.applicableFrom) as applicableFrom, MAX( sm.name) AS name, max(scmd.applicableTo) as applicableTo, max(scmd.isStatus) as isStatus FROM schemeMast sm LEFT JOIN scheme scmd ON scmd.schemeCode = sm.code WHERE ( sm.code = :code and scmd.entryFrom = :entryFrom and scmd.site_code = :site_code ) GROUP BY sm.code, scmd.applicableFrom ORDER BY scmd.applicableFrom desc`
- Params: `:code` ← `keyCode`; `:entryFrom` ← `DmShared.iNull(SchemeEntryFrom.SCHEME_MASTER.toInt()).toString()`; `:site_code` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `SCHEME_MASTER.toInt`

### `deleteRecordApplicable`
`public DmResult<String> deleteRecordApplicable(String schemeCode, String applicableDate)`

- SQL 1: `select COUNT(*) as cnt from scheme s where s.schemeCode = :schemeCode and s.entryFrom = :entryFrom and s.site_Code = :site_Code and s.isStatus = 1`
- Params: `:schemeCode` ← `schemeCode`; `:entryFrom` ← `DmShared.iNull(entryFrom).toString()`; `:site_Code` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`
- Errors: "Scheme is Closed. Can't delete record! "
- Other calls: `SCHEME_MASTER.toInt`, `Scheme_Server.deleteScheme`

### `fillGrid`
`public DmResult<List<GrdItemDetail_Mdl>> fillGrid(String keyCode, String copySiteCode, String applicableFromDate)`


### `getTxtHUnit`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHUnit(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select um.code,um.unit as unitName ,um.decimalPlaces as unitDecimalPlaces from unitMast um`

### `updateClosingDate`
`public DmResult<String> updateClosingDate(TxtHScheme_Mdl mrTemp, boolean isClose)`

- Other calls: `SCHEME_MASTER.toInt`, `Scheme_Server.updateCloseOpenScheme`

### `getTxtHSchemeGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSchemeGroup(DmHelper helper, boolean blnToEndOfFile, boolean flagShowHelp)`

- SQL 1: `select sgm.code,sgm.name from schemeGroupMast as sgm where sgm.isActive = '1' {…}`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `paperbiz.master.schemeGroupPopup.client`, `paperbiz.master.schemeGroupPopup.shared`, `paperbiz.master.schemeMasterPopup.client`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| deleteRecord | `if (rsTemp.getLong("cnt") > 1)` | "Record have multiple Scheme List. Could not delete this record." |
| deleteRecord | `if (rsTemp.getLong("cnt") > 0)` | "Scheme is Closed. Can't delete record! " |
| saveRecord | `if ((mrTemp.getSharedKeyFieldCode()).equalsIgnoreCase(""))` | "Invalid key." |
| deleteRecordApplicable | `if (rsTemp.getLong("cnt") > 0)` | "Scheme is Closed. Can't delete record! " |

## Notes (hand-written)
_No hand-written notes yet._
