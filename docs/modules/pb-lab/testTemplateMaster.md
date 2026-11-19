# pb-lab / testTemplateMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/lab/src/testTemplateMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: testTemplateMast
- Update tables: testTemplateMast
- Delete cascade (in order): testTemplateDetail, testTemplateMast
- Duplicate checks on save:
  - `SELECT COUNT(description) AS cnt FROM testTemplateMast WHERE description = :name AND code <> :code`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Test Template (Text); Active (Text); rule: GridRow; rule: Test Template Details are mandatory.; rule: One row is mandatory.; rule: <Grid> Is Unmapped. Please Map It.; rule: Duplicate Grid.
- Lookups/suggestions: getTxtHImpurity, getHYesNo
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `ImpurityFormula`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Formula | DmTextArea | `txtFormula` | `150, false` |
| 2 | - | DmGridPaging | `gridFormula` | `GridFormula_PrpAcs, 8, true` |
| 3 | - | DmGridPaging | `gridFormulaValue` | `GridFormulaValue_PrpAcs, 8, true` |

Other widgets (not in tab list): `txtSearchValue` (DmTextBox), `txtFormulaSearchValue` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `gridFormula`: Heads [Text, w120]; code [Text, w50]
- `gridFormulaValue`: Values [Text, w120, name]; code [Text, w50, manualCode]

**Lookup / suggestion / find sources (server RPC called):**
- `gridFormula` → `getLabOverhead`
- `gridFormulaValue` → `getFormulaValues`

