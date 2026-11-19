# dm-utility / termsConditionMast

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/utility/src/termsConditionMast/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: termsConfig, termsConfigDetail
- Update tables: termsConfig, termsConfigDetail
- Delete cascade (in order): termsConfigDetail
- Duplicate checks on save:
  - `Select Count(name) As Cnt From termsConfig Where name = :name And code <> :code`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: 'Voucher Group' (Text); 'Template Name' (Text); rule: GridRow; rule: 'Terms & Conditions' are mandatory.
- Lookups/suggestions: getSuggestion, getTxtHTable, getTxtHVoucherGroup, getTxtHSaveInField, getTxtHEntryType, getTxtHCopyTemplate
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `CopyTermsPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Template | DmTextHelp | `txtHCopyTerms` | `TxtHCopyTerms_PrpAcs, 6, true` |

**Grid / popup columns** (header, width, db field):
- `txtHCopyTerms`: Name [Text, w200, name]; Voucher Group [Text, w240, voucherGroupName]; Manual Code [Text, w100, voucherGroupManualCode]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHCopyTerms` → `getTxtHCopyTemplate`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHCopyTerms.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(true).getTxtHCopyTemplate(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHCopyTerm
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<TxtHCopyTerms_Mdl>> result) {
if (result.isStatus()) {
txtHCopyTerms.setData(result.getData(), result.getPaging());
```

`txtHCopyTerms.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHCopyTerms.setData(result.getData(), result.getPaging());
```

`txtHCopyTerms.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
```
- Alert: "Template is mandatory!"

### Screen `TermsConditionGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Terms & Condition | DmTextHelp | `txtHTermCondition` | `KeyField_PrpAcs, 10, true` |
| 2 | Manual Code | DmTextBox | `txtManualCode` | `50, false` |
| 3 | Save In Field | DmTextHelp | `txtHSaveInField` | `KeyField_PrpAcs, 10, false` |
| 4 | Data Type | DmTextHelp | `txtHDataType` | `KeyField_PrpAcs, 5, true` |
| 5 | Default Value | DmTextBox | `txtDefaultValue` | `255, false` |
| 6 | - | DmTextBoxNumeric | `txtDefaultValueInt` | `0, true, 12, false` |
| 7 | - | DmTextBoxNumeric | `txtDefaultValueDouble` | `2, true, 12, false` |
| 8 | Is Mandatory | DmTextHelp | `txtHMandatory` | `KeyField_PrpAcs, 3, true` |

Other widgets (not in tab list): `txtDefaultCode` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `txtHTermCondition`: Terms & Condition [Text, w0, name]
- `txtHDataType`: Data Type [Text, w0, Data Type]
- `txtHMandatory`: Name [Text, w100, Name]
- `txtHSaveInField`: Column [Text, w10, columnName]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHTermCondition` → `getTermsCondition`
- `txtHSaveInField` → `getTxtHSaveInField`
- `txtHDataType` → `getDataType`
- `txtHMandatory` → `getYesNo`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtManualCode.setEnabled(false)`
- `txtHTermCondition.setEnabled(isNewMode || isEditModeNewLine)`
- `txtHDataType.setEnabled(isNewMode || isEditModeNewLine)`
- `txtHSaveInField.setEnabled(!DmShared.xNull(header.txtHTableName.getText()).tr)`
- `txtHMandatory.setEnabled(isNewMode || isEditMode)`

**`manageDisplay()` logic (condensed code):**
```java
boolean isNewMode = header.topControl.getMode().equalsIgnoreCase("New");
boolean isEditMode = header.topControl.getMode().equalsIgnoreCase("Edit");
boolean isEditModeNewLine = isEditMode && DmShared.iNull(vSNo).intValue() == 0;
txtManualCode.setEnabled(false);
txtHTermCondition.setEnabled(isNewMode || isEditModeNewLine);
txtHDataType.setEnabled(isNewMode || isEditModeNewLine);
txtHSaveInField.setEnabled(!DmShared.xNull(header.txtHTableName.getText()).trim().equalsIgnoreCase("")
&& (isNewMode || isEditModeNewLine));
txtHMandatory.setEnabled(isNewMode || isEditMode);
if (isNewMode) {
txtHTermCondition.setFocus(true);
manageDefaultTextWidget(isEditMode);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHTermCondition.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTermsCondition(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Key
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHTermCondition).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHTermCondition.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTermCondition).show();
```

`txtHTermCondition.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHTermCondition.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTermCondition).show();
```

`txtHTermCondition.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHTermCondition.getTag2nd()).trim().equalsIgnoreCase(DmShared.xNull(txtHTermCondition.getTag()))) 
resetTermsCondition();
txtHTermCondition.setTag2nd(DmShared.xNull(txtHTermCondition.getTag()));
if (blnStatus && t1Row != null) {
txtManualCode.setText(t1Row.getSharedModifiedBy());
```

`txtHSaveInField.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHSaveInField(helper, blnToEndOfFile, DmShared.xNull(termsCondition_Mdl.txtHTab
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSaveInField).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHSaveInField.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSaveInField).show();
```

`txtHSaveInField.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSaveInField.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSaveInField).show();
```

`txtHDataType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getDataType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDataType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHDataType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDataType).show();
```

`txtHDataType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHDataType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDataType).show();
```

`txtHDataType.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHDataType.getTag2nd()).trim().equalsIgnoreCase(DmShared.xNull(txtHDataType.getTag()))) {
resetDataType();
txtHDataType.setTag2nd(DmShared.xNull(txtHDataType.getTag()));
manageDefaultTextWidget();
```

`txtHMandatory.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExte
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHMandatory).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHMandatory.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHMandatory).show();
```

`txtHMandatory.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHMandatory.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHMandatory).show();
```
- Alert: "'Terms & Condition' is mandatory!"
- Alert: "Data Type is mandatory!"
- Alert: "'Is Mandatory' is mandatory!"

### Screen `TermsConditionMast`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Voucher Group | DmTextHelp | `txtHVoucherGroup` | `KeyField_PrpAcsAll, 5, true` |
| 2 | Template Name | DmSuggestBox | `txtName` | `100, 10, true, "name", DmDataType.STRING` |
| 3 | Table Name | DmTextHelp | `txtHTableName` | `KeyField_PrpAcs, 10, false` |

Other widgets (not in tab list): `gridVoucherType` (DmGrid), `gridTermsCondition` (DmGrid), `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `txtHVoucherGroup`: Name [Text, w0, name]; Manual Code [Text, w100, manualCode]
- `txtHTableName`: Table [Text, w150, name]
- `gridVoucherType`: Voucher Type [Text, w1]; Short Name [Text, w80]
- `gridTermsCondition`: T & C [Text, w250]; ManualCode [Text, w100]; Data Type [Text, w80]; Default Value [Text, w350]; Save In Field [Text, w120]; Mandatory [Text, w100]
- `dmFind`: Name [Text, w300, name]; Voucher Group [Text, w300, voucherGroup]; Table Name [Text, w200, tableName]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHVoucherGroup` → `getTxtHVoucherGroup`
- `txtHTableName` → `getTxtHTable`
- `copyTermsPopup` → `copyTemplate`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtName.getText())
- `TableCode` ← DmShared.xNull(txtHTableName.getTag())
- `TableName` ← DmShared.xNull(txtHTableName.getText())
- `VoucherGroupCode` ← DmShared.xNull(txtHVoucherGroup.getTag())
- `VoucherGroupName` ← DmShared.xNull(txtHVoucherGroup.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- `txtName.setText` ← data.getSharedKeyFieldName()
- `txtHVoucherGroup.setTag` ← data.getVoucherGroupCode()
- `txtHVoucherGroup.setText` ← data.getVoucherGroupName()
- `txtHVoucherGroup.setTag2nd` ← data.getVoucherGroupCode()
- `txtHTableName.setTag` ← data.getTableName()
- `txtHTableName.setText` ← data.getTableName()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtHVoucherGroup.setEnabled(isNewMode)`

**On New click:**
- `clear()`
- `manageDisplay()`
- `btnCopy.setVisible(false)`

**`manageDisplay()` logic (condensed code):**
```java
boolean isNewMode = topControl.getMode().equalsIgnoreCase("New");
boolean isEditMode = topControl.getMode().equalsIgnoreCase("Edit");
txtHVoucherGroup.setEnabled(isNewMode);
if (isNewMode) {
txtHVoucherGroup.setFocus(true);
} else if (isEditMode) {
txtName.setFocus(true);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
clear();
btnCopy.setVisible(false);
gridVoucherType.getGrid().getStore().clear();
gridVoucherType.getGrid().getStore().add(new GrdVoucherType_Mdl());
gridTermsCondition.getGrid().getStore().clear();
gridTermsCondition.getGrid().getStore().add(new GrdTermsCondition_Mdl());
manageDisplay();
```

`topControl.addEditHandeller` → `editClicked()`
```java
btnCopy.setVisible(true);
gridVoucherType.getGrid().getStore().add(new GrdVoucherType_Mdl());
gridTermsCondition.getGrid().getStore().add(new GrdTermsCondition_Mdl());
manageDisplay();
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
btnCopy.setVisible(true);
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(TermsConditionMast.this);
```

`topControl.addOtherHandeller` → `otherClicked()`
```java
if (topControl.getMode().equalsIgnoreCase("New")) {
menu.show(topControl.getButton("Other"));
```

`txtHVoucherGroup.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHVoucherGroup(topControl.getMode().equalsIgnoreCase("New") ? "" : topControl.g
helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExtended>>>() {
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHVoucherGroup).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHVoucherGroup.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHVoucherGroup).show();
```

`txtHVoucherGroup.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHVoucherGroup.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHVoucherGroup).show();
```

`txtHVoucherGroup.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHVoucherGroup.getTag2nd()).trim().equalsIgnoreCase(DmShared.xNull(txtHVoucherGroup.getTag()))) {
resetVoucherGroup();
txtHVoucherGroup.setTag2nd(DmShared.xNull(txtHVoucherGroup.getTag()));
fillVoucherType();
if (blnStatus && tRow != null) {
txtName.setText(DmShared.xNull(txtHVoucherGroup.getText()));
fillVoucherType();
```

`txtHTableName.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHTable(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyField
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHTableName).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHTableName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTableName).show();
```

`txtHTableName.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHTableName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTableName).show();
```

`dmFind.addHelpGridCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getFind(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Find_Mdl>>>()
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), dmFind.getTextBox()).show();
public void onSuccess(DmResultPageing<List<Find_Mdl>> result) {
if (result.isStatus()) {
dmFind.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), dmFind.getTextBox()).show();
```

`dmFind.addHelpGridCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
dmFind.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), dmFind.getTextBox()).show();
```

`dmFind.addHelpGridCallBack` → `getSelectedRow()`
```java
flwForm.remove(dmFind);
flwForm.add(vrtInnerForm);
if (blnStatus) {
topControlMoveRec(tRow.getSharedKeyFieldCode());
```

`copyTermsPopup.addCallBack` → `onOk()`
```java
fillOldSalesOrderDetail(code);
```

`copyTermsPopup.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
TermsConditionMast_Mdl mrTemp = result.getData();
List<GrdTermsCondition_Mdl> termsCondition_Mdls = mrTemp.getGrdTermsCondition_Mdl();
gridTermsCondition.getGrid().getStore().clear();
gridTermsCondition.getGrid().getStore().addAll(termsCondition_Mdls);
gridTermsCondition.getGrid().getStore().add(new GrdTermsCondition_Mdl());
fillItemDetail(termsCondition_Mdls);
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`termsConditionGridEdit.addCallBack` → `onOk()`
```java
gridTermsCondition.commitChanges();
```

`termsConditionGridEdit.addCallBack` → `onDelete()`
```java
gridTermsCondition.commitChanges();
```

**Top control options:** setRefreshRequired(false), setDisplay(true)

**Menu items (Other button):** Copy Template

_Print permission "P" is removed for this screen (replace "P" with "*")._
- Confirm: "Are you sure? You want to delete this record."
- Alert: "Please select 'Voucher Group!'"

## Files
| file | lines |
|---|---|
| client/CopyTermsPopup.java | 153 |
| client/CopyTermsPopup_Dsg.java | 143 |
| client/TermsConditionGridEdit.java | 473 |
| client/TermsConditionGridEdit_Dsg.java | 183 |
| client/TermsConditionMast.java | 611 |
| client/TermsConditionMast_Dsg.java | 302 |
| server/TermsConditionMast_SrvImpl.java | 1072 |
| shared/Find_Mdl.java | 32 |
| shared/GrdTermsCondition_Mdl.java | 95 |
| shared/GrdVoucherType_Mdl.java | 41 |
| shared/TermsConditionMast_Mdl.java | 91 |
| shared/TermsConditionMast_Srv.java | 46 |
| shared/TermsConditionMast_SrvAsync.java | 46 |
| shared/TermsConditionMast_Vld.java | 42 |
| shared/TxtHCopyTerms_Mdl.java | 41 |

## UI labels / columns (client)
`Template`, `Terms & Condition`, `Manual Code`, `Save In Field`, `Data Type`, `Default Value`, `Is Mandatory`, `Voucher Group`, `Template Name`, `Table Name`

## Models (shared DTOs)
### TxtHCopyTerms_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | voucherGroupCode |
| String | voucherGroupName |
| String | manualCode |

### TermsConditionMast_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | EntryTypeCode |
| String | EntryTypeName |
| String | tableCode |
| String | tableName |
| String | voucherGroupCode |
| String | voucherGroupName |
| List<GrdTermsCondition_Mdl> | grdTermsCondition_Mdl |
| List<GrdVoucherType_Mdl> | grdVoucherType_Mdl |

### GrdVoucherType_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | shortName |
| String | description |
| String | sNo |

### GrdTermsCondition_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | mandatoryCode |
| String | mandatoryName |
| String | defaultValue |
| String | defaultCode |
| String | dataTypeCode |
| String | dataTypeName |
| String | saveInField |
| String | vSNo |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | voucherGroup |
| String | tableName |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getSuggestion | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getMoveRec | String strKey | DmResult<TermsConditionMast_Mdl> |
| saveRecord | TermsConditionMast_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey | DmResult<String> |
| deleteRecord | String strKey, String vSNo | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getTxtHTable | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVoucherGroup | String keyCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSaveInField | DmHelper helper, boolean blnToEndOfFile, String tableName | DmResultPageing<List<KeyFieldExtended>> |
| getTermsCondition | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDataType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getYesNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHEntryType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| fillVoucherTypeGrid | String groupCode | DmResult<TermsConditionMast_Mdl> |
| getTxtHCopyTemplate | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHCopyTerms_Mdl>> |
| copyTemplate | String code | DmResult<TermsConditionMast_Mdl> |

## Validator `TermsConditionMast_Vld`
```java
public class TermsConditionMast_Vld {
    public static void chkSave(TermsConditionMast_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getVoucherGroupCode(), "'Voucher Group'", 100);
        DmShared.isValidText(mrTemp.getSharedKeyFieldName(), "'Template Name'", 200);
        boolean flag = false;
        List<GrdTermsCondition_Mdl> listItemDetail = mrTemp.getGrdTermsCondition_Mdl();
        if (listItemDetail != null && !listItemDetail.isEmpty()) {
            for (int i = 0; i < listItemDetail.size(); i++) {
                GrdTermsCondition_Mdl detail_Mdl = listItemDetail.get(i);
                if (!DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()).trim().equalsIgnoreCase("")) {
                    flag = true;
                    if (DmShared.iNull(detail_Mdl.getSharedKeyFieldCode()).intValue() == 0) {
                        throw new DmException(300, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please Fill 'Terms & Conditions' in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                    if (DmShared.xNull(detail_Mdl.getDataTypeCode()).trim().equalsIgnoreCase("")) {
                        throw new DmException(300, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please Fill 'Data Type' in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                    if (DmShared.xNull(detail_Mdl.getMandatoryCode()).trim().equalsIgnoreCase("")) {
                        throw new DmException(300, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please Fill 'Is Mandatory' in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
            }
        }
        if (!flag) {
            throw new DmException(500, "'Terms & Conditions' are mandatory.");
        }
    }
}
```

## Server `TermsConditionMast_SrvImpl.java` (1072 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.setSsnOldData`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.vNull`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `Select code From termsConfig Order By name;`

### `getSuggestion`
`public DmResultPageing<List<String>> getSuggestion(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select User_Name From UserMast`

### `getMoveRec`
`public DmResult<TermsConditionMast_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT tcm.code, tcm.name , tcm.voucherGroupCode, vg.name AS voucherGroupName , tcm.tableName, tcm.u_EntDt_LatestLine , tcm.u_EntDt, tcm.u_Name, tcm.preparedBy, tcm.preparedDt , tcm.transferDt, tcm.preparedByIP FROM termsConfig tcm LEFT JOIN voucherGroup vg ON vg.code = tcm.voucherGroupCode WHERE tcm.code = :code`
- SQL 2: `SELECT tcd.code, tcd.v_SNo, tcd.termsTypeCode , tcm.name AS termsConditionName, tcm.manualCode AS tcManualcode , tcd.dataTypeCode, tcd.defaultValue, tcd.saveInField , tcd.mandatory FROM termsConfigDetail tcd LEFT JOIN termsTypeMast tcm ON tcm.code = tcd.termsTypeCode WHERE (tcd.code = :code)`
- Params: `:code` ← `DmShared.iNull(strKey).toString()`; `:code` ← `DmShared.xNull(strKey)`
- Other calls: `DmDataType.valueOf`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, String vSNo)`

- SQL 1: `DELETE FROM termsConfigDetail WHERE code = :code {…} AND v_SNo = :v_SNo {…}`
- SQL 2: `DELETE FROM termsConfigDetail WHERE code = :code`
- Params: `:code` ← `DmShared.xNull(strKey)`; `:v_SNo` ← `DmShared.iNull(vSNo).intValue()`; `:code` ← `DmShared.xNull(strKey)`

### `saveRecord`
`public DmResult<String> saveRecord(TermsConditionMast_Mdl mrTemp)`

- SQL 1: `Select Count(name) As Cnt From termsConfig Where name = :name And code <> :code`
- SQL 2: `Insert Into termsConfig(code ,name ,tableName, voucherGroupCode ,preparedBY ,U_EntDt ,preparedByIP ,preparedDt) Values (:code ,:name ,:tableName, :voucherGroupCode ,:preparedBY ,:preparedDt ,:preparedByIP ,:preparedDt)`
- SQL 3: `Update termsConfig Set name = :name, tableName = :tableName, voucherGroupCode = :voucherGroupCode, transferDt = :transferDt, U_Name = :u_name, U_EntDt = :U_EntDt Where code = :code`
- SQL 4: `Update termsConfigDetail SET defaultValue = :defaultValue, mandatory = :mandatory WHERE code = :code AND v_SNo = :v_SNo`
- SQL 5: `SELECT MAX(tcd.v_SNo) AS v_SNo FROM termsConfigDetail tcd WHERE tcd.code = :code`
- Params: `:name` ← `mrTemp.getSharedKeyFieldName()`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:name` ← `mrTemp.getSharedKeyFieldName()`; `:tableName` ← `mrTemp.getTableName()`; `:voucherGroupCode` ← `mrTemp.getVoucherGroupCode()`; `:preparedBY` ← `DmServer.getSsnUserName(this)`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:preparedDt` ← `timestamp`; `:name` ← `mrTemp.getSharedKeyFieldName()`; `:tableName` ← `mrTemp.getTableName()`; `:voucherGroupCode` ← `mrTemp.getVoucherGroupCode()`; `:transferDt` ← `null`; `:u_name` ← `DmServer.getSsnUserName(this)`; `:U_EntDt` ← `timestamp`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:v_SNo` ← `DmShared.iNull(condition_Mdl.getvSNo()).intValue()`; `:defaultValue` ← `DmShared.xNull(condition_Mdl.getDefaultValue())`; `:mandatory` ← `DmShared.xNull(condition_Mdl.getMandatoryCode())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`
- Errors: "Invalid key." | "This 'Terms & Conditions' Already Exists."
- Other calls: `TermsConditionMast_Vld.chkSave`

