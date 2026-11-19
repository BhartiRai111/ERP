# pb-master / prefixCounterMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/master/src/prefixCounterMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: productPrefixMast, ProductPrefixDetail
- Update tables: productPrefixMast
- Delete cascade (in order): ProductPrefixDetail, ProductPrefixMast
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Type (Text); Financial Year (Text); Machine (Text); Manual Prefix (Text); Prefix Type (Text); Format (Text); Formula String (Text); rule: GridRow; rule: Prefix Counter Details are mandatory.
- Lookups/suggestions: getTxtHMachine
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `FormulaPopUp`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Formula | DmTextArea | `txtFormula` | `150, true` |

Other widgets (not in tab list): `formulaGrd` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `formulaGrd`: Code [Text, w80]; Name [Text, w140]
- Alert: "Formula is mandatory."
- Alert: "This number format has been used in number format"

### Screen `PrefixCounterMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Type | DmTextHelp | `txtHProductType` | `com.google.gwt.core.shared.KeyField_PrpAcs, 7, true, true` |
| 2 | Financial Year | DmTextHelp | `txtHFinancialYear` | `FinanceYear_PrpAcs, 4, true` |
| 3 | Year Prefix | DmTextBox | `txtYearPrefix` | `100, true` |
| 4 | Start Date | DmTextBox | `txtStartDate` | `11, true` |
| 5 | End Date | DmTextBox | `txtEndDate` | `11, true` |
| 6 | MachinePrefixY/N | DmTextHelp | `txtHMachinePrefixYN` | `com.google.gwt.core.shared.KeyField_PrpAcs, 3, true, false` |
| 7 | Machine | DmTextHelpMulti | `txtHMachine` | `KeyField_PrpAcs, 5, false` |
| 8 | Manual Prefix | DmTextBox | `txtManualPrefix` | `20, true` |
| 9 | Prefix Type | DmTextHelp | `txtHPrefixType` | `com.google.gwt.core.shared.KeyField_PrpAcs, 3, true, false` |
| 10 | Format | DmTextBox | `txtFormatDigits` | `2, true` |
| 11 | Formula String | DmTextBox | `txtFormulaString` | `20, false` |