**`manageDisplay()` logic (condensed code):**
```java
flwContainer.clear();
if (formulaType.equalsIgnoreCase(FormulaType.FORMULA_FOR_VALUE)) {
flwContainer.add(flwFormulaValue);
toolContainer.addWidget(txtFormulaSearchValue);
txtFormulaSearchValue.setWidth("150px");
} else {
flwContainer.add(flwFormula);
toolContainer.addWidget(txtSearchValue);
txtSearchValue.setWidth("150px");
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`gridFormula.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnToEndOfFile).getLabOverhead(formulaType, helper, blnToEndOfFile, new AsyncCallback<DmResultPageing
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
gridFormula.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`gridFormula.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
gridFormula.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`gridFormula.addCallBack` → `getParam()`
```java
return null;
```

`gridFormulaValue.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnToEndOfFile).getFormulaValues(impurity, helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
gridFormulaValue.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`gridFormulaValue.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
gridFormulaValue.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`gridFormulaValue.addCallBack` → `getParam()`
```java
return null;
```
- Alert: "This formula has been used "
- Alert: "This formula has been used "

### Screen `ImpurityGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Unit | DmTextBoxNumeric | `txtvSno` | `0, false, 2, true` |
| 2 | - | DmTextHelp | `txtHImpurity` | `TxtImpurityHelp_Mdl_PrpAcs, 4, true` |
| 3 | Formula For Value | DmTextBox | `txtFormulaForValue` | `550, false` |
| 4 | Formula For Quantity | DmTextBox | `txtFormulaForQuantity` | `250, false` |
| 5 | Formula For Amount | DmTextBox | `txtFormulaForAmount` | `550, false` |

Other widgets (not in tab list): `txtUnit` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `txtHImpurity`: Impurity [Text, w0, name]; Unit [Text, w80, UnitName]; Value Type [Text, w100]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHImpurity` → `getTxtHImpurity`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtFormulaForQuantity.setEnabled(false)`
- `txtFormulaForAmount.setEnabled(false)`
- `txtFormulaForValue.setEnabled(false)`
- `txtFormulaForQuantity.setEnabled(true)`
- `txtFormulaForAmount.setEnabled(true)`
- `txtFormulaForValue.setEnabled(true)`

**`manageDisplay()` logic (condensed code):**
```java
if (!DmShared.xNull(txtHImpurity.getTag()).equalsIgnoreCase("")) {
txtHImpurity.setFocus(true);
txtFormulaForQuantity.setEnabled(false);
txtFormulaForAmount.setEnabled(false);
txtFormulaForValue.setEnabled(false);
} else {
txtHImpurity.setFocus(true);
txtFormulaForQuantity.setEnabled(true);
txtFormulaForAmount.setEnabled(true);;
txtFormulaForValue.setEnabled(true);;
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHImpurity.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHImpurity(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtIm
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHImpurity).show();
public void onSuccess(DmResultPageing<List<TxtImpurityHelp_Mdl>> result) {
if (result.isStatus()) {
txtHImpurity.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHImpurity).show();
```

`txtHImpurity.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHImpurity.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHImpurity).show();
```

`txtHImpurity.addTextHelpCallBack` → `getSelectedRow()`
```java
if (DmShared.iNull(previousImpurityCode).longValue() != DmShared.iNull(txtHImpurity.getTag()).longValue()) {
resetControls();
if (blnStatus && t1Row != null) {
txtUnit.setText(t1Row.getUnitName());
txtUnit.setTag(t1Row.getManualCode());
txtHImpurity.setTag2nd(DmShared.xNull(t1Row.getValueTypeCode()));
previousImpurityCode = DmShared.xNull(txtHImpurity.getTag());
manageFormulaControls();
```

`txtFormulaForQuantity.addKeyDownHandler` → `onKeyDown()`
```java
if (event.getNativeKeyCode() == KeyCodes.KEY_DELETE || event.getNativeKeyCode() == KeyCodes.KEY_BACKSPACE) {
txtFormulaForQuantity.setTag("");
txtFormulaForQuantity.setText("");
} else if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER || event.getNativeKeyCode() == KeyCodes.KEY_INSERT
|| event.getNativeKeyCode() == KeyCodes.KEY_RIGHT || event.getNativeKeyCode() == KeyCodes.KEY_LEFT
|| event.getNativeKeyCode() == KeyCodes.KEY_UP || event.getNativeKeyCode() == KeyCodes.KEY_DOWN
|| event.getNativeKeyCode() == KeyCodes.KEY_ALT || event.getNativeKeyCode() == KeyCodes.KEY_CTRL
|| event.getNativeKeyCode() == KeyCodes.KEY_SHIFT || event.getNativeKeyCode() == KeyCodes.KEY_ESCAPE
|| event.isAltKeyDown() || event.isControlKeyDown() || event.isShiftKeyDown()) {
} else {
...
```

`txtFormulaForAmount.addKeyDownHandler` → `onKeyDown()`
```java
if (event.getNativeKeyCode() == KeyCodes.KEY_DELETE || event.getNativeKeyCode() == KeyCodes.KEY_BACKSPACE) {
txtFormulaForAmount.setTag("");
txtFormulaForAmount.setText("");
} else if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER || event.getNativeKeyCode() == KeyCodes.KEY_INSERT
|| event.getNativeKeyCode() == KeyCodes.KEY_RIGHT || event.getNativeKeyCode() == KeyCodes.KEY_LEFT
|| event.getNativeKeyCode() == KeyCodes.KEY_UP || event.getNativeKeyCode() == KeyCodes.KEY_DOWN
|| event.getNativeKeyCode() == KeyCodes.KEY_ALT || event.getNativeKeyCode() == KeyCodes.KEY_CTRL
|| event.getNativeKeyCode() == KeyCodes.KEY_SHIFT || event.getNativeKeyCode() == KeyCodes.KEY_ESCAPE
|| event.isAltKeyDown() || event.isControlKeyDown() || event.isShiftKeyDown()) {
} else {
...
```

`txtFormulaForValue.addKeyDownHandler` → `onKeyDown()`
```java
if (event.getNativeKeyCode() == KeyCodes.KEY_DELETE || event.getNativeKeyCode() == KeyCodes.KEY_BACKSPACE) {
txtFormulaForValue.setTag("");
txtFormulaForValue.setText("");
} else if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER || event.getNativeKeyCode() == KeyCodes.KEY_INSERT
|| event.getNativeKeyCode() == KeyCodes.KEY_RIGHT || event.getNativeKeyCode() == KeyCodes.KEY_LEFT
|| event.getNativeKeyCode() == KeyCodes.KEY_UP || event.getNativeKeyCode() == KeyCodes.KEY_DOWN
|| event.getNativeKeyCode() == KeyCodes.KEY_ALT || event.getNativeKeyCode() == KeyCodes.KEY_CTRL
|| event.getNativeKeyCode() == KeyCodes.KEY_SHIFT || event.getNativeKeyCode() == KeyCodes.KEY_ESCAPE
|| event.isAltKeyDown() || event.isControlKeyDown() || event.isShiftKeyDown()) {
} else {
...
```

`impurityGrd1.addCallBack` → `ok()`
```java
txtFormulaForQuantity.setText(name);
```

`impurityGrd2.addCallBack` → `ok()`
```java
txtFormulaForAmount.setText(name);
```

`impurityGrd3.addCallBack` → `ok()`
```java
txtFormulaForValue.setText(name);
```
- Alert: "SNo. is mandatory."
- Alert: "Impurity Name is mandatory"

### Screen `TestTemplateMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Test Template | DmSuggestBox | `txtTestTemplate` | `50, 10, true, "Name", DmEnum.DmDataType.STRING` |

Other widgets (not in tab list): `dmFind` (DmFind), `txtHYesNo` (DmTextHelp), `dmGrd` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `dmFind`: Test Template [Text, w200, name]; Active [Text, w80, isActive]
- `txtHYesNo`: Name [Text, w0]
- `dmGrd`: S.No. [Text, w50]; Parameter [Text, w260]; Manual Code [Text, w75]; Unit [Text, w70]; Formula For Value [Text, w260]; Formula For Quantity [Text, w260]; Formula For Amount [Text, w260]

**Lookup / suggestion / find sources (server RPC called):**
- `txtTestTemplate` → `getTestTemplateSuggestion`
- `txtHYesNo` → `getHYesNo`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtTestTemplate.getText())
- `IsActiveCode` ← DmShared.xNull(txtHYesNo.getTag())
- `IsActiveName` ← DmShared.xNull(txtHYesNo.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtTestTemplate.setText` ← data.getSharedKeyFieldName()
- `txtHYesNo.setText` ← data.getIsActiveName()
- `txtHYesNo.setTag` ← data.getIsActiveCode()
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtHYesNo.setEnabled(false)`

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
if (topControl.getMode().equalsIgnoreCase("Browse")) {
txtHYesNo.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
onAdd();
```

`topControl.addEditHandeller` → `editClicked()`
```java
txtTestTemplate.setFocus(true);
dmGrd.getGrid().getStore().add(new GrdTestTemplate_Mdl());
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

`topControl.addSaveHandeller` → `saveClicked()`
```java
try {
saveValidation();
} catch (Exception ex) {
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(TestTemplateMaster.this);
```

`topControl.addRefershHandeller` → `refreshClicked()`
```java
topControlMoveRec(topControl.getDocId());
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

`dmFind.addHelpGridCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getFind(topControl.getPermissions(), helper, blnToEndOfFile, new AsyncCallback<DmRes
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

`txtTestTemplate.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTestTemplateSuggestion(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtTestTemplate).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtTestTemplate.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtTestTemplate).show();
```

`txtTestTemplate.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtTestTemplate.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtTestTemplate).show();
```

`txtHYesNo.addKeyDownHandler` → `onKeyDown()`
```java
txtHYesNo.addTextHelpCallBack(new DmTextHelp.TextHelpCallBack<KeyFieldExtended>() {
public void populateData(DmHelper helper, boolean blnToEndOfFile, boolean blnCallBackWait) {
SrvAsync.invokeRPC(blnCallBackWait).getHYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExt
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHYesNo).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHYesNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHYesNo).show();
...
```

`txtHYesNo.addKeyDownHandler` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getHYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExt
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHYesNo).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHYesNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHYesNo).show();
```

`txtHYesNo.addKeyDownHandler` → `onSuccess()`
```java
if (result.isStatus()) {
txtHYesNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHYesNo).show();
```

`txtHYesNo.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getHYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExt
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHYesNo).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHYesNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHYesNo).show();
```

`txtHYesNo.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHYesNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHYesNo).show();
```

`gridEdit.addCallBack` → `onOk()`
```java
dmGrd.commitChanges();
```

`gridEdit.addCallBack` → `onDelete()`
```java
dmGrd.commitChanges();
```

**Top control options:** setOtherRequired(false), setRefreshRequired(false), setDisplay(true)

_Print permission "P" is removed for this screen (replace "P" with "*")._

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Alert: "Please Select Test Template."

## Files
| file | lines |
|---|---|
| client/ImpurityFormula.java | 436 |
| client/ImpurityFormula_Dsg.java | 232 |
| client/ImpurityGridEdit.java | 344 |
| client/ImpurityGridEdit_Dsg.java | 187 |
| client/TestTemplateMaster.java | 614 |
| client/TestTemplateMaster_Dsg.java | 187 |
| server/TestTemplateMaster_SrvImpl.java | 675 |
| shared/Find_Mdl.java | 23 |
| shared/FormulaType.java | 14 |
| shared/GrdTestTemplate_Mdl.java | 77 |
| shared/TestTemplateMaster_Mdl.java | 51 |
| shared/TestTemplateMaster_Srv.java | 35 |
| shared/TestTemplateMaster_SrvAsync.java | 35 |
| shared/TestTemplateMaster_Vld.java | 68 |
| shared/TxtImpurityHelp_Mdl.java | 50 |

## UI labels / columns (client)
`Formula`, `Test Template`, `Active`, `SNo.`, `Parameter`, `Unit`, `Formula For Value`, `Formula For Quantity`, `Formula For Amount`

## Models (shared DTOs)
### GrdTestTemplate_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | vsno |
| String | manualCode |
| String | unitName |
| String | formulaForQuantity |
| String | formulaForAmount |
| String | formulaForValue |
| String | valueTypeCode |

### TestTemplateMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | isActiveName |
| String | isActiveCode |
| ModifyReason_Mdl | reasonInfo_Mdl |
| List<GrdTestTemplate_Mdl> | grdData |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | isActive |

### TxtImpurityHelp_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | unitName |
| String | manualCode |
| String | valueTypeCode |
| String | valueTypeText |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator | UserPermission userPermission | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<TestTemplateMaster_Mdl> |
| saveRecord | TestTemplateMaster_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey,ModifyReason_Mdl reasonInfo_Mdl | DmResult<String> |
| getFind | UserPermission userPermission, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getTxtHImpurity | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtImpurityHelp_Mdl>> |
| getTestTemplateSuggestion | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getLabOverhead | String formulaType, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getFormulaValues | String impurity, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getHYesNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `TestTemplateMaster_Vld`
```java
public class TestTemplateMaster_Vld {
    public static void chkSave(TestTemplateMaster_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getSharedKeyFieldName().trim(), "Test Template", 100);
        DmShared.isValidText(mrTemp.getIsActiveCode().trim(), "Active", 200);
        boolean flag = false;
        List<GrdTestTemplate_Mdl> listItemDetail = mrTemp.getGrdData();
        for (GrdTestTemplate_Mdl detail_Mdl : listItemDetail) {
            if (!DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()).trim().equalsIgnoreCase("")) {
                flag = true;
                if (DmShared.xNull(detail_Mdl.getVsno()).trim().equalsIgnoreCase("")) {
                    throw new DmException(300, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Found value is manadatory in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
            }
        }
        if (!flag) {
            throw new DmException(300, "Test Template Details are mandatory.");
        }
    }
    public static boolean checkGrid(List<GrdTestTemplate_Mdl> grdParameter) {
        try {
            if (grdParameter.size() <= 1) {
                if (DmShared.xNull(grdParameter.get(0).getSharedKeyFieldName()).equalsIgnoreCase("")) {
                    throw new DmException(0, "One row is mandatory.");
                }
            }
            boolean blnRtn = true;
            String sql;
            for (int i = 0; i < grdParameter.size(); i++) {
                if (!DmShared.xNull(grdParameter.get(i).getSharedKeyFieldName()).equalsIgnoreCase("")) {
                    if (DmShared.xNull(grdParameter.get(i).getSharedKeyFieldCode()).equalsIgnoreCase("")) {
                        blnRtn = false;
                        throw new DmException(0, "<Grid> Is Unmapped. Please Map It.");
                    }
                    for (int j = i + 1; j < grdParameter.size(); j++) {
                        if (!DmShared.xNull(grdParameter.get(i).getSharedKeyFieldName()).equalsIgnoreCase("")) {
                            if (DmShared.xNull(grdParameter.get(i).getSharedKeyFieldName()).equalsIgnoreCase(DmShared.xNull(grdParameter.get(j).getSharedKeyFieldName()))) {
                                blnRtn = false;
                                throw new DmException(0, "Duplicate Grid.");
                            }
                        }
                    }
                }
                if (!blnRtn) {
                    break;
                }
            }
            return blnRtn;
        } catch (Exception e) {
            throw new DmException(0, e.getMessage());
        }
    }
}
```

## Server `TestTemplateMaster_SrvImpl.java` (675 lines)
Helpers used: `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnUserName`, `DmServer.getUserWithDateTime`, `DmServer.setSsnOldData`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(UserPermission userPermission)`