### `saveTermsConfigDetail`
`private void saveTermsConfigDetail(String docId, int vSNo, GrdTermsCondition_Mdl condition_Mdl, Connection cnTemp) throws SQLException`

- SQL 1: `INSERT INTO termsConfigDetail (code, v_SNo, termsTypeCode, dataTypeCode , defaultValue, saveInField, mandatory) VALUES (:code, :v_SNo, :termsTypeCode, :dataTypeCode, :defaultValue, :saveInField, :mandatory)`
- Params: `:code` ← `DmShared.xNull(docId)`; `:v_SNo` ← `vSNo`; `:termsTypeCode` ← `DmShared.xNull(condition_Mdl.getSharedKeyFieldCode())`; `:dataTypeCode` ← `DmShared.xNull(condition_Mdl.getDataTypeCode())`; `:defaultValue` ← `DmShared.xNull(condition_Mdl.getDefaultValue())`; `:saveInField` ← `DmShared.xNull(condition_Mdl.getSaveInField())`; `:mandatory` ← `DmShared.xNull(condition_Mdl.getMandatoryCode())`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT tc.code, tc.name , vg.name AS voucherGroup , tc.tableName FROM termsConfig tc LEFT JOIN voucherGroup vg ON vg.code = tc.voucherGroupCode`

### `getTxtHTable`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTable(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT so.name FROM {…} .sys.objects so WHERE so.Type = 'U' AND so.name != 'termsTrans'`