Other widgets (not in tab list): `gui` (DmGUI), `dmFind` (DmFind), `grdItemDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHFinancialYear`: Financial Year [Text, w100, financeYear]; Year Prefix [Text, w80, yearPrefix]; Start Dt [Text, w80, startDate]; End Dt [Text, w80, endDate]
- `txtHProductType`: Type [Text, w0, description]
- `txtHPrefixType`: Name [Text, w0, name]
- `txtHMachinePrefixYN`: Name [Text, w0, name]
- `txtHMachine`: Name [Text, w200, name]
- `dmFind`: Product Type [Text, w220]; Financial Year [Text, w100, financialYear]; Year Prefix [Text, w80, yearPrefix]; Prefix Type [Text, w80, prefixTypeName]; Machine Prefix [Text, w90]; Format [Text, w70, intFormat]
- `grdItemDetail`: Year Prefix [Text, w100]; Month Prefix [Text, w100]; Manual Prefix [Text, w100]; Start Date [Text, w150]; End Date [Text, w150]; Format [Text, w150]; Counter [Text, w100]; Prefix Type [Text, w100]; Machine Name [Text, w150]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHProductType` → `getProductType`
- `txtHFinancialYear` → `getTxtFinancialYear`
- `txtHMachinePrefixYN` → `getTxtMachinePrefixYN`
- `txtHPrefixType` → `getTxtPrefixType`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `ProductTypeTag` ← DmShared.xNull(txtHProductType.getTag())
- `ProductTypeText` ← DmShared.xNull(txtHProductType.getText())
- `PrefixTypeTag` ← DmShared.xNull(txtHPrefixType.getTag())
- `PrefixTypeText` ← DmShared.xNull(txtHPrefixType.getText())
- `MachineCode` ← DmShared.xNull(txtHMachine.getTag())
- `MachineName` ← DmShared.xNull(txtHMachine.getText())
- `MachinePrefixYTag` ← DmShared.xNull(txtHMachinePrefixYN.getTag())
- `MachinePrefixYText` ← DmShared.xNull(txtHMachinePrefixYN.getText())
- `FinancialYearTag` ← DmShared.xNull(txtHFinancialYear.getTag())
- `FinancialYearText` ← DmShared.xNull(txtHFinancialYear.getText())
- `ManualPrefix` ← DmShared.xNull(txtManualPrefix.getText())
- `Format` ← DmShared.xNull(txtFormatDigits.getText())
- `FormulaString` ← DmShared.xNull(txtFormulaString.getText())
- `FormulaString` ← DmShared.xNull(txtFormulaString.getText())
- `StartDate` ← DmShared.dNull(txtStartDate.getText())
- `EndDate` ← DmShared.dNull(txtEndDate.getText())
- `YearPrefix` ← DmShared.xNull(txtYearPrefix.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtHProductType.setTag` ← mrTemp.getProductTypeTag()
- `txtHProductType.setText` ← mrTemp.getProductTypeText()
- `txtHProductType.setTag2nd` ← mrTemp.getProductTypeTag()
- `txtHFinancialYear.setTag` ← mrTemp.getFinancialYearTag()
- `txtHFinancialYear.setText` ← mrTemp.getFinancialYearText()
- `txtHFinancialYear.setTag2nd` ← mrTemp.getFinancialYearTag()
- `txtYearPrefix.setText` ← mrTemp.getYearPrefix()
- `txtStartDate.setText` ← mrTemp.getStartDate()
- `txtEndDate.setText` ← mrTemp.getEndDate()
- `txtHMachinePrefixYN.setTag` ← mrTemp.getMachinePrefixYTag()
- `txtHMachinePrefixYN.setText` ← mrTemp.getMachinePrefixYText()
- `txtHMachinePrefixYN.setTag2nd` ← mrTemp.getMachinePrefixYTag()
- `txtHMachine.setTag` ← mrTemp.getMachineCode()
- `txtHMachine.setText` ← mrTemp.getMachineName()
- `txtHMachine.setTag2nd` ← mrTemp.getMachineCode()
- `txtManualPrefix.setText` ← mrTemp.getManualPrefix()
- `txtHPrefixType.setTag` ← mrTemp.getPrefixTypeTag()
- `txtHPrefixType.setText` ← mrTemp.getPrefixTypeText()
- `txtHPrefixType.setTag2nd` ← mrTemp.getPrefixTypeTag()
- `txtFormatDigits.setText` ← mrTemp.getFormat()
- `txtFormulaString.setText` ← mrTemp.getFormulaString()
- `userControl.setTextBlog` ← preparedByBlog, mrTemp.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, mrTemp.getSharedModifiedBy()
- userControl.setTextBlog(preparedByBlog, mrTemp.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, mrTemp.getSharedModifiedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtYearPrefix.setEnabled(false)`
- `txtStartDate.setEnabled(false)`
- `txtEndDate.setEnabled(false)`
- `txtHProductType.setEnabled(false)`
- `txtHFinancialYear.setEnabled(false)`
- `txtHMachinePrefixYN.setEnabled(false)`
- `txtHMachine.setEnabled(false)`
- `txtManualPrefix.setEnabled(false)`
- `txtHPrefixType.setEnabled(false)`
- `txtFormatDigits.setEnabled(false)`
- `txtFormulaString.setEnabled(false)`

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
txtYearPrefix.setEnabled(false);
txtStartDate.setEnabled(false);
txtEndDate.setEnabled(false);
if (DmShared.xNull(topControl.getMode()).equalsIgnoreCase("Edit")) {
txtHProductType.setEnabled(false);
txtHFinancialYear.setEnabled(false);
txtHMachinePrefixYN.setEnabled(false);
txtHMachine.setEnabled(false);
txtManualPrefix.setEnabled(false);
txtHPrefixType.setEnabled(false);
txtFormatDigits.setEnabled(false);
txtFormulaString.setEnabled(false);
manageMachinePrefixYN();
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`gui.addCallback` → `getGUITemplate()`
```java
return guiTemplateCode;
```

`gui.addCallback` → `addCustom()`
```java
return null;
```

`gui.addCallback` → `guiDataCopied()`
```java
if (gui_Mdl != null) {
gui.initialize(gui_Mdl);
```

`topControl.addNewHandeller` → `newClicked()`
```java
onAdd();
```

`topControl.addEditHandeller` → `editClicked()`
```java
grdItemDetail.getGrid().getStore().add(new GridEdit_Mdl());
manageDisplay(false);
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
saveValidation();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(PrefixCounterMaster.this);
```

`txtHProductType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getProductType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFie
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHProductType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHProductType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHProductType).show();
```

`txtHProductType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHProductType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHProductType).show();
```

`txtHProductType.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHProductType.getTag()).equalsIgnoreCase(DmShared.xNull(txtHProductType.getTag2nd()))) {
txtHProductType.setTag2nd(DmShared.xNull(txtHProductType.getTag()));
clearGrid();
```

`txtHFinancialYear.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtFinancialYear(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<T
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHFinancialYear).show();
public void onSuccess(DmResultPageing<List<TxtHFinancialYear_Mdl>> result) {
if (result.isStatus()) {
txtHFinancialYear.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHFinancialYear).show();
```

`txtHFinancialYear.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHFinancialYear.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHFinancialYear).show();
```

`txtHFinancialYear.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHFinancialYear.getTag()).equalsIgnoreCase(DmShared.xNull(txtHFinancialYear.getTag2nd()))) {
txtHFinancialYear.setTag2nd(DmShared.xNull(txtHFinancialYear.getTag()));
resetFinancialYear();
if (blnStatus && t1Row != null) {
txtYearPrefix.setText(DmShared.xNull(t1Row.getYearPrefix()));
txtStartDate.setText(DmShared.dNull(t1Row.getStartDate()));
txtEndDate.setText(DmShared.dNull(t1Row.getEndDate()));
```

`txtHMachinePrefixYN.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtMachinePrefixYN(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHMachinePrefixYN).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHMachinePrefixYN.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHMachinePrefixYN).show();
```

`txtHMachinePrefixYN.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHMachinePrefixYN.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHMachinePrefixYN).show();
```

`txtHMachinePrefixYN.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHMachinePrefixYN.getTag()).equalsIgnoreCase(DmShared.xNull(txtHMachinePrefixYN.getTag2nd()))) {
txtHMachinePrefixYN.setTag2nd(DmShared.xNull(txtHMachinePrefixYN.getTag()));
resetMachinePrefixYN();
manageMachinePrefixYN();
```

`txtHMachine.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHMachine(txtHProductType.getTag(), txtHFinancialYear.getTag(), txtHPrefixType.
helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExtended>>>() {
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHMachine).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHMachine.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHMachine).show();
```

`txtHMachine.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHMachine.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHMachine).show();
```

`txtHMachine.addTextHelpMultiCallBack` → `getSelection()`
```java
if (!DmShared.xNull(txtHMachine.getTag()).equalsIgnoreCase(DmShared.xNull(txtHMachine.getTag2nd()))) {
txtHMachine.setTag2nd(DmShared.xNull(txtHMachine.getTag()));
clearGrid();
```

`txtHPrefixType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtPrefixType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyF
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHPrefixType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHPrefixType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPrefixType).show();
```

`txtHPrefixType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHPrefixType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPrefixType).show();
```

`txtHPrefixType.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHPrefixType.getTag()).equalsIgnoreCase(DmShared.xNull(txtHPrefixType.getTag2nd()))) {
txtHPrefixType.setTag2nd(DmShared.xNull(txtHPrefixType.getTag()));
grdItemDetail.getGrid().getStore().clear();
```

`formulaPopUp.addCallBack` → `onOk()`
```java
txtFormulaString.setText(DmShared.xNull(formulaString));
btnGenCounter.setFocus(true);
```

`formulaPopUp.addCallBack` → `onClose()`
```java
btnGenCounter.setFocus(true);
```

`txtFormulaString.addClickHandler` → `onClick()`
```java
showFormulaPopUp();
```

**Top control options:** setOtherRequired(true), setRefreshRequired(false), setDisplay(true)

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."

## Files
| file | lines |
|---|---|
| client/FormulaPopUp.java | 188 |
| client/FormulaPopUp_Dsg.java | 154 |
| client/PrefixCounterMaster.java | 882 |
| client/PrefixCounterMaster_Dsg.java | 561 |
| server/PrefixCounterMaster_SrvImpl.java | 1104 |
| shared/Find_Mdl.java | 195 |
| shared/FormulaPopUp_Mdl.java | 23 |
| shared/GridEdit_Mdl.java | 167 |
| shared/Param_Mdl.java | 104 |
| shared/PrefixCounterMaster_Mdl.java | 218 |
| shared/PrefixCounterMaster_Srv.java | 43 |
| shared/PrefixCounterMaster_SrvAsync.java | 43 |
| shared/PrefixCounterMaster_Vld.java | 83 |
| shared/TxtHFinancialYear_Mdl.java | 41 |

## UI labels / columns (client)
`Type`, `Financial Year`, `Year Prefix`, `Start Date`, `End Date`, `MachinePrefixY/N`, `Machine`, `Manual Prefix`, `Prefix Type`, `Format`, `Formula String`, `Formula`

## Models (shared DTOs)
### GridEdit_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | formulaString |
| String | financialYearName |
| String | yearPrefix |
| String | startDate |
| String | endDate |
| String | machinePrefixYNTag |
| String | machinePrefixYNText |
| String | machineCode |
| String | machineName |
| String | manualPrefix |
| String | monthPrefix |
| String | prefixTypeTag |
| String | prefixTypeText |
| String | format |
| String | counter |
| String | isStatus |
| String | vSno |

### TxtHFinancialYear_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | yearPrefix |
| String | startDate |
| String | endDate |

### Param_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | yearPrefix |
| String | startDate |
| String | endDate |
| String | financialYear |
| String | machineCode |
| String | machineName |
| String | manualPrefix |
| String | prefixTypeTag |
| String | prefixTypeText |
| String | format |

### PrefixCounterMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | productTypeTag |
| String | productTypeText |
| String | machinePrefixYTag |
| String | machinePrefixYText |
| String | machineCode |
| String | machineName |
| String | manualPrefix |
| String | prefixTypeTag |
| String | prefixTypeText |
| String | financialYearTag |
| String | financialYearText |
| String | startDate |
| String | endDate |
| String | format |
| String | counter |
| String | yearPrefix |
| String | formulaString |
| String | monthPrefix |
| String | process |
| String | pTypeName |
| List<GridEdit_Mdl> | grdItemDetail |
| ModifyReason_Mdl | modifyReasonMdl |

### FormulaPopUp_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | formulaString |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | productTypeCode |
| String | productTypeName |
| String | startDate |
| String | endDate |
| String | financialYearCode |
| String | financialYearName |
| String | machinePrefixYCode |
| String | machinePrefixYName |
| String | machineCode |
| String | machineName |
| String | prefixTypeCode |
| String | prefixTypeName |
| String | manualPrefix |
| String | yearPrefix |
| String | monthPrefix |
| String | format |
| String | counter |
| String | formulaString |
| String | process |
| String | pTypeName |

## RPC interface
| method | params | returns |
|---|---|---|
| getFind | UserPermission userPermission, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| saveRecord | PrefixCounterMaster_Mdl mrTemp, String voucherPrefix | DmResult<String> |
| getNavigator | UserPermission userPermission | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<PrefixCounterMaster_Mdl> |
| deleteRecord | String strKey, ModifyReason_Mdl reasonInfo_Mdl, String guiConfigCode | DmResult<String> |
| getServerDateTime |  | DmResult<String[]> |
| getTxtMachinePrefixYN | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHMachine | String productTypeCode, String financialYearCode, String prefixTypeCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtPrefixType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getProductType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtFinancialYear | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHFinancialYear_Mdl>> |
| fillFormula |  | DmResult<List<FormulaPopUp_Mdl>> |
| generateCounter | Param_Mdl param_Mdl | DmResult<List<GridEdit_Mdl>> |

## Validator `PrefixCounterMaster_Vld`
```java
public class PrefixCounterMaster_Vld {
    public static void validateHeader(PrefixCounterMaster_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getProductTypeTag(), "Type", 90);
        DmShared.isValidText(mrTemp.getFinancialYearTag(), "Financial Year", 100);
        if (DmShared.iNull(mrTemp.getMachinePrefixYTag()).intValue() == 1) {
            DmShared.isValidText(mrTemp.getMachineCode(), "Machine", 200);
        }
        DmShared.isValidText(mrTemp.getManualPrefix(), "Manual Prefix", 300);
        DmShared.isValidText(mrTemp.getPrefixTypeTag(), "Prefix Type", 400);
        DmShared.isValidText(mrTemp.getFormat(), "Format", 500);
        DmShared.isValidText(mrTemp.getFormulaString(), "Formula String", 600);
    }
    public static void chkSave(PrefixCounterMaster_Mdl mrTemp) {
        validateHeader(mrTemp);
        List<GridEdit_Mdl> listItemDetail = mrTemp.getGrdItemDetail();
        boolean flag = false;
        for (GridEdit_Mdl detail_Mdl : listItemDetail) {
            if (!DmShared.xNull(detail_Mdl.getYearPrefix()).equalsIgnoreCase("")) {
                flag = true;
                if (!DmShared.xNull(detail_Mdl.getYearPrefix()).equalsIgnoreCase("")) {
                    if (DmShared.xNull(detail_Mdl.getYearPrefix()).equalsIgnoreCase("")) {
                        throw new DmException(800, "GridRow", DmShared.xNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Year Prefix is mandatory in line no. " + DmShared.xNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (!DmShared.xNull(detail_Mdl.getMonthPrefix()).equalsIgnoreCase("")) {
                    if (DmShared.xNull(detail_Mdl.getMonthPrefix()).equalsIgnoreCase("")) {
                        throw new DmException(800, "GridRow", DmShared.xNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Month Prefix is mandatory in line no. " + DmShared.xNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (!DmShared.xNull(detail_Mdl.getManualPrefix()).equalsIgnoreCase("")) {
                    if (DmShared.xNull(detail_Mdl.getManualPrefix()).equalsIgnoreCase("")) {
                        throw new DmException(800, "GridRow", DmShared.xNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Manual Prefix is mandatory in line no. " + DmShared.xNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (!DmShared.dNull(detail_Mdl.getStartDate()).equalsIgnoreCase("")) {
                    if (DmShared.dNull(detail_Mdl.getStartDate()).equalsIgnoreCase("")) {
                        throw new DmException(800, "GridRow", DmShared.xNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Start Date is mandatory in line no. " + DmShared.xNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (!DmShared.dNull(detail_Mdl.getEndDate()).equalsIgnoreCase("")) {
                    if (DmShared.dNull(detail_Mdl.getEndDate()).equalsIgnoreCase("")) {
                        throw new DmException(800, "GridRow", DmShared.xNull(listItemDetail.indexOf(detail_Mdl)).toString(), "End Date is mandatory in line no. " + DmShared.xNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (!DmShared.xNull(detail_Mdl.getFormat()).equalsIgnoreCase("")) {
                    if (DmShared.xNull(detail_Mdl.getFormat()).equalsIgnoreCase("")) {
                        throw new DmException(800, "GridRow", DmShared.xNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Format is mandatory in line no. " + DmShared.xNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (!DmShared.xNull(detail_Mdl.getPrefixTypeTag()).equalsIgnoreCase("")) {
                    if (DmShared.xNull(detail_Mdl.getPrefixTypeTag()).equalsIgnoreCase("")) {
                        throw new DmException(800, "GridRow", DmShared.xNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Prefix Type is mandatory in line no. " + DmShared.xNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (!DmShared.xNull(detail_Mdl.getMachineCode()).equalsIgnoreCase("")) {
                    if (DmShared.xNull(detail_Mdl.getMachineCode()).equalsIgnoreCase("")) {
                        throw new DmException(800, "GridRow", DmShared.xNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Machine is mandatory in line no. " + DmShared.xNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
            }
        }
        if (!flag) {
            throw new DmException(800, "Prefix Counter Details are mandatory.");
        }
    }
}
```

## Server `PrefixCounterMaster_SrvImpl.java` (1104 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getParamList`, `DmServer.getSerialNoInteger`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getUserWithDateTime`, `DmServer.setSsnOldData`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.vNull`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(UserPermission userPermission)`

- SQL 1: `SELECT ppm.code FROM productPrefixMast ppm WHERE ppm.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getMoveRec`
`public DmResult<PrefixCounterMaster_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT de.code, de.productType, de.financialYear, de.machinePrefixYN, de.intFormat, de.formulaString, de.site_Code, de.manualPrefix, aim.code AS machineCode, aim.name AS machinName, de.prefixType, de.StartDate, de.EndDate, de.YearPrefix FROM ProductPrefixMast de LEFT JOIN assetItemMast aim ON aim.code = ''/*de.machineCode*/ WHERE de.code = :code`
- SQL 2: `SELECT STRING_AGG(cte.machineCode, ',') AS machineCodeCSV, STRING_AGG(cte.machineName, ',') AS machineNameCSV FROM ( SELECT MAX(ppd.machineCode) AS machineCode, MAX(aim.name) AS machineName FROM productPrefixDetail ppd LEFT JOIN assetItemMast aim ON aim.code = ppd.machineCode WHERE ppd.code = :code GROUP BY ppd.machineCode ) cte`
- SQL 3: `SELECT ppd.Code , ppd.V_SNo , ppm.YearPrefix , ppd.MonthPrefix , ppm.ManualPrefix , ppm.machinePrefixYN , ppm.FormulaString , ppm.IntFormat , ppd.Counter , ppd.StartDate , ppd.EndDate , ppd.machineCode , ait.name AS machineName , ppm.PrefixType FROM productPrefixMast ppm LEFT JOIN productPrefixDetail ppd ON ppd.code = ppm.code LEFT JOIN assetItemMast ait ON ait.code = ppd.MachineCode WHERE ppm.code = :code ORDER BY ppd.V_SNo`
- Params: `:code` ← `strKey`; `:code` ← `strKey`; `:code` ← `strKey`
- Other calls: `ProductType.getViaCode`

### `deleteRecord`
`public DmResult<String> deleteRecord(String keyCode, ModifyReason_Mdl reasonInfo_Mdl, String guiConfigCode)`

- SQL 1: `SELECT SUM(ppd.counter) AS counter FROM productPrefixDetail ppd where code = :code`
- SQL 2: `Delete From ProductPrefixDetail Where code = :code`
- SQL 3: `Delete From ProductPrefixMast Where code = :code`
- Params: `:code` ← `DmShared.xNull(keyCode)`; `:code` ← `DmShared.iNull(keyCode).toString()`; `:code` ← `DmShared.iNull(keyCode).toString()`
- Errors: "Document is used!"

### `saveRecord`
`public DmResult<String> saveRecord(PrefixCounterMaster_Mdl mrTemp, String voucherPrefix)`

- SQL 1: `SELECT COUNT(*) FROM productPrefixMast ppm LEFT JOIN productPrefixDetail ppd ON ppd.code = ppm.code WHERE ppm.site_code = :site_code AND ppm.productType = :productType AND ppm.financialYear = :financialYear AND ppm.prefixType = :prefixType AND ppd.machineCode IN ( {…} :machineCode {…} )`
- SQL 2: `INSERT INTO productPrefixMast (code, productType, financialYear, machinePrefixYN , intFormat, formulaString, site_Code, manualPrefix, prefixType, StartDate, EndDate, YearPrefix) VALUES (:code, :productType, :financialYear, :machinePrefixYN , :intFormat, :formulaString, :site_Code, :manualPrefix, :prefixType, :StartDate, :EndDate, :YearPrefix)`
- SQL 3: `UPDATE productPrefixMast SET Code = :Code , ProductType = :ProductType , StartDate = :StartDate , EndDate = :EndDate , PrefixType = :PrefixType , machinePrefixYN = :machinePrefixYN , IntFormat = :IntFormat , FinancialYear = :FinancialYear , ManualPrefix = :ManualPrefix , YearPrefix = :YearPrefix , FormulaString = :FormulaString , Site_Code = :Site_Code WHERE Code = :Code`
- SQL 4: `DELETE FROM ProductPrefixDetail WHERE Code = :Code`
- SQL 5: `INSERT INTO ProductPrefixDetail (code, v_SNo, startDate, endDate, monthPrefix, counter, machineCode, site_Code) VALUES (:code, :v_SNo, :startDate, :endDate, :monthPrefix, :counter, :machineCode, :site_Code)`
- Params: `:site_code` ← `DmServer.getSsnSiteCode(this)`; `:productType` ← `DmShared.xNull(mrTemp.getProductTypeTag())`; `:financialYear` ← `DmShared.xNull(mrTemp.getFinancialYearTag())`; `:prefixType` ← `DmShared.xNull(mrTemp.getPrefixTypeTag())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:productType` ← `DmShared.xNull(mrTemp.getProductTypeTag())`; `:financialYear` ← `DmShared.xNull(mrTemp.getFinancialYearTag())`; `:machinePrefixYN` ← `DmShared.xNull(mrTemp.getMachinePrefixYTag())`; `:intFormat` ← `DmShared.xNull(mrTemp.getFormat())`; `:formulaString` ← `DmShared.xNull(mrTemp.getFormulaString())`; `:manualPrefix` ← `DmShared.xNull(mrTemp.getManualPrefix())`; `:prefixType` ← `DmShared.xNull(mrTemp.getPrefixTypeTag())`; `:startDate` ← `DmServer.convertDate(mrTemp.getStartDate())`; `:endDate` ← `DmServer.convertDate(mrTemp.getEndDate())`; `:formulaString` ← `DmShared.xNull(mrTemp.getFormulaString())`; `:YearPrefix` ← `DmShared.xNull(mrTemp.getYearPrefix())`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:Code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:productType` ← `DmShared.xNull(mrTemp.getProductTypeTag())`; `:StartDate` ← `DmShared.xNull(mrTemp.getStartDate())`; `:EndDate` ← `DmShared.xNull(mrTemp.getEndDate())`; `:prefixType` ← `DmShared.xNull(mrTemp.getPrefixTypeTag())`; `:machinePrefixYN` ← `DmShared.xNull(mrTemp.getMachinePrefixYTag())`; `:intFormat` ← `DmShared.xNull(mrTemp.getFormat())`; `:financialYear` ← `DmShared.xNull(mrTemp.getFinancialYearTag())`; `:manualPrefix` ← `DmShared.xNull(mrTemp.getManualPrefix())`; `:YearPrefix` ← `DmShared.xNull(mrTemp.getYearPrefix())`; `:formulaString` ← `DmShared.xNull(mrTemp.getFormulaString())`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:Code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:v_SNo` ← `DmShared.iNull(detail_Mdl.getvSno()).intValue()`; `:startDate` ← `DmShared.xNull(detail_Mdl.getStartDate())`; `:endDate` ← `DmShared.xNull(detail_Mdl.getEndDate())`; `:monthPrefix` ← `DmShared.xNull(detail_Mdl.getMonthPrefix())`; `:counter` ← `DmShared.xNull(detail_Mdl.getCounter())`; `:machineCode` ← `DmShared.foriegnKey(detail_Mdl.getMachineCode())`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Errors: "Duplicate Record!"

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(UserPermission userPermission, DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT ppm.code, ppm.productType ,ppm.financialYear, ppm.machinePrefixYN, ppm.intFormat ,ppm.formulaString,ppm.site_Code ,ppm.manualPrefix , ppm.prefixType , CASE WHEN ppm.prefixType='Y' THEN 'Yearly' WHEN ppm.prefixType='M' THEN 'Monthly' WHEN ppm.prefixType='D' THEN 'Daily' END AS prefixTypeName ,ppm.StartDate ,ppm.EndDate ,ppm.YearPrefix FROM ProductPrefixMast ppm WHERE ppm.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `ProductType.getViaCode`

### `getServerDateTime`
`public DmResult<String[]> getServerDateTime()`


### `fillFormula`
`public DmResult<List<FormulaPopUp_Mdl>> fillFormula()`


### `getTxtMachinePrefixYN`
`public DmResultPageing<List<KeyFieldExtended>> getTxtMachinePrefixYN(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHMachine`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHMachine(String productTypeCode, String financialYearCode, String prefixTypeCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT aim.code, aim.name, aim.manualCode FROM assetItemMast aim LEFT JOIN ( SELECT ppd.code, ppd.machineCode FROM productPrefixDetail ppd LEFT JOIN productPrefixMast ppm ON ppm.code = ppd.code WHERE ppm.productType = :productType AND ppm.financialYear = :financialYear AND ppm.prefixType = :prefixType ) AS Vppd ON Vppd.machineCode = aim.code WHERE Vppd.code IS NULL`
- Params: `:productType` ← `DmShared.xNull(productTypeCode)`; `:financialYear` ← `DmShared.xNull(financialYearCode)`; `:prefixType` ← `DmShared.xNull(prefixTypeCode)`

### `getProductType`
`public DmResultPageing<List<KeyFieldExtended>> getProductType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `ProductType.values`

### `getTxtPrefixType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtPrefixType(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtFinancialYear`
`public DmResultPageing<List<TxtHFinancialYear_Mdl>> getTxtFinancialYear(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT year As financeYear, code As yearPrefix, startDate, endDate FROM yearPrefixMast`

### `generateCounter`
`public DmResult<List<GridEdit_Mdl>> generateCounter(Param_Mdl param_Mdl)`


### `fillGridData`
`private List<GridEdit_Mdl> fillGridData(List<GridEdit_Mdl> lstTemp, Param_Mdl param_Mdl, String machineCode, String machineName) throws Exception`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGUI.client`, `dataman.dmToolkit.dmGUI.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextArea.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| deleteRecord | `if (DmShared.vNull(rsTemp.getString("counter")).doubleValue() > 0)` | "Document is used!" |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("")).intValue() > 0)` | "Duplicate Record!" |

## Notes (hand-written)
_No hand-written notes yet._