- SQL 1: `SELECT code,description FROM testTemplateMast tm`

### `getMoveRec`
`public DmResult<TestTemplateMaster_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT tm.code, tm.description as name,isActive, tm.preparedBy,tm.preparedDt,tm.u_Name, tm.u_EntDt,tm.preparedByIP from testTemplateMast tm where tm.code=:code`
- SQL 2: `Select tmd.code,tmd.sNo,tmd.impurityCode,tmd.formulaForQuantity, tmd.formulaForValue, tmd.formulaForAmount,im.name as impurityName,um.code as unitCode,um.unit as unitName, im.manualCode, im.valueType From testTemplateDetail tmd Left Join impurityMast im on tmd.impurityCode=im.code Left Join unitMast um on im.unitCode=um.code where tmd.code=:code order by sNo`
- Params: `:code` ← `strKey`; `:code` ← `strKey`

### `saveRecord`
`public DmResult<String> saveRecord(TestTemplateMaster_Mdl mrTemp)`

- SQL 1: `SELECT COUNT(description) AS cnt FROM testTemplateMast WHERE description = :name AND code <> :code`
- SQL 2: `Insert Into testTemplateMast(code ,description,isActive, preparedBy,preparedDt,preparedByIP) Values (:code ,:name,:isActive,:preparedBy,:preparedDt,:preparedByIP)`
- SQL 3: `Update testTemplateMast Set Description = :name, isActive = :isActive, u_Name=:u_Name, u_Entdt = :uEntDt Where code = :code`
- SQL 4: `DELETE FROM testTemplateDetail WHERE code = :code`
- Params: `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName()).toLowerCase()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:isActive` ← `DmShared.xNull(mrTemp.getIsActiveCode())`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:isActive` ← `DmShared.xNull(mrTemp.getIsActiveCode())`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:uEntDt` ← `timestamp`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:sNO` ← `DmShared.xNull(detail.getVsno())`; `:impurityCode` ← `DmShared.foriegnKey(detail.getSharedKeyFieldCode())`; `:formulaQty` ← `DmShared.xNull(detail.getFormulaForQuantity())`; `:formulaAmount` ← `DmShared.xNull(detail.getFormulaForAmount())`; `:formulaValue` ← `DmShared.xNull(detail.getFormulaForValue())`
- Errors: "Invalid key." | "Test Description already exists."
- Other calls: `ModifyReason_Server.saveNew`, `TestTemplateMaster_Vld.chkSave`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl reasonInfo_Mdl)`