### `getTxtHVoucherGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVoucherGroup(String keyCode, DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT vg.code, vg.name, vg.manualCode FROM voucherGroup vg LEFT JOIN termsConfig tc ON tc.voucherGroupCode = vg.code WHERE ISNULL(tc.voucherGroupCode, '') = '' {…}`
- Params: `:keyCode` ← `DmShared.xNull(keyCode)`

### `getTxtHSaveInField`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSaveInField(DmHelper helper, boolean blnToEndOfFile, String tableName)`

- SQL 1: `SELECT sc.name AS columnName, st.name AS dataType, sc.max_length FROM sys.columns AS sc RIGHT OUTER JOIN {…} .sys.objects AS so ON so.object_id = sc.object_id LEFT OUTER JOIN {…} .sys.types AS st ON sc.user_type_id = st.user_type_id WHERE (so.name = ' {…} ') AND (sc.name not in ('docId','V_SNo')) AND (sc.name LIKE 'terms_%')`

### `getTermsCondition`
`public DmResultPageing<List<KeyFieldExtended>> getTermsCondition(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT tcm.code, tcm.name, tcm.manualCode FROM termsTypeMast tcm`

### `getDataType`
`public DmResultPageing<List<KeyFieldExtended>> getDataType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `DmDataType.valueOf`, `DmDataType.values`

### `getYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHEntryType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHEntryType(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT VT.V_Type AS CODE,CONCAT(VT.Description,' (', VT.short_Name,')' )AS NAME ,VT.category,VT.Description FROM Voucher_Type VT`

### `fillVoucherTypeGrid`
`public DmResult<TermsConditionMast_Mdl> fillVoucherTypeGrid(String groupCode)`


### `getVoucherTypeGrid`
`public List<GrdVoucherType_Mdl> getVoucherTypeGrid(String groupCode, Connection cnTemp)`

- SQL 1: `SELECT vt.v_Type AS code , vt.Description AS name , vt.short_Name FROM voucher_Type vt WHERE vt.voucherGroupCode = :groupCode`
- Params: `:groupCode` ← `DmShared.xNull(groupCode)`

### `getTxtHCopyTemplate`
`public DmResultPageing<List<TxtHCopyTerms_Mdl>> getTxtHCopyTemplate(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT tc.code, tc.name , tc.voucherGroupCode, vg.name AS voucherGroupName , vg.manualCode AS voucherGroupManualCode FROM termsConfig tc LEFT JOIN voucherGroup vg ON vg.code = tc.voucherGroupCode`

### `copyTemplate`
`public DmResult<TermsConditionMast_Mdl> copyTemplate(String code)`

- SQL 1: `SELECT tcd.termsTypeCode , ttm.name AS termTypeName , ttm.manualCode , tcd.dataTypeCode , tcd.defaultValue , tcd.mandatory , tcd.saveInField FROM termsConfigDetail tcd LEFT JOIN termsTypeMast ttm ON ttm.code = tcd.termsTypeCode WHERE tcd.code = :code ORDER BY tcd.v_SNo`
- Params: `:code` ← `DmShared.xNull(code)`
- Other calls: `DmDataType.valueOf`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.libraryMenu.self.shared`, `dataman.utility.termsConditionMast.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (rsTemp.next() && rsTemp.getLong("cnt") > 0)` | "This 'Terms & Conditions' Already Exists." |

## Notes (hand-written)
_No hand-written notes yet._