- SQL 1: `Delete From testTemplateDetail Where code=:code`
- SQL 2: `Delete From testTemplateMast Where code=:code`
- Params: `:code` ← `strKey`; `:code` ← `strKey`
- Other calls: `ModifyReason_Server.saveNew`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(UserPermission userPermission, DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT tm.code, tm.description as name, case when tm.isActive = 0 then 'No' when tm.isActive = 1 then 'Yes' end as isActive from testTemplateMast as tm`

### `getTxtHImpurity`
`public DmResultPageing<List<TxtImpurityHelp_Mdl>> getTxtHImpurity(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `Select im.code, im.name, im.manualCode, um.unit as unitName,im.valueType from impurityMast im Left Join unitMast um on im.UnitCode=um.code where im.nature= :nature And im.isActive = 1`
- Params: `:nature` ← `DmShared.iNull(STEnum.ImpurityNature.LAB.getId()).toString()`
- Other calls: `ImpurityValueType.getViaKey`, `LAB.getId`

### `getTestTemplateSuggestion`
`public DmResultPageing<List<String>> getTestTemplateSuggestion(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select description as Name From testTemplateMast`

### `getHYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getHYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getLabOverhead`
`public DmResultPageing<List<KeyFieldExtended>> getLabOverhead(String formulaType, DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `ACCEPT_QTY.getValue`, `LabOverhead.values`

### `getFormulaValues`
`public DmResultPageing<List<KeyFieldExtended>> getFormulaValues(String impurity, DmHelper helper, boolean blnToEndOfFile )`

- SQL 1: `select im.code, im.name, im.manualCode from impurityMast im where im.valueType = :valueType AND im.code <> :impurity`
- Params: `:valueType` ← `DmShared.iNull(STEnum.ImpurityValueType.RANGE.getId()).toString()`; `:impurity` ← `DmShared.xNull(impurity)`
- Other calls: `RANGE.getId`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextArea.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.server`, `dataman.dmToolkit.modifyReason.shared`, `paperbiz.lab.testTemplateMaster.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Test Description already exists." |

## Notes (hand-written)
_No hand-written notes yet._
